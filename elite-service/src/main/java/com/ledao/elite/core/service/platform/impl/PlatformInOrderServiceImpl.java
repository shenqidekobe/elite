package com.ledao.elite.core.service.platform.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Status;
import com.ledao.elite.core.domain.platform.PlatformFund.PlatformFund_Type;
import com.ledao.elite.core.domain.platform.PlatformInOrder;
import com.ledao.elite.core.domain.platform.PlatformInOrder.PlatformInOrder_Status;
import com.ledao.elite.core.domain.platform.PlatformInOrder.PlatformInOrder_Type;
import com.ledao.elite.core.domain.project.ProjectDefineStage;
import com.ledao.elite.core.domain.sys.CommonCode.COMMON_PREVAL;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Data_Oper;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant.Pay_Channel;
import com.ledao.elite.core.repository.platform.PlatformInOrderRepository;
import com.ledao.elite.core.service.BaseSerivceImpl;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.platform.PlatformFundService;
import com.ledao.elite.core.service.platform.PlatformInOrderService;
import com.ledao.elite.core.service.project.ProjectDefineStageService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.sys.CommonCodeService;
import com.ledao.elite.core.utils.CommonUtils;

@Service
public class PlatformInOrderServiceImpl extends BaseSerivceImpl implements PlatformInOrderService {

	public static final Integer ORDER_VALID_TIME = 1;// 订单过期时间(小时)
	@Resource
	private PlatformInOrderRepository platformInOrderRepository;
	@Resource
	private PlatformFundService platformFundService;
	@Resource
	private ProjectService projectService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private CommonCodeService commonCodeService;
	@Resource
	private ProjectDefineStageService projectDefineStageService;
	@Resource
	private MemberCreditService memberCreditService;

	@Override
	public String createPlatformInOrder(PlatformInOrder obj) throws EliteServiceException {
		this.verifyParams(obj, obj.getOrderAmount(), obj.getMemberId(), obj.getType());
		if (obj.getProjectId() != null) {
			// 如果订单号已经存在，则获取旧的订单号(验证当前订单的时间是否在规定时间内)
			PlatformInOrder pojo = this.platformInOrderRepository.queryPlatformInOrder(obj.getProjectId(),
					obj.getMemberId(), obj.getType(), PlatformInOrder_Status.wait_pay);
			if (pojo != null) {
				boolean flag = CommonUtils.validTimeIsExpire(pojo.getCreateTime(), ORDER_VALID_TIME * 60);
				if (!flag) {
					String orderId = pojo.getOrderId();
					logger.info("当前订单已在数据库中找到orderId:" + orderId);
					// 线上支付
					if (!PlatformInOrder_Status.success.equals(obj.getStatus())) {
						return orderId;
					}
				} else {
					// 过期，更新订单状态,开始获取新的订单
					pojo.setStatus(PlatformInOrder_Status.expire);
					this.platformInOrderRepository.save(pojo);
				}
			}
		}
		String orderId = commonCodeService.disposeOddNumber("inOrderNum", COMMON_PREVAL.DT.name(), "yyyyMMdd", 8, null);
		obj.setOrderId(orderId);
		// 添加判断条件，线下支付 生成订单 状态为 success
		PlatformFund_Status fundStatus = PlatformFund_Status.success;
		if (!PlatformInOrder_Status.success.equals(obj.getStatus())) {
			obj.setStatus(PlatformInOrder_Status.wait_pay);
			fundStatus = PlatformFund_Status.wait_pay;
		}
		String title = "";
		switch (obj.getType()) {
		case intention:
			title = "提交意向金";
			break;
		case prostage:
			ProjectDefineStage pds = this.projectDefineStageService.findProjectDefineStageById(obj.getStageId());
			title = "托管阶段费用";
			if (pds != null) {
				title = "托管" + pds.getTitle() + "费用";
			}
			break;
		}
		this.platformInOrderRepository.save(obj);

		this.platformFundService.createPlatformFund(title, orderId, obj.getProjectId(), obj.getStageId(),
				obj.getMemberId(), obj.getOrderAmount(), orderTypeToFundType(obj.getType()), fundStatus);
		return orderId;
	}

	@Override
	public void updatePlatformInOrderAsCallback(String orderNum, String totalFee, String tradeNum, String buyerEmail,
			Date payTime, boolean isSuccess) throws EliteServiceException {
		PlatformInOrder order = this.findPlatformInOrderByOrderId(orderNum);
		//错误订单和成功订单不做后续处理
		if (order == null||PlatformInOrder_Status.success.equals(order.getStatus()))
			return;
		if (isSuccess) {
			BigDecimal receiptAmount = new BigDecimal(totalFee);
			order.setStatus(PlatformInOrder.PlatformInOrder_Status.success);
			order.setReceiptAmount(receiptAmount);
			order.setThirdSerial(tradeNum);
			order.setPayTime(payTime);
			order.setPayAccount(buyerEmail);
			this.platformInOrderRepository.save(order);
			// TODO:根据订单类型通知到具体的服务开始更新相应数据
			Long projectId = order.getProjectId();
			switch (order.getType()) {
			case intention:
				this.projectService.updateProjectAsIntention(projectId, receiptAmount);
				break;
			case prostage:
				// 更新项目信息
				this.projectService.updateProjectAsTrust(projectId, order.getStageId(), receiptAmount);
				break;
			}
			// 更新会员的托管费用总额
			this.memberAccountService.updateMemberAccountTrustAmount(order.getMemberId(), receiptAmount, Data_Oper.sum);
		} else {
			order.setStatus(PlatformInOrder.PlatformInOrder_Status.failure);
			this.platformInOrderRepository.save(order);
		}
		// 更新流水
		this.platformFundService.updatePlatformFund(orderNum,
				isSuccess ? PlatformFund_Status.success : PlatformFund_Status.failure);
	}

	@Override
	public void updatePlatformInOrderStatus(Long id, PlatformInOrder_Status status) throws EliteServiceException {
		this.verifyParams(id, status);
		PlatformInOrder obj = this.platformInOrderRepository.find(id);
		if (obj == null)
			return;
		obj.setStatus(status);
		this.platformInOrderRepository.save(obj);
	}

	@Override
	public PlatformInOrder findPlatformInOrderById(Long id) throws EliteServiceException {
		this.verifyParams(id);
		return this.platformInOrderRepository.find(id);
	}

	@Override
	public PlatformInOrder findPlatformInOrderByOrderId(String orderId) throws EliteServiceException {
		this.verifyParams(orderId);
		return this.platformInOrderRepository.searchUnique(new Search().addFilterEqual("orderId", orderId));
	}

	private PlatformFund_Type orderTypeToFundType(PlatformInOrder_Type orderType) {
		PlatformFund_Type fundType = null;
		switch (orderType) {
		case intention:
			fundType = PlatformFund_Type.intention;
			break;
		case prostage:
			fundType = PlatformFund_Type.prostage;
			break;
		}
		return fundType;
	}

	@Override
	public SearchResult<PlatformInOrder> findPlatformInOrdersByKey(String keyword,String status,String type,String payType, Pager pager)
			throws EliteServiceException {
		SearchResult<PlatformInOrder> sr = new SearchResult<>();
		List<PlatformInOrder> memberList = this.platformInOrderRepository.queryPlatformInOrdersByKeyword(keyword,
				status,type,payType,pager);
		for(int i=0;i<memberList.size();i++){
			if(memberList.get(i).getMemberId()!=null){
				MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(memberList.get(i).getMemberId());
				if(credit!=null){
					memberList.get(i).setMemberName(credit.getRealName());
				}
			}
		}
		Integer totalCount = this.platformInOrderRepository.queryPlatformInOrdersCountByKeyword(keyword,
				status,type,payType);
		sr.setTotalCount(totalCount);
		sr.setResult(memberList);
		return sr;
	}

	@Override
	public List<PlatformInOrder> findPlatformInOrderByStageId(Long stageId, PlatformInOrder_Status status)
			throws EliteServiceException {
		this.verifyParams(stageId);
		Search search = new Search();
		search.addFilterEqual("stageId", stageId);
		search.addFilterEqual("status", status);
		return this.platformInOrderRepository.search(search);
	}

	@Override
	public List<PlatformInOrder> findPlatformInOrderExpire(Integer expireMinute) throws EliteServiceException {
		this.verifyParams(expireMinute);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -expireMinute);
		Date time = cal.getTime();
		Search search = new Search();
		search.addFilterLessOrEqual("createTime", time);// 创建时间小于(当前时间-过期时间)
		search.addFilterEqual("status", PlatformInOrder_Status.wait_pay);
		return this.platformInOrderRepository.search(search);
	}

	@Override
	public String createPlatformInOrderOffline(PlatformInOrder order, ProjectDefineStage stage)
			throws EliteServiceException {
		this.verifyParams(stage, stage.getId());
		ProjectDefineStage obj = this.projectDefineStageService.findProjectDefineStageById(stage.getId());
		if (obj == null)
			throw new EliteServiceException("找不到对应的项目阶段", ErrorCodeEnum.OBJECT_NOT_EXIST.code);

		// 创建线下订单
		order.setStatus(PlatformInOrder_Status.success);
		order.setProjectId(obj.getProjectId());
		order.setStageId(stage.getId());
		order.setPayType(Pay_Channel.offline);
		// 更新阶段状态 付款金额
		this.projectService.updateProjectAsTrust(obj.getProjectId(), stage.getId(),order.getReceiptAmount());
		// 更新会员的托管费用总额
		this.memberAccountService.updateMemberAccountTrustAmount(order.getMemberId(),order.getReceiptAmount(), Data_Oper.sum);
		return this.createPlatformInOrder(order);
	}
}
