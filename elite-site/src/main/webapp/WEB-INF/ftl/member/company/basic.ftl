<@extend name="completeLayout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/company/companyContainer.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/ceo_main.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/script/plugin/datepicker/datepicker3.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/kuCity.css"/>
    </@block>

    <#--中间正文部分-->
    <@block name="content">
        <div class="process">
        	<div class="process-box-container">
        			<div class="process-box">
		                <div class="process-circle active-process">1</div>
		                <div class="process-circle" style="left:274px;">2</div>
		                <div class="process-circle" style="left:539px;">3</div>
		                <p class="process-title active-title">基本资料</p>
		                <p class="process-title" style="left:266px;">创业属性</p>
		                <p class="process-title" style="left:532px;">征信信息</p>
		                <div class="process-line"></div>
		                <div class="process-line" style="left:340px;"></div>
                   </div>	
        	</div>
        </div>
        
        <div class="content" style="margin-top:-100px;min-height:600px;">
            <div style="position: relative;">
                <form class="form-group form-horizontal form-person paddingtop60" role="form" id="personFrom" name="personFrom">
                <input type="hidden" name="id" value="${obj.id}">
                <input type="hidden" name="photoId" id="photoId" value=${obj.photoId}>
                <#--<div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
            			<div class="title-input">
	                    		<span class="required">* </span> 出生年月<span class="secret-circle">密</span>
	                    </div>
                    </label>
                    <div class="col-xs-4 col-md-4">
                         <span class="date-select">
                            <img src="${_PATH}/res/images/date_icon.png" class="date-icon-new" id="birthday_img">
                            <input type="text" class="form-control input-unified width300" id="birthday" name="birthday"  readonly value="${obj.birthday}" placeholder="请选择你的出生年月" style="background-color: white;"/>
                        </span>
                    </div>
                </div>-->

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                    		<div class="title-input"><span class="required">* </span> 所在城市</div>
                    </label>
                    <div class="col-xs-4 col-md-4">
                        <div class="birth-box cityContainer">
                            <input class="form-control input-unified width300 " type="text" id="areaName" name="areaName" value="${obj.areaName}" placeholder="选择所在城市" style="background-color: white;padding-left:60px;" maxlength="10">
                            <img src="${_PATH}/res/images/location_icon.png" class="location" id="cityIcon">
                            <#--<div>
                                <ul class="year-select">
                                <li data-type="workYear" style="padding-right:26px;">${i?c}&nbsp;年</li>
                                </ul>
                                <div class="form-triangle-new" style="right: 26px;">
                                    <div class="form-trigger"></div>
                                </div>
                            </div>-->

                        </div>
                    </div>
                </div>

                <#--上传头像-->
                <div class="upload-head">
                    <#if obj.photoId??>
                        <img src="${obj.memberPhoto.path}" id="headImg" style="border-radius: 50px" title="支持jpg/jpeg/png/bim/bmp格式，文件小于2M">
                    <#else>
                        <img src="${_PATH}/res/images/head.png" id="headImg" style="border-radius: 50px" title="支持jpg/jpeg/png/bim/bmp格式，文件小于2M">
                    </#if>
                    <input type="file" name="file" id="headFile" style="display:none;">
                    <input type="hidden" name="photoUrl" id="photoUrl">
                    <div class="modifyBtn">上传头像</div>
                </div>
                <#--上传头像弹窗-->
                <div class="headWindow" style="display:none;" save="N"></div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                    		<div class="title-input">
                    					<span class="required">* </span> 绑定邮箱
                    		</div>
                    </label>
                    <div class="col-xs-5 col-md-5">
                        <input class="form-control input-unified width400" type="email" id="email" name="email" value="${obj.email}" placeholder="用于账户登录及项目进展的消息推送" maxlength="32">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                    	<div class="title-input"><span class="required">* </span>头衔/职位</div>	      
                    </label>
                    <div class="col-xs-6 col-md-6">
                        <input class="form-control input-unified width400" type="text" id="memberSign" name="memberSign" value="${obj.memberSign}" placeholder="请输入你的职位头衔，少于20个字" maxlength="20">
                    </div>
                </div>
                
                

                <div class="form-opt">
                	<div class="error_div" style="display:none;">
                		<span class="error_icon"></span>
	                    <span id="tipError" class="error_text"></span>
	                </div>
                    <button type="button" class="btn-ok" id="saveBtn">下一步</button>
                    <a href="javascript:void(0);" style="font-size: 14px;" id="skip" class="color2c">先跳过，去发布项目</a>
                </div>
            </form>
            </div>

        </div>
    </@block>

    <@block name="script">
	    <script src='${_PATH}/res/script/myjs/member/company/basic.js'></script>
	    <script src='${_PATH}/res/script/myjs/member/customHead.js'></script>
    </@block>
</@extend>