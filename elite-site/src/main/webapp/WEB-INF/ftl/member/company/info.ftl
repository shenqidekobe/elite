<@extend name="completeLayout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/company/companyContainer.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/ceo_main.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/register/projectInfo.css"/>
    </@block>

    <#--中间正文部分-->
    <@block name="content">

        <div class="process" id="projectInfo_process">
        	<div class="process-box-container">
        			<div class="process-box">
        					<div class="process-circle active-process">
                    <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
                </div>
                <div class="process-circle active-process" style="left:274px;">2</div>
                <div class="process-circle" style="left:539px;">3</div>
                <p class="process-title active-title">基本资料</p>
                <p class="process-title active-title" style="left:266px;">创业属性</p>
                <p class="process-title" style="left:532px;">征信信息</p>

                <div class="process-line finish-line"></div>
                <div class="process-line" style="left:340px;"></div>
            		</div>
        	</div>
        </div>

        <div class="content" style="margin-top: -100px;" id="projectInfo_content">
            <#--选择已注册或未注册的公司-->
            <div class="select-status">
                <span id="have_company" style="cursor:pointer">已注册公司</span>
                <span id="unhave_company" style="margin-left:184px;cursor:pointer;">尚未注册公司</span>
                <div class="select-line"></div>
                <div class="active-line" <#if !obj.companyed>style="margin-left: 270px;"</#if>></div>
            </div>

            <form class="form-group form-horizontal form-person  paddingtop30" role="form" id="companyFrom">
               
                 <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                    <div class="title-input">
                        <span class="required">* </span>  头衔/职位  <span class="secret-circle">密</span>
                    </div>
                    </label>
                    <div class="col-xs-5 col-md-5">
                        <input class="form-control input-unified width400" type="text" id="companyPosition" name="companyPosition" value="${obj.companyPosition}" placeholder="你在公司担任的角色" maxlength="20">
                    </div>
                </div>
                <div id="already" style="<#if !obj.companyed>display:none;</#if>">
	                <input type="hidden" name="id" value="${obj.id}">
	                <input type="hidden" name="companyScale" id="companyScale" value="${obj.companyScale}">
	                <input type="hidden" name="teamNum" id="teamNum" value="${obj.teamNum}">
	                <input type="hidden" name="companyed" id="companyed" value="${obj.companyed}">
	                <div class="form-group">
	                    <label class="col-xs-2 col-md-2 control-label">
	                    	<div class="title-input">
	                        		<span class="required">* </span> 公司名称<span class="secret-circle">密</span>
	                        </div>
	                    </label>
	                    <div class="col-xs-5 col-md-5">
	                        <input class="form-control input-unified width400" type="text" id="companyName" name="companyName" value="${obj.companyName}" placeholder="你的公司名称" maxlength="20">
	                    </div>
	                </div>
	
	                <div class="form-group">
	                    <label class="col-xs-2 col-md-2 control-label">
	                    	<div class="title-input">
	                        		<span class="required">* </span> 公司规模<span class="secret-circle">密</span>
	                        </div>
	                    </label>
	                    <div class="col-xs-10 col-md-10">
	                        <@dict type="company_scale"></@dict>
	                        <div class="radioDiv">
	                        <#list dictList as dt>
	                           <span class="check-custom" style="cursor:pointer">
	                               <#if obj.companyScale==dt.dictKey>
		                               <img src="${_PATH}/res/images/radio_check_icon.png" class="radio-box check-radio">
	                               <#else>
	                               	   <img src="${_PATH}/res/images/radio_empty_icon.png" class="radio-box check-radio">
	                               </#if>
		                           <span class="check-title" data="${dt.dictKey}">${dt.dictVal}</span>
	                           </span>
	                        </#list>
	                        </div>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="col-xs-2 col-md-2 control-label">
	                    	<div class="title-input">
	                            <span class="required">* </span> 公司简介<span class="secret-circle">密</span>
	                        </div>
	                    </label>
	                    <div class="col-xs-7 col-md-7">
	                        <#--<input type="hidden" value="" id="companyIntro" name="companyIntro"/>
	                        <script id="editor1" type="text/plain" style="width:500px;height:200px;">${obj.companyIntro}</script>-->
	                        <textarea class="form-control textarea-unified width506" rows="8" id="companyIntro" name="companyIntro" placeholder="简单介绍一下你的公司，投资人可能会作简单浏览 (少于200字)" maxlength="200">${obj.companyIntro}</textarea>
	                    </div>
	                </div>
                </div>
                
                <div id="unalready" style="<#if obj.companyed>display:none;</#if>">
	                <div class="form-group">
	                    <label class="col-xs-2 col-md-2 control-label">
	                    	<div class="title-input">
	                        		<span class="required">* </span> 团队人数<span class="secret-circle">密</span>
	                        </div>
	                    </label>
	                    <div class="col-xs-10 col-md-10">
	                    	<div class="radioDiv">
	                        <#list dictList as dt>
	                           <span class="check-custom" style="cursor:pointer">
		                           <#if obj.teamNum==dt.dictKey>
		                               <img src="${_PATH}/res/images/radio_check_icon.png" class="radio-box check-radio">
	                               <#else>
	                               	   <img src="${_PATH}/res/images/radio_empty_icon.png" class="radio-box check-radio">
	                               </#if>
		                           <span class="check-title" data="${dt.dictKey}">${dt.dictVal}</span>
	                           </span>
	                        </#list>
	                        </div>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="col-xs-2 col-md-2 control-label">
	                    		<div class="title-input">
	                    			<span class="required">* </span>团队介绍<span class="secret-circle">密</span>
	                    		</div>
	                    </label>
	                    <div class="col-xs-7 col-md-7">
	                        <#--<input type="hidden" value="" id="teamIntro" name="teamIntro"/>
	                        <script id="editor2" type="text/plain" style="width:500px;height:200px;">${obj.teamIntro}</script>-->
	                        <textarea class="form-control textarea-unified width506" id="teamIntro" name="teamIntro" rows="8" placeholder="简单介绍一下你的公司，投资人可能会作简单浏览 (少于200字)" maxlength="200">${obj.teamIntro}</textarea>
	                    </div>
	                </div>
                </div>
                
                <div class="form-opt" style="margin-top:60px;">
                	<div class="error_div" style="display:none;">
                		<span class="error_icon"></span>
	                    <span id="tipError" class="error_text"></span>
	                </div>
                    <a href="javascript:void(0);" style="color: #FEA600;font-size: 18px" id="prevBtn">上一步</a>
                    <button type="button" class="btn-ok" id="saveBtn">下一步</button>
                    <a href="javascript:void(0);" style="font-size: 14px;" id="skip" class="color2c">暂时跳过</a>
                </div>
            </form>
            
        </div>
    </@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/company/info.js"></script>
        <script src="${_PATH}/res/script/plugin/ueditor/ueditor.config.js"></script>
        <script src="${_PATH}/res/script/plugin/ueditor/ueditor.all.min.js"></script>
        <script src="${_PATH}/res/script/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
    </@block>
</@extend>