package com.wisenc.wizex.api.common.javascript.service.impl;

import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wisenc.wizex.api.cndrule.vo.CndRuleFuncVO;
import com.wisenc.wizex.api.common.javascript.service.JavascriptService;
import com.wisenc.wizex.framework.exception.WizexException;

/**
 * Class Name : JavascriptServiceImpl.java
 * Description : 자바스크립트 서비스.
 * Modification Information
 *
 *  수정일			수정자              수정내용
 *  -------------------------------------------------
 *  2022. 10. 16.   		김경석				최초생성
 *
 * @author 김경석
 * since 2022. 10. 16.
 * version 1.0
 */
@Service
public class JavascriptServiceImpl implements JavascriptService {
	private ScriptEngine		engine 	= new ScriptEngineManager().getEngineByName("nashorn");

	/** The Constant LOGGER. */
	private static final Logger LOGGER	= LoggerFactory.getLogger(JavascriptServiceImpl.class);

	@Override
	public Object run(String script) {
		try {
			if (LOGGER.isDebugEnabled())	LOGGER.debug("script : {}", script);

			Object	result	= this.engine.eval(script);

			if (LOGGER.isDebugEnabled())	LOGGER.debug("result : {}", result);

			return result;
		} catch (ScriptException e) {
			LOGGER.error("실행 중 오류 - " + script, e);
			throw new WizexException("wizex.common.error.custom", new String[] {e.getMessage()});
		}
	}

	@Override
	public CndRuleFuncVO createToCallInFunc(String funcDesc, Map<String, Object> paramMap) {
		if (LOGGER.isDebugEnabled())	LOGGER.debug("funcDesc : {}", funcDesc);

		String	script		= funcDesc.replace("${", "").replace("}", "");	// ${, }를 없앰.

		int				resultIdx		= script.indexOf(":");		// result의 시작 index.
		int				paramIdx	= script.indexOf("::");	// parameter의 시작 index.

		String	cndRuleId	= script.substring(0, resultIdx);
		CndRuleFuncVO	funcVO		= new CndRuleFuncVO(cndRuleId, paramMap);

		if (paramIdx >= 0) {
			String	params		= script.substring(paramIdx + 2);
			String[]	arrParam	= params.split(",");
			if (arrParam.length > 0) {
				for (String param : arrParam) {
					if (StringUtils.isEmpty(param))	continue;

					String[]	_tokens	= param.split("=");
					if (_tokens.length != 2)				continue;

					funcVO.addParam(_tokens[0], _tokens[1].replaceAll("\"", "").replaceAll("'", ""));
				}
			}
		} else {
			paramIdx	= script.length();
		}


		if (resultIdx >= 0)	// 결과 속성.
			funcVO.setResult(script.substring(resultIdx + 1, paramIdx));

		if (LOGGER.isDebugEnabled())	LOGGER.debug("run script : {}", funcVO.createRunScript());

		return funcVO;
	}
}
