$(function() {
	list.init();
});
var list = {
	init : function() {
		list.initGetSearchData();
		list.search();
		$("#initPageth").html("");
		$("#search").click(function(){
			//搜索默认第一页
			$("#pager_pageth").val(1);
			list.search()
		});
		
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
			url : ctx + '/channel/company/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id=detail]").click(list.detail);
				$("a[id=aduit]").click(aduitlist.showForm);
				$("a[id=allot]").click(list.allotBm);
				$("a[id=addRemarks]").click(remarkslist.showForm);
				$("a[id=remove]").click(list.remove);
			}
		})
	},
	
	searchBtnclick:function(){
		$("#pager_pageth").val("1");
		list.search();
	},
	detail : function() {
		list.initSetSearchData();
	    var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/channel/company/detail").submit();
	},
	allotBm : function() {
	    var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/channel/company/allot/view").submit();
	},
	showWorkLogs : function() {
		var memberId = $(this).parent().attr("data");
		if (null != memberId && "" != memberId) {
			$("#workRecordForeignid").val(memberId);
		} else {
			memberId = $("#workRecordForeignid").val();
		}
		$('#workLogsModel').modal('show');
		jsonAjax.post({
			url : ctx + '/work/listData',
			data : {
				foreignId : memberId,
				type : "partnerCompany"
			},
			success : function(data) {
				if ("EXCEPTION" == data.code) {
					$.tips({
						content : data.msg
					});
				} else {

					$('#workLogsModel').html(data);
					$('#workLogsModel').modal('show');
					$('#removeWorkRecord').click(list.removeWorkRecord);
					$("#workRecordForeignid").val(memberId);
				}

			},
			error : function(data) {
				$('#removeWorkRecord').click(list.removeWorkRecord);
				$.tips({
					content : data.msg
				});
			},
		})

	},
	selectaUserModel : function() {

	},
	addWorkRecord : function() {
		$('#workRecordType').val('partnerCompany');
		jsonAjax.post({
			url : ctx + '/work/save',
			data : $("#workRecordForm").serialize(),
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					list.showWorkLogs();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}

		})
	},
	removeWorkRecord : function(id) {

		jsonAjax.post({
			url : ctx + '/work/remove',
			data : {
				id : id
			},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					list.showWorkLogs();
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
		var keyword=sessionStorage.getItem('partnerCompanykeyword');
		if(keyword!=null){
			$("#keywordId").val(keyword);
			sessionStorage.removeItem("partnerCompanykeyword");
		}
		var status=sessionStorage.getItem('partnerCompanystatus');
		if(status!=null){
			$("#partnerStatus").val(status);
			sessionStorage.removeItem("partnerCompanystatus");
		}
		var pageth=sessionStorage.getItem('partnerCompanypageth');
		if(pageth!=null)
		{
			$("#initPageth").html("<input type='hidden' name='pageth'  value='"+pageth+"'/>")
			sessionStorage.removeItem('partnerCompanypageth');
		}
	
	},
	initSetSearchData:function(){
		var keyword = $("#keywordId").val();
		var status = $("#partnerStatus").find("option:selected").val();
		var pageth = $("#pager_pageth").val();
		if (keyword != null) {
			sessionStorage.setItem('partnerCompanykeyword', keyword);
		}
		if(status!=null){
			sessionStorage.setItem('partnerCompanystatus', status);
		}
		if(pageth!=null)
		{
			sessionStorage.setItem('partnerCompanypageth', pageth);
		}
	
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

};