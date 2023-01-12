package com.wisenc.wizex.mapper.wizexpom.cache;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface WizexApiCacheMapper {

	List<String> listCache();
}
