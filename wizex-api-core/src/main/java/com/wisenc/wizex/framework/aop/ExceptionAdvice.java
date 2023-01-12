package com.wisenc.wizex.framework.aop;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import com.wisenc.wizex.framework.exception.WizexException;
import com.wisenc.wizex.framework.util.SessionUtil;
import com.wisenc.wizex.framework.vo.ErrorVO;
import com.wisenc.wizex.framework.vo.ResponseVO;

/**
 * @Class Name : ControllerAdvice.java
 * @Description : 예외처리 advice.
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2019. 12. 23.   		최초생성
 *
 * @author 김경석
 * @since 2019. 12. 23.
 * @version 1.0
 * @see
 */
public class ExceptionAdvice  {

	/** 오류 메시지 처리를 위한 message source */
	private MessageSource	messageSource;

	private static final Logger	LOGGER	= LoggerFactory.getLogger(ExceptionAdvice.class);

	/**
	 * 메소드의 호출 전 parameter 출력. 메소드의 수행 후 result 출력.
	 *
	 * @param call
	 *            the call
	 * @return the object
	 * @throws Throwable
	 *             the throwable
	 */
	public Object handleException(ProceedingJoinPoint call) throws Throwable {
		try {
			// 메소드를 수행
			return call.proceed();

		} catch (WizexException e) {
			return getResult(e);
		} catch (Throwable e) {
			LOGGER.error(e.getMessage(), e);
			return getResult(new WizexException(e));
		}
	}

	/**
	 * 예외에 따른 반환값 결정.
	 * ajax일 경우 ErrorVO, jsp일 경우 jsp 예외에 따라 설정된 오류 페이지를 반환.
	 *
	 * @param e
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object getResult(WizexException e) {
		String	errorCode	= e.getErrorCode();		// 오류 코드
		boolean	ajax		= true;	// ajax 요청 여부.
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("ajax is {}", ajax);
			LOGGER.debug("errorCode is {}", errorCode);
		}

		// 2022.10.26 양준모 추가 ----
		// ajax url 필터 분기처리에 따라 응답 객체 변경(web : responseEntity, 그 외 : responseVO)
		HttpServletRequest	request	= SessionUtil.getRequest();
		if("web".equals(request.getHeader("callType"))) {
			ErrorVO	errorVO	= new ErrorVO();
			errorVO.setErrorCode(errorCode);

			String	errorMessage	= messageSource.getMessage(errorCode, e.getMessageParams(), Locale.getDefault());
			errorVO.setErrorMessage(errorMessage);

			if (LOGGER.isDebugEnabled())
				LOGGER.debug("errorMessage is {}", errorMessage);

			return new ResponseEntity(errorVO, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			ErrorVO	errorVO	= new ErrorVO();
			if (StringUtils.isEmpty(errorCode)) errorCode	= "wizex.common.error.unknown";

			errorVO.setErrorCode(errorCode);

			String	errorMessage	= messageSource.getMessage(errorCode, e.getMessageParams(), Locale.getDefault());
			errorVO.setErrorMessage(errorMessage);

			if (LOGGER.isDebugEnabled())
				LOGGER.debug("errorMessage is {}", errorMessage);

			ResponseVO	response	= new ResponseVO();
			response.setError(errorVO);
			return response;
		}
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}