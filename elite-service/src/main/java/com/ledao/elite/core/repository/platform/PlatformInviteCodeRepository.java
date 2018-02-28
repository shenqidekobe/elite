package com.ledao.elite.core.repository.platform;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 平台邀请码信息接口 包含(会员邀请码、项目方邀请码、合作伙伴渠道邀请码)
 * 
 * @author kobe.liu
 */
public interface PlatformInviteCodeRepository extends GenericDAO<PlatformInviteCode, Long> {

	/**
	 * 组合条件查询
	 * @param keyword(验证码，用户昵称）
	 * @param type
	 * @param pager
	 * @return
	 */
	List<PlatformInviteCode> queryPlatformInviteCodetList(String keyword, String type, Pager pager);

	/**
	 * 组合条件查询数量
	 * @param keyword(验证码，用户昵称)
	 * @param type
	 * @return
	 */
	Integer queryPlatformInviteCodetCount(String keyword, String type);

}
