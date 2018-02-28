package com.ledao.elite.core.service.platform.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatformFund;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Status;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Type;
import com.ledao.elite.core.domain.sys.CommonCode.COMMON_PREVAL;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.platform.PlatformFundRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.platform.PlatformFundService;
import com.ledao.elite.core.service.sys.CommonCodeService;

@Service("platformFundService")
public class PlatformFundServiceImpl extends BaseSerivceImpl implements PlatformFundService{
	
	@Resource
	private PlatformFundRepository platformFundRepository;
	
	@Resource
	private CommonCodeService commonCodeService;

	@Override
	public PlatformFund createPlatformFund(String title,String orderId,Long projectId,Long stageId,Long memberId,BigDecimal amount,PlatformFund_Type type,PlatformFund_Status status) throws EliteServiceException {
		this.verifyParams(title,orderId,memberId,type);
		PlatformFund obj=new PlatformFund();
		String fundNum=commonCodeService.disposeOddNumber("fundNum", COMMON_PREVAL.S.name(), "yyyyMMdd", 8, null);
		obj.setFundNum(fundNum);
		obj.setTitle(title);
		obj.setOrderId(orderId);
		obj.setProjectId(projectId);
		obj.setStageId(stageId);
		obj.setMemberId(memberId);
		obj.setAmount(amount);
		obj.setType(type);
		obj.setStatus(status);
		this.platformFundRepository.save(obj);
		return obj;
	}
	
	@Override
	public void updatePlatformFund(String orderId,PlatformFund_Status status)throws EliteServiceException{
		this.verifyParams(orderId,status);
		List<PlatformFund> list=this.platformFundRepository.search(new Search().addFilterEqual("orderId", orderId));
		for(PlatformFund fund:list){
			fund.setStatus(status);
			//如果是提交的意向金、关闭直接改为删除(提交意向金未成功可以删除项目导致对象关联失败)
			if(PlatformFund_Status.close.equals(status)&&PlatformFund_Type.intention.equals(fund.getType())){
				this.platformFundRepository.remove(fund);
			}else{
				this.platformFundRepository.save(fund);
			}
		}
	}

	@Override
	public PlatformFund findPlatformFundById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.platformFundRepository.find(id);
	}

	@Override
	public SearchResult<PlatformFund> findPlatformFundList(Long memberId, Long projectId, String type,String keyword,
			Pager pager) throws EliteServiceException {
		this.verifyParams(memberId);
		Search search = new Search(PlatformFund.class);
		search.addFilterEqual("memberId",memberId);
		if(projectId!=null){
			search.addFilterEqual("projectId", projectId);
		}
		if(StringUtils.isNotEmpty(type)){
			if(type.equals(PlatformFund_Type.prostage.name())){
				search.addFilterIn("type", PlatformFund_Type.valueOf(PlatformFund_Type.class, type),PlatformFund_Type.intention);
			}else{
				search.addFilterEqual("type", PlatformFund_Type.valueOf(PlatformFund_Type.class, type));
			}
		}
		if(StringUtils.isNotEmpty(keyword)){
			search.addFilterOr(new Filter("orderId", keyword, Filter.OP_LIKE),new Filter("projectId", keyword, Filter.OP_LIKE),new Filter("title", keyword, Filter.OP_LIKE));
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime",true);
		}
		return this.platformFundRepository.searchAndCount(search);
	}

}
