package com.ledao.elite.core.service.member;

import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;

/**
 * 会员基本信息
 */
public interface MemberDetailInfoService {

	/**
	 * 更新会员信息
	 */
	MemberDetailInfo updateMemberDetailInfo(MemberDetailInfo obj,Long memberId) throws EliteServiceException;
}
