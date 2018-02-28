package com.ledao.elite.core.framework.dto.site;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * 周报存储key值
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
@ToString
public class WeeklyKey implements Serializable{
	
	private static final long serialVersionUID = -7596243342214023854L;
	
	private Long memberId;//发送周报的会员ID
	public Date startTime;//周区间{5/23~5/29}
	public Date endTime;
	public boolean currentWeek=false;//是否当前周
	public boolean exist=false;//本周是否提交了周报
	
	

}
