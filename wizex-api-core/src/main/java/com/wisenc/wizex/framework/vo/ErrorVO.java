package com.wisenc.wizex.framework.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * @Class Name : ErrorVO.java
 * @Description : 오류처리를 위한 value object.
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2019. 12. 24.   		최초생성
 *
 * @author 김경석
 * @since 2019. 12. 24.
 * @version 1.0
 * @see
 */
public class ErrorVO extends FieldPrinter implements Serializable {
	private static final long serialVersionUID = 522297106288619441L;

	/** 오류 코드 */
	private String	errorCode;

	/** 오류 메시지 */
	private String	errorMessage;

	public ErrorVO() {
	}

	public ErrorVO(String errorCode, String errorMessage) {
		this.errorCode		= errorCode;
		this.errorMessage	= errorMessage;
	}

	@XmlElement
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@XmlElement
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
