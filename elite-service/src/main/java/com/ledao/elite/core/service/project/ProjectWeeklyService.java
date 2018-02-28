package com.ledao.elite.core.service.project;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.project.ProjectWeekly;
import com.ledao.elite.core.domain.project.ProjectWeekly.ProjectWeekly_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.dto.site.WeeklyKey;

/**
 * 项目周报服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface ProjectWeeklyService {

	
	/**
	 * 发送项目周报
	 * 
	 * @param projectWeekly
	 * @return projectWeekly
	 * */
	ProjectWeekly createProjectWeekly(ProjectWeekly obj)throws EliteServiceException;
	
	/**
	 * 更新周报的状态
	 * 
	 * @param projectWeekly
	 * @return projectWeekly
	 * */
	ProjectWeekly updateProjectWeeklyStatus(ProjectWeekly obj)throws EliteServiceException;
	
	/**
	 * 按ID查询周报内容
	 * 
	 * @param id
	 * @return projectWeekly
	 * */
	ProjectWeekly findProjectWeeklyById(Long id)throws EliteServiceException;
	
	
	/**
	 * 发送的周报数量
	 * 
	 * @param projectId
	 * @param memberId
	 * @param weeklyType
	 * @param readed
	 * @param month
	 * @return int
	 * */
	Integer findProjectWeeklyCountAsSend(Long taskId,Long projectId,Long memberId,ProjectWeekly_Type weeklyType,Boolean readed,Integer month)throws EliteServiceException;

	/**
	 * 收到的周报数量
	 * 
	 * @param projectId
	 * @param memberId
	 * @param weeklyType
	 * @param readed
	 * @param month
	 * @return int
	 * */
	Integer findProjectWeeklyCountAsReceive(Long taskId,Long projectId,Long memberId,ProjectWeekly_Type weeklyType,Boolean readed,Integer month)throws EliteServiceException;
	
	/**
	 * 我收到的周报
	 * 
	 * @param projectId:项目ID
	 * @param weeklyType:周报类型
	 * @param memberId:会员ID
	 * @param month:某月{如本周还未结束，月份已经结束则查询上月的}
	 * @return LMap<WeeklyKey,List<ProjectWeekly>>
	 * */
	Map<WeeklyKey,List<ProjectWeekly>> findProjectWeeklyListAsReceive(Long taskId,Long projectId,String weeklyType,Long memberId,int month)throws EliteServiceException;
	
	/**
	 * 我发出的周报
	 * 
	 * @param projectId:项目ID
	 * @param memberId:会员ID
	 * @param month:月份
	 * @return Map<WeeklyKey,List<ProjectWeekly>>
	 * */
	Map<WeeklyKey,List<ProjectWeekly>> findProjectWeeklyListAsSend(Long taskId,Long projectId,Long memberId,int month)throws EliteServiceException;
	
	/**
	 * 删除周报
	 * @param id
	 */
	void delWeekly(Long id)throws EliteServiceException;
	
	/**
	 * 分页查询周报
	 */
	SearchResult<ProjectWeekly>findProjectWeeklys(Long projectId,String searchType,Date startTime,Date endTime,String status,Pager pager);
     /**
      * pm查询周报
      */
	Map<WeeklyKey,List<ProjectWeekly>> findProjectWeeklyListByPm(Long taskId,Long projectId,String weeklyType,Long memberId,int month)throws EliteServiceException;
	
}
