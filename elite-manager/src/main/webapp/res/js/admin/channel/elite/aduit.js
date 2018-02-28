var aduitlist = {

	showForm : function() {
		var id = $(this).attr("data");
		jsonAjax.post({
			url : ctx + '/channel/elite/aduit/view',
			data : {
				memberId : id
			},
			success : function(data) {
				$("#aduitDate").html(data);
				$('#myModel').modal('show');
				$('#refuseAduit').click(aduitlist.refuseAduit);
				$('#passAduit').click(aduitlist.passAduit);
			}
		})
	},
	refuseAduit : function() {
		aduitlist.aduitSubmit("auditNotPass");
	},
	passAduit : function() {
		aduitlist.aduitSubmit("normal");
	},
	aduitSubmit : function(status) {
		var  auditReason=$("#auditReasonId").val();
		var  memberId=$("#aduitMemberId").val();
		if (auditReason=="") {
			$.tips({
				content : "请输入审核原因"
			});
			return;
		}
		jsonAjax.post({
			url : ctx + '/channel/elite/aduit',
			data :{
				memberId:memberId,
				auditReason:auditReason,
				status:status
				
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
};