package com.ledao.elite.core.service.member.impl;

import javax.annotation.Resource;

import com.ledao.elite.core.repository.member.MemberRoleRepository;
import com.ledao.elite.core.repository.member.SitePowerRepository;
import com.ledao.elite.core.repository.member.SiteRolePowerRepository;
import com.ledao.elite.core.repository.member.SiteRoleRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberAuthorityService;

@Resource
public class MemberAuthorityServiceImpl extends BaseSerivceImpl implements MemberAuthorityService{
	
	@Resource
	private MemberRoleRepository memberRoleRepository;
	@Resource
	private SiteRoleRepository siteRoleRepository;
	@Resource
	private SitePowerRepository sitePowerRepository;
	@Resource
	private SiteRolePowerRepository siteRolePowerRepository;
	

}
