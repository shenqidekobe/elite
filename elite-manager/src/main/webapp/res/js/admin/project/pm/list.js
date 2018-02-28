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
		//清空默认第一页
		$("#initPageth").html("");
		
		$('#startTimeId').datepicker({
			language : 'zh-CN',
			autoclose : true,
			format : "yyyy-MM-dd",
			todayHighlight : true,
		})
		$('#endTimeId').datepicker({
			language : 'zh-CN',
			autoclose : true,
			format : "yyyy-MM-dd",
			todayHighlight : true
		})
	},
	search : function() {
		Ajax.post({
			url : ctx + '/project/pm/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id=show_detail]").click(list.detail);
				$("a[id=send_tender]").click(list.showTenderPage);
				$("a[id=tender_list]").click(list.showTenderListPage);
				$("a[id=setting_stage]").click(stagelist.showForm);
				$("a[id=audit_project]").click(auditProjectlist.showForm);
				$("a[id=add_define]").click(list.addfine);
				$("a[id=search_define]").click(list.searchfine);
				
				//查看文件
				$("a[id=view_material]").click(list.viewMaterial);
				//查看周报
				$("a[id=view_weekly]").click(list.viewWeekly);
			}
		})
	},
	detail:function(){
		list.initSetSearchData();
		var id = $(this).attr("data");
		$("#detailFormProjectId").val(id);
		$('#detailForm').attr('action', ctx + "/project/pm/detail").submit();
	},
	showTenderPage:function(){
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/pm/tender/view").submit();
	},
	showTenderListPage:function(){
		list.initSetSearchData();
		var id = $(this).attr("data");
		$("#detailFormProjectId").val(id);
		$('#detailForm').attr('action', ctx + "/project/pm/tender/list").submit();
	},
	addfine:function(){
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/pm/adddefine/view").submit();
	},
	searchfine:function(){
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/pm/finddefine/view").submit();
	},
	viewMaterial:function(){
		list.initSetSearchData();
		sessionStorage.setItem('pmViewDetailType','material');
		var id = $(this).attr("data");
		$("#detailFormProjectId").val(id);
		$('#detailForm').attr('action', ctx + "/project/pm/detail").submit();
	},
	viewWeekly:function(){
		list.initSetSearchData();
		sessionStorage.setItem('pmViewDetailType','weekly');
		var id = $(this).attr("data");
		$("#detailFormProjectId").val(id);
		$('#detailForm').attr('action', ctx + "/project/pm/detail").submit();
	},
	initGetSearchData:function(){
		var keyword=sessionStorage.getItem('pmkeyword');
		if(keyword!=null){
			$("#keywordId").val(keyword);
			sessionStorage.removeItem("pmkeyword");
		}
		var status=sessionStorage.getItem('pmstatus');
		if(status!=null){
			$("#projectStatus").val(status);
			sessionStorage.removeItem("pmstatus");
		}
		var pageth=sessionStorage.getItem('pmpageth');
		if(pageth!=null)
		{
			$("#initPageth").html("<input type='hidden' name='pageth'  value='"+pageth+"'/>")
			sessionStorage.removeItem('pmpageth');
		}
	},
	initSetSearchData:function(){
		var keyword = $("#keywordId").val();
		var status = $("#projectStatus").find("option:selected").val();
		var pageth = $("#pager_pageth").val();
		if (keyword != null) {
			sessionStorage.setItem('pmkeyword', keyword);
		}
		if(status!=null){
			sessionStorage.setItem('pmstatus', status);
		}
		if(pageth!=null)
		{
			sessionStorage.setItem('pmpageth', pageth);
		}
	},
};