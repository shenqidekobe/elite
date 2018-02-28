package com.ledao.elite.core.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.domain.sys.Dict.Dict_Type;
import com.ledao.elite.core.domain.sys.Dict.PartnerIncome_Option_Key;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.dto.PartnerCompanyIncomeRule;
import com.ledao.elite.core.framework.dto.PartnerEliteIncomeRule;
import com.ledao.elite.core.framework.dto.manager.DictMangerTree;
import com.ledao.elite.core.repository.sys.DictRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.sys.DictService;
import com.ledao.elite.core.utils.CommonUtils;

/**
 *
 *
 * @author zhiyu cao
 **/

@Service("dictService")
public class DictServiceImpl extends BaseSerivceImpl implements DictService {
	
	@Resource
	private DictRepository dictRepository;

	@Override
	public Dict createDict(Dict obj) throws EliteServiceException {
		this.verifyParams(obj);
		Pager pager = new Pager();

		String sort = pager.getSort();
		Search search = new Search();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("orders", true);
		}
		List<Dict> dictList = this.dictRepository.search(search);
		if (dictList.size() > 0) {
			obj.setOrders(dictList.get(0).getOrders() + 1);
		} else {
			obj.setOrders(1);
		}

		this.dictRepository.save(obj);
		return null;
	}

	@Override
	public SearchResult<Dict> findDictList(String dictType, String key, String val, Pager pager)
			throws EliteServiceException {

		return this.dictRepository.fuzzySearchDicts(dictType, key, val, pager);
	}

	@Override
	public Dict removePhysicalById(Long Id) throws EliteServiceException {

		this.verifyParams(Id);
		Dict obj = dictRepository.find(Id);
		if (obj == null)
			throw new EliteServiceException(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return this.dictRepository.remove(obj) ? obj : null;
	}

	@Override
	public Dict findById(Long Id) throws EliteServiceException {
		this.verifyParams(Id);
		return this.dictRepository.find(Id);
	}

	@Override
	public Dict findByKey(String key) throws EliteServiceException {
		this.verifyParams(key);
		return this.dictRepository.searchUnique(new Search().addFilterEqual("dictKey", key));
	}

	@Override
	public Dict updateDict(Long id, Dict dict) throws EliteServiceException {
		this.verifyParams(dict, dict.getDictKey(), dict.getDictType(), dict.getDictVal());
		Dict obj = this.dictRepository.find(id);
		if (obj == null)
			throw new EliteServiceException("数据字典不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);

		obj.setDictKey(dict.getDictKey());
		obj.setDictVal(dict.getDictVal());
		obj.setDictDesc(dict.getDictDesc());
		obj.setDictType(dict.getDictType());
		obj.setParentId(dict.getParentId());
		return this.dictRepository.save(obj) ? obj : null;

	}

	@Override
	public Dict findSysDictByIdAndKey(Long id, String dictKey) throws EliteServiceException {
		Search search = new Search();
		search.addFilterEqual("id", id);
		search.addFilterEqual("dictKey", dictKey);
		return this.dictRepository.searchUnique(search);
	}

	@Override
	public Dict updateMoveDict(Long id, String moveType) throws EliteServiceException {
		Dict obj = this.dictRepository.find(id);
		// 被移动的选项
		Dict movedObj = null;
		if (obj == null)
			throw new EliteServiceException("数据字典不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		List<Dict> movedObjList;
		Search search = new Search();
		String title;
		if (GlobalDefinedConstant.Data_Operate_Type.up.name().equals(moveType)) {
			search.addSort("orders", true);
			search.addFilterLessThan("orders", obj.getOrders());
			search.addFilterEqual("dictType", obj.getDictType());
			title = "不能进行上移操作";
			movedObjList = this.dictRepository.search(search);
		} else {
			search.addSort("orders", false);
			search.addFilterEqual("dictType", obj.getDictType());
			search.addFilterGreaterThan("orders", obj.getOrders());
			title = "不能进行下移操作";
			movedObjList = this.dictRepository.search(search);
		}
		if (movedObjList.size() > 0) {
			movedObj = movedObjList.get(0);
			int objOrder = obj.getOrders();
			obj.setOrders(movedObj.getOrders());
			movedObj.setOrders(objOrder);
			this.dictRepository.save(movedObj);
			this.dictRepository.save(obj);
			return obj;
		} else {
			throw new EliteServiceException(title, ErrorCodeEnum.FAILURE.code);
		}

	}

	@Override
	public List<DictMangerTree> findDictTreeChildren(Long parentId, String dictType) throws EliteServiceException {
		this.verifyParams(dictType);
		List<DictMangerTree> resultList = new ArrayList<DictMangerTree>();
		List<Dict> dictList = null;
		String tp = DictMangerTree.DictMangerTree_TYPE.sub.name();
		if (parentId == null) {
			dictList = this.dictRepository.findRootDictsByDictType(dictType);
			tp = DictMangerTree.DictMangerTree_TYPE.first.name();
		} else {
			dictList = this.dictRepository.queryDictByParentId(parentId, dictType);
		}
		for (Dict dict : dictList) {
			resultList.add(new DictMangerTree(dict.getId(), dict.getId(), 0L, dict.getDictVal(), 1, tp, true, true));
		}
		return resultList;

	}

	@Override
	public List<Dict> findRootDictListByDictType(String dictType) throws EliteServiceException {
		this.verifyParams(dictType);
		return this.dictRepository.findRootDictsByDictType(dictType);
	}

	@Override
	public List<Dict> findDictListByDictType(String dictType) throws EliteServiceException {
		this.verifyParams(dictType);
		Search search = new Search();
		search.addFilterEqual("dictType", dictType);
		return this.dictRepository.search(search);
	}

	@Override
	public List<Dict> findDictsByDictType(String dictType, Long parentId) throws EliteServiceException {
		this.verifyParams(dictType);
		return this.dictRepository.queryDictByParentId(parentId, dictType);
	}

	@Override
	public Dict findDictByKeyAndType(String dictType, String dictKey) throws EliteServiceException {
		this.verifyParams(dictType, dictKey);
		return this.dictRepository.findDictByTypeAndKey(dictType, dictKey);
	}

	@Override
	public String findDictValsByKeys(String dictType, String dictKeys, String joinSymbol) throws EliteServiceException {
		this.verifyParams(dictType, joinSymbol);
		if (StringUtils.isEmpty(dictKeys))
			return "";
		String result = null;
		List<String> keys = CommonUtils.separatorStrToList(dictKeys,
				GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
		Search search = new Search();
		search.addFilterEqual("dictType", dictType);
		search.addFilterIn("dictKey", keys);
		List<Dict> dictList = this.dictRepository.search(search);
		for (Dict dict : dictList) {
			if (result == null) {
				result = dict.getDictVal();
			} else {
				result = result + joinSymbol + dict.getDictVal();
			}
		}
		return result;
	}

	@Override
	public Object findDictPartnerRule(String dictType) throws EliteServiceException {
		Search search = new Search();
		String dictKey;
		boolean isPartnerCompany = false;
		if (dictType.equals(Dict_Type.partnerCompany_option.name())) {
			dictKey = PartnerIncome_Option_Key.PartnerCompanyIncomeRule.name();
			isPartnerCompany = true;
		} else {
			dictKey = PartnerIncome_Option_Key.PartnerEliteIncomeRule.name();
		}
		search.addFilterEqual("dictKey", dictKey);
		Dict dict = this.dictRepository.searchUnique(search);
		if (dict != null) {
			if (dict.getDictVal() != null) {
				Gson gson = new Gson();
				if (isPartnerCompany) {
					return gson.fromJson(dict.getDictVal(), PartnerCompanyIncomeRule.class);
				} else {
					return gson.fromJson(dict.getDictVal(), PartnerEliteIncomeRule.class);
				}
			}
		}
		if (isPartnerCompany) {
			return new PartnerCompanyIncomeRule();
		} else {
			return new PartnerEliteIncomeRule();
		}
	}

	@Override
	public Dict createDictPartnerCompanyRule(String key, String dictType, Object rule) throws EliteServiceException {
		Search search = new Search();
		search.addFilterEqual("dictKey", key);
		Dict dict = this.dictRepository.searchUnique(search);
		Gson gson = new Gson();
		String val = gson.toJson(rule);
		if (dict == null) {
			dict = new Dict();
			dict.setDictKey(key);
		}
		dict.setDictVal(val);
		dict.setDictType(dictType);
		this.dictRepository.save(dict);
		return dict;

	}

	@Override
	public List<Dict> findDicJobRoleTop(String dictType) throws EliteServiceException {
    	return	dictRepository.findRootDictsByDictType(dictType);
	}
}
