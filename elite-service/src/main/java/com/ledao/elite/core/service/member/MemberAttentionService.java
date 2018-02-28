package com.ledao.elite.core.service.member;

import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberAttention;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 会员关注精英服务接口
 */
public interface MemberAttentionService {

	/**
	 * 查询我关注的
	 */
	List<MemberAttention> findAttenTionUsers(Long memberId) throws EliteServiceException;

	/**
	 * 查询关注我的
	 */
	List<MemberAttention> findAttenTionedUsers(Long memberId) throws EliteServiceException;
	
	/**
	 * 查询关注我的总人数
	 */
	Integer findAttenTionedUserCount(Long memberId) throws EliteServiceException;

	/**
	 * 取消关注
	 */
	MemberAttention removePhysicalById(Long memberId, Long attentionMemberId) throws EliteServiceException;
	/**
	 * 添加关注
	 */
	void addPhysicalById(MemberAttention obj) throws EliteServiceException;
	/**
	 * 是否关注
	 */
	MemberAttention checkAttentioned(Long memberId, Long attentionMemberId) throws EliteServiceException;
	
	/**
	 * 查询关注信息
	 * 关注我的和我关注的｛type：attention or attentioned｝
	 */
	SearchResult<MemberAttention> findAttenTionUser(String type,Long memberId,String keyWord,Pager pager);
}
