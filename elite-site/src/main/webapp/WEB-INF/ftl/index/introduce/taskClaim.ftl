<@extend name="layout">
    <@block name="cs">
	   <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
	   <link rel="stylesheet" href="${_PATH}/res/style/css/index/claimTask.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <#--正文开始-->
    <div class="y-bd">
	    <div id="claimTask">
	        <div class="claimTask_bg">
	            <div class="title">认领任务的流程</div>
	        </div>
	        <div class="claimTask_text">
	            <div class="conditions">
	                <div class="titleDiv clearfloat">
		                <span class="titleDiv_text">精英认领任务的必要条件</span>
		                <#if session().memberId?exists>
		                    <a href="${_PATH}/member/index" class="in_btn">立即入驻</a>
		                <#else>
		                    <a href="${_PATH}/register?ts=elite" class="in_btn">立即入驻</a>
		                </#if>
	                </div>
	                <div class="conditions_box">
	                    <div class="conditions_content">注册成为平台精英方，完善个人资料，平台审核通过即可（审核时间1-3个工作日）目前平台暂时接受以‘搭班子，做产品’为主的互联网IT技术人才入驻：产品、设计、开发、测试四大类，其他角色后续开放</div>
	                </div>
	            </div>
	            <div class="skills">
	                <div class="title">成功认领任务的技巧：</div>
	                <div class="skills_box">
	                    <div></div>
	                    <ul class="skills_content">
	                        <li class="clearfloat"><span class="li_icon"></span><span class="li_text">CTO指派精英后，会以站内箱和短信通知；</span></li>
	                        <li class="clearfloat"><span class="li_icon"></span><span class="li_text">联系方式一定要正确，CTO可能会联系你进行沟通；</span></li>
	                        <li class="clearfloat"><span class="li_icon"></span><span class="li_text">资料尽可能精准详细，这样平台或他人可以更精准地指派任务；</span></li>
	                        <li class="clearfloat"><span class="li_icon"></span><span class="li_text">多关注CTO，任务都是由签约的CTO对项目进行分解的，所以要勾搭好哦~</span></li>
	                        <li class="clearfloat"><span class="li_icon"></span><span class="li_text">主动沟通，细致负责，CTO下次有任务还会找你，一人就可以在多个团队中游走，不差活~</span></li>
	                    </ul>
	                </div>
	            </div>
	            <div class="problems">
	                <div class="title">常见问题：</div>
	                <div class="problems_group">
	                    <ul>
	                        <li class="clearfloat marginbottom8"><img src="${_PATH}/res/images/task/a_icon.png"><span>认领任务需要交押金吗?</span></li>
	                        <li class="clearfloat marginbottom10"><img src="${_PATH}/res/images/task/q_icon.png"><span>不需要，平台实名制精英的信用作为背书，暂不收取任何费用押金</span></li>
	                    </ul>
	                </div>
	                <div class="problems_group">
	                    <ul>
	                        <li class="clearfloat marginbottom8"><img src="${_PATH}/res/images/task/a_icon.png"><span>CTO可以认领任务吗？</span></li>
	                        <li class="clearfloat marginbottom10"><img src="${_PATH}/res/images/task/q_icon.png"><span>可以，CTO同样属于精英，除去竞标项目之外，也可以认领任务。</span></li>
	                    </ul>
	                </div>
	                <div class="problems_group">
	                    <ul>
	                        <li class="clearfloat marginbottom8"><img src="${_PATH}/res/images/task/a_icon.png"><span>任务做完后，什么时候可以拿到钱？</span></li>
	                        <li class="clearfloat marginbottom10"><img src="${_PATH}/res/images/task/q_icon.png"><span>任务酬金到达精英账户的条件是：项目方验收该任务所属项目阶段，任务酬金自动到达精英账户（除去质保金），质保期结束后，质保金也将自动到帐。</span></li>
	                    </ul>
	                </div>
	                <div class="problems_group">
	                    <ul>
	                        <li class="clearfloat marginbottom8"><img src="${_PATH}/res/images/task/a_icon.png"><span>任务质保期是什么意思？周期多长？</span></li>
	                        <li class="clearfloat marginbottom10 "><img src="${_PATH}/res/images/task/q_icon.png"><span>质保期是精英方完成任务后，需要为任务提供需求内的后期服务，例如改bug等。<br/>质保期周期（天）=（任务验收的时间—任务启动的时间）/2，不足1天算1天。</span></li>
	                    </ul>
	                </div>
	                <div class="problems_group">
	                    <ul>
	                        <li class="clearfloat marginbottom8"><img src="${_PATH}/res/images/task/a_icon.png"><span>任务交付后，CTO不给钱怎么办？</span></li>
	                        <li class="clearfloat marginbottom10"><img src="${_PATH}/res/images/task/q_icon.png"><span>任务酬金由平台待托管，任务酬金不由CTO直接支付。</span></li>
	                    </ul>
	                </div>
	            </div>
	            <div class="more">
	                <div class="btn_div clearfloat"><a class="check_btn" href="${_PATH}/help">查看更多问题</a><a class="return_btn" href="${_PATH}/hall">返回认领任务</a></div>
	                <div class="reminder_div">
	                    <img src="${_PATH}/res/images/task/reminder_icon.png"><span>平台有项目管家团队，针对每个项目有专属的项目经理，任务过程中的任何问题都可以及时咨询、投诉、给予建议。</span>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
    </@block>

    <@block name="script">
    </@block>
</@extend>