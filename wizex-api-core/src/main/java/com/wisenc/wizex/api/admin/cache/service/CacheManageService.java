package com.wisenc.wizex.api.admin.cache.service;

/**
 * Class Name : CacheManageService.java
 * Description : API 요청/응답을 위한 cache 기능을 관리.
 * Modification Information
 *
 *  수정일			수정자              수정내용
 *  -------------------------------------------------
 *  2022. 10. 18.   		김경석				최초생성
 *
 * @author 김경석
 * since 2022. 10. 18.
 * version 1.0
 */
public interface CacheManageService {

	/**
	 * Cache를 초기화.
	 *
	 * @param name		cache name.
	 */
	public void clearCache(String name);
}
