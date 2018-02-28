<@extend name="layout">
    <@block name="cs">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>

    <#--正文开始-->
    
    <style>
    	.kucity .kucity_body{
		    position: absolute;
		    top: 49px;
		    left: -5px;
		    max-height: 265px;
		    overflow-y: auto;
		    width: 360px;
		}
    </style>
    
    <div class="content companyInfo" id="homepage">
    <#--<div class="content">-->
        
        <div class="content-box">
            <#--左边的资料区-->
            <div style="float:left;" class="companyInfo_left">
            
            	<div class="location-nav">
		            <span style="font-size: 18px;cursor:pointer" id="myMain">个人主页</span>&nbsp;&gt;&nbsp;
		            <span style="color:#2CB7C9;" id="crumbsText">我的资料</span>
		        </div>
            
            
                <div class="companyInfo_ul" style="padding-bottom: 26px;">
                    <div class="ceoInfo-l-a" id="companyInfoDiv">
					    <ul class="ceoInfo-l-a-content">
					        <li class="clearfloat select" data="basic">
					            <div class="li-box">
					                <span class="icon-box icon-a"></span>
					                <span class="txt-box txt_box_select">基本信息</span>
					            </div>
					        </li>
					
					        <li class="clearfloat" data="company">
					            <div class="li-box">
					                <span class="icon-box icon-b-s"></span>
					                <span class="txt-box">创业属性</span>
					            </div>
					        </li>
					
					        <li class="clearfloat" data="credit">
					            <div class="li-box">
					                <span class="icon-box icon-e-s"></span>
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