<@extend name="completeLayout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css//index/ceo_main.css"/>
    <link rel="stylesheet" href="${_PATH}/res/script/plugin/datepicker/datepicker3.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/register/eliteExperience.css"/>
    </@block>

    <#--中间正文部分-->
    <@block name="content">
    <@optmodal title="删除提示" ask="您确定删除?"/>
    <div class="process" id="eliteExperience_process">
    <div class="box-container-elite">
        <div class="process-box-elite">
            <div class="process-circle active-process">
                <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
            </div>
            <div class="process-circle active-process" style="left:269px;">
                <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
            </div>
            <div class="process-circle active-process" style="left:534px;">3</div>
            <div class="process-circle" style="left:805px;">4</div>
            <p class="process-title active-title">当前状态</p>
            <p class="process-title active-title" style="left:266px;">基本信息</p>
            <p class="process-title active-title" style="left:532px;">技能<span>|</span>经历</p>
            <p class="process-title" style="left:799px;">个人征信</p>

            <div class="process-line finish-line"></div>
            <div class="process-line finish-line" style="left:337px;"></div>
            <div class="process-line" style="left:602px;"></div>
        </div>
     </div>   
    </div>

    <div class="content_elite" id="eliteExperience_content">
    	<div class="content_elite_container">
        <form class="form-group form-horizontal form-person" role="form" id="personFrom" name="personFrom">
            <input type="hidden" name="id" value="${obj.id}">
            <input type="hidden" id="project_count" value="${list?size}">
            <#list list as li>
				 <input type="hidden"  name="projects" value="${li.id},${li.project},${li.position},${li.startTime},${li.endTime}">
			</#list>
           
            <input type="hidden" name="resumeAttaId" id="resumeAttaId" value="${obj.resumeAttaId}">
             <input type="hidden"  id="atta" value="${atta.id},${atta.fileName}">
            <input type="hidden" name="attentionIndustry" id="attentionIndustry" value="${obj.attentionIndustry}">
            
            <div class="form-group">
                <label class="col-xs-3 col-md-3 control-label"><div class="title-input"><span class="required">* </span> 关注行业<span class="label-tip">&nbsp;(不多于3个)</span></div></label>
                <div class="col-xs-8 col-md-8">
                    <div class="belong-class">
                        <div class="select-box">
                            <@dict type="choice_industry"></@dict>
	                        <#list dictList as dt>
	                           <button type="button" class="tour" data="${dt.dictKey}" orders="${dt_index}">${dt.dictVal}</button>
	                        </#list>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 col-md-3 control-label"><div class="title-input"><span class="required">* </span> 项目经历</div></label>
                <div class="col-xs-6 col-md-6">
                    <div class="add-tr" id="add_project_info">
                        <img src="${_PATH}/res/images/add_icon2.png" id="add_project"> &nbsp;添加
                    </div>
                </div>
                <div class="col-xs-2 col-md-2" id="add_project_oper">
                
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 col-md-3 control-label"><div class="title-input">自我描述</div></label>
                <div class="col-xs-6 col-md-6">
                   <textarea class="form-control textarea-unified " rows="6" id="intro"  name="intro">${obj.intro}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 col-md-3 control-label"><div class="title-input">附件简历</div></label>
                <div class="col-xs-4 col-md-4">
                    <div class="upload-resume" onclick="document.personFrom.resumeAttaFile.click()">
                        <img src="${_PATH}/res/images/upload_icon.png" width="20" height="20">
                        <input type="file" name="file" id="resumeAttaFile" style="display:none;">
                        <span style="margin-left:10px;">上传附件简历</span>
                    </div>
                     <div class="upload-button" style="display:none;" id="resumeAtta_show"></div>
                </div>
            </div>
           

            <div class="form-opt" style="padding-left:92px;">
            	 <div class="error-tip">
	                <span id="tipError"></span>
	            </div>
         	     <a href="javascript:void(0);" class="back_btn" id="prevBtn">上一步</a>
                <button type="button" class="btn-ok" id="saveBtn">下一步</button>
                <a href="javascript:void(0);" class="skip_btn" id="skip">暂时跳过</a>
            </div>
        </form>
    </div>
</div>


    <#--添加项目经历模态框-->
    <div class="container" id="eliteExperience_window">
        <div class="row">
            <div class="modal fade" data-backdrop="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  id="addProjectDialog" style="top:50%;margin-top:-250px;">
                <div class="modal-dialog" style="margin:0 auto;">
                    <div class="modal-content" style="width:580px; height:535px;margin:0 auto;">
                        <div class="modal-body" style="padding:0;height:100%;border-radius: 4px;">
                        <#--<button type="button" class="close" style="color:white;top: -18px;right: -20px;position: absolute;outline: none;" data-dismiss="modal" aria-label="Close">-->
                        <#--<span class="glyphicon glyphicon-remove-circle closeBtn" aria-hidden="true"></span>-->
                        <#--</button>-->

                            <img src="${_PATH}/res/images/close_icon.png" class="close-icon" data-dismiss="modal" aria-label="Close">
                            <#--正文-->
                            <form class="form-group form-horizontal my-form" role="form" id="addProjectForm" style="padding-top:30px;">

                                <div class="form-group group_div">
                                    <label class="col-xs-3 col-md-3 control-label">公司或项目名称</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input type="hidden" id="project_id" name="id">
                                        <input class="form-control" type="text" id="project" name="project" placeholder=" 公司或项目名称" maxlength="20">
                                    </div>
                                </div>

                                <div class="form-group group_div">
                                    <label class="col-xs-3 col-md-3 control-label">担任的职位</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" type="text" id="position" name="position" placeholder=" 担任的职位" maxlength="11">
                                    </div>
                                </div>

                                <div class="form-group group_div">
                                    <label class="col-xs-3 col-md-3 control-label">在职或项目时间</label>
                                    <div class="col-xs-9 col-md-9 time_div">
                                         <div class="date-select select1">
                                         	<span id="startTimeSelect">
	                                            <img src="${_PATH}/res/images/date_icon.png" class="date-icon" id="startTimeIcon">
	                                            <input type="text" class="form-control" id="startTime" name="startTime"  readonly  placeholder=" 起始时间" style="width:160px"/>
                                            </span>
                                        </div>

                                        <span class="time_line"></span>
                                         <div class="date-select select2">
	                                         <span id="endTimeSelect">
	                                            <img src="${_PATH}/res/images/date_icon.png" class="date-icon" id="endIcon">
	                                            <input type="text" class="form-control" id="endTime" name="endTime"  readonly  placeholder=" 截止时间" style="width:160px"/>
	                                        </span>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3 col-md-3 control-label">项目描述</label>
                                    <div class="col-xs-9 col-md-9">
                                        <textarea class="textarea" rows="8" id="intro" name="intro" placeholder=" 简要填写项目核心功能和自己参与的模块，不超过500个字符" length="500"></textarea>
                                    </div>
                                </div>


                                

                                <div class="modal-opt">
                                	<div class="error-tip"></div>
                                    <button type="button" class="opt-btn-ok" id="save_project_btn">保存</button>
                                    <button type="button" class="opt-btn-cancel" data-dismiss="modal" aria-label="Close">取消</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#--upload progress modal-->
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
    </@block>

    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/member/elite/experience.js"></script>
    </@block>
</@extend>