package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberFund;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberFundRepository;

@Repository
public class MemberFundRepositoryImpl extends GenericRepositoryImpl<MemberFund, Long> implements MemberFundRepository {

}
