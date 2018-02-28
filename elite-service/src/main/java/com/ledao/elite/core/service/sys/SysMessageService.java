package com.ledao.elite.core.service.sys;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysMessage;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 系统消息接口
 *
 **/

public interface SysMessageService {

	
	/**
	 * 创建一条消息
	 * */
	void createSysMessage(SysMessage obj)throws EliteServiceException;
	
    /**
     * 获取userId拥有的菜单
     *
     * @param userId
     * @return SearchResult<SysMessage>
     * */
    SearchResult<SysMessage> findSysMessagesByUserId(Long userId, String unread, Pager pager)throws EliteServiceException;

    /**
     * 按Id逻辑删除
     */
    void removeSysMessageByIds(Long[] ids) throws EliteServiceException;

    /**
     * 标为已读
     */
    void updateRead(Long[] ids) throws EliteServiceException;

    /**
     * 消息详情
     */
    SysMessage find(Long id) throws EliteServiceException;

}
