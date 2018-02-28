<@extend name="layout">
    <@block name="cs">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
		
    <#--正文开始-->
    <div class="content" id="homepage">
    	<div id="messageMain">
        <div class="location-nav">
            <span class="text1" id="index">个人主页</span> &gt; 
            &nbsp;<span class="text2" >消息箱</span>
        </div>

        <div class="content-box">
            <#--左边的资料区-->
            <div class="pagebox">
				<div class="mbox-left">
					<ul>
						<li data="system" imgCss="activstm"><span class="system1 img_span"></span><a class="active_color" href="javascript:void(0);">系统消息 </a><span class="active_line line"></span></li>
						<#if session().currentType=='company'>
						    <li data="project" imgCss="projec"><span class="project img_span"></span><a href="javascript:void(0);">项目消息</a><span class="line"></span></li>
						</#if>
						<#if session().currentType=='cto'||session().currentType=='elite'>
						    <li data="project" imgCss="projec"><span class="project img_span"></span><a href="javascript:void(0);">任务消息</a><span class="line"></span></li>
						</#if>
						<li data="activity" imgCss="activctivy"><span class="activity img_span"></span><a href="javascript:void(0);">活动通知</a><span class="line"></span></li>
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
    </div>
    <@block name="flow"></@block>
    </@block>
    <@block name="script">
    </@block>
</@extend>