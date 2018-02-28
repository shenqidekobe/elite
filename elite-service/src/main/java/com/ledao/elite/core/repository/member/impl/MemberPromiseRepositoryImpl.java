package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberPromise;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberPromiseRepository;

@Repository
public class MemberPromiseRepositoryImpl extends GenericRepositoryImpl<MemberPromise, Long> implements MemberPromiseRepository {

}
