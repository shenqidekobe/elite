var auditlist = {

	showAuditForm : function() {
		var id = $(this).attr("data");
		$('#auditId').val(id);
		jsonAjax.post({
			url : ctx + '/member/company/audit/view',
			data : {
				id : id
			},
			success : function(data) {
				$("#auditData").html(data);
				$('#myModel').modal('show');
				$('#refuseAudit').click(auditlist.refuseAudit);
				$('#passAudit').click(auditlist.passAudit);
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
	aduitSubmit : function() {
		if ($("#auditReasonId").val() == ""
				|| null == $("#auditReasonId").val()) {
			$.tips({
				content : "请输入审核原因"
			});
			return;
		}
		jsonAjax.post({
			url : ctx + '/member/company/audit',
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