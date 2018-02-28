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
			url : ctx + '/member/company/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id=detail]").click(list.detail);
				$("a[id=audit]").click(auditlist.showAuditForm);
				$("a[id=assist_perfect]").click(prefectlist.showPrefectForm);
				$("a[id=disabledBtn]").click(list.disablefun);
				$("a[id=unDisabledBtn]").click(list.unDisablefun);
				$("a[id=remove]").click(list.remove);
			}
		})
	},
	remove : function() {
		var memberId = $(this).attr("data");
		$.confirm({
			title : "提示",
			content : "你确定要注销嘛",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({url : ctx + '/common/command',data:{key:memberId},success:function(data) {}});
				$("#affirmModel").modal();
				$("#directive").val('');
				$("#affirm_submit").unbind("click");
				$("#affirm_submit").click(function(){
					var directive=$("#directive").val();
					if(directive==""){
						$.tips({
							content : "请输入上级指令"
						});
						return false;
					}
					jsonAjax.post({
						url : ctx + '/member/remove',
						data : {
							memberId:memberId,
							directive:directive
						},
						success : function(data) {
							if (BASE.JS_RESULT.SUCCESS == data.result) {
								$("#affirmModel").modal('hide');
								list.search();
							} else {
								$.tips({
									content : data.msg
								});
							}
						}
					})
				});
			}
		});
	},
	update : function(user) {

	},
	formSumbit : function() {
	},
	detail : function() {
		list.initSetSearchData();
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		var form = $("#detailForm").submit();
	},
	disablefun : function() {
		var memberId=$(this).attr("data");
		$.confirm({
			title : "提示",
			content : "您确认要禁用此会员吗",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/member/disable',
					data : {
						memberId:memberId
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
	unDisablefun : function() {
		var memberId=$(this).attr("data");
		$.confirm({
			title : "提示",
			content : "您确认要启用此会员吗",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/member/enable',
					data : {
						memberId:memberId,
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
	initGetSearchData:function(){
		var keyword=sessionStorage.getItem('companykeyword');
		if(keyword!=null){
			$("#keywordId").val(keyword);
			sessionStorage.removeItem("companykeyword");
		}
		var status=sessionStorage.getItem('companystatus');
		if(status!=null){
			$("#eliteStatus").val(status);
			sessionStorage.removeItem("companystatus");
		}
		var pageth=sessionStorage.getItem('companypageth');
		if(pageth!=null)
		{
			$("#initPageth").html("<input type='hidden' name='pageth'  value='"+pageth+"'/>")
			sessionStorage.removeItem('companypageth');
		}
	},
	initSetSearchData:function(){
		var keyword = $("#keywordId").val();
		var status = $("#eliteStatus").find("option:selected").val();
		var pageth = $("#pager_pageth").val();
		var inviteCode=$("#inviteCodeId").val();
		if (keyword != null) {
			sessionStorage.setItem('companykeyword', keyword);
		}
		if(status!=null){
			sessionStorage.setItem('companystatus', status);
		}
		if(pageth!=null)
		{
			sessionStorage.setItem('companypageth', pageth);
		}
	},

};