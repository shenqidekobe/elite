$(function() {
	list.init();
});
var list = {
	init : function() {
		list.initGetSearchData();
		list.search();
		$("#search").click(function() {
			// 搜索默认第一页
			$("#pager_pageth").val(1);
			list.search()
		});
		$("#initPageth").html("");
		$("#create").click(createprojectlist.showForm);
		
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
	initGetSearchData:function(){
		var keyword=sessionStorage.getItem('bmkeyword');
		if(keyword!=null){
			$("#keywordId").val(keyword);
			sessionStorage.removeItem("bmkeyword");
		}
		var status=sessionStorage.getItem('bmstatus');
		if(status!=null){
			$("#projectStatus").val(status);
			sessionStorage.removeItem("bmstatus");
		}
		var pageth=sessionStorage.getItem('bmpageth');
		if(pageth!=null)
		{
			$("#initPageth").html("<input type='hidden' name='pageth'  value='"+pageth+"'/>")
			sessionStorage.removeItem('bmpageth');
		}
	},
	initSetSearchData:function(){
		var keyword = $("#keywordId").val();
		var status = $("#projectStatus").find("option:selected").val();
		var pageth = $("#pager_pageth").val();
		if (keyword != null) {
			sessionStorage.setItem('bmkeyword', keyword);
		}
		if(status!=null){
			sessionStorage.setItem('bmstatus', status);
		}
		if(pageth!=null)
		{
			sessionStorage.setItem('bmpageth', pageth);
		}
	},
	search : function() {
		Ajax.post({
			url : ctx + '/project/bm/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id=assist_perfect]").click(projectprefectlist.showForm);
				$("a[id=audit_company]").click(auditlist.showAuditForm);
				$("a[id=assistelite_prefect]").click(
						prefectlist.showPrefectForm);
				$("a[id=add_business]").click(businesslist.showForm);
				$("a[id=show_detail]").click(list.detail);
				$("a[id=search_define]").click(list.define);
				$("a[id=add_define]").click(list.addfine);
				$("a[id=alert_define]").click(list.alertdefine);
				$("a[id=search_define]").click(list.searchfine);
				$("a[id=allot_pm]").click(list.allotPm);
				$("a[id=audit_project]").click(auditProjectlist.showForm);
				$("a[id=change_bm]").click(list.changeBM);
				// 撤回立项书
				$("a[id=remove_define]").click(list.removeDefineFun);
				$("a[id=invalid]").click(list.invalid);
			}
		})
	},
	alertdefine:function(){
		$.tips({
			content : "此项目需要PM评估后，设置好服务阶段，才能发立项书，请通知PM做相关设置",
			timeout : 2500
		});
	},
	detail : function() {
		list.initSetSearchData();
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/bm/detail").submit();
		
	},
	define : function() {
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/bm/define/view")
				.submit();
	},
	addfine : function() {
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/bm/adddefine/view")
				.submit();
	},
	searchfine : function() {
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/bm/finddefine/view")
				.submit();
	},
	allotPm : function() {
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/bm/allot/view")
				.submit();
	},
	changeBM : function() {
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/bm/bmlist/view")
		.submit();
	},
	removeDefineFun : function() {
		var id = $(this).attr("data");
		$.confirm({
			title : "提示",
			content : "您确认要撤回立项书吗？",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/project/bm/define/remove',
					data : {
						id : id
					},
					success : function(data) {
						if (BASE.JS_RESULT.SUCCESS == data.result) {
							list.search();
						} else {
							$.tips({
								content : data.msg
							});
						}
					}
				})
			}
		});
	},
	invalid:function(){
		var id = $(this).attr("data");
		$.confirm({
			title : "提示",
			content : "您确认要更新为无效立项吗？",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/project/bm/invalid/save',
					data : {
						projectId : id
					},
					success : function(data) {
						if (BASE.JS_RESULT.SUCCESS == data.result) {
							list.search();
						} else {
							$.tips({
								content : data.msg
							});
						}
					}
				})
			}
		});
	}
};