package com.ledao.elite.core.repository.sys.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.sys.SysConfig;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.SysConfigRepository;

@Repository
public class SysConfigRepositoryImpl extends GenericRepositoryImpl<SysConfig, Long> implements SysConfigRepository {

}
