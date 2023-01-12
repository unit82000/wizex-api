package com.wisenc.wizex.api.exam.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EXAM0001")
public class ResponseTestVO {

	private String	name;

	private Integer	age;

	public ResponseTestVO() {
	}

	public ResponseTestVO(String name, Integer age) {
		this.name	= name;
		this.age	= age;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


}
