package com.wisenc.wizex.api.exam.service;

import com.wisenc.wizex.api.exam.vo.ResponseTestVO;
import com.wisenc.wizex.framework.vo.ResponseVO;

public interface ResponseTestService {

	ResponseTestVO get();

	ResponseVO products();

	ResponseVO users();
}
