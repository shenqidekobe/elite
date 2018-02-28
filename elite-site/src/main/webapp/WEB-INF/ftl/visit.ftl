<!DOCTYPE html>
<html lang="en">
<head>
	<title>云英汇-您身边的技术合伙人</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
    <meta name="keywords" content="云英汇"/>
    <meta name="description" content="云英汇"/>
    <link rel="shortcut icon" href="${_PATH}/res/images/favicon.png" type="image/x-icon"/>
	<link rel="stylesheet" href="${_PATH}/res/style/css/visit/style.css">
    <script>var ctx= '${_PATH}';</script>
</head>
<body>
<div class="invite">
	<div class="invite-bgrd">
		<div class="inviter-box">
			<div class="log">
			    <input type="hidden" id="sessionId" value="${(session().memberId)}">
				<img src="${_PATH}/res/images/huiyilog.png" alt="">
			</div>
			<div class="invite-box">
				<#--<h3>内测中，只接受邀请码登录</h3>-->
				<h3>云英汇1.0版</h3>
				<form id="visitForm" name="visitForm">
					<input type="text" placeholder="请输入邀请码" id="visitCode" name="visitCode">
					<input type="button" value="立即进入" id="startBtn">
				</form>
				<div class="errormsg" style="display:none;">
				</div>
				<p>你身边优秀的技术精英和投资人会有邀请码，问问看~~~</p>
			</div>
		</div>
		<div class="invite-dog">
		    <img src="${_PATH}/res/images/doglog-2.png" alt="">
		</div>
	</div>
</div>
<script src="${_PATH}/res/script/plugin/jquery.min.js"></script>
<script src="${_PATH}/res/script/plugin/ajax.js"></script>
<script src="${_PATH}/res/script/plugin/jsonAjax.js"></script>
<script src="${_PATH}/res/script/myjs/visit.js"></script>
</body>
</html>