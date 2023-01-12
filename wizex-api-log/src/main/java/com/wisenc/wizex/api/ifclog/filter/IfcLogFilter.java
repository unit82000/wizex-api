package com.wisenc.wizex.api.ifclog.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import com.wisenc.wizex.api.common.bean.WizexApiApplicationContext;
import com.wisenc.wizex.api.ifclog.vo.IfcLogVO;
import com.wisenc.wizex.framework.util.ConstantUtil;
import com.wisenc.wizex.framework.util.HttpUtil;
import com.wisenc.wizex.mapper.wizexapi.ifclog.IfcLogMapper;

/**
 * Class Name : IfcLogFilter.java
 * Description : 인터페이스 요청/응답을 기록.
 * Modification Information
 *
 *  수정일			수정자              수정내용
 *  -------------------------------------------------
 *  2022. 10. 6.   		김경석				최초생성
 *
 * @author 김경석
 * since 2022. 10. 6.
 * version 1.0
 */
public class IfcLogFilter implements Filter {

	/** Transaction ID Key */
	public static final String	TX_ID_KEY	= ConstantUtil.TX_ID_KEY;

	private static final Logger LOGGER = LoggerFactory.getLogger(IfcLogFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

			IfcLogVO	ifcLogVO	= new IfcLogVO();	// 인터페이스 로그 객체.

			((HttpServletRequest) req).setAttribute(TX_ID_KEY, ifcLogVO.getTxId());

			// request, response를 여러번 읽기 위해 wrapper로 작성.
			ContentCachingRequestWrapper 	reqWrap 	= new ContentCachingRequestWrapper((HttpServletRequest) req);
			ContentCachingResponseWrapper	resWrap	 	= new ContentCachingResponseWrapper((HttpServletResponse) res);

			// 계속 진행.
			chain.doFilter(reqWrap, resWrap);

			String	uri		= HttpUtil.getRequestURI((HttpServletRequest) req);
			if (uri.endsWith(".xmlapi") || uri.endsWith(".jsonapi")) {
				String	ifcId	= uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));	// 인터페이스 아이디를 자름. /api/S00001.jsonapi 형식.

				// 수행이 끝난 후 로그 기록.
				ifcLogVO.setIfcId(ifcId);
				ifcLogVO.setReqIp(HttpUtil.getClientIp((HttpServletRequest) req));
				ifcLogVO.setResDt(new Date());
				ifcLogVO.setPrcTm(ifcLogVO.getResDt().getTime() - ifcLogVO.getReqDt().getTime());
				ifcLogVO.setReqMsg(this.getRequestBody(reqWrap));
				ifcLogVO.setResMsg(this.getResponseBody(resWrap));

				IfcLogMapper	mapper	= (IfcLogMapper)WizexApiApplicationContext.getBean(IfcLogMapper.class);

				mapper.insert(ifcLogVO);
			}
	}

	private String getRequestBody(ContentCachingRequestWrapper request) {
		ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
		if (wrapper != null) {
			byte[] buf = wrapper.getContentAsByteArray();
			if (buf.length > 0) {
				try {
					return new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
				} catch (UnsupportedEncodingException e) {
					LOGGER.error(e.getMessage(), e);
					return "";
				}
			}
		}
		return "";
	}

	private String getResponseBody(final HttpServletResponse response) throws IOException {
		String payload = null;
		ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
		if (wrapper != null) {
			wrapper.setCharacterEncoding("UTF-8");
			byte[] buf = wrapper.getContentAsByteArray();
			if (buf.length > 0) {
				payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
				wrapper.copyBodyToResponse();
			}
		}

		return null == payload ? "" : payload;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		LOGGER.info("IfcLogFilter init");
	}

	@Override
	public void destroy() {
	}
}
