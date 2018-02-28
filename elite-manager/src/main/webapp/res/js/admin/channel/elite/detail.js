$(function() {
	list.init();
});
var list = {
	init : function() {
		$("#backBtn").click(list.back)
		$("button[id=search]").click(list.searchElite);
		$("#recordElite").click(list.recordEliteView);
		$("#checkedElite").click(list.checkedEliteView);
		$("#audit_agree").click(list.auditAgree);
		$("#audit_reject").click(list.auditReject);
		$("#aduitBtn").click(aduitlist.showForm);
		$("#income").click(list.incomeView);
		$("a[id=aduit]").click(aduitlist.showForm);
		$("a[id=allot]").click(list.allotPm);
		$("a[id=addRemarks]").click(remarkslist.showForm);
	},
	search:function(){
		list.searchElite();
	},
	searchElite : function() {
		var data = ""; var url=""; var div="";
		if($("#eliteListForm").is(":visible")){
			data=$("#eliteListForm").serialize();
			div=$("#eliteListData");
			url = ctx + '/channel/partnerElite/elite/listData';
		}else if($("#partnerListForm").is(":visible")){
			data=$("#partnerListForm").serialize();
			url =  ctx + '/channel/partnerElite/partner/listData';
			div=$("#partnerListData")
		}
		jsonAjax.post({
			url : url,
			data : data,
			success : function(data) {
				div.html(data);
			}
		})
	},
	back : function() {
		window.location.href = ctx + '/channel/member/list'
	},
	recordEliteView : function() {
		$("#pager_pageth").val(1);
		jsonAjax.post({
			url : ctx + '/channel/partnerElite/elite/listData',
			data : $("#eliteListForm").serialize(),
			success : function(data) {
				$("#eliteListData").html(data);
				$("#partnerListData").html("");
			}
		})
	},
	checkedEliteView : function() {
		$("#pager_pageth").val(1);
		jsonAjax.post({
			url : ctx + '/channel/partnerElite/partner/listData',
			data : $("#partnerListForm").serialize(),
			success : function(data) {
				$("#partnerListData").html(data);
				$("#eliteListData").html("");
			}
		})
	},
	incomeView : function() {
		jsonAjax.post({
			url : ctx + '/channel/elite/income',
			data : $("#incomeForm").serialize(),
			success : function(data) {
				$("#incomeData").html(data);
			}
		})
	},
	allotPm : function() {
	    var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/channel/elite/allot/view").submit();
	},
	showWorkLogs : function() {
		var memberId = $(this).parent().attr("data");
		if (null != memberId && "" != memberId) {
			$("#workRecordForeignid").val(memberId);
		} else {
			memberId = $("#workRecordForeignid").val();
		}
		$('#workLogsModel').modal('show');
		jsonAjax.post({
			url : ctx + '/work/listData',
			data : {
				foreignId : memberId,
				type : "partnerElite_project"
			},
			success : function(data) {
				if ("EXCEPTION" == data.code) {
					$.tips({
						content : data.msg
					});
				} else {

					$('#workLogsModel').html(data);
					$('#workLogsModel').modal('show');

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
		$('#workRecordType').val('partnerElite_project');
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

};