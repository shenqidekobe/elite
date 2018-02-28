package com.ledao.elite.core.repository.platform.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.platform.PlatformWorkRecord;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.platform.PlatformWorkRecordRepository;

@Repository
public class PlatformWorkRecordRepositoryImpl extends GenericRepositoryImpl<PlatformWorkRecord, Long> implements PlatformWorkRecordRepository {

}
