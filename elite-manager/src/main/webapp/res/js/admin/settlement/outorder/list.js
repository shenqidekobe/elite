$(function() {
	list.init();
});
var list = {
	init : function() {
		list.initGetSearchData();
		list.search();
		$("#search").click(function(){
			//搜索默认第一页
			$("#pager_pageth").val(1);
			list.search()
		});
		$("#initPageth").html("");
	},
	search : function() {
		jsonAjax.post({
			url : ctx + '/settlement/platfromOutOrder/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id='viewDeatil']").click(list.viewDetail)
			}
		})
	},
	viewDetail:function(){
		list.initSetSearchData();
		   var id = $(this).parent().attr("data");
			$("#detailFormInputId").val(id);
			$('#detailForm').attr('action', ctx + "/settlement/platfromOutOrder/detail").submit();
	},
	viewReceipt:function(){
		   var id=$(this).parent().attr("data");
	},
	initGetSearchData:function(){
		var keyword=sessionStorage.getItem('outorderkeyword');
		if(keyword!=null){
			$("#keyword").val(keyword);
			sessionStorage.removeItem("outorderkeyword");
		}
		var status=sessionStorage.getItem('outorderstatus');
		if(status!=null){
			$("#status").val(status);
			sessionStorage.removeItem("outorderstatus");
		}
		var type=sessionStorage.getItem('outordertype');
		if(status!=null){
			$("#type").val(type);
			sessionStorage.removeItem("outordertype");
		}
		var pageth=sessionStorage.getItem('outorderpageth');
		if(pageth!=null)
		{
			$("#initPageth").html("<input type='hidden' name='pageth'  value='"+pageth+"'/>")
			sessionStorage.removeItem('outorderpageth');
		}
	},
	initSetSearchData:function(){
		var keyword = $("#keyword").val();
		var status = $("#status").find("option:selected").val();
		var type = $("#type").find("option:selected").val();
		var pageth = $("#pager_pageth").val();
		if (keyword != null) {
			sessionStorage.setItem('outorderkeyword', keyword);
		}
		if(status!=null){
			sessionStorage.setItem('outorderstatus', status);
		}
		if(type!=null){
			sessionStorage.setItem('outordertype', type);
		}
		if(pageth!=null)
		{
			sessionStorage.setItem('outorderpageth', pageth);
		}
	},
};