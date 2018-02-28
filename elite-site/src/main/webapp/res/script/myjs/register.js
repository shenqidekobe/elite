require([ "jquery", "ajax", "jsonAjax","md5","commons"], function($) {
	$("#account").val('');
	$("#password").val('');
	
	$(".login-logo").click(function(){
		window.location.href=ctx+'/';
	});
	// 身份选择
	$(".project").click(
			function() {
				if ($(this).hasClass("other-select")) {
					$(".select-line").animate({
						"margin-left" : "0"
					}, 'slow');
					$(this).addClass("current-select").removeClass(
							"other-select").siblings(".identity").addClass(
							"other-select").removeClass("current-select");
					$(".show-box").removeClass("register2").addClass("register1");
				}
				$("#currentType").val("company");
				$(".invite_code_box").hide();
			});

	$(".person").click(
			function() {
				if ($(this).hasClass("other-select")) {
					$(".select-line").animate({
						"margin-left" : "200px"
					}, 'slow');
					$(this).addClass("current-select").removeClass(
							"other-select").siblings(".identity").addClass(
							"other-select").removeClass("current-select");
					$(".show-box").removeClass("register1").addClass("register2");
				}
				$("#currentType").val("elite");
				$(".invite_code_box").show();
				$("#inviteCode").focus(function(){
					$(".code_div").hide();
				});
				$("#inviteCode").mouseover(function(){
					if(!$(".code_div").is(':visible')){
						$(".code_div").show();
						$(".code_div").unbind("click");
						$(".code_div").click(function(){
							window.open(ctx+"/invite");
						});
					}
				});
				$(".code_div").click(function(){
					window.open(ctx+"/invite");
				});
				$("body").mouseout(function(e){
					if(e.target.id!="inviteCode"){
						$(".code_div").hide();
					}
				});
				
			});
	//url是否有邀请码
	var inviteCode=getUrlParams("ic");
	$("#inviteCode").val(inviteCode);
	var registerType=sessionStorage.registerType;
	if(registerType=="elite"||registerType=="cto"){
		$(".person").click();
	}else{
		var ts=getUrlParams("ts");
		if("elite"==ts){
			$(".person").click();
		}
	}
	$("#nickName,#account,#password,#verifycode").focus(function(){
		$(this).removeClass("error-sign");
		$("#registerError").text("");
	});

	var timeoutProcess=null;
	// 注册
	$("#registerBtn").click(function() {
		$("#registerForm  input").removeClass("error-sign");
		$("#registerError").text("");
		var nickName = $.trim($("#nickName").val());
		var account = $.trim($("#account").val());
		var password = $.trim($("#password").val());
		var verifycode = $.trim($("#verifycode").val());
		var currentType=$("#currentType").val();

		nickName = $.trim(nickName);
		var inviteCode="";
		//验证精英的邀请码是否已输入
		if(currentType=='elite'){
			inviteCode=$.trim($("#inviteCode").val());
			if(inviteCode==""||inviteCode.length>8){
				$("#inviteCode").addClass("error-sign");
				$("#registerError").text("请输入您的邀请码");
				return;
			}
		}
		if (nickName == "" || nickName == null) {
			$("#nickName").addClass("error-sign");
			$("#registerError").text("请输入用户名");
			return;
		}else if(!validateIllegalChar(nickName)){
			$("#nickName").addClass("error-sign");
			$("#registerError").text("请输入合法的用户名");
			return;
		}else if (account == "") {
			$("#account").addClass("error-sign");
			$("#registerError").text("请输入手机号");
			return;
		}else if (!validatePhone(account)) {
			$("#account").addClass("error-sign");
			$("#registerError").text("手机号无效");
			return;
		}else if (password == "" || password == null) {
			$("#password").addClass("error-sign");
			$("#registerError").text("请输入密码");
			return;
		}else if (password.length < 6) {
			$("#password").addClass("error-sign");
			$("#registerError").text("密码长度不少于6位");
			return;
		}else if (verifycode == "") {
			$("#verifycode").addClass("error-sign");
			$("#registerError").text("请输入验证码");
			return;
		}else if (!$('.project_checkbox').is(':checked')) {
			$("#registerError").text("请先阅读并同意《云英汇用户协议》");
			return;
		}
		password = $.md5(password);
		var type=$("#currentType").val();
		jsonAjax.post({
			url : ctx+'/register/save',
			data : {
				nickName:nickName,
				account:account,
				password:password,
				verifyCode:verifycode,
				inviteCode:inviteCode,
				currentType:type
			},
			success : function(data) {
				if (data.result == "SUCCESS") {
					if(type=="company"){
						window.location.href = ctx+"/member/basic";
					}else if(type=="elite"){
						window.location.href = ctx+"/member/current";
					}
				} else {
					if(data.code=="3011"){
						$("#verifycode").removeClass("error-sign");
						$("#inviteCode").addClass("error-sign");
					}
					$("#sendMsgBtn").val("获取验证码");
					$("#sendMsgBtn").removeAttr("disabled");
					$("#registerError").text(data.msg);
					clearTimeout(timeoutProcess);
				}
			}
		});

	});

	// 验证码
	$("#verifycode").on("keyup", function() {
		this.value = this.value.replace(/\D|\s/g, "");
	});

	// 清除错误消息
	$("#registerForm  input").focus(function() {
		$("#registerError").text("");
	});

	// 获取验证码
	$("#sendMsgBtn").click(function() {
		var $this = $(this);
		var account = $("#account").val();
		var currentType=$("#currentType").val();

		//验证精英的邀请码是否已输入
		var inviteCode="";
		if(currentType=='elite'){
			inviteCode=$.trim($("#inviteCode").val());
			if(inviteCode==""||inviteCode.length>8){
				$("#inviteCode").addClass("error-sign");
				$("#registerError").text("请输入您的邀请码");
				return;
			}
		}
		if (account == "") {
			$("#account").addClass("error-sign");
			$("#registerError").text("请输入手机号");
			return;
		}else if (!validatePhone(account)) {
			$("#account").addClass("error-sign");
			$("#registerError").text("手机号无效");
			return;
		}
		// 发送验证码
		jsonAjax.post({
			url : ctx+'/common/sms/send',
			data : {
				phone : account,
				type : "register",
				inviteCode:inviteCode,
				currentType:currentType
			},
			success : function(data) {
				if (data.result == "SUCCESS") {
					$("#inviteCode").removeClass("error-sign");
					sendSmsCode($this);
				}else{
					if(data.code=="3011"){
						$("#verifycode").removeClass("error-sign");
						$("#inviteCode").addClass("error-sign");
					}
					$("#registerError").text(data.msg);
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
		timeoutProcess=setTimeout(function() {
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