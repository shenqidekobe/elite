<div id="task_assign">
<div style="text-align: center;"><span>将项目拆分成任务,招募线下的好友或云英汇的精英来认领</span></div>
<div style="text-align: center;margin-top:10px;">
    <button type="button" class="release-btn">
        <img src="/res/images/edit_icon2.png" width="20" height="20">
        &nbsp;发布新任务
    </button>
</div>
<form id="taskForm">
	<input type="hidden" name="projectId" value="${obj.id}"/>
    <#assign flag = 1 />
    <#list list as task>
        <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：${task.createTime?string("yyyy-MM-dd HH:mm")}</span>
                </div>
                <div class="col-xs-8 col-md-8">
                    <span class="pull-left">任务编号：${task.taskNum}</span>
                    <#if task.status=='wait_recruit'>
	                    <div class="pull-right" id="closeTask_${task_index}" data="${task.id}">
	                        <img src="/res/images/shutdown_icon.png" class="opt-icon">
	                        &nbsp;<span class="sign">关闭任务</span>
	                    </div>
	               <#-- <#elseif task.status=='closed'>
	                	<div class="pull-right">
	                        <img src="/res/images/shutdown_icon.png" class="opt-icon">
	                        &nbsp;<span class="sign">已关闭</span>
	                    </div>-->    
                    </#if>
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

        <div class="project-detail">
        	<div class="main-info">
                <div style="padding:20px;">
                	<#if task.taskLogo??>
                		<div class="logo" style="background:url('${task.logoAtta.path}')"><img class="logo" src="${task.logoAtta.path}"></div>
                	<#else>
                		<div class="project-logo" style="background-color:#${task.backgroundColor}">${task.name?substring(0,1)}</div>
					</#if>
                    <div class="project-info">
                        <div class="project-name" title="${task.name}"><#if task.name?length gt 16>${task.name?substring(0,16)}<#else>${task.name}</#if></div>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">${task.demandTypeVal}</span></div>
                        <div><span class="index">报酬:</span>&nbsp;<span class="value">${task.amount}</span></div>
                        <div><span class="index">交付日期:${task.deliveryTime?string("yyyy-MM-dd")}</span>&nbsp;<span class="value"> (${task.deliveryDay?string('#')}个工作日)</span></div>
                    </div>
                </div>
            </div>
            
            <#if task.status=='wait_recruit'>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p class="current-status">已推送至<span style="color:#2CB7C9;cursor:pointer" id="toHall">任务大厅</span></p>
                    <p class="view-times">
                        <img src="/res/images/eye_icon.png" width="20" height="12">
                        &nbsp;${task.viewCount}
                    </p>
                    <a href="javascript:void(0);" id="taskDetail_${task_index}" data="${task.id}">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                	<p style="font-size: 14px;">待认领</p>
                    <#--<button type="button" class="recommend-btn">推荐给他人</button>-->
                </div>
            </div>
            
          <#elseif task.status=='closed'>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p class="current-status">已关闭</p>
                     <a href="javascript:void(0);" id="taskDetail_${task_index}" data="${task.id}">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                	<p style="font-size: 14px;">未指定精英</p>
                    <#--<button type="button" class="recommend-btn">推荐给他人</button>-->
                </div>
            </div>
            <#elseif task.status=='starting'>
            <div class="status">
                <div class="imgBox" id="showEliteInfo" data="${task.member.id}">
                    <#if task.member.basic.photoId??>
                		<div class="head-logo" style="background:url('${task.member.basic.memberPhoto.path}')"></div>
                	<#else>
                		<div class="head-logo" style="background:url('${_PATH}/res/images/default.jpg')"></div>
                	</#if>
                </div>
                    <p class="moreBox"><a href="javascript:void(0);" id="taskDetail_${task_index}" data="${task.id}">查看详情</a></p>
                
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <p>
                        <img src="/res/images/clock_icon.png" width="16" height="16">
                    &nbsp;<span style="font-size: 12px;">距任务交付：&nbsp;<span style="color:#FEA600;"><span id="deadlineSpan_${flag}" data="${task.taskoverVal}"></span></span></span>
                    </p>
                    <#assign flag = flag+1 />
                    <p style="font-size: 14px;">进行中</p>
                    <#if task.remind>
                    	<button type="button"  class="opt-btn" style="background-color:grey">已提醒</button>
                    <#else>
                    	<button type="button" id="remind_${task_index}" data="${task.id}" class="opt-btn">提醒</button>
                    </#if>	
                </div>
            </div>
            
            <#elseif task.status=='wait_accept'>
            <div class="status">
                <div class="imgBox" id="showEliteInfo" data="${task.member.id}">
                	<#if task.member.basic.photoId??>
                		<div class="head-logo" style="background:url('${task.member.basic.memberPhoto.path}')"></div>
                	<#else>
                		<div class="head-logo" style="background:url('${_PATH}/res/images/default.jpg')"></div>
                	</#if>	
                </div>
                    <p class="moreBox"><a href="javascript:void(0);" id="taskDetail_${task_index}" data="${task.id}">查看详情</a></p>
                
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <p style="font-size: 14px;">待验收</p>
                    <button type="button" id="acceptance_${task_index}" data="${task.id}" class="opt-btn">去验收</button>
                </div>
            </div>
            
            <#elseif task.status=='quality'>
            <div class="status">
                <div class="imgBox" id="showEliteInfo" data="${task.member.id}">
                	<#if task.member.basic.photoId??>
                		<div class="head-logo" style="background:url('${task.member.basic.memberPhoto.path}')"></div>
                	<#else>
                		<div class="head-logo" style="background:url('${_PATH}/res/images/default.jpg')"></div>
                	</#if>
                </div>
                    <p class="moreBox"><a href="javascript:void(0);" id="taskDetail_${task_index}" data="${task.id}">查看详情</a></p>
                
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <p style="font-size: 14px;">质保期</p>
                    <#--<button type="button" class="opt-btn">评价</button>-->
                </div>
            </div>
            
            <#elseif task.status=='accept_success'>
            <div class="status">
                <div class="imgBox" id="showEliteInfo" data="${task.member.id}">
                    <#if task.member.basic.photoId??>
                		<div class="head-logo" style="background:url('${task.member.basic.memberPhoto.path}')"></div>
                	<#else>
                		<div class="head-logo" style="background:url('${_PATH}/res/images/default.jpg')"></div>
                	</#if>
                </div>
                    <p class="moreBox"><a href="javascript:void(0);" id="taskDetail_${task_index}" data="${task.id}">查看详情</a></p>
                
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <p style="font-size: 14px;">已验收</p>
                    <#--<button type="button" class="opt-btn">评价</button>-->
                </div>
            </div>

            
            <#elseif task.status=='finish'>
            <div class="status">
                <div class="imgBox" id="showEliteInfo" data="${task.member.id}">
                    <#if task.member.basic.photoId??>
                		<div class="head-logo" style="background:url('${task.member.basic.memberPhoto.path}')"></div>
                	<#else>
                		<div class="head-logo" style="background:url('${_PATH}/res/images/default.jpg')"></div>
                	</#if>
                </div>
                    <p class="moreBox"><a href="javascript:void(0);" id="taskDetail_${task_index}" data="${task.id}">查看详情</a></p>
                
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <p style="font-size: 14px;">已完成</p>
                    <#--<button type="button" class="opt-btn">评价</button>-->
                </div>
            </div>
            
            <#elseif task.status=='recruit_in'>
            <div class="status">
                <div class="imgBox">
                	<#list task.recruitList as recruit>
                		<#if recruit_index lt 3>
	                		<#if recruit.info.basic.photoId??>
		                		<div class="head-logo2" style="background:url('${recruit.info.basic.memberPhoto.path}');"></div>
		                	<#else>
		                		<div class="head-logo2" style="background:url('${_PATH}/res/images/default.jpg');"></div>
		                	</#if>
		                </#if>	
                	</#list>
                	
                	</div>
                    <div class="numberBox">
                        <img src="/res/images/group_icon.png" width="18" height="16">
                        <span class="number1">${task.recruitList?size}</span>
                        <img src="/res/images/eye_icon.png" width="20" height="12" style="margin-left:14px;">
                        <span class="number2">${task.viewCount}</span>
                    </div>
                    <p class="moreBox"><a href="javascript:void(0);" id="taskDetail_${task_index}" data="${task.id}">查看详情</a></p>
                </div>
            
            <div class="opt">
            	<#if task.recruitList?size gt 0>
	                <div style="padding-top:20px;text-align: center;">
	                    <button type="button" id="confirmElite_${task_index}" data="${task.id}" class="recommend-btn">去确定人选</button>
	                    <#--<button type="button" class="opt-btn" style="margin-top:10px;">推荐给他人</button>-->
	                </div>
	            <#else>
	            	<div style="padding-top:20px;text-align: center;">
	                	<p style="font-size: 14px;">待认领</p>
	                </div>
	            </#if>    
            </div>
            
            </#if>
        </div>
    </div>
    
    </#list>
   <#include "/init/pager.ftl"/>
 </form>
 
    <div class="modal fade" data-backdrop="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  id="closeDialog" style="top:50%;margin-top:-250px;">
        <div class="modal-dialog" style="margin:0 auto;">
            <div class="modal-content">
                <div class="modal-body">
                	<div class="textarea_div">
                        <p class="textarea_text">输入关闭原因</p>
                        <div class="textarea_box">
                             <textarea class="form-control" rows="6" name="intro" id="reason"></textarea>
                             
                             <div class="btnDiv">
                             	<div class="errortip"></div>
                                 <button type="button" class="modal-opt-btn" id="confirm">确定</button>
                                 <button type="button" class="cancel-btn" data-dismiss="modal" aria-label="Close" id="cancel">取消</button>
                             </div>
                        </div>
                    </div>
                </div>
            </div>
       </div>
    </div>
                      
    <#--模态框-->
    <div class="container">
        <div class="row">
            <div class="modal fade" data-backdrop="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  id="releaseTaskDialog" style="top:50%;margin-top:-250px;">
                <div class="modal-dialog" style="margin:0 auto;">
                    <div class="modal-content" style="width:760px; height:500px;margin:0 auto;background-color: #F5FBFC;overflow: auto;">
                        <div class="modal-body" style="padding:0;height:100%;border-radius: 4px;">
                           <img src="/res/images/close_icon.png" class="close-icon" data-dismiss="modal" aria-label="Close" id="closeModal">
                        <#--正文-->
                            <form class="form-group form-horizontal" role="form" id="releaseTaskForm" name="releaseTaskForm" style="padding:30px;">
                                <input type="hidden" value="${obj.id}" name="projectId" />
                                <input type="hidden" value="${record.trustStage.id}" name="stageId" />
                                <#--<div class="logo-box">
                                    <div class="word-logo" style="display:none"><img id="logo" style="width:80px;height:80px;" src=''/></div>
                                    <div class="upload-tip" onclick="releaseTaskForm.taskLogoFile.click()">
                                        <img id="taskLogoImg" src="/res/images/upload_icon.png" width="16" height="16">
                                        &nbsp;上传logo
                                        <input type="file" name="file" id="taskLogoFile" style="display:none;">
                            			<input type="hidden" name="taskLogo" id="attaId" />
                                    </div>
                                </div>-->
                                <div style="margin-bottom:20px;">
                                    <p><span class="required">*</span>&nbsp;&nbsp;任务名称</p>
                                    <div style="padding-left:18px;">
                                        <input type="text" id="name" name="name" value="" class="form-control" placeholder="任务名称" maxlength="15" style="width:400px" />
                                    </div>
                                </div>

                                <div style="margin-bottom:20px;height:67px;">
                                    <p><span class="required">*</span>&nbsp;&nbsp;所需精英角色<span class="required" style="margin-left:160px;">*</span>&nbsp;&nbsp;任务类型</p>
                                    <div class="modal-select" data="demandList">
                                        <input type="text" name="demandTypeVal" data="demandList" id="demandTypeVal" class="form-control" placeholder="请选择" style="width:220px;" readonly="readonly" />
                                        <input type="hidden" name="demandType" id="demandType"/>
                                        <div class="form-triangle" id="demandTypeIcon" data="demandList">
                                            <div class="form-trigger"></div>
                                        </div>
	                                    <div class="budget" id="demandList">
		                                    <ul>
		                                    	<@dict type="job_role"></@dict>
		                                    	<#list dictList as dt>
		                                    		<li data="${dt.dictKey}">${dt.dictVal}</li>
		                                    	</#list>
		                                    </ul>
	                                    </div>
                                    </div>

                                    <div style="margin-left:50px;" class="modal-select" data="taskTypeList">
                                        <input type="text" name="taskTypeVal" id="taskTypeVal" data="taskTypeList" class="form-control" placeholder="请选择" style="width:220px;" readonly="readonly" />
                                        <input type="hidden" name="taskType" id="taskType"/>
                                        <div class="form-triangle" id="taskTypeIcon" data="taskTypeList">
                                            <div class="form-trigger"></div>
                                        </div>
                                    	<div class="budget" id="taskTypeList">
		                                    <ul>
		                                    	<@dict type="task_type"></@dict>
		                                    	<#list dictList as dt>
		                                    		<li data="${dt.dictKey}">${dt.dictVal}</li>
		                                    	</#list>
		                                    </ul>
	                                    </div>
                                    </div>
                                </div>

                                <div style="margin-bottom:20px;height:67px;">
                                    <p><span class="required">*</span>&nbsp;&nbsp;任务周期</p>
                                    <div class="modal-select">
                                    	<div class="select_input1">
                                        	<input type="text" id="startTime" name="startTime" class="form-control" placeholder="任务开始时间" style="width:220px;">
                                        	<img id="startIcon" src="/res/images/date_icon.png" class="date-icon">
                                        </div>
                                    </div>
                                    <span class="date-link">-</span>
                                    <div style="margin-left:7px;" class="modal-select">
                                      <div class="select_input2">
                                        	<input type="text" id="deliveryTime" name="deliveryTime" class="form-control" placeholder="任务交付时间" style="width:220px;">
                                        	<img id="deliveryIcon" src="/res/images/date_icon.png" class="date-icon">
                                      </div>  
                                    </div>
                                </div>

                                <div style="margin-bottom:20px;height:67px;padding-left:20px;" id="endTime_div">
                                    <p><span class="required">*</span>任务招募截止日期<span class="required" style="margin-left:130px;">*</span>&nbsp;&nbsp;任务报酬</p>
                                    <div class="modal-select" style="padding-left:0;">
                                    	<div class="select_input3">
                                        	<input type="text" id="endTime" name="endTime" class="form-control" placeholder="选择日期" style="width:220px;">
                                        	<img id="endIcon" src="/res/images/date_icon.png" class="date-icon">
                                        </div>
                                    </div>

                                    <div style="margin-left:28px;" class="modal-select">
                                        <input type="text" name="amount" id="amount" class="form-control" placeholder="输入金额" style="width:220px;">
                                        <span class="money-unit">元</span>
                                    </div>
                                </div>

                                <div style="margin-bottom:20px;min-height:300px;">
                                    <p><span class="required">*</span>&nbsp;&nbsp;要求</p>
                                    <div  style="padding-left:18px;">
                                       <input type="hidden" name="intro" id="intro" />
                                       <script id="container" type="text/plain" style="width:490px;height:200px;float:left;"></script>
                                       <#--
                                          <textarea class="form-control" rows="5" name="intro" id="intro" style="width:490px;float:left;" placeholder="提一些要求"></textarea>
                                       -->
                                        
                                    </div>
                                </div>

                                <div class="uploadBox">
                                	<div class="btn_div clearfloat">
                                	<button type="button" class="upload-btn"  onclick="releaseTaskForm.resumeAttaFile.click()">
                                        <img  src="/res/images/upload_icon.png" width="16" height="16">
                                        &nbsp;上传附件
                                        <input type="file" name="file" id="resumeAttaFile" style="display:none;">
                                        <input type="hidden" name="attaId" id="resumeAttaId" />
                                    </button>
                                    <div class="btn_text">其他说明性文档</div>
                                    </div>
                                    <div class="upload-button" style="display:none;" id="resumeAtta_show"></div>
                                </div>

                                <div class="modal-opt">
                                	<div class="error-tip"></div>
                                    <#--<button type="button" class="modal-opt-btn" id="assignBtn">将任务指派给</button>-->
                                    <button type="button" class="modal-opt-btn" id="publicBtn">发布到任务大厅</button>
                                    <button type="button" class="cancel-btn" data-dismiss="modal" aria-label="Close">取消</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="modal fade" data-backdrop="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  id="assignDialog" style="top:50%;margin-top:-250px;">
                <div class="modal-dialog" style="margin:0 auto;">
                    <div class="modal-content" style="width:760px; height:500px;margin:0 auto;background-color: #F5FBFC;overflow: auto;">
                        <div class="modal-body" style="padding:0;height:100%;border-radius: 4px;">
                            <img src="/res/images/close_icon.png" class="close-icon" data-dismiss="modal" aria-label="Close">
                        <#--正文-->

                            <div style="position: relative;padding-top:30px;">
                                <div class="tabs">
                                    <div class="tab active-tab">推荐给你的</div>
                                    <div class="tab">认领中的任务</div>
                                    <div class="tab">已认领的任务</div>
                                </div>
                                <div class="active-line"></div>
                            </div>

                            <div style="padding:30px;">
                                <div class="team-box" style="float:left;">
                                    <div style="line-height: 30px;">
                                        <img src="/res/images/trigger_close.png" class="trigger-icon">
                                        &nbsp;&nbsp;最近分享（3）
                                    </div>

                                    <div style="line-height: 30px;">
                                        <img src="/res/images/trigger_close.png" class="trigger-icon trigger-icon-inverse">
                                        &nbsp;&nbsp;团队里的人（12）
                                    </div>

                                    <div>
                                        <div class="person-box">
                                            <div class="modal-head"></div>
                                            <div class="name-title">
                                                <div>Janet Woods</div>
                                                <div>产品经理</div>
                                            </div>
                                            <div class="phone-task">
                                                <div>15789209400</div>
                                                <div>进行中的任务数：<span style="color:#2CB7C9;">3</span></div>
                                            </div>
                                            <img src="/res/images/select_tick.png" class="opt-person">
                                        </div>

                                        <div class="person-box">
                                            <div class="modal-head"></div>
                                            <div class="name-title">
                                                <div>Janet Woods</div>
                                                <div>产品经理</div>
                                            </div>
                                            <div class="phone-task">
                                                <div>15789209400</div>
                                                <div>进行中的任务数：<span style="color:#2CB7C9;">3</span></div>
                                            </div>
                                            <img src="/res/images/select_tick.png" class="opt-person">
                                        </div>
                                    </div>

                                    <div style="line-height: 30px;">
                                        <img src="/res/images/trigger_close.png" class="trigger-icon">
                                        &nbsp;&nbsp;我关注的精英（58）
                                    </div>

                                    <div style="line-height: 30px;">
                                        <img src="/res/images/trigger_close.png" class="trigger-icon">
                                        &nbsp;&nbsp;关注我的精英（96）
                                    </div>

                                </div>
                                <div class="team-box" style="float:right;padding:0;">
                                    <div class="search-box">
                                        <input type="text" class="form-control phone-email-input" placeholder="输入手机号或邮箱添加精英">
                                        <img src="/res/images/search_green_icon.png" class="search-person">
                                    </div>

                                    <div style="padding:10px 14px;">
                                        <p class="select-person">已选择2个精英</p>
                                        <div class="select-person-box">
                                            <div class="modal-head"></div>
                                            <div class="name-title">
                                                <div>Janet Woods</div>
                                                <div>产品经理</div>
                                            </div>
                                            <div class="phone-task">
                                                <div>15789209400</div>
                                                <div>进行中的任务数：<span style="color:#2CB7C9;">3</span></div>
                                            </div>
                                            <img src="/res/images/delete_cross.png" class="delete-cross">
                                        </div>

                                        <div class="select-person-box">
                                            <div class="modal-head"></div>
                                            <div class="name-title">
                                                <div>Janet Woods</div>
                                                <div>产品经理</div>
                                            </div>
                                            <div class="phone-task">
                                                <div>15789209400</div>
                                                <div>进行中的任务数：<span style="color:#2CB7C9;">3</span></div>
                                            </div>
                                            <img src="/res/images/delete_cross.png" class="delete-cross">
                                        </div>
                                    </div>

                                </div>
                            </div>

                            <#--<div class="send-to-friends">-->
                                <#--<div>-->
                                    <#--<p>输入他（她）的<span style="color:#30C3D3;">手机号</span></p>-->
                                    <#--<input type="text" class="form-control" placeholder="他（她）的手机号" style="width:400px;display: inline-block;">-->
                                    <#--&lt;#&ndash;<img src="/res/images/add_icon2.png" width="25" height="25" style="margin-left:20px;">&ndash;&gt;-->
                                <#--</div>-->

                                <#--<div style="margin-top:20px;">-->
                                    <#--<p>输入指派对象的<span style="color:#30C3D3;">邮箱</span></p>-->
                                    <#--<input type="text" class="form-control" placeholder="他（她）的邮箱" style="width:400px;">-->
                                <#--</div>-->
                            <#--</div>-->

                            <div style="padding-left:230px;">
                                <button type="button" class="modal-opt-btn" style="width:290px;margin:30px 0;">指派给他，同步推送到任务大厅</button>
                                <button type="button" class="cancel-btn" style="float:right;margin:30px 40px 30px 0;" data-dismiss="modal" aria-label="Close">取消</button>
                            </div>


                            <#--<div style="padding-left:230px;margin-top:150px;">-->
                                <#--<button type="button" class="modal-opt-btn" style="width:290px;margin:30px 0;">指派给他，同步推送到任务大厅</button>-->
                                <#--<button type="button" class="cancel-btn" style="float:right;margin:30px 40px 30px 0;" data-dismiss="modal" aria-label="Close">取消</button>-->
                            <#--</div>-->

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
     
    <div class="window-assigned" style="display:none;"></div>
  
    
    </div>
    
    
