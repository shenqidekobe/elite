var auditlist = {

	showAuditForm : function() {
		var id = $(this).attr("data");
	    if(id=="")
	    	{
	    	$.tips({
				content : "项目方为空"
			});
	    	return;
	    	}
	    	
		jsonAjax.post({
			url : ctx + '/member/elite/auditview',
			data : {
				id : id
			},
			success : function(data) {
				$("#auditData").html(data);
				$('#myModel').modal('show');
				$('#auditId').val(id);
				$('#refuseAudit').click(auditlist.refuseAudit);
				$('#passAudit').click(auditlist.passAudit);
				$("#passEliteAudit").click(auditlist.passEliteAudit);
				$("#passEliteToCTOAgainAudit").click(auditlist.passEliteToCTOAgaginAudit);
			}
		})
	},
	refuseAudit : function() {
		$("#auditStatusId").val("auditNotPass")
		auditlist.aduitSubmit();
	},
	passAudit : function() {
		$("#auditStatusId").val("normal")
		auditlist.aduitSubmit();
	},
	passEliteAudit:function(){
		$("#noCto").val("N")
		$("#auditStatusId").val("normal")
		auditlist.aduitSubmit();
	},
	passEliteToCTOAgaginAudit:function(){
		$("#noCto").val("NAgain")
		$("#auditStatusId").val("normal")
		auditlist.aduitSubmit();
	},
	aduitSubmit : function() {
		if ($("#auditReasonId").val() == ""
				|| null == $("#auditReasonId").val()) {
			$.tips({
				content : "请输入审核原因"
			});
			return;
		}
		jsonAjax.post({
			url : ctx + '/member/elite/audit',
			data : $("#auditForm").serialize(),
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

};