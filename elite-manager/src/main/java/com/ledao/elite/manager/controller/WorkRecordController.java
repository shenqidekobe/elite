package com.ledao.elite.manager.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatformWorkRecord;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.platform.PlatformWorkRecordService;
import com.ledao.elite.core.service.sys.SysUserService;

/**
 * 平台工作记录控制器
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller("workRecordController")
@RequestMapping("/work")
public class WorkRecordController extends BaseController {

	@Resource
	private PlatformWorkRecordService platformWorkRecordService;
	@Resource
	private SysUserService SysUserService;

	/**
	 * 保存工作记录
	 */
	@SystemHandleLog(description = "保存工作记录", type = LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseBase save(PlatformWorkRecord obj) {

		obj.setUserId(getUserId());
		if (obj.getId() == null) {
			this.platformWorkRecordService.createPlatformWorkRecord(obj);
		} else {
			this.platformWorkRecordService.updatePlatformWorkRecord(obj);
		}
		return new ResponseBase();
	}

	/**
	 * 查询单条工作记录
	 */
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public PlatformWorkRecord findById(Long id) {
		return this.platformWorkRecordService.findPlatformWorkRecordById(id);
	}

	/**
	 * 查询工作记录列表
	 */
	@RequestMapping(value = "/listData", method = RequestMethod.POST)
	public String findList(String type, Long foreignId, Pager pager, Model model) {
		SearchResult<PlatformWorkRecord> sr = this.platformWorkRecordService.findPlatformWorkRecordList(type, foreignId,
				getUserId(), pager);
		pager.calPageCount((long) sr.getTotalCount());
		List<PlatformWorkRecord> list = sr.getResult();
		for(int i=0;i<list.size();i++){
			Long memberId=list.get(i).getUserId();
			if(memberId!=null){
				SysUser user=this.SysUserService.findSysUserById(memberId);
				list.get(i).setRealName(user.getLoginName());
			}
		}
		model.addAttribute("foreignId",foreignId);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		return "/workrecord/list_frag";
	}

	/**
	 * 删除工作记录
	 */
	@SystemHandleLog(description = "删除工作记录", type = LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseBase remove(Long id) {
		this.platformWorkRecordService.removePlatformWorkRecordById(id);
		return new ResponseBase();
	}
	/**
	 * 更新工作记录
	 */
	@SystemHandleLog(description = "更新工作记录", type = LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseBase update(PlatformWorkRecord obj) {
		obj.setUserId(getUserId());
		this.platformWorkRecordService.updatePlatformWorkRecord(obj);
		return new ResponseBase();
	}

}
