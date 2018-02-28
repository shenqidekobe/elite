<@extend name="layout">
    <@block name="cs">
	   
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <#--正文开始-->
    
    <div class="how_work_box" style="padding-top:0px;">
    	<div class="how_work">
    		<img class="close_btn" style="display:none;" src="${_PATH}/res/images/index/close_index.png" alt="">
		</div>
    </div>
    
	<div class="y-bd">
		<div class="bd_bg_box">
			<ul class="shuffling_img clearfloat">
				<li style="display:block;" id="point1_img">
					<div class="bd-bg">
				        <div class="bd-bg-content">
				            <div class="bg-int">
				                <h1 class="int-a">共享才能，轻创才富</h1>
				                <h2 class="int-b">云英汇，领先的技术人才共享平台</h2>
				                <a class="int-c">看看我们如何工作的？</a>
				            </div>
				        </div>
				    </div>
				</li>
				<li id="point2_img" style="display:none;">
					<div class="bd_bg2"></div>
				</li>
			</ul>
			
			<ul class="shuffling_point clearfloat">
				<li data="point1" style="opacity: 0.5;"></li>
				<li data="point2"></li>
			</ul>
		</div>
		
	    <div class="bd-elite">
	        <div class="elite-box">
	            <div class="elite-a fl">
	                <img class="box-logo" src="${_PATH}/res/images/index/elite_a.png" alt="">
	                <div class="box-title">
	                    	项目管家
	                </div>
	                <div class="box-int">
	                    <p>项目需求梳理，全程项目监管</p>
	                </div>
	            </div>
	            <div class="elite-b fl">
	                <img class="box-logo" src="${_PATH}/res/images/index/elite_b.png" alt="">
	                <div class="box-title">
	                  	   靠谱精英
	                </div>
	                <div class="box-int">
	                    <p>实名邀请注册，严格审查机制</p>
	                </div>
	            </div>
	            <div class="elite-c fl">
	                <img class="box-logo" src="${_PATH}/res/images/index/elite_c.png" alt="">
	                <div class="box-title">
	             		信用保障
	                </div>
	                <div class="box-int">
	                    <p>平台酬金托管，认证信用评估</p>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="bd-create">
	        <div class="create-box">
				<div class="create_box_title">
				    <div class="title1">精英圈</div>
				    <div class="title2">创造一种技术人才的未来连接方式</div>
				</div>
	        	
	            <ul class="clearfloat eliteUl" id="eliteUl">
	                <@eliteList eliteSize="8"></@eliteList>
	                <#list eliteList as it>
		                <li data="${it.id}">
		                    <div class="create-box-content">
		                    	<div class="create-logo-box">
				                    <#if it.basic.photoId??>
					                    <div class="logo_box"><div class="create-logo"><img src="${it.basic.memberPhoto.path}"></div></div>
					                <#else>
				                        <div class="logo_box"><div class="create-logo"><img src="${_PATH}/res/images/default.jpg"></div></div>
				                    </#if>
			                       
			                        <div class="create-name">${it.nickName}</div>
			                        <div class="create-position">
									   <#if it.basic.memberSign??><#if it.basic.memberSign?length gt 13>${it.basic.memberSign.substring(0,13)}<#else>${it.basic.memberSign}</#if><#else>云英汇精英</#if>
									</p>
									</div>
								</div>
								
								<#if session().memberId?exists>
								    <#if it.attentioned>
				                        <div class="index_icon" data="${it.id}" oper="remove" style="background:#999">已关注</div>
				                    <#else>
				                        <div class="index_icon" data="${it.id}" oper="yes" style="background:#fea600">关注</div>
				                    </#if>
                                <#else>
                                    <div id="nologin_icon" class="index_icon" style="background:#fea600">关注</div>
								</#if>
								<div class="logo_box_hide"></div>
								
		                        <div class="create-in">
		                        	<div class="create_in_content">
			                            <div class="create-in-a">
			                                <div class="height22"><#if it.elite.jobAge??>${it.elite.jobAgeVal}<#else>十年以上</#if></div>
			                                <div class="font-size12 colorb3">工作经验</div>
			                            </div>
			                            <div class="create-in-b" style="display:none;">
			                                <div class="height22">${it.taskCount}</div>
			                                <div class="font-size12 colorb3">任务</div>
			                            </div>
			                            <div class="create-in-c">
			                                <div class="height22"><#if it.elite.allotDuration??>${it.elite.allotDurationVal}<#else>不限</#if></div>
			                                <div class="font-size12 colorb3">每周可共享</div>
			                            </div>
		                            </div>
		                        </div>
		                        
		                       <div class="create_in_hide">
			                       <div class="in_hide_content">
			                           <@clearTag value='${it.elite.intro}' length=65/>
								    </div>
							   </div>
		                    </div>
		                </li>
	                </#list>
	            </ul>
	            <div class="more_div"><a class="more" href="${_PATH}/circle">发现更多精英</a><div>
	        </div>
	    </div>
	    </div>
	    </div>
	    
	    <div class="bd_person">
	        <div class="person_box">
	            <div class="person_project">
	            	<div class="project_text">
            			<h1>我是项目方</h1>
            			<div class="how_p"><a href="${_PATH}/introduce/company">我们是如何保障项目顺利进行和完美交付？</a><span>&gt;</span></div>
	            	</div>
	            </div>
	            <div class="person_elite">
	            	<div class="elite_text">
            			<h1>我是精英方</h1>
            			<div class="how_e"><a href="${_PATH}/introduce/elite">我们如何获得任务，赚取报酬？</a><span>&gt;</span></div>
	            	</div>
	            </div>
	        </div>
    	</div>
    	
    	
    	<div class="bd_partner">
			<div class="case">
				<div class="case_title">成功案例</div>
				<div class="case_img">
					<img class="marginright30" src="${_PATH}/res/images/index/case1.png" alt="">
					<img class="marginright30" src="${_PATH}/res/images/index/case2.png" alt="">
					<img class="marginright30" src="${_PATH}/res/images/index/case3.png" alt="">
					<img class="" src="${_PATH}/res/images/index/case4.png" alt="">
				</div>
			</div>
			<div class="cooperation">
				<div class="cooperation_title">合作伙伴</div>
				<div class="cooperation_img">
					<img class="" src="${_PATH}/res/images/index/cooperation_img.png" alt="">
				</div>
			</div>
    	</div>
    	<input type="hidden" id="sessionId" value="${(session().memberId)}">
	    <div class="bd-start">
	        <h1>从零开始，加入云英汇大家庭</h1>
	        <#if session().memberId?exists>
		        <div class="start_box">
		            <a class="start" href="${_PATH}/member/index">立刻开始</a>
		        </div>
	        <#else>
		        <div class="start_box">
		            <a class="start" href="${_PATH}/register">立刻开始</a>
		        </div>
	        </#if>
	    </div>
	</div>
    </@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/index.js"></script>
    </@block>
</@extend>