<@extend name="layout">
    <@block name="cs">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>

    <#--正文开始-->
    <div class="content eliteInfo" id="homepage">
    <#--<div class="content">-->
        
        <div class="content-box">
            <#--左边的资料区-->
            <div style="float:left;" class="left_container eliteInfo_left">
            	
            	<div class="location-nav">
		            <span style="font-size: 18px;cursor:pointer" id="myMain">个人主页</span>&nbsp;&gt;&nbsp;
		            <span style="color:#2CB7C9;" id="crumbsText">我的资料</span>
		        </div>
            		
                <div class="personage_nav" id="personage_nav"  style="padding-bottom: 26px;">
                    <div class="ceoInfo-l-a">
					    <ul class="ceoInfo-l-a-content" style="margin-top:24px;">
					        <li class="clearfloat select" data="basic" id="icon-a-s">
					            <div class="li-box">
					                <span class="icon-box icon-a-s"></span>
					                <span class="txt-box active_color">基本信息</span>
					            </div>
					        </li>
							<li class="clearfloat" data="current">
					            <div class="li-box">
					                <span class="icon-box icon-b"></span>
					                <span class="txt-box">当前状态</span>
					            </div>
					        </li>
					        <li class="clearfloat" data="selfDescrip">
					            <div class="li-box">
					                <span class="icon-box icon-h"></span>
					                <span class="txt-box">自我描述</span>
					            </div>
					        </li>
							<li class="clearfloat" data="experience">
					            <div class="li-box">
					                <span class="icon-box icon-c"></span>
					                <span class="txt-box">项目经历</span>
					            </div>
					        </li>
							<li class="clearfloat" data="education">
					            <div class="li-box">
					                <span class="icon-box icon-d"></span>
					                <span class="txt-box">教育经历</span>
					            </div>
					        </li>
					        <li class="clearfloat" data="credit">
					            <div class="li-box">
					                <span class="icon-box icon-e"></span>
					                <span class="txt-box">征信信息</span>
					            </div>
					        </li>
					    </ul>
					
					</div>
                    <#--图标区-->
                    <#--口号-->
	                </div>
	                <#--<div class="ceoInfo-l-b">
	    				<div class="progess-num">
					        <span class="num-txt">资料完整度:</span><span class="num-num">80%</span>
					    </div>
					    <div class="progess-img"></div>
					</div>-->
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
    </@block>
</@extend>