package com.ledao.elite.manager.controller.settings;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ledao.elite.core.domain.sys.SysDept;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.service.sys.SysDeptService;
import com.ledao.elite.manager.controller.BaseController;

import flexjson.JSONSerializer;

/**
 * 部门管理控制器
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("deptController")
@RequestMapping("/settings")
public class SysDeptController extends BaseController{

	
	@Resource
	private SysDeptService sysDeptService;
	
	/**
	 * 部门首页
	 * */
	@RequestMapping("/dept")
	public String list(){
		return "/settings/dept/list";
	}
	
	
	/**
	 * 部门树数据
	 * */
	@ResponseBody
	@RequestMapping(value="/dept/listData",method=RequestMethod.POST)
	public String listData(Long organId,Long parentId){
		return JSON.toJSONString(sysDeptService.findDeptTreeChildren(true,organId, parentId));
	}
	
	/**
	 * 保存部门信息
	 * */
	@SystemHandleLog(description="保存部门信息",type=LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value="/dept/save",method=RequestMethod.POST)
	public String save(SysDept dept){
		SysDept obj=null;
		if(dept.getId()==null){
			obj=this.sysDeptService.create(dept);
		}else{
			obj=this.sysDeptService.updateSysDept(dept.getId(),dept);
		}
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("sysOrgan", "users","roles","parent","children","*.class");
		return serializer.serialize(obj);
	}
	
	/**
	 * 删除部门
	 * */
	@SystemHandleLog(description="删除部门信息",type=LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value="/dept/remove",method=RequestMethod.POST)
	public ResponseBase find(Long deptId){
		this.sysDeptService.removeLogicById(deptId);
		return new ResponseBase();
	}
	
	/**
	 * 判断部门名是否存在
	 * 
	 * @param
	 * @param
	 */
	@ResponseBody
	@RequestMapping(value = "/dept/check/deptName")
	public Boolean checkLoginName(Long id,Long organId,String name) {
		if(id!=null){
			SysDept dept=this.sysDeptService.findById(id);
			if(dept!=null&&dept.getName().equals(name)){
				return true;
			}
		}
		SysDept dept=this.sysDeptService.findSysDeptByOrganIdAndName(organId, name);
		return dept==null;
	}
}
