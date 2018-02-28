package com.ledao.elite.core.service.platform;

import com.ledao.elite.core.domain.platform.PlatformFeedback;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 意见反馈服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface PlatformFeedbackService {

	
	/**
	 * 保存记录
	 * */
	PlatformFeedback createPlatformFeedback(PlatformFeedback obj)throws EliteServiceException;
}
