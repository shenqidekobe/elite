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

	// 提现
	$("#withdrawBtn").click(withdraw);

	// 结算记录查询
	$("#incomeSearchBtn,#incomeSearchBtnIcon").click(imcomeListDate)

	// 选择icome搜索状态
	$("#ownselectspan").click(function() {
		var data = $("#incomeOwnSearchTypeId").val();
		if (data == "" || data == null) {
			$("#incomeOwnSearchTypeId").val($(this).attr("data"));
			$(this).attr("class", "li_icon check")
		} else {
			$("#incomeOwnSearchTypeId").val("");
			$(this).attr("class", "li_icon nocheck")
		}
		imcomeListDate();
	})
	$("#directselectspan").click(function() {
		var data = $("#incomeDirectSearchTypeId").val();
		if (data == "" || data == null) {
			$("#incomeDirectSearchTypeId").val($(this).attr("data"));
			$(this).attr("class", "li_icon check")
		} else {
			$("#incomeDirectSearchTypeId").val("");
			$(this).attr("class", "li_icon nocheck")
		}
		imcomeListDate();
	})
	$("#indirectselectspan").click(function() {
		var data = $("#incomeIndirectSearchTypeId").val();
		if (data == "" || data == null) {
			$("#incomeIndirectSearchTypeId").val($(this).attr("data"));
			$(this).attr("class", "li_icon check")
		} else {
			$("#incomeIndirectSearchTypeId").val("");
			$(this).attr("class", "li_icon nocheck")
		}
		imcomeListDate();
	})
	$("#revenueDetailBtn").click(function() {
		initPigChart();
		partnerEarnListDate();
	})
	// 渠道本身收益查询
	partnerEarnListDate()
	// 收益记录
	imcomeListDate();

	$("#partnerTypeSelId li").click(function() {
		var data = $(this).attr("data");
		$("#searchTypeId").val(data);
		partnerEarnListDate();
	})
	initPigChart();

}
// 提现申请
function withdraw() {
	// 验证其余额是否达到提现标准
	var balance = $("#withdrawAccount").val();
	var isCard = $("#iscardId").val();
	if (parseFloat(balance) < 100) {
		tostHint("余额未达到提现标准", "余额还不够哦，目前还不能提前，点击确认可返回查看");
	} else if (!isCard) {
		tostHint("提现失败", "身份证未认证通过，不能提现");
	} else {
		window.location.href = ctx + '/member/withdraw?' + jsonAjax.random();
	}
}

// 直接收益，本身收益，间接收益查询
function partnerEarnListDate() {
	var type = $("#searchTypeId").val();
	jsonAjax
			.post({
				url : ctx + "/partner/partnerCompany/revenueDetail/" + type
						+ "/listData",
				data : $("#detail_form").serialize(),
				success : function(data) {
					initPigChart();
					$("#earnListData").html(data);
					// 分页组件js
					var myIncome = $("#myIncomeId").val();
					$("#channelMoneyId").html("￥" + myIncome)
					$("#partnerEarnId").html("￥" + myIncome);
					$("#partnerProportionId").html($("#proportionId").val())
					if (type == "partner_own") {
						$("#channelTitleId").html("我推荐的项目收益:");
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
					$("a[id='showProjectListId']").click(function() {
						var partnerId = $(this).attr("data")
						var type = $(this).attr("type");
						projectListDate(partnerId, type);
					})
				}
			})
}
function projectListDate(id, incomeType) {
	jsonAjax.post({
		url : ctx + "/partner/partnerCompany/revenue/project/listData",
		data : {
			partnerId : id,
			incomeType : incomeType
		},
		success : function(data) {
			$("#projectListData").html(data);
			// 分页组件js
			initPageFun()
		}
	})

}
function imcomeListDate() {
	jsonAjax.post({
		url : ctx + "/partner/partnerCompany/income/listData",
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
			initPageFun()

		}
	})
}
// 分页组件js
function initPageFun() {
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

						var json = data.object
						var objJson = JSON.parse(json);
						var own = objJson.项目收益
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
												pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
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
														format : '<b>{point.name}</b>: {point.percentage:.1f}%'
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