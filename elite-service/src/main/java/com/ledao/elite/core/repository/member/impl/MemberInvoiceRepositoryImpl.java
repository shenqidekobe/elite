package com.ledao.elite.core.repository.member.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberInvoice;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberInvoiceRepository;

@Repository
public class MemberInvoiceRepositoryImpl extends GenericRepositoryImpl<MemberInvoice, Long> implements MemberInvoiceRepository {

}
