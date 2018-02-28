<@extend name="layout">
    <@block name="cs">
     <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/partner_main.css"/>
     <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/kuCity.css"/>
     <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/partnerElite/registerIndustry.css"/>
    </@block>
    <@block name="body">
    <@accounthead opt=""/>
    <div id="registerIndustry">
	<div class="recommend-form-box">
         <div class="recommend-form" id="personnelStep2">
           
                <div class="processBox">
				      <div class="point1 point" style="border:1px solid #2cb7c9;color:#2cb7c9;">1</div>
				      <div class="point2 point" style="border:1px solid #2cb7c9;color:#2cb7c9;">2</div>
				      <div class="point3 point">3</div>
				      <div class="line1 line"></div>
				      <div class="line2 line"></div>
				      <div class="text1 text">基本信息</div>
				      <div class="text2 text" style="color:#2cb7c9;">行业信息</div>
				      <div class="text3 text">征信信息</div>
				 </div>  	
               
                <form role="form" id="personnel-form2">
                    <div class="group clearfloat">
                        <label class="labelDiv"><span class="must">*</span>从业年限</label>
                        <div class="inputDiv" style="width:160px;">
                                <input class="form-control" type="text" id="work"  style="width:160px;background-color:#fff;" readonly='readonly' >
                                <div class="birth-year" id="workYear" data="${elite.jobAge}">
                                 <#if elite.jobAge??>
                                        ${elite.jobAgeVal}
                                <#else>
                                                                                                                                          请选择  
                                </#if>
                                </div>
                                 <div class="triangleBtn" id="workyear-triangle">
                                        <div class="down"></div>
                                    </div>
                                    <ul class="year-select selectUl" id="workYear-select">
                                                 <#list 1..10 as i>
		                                          <li class="selectUl_li" data-type="workYear" data="${i}">${i}年</li>
		                                          </#list>
                                                  <li class="selectUl_li" data-type="workYear" data="${20}">10年以上</li>
                                    </ul>
                            </div>
                            <label class="labelDiv" style="margin-left:40px;"><span class="must">*</span>聚焦行业</label>
                            <div class="inputDiv" style="width:160px;">
                                <input class="form-control" type="text" id="dustryinputid"  style="width:160px;background-color: white;" readonly='readonly' >
                                <div class="birth-year" id="workProfession" name="attentionIndustry" data="${elite.attentionIndustry}">
                                  <#if elite.attentionIndustryVal??>
                                        ${elite.attentionIndustryVal}
                                <#else>
                                                                                                                                          请选择  
                                </#if>
                                </div>
                                
                                 <@dict type="choice_industry"></@dict>
                                    <ul class="year-select selectUl" style="height:auto;" id="industry-select">
                                     <#list dictList as dt>
                                        <li class="selectUl_li" data-type="workProfession" data="${dt.dictKey}">${dt.dictVal}</li>
                                    </#list>
                                    </ul>
                                    <div class="triangleBtn" id="workProfession-triangle">
                                        <div class="down"></div>
                                    </div>
                                </div>
                            </div>
                            
                            
                        
                    
                    <div class="group clearfloat">
                         <label class="two-labels"><span class="must">*</span>擅长职位</label>
                            <div class="inputDiv" style="width:160px;" >
                                <input class="form-control" type="text" id="birth"  style="width:160px;background-color:#fff;" readonly='readonly' >
                                <div class="birth-year" id="workTitle" name="goodatJob" data="${elite.goodatJob}"> 
                                <#if elite.goodatJobVal??>
                                        ${elite.goodatJobVal}
                                <#else>
                                                                                                                                          请选择  
                                </#if>
                                </div>
                            <div>
                                <@dict type="good_job"></@dict>
                                    <ul class="year-select selectUl" style="height:auto;" id="work-select">
                                      <#list dictList as dt>
                                        <li class="selectUl_li" data-type="workTitle" data="${dt.dictKey}">${dt.dictVal}</li>
                                           </#list>
                                    </ul>

                                    <div class="triangleBtn" id="work-triangle">
                                        <div class="down"></div>
                                    </div>
                                </div>
                            </div>

                       
                    </div>
                      
                    <div class="group clearfloat">
                        <label class="labelDiv">机构名称</label>
                        <div class="inputDiv" style="width:400px">
                            <input class="form-control" type="text" id="companyNameId" name="companyName" placeholder="请输入机构名称" maxlength="20" style="width:400px;cursor: default;" value="${elite.companyName}">
                        </div>
                    </div>
                    <div class="nextStep">
                    	 <div class="error_div" style="display:none;"><span class="error_icon"></span><span class="error-tip error_text"></span></div>
                    	 <a href="${_PATH}/partner/partnerElite/basic/register/view" class="lastStep">上一步</a>
                         <button type="button" class="next-step-btn" id="saveIndustryBtn">保存并继续下一步</button>
                         <a href="${_PATH}/member/index?rp=inviteRegister" class="skip_btn">先跳过，直接去备案人才</a>
                    </div>
                    
                </form>
               </div>
              </div>
             </div>
            </div>
           </div>
          
          </div>
    </@block>
     <@block name="script">
         <script  src="${_PATH}/res/script/myjs/member/partnerElite/registerIndustry.js"></script>
    </@block>
</@extend>