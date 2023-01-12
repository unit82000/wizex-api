package com.wisenc.wizex.framework.exception;

/**
 * @Class Name : WisencAuthException.java
 * @Description : 접근 권한 예외.
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
public class WizexAuthException extends WizexException {

	private static final long serialVersionUID = 5704615757996216534L;

	public WizexAuthException(Throwable e) {
		super(e);
	}

	public WizexAuthException(String errorCode) {
		super(errorCode);
	}

	public WizexAuthException(String errorCode, Object[] messageParams) {
		super(errorCode, messageParams);
	}

	public WizexAuthException(String errorCode, Throwable e) {
		super(errorCode, e);
	}
}
