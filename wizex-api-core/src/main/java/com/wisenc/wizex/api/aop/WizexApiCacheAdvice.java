package com.wisenc.wizex.api.aop;

import java.sql.SQLRecoverableException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wisenc.wizex.api.common.cache.WizexApiCacheManager;
import com.wisenc.wizex.framework.exception.WizexException;
import com.wisenc.wizex.framework.vo.RequestForCacheVO;
import com.wisenc.wizex.framework.vo.ResponseVO;

/**
 * Class Name : WizexApiCacheAdvice.java
 * Description : Cache를 적용하기 위한 AOP Advice.
 * Modification Information
 *
 *  수정일			수정자              수정내용
 *  -------------------------------------------------
 *  2022. 10. 18.   		김경석				최초생성
 *
 * @author 김경석
 * since 2022. 10. 18.
 * version 1.0
 */
public class WizexApiCacheAdvice {
	@Resource
	private WizexApiCacheManager	wizexApiCacheManager;

	/** The Constant LOGGER. */
	private static final Logger LOGGER	= LoggerFactory.getLogger(WizexApiCacheAdvice.class);

	/**
	 * 요청이 Cache 대상일 때
	 * 		- hit 		: 적재된 Cache의 정보를 반환(Controller로 요청을 넘기지 않음).
	 * 		- not hit	: 요청을 Controller로 전달하고 그 반환값을 받아 Cache에 적재.
	 *
	 * @param call
	 * @return
	 * @throws Throwable
	 */
	public Object cache(ProceedingJoinPoint call) throws Throwable {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

			String	txId	= (String)request.getAttribute("txId");	// 트랜잭션아이디. IfcLogFilter에서 생성됨.

			// /api/users.jsonapi 과 같은 형태.
			String	uri		= request.getRequestURI();
			String	cacheKey	= null;

			Cache	cache	= null;
			String	ifId	= this.getSvcTypeCd(uri);
			if (wizexApiCacheManager.isTarget(ifId)) {				// cache 사용
				cache	= wizexApiCacheManager.getCache(ifId);	// cache 객체

				cacheKey			= getCacheKey(call.getArgs());	// cache key를 작성.
				ValueWrapper	vw	= cache.get(cacheKey);				// cache를 탐색.

				if (LOGGER.isDebugEnabled())	LOGGER.debug("cacheKey] {}, vw] ", cacheKey, vw);

				if (vw != null) {	// hit 이면 바로 반환.
					ResponseVO	result	= (ResponseVO)vw.get();
					result.setHitCache(true);	// hitted.
					result.setTxId(txId);			// 트랜잭션아이디
					return result;
				}
			}

			// 메소드를 수행
			Object result = call.proceed();

			if (result instanceof ResponseVO)	((ResponseVO)result).setTxId(txId);	// 트랜잭션아이디를 설정.

			if (!StringUtils.isEmpty(cacheKey)) {	// not hit이면 cache 적재
				cache.put(cacheKey, result);
			}

			return result;

		} catch (WizexException e) {
			throw e;
		} catch (SQLRecoverableException e) {
			LOGGER.error(e.getMessage(), e);
			throw new WizexException("wizex.error.code.nothing", new String[] {e.getMessage()});
		} catch (Throwable e) {
			LOGGER.error(e.getMessage(), e);
			throw new WizexException("wizex.error.code.nothing", new String[] {e.getMessage()});
			//throw new WizexException(e);
		}
	}

	/**
	 * 인터페이스아이디 추출.
	 *
	 * @param uri
	 * @return
	 */
	private String getSvcTypeCd(String uri) {
		int		stxIdx	= uri.lastIndexOf("/");
		int		endIdx	= uri.lastIndexOf(".");

		return uri.substring(stxIdx + 1, endIdx);
	}

	/**
	 * create cache key.
	 *
	 * @param args
	 * @return
	 */
	private String getCacheKey(Object[] args) {
		if (args != null && args.length != 0) {
			for (Object arg : args) {
				if (arg instanceof RequestForCacheVO) {
					RequestForCacheVO	_arg	= (RequestForCacheVO)arg;
					return _arg.getCacheKey();
				}
			}
		}

		return String.valueOf(new SimpleKey(args).hashCode());
	}
}
