package com.ledao.elite.site.controller.member;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberAccount;
import com.ledao.elite.core.domain.member.MemberBank;
import com.ledao.elite.core.domain.member.MemberCredit;
import com.ledao.elite.core.domain.member.MemberIdentity.MemberIdentity_Type;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.domain.member.MemberWithdraw;
import com.ledao.elite.core.domain.platform.PlatformFund;
import com.ledao.elite.core.domain.sys.SmsRecord.Sms_Type;
import com.ledao.elite.core.framework.annotation.MemberHandleLog;
import com.ledao.elite.core.framework.annotation.SSOToken;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.framework.constant.LogsEnum;
import com.ledao.elite.core.framework.dto.site.WithdrawConversion;
import com.ledao.elite.core.service.member.MemberAccountService;
import com.ledao.elite.core.service.member.MemberBankService;
import com.ledao.elite.core.service.member.MemberCreditService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.core.service.member.MemberWithdrawService;
import com.ledao.elite.core.service.platform.PlatformFundService;
import com.ledao.elite.core.service.project.ProjectService;
import com.ledao.elite.core.service.sys.SmsRecordService;
import com.ledao.elite.core.utils.BankUtils;
import com.ledao.elite.core.utils.CommonUtils;
import com.ledao.elite.site.controller.BaseController;
import com.ledao.elite.site.dto.ResponseResult;

/**
 * 会员结算中心控制器
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
	private SmsRecordService smsRecordService;
	
	
	/**
	 * 结算中心
	 * */
	@SSOToken
	@RequestMapping(value="/settlement",method=RequestMethod.POST)
	public String settlement(Model model){
		String currentType=getMemberCurrentType();
		MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(getMemberId());
		MemberAccount account = this.memberAccountService.findMemberAccountByMemberId(getMemberId());
		BigDecimal amountTax = CommonUtils.calculateAmountTax(account.getBalance());
		model.addAttribute("account", account);
		model.addAttribute("amountTax", amountTax);
		model.addAttribute("projectList",this.projectService.findProjectListByCompanyId(getMemberId()));
		model.addAttribute("credit", credit);
		if(MemberIdentity_Type.cto.name().equals(currentType)){
			currentType=MemberIdentity_Type.elite.name();
		}
		return "/member/"+currentType+"/main/settlement_list";
	}
	
	/**
	 * 结算中心列表查询
	 * */
	@SSOToken
	@RequestMapping(value="/settlement/listData",method=RequestMethod.POST)
	public String settlementListData(Long projectId,String type,Pager pager,Model model){
		String currentType=getMemberCurrentType();
		SearchResult<PlatformFund> sr=this.platformFundService.findPlatformFundList(getMemberId(), projectId, type, null,pager);
		model.addAttribute("list", sr.getResult());
		pager.calPageCount((long) sr.getTotalCount());
		model.addAttribute("pagination",pager);
		if(MemberIdentity_Type.cto.name().equals(currentType)){
			currentType=MemberIdentity_Type.elite.name();
		}
		return "/member/"+currentType+"/main/settlement_list_frag";
	}
	
	/**
	 * 进入提现页面
	 * */
	@SSOToken
	@RequestMapping(value="/withdraw",method=RequestMethod.GET)
	public String withdraw(Model model){
		MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(getMemberId());
		if(credit==null||!credit.getIsCard()){
			//身份证未认证通过，不能提现
			return REDIRECT_COMMAND+"/member/index";
		}
		BigDecimal  balance=this.memberAccountService.findMemberAccountByMemberId(getMemberId()).getBalance();
		if(balance.compareTo(new BigDecimal(100))==-1){
			return REDIRECT_COMMAND+"/member/index";
		}
		model.addAttribute("balance", balance);
		model.addAttribute("credit", credit);
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 7);
		model.addAttribute("date", cal.getTime());
		return "/member/withdraw/withdraw";
	}
	
	/**
	 * 提现金额税率换算
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/withdraw/conversion")
	public ResponseResult<WithdrawConversion> withdrawAmountConversion(BigDecimal amount,String invoiceWay){
		BigDecimal tx=new BigDecimal(0);;
    	//小于1000时需要手续费2块
		if(amount.compareTo(new BigDecimal(1000))==-1){
			tx=new BigDecimal(2);//手续费2块
		}
		ResponseResult<WithdrawConversion> rsp=new ResponseResult<>();
		WithdrawConversion wc=new WithdrawConversion();
		wc.setTax(tx);
		wc.setTaxAfter(amount.subtract(tx));
		rsp.setObject(wc);
		return rsp;
	}
	
	/**
	 * 进入提现确认页面
	 * */
	@SSOToken
	@RequestMapping(value="/withdraw/affirm",method=RequestMethod.POST)
	public String withdrawAffirm(Long bankId,BigDecimal withdrawAmount,String invoiceWay,Model model){
		MemberBank memberBank=this.memberBankService.findMemberBankById(bankId);
		//重新计算手续费
		BigDecimal tx=new BigDecimal(0);;
    	//小于1000时需要手续费2块
		if(withdrawAmount.compareTo(new BigDecimal(1000))==-1){
			tx=new BigDecimal(2);//手续费2块
		}
		model.addAttribute("obj", memberBank);
		model.addAttribute("withdrawAmount", withdrawAmount);
		model.addAttribute("receiptAmount", withdrawAmount.subtract(tx));
		model.addAttribute("invoiceWay", invoiceWay);
		model.addAttribute("tax", tx);
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 7);
		model.addAttribute("date", cal.getTime());
		return "/member/withdraw/withdraw_affirm";
	}
	
	/**
	 * 保存提现记录
	 * */
	@SSOToken
	@MemberHandleLog(description="会员申请提现",type=LogsEnum.create)
	@ResponseBody
	@RequestMapping(value="/withdraw/save",method=RequestMethod.POST)
	public ResponseResult<String> withdrawSave(Long bankId,BigDecimal withdrawAmount,BigDecimal receiptAmount,String invoiceWay,String password){
		MemberPassport memberPassport=this.memberPassportService.findMemberPassportByIdValidPassword(getMemberId(), password);
		if(memberPassport!=null){
			if(withdrawAmount==null||withdrawAmount.compareTo(new BigDecimal(100))==-1){
				return  new ResponseResult<>(GlobalDefinedConstant.FAILURE,"提现金额最低为100元");
			}
			MemberBank memberBank=this.memberBankService.findMemberBankById(bankId);
			if(memberBank==null){
				return  new ResponseResult<>(GlobalDefinedConstant.FAILURE,"该银行卡号不存在");
			}
			MemberWithdraw withdraw=new MemberWithdraw();
			withdraw.setBankId(bankId);
			withdraw.setAmount(withdrawAmount);
			withdraw.setReceiptAmount(receiptAmount);
			withdraw.setMemberId(getMemberId());
			withdraw.setInvoiceWay(invoiceWay);
			withdraw.setTaxAmount(withdrawAmount.subtract(receiptAmount));
			this.memberWithdrawService.createMemberWithdraw(withdraw);
		}
		return new ResponseResult<>();
	}
	
	/**
	 * 添加新的银行卡
	 * */
	@SSOToken
	@MemberHandleLog(description="会员绑定银行卡",type=LogsEnum.create)
	@ResponseBody
	@RequestMapping(value="/bank/save",method=RequestMethod.POST)
	public ResponseResult<String> bankSave(MemberBank bank,String verifyCode){
		boolean flag=this.smsRecordService.findSmsRecordIsExists(bank.getPhone(), Sms_Type.valid_bank.name(), verifyCode, null);
		if(!flag){
			bank.setMemberId(getMemberId());
			bank.setVerifyed(true);
			String bankName=BankUtils.getNameOfBank(bank.getBankCard());
			String bankCode=BankUtils.getBankCode(bank.getBankCard());
			if(StringUtils.isEmpty(bankCode)){
				return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"此卡系统不支持，绑定失败");
			}
			bank.setBankCode(bankCode);
			bank.setBankName(bankName);
			this.memberBankService.createMemberBank(bank);
			return new ResponseResult<>();
		}
		return new ResponseResult<>(GlobalDefinedConstant.FAILURE,"验证码错误");
	}
	
	/**
	 *删除我的银行卡
	 * */
	@SSOToken
	@MemberHandleLog(description="会员绑定银行卡",type=LogsEnum.create)
	@ResponseBody
	@RequestMapping(value="/bank/remove",method=RequestMethod.POST)
	public ResponseResult<String> bankRemove(Long id){
		this.memberBankService.removeMemberBank(id, getMemberId());
		return new ResponseResult<>();
	}
	
	/**
	 * 获取会员的银行卡列表
	 * */
	@SSOToken
	@ResponseBody
	@RequestMapping(value="/bank/list",method=RequestMethod.POST)
	public List<MemberBank> bankList(){
		return this.memberBankService.findMemberBankByMemberId(getMemberId());
	}
	
	/**
	 * 绑定银行卡页面
	 * */
	@SSOToken
	@RequestMapping(value="/bank/settings",method=RequestMethod.GET)
	public String bankSettings(Model model){
		MemberCredit credit=this.memberCreditService.findMemberCreditByMemberId(getMemberId());
		model.addAttribute("credit", credit);
		return "/member/settings/bank";
	}

}
