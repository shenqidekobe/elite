package com.ledao.elite.core.service.member;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberTeam;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

public interface MemberTeamService {
	
	MemberTeam createMemberTeam(MemberTeam team)throws EliteServiceException;
	
	MemberTeam updatetMemberTeam(MemberTeam team)throws EliteServiceException;
	
	/**
	 * 查询该团队by团队成员ID
	 * @param memberId
	 * @return
	 * @throws EliteServiceException
	 */
	MemberTeam findMemberTeamByMemberId(Long memberId,Long teamMemberId)throws EliteServiceException;
	
	
	SearchResult<MemberTeam> findMyMemberTeam(String keyWord,Long memberId,Pager pager)throws EliteServiceException;
}
