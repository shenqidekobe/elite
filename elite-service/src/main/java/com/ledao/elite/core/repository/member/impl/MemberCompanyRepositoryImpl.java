package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberCompany;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberCompanyRepository;

@Repository
public class MemberCompanyRepositoryImpl extends GenericRepositoryImpl<MemberCompany, Long> implements MemberCompanyRepository {

}
