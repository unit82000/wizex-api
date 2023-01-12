package com.wisenc.wizex.framework.batch;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Class Name : SchedulerUtil.java
 * @Description :
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
public class SchedulerUtil {

	public static void scheduling(JobDetail jobDetail, Trigger trigger) {
		try {
			// 아래의 팩토리 메서드는 이름이 같으면 여러번 호출해도 항상 동일한 스케줄러를 반환한다.
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			scheduler.scheduleJob(jobDetail, trigger);
		} catch(SchedulerException e) {
			e.printStackTrace();
		}
	}
}
