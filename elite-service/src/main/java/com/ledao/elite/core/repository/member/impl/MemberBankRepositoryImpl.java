package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberBank;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberBankRepository;

@Repository
public class MemberBankRepositoryImpl extends GenericRepositoryImpl<MemberBank, Long> implements MemberBankRepository{

}
