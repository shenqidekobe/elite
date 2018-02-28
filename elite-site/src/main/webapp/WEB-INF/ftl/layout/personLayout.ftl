<@extend name="layout">
    <@block name="cs">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>

    <#--正文开始-->
    <div class="content" id="personRecommend">
        <div class="location-nav"><span style="font-size: 18px;">人才推荐方页</span>&nbsp;&gt;&nbsp;<span style="color:#2CB7C9;">邀请注册</span></div>
        <div class="content-box">

        <#--左边的资料区-->
            <div class="left-column">
                <div class="circle-box">
                    <div class="column-head"></div>
                </div>

                <div class="username">
                    <span style="font-size: 20px;">Olivia</span><span style="font-size: 14px;margin-left:15px;">上海</span>
                </div>

                <div class="split-line"></div>

                <div>
                    <ul class="left-menu">
                        <li class="active-menu"><span class="menu-icon" style="background: url('${_PATH}/res/images/invite_icon.png') no-repeat -5px -5px"></span>邀请注册</li>
                        <li><span class="menu-icon"></span>精英管理</li>
                        <li><span class="menu-icon"></span>收益中心</li>
                        <li><span class="menu-icon"></span>账户安全</li>
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


    </@block>
    <@block name="script">
    </@block>
</@extend>