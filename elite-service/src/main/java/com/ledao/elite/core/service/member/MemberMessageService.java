package com.ledao.elite.core.service.member;

import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberMessage;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.dto.MemberMessageInfo;

/**
 * 会员消息服务接口
 */
public interface MemberMessageService {
	
	
	/**
	 * 发送一条信息
	 * 
	 * @param memberMessage
	 * @return memberMessage
	 * */
	MemberMessage createMemberMessage(MemberMessage obj)throws EliteServiceException;
	
	/**
	 * 更新消息状态
	 * 
	 * @param id
	 * @param status
	 * */
	void updateMemberMessageStatus(Long id,String status)throws EliteServiceException;
	
	/**
	 * 按ID查询消息
	 * 
	 * @param id
	 * @return memberMessage
	 * */
	MemberMessage findMemberMessageById(Long id)throws EliteServiceException;
	
	/**
	 * 查询会员的消息数量
	 * 
	 * @param memberId
	 * @param type
	 * @param status
	 * @param projectId
	 * @return Integer
	 * */
	Integer findMemberMessageCount(Long memberId,String type,String status,Long projectId)throws EliteServiceException;
	
	/**
	 * 检索会员的消息列表
	 * 
	 * @param memberId
	 * @param status
	 * @param type
	 * @param projectId
	 * @param pager
	 * 
	 * @return SearchResult<MemberMessage>
	 * */
	SearchResult<MemberMessage> findMemberMessageList(Long memberId, String status, String type,Long projectId,Pager pager)throws EliteServiceException;
	
	/**
	 * 手机端消息首页信息
	 * @param memberId
	 * @return
	 */
	List<MemberMessageInfo> findIndexMessage(Long memberId);
}
