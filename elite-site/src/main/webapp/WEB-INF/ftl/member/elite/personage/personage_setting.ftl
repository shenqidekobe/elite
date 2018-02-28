<form id="personForm" name="persoonForm" >
	<div class="ceo-phone">
	    <div class="clearfloat marginbottom30 paddingtop30"> <label>手机号 </label><input type="text" readonly value="${obj.formatPhone}" class="width278 phone_input"></div>
	    <div class="clearfloat marginbottom30"> <label>当前密码 </label><input type="password" placeholder="请输入当前密码" id="oldPass" name="oldPass" class="width234"></div>
	    <div class="clearfloat marginbottom30"> <label>新密码 </label><input type="password" placeholder="最小长度为6个字符" id="newPass" name="newPass" class="width234"></div>
	    <div class="clearfloat marginbottom30"> <label>确认密码 </label><input type="password" placeholder="请再次输入密码" id="newPass1" name="newPass1" class="width234"></div>
	    
	    <div class="clearfloat margintop50 relative" >
	    	<div class="company_error" style="display:none;"></div>
		    <button type="button" class="save">保存</button>
		    <button type="button" class="cancel">取消</button>
	    </div>
	</div>
</form>