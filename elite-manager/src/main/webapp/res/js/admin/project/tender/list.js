$(function() {
	list.init();
});
var list = {
	init : function() {
		list.search();
		$("#search").click(function() {
			// 搜索默认第一页
			$("#pager_pageth").val(1);
			list.search()
		});
		$("#back").click(list.back);
		$("#show_tenderdetail").click(list.showTenderDetail);
		$("#show_recommend").click(recommendlist.showForm);
		// 重新发招标书
		$("a[id=send_tender]").click(list.showTenderPage);
		// 关闭本次招标书
		$("#remove_tender").click(list.showRemoveTender);
		if (tenderoverTime != null && tenderoverTime != "") {
			if ((new Date(tenderoverTime)) - (new Date()) <= 0) {
				$("#countdownId").html(
						"招标已于" + tenderoverTime.split(" ")[0] + "结束");
			} else {
				list.timer();
			}

		} else {
			$("#countdownId").html("还未发过招标书");
			$("#show_tenderdetail").hide();
			$("#show_recommend").hide();
			$("#remove_tender").hide();
			$("#send_tender").hide();
		}

	},
	search : function() {
		Ajax.post({
			url : ctx + '/project/pm/tender/listDate',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id=show_detail]").click(list.detail);
				$("a[id=showWorkLogs]").click(list.showWorkLogs);

				// 定标
				$("a[id=show_calibration]").click(list.calibration);
				// 取消定标
				$("a[id=cancelshow_calibration]").click(list.recalibration);

				// 发立项书，查看立项书 给cto
				$("a[id=add_define]").click(list.addfine);
				// 查看立项书
				$("a[id=search_define]").click(list.searchfine);
				//撤回定标书
				$("a[id=remove_define]").click(list.removeDefineFun);

			}
		})
	},
	back : function() {
		window.location.href = ctx + '/project/pm'
	},
	detail : function() {
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/tender/list/detail")
				.submit();
	},
	showWorkLogs : function() {
		var memberId = $(this).attr("data");
		if (null != memberId && "" != memberId) {
			$("#workRecordForeignid").val(memberId);
		} else {
			memberId = $("#workRecordForeignid").val();
		}
		$('#myModel').modal('show');
		jsonAjax.post({
			url : ctx + '/work/listData',
			data : {
				foreignId : memberId,
				type : "partnerElite"
			},
			success : function(data) {
				if ("EXCEPTION" == data.code) {
					$.tips({
						content : data.msg
					});
				} else {

					$('#data').html(data);
					$('#myModel').modal('show');

					$('#removeWorkRecord').click(list.removeWorkRecord);
					$("#workRecordForeignid").val(memberId);
				}

			},
			error : function(data) {
				$('#removeWorkRecord').click(list.removeWorkRecord);
				$.tips({
					content : data.msg
				});
			},
		})

	},
	addWorkRecord : function() {
		$('#workRecordType').val('partnerElite');
		jsonAjax.post({
			url : ctx + '/work/save',
			data : $("#workRecordForm").serialize(),
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					list.showWorkLogs();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		})
	},
	removeWorkRecord : function(id) {
		jsonAjax.post({
			url : ctx + '/work/remove',
			data : {
				id : id
			},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					list.showWorkLogs();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		})
	},
	showTenderDetail : function() {
		var id = $("#projectId").val();
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/tender/findeview")
				.submit();
	},
	addfine : function() {
		var id = $(this).attr("data");
		var projectId = $("#projectId").val();
		$("#detailFormInputId").val(projectId);
		$("#projectTenderRecordId").val(id);
		$('#detailForm').attr('action', ctx + "/project/pm/adddefine/view")
				.submit();
	}
	// 定标
	,
	calibration : function() {
		var id = $(this).attr("data");
		var projectId = $("#projectId").val();
		$.confirm({
			title : "提示",
			content : "您确认要定标吗？",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/project/tender/settender',
					data : {
						recordId : id
					},
					success : function(data) {
						if (BASE.JS_RESULT.SUCCESS == data.result) {
							list.search();
						} else {
							$.tips({
								content : data.msg
							});
						}
					}
				})
			}
		});
	},
	// 取消定标
	
	recalibration : function() {
		var id = $(this).attr("data");
		var projectId = $("#projectId").val();
		$.confirm({
			title : "提示",
			content : "您确认要取消定标吗？",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/project/tender/removetender',
					data : {
						recordId : id
					},
					success : function(data) {
						if (BASE.JS_RESULT.SUCCESS == data.result) {
							list.search();
						} else {
							$.tips({
								content : data.msg
							});
						}
					}
				})
			}
		});
	},
	showRemoveTender : function() {
		var wined=$("#tenderWinedId").val();
		if(wined=="true"){
			$.tips({
				content : "已经定标，无法取消本次招标"
			});
			return;
		}
		var id = $(this).attr("data");
		jsonAjax.post({
			url : ctx + '/project/tender/view/remove',
			data : {
				id : id
			},
			success : function(data) {
				$('#data').html(data);
				$('#myModel').modal('show');
				$("#removeTenderBtn").click(list.removeTenderFun)
			}
		})
	},
	removeTenderFun : function() {
		var id = $("#removeTenderId").val();
		var reason = $("#reasonId").val();
		if (reason == "" || reason == null) {
			$.tips({
				content : "请输入取消原因"
			});
			return;
		}
		$.confirm({
			title : "提示",
			content : "您确认要取消本次招标吗？",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/project/tender/remove',
					data : {
						id : id,
						reason : reason
					},
					success : function(data) {
						if (BASE.JS_RESULT.SUCCESS == data.result) {
							$('#myModel').modal('hide');
							window.location.reload();
						} else {
							$.tips({
								content : data.msg
							});
						}
					}
				})
			}
		});
	},
	searchfine : function() {
		var id = $(this).attr("data");
		var projectId = $("#projectId").val();
		$("#detailFormInputId").val(projectId);
		$("#projectTenderRecordId").val(id);
		$('#detailForm').attr('action', ctx + "/project/pm/finddefine/view")
				.submit();
	},
	showTenderPage : function() {
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/pm/tender/view")
				.submit();
	},
	removeDefineFun:function(){
		var tenderIdRecordId = $(this).attr("data");
		var projectId=$("#projectId").val();
		$.confirm({
			title : "提示",
			content : "您确认要撤回定标书吗？",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/project/pm/define/remove',
					data : {tenderRecordId:tenderIdRecordId,
						projectId:projectId
					},
					success : function(data) {
						if (BASE.JS_RESULT.SUCCESS == data.result) {
							list.search();
						} else {
							$.tips({
								content : data.msg
							});
						}
					}
				})
			}
		});
	},
	// 倒计时显示
	timer : function() {

		var ts = (new Date(tenderoverTime)) - (new Date());// 计算剩余的毫秒数
		var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);// 计算剩余的天数
		var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);// 计算剩余的小时数
		var mm = parseInt(ts / 1000 / 60 % 60, 10);// 计算剩余的分钟数
		var ss = parseInt(ts / 1000 % 60, 10);// 计算剩余的秒数
		dd = list.checkTime(dd);
		hh = list.checkTime(hh);
		mm = list.checkTime(mm);
		ss = list.checkTime(ss);
		$("#countdownId").html(
				"招标截止时间:" + dd + "天"
				+ hh + "时" + mm + "分");
		setInterval(list.timer, 60000);
	},
	checkTime : function(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	}

};