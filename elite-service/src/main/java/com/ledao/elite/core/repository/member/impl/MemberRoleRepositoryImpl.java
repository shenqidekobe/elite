package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberRole;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberRoleRepository;

@Repository
public class MemberRoleRepositoryImpl extends GenericRepositoryImpl<MemberRole, Long> implements MemberRoleRepository {

}
