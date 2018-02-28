package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberCoinOrder;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberCoinOrderRepository;

@Repository
public class MemberCoinOrderRepositoryImpl extends GenericRepositoryImpl<MemberCoinOrder, Long> implements MemberCoinOrderRepository {

}
