package com.ledao.elite.core.service.platform;

import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.platform.PlatforMarketing;
import com.ledao.elite.core.domain.platform.PlatforMarketing.Marketing_Platform;
import com.ledao.elite.core.domain.platform.PlatforMarketing.Marketing_Type;
import com.ledao.elite.core.domain.platform.PlatforMarketing.Role_Channel;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 平台营销服务接口
 */
public interface PlatforMarketingService {
	
	/**
	 * 删除
	 */
	PlatforMarketing createPlatForMarketing(PlatforMarketing obj) throws EliteServiceException;
	/**
	 * 分页查询营销数据列表
	 */
	SearchResult<PlatforMarketing> findPlatforMarketings(String keyword, String usePlatform, String type, Pager pager)
			throws EliteServiceException;

	/**
	 * 查询营销数据列表
	 */
	List<PlatforMarketing> findPlatforMarketingList(Marketing_Platform use, Marketing_Type type,Role_Channel channel)
			throws EliteServiceException;
	
	/**
	 * 查询详情
	 */
	PlatforMarketing findPlatforMarketingById(Long id)throws EliteServiceException;

	/**
	 * 上下移动
	 */
	PlatforMarketing updatePlatforMarketing(Long id, String moveType) throws EliteServiceException;
	/**
	 * 更新信息
	 */
	PlatforMarketing updatePlatforMarketingInfo(PlatforMarketing obj) throws EliteServiceException;

	/**
	 * 删除
	 */
	PlatforMarketing removePhysicalById(Long Id) throws EliteServiceException;

}
