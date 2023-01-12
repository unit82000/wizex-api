package com.wisenc.wizex.framework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.wisenc.wizex.framework.exception.WizexException;

/**
 * @Class Name : DateUtil.java
 * @Description : Date utilities.
 * @Modification Information
 * @
 * @  수정일			수정자              수정내용
 * @ -------------------------------------------------
 * @ 2020. 3. 18.   		최초생성
 *
 * @author 김경석
 * @since 2020. 3. 18.
 * @version 1.0
 * @see
 */
public class DateUtil {

	private static final SimpleDateFormat	yyyyFormat	= new SimpleDateFormat("yyyy");
	private static final SimpleDateFormat	mmFormat	= new SimpleDateFormat("MM");
	private static final SimpleDateFormat	ddFormat	= new SimpleDateFormat("dd");

	private static final SimpleDateFormat	yyyyMMddFormat			= new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat	yyyyMMddHHmmssFormat	= new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * get yyyy.
	 *
	 * @param date
	 * @return
	 */
	public static String getYyyy(Date date) {
		return yyyyFormat.format(date);
	}

	/**
	 * get MM.
	 *
	 * @param date
	 * @return
	 */
	public static String getMm(Date date) {
		return mmFormat.format(date);
	}

	/**
	 * get dd.
	 *
	 * @param date
	 * @return
	 */
	public static String getDd(Date date) {
		return ddFormat.format(date);
	}

	/**
	 * get yyyyMMdd(today)
	 *
	 * @return
	 */
	public static String getYyyyMMdd() {
		return yyyyMMddFormat.format(new Date());
	}

	/**
	 * get yyyyMMdd
	 *
	 * @param date
	 * @return
	 */
	public static String getYyyyMMdd(Date date) {
		return yyyyMMddFormat.format(date);
	}

	/**
	 * get date
	 *
	 * @param yyyyMMdd
	 * @return
	 * @throws WizexException
	 */
	public static Date getDate(String yyyyMMdd) throws WizexException {
		try {
			return yyyyMMddFormat.parse(yyyyMMdd);
		} catch (Exception e) {
			throw new WizexException("wizex.date.invalidformat", new Object[] { yyyyMMdd, "yyyyMMdd" });
		}
	}

	/**
	 * get date time
	 *
	 * @param yyyyMMddHHmmss
	 * @return
	 * @throws WizexException
	 */
	public static Date getDateTime(String yyyyMMddHHmmss) throws WizexException {
		try {
			return yyyyMMddHHmmssFormat.parse(yyyyMMddHHmmss);
		} catch (Exception e) {
			throw new WizexException("wizex.date.invalidformat", new Object[] { yyyyMMddHHmmss, "yyyyMMddHHmmss" });
		}
	}

	/**
	 * add dates.
	 *
	 * @param yyyyMMdd
	 * @param addDates
	 * @return
	 */
	public static String addDates(String yyyyMMdd, int addDates) {
		Date		date	= getDate(yyyyMMdd);
		Calendar	cal		= new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, addDates);

		return getYyyyMMdd(cal.getTime());
	}

}
