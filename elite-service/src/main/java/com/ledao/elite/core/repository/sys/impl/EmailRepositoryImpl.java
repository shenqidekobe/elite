package com.ledao.elite.core.repository.sys.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.sys.EmailRecord;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.EmailRepository;

@Repository
public class EmailRepositoryImpl extends GenericRepositoryImpl<EmailRecord, Long> implements EmailRepository {

}
