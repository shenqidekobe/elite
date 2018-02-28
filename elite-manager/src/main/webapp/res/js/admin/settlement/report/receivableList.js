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
	},
	search : function() {
		Ajax.post({
			url : ctx + '/settlement/receivable/report/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
			}
		})
	},
};