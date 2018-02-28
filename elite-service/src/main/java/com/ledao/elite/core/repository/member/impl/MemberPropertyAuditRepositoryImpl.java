package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberPropertyAudit;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberPropertyAuditRepository;

@Repository
public class MemberPropertyAuditRepositoryImpl extends GenericRepositoryImpl<MemberPropertyAudit, Long> implements MemberPropertyAuditRepository {

}
