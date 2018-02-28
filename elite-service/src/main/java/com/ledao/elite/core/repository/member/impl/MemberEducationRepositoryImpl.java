package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberEducation;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberEducationRepository;

@Repository
public class MemberEducationRepositoryImpl extends GenericRepositoryImpl<MemberEducation, Long> implements MemberEducationRepository {

}
