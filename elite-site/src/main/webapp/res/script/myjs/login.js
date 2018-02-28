require([ "jquery", "ajax", "jsonAjax","md5","commons"], function($) {
	var autoLogin=typeof(localStorage.autoEliteLogin)=='undefined'?'false':localStorage.autoEliteLogin;
	var localUser=localStorage.eliteUserName;
	if(autoLogin=='true'){
		$("#autoLogin").attr('checked','checked');
		var password=localStorage.elitePassword;
		$("#password").val(password);
		login(localUser, password, true);
	}else{
		$("#password").val('');
	}
	$(".login-logo").click(function(){
		window.location.href=ctx+'/';
	});
	$("#account").val(localUser);
	$('body').keydown(function(event) {
		if (event.keyCode == 13){
			$("#loginBtn").click();
		}
	});
	$("#account").keyup(function(){
		$("#password").val('');
	});
	$("#loginBtn").click(function() {
		$("#loginForm  input").removeClass("error-sign");
		$("#loginError").text("");
		var account = $.trim($("#account").val());
		var password = $.trim($("#password").val());
		var rememberMe=$("#autoLogin").is(":checked");

		if (account == "") {
			$("#account").addClass("error-sign");
			$("#loginError").text("请输入手机号");
			return;
		}else if (password == "" || password == null) {
			$("#password").addClass("error-sign");
			$("#loginError").text("请输入密码");
			return;
		}
		password = $.md5($.trim(password));
		localStorage.eliteUserName=account;
		localStorage.elitePassword=password;
		login(account, password, rememberMe);
	});
	function login(account,password,rememberMe){
		jsonAjax.post({
			url : ctx+'/startLogin',
			data : {
				account:account,
				password:password,
				rememberMe:rememberMe,
				rps:$("#rps").val()
			},
			success : function(data) {
				if (data.result == "SUCCESS") {
					var object=data.object;
					sessionStorage.currentType=object.currentType;
					localStorage.ssoToken=object.token;
					localStorage.autoEliteLogin=rememberMe;
					var redirect=object.redirect;
					if(redirect=="invite"){
						window.location.href = ctx+"/invite?"+jsonAjax.random();
					}else if(redirect=="task"){
						window.location.href = ctx+"/hall?"+jsonAjax.random();
					}else{
						window.location.href = ctx+"/member/index?"+jsonAjax.random();
					}
				} else {
					$("#loginError").text(data.msg);
					localStorage.autoEliteLogin=false;
				}
			}
		});
	}
});