package com.ledao.elite.core.repository.platform.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.platform.PlatformReceipt;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.platform.PlatformReceiptRepository;

@Repository
public class PlatformReceiptRepositoryImpl extends GenericRepositoryImpl<PlatformReceipt, Long> implements PlatformReceiptRepository {

}
