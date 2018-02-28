package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberCharge;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberChargeRepository;

@Repository
public class MemberChargeRepositoryImpl extends GenericRepositoryImpl<MemberCharge, Long> implements MemberChargeRepository {

}
