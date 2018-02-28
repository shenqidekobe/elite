$(function() {
	list.init();
});
var list = {
	init : function() {
		$("#back").click(list.back)
		$("#tender_list").click(list.showTenderListPage)
		$("#setting_stage").click(stagelist.showForm);
		$("a[id=audit_project]").click(auditProjectlist.showForm);
		$("#materialShowBtn").click(list.showMaterial);
		$("#weeklyShowBtn").click(list.showWeekly);
		$("#logsShowBtn").click(list.showLogs);
		// 提交上传文件
		$("#submitMaterial").click(list.addMaterial)
		// 查询项目方周报
		$("#selCompanyWeek").click(function() {
			$("#searchStatusId").val("normal");
			$("#searchWeekTypeId").val("company");
			list.searchWeekly();
		})
		// 查询CTO周报
		$("#selCTOweek").click(function() {
			$("#searchStatusId").val("");
			$("#searchWeekTypeId").val("cto");
			list.searchWeekly();
		})

		// 查看新文件
		$("#view_material").click(function() {
			$("#materialShowBtn").click()
		})
		// 查看新周报
		$("#view_weekly").click(function() {
			$("#weeklyShowBtn").click()
		})

		// 发招标书
		$("#send_tender").click(
				function() {
					var id = $(this).attr("data");
					$("#detailFormInputId").val(id);
					$('#detailForm').attr('action',
							ctx + "/project/pm/tender/view").submit();
				})
		// 初始化月份select
		var date = new Date;
		var month = date.getMonth() + 1;
		$("#selectMonth").val(month);

		// 按月份查询周报
		$("#selectMonth").click(function() {
			$("#selectMonthId").val($(this).val())
			list.searchWeekly();
		})
		// 默认显示类别
		var showType = sessionStorage.getItem('pmViewDetailType');
		if (showType != null) {
			if (showType == "material") {
				$("#materialShowBtn").click();
			} else if (showType == "weekly") {
				$("#weeklyShowBtn").click();
			}
			sessionStorage.removeItem("pmViewDetailType");
		}
	},
	search : function() {
		$('#myModel').modal('hide');
		list.searchMaterial();
	},
	back : function() {
		window.location.href = ctx + '/project/pm'
	},
	showTenderListPage : function() {
		var id = $(this).attr("data");
		$("#detailFormProjectId").val(id);
		$('#detailForm').attr('action', ctx + "/project/pm/tender/list")
				.submit();
	},
	// 文件管理显示
	showMaterial : function() {
		list.searchMaterial();
	},
	// 查询文件
	searchMaterial : function() {
		jsonAjax.post({
			url : ctx + '/project/pm/material/listData',
			data : $("#materialForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				// 审核文件
				$("a[id='audit_material']").click(list.auditMaterialView);

				// 上传文件
				$("#uploadMaterialButton").click(function() {
					$("#uploadMaterialFile").click();
				})
				// 上传文件附件
				upload.uploadFile($("#uploadMaterialFile"), "project",
						"resume_atta", function(data) {
							$("#materialAttaId").val(data.attaId);
							$("#materialNameId").html(data.originalName);
							$("#submitMaterial").attr("style", "")
							$("#cancleMaterial").attr("style", "")
						});
				// 取消文件上传
				$("#cancleMaterial").click(function() {
					$(this).attr("style", "display: none;")
					$("#submitMaterial").attr("style", "display: none;")
					$("#materialNameId").html("");
				})

			}
		})
	},
	// 审核文件页面显示
	auditMaterialView : function() {
		var id = $(this).parent().attr("data");
		jsonAjax.post({
			url : ctx + '/project/pm/material/audit/view',
			data : {
				id : id
			},
			success : function(data) {
				$("#showData").html(data);
				$('#myModel').modal('show');
				$('#auditId').val(id);
				$('#audit_material_submit').click(list.auditMaterial);
				$('#audit_material_cannel').click(list.auditMaterial);
			}
		})
	},
	// 审核文件
	auditMaterial : function() {
		var id = $("#auditMaterialId").val();
		var type = $(this).attr("data");
		var auditReason = $("#auditReasonId").val();
        
		if (auditReason == "" || auditReason == null) {
			$.tips({
				content : "请输入审核原因"
			});
			return;
		}

		jsonAjax.post({
			url : ctx + '/project/pm/material/audit',
			data : {
				id : id,
				type : type,
				auditReason : auditReason
			},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					list.searchMaterial();
					$('#myModel').modal('hide');
				} else {
					$.tips({
						content : data.msg
					});
				}

			}
		})
	},
	// 上传文件
	addMaterial : function() {
		var projectId = $("#materialProjectId").val();
		var type = $(this).attr("data");
		$("#submitMaterial").attr("style", "display: none;")
		var stageId = $("#searchRole").val();
		var attaId = $("#materialAttaId").val();
		var receiveId = $("#selectToType").val();
		jsonAjax.post({
			url : ctx + '/project/pm/material/addMaterial',
			data : {
				projectId : projectId,
				stageId : stageId,
				attaId : attaId,
				receiveId : receiveId
			},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					$("#cancleMaterial").click();
					list.searchMaterial();
				} else {
					$.tips({
						content : data.msg
					});
				}

			}
		})
	},
	// 招标等记录
	showLogs : function() {
		list.searchLogs();
	},
	searchLogs : function() {
		jsonAjax.post({
			url : ctx + '/project/pm/detailLogs/listData',
			data : $("#logsForm").serialize(),
			success : function(data) {
				$("#logsData").html(data);
				// 查看招标书
				$("a[id='logsViewTender']").click(list.logsViewTender)
				// 查看CTO竞标书
				$("a[id='logsViewCTOTender']").click(list.logsViewCTOtender)
				// 查看定标书
				$("a[id='logsViewDefine']").click(list.logsViewDefine)

			}
		})
	},
	// 周报管理显示
	showWeekly : function() {
		list.searchWeekly();
	},
	// 查询周报
	searchWeekly : function() {
		jsonAjax.post({
			url : ctx + '/project/pm/weekly/listData',
			data : $("#weeklyForm").serialize(),
			success : function(data) {
				$("#worklistData").html(data);
				// 审核文件
				$("a[id='audit_material']").click(list.auditMaterialView);
				var selectupdate;
				// 上传周报
				upload.uploadFile($("input[id=uploadWeekFile]"), "project",
						"resume_atta", function(data) {
							selectupdate.html(data.fileName)
							selectupdate.next().val(data.attaId)
							selectupdate.next().next().attr("style", "")
							selectupdate.next().next().next().attr("style", "")
						});

				// 提交周报
				$("button[id=submitWeek]").click(list.submitWeek);
				// 取消提交周报
				$("button[id=cancleWeek]").click(function() {
					$(this).attr("style", "display: none;")
					$(this).prev().attr("style", "display: none;")
					$(this).prev().prev().prev().html("");

				})
				// 索要周报
				$("button[id=askWeek]").click(function() {
					var time = $(this).attr("data");
					var projectId = $("#projectId").val();
					var that = this;
					jsonAjax.post({
						url : ctx + '/project/pm/weekly/ask',
						data : {
							projectId : projectId,
							time : time
						},
						success : function(data) {
							if (BASE.JS_RESULT.SUCCESS == data.result) {
								$(that).hide();
								$.tips({
									content : "已经成功索要，请耐心等待"
								});
							} else {
								$.tips({
									content : data.msg
								});

							}

						}
					})
				})
				// 上传周报
				$("button[id=uploadWeekly]").click(function() {
					$(this).next().click();
					selectupdate = $(this).next().next();
				})
				// 周报审核通过
				$("button[id=weekPassAudit]").click(function() {
					var id = $(this).attr("data");
					var time = $(this).attr("time");
					list.auditWeek(id, "normal",time);
				})
				// 审核不通过
				$("button[id=weekunPassAudit]").click(function() {
					var id = $(this).attr("data");
					var time = $(this).attr("time");
					list.auditWeek(id, "unaudit",time);
				})

			}
		})
	},
	submitWeek : function() {
		var projectId = $("#projectId").val();
		var attaId = $(this).prev().val();
		var weeklyTime = $(this).next().next().val();
		var type = $(this).attr("data");
		jsonAjax.post({
			url : ctx + '/project/pm/weekly/save',
			data : {
				projectId : projectId,
				attaId : attaId,
				weeklyTime : weeklyTime
			},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					list.searchWeekly();
				} else {
					$.tips({
						content : data.msg
					});
				}

			}
		})
	},
	// 审核周报
	auditWeek : function(id, status,time) {
		jsonAjax.post({
			url : ctx + '/project/pm/weekly/audit',
			data : {
				id : id,
				status : status,
				time:time
			},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					list.searchWeekly();
				} else {
					$.tips({
						content : data.msg
					});
				}

			}
		})
	},
	// 招标等记录_查询招标书
	logsViewTender : function(id, status) {
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/tender/view/detail")
				.submit();
	},
	// 招标等记录_查询CTO竞标书
	logsViewCTOtender : function() {
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/tender/list/detail")
				.submit();
	},
	// 招标等记录_查询定标书
	logsViewDefine : function() {
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/pm/finddefine/view")
				.submit();
	},

};