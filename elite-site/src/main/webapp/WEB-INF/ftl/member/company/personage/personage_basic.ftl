<div id="companyPersonageBasic">
<form id="basicFrom" name="basicFrom">
	<div class="ceo-int">
	    <div class="ceo-int-content">
	        <input type="hidden" name="id" value="${obj.id}">
	        <input type="hidden" name="sex" id="sex" value="${obj.sex}">
	        <div class="clearfloat marginbottom20"><label>昵称</label><input type="text" value="${member.nickName}" name="nickName" id="nickName" maxlength="20" class="width278"></div>
	        <div class="clearfloat marginbottom20"><label>出生年月</label><input type="text" id="birthday" name="birthday" readonly value="${obj.birthday}" class="width278"></div>
	        <div class="clearfloat marginbottom20">
                <label>性别</label>
                <div class="col-xs-8 col-md-8">
                    <div class="btn-group sex">
                    	<div class="manBox" style="margin-left: -15px;"><img class="sex_img" id="man" src="${_PATH}/res/images/ceo/man_select.png"><button type="button" class="btn-default active-btn input_sex" data="M" style="margin-right:19px;">男</button></div>
                    	<div class="womanBox"><img class="sex_img"  id="woman" src="${_PATH}/res/images/ceo/woman_noselect.png"><button type="button" class="btn-default" data="F">女</button></div>
                    </div>
                </div>
            </div>
	        <div class="clearfloat marginbottom20 cityDiv cityBox">
		        <label>所在城市</label>
		        <div class="cityContainer"><span class="city-icon" id="cityIcon"></span><input type="text" class="width278" id="areaName" name="areaName" value="${obj.areaName}" maxlength="10" style="padding-left:100px;"></div>
	        </div>
	        <div class="clearfloat marginbottom20"><label>邮箱</label><input type="email" id="email" name="email" value="${obj.email}" class="width278" maxlength="32"></div>
	        <div class="clearfloat marginbottom20"><label>职位/头衔</label><input type="text"  id="memberSign" name="memberSign" value="${obj.memberSign}" maxlength="20" class="width464"></div>
	        
	        <div class="btnBox">
	        	<div class="error_div" style="display:none;"><span class="error_icon"></span><span class="error_text"></span></div>
		        <button type="button" class="save">保存</button>
		        <button type="button" class="cancel">取消</button>
	        </div>
	    </div>
	</div>
</form>
</div>