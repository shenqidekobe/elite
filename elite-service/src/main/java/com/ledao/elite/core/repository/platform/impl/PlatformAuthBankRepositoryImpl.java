package com.ledao.elite.core.repository.platform.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.platform.PlatformAuthBank;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.platform.PlatformAuthBankRepository;

@Repository
public class PlatformAuthBankRepositoryImpl extends GenericRepositoryImpl<PlatformAuthBank, Long> implements PlatformAuthBankRepository {

}
