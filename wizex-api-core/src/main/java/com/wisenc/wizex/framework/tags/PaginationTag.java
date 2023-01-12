package com.wisenc.wizex.framework.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.wisenc.wizex.framework.vo.SearchVO;

/**
 * @Class Name : PaginationTag.java
 * @Description : page navigation tag.
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
@SuppressWarnings("serial")
public class PaginationTag extends TagSupport {

	/** 페이징을 위한 정보 객체. */
	private SearchVO   searchVO;

	/** 페이지 번호 클릭시 호출할 script. */
	private String      script;

	/** The Constant LOGGER. */
	private static final Logger      LOGGER   = Logger.getLogger(PaginationTag.class);

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {

		StringBuilder   buffer   = new StringBuilder();

		// 처음 페이지로...
		buffer.append("<a href=\"#\" class=\"btn_paging first");
		if (searchVO.getCurrentPageNo() == 1) {
			buffer.append(" no_active\" onclick=\"return false;\"");
		} else {
			buffer.append("\" onclick=\"").append(script).append("('1'); return false;\"");
		}
		buffer.append(">처음페이지</a>");


		// 이전 페이지로...
		buffer.append("<a href=\"#\" class=\"btn_paging prev");
		if (searchVO.getFirstPageNo() <= 1) {
			buffer.append(" no_active\" onclick=\"return false;\"");
		} else {
			buffer.append("\" onclick=\"").append(script).append("('")
			.append(searchVO.getFirstPageNo() - 1).append("');return false;\"");
		}
		buffer.append(">이전페이지블럭</a>");


		for (int i = searchVO.getFirstPageNo(); i <= searchVO.getLastPageNo(); i++) {

			// 선택된 페이지
			if (i == searchVO.getCurrentPageNo()) {
				buffer.append("<span class=\"pos_page page_on\">").append(i).append("</span>");

				// 그 외 페이지
			} else {
				buffer.append("<a href=\"#\" class=\"pos_page\" onclick=\"")
				.append(script)
				.append("('").append(i).append("');return false;\">");
				buffer.append(i).append("</a>");
			}

		}

		// 다음 페이지로...
		buffer.append("<a href=\"#\" class=\"btn_paging next");
		if (searchVO.getLastPageNo() >= searchVO.getTotalPageCount()) {
			buffer.append(" no_active\" onclick=\"return false;\"");
		} else {
			buffer.append("\" onclick=\"").append(script).append("('")
			.append(searchVO.getLastPageNo() + 1).append("'); return false;\"");
		}
		buffer.append(">다음페이지블럭</a>");

		// 마지막 페이지로...
		buffer.append("<a href=\"#\" class=\"btn_paging last");
		if (searchVO.getCurrentPageNo() == searchVO.getTotalPageCount()) {
			buffer.append(" no_active\" onclick=\"return false;\"");
		} else {
			buffer.append("\" onclick=\"").append(script).append("('")
			.append(searchVO.getTotalPageCount()).append("'); return false;\"");
		}
		buffer.append(">마지막페이지</a>");

		try {
			this.pageContext.getOut().write(buffer.toString());
			this.pageContext.getOut().flush();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return SKIP_BODY;
	}

	/** Sets the search vo.
	 *
	 * @param searchVO
	 *            the new search vo
	 */
	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}

	/** Sets the script.
	 *
	 * @param script
	 *            the new script
	 */
	public void setScript(String script) {
		this.script = script;
	}

}
