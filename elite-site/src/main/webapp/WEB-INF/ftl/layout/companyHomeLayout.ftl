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
            <span style="font-size: 18px;">个人主页</span>&nbsp;&gt;&nbsp;
            <span style="color:#2CB7C9;" id="crumbsText">我的项目</span>
        </div>
        <div class="content-box">
            <#--左边的资料区-->
            <div style="float:left;">
                <div class="left-column" style="padding-bottom: 26px;" id="projectLeft1">
                    <div class="circle-box">
                        <#if obj.company.stared==true>
                    	    <div class="gold_icon"></div>
                    	</#if>
                        <div class="head-label">项目方</div>
	                    <#if session().memberId?exists??&&obj.basic.photoId??>
		                    <div class="column-head">
		                    	<img src="${obj.basic.memberPhoto.path}">
		                    </div>
		                <#else>
	                        <div class="column-head">
	                        	<img src="${_PATH}/res/images/default.jpg">
	                        </div>
	                    </#if>
                    </div>
                    <div class="username">
                        <span class="name_name">${obj.nickName}</span>
                        <span class="name_title">${obj.basic.memberSign}</span>
                    </div>
                    <#--图标区-->
                    <#--口号-->
                    <#--<p class="slogan"></p>-->
                    <p></p>
                    <div class="split-line"></div>
                    <div style="padding:10px;">
                        <div class="index-title" style="width:20%;" >诚信度</div>
                        <div class="index-title" style="width:40%;">进行中的项目</div>
                        <div class="index-title" style="width:40%;">托管的费用</div>
                    </div>

                    <div style="padding:0 10px;margin-top:10px;">
                        <div class="index-data" style="width:20%;">${obj.basic.integrity}</div>
                        <div class="index-data" style="width:35%;">${obj.handProjectCount}</div>
                        <div class="index-data" style="width:45%;">
	                        <#setting number_format="currency"/>
	                        ${obj.trustAmount?string.currency}
                        </div>
                    </div>

                </div>

                <div class="left-column column-b" id="projectLeft2">
                    <ul class="left-menu column-b-ul" style="padding-top:10px;">
                        <li data="myProject" style="cursor:pointer" class="active_color"><img class="menu-icon" src="${_PATH}/res/images/ceo/myProject_select.png">我的项目<span class="active-menu"></span></li>
                        <li data="settlement" style="cursor:pointer"><img class="menu-icon" src="${_PATH}/res/images/ceo/settlement_noselect.png">结算管理<span class="active-menu"></span></li>
                        <li data="myInfo" style="cursor:pointer"><img class="menu-icon" src="${_PATH}/res/images/ceo/myInfo_noselect.png">我的资料<span class="active-menu"></span></li>
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