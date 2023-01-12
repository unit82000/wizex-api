package com.wisenc.wizex.framework.vo;

/**
 * cache 대상 요청용 parameter vo.
 *
 * @Class Name : RequestForCacheVO.java
 * @Description :
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2022. 9. 19.   		최초생성
 *
 * @author 김경석
 * @since 2022. 9. 19.
 * @version 1.0
 * @see
 */
public abstract class RequestForCacheVO {

	/**
	 * get cache key source.
	 *
	 * @return
	 */
	public abstract String getCacheKeySource();

	/**
	 * get cache key.
	 *
	 * @return
	 */
	public String getCacheKey() {
		return String.valueOf(this.getCacheKeySource().hashCode());
	}
}
