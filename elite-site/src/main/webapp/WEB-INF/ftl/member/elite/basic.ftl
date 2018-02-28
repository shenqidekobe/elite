<@extend name="completeLayout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css//index/ceo_main.css"/>
    <link rel="stylesheet" href="${_PATH}/res/script/plugin/datepicker/datepicker3.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/kuCity.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/register/eliteBasic.css"/>
    </@block>

    <#--中间正文部分-->
    <@block name="content">

    <div class="process">
    <div class="box-container-elite">
        <div class="process-box-elite">
            <div class="process-circle active-process">
                <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
            </div>
            <div class="process-circle active-process" style="left:269px;">2</div>
            <div class="process-circle" style="left:534px;">3</div>
            <div class="process-circle" style="left:805px;">4</div>
            <p class="process-title">当前状态</p>
            <p class="process-title active-title" style="left:266px;">基本信息</p>
            <p class="process-title" style="left:532px;">技能<span>|</span>经历</p>
            <p class="process-title" style="left:799px;">个人征信</p>

            <div class="process-line finish-line"></div>
            <div class="process-line" style="left:337px;"></div>
            <div class="process-line" style="left:602px;"></div>
        </div>
    </div>
    </div>

    <div class="content" id="eliteBasic_content">
        <div style="position: relative;">
            <form class="form-group form-horizontal form-person" role="form" id="personFrom" name="personFrom">
            	
                
                <input type="hidden" name="id" value="${obj.id}">
                <input type="hidden" name="photoId" id="photoId" value="${head.id}">
                <input type="hidden" name="sex" id="sex" value="${obj.sex}">
                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label"><div class="title-input"><span class="required">* </span> 出生年月<span class="secret-circle">密</span></div></label>
                    <div class="col-xs-4 col-md-4">
                         <span class="date-select">
                            <img src="${_PATH}/res/images/date_icon.png" class="date-icon">
                            <input type="text" class="form-control input-unified width300"  id="birthday" name="birthday"  readonly value="${obj.birthday}" placeholder="请选择你的出生年月" style="background-color: white;"/>
                        </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label"><div class="title-input" style="padding-left: 30px"><span class="required">* </span> 性别</div></label>
                    <div class="col-xs-4 col-md-4">
                        <div class="btn-group sex">
                            <button type="button" class="btn btn-default active-btn" data="M">男</button>
                            <button type="button" class="btn btn-default" data="F">女</button>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label"><div class="title-input"><span class="required">* </span> 所在城市</div></label>
                    <div class="col-xs-4 col-md-4">
                        <div class="birth-box cityContainer">
                            <input class="form-control input-unified width300" type="text" id="areaName" name="areaName" value="${obj.areaName}" placeholder="选择所在城市" style="background-color: white;padding-left:60px;">
                            <img src="${_PATH}/res/images/location_icon.png" class="location" id="cityIcon">
                            <#--<div>
                                <ul class="year-select">
                                <li data-type="workYear" style="padding-right:26px;">${i?c}&nbsp;年</li>
                                </ul>
                                <div class="form-triangle">
                                    <div class="form-trigger"></div>
                                </div>
                            </div>-->

                        </div>
                    </div>
                </div>

                <#--上传头像-->
                <div class="upload-head">
	                <input type="hidden" id="head" value="${head.attaName}">
					<#if head!="">
					    <img src="" id="headImg" style="border-radius: 50px;" title="支持jpg/jpeg/png/bim/bmp格式，文件小于2M">
					<#else>
					    <img src="${_PATH}/res/images/head.png" id="headImg" style="border-radius: 50px;" title="支持jpg/jpeg/png/bim/bmp格式，文件小于2M">
					</#if>
	                <input type="file" name="file" id="headFile" style="display:none;">
	                <input type="hidden" name="photoUrl" id="photoUrl">
	                <div class="modifyBtn">上传头像</div>
                </div>
                <#--上传头像弹窗-->
                <div class="headWindow" style="display:none;" save="N"></div>

                <div class="split-line2-elite"></div>

                <div class="form-group" style="margin-top:50px;">
                    <label class="col-xs-2 col-md-2 control-label"><div class="title-input"><span class="required">* </span> 绑定邮箱</div></label>
                    <div class="col-xs-5 col-md-5">
                        <input class="form-control input-unified width400" type="email" value="${obj.email}" id="email" name="email" placeholder="用于账户登录及项目进展的消息推送" maxlength="32">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label"><div class="title-input"><span class="required">* </span>职位头衔</div></label>
                    <div class="col-xs-6 col-md-6">
                        <input class="form-control input-unified width400" type="text" id="memberSign" value="${obj.memberSign}" name="memberSign" placeholder="请输入你的职位头衔" maxlength="20">
                    </div>
                </div>
               
               
                <div class="form-opt">
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
    </@block>

    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/member/elite/basic.js"></script>
    </@block>
</@extend>
<