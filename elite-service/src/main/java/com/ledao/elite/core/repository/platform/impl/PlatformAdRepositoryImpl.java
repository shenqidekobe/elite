package com.ledao.elite.core.repository.platform.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.platform.PlatformAd;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.platform.PlatformAdRepository;

@Repository
public class PlatformAdRepositoryImpl extends GenericRepositoryImpl<PlatformAd, Long> implements PlatformAdRepository {

}
