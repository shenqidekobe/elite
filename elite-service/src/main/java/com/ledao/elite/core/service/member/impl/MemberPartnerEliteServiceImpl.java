package com.ledao.elite.core.service.member.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberPartnerElite;
import com.ledao.elite.core.domain.member.MemberPartnerElite.MemberPartnerElite_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberPassport.Member_Status;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.member.MemberCreditRepository;
import com.ledao.elite.core.repository.member.MemberPartnerEliteRepository;
import com.ledao.elite.core.repository.member.MemberPassportRepository;
import com.ledao.elite.core.repository.partner.PartnerEliteRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberBasicService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberIncomeService;
import com.ledao.elite.core.service.member.MemberPartnerEliteService;

@Service("memberPartnerEliteService")
public class MemberPartnerEliteServiceImpl extends BaseSerivceImpl implements MemberPartnerEliteService {

	@Resource
	private MemberPartnerEliteRepository memberPartnerEliteRepository;
	@Resource
	private PartnerEliteRepository partnerEliteRepository;
	@Resource
	private MemberBasicService memberBasicService;
	@Resource
	private MemberIncomeService memberIncomeService;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private MemberPassportRepository memberPassportRepository;
	@Resource
	private MemberCreditRepository memberCreditRepository;

	@Override
	public MemberPartnerElite createMemberPartnerElite(MemberPartnerElite obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getMemberId());
		// 初始创建会员为待审核状态
		obj.setStatus(MemberPassport.Member_Status.waitAduit);
		this.memberPartnerEliteRepository.save(obj);
		return obj;
	}

	@Override
	public void createMemberBasicInfo(String type, MemberBasic basic) throws EliteServiceException {
		this.verifyParams(type, basic.getEmail(), basic.getAreaName());
		MemberBasic obj = this.memberBasicService.findMemberBasicByMemberId(basic.getMemberId());
		if (obj == null) {
			obj = new MemberBasic();
			obj.setMemberId(basic.getMemberId());
		}
		obj.setEmail(basic.getEmail());
		obj.setPhotoId(basic.getPhotoId());
		obj.setAreaName(basic.getAreaName());
		obj.setSex(basic.getSex());

		if (obj.getId() == null) {
			this.memberBasicService.createMemberBasic(obj);
		} else {
			this.memberBasicService.updateBasicInfoNoFixed(obj);
		}
		MemberPartnerElite elite = this.findMemberPartnerEliteByMemberId(basic.getMemberId());
		elite.setType(MemberPartnerElite_Type.valueOf(MemberPartnerElite_Type.class, type));
		this.memberPartnerEliteRepository.save(elite);
	}

	@Override
	public MemberPartnerElite updateMemberPartnerElite(Long memberId, MemberPartnerElite obj)
			throws EliteServiceException {
		MemberPartnerElite pojo = this.findMemberPartnerEliteByMemberId(memberId);
		if (pojo == null)
			return null;
		BeanUtils.copyProperties(obj, pojo);
		this.memberPartnerEliteRepository.save(pojo);
		return pojo;
	}

	@Override
	public MemberPartnerElite updateMemberPartnerEliteStatus(Long memberId, String status)
			throws EliteServiceException {
		MemberPartnerElite pojo = this.findMemberPartnerEliteByMemberId(memberId);
		if (pojo == null)
			return null;
		pojo.setStatus(Member_Status.valueOf(Member_Status.class, status));
		this.memberPartnerEliteRepository.save(pojo);
		return pojo;
	}

	@Override
	public MemberPartnerElite updateMemberPartnerEliteAudit(MemberPartnerElite elite) throws EliteServiceException {
		MemberPartnerElite pojo = this.findMemberPartnerEliteByMemberId(elite.getMemberId());
		if (pojo == null)
			return null;
		pojo.setAuditId(elite.getAuditId());
		pojo.setAuditReason(elite.getAuditReason());
		pojo.setAuditTime(new Date());
		pojo.setStatus(elite.getStatus());
		if (elite.getParentId() != null) {
			pojo.setParentId(elite.getParentId());
		}
		this.memberPartnerEliteRepository.save(pojo);
		return pojo;
	}

	@Override
	public MemberPartnerElite findMemberPartnerEliteByMemberId(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		return this.memberPartnerEliteRepository.searchUnique(new Search().addFilterEqual("memberId", memberId));
	}

	@Override
	public MemberPartnerElite updateMemberPartnerEliteInfo(Long id, MemberPartnerElite elite)
			throws EliteServiceException {
		this.verifyParams(elite.getId());
		this.memberPartnerEliteRepository.save(elite);
		return elite;
	}

	@Override
	public void updateMemberPartnerEliteBasicInfo(Long memberId, String nickName, MemberBasic basic,
			MemberPartnerElite elite, MemberCredit credit) throws EliteServiceException {
		MemberBasic basicObj = this.memberBasicService.findMemberBasicByMemberId(memberId);
		this.verifyParams(memberId);
		if (basicObj == null) {
			basicObj = new MemberBasic();
			basicObj.setMemberId(memberId);
		}
		basicObj.setBirthday(basic.getBirthday());
		basicObj.setEmail(basic.getEmail());
		basicObj.setAreaName(basic.getAreaName());
		basicObj.setSex(basic.getSex());
		if (basicObj.getId() == null) {
			this.memberBasicService.createMemberBasic(basicObj);
		} else {
			this.memberBasicService.updateBasicInfoNoFixed(basicObj);
		}
		MemberPartnerElite eliteObj = this.findMemberPartnerEliteByMemberId(memberId);
		eliteObj.setCompanyName(elite.getCompanyName());
		eliteObj.setJobAge(elite.getJobAge());
		eliteObj.setAttentionIndustry(elite.getAttentionIndustry());
		eliteObj.setGoodatJob(elite.getGoodatJob());
		eliteObj.setType(elite.getType());
		this.memberPartnerEliteRepository.save(eliteObj);

		MemberPassport passport = this.memberPassportRepository.find(memberId);
		passport.setNickName(nickName);
		this.memberPassportRepository.save(passport);
	}

	@Override
	public void updateEliteInfoNoFixed(MemberPartnerElite elite) throws EliteServiceException {
		this.verifyParams(elite, elite.getId());
		this.memberPartnerEliteRepository.save(elite);
	}
	
	@Override
	public void updateMemberPartnerEliteAsCalculate(Long id,Integer taskCount,BigDecimal taskTotalAmount,Integer calculateEliteCount,
			Integer calculateTaskCount,BigDecimal calculateTaskTotalAmount,Date startOrderTime,Date lastOrderTime)throws EliteServiceException{
		this.memberPartnerEliteRepository.updateMemberPartnerEliteAsCalculate(id, taskCount, taskTotalAmount, calculateEliteCount,
				calculateTaskCount, calculateTaskTotalAmount, startOrderTime, lastOrderTime);
	}
	
	@Override
	public void updateMemberPartnerEliteClearCalculate(Long id,Date clearCalculateTime)throws EliteServiceException{
		this.memberPartnerEliteRepository.updateMemberPartnerEliteClearCalculate(id, clearCalculateTime);
	}

	@Override
	public void createMemberPartnerEliteIndustry(Long memberId, MemberPartnerElite elite, MemberBasic basic)
			throws EliteServiceException {
		this.createMemberPartnerElite(elite);
		MemberBasic obj = this.memberBasicService.findMemberBasicByMemberId(memberId);
		if (obj == null) {
			obj = new MemberBasic();
			obj.setMemberId(memberId);
		}
		if (obj.getId() == null) {
			this.memberBasicService.createMemberBasic(obj);
		} else {
			this.memberBasicService.updateBasicInfoNoFixed(obj);
		}
		this.memberPartnerEliteRepository.save(elite);
	}

	@Override
	public void updateEliteInfoCount(Long id, Integer putCount, Integer enterCount,Integer calculateEliteCount) throws EliteServiceException {
		this.memberPartnerEliteRepository.updateEliteInfoCount(id, putCount, enterCount,calculateEliteCount);
	}

	@Override
	public MemberPartnerElite findMemberPartnerEliteById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return memberPartnerEliteRepository.find(id);
	}

	@Override
	public List<MemberPartnerElite> findMemberPartnerEliteListTopThree(Long parentId, Date startTime, String status,
			Date endTime) throws EliteServiceException {
		this.verifyParams(parentId);
		Pager pager = new Pager();
		pager.setPageSize(3);
		List<MemberPartnerElite> list = this.memberPartnerEliteRepository.findMemberPartnerEliteList(parentId, status,
				startTime, endTime, pager);

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMemberId() != null) {
				MemberBasic basic = this.memberBasicService.findMemberBasicByMemberId(list.get(i).getMemberId());
				list.get(i).setMemberBasic(basic);

				// 收益
				BigDecimal income = this.memberIncomeService.findMemberIncomeSumByMemberId(list.get(i).getMemberId(),
						startTime, endTime, null);
				list.get(i).setPartnerIncome(income);

				// 推荐的渠道
				Integer putParnterCount = this.memberPartnerEliteRepository
						.findMemberPartnerPutCount(list.get(i).getId(), startTime, endTime);
				list.get(i).setPutParnterCount(putParnterCount);

				// 推荐的人才
				Integer putCount = this.partnerEliteRepository.findPartnerEliteByKeywordCount(null, null, null,
						list.get(i).getId(), null, startTime, endTime, null);
				list.get(i).setPutCount(putCount);
			}

		}
		return list;
	}
	
	@Override
	public List<MemberPartnerElite> findMemberPartnerEliteListByStatus(Member_Status status) throws EliteServiceException{
		return this.memberPartnerEliteRepository.search(new Search().addFilterEqual("status", status));
	}

	@Override
	public SearchResult<MemberPartnerElite> findMemberPartnerEliteListByPartnerId(Long partnerId, Date startTime,
			Date endTime, String status, Pager pager) throws EliteServiceException {
		SearchResult<MemberPartnerElite> sr = new SearchResult<MemberPartnerElite>();
		Integer totalCount = this.memberPartnerEliteRepository.findMemberPartnerEliteListCount(partnerId, status,
				startTime, endTime);
		pager.setStartIndex(pager.getStartIndex() + 3);
		List<MemberPartnerElite> list = this.memberPartnerEliteRepository.findMemberPartnerEliteList(partnerId, status,
				startTime, endTime, pager);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMemberId() != null) {
				MemberBasic basic = this.memberBasicService.findMemberBasicByMemberId(list.get(i).getMemberId());
				list.get(i).setMemberBasic(basic);

				// 收益
				BigDecimal income = this.memberIncomeService.findMemberIncomeSumByMemberId(list.get(i).getMemberId(),
						startTime, endTime, null);
				list.get(i).setPartnerIncome(income);

				// 推荐的渠道
				Integer putParnterCount = this.memberPartnerEliteRepository
						.findMemberPartnerPutCount(list.get(i).getId(), startTime, endTime);
				list.get(i).setPutParnterCount(putParnterCount);

				// 推荐的人才
				Integer putCount = this.partnerEliteRepository.findPartnerEliteByKeywordCount(null, null, null,
						list.get(i).getId(), null, startTime, endTime, null);
				list.get(i).setPutCount(putCount);
			}

		}
		if (totalCount > 2) {
			totalCount = totalCount - 3;
		} else {
			totalCount = 0;
		}
		sr.setResult(list);
		sr.setTotalCount(totalCount);
		return sr;
	}

	@Override
	public void createMemberCredit(MemberCredit memberCredit, MemberCredit obj) throws EliteServiceException {

		if (obj == null) {
			this.memberCreditService.createMemberCredit(memberCredit);
		} else {
			obj.setCardJust(memberCredit.getCardJust());
			obj.setJobCert(memberCredit.getJobCert());
			obj.setBusinessCert(memberCredit.getBusinessCert());
			obj.setPmpCret(memberCredit.getPmpCret());
			obj.setCardReverse(memberCredit.getCardReverse());
			if (!obj.getIsCard()) {
				
				if(!memberCredit.getIdCard().equals(obj.getId())){
					MemberCredit newobj = this.memberCreditRepository.queryMemberCreditByIdCard(obj.getIdCard());
					if(newobj!=null){
						throw new EliteServiceException("身份证号:"+memberCredit.getIdCard()+"已存在",ErrorCodeEnum.IDCARD_EXIST.code);
					}
				}
				obj.setRealName(memberCredit.getRealName());
				obj.setIdCard(memberCredit.getIdCard());
				
			}
			obj.setCard(true);
			this.memberCreditService.updateCreditInfoNoFixed(obj);
		}
	}

	@Override
	public Integer findMemberPartnerEliteIndex(Long partnerId, Long memberId, Date startTime, Date endTime,
			String status) {
		List<MemberPartnerElite> list = this.memberPartnerEliteRepository.findMemberPartnerEliteList(partnerId, status,
				startTime, endTime);
		int result = 0;
		for (int i = 0; i < list.size(); i++) {
			if (memberId.equals(list.get(i).getMemberId())) {
				result = i + 1;
				break;
			}
		}
		return result;
	}
	
	@Override
	public Map<String,Object> findPartnerEliteDirectCount(Long id,Date startTime,Date endTime)throws EliteServiceException{
		Map<String,Object> resultMap=new HashMap<String, Object>();
		MemberPartnerElite partner=this.memberPartnerEliteRepository.find(id);
		Long partnerId=partner.getParentId();
		if(partnerId==null)return resultMap;
		MemberPartnerElite parentPartner=this.memberPartnerEliteRepository.find(partnerId);
		List<MemberPartnerElite> resultList=getChildrenPartner(parentPartner.getId());
		Integer eliteCount=0;
		Integer taskCount=0;
		BigDecimal taskAmount=new BigDecimal(0);
		//直接=本人的上级推荐的渠道的人才总数
		for(MemberPartnerElite pce:resultList){
			Integer aa=pce.getCalculateEliteCount()==null?0:pce.getCalculateEliteCount();
			Integer bb=pce.getCalculateTaskCount()==null?0:pce.getCalculateTaskCount();
			BigDecimal cc=pce.getCalculateTotalAmount()==null?new BigDecimal(0):pce.getCalculateTotalAmount();
			eliteCount+=aa;
			taskCount+=bb;
			taskAmount=taskAmount.add(cc);
		}
		resultMap.put("eliteCount", eliteCount);
		resultMap.put("taskCount", taskCount);
		resultMap.put("taskAmount", taskAmount);
		return resultMap;
	}
	
	@Override
	public Map<String,Object> findPartnerEliteInDirectCount(Long id,Date startTime,Date endTime)throws EliteServiceException{
		Map<String,Object> resultMap=new HashMap<String, Object>();
        MemberPartnerElite partner=this.memberPartnerEliteRepository.find(id);
        Long partnerId=partner.getParentId();
		if(partnerId==null)return resultMap;
	    MemberPartnerElite parentPartner=this.memberPartnerEliteRepository.find(partnerId);
	    Long parentPartnerId=parentPartner.getParentId();
		if(parentPartnerId==null)return resultMap;
		MemberPartnerElite parentParentPartner=this.memberPartnerEliteRepository.find(parentPartnerId);
		List<MemberPartnerElite> resultList=new ArrayList<>();
		List<MemberPartnerElite> list=getChildrenPartner(parentParentPartner.getId());
		for(MemberPartnerElite mpe:list){
			resultList.addAll(getChildrenPartner(mpe.getId()));
		}
		//间接=本人的上级的上级下面的渠道推荐的渠道的人才数量
		Integer eliteCount=0;
		Integer taskCount=0;
		BigDecimal taskAmount=new BigDecimal(0);
		for(MemberPartnerElite pce:resultList){
			Integer aa=pce.getCalculateEliteCount()==null?0:pce.getCalculateEliteCount();
			Integer bb=pce.getCalculateTaskCount()==null?0:pce.getCalculateTaskCount();
			BigDecimal cc=pce.getCalculateTotalAmount()==null?new BigDecimal(0):pce.getCalculateTotalAmount();
			eliteCount+=aa;
			taskCount+=bb;
			taskAmount=taskAmount.add(cc);
		}
		resultMap.put("eliteCount", eliteCount);
		resultMap.put("taskCount", taskCount);
		resultMap.put("taskAmount", taskAmount);
		return resultMap;
	}
	
	private List<MemberPartnerElite> getChildrenPartner(Long parentId){
		Search search=new Search();
		search.addFilterEqual("parentId",parentId);
		return memberPartnerEliteRepository.search(search);
	}
	

	@Override
	public void updateMemberPartnerElitePartnerId(Long partnerId, Long id) throws EliteServiceException {
		this.verifyParams(id);
		this.memberPartnerEliteRepository.updateMemberPartnerElitePartnerIdBysql(partnerId, id);
		
	}

}
