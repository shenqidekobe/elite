package com.ledao.elite.core.service.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysMessage;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.repository.sys.SysMessageRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.SysMessageService;

@Service
public class SysMessageServiceImpl extends BaseSerivceImpl implements SysMessageService {

	@Resource
	private SysMessageRepository sysMessageRepository;
	
	@Override
	public void createSysMessage(SysMessage obj)throws EliteServiceException{
		this.verifyParams(obj,obj.getUserId(),obj.getTitle());
		obj.setStatus(GlobalDefinedConstant.System_Status.normal.name());
		this.sysMessageRepository.save(obj);
	}

	@Override
	public SearchResult<SysMessage> findSysMessagesByUserId(Long userId, String unread, Pager pager) throws EliteServiceException {
		this.verifyParams(userId);
		return this.sysMessageRepository.querySysMessagesByUserId(userId, unread, pager);
	}

	@Override
	public void removeSysMessageByIds(Long[] ids) throws EliteServiceException {
		for (Long id : ids) {
			SysMessage obj = this.sysMessageRepository.find(id);
			if (obj == null)
				throw new EliteServiceException("未找到用户消息", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
			obj.setStatus(GlobalDefinedConstant.System_Status.deleted.name());
			this.sysMessageRepository.save(obj);
		}
	}

	@Override
	public void updateRead(Long[] ids) throws EliteServiceException {
		for (Long id : ids) {
			SysMessage obj = this.sysMessageRepository.find(id);
			if (obj == null)
				throw new EliteServiceException("未找到消息", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
			obj.setRead(true);
			this.sysMessageRepository.save(obj);
		}
	}

	@Override
	public SysMessage find(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return sysMessageRepository.find(id);
	}
}
