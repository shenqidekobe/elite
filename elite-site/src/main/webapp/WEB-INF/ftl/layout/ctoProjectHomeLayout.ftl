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
            <span style="font-size: 18px;" id="crumbs_main">个人主页</span>&nbsp;&gt;&nbsp;
            <span style="color:#2CB7C9;cursor:pointer;" id="crumbs_myproject" data="${project.id}">我的项目</span>&nbsp;&gt;&nbsp;
            <span style="color:#2CB7C9;" id="crumbs_detail">项目详情</span>
        </div>
        <div class="content-box">
            <#--左边的资料区-->
            <div style="float:left;">
                <div class="left-column cto_ul_div" >
                    <ul class="left-menu cto_ul" style="padding-top:10px;">
                    	<#if record?? && record.status=='tender_normal'>
	                        <li data="project_detail" class="detail"><span class="icon_span detail_select"></span><span class="text_span active_color">项目详情</span><span class="line_span active_line"></span></li>
	                        <li data="task_assign" class="task"><span class="icon_span task_noselect"></span><span class="text_span">任务分配</span><span class="line_span"></span></li>
	                        <#--<li data="project_demand" class="demand"><span class="icon_span demand_noselect"></span><span class="text_span">需求修改</span><span class="line_span"></span></li>-->
	                        <li data="project_weekly" class="weekly"><span class="icon_span weekly_noselect"></span><span class="text_span">项目周报</span><span class="line_span"></span></li>
	                        <li data="project_material" class="material"><span class="icon_span material_noselect"></span><span class="text_span">文件管理</span><span class="line_span"></span></li>
	                        <li data="project_log" class="log"><span class="icon_span log_noselect"></span><span class="text_span">项目日志</span><span class="line_span"></span></li>
	                     <#else>
	                        <li data="project_detail" class="detail"><span class="icon_span detail_select"></span><span class="text_span active_color">项目详情</span><span class="line_span active_line"></span></li>
	                        <li data="project_material" class="material"><span class="icon_span material_noselect"></span><span class="text_span">文件管理</span><span class="line_span"></span></li>
	                        <li data="project_log" class="log"><span class="icon_span log_noselect"></span><span class="text_span">项目日志</span><span class="line_span"></span></li>
                        </#if>
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