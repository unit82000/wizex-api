package com.wisenc.wizex.api.exam.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wisenc.wizex.framework.vo.ResponseVO;

@XmlRootElement(name = "response")
public class ProductsVO extends ResponseVO implements Serializable {

	private static final long serialVersionUID = -2729149765803493763L;

	private List<ProductVO> products;

	@XmlElement(name = "product")
	public List<ProductVO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVO> products) {
		this.products = products;
	}
}
