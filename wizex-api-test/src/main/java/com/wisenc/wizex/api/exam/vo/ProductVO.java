package com.wisenc.wizex.api.exam.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
public class ProductVO implements Serializable {
	private static final long serialVersionUID = 3732112813106561620L;

	private String objCd;

	private String objNm;

	private String aplyStDt;

	private String aplyEdDt;

	@XmlElement
	public String getObjCd() {
		return objCd;
	}

	public void setObjCd(String objCd) {
		this.objCd = objCd;
	}

	@XmlElement
	public String getObjNm() {
		return objNm;
	}

	public void setObjNm(String objNm) {
		this.objNm = objNm;
	}

	@XmlElement
	public String getAplyStDt() {
		return aplyStDt;
	}

	public void setAplyStDt(String aplyStDt) {
		this.aplyStDt = aplyStDt;
	}

	@XmlElement
	public String getAplyEdDt() {
		return aplyEdDt;
	}

	public void setAplyEdDt(String aplyEdDt) {
		this.aplyEdDt = aplyEdDt;
	}

}
