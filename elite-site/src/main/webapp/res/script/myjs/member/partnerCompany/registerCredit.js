require([ "jquery", "ajax", "jsonAjax", "md5", "webupload", "bootstrap-datepicker", "commons" ], function($) {
	// 项目定级时间
	upload.uploadImg($("#cardJustFile"), "credit", function(data) {
		$("#cardJustImg").attr("src", data.url);
		$("#cardJustId").val(data.attaId);
	});
	upload.uploadImg($("#cardReverseFile"), "credit", function(data) {
		$("#cardReverseImg").attr("src", data.url);
		$("#cardReverseId").val(data.attaId);
	});

	// 上传征信信息
	$("#saveCreditBtn").click(function() {

		$(".error-tip").text("")
		var isCard = $("#idCardId").val();
		var reaName = $("#realNameId").val();
		var cardJust=$("#cardJustId").val();
		var cardReverse=$("#cardReverseId").val();
		if (reaName == "") {
			$(".error-tip").text("请输入您的姓名");
			return;
		} 
		else if (isCard == "") {
			$(".error-tip").text("请输入身份证号");
			return;
		} else if (!validateCard(isCard)) {
			$(".error-tip").text("请输入正确身份证号");
			return;
		}
		jsonAjax.post({
			url : ctx + '/partner/partnerCompany/credit/save',
			data : $("#personnel-form2").serialize(),
			success : function(data) {
				if (data.result == "SUCCESS") {
					window.location.href = ctx + '/member/index';
				} else {
					$(".error-tip").text(data.msg);
				}
			}
		});
	})
});
