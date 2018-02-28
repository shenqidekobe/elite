<@extend name="layout">
    <@block name="cs">
    
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <@optmodal title="删除提示" ask="您确定删除?"/>
    <@tipmodal tip="恭喜您操作成功"/>
    <#--正文开始-->
    <div class="content" id="homepage">
    <#--<div class="content">-->
        <div class="location-nav">
            <span style="font-size: 18px;cursor:pointer;" id="crumbs_main">个人主页</span>&nbsp;&gt;&nbsp;
            <span style="color:#2CB7C9;cursor:pointer;" id="crumbs_mytask" data="${obj.projectId}">任务管理</span>&nbsp;&gt;&nbsp;
            <span style="color:#2CB7C9;" id="crumbs_detail">任务详情</span>
        </div>
        <div class="content-box">
            <#--左边的资料区-->
            <div style="float:left;">
                <div class="left-column cto_task_div ">
                    <ul class="left-menu cto_task_ul">
                        <li data="task_detail"><span class="icon_span detail_select"></span><span class="text_span active_color">任务详情</span><span class="line_span active_line"></span></li>
                        <#if obj.eliteMemberId??>
                        	<li data="project_material"><span class="icon_span material_noselect "></span><span class="text_span">文件管理</span><span class="line_span"></span></li>
                        </#if>
                        <li data="project_log"><span class="icon_span log_noselect"></span><span class="text_span">任务日志</span><span class="line_span"></span></li>
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
</@extend>