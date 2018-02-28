<@extend name="layout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/script/plugin/datepicker/datepicker3.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/elite/bid_tender.css"/>
    </@block>
    <@block name="body">
    <@optmodal title="竞标提示" ask="提交后不可更改，请谨慎检查竞标书内容。确定提交嘛?"/>
    <@tipmodal tip="竞标成功！"/>
    <#--头部-->
    <@accounthead opt=""/>

<div id="bidTender">
    <div class="content" id="bid_tender">
        <div class="flag">
            <span><span style="cursor:pointer" id="myMain">个人主页</span> &gt; <span style="cursor:pointer" id="myProject">我的项目 </span>&gt; <span style="cursor:pointer" data="${obj.projectId}" id="tenderDetail">标书详情</span> &gt; 
                <span style="color:#2CB7C9;">竞标</span>
            </span>
        </div>
        <div class="content-box">
            <div class="process">
                <div class="process-circle active-process">1</div>
                <div class="process-circle active-process" style="left:230px;">2</div>
                <div class="process-circle" style="left:440px;">3</div>
                <div class="process-circle" style="left:650px;">
                    <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
                </div>
                <p class="process-title active-title">待竞标</p>
                <p class="process-title active-title" style="left:213px;">CTO竞标</p>
                <p class="process-title" style="left:442px;">中标</p>
                <p class="process-title" style="left:638px;">项目启动</p>

                <div class="process-line finish-line"></div>
                <div class="process-line finish-line" style="left:280px;"></div>
                <div class="process-line" style="left:490px;"></div>

                <p class="process-tip" style="left:284px;">填写竞标书，项目经理审核</p>
            </div>
            <form id="tenderForm" name="tenderForm">
            <div class="tender-box">
                <p style="font-size: 18px;" class="text-center">《${obj.project.name}项目竞标书》</p>

                <p class="title-index" style="padding-left:10px;">一、选择服务方式</p>
                <div style="padding-left:40px;">
                    <button type="button" class="service-btn" data="cost">费用</button>
                    <#if obj.stockRate gt 0>
                    	<button type="button" class="service-btn" style="margin-left:40px;" data="costAstock">费用&nbsp;+&nbsp;股权</button>
                    </#if>
                    <span class="tip" style="vertical-align: sub;">股权数待线下<span style="color:#7DD2E1;">沟通</span>协商确定</span>
                </div>

                <p class="title-index"><span class="required">*</span>&nbsp;二、项目所需费用 <span class="tip">一个合理的报价将大大提高中标率</span></p>
                <div style="padding-left:40px;">
                    <input type="hidden" name="amount" id="realAmount">
                    <input type="text" class="form-control" placeholder="填写费用" id="amount" style="width:140px;display: inline-block;" onkeyup="formatMoney(this)">
                    &nbsp;<span style="font-size: 12px;color:#4A4A4A;vertical-align: bottom;">元</span>
                </div>

                <p class="title-index"><span class="required">*</span>&nbsp;三、研发计划 <span class="tip">合理地对项目进行计划安排</span></p>
                <div style="width:600px;padding-left:32px;">
                       <table class="table period-table">
                           <thead>
                                <tr>
                                    <th>项目阶段</th>
                                    <th>阶段时间规划</th>
                                    <th>阶段所需费用</th>
                                </tr>
                           </thead>
                           <tbody>
                           		<#assign n = 1 />
                                <#list stageList as pst>
	                                <tr data="${pst.stageCode}" id="stageTr">
	                                    <td>
	                                        <div class="period-lab">${pst.title}</div>
	                                    </td>
	                                    <td>
	                                        <div class="period-time-cost" style="font-size: 12px;">
	                                            <span id="startTime">
		                                            <span class="date-block" id="datetime_${n}"></span>
		                                            <input type="hidden" id="sh">
		                                            <img src="${_PATH}/res/images/date_icon.png" class="date-icon">
	                                            </span>
	                                            <span class="date-block" style="width:10px;left:124px;">-</span>
	                                            <span id="endTime">
		                                            <span class="date-block" style="left:134px;" id="datetime_${n+1}"></span>
		                                            <input type="hidden" id="eh">
		                                            <img src="${_PATH}/res/images/date_icon.png" class="date-icon" style="left:224px;">
	                                            </span>
	                                            <div class="vertical-line"></div>
	                                            <span class="day-count">共<span style="color:#FEA600;" id="deliveryDay">0</span>天</span>
	                                        </div>
	                                    </td>
	                                    <td style="text-align: center;font-size: 14px;color:#FEA600;">
	                                        <input class="period-time-cost" style="width:50px;text-align: center;" id="stageAmount" onkeyup="formatMoney(this)">&nbsp;元
	                                    </td>
	                                </tr>
	                                <#assign n = n+2 />
                                </#list>
                           </tbody>
                       </table>
                </div>
                <input type="hidden" id="projectId" name="projectId" value="${obj.project.id}">
                <input type="hidden" id="tenderId" name="tenderId" value="${obj.id}">
                <input type="hidden" id="deadlineTime" value="${obj.tenderoverVal}">
                <input type="hidden" id="serviceWay" name="serviceWay">
                <input type="hidden" id="recordStages" name="recordStages">
                <input type="hidden" id="stageSize" value="${stageList?size}">
                <p class="title-index" style="padding-left:10px;">四、项目质保期</p>
                <div style="padding-left:40px;">
                    <p>质保期时长:<span style="color:#fea600">${month}个月</span></p>
                </div>
                <p class="title-index" style="padding-left:10px;">五、您针对此项目的优势</p>
                <div style="padding-left:40px;position: relative;">
                    <textarea class="form-control" rows="7" style="width:550px;" id="advantage" name="advantage" placeholder="告诉我们您的报价和计划是合理且具备可行性的，如类似项目经历、框架、成果等（不少于50字）"></textarea>
               
                </div>
                <div class="uploadBox">
	                <button type="button" class="upload-btn" onclick="document.tenderForm.attaFile.click()">
	                        <img src="${_PATH}/res/images/upload_icon.png" width="20" height="20">
	                        <input type="file" name="file" id="attaFile" style="display:none;">
	                        <input type="hidden" name="attaId" id="attaId">
	                        &nbsp;上传附件文档
	                    </button>
	                <div class="upload-button" style="display:none;" id="atta_show"></div>
                </div>

                <div style="height:30px;margin-top:20px;">
                <#--
                    <div class="download-box">
                        <#if obj.attas??>
                           <#list obj.attas as atta>
                           </#list>
                        </#if>
                        <img src="${_PATH}/res/images/brooch_icon.png" width="18" height="20">
                        &nbsp;<a href="#">堆糖网项目参考文档</a>
                        &nbsp;
                        <a href="#" style="margin-left:30px;">
                            <img src="${_PATH}/res/images/download_icon.png" class="download-icon">
                        </a>
                    </div>
                  -->  
                </div>

            </div>

            <p class="end-time">
                <span>距离招标截止：</span>&nbsp;<span id="deadlineSpan"></span>
            </p>
            <div class="button_box">
            	<div class="error_div"></div>
            	<button type="button" class="go-bid-btn">确认并提交</button>
            </div>
            
        </div>
        </form>
    </div>
    
    <div class="container">
	    <div class="row">
	        <div class="modal fade" id="progressDialog" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	            <div class="modal-dialog">
	                <div class="modal-content" style="top:250px;height:100px;">
	                    <div class="modal-body" style="text-align: center;padding:35px;">
	                        <div class="progress progress-striped active">
						        <div class="progress-bar progress-bar-success" role="progressbar" style="width: 0%;">0%</div>
						    </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</div>
    </@block>
    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/member/cto/global/bid.js"></script>
    </@block>
</@extend>