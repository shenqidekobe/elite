package com.ledao.elite.core.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberAttention;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.repository.member.MemberAttentionRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberAttentionService;


@Service
public class MemberAttentionServiceImpl extends BaseSerivceImpl implements MemberAttentionService {

	@Resource
	private MemberAttentionRepository memberAttentionRepository;

	@Override
	public List<MemberAttention> findAttenTionUsers(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		Search search = new Search(MemberAttention.class);
		search.addFilter(new Filter("memberId", memberId));
		return this.memberAttentionRepository.search(search);
	}

	@Override
	public List<MemberAttention> findAttenTionedUsers(Long memberId) throws EliteServiceException {

		this.verifyParams(memberId);
		Search search = new Search(MemberAttention.class);
		search.addFilter(new Filter("attentionMemberId", memberId));
		return this.memberAttentionRepository.search(search);
	}

	@Override
	public MemberAttention removePhysicalById(Long memberId, Long attentionMemberId) throws EliteServiceException {
		this.verifyParams(memberId, attentionMemberId);
		Search search = new Search(MemberAttention.class);
		search.addFilter(new Filter("memberId", memberId));
		search.addFilter(new Filter("attentionMemberId", attentionMemberId));
		MemberAttention obj = memberAttentionRepository.searchUnique(search);
		if (obj == null)
			throw new EliteServiceException(ErrorCodeEnum.OBJECT_NOT_EXIST.code);
		return memberAttentionRepository.remove(obj) ? obj : null;
	}
	

	@Override
	public void addPhysicalById(MemberAttention obj) throws EliteServiceException {
		 this.verifyParams(obj.getMemberId(), obj.getAttentionMemberId());
		 Search search = new Search(MemberAttention.class);
		 search.addFilter(new Filter("memberId", obj.getMemberId()));
		 search.addFilter(new Filter("attentionMemberId", obj.getAttentionMemberId()));
		 MemberAttention pojo = memberAttentionRepository.searchUnique(search);
		 if(pojo==null){
			 memberAttentionRepository.save(obj);
		 }
	}

	@Override
	public MemberAttention checkAttentioned(Long memberId, Long attentionMemberId) throws EliteServiceException {
		this.verifyParams(memberId, attentionMemberId);
		Search search = new Search(MemberAttention.class);
		search.addFilter(new Filter("memberId", memberId));
		search.addFilter(new Filter("attentionMemberId", attentionMemberId));
		List<MemberAttention> list = memberAttentionRepository.search(search);
		return  list.isEmpty()?null:list.get(0);
	}
	
	@Override
	public SearchResult<MemberAttention> findAttenTionUser(String type, Long memberId, String keyWord, Pager pager) {
		this.verifyParams(memberId, type);
		
		SearchResult<MemberAttention> sr = new SearchResult<MemberAttention>();
		List<MemberAttention> list = memberAttentionRepository.findAttenTionUsers(type, memberId, keyWord, pager);
		Integer count = memberAttentionRepository.findAttenTionUserCount(type, memberId, keyWord);
		sr.setTotalCount(count);
		sr.setResult(list);
		return sr;
	}

	@Override
	public Integer findAttenTionedUserCount(Long memberId) throws EliteServiceException {
		this.verifyParams(memberId);
		Search search = new Search(MemberAttention.class);
		search.addFilter(new Filter("attentionMemberId", memberId));
		return memberAttentionRepository.count(search);
	}
	
}
