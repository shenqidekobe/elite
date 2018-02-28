package com.ledao.elite.site.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.platform.PlatformInviteCode;
import com.ledao.elite.core.domain.platform.PlatformInviteCode.InviteCode_Type;
import com.ledao.elite.core.domain.sys.SmsRecord;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.config.PingPlusPlusConfig;
import com.ledao.elite.core.framework.plugin.pay.PaymentService;
import com.ledao.elite.core.framework.plugin.pay.pingpp.PingPlusPlusComponent;
import com.ledao.elite.core.framework.plugin.pay.pingpp.WebhooksVerify;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberWithdrawService;
import com.ledao.elite.core.service.platform.PlatformInviteCodeService;
import com.ledao.elite.core.service.sys.SmsRecordService;
import com.ledao.elite.core.utils.QRCodeBuilder;
import com.ledao.elite.core.utils.WebUtils;
import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;

/**
 * Ping++支付测试
 * */
@Controller
@RequestMapping("/pingpp")
public class PingppTestPaymentController extends BaseController{

	@Resource(name="pingPlusPlusService")
	private PaymentService paymentService;
	@Resource
	private PingPlusPlusComponent pingPlusPlusComponent;
	@Resource
	private PingPlusPlusConfig pingPlusPlusConfig;
	@Resource
	private WebhooksVerify webhooksVerify;
	@Resource
	private SmsRecordService smsRecordService;
	@Resource
	private PlatformInviteCodeService platformInviteCodeService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberWithdrawService memberWithdrawService;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		return "ping";
	}
	
	/**
	 * 发起支付
	 * */
	@ResponseBody
	@RequestMapping("/pay")
	public Charge pay(HttpServletRequest request){
		Map<String,Object> payParams=new HashMap<String, Object>();
		payParams.put("orderNum", "D201609210005255");
		payParams.put("amount", 1);
		payParams.put("channel", "wx_pub_qr");//cp_b2b，upacp_pc,wx_pub_qr
		payParams.put("ip", WebUtils.getClientIp(request));
		payParams.put("subject", "自行车");
		payParams.put("body", "这是一个神奇的网站");
		Map<String, String> extra = new HashMap<String, String>();
		//extra.put("result_url","http://test.yunyinghui.com");
		extra.put("product_id",UUID.randomUUID().toString().replaceAll("-", ""));
		payParams.put("extra", extra);
		Map<String,Object> result=paymentService.pay(payParams);
		Charge charge=(Charge) result.get("pingpp");
		logger.info("支付信息charge："+charge.toString());
		return charge;
	}
	
	/**
	 * 发起提现
	 * */
	@ResponseBody
	@RequestMapping("/withdraw")
	public Transfer withdraw(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		Map<String,Object> transferParams=new HashMap<String, Object>();
		transferParams.put("orderNum", "20160911000001");
		transferParams.put("amount", 1000);
		transferParams.put("recipient", null);
		transferParams.put("desc", "会员提现申请");
		
		String cardNum="622263060002111";//银行卡号
		String cardName="毛磊";//银行卡姓名
		String openBankCode="0102";//开户银行编号
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("card_number",cardNum);
		extra.put("user_name",cardName);
		extra.put("open_bank_code",openBankCode);
		transferParams.put("extra", extra);
		Map<String,Object> result=paymentService.transfer(transferParams);
		Transfer transfer=(Transfer) result.get("pingpp");
		logger.info("企业付款信息transfer："+transfer.toString());
		return transfer;
	}
	
	/**
	 * 查询提现
	 * */
	@ResponseBody
	@RequestMapping("/query/withdraw")
	public Transfer queryWithdraw(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Pingpp.apiKey = pingPlusPlusConfig.getLiveSecretKey();
		Transfer transfer=Transfer.retrieve("tr_5mDW58fzrP0S5KOaj5DabjDC");
		logger.info("企业付款信息transfer："+transfer.toString());
		return transfer;
	}
	
	/**
	 * 查询手机号验证码
	 * */
	@ResponseBody
	@RequestMapping("/query/code")
	public String queryCode(String phone,String type)throws Exception{
		SmsRecord obj=smsRecordService.findSmsRecordByTypeAndPhone(phone, type);
		if(obj==null){
			return null;
		}
		return obj.getContent();
	}
	
	/**
	 * 获取一个精英邀请码
	 * */
	@ResponseBody
	@RequestMapping("/query/invite")
	public String queryInvite()throws Exception{
		SearchResult<MemberPassport> mrs=memberPassportService.findMemberPassportList(MemberIdentity_Type.partnerElite.name(), null, null, null, false, null, null, new Pager(1,1));
		List<MemberPassport> list=mrs.getResult();
		Long memberId=list.isEmpty()?null:list.get(0).getId();
		PlatformInviteCode pcd=platformInviteCodeService.findPlatformInviteCodeByUserId(memberId,InviteCode_Type.channel_elite);
		if(pcd==null){
			return null;
		}
		return pcd.getInviteCode();
	}
	
	/**
	 * 验证身份证或银行卡
	 * */
	@ResponseBody
	@RequestMapping("/validate/{type}")
	public String validate(@PathVariable String type,
			String name,String idCard,String bankCode,String phone)throws Exception{
		if("bank".equals(type)){
			this.pingPlusPlusComponent.validateBankCard(name, idCard, bankCode, phone);
		}else{
			this.pingPlusPlusComponent.validateIdCard(name, idCard);
		}
		return "";
	}
	
	/**
	 * 确认提现
	 * */
	@ResponseBody
	@RequestMapping("/affirm/withdraw")
	public String affirmWithdraw(Long id){
		memberWithdrawService.updateMemberWithdrawAsFinanceAffirm(id,null,null, true, null, 2);
		return "";
	}
	
	/**
	 * 微信支付二维码
	 * */
	@RequestMapping("/wx/qr")
	public void wxPayQr(String url,HttpServletResponse rsp){
		QRCodeBuilder.getQRCode(url, 100, rsp);
	}
	
}
