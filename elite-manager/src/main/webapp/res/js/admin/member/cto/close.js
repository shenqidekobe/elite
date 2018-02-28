var closelist = {
	showCreateCloseForm : function() {
		var id = $(this).attr("data");
		closelist.showForm(id,"create");
	},
	showStopCloseForm : function() {
		var id = $(this).attr("data");
		closelist.showForm(id,"stop");
	},
	showForm : function(id,type) {
	
		jsonAjax.post({
			url : ctx + '/member/cto/closeview',
			data : {
				memberId : id,
				closetype:"closeMember"
			},
			success : function(data) {
				$("#auditData").html(data);
				$('#myModel').modal('show');
				$('#createCloseBtn').click(closelist.aduitSubmit);
				$('#closeId').val(id);
			
				if(type=="create"){
					$('#titleId').html("关停人员")
					$('#controltitleId').html("您将要关停此用户")
					$('#stoptime').hide()
					$('#closeTime').show()
					$("#closeStatusId").val("disabled")
					
				}else{
					$('#titleId').html("结束关停")
					$('#controltitleId').html("您将要提前结束此用户关停")
					$('#stoptime').show()
					$('#closeTime').hide()
					$("#closeStatusId").val("normal")
				}
			}
		})
	},

	aduitSubmit : function() {
		var closeTime=$("#closeTime").val();
		var closeType = $("#closeStatusId").val();
		if (closeTime == "" || closeTime == null) {
			$("#closeTime").val(0)
		}

		if ($("#closeReasonId").val() == ""
				|| null == $("#closeReasonId").val()) {
			$.tips({
				content : "请输入关停原因"
			});
			return;
		} else if (closeType == "disabled") {
			if ("" == closeTime || null == closeTime) {
				$.tips({
					content : "请输入关停时间"
				});
				return;
			} else if (isNaN(closeTime)) {
				$.tips({
					content : "请输入数字"
				});
				return;

			}
		}
		jsonAjax.post({
			url : ctx + '/member/cto/updateclose',
			data : $("#closeForm").serialize(),
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