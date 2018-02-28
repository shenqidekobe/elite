require([ "jquery", "ajax", "jsonAjax","commons"], function($) {
	
	//面包屑处理
	$("#crumbs_main").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#seles_manage").click(function(){
		sessionStorage.setItem('rsp_intent_isd','settlement');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	//发票选择
	$(".invoice_ul li").click(function(){
		var invoiceWay=$(this).attr("data");
		$(".invoice_ul img").attr("src",ctx+'/res/images/ceo/li_noselect.png');
		$(this).find("img").attr("src",ctx+'/res/images/ceo/li_select.png');
		if("customer"==invoiceWay){
			$("#customerHint").fadeIn(500);
		}else{
			$("#customerHint").fadeOut(500);
		}
		$("#form_invoiceWay").val(invoiceWay);
		processRecepit();
		sessionStorage.invoiceWayIndex=$(this).index();
	});
	var initAmount=$("#withdrawAmount").val();
	if(initAmount!=''&&validateAmount(initAmount)){
		var ii=sessionStorage.invoiceWayIndex;
		if(ii!="undefined"){
			$(".invoice_ul li:eq("+ii+")").click();
		}else{
			conversion(initAmount);
		}
	}else{
		sessionStorage.invoiceWayIndex=undefined;
	}
	//输入金额
	$("#withdrawAmount").bind("keyup blur",function(){
		var amount=$(this).val();
		if(!validateAmount(amount)){
			$(this).val('');
			return false;
		}
	});
	$("#withdrawAmount").keyup(function(){
		processRecepit();
	});
	function processRecepit(){
		var amount=$("#withdrawAmount").val();
		if(!validateAmount(amount)){
			$("#conversion").text('￥0');
			return false;
		}
		conversion(amount);
	}
	function conversion(amount){
		var invoiceWay=$("#form_invoiceWay").val();
		jsonAjax.post({
			url:ctx+'/member/withdraw/conversion',
			data:{amount:amount,invoiceWay:invoiceWay},
			success:function(data){
				var taxAfter=data.object.taxAfter;
				var tax=data.object.tax;
				$("#conversion").text('￥'+taxAfter);
				$("#tax").text(tax);
				
				$("#form_withdrawAmount").val(amount);
			}
		});
	}
	
	//下一步
	$(".nextStep").click(function(){
		var withdrawAmount=$.trim($("#withdrawAmount").val());
		var bankId=$("input[name='bankId']:checked").val();
		var balance=$("#balance").val();
		if(bankId==undefined||bankId==""){
			$(".error_text").text("请选择你要提现的账户");
			$(".error_div").show();
			$("input[name='bankId']").click(function(){
				$(".error_div").hide();
			});
			return false;
		}else if(!validateAmount(withdrawAmount)){
			$(".error_text").text("请输入合法的提现金额");
			$(".error_div").show();
			$("#balance").focus(function(){
				$(".error_div").hide();
			});
			return false;
		}else if(isNaN(withdrawAmount)||withdrawAmount.indexOf(".")!=-1){
			$(".error_text").text("提现金额必须为正整数");
			$(".error_div").show();
			$("#balance").focus(function(){
				$(".error_div").hide();
			});
			return false;
		}else if(parseFloat(withdrawAmount)>parseFloat(balance)){
			$(".error_text").text("提现金额不能大于余额");
			$(".error_div").show();
			$("#balance").focus(function(){
				$(".error_div").hide();
			});
			return false;
		}else if(parseFloat(withdrawAmount)<100){
			$(".error_text").text("最少提现100元");
			$(".error_div").show();
			$("#balance").focus(function(){
				$(".error_div").hide();
			});
			return false;
		}
		$("#form_bankId").val(bankId);
		$("#withdrawAffirmForm").submit();
		//window.location.href=ctx+'/member/withdraw/affirm/'+bankId+'?'+jsonAjax.random();
	});
	loadBankList();
	//获取会员的所有绑定的银行卡
	function loadBankList(){
		jsonAjax.post({
			url:ctx+'/member/bank/list',
			success:function(data){
				var html="";
				for(var i in data){
					var bank=data[i];
					if(bank.type=='alipay'){
						html+='<div class="way-group-b clearfloat">';
						html+='<input type="radio" name="bankId" value="'+bank.id+'" class="alipay-radio">';
						html+='<span class="alipay-icon"></span>';
						html+='<span class="alipay-phone">'+bank.formatBankCard+'</span>';
						html+='<span class="alipay-name">'+bank.formatHolder+'</span>';
						html+='</div>';
					}else{
						var name=bank.bankName;
						if(name.length>8){
							name=name.substring(0,8);
						}
						html+='<div class="way-group-b clearfloat">';
						html+='<input type="radio" name="bankId" value="'+bank.id+'" class="alipay-radio" index="'+i+'">';
						html+='<span class="bank-icon"></span>';
						html+='<span class="alipay-bank">'+name+'</span>';
						html+='<span class="alipay-number">'+bank.formatBankCard+'</span>';
						html+='<span class="alipay-name">'+bank.formatHolder+'</span>';
						html+='</div>';
					}
				}
				$(".way-box-r").html(html);
				$("input[name='bankId']").click(function(){
					$(".way-group-b").removeClass("way-select");
					$(this).parent().addClass("way-select");
					
					var bankIndex = $("input[name='bankId'").index(this);
				    sessionStorage.bankIndex=bankIndex;
				});
				var bi=sessionStorage.bankIndex;
				if(bi!="undefined"){
					$("input[name='bankId']:eq("+bi+")").click();
				}
			}
		});
	}
	
	//添加提现账户
	$(".addAccount").click(function(){
		$("#bankForm")[0].reset();
		$(".account-box").show(0,function(){
			//取消事件
			$(".a-close,.cancel-btn").click(function(){
				$(".account-box").hide();
			});
			//绑定发送短信事件
			$("#sendBank").click(sendPhoneSms);
			//确认事件
			$(".confirm-btn").unbind("click");
			$(".confirm-btn").click(saveBank);
		});
	});
	//验证银行卡信息
	function validBankCode(name,idCard,bankCode,phone){
		var bankParams={
			name:name,
			idCard:idCard,
			bankCode:bankCode,
			phone:phone,
		}
		jsonAjax.post({
			url:ctx+'/common/valid/bank',
			data:bankParams,
			success:function(data){
				if(data.result=="SUCCESS"){
				}else{
					tostHint("提交失败",data.msg);
				}
			}
		});
	}
	//保存银行卡记录
	function saveBank(){
		var type=$("#bankType").val();
		var name,idCard,bankCard,phone,verifyCode;
		if(type=="bank"){
			name=$.trim($("#name").val());
			idCard=$.trim($("#idCard").val());
			bankCard=$.trim($("#bankCard").val());
			if(bankCard==""){
				$("#bankCard").next(".error").show(0,function(){
					var thiss=$(this);
					thiss.text("请输入银行卡号码");
					$("#bankCard").focus(function(){
						thiss.text('');
						thiss.hide();
					});
				});
				return false;
			}else if(!luhmCheck(bankCard)){
				$("#bankCard").next(".error").show(0,function(){
					var thiss=$(this);
					thiss.text("请输入正确的银行卡号码");
					$("#bankCard").focus(function(){
						thiss.text('');
						thiss.hide();
					});
				});
				return false;
			}
			phone=$.trim($("#bankPhone").val());
			if(phone==""){
				$("#bankPhone").next(".error").show(0,function(){
					var thiss=$(this);
					thiss.text("请输入银行预留手机号");
					$("#bankPhone").focus(function(){
						thiss.text('');
						thiss.hide();
					});
				});
				return false;
			}
			verifyCode=$.trim($("#verifyCode").val());
		}
		var bankParams={
			holder:name,
			idCard:idCard,
			bankCard:bankCard,
			phone:phone,
			type:type,
			verifyCode:verifyCode
		}
		jsonAjax.post({
			url:ctx+'/member/bank/save',
			data:bankParams,
			success:function(data){
				if(data.result=="SUCCESS"){
					$(".account-box").hide();
					loadBankList();//重新加载银行卡列表
				}else{
					tostHint("保存失败",data.msg);
				}
			}
		});
	}
	
	// 获取验证码
	function sendPhoneSms(){
		var $this = $(this);
		var type=$("#bankType").val();
		var phone=$.trim($("#bankPhone").val());
		var name=$.trim($("#name").val());
		var idCard=$.trim($("#idCard").val());
		var bankCard=$.trim($("#bankCard").val());
		if(bankCard==""){
			tostHint("获取失败","请输入您的银行卡号");
			return false;
		}else if(!luhmCheck(bankCard)){
			tostHint("获取失败","请输入正确的银行卡号")
			return false;
		}
		if (!validatePhone(phone)) {
			tostHint("获取失败","请输入正确的手机号码");
			return false;
		}
		// 发送验证码
		jsonAjax.post({
			url : ctx+'/common/sms/send',
			data : {
				phone : phone,
				name:name,
				idCard:idCard,
				bankCard:bankCard,
				type : "valid_bank"
			},
			success : function(data) {
				if (data.result == "SUCCESS") {
					sendSmsCode($this);
				}else{
					tostHint("获取失败",data.msg);
				}
			}
		});
	}
	
	// 发送验证码倒计时
	var wait = 60;
	function sendSmsCode(btnElement) {
		if (wait == 0) {
			btnElement.text("获取");
			btnElement.removeAttr("disabled");
			wait = 60;
		} else {
			return timeLoop(btnElement, wait);
		}
	}

	function timeLoop(btnElement, wait) {
		btnElement.attr({
			"disabled" : "disabled"
		});
		timeoutProcess=setTimeout(function() {
			btnElement.text(wait);
			wait--;
			if (wait < 0) {
				btnElement.text("获取");
				btnElement.removeAttr("disabled");
				return;
			}
			timeLoop(btnElement, wait);
		}, 1000);
	}
});