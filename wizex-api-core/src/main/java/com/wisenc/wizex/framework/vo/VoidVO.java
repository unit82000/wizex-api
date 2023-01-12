package com.wisenc.wizex.framework.vo;

/**
 * @Class Name : VoidVO.java
 * @Description : ajax 호출 후 특별한 반환값이 없을 경우 사용.
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2020. 1. 7.   		최초생성
 *
 * @author 김경석
 * @since 2020. 1. 7.
 * @version 1.0
 * @see
 */
public class VoidVO extends FieldPrinter {

	/** 결과 메시지 */
	private String message;

	public VoidVO() {}

	public VoidVO(String message) {
		this.message	= message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
