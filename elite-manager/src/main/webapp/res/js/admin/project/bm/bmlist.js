$(function() {
	list.init();
});
var list = {
	init : function() {
		list.search();
		$("#search").click(list.search);
	},
	search : function() {
		Ajax.post({
			url : ctx + '/project/bm/bmlist/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id=allot]").click(list.allot);
			}
		})
	},
	allot:function(){
		var userId=$(this).attr("data");
		var userName=$(this).attr("value");
		$("#selectUserId").val(userId);
		$("#selectUserName").val(userName);
		var content ="您确认要分配";
		if($("#show_result").text().indexOf("已经分配")>=0){
			content="您确认要重新分配"
		}
		var id = $(this).parent().attr("data");
		$.confirm({
			title : "提示",
			content : content,
			iconSmall : "icon-warning-sign",
			callback : list.submit
		});
	}
	,submit:function(){
		var userId=$("#selectUserId").val();
		var projectId=$("#projectId").val();
		jsonAjax.post({
			url : ctx + '/project/bm/bmlist/save',
			data : {userId:userId,projectId:projectId},
			success : function(data) {
				$("#show_result").html("已经分配："+$("#selectUserName").val())
			}
		})
	}
};