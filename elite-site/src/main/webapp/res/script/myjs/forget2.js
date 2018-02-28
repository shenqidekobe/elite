require([ "jquery", "ajax", "jsonAjax","md5"], function($) {
	$(".input-yel").click(function() {
		var newPass = $.trim($("#newPass").val());
		var newPass1 = $.trim($("#newPass1").val());
		if (newPass == "") {
			$("#newPass").addClass("active");
			$("#tipsError").text("请输入新的密码");
			return false;
		}else if(newPass.length<6){
			$("#newPass").addClass("active");
			$("#tipsError").text("密码长度不能小于六位");
			return false;
		}else if(newPass!=newPass1){
			$("#newPass1").addClass("active");
			$("#tipsError").text("确认密码不一致");
			return false;
		}
		newPass = $.md5(newPass);
		jsonAjax.post({
			url : ctx+'/forget/reset/pass',
			data : {newPass:newPass},
			success : function(data) {
				if (data.result == "SUCCESS") {
					window.location.href = ctx+"/forget/reset";
				}else{
					$("#tipsError").text(data.msg);
				}
			}
		});
	});

	// 清除错误消息
	$("#forgetForm input").focus(function() {
		$("#tipsError").text("");
		$(this).removeClass("active");
	});
});