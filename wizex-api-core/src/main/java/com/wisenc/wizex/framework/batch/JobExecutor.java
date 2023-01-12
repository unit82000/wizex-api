package com.wisenc.wizex.framework.batch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class Name : JobExecutor.java
 * @Description : 잡 실행
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2022. 2. 22.   		양준모			최초생성
 *
 * @author 양준모
 * @since 2022. 2. 22.
 * @version 1.0
 * @see
 */
public class JobExecutor implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobExecutor.class);

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		beforeExecute(ctx);
		doExecute(ctx);
		afterExecute(ctx);
		nextJob(ctx);
	}

	private void beforeExecute(JobExecutionContext ctx) {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug(":::::::::::::::::::::::::::");
			LOGGER.debug("::: BATCH SERVICE START :::");
			LOGGER.debug(":::::::::::::::::::::::::::");
		}
	}

	private void doExecute(JobExecutionContext ctx) {
		JobDataMap jobDataMap = ctx.getJobDetail().getJobDataMap();

		Class<?>	cls = null;
		Method		mtd = null;
		String		clsNm = null;

		try {
			Object clsObj	= jobDataMap.get("targetMapper");
			Object mtdObj	= jobDataMap.get("targetMethod");
			Object voObj	= jobDataMap.get("targetVO");

			clsNm = clsObj.getClass().getName();
			cls = Class.forName(clsNm);
			mtd = cls.getDeclaredMethod(mtdObj.toString(), voObj.getClass());
			mtd.invoke(clsObj, voObj);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void afterExecute(JobExecutionContext ctx) {
		Object jobDetailQueueObj = ctx.getJobDetail().getJobDataMap().get("jobDetailQueue");
		List<JobDetail> jobDetailQueue = (List<JobDetail>) jobDetailQueueObj;

		if(jobDetailQueue.size() > 0) {
			jobDetailQueue.remove(0);
		}
	}

	@SuppressWarnings("unchecked")
	private void nextJob(JobExecutionContext ctx) {
		Object jobDetailQueueObj = ctx.getJobDetail().getJobDataMap().get("jobDetailQueue");
		List<JobDetail> jobDetailQueue = (List<JobDetail>) jobDetailQueueObj;

		if(jobDetailQueue.size() > 0) {
			JobDetail nextJobDetail = jobDetailQueue.get(0);
			nextJobDetail.getJobDataMap().put("jobDetailQueue", jobDetailQueue);
			Trigger nowTrigger = TriggerBuilder.newTrigger().startNow().build();

			SchedulerUtil.scheduling(nextJobDetail, nowTrigger);
		}
	}
}
