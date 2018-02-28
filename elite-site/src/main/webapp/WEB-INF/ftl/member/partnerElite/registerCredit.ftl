<@extend name="layout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/partner_main.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/partnerElite/registerCredit.css"/>
    </@block>
    <@block name="body">
    <#--头部-->
         <@accounthead opt=""/>
		<#-- 上传认证信息 -->
<div id="registerCredit">		
 <div class="recommend-form-box" >
	<div class="recommend-form" id="personnelStep1" style="max-height:750px;overflow-y: auto;">
	    
	        <div class="processBox">
				      <div class="point1 point" style="border:1px solid #2cb7c9;color:#2cb7c9;">1</div>
				      <div class="point2 point" style="border:1px solid #2cb7c9;color:#2cb7c9;">2</div>
				      <div class="point3 point" style="border:1px solid #2cb7c9;color:#2cb7c9;">3</div>
				      <div class="line1 line"></div>
				      <div class="line2 line"></div>
				      <div class="text1 text" style="color:#2cb7c9;">基本信息</div>
				      <div class="text2 text" style="color:#2cb7c9;">行业信息</div>
				      <div class="text3 text" style="color:#2cb7c9;">征信信息</div>
			</div>  	
	    
        <form  id="personnel-form2" name="creditForm">
         <div class="group clearfloat">
                <label class="labelDiv"><span class="must">* </span>你的姓名</label>
                <div class="inputDiv">
                    <input class="form-control" type="text"  name="realName" id="realNameId" placeholder="请输入您的姓名" maxlength="6" style="width:300px;">
                </div>
            </div>
            
            <div class="group clearfloat">
                <label class="labelDiv"><span class="must">* </span>身份证号</label>
                <div class="inputDiv">
                    <input class="form-control" type="text"   id="idCardId" name="idCard" placeholder="请输入您的身份证号" maxlength="18" style="width:300px;">
                </div>
            </div>
            <div class="photoBox clearfloat">
            	<div class="font">
            		<span>上传身份证正面</span>
            		<img class="boxt" onclick="document.creditForm.cardJustFile.click()" id="cardJustImg" src="${path}/res/images/partner/ID_front.png">
	                    <input type="file" name="file" id="cardJustFile" style="display:none;">
                        <input type="hidden" name="cardJust" id="cardJustId" />
	                  </img>
            	</div>
            	<div class="back">
            		<span>上传身份证背面</span>
            		<img class="boxt" onclick="document.creditForm.cardReverseFile.click()" id="cardReverseImg" src="${path}/res/images/partner/ID_front.png">
	                    <input type="file" name="file" id="cardReverseFile" style="display:none;">
                        <input type="hidden" name="cardReverse" id="cardReverseId" />
	                  </img>
            	</div>
            </div>
            
            <div class="otherBox">
            	<h1>选择其他证件：</h1>
            	<ul class="otherUl">
            		<li>
            			<div class="select_text" id="jobCertShowBtn"><span>名片或工作证</span><img src="/res/images/partner/hide.png" alt=""></div>
            			<div class="select_img" id="otherImageId" hidden >
            			<img src="/res/images/partner/card.png" alt="" onclick="document.creditForm.jobCertFile.click()" id="jobCertImg">
            			</div>
            			<input type="file" name="file" id="jobCertFile" style="display:none;">
                        <input type="hidden" name="jobCert" id="jobCertId" />
            		</li>
            		<li>
            			<div class="select_text" id="businessCertShowBtn"><span>公司营业执照复印件</span><img src="/res/images/partner/hide.png" alt=""></div>
            			<div class="select_img" id="otherImageId" hidden>
            			<img src="/res/images/partner/license.png" alt="" onclick="document.creditForm.businessCertFile.click()" id="businessCertImg">
            			</div>
            		   <input type="file" name="file" id="businessCertFile" style="display:none;">
                             <input type="hidden" name="businessCert" id="businessCertId"/>
            		</li>
            		<li>
            			<div class="select_text" id="pmpCretShowBtn"><span>人力资源相关证书</span><img src="/res/images/partner/hide.png" alt=""></div>
            			<div class="select_img"  id="otherImageId" hidden >
            			<img src="/res/images/partner/human.png" alt="" onclick="document.creditForm.pmpCretFile.click()" id="pmpCretImg">
            			</div>
            			   <input type="file" name="file" id="pmpCretFile" style="display:none;">
                           <input type="hidden" name="pmpCret" id="pmpCretId" />
            		</li>
            	</ul>
            </div>
            
           
           
        </form>
        	
            <div class="nextStep">
            	<div class="error_div" style="display:none;">
	        		<span class="error_icon"></span>
	        		<span class="error_text error-tip"></soan>
	       		</div>
            	<a href="${_PATH}/partner/partnerElite/industry/register/view" class="back_btn">上一步</a>
                <button type="button" class="saveBtn" id="saveCreditBtn">创建完成</button>
                <a href="${_PATH}/member/index?rp=inviteRegister" class="skip_btn">先跳过，直接去备案项目</a>
            </div>
         </div>
        </div>
      </div>
</div>
	<#--行业信息结束-->
        <#--正文开始-->
    </@block>
     <@block name="script">
         <script  src="${_PATH}/res/script/myjs/member/partnerElite/registerCredit.js"></script>
    </@block>
</@extend>