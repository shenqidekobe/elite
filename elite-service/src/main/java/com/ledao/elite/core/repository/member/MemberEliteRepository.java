package com.ledao.elite.core.repository.member;


import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.member.MemberElite;

/**
 * 精英会员接口
 * */
public interface MemberEliteRepository extends GenericDAO<MemberElite, Long>{

	 /**
	  * 查询精英状态数量
	  * @param ctoed
	  * @param status
	  * @return
	  */
	Integer queryMemberElitePassportCount(boolean ctoed,String status);
}
