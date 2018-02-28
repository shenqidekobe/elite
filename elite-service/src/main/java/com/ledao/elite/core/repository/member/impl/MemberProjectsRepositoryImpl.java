package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberProjects;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberProjectsRepository;

@Repository
public class MemberProjectsRepositoryImpl extends GenericRepositoryImpl<MemberProjects, Long> implements MemberProjectsRepository {

}
