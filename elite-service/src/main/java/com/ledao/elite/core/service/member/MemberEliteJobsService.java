package com.ledao.elite.core.service.member;

import java.util.List;
import java.util.Map;

import com.ledao.elite.core.domain.member.MemberEliteJobs;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 精英职能角色服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface MemberEliteJobsService {
	
	
	/**
	 * 创建精英职能角色
	 * 
	 * @param memberEliteJobs
	 * @return memberEliteJobs
	 * */
	MemberEliteJobs createMemberEliteJobs(MemberEliteJobs obj)throws EliteServiceException;
	
	/**
	 * 精英职能角色评级
	 * 
	 * @param id
	 * @param obj
	 * @return map<String,String>
	 * */
	Map<String,String> updateMemberEliteJobsLevel(Long id,MemberEliteJobs obj)throws EliteServiceException;
	
	/**
	 * 修改精英职能
	 * 
	 * @param id
	 * @param obj
	 * @return map<String,String>
	 * */
	MemberEliteJobs updateMemberEliteJobs(MemberEliteJobs obj)throws EliteServiceException;

	
	/**
	 * 删除精英的职能
	 * 
	 * @param eliteId
	 * */
	void removeMemberEliteJobsByEliteId(Long eliteId)throws EliteServiceException;
	
	/**
	 * 按ID查询
	 * 
	 * @param id
	 * @return memberEliteJobs
	 * */
	MemberEliteJobs findMemberEliteJobsById(Long id)throws EliteServiceException;
	
	/**
	 * 查询精英的职能角色map
	 * 
	 * @param eliteId
	 * @return map<String,String>
	 * */
	Map<String,String> findMemberEliteJobsByEliteId(Long eliteId)throws EliteServiceException;

	/**
	 * 查询精英的职能list
	 * @param eliteId
	 * @return List<MemberEliteJobs>
	 * @throws EliteServiceException
	 */
	List<MemberEliteJobs>findMemberEliteJobsListByMemberId(Long eliteId)throws EliteServiceException;
}
