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
		$("#inviteCodeBtn").click(list.inviteCodeFun);
		$("#initPageth").html("");
		
		$('#startTimeId').datepicker({
			language : 'zh-CN',
			autoclose : true,
			format : "yyyy-MM-dd",
			todayHighlight : true,
		});
		$('#endTimeId').datepicker({
			language : 'zh-CN',
			autoclose : true,
			format : "yyyy-MM-dd",
			todayHighlight : true
		});
	},
	search : function() {
		jsonAjax.post({
			url : ctx + '/member/cto/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id=detail]").click(list.detail);
				$("a[id=audit]").click(auditlist.showAuditForm);
				$("a[id=createClose]").click(closelist.showCreateCloseForm);
				$("a[id=stopClose]").click(closelist.showStopCloseForm);
				$("a[id=level]").click(levellist.showLevelForm);
				$("a[id=homeShowBtn]").click(list.homeShow);
				$("a[id=noHomeShowBtn]").click(list.noHomeShow);
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
		$('#detailForm').attr('action', ctx + "/member/cto/detail").submit();
	},
	inviteCodeFun : function() {
		$("#detailFormCurrentId").val("cto");
		$('#detailForm').attr('action', ctx + "/member/invitecode/index").submit();
	},
	 homeShow:function(){
	    	var memberId=$(this).attr("data");
	    	list.homeShowSubmit(memberId,true)
	    },
		noHomeShow:function(){
			var memberId=$(this).attr("data");
	    	list.homeShowSubmit(memberId,false)
		},
		homeShowSubmit:function(memberId,type){
			jsonAjax.post({
				url : ctx + '/member/elite/updateHomeShow',
				data :{
					memberId:memberId,
					homeShow:type
				},
				success : function(data) {
					if (BASE.JS_RESULT.SUCCESS == data.result) {
						list.search();
						$('#myModel').modal('hide');
					} else {
						$.tips({
							content : data.msg
						});
					}

				}
			})
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
			var keyword=sessionStorage.getItem('ctokeyword');
			if(keyword!=null){
				$("#keywordId").val(keyword);
				sessionStorage.removeItem("ctokeyword");
			}
			var status=sessionStorage.getItem('ctostatus');
			if(status!=null){
				$("#eliteStatus").val(status);
				sessionStorage.removeItem("ctostatus");
			}
			var pageth=sessionStorage.getItem('ctopageth');
			if(pageth!=null)
			{
				$("#initPageth").html("<input type='hidden' name='pageth'  value='"+pageth+"'/>")
				sessionStorage.removeItem('ctopageth');
			}
			var inviteCode=sessionStorage.getItem('ctoinviteCode');
			if(inviteCode!=null)
			{
				$("#inviteCodeId").val(inviteCode);
				sessionStorage.removeItem('ctoinviteCode');
			}
		},
		initSetSearchData:function(){
			var keyword = $("#keywordId").val();
			var status = $("#eliteStatus").find("option:selected").val();
			var pageth = $("#pager_pageth").val();
			var inviteCode=$("#inviteCodeId").val();
			if (keyword != null) {
				sessionStorage.setItem('ctokeyword', keyword);
			}
			if(status!=null){
				sessionStorage.setItem('ctostatus', status);
			}
			if(pageth!=null)
			{
				sessionStorage.setItem('ctopageth', pageth);
			}
			if(inviteCode!=null)
			{
				sessionStorage.setItem('ctoinviteCode', inviteCode);
			}
		},
};