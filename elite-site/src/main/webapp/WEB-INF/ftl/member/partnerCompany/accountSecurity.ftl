<div id="accountSecurity">
    <div class="partnerElite_data">
    
				<!-- 人才方-个人资料	 -->
                <div class="basicBox" style="min-height:520px;">
	               	<div class="line-box"> 基本资料</div>
	                <form class="basicForm"  id="basicForm" action="" style="min-height:414px;">
	                
	                   <div class="labelDiv">
	                        <label>用户名</label>
	                        <div class="inputDiv">
	                        	<input type="text" placeholder="请输入您的用户名" value="${it.nickName}"  id="nickName" maxlength="20">
	                        </div>
	                    </div>
	                    
	                   <div class="labelDiv">
	                            <label>性别</label>
	                            <div class="inputDiv">
	                            	<ul class="sex_ul" id="selectSexPan">
	                            	 <#if it.basic.sex=='F'>
	                            	   <li class="default_bg" id="manselectbg" data="M"><span class="sex_icon man_noselect" id="manselecticon"></span><span class="sex_text default_color" id="manselectcolor">男</span></li>
	                            		<li class="active_bg"  id="womanselectbg" data="F"><span class="sex_icon woman_select" id="womanselecticon"></span><span class="sex_text active_color" id="womanselectcolor">女</span></li>
	                                    <input type="hidden" value="F" id="sexId" name="sex">
                                       <#else>
	                                    <li class="active_bg" id="manselectbg" data="M"><span class="sex_icon man_select" id="manselecticon"></span><span class="sex_text active_color" id="manselectcolor">男</span></li>
	                            		<li class="default_bg" id="womanselectbg" data="F"><span class="sex_icon woman_noselect" id="womanselecticon"></span><span class="sex_text default_color" id="womanselectcolor">女</span></li>
	                                          <input type="hidden" value="M" id="sexId" name="sex">
	                                  </#if>
	                            	</ul>
	                            </div> 
	                     </div>
	                     
	                     
	                     <div class="labelDiv city_select"  >
	                        <label>所在城市</label>
	                        <div class="inputDiv cityContainer">
	                        	<input type="text" id="areaName" name="areaName" placeholder="选择所在城市" value="${it.basic.areaName}"  id="areaName">
	                        	<img src="${_PATH}/res/images/location_icon.png" class="location_icon" id="cityIcon">
	                        </div>  
	                    </div>
	                    
	                
	                    
	                    <div class="labelDiv" id="displayCompany">
	                        <label>身份类型</label>
	                        <div class="inputDiv">
	                        	     <#if it.partnerCompany.type??>
				        		    <input type="text" value="${it.partnerCompany.type.label}" id="selectTypeId" readonly>
				        		    <#else>
				        		     <input type="text" placeholder="请选择"   readonly="readonly" id="selectTypeId" readonly>
	                                 </#if>
		                              <input type="hidden"  name="type" value="${it.partnerCompany.type}" id="selectTypeValueId">
		                             <span class="triangleBtn triangleBtn_down" id="selectTypeIconId"></span>
		                             <ul class="selectUl" id="selectTypeUlId" hidden>
		                                  <#list typelist as role>
		                                      <li class="selectUl_li" data-type="workTitle"  text="${role}" data="${role}">${role.label}</li>
		                                   </#list>
		                             </ul>   
	                        </div>
	                    </div>
	                    
	                    <div class="labelDiv">
	                            <label>邮箱</label>
	                            <div class="inputDiv">
	                            	<input type="text" placeholder="用于消息通知和登录" value="${it.basic.email}" id="email" maxlength="32">
	                            </div> 
	                     </div>
	                        
	                      <div class="labelDiv" id="displayCompany">
	                        <label>机构名称</label>
	                        <div class="inputDiv">
	                        	 <input type="text" placeholder="请输入所在机构名称" value="${it.partnerCompany.companyName}"  id="companyName" maxlength="20">
	                        </div>
	                    </div>
	                    
	                     
	                    
	                    <div class="saveBtn" id="saveAccountBasicBtn">
	                    	<div class="error_div" id="basicErrorId" hidden><span class="error_icon"></span><span class="error_text" id="basicErrorMessageId"></span></div>
	                    	保存
	                    </div>
	                    
	                </form>
                
                </div>
                
                
             <div class="creditBox" style="min-height:530px;">
               	<div class="line-box" ><span>征信信息</span><span>（征信信息一经验证无法修改，请如实填写，平台会严格保密）</span></div>
                <form action="" name="picForm" id="creditInfoForm" class="creditForm" style="min-height:280px;"> 
                
                		<div class="identity_group clearfloat">
                			<div class="labelDiv">
		                        <label>姓名</label>
		                        <div class="inputDiv">
		                        	<input type="text" placeholder="请输入姓名" value="${it.credit.realName}"  id="realNameId" maxlength="20"
		                        	<#if it.credit.isCard==true>
		                        	readonly
		                        	</#if>
		                        	>
		                        </div>
		                    </div>
	                    
	                        <div class="labelDiv">
	                            <label>身份证号</label>
	                            <div class="inputDiv">
	                            	<input type="text" placeholder="请输入身份证号" name="idCardId" value="${it.credit.idCard}" id="idCardId"  maxlength="18"
	                            	<#if it.credit.isCard==true>
		                        	readonly
		                        	</#if>
	                            	>
	                            </div>
	                        </div>   
                		</div>
                		
                		<div class="certificate_group clearfloat">
                			<div class="photoBox">
	                         	<div class="photo_text">上传身份证正面</div>
	                         	<div class="photo_img">
	                         		<#if it.credit.cardJust??>
			                           <img class="idbox" onclick="document.picForm.cardJustFile.click()" id="cardJustImg" src="${it.credit.cardJustPhoto.path}">
			                    	<#else>
			                       		<img class="idbox" onclick="document.picForm.cardJustFile.click()" id="cardJustImg" src="${_PATH}/res/images/elite/ID_front.png">
			                    	</#if>
			                         <input type="file" name="file" id="cardJustFile" style="display:none;">
		                             <input type="hidden" name="cardJust" id="cardJustId"  value="${it.credit.cardJust}"/>
		                            </img>
	                         	</div>
	                         </div>
                         
                           
                          <div class="photoBox">
                         	<div class="photo_text">上传身份证反面</div>
                         	<div class="photo_img">
                         		<#if it.credit.cardReverse??>
		                           <img class="idbox" onclick="document.picForm.cardReverseFile.click()" id="cardReverseImg" src="${it.credit.cardReversePhoto.path}">
		                    	<#else>
		                       		<img class="idbox" onclick="document.picForm.cardReverseFile.click()" id="cardReverseImg" src="${_PATH}/res/images/elite/ID_front.png">
		                    	</#if>
		                        <input type="file" name="file" id="cardReverseFile" style="display:none;">
	                             <input type="hidden" name="cardReverse" id="cardReverseId"  value="${it.credit.cardReverse}"/>
                         	</div>
                         </div>
                		</div>
                </form>
                
                <div class="saveBtn" id="saveAccountCreditBtn"><div class="error_div" id="creditErrorId" hidden><span class="error_icon"></span><span class="error_text" id="creditErrorMessageId"></span></div>保存</div>
                
              
               </div>
                
                <div class="accountBox">
                	<div class="line-box">账号设置</div>
	                <form action="" class="accountForm">
	                    <div class="phoneDiv">
	                        <label>当前手机号:</label>
	                        <input id="inpt" type="tel" value="${it.formatPhone}"  disabled="true" >
	                    </div>
	                   <div class="passwordDiv">
	                   		<label>修改密码:</label>
	                        <button class="restBtn" type="reset" id="restPassBtn" >修改</button>
	                    </div>
	                    <div class="cardDiv" style="display:none;">
	                    	<label>提现账号:</label>
	                    	<div class="bankGroup">
	                    		<img class="bank_logo" src="/res/images/channel/icbc.png" alt="银行logo">
	                    		<div class="bank_number"><span>尾号</span><span>9908</span></div>
	                    		<div class="bank_id"><span class="arrow_icon"></span>储蓄卡</div>
	                    		<div class="bank_delete">删除</div>
	                    	</div>
	                    	<div class="bankAdd">
	                    		<span class="add_icon"></span>
	                    		<span class="add_text">添加银行卡</span>
	                    	</div>
	                    </div>
	                </form>
            	</div>
            
            

    
    <!-- 人才方-个人资料    -->

    <!-- 修改密码 -->
    <div class="passwordWindow" id="contean-account" style="display: none">
        <form id="resetPassForm" class="windowForm">
        	<div class="close_btn" id="closeRestSetFormId"></div>
        	<div class="form_bg"></div>
        	<h1>修改密码</h1>
            <div class="group">
                <label>原密码</label>
                <input type="password" placeholder="请输入原密码" id="oldPasswordId">
            </div>
            <div class="group">
                <label>新密码</label>
                <input type="password" placeholder="请输入新密码" id="newPasswordId">
            </div>
            <div class="group">
                <label>确认密码</label>
                <input type="password" placeholder="请再次确认密码" id="repeatNewPasswordId">
            </div>
            <div class="btnDiv">
            	<div class="error_div" id="accountErrorId" hidden>
            		<span class="error_icon" ></span>
            		<span class="error_text" id="accountErrorMessageId"></span>
           		</div>
                <button type="button" value="保存" id="restPassSubBtn" class="saveBtn">保存</button> 
                <button type="button" value="取消" id="restPassCancelBtn" class="cancelBtn">取消</button> 
            </div>
        </form> 
    </div>
    
    
   <!-- 保存成功弹窗 -->
    <div class="window"  id="MassageBoxId" hidden>
    	<div class="window_bg"></div>
    	<form class="windowForm">
    		<div class="close_btn" id="successMessageCloseBtn"></div>
    		<div class="logoDiv"><span class="logo success"></span></div>
    		<div class="textDiv success_text">保存成功</div>
    		<div class="btnDiv"><button type="button" class="saveBtn" id="successMessageBtn">确定</button></div>
    	</form>
    </div>
    
    
      
   <!-- 待完善弹窗 -->
    <div class="window"  id="goprefectBoxId" hidden>
    	<div class="window_bg"></div>
    	<form class="windowForm">
    		<div class="close_btn" id="gocloseId"></div>
    		<div class="logoDiv"><span class="logo failure"></span></div>
    		<div class="textDiv failure_text">你的资料完善度还没达到审核要求，先完善哦~</div>
    		<div class="btnDiv"><button type="button" class="saveBtn" id="goPrefectBtn">去完善</button></div>
    	</form>
    </div>
    
    
     <!-- 待验证 -->
    <div class="window" style="display:none;">
    	<div class="window_bg"></div>
    	<form class="windowForm">
    		<div class="close_btn"></div>
    		<div class="logoDiv"><span class="logo failure"></span></div>
    		<div class="noteDiv"><span class="note_text">未通过认证的用户无法备案项目，如您尚未完善资料请尽快完善，如已完善，请耐心等待，我们会在1-3个工作日完成审核工作。</span></div>
    		<div class="btnDiv_verify"><button type="button" class="verifyBtn1">去完善</button><button type="button" class="verifyBtn2">忽略</button></div>
    	</form>
    </div>
    
                
                
              
</div>
