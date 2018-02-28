package com.ledao.elite.core.repository.project;

import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 项目接口
 * */
public interface ProjectRepository extends GenericDAO<Project, Long>{
	
	/**
	 * 合并更新
	 * */
	void mergeProject(Project obj);
	
	/**
	 * 根据项目推荐方memberId 查询推荐项目
	 */
	List<Project> findProjectListByMemberPartnerCompanyId(Long memberId);
	
	/**
	 * 分页查询项目列表
	 */
	List<Project> findProjectListByMemberPartnerId(Long partnerId,String status,Pager pager);
	
	/**
	 * 分页查询项目列表数量
	 */
	Integer findProjectListCountByMemberPartnerId(Long partnerId,String status);
	
	/**
	 * 项目渠道方查询 自身推荐项目
	 * @param memberId
	 * @param startTime
	 * @param endTime
	 * @param pager
	 * @return
	 */
	List<Project>findProjectListByMemberPartnerCompanyByOwn(Long memberId,String incomeType,Date startTime,Date endTime,Pager pager);
	/**
	 * 项目渠道方查询 自身推荐项目数
	 * @param memberId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	
	Integer findProjectCountByMemberPartnerCompanyByOwn(Long memberId,String incomeType,Date startTime,Date endTime);
}
