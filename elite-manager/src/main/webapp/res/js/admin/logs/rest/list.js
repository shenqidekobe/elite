$(function() {
	list.init();
});
var list = {
	init : function() {
		list.search();
		$("#search").click(function(){
			//搜索默认第一页
			$("#pager_pageth").val(1);
			list.search()
		});
		list.dateTimePicker();
	},
	search : function() {
		var startData = $("#startTimeId").val();
		var endData = $("#endTimeId").val();
		new Date(startData.replace(/-/g, "-"));
		if (startData != "") {
			if (new Date() < Date.parse(new Date(startData.replace(/-/g, "-")))) {
				$.tips({
					content : "开始时间不能大于当前时间!"
				});
				return;
			}
		}
		if (endData != "") {
			if (new Date() < Date.parse(new Date(endData.replace(/-/g, "-")))) {
				$.tips({
					content : "结束时间不能大于当前时间!"
				});
				return;
			}
		}
		if (startData != "" && endData != "") {
			if (Date.parse(new Date(startData.replace(/-/g, "-"))) > Date
					.parse(new Date(endData.replace(/-/g, "-")))) {
				$.tips({
					content : "开始时间不能大于结束时间!"
				});
				return;
			}
		}

		jsonAjax.post({
			url : ctx + '/logs/sysRestLogs/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
			}
		})
	},
	viewDetail : function(id) {
		jsonAjax.post({
			url : ctx + '/logs/sysRestLogs/view',
			data : {
				id : id
			},
			success : function(data) {
				var rsp = data;
				if (rsp.id) {
					$('#myModel').modal('show');
					$('#detailuserId').html(rsp.userId);
					$('#detailoperType').html(rsp.operType);
					$('#classImpl').html(rsp.classImpl);
					$('#reqMethod').html(rsp.reqMethod);
					$('#reqParam').html(rsp.reqParam);
					$('#rsqParam').html(rsp.rsqParam);
					$('#detailreqIp').html(rsp.reqIp);
					$('#detailresult').html(rsp.result);
					if (null != rsp.sysUser) {
						$('#detailLoginName').html(rsp.sysUser.loginName);
					} else {
						$('#detailLoginName').html("");
					}
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		});
	},
	dateTimePicker : function() {
		$('#startTimeId').datepicker({
			language : 'zh-CN',
			autoclose : true,
			format : "yyyy-MM-dd",
			todayHighlight : true,
		})
		$('#endTimeId').datepicker({
			language : 'zh-CN',
			autoclose : true,
			format : "yyyy-MM-dd",
			todayHighlight : true
		})
	}

};