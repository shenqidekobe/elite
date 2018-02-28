require([ "jquery", "ajax", "jsonAjax", "md5", "city", "commons" ], function($) {

	var kucity = $('#areaName').kuCity();
	$("#cityIcon").click(function() {
		kucity.focus();
	});
	
	//url是否有邀请码
	var inviteCode=getUrlParams("ic");
	$("#inviteCodeId").val(inviteCode);
	// 手机号码数字限制
	$("#phone").bind("keyup blur", function() {
		var phone = $(this).val();
		$(this).val(phone.replace(/\D/g, ''));
	});
	// 注册账户
	$("#registerBtn").click(function() {
		$(".error-tip").text("");

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
			url : ctx + '/partner/partnerCompany/register',
			data : $("#project-form1").serialize(),
			success : function(data) {
				if (data.result == "SUCCESS") {
					window.location.href = ctx + "/partner/partnerCompany/basic/register/view";
				} else {
					$(".error-tip").text(data.msg);
				}
			}
		});
	});
	// 资料完善 提交
	$("#updateBasic").click(function() {
		var areaName = $("#areaName").val().trim();
		$(".error-tip").text("");
		if (areaName == "") {
			$(".error-tip").text("请选择城市");
			return;
		}
		var companyName = $("#companyNameId").val().trim();
		var email = $("#emailId").val().trim();
		if (email == "") {
			$(".error-tip").text("请输入邮箱");
			return;
		} else {
			var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
			if (!pattern.test(email)) {
				$(".error-tip").text("请输入正确邮箱地址");
				return;
			}
		}
		var sex = $("#sexId").val();
		jsonAjax.post({
			url : ctx + '/partner/partnerCompany/info/update',
			data : {
				sex : sex,
				areaName : areaName,
				companyName : companyName,
				email : email
			},
			success : function(data) {
				if (data.result == "SUCCESS") {
					window.location.href = ctx + "/partner/partnerCompany/credit/register/view";
				} else {
					$(".error-tip").text(data.msg);
				}
			}
		});
	})

	// 性别选择
	$("button[id='sex']").click(function() {
		var val = $(this).val();

		$(this).addClass("active_radio").siblings().removeClass("active_radio");
		if (val == "M") {
			$(this).find(".radio-male").removeClass("man_noselect").addClass("man_select");
			$(".woman_radio").find(".radio-male").removeClass("woman_select").addClass("woman_noselect");

		} else if (val == "F") {
			$(this).find(".radio-male").removeClass("woman_noselect").addClass("woman_select");
			$(".man_radio").find(".radio-male").removeClass("man_select").addClass("man_noselect");

		}

		$("#sexId").val($(this).val());

	})
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