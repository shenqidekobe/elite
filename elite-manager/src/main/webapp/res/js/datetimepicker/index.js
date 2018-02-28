$(function(){
	index.init();
});
var index={
	init:function(){
		$("#ep_btn").click(function(){
			$('#epModel').modal('show');
		});
		$("#logout").click(function(){
			$.confirm({
				title : "提示",
				content : "你确定要退出登录嘛?",
				iconSmall : "icon-warning-sign",
				callback : function() {
					location.href=ctx+'/logout';
				}
			});
		});
		index.validate();
	},
	validate : function() {
		$("#epForm").validate({
			debug : false,
			rules : {
				oldPass : {
					required : true,
					minlength : 6,
					maxlength : 16,
				},
				newPass : {
					required : true,
					minlength : 6,
					maxlength : 16,
				},
				affirmPass : {
					required : true,
					minlength : 6,
					maxlength : 16,
					equalTo: "#newPass"
				},
			},
			messages : {
				password : {
					required : '请输入旧密码',
					minlength : '密码只允许6~32个字符',
					maxlength : '密码只允许6~32个字符',
				},
				newPass : {
					required : '请输入新密码',
					minlength : '密码只允许6~32个字符',
					maxlength : '密码只允许6~32个字符',
				},
				affirmPass : {
					required : '请输入确认密码',
					minlength : '密码只允许6~32个字符',
					maxlength : '密码只允许6~32个字符',
					equalTo: "确认密码不一致"
				}
			},
			submitHandler : function(form) {
				index.formSumbit();
			}
		});
	},
	formSumbit : function() {
		var oldPass = $.trim($("#oldPass").val());
		var newPass = $.trim($("#newPass").val());
		oldPass = encrypt.DES(oldPass);
		newPass = encrypt.DES(newPass);
		jsonAjax.post({
			url : ctx + '/index/update/pass',
			data : {
				oldPass:oldPass,
				newPass:newPass
			},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					 $('#epModel').modal('hide');
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		});
	}
}