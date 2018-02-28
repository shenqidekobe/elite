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
		$("#logsSel").click(list.logsSearch);
		$("#loginLogsSel").click(list.loginSearch);
		list.dateTimePicker();
	},
	search : function() {
		var type = $('#selectType').val()
		if (null == type || "" == type || "logs" == type) {
			list.logsSearch(false);
		} else {
			list.loginSearch(false);
		}
	},
	logsSearch : function(resetPageth) {
		if (resetPageth) {
			$("#pager_pageth").val(1);
		}
         
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
		
		
		$('#selectType').val("logs");
		jsonAjax.post({
			url : ctx + '/logs/memberLogs/ListData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
			}
		})
	},

	viewDetail : function(id) {
		jsonAjax.post({
			url : ctx + '/logs/memberLogs/view',
			data : {
				id : id
			},
			success : function(data) {
				var rsp = data;
				if (rsp.id) {
					$('#classImpl').show();
					$('#reqMethod').show();
					$('#rspParam').show();
					$('#myModel').modal('show');

					$('#detailMemberName').html(rsp.memberId);
					$('#detailOperType').html(rsp.operType);
					$('#detailReqIp').html(rsp.reqIp);
					$('#detailResult').html(rsp.result);
					$('#detailReqParam').html(rsp.reqParam);
					$('#detailclassImpl').html(rsp.classImpl);
					$('#detailreqMethod').html(rsp.reqMethod);
					$('#detailrspParam').html(rsp.rspParam);
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
	loginSearch : function(resetPageth) {
		if (resetPageth) {
			$("#pager_pageth").val(1);
		}
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
		
		$('#selectType').val("loginLogs");
		var data = $("#startTimeId").val();
		jsonAjax.post({
			url : ctx + '/logs/memberLoginLogs/ListData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
			}

		})
	},
	loginviewDetail : function(id) {
		jsonAjax.post({
			url : ctx + '/logs/memberLoginLogs/view',
			data : {
				id : id
			},
			success : function(data) {
				var rsp =data;
				if (rsp.id) {
					$('#classImpl').hide();
					$('#reqMethod').hide();
					$('#rspParam').hide();

					$('#myModel').modal('show');
					$('#detailMemberName').html(rsp.memberId);
					$('#detailOperType').html(rsp.operType);
					$('#detailReqIp').html(rsp.loginIp);
					$('#detailResult').html(rsp.result);
					$('#detailReqParam').html(rsp.reqParam);
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
			format : "yyyy-MM-dd",
			autoclose : true,
			todayHighlight : true
		})
	}

};