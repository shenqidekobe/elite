<div id="companyPersonagecompany">
<div class="ceo-login">
    <div class="login-title">
        <ul class="clearfloat">
            <li class="<#if obj.companyed>yet</#if>" data="Y">已注册公司</li>
            <li class="<#if !obj.companyed>yet</#if>" data="N">尚未注册公司</li>
        </ul>
    </div>
    <input type="hidden" id="companyedFlag" value="${obj.companyed}">
    <div class="login-content ">
        <form id="personForm1" name="personForm1">
	        <div class="yed-box" <#if !obj.companyed>style="display: none"</#if>>
	            <div class="clearfloat marginbottom30">
	                <span>担任角色</span>
	                <input type="text" id="companyPosition" name="companyPosition" value="${obj.companyPosition}" maxlength="20" class="width335">
	            </div>
	            <input type="hidden" name="id" value="${obj.id}">
	            <div class="clearfloat marginbottom30">
	                <span>公司名称</span>
	                <input type="text" id="companyName" name="companyName" value="${obj.companyName}" maxlength="20" class="width335">
	            </div>
	            <input type="hidden" name="id" value="${obj.id}">
	            <input type="hidden" name="companyed" id="companyed" value="${obj.companyed}">
	            <div class="clearfloat number marginbottom30">
	                <span>公司规模</span>
	                <ul>
	                    <@dict type="company_scale"></@dict>
	                    <#list dictList as dt>
	                       <span class="check-custom" style="cursor:pointer">
	                           <#if obj.companyScale==dt.dictKey>
	                               <li data=""><input type="radio" id="companyScale" name="companyScale" value="${dt.dictKey}" checked>${dt.dictVal}</li>
	                           <#else>
	                           	   <li data="${dt.dictKey}"><input type="radio" id="companyScale" name="companyScale" value="${dt.dictKey}">${dt.dictVal}</li>
	                           </#if>
	                       </span>
	                    </#list>
	                </ul>
	            </div>
	            <div class="clearfloat companyDiv marginbottom30">
	                <span>公司简介</span>
	                <div class="company-box">
	                    <input type="hidden" value="" id="companyIntro" name="companyIntro"/>
	                    <script id="editor1" type="text/plain" style="width:500px;height:200px;">${obj.companyIntro}</script>
	                    <#--<textarea class="company-text" id="companyIntro" name="companyIntro">${obj.companyIntro}</textarea>-->
	                </div>
	            </div>
	            
	            <div class="btnBox">
	            	<div class="error_div" style="display:none;"><span class="error_icon"></span><span class="error_text"></span></div>
		            <button type="button" class="save" data="personForm1">保存</button>
		            <button type="button" class="cancel">取消</button>
	            </div>
	        </div>
        </form>
        <form id="personForm2" name="personForm2" class="relative">
	        <div class="not-box" <#if obj.companyed>style="display: none"</#if>>
	            <div class="clearfloat marginbottom30 title">
	                <span>你的职位</span>
	                <input type="text" id="companyPosition" name="companyPosition" value="${obj.companyPosition}" maxlength="20" class="width464">
	            </div>
	            <input type="hidden" name="id" value="${obj.id}">
	            <input type="hidden" name="companyed" id="companyed" value="${obj.companyed}">
	            <div class="clearfloat number marginbottom30">
	                <span >团队规模</span>
	                <ul>
	                    <@dict type="company_scale"></@dict>
	                    <#list dictList as dt>
	                       <span class="check-custom" style="cursor:pointer">
	                           <#if obj.teamNum==dt.dictKey>
	                               <li data=""><input type="radio" id="teamNum" name="teamNum" value="${dt.dictKey}" checked>${dt.dictVal}</li>
	                           <#else>
	                           	   <li data="${dt.dictKey}"><input type="radio" id="teamNum" name="teamNum" value="${dt.dictKey}">${dt.dictVal}</li>
	                           </#if>
	                       </span>
	                    </#list>
	                </ul>
	            </div>
	
	            <div class="clearfloat companyDiv marginbottom30">
	                <span>团队介绍</span>
	                <div class="company-box">
	                    <input type="hidden" value="" id="teamIntro" name="teamIntro"/>
	                    <script id="editor2" type="text/plain" style="width:500px;height:200px;">${obj.teamIntro}</script>
	                    <#--<textarea class="company-text" id="teamIntro" name="teamIntro">${obj.teamIntro}</textarea>-->
	                </div>
	            </div>
	            
	            <div class="btnBox">
	            	<div class="error_div" style="display:none;"><span class="error_icon"></span><span class="error_text"></span></div>
		            <button type="button" class="save" data="personForm2">保存</button>
		            <button type="button" class="cancel">取消</button>
	            </div>
	            </div>
	        </div>
	    </form>
    </div>
</div>
</div>
