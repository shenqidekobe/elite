package com.ledao.elite.core.service.platform.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatforMarketing;
import com.ledao.elite.core.domain.platform.PlatforMarketing.Marketing_Platform;
import com.ledao.elite.core.domain.platform.PlatforMarketing.Marketing_Type;
import com.ledao.elite.core.domain.platform.PlatforMarketing.Role_Channel;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.repository.platform.PlatforMarketingRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.platform.PlatforMarketingService;
import com.ledao.elite.core.service.sys.SysUserService;

@Service
public class PlatforMarketingServiceImpl extends BaseSerivceImpl implements PlatforMarketingService {

	@Resource
	private PlatforMarketingRepository platforMarketingRepository;
	@Resource
	private SysUserService sysUserService;

	@Override
	public PlatforMarketing createPlatForMarketing(PlatforMarketing obj) throws EliteServiceException {
		this.verifyParams(obj.getTitle(), obj.getType(), obj.getUsePlatform());

		Search search = new Search();
		search.addSort("orders", true);
		List<PlatforMarketing> platforMarketingList = this.platforMarketingRepository.search(search);
		if (platforMarketingList.size() > 0) {
			obj.setOrders(platforMarketingList.get(0).getOrders() + 1);
		} else {
			obj.setOrders(1);
		}
		this.platforMarketingRepository.save(obj);
		return obj;
	}

	@Override
	public SearchResult<PlatforMarketing> findPlatforMarketings(String keyword, String usePlatform, String type,
			Pager pager) throws EliteServiceException {
		Search search = new Search();
		if (StringUtils.isNotEmpty(keyword)) {
			search.addFilterOr(new Filter("title", "%" + keyword + "%", Filter.OP_LIKE),
					new Filter("href", "%" + keyword + "%", Filter.OP_LIKE),
					new Filter("content", "%" + keyword + "%", Filter.OP_LIKE));
		}
		if (StringUtils.isNotEmpty(usePlatform)) {
			search.addFilterEqual("usePlatform", Marketing_Platform.valueOf(Marketing_Platform.class, usePlatform));
		}
		if (StringUtils.isNotEmpty(type)) {
			search.addFilterEqual("type", Marketing_Type.valueOf(Marketing_Type.class, type));
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		search.addSort("orders", false);
		SearchResult<PlatforMarketing> sr = this.platforMarketingRepository.searchAndCount(search);
		List<PlatforMarketing> list = sr.getResult();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCreateId() != null) {
				SysUser user = this.sysUserService.findSysUserById(list.get(i).getCreateId());
				if (user != null) {
					list.get(i).setCreateName(user.getLoginName());
				}
			}
		}
		sr.setResult(list);
		return sr;
	}

	@Override
	public List<PlatforMarketing> findPlatforMarketingList(Marketing_Platform use, Marketing_Type type,Role_Channel channel)
			throws EliteServiceException {
		this.verifyParams(use, type);
		Search search = new Search();
		search.addFilterEqual("usePlatform", use);
		if(channel!=null){
			search.addFilterEqual("channel", channel);
		}
		search.addFilterEqual("type", type);
		search.addSort("orders", false);
		return platforMarketingRepository.search(search);
	}

	@Override
	public PlatforMarketing findPlatforMarketingById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		PlatforMarketing obj= this.platforMarketingRepository.find(id);
		if(obj.getCreateId()!=null){
			SysUser user = this.sysUserService.findSysUserById(obj.getCreateId());
			if(user!=null){
	        	obj.setCreateName(user.getLoginName());
			}
		}
		return obj;
	}

	@Override
	public PlatforMarketing removePhysicalById(Long Id) throws EliteServiceException {
		this.verifyParams(Id);
		PlatforMarketing obj = this.platforMarketingRepository.find(Id);
		if (obj == null)
			throw new EliteServiceException(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return this.platforMarketingRepository.remove(obj) ? obj : null;
	}

	@Override
	public PlatforMarketing updatePlatforMarketing(Long id, String moveType) throws EliteServiceException {
		PlatforMarketing obj = this.platforMarketingRepository.find(id);
		// 被移动的选项
		PlatforMarketing movedObj = null;
		if (obj == null)
			throw new EliteServiceException("广告信息不存在", ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		List<PlatforMarketing> movedObjList;
		Search search = new Search();
		String title;
		if (GlobalDefinedConstant.Data_Operate_Type.up.name().equals(moveType)) {
			search.addSort("orders", true);
			search.addFilterLessThan("orders", obj.getOrders());
			search.addFilterEqual("type", obj.getType());
			title = "不能进行上移操作";
			movedObjList = this.platforMarketingRepository.search(search);
		} else {
			search.addSort("orders", false);
			search.addFilterEqual("type", obj.getType());
			search.addFilterGreaterThan("orders", obj.getOrders());
			title = "不能进行下移操作";
			movedObjList = this.platforMarketingRepository.search(search);
		}
		if (movedObjList.size() > 0) {
			movedObj = movedObjList.get(0);
			int objOrder = obj.getOrders();
			obj.setOrders(movedObj.getOrders());
			movedObj.setOrders(objOrder);
			this.platforMarketingRepository.save(movedObj);
			this.platforMarketingRepository.save(obj);
			return obj;
		} else {
			throw new EliteServiceException(title, ErrorCodeEnum.FAILURE.code);
		}

	}

	@Override
	public PlatforMarketing updatePlatforMarketingInfo(PlatforMarketing market) throws EliteServiceException {
		this.verifyParams(market.getId(), market.getTitle());
		PlatforMarketing  obj=this.findPlatforMarketingById(market.getId());
		obj.setTitle(market.getTitle());
		obj.setContent(market.getContent());
		obj.setHref(market.getHref());
		obj.setUsePlatform(market.getUsePlatform());
		obj.setLogoId(market.getLogoId());
		obj.setType(market.getType());
		this.platforMarketingRepository.save(obj);
		return obj;
	}

}
