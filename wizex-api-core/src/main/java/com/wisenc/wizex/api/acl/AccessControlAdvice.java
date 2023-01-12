package com.wisenc.wizex.api.acl;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.wisenc.wizex.framework.exception.WizexAuthException;
import com.wisenc.wizex.framework.util.BeanUtil;
import com.wisenc.wizex.framework.util.HttpUtil;

/**
 * Class Name : AccessControlAdvice.java
 * Description : 접근제어. 등록된 IP에 대한 요청인지 확인.
 * 					config.properties 내의 다음 항목들을 사용함.
 * 						acl.use
 * 							false : 접근제어 비활성(모두허용).
 * 							true : 접근제어 활성.
 * 						acl.iplist
 * 							acl.user가 true일 때 접근을 허용할 ip 목록. ';'을 구분자로하여 접근 허용할 IP 목록을 기술.
 *
 * Modification Information
 *  수정일			수정자              수정내용
 *  -------------------------------------------------
 *  2022. 10. 18.   		김경석				최초생성
 *
 * @author 김경석
 * since 2022. 10. 18.
 * version 1.0
 */
public class AccessControlAdvice {
	
	private static final Logger	LOGGER	= LoggerFactory.getLogger(AccessControlAdvice.class);

	/** 접근제어 활성 여부 */
	@Value("#{config['acl.use']}")
	private boolean	use;

	/** 접근 허용할 IP 목록 */
	@Value("#{config['acl.iplist']}")
	private String		iplist;

	/** 접근 허용할 IP 목록의 빠른 탐색을 위해 Map으로 유지 */
	private Map<String, Boolean>	permitIpList;

	/**
	 * 초기화
	 */
	@PostConstruct
	public void init() {
		this.permitIpList	= new HashMap<String, Boolean>();

		// 접근 허용할 IP 목록의 빠른 탐색을 위해 Map으로 변환.
		if (!StringUtils.isEmpty(iplist)) {
			StringTokenizer	tokens	= new StringTokenizer(iplist, ";");
			while (tokens.hasMoreElements()) {
				this.permitIpList.put(tokens.nextToken(), true);
			}
		}
	}

	/**
	 * 접근제어
	 *
	 * @param call
	 * @return
	 * @throws Throwable
	 */
	public Object control(ProceedingJoinPoint call) throws Throwable {

		if (this.use) {		// ACL 활성.
			String uri = HttpUtil.getRequestURI();
			if (uri.endsWith(".jsonapi") || uri.endsWith(".xmlapi")) {	// API 요청에 대해서만 제어.
				String ip = HttpUtil.getClientIp();
				if( LOGGER.isDebugEnabled() ) {
					LOGGER.debug("ACL use=y, check ip : "+ip);
				}
				if (!this.permitIpList.containsKey(ip))
					throw new WizexAuthException("wizex.acl.notpermitted", new Object[] {ip});
			}
		}

		return call.proceed();
	}

}
