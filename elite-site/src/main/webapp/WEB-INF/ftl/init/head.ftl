<#global mainhead=mainhead/>
<#macro accounthead opt>
    <#local opt=opt/>
    
<div class="wrap-head" id="accountHead">
	<div class="head_bg"></div>
    <div class="head">
                <a href="${_PATH}/">
                    <img src="${_PATH}/res/images/logo.png" class="logo">
                </a>
				<#if session().memberId?exists>
					<ul class="tasks">
					    <li><a class="ative" href="${_PATH}/">首页</a></li>
					    <#if session().currentType=='company'>
							<li id="projectLi">
							    <a href="${_PATH}/project/publish">发布项目</a>
							</li>
						</#if>
						<#if session().currentType=='cto'||session().currentType=='elite'>
							<li id="eliteLi">
							    <a href="${_PATH}/hall">认领任务</a>
							</li>
						</#if>
						<li><a href="${_PATH}/help">帮助</a></li>
					</ul>
				<#else>
					<ul class="tasks">
						<li><a class="ative" href="${_PATH}/">首页</a></li>
						<li id="projectLi">
							<a href="${_PATH}/login">发布项目</a>
						</li>
						<li id="eliteLi">
							<a href="${_PATH}/login?rps=task">认领任务</a>
					    </li>
						<li><a href="${_PATH}/help">帮助</a></li>
					</ul>
				</#if>
                <#if session().memberId?exists>
	                <div class="email">
	                    <a href="javascript:void(0);" id="message_btn">
	                        <div style="position: relative;">
	                            <img src="${_PATH}/res/images/email_icon.png">
	                            <span class="<#if unreadCount gt 0>point</#if>"></span>
	                        </div>
	                    </a>
	                </div>
	                <div id="userHeader">
	                    <div class="head-portrait" data="${session().currentType}">
		                     <#if session().memberId?exists&&session().header??>
		                        <img src="${session().header}" id="headerImg">
		                     <#else>
		                         <img src="${_PATH}/res/images/default.jpg" id="headerImg">
		                     </#if>
                         </div>
		                 <div class="nav-triangle">
		                    <div class="nav-trigger">
		                    </div>
		                 </div>
		                 <div class="downbox" style="display:none;">
								<#if session().currentType=='cto'>
								<ul id="downUlCto">
									<li class="active_bg" data="main" id="li_mpro"><span class="liIcon main_select"></span><span class="liText active_color">个人主页</span></li>
									<li data="code" id="li_code"><span class="liIcon code_noselect"></span><span class="liText">邀请码</span></li> 
									<li data="set" id="li_set"><span class="liIcon set_noselect"></span><span class="liText">账户设置</span></li>
								<#elseif session().currentType=='elite'>
								<ul id="downUlElite">
									<li class="active_bg" data="main" id="li_mtask"><span class="liIcon main_select"></span><span class="liText active_color">个人主页</span></li>
									<li data="code" id="li_code"><span class="liIcon code_noselect"></span><span class="liText">邀请码</span></li> 
									<li data="set" id="li_set"><span class="liIcon set_noselect"></span><span class="liText">账户设置</span></li>
								<#elseif session().currentType=='company'>
								<ul id="downUlCompany">
									<li data="main"  class="active_bg" id="li_mpro"><span class="liIcon main_select"></span><span class="liText active_color">个人主页</span></li>
									<li data="set" id="li_set"><span class="liIcon set_noselect"></span><span class="liText">账户设置</span></li>
									<#--<li id="li_jsele"><span class="liIcon code_noselect"></span><span class="liText">结算管理</span></li>
									<li id="li_minfo"><span class="liIcon code_noselect"></span><span class="liText">我的资料</span></li>-->
								<#elseif session().currentType=='partnerCompany'>
								<ul id="downUlPartnerCompany">
									<li data="main" class="active_bg" id="li_champion"><span class="liIcon main_select"></span><span class="liText active_color">个人主页</span></li>
									<li data="invite" id="li_inreg"><span class="liIcon invite_noselect"></span><span class="liText">邀请注册</span></li>
									<li data="earnings" id="li_jsele"><span class="liIcon earnings_noselect"></span><span class="liText">收益中心</span></li>
								<#elseif session().currentType=='partnerElite'>
								<ul id="downUlPartnerElite">
									<li data="main" class="active_bg" id="li_champion"><span class="liIcon main_select"></span><span class="liText active_color">个人主页</span></li>
									<li data="invite"  id="li_inreg"><span class="liIcon invite_noselect"></span><span class="liText">邀请注册</span></li>
									<li data="earnings" id="li_jsele"><span class="liIcon earnings_noselect"></span><span class="liText">收益中心</span></li>
								</#if>
                                <li id="li_switch" data="switch"><span class="liIcon switch_noselect"></span><span class="liText">切换身份</span></li>
								<li id="li_logout" data="exit"><span class="liIcon exit_noselect"></span><span class="liText">退出登录</span></li>
								
							</ul>
						</div>
	             </div>
	             <#else>
	                <div class="new_login">
                		<a href="${_PATH}/login?ts=${RequestParameters["ts"]}" class="login_login">登录</a>
                		<#if RequestParameters["ts"]=='partnerCompany'||RequestParameters["ts"]=='partnerElite'>
                		   <a href="${_PATH}/register/p?ts=${RequestParameters["ts"]}" class="login_register">注册</a>
                		<#else>
                		   <a href="${_PATH}/register?ts=${RequestParameters["ts"]}" class="login_register">注册</a>
                		</#if>
	                </div>
                </#if>
            </div>
       
</div>
<div class="switchWindow"></div>
</#macro>
<#global accounthead=accounthead/>