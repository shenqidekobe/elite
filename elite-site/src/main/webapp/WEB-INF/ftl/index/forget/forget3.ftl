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
					<li class="span-rt"><span class="active"><img src="${_PATH}/res/images/fgh.png" alt=""></span><a href="javascript:void(0);">完成</a></li>
				</ul>
				<span class="hr1 hr active"></span>
				<span class="hr2 hr active"></span>
			</div>
			<div class="password">
				<div class="password-content">
				<img src="${_PATH}/res/images/groud.png" alt="">
				<div class="successful">
					<h1>密码设置成功！</h1>
					<p><span id="down_time">6s</span>后自动登录</p>
					<button type="button" id="promptly_login">立即登录</button>
				</div>
				</div>
			</div>
		</div>
    </@block>

    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/forget3.js"></script>
    </@block>
</@extend>