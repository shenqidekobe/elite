package com.ledao.elite.core.service.project.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.project.ProjectWeekly;
import com.ledao.elite.core.domain.project.ProjectWeekly.ProjectWeekly_Status;
import com.ledao.elite.core.domain.project.ProjectWeekly.ProjectWeekly_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.dto.site.WeeklyKey;
import com.ledao.elite.core.repository.project.ProjectWeeklyRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.project.ProjectWeeklyService;
import com.ledao.elite.core.service.sys.AttasService;
import com.ledao.elite.core.utils.DateTimeUtils;

@Service
public class ProjectWeeklyServiceImpl extends BaseSerivceImpl implements ProjectWeeklyService{
	
	
	@Resource
	private ProjectWeeklyRepository projectWeeklyRepository;
	@Resource
	private AttasService attasService;
	@Resource
	private MemberPassportService memberPassportService;

	@Override
	public ProjectWeekly createProjectWeekly(ProjectWeekly obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getProjectId(),obj.getWeeklyType());
		switch (obj.getWeeklyType()) {
		case cto:
			obj.setStatus(ProjectWeekly_Status.normal);
			break;
		case company:
			obj.setStatus(ProjectWeekly_Status.wait_audit);
			break;
		}
		if(obj.getCreateId()==-1L){
			obj.setStatus(ProjectWeekly_Status.normal);
		}
		this.projectWeeklyRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectWeekly updateProjectWeeklyStatus(ProjectWeekly obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getId(),obj.getStatus());
		this.projectWeeklyRepository.save(obj);
		return obj;
	}

	@Override
	public ProjectWeekly findProjectWeeklyById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.projectWeeklyRepository.find(id);
	}
	
	@Override
	public Integer findProjectWeeklyCountAsReceive(Long taskId,Long projectId,Long memberId,ProjectWeekly_Type weeklyType,Boolean readed,Integer month)throws EliteServiceException{
		this.verifyParams(memberId,weeklyType);
		Search search=new Search();
		if(taskId!=null){
			search.addFilterEqual("taskId", taskId);
		}else{
			search.addFilterEqual("projectId", projectId);
		}
		search.addFilterEqual("receiveId", memberId);
		search.addFilterEqual("weeklyType", weeklyType);
		search.addFilterEqual("status", ProjectWeekly_Status.normal);
		if(month==null){
			Calendar calendar=Calendar.getInstance();
			month=calendar.get(Calendar.MONTH)+1;
		}
		Date startTime=DateTimeUtils.getMonthStartTime(month);
		Date endTime=DateTimeUtils.getMonthEndTime(month);
		search.addFilterGreaterOrEqual("weeklyTime",startTime);
		search.addFilterLessOrEqual("weeklyTime",endTime);
		if(readed!=null&&readed==false){
			search.addFilterEqual("readed", readed);
		}
		return this.projectWeeklyRepository.count(search);
	}
	
	@Override
	public Integer findProjectWeeklyCountAsSend(Long taskId,Long projectId,Long memberId,ProjectWeekly_Type weeklyType,Boolean readed,Integer month)throws EliteServiceException{
		this.verifyParams(memberId,weeklyType);
		Search search=new Search();
		if(taskId!=null){
			search.addFilterEqual("taskId", taskId);
		}else{
			search.addFilterEqual("projectId", projectId);
		}
		search.addFilterEqual("createId", memberId);
		search.addFilterEqual("weeklyType", weeklyType);
		search.addFilterEqual("status", ProjectWeekly_Status.normal);
		if(month==null){
			Calendar calendar=Calendar.getInstance();
			month=calendar.get(Calendar.MONTH)+1;
		}
		Date startTime=DateTimeUtils.getMonthStartTime(month);
		Date endTime=DateTimeUtils.getMonthEndTime(month);
		search.addFilterGreaterOrEqual("weeklyTime",startTime);
		search.addFilterLessOrEqual("weeklyTime",endTime);
		if(readed!=null&&readed==false){
			search.addFilterEqual("readed", readed);
		}
		return this.projectWeeklyRepository.count(search);
	}


	@Override
	public Map<WeeklyKey,List<ProjectWeekly>> findProjectWeeklyListAsReceive(Long taskId,Long projectId,String weeklyType,Long memberId, int month) throws EliteServiceException {
		this.verifyParams(weeklyType,month);
		Date startTime=DateTimeUtils.getMonthStartTime(month);
		Date endTime=DateTimeUtils.getMonthEndTime(month);
		Search search=new Search();
		if(taskId!=null){
			search.addFilterEqual("taskId", taskId);
		}else{
			search.addFilterEqual("projectId", projectId);
		}
		if(memberId!=null){
			search.addFilterEqual("receiveId", memberId);
		}
		search.addFilterEqual("status", ProjectWeekly_Status.normal);
		search.addFilterEqual("weeklyType", ProjectWeekly_Type.valueOf(ProjectWeekly_Type.class, weeklyType));
		search.addFilterGreaterOrEqual("weeklyTime",startTime);
		search.addFilterLessOrEqual("weeklyTime",endTime);
		search.addSort("createTime", true);//倒序
		List<ProjectWeekly> weeklyList=this.projectWeeklyRepository.search(search);
		for(ProjectWeekly weekly:weeklyList){
			MemberPassport member = memberPassportService.findMemberPassportById(weekly.getCreateId());
			weekly.setMemberPassport(member);
		}
		Map<WeeklyKey,List<ProjectWeekly>> map=listToMap(month, weeklyList);
		return map;
	}

	@Override
	public Map<WeeklyKey,List<ProjectWeekly>> findProjectWeeklyListAsSend(Long taskId,Long projectId,Long memberId, int month) throws EliteServiceException {
		this.verifyParams(memberId,month);
		Date startTime=DateTimeUtils.getMonthStartTime(month);
		Date endTime=DateTimeUtils.getMonthEndTime(month);
		Map<WeeklyKey,List<ProjectWeekly>> map=new HashMap<>();
		Search search=new Search();
		if(taskId!=null){
			search.addFilterEqual("taskId", taskId);
		}else{
			search.addFilterEqual("projectId", projectId);
		}
		search.addFilterEqual("createId", memberId);
		search.addFilterGreaterOrEqual("weeklyTime", startTime);
		search.addFilterLessOrEqual("weeklyTime",endTime);
		search.addSort("weeklyTime", true);//倒序,按周报时间
		List<ProjectWeekly> weeklyList=this.projectWeeklyRepository.search(search);
		map=listToMap(month, weeklyList);
		return map;
	}
	
	/**
	 * 按每周进行迭代计算
	 * */
	private Map<WeeklyKey,List<ProjectWeekly>> listToMap(int month,List<ProjectWeekly> weeklyList){
		Map<WeeklyKey,List<ProjectWeekly>> map=new LinkedHashMap<>();
		WeeklyKey mapKey=null;
		List<ProjectWeekly> mapList=null;
		int weekCount = DateTimeUtils.getMonthAsWeekCount(month);
		for (int i = 1; i <= weekCount; i++) {
			mapKey=new WeeklyKey();
			mapList=new ArrayList<>();
			boolean exist=false,currentWeek=false;
			//每周的日期
			List<Date> dayDateList=DateTimeUtils.getMonthAsSomeWeekList(month, i);
			int dayDateSize=dayDateList.size();
			Date endTime=dayDateList.get(dayDateSize-1);
			mapKey.setStartTime(dayDateList.get(0));
			mapKey.setEndTime(endTime);
			for(Date dayDate:dayDateList){
				Iterator<ProjectWeekly> iter = weeklyList.iterator();
				ProjectWeekly temp = null;
				String dayStr=DateTimeUtils.dayFormat.format(dayDate);
		        while (iter.hasNext()) {
		            temp = iter.next();
		            if(dayStr.equals(DateTimeUtils.dayFormat.format(temp.getWeeklyTime()))){
		            	exist=true;
		            	//检查周报是否超期提交
		            	if(temp.getWeeklyTime().after(endTime)){
		            		temp.setExpired(true);
		            	}
		            	mapList.add(temp);
		            	iter.remove();
		            }
		        }
		        if(dayStr.equals(DateTimeUtils.dayFormat.format(new Date()))){
		        	currentWeek=true;
		        }
			}
			mapKey.setCurrentWeek(currentWeek);
			mapKey.setExist(exist);
			map.put(mapKey, mapList);
		}
		return map;
	}

	@Override
	public void delWeekly(Long id) throws EliteServiceException {
		this.verifyParams(id);
		ProjectWeekly weekly = projectWeeklyRepository.find(id);
		projectWeeklyRepository.deleteWeeklyById(id);
		attasService.removePhysicalById(weekly.getAttaId());
	}

	@Override
	public SearchResult<ProjectWeekly> findProjectWeeklys(Long projectId, String searchType, Date startTime,
			Date endTime,String status, Pager pager) {
		Search search=new Search();
		search.addFilterEqual("projectId", projectId);
		if (startTime != null) {
			search.addFilterGreaterOrEqual("createTime", DateTimeUtils.dayFormat.format(startTime));
		}
		if (endTime != null) {
			search.addFilterLessOrEqual("createTime", DateTimeUtils.dayFormat.format(endTime));
		}
		if(StringUtils.isNotEmpty(searchType)){
				search.addFilterEqual("weeklyType", ProjectWeekly_Type.valueOf(ProjectWeekly_Type.class, searchType));
		}
		if(StringUtils.isNotEmpty(status)){
			search.addFilterEqual("status",status);
		}
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		SearchResult<ProjectWeekly> sr = this.projectWeeklyRepository.searchAndCount(search);
		return sr;
	}
	
	@Override
	public Map<WeeklyKey,List<ProjectWeekly>> findProjectWeeklyListByPm(Long taskId,Long projectId,String status,Long memberId, int month) throws EliteServiceException {
		this.verifyParams(month);
		Date startTime=DateTimeUtils.getMonthStartTime(month);
		Date endTime=DateTimeUtils.getMonthEndTime(month);
		Search search=new Search();
		if(taskId!=null){
			search.addFilterEqual("taskId", taskId);
		}else{
			search.addFilterEqual("projectId", projectId);
		}
		if(StringUtils.isNotEmpty(status)){
			search.addFilterEqual("status", ProjectWeekly_Status.valueOf(ProjectWeekly_Status.class, status));
		}
		search.addFilterEqual("weeklyType", ProjectWeekly_Type.company);
		search.addFilterGreaterOrEqual("weeklyTime",startTime);
		search.addFilterLessOrEqual("weeklyTime",endTime);
		search.addSort("createTime", true);//倒序
		List<ProjectWeekly> weeklyList=this.projectWeeklyRepository.search(search);
		Map<WeeklyKey,List<ProjectWeekly>> map=listToMap(month, weeklyList);
		return map;
	}
	
}
