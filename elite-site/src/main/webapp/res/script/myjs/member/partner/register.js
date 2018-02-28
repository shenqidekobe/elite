require([ "jquery", "ajax", "jsonAjax", "md5", "city", "commons" ], function($) {

	//url是否有邀请码
	var inviteCode=getUrlParams("ic");
	$("#inviteCodeId").val(inviteCode);
	
	//url判断类别
	var channelType=getUrlParams("ts");
	if(channelType==null||channelType==""){
		$("#selectChannelValId").val("");
	}else{
		
		if(channelType=="partnerCompany"){
			$("#selectChannelId li:first").attr("class","active_select").siblings().attr("class","default_select")
			$("#selectChannelValId").val(channelType);
		}
		else if(channelType=="partnerElite") {
			$("#selectChannelId li:last").attr("class","active_select").siblings().attr("class","default_select")
			$("#selectChannelValId").val(channelType);
		}else{
			$("#selectChannelValId").val("");
		}
	}
	
	//channelType选择框
	$("#selectChannelId li").click(function(){
		$(this).attr("class","active_select").siblings().attr("class","default_select");
		$("#selectChannelValId").val($(this).attr("data"));
	})
	
	// 手机号码数字限制
/*	$("#phone").bind("keyup blur", function() {
		var phone = $(this).val();
		$(this).val(phone.replace(/\D/g, ''));
	});*/
	// 注册账户
	$("#registerBtn").click(function() {
	    $(".error-tip").text("");
	    var channelType=$("#selectChannelValId").val();
	    if(channelType==null||channelType==""){
	    	 $(".error-tip").text("请选择渠道类型");	
	    	 return;
	    }
		if (!$('#inlineCheckbox').is(':checked')) {
			$(".error-tip").text("请先阅读并同意《云英汇用户协议》");
			return;
		}
		var account = $("#phone").val();
		 if (account == "") {
			$(".error-tip").text("请输入手机号");
			return;
		}else if (!validatePhone(account)) {
			$(".error-tip").text("手机号无效");
			return;
		}
		var passWord = $("#password").val().trim();
		if (passWord == "") {
			$(".error-tip").text("请输入密码");
			return;
		} else if (passWord.length < 6) {
			$(".error-tip").text("密码不少于6位");
			return;
		} else {
			$("#passwordHiddenId").val($.md5($.trim(passWord)));
		}

		var verifycode = $("#verifycode").val().trim();
		if (verifycode == "") {
			$(".error-tip").text("请输入手机验证码");
			return;
		}

		jsonAjax.post({
			url : ctx + '/partner/'+channelType+'/register',
			data : $("#project-form1").serialize(),
			success : function(data) {
				if (data.result == "SUCCESS") {
					window.location.href = ctx + "/partner/"+channelType+"/basic/register/view";
				} else {
					$(".error-tip").text(data.msg);
				}
			}
		});
	});
	// 获取验证码
	$("#send-msg-btn").click(function() {
		$(".error-tip").text("");
		var $this = $(this);
		var account = $("#phone").val();
		if (account == "") {
			$("#account").addClass("error-sign");
			$(".error-tip").text("请输入手机号");
			return;
		}else if (!validatePhone(account)) {
			$(".error-tip").text("手机号无效");
			return;
		}
		// 发送验证码
		jsonAjax.post({
			url : ctx + '/common/sms/send',
			data : {
				phone : account,
				type : "register"
			},
			success : function(data) {
				if (data.result == "SUCCESS") {
					sendSmsCode($this);
				} else {
					$(".error-tip").text(data.msg);
				}
			}
		});

	});
	// 发送验证码倒计时
	var wait = 60;
	function sendSmsCode(btnElement) {
		if (wait == 0) {
			btnElement.val("获取验证码");
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
		timeoutProcess = setTimeout(function() {
			btnElement.val("重新发送(" + wait + ")");
			wait--;
			if (wait < 0) {
				btnElement.val("获取验证码");
				btnElement.removeAttr("disabled");
				return;
			}
			timeLoop(btnElement, wait);
		}, 1000);
	}
});