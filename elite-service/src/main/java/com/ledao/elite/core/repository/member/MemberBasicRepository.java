package com.ledao.elite.core.repository.member;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.member.MemberBasic;

/**
 * 会员基本信息接口
 * */
public interface MemberBasicRepository extends GenericDAO<MemberBasic, Long>{
	
	int updateBasic(MemberBasic obj);
	
	/**
	 * 按邮箱查询
	 * */
	List<MemberBasic> queryMemberBasicByEmail(String email);
	
	
	/**
	 * merge
	 * */
	MemberBasic merge(MemberBasic obj);

}
