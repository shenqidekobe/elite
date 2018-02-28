package com.ledao.elite.core.repository.platform.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.platform.PlatformNotice;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.platform.PlatformNoticeRepository;

@Repository
public class PlatformNoticeRepositoryImpl extends GenericRepositoryImpl<PlatformNotice, Long> implements PlatformNoticeRepository {

}
