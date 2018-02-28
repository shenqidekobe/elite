<@extend name="layout">
    <@block name="cs">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>

    <#--正文开始-->
    
   <div class="content" id="homepage">
        <div class="location-nav">
            <span style="font-size: 18px;">个人主页</span>&nbsp;&gt;&nbsp;
            <span style="color:#2CB7C9;" id="crumbsText">我的任务</span>
        </div>

        <div class="content-box">
            <#--左边的资料区-->
            <div style="float:left;">
               <div class="left-column-elite" style="padding-bottom: 26px;">
                    <div class="circle-box-elite"> 
                        <#if obj.elite.status=='normal'><#if obj.currentType=='cto'><img src="${_PATH}/res/images/cto/cto.png" class="icon_id"><img src="${_PATH}/res/images/dui.png" class="gold-use"><#else><img src="${_PATH}/res/images/dui.png" class="gold-use" style="left:158px;"></#if></#if>
                        <#if session().memberId?exists??&&obj.basic.photoId??>
                        	<div class="column-head-elite">
				        		<img src="${obj.basic.memberPhoto.path}">
				        	</div>
				        <#else>
				        	<div class="column-head-elite">
				        		<img src="${_PATH}/res/images/default.jpg">
				        	</div>
				        </#if>
                    </div>

                    <div class="username">
                        <div class="work"><span title="${obj.nickName}"><#if obj.nickName?length gt 5>${obj.nickName?substring(0,5)}..<#else>${obj.nickName}</#if></span></div>
                        <#if obj.elite.status=='normal'>
							<#if obj.elite.taked>
								<div class="switch_box">
								    <div id="switch_btn" class="switch_no">
								        <span class="btn_ball"></span>
								    </div>
								    <div class="switch_text" style="color: #FEA600">接活</div>
								</div>
							<#else>
								<div class="switch_box">
								    <div id="switch_btn" class="switch_yes">
								        <span class="btn_ball" style="left:1px"></span>
								    </div>
								    <div class="switch_text" style="color: #9b9b9b">休息</div>
								</div>
							</#if>	
							
	                    <#else>
	                    	<div class="audit">
		                      <#if obj.elite.status=='waitAduit'>
		                           <span id="statuscontent">待完善</span>
		                           <div id="aduitbutton"><button type="button" id="applyAduit" class="recommend-btn">申请审核</button></div>
		                      <#elseif obj.elite.status=='aduitIn'>
		                           <span>审核中</span>
		                      <#elseif obj.elite.status=='auditNotPass'>
		                          <span id="statuscontent">审核未通过</span>
		                          <div id="aduitbutton"><button type="button" id="applyAduit" class="recommend-btn">申请审核</button></div>	                                         		    
		                     </#if>
		                   </div> 
                        </#if>
						
                    </div>
					<div style="margin-top:170px">${obj.basic.memberSign}</div>
					
					<p class="al clearfloat"></p>
                    <ul class="afine">
                    	<li>诚信度:&nbsp;<span>${obj.basic.integrity}</span></li>
                    	<#--<li class="fine">精英币:&nbsp;<span>${account.eliteCoin}</span>币</li>-->
                    </ul>
                   <#--<div class="sortf"><img src="${_PATH}/res/images/soritef.png" alt=""></div>-->

                    <div class="split-line"></div>

                    <div class="title_elite_box">
                        <div class="index-title-elite">收益</div>
                        <div class="index-title-elite" style="width:34%;">接活</div>
                        <div class="index-title-elite">获得关注</div>
                    </div>

                    <div class="data_elite_box">
                        <div class="index-data-elite">￥<span>${account.totalIncome}</span></div>
                        <div class="index-data-elite" style="width:34%;">${obj.taskCount}</div>
                        <div class="index-data-elite">${attentionCount}</div>
                    </div>
                </div>
                <div class="menu_elite">
                    <ul class="left-menu">
                    <#if obj.currentType=='cto'>
                    	<li data="myProject" class="active_color"><img class="menu-icon" src="/res/images/elite/a_select.png">我的项目<span class="active-menu"></span></li>
                    	<li data="myTask"><img class="menu-icon" src="/res/images/elite/a_select.png">我的任务<span class="active-menu"></span></li>
                        <li data="myTeam"><img class="menu-icon" src="/res/images/elite/team_noselect.png">我的团队<span class="active-menu"></span></li>
                        <li data="myAttention"><img class="menu-icon" src="/res/images/elite/b.png">我的关注<span class="active-menu"></span></li>
                        <li data="settlement"><img class="menu-icon" src="/res/images/elite/c.png">结算管理<span class="active-menu"></span></li>
                        <li data="myInfo"><img class="menu-icon" src="/res/images/elite/d.png"></span>我的资料<span class="active-menu"></span></li>
                   <#else>
                   		<li data="myTask"><img class="menu-icon" src="/res/images/elite/a_select.png">我的任务<span class="active-menu"></span></li>
                        <li data="myAttention"><img class="menu-icon" src="/res/images/elite/b.png">我的关注<span class="active-menu"></span></li>
                        <li data="settlement"><img class="menu-icon" src="/res/images/elite/c.png">结算管理<span class="active-menu"></span></li>
                        <li data="myInfo"><img class="menu-icon" src="/res/images/elite/d.png"></span>我的资料<span class="active-menu"></span></li>     
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
    <@block name="script">
    </@block>
</@extend>