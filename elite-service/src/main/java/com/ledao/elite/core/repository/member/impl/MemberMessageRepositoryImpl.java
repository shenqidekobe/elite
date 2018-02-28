package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberMessage;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberMessageRepository;

@Repository
public class MemberMessageRepositoryImpl extends GenericRepositoryImpl<MemberMessage, Long> implements MemberMessageRepository {

}
