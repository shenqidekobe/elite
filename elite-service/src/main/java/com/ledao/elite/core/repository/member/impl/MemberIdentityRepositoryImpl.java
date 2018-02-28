package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberIdentity;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberIdentityRepository;

@Repository
public class MemberIdentityRepositoryImpl extends GenericRepositoryImpl<MemberIdentity, Long> implements MemberIdentityRepository {
	
}
