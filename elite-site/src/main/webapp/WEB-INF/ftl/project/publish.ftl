<@extend name="completeLayout">
    <@block name="cs">
	    <link rel="stylesheet" href="${_PATH}/res/script/plugin/datepicker/datepicker3.css"/>
	    <link rel="stylesheet" href="${_PATH}/res/style/css/publish_project.css"/>
	    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/kuCity.css"/>
	    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/ceo_main.css"/>
    </@block>

    <#--中间正文部分-->
    <@block name="content">
    <div class="process-release">
        	<div class="release-title">
			    <p>发布项目</p>
			    <p>说出你的梦想，云英汇为你搞定产品、CTO、团队和资金</p>
			</div>
    </div>

    <div class="content" style="margin-top: -100px;">
        <form class="form-group form-horizontal form-release" role="form" id="releaseFrom" name="releaseFrom">
            <p class="marginbottom15"><span class="required">*</span>&nbsp;&nbsp;<span class="font-size16">解决方案类型
            &nbsp;&nbsp;<span style="font-size:12px;color:#9B9B9B;">(可多选)</span></p>
            <div>
                <@dict type="project_type"></@dict>
                <#list dictList as dt>
                    <button type="button" class="solution-btn" data="${dt.dictKey}" orders="${dt_index}">${dt.dictVal}</button>
                </#list>
            </div>
            <div style="margin-top:20px;display:none;" id="solution_div">
                <span>已选</span><button type="button" class="selected-solution" id="psolBtn"></button>
            </div>
            <div class="popupsw" id="solutionError"></div>
            <div style="margin-top:40px;">
                <p class="marginbottom10"><span class="required">*</span>&nbsp;&nbsp;<span class="font-size16">项目预算</span></p>
                <div style="position: relative;width:240px;padding-top:11px;" id="budgetDiv">
                    <input type="text" class="form-control input-unified width240" id="projectBudget" name="projectBudget" placeholder="请选择" style="background:#fff;" readonly>
                    <div class="form-triangle-project">
                        <div class="form-trigger"></div>
                    </div>
                    <@dict type="project_budget"></@dict>
					<div class="budget">
						<ul>
		                    <#list dictList as dt>
		                       <li data="${dt.dictKey}">${dt.dictVal}</li>
		                    </#list>
	                    </ul>
                    </div>
                </div>
            </div>
            <div class="popupsw" id="budgetError"></div>

            <div style="margin-top:40px;">
                <p class="marginbottom10"><span class="required">*</span>&nbsp;&nbsp;<span class="font-size16">项目名称</span><span class="font-size12 color9b">(少于15个字)</span></p>
                <input type="text" class="form-control input-unified" placeholder="简洁且能概括产品的项目标题" id="name" name="name" style="width:500px;" maxlength="15">
                <input type="hidden" name="projectSolution" id="projectSolution">
                <input type="hidden" name="productFunction" id="productFunction">
                <input type="hidden" name="productIndustry" id="productIndustry">
                <input type="hidden" name="planed" id="planed" value="false">
            </div>
            <div class="popupsw" id="nameError"></div>

            <div style="margin-top:40px;">
                <p class="marginbottom10"><span class="required">*</span>&nbsp;&nbsp;<span class="font-size61">项目简介</span>&nbsp;&nbsp;<span style="font-size:12px;color:#9B9B9B;">（1000字以内）</span></p>
                <#--<textarea class="form-control input-unified" rows="5" placeholder="简单描述一下你的创意/功能/需求" id="intro" name="intro" style="width:500px;"></textarea>-->
                <input type="hidden" value="" id="intro" name="intro"/>
                <script id="editor" type="text/plain" style="width:500px;height:200px;"></script>
            </div>
            <div class="popupsw" id="introError"></div>

            <div class="hr-line"></div>

            <div>
                <p><span class="font-size16">其他描述类文档</span>&nbsp;&nbsp;<span style="font-size:12px;color:#9B9B9B;">(任何能够辅助描述项目的文档如<span style="color:#000;">产品需求文档、功能介绍说明、草图</span>等 ，小于50M)</span></p>
                <#--<input type="text" class="form-control" placeholder="简洁且能概括产品的项目标题" style="width:500px;">-->
                <div style="margin-top:20px;">
                    <div class="upload-resume" onclick="document.releaseFrom.attaFile.click()">
                        <img src="${_PATH}/res/images/upload_icon.png" width="20" height="20">
                        <input type="file" name="file" id="attaFile" style="display:none;">
                        <input type="hidden" id="attaIds" name="attaIds">
                        <span style="margin-left:10px;">上传附件描述文档</span>
                    </div>
                </div>
            </div>
            <div style="margin-top:20px;dispaly:none;" id="attaFile_show">
            </div>
            <div class="clear"></div>

            <div style="margin-top:40px;">
                <p>推荐人</p>
                <input type="text" class="form-control input-unified" placeholder="如果有，请填写推荐人的手机号或邀请码" id="recommendUser" name="recommendUser" style="width:400px;">
            </div>
            <div class="popupsw" id="remdError"></div>
            <div style="margin-top:40px;" class="more_div">
                <div class="link-line"></div>
                <span style="margin:0 20px;" id="more_to" >如果你有更多细节要告诉我们</span>
                <div class="link-line"></div>
                <div class="more_btn" id="more_btn"></div>
            </div>

            <div class="release-more" style="display:none;">
                <div>
                    <span>期望交付时间</span><img src="${_PATH}/res/images/project/arrowRight.png" class="expand-icon">
                    <div style="display:none" id="timeDiv" class="margintop10 hideDiv">
                          <span class="date-select" style="width:160px;">
                            <img src="${_PATH}/res/images/date_icon.png" class="date-icon-p" id="startTime_icon">
                            <input type="hidden" id="serverDate" value="<@serverDate patten='yyyy-MM-dd' transform='true'/>">
                            <input type="text" class="form-control input-unified marginright37 " id="startTime" name="startTime"  readonly  placeholder="起始日期" style="background-color: white;width:160px;"/>
                          </span>
                          <span class="line_input"></span>
                          <span class="date-select" style="width:160px;">
                            <img src="${_PATH}/res/images/date_icon.png" class="date-icon-p" id="endTime_icon">
                            <input type="text" class="form-control input-unified" id="expectTime" name="expectTime"  readonly  placeholder="交付日期" style="background-color: white;width:160px;"/>
                          </span>
                        <span style="margin-left:10px;">共&nbsp;<input type="text" class="form-control input-sm work-days input-unified" id="deliveryDay"  maxlength="3" readonly>&nbsp;个工作日</span>
                    </div>
                </div>
                <div class="popupsw" id="timeError"></div>

                <div style="margin-top:20px;">
                    <span>项目紧急联系人</span><img src="${_PATH}/res/images/project/arrowRight.png" class="expand-icon">
                    <div style="display:none;" class="margintop10 hideDiv">
                        <input type="text" class="form-control input-unified marginright20" placeholder="姓名" id="contactName" name="contactName" style="width:130px;display: inline-block;" maxlength="10">
                        <input type="text" class="form-control input-unified" placeholder="电话号码" id="contactPhone" name="contactPhone" style="width:300px;display: inline-block;margin-left:20px;" maxlength="32">
                    </div>
                </div>
                <div class="popupsw" id="contactError"></div>

                <div style="margin-top:20px;">
                    <span>参考项目</span><img src="${_PATH}/res/images/project/arrowRight.png" class="expand-icon">
                    <div class="margintop10 hideDiv" style="display:none;">
                        <input type="text" class="form-control input-unified" placeholder="其他相似功能的产品名称或网址" id="referProject" name="referProject" style="width:300px;display: inline-block;" maxlength="100">
                        <#--<img src="${_PATH}/res/images/add_icon2.png" class="add-url">-->
                    </div>
                </div>
                <div class="popupsw" id="referError"></div>

                <div style="margin-top:20px;">
                    <span>项目所在城市</span><img src="${_PATH}/res/images/project/arrowRight.png" class="expand-icon">
                    <div style="margin-top:10px;position: relative;width:250px;display:none;" class="hideDiv cityContainer">
                        <input type="text" class="form-control input-unified width300" placeholder="所在城市" id="areaName" name="areaName" style="padding-left:60px;">
                        <img src="${_PATH}/res/images/location_icon.png" class="location" id="cityIcon">
                        <#--<div class="form-triangle"><div class="form-trigger"></div></div>-->
                    </div>
                </div>
                <div class="popupsw" id="areaError"></div>

                <div style="margin-top:20px;">
                    <span>行业分类</span><img src="${_PATH}/res/images/project/arrowRight.png" class="expand-icon">
                    <div style="display:none;" id="indusDiv" class="margintop10 hideDiv">
                        <div class="belong-class">
                            <div class="select-box">
                                <@dict type="choice_industry"></@dict>
		                        <#list dictList as dt>
		                           <button type="button" class="industry" data="${dt.dictKey}" orders="${dt_index}">${dt.dictVal}</button>
		                        </#list>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="popupsw" id="indusError"></div>

                <div style="margin-top:20px;">
                    <span>功能分类</span><img src="${_PATH}/res/images/project/arrowRight.png" class="expand-icon">
                    <div style="margin-top:10px;display:none" class="hideDiv">
                        <@dict type="project_func"></@dict>
                        <#list dictList as dt>
                           <button type="button" class="function-btn" data="${dt.dictKey}" orders="${dt_index}">${dt.dictVal}</button>
                        </#list>
                    </div>
                </div>
                <div class="popupsw" id="funcError"></div>
                
                <div style="margin-top:20px;">
                <span>是否参与云英汇<span id="starPlan" style="cursor:pointer;">《明星开发团队持股开发计划》</span></span><img src="${_PATH}/res/images/project/arrowRight.png" class="expand-icon">
                <div style="margin-top:10px;display:none;" class="hideDiv">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default" data="Y">是</button>
                        <button type="button" class="btn btn-default active-btn" data="N">否</button>
                    </div>
                    <div class="upload-resume" style="margin-left:20px;" onclick="document.releaseFrom.planBookFile.click()">
                        <img src="${_PATH}/res/images/upload_icon.png" width="20" height="20">
                        <input type="file" name="file" id="planBookFile" style="display:none;">
                        <input type="hidden" name="planBook" id="planBook_val">
                        <span style="margin-left:10px;">上传商业计划书</span>
                    </div>
                </div>
                <div style="margin-top:20px;dispaly:none;" id="planBook_show">
	            </div>
	            <div class="clear"></div>
                
               </div>
            </div>
            <button type="button" class="btn-ok" id="saveBtn" style="margin-left:0;margin-top:60px;">完成并发布</button>
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
	
	<div class="complete-success" style="display: none">
        <div class="resu">
			<div class="paddingtop50 padingleft58 lineh50 resu-a"><span class="resu-a-bg fl marginright36"></span><span class="color2c fl fontsize24">恭喜！你已成功发布项目！</span></div>
			<div class="margintop69 resu-b">
				<span class="colorFE fontsize14 fl db lineh40" id="down_time">6s</span>
				<span class="color9B fl db lineh40 marginright30" id="enter_index">后进入个人主页</span><a href="javascript:void(0);" class="resu-btn fl db">进入个人主页</a>
			</div>
		</div>
	</div>
    </@block>

    <@block name="script">
       <script src="${_PATH}/res/script/plugin/ueditor/ueditor.config.js"></script>
       <script src="${_PATH}/res/script/plugin/ueditor/ueditor.all.min.js"></script>
       <script src="${_PATH}/res/script/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
       <script src="${_PATH}/res/script/myjs/project/publish.js"></script>
       <script src="${_PATH}/res/script/myjs/member/header.js"></script>
    </@block>
</@extend>