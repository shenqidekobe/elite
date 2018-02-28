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
					<li class="span-cont"><span>2</span><a href="javascript:void(0);">输入新密码</a></li>
					<li class="span-rt"><span ><img src="${_PATH}/res/images/fgh.png" alt=""></span><a href="javascript:void(0);">完成</a></li>
				</ul>
				<span class="hr1 hr"></span>
				<span class="hr2 hr"></span>
			</div>
			<div class="password">
				<h1><span class="fdj"></span>密码找回</h1>
				<form id="forgetForm" name="forgetForm">	
					<div class="labe">
						<label for="">手机号</label>
						<input type="tel" maxlength="11" id="account" placeholder="请输入注册时使用的手机号"/>
					</div>
					<div class="labe">
						<label for="">验证码</label>
						<input type="text" required="required" id="verifycode" placeholder="请输入短信验证码">
						<input type="button" id="sendMsgBtn" value="发送验证码">
					</div>
					<div class="Error" id="tipsError"></div>
					<button class="input-yel" type="button">下一步</button> 
				</form>
			</div>
		</div>		
    </@block>

    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/forget.js"></script>
    </@block>
</@extend>