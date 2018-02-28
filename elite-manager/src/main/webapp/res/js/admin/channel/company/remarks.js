var remarkslist = {
	showForm : function() {
		var id = $(this).attr("data");
		if (typeof (id) == "undefined") {
			id = $("#foreignId").val();
		}
		jsonAjax.post({
			url : ctx + '/channel/company/view/remarks',
			data : {id : id},
			success : function(data) {
				$("#showDate").html(data);
				$('#remarkModel').modal('show');
				$("#addWorkRecord").click(remarkslist.addWorkRecord);
				$("button[id='removeWorkRecord']").click(
						remarkslist.removeWorkRecord)
				$("button[id='eliteWorkRecord']").click(
						remarkslist.eliteWorkRecord)
				$("button[id='updateWorkRecord']").click(
						remarkslist.updateWorkRecord)
				$("#cancelBtn").click(function() {
					$('#remarkModel').modal('hide');
				})
			}
		})
	},
	addWorkRecord : function() {
		var content = $("#workRecordContent").val().trim();
		if (content == "") {
			$.tips({
				content : "请输入备注内容"
			});
			return;
		}else if(content.length>200){
			$.tips({
				content : "输入的备注内容在200字以内"
			});
			return;
		}
		jsonAjax.post({
			url : ctx + '/work/save',
			data : $("#workRecordForm").serialize(),
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					remarkslist.showForm();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}

		})
	},
	removeWorkRecord : function() {
		var id = $(this).parent().attr("data");
		jsonAjax.post({
			url : ctx + '/work/remove',
			data : {
				id : id
			},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					remarkslist.showForm();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}

		})
	},
	eliteWorkRecord : function() {
		var content = $(this).parent().next();
		$(this).next().toggle();
		content.toggle();
		content.next().toggle();
		content.next().val(content.html());
		if ($(this).html() == "编辑") {
			$(this).html("取消")
		} else if ($(this).html() == "取消") {
			$(this).html("编辑")
		}
	},
	updateWorkRecord : function() {
		var id = $(this).parent().attr("data");
		var contentMsg = $(this).parent().next().next().val();
		if (contentMsg == "") {
			$.tips({
				content : "备注内容不能为空"
			});
			return;
		}else if(contentMsg.length>200){
			$.tips({
				content : "请输入的备注内容在200字以内"
			});
			return;
		}
		jsonAjax.post({
			url : ctx + '/work/update',
			data : {
				id : id,
				content:contentMsg
				
			},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					remarkslist.showForm();
					$(this).parent().next().next().val("");
				} else {
					$.tips({
						content : data.msg
					});
				}
			}

		})

	}

};