package com.ledao.elite.core.framework.constant;

/**
 * 统计的枚举类型
 * */
public enum StatisticsEnum {

	memberViewCount("会员查看数量统计");
	
	public String value;
	StatisticsEnum(String value) {
    	this.value = value;
	}
	public String getValue(){
		return value;
	}
}
