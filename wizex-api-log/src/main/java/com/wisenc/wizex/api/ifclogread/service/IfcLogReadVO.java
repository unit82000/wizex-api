package com.wisenc.wizex.api.ifclogread.service;

/**
 * @Class Name : IfcLogReadVO.java
 * @Description :
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2022. 10. 27.   유지우			최초생성
 *
 * @author 유지우
 * @since 2022. 10. 27.
 * @version 1.0
 * @see
 */
public class IfcLogReadVO {

	/** Transaction ID */
	private String txId;

	/** Interface ID */
	private String ifcId;

	/** 요청 메시지 */
	private String reqMsg;

	/** 응답 메시지 */
	private String resMsg;

	/** 요청일시 */
	private String reqDt;

	/** 요청일시 시작시간 */
	private String reqStDt;

	/** 요청일시 종료시간 */
	private String reqEdDt;

	/** 응답일시 */
	private String resDt;

	/** 요청 IP */
	private String reqIp;

	/** 수행시간 */
	private Long prcTm;

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getIfcId() {
		return ifcId;
	}

	public void setIfcId(String ifcId) {
		this.ifcId = ifcId;
	}

	public String getReqMsg() {
		return reqMsg;
	}

	public void setReqMsg(String reqMsg) {
		this.reqMsg = reqMsg;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getReqDt() {
		return reqDt;
	}

	public void setReqDt(String reqDt) {
		this.reqDt = reqDt;
	}

	public String getReqStDt() {
		return reqStDt;
	}

	public void setReqStDt(String reqStDt) {
		this.reqStDt = reqStDt;
	}

	public String getReqEdDt() {
		return reqEdDt;
	}

	public void setReqEdDt(String reqEdDt) {
		this.reqEdDt = reqEdDt;
	}

	public String getResDt() {
		return resDt;
	}

	public void setResDt(String resDt) {
		this.resDt = resDt;
	}

	public String getReqIp() {
		return reqIp;
	}

	public void setReqIp(String reqIp) {
		this.reqIp = reqIp;
	}

	public Long getPrcTm() {
		return prcTm;
	}

	public void setPrcTm(Long prcTm) {
		this.prcTm = prcTm;
	}

}
