<@extend name="layout">
    <@block name="cs">
	   <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/inviteCode.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <#--正文开始-->
	<div class="y-bd">
	    <div class="inviteCode">
	        <div class="inviteCode_a">
	            <div class="inviteCode_a_content">
	                <div class="code_box">
	                    <span class="code_text">我的邀请码</span>
                        <#if session().memberId?exists>
                            <div class="code_btn">
                            <#if obj??>
		                        <div class="txt_code_div" style="display: block">
		                            <span class="txt_code">${obj.inviteCode}</span>
		                            <span class="txt_time">有效期至<#if obj.expireTime??>${obj.expireTime?string("yyyy-MM-dd")}<#else>2020-12-29</#if></span>
		                        </div>
	                        <#else>
	                            <span class="txt_none" style="display: block">暂无</span>
	                        </#if>
                            </div>
	                    <#else>
	                        <div class="code_btn" onclick="window.location.href='${_PATH}/login?rps=invite'">
	                             <span class="txt_btn" style="display: block">登录查看</span>
	                        </div>
                        </#if>
	                </div>
	                <div class="inviteCode_bg"></div>
	            </div>
	        </div>
	
	        <div class="inviteCode_b">
	            <div class="inviteCode_b_content">
	            	<div class="where">
	                    <h1>从哪获得邀请码？<span class="h1_line"></span></h1>
	                    <div class="weChat_box">
	                    	<img src="${_PATH}/res/images/index/wechat.jpg" alt="" width="84" height="84">
	                    	<span>扫码得邀请码</span>
	                    </div>
	                    <img class="where_icon" src="${_PATH}/res/images/message/where.png" alt="">
	                    <div class="where_dl_box">
	                        <dl class="where_dl">
	                            <dd class="clearfloat"><span class="dd_icon"></span>
	                                <span class="dd_text">微信公众号每日只发放50个邀请码，9:00开始，每人每日限领1个（页脚有二维码），去晚了又要等一天~</span>
	                            </dd>
	                            <dd class="clearfloat"><span class="dd_icon"></span>
	                                <span class="dd_text">已经审核通过的精英和CTO，都至少有一个邀请码，试着问问您身边的大牛吧~</span>
	                            </dd>
	                            <dd class="clearfloat"><span class="dd_icon"></span>
	                                <span class="dd_text">其他：合作投资人、猎头、内部员工等都会有邀请码，快去巴结吧~</span>
	                            </dd>
	                        </dl>
	                    </div>
	                </div>
	            
	                <div class="why">
	                    <h1>为什么设置邀请码？<span class="h1_line"></span></h1>
	                    <img class="why_icon" src="${_PATH}/res/images/message/why.png" alt="">
	                    <div class="why_dl_box">
	                        <dl class="why_dl">
	                            <dd class="clearfloat"><span class="dd_icon"></span>
	                                <span class="dd_text">云英汇为创造高质量的精英技能合作平台，暂时只接受邀请码注册制，在严格审核的基础上，把控精英的人选，帮助精英进行项目和任务合作时更为顺畅，保障项目的完美交付；</span>
	                            </dd>
	                            <dd class="clearfloat"><span class="dd_icon"></span>
	                                <span class="dd_text">所有通过邀请码注册进来的用户都将作为早期用户，在后续的平台运营中会有很多福利和权利，比如免费讲座、优先推荐项目、免费活动等等一系列福利，敬请期待吧~~</span>
	                            </dd>
	                        </dl>
	                    </div>
	
	                </div>
	                
	            </div>
	        </div>
	    </div>
	</div>

    </@block>

    <@block name="script">
    </@block>
</@extend>