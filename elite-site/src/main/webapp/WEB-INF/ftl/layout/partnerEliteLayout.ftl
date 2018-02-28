<@extend name="layout">
    <@block name="cs">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <#--正文开始-->
    
    <div id="personRecommend">
        <div class="location-nav">
        	<span>人才推荐方页&gt;</span>
        	<span id="itemId">邀请注册</span>
        </div>
        <div class="contentBox">

        <#--左边的资料区-->
            <div class="left-column">
            	<#if obj.partnerElite.status='normal'>
            		<img src="/res/images/channel/badge.png" alt="徽章" class="badge_icon">
            	</#if>
            	<div class="id_icon">人才渠道</div>
                <div class="circle-box">
	                <form id="personFrom" name="personFrom">
		                <#if session().memberId?exists??&&obj.basic.photoId??>
		                    <div class="column-head">
		                    	<img src="${obj.basic.memberPhoto.path}" id="headImg">
		                    </div>
		                <#else>
		                    <div class="column-head">
		                         <img src="${_PATH}/res/images/default.jpg" id="headImg">
		                    </div>
		                </#if>
	                    <input type="file" name="file" id="headFile" style="display:none;">
	                    <#--上传头像弹窗-->
                        <div class="headWindow" style="display:none;" save="Y"></div>
	                </form>
                </div>
                <div class="username_box">
	                    <span class="username_name" id="layoutNickNameId">
	                      <#if obj.nickName?length gt 5>${obj.nickName?substring(0,5)}...<#else>${obj.nickName}</#if>
	                    </span>
	                    <span class="username_city">${obj.basic.areaName}</span>
                </div>
                <#if obj.partnerElite.status='waitAduit'>
                	<div class="checkDiv" id="checkContent">待审核<button type="button" class="checkBtn check_default" id="apalyAduit">申请审核</button></div>
                <#elseif obj.partnerElite.status='auditNotPass'>
                	<div class="checkDiv" id="checkContent">审核不通过<button type="button" class="checkBtn check_default" id="apalyAduit">申请审核</button></div>
                <#elseif obj.partnerElite.status='aduitIn'>
               		<div class="checkDiv">审核中</div>
               	<#elseif obj.partnerElite.status='normal'>
               	 	
                <#else>
                	
                </#if>	

                <div class="split-line"></div>
                 <input type="hidden" id="selectRightTypeId" value="champions"/>
                 <div class="invite-l-box">
                    <ul class="ceoInfo-l-a-content" id="left-menu">
				        <li data="champions" text="冠军榜单">
				            <div class="li-box">
				                <span class="icon-box champion"></span>
				                <span class="txt-box active_color">冠军榜单</span>
				            </div>
				        </li>
				        <li data="inviteRegister" text="邀请注册">
				            <div class="li-box">
				                <span class="icon-box invitation_no"></span>
				                <span class="txt-box">邀请注册</span>
				            </div>
				        </li>
				
				        <li data="personActive" text="精英管理">
				            <div class="li-box">
				                <span class="icon-box elite_no"></span>
				                <span class="txt-box">精英管理</span>
				            </div>
				        </li>
				        
				        
				         <li data="channelManage" text="渠道管理">
				            <div class="li-box">
				                <span class="icon-box channel_no"></span>
				                <span class="txt-box">渠道管理</span>
				            </div>
				        </li>
				
				        <li data="revenue" text="收益中心">
				            <div class="li-box">
				                <span class="icon-box earnings_no"></span>
				                <span class="txt-box">收益中心</span>
				            </div>
				        </li>
				        
				        <li data="accountSecurity" text="我的资料">
				            <div class="li-box">
				                <span class="icon-box data_no"></span>
				                <span class="txt-box">我的资料</span>
				            </div>
				        </li>
				    </ul>
				    <div class="line_active"></div>
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