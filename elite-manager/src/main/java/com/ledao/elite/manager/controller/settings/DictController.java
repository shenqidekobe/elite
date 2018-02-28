package com.ledao.elite.manager.controller.settings;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.domain.sys.Dict.PartnerIncome_Option_Key;
import com.ledao.elite.core.framework.annotation.SystemHandleLog;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.PartnerCompanyIncomeRule;
import com.ledao.elite.core.framework.dto.PartnerEliteIncomeRule;
import com.ledao.elite.core.service.sys.DictService;
import com.ledao.elite.manager.model.DictModel;

import flexjson.JSONSerializer;

/**
 * 数据字典控制器
 * 
 */
@Controller("dictController")
@RequestMapping("/settings")
public class DictController {

	@Resource
	private DictService dictService;

	/**
	 * 数据字典首页
	 */
	@RequestMapping(value="/dict",method=RequestMethod.GET)
	public String list(Model model) {
		Dict.Dict_Type lables[] = Dict.Dict_Type.values();
		List<String> labelList = new ArrayList<String>();
		List<Dict> dictList = new ArrayList<Dict>();
		for (int i = 0; i < lables.length; i++) {
			labelList.add(lables[i].label);
			Dict dict = new Dict();
			dict.setDictKey(lables[i] + "");
			dict.setDictDesc(lables[i].label);
			dictList.add(dict);
		}
		model.addAttribute("typeList", dictList);
		return "/settings/dict/list";
	}

	/**
	 * 根据dicKey，dictVal分页模糊查询
	 * 
	 * @param model
	 * @param dictType
	 * @param dictKey
	 * @param dictVal
	 * @param pager
	 * @return
	 */
	@RequestMapping(value="/dict/listData",method = RequestMethod.POST)
	public String listData(Model model, String dictType, String dictKey, String dictVal, Pager pager) {
		if (null == dictType || "" == dictType) {
			dictType = Dict.Dict_Type.values()[0].name();
		}
		SearchResult<Dict> dictResult = this.dictService.findDictList(dictType, dictKey, dictVal, pager);
		List<Dict> dictList = dictResult.getResult();
		pager.calPageCount((long) dictResult.getTotalCount());
		model.addAttribute("list", dictList);
		model.addAttribute("pager", pager);
		return "/settings/dict/list_frag";
	}

	/**
	 * 创建修改数据字典
	 * 
	 * @param dict
	 * @param
	 */
	@SystemHandleLog(description = "创建或者修改数据字典", type = LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value="/dict/save",method = RequestMethod.POST)
	public ResponseBase createDict(Model model, Dict dict) {
		if (dict.getId() == null) {
			this.dictService.createDict(dict);
		} else {
			this.dictService.updateDict(dict.getId(), dict);
		}
		return new ResponseBase();
	}

	/**
	 * 检测key是否存在
	 * 
	 * @param id
	 * @param dictKey
	 */
	@ResponseBody
	@RequestMapping(value = "/dict/check/key")
	public Boolean checkLoginName(Long id, String dictKey) {
		if (id != null) {
			Dict dict = this.dictService.findById(id);
			if (dict.getDictKey().equals(dictKey)) {
				return true;
			}
		}
		Dict dict = this.dictService.findSysDictByIdAndKey(id, dictKey);
		return dict == null;
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 */
	@ResponseBody
	@RequestMapping(value = "/dict/view")
	public String findDictById(Long id) {
		Dict dict = this.dictService.findById(id);
		
		//懒加载转接送冲突
		dict.setChildren(null);
		dict.setParent(null);
		//
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		return serializer.serialize(dict);
	}

	/**
	 * 物理删除数据字典
	 * 
	 * @param id
	 */
	@SystemHandleLog(description = "删除数据字典", type = LogsEnum.remove)
	@ResponseBody
	@RequestMapping(value = "/dict/remove")
	public ResponseBase removeDict(Long id) {
		this.dictService.removePhysicalById(id);
		return new ResponseBase();
	}

	/**
	 * 上下移动
	 * 
	 * @param movetType
	 * @param id
	 */
	@SystemHandleLog(description = "数据字典上下移动", type = LogsEnum.update)
	@ResponseBody
	@RequestMapping(value = "/dict/move")
	public ResponseBase moveDict(String moveType, Long id) {
		this.dictService.updateMoveDict(id, moveType);
		return new ResponseBase();
	}

	/**
	 * 设置查询
	 */
	@RequestMapping(value = "/dict/option/view")
	public String viewOption(Model model, String dictType) {
		if(Dict_Type.partnerCompany_option.name().equals(dictType)||Dict_Type.partnerElite_option.name().equals(dictType)){
			model.addAttribute("it", this.dictService.findDictPartnerRule(dictType));
		}else{
		List<Dict> dictList = this.dictService.findDictListByDictType(dictType);
		for (int i = 0; i < dictList.size(); i++) {
			model.addAttribute(dictList.get(i).getDictKey(), dictList.get(i).getDictVal());
		}
		}
		String dictReturn=dictType.replace("_option", "");
		return "/settings/dict/" + dictReturn + "_setting";
	}

	/**
	 * 设置创建修改
	 */
	@SystemHandleLog(description = "创建或者更新数据字典", type = LogsEnum.saveOrUpdate)
	@ResponseBody
	@RequestMapping(value = "/dict/option/save",method = RequestMethod.POST)
	public ResponseBase createOption(DictModel dicts, String dictType) {
		List<Dict> dictList = dicts.getDicts();
		for (int i = 0; i < dictList.size(); i++) {
			Dict obj = this.dictService.findDictByKeyAndType(dictType, dictList.get(i).getDictKey());
			if (null == obj || null == obj.getId()) {
				dictList.get(i).setDictType(dictType);
				this.dictService.createDict(dictList.get(i));
			} else {
				obj.setDictKey(dictList.get(i).getDictKey());
				obj.setDictVal(dictList.get(i).getDictVal());
				this.dictService.updateDict(obj.getId(), obj);
			}
		}
		return new ResponseBase();
	}

	/**
	 * 查询数据字典的树形结构
	 * 
	 * @param dictId
	 * @param parentId
	 */
	@ResponseBody
	@RequestMapping("/dict/tree/listData")
	public String listTreeData(Long parentId) {
		return JSON.toJSONString(this.dictService.findDictTreeChildren(parentId, Dict_Type.job_role.name()));
	}
	/**
	 * 创建，更新项目渠道收益规则
	 */
	@ResponseBody
	@RequestMapping(value = "/dict/option/partnerCompanyRule/save",method = RequestMethod.POST)
	public ResponseBase createPartnerCompanyRule(PartnerCompanyIncomeRule rule) {
		this.dictService.createDictPartnerCompanyRule(PartnerIncome_Option_Key.PartnerCompanyIncomeRule.name(),Dict_Type.partnerCompany_option.name(), rule);
		return new ResponseBase();
	}
	/**
	 * 查询渠道收益规则
	 */
	@ResponseBody
	@RequestMapping(value = "/dict/option/partnerEliteRule/save",method = RequestMethod.POST)
	public ResponseBase createPartnerEliteRule(PartnerEliteIncomeRule rule) {
		this.dictService.createDictPartnerCompanyRule(PartnerIncome_Option_Key.PartnerEliteIncomeRule.name(),Dict_Type.partnerElite_option.name(), rule);
		return new ResponseBase();
	}

	
}
