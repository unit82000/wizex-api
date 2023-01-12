package com.wisenc.wizex.api.admin.menu.service;

import java.util.ArrayList;
import java.util.List;

import com.wisenc.wizex.framework.vo.FieldPrinter;

/**
 * @Class Name : MenuVO.java
 * @Description :
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2022. 10. 27. 	유지우			최초생성
 *
 * @author 유지우
 * @since 2022. 10. 27.
 * @version 1.0
 * @see
 */
public class MenuVO extends FieldPrinter {

	/** 메뉴ID */
	private String menuId;

	/** 메뉴명 */
	private String menuNm;

	/** 메뉴URL */
	private String menuUrl;

	/** 상위메뉴ID */
	private String uperMenuId;

	/** 하위메뉴목록 */
	private List<MenuVO>	children	= new ArrayList<MenuVO>();

	/** 하위메뉴개수 */
	private int childCount;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getUperMenuId() {
		return uperMenuId;
	}

	public void setUperMenuId(String uperMenuId) {
		this.uperMenuId = uperMenuId;
	}

	public List<MenuVO> getChildren() {
		return children;
	}

	public void setChildren(List<MenuVO> children) {
		this.children = children;
	}

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

}
