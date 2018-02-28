function initPersonActive() {
	$('#startTimeId').datepicker({
		autoclose : true
	}).on("changeDate", function(e) {
		var time = e.date;
		startTime = time.getTime();
		$("#startTimeId").val(time.Format("yyyy-MM-dd"));
	});
	$('#endTimeId').datepicker({
		autoclose : true
	}).on("changeDate", function(e) {
		var time = e.date;
		var endTime = time.getTime();
		if (startTime != "" && endTime < startTime) {
			tostHint("时间错误", "结束时间不能早于开始日期");
			$("#endTimeId").val("");
			return;
		} else {
			$("#endTimeId").val(time.Format("yyyy-MM-dd"));
		}
	});
	var startTime = $("#startTimeId").val();
	$('#startTime-date-icon').datepicker({
		autoclose : true
	}).on("changeDate", function(e) {
		var time = e.date;
		startTime = time.getTime();
		$("#startTimeId").val(time.Format("yyyy-MM-dd"));
	});
	$('#endTime-date-icon').datepicker({
		autoclose : true
	}).on("changeDate", function(e) {
		var time = e.date;
		var endTime = time.getTime();
		if (startTime != "" && endTime < startTime) {
			tostHint("时间错误", "结束时间不能早于开始日期");
			return;
		} else {
			$("#endTimeId").val(time.Format("yyyy-MM-dd"));
		}
	});
	
	//我推荐的精英，点击切换
	
	$("#selectStatusPane li").click(function() {
		$(this).addClass("active_bg").siblings().removeClass("active_bg");
		var searchType = $(this).attr("data");
		$("#searchTypeId").val(searchType);
		var index = parseInt($(this).attr("index"));
		if (index == 0) {
			
		}
		if (index == 1) {
			
		}
		if (index == 2) {
			
		}
		if (index == 3) {
			
		}
		
		$("#pager_pageth").val(1);
		selectPersonStatusPaneFun();
	})
}
function selectPersonStatusPaneFun() {
	jsonAjax.post({
		url : ctx + "/partner/partnerElite/personActive/listData",
		data : $("#searchForm").serialize(),
		success : function(data) {
			$("#datalist").html(data);
			// 分页组件js
			$(".pagination #prev_pager").click(function() {
				var currentPageth = $("#pager_pageth").val();
				if (currentPageth - 0 <= 1) {
					return;
				}
				$("#pager_pageth").val(currentPageth - 1);
				selectPersonStatusPaneFun();
			});
			$(".pagination #jump_pager").click(function() {
				var pageth = $(this).attr("data");
				$("#pager_pageth").val(pageth - 0);
				selectPersonStatusPaneFun();
			});
			$(".pagination #next_pager").click(function() {
				var currentPageth = $("#pager_pageth").val();
				if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
					return;
				}
				console.info($("#pager_pageth").val());
				$("#pager_pageth").val(currentPageth - 0 + 1);
				selectPersonStatusPaneFun();
			});
		}
	});
}
// 修改基本信息
function searchInviteListDate() {

}