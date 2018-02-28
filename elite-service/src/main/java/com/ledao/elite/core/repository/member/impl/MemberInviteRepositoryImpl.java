package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberInvite;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberInviteRepository;

@Repository
public class MemberInviteRepositoryImpl extends GenericRepositoryImpl<MemberInvite, Long> implements MemberInviteRepository {

}
