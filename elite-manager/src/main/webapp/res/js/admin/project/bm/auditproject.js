var auditProjectlist= {

	showForm : function() {
		var id = $(this).attr("data");
		jsonAjax.post({
			url : ctx + '/project/bm/project/auditview',
			data : {
				projectId : id
			},
			success : function(data) {
				$("#showData").html(data);
				$('#myModel').modal('show');
			    $('#btn_submitAudit').click(auditProjectlist.aduitProjectSubmit)
			}
		})
	},
    aduitProjectSubmit:function(){
    	var auditResult=$("#auditReasonId").val();
    	if(auditResult=="")
    		{
    		$.tips({
				content :"请输入审核原因"
			});
    		return;
    		}
    	var projectId=$("#projectId").val();
    	jsonAjax.post({
			url : ctx + '/project/bm/saveAuditProject',
			data : {
				projectId : projectId,
				auditReason:auditResult
			},			success : function(data) {
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
}