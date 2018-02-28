package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.SiteRole;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.SiteRoleRepository;

@Repository
public class SiteRoleRepositoryImpl extends GenericRepositoryImpl<SiteRole, Long> implements SiteRoleRepository {

}
