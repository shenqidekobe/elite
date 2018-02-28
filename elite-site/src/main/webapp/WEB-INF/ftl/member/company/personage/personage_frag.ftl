<div class="c-box">
    <div class="c-box-logo" id="anchor1">
    	<div class="c-box-logo-content">
    	<div class="mark-icon">项目方</div>
    	<#if obj.company.stared>
    	   <div class="badge-icon"></div>
    	</#if>
        <form id="personFrom" name="personFrom">
	        <#if obj.basic.photoId??>
	           <div class="box-logo-a" >
	           	   <img src="${obj.basic.memberPhoto.path}" id="headImg" title="支持jpg/jpeg/png/bim/bmp格式，文件小于2M">
	           </div>
	        <#else>
	           <div class="box-logo-a">
	           	   <img src="${_PATH}/res/images/default.jpg" id="headImg" title="支持jpg/jpeg/png/bim/bmp格式，文件小于2M">
	           </div>
	        </#if>
			<input type="file" name="file" id="headFile" style="display:none;">
			<#--上传头像弹窗-->
            <div class="headWindow" style="display:none;" save="Y"></div>
		</form>
        </div>
        
    </div>
    <div class="c-box-a"></div>
    <div class="c-box-b">
        <div class="box-b-content " id="editContent">
        <div class="abc_basic editContent_container" id="anchor2">
			<div class="box-txt"  style="display:block;" id="basicBox">
					 <div class="cy-r-total editCls" id="editBasic">
	                    <span class="pen"></span>
	                    <span class="editor">编辑</span>
		             </div>
		             <div class="box-logo-b clearfloat">
			            <span class="name">${obj.nickName}</span>
			            <#if obj.basic.sex??>
			                <span class="name-logo" style="background:url('<#if obj.basic.sex=='M'>${_PATH}/res/images/ceo/man.png<#else>${_PATH}/res/images/ceo/woman.png</#if>') no-repeat;"></span>
			            </#if>
			        </div>
			        <div class="box-logo-c">
			            <span class="c-title">${obj.basic.memberSign}</span>
			            <#if obj.basic.areaName??>
			               <span class="c-logo"></span><span class="c-city">${obj.basic.areaName}</span>
			            </#if>
			        </div>
			</div>
			<div class="editContent" style="display:block;" id="editBasicBox"></div>
       </div>
        
        
      <div class="b-content-a relative" id="anchor2">
            <div class="line-box clearfloat marginbottom50">
                <span class="line"></span>
                <span class="line-btn">创业属性</span>
                <span class="line"></span>
            </div>
            <div class="editContent_container">
        		<div class="box-txt relative" style="display:block;" id="infoBox">
    				<div class="business_title">
				        <#if obj.company.companyPosition??>
	                   		<span class="cy-l font-weight700">
							<#if obj.company.companyName??>
							   <#if obj.company.companyName?length gt 12>${obj.company.companyName?substring(0,12)}<#else>${obj.company.companyName}</#if>
							   <#else>${obj.company.companyPosition}
							</#if>
							</span>
	                        <span class="cy-c"><#if obj.company.companyScale??>${obj.company.companyScaleVal}<#else>${obj.company.teamNumVal}</#if></span>
                        </#if>
                        <div class="cy-r-com editCls" id="editCompany">
                            <span class="pen"></span>
                            <span class="editor">编辑</span>
                        </div>
                        <#if obj.company.companyName??><div class="director">${obj.company.companyPosition}</div></#if>
                    </div>
	                <div class="business_box">
	               		<div class="business_box_content"><#if obj.company.companyIntro??>${obj.company.companyIntro}<#else>${obj.company.teamIntro}</#if></div>
	                </div> 
                </div>
        		<div class="editContent"  style="display:block;" id="editInfoBox"></div>
            </div>
	  </div>
            
    <div class="b-content-b marginbottom40" id="anchor3">
            <div class="line-box clearfloat marginbottom50">
                <span class="line"></span>
                <span class="line-btn">征信信息</span>
                <span class="line"></span>
            </div>
            <div class="box-txt relative" style="display:block;" id="creditBox">
                <div class="cy-r editCls" id="editCredit" >
                    <span class="pen"></span>
                    <span class="editor">编辑</span>
                </div>
                <#if obj.credit.realName??>
                <div class="margintop50">
                    <span class="marginright20 font-weight700">身份证号</span>
                    <span class="marginright20">${obj.credit.formatCard}</span>
                    <span>${obj.credit.formatName}</span>
                </div>
                </#if>
                <div class="margintop27 photo_div">
                    <#if obj.credit.realName??>
                        <p class="font-weight700">证件上传</p>
                    </#if>
                    <ul class="margintop15 photo_div_ul">
                        <#if obj.credit.cardJust??>
                        <li>
                            <div class="idbox">
                               <img src="${obj.credit.cardJustPhoto.path}" style="width:160px;height:100px;">
                               <#--<span class="idlogo"></span><span class="idtxt"></span>-->
                            </div>
                        </li>
                        </#if>
                        <#if obj.credit.cardReverse??>
                        <li>
                            <div class="idbox">
                               <img src="${obj.credit.cardReversePhoto.path}" style="width:160px;height:100px;">
                               <#--<span class="idlogo"></span><span class="idtxt"></span>-->
                            </div>
                        </li>
                        </#if>
                        <#if obj.credit.businessCert??>
                        <li>
                            <div class="idbox">
                               <img src="${obj.credit.businessCertPhoto.path}" style="width:160px;height:100px;">
                               <#--<span class="idlogo"></span><span class="idtxt"></span>-->
                            </div>
                        </li>
                        </#if>
                        <#if obj.credit.jobCert??>
                            <li>
	                            <div class="idbox">
	                               <img src="${obj.credit.jobCertPhoto.path}" style="width:160px;height:100px;">
	                               <#--<span class="idlogo"></span><span class="idtxt"></span>-->
	                            </div>
                            </li>
                        </#if>
                    </ul>
                </div>
         </div>
         <div class="editContent"  style="display:block;" id="editCreditBox"></div>
      </div>
     </div>
    </div>
</div>