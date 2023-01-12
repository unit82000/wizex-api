package com.wisenc.wizex.framework.vo;

import java.util.ResourceBundle;

/**
 * @Class Name : SearchVO.java
 * @Description : paging을 위한 최상위 class.
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
public class SearchVO extends FieldPrinter {

	/**
	 * 페이지 당 노출한 레코드 수
	 */
	private Integer recordCountPerPage;

	/**
	 * 페이지 네비게이션에 노출할 페이지 수
	 */
	private Integer pageBlocks;

	/**
	 * 검색된 총 레코드 수
	 */
	private Integer totalRecordCount;

	/**
	 * 선택된 페이지 번호
	 */
	private Integer currentPageNo;

	/**
	 * 검색조건 (검색 할 필드명)
	 */
	protected String searchKey;

	/**
	 * 검색조건 (검색 값)
	 */
	protected String searchValue;

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getPageBlocks() {
		return pageBlocks;
	}

	public void setPageBlocks(int pageBlocks) {
		this.pageBlocks = pageBlocks;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public int getTotalPageCount() {
		return totalRecordCount == 0 ? 1 : ((totalRecordCount - 1) / getRecordCountPerPage()) + 1;
	}

	public int getFirstPageNo() {
		return totalRecordCount == 0 ? 1 : ((currentPageNo - 1) / pageBlocks) * pageBlocks + 1;
	}

	public int getLastPageNo() {
		int lastPageNo = getFirstPageNo() + pageBlocks - 1;
		if (lastPageNo > getTotalPageCount()) {
			lastPageNo = getTotalPageCount();
		}

		return lastPageNo;
	}

	public int getFirstRecordIndex() {
		return (currentPageNo - 1) * recordCountPerPage;
	}

	public int getLastRecordIndex() {
		return currentPageNo * recordCountPerPage;
	}

	public Integer getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public SearchVO() {
		ResourceBundle bundle = ResourceBundle.getBundle("wizex.properties.config");

		this.recordCountPerPage = Integer.parseInt(bundle.getString("wizex.common.paging.recordCountPerPage"));
		this.pageBlocks = Integer.parseInt(bundle.getString("wizex.common.paging.pageBlocks"));
		this.totalRecordCount = 0;
		this.currentPageNo = 1;
	}
}
