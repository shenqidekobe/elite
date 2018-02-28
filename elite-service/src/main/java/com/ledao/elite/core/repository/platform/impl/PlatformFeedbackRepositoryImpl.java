package com.ledao.elite.core.repository.platform.impl;

import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.platform.PlatformFeedback;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.platform.PlatformFeedbackRepository;

@Repository
public class PlatformFeedbackRepositoryImpl extends GenericRepositoryImpl<PlatformFeedback, Long> implements PlatformFeedbackRepository{

}
