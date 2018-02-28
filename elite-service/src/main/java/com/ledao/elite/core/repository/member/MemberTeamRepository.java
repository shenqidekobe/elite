package com.ledao.elite.core.repository.member;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.member.MemberTeam;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 我的团队接口
 * */
public interface MemberTeamRepository extends GenericDAO<MemberTeam, Long>{
	
	List<MemberTeam> findMemberTeamList(Long memberId,String keyWord,Pager page);
	
	Integer findMemberTeamCount(Long memberId,String keyWord);
}
