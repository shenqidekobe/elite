package com.ledao.elite.core.framework.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import com.ledao.elite.core.domain.member.MemberPassport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 会员详细信息
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
@EqualsAndHashCode(callSuper = false,of={})
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailInfo extends MemberPassport{

	private static final long serialVersionUID = 6634118755208875439L;
	
	private Integer publishProjectCount=0;//发布的项目数量
	private Integer handProjectCount=0;//进行中的项目数(CEO)
	private Integer taskCount=0;//任务数量（承接的任务）
	private Integer carryProjectCount=0;//承接项目数(CTO)
	private Integer publishTaskCount=0;//发布的任务数量
	private Integer attentionCount=0;//关注数量
	
	private BigDecimal trustAmount=new BigDecimal(0);//已托管费用
	private BigDecimal totalIncome=new BigDecimal(0);//累计收益
	
	
	public MemberDetailInfo(MemberPassport member){
		BeanUtils.copyProperties(member, this);
	}
}
