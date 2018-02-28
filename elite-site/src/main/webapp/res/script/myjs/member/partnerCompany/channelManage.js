function initChannelManage() {
	$('#startTimeId,#startTime-date-icon').datepicker({
		autoclose : true
	}).on("changeDate", function(e) {
		var time = e.date;
		startTime = time.getTime();
		$("#startTimeId").val(time.Format("yyyy-MM-dd"));
	});
	var startTime = $("#startTimeId").val();
	$('#endTimeId,#endTime-date-icon').datepicker({
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
	$("#searchChannelBtn").click(function(){
		selectChannelStatusPaneFun();
	})
	selectChannelStatusPaneFun();
	
	$("#selectStatusPane li").click(function() {
		$(this).addClass("active_bg").siblings().removeClass("active_bg");
		var searchType = $(this).attr("data");
		$("#searchTypeId").val(searchType);
		var index = parseInt($(this).attr("index"));
		if (index == 0) {
			var left = 0;
			$(".active-line").css({
				'width' : '90px'
			});
		}
		if (index == 1) {
			var left = 90;
			$(".active-line").css({
				'width' : '150px'
			});
		}
		if (index == 2) {
			var left = 245
			$(".active-line").css({
				'width' : '160px'
			});
		}
		if (index == 3) {
			var left = 410;

			$(".active-line").css({
				'width' : '240px'
			});
		}
		$(".active-line").css({
			'left' : left + 'px'
		});
		$("#pager_pageth").val(1);
		selectChannelStatusPaneFun();
	})
	//初始化调用
}
function selectChannelStatusPaneFun() {
	jsonAjax.post({
		url : ctx + "/partner/partnerCompany/channelManage/listData",
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
				selectChannelStatusPaneFun();
			});
			$(".pagination #jump_pager").click(function() {
				var pageth = $(this).attr("data");
				$("#pager_pageth").val(pageth - 0);
				selectChannelStatusPaneFun();
			});
			$(".pagination #next_pager").click(function() {
				var currentPageth = $("#pager_pageth").val();
				if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
					return;
				}
				console.info($("#pager_pageth").val());
				$("#pager_pageth").val(currentPageth - 0 + 1);
				selectChannelStatusPaneFun();
			});
		}
	});
}
