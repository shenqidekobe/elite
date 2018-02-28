package com.ledao.elite.rest.framework.response.project;

import com.ledao.elite.core.framework.dto.site.WeeklyKey;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.Data;

@Data
public class RProjectWeekly {
	private Long id;
	private Integer week;
	private String startTime;//周区间{5/23~5/29}
	private String endTime;
	private String content;
	private boolean currentWeek=false;//是否当前周
	private boolean exist=false;//本周是否提交了周报
	private String fileName;//文件名
	private String fileSize;//文件大小
	private String createDate;//文件上传日期
	private boolean isClaim=false;
	
	public RProjectWeekly(){}
	public RProjectWeekly(WeeklyKey weeklyKey){
		this.content=DateTimeUtils.formatDate(weeklyKey.getEndTime(), "yyyy-MM-dd");
		this.startTime=DateTimeUtils.formatDate(weeklyKey.getStartTime(), "MM.dd");
		this.endTime=DateTimeUtils.formatDate(weeklyKey.getEndTime(), "MM.dd");
		this.currentWeek=weeklyKey.isCurrentWeek();
		this.exist=weeklyKey.isExist();
	}
}
