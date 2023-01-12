package com.wisenc.wizex.framework.exception;

/**
 * @Class Name : WizexException.java
 * @Description : 최상위 exception.
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
public class WizexException extends RuntimeException {

	private static final long serialVersionUID = 5704615757996216534L;

	/** 오류 코드 */
	private String		errorCode;

	/** 메시지 작성에 사용할 parameter */
	private Object[]	messageParams;

	public WizexException(Throwable e) {
		super(e);
	}

	public WizexException(String errorCode) {
		this.errorCode	= errorCode;
	}

	public WizexException(String errorCode, Object[] messageParams) {
		this.errorCode		= errorCode;
		this.messageParams	= messageParams;
	}

	public WizexException(String errorCode, Throwable e) {
		super(e);
		this.errorCode	= errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Object[] getMessageParams() {
		return messageParams;
	}

	public void setMessageParams(Object[] messageParams) {
		this.messageParams = messageParams;
	}
}
