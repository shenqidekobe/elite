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
	upload.uploadImg($("#jobCertFile"), "credit", function(data) {
		$("#jobCertImg").attr("src", data.url);
		$("#jobCertImg").css({
			width : '230px',
			height : '160px'
		});
		$("#jobCertId").val(data.attaId);
	});
	upload.uploadImg($("#businessCertFile"), "credit", function(data) {
		$("#businessCertImg").attr("src", data.url);
		$("#businessCertImg").css({
			width : '230px',
			height : '160px'
		});
		$("#businessCertId").val(data.attaId);
	});
	upload.uploadImg($("#graduateCertFile"), "credit", function(data) {
		$("#graduateCertImg").attr("src", data.url);
		$("#graduateCertImg").css({
			width : '230px',
			height : '160px'
		});
		$("#graduateCertId").val(data.attaId);
	});
	upload.uploadImg($("#pmpCretFile"), "credit", function(data) {
		$("#pmpCretImg").attr("src", data.url);
		$("#pmpCretId").val(data.attaId);
	});
	$("#pmpCretShowBtn,#jobCertShowBtn,#businessCertShowBtn").click(function() {
			$(this).next().toggle();
			$(this).parent().siblings().children("#otherImageId").hide();
	})
	upload.uploadFile($("#resumeAttaFile"), "elite", "resume_atta", function(data) {
		$("#resumeAtta_show").show();
		var prd = '<button type="button" class="upload-file">' + data.originalName + '</button><img src=' + ctx + '"/res/images/del.png" data="' + data.attaId + '" id="pbimg" class="delete-cross">';
		$("#resumeAtta_show").html(prd);
		$("img[id=pbimg]").unbind('click');
		$("img[id=pbimg]").bind('click', function(e) {
			var thiss = $(this);
			var bthis = thiss.prev();
			var id = thiss.attr("data");
			upload.removeFile("resume_atta", function(url) {
				jsonAjax.post({
					url : url,
					data : {
						attaId : id
					},
					success : function(drsp) {
						if (drsp.result == "SUCCESS") {
							$("#resumeAttaId").val('');
							thiss.remove();
							bthis.remove();
						}
					}
				});
			});
		});
		$("#resumeAttaId").val(data.attaId);
	});

	// 上传征信信息
	$("#saveCreditBtn").click(function() {

		$(".error-tip").text("")
		var isCard = $("#idCardId").val();
		var reaName = $("#realNameId").val();
		var cardJust = $("#cardJustId").val();
		var cardReverse = $("#cardReverseId").val();
		if (reaName == "") {
			$(".error-tip").text("请输入您的姓名");
			errorShow();
			return;
		} else if (isCard == "") {
			$(".error-tip").text("请输入身份证号");
			errorShow();
			return;
		}
		else if (!validateCard(isCard)) {
			$(".error-tip").text("请输入正确身份证号");
			errorShow();
			return;
		}
		
		jsonAjax.post({
			url : ctx + '/partner/partnerElite/credit/save',
			data : $("#personnel-form2").serialize(),
			success : function(data) {
				if (data.result == "SUCCESS") {
					window.location.href = ctx + '/member/index';
				} else {
					$(".error-tip").text(data.msg);
					errorShow();
				}
			}
		});
		
		
		function errorShow(){
			$(".error_div").show();
			$("body").bind("mousedown",function(){
				$(".error_div").hide();
				$("body").unbind("mousedown");
			});
		}
		
		
		
		
	})

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
