require([ "jquery", "ajax", "jsonAjax", "md5", "webupload", "bootstrap-datepicker", "commons" ], function($) {
	// 行业信息 注册
	$("#saveIndustryBtn").click(function() {
		$(".error-tip").text("");
		var jobAge = $("#workYear").attr("data");
		if (jobAge == "") {
			showError();
			$(".error-tip").text("请选择从业年限");
			return;
		}
		var attentionIndustry = $("#workProfession").attr("data");
		if (attentionIndustry == "") {
			showError();
			$(".error-tip").text("请选择聚焦行业");
			return;
		}
		var goodatJob = $("#workTitle").attr("data");
		if (goodatJob == "") {
			showError();
			$(".error-tip").text("请选择擅长职位");
			return;
		}
		var companyName = $("#companyNameId").val();
		jsonAjax.post({
			url : ctx + '/partner/partnerElite/industry/save',
			data : {
				jobAge : jobAge,
				attentionIndustry : attentionIndustry,
				goodatJob : goodatJob,
				companyName : companyName
			},
			success : function(data) {
				if (data.result == "SUCCESS") {
					window.location.href = ctx + "/partner/partnerElite/credit/register/view";
				} else {
					showError();
					$(".error-tip").text(data.msg);
				}
			}
		});
		
		function showError(){
			$(".error_div").show();
			$("body").bind("mousedown",function(){
				$(".error_div").hide();
				$("body").unbind("mousedown");
			});
		}
		
		
	})
	// 从业年限
	$("#workYear,#workyear-triangle,#work").click(function() {
		$("#workYear-select").fadeIn();
		$("#workYear-select li").click(function() {
			var thiss = $(this);
			$("#workYear").text(thiss.text());
			$("#workYear").attr("data", thiss.attr("data"))
			$("#workYear-select").fadeOut("fast");
		});
		$("body").bind("mousedown", function(event) {
			$("#workYear-select").fadeOut("fast");
			$("body").unbind("mousedown");
		});
	});
	// 关注行业
	$("#workProfession,#workProfession-triangle,#dustryinputid").click(function() {
		$("#industry-select").fadeIn();
		$("#industry-select li").click(function() {
			var thiss = $(this);
			$("#workProfession").text(thiss.text());
			$("#workProfession").attr("data", thiss.attr("data"))
			$("#industry-select").fadeOut("fast");
		});
		$("body").bind("mousedown", function(event) {
			$("#industry-select").fadeOut("fast");
			$("body").unbind("mousedown");
		});
	});

	// 擅长职位
	$("#workTitle,#work-triangle,#birth").click(function() {
		$("#work-select").fadeIn();
		$("#work-select li").click(function() {
			var thiss = $(this);
			$("#workTitle").text(thiss.text());
			$("#workTitle").attr("data", thiss.attr("data"))
			$("#work-select").fadeOut("fast");
		});
		$("body").bind("mousedown", function(event) {
			$("#work-select").fadeOut("fast");
			$("body").unbind("mousedown");
		});
	});

});
