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
			url : ctx + '/settlement/platfromInOrder/listData',
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
			$('#detailForm').attr('action', ctx + "/settlement/platfromInOrder/detail").submit();
	},
	viewReceipt:function(){
		   var id=$(this).parent().attr("data");
	},
	initGetSearchData:function(){
		var keyword=sessionStorage.getItem('inorderkeyword');
		if(keyword!=null){
			$("#keyword").val(keyword);
			sessionStorage.removeItem("inorderkeyword");
		}
		var status=sessionStorage.getItem('inorderstatus');
		if(status!=null){
			$("#status").val(status);
			sessionStorage.removeItem("inorderstatus");
		}
		var type=sessionStorage.getItem('inordertype');
		if(status!=null){
			$("#type").val(type);
			sessionStorage.removeItem("inordertype");
		}
		var payType=sessionStorage.getItem('inorderpaytype');
		if(payType!=null){
			$("#payType").val(payType);
			sessionStorage.removeItem("inorderpaytype");
		}
		var pageth=sessionStorage.getItem('inorderpageth');
		if(pageth!=null)
		{
			$("#initPageth").html("<input type='hidden' name='pageth'  value='"+pageth+"'/>")
			sessionStorage.removeItem('inorderpageth');
		}
	},
	initSetSearchData:function(){
		var keyword = $("#keyword").val();
		var status = $("#status").find("option:selected").val();
		var type = $("#type").find("option:selected").val();
		var payType = $("#payType").find("option:selected").val();
		var pageth = $("#pager_pageth").val();
		if (keyword != null) {
			sessionStorage.setItem('inorderkeyword', keyword);
		}
		if(status!=null){
			sessionStorage.setItem('inorderstatus', status);
		}
		if(type!=null){
			sessionStorage.setItem('inordertype', type);
		}
		if(payType!=null){
			sessionStorage.setItem('inorderpaytype', payType);
		}
		if(pageth!=null)
		{
			sessionStorage.setItem('inorderpageth', pageth);
		}
	},
};