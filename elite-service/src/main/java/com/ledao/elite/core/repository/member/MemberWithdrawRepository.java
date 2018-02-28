package com.ledao.elite.core.repository.member;

import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.member.MemberWithdraw;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 会员提现记录接口
 */
public interface MemberWithdrawRepository extends GenericDAO<MemberWithdraw, Long> {
	
	/**
	 * 模糊查询 提现
	 * @param keyword(姓名，提现编号）
	 * @param status
	 * @param pager
	 * @return
	 */
	public List<MemberWithdraw> queryMemberWithdrawList(String keyword, String status,Date startTime,Date endTime, Pager pager);
   
	/**
	 * 模糊查询 提现 数量
	 * @param keyword
	 * @param ctoed
	 * @param status
	 * @return
	 */
	public Integer queryMemberWithdrawListCount(String keyword,Date startTime,Date endTime,String status);

}
