package com.ledao.elite.core.service.platform.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatformWorkRecord;
import com.ledao.elite.core.domain.sys.SysUser;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.platform.PlatformWorkRecordRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.platform.PlatformWorkRecordService;
import com.ledao.elite.core.service.sys.SysUserService;

@Service
public class PlatformWorkRecordServiceImpl extends BaseSerivceImpl implements PlatformWorkRecordService {
	
	
	@Resource
	private PlatformWorkRecordRepository platformWorkRecordRepository;
	
	@Resource
	private SysUserService  sysUserService;

	@Override
	public PlatformWorkRecord createPlatformWorkRecord(PlatformWorkRecord obj) throws EliteServiceException {
		this.verifyParams(obj,obj.getForeignId(),obj.getUserId(),obj.getContent());
		this.platformWorkRecordRepository.save(obj);
		return obj;
	}
	
	@Override
	public PlatformWorkRecord updatePlatformWorkRecord(PlatformWorkRecord record)throws EliteServiceException{
		this.verifyParams(record,record.getId(),record.getContent());
		PlatformWorkRecord obj=this.findPlatformWorkRecordById(record.getId());
		obj.setContent(record.getContent());
		obj.setUserId(record.getUserId());
		this.platformWorkRecordRepository.save(obj);
		return obj;
	}

	@Override
	public PlatformWorkRecord findPlatformWorkRecordById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.platformWorkRecordRepository.find(id);
	}
	
	@Override
	public void removePlatformWorkRecordById(Long id)throws EliteServiceException{
		this.verifyParams(id);
		this.platformWorkRecordRepository.removeById(id);
	}

	@Override
	public SearchResult<PlatformWorkRecord> findPlatformWorkRecordList(String type,Long foreignId,Long userId, Pager pager)
			throws EliteServiceException {
		if(pager==null)
			pager=new Pager();
		Search search=new Search(PlatformWorkRecord.class);
		search.addFilterEqual("type", type);
		if(foreignId!=null)
			search.addFilterEqual("foreignId", foreignId);
		if(userId!=null)
			search.addFilterEqual("userId", userId);
		search.addSort("createTime", true);
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		return this.platformWorkRecordRepository.searchAndCount(search);
	}

	@Override
	public List<PlatformWorkRecord> findPlatFromWorkRecords(String type, Long foreignId) {
		this.verifyParams(type,foreignId);
		Search search=new Search();
		search.addFilterEqual("type", type);
		search.addFilterEqual("foreignId", foreignId);
		search.addSort("createTime", true);
		
		List<PlatformWorkRecord>records= this.platformWorkRecordRepository.search(search);
	   for(int i=0;i<records.size();i++){
		   SysUser user=this.sysUserService.findSysUserById(records.get(i).getUserId());
		   records.get(i).setRealName(user.getUserName());
	   }
	   return records;
	}

}
