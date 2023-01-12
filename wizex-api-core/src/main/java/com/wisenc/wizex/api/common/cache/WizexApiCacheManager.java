package com.wisenc.wizex.api.common.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import com.wisenc.wizex.mapper.wizexpom.cache.WizexApiCacheMapper;

/**
 * Class Name : WizexApiCacheManager.java
 * Description : API 요청/응답을 위한 cache 기능을 관리.
 * 					Cache의 동적생성을 위해 EhCacheCacheManager를 재정의 함.
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
public class WizexApiCacheManager extends EhCacheCacheManager {

	@Resource
	private WizexApiCacheMapper		wizexApiCacheMapper;

	private Map<String, Boolean>	targetCache;

	private static final Logger	LOGGER	= LoggerFactory.getLogger(WizexApiCacheManager.class);

	/**
	 * 캐시 대상 초기화
	 */
	@PostConstruct
	public void init() {
		List<String>	list	= wizexApiCacheMapper.listCache();
		this.targetCache		= new HashMap<String, Boolean>();
		for (String c : list) {
			this.targetCache.put(c, true);
		}

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Target Cache : {}", this.targetCache);
		}
	}

	/**
	 * 캐시 대상 여부
	 *
	 * @param svcTypeCd
	 * @return
	 */
	public boolean isTarget(String svcTypeCd) {
		return this.targetCache.containsKey(svcTypeCd);
	}

	/**
	 * 캐시를 획득.
	 */
	@Override
	public Cache getCache(String name) {
	    Cache cache = super.getCache(name);
	    if (cache == null) {
	    	if (LOGGER.isInfoEnabled())
	    		LOGGER.info("No cache with name {} is defined in encache.xml. Hence creating the cache dynamically...", name);

	        getCacheManager().addCache(name);
	        cache	= super.getMissingCache(name);

	    	if (LOGGER.isInfoEnabled())
	    		LOGGER.info("Cache with name {} is created dynamically...", name);
	    }

	    return cache;
	}

	/**
	 * 전체 cache clear
	 */
	public void clearCache() {
		Iterator<String>	caches	= this.targetCache.keySet().iterator();
		while (caches.hasNext()) {
			this.clearCache(caches.next());
		}

		if (LOGGER.isDebugEnabled()) LOGGER.debug("All caches cleared.");
	}

	/**
	 * cache clear
	 *
	 * @param name
	 */
	public void clearCache(String name) {
	    Cache cache = super.getCache(name);
		if (cache != null) {
			cache.clear();
			if (LOGGER.isDebugEnabled()) LOGGER.debug(name + " cache cleared.");
		}
	}
}
