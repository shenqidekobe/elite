package com.ledao.elite.core.repository.platform.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.platform.PlatformNews;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.platform.PlatformNewsRepository;

@Repository
public class PlatformNewsRepositoryImpl extends GenericRepositoryImpl<PlatformNews, Long> implements PlatformNewsRepository {

}
