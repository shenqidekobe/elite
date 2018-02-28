package com.ledao.elite.core.service.member;

import java.util.List;

import com.ledao.elite.core.domain.member.MemberInvoice;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 会员索要的发票记录 服务接口
 * 
 * @author zhiyu.cao
 * @version 1.0
 */
public interface MemberInvoiceService {

	/**
	 * 开发票
	 */

	void createMemberInvoice(MemberInvoice obj) throws EliteServiceException;

	/**
	 * 根据projectId
	 * 
	 * @param projectId
	 * @return
	 * @throws EliteServiceException
	 */
	List<MemberInvoice> findMemberInvoiceListByProjectId(Long projectId) throws EliteServiceException;

	/**
	 * 根据stageId
	 * 
	 * @param stageId
	 * @return
	 * @throws EliteServiceException
	 */
	MemberInvoice findMemberInvoiceByStageId(Long stageId) throws EliteServiceException;
}
