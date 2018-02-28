$(function() {
	detail.init()
});
var detail = {
	init : function() {
		$("#backBtn").click(detail.back)
		$("#recordProject").click(detail.recordProjectView);
		$("#recordPartner").click(detail.recordPartnerView);
		$("#income").click(detail.incomeView);
		$("#aduitBtn").click(aduitlist.showForm);
		$("#audit_agree").click(detail.auditAgree);
		$("#audit_reject").click(detail.auditReject);
		$("a[id=aduit]").click(aduitlist.showForm);
		$("a[id=allot]").click(detail.allotBm);
		$("a[id=addRemarks]").click(remarkslist.showForm);
	},
	back : function() {
		window.location.href = ctx + '/channel/project/list'
	},
	recordProjectView : function() {
		$("#pager_pageth").val(1);
		$("#status").val("registered");
		jsonAjax.post({
			url : ctx + '/channel/company/project/listData',
			data : $("#projectListForm").serialize(),
			success : function(data) {
				$("#projectListData").html(data);
				$("#partnerListData").html("");
				list.init();
			}
		})
	},
	recordPartnerView : function() {
		$("#pager_pageth").val(1);
		jsonAjax.post({
			url : ctx + '/channel/company/partner/listData',
			data : $("#partnerListForm").serialize(),
			success : function(data) {
				$("#projectListData").html("");
				$("#partnerListData").html(data);
				list.init();
			}
		})
	},
	incomeView : function() {
		jsonAjax.post({
			url : ctx + '/channel/company/income',
			data : $("#incomeForm").serialize(),
			success : function(data) {
				$("#incomeData").html(data);
			}
		})
	},
	allotBm : function() {
	    var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/channel/company/allot/view").submit();
	},
	showProjectInfo:function(){
		var info=$(this).attr("data");
		var projectInfoPage=
			"<div class='modal-content' style='margin-left: 550px;margin-right: 400px; width:700px; margin-top:50px;'>"
	        +"<div class='modal-header'>"
	        +"<button type='button' class='close' data-dismiss='modal' aria-label='Close'>"
	        +" <span aria-hidden='true'>×</span>"
	        +"</button>"
	        +"<h4 class='modal-title' id='myModalLabel'>项目详情"
	        +"</h4>"
	    	+" </div>"
		    +" <div class='modal-body'>"
		    +" <div>"+info+"</div>"
		    +" </div>"
		    +" </div>"
		 
			
		$('#workLogsModel').html(projectInfoPage);
		$('#workLogsModel').modal('show');
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
				type : "partnerCompany_project"
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
		$('#workRecordType').val('partnerCompany_project');
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