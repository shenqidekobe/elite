function initChampionsActive() {
	$('#startTimeId,#startTimeIconId').datepicker({
		autoclose : true
	}).on("changeDate", function(e) {
		var time = e.date;
		startTime = time.getTime();
		var endTime=$("#endTimeId").val();
		if(endTime != "" && endTime	 < time.Format("yyyy-MM-dd"))
		{
			tostHint("时间错误", "开始日期不能晚于结束时间");
			$("#startTimeId").val("");
			return;
		}else{
		$("#startTimeId").val(time.Format("yyyy-MM-dd"));
		}
	});
	$('#endTimeId,#endTimeIconId').datepicker({
		autoclose : true
	}).on("changeDate", function(e) {
		var time = e.date;
		var endTime = time.getTime();
		var startTime = $("#startTimeId").val();
		if (startTime != "" && time.Format("yyyy-MM-dd") < startTime) {
			tostHint("时间错误", "结束时间不能早于开始日期");
			$("#endTimeId").val("");
			return;
		} else {
			$("#endTimeId").val(time.Format("yyyy-MM-dd"));
		}
	});
	// 搜索
	$("#searChampions").click(selectChampionsStatusPaneFun)

	$("#selectChampionsPan li").click(function() {
		$(this).addClass("active_color")
		$(this).siblings().removeClass("active_color");
		var searchType = $(this).attr("data");
		$("#searchTypeId").val(searchType);
		if (searchType == "myTeam") {
			$("#title_line").animate({
				left : '0px'
			}, 300)
			$("#myTeam").show();
			$("#myParentTeam").hide();
		} else {
			$("#myTeam").hide();
			$("#myParentTeam").show();
			$("#title_line").animate({
				left : '260px'
			}, 300)
		}
		selectChampionsStatusPaneFun()
	})
}
function selectChampionsStatusPaneFun() {

	var startTime = $("#startTimeId").val();
	var endTime = $("#endTimeId").val();
	if (startTime != "" && endTime != null) {
		startTime = startTime.replace(/-/g, ".");
		endTime = endTime.replace(/-/g, ".");
		$("#showTime").html(startTime + "-" + endTime);
	}
	jsonAjax.post({
		url : ctx + "/partner/partnerCompany/champions/listData",
		data : $("#searchForm").serialize(),
		success : function(data) {
			$("#datalist").html(data);

			var searchType = $("#searchTypeId").val();
			if (searchType == "myParentTeam") {
				$("#teamTitleId").html($("#parentNameId").val() + "团队龙虎榜")
				var myRanking = $("#rankingId").val();
				$("#myRankingId").html(myRanking);
			} else {
				var myincome = $("#listmyIncomeId").val();
				$("#myIncomeId").html(myincome + "元");
				$("#teamTitleId").html("我的团队龙虎榜");
			}
			// 分页组件js
			$(".pagination #prev_pager").click(function() {
				var currentPageth = $("#pager_pageth").val();
				if (currentPageth - 0 <= 1) {
					return;
				}
				$("#pager_pageth").val(currentPageth - 1);
				selectChampionsStatusPaneFun();
			});
			$(".pagination #jump_pager").click(function() {
				var pageth = $(this).attr("data");
				$("#pager_pageth").val(pageth - 0);
				selectChampionsStatusPaneFun();
			});
			$(".pagination #next_pager").click(function() {
				var currentPageth = $("#pager_pageth").val();
				if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
					return;
				}
				console.info($("#pager_pageth").val());
				$("#pager_pageth").val(currentPageth - 0 + 1);
				selectChampionsStatusPaneFun();
			});
		}
	});
}
