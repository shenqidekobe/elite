function initRevenue() {

	// 时间选择器
	var incomeStart = "";
	var incomeEnd = "";
	$('#inComeStartTShow,#inComeStartTicon').datepicker({
		autoclose : true
	}).on("changeDate", function(e) {
		var time = e.date;
		incomeStart = time.getTime();
		if (incomeEnd != "" && incomeEnd < incomeStart) {
			tostHint("时间错误", "开始时间不能晚于结束时间");
			return;
		} else {
			$("#inComeStartTShow").html(time.Format("yyyy-MM-dd"));
			$("#inComeStartTimeValId").val(time.Format("yyyy-MM-dd"));
		}
	});
	$('#inComeEndTShow,#inComeEndTicon').datepicker({
		autoclose : true
	}).on("changeDate", function(e) {
		var time = e.date;
		incomeEnd = time.getTime();
		if (incomeStart != "" && incomeEnd < incomeStart) {
			tostHint("时间错误", "开始时间不能晚于结束时间");
			return;
		} else {
			$("#inComeEndTShow").html(time.Format("yyyy-MM-dd"));
			$("#inComeEndTimeValId").val(time.Format("yyyy-MM-dd"));
		}
	});
	var startTime = "";
	var endTime = "";
	$('#startTimeShow,#startTimeIcon').datepicker({
		autoclose : true
	}).on("changeDate", function(e) {
		var time = e.date;
		startTime = time.getTime();
		if (endTime != "" && endTime < startTime) {
			tostHint("时间错误", "开始时间不能晚于结束时间");
			return;
		} else {
			$("#startTimeShow").html(time.Format("yyyy-MM-dd"));
			$("#startTimeId").val(time.Format("yyyy-MM-dd"));
		}
	});
	$('#endTimeShow,#endTimeIcon').datepicker({
		autoclose : true
	}).on("changeDate", function(e) {
		var time = e.date;
		endTime = time.getTime();
		if (startTime != "" && endTime < startTime) {
			tostHint("时间错误", "结束时间不能早于开始日期");
			return;
		} else {
			$("#endTimeShow").html(time.Format("yyyy-MM-dd"));
			$("#endTimeId").val(time.Format("yyyy-MM-dd"));
		}
	});

	imcomeListDate();
	// 提现
	$("#withdrawBtn").click(withdraw);
	// 结算记录查询
	$("#incomeSearchBtn").click(imcomeListDate)

	// 选择icome搜索状态
	$("span[id='noselectspan']").click(
			function() {
				$(this).attr("class", "li_icon check")
				$(this).parent().siblings().children("#noselectspan").attr(
						"class", "li_icon nocheck")
				$("#incomeSearchTypeId").val($(this).attr("data"));
				imcomeListDate();
			})
	// 结算记录选择
	$("#selectRecordUl li").click(function() {
		$(this).attr("class", "active_color").siblings().attr("class", "");
		var data = $(this).attr("data");
		if (data == "withdraw") {
			$("#incomeSearchBoxId").hide();
			withdrawListDate()
		} else {
			$("#incomeSearchBoxId").show();
			imcomeListDate();
		}
	})
	// 间接 直接渠道查询
	$("#partnerTypeSelId li").click(function() {
		var data = $(this).attr("data");
		$("#searchTypeId").val(data);
		if (data == "partner_own") {
			$("#partnerEliteListData").html("");
		}
		partnerEarnListDate();
	})
	$("#eliteReveueSearchBtn").click(function() {
		partnerEarnListDate();
		initPigChart()
	})
	partnerEarnListDate();
	// initPigChart()
}
// 直接收益，本身收益，间接收益查询
function partnerEarnListDate() {
	var type = $("#searchTypeId").val();
	jsonAjax
			.post({
				url : ctx + "/partner/partnerElite/revenueDetail/" + type + "/listData",
				data : $("#partnerForm").serialize(),
				success : function(data) {
					$("#earnListData").html(data);
					initPigChart();
					// 分页组件js
					var myIncome = $("#myIncomeId").val();
					$("#channelMoneyId").html("￥" + myIncome)
					if (type == "partner_own") {
						$("#channelTitleId").html("我推荐的人才收益:");
						$("#projectListData").html("");
					} else if (type == "partner_direct") {
						$("#channelTitleId").html("直接渠道收益:");
					} else if (type == "partner_indirect") {
						$("#channelTitleId").html("间接渠道收益：")
					}

					// 分页组件js
					$(".pagination #prev_pager")
							.click(
									function() {
										var currentPageth = $(
												"input[id='pager_pageth']:first")
												.val();
										if (currentPageth - 0 <= 1) {
											return;
										}
										$("input[id='pager_pageth']:first")
												.val(currentPageth - 1);
										partnerEarnListDate();
									});
					$(".pagination #jump_pager").click(function() {
						var pageth = $(this).attr("data");
						$("input[id='pager_pageth']:first").val(pageth - 0);
						partnerEarnListDate();
					});
					$(".pagination #next_pager")
							.click(
									function() {
										var currentPageth = $(
												"input[id='pager_pageth']:first")
												.val();
										if (currentPageth - 0 >= $(
												"input[id='pager_pageCount']:first")
												.val() - 0) {
											return;
										}
										$("input[id='pager_pageth']:first")
												.val(currentPageth - 0 + 1);
										partnerEarnListDate();
									});
					// 查看项目列表
					$("a[id='showPartnerEliteListId']").click(function() {
						var partnerId = $(this).attr("data");
						var memberId = $(this).attr("memberId")
						var name = $(this).attr("text");
						var mess = $(this).attr("mess");
						$("#eliteTitleId").html(name + "推荐的人才收益");
						$("#eliteMoneyId").html("￥" + mess);
						partnerEliteListDate(partnerId, memberId);
					})
				}
			})
}
// 提现申请
function withdraw() {
	var balance = $("#withdrawAccount").val();
	// 验证其余额是否达到提现标准
	var isCard = $("#iscardId").val();
	if (parseFloat(balance) < 100) {
		tostHint("余额未达到提现标准", "余额还不够哦，目前还不能提前，点击确认可返回查看");
	} else if (!isCard) {
		tostHint("提现失败", "身份证未认证通过，不能提现");
	} else {
		window.location.href = ctx + '/member/withdraw?' + jsonAjax.random();
	}
}
function imcomeListDate() {
	jsonAjax.post({
		url : ctx + "/partner/partnerElite/income/listData",
		data : $("#incomeForm").serialize(),
		success : function(data) {
			$("#incomeListData").html(data);
			// 分页组件js
			$(".pagination #prev_pager").click(function() {
				var currentPageth = $("input[id='pager_pageth']:last").val();
				if (currentPageth - 0 <= 1) {
					return;
				}
				$("input[id='pager_pageth']:last").val(currentPageth - 1);
				imcomeListDate();
			});
			$(".pagination #jump_pager").click(function() {
				var pageth = $(this).attr("data");
				$("input[id='pager_pageth']:last").val(pageth - 0);
				imcomeListDate();
			});
			$(".pagination #next_pager").click(
					function() {
						var currentPageth = $("input[id='pager_pageth']:last")
								.val();
						if (currentPageth - 0 >= $(
								"input[id='pager_pageCount']:last").val() - 0) {
							return;
						}
						$("input[id='pager_pageth']:last").val(
								currentPageth - 0 + 1);
						imcomeListDate();
					});
		}
	})
}
function withdrawListDate() {
	jsonAjax.post({
		url : ctx + "/partner/withdraw/listData",
		data : $("#incomeForm").serialize(),
		success : function(data) {
			$("#incomeListData").html(data);
			// 分页组件js
			initPagerFun();

		}
	})
}
function partnerEliteListDate(id, memberId) {
	var incomeType = $("#searchTypeId").val();
	jsonAjax.post({
		url : ctx + "/partner/partnerElite/revenue/elite/listData",
		data : {
			partnerId : id,
			memberId : memberId,
			incomeType : incomeType
		},
		success : function(data) {
			$("#partnerEliteListData").html(data);
			// 分页组件js

			initPageFun()
		}
	})

}
function initPagerFun() {
	$(".pagination #prev_pager").click(function() {
		var currentPageth = $("#pager_pageth").val();
		if (currentPageth - 0 <= 1) {
			return;
		}
		$("#pager_pageth").val(currentPageth - 1);
		partnerEarnListDate();
	});
	$(".pagination #jump_pager").click(function() {
		var pageth = $(this).attr("data");
		$("#pager_pageth").val(pageth - 0);
		partnerEarnListDate();
	});
	$(".pagination #next_pager").click(function() {
		var currentPageth = $("#pager_pageth").val();
		if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
			return;
		}
		console.info($("#pager_pageth").val());
		$("#pager_pageth").val(currentPageth - 0 + 1);
		partnerEarnListDate();
	});
}
function initPigChart() {
	var startTime = $("#startTimeId").val();
	var endTime = $("#endTimeId").val();
	var highcharts = require([ 'highcharts' ]);
	jsonAjax
			.post({
				url : ctx + "/partner/partnerCompany/searchPieChart",
				data : {
					startTime : startTime,
					endTime : endTime
				},
				success : function(data) {
					if (data.result == "SUCCESS") {

						// json='{"人才渠道":"4500","直接渠道":"300","间接渠道":"200"}'
						var json = data.object
						var objJson = JSON.parse(json);
						var own = objJson.人才收益
						var direct = objJson.间接渠道
						var indirect = objJson.直接渠道
						if (own == 0 && direct == 0 && indirect == 0) {
							$("#pigChatId").hide()
							$("#pigNodataId").show()
							return false;
						} else {
							$("#pigChatId").show()
							$("#pigNodataId").hide()
						}
						var chartData = [];
						for ( var obj in objJson) {
							chartData.push([ obj, parseFloat(objJson[obj]) ])
						}
						$('#container')
								.highcharts(
										{
											chart : {
												plotBackgroundColor : null,
												plotBorderWidth : null,
												plotShadow : false,
											},
											title : {
												text : ''
											},
											tooltip : {
												pointFormat : '{series.name}: <b>{point.percentage:.1f}</b>'
											},
											colors : [ '#6AD2C5', '#EF8884',
													'#F8C76F' ],
											legend : {
												layout : 'vertical',
												align : 'right',
											},
											plotOptions : {
												pie : {
													allowPointSelect : true,
													cursor : 'pointer',
													dataLabels : {
														enabled : true,
														color : '#000000',
														connectorColor : '#000000',
														format : '<b>{point.name}</b>: {point.percentage:.1f} %'
													},
													showInLegend : true,
													events : {
														click : function(e) {
															console
																	.info(e.point);
															var name = e.point.name;
															var type = "partner_own"
															if (name == "直接渠道") {
																type = "partner_direct"
															} else if (name == "间接渠道") {
																type = "partner_indirect"
															}
															$("#searchTypeId")
																	.val(type);
															partnerEarnListDate()

														}
													}
												}
											},
											exporting : {
												filename : 'answer',
											},
											series : [ {
												type : 'pie',
												name : '占比',
												data : chartData
											} ]
										});

					} else {

					}
				}
			})

}
