$(function() {
	list.init();
});
var list = {
	init : function() {
		list.search();
		$("#createUser").click(list.create);
		$("#search").click(function(){
			//搜索默认第一页
			$("#pager_pageth").val(1);
			list.search()
		});
	},
	search : function() {
		jsonAjax.post({
			url : ctx + '/settings/organ/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id=remove]").click(list.remove);
				$("a[id=update]").click(list.update);
			}
		})
	},
	remove : function() {
		var id = $(this).parent().attr("data");

		$.confirm({
			title : "提示",
			content : "你确定要删除",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/settings/organ/remove',
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
				});
			}
		});
	},
	update : function(user) {
		$("#organForm")[0].reset();
		var id = $(this).parent().attr("data");
		jsonAjax.post({
			url : ctx + '/settings/organ/view',
			data : {
				id : id
			},
			success : function(data) {
				var bean=data;
				$("#organId").val(bean.id)
				$("#organName").val(bean.name)
				$("#organIntro").val(bean.intro);
				$('#myModel').modal('show');
				list.validate();
			}
		});

	},
	create : function() {
		$("#organForm")[0].reset();
		$("#organId").val("");
		$('#myModel').modal('show');
		list.validate();
	},
	validate : function() {
		$("#organForm").validate({
			debug : false,
			rules : {
				name : {
					required : true,
					minlength : 2,
					maxlength : 25,
					remote : {
						url : ctx + "/settings/organ/check/organName",
						type : "Post",
						data : {
							id : function() {
								return $("#organId").val();
							},
						}
					}

				}

			},
			messages : {
				name : {
					required : '请输入单位名称',
					minlength : '单位名称只允许2~25个字符',
					maxlength : '单位名称只允许2~25个字符',
					remote : '单位名称已存在',
				}
			},
			submitHandler : function(form) {
				list.formSumbit();
			}
		});
	},
	formSumbit : function() {
		jsonAjax.post({
			url : ctx + '/settings/organ/save',
			data : $("#organForm").serialize(),
			success : function(rsp) {
				if (rsp.id) {
					$('#myModel').modal('hide');
					list.search();
				} else {
					$.tips({
						content : rsp.msg
					});
				}
				
			}
		});
	}

};