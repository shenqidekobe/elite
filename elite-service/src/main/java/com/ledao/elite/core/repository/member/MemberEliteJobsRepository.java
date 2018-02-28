package com.ledao.elite.core.repository.member;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.member.MemberEliteJobs;

public interface MemberEliteJobsRepository extends GenericDAO<MemberEliteJobs, Long>{
	
	int deleteMemberEliteJobsByEliteId(Long eliteId);

}
