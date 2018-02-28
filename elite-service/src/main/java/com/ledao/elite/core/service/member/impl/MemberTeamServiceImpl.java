package com.ledao.elite.core.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberAttention;
import com.ledao.elite.core.domain.member.MemberTeam;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.repository.member.MemberTeamRepository;
import com.ledao.elite.core.repository.project.ProjectTaskRecruitRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberAttentionService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberTeamService;

@Service
public class MemberTeamServiceImpl  extends BaseSerivceImpl implements MemberTeamService{
	
	@Resource
	private MemberTeamRepository memberTeamRepository;
	@Resource
	private ProjectTaskRecruitRepository projectTaskRecruitRepository;
	@Resource
	private MemberAttentionService memberAttentionService;
	@Resource
	private MemberPassportService memberPassportService;
	
	
	@Override
	public MemberTeam createMemberTeam(MemberTeam team) throws EliteServiceException {
		this.verifyParams(team.getMemberId(),team.getTeamMemberId());
		this.memberTeamRepository.save(team);
		return team;
	}



	@Override
	public MemberTeam updatetMemberTeam(MemberTeam team) throws EliteServiceException {
		this.verifyParams(team.getId());
		this.memberTeamRepository.save(team);
		return team;
	}


	@Override
	public MemberTeam findMemberTeamByMemberId(Long memberId,Long teamMemberId) throws EliteServiceException {
		this.verifyParams(teamMemberId);
		Search search = new Search(MemberTeam.class);
		search.addFilter(new Filter("teamMemberId", teamMemberId));
		search.addFilter(new Filter("memberId", memberId));
		return memberTeamRepository.searchUnique(search);
	}



	@Override
	public SearchResult<MemberTeam> findMyMemberTeam(String keyWord,Long memberId,Pager pager) throws EliteServiceException {
		this.verifyParams(memberId);
		SearchResult<MemberTeam> sr = new SearchResult<MemberTeam>();
		List<MemberTeam> list = this.memberTeamRepository.findMemberTeamList(memberId, keyWord, pager);
		for(MemberTeam team:list){
			MemberDetailInfo info = memberPassportService.findMemberDetailInfoById(team.getTeamMemberId());
			//进行中的任务
			Integer taskCount = projectTaskRecruitRepository.memberProjectTaskCount(team.getTeamMemberId());
			info.setTaskCount(taskCount);
			MemberAttention attention = memberAttentionService.checkAttentioned(memberId, team.getTeamMemberId());
			if(attention!=null){
				team.setAttentioned(true);
			}
			team.setInfo(info);
		}
		Integer count = this.memberTeamRepository.findMemberTeamCount(memberId, keyWord);
		sr.setTotalCount(count);
		sr.setResult(list);
		return sr;
	}

	
	
}
