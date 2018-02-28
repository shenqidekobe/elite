<@extend name="layout">
    <@block name="cs">
        <link rel="stylesheet" href="${_PATH}/res/style/css/password/password.css"/>
    </@block>
    <@block name="body">
        <#--头部-->
        <@accounthead opt=""/>
		<div class="coenten">
			<div class="tpbar">
				<ul>
					<li class="span-left"><span class="active">1</span><a href="javascript:void(0);" class="active ">验证手机号</a></li>
					<li class="span-cont"><span class="active">2</span><a href="javascript:void(0);">输入新密码</a></li>
					<li class="span-rt"><span ><img src="${_PATH}/res/images/fgh.png" alt=""></span><a href="javascript:void(0);">完成</a></li>
				</ul>
				<span class="hr1 hr active"></span>
				<span class="hr2 hr"></span>
			</div>
			<div class="password">
				<h1><span class="fdj"></span>密码找回</h1>
				<form id="forgetForm" name="forgetForm">
					<div class="labe">
						<label for="">登录名</label>
						<input type="tel" maxlength="11" readonly value="${accountSession?substring(0,4)}****${accountSession?substring(8,11)}" style="border:none">
					</div>	
					<div class="labe">
						<label for="">设置新密码</label>
						<input type="password" required="required" id="newPass" placeholder="请输入新密码">
					</div>	
					<div class="labe">
						<label for="">确认密码</label>
						<input type="password" required="required" id="newPass1" placeholder="请再次输入密码">
					</div>
					<div class="Error" id="tipsError"></div>
					<button class="input-yel" type="button">下一步</button> 
				</form>
			</div>
		</div>
    </@block>

    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/forget2.js"></script>
    </@block>
</@extend>