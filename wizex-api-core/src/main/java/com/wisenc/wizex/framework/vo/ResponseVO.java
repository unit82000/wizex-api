package com.wisenc.wizex.framework.vo;

/**
 * Class Name : ResponseVO.java
 * Description : API요청에 대한 응답객체.
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
public class ResponseVO extends FieldPrinter {
	/** Transaction ID */
	private String		txId;

	/** cache hit 여부 */
	private boolean	hitCache	= false;

	/** 오류정보 */
	private ErrorVO		error		= null;

	public boolean isHitCache() {
		return hitCache;
	}

	public void setHitCache(boolean hitCache) {
		this.hitCache = hitCache;
	}

	public ErrorVO getError() {
		return error;
	}

	public void setError(ErrorVO error) {
		this.error = error;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}
}
