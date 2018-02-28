<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html class="bg-black">
<head>
<meta charset="utf-8">
<title>云英汇 | 后台</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<link href="${_PATH}/res/css/bootstrap.min.css" rel="stylesheet"/>
<link href="${_PATH}/res/css/font-awesome.min.css" rel="stylesheet"/>
<link href="${_PATH}/res/css/AdminLTE.css" rel="stylesheet"/>
<link href="${_PATH}/res/css/mac-production-plugins.min.css" rel="stylesheet" type="text/css" />

</head>
<body class="bg-black">
	<div class="form-box" id="login-box">
		<div class="header">登 录</div>
		<form action="" method="post">
			<div class="body bg-gray">
				<div class="form-group">
					<input type="text" name="account" id="account" class="form-control" placeholder="用户名" />
				</div>
				<div class="form-group">
					<input type="password" name="password" id="password" class="form-control"
						placeholder="密码" />
				</div>
				<div class="form-group">
					<div class="inline">
						<input type="text" name="captcha"
							id="captcha" maxlength="4" style="width: 100px;" placeholder="验证码">&nbsp;&nbsp;&nbsp;
						<img id="captchaImg" src="${_PATH}/captcha/produce" alt="验证码" title="点击刷新验证码">
						<a class="inline" href="javascript:void(0);" id="replaceCode">换一张</a><br>
					</div>
				</div>
			</div>
			<div class="footer">
				<button type="button" id="submitLogin" class="btn bg-olive btn-block">登 录</button>
			</div>
		</form>
	</div>

	<script src="${_PATH}/res/js/jquery.min.js"></script>
	<script src="${_PATH}/res/js/bootstrap.min.js"></script>
	<script src="${_PATH}/res/js/ajax/json3.js"></script>
	<script src="${_PATH}/res/js/ajax/ajax.js"></script>
	<script src="${_PATH}/res/js/common/base.js"></script>
	<script src="${_PATH}/res/js/crypto/core-min.js"></script>
	<script src="${_PATH}/res/js/crypto/cipher-core-min.js"></script>
	<script src="${_PATH}/res/js/crypto/enc-base64-min.js"></script>
	<script src="${_PATH}/res/js/crypto/mode-ecb-min.js"></script>
	<script src="${_PATH}/res/js/crypto/tripledes-min.js"></script>
	<script src="${_PATH}/res/js/common/encrypt.js"></script>
	<script src="${_PATH}/res/js/plugins/custom/macNotification.min.js" type="text/javascript"></script>
	<script src="${_PATH}/res/js/common/boxMessage.js"></script>
	<script src="${_PATH}/res/js/common/boxMessage.js"></script>
	<script type="text/javascript">
	    $(function(){
	    	$("#replaceCode").click(function() {
				$('#captchaImg').attr('src','${_PATH}/captcha/produce?now='+new Date().getTime());
			});
	    	$('body').keydown(function(event) {
				if (event.keyCode == 13){
					login();
				}
			});
			$("#submitLogin").click(function() {
				 login();
			});
	    	function login(){
	    		var account=$.trim($("#account").val());
				var password=$.trim($("#password").val());
				var captcha=$.trim($("#captcha").val());
				password=encrypt.DES(password);
				if(account==""){
					$.tips({content:"请输入登录帐号"});
					return false;
				}else if(password==""){
					$.tips({content:"请输入登录密码"});
					return false;
				}else if(captcha==""){
					$.tips({content:"请输入验证码"});
					return false;
				}
				$("#submitLogin").attr('disabled',"true");
				Ajax.post({
					url:'${_PATH}/login',
					data:{account:account,password:password,captcha:captcha},
					success:function(data){
						if(BASE.JS_RESULT.SUCCESS==data){
						    window.location.href='${_PATH}/index';
						}else{
							$("#submitLogin").removeAttr('disabled');
							$.tips({
			 	    			title:"提示",
			 	    			content:data,
			 	    			iconSmall:"icon-warning-sign",
			 	    			timeout:3000
			 	    		});
						}
					}
				});
	    	}
	    });
	</script>

</body>
</html>