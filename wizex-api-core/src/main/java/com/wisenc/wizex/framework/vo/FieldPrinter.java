package com.wisenc.wizex.framework.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Class Name : FieldPrinter.java
 * @Description : column 속성을 출력(logging 등)하기 위한 최상위 class.
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2019. 12. 20.   		최초생성
 *
 * @author 김경석
 * @since 2019. 12. 20.
 * @version 1.0
 * @see
 */
public class FieldPrinter {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
