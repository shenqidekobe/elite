package com.ledao.elite.core.service.member;

import java.util.List;

import com.ledao.elite.core.domain.member.MemberSkill;
import com.ledao.elite.core.exception.EliteServiceException;;

/**
 * 会员技能槽接口
 */
public interface MemberSkillService {

	
	/**
	 * 创建会员技能
	 * @param MemberSkill
	 * @return MemberSkill
	 */
	MemberSkill createMemberSkill(MemberSkill obj) throws EliteServiceException;
   
	
	/**
	 * 修改会员技能
	 * @param obj
	 * @return MemberSkill
	 */
	MemberSkill updteMemberSkill(Long id,MemberSkill obj)throws EliteServiceException;
	
	/**
	 * 删除会员的某个技能
	 * 
	 * @param id
	 */
	void removePhysicalById(Long id)throws EliteServiceException;
	
	/**
	 * 按ID查询技能
	 * 
	 * @param id
	 * @return MemberSkill
	 * */
	MemberSkill findById(Long id)throws EliteServiceException;
	
	/**
	 * 查询会员的技能槽列表
	 * 
	 * @param memberId
	 * 
	 * @return List<MemberSkill>
	 * */
	List<MemberSkill> findMemberSkillByMemberId(Long memberId)throws EliteServiceException;
}
