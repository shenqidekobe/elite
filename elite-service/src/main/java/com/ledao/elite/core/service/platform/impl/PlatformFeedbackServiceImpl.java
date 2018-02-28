package com.ledao.elite.core.service.platform.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ledao.elite.core.domain.platform.PlatformFeedback;
import com.ledao.elite.core.domain.platform.PlatformFeedback.Feedback_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.repository.platform.PlatformFeedbackRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.platform.PlatformFeedbackService;

@Service("platformFeedbackService")
public class PlatformFeedbackServiceImpl extends BaseSerivceImpl implements PlatformFeedbackService{
	
	@Resource
	private PlatformFeedbackRepository platformFeedbackRepository;

	@Override
	public PlatformFeedback createPlatformFeedback(PlatformFeedback obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getContent());
		obj.setStatus(Feedback_Status.init);
		this.platformFeedbackRepository.save(obj);
		return obj;
	}

}
