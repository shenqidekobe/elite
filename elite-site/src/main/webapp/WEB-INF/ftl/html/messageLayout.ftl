<@extend name="layout">
    <@block name="cs">
    </@block>
    <@block name="body">
    <#--头部-->
        <@accounthead opt=""/>
		
    <#--正文开始-->
    <div class="content" id="homepage">
        <div class="location-nav">
            <span style="font-size: 18px;">个人主页</span>&nbsp;&gt;&nbsp;<span style="color:#2CB7C9;">我的项目</span>
        </div>

        <div class="content-box">
            <#--左边的资料区-->
           
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
    </@block>
</@extend>