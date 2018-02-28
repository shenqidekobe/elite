package com.ledao.elite.core.repository.member;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.member.MemberAttention;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 会员的关注信息、CTO关注的精英接口
 * */
public interface MemberAttentionRepository extends GenericDAO<MemberAttention, Long>{
	
	/**
	 * 角色或姓名或等级查询关注我的精英
	 * @param memberId
	 * @param keyWord
	 * @return
	 */
	List<MemberAttention> findAttenTionUsers(String type,Long memberId,String keyWord,Pager page);
	
	
	Integer findAttenTionUserCount(String type,Long memberId,String keyWord);
}
