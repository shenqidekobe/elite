require([ "jquery", "ajax", "jsonAjax","commons"], function($) {
	var timeoutProcess=null;
	$(".input-yel").click(function() {
		var account = $("#account").val();
		var verifycode = $("#verifycode").val();
		if (account == "") {
			$("#account").addClass("active");
			$("#tipsError").text("请输入注册的手机号");
			return;
		}else if (!validatePhone(account)) {
			$("#account").addClass("active");
			$("#tipsError").text("手机号无效");
			return;
		}else if (verifycode == "") {
			$("#verifycode").addClass("active");
			$("#tipsError").text("请输入验证码");
			return;
		}
		jsonAjax.post({
			url : ctx+'/forget/verify',
			data : {
				account:account,
				verifyCode:verifycode,
			},
			success : function(data) {
				if (data.result == "SUCCESS") {
					window.location.href = ctx+"/forget/code";
				} else {
					$("#tipsError").text(data.msg);
					$("#sendMsgBtn").val("获取验证码");
					$("#sendMsgBtn").removeAttr("disabled");
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
	$("#forgetForm input").focus(function() {
		$("#tipsError").text("");
		$(this).removeClass("active");
	});

	// 获取验证码
	$("#sendMsgBtn").click(function() {
		var $this = $(this);
		var account = $("#account").val();
		console.info("account = "+account);
		if (account == "") {
			$("#account").addClass("active");
			$("#tipsError").text("请输入注册的手机号");
			return;
		}else if (!validatePhone(account)) {
			$("#account").addClass("active");
			$("#tipsError").text("手机号无效");
			return;
		}
		// 发送验证码
		jsonAjax.post({
			url : ctx+'/common/sms/send',
			data : {
				phone : account,
				type : "forget"
			},
			success : function(data) {
				if (data.result == "SUCCESS") {
					sendSmsCode($this);
				}else{
					$("#tipsError").text(data.msg);
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