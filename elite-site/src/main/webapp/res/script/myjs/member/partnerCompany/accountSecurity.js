function initAccountSecurity() {
	var kucity = $('#areaName').kuCity()
	$("#cityIcon").click(function() {
		kucity.focus();
	});
	$("#saveAccountBasicBtn").click(updateBasic);
	$("#saveAccountCreditBtn").click(updateCredit);
	$("#restPassBtn,#restPassCancelBtn,#reset_icon,#closeRestSetFormId").click(function() {
		$("#oldPasswordId").val("");
		$("#newPasswordId").val("");
		$("#repeatNewPasswordId").val("");
		$("#accountErrorId").hide();
		$("#contean-account").toggle();
	});
	$("#restPassSubBtn").click(restPassSubmit);
	$("#repeatNewPasswordId").blur(repeatNewPasswordValidate)
	
	//性别选择
	 $("#selectSexPan li").click(function(){
  	  var data=$(this).attr("data");
  	  if(data=="M"){
  		  $("#manselecticon").attr("class","sex_icon man_select");
  		  $("#manselectcolor").attr("class","sex_text active_color");
  		  $("#womanselecticon").attr("class","sex_icon woman_noselect");
  		  $("#womanselectcolor").attr("class","sex_text default_color");
  	  }
  	  else if(data=="F"){
 		  $("#womanselecticon").attr("class","sex_icon woman_select");
 		  $("#womanselectcolor").attr("class","sex_text active_color");
 		  $("#manselecticon").attr("class","sex_icon man_noselect");
 		  $("#manselectcolor").attr("class","sex_text default_color");
  	  }
  	  $(this).removeClass("default_bg").addClass("active_bg");
  	  $(this).siblings().removeClass("active_bg").addClass("default_bg");
  	  $("#sexId").val(data);
    })
	   //type选择
	$("#selectTypeId,#selectTypeIconId").click(function(){
		$("#selectTypeUlId").fadeIn("fast");
		$("#selectTypeUlId li").click(function() {
			var title=$(this).html();
			var data=$(this).attr("data");
			$("#selectTypeId").val(title)
			$("#selectTypeValueId").val(data)
		});
		$("body").bind("mousedown", function(event) {
			$("#selectTypeUlId").fadeOut("fast");
			$("body").unbind("mousedown");
		});
	})
	
	upload.uploadImg($("#cardJustFile"), "credit", function(data) {
		$("#cardJustImg").attr("src", data.url);
		$("#cardJustId").val(data.attaId);
	});
	upload.uploadImg($("#cardReverseFile"), "credit", function(data) {
		$("#cardReverseImg").attr("src", data.url);
		$("#cardReverseId").val(data.attaId);
	});
	
	//success提醒框消失
	$("#successMessageCloseBtn,#successMessageBtn").click(function(){
		$("#MassageBoxId").fadeOut();
	})

}
// 修改基本信息
function updateBasic() {
	$("#basicErrorId").hide();
	var nickName = $("#nickName").val().trim();
	if (nickName == "") {
		$("#basicErrorId").show();
		$("#basicErrorMessageId").html("请输入用户名");
		errorTipHide($("#basicErrorId"),3000);
		return;
	}
	var areaName = $("#areaName").val().trim();
	if (areaName == "") {
		$("#basicErrorId").show();
		$("#basicErrorMessageId").html("请选择城市");
		errorTipHide($("#basicErrorId"),3000);
		return;
	}
	var companyName = $("#companyName").val().trim();
	if (companyName == "") {
		$("#basicErrorId").show();
		$("#basicErrorMessageId").html("请填写结构名称");
		errorTipHide($("#basicErrorId"),3000);
		return;
	}
	var email = $("#email").val()
	if (email == "") {
		$("#basicErrorId").show();
		$("#basicErrorMessageId").html("请输入邮箱");
		errorTipHide($("#basicErrorId"),3000);
		return;
	} else {
		var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
		if (!pattern.test(email)) {
			$("#basicErrorId").show();
			$("#basicErrorMessageId").html("请输入正确邮箱");
			errorTipHide($("#basicErrorId"),3000);
			return;
		}
	}
	var type=$("#selectTypeValueId").val();
	var sex=$("#sexId").val()
	jsonAjax.post({
		url : ctx + '/partner/partnerCompany/basic/update',
		data : {
			nickName : nickName,
			areaName : areaName,
			companyName : companyName,
			email : email,
		    sex:sex,
		    type:type
		},
		success : function(data) {
			if (data.result == "SUCCESS") {
				$("#MassageBoxId").fadeIn();
			/*	window.setTimeout(function() {
					refreshThisPage();;
				}, 3000);*/
				if (nickName.length > 5) {
					nickName = nickName.substring(0, 5) + "...";
				}
				$("#layoutNickNameId").html(nickName).siblings().text(areaName);
				
			} else {
				$("#basic_error_tip").text(data.msg);
			}

		}
	});
}

// 提交修改 账户安全
function restPassSubmit() {
	$("#accountErrorId").hide();
	var oldPass = $("#oldPasswordId").val();
	if (oldPass == "") {
		$("#accountErrorId").show();
		$("#accountErrorMessageId").html("请输入密码");
		errorTipHide($("#accountErrorId"),3000);
		return;
	}
	var newPass = $("#newPasswordId").val();
	if (newPass == "") {
		$("#accountErrorId").show();
		$("#accountErrorMessageId").html("请输入新密码");
		errorTipHide($("#accountErrorId"),3000);
		return;
	} else if (oldPass == newPass) {
		$("#accountErrorId").show();
		$("#accountErrorMessageId").html("新密码不能和旧密码一样");
		errorTipHide($("#accountErrorId"),3000);
		return;
	} else if (newPass.length < 6) {
		$("#accountErrorId").show();
		$("#accountErrorMessageId").html("密码长度最少6位");
		return;
	} else {
		var reNewPass = $("#repeatNewPasswordId").val();
		if (newPass != reNewPass) {
			$("#accountErrorId").show();
			$("#accountErrorMessageId").html("两次密码输入不一样");
			errorTipHide($("#accountErrorId"),3000);
			return;
		}
	}

	$("#newPassId").val($.md5($.trim($("#newPassId").val())));
	jsonAjax.post({
		url : ctx + '/partner/partnerCompany/reset/pass',
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
				$("#accountErrorId").show();
				$("#accountErrorMessageId").html(data.msg);
			}
		}
	});
}
//
function updateCredit() {
	$("#creditErrorId").hide();
	var realName = $("#realNameId").val();
	if (realName == "") {
		$("#creditErrorId").show();
		$("#creditErrorMessageId").html("请输入姓名");
		errorTipHide($("#creditErrorId"),3000);
		return;
	}
	var idCard = $("#idCardId").val();
	if (idCard == "") {
		$("#creditErrorId").show();
		$("#creditErrorMessageId").html("请输入身份证号码");
		errorTipHide($("#creditErrorId"),3000);
		return;
	} 
	var cardJust = $("#cardJustId").val();
	if (cardJust == "") {
		$("#creditErrorId").show();
		$("#creditErrorMessageId").html("请上传身份证正面");
		errorTipHide($("#creditErrorId"),3000);
		return;
	} 
	var cardReverse = $("#cardReverseId").val();
	if (cardReverse == "") {
		$("#creditErrorId").show();
		$("#creditErrorMessageId").html("请上传身份证反面");
		errorTipHide($("#creditErrorId"),3000);
		return;
	} 
	jsonAjax.post({
		url : ctx + '/partner/partnerCompany/credit/save',
		data : {
			realName:realName,
			idCard:idCard,
			cardJust:cardJust,
			cardReverse:cardReverse
		},
		success : function(data) {
			if (data.result == "SUCCESS") {
				$("#MassageBoxId").fadeIn();
				window.setTimeout(function() {
					$("#MassageBoxId").fadeOut();
				}, 3000);
			}else{
				$("#creditErrorId").show();
				$("#creditErrorMessageId").html(data.msg);
			}
		}
	});
}
// 刷新
function refreshThisPage() {
	jsonAjax.post({
		url : ctx + '/partner/partnerCompany/accountSecurity',
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
		$("#accountErrorId").show();
		$("#accountErrorMessageId").html('两次新密码输入不一样');
	} else {
		if ("#accountErrorMessageId:contains('两次新密码输入不一样')") {
			$("#accountErrorMessageId").html("");
			$("#accountErrorId").hide();
		}
	}
}
function errorTipHide(that,time) {
	window.setTimeout(function() {
		that.hide();
	}, time);
}
