package com.wisenc.wizex.api.ifclog.vo;

import java.util.Date;
import java.util.UUID;

/**
 * Class Name : IfcLogVO.java
 * Description : 인터페이스 로그.
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
public class IfcLogVO {
	/** Transaction ID */
	private String txId;

	/** Interface ID */
	private String ifcId;

	/** 요청 메시지 */
	private String reqMsg;

	/** 응답 메시지 */
	private String resMsg;

	/** 요청일시 */
	private Date reqDt;

	/** 응답일시 */
	private Date resDt;

	/** 요청 IP */
	private String reqIp;

	/** 수행시간 */
	private Long	prcTm;

	public IfcLogVO() {
		this.txId		= createTxId();
		this.reqDt	= new Date();
	}

	public static final String createTxId() {
		return UUID.randomUUID().toString();
	}

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

	public Date getReqDt() {
		return reqDt;
	}

	public void setReqDt(Date reqDt) {
		this.reqDt = reqDt;
	}

	public Date getResDt() {
		return resDt;
	}

	public void setResDt(Date resDt) {
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
