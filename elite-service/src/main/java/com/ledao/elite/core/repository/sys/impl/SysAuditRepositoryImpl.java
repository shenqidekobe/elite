package com.ledao.elite.core.repository.sys.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.sys.SysAudit;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.SysAuditRepository;

@Repository
public class SysAuditRepositoryImpl extends GenericRepositoryImpl<SysAudit, Long> implements SysAuditRepository {

}
