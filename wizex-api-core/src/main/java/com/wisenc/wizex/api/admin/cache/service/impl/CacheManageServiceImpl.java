package com.wisenc.wizex.api.admin.cache.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.wisenc.wizex.api.admin.cache.service.CacheManageService;
import com.wisenc.wizex.api.common.cache.WizexApiCacheManager;

/**
 * Class Name : CacheManageServiceImpl.java
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
@Service
public class CacheManageServiceImpl implements CacheManageService {

	@Resource
	private WizexApiCacheManager 	wizexApiCacheManager;

	@Override
	public void clearCache(String name) {
		if (StringUtils.isEmpty(name)) {
			wizexApiCacheManager.clearCache();
		} else {
			wizexApiCacheManager.clearCache(name);
		}
	}

}
