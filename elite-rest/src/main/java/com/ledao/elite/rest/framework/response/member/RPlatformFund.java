package com.ledao.elite.rest.framework.response.member;

import com.ledao.elite.core.domain.platform.PlatformFund;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Status;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Type;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.Data;


/**
 * 托管记录
 * @author Chenghao
 *
 */
@Data
public class RPlatformFund {
	
	private String title;
	private String firstName;
	private String backgroundColor;
	private String createDate;
	private String amount;
	private String statusVal;
	
	public RPlatformFund(){}
	public RPlatformFund(PlatformFund fund){
		this.title=fund.getTitle();
		this.firstName=fund.getProject()==null?"":fund.getProject().getFirstName();
		this.backgroundColor=fund.getProject()==null?"":fund.getProject().getBackgroundColor();
		this.statusVal="交易"+fund.getStatus().label;
		this.createDate = DateTimeUtils.formatDate(fund.getCreateTime(), "yyyy-MM");
		if(fund.getType().equals(PlatformFund_Type.withdraw)){
			this.amount="-"+fund.getAmount();
			if(fund.getStatus().equals(PlatformFund_Status.wait_pay)){
				statusVal="待处理";
			}
		}else{
			this.amount="+"+fund.getAmount();
			if(fund.getStatus().equals(PlatformFund_Status.wait_pay)){
				statusVal="待付款";
			}
		}
	}
}
