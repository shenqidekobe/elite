<#macro accountheadChannel opt2>
    <#local opt2=opt2/>
    
	<div class="channel_head" id="channel_head">
        <div class="head_bg"></div>
        <div class="headMain">
            <div class="logoBox">
                <a href="${_PATH}/index"><img class="logo" src="${_PATH}/res/images/index/logo.png" width="154" height="72"></a>
                <div class="logoLine"></div>
                <div class="logoText">渠道方合作</div>
            </div>
            <ul class="headNav" id="headNav">
                <li data="company" class="active_color">我有项目推荐</li>
                <li data="person">我有人才推荐</li>
                <li data="help">帮助中心</li>
            </ul>
            <ul class="headLogin" id="headLogin" style="display: none">
                <#if !session().memberId?exists>
	                <li style="margin-right: 20px"><a href="${_PATH}/login?ts=${RequestParameters["ts"]}"><button type="button" class="login">登录</button></a></li>
	                <li><a href="${_PATH}/register/p?ts=${RequestParameters["ts"]}"><button type="button" class="register">注册</button></a></li>
                </#if>
            </ul>
        </div>
    </div>

</#macro>
<#global accountheadChannel=accountheadChannel/>