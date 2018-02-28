$(function() {
	list.init();
});
var list = {
	init : function() {
		list.search();
		$("#search").click(function(){
			//搜索默认第一页
			$("#pager_pageth").val(1);
			list.search()
		});
		$("#inviteCodeBtn").click(list.inviteCodeFun);
		$("#sendCodeBtn").click(list.sendCodeFun);
		$("button[id='eliteType']").click(function(){
			$("#currentTypeId").val($(this).attr("data"));
			$(this).attr("class","btn btn-info").siblings().attr("class","btn btn-default");
			list.search();
		})
	},
	search : function() {
		Ajax.post({
			url : ctx + '/member/invitecode/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("input[id='headSelect']").click(list.select)
				$("input[id='left'],input[id='middle'],input[id='right']").click(list.detailSelect)
				$("#descBtn").click(function(){
					$("#auditTimeOrderType").val("desc");
					list.search();
				})
				$("#ascBtn").click(function(){
					$("#auditTimeOrderType").val("asc");
					list.search();
				})
			}
		})
	},
	select:function(){
		var data=$(this).attr("data");
		var selectId="input[id='"+data+"']";
		if($(this).is(':checked')){
			$(selectId).prop("checked",true);
		}else{
			$(selectId).prop("checked",false)
		}
	     var selectLength=$("input[id='left']:checked,input[id='middle']:checked,input[id='right']:checked").length;
    	 $("#showSelectResult").html("一共选定"+selectLength+"位精英方");
	},
    detailSelect:function(){
    	var selectLength=$("input[id='left']:checked,input[id='middle']:checked,input[id='right']:checked").length;
    	$("#showSelectResult").html("一共选定"+selectLength+"位精英方");
     },
     sendCodeFun:function(){
    	var selectElite= $("input[id='left']:checked,input[id='middle']:checked,input[id='right']:checked")
    	if(selectElite.length<=0){
    		$.tips({
				content : "请选择要发送的精英"
			});
			return;
    	}
    	var ids = new Array();
    	for(var i=0;i<selectElite.length;i++){
    		var elite=selectElite[i];
    		var id=$(elite).attr("data");
    		ids[i]=Number(id);
    	}
    	jsonAjax.post({
			url : ctx + '/member/invitecode/sendCode',
			data : {ids:ids},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					$.tips({
						content : "已经成功发送"
					});
					list.search();
					$("#showSelectResult").html("");
				} else {
					$.tips({
						content : data.msg
					});
				}

			}
		})  
     }
  
};