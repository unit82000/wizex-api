package com.wisenc.wizex.api.cndrule.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.wisenc.wizex.api.cndrule.service.CndRuleService;
import com.wisenc.wizex.api.cndrule.vo.CndRuleFuncVO;
import com.wisenc.wizex.api.cndrule.vo.CndRuleParamVO;
import com.wisenc.wizex.api.cndrule.vo.CndRuleResponseVO;
import com.wisenc.wizex.api.cndrule.vo.CndRuleVO;
import com.wisenc.wizex.api.cndrule.vo.SvcCndRuleInVO;
import com.wisenc.wizex.api.common.javascript.service.JavascriptService;
import com.wisenc.wizex.framework.exception.WizexException;
import com.wisenc.wizex.framework.vo.ResponseVO;
import com.wisenc.wizex.mapper.wizexapi.cndrule.CndRuleMapper;

/**
 * Class Name : CndRuleServiceImpl.java
 * Description : 조건규칙 서비스.
 * Modification Information
 *
 *  수정일			수정자              수정내용
 *  -------------------------------------------------
 *  2022. 10. 3.   		김경석				최초생성
 *
 * @author 김경석
 * since 2022. 10. 3.
 * version 1.0
 */
@Service
public class CndRuleServiceImpl implements CndRuleService {

	@Resource
	private CndRuleMapper	cndRuleMapper;

	@Resource
	private JavascriptService	javascriptService;

	private static final Logger	LOGGER	= LoggerFactory.getLogger(CndRuleServiceImpl.class);

	/** 조건규칙 정보를 적재 */
	private Map<String, List<CndRuleVO>>	cndRules;

	/**
	 * 조건규칙 정보를 초기화.
	 */
	@Override
	@PostConstruct
	public void init() {
		CndRuleParamVO 	cndRuleParamVO	= new CndRuleParamVO();
		List<CndRuleVO>	list							= this.cndRuleMapper.listCndRule(cndRuleParamVO);	// 조건규칙 전체목록 조회.

		// 조건규칙 아이디에 대해 다수의 유효시종일이 존재할 수 있으므로 적당하게 가공.
		Map<String, List<CndRuleVO>>	map			= new HashMap<String, List<CndRuleVO>>();
		for (CndRuleVO cndRuleVO : list) {
			String					cndRuleCd	= cndRuleVO.getCndRuleCd();
			List<CndRuleVO>	rules			= null;
			if (map.containsKey(cndRuleCd))	rules	= map.get(cndRuleCd);
			else {
				rules	= new ArrayList<CndRuleVO>();
				map.put(cndRuleCd, rules);
			}

			rules.add(cndRuleVO);
		}

		this.cndRules	= map;

		LOGGER.info("조건규칙 초기화 완료.");
	}

	/**
	 * 조건규칙정보를 찾는다.
	 *
	 * @param cndRuleParamVO
	 * @return
	 */
	private CndRuleVO getCndRuleVO(CndRuleParamVO cndRuleParamVO) {
		if (StringUtils.isEmpty(cndRuleParamVO.getCndRuleCd()))	throw new WizexException("wizex.common.error.custom", new String[] {"조건규칙코드는 필수 입력값입니다."});
		if (StringUtils.isEmpty(cndRuleParamVO.getStDt()))			throw new WizexException("wizex.common.error.custom", new String[] {"유효일자는 필수 입력값입니다."});

		// 조건규칙코드에 맞는 목록을 조회
		List<CndRuleVO>	rules			= this.cndRules.get(cndRuleParamVO.getCndRuleCd());
		if (ObjectUtils.isEmpty(rules))			throw new WizexException("wizex.common.error.custom", new String[] {"유효하지 않은 조건규칙코드[" + cndRuleParamVO.getCndRuleCd() + "]입니다."});

		// 특정기간에 일치하는 조건규칙을 찾음
		String					stDt			= cndRuleParamVO.getStDt();
		for (CndRuleVO cndRuleVO : rules) {
			if (stDt.compareTo(cndRuleVO.getAplyStDt()) >= 0 &&
					stDt.compareTo(cndRuleVO.getAplyEdDt()) <= 0)	return cndRuleVO;
		}

		throw new WizexException("wizex.common.error.custom", new String[] {"요청한 조건규칙을 찾을 수 없습니다."});
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseVO exec(CndRuleParamVO cndRuleParamVO) {
		if (LOGGER.isDebugEnabled())	LOGGER.debug("params : {}", cndRuleParamVO);

		// 조건규칙 정보
		CndRuleVO	cndRuleVO	= this.getCndRuleVO(cndRuleParamVO);

		if (LOGGER.isDebugEnabled())	LOGGER.debug("cndRuleVO : {}", cndRuleVO);

		try {
			// 스크립트를 생성
			String						script	= this.createScript(cndRuleVO, cndRuleParamVO);

			if (LOGGER.isDebugEnabled())	LOGGER.debug("exec script : {}", script);

			// 스크립트 실행
			Map<String, Object>	result	= (Map<String, Object>)javascriptService.run(script);

			// 결과 반환
			CndRuleResponseVO	response	= new CndRuleResponseVO();
			response.setResult(result);

			return response;

		} catch (WizexException e) {
			throw e;

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new WizexException("wizex.common.error.custom", new String[] {e.getMessage()});
		}
	}

	/**
	 * 조건규칙 스크립트를 작성.
	 *
	 * @param cndRuleVO
	 * @param cndRuleParamVO
	 * @return
	 * @throws JSONException
	 */
	private String createScript(CndRuleVO cndRuleVO, CndRuleParamVO cndRuleParamVO) throws JSONException {
		CndRuleFuncVO			funcVO		= new CndRuleFuncVO(cndRuleVO.getCndRuleCd());
		Map<String, String[]> 	allParams	= cndRuleParamVO.getAllParams();

		// 파라미터를 적절하게 가공.
		for (SvcCndRuleInVO param : cndRuleVO.getSvcCndRuleInVOs()) this.addParam(param, allParams, funcVO);

		StringBuilder				builder		= new StringBuilder();						// 스크립트를 적재.
		Map<String, Boolean>	attached		= new HashMap<String, Boolean>();	// 적재된 스크립트를 기술.
		// 스크립트를 추가.
		this.addScript(cndRuleVO.getCndRuleCd(), cndRuleParamVO.getStDt(), attached, funcVO.getParams(), builder);

		return builder.toString() + funcVO.createRunScript();
	}

	/**
	 * 필요한 조건규칙 스크립트를 추가. 재귀함수.
	 *
	 * @param cndRuleCd
	 * @param stDt
	 * @param attached
	 * @param params
	 * @param builder
	 */
	private void addScript(String cndRuleCd, String stDt, Map<String, Boolean> attached, Map<String, Object> params, StringBuilder builder) {
		if (attached.containsKey(cndRuleCd))	return;	// 이미 포함된 스크립트.

		attached.put(cndRuleCd, true);						// 포함되어 있음으로 표기.

		CndRuleParamVO	ruleParamVO	= new CndRuleParamVO();
		ruleParamVO.setCndRuleCd(cndRuleCd);
		ruleParamVO.setStDt(stDt);
		CndRuleVO	cndRuleVO	= this.getCndRuleVO(ruleParamVO);	// 조건규칙정보.

		Matcher 	matcher	= JavascriptService.PATTERN.matcher(cndRuleVO.getCndRuleScript());	// 수정해야 할 스크립트 항목을 찾음.
		String		script		= cndRuleVO.getCndRuleScript();
		while (matcher.find()) {
			String				funcDesc		= matcher.group();																// 수정해야할 스크립트 항목
			CndRuleFuncVO	innerFuncVO	= this.javascriptService.createToCallInFunc(funcDesc, params);		// 스크립트 정보를 생성.

			this.addScript(innerFuncVO.getCndRuleCd(), stDt, attached, params, builder);							// 스크립트를 추가(재귀호출).

			script		= script.replace(funcDesc, innerFuncVO.createRunScript());											// 실행스크립트로 치환.
		}

		builder.append(script).append(';');		// 스크립트를 추가.
	}

	/**
	 * add params
	 *
	 * @param svcCndRuleInVO
	 * @param allParams
	 * @param funcVO
	 * @throws JSONException
	 */
	private void addParam(SvcCndRuleInVO svcCndRuleInVO, Map<String, String[]> allParams, CndRuleFuncVO funcVO) throws JSONException {
		String[]	values	= allParams.get(svcCndRuleInVO.getParamEnm());
		if ("Y".equals(svcCndRuleInVO.getArrYn())) {
			if (!ObjectUtils.isEmpty(values))	funcVO.addParam(svcCndRuleInVO.getParamEnm(), Arrays.asList(values));

		} else {
			if (!ObjectUtils.isEmpty(values))	funcVO.addParam(svcCndRuleInVO.getParamEnm(), values[0]);
		}
	}
}
