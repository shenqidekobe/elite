package com.ledao.elite.core.repository.platform.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.platform.PlatformFund;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.platform.PlatformFundRepository;

@Repository
public class PlatformFundRepositoryImpl extends GenericRepositoryImpl<PlatformFund, Long> implements PlatformFundRepository {

}
