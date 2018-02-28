<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>云英汇，领先的IT技术人才共享平台</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
    <meta name="keywords" content="云英汇">
    <meta name="description" content="云英汇">
    <link rel="stylesheet" type="text/css" href="${_PATH}/res/css/login.css">
</head>
<body>
	<div id="login">
		<div class="login_head">
				<div class="logoBox">
					<div class="imgBox">
						<a href="#"><img src="${_PATH}/res/img/logo.png" alt="" class="logo_img"></a>
					</div>
				</div>
				<div class="head_bg"></div>
	    </div>
	    <div class="login_content">
	        
	        <div class="login_form">
	        	<div class="logo_text">系统运营中心</div>
	            <form action="" method="post">
	                <div class="form_group"><img src="${_PATH}/res/img/user.png" alt="" class="icon_img"><input type="text" name="account" id="account" placeholder="用户名"></div>
	                <div class="form_group"><img src="${_PATH}/res/img/password.png" alt="" class="icon_img"><input type="password" name="password" id="password"  placeholder="密码"></div>
	                <div class="form_group"><img src="${_PATH}/res/img/code.png" alt="" class="icon_img">
		                <input type="text" name="captcha" id="captcha" maxlength="4" placeholder="验证码">
		                <img class="code" id="captchaImg" alt="验证码" src="${_PATH}/captcha/produce">
		                <button type="button" class="changeBtn" id="replaceCode">换一张</button>
	                </div>
	                <div class="form_group">
		                <span class="error" style="display:none;">错误提示</span>
		                <button type="button" class="loginBtn" id="submitLogin">登录</button>
	                </div>
	            </form>
	            <div class="platform">云英汇，领先的IT技术人才共享平台</div>
	            <div class="login_copyright">&copy;Copyright by yunyinghui.com</div>
	        </div>
	    </div>
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
	<script type="text/javascript">
	    $(function(){
	    	console.info(localStorage.LOGINNAME);
	    	$("#account").val(localStorage.LOGINNAME);
	    	$("#password").val(localStorage.PASSWORD);
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
				var pwd=$.trim($("#password").val());
				var captcha=$.trim($("#captcha").val());
				password=encrypt.DES(pwd);
				if(account==""){
					$(".error").text("请输入登录帐号");
					$(".error").show();
					$("#account").focus(function(){
						$(".error").hide();
					});
					return false;
				}else if(password==""){
					$(".error").text("请输入登录密码");
					$(".error").show();
					$("#password").focus(function(){
						$(".error").hide();
					});
					return false;
				}else if(captcha==""){
					$(".error").text("请输入验证码");
					$(".error").show();
					$("#captcha").focus(function(){
						$(".error").hide();
					});
					return false;
				}
				$("#submitLogin").attr('disabled',"true");
				Ajax.post({
					url:'${_PATH}/login',
					data:{account:account,password:password,captcha:captcha},
					success:function(data){
						if(BASE.JS_RESULT.SUCCESS==data){
							localStorage.LOGINNAME=account;
							localStorage.PASSWORD=pwd;
						    window.location.href='${_PATH}/index';
						}else{
							$("#submitLogin").removeAttr('disabled');
							$(".error").text(data);
							$(".error").show();
						}
					}
				});
	    	}
	    });
	</script>

</body>
</html>