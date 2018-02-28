function initAccountSecurity() {
	upload.uploadImg($("#cardJustFile"), "credit", function(data) {
		$("#cardJustImg").attr("src", data.url);
		$("#cardJustId").val(data.attaId);
	});
	upload.uploadImg($("#cardReverseFile"), "credit", function(data) {
		$("#cardReverseImg").attr("src", data.url);
		$("#cardReverseId").val(data.attaId);
	});
	upload.uploadImg($("#jobCertFile"), "credit", function(data) {
		$("#jobCertImg").attr("src", data.url);
		$("#jobCertId").val(data.attaId);
	});
	upload.uploadImg($("#pmpCretFile"), "credit", function(data) {
		$("#pmpCretImg").attr("src", data.url);
		$("#pmpCretId").val(data.attaId);
	});
	upload.uploadImg($("#businessCertFile"), "credit", function(data) {
		$("#businessCertImg").attr("src", data.url);
		$("#businessCertId").val(data.attaId);
	});
	$("p[id='jobCertShowBtn']").click(function() {
		$(this).next().toggle();
	})
	$("p[id='businessCertShowBtn']").click(function() {
		$(this).next().toggle();
	})
	$("p[id='pmpCretShowBtn']").click(function() {
		$(this).next().toggle();
	})
	// 解决文本框设置readonly后按删除键后退页面问题
	$("input[readOnly]").keydown(function(e) {
		e.preventDefault();
	});
	var kucity = $('#areaName').kuCity()
	$("#cityIcon").click(function() {
		kucity.focus();
	});
	// 从业年限
	$("#workYearAcc,#workyear-triangle,#work").click(function() {
		if ($("#saveAccountBasicBtn").is(":hidden") == true) {
			return;
		}
		$("#workYear-select").fadeIn();
		$("#workYear-select li").click(function() {
			var thiss = $(this);
			$("#work").val(thiss.text());
			$("#jobAge").val(thiss.attr("data"))
			$("#workYear-select").fadeOut("fast");
		});
		$("body").bind("mousedown", function(event) {
			$("#workYear-select").fadeOut("fast");
			$("body").unbind("mousedown");
		});
	});

	// 关注行业
	$("#workProfession,#workProfession-triangle,#dustryinputid").click(function() {
		if ($("#saveAccountBasicBtn").is(":hidden") == true) {
			return;
		}
		$("#industry-select").fadeIn();
		$("#industry-select li").click(function() {
			var thiss = $(this);
			$("#dustryinputid").val(thiss.text());
			$("#attentionIndustry").val(thiss.attr("data"))
			$("#industry-select").fadeOut("fast");
		});
		$("body").bind("mousedown", function(event) {
			$("#industry-select").fadeOut("fast");
			$("body").unbind("mousedown");
		});
	});

	// 擅长职位
	$("#workTitle,#work-triangle,#job").click(function() {
		if ($("#saveAccountBasicBtn").is(":hidden") == true) {
			return;
		}
		$("#work-select").fadeIn();
		$("#work-select li").click(function() {
			var thiss = $(this);
			$("#job").val(thiss.text());
			$("#goodatJob").val(thiss.attr("data"))
			$("#work-select").fadeOut("fast");
		});
		$("body").bind("mousedown", function(event) {
			$("#work-select").fadeOut("fast");
			$("body").unbind("mousedown");
		});
	});
	$('#birthday').datepicker({
		orientation : "right",
		startDate : new Date(1920, 1, 1),
		endDate : new Date(),
		startView : 'decade',
		format : "yyyy-mm-dd",
		autoclose : true
	});
	$("#saveAccountBasicBtn").click(updateBasic);
	$("#saveAccountCreditBtn").click(updateCredit);
	$("#restPassBtn,#restPassCancelBtn,#closeRestSetFormId").click(function() {
		$("#oldPasswordId").val("");
		$("#newPasswordId").val("");
		$("#repeatNewPasswordId").val("");
		$("#accountErrorDivId").hide();
		$("#contean-account").toggle();
	});
	$("#restPassSubBtn").click(restPassSubmit);

	// 性别选择
	$("#selectSexPan li").click(function() {
		var data = $(this).attr("data");
		if (data == "M") {
			$("#manselecticon").attr("class", "sex_icon man_select");
			$("#manselectcolor").attr("class", "sex_text active_color");
			$("#womanselecticon").attr("class", "sex_icon woman_noselect");
			$("#womanselectcolor").attr("class", "sex_text default_color");
		} else if (data == "F") {
			$("#womanselecticon").attr("class", "sex_icon man_select");
			$("#womanselectcolor").attr("class", "sex_text active_color");
			$("#manselecticon").attr("class", "sex_icon woman_noselect");
			$("#manselectcolor").attr("class", "sex_text default_color");
		}
		$(this).removeClass("default_bg").addClass("active_bg");
		$(this).siblings().removeClass("active_bg").addClass("default_bg");
		$("#sexId").val(data);
	})
	// type选择
	$("#selectTypeId,#selectTypeIconId").click(function() {
		$("#selectTypeUlId").fadeIn("fast");
		$("#selectTypeUlId li").click(function() {
			var title = $(this).html();
			var data = $(this).attr("data");
			$("#selectTypeId").val(title)
			$("#selectTypeValueId").val(data)
		});
		$("body").bind("mousedown", function(event) {
			$("#selectTypeUlId").fadeOut("fast");
			$("body").unbind("mousedown");
		});
	})

	// success提醒框消失
	$("#successMessageCloseBtn,#successMessageBtn").click(function() {
		$("#MassageBoxId").fadeOut();
	})
}
// 修改基本信息
function updateBasic() {
	$("#errorDivId").hide();
	var nickName = $("#nickName").val().trim();
	if (nickName == "") {
		$("#errorDivId").fadeIn();
		$("#errorMessageId").text("请输入用户名");
		errorTipHide($("#errorDivId"),3000);
		return;
	}
	var birthday = $("#birthday").val().trim();
	if (birthday == "") {
		$("#errorDivId").fadeIn();
		$("#errorMessageId").text("请选择出生年月");
		errorTipHide($("#errorDivId"),3000);
		return;
	}
	var email = $("#email").val()
	if (email == "") {
		$("#errorDivId").fadeIn();
		$("#errorMessageId").text("请输入邮箱");
		errorTipHide($("#errorDivId"),3000);
		return;
	} else {
		var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
		if (!pattern.test(email)) {
			$("#errorDivId").fadeIn();
			$("#errorMessageId").text("请输入正确邮箱地址");
			errorTipHide($("#errorDivId"),3000);
			return;
		}
	}
	var jobAge = $("#jobAge").val().trim();
	if (jobAge == "") {
		$("#errorDivId").fadeIn();
		$("#errorMessageId").text("请选择工作年限");
		errorTipHide($("#errorDivId"),3000);
		return;
	}
	var attentionIndustry = $("#attentionIndustry").val().trim();
	if (attentionIndustry == "") {
		$("#errorDivId").fadeIn();
		$("#errorMessageId").text("请选择聚焦行业");
		errorTipHide($("#errorDivId"),3000);
		return;
	}
	var goodatJob = $("#goodatJob").val().trim();
	if (goodatJob == "") {
		$("#errorDivId").fadeIn();
		$("#errorMessageId").text("请选择擅长职位");
		errorTipHide($("#errorDivId"),3000);
		return;
	}
	var areaName = $("#areaName").val().trim();
	if (areaName == "") {
		$("#errorDivId").fadeIn();
		$("#errorMessageId").text("请选择城市");
		errorTipHide($("#errorDivId"),3000);
		return;
	}
	var companyName = $("#companyName").val().trim();
	if (companyName == "") {
		$("#errorDivId").fadeIn();
		$("#errorMessageId").text("请输入公司名称");
		errorTipHide($("#errorDivId"),3000);
		return;
	}
	var sex = $("#sexId").val();
	var type = $("#selectTypeValueId").val();
	jsonAjax.post({
		url : ctx + '/partner/partnerElite/basic/update',
		data : {
			birthday : birthday,
			email : email,
			jobAge : jobAge,
			attentionIndustry : attentionIndustry,
			goodatJob : goodatJob,
			areaName : areaName,
			companyName : companyName,
			nickName : nickName,
			sex : sex,
			type : type
		},
		success : function(data) {
			if (data.result == "SUCCESS") {
				$("#MassageBoxId").fadeIn();
				if (nickName.length > 5) {
					nickName = nickName.substring(0, 5) + "...";
				}
				$("#layoutNickNameId").html(nickName).siblings().text(areaName);
				/*window.setTimeout(function() {
					refreshThisPage();
				}, 3000);*/
			} else {
				$("#errorDivId").fadeIn();
				$("#errorMessageId").text(data.msg);
			}
		}
	});
}

// 修改征信信息
function updateCredit() {
	var idCard = $("#idCard").val().trim();
	$("#creditErrorDivId").hide();
	var realName = $("#realNameId").val().trim();
	if (realName == "") {
		$("#creditErrorDivId").fadeIn();
		$("#creditErrorMessageId").text("请输入姓名");
		errorTipHide($("#creditErrorDivId"),3000);
		return;
	} else if (idCard == "") {
		$("#creditErrorDivId").fadeIn();
		$("#creditErrorMessageId").text("请输入身份证号");
		errorTipHide($("#creditErrorDivId"),3000);
		return;
	} else if (!validateCard(idCard)) {
		$("#creditErrorDivId").fadeIn();
		$("#creditErrorMessageId").text("请输入正确身份证号");
		errorTipHide($("#creditErrorDivId"),3000);
		return;
	}
	var cardJust = $("#cardJustId").val().trim();
	if (cardJust == "") {
		$("#creditErrorDivId").fadeIn();
		$("#creditErrorMessageId").text("请上传身份证正面");
		errorTipHide($("#creditErrorDivId"),3000);
		return;
	}
	var jobCert = $("#jobCertId").val().trim();
	var businessCert = $("#businessCertId").val().trim();
	var pmpCret = $("#pmpCretId").val().trim();
	var cardReverse = $("#cardReverseId").val().trim();
	jsonAjax.post({
		url : ctx + '/partner/partnerElite/credit/save',
		data : {
			idCard : idCard,
			cardJust : cardJust,
			cardReverse : cardReverse,
			jobCert : jobCert,
			businessCert : businessCert,
			pmpCret : pmpCret,
			realName : realName
		},
		type : "POST",
		success : function(data) {
			if (data.result == "SUCCESS") {
				$("#MassageBoxId").fadeIn();
				window.setTimeout(function() {
					refreshThisPage();
				}, 3000);
			} else {
				$("#creditErrorDivId").fadeIn();
				$("#creditErrorMessageId").text(data.msg);
				errorTipHide($("#creditErrorDivId"),3000);
			}

		}
	});
}

// 提交修改 账户安全
function restPassSubmit() {
	$("#accountErrorDivId").hide();
	$("#accountErrorMessageId").text("");
	var oldPass = $("#oldPasswordId").val();
	if (oldPass == "") {
		$("#accountErrorDivId").fadeIn();
		$("#accountErrorMessageId").text("请输入原密码");
		errorTipHide($("#accountErrorDivId"),3000);
		return;
	}
	var newPass = $("#newPasswordId").val();
	if (newPass == "") {
		$("#accountErrorDivId").fadeIn();
		$("#accountErrorMessageId").text("请输入新密码");
		errorTipHide($("#accountErrorDivId"),3000);
		return;
	} else if (oldPass == newPass) {
		$("#accountErrorDivId").fadeIn();
		$("#accountErrorMessageId").text("新密码不能和旧密码一样");
		errorTipHide($("#accountErrorDivId"),3000);
		return;
	} else if (newPass.length < 6) {
		$("#accountErrorDivId").fadeIn();
		$("#accountErrorMessageId").text("密码长度最少6位");
		errorTipHide($("#accountErrorDivId"),3000);
		return;
	} else {
		var reNewPass = $("#repeatNewPasswordId").val();
		if (newPass != reNewPass) {
			$("#accountErrorDivId").fadeIn();
			$("#accountErrorMessageId").text("两次密码输入不一样");
			errorTipHide($("#accountErrorDivId"),3000);
			return;
		}
	}
	$("#newPassId").val($.md5($.trim($("#newPassId").val())));
	jsonAjax.post({
		url : ctx + '/partner/partnerElite/reset/pass',
		data : {
			oldPass : $.md5($.trim(oldPass)),
			newPass : $.md5($.trim(newPass))
		},
		success : function(data) {
			if (data.result == "SUCCESS") {
				$("#MassageBoxId").fadeIn();
				window.setTimeout(function() {
					refreshThisPage()
				}, 1000);

			} else {
				$("#accountErrorDivId").fadeIn();
				$("#accountErrorMessageId").text(data.msg);
			}
		}
	});
}
// 刷新
function refreshThisPage() {
	jsonAjax.post({
		url : ctx + '/partner/partnerElite/accountSecurity',
		success : function(data) {
			$("#context").html(data);
			initAccountSecurity();
		}
	});
}
// 验证两次密码是否相同
function repeatNewPasswordValidate() {
	var newpassword = $("#newPasswordId").val();
	var renewpassword = $("#repeatNewPasswordId").val();
	if (newpassword != renewpassword) {
		$("#resetpass_error_tip").text("两次新密码输入不一样");
	} else {
		if ("#resetpass_error_tip:contains('两次新密码输入不一样')") {
			$("#resetpass_error_tip").text("");
		}
	}
}
// 获取验证码
function sendCodeFuncton() {
	$(".error-tip").text("");
	var $this = $("#send-msg-btn");
	var account = $("#useraccount").val();
	var mobileReg = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
	if (!mobileReg.test(account)) {
		$("#.error-tip").text("手机号无效");
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
			}
		}
	});
};
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
function errorTipHide(that,time) {
	window.setTimeout(function() {
		that.hide();
	}, time);
}
