var calibrationlist = {
	showForm:function(){
		var id = $(this).attr("data");
		jsonAjax.post({
			url : ctx + '/project/tender/calibration/view',
			data : {
				id : id,
			},
			success : function(data) {
				$("#data").html(data);
				$('#myModel').modal('show');
				$('#submitBtn').click(calibrationlist.submit);
			}
		})
	},
	submit:function(){
	$.confirm({
		title : "提示",
		content : "您确认要定标吗？",
		iconSmall : "icon-warning-sign",
		callback : calibrationlist.submitForm
	});
	},
	submitForm:function()
	{
		var id=$("#recordId").val();
		jsonAjax.post({
			url : ctx + '/project/tender/save',
			data : $("#form").serialize(),
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					$('#myModel').modal('hide');
					list.search();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		})
	}
};