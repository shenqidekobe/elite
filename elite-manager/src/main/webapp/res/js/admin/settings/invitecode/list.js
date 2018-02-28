$(function() {
	list.init();
});
var list = {
	init : function() {
		list.search();
		$("#createUser").click(list.create);
		$("#search").click(function(){
			//搜索默认第一页
			$("#pager_pageth").val(1);
			list.search()
		});
		$("#addInviteCode").click(list.showAddForm)
		$("#downExcelBtn").click(function(){
					list.downExcelFunc();
				})
	},
	search : function() {
		jsonAjax.post({
			url : ctx + '/settings/invitecode/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
			}
		})
	},
	downExcelFunc:function(){
		jsonAjax.post({
			url : ctx + '/settings/invitecode/listAllData',
			success : function(data) {
				$("#listData").html(data);
			    $('#detailTable').tableExport({type: 'excel', escape: 'false'});
			    $("#search").click();
			}
		})
	},
	create : function() {
		$("#userForm")[0].reset();
		$("#sysUserId").val("");
		$('#myModel').modal('show');
		list.validate();
	},
	showAddForm : function() {
		var id = $(this).attr("data");
		jsonAjax.post({
			url : ctx + '/settings/invitecode/addview',
			success : function(data) {
				$("#showData").html(data);
				$('#myModel').modal('show');
				$("#addInviteCodebtn_submit").click(list.addInviteCodeSubmit)
				
				$('#addsearchType').change(list.myselectChange)
				//时间选择器
				//期望交付时间选择器
				$('#expireTimeId').datepicker({
					language : 'zh-CN',
					autoclose : true,
					format : "yyyy-MM-dd",
					todayHighlight : true,
				})
 			}
		})
	},
	addInviteCodeSubmit:function(){
		var type=$('#addsearchType option:selected').val();
	    var maxCount=$("#maxCountId").val();
	    var expireTime=$("#expireTimeId").val();
		jsonAjax.post({
			url : ctx + '/settings/invitecode/save',
			data :{
				type:type,
				maxCount:maxCount,
				expireTime:expireTime
			},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					list.search();
					$('#myModel').modal('hide');
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		})
	}
	,
	myselectChange:function(){
	var type=$(this).children('option:selected').val();
	if(type=="platform_one"){
		$("#maxCountId").val(1);
		$("#maxCountDivId").hide()
		
	}
	else if(type=="platform_infinite"){
		$("#maxCountId").val(0);
		$("#maxCountDivId").hide()
	}else {
		$("#maxCountId").val("");
		 $("#maxCountDivId").show()
	}
	}
	
};