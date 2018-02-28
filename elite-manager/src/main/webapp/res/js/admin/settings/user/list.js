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
			url : ctx + '/settings/user/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id=remove]").click(list.remove);
				$("a[id=update]").click(list.update);
				$("a[id=enable]").click(list.oper);
				$("a[id=disable]").click(list.oper);
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
					url : ctx + '/settings/user/remove',
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
	oper:function(){
		var id = $(this).parent().attr("data");
		var oper = $(this).attr("id");
		var operText = $(this).text();
		$.confirm({
			title : "提示",
			content : "你确定要"+operText+"嘛？",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/settings/user/'+oper,
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
			url : ctx + '/settings/user/view',
			data : {
				id : id
			},
			success : function(data) {
				var user = data;
				$("#sysUserId").val(user.id)
				$("#userNameId").val(user.userName);
				$("#roleId").val(user.roleId);
				$("#phoneId").val(user.phone);
				$("#loginNameId").val(user.loginName);
				$("#emailId").val(user.email);
				$("#passWordId").val(user.password);
				$("#changePassWord").val(user.password);
				$("#deptId").val(user.deptId);
				$("#deptName").val(user.sysDept.name);
				$("#userPhoto").val(user.userPhoto);
				if(user.userPhoto!=""&&user.userPhoto!=null){
					$("#userPhotoImg").attr('src',user.photo.path);
				}

				list.operEdit();
				list.validate();
			}
		});

	},
	create : function() {
		$("#userForm")[0].reset();
		$("#sysUserId").val("");
		$("#userPhotoImg").attr('src','');
		list.operEdit();
		list.validate();
	},
	operEdit:function(){
		//打款凭证上传
		upload.uploadImg($("#userPhotoFile"), "userPhoto", function(data) {
			$("#userPhotoImg").attr("src", data.url);
			$("#userPhoto").val(data.attaId);
		});
		$('#myModel').modal('show');
	},
	validate : function() {
		$("#deptName").click(function() {
			selectDept.init();
			var deptObj = $("#deptName");
			var deptOffset = $("#deptName").offset();
			$("#menuContent").css({
				left : deptOffset.left + "px",
				top : deptOffset.top + deptObj.outerHeight() + "px"
			}).slideDown("fast");
			$("body").bind("mousedown", function(event) {
				if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" 
					|| $(event.target).parents("#menuContent").length>0)) {
					$("#menuContent").fadeOut("fast");
					$("body").unbind("mousedown");
				}
			});

		});
		
		$.validator.addMethod("isMobile", function(value, element) {
		    var length = value.length;
		    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
		    return this.optional(element) || (length == 11 && mobile.test(value));
		}, "请正确填写您的手机号码")
		
		$("#userForm").validate({
			debug : false,
			rules : {
				loginName : {
					required : true,
					minlength : 4,
					maxlength : 16,
					remote : {
						url : ctx + "/settings/user/check/loginName",
						type : "Post",
						data : {
							id : function() {
								return $("#sysUserId").val();
							},
						}
					}

				},
				password : {
					required : true,
					minlength : 6,
					maxlength : 32,
				},
				userName : {
					required : true,
					minlength : 2,
					maxlength : 8,
				},
				email : {
					required : false,
					email : true,
				},
				phone : {
					required : false,
					minlength : 11,
					isMobile:true,
				},
				roleId : {
					required : true,
				},
				deptName : {
					required : true,
				},
			},
			messages : {
				loginName : {
					required : '请输入登录名',
					minlength : '登录名只允许4~16个字符',
					maxlength : '登录名只允许4~16个字符',
					remote : '登录名已存在',
				},
				password : {
					required : '请输入登录密码',
					minlength : '密码只允许6~32个字符',
					maxlength : '密码只允许6~32个字符',
				},
				userName : {
					required : '请输入用户名称',
					minlength : '用户名称只允许2~8个字符',
					maxlength : '用户名称只允许2~8个字符',
				},
				phone : {
					 minlength : "确认手机不能小于11个字符",
			         isMobile : "请正确填写手机号码"
				},
				roleId : {
					required : '请选择角色',
				},
				deptName : {
					required : '请选择部门',
				}
			},
			submitHandler : function(form) {
				list.formSumbit();
			}
		});
	},
	formSumbit : function() {
		var defaultPassWord = $("#changePassWord").val();
		var password = $("#passWordId").val();
		var encryptpassword = encrypt.DES(password);
		$("#passWordId").val(encryptpassword);
		if (encryptpassword ==defaultPassWord || password==defaultPassWord) {
			$("#changePassWord").val(false)
		} else {
			$("#changePassWord").val(true)
			
		}

		jsonAjax.post({
			url : ctx + '/settings/user/save',
			data : $("#userForm").serialize(),
			success : function(data) {
				
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					 $('#myModel').modal('hide');
					 list.search();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		});
	}

};