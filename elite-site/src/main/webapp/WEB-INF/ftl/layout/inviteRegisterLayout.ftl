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
                    <div class="column-head">
                    	<img src="" alt=""> 
                    </div>
                </div>

                <div class="username">
                    <span style="font-size: 20px;">Olivia</span><span style="font-size: 14px;margin-left:15px;">上海</span>
                </div>

                <div class="split-line"></div>

                <div class="invite-l-box">
                    <ul class="ceoInfo-l-a-content">
				        <li class="clearfloat select">
				            <div class="li-box">
				                <span class="icon-box invite-a-s"></span>
				                <span class="txt-box">邀请注册</span>
				            </div>
				        </li>
				
				        <li class="clearfloat">
				            <div class="li-box">
				                <span class="icon-box invite-b"></span>
				                <span class="txt-box">精英管理</span>
				            </div>
				        </li>
				
				        <li class="clearfloat">
				            <div class="li-box">
				                <span class="icon-box invite-c"></span>
				                <span class="txt-box">收益中心</span>
				            </div>
				        </li>
				
				        <li class="clearfloat">
				            <div class="li-box">
				                <span class="icon-box invite-d"></span>
				                <span class="txt-box">我的资料</span>
				            </div>
				        </li>
				        
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