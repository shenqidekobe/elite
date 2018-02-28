$(function() {
	list.init();
});
var list = {
	init : function() {
		list.search();
		$("#create").click(list.create);
		$("#search").click(function(){
			//搜索默认第一页
			$("#pager_pageth").val(1);
			list.search()
		});
	},
	search : function() {
		Ajax.post({
			url : ctx + '/settings/role/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id=remove]").click(list.remove);
				$("a[id=update]").click(list.update);
				$("a[id=right-1]").click(list.showRight);
			}
		})
	},
	
	showRight:function(){
		var id = $(this).parent().attr("data");
		showRightDialog(id)
	},
	remove : function() {
		var id = $(this).parent().attr("data");
		$.confirm({
			title : "提示",
			content : "你确定要删除",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/settings/role/remove',
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
		$("#userForm")[0].reset();
		var id = $(this).parent().attr("data");
		jsonAjax.post({
			url : ctx + '/settings/role/view',
			data : {
				id : id
			},
			success : function(data) {
				var role = data;
				$("#roleId").val(role.id)
				$("#roleName").val(role.name);
				$("#roleIntro").val(role.intro);
				$('#myModel').modal('show');
				list.validate();
			}
		});

	},
	create : function() {
		$("#userForm")[0].reset();
		$('#myModel').modal('show');
		list.validate();
	},
	validate : function() {
		$("#userForm").validate({
			debug : false,
			rules : {
				name : {
					required : true,
					minlength : 1,
					maxlength : 16,
					remote : {
						url : ctx + "/settings/role/check/roleName",
						type : "Post",
						data : {
							id : function() {
								return $("#roleId").val();
							},
						}
					}

				},
			
			},
			messages : {
				name : {
					required : '请输入角色名',
					minlength : '角色名只允许1~16个字符',
					maxlength : '角色名只允许1~16个字符',
					remote : '角色名已存在',
				}
			},
			submitHandler : function(form) {
				list.formSumbit();
			}
		});
	},
	formSumbit : function() {
		jsonAjax.post({
			url : ctx + '/settings/role/save',
			data : $("#userForm").serialize(),
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					 $('#myModel').modal('hide');
					 list.search();
				} else {
					$.tips({
						content :data.msg
					});
				}
			}
		});
	}

};
