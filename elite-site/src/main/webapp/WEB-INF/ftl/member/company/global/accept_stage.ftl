<@extend name="layout">
    <@block name="cs">
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/public/public.css">
	    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
	    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/settlement/acceptance.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <div class="y-bd" style="padding-top:100px;">
	    <div class="bd-acceptance">
	        <div class="bd-acceptance-title">
	            <h1><span style="cursor:pointer;" id="crumbs_main">个人主页</span>&gt;
		            <span style="color:#2CB7C9;cursor:pointer;" id="myProject">我的项目</span>&gt;
		            <span style="color:#2CB7C9;cursor:pointer;">验收</span>
	            </h1>
	        </div>
	        <div class="bd-acceptance-content">
	            <div class="bac-progress-box">
	                <div class="bac-progress">
	                    <div class="progress-one">
	                        <span class="ball finish">1</span>
	                        <span class="color2c">${obj.acceptStage.title}</span>
	                    </div>
	                    <div class="line-finish"></div>
	                    <div class="progress-two">
	                        <span class="ball finish">2</span>
	                        <span class="color2c">验收</span>
	                    </div>
	                    <div class="<#if obj.acceptStage.status=='wait_accept'>line-unfinish<#else>line-finish</#if>" style="left:355px;"></div>
	                    <div class="progress-three">
	                        <span class="ball <#if obj.acceptStage.status=='wait_accept'>unfinish<#else>finish</#if>">3</span>
	                        <span>本阶段完成</span>
	                    </div>
	                </div>
	            </div>
	            <div class="bac-details">
	                <div class="details-text">
	                    <div class="details-text-a">
	                        <span class="marginleft19">创建时间：</span>
	                        <span>${obj.createTime?string("yyyy-MM-dd HH:mm")}</span>
	                        <span class="marginleft33">项目编号：</span>
	                        <span>${obj.projectNum}</span>
	                        <div class="dta-line"></div>
	                    </div>
	                    <input type="hidden" id="projectId" name="projectId" value="${obj.id}">
	                    <div class="details-text-b">
	                        <div class="dtb-l">
	                            <div class="dtb-l-logo" style="background-color:#${obj.backgroundColor}">${obj.firstName}</div>
	                            <div class="dtb-l-text">
	                                <div class="text-box">
	                                    <p class="font-size16 marginbottom13">${obj.name}</p>
	                                    <p><span class="color9b">开发类型：</span><span>${obj.solutionVals}</span></p>
	                                    <P><span class="color9b">总费用：</span><span>${obj.totalAmount?string.currency}</span></P>
	                                    <p><span class="color9b">开发周期：</span>
		                                    <span>${obj.startTime?string("yyyy.MM.dd")}-${obj.expectTime?string("yyyy.MM.dd")}</span>
		                                    <span>共${obj.deliveryDay?string('#')}个工作日</span>
	                                    </p>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="dtb-c">
	                            <div class="dtb-c-content">
	                                <p class="color9b marginbottom10">已托管费用合计：<span class="color4a">${obj.trustedAmount}</span></p>
	                                <p class="color9b marginbottom10">本阶段已托管费用：<span class="color4a">${obj.acceptStage.amount}</span></p>
	                                <p class="color9b">股权托管：<span class="color4a"><#if obj.isStock>有<#else>无</#if></span></p>
	                            </div>
	                        </div>
	                        <div class="dtb-r">
	                            <a href="javascript:void(0);" class="dtb-r-a">${obj.acceptStage.title}</a>
	                            <a href="javascript:void(0);" class="dtb-r-b" id="projectDetail1" data="${obj.id}">查看文件</a>
	                        </div>
	                        <div class="dtb-line"></div>
	                    </div>
	                </div>
	                <div class="details-money">
	                    <span>本阶段实际费用：</span>
	                    <span class="font-size20 colorfe">${obj.acceptStage.amount?string.currency}</span>
	                </div>
	            </div>
	            <#if obj.acceptStage.status=='wait_accept'>
		            <div class="bac-pay">
		                <div class="bac-pay-content">
		                    <p>确认后，本阶段将确认支付！</p>
		                    <div>
			                    <span>账户密码</span>
			                    <input type="password" placeholder="请输入账户登录密码" id="password">
			                    <a href="${_PATH}/forget/p" target="_blank" class="color2c">忘记密码?</a>
		                    </div>
		                </div>
		                <div class="bac-pay-confirm" data="${obj.id}">确认</div>
		            </div>
	            <#else>
		            <div class="bac-pay">
			                <div class="bac-pay-content">
			                    <p>${obj.acceptStage.title}已验收成功！</p>
			                </div>
			            </div>
		            </#if>
	            <div class="bac-window" style="display: none">
	                <div class="window-box">
	                    <div class="window-a">
	                        <span class="window-icon"></span>
	                        <span class="window-text">您已验收${obj.acceptStage.title}！</span>
	                    </div>
	                    <#if obj.trustNextStage??>
		                    <p class="window-b">您的下个阶段为${obj.trustNextStage.title}，托管本阶段费用后，项目即可继续进行了哦</p>
		                    <div class="window-c">
		                        <a class="hosting-btn" href="javascript:void(0);" id="trustNext">去托管下一阶段费用</a>
		                        <#--<a class="evaluation-btn" href="javascript:void(0);" id="evaluate">先评价本阶段</a>-->
		                    </div>
	                    <#else>
		                    <p class="window-b">您的项目所有阶段已经全部验收完毕！</p>
		                    <#--<div class="window-c">
		                        <a class="evaluation-btn" href="javascript:void(0);" id="evaluate">先评价本阶段</a>
		                    </div>-->
	                    </#if>
	                    <div class="window-close"></div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
    </@block>
    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/company/global/accept.js"></script>
    </@block>
</@extend>