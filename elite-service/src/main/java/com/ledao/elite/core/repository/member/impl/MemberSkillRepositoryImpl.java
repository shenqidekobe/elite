package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberSkill;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberSkillRepository;

@Repository
public class MemberSkillRepositoryImpl extends GenericRepositoryImpl<MemberSkill, Long> implements MemberSkillRepository {

}
