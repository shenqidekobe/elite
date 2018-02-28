package com.ledao.elite.core.service.platform.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberMessage.MemberMessage_Type;
import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.domain.platform.PlatformInviteCode.InviteCode_Type;
import com.ledao.elite.core.domain.sys.CommonCode.COMMON_PREVAL;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.component.EventPublishService;
import com.ledao.elite.core.framework.config.LocalCoreConfig;
import com.ledao.elite.core.repository.platform.PlatformInviteCodeRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.platform.PlatformInviteCodeService;
import com.ledao.elite.core.service.sys.CommonCodeService;
import com.ledao.elite.core.utils.DateTimeUtils;
import com.ledao.elite.core.utils.encry.Md5;

@Service("platformInviteCodeService")
public class PlatformInviteCodeServiceImpl extends BaseSerivceImpl implements PlatformInviteCodeService {

	@Resource
	private PlatformInviteCodeRepository platformInviteCodeRepository;
	@Resource
	private CommonCodeService commonCodeService;
	@Resource
	private LocalCoreConfig localCoreConfig;
	@Resource
	private EventPublishService eventPublishService;

	@SuppressWarnings("incomplete-switch")
	@Override
	public PlatformInviteCode createPlatformInviteCode(PlatformInviteCode obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getType());
		// TODO:生成邀请码
		String inviteCode = commonCodeService.disposeOddNumber("inviteCode", COMMON_PREVAL.IV.name(), "yyyyMM", 6,null);
		inviteCode = Md5.getFixMD5(inviteCode, 4);// 加密成8位字符串
		obj.setInviteCode(inviteCode);
		switch (obj.getType()) {
		case channel_company:
			obj.setMaxCount(Integer.MAX_VALUE);
			break;
		case channel_elite:
			obj.setMaxCount(Integer.MAX_VALUE);
			break;
		case member:
			obj.setMaxCount(1);
			break;
		case platform_infinite:
			obj.setMaxCount(Integer.MAX_VALUE);
			break;
		}
		this.platformInviteCodeRepository.save(obj);
		return obj;
	}

	@Override
	public void createPlatformInviteCodesToElite(long[] ids) throws EliteServiceException {
		// TODO Auto-generated method stub
		for (int i = 0; i < ids.length; i++) {
			String inviteCode = commonCodeService.disposeOddNumber("inviteCode", COMMON_PREVAL.IV.name(), "yyyyMM", 6,
					null);
			inviteCode = Md5.getFixMD5(inviteCode, 4);// 加密成8位字符串
			PlatformInviteCode obj = new PlatformInviteCode();
			obj.setInviteCode(inviteCode);
			obj.setUserId(ids[i]);
			obj.setType(InviteCode_Type.member);
			// 失效时间一个月
			obj.setExpireTime(DateTimeUtils.addMonth(new Date(), 1));
			this.platformInviteCodeRepository.save(obj);
			String title = "恭喜你，获取了平台邀请码";
			String content = "云英汇平台在现阶段采用邀请制注册，恭喜您获得了我们平台发放的免费邀请码，你可以邀请你的好友一起入驻云英汇平台，共同创造财富<br>" + "邀请码：" + inviteCode
					+ "<br>" + "使用方法：一个邀请码仅可以邀请一位好友入驻<br>" + "有效期：1个月<br>" + "邀请码制度最终解释权归云英汇凭条所有<br>";
			eventPublishService.publishMessageEvent(ids[i], null, null, title, content, false,
					MemberMessage_Type.system);

		}

	}

	@Override
	public void updatePlatformInviteCode(PlatformInviteCode obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getId());
		this.platformInviteCodeRepository.save(obj);
	}

	@Override
	public PlatformInviteCode findPlatformInviteCodeById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.platformInviteCodeRepository.find(id);
	}

	@Override
	public PlatformInviteCode findPlatformInviteCodeByCode(String code) throws EliteServiceException {
		this.verifyParams(code);
		return this.platformInviteCodeRepository.searchUnique(new Search().addFilterEqual("inviteCode", code));
	}
	
	@Override
	public PlatformInviteCode findNewPlatformInviteCodeByUserId(Long userId)throws EliteServiceException{
		this.verifyParams(userId);
		Search search = new Search();
		search.addFilterEqual("userId", userId);
		search.addSort("inviteCount", false);
		List<PlatformInviteCode> list=this.platformInviteCodeRepository.search(search);
		if(list.isEmpty())return null;
		PlatformInviteCode obj=list.get(0);
		if(InviteCode_Type.member.equals(obj.getType())&&obj.getInviteCount()>0){
			return null;
		}
		return obj;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public PlatformInviteCode findPlatformInviteCodeByUserId(Long userId, InviteCode_Type type)
			throws EliteServiceException {
		this.verifyParams(userId, type);
		Search search = new Search();
		search.addFilterEqual("type", type);
		switch (type) {
		case channel_company:
			search.addFilterEqual("userId", userId);
			break;
		case channel_elite:
			search.addFilterEqual("userId", userId);
			break;
		case member:
			search.addFilterEqual("userId", userId);
			search.addFilterGreaterThan("inviteCount", 0);
			break;
		}
		PlatformInviteCode obj = this.platformInviteCodeRepository.searchUnique(search);
		if (obj == null) {
			PlatformInviteCode pojo = new PlatformInviteCode();
			pojo.setType(type);
			pojo.setUserId(userId);
			return this.createPlatformInviteCode(pojo);
		}
		return obj;
	}

	@Override
	public SearchResult<PlatformInviteCode> findPaltformInviteCodes(String keyword, String type, Pager pager)
			throws EliteServiceException {
		SearchResult<PlatformInviteCode> sr = new SearchResult<>();
		List<PlatformInviteCode> codeList = this.platformInviteCodeRepository.queryPlatformInviteCodetList(keyword,
				type, pager);
		Integer totalCount = this.platformInviteCodeRepository.queryPlatformInviteCodetCount(keyword, type);
		sr.setTotalCount(totalCount);
		sr.setResult(codeList);
		return sr;
	}

	@Override
	public List<PlatformInviteCode> findAllPaltformInviteCodes() throws EliteServiceException {
		// TODO Auto-generated method stub
		return this.platformInviteCodeRepository.findAll();
	}

}
