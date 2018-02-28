package com.ledao.elite.core.service.member;

import java.util.List;

import com.ledao.elite.core.domain.member.MemberProjects;
import com.ledao.elite.core.exception.EliteServiceException;;

/**
 * 会员项目服务接口
 */
public interface MemberProjectsService {

	/**
	 * 添加会员项目
	 * 
	 * @param memberProjects
	 * @return MemberProjects
	 */
	MemberProjects createMemberProject(MemberProjects memberProjects) throws EliteServiceException;

	/**
	 * 更新会员项目
	 * 
	 * @param memberProjects
	 * @return MemberProjects
	 */
	MemberProjects updateMemberProject(Long id,MemberProjects memberProjects) throws EliteServiceException;

	/**
	 * 删除会员项目
	 * 
	 * @param ProjectId
	 * @return MemberProjects
	 */
	MemberProjects removePhysicalProjectById(Long id) throws EliteServiceException;
	
	/**
	 * 删除会员项目
	 * 
	 * @param ProjectId
	 * @return MemberProjects
	 */
	MemberProjects findById(Long id) throws EliteServiceException;

	/**
	 * 根据MemberId查询
	 * 
	 * @param memberNum
	 * @return List<MemberProjects>
	 */
	List<MemberProjects> findByMemberId(Long memberId) throws EliteServiceException;

}
