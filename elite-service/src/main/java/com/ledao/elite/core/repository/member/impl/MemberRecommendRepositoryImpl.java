package com.ledao.elite.core.repository.member.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberRecommend;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberRecommendRepository;

@Repository
public class MemberRecommendRepositoryImpl extends GenericRepositoryImpl<MemberRecommend, Long> implements MemberRecommendRepository {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberRecommend> findRecommendProjectList(Long memberId, Pager pager) {
		String sql = "select tr.* from t_member_recommend tr left join t_project p on "
				+ "	tr.project_id = p.id where tr.project_id is not null and tr.member_id=:memberId and tr.project_id not in("
				+ " select ptr.project_id  from t_project_tender_record ptr where ptr.member_id=:tenderMemberId)"
				+ " order by tr.create_time desc";
		
		Query query=getSession().createSQLQuery(sql).addEntity(MemberRecommend.class);
		query.setLong("memberId", memberId);
		query.setLong("tenderMemberId", memberId);
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberRecommend> findRecommendTaskList(Long memberId, Pager pager) {
		String sql = "select tr.* from t_member_recommend tr left join"
				+ "  t_project_stage_task pst on tr.task_id = pst.id left join "
				+ "  t_project p on tr.project_id = p.id "
				+ "  where tr.task_id is not null and tr.task_id not in"
				+ "	 (select ptr.task_id from t_project_task_recruit ptr where ptr.member_id =:memberId and ptr.status!=:status ) and tr. member_id=:tmemberId";
		
		Query query=getSession().createSQLQuery(sql).addEntity(MemberRecommend.class);
		query.setLong("memberId", memberId);
		query.setString("status", "recruit_cancel");
		query.setLong("tmemberId", memberId);
		query.setFirstResult(pager.getStartIndex());
		query.setMaxResults(pager.getPageSize());
		
		return query.list();
	}

	@Override
	public Integer findRecommendProjectCount(Long memberId) {
		String sql = "select count(tr.id) from t_member_recommend tr "
				+ " where tr.project_id is not null and tr.member_id=:memberId and tr.project_id not in("
				+ " select ptr.project_id  from t_project_tender_record ptr where ptr.member_id=:tenderMemberId)";
		
		Query query=getSession().createSQLQuery(sql);
		query.setLong("memberId", memberId);
		query.setLong("tenderMemberId", memberId);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@Override
	public Integer findRecommendTaskCount(Long memberId) {

		String sql = "select count(tr.id) from t_member_recommend tr where tr.task_id is not null and tr.member_id=:memberId and tr.task_id not in("
				+ " select ptr.task_id from t_project_task_recruit ptr where ptr.member_id =:tmemberId and ptr.status!='recruit_cancel')";
		
		Query query=getSession().createSQLQuery(sql);
		query.setLong("memberId", memberId);
		query.setLong("tmemberId", memberId);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@Override
	public MemberRecommend findRecommendProject(Long memberId, Long projectId) {
		String sql = "select tr.* from t_member_recommend tr left join t_project p on "
				+ "	tr.project_id = p.id where tr.member_id=:memberId and tr.project_id =:projectId and tr.project_id not in("
				+ " select ptr.project_id  from t_project_tender_record ptr where ptr.member_id=:tenderMemberId)";
		
		Query query=getSession().createSQLQuery(sql).addEntity(MemberRecommend.class);
		query.setLong("memberId", memberId);
		query.setLong("tenderMemberId", memberId);
		query.setLong("projectId", projectId);
		MemberRecommend commend = (MemberRecommend)query.uniqueResult();
		return commend;
	}

	@Override
	public MemberRecommend findRecommendTask(Long memberId, Long taskId) {
		String sql = "select tr.* from t_member_recommend tr "
				+ "	where tr.member_id=:memberId and tr.task_id =:taskId and tr.task_id";
		Query query=getSession().createSQLQuery(sql).addEntity(MemberRecommend.class);
		query.setLong("memberId", memberId);
		query.setLong("taskId", taskId);
		MemberRecommend commend = (MemberRecommend)query.uniqueResult();
		return commend;
	}
	
	
	
	
}
