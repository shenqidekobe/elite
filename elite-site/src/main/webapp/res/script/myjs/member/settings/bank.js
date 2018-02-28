require([ "jquery", "ajax","md5","jsonAjax","commons"], function($) {
	
	var timeoutProcess=null;
	$("#crumbs_main").click(function(){
		sessionStorage.setItem('rsp_intent_isd','tasks');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	
	 //点击切换
    $("#navUl li").click(function(){
        var data=$(this).attr("data");
        var text=$(this).children(".liText").text();
        $("#selectText").text(text);
        $("#navUl li").children(".liText").removeClass("active_color");
        $(this).children(".liText").addClass("active_color");
        if (data=="account"){
            $(this).children(".liIcon").removeClass("account_noselect").addClass("account_select");
            $("#cardLi").children(".liIcon").removeClass("card_select").addClass("card_noselect");
            $("#activeLine").animate({"top":"20px"},500);
            $("#passWord").show();
            $("#bankCardBox").hide();
            $("#passError").hide();
        }else if (data=="card"){
            $(this).children(".liIcon").removeClass("card_noselect").addClass("card_select");
            $("#accountLi").children(".liIcon").removeClass("account_select").addClass("account_noselect");
            $("#activeLine").animate({"top":"66px"},500);
            $("#passWord").hide();
            $("#bankCardBox").show();
            
            loadBankList();//重新加载银行卡列表
        }
    });
    
    //修改密码
    $("#editPass").click(function(){
		var oldPass=$.trim($("#oldPass").val());
		var newPass=$.trim($("#newPass").val());
		var newPass1=$.trim($("#newPass1").val());
		if(oldPass == "") {
			$("#passError").show();
			$("#passError .error_text").text("请输入原始密码");
			$("#oldPass").focus(function(){$("#passError").hide();});
			return false;
		}else if(newPass == "") {
			$("#passError").show();
			$("#passError .error_text").text("请输入新的密码");
			$("#newPass").focus(function(){$("#passError").hide();});
			return false;
		}else if(newPass.length<6){
			$("#passError").show();
			$("#passError .error_text").text("密码长度不能小于六位");
			$("#newPass").focus(function(){$("#passError").hide();});
			return false;
		}else if (newPass1 == "") {
			$("#passError").show();
			$("#passError .error_text").text("请输入确认密码");
			$("#newPass1").focus(function(){$("#passError").hide();});
			return false;
		}else if(newPass!=newPass1){
			$("#passError").show();
			$("#passError .error_text").text("确认密码不一致");
			$("#newPass1").focus(function(){$("#passError").hide();});
			return false;
		}
		oldPass = $.md5(oldPass);
		newPass = $.md5(newPass);
		jsonAjax.post({
			url:ctx+'/member/personage/setting/save',
			data:{oldPass:oldPass,newPass:newPass},
			success:function(data){
				if(data.result=="SUCCESS"){
					$("#passError").hide();
					$("#passForm")[0].reset();
					tostHint("提示信息","密码修改成功");
				}else{
					$("#passError").show();
					$("#passError .error_text").text(data.msg);
					$("#oldPass").focus(function(){$("#passError").hide();});
				}
			}
		});
    });

    //添加银行卡
    $("#addCardBtn").click(function(){
    	var isCard=$("#isCard").val(); 
		if(isCard==""||isCard=="false"){
			tostHint("温馨提示","身份证未认证通过，不能绑卡");
			return false;
		}
        $("#cardWindow").show();
        
        $("#bankError").hide();
        $("#cardForm")[0].reset();
        clearTimeout(timeoutProcess);
        $("#sendBank").text("获取");
        $("#sendBank").removeAttr("disabled");
        $("#sendBank").unbind('click');
       //绑定发送短信事件
		$("#sendBank").click(sendPhoneSms);
       //关闭银行卡弹窗
        $("#closeBtnCard").click(function(){
            $("#cardWindow").hide();
        });
        $("#saveBank").click(function(){
        	saveBank();
        });
    });
    
	//保存银行卡记录
	function saveBank(){
		var name,idCard,bankCard,phone,verifyCode;
		name=$.trim($("#name").val());
		idCard=$.trim($("#idCard").val());
		bankCard=$.trim($("#bankCard").val());
		phone=$.trim($("#bankPhone").val());
		if(bankCard==""){
			$("#bankError").show();
			$("#bankError .error_text").text("请输入银行卡号码");
			$("#bankCard").focus(function(){$("#bankError").hide();});
			return false;
		}else if(!luhmCheck(bankCard)){
			$("#bankError").show();
			$("#bankError .error_text").text("请输入正确的银行卡号码");
			$("#bankCard").focus(function(){$("#bankError").hide();});
			return false;
		}else if(phone==""){
			$("#bankError").show();
			$("#bankError .error_text").text("请输入银行预留手机号");
			$("#bankPhone").focus(function(){$("#bankError").hide();});
			return false;
		}else if (!validatePhone(phone)) {
			$("#bankError").show();
			$("#bankError .error_text").text("请输入正确的手机号");
			$("#bankPhone").focus(function(){$("#bankError").hide();});
			return false;
		}
		verifyCode=$.trim($("#verifyCode").val());
		var bankParams={
			holder:name,
			idCard:idCard,
			bankCard:bankCard,
			phone:phone,
			type:"bank",
			verifyCode:verifyCode
		}
		jsonAjax.post({
			url:ctx+'/member/bank/save',
			data:bankParams,
			success:function(data){
				if(data.result=="SUCCESS"){
					$("#cardWindow").hide();
					loadBankList();//重新加载银行卡列表
				}else{
					$("#bankError").show();
					$("#bankError .error_text").text("保存失败:"+data.msg);
					$("#bankCard").focus(function(){$("#bankError").hide();});
				}
			}
		});
	}
	
	// 获取验证码
	function sendPhoneSms(){
		var $this = $(this);
		var phone=$.trim($("#bankPhone").val());
		var name=$.trim($("#name").val());
		var idCard=$.trim($("#idCard").val());
		var bankCard=$.trim($("#bankCard").val());
		if(bankCard==""){
			$("#bankError").show();
			$("#bankError .error_text").text("请输入银行卡号码");
			$("#bankCard").focus(function(){$("#bankError").hide();});
			return false;
		}else if(!luhmCheck(bankCard)){
			$("#bankError").show();
			$("#bankError .error_text").text("请输入正确的银行卡号码");
			$("#bankCard").focus(function(){$("#bankError").hide();});
			return false;
		}else if(phone==""){
			$("#bankError").show();
			$("#bankError .error_text").text("请输入银行预留手机号");
			$("#bankPhone").focus(function(){$("#bankError").hide();});
			return false;
		}else if (!validatePhone(phone)) {
			$("#bankError").show();
			$("#bankError .error_text").text("请输入正确的手机号");
			$("#bankPhone").focus(function(){$("#bankError").hide();});
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
					$("#bankError").show();
					$("#bankError .error_text").text("获取失败:"+data.msg);
					$("#bankPhone").focus(function(){$("#bankError").hide();});
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


    //删除银行卡
    function removeBank(){
    	var id=$(this).attr("data");
    	tostConfirm("确定要删除此银行卡吗？",function(){
    		jsonAjax.post({
    			url:ctx+'/member/bank/remove',
    			data:{id:id},
    			success:function(data){
    				if(data.result=="SUCCESS"){
    					loadBankList();//重新加载银行卡列表
    				}else{
    					tostHint("删除失败",data.msg);
    				}
    			}
        	});
    	})
    };
    
	//获取会员的所有绑定的银行卡
	function loadBankList(){
		jsonAjax.post({
			url:ctx+'/member/bank/list',
			success:function(data){
				var html="";
				for(var i in data){
					var bank=data[i];
					var tailNum=bank.tailBankCard;
					var bankLogo=ctx+'/res/images/bankLogo/'+bank.bankCardLogo+'.png';
					html+='<div class="cardGroup">';
					html+='<img src="'+bankLogo+'" alt="银行标志" class="bankLogo">';
					html+='<span class="cardNumber">尾号&nbsp;'+tailNum+'</span>';
					html+='<span class="deleteBtn" data="'+bank.id+'">删除</span>';
					html+='</div>';
				}
				$("#bankList").html(html);
				$(".cardGroup .deleteBtn").click(removeBank);
			}
		});
	}

});