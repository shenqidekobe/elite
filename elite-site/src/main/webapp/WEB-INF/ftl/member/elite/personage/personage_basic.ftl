
    <div class="personage_basic">
        <div style="position: relative;">
            <form class="form-group form-horizontal form-person" role="form" id="basicForm" name="basicFrom" style="padding-left:0;">
             
                <input type="hidden" name="id" value="${obj.id}">
                <input type="hidden" name="photoId" id="photoId" value="${head.id}">
                <input type="hidden" name="sex" id="sex" value="${obj.sex}">
                <#--
                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label text_public">用户名</label>
                    <div class="col-xs-4 col-md-4">
                         <input type="text" class="form-control input_pubic" placeholder="Louis" style="background-color: white;width:300px;"/>
                    </div>
                </div>
                -->
                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label text_public">用戶名</label>
                    <div class="col-xs-6 col-md-6">
                        <input type="text" class="form-control"  id="nickName" name="nickName" value="${member.nickName}" placeholder="请输入用户名" style="background-color: white;width:300px;" maxlength="16"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label text_public"> 出生年月</label>
                    <div class="col-xs-4 col-md-4">
                         <span class="date-select">
                            <img src="${_PATH}/res/images/date_icon.png" class="date-icon">
                            <input type="text" class="form-control"  id="birthday" name="birthday"  readonly value="${obj.birthday}" placeholder="请选择你的出生年月" style="background-color: white;width:300px;"/>
                        </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label text_sex">性别</label>
                    <div class="col-xs-8 col-md-8">
                        <div class="btn-group sex">
                            <div class="sexDiv">
                            	<img class="sexImg" src="/res/images/elite/man_select.png" alt="">
                            	<button type="button" class="btn-default active-btn input_sex" data="M" style="margin-right:19px;">男</button>
                            </div>
                            <div class="sexDiv">
                            	<img class="sexImg" src="/res/images/elite/woman_noselect.png" alt="">
                            	<button type="button" class="btn-default" data="F">女</button>
                            </div>
                            
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label text_public">所在城市</label>
                    <div class="col-xs-4 col-md-4">
                        <div class="birth-box cityContainer">
                            <input class="form-control" type="text" id="areaName" name="areaName" value="${obj.areaName}" placeholder="选择所在城市" style="background-color: white;padding-left:38px;">
                            <img src="${_PATH}/res/images/location_icon.png" class="location" id="cityIcon">
                        </div>
                    </div>
                </div>
                
				<div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label text_public">头衔</label>
                    <div class="col-xs-6 col-md-6">
                        <input class="form-control" type="text" id="memberSign" value="${obj.memberSign}" name="memberSign" style="background-color: white;width:315px;" maxlength="20">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label text_public">绑定邮箱</label>
                    <div class="col-xs-5 col-md-5">
                        <input class="form-control" type="email" value="${obj.email}" id="email" name="email" style="background-color: white;width:315px;" placeholder="用于账户登录及项目进展的消息推送" maxlength="32">
                    </div>
                </div>
                
                
              
                <div class="form-opt">
                	<div class="error-tip">
	                    <span id="tipError"></span>
	                </div>
                    <button type="button" class="btn-ok" id="saveBtn">保存</button>
                    <button type="button" class="btn-no" id="cancel">取消</button>
                </div>
            </form>
        </div>

    </div>
