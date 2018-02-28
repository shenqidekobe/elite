var businesslist = {
	showForm : function() {
		var id = $(this).attr("data");
		if(id==null){
			id=$("#projectIdInputId").val();
		}
		jsonAjax.post({
			url : ctx + '/project/bm/business/view',
			data : {
				projectId : id,
			},
			success : function(data) {
				$("#auditData").html(data);
				$('#myModel').modal('show');
				$("a[id=selectWayId]").click(businesslist.selectWay);
				$("button[id=remove_business]").click(businesslist.remove);
				$("#business_submit").click(businesslist.submitForm);
				$("#projectIdInputId").val(id);
				$("button[id='eliteWorkRecord']").click(
						businesslist.eliteWorkRecord)
				$("button[id='updateWorkRecord']").click(
						businesslist.updateWorkRecord)

			}
		})
	},
	eliteWorkRecord : function() {
		var content = $(this).parent().next();
		$(this).next().toggle();
		content.toggle();
		content.next().toggle();
		if ($(this).html() == "编辑") {
			$(this).html("取消")
		} else if ($(this).html() == "取消") {
			$(this).html("编辑")
		}
	},
	updateWorkRecord:function(){
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
			url : ctx + '/project/business/update',
			data : {
				id : id,
				content:contentMsg
				
			},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					businesslist.showForm();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}

		})
	},
	selectWay : function() {
		var data = $(this).attr("data");
        $("#showWayId").html(data);
        $("#wayInputId").val(data);
	},
	submitForm:function(){
		var content=$("#content").val();
		if(content==""||content==null){
			$.tips({
				content : "请输入沟通记录"
			});
			return;
		}
		jsonAjax.post({
			url : ctx + '/project/business/save',
			data : $("#businessForm").serialize(),
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					businesslist.showForm();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		})
	},
	remove:function()
	{
		var id = $(this).parent().attr("data");
		jsonAjax.post({
			url : ctx + '/project/business/remove',
			data :{id:id},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					businesslist.showForm();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		})
	}

};