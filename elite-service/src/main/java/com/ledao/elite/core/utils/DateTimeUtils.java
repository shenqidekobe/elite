package com.ledao.elite.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期时间工具类
 * */
public class DateTimeUtils {
	
	public static final String DATE_DEFAULT_PATTEN="yyyy-MM-dd HH:mm:ss";
	public static final String DATE_DAY_PATTEN="yyyy-MM-dd";
	public static final String DATE_MONTH_PATTEN="yyyy-MM";
	
	public static SimpleDateFormat defaultFormat=new SimpleDateFormat(DATE_DEFAULT_PATTEN); 
	public static SimpleDateFormat dayFormat=new SimpleDateFormat(DATE_DAY_PATTEN); 
	public static SimpleDateFormat monthFormat=new SimpleDateFormat(DATE_MONTH_PATTEN); 
	
	
	/**
	 * 输入日期，按照指定格式返回
	 * 
	 * @param date
	 * @param pattern
	 *            e.g.DATE_FORMAT_8 = "yyyyMMdd"; DATE_TIME_FORMAT_14 =
	 *            "yyyyMMddHHmmss"; 或者类似于二者的格式,e.g."yyyyMMddHH"，"yyyyMM"
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		if (date==null) {
			return "";
		}
		SimpleDateFormat dateFormator = new SimpleDateFormat(pattern);

		return dateFormator.format(date);
	}
	
	/**
	 * 时间date转换为月份(本周所在的月份)
	 * */
	public static Date currentDateToWeekMonth(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		int dw=cal.get(Calendar.DAY_OF_WEEK);
		if(dw==1){
			dw=7;
		}else{
			dw=dw-1;
		}
		int days=cal.get(Calendar.DATE);
		if(days<7&&dw>days){
			cal.add(Calendar.MONTH, -1);
		}
		return cal.getTime();
	}
	
	/**
	 * 获取时间date的周一
	 * */
	public static String getWeekAsMonday(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		String result = dayFormat.format(cal.getTime());  
        return result;
	}
	
	/**
	 * 获取时间date的周日
	 * */
	public static String getWeekAsSunday(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		cal.add(Calendar.DATE, 6);
		String result = dayFormat.format(cal.getTime());
        return result;
	}
	
	/**
	 * 按类型获取日期
	 * 1，本周第一天
	 * 2，上周第一天
	 * 3，上周最后一天
	 * 4，上月第一天
	 * 5，上月最后一天
	 * 6，本月第一天
	 * 7，本月最后一天
	 * 8，本周最后一天
	 * **/
	public static String getDate(int type){
		 Calendar c = Calendar.getInstance();
		 switch (type) {
			case 1:
				c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				return formatDate(c.getTime(),DATE_DAY_PATTEN);
			case 2:
				c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				c.add(Calendar.WEEK_OF_MONTH, -1);
				return formatDate(c.getTime(),DATE_DAY_PATTEN);
			case 3:
				c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				c.add(Calendar.WEEK_OF_MONTH, -1);
				c.add(Calendar.DAY_OF_WEEK, 6);
				return formatDate(c.getTime(),DATE_DAY_PATTEN);
			case 4:
				c.set(Calendar.DAY_OF_MONTH, 1);
			    c.add(Calendar.DAY_OF_MONTH, -1);
			    c.set(Calendar.DAY_OF_MONTH, 1);
				return formatDate(c.getTime(),DATE_DAY_PATTEN);
			case 5:
				c.set(Calendar.DAY_OF_MONTH, 1);
			    c.add(Calendar.DAY_OF_MONTH, -1);
				return formatDate(c.getTime(),DATE_DAY_PATTEN);
			case 6:
				c.set(Calendar.DAY_OF_MONTH, 1);
				return formatDate(c.getTime(),DATE_DAY_PATTEN);
			case 7:
				c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
				return formatDate(c.getTime(), DATE_DAY_PATTEN);
			case 8:
				c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				return formatDate(c.getTime(), DATE_DAY_PATTEN);
			default:
				break;
		}
		 return formatDate(new Date(), DATE_DAY_PATTEN);
	}
	
	/**
	 * 获取第N月的时间
	 * */
	public static Date getMonth(int month){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.MONTH, month-1);
		return cal.getTime();
	}
	
	/**
	 * 在date上加减天数
	 * */
	public static Date addOrSub(Date date,int factor){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, factor);
		return cal.getTime();
	}
	/**
	 * 再data上
	 */
	public static Date addMonth(Date date,int factor){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, factor);
		return cal.getTime();
	}
	
	/**
	 * 获取某月一号所属周的周一时间
	 * */
	public static Date getMonthFirstDayAsMonday(int month){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH,1);
		Calendar rel=Calendar.getInstance();
		rel.setTime(cal.getTime());
		rel.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return rel.getTime();
	}
	
	
	/**
	 * 获取某月最后一天所属周的周一时间
	 * */
	public static Date getMonthLastDayAsMonday(int month){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Calendar rel=Calendar.getInstance();
		rel.setTime(cal.getTime());
		rel.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return rel.getTime();
	}
	
	/**
	 * 获取某月的开始时间，按周计算(第一周的周一在上个月则从第二周的周一开始)
	 * */
	public static Date getMonthStartTime(int month){
		Date result=null;
		try {
			Date firstWeekMonday=getMonthFirstDayAsMonday(month);
			Calendar cal=Calendar.getInstance();
			cal.set(Calendar.MONTH, month-1);
			cal.set(Calendar.DAY_OF_MONTH,1);
			Date firstMonthDay=cal.getTime();
			if(!firstWeekMonday.before(firstMonthDay)){
				return firstWeekMonday;
			}
			return addOrSub(dayFormat.parse(getWeekAsSunday(firstMonthDay)),1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取某月的结束时间，按周计算(最后一周的周日时间)
	 * */
	public static Date getMonthEndTime(int month){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		int wk=cal.get(Calendar.DAY_OF_WEEK)-1;
		if(wk==0){
			return cal.getTime();
		}
		cal.add(Calendar.DAY_OF_MONTH, (7-wk));
		return cal.getTime();
	}
	
	/**
	 * 获取某月的第几周的时间列表
	 * 
	 * @param month:第几月
	 * @param week:第几周
	 * */
	public static List<Date> getMonthAsSomeWeekList(int month,int week){
		List<Date> resultList=new ArrayList<Date>();
		Date startTime=getMonthStartTime(month);
		if(week==1){
			resultList.add(startTime);
		}
		int factor=week==1?1:week*7-7;
		int len=week*7;
		for (int i = factor; i < len; i++) {
			Calendar cal=Calendar.getInstance();
			cal.setTime(startTime);
			cal.add(Calendar.DAY_OF_MONTH, i);
			Date rdate=cal.getTime();
			resultList.add(rdate);
		}
		return resultList;
	}
	
	/**
	 * 时间相减得到天数(T+1)
	 * @param endTime
	 * @param startTime
	 * */
	public static int dateSub(Date endTime,Date startTime){
		if(endTime==null||startTime==null)
			return 0;
		long is=endTime.getTime()-startTime.getTime();
		long fs=24*60*60*1000;
		long rs=is/fs;
		return (int) rs+1;
	}
	
	
	/**
	 * 时间相减得到月数(T+1)
	 * @param endTime
	 * @param startTime
	 * */
	public static int getMonth(Date endTime,Date startTime){
		if(endTime==null||startTime==null)
			return 0;
		int result = 0; 
		Calendar c1 = Calendar.getInstance();
	    Calendar c2 = Calendar.getInstance();

        c1.setTime(startTime);
        c2.setTime(endTime);
        int month = Math.abs(c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR))*12;
	    result = month+c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		return result == 0 ? 1 : Math.abs(result);
	}

	
	/**
	 * 任务质保时间结算
	 * 交付时间-开始时间/2，小数+1
	 * */
	public static int taskQuaTime(Date et,Date st){
		if(et==null||st==null)
			return 0;
		long is=et.getTime()-st.getTime();
		long fs=24*60*60*1000;
		long rs=is/fs;
		int r=(int) rs+1;
		int sb=r/2;
		if(r%2!=0){
			sb++;
		}
		return sb;
	}
	
	/**
	 * 获取某月有多少周{endTime-startTime/7}
	 * */
	public static int getMonthAsWeekCount(int month){
		Date startTime=getMonthStartTime(month);
		Date endTime=getMonthEndTime(month);
		long et=endTime.getTime();
		long bt=startTime.getTime();
		int betweenDays = (int)((et - bt) / (1000 * 60 * 60 *24) + 0.5); 
		int remainder=betweenDays%7;
		int result=betweenDays/7;
		return remainder==0?result:result+1;
	}
	
	/**
	 * 验证时间是否在day天后是否过期
	 * */
	public static boolean timeIsExpire(Date time,Integer day){
		if(time==null)return false;
		if(day==null)day=1;
		boolean flag=false;
		Calendar cal=Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.DAY_OF_MONTH, day);//+天数
		Date currentTime=new Date();
		//当前时间在(质保天数+验收时间)之后就是已到期
		if(currentTime.after(cal.getTime()))
			flag=true;
		return flag;
	}
	
	/**
	 * 周期结束时间
	 * 获取时间的N个月后的最后一天时间
	 * */
	public static Date getDateMonthLastDay(Date date,int month){
		if(date==null)date=new Date();
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}
	
	/**
	 * 周期开始时间
	 * 获取时间的N个月前的第一天时间
	 * */
	public static Date getDateMonthFirstDay(Date date,int month){
		if(date==null)date=new Date();
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		//cal.add(Calendar.MONTH, -(month-1));
		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	
	public static void main(String[] args)throws Exception{
		Calendar cc=Calendar.getInstance();
		cc.set(Calendar.MONTH, 11);
		Date d=getDateMonthFirstDay(new Date(),2);
		System.out.println(defaultFormat.format(cc.getTime()));
		System.out.println(defaultFormat.format(getDateMonthLastDay(cc.getTime(), 1)));
		Date currentDate=new Date();
		System.out.println(currentDate.before(d));
		System.out.println(timeIsExpire(d, 5));
		/*Calendar dd=Calendar.getInstance();
		for (int i = 1; i < 1; i++) {
			dd.setTime(dayFormat.parse("2016-06-25"));
			dd.add(Calendar.DATE, i);
			Calendar cal=Calendar.getInstance();
			cal.setTime(dd.getTime());
			int dw=cal.get(Calendar.DAY_OF_WEEK);
			if(dw==1){
				dw=7;
			}else{
				dw=dw-1;
			}
			int days=cal.get(Calendar.DATE);
			System.out.println("当前时间周："+dw+":"+dayFormat.format(cal.getTime()));
			if(days<7&&dw>days){
				cal.add(Calendar.MONTH, -1);
			}
			System.out.println("月份："+monthFormat.format(cal.getTime()));
			System.out.println("\n");
		}
		*/
		/*for (int i = 1; i <13; i++) {
			Calendar cal=Calendar.getInstance();
			cal.set(Calendar.MONTH, i-1);
			System.out.println(i+"month max day = "+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		}*/
		/*for (int i = 1; i < 13; i++) {
			System.out.println("st = "+dayFormat.format(getMonthStartTime(i))+":"+dayFormat.format(getMonthEndTime(i)));
		}*/
		/*for (int i = 1; i < 5; i++) {
			List<Date> list=getMonthAsSomeWeekList(6, i);
			for(Date d:list){
				System.out.println("date day = "+i+"---------"+dayFormat.format(d));
			}
		}*/
/*		
		for (int i = 1; i <13; i++) {
			System.out.println(getMonthAsWeekCount(i));
		}
		
		System.out.println(getMonthStartTime(6));
		Date startTime=DateUtils.parseDate("20160601", "yyyyMMdd");
		Date endTime=DateUtils.parseDate("20160630", "yyyyMMdd");
		long is=endTime.getTime()-startTime.getTime();
		System.out.println(is);
		System.out.println(24*60*60*1000);
		long fs=24*60*60*1000;
		long o= is/fs;
		
		System.out.println(o);
		
		Date time=new Date();
		Calendar cal=Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.DAY_OF_MONTH, 5);//+天数
		System.out.println("time ="+cal.getTime());
		
		long rs=4/2;
		System.out.println(4%2);
		int r=(int) rs+1;
		System.out.println(r);*/
	}

}
