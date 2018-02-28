package com.ledao.elite.core.service.platform.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ledao.elite.core.repository.platform.PlatformRefundRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.platform.PlatformRefundService;

@Service
public class PlatformRefundServiceImpl extends BaseSerivceImpl implements PlatformRefundService{

	@Resource
	private PlatformRefundRepository platformRefundRepository;
	

}
