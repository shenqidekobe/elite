package com.ledao.elite.core.repository.sys;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysMessage;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 系统消息接口
 * @author maolei
 *
 */
public interface SysMessageRepository extends GenericDAO<SysMessage, Long>{

    /**
     * 用户消息列表
     */
    SearchResult<SysMessage> querySysMessagesByUserId(Long userId, String unread, Pager pager);
}
