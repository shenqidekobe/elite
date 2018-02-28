<@extend name="layout">
    <@block name="cs">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>

    <#--正文开始-->
    
   <div class="content" id="homepage">
        <div class="location-nav">
            <span style="font-size: 18px;">个人主页</span>&nbsp;&gt;&nbsp;
            <span style="color:#2CB7C9;" id="crumbsText">我的项目</span>
        </div>

        <div class="content-box">
            <#--左边的资料区-->
            <div style="float:left;">
               <div class="left-column" style="padding-bottom: 26px;">
                    <div class="circle-box"> 
                        <img src="${_PATH}/res/images/dui.png" class="gold-use">
                        <#--<img src="${_PATH}/res/images/huizan.png" class="gold-user">-->
                        <#if session().memberId?exists??&&obj.basic.photoId??>
                        	<div class="column-head" style="background:url('${obj.basic.memberPhoto.path}') no-repeat;border-radius: 60px"></div>
				        </#if>
                    </div>

                    <div class="username">
                        <span style="font-size: 20px;vertical-align:sub;">${obj.basic.areaName}</span>
						<#if obj.elite.taked>
	                        <span class="slider"><span class="fast"><i class="slippage"></i></span></span>
	                        <span id="workFlag" data="true" style="font-size: 14px;margin-left:10px;color:#9B9B9B;">接活</span>
                        <#else>
	                        <span class="slider"><span class="fast"><i class="slippage"></i></span></span>
	                        <span  id="workFlag" data="false" style="font-size: 14px;margin-left:10px;color:#9B9B9B;">不接活</span>
                        </#if>
                    </div>
			
					<p class="al">${job.jobRole}</p>
                    <ul class="afine">
                    	<li>诚信度:&nbsp;<span>${obj.basic.integrity}</span></li>
                    	<li class="fine">精英币:&nbsp;<span>${account.eliteCoin}</span>币</li>
                    </ul>
                   <#--<div class="sortf"><img src="${_PATH}/res/images/soritef.png" alt=""></div>-->
                    <div class="split-line"></div>

                    <div style="padding:10px;">
                        <div class="index-title text-left">收益</div>
                        <div class="index-title text-center" style="width:34%;">接活</div>
                        <div class="index-title text-right">获得关注</div>
                    </div>

                    <div style="padding:0 20px;margin-top:10px;">
                        <div class="index-data text-left">￥<span>${account.totalIncome}</span></div>
                        <div class="index-data text-center" style="width:34%;">${obj.taskCount}</div>
                        <div class="index-data text-right">${attentionCount}</div>
                    </div>
                </div>

                <div class="left-column">
                    <ul class="left-menu" style="padding-top:10px;">
                        <li data="myProject" class="active_color"><span class="menu-icon" style="background: url('${_PATH}/res/images/invite_icon.png') no-repeat -5px -5px"></span>我负责的项目</li>
                        <li data="myTask"><span class="menu-icon"></span>我认领的任务<#--<span class="concerns" style="right:60px">9</span>--></li>
                        <#--
                        <li data="myTeam"><span class="menu-icon"></span>我的团队</li>
                        -->
                        <li data="myAttention"><span class="menu-icon"></span>我的关注</li>
                        <li data="settlement"><span class="menu-icon"></span>结算管理</li>
                        <li data="myInfo"><span class="menu-icon"></span>我的资料</li>
                    </ul>
                </div>
            </div>
    
            <#--右边的操作区-->
            <div class="right-opt">
                <@block name="rightContent">
                </@block>
            </div>

        </div>
    </div>
    <@block name="flow"></@block>
    </@block>
    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/member/homeLayout.js')}"></script>
    </@block>
</@extend>