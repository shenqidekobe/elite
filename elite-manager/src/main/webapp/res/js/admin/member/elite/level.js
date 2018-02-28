var levellist = {

	showLevelForm : function() {
		var id = $(this).attr("data");

		jsonAjax.post({
			url : ctx + '/member/elite/levelview',
			data : {
				id : id
			},
			success : function(data) {
				$("#auditData").html(data);
				$('#myModel').modal('show');
				$('#levelMemerId').val(id);
				$('#levelUpdate').click(levellist.update);

			}
		})
	},
	update : function() {
		levellist.formSubmit();
	},
	formSubmit : function() {
		var size = $("#jobsSize").val();
		if (size == 0) {
			$.tips({
				content : "请完善您的技能"
			});
			return;
		} else {
			for (var i = 0; i < size; i++) {
				var id = "input[name='jobs[" + i + "].level']";
				var level = $(id).val();
				if ("" == level || null == level) {
					$.tips({
						content : "请输入级别"
					});
					return;
					break;
				} else if (isNaN(level)) {
					$.tips({
						content : "请输入数字"
					});
					return;
					break;
				} else if (level != Math.abs(level)) {
					$.tips({
						content : "请输入正数"
					});
					return;
					break;
				}else if(level>10){
					$.tips({
						content : "级别最高为10"
					});
					return;
				}
			}
		}
		jsonAjax.post({
			url : ctx + '/member/elite/updatelevel',
			data : $("#levelForm").serialize(),
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