package com.wisenc.wizex.framework.aop;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wisenc.wizex.framework.exception.WizexException;

/**
 * @Class Name : ControllerAdvice.java
 * @Description : controller advice.
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2019. 12. 23.   		최초생성
 *
 * @author 김경석
 * @since 2019. 12. 23.
 * @version 1.0
 * @see
 */
public class ControllerAdvice  {

	/** The Constant LOGGER. */
	private static final Logger LOGGER	= LoggerFactory.getLogger(ControllerAdvice.class);

	/**
	 * 메소드의 호출 전 parameter 출력. 메소드의 수행 후 result 출력.
	 *
	 * @param call
	 *            the call
	 * @return the object
	 * @throws Throwable
	 *             the throwable
	 */
	public Object log(ProceedingJoinPoint call) throws Throwable {
		Signature sign = call.getSignature();
		long  startTime = 0;
		long  endTime   = 0;

		try {
			StringBuffer	buffer	= null;

			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

			// arguments 출력
			if (LOGGER.isDebugEnabled()) {
				buffer	= new StringBuffer();

				buffer.append("\n====================================================== METHOD CALLED. START ======================================================");
				buffer.append("\nCalled	: ");
				buffer.append("\n★★CLASS" + " : " + call.getSignature().getDeclaringTypeName());
				buffer.append("\n★★METHOD" + " : " + call.getSignature().getName());
				buffer.append("\n★★URL" + " : " + request.getRequestURL());
				buffer.append("\n");
				buffer.append(sign.toLongString());

				Object[] args = call.getArgs();	// arguments를 얻음
				if (args != null && args.length != 0) {
					buffer.append("\nARGUMENTS --------------------------------------------");

					for (int i = 0; i < args.length; i++) {
						// 객체가 존재할 경우만 출력
						if (args[i] != null) {
							// ServletRequest객체이면 parameter들을 출력
							if (args[i] instanceof ServletRequest) {
								buffer.append("\nARGS[" + i + "]: REQUEST PARAMETERS {");
								printParameters((ServletRequest)args[i], buffer);
								buffer.append("\n}");
							}

							// ServletRequest외의 객체이면 toString으로 출력
							else {
								buffer.append("\nARGS[" + i + "]type: " + args[i].getClass().getName());
								buffer.append(", value: " + args[i].toString());
							}
						}

						else {
							buffer.append("\nARGS[" + i + "]");
							buffer.append(" is null");
						}
					}
				}

				buffer.append("\n====================================================== METHOD CALLED. END ======================================================");
				LOGGER.debug(buffer.toString());
			}

			// 메소드를 수행
			Object result = null;

			startTime = System.currentTimeMillis(); // 시작시간 측정
			result	= call.proceed();               // 메소드 수행
			endTime   = System.currentTimeMillis(); // 종료시간 측정

			// 수행결과 출력
			if (LOGGER.isDebugEnabled()) {
				buffer.setLength(0);	// buffer 초기화
				buffer.append("\n====================================================== METHOD COMPLETE. START ======================================================");
				buffer.append("\nCompleted	: ");
				buffer.append(sign.toLongString());

				/**	-- 2021/12/20 양준모 method 결과 값 출력 막음.
				if (result != null) {
					buffer.append("\nRESULT]type : ");
					buffer.append(result.getClass().toString());
					buffer.append("\nvalue : ");
					buffer.append(result);
				}
				*/

				long  runningTime = endTime - startTime;
				buffer.append("\n수행시간 : ");
				buffer.append(runningTime / 1000);
				buffer.append(".");
				buffer.append(runningTime % 1000);
				buffer.append("sec.");
				buffer.append("\n====================================================== METHOD COMPLETE. END ======================================================");

				LOGGER.debug(buffer.toString());
			}

			return result;

		} catch (WizexException e) {
			throw e;
		} catch (Throwable e) {
			LOGGER.error(e.getMessage(), e);
			throw new WizexException(e);
		}
	}

	/**
	 * request의 모든 파라미터 정보를 출력.
	 *
	 * @param req
	 *            the req
	 * @param buffer
	 *            the buffer
	 */
	private void printParameters(ServletRequest req, StringBuffer buffer) {
		Enumeration<String>	names	= req.getParameterNames();

		String	   name;
		String[]   values;
		while (names.hasMoreElements()) {
			name	  = names.nextElement();
			values = req.getParameterValues(name);
			buffer.append("\n");
			buffer.append(name);
			buffer.append("=");

			if (values != null) {
				buffer.append("[");
				for (int i = 0; i < values.length; i++) {
					if (i > 0) buffer.append(", ");
					buffer.append(values[i]);
				}
				buffer.append("]");
			}
		}
	}

}
