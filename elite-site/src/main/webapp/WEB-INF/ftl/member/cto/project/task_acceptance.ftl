<@extend name="layout">
    <@block name="cs">
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/ceo_main.css">
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/settlement/acceptance.css">
	</@block>
	<@block name="body">
    <@accounthead opt=""/>
        <div class="y-bd">
		    <div class="bd-acceptance">
		        <div class="bd-acceptance-content">
		        	<div class="task_acceptance_title">
	                <h1>个人主页<span  style="padding: 0 4px;color: #4a4a4a;">&gt;</span><span id="myProject" style="cursor:pointer">我的项目</span><span style="padding: 0 4px;color: #4a4a4a;">&gt;</span><span class="color2c">验收</span></h1>
	            </div>
		            <div class="bd-acceptance-content">
		                <div class="bac-progress-box">
		                    <div class="task_acceptance_progress">
		                        <div class="progress_ball active-process">1</div>
		                        <div class="progress_ball active-process" style="left: 136px">2</div>
		                        <div class="progress_ball active-process" style="left: 272px">3</div>
		                        <div class="progress_ball active-process" style="left: 411px">4</div>
		                        <div class="progress_ball" style="left: 547px">5</div>
		                        <div class="progress_ball" style="left: 683px"><img src="../../../../res/images/ceo/right_icon.png"></div>
		                        <div class="progress-text active-title" >待认领</div>
		                        <div class="progress-text active-title" style="left: 137px">认领中</div>
		                        <div class="progress-text active-title" style="left: 271px">进行中</div>
		                        <div class="progress-text active-title" style="left: 412px">待验收</div>
		                        <div class="progress-text" style="left: 545px">质保期</div>
		                        <div class="progress-text" style="left: 691px">完成</div>
		                        <div class="progress-line finish-line"  style="left: 46px"></div>
		                        <div class="progress-line finish-line"  style="left: 184px"></div>
		                        <div class="progress-line finish-line"  style="left: 324px"></div>
		                        <div class="progress-line"  style="left: 459px"></div>
		                        <div class="progress-line" style="left: 597px"></div>
		                    </div>
		                </div>
		                <div class="bac-details">
		                    <div class="details-text">
		                        <div class="details-text-a">
		                            <span class="marginleft19">创建时间：</span><span>${obj.createTime?string("yyyy-MM-dd")}&nbsp;&nbsp;9:30</span><span class="marginleft33">任务编号：</span><span>${obj.taskNum}</span>
		                            <div class="dta-line"></div>
		                        </div>
		                        <div class="details-text-b">
		                            <div class="dtb-l">
		                                <div class="dtb-l-logo" style="background-color:#${obj.project.backgroundColor}">${obj.project.firstName}</div>
		                                <div class="dtb-l-text">
		                                    <div class="text-box">
		                                        <p class="font-size16 marginbottom13">${obj.name}</p>
		                                        <p><span class="color9b">所需精英：</span><span>${obj.demandTypeVal} </span></p>
		                                        <p><span class="color9b">任务类型：</span><span>${obj.taskTypeVal} </span></p>
		                                        <p class="date_released"><span class="color9b">交付周期：</span><span><#if obj.startTime??>${obj.startTime?string("yyyy/MM/dd")}</#if>-<#if obj.deliveryTime??>${obj.deliveryTime?string("yyyy/MM/dd")}</#if></span>&nbsp;<span>(${obj.deliveryDay?string('#')}个工作日)</span></p>
		                                    	<P><span class="color9b">任务报酬：</span><span>${obj.amount}元</span></P>
		                                    </div>
		                                </div>
		                            </div>
		                            <div class="dtb-c">
		                                <div class="dtb-c-content">
		                                    <#if obj.member.basic.photoId??>
		                						<div class="head-logo2" style="background:url('${obj.member.basic.memberPhoto.path}');background-size: cover;background-position: center;"></div>
						                	<#else>
						                		<div class="head-logo2" style="background:url('${_PATH}/res/images/default.jpg');background-size: cover;background-position: center;"></div>
						                	</#if>
		                                </div>
		                            </div>
		                            <div class="dtb-r">
		                                <a href="#" class="dtb-r-a">任务状态</a>
		                                <a href="#" class="dtb-r-b">${obj.status.label}</a>
		                            </div>
		                            <div class="dtb-line"></div>
		                        </div>
		                    </div>
		                    <div class="details-money">
		                        <span>任务实际费用：</span>
		                        <span class="font-size20 colorfe">￥${obj.amount}</span>元
		                    </div>
		                </div>
		                <#if obj.status=='wait_accept'>
			                <div class="bac-pay">
			                    <div class="bac-pay-content" style="border: 1px solid #E6E6E6">
			                        <p>确认验收后，任务进入质保期！<span class="font-size12 color9b">（待本阶段项目方验收后，任务费用将自动转入他的账户）</span></p>
			                        <div><span>账户密码</span><input type="password" id="password" placeholder="请输入账户登录密码"><a href="javascript:void(0);" id="forgetPassport" class="color2c">忘记密码?</a></div>
			                    </div>
			                     <div id="errortip"></div>
			                    <div id="payConfirm" data="${obj.id}" class="bac-pay-confirm">确认</div>
			                </div>
			                <div class="bac-window" style="display:none">
			                    <div class="window-box">
			                        <div class="window-a" style="padding-left: 64px">
			                            <span class="window-icon" style="padding-right: 20px"></span>
			                            <span class="window-text">任务已验收完成！</span>
			                        </div>
			                        <p class="window-b" style="padding-left: 84px">将进入质保期，查看任务详情</p>
			                        <div class="window-c">
			                            <div class="window-c-text" style="padding-left: 204px">
			                                <span class="colorfe">6s</span>
			                                <span class="font-size12 color9b">后回到个人主页</span>
			                            </div>
			                            <a href="#" class="backButton" style="margin-left: 28px">立即返回</a>
			                        </div>
			                        <div class="window-close" id="closeWindow" data="${obj.id}"></div>
			                    </div>
			                </div>
			           </#if>     
		            </div>
		        </div>
		    </div>
		</div>
	    <#include "/rightFloat.ftl">
	</@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/cto/project/acceptance.js"></script>
        
    </@block>
</@extend>

