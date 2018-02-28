package com.ledao.elite.rest.controller.member;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberAccount;
import com.ledao.elite.core.domain.member.MemberBank;
import com.ledao.elite.core.domain.member.MemberBank.MemberBank_Type;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberWithdraw;
import com.ledao.elite.core.domain.platform.PlatformFund;
import com.ledao.elite.core.domain.sys.SmsRecord.Sms_Type;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.cache.custom.BankCardCache;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.dto.site.WithdrawConversion;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberBankService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberWithdrawService;
import com.ledao.elite.core.service.platform.PlatformAuthBankService;
import com.ledao.elite.core.service.platform.PlatformFundService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.sys.SmsRecordService;
import com.ledao.elite.core.utils.BankUtils;
import com.ledao.elite.core.utils.CommonUtils;
import com.ledao.elite.rest.controller.BaseController;
import com.ledao.elite.rest.framework.ResponseBaseRest;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.request.MemberBankRequest;
import com.ledao.elite.rest.framework.request.MemberWithdrawRequest;
import com.ledao.elite.rest.framework.request.SettlementListRequest;
import com.ledao.elite.rest.framework.response.member.RMemberAccount;
import com.ledao.elite.rest.framework.response.member.RMemberBank;
import com.ledao.elite.rest.framework.response.member.RPlatformFund;
import com.ledao.elite.rest.framework.response.member.RPlatformFundInfo;

/**
 * 会员结算中心的接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Controller("memberSettlementController")
@RequestMapping("/member")
public class MemberSettlementController extends BaseController{

	@Resource
	private MemberWithdrawService memberWithdrawService;
	@Resource
	private MemberBankService memberBankService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberCreditService memberCreditService;
	@Resource
	private ProjectService projectService;
	@Resource
	private PlatformFundService platformFundService;
	@Resource
	private PlatformAuthBankService platformAuthBankService;
	@Resource
	private SmsRecordService smsRecordService;
	
	
	/**
	 * 结算中心
	 * */
	/**
	 * @param model
	 * @return
	 */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/settlement",method=RequestMethod.POST)
	public ModelAndView settlement(Model model){
		MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(getMemberId());
		MemberAccount account = this.memberAccountService.findMemberAccountByMemberId(getMemberId());
		BigDecimal amountTax = CommonUtils.calculateAmountTax(account.getBalance());
		account.setAmountTax(amountTax);
		RMemberAccount raccount= new RMemberAccount(account);
		if(credit==null || !credit.getIsCard()){
			raccount.setCard(false);
		}
		ResponseResultData<RMemberAccount> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(raccount);
	}
	
	
	
	/**
	 * 结算中心列表查询
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/settlement/listData",method=RequestMethod.POST)
	public ModelAndView settlementListData(){
		String reqJson=parseRequest();
		SettlementListRequest request=JSON.parseObject(reqJson, SettlementListRequest.class);
		String type=request.getType(),keyword=request.getKeyword();
		Pager pager = new Pager();
		pager.setPageth(request.getPageth());
		SearchResult<PlatformFund> sr=this.platformFundService.findPlatformFundList(getMemberId(), null, type,keyword,pager);
		String key="";
		List<RPlatformFundInfo> infolist = new ArrayList<>();
		List<RPlatformFund> list = new ArrayList<>();
		for(PlatformFund fund:sr.getResult()){
			RPlatformFund fd = new RPlatformFund(fund);
			if("".equals(key)){
				key=fd.getCreateDate();
			}
			if(key.equals(fd.getCreateDate())){
				list.add(fd);
			}else{
				infolist.add(new RPlatformFundInfo(key,list));
				key=fd.getCreateDate();
				list = new ArrayList<RPlatformFund>();
				list.add(fd);
			}
		}
		if(list.size()>0){
			infolist.add(new RPlatformFundInfo(key, list));
		}
		ResponseResultData<List<RPlatformFundInfo>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(infolist);
	}
	
	/**
	 * 获取会员的银行卡列表
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/bank/listData",method=RequestMethod.POST)
	public ModelAndView bankList(){
		List<MemberBank> list=this.memberBankService.findMemberBankByMemberId(getMemberId());
		List<RMemberBank> banklist = new ArrayList<>();
		for(MemberBank bank:list){
			banklist.add(new RMemberBank(bank));
		}
		ResponseResultData<List<RMemberBank>> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(banklist);
	}
	
	/**
	 * 验证银行卡
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/bank/valid",method=RequestMethod.POST)
	public ModelAndView validbank(){
		MemberCredit credit=memberCreditService.findMemberCreditByMemberId(getMemberId());
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		ResponseBaseRest rs=new ResponseBaseRest();
		boolean flag=BankCardCache.valid(getMemberId());
		String reqJson=parseRequest("phone","bankCard","holder");
		MemberBankRequest request = JSON.parseObject(reqJson, MemberBankRequest.class);
		if(!flag){
			rs.setCode(ErrorCodeEnum.BANK_FREQUENT_VALID.code);
		}
		if (!platformAuthBankService.findValidateBankCard(request.getHolder(), credit.getIdCard(), request.getBankCard(), request.getPhone())){
			rs.setCode(ErrorCodeEnum.BANK_NOT_MATCH.code);
		}
		return rsp.responseResult(rs);
	}
	
	
	/**
	 * 验证银行卡号是否规则
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/bankcard/valid",method=RequestMethod.POST)
	public ModelAndView validbankcard(){
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		ResponseBaseRest rs=new ResponseBaseRest();
		String reqJson=parseRequest("bankCard");
		MemberBankRequest request = JSON.parseObject(reqJson, MemberBankRequest.class);
		String bankCode=BankUtils.getBankCode(request.getBankCard());
		if(StringUtils.isEmpty(bankCode)){
			rs.setCode(ErrorCodeEnum.BANK_NOT_SUPPORT.code);
		}
		return rsp.responseResult(rs);
	}
	
	/**
	 * 添加新的银行卡
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/bank/save",method=RequestMethod.POST)
	public ModelAndView bankSave(){
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		ResponseBaseRest rs=new ResponseBaseRest();
		String reqJson=parseRequest("phone","verifyCode","bankCard","holder");
		MemberBankRequest request = JSON.parseObject(reqJson, MemberBankRequest.class);
		boolean flag=this.smsRecordService.findSmsRecordIsExists(request.getPhone(), Sms_Type.valid_bank.name(), request.getVerifyCode(), null);
		if(!flag){
			MemberBank bank = new MemberBank();
			bank.setPhone(request.getPhone());
			bank.setType(MemberBank_Type.bank);
			bank.setMemberId(getMemberId());
			bank.setHolder(request.getHolder());
			bank.setVerifyed(true);
			String bankName=BankUtils.getNameOfBank(request.getBankCard());
			String bankCode=BankUtils.getBankCode(request.getBankCard());
			if(StringUtils.isEmpty(bankCode)){
				rs.setCode(ErrorCodeEnum.BANK_NOT_SUPPORT.code);
				return rsp.responseResult(rs);
			}
			bank.setBankCard(request.getBankCard());
			bank.setBankCode(bankCode);
			bank.setBankName(bankName);
			try {
				this.memberBankService.createMemberBank(bank);
			} catch (EliteServiceException e) {
				rs.setCode(ErrorCodeEnum.APPERROR.code);
				rs.setMsg(e.getMsg());
			}
			return rsp.responseResult(rs);
		}
		rs.setCode(ErrorCodeEnum.VERIFYCODEFAULT.code);
		return rsp.responseResult(rs);
	}
	
	
	/**
	 * 保存提现记录
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/withdraw/save",method=RequestMethod.POST)
	public ModelAndView withdrawSave(){
		ResponseResultData<Object> rsp=new ResponseResultData<>(); 
		ResponseBaseRest rs=new ResponseBaseRest();
		String reqJson=parseRequest("bankId","withdrawAmount","receiptAmount","invoiceWay","password");
		MemberWithdrawRequest request = JSON.parseObject(reqJson, MemberWithdrawRequest.class);
		MemberPassport memberPassport=this.memberPassportService.findMemberPassportByIdValidPassword(getMemberId(), request.getPassword());
		if(memberPassport!=null){
			if(request.getWithdrawAmount()==null||request.getWithdrawAmount().compareTo(new BigDecimal(100))==-1){
				rs.setCode(ErrorCodeEnum.FAILURE.code);
				return rsp.responseResult(rs);
			}
			MemberBank memberBank=this.memberBankService.findMemberBankById(request.getBankId());
			if(memberBank==null){
				rs.setCode(ErrorCodeEnum.BANK_NOT_EXIT.code);
				return rsp.responseResult(rs);
			}
			MemberWithdraw withdraw=new MemberWithdraw();
			withdraw.setBankId(request.getBankId());
			withdraw.setAmount(request.getWithdrawAmount());
			withdraw.setReceiptAmount(request.getReceiptAmount());
			withdraw.setMemberId(getMemberId());
			withdraw.setInvoiceWay(request.getInvoiceWay());
			this.memberWithdrawService.createMemberWithdraw(withdraw);
			return rsp.responseResult(rs);
		}
		rs.setCode(ErrorCodeEnum.TRADE_PASS_FAULT.code);
		return rsp.responseResult(rs);
	}
	
	/**
	 * 提现金额税率换算
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/withdraw/conversion",method=RequestMethod.POST)
	public ModelAndView withdrawAmountConversion(){
		String reqJson=parseRequest("amount");
		MemberWithdrawRequest request = JSON.parseObject(reqJson, MemberWithdrawRequest.class);
		BigDecimal amount=request.getAmount();
		BigDecimal tx=CommonUtils.calculateAmountTax(amount);
		WithdrawConversion wc=new WithdrawConversion();
		wc.setTax(tx);
		wc.setTaxAfter(amount.subtract(tx));
		ResponseResultData<WithdrawConversion> rsp=new ResponseResultData<>(); 
		return rsp.responseResult(wc);
	}
	
}
