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
			url : ctx + '/channel/company/allot/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id=allot]").click(list.allot);
			}
		})
	},
	allot:function(){
		var memberId=$("#memberId").val();
		var chargeId=$("#chargeId").val();
		$("#name").val($(this).attr("value"));
		var content ="您确认要分配";
		if(chargeId!=''){
			content="您确认要重新分配"
		}
		var id = $(this).attr("data");
		$("#chargeId").val(id);
		$.confirm({
			title : "提示",
			content : content,
			iconSmall : "icon-warning-sign",
			callback : list.submit
		});
	}
	,submit:function(){
		var memberId=$("#memberId").val();
		var chargeId=$("#chargeId").val();
		var name=$("#name").val();
		jsonAjax.post({
			url : ctx + '/channel/company/allot/update',
			data : {memberId:memberId,chargeId:chargeId},
			success : function(data) {
				if(data.result=='SUCCESS'){
					$.tips({
						content : "分配成功！"
					});
					$("#show_result").text("已经分配："+name);
				}else{
					$.tips({
						content : "分配失败！"
					});
				}
			}
		})
	}
};