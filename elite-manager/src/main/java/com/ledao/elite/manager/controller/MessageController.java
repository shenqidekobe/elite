package com.ledao.elite.manager.controller;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysMessage;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.sys.SysMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 消息箱
 *
 * @author maolei
 * @version 1.0
 * */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Resource
    private SysMessageService sysMessageService;

    /**
     * 消息列表整页
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list (Model model) {
    	
    	//为读信息数
    	SearchResult<SysMessage> sr = sysMessageService.findSysMessagesByUserId(getUserId(), "true", null);
		int unreadCount = sr.getTotalCount();
		model.addAttribute("unreadCount", unreadCount);
        return "/message/list";
    }

    /**
     * 消息列表数据
     */
    @RequestMapping(value = "/listData", method = RequestMethod.POST)
    public String listData (String unread, Pager pager, Model model) {
        if (unread != null)
            pager.setPageth(Integer.valueOf(1));
        SearchResult<SysMessage> sr = sysMessageService.findSysMessagesByUserId(getUserId(), unread, pager);
        model.addAttribute("list", sr.getResult());
        model.addAttribute("pager", pager);
        pager.calPageCount((long)sr.getTotalCount());
        return "/message/list_frag";
    }

    /**
     * 按Id逻辑删除
     */
    @SystemHandleLog(description = "逻辑删除用户消息", type = LogsEnum.remove)
    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseBase removeMessageByIds(Long[] messageId) {
        sysMessageService.removeSysMessageByIds(messageId);
        return new ResponseBase();
    }

    /**
     * 标为已读
     */
    @SystemHandleLog(description = "消息标为已读", type = LogsEnum.saveOrUpdate)
    @ResponseBody
    @RequestMapping(value = "/markRead", method = RequestMethod.POST)
    public ResponseBase markRead(Long[] messageId) {
        sysMessageService.updateRead(messageId);
        return new ResponseBase();
    }

    /**
     * 消息详情页
     */
    @RequestMapping("/detail")
    public String detail(Model model,Long id) {
        SysMessage message = sysMessageService.find(id);
        if (!message.isRead()) {
            sysMessageService.updateRead(new Long[]{id});
        }
        model.addAttribute("message", message);
        SearchResult<SysMessage> sr = sysMessageService.findSysMessagesByUserId(getUserId(), null, null);
        model.addAttribute("maxCount",sr.getTotalCount());
        return "/message/detail";
    }

}
