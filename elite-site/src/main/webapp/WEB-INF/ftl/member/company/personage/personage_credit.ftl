<div id="companyPersonageCredit">
<form class="form-group form-horizontal form-person paddingtop60" role="form" id="companyFrom" name="companyFrom" style="padding-left:0;">
    <div class="form-group">
        <label class="col-xs-2 col-md-2 control-label">
                <div class="title-input">
                    	真实姓名 
                </div>    
        </label>
        <div class="col-xs-9 col-md-9">
            <input class="form-control input-unified width300" type="text" id="realName" name="realName" <#if obj.isCard>readonly</#if> placeholder="与身份证上姓名保持一致" maxlength="10" value="${obj.realName}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-xs-2 col-md-2 control-label">
                <div class="title-input">
                    	身份证号 
                    <input type="hidden" name="id" value="${obj.id}">
	                <input type="hidden" name="cardJust" id="cardJust" value="${obj.cardJust}">
	                <input type="hidden" name="cardReverse" id="cardReverse" value="${obj.cardReverse}">
	                <input type="hidden" name="businessCert" id="businessCert" value="${obj.businessCert}">
	                <input type="hidden" name="jobCert" id="jobCert" value="${obj.jobCert}">
	                <input type="hidden" name="visitingCert" id="visitingCert" value="${obj.visitingCert}">
	            </div>    
        </label>
        <div class="col-xs-9 col-md-9">
            <input class="form-control input-unified width300" type="tel" id="idCard" name="idCard" <#if obj.isCard>readonly</#if> placeholder="18位二代身份证号" maxlength="20" value="${obj.idCard}">
        </div>
    </div>

    <div class="form-group" style="margin-bottom: 10px;">
        <label class="col-xs-3 col-md-3 control-label">
                 <div class="title-input">
                                                                             上传认证资料
                </div>
        </label>
    </div>



    <div class="company_photo">
        <div class="photo-row">
            <div class="photo-box">
                <div class="photo" onclick="document.companyFrom.cardJustFile.click()">
                    <#if obj.cardJust??>
                        <img src="${obj.cardJustPhoto.path}" class="photo-add" id="cardJustImg" style="width:160px;height:100px">
                    <#else>
                        <img src="${_PATH}/res/images/elite/ID_front.png" class="photo-add" id="cardJustImg" style="width:160px;height:100px">
                    </#if>
                    <input type="file" name="file" id="cardJustFile" style="display:none;">
                </div>
                <p>身份证号正面</p>
            </div>
            <div class="photo-box">
                <div class="photo" onclick="document.companyFrom.cardReverseFile.click()">
                   <#if obj.cardReverse??>
                       <img src="${obj.cardReversePhoto.path}" class="photo-add" id="cardReverseImg" style="width:160px;height:100px">
                   <#else>
                       <img src="${_PATH}/res/images/elite/ID_back.png" class="photo-add" id="cardReverseImg" style="width:160px;height:100px">
                   </#if>
                   <input type="file" name="file" id="cardReverseFile" style="display:none;">
                </div>
                <p>身份证号反面</p>
            </div>
            
            
            <div class="photo-box">
                <div class="photo" onclick="document.companyFrom.businessCertFile.click()">
                   <#if obj.businessCert??>
                       <img src="${obj.businessCertPhoto.path}" class="photo-add" id="businessCertImg" style="width:160px;height:100px">
                   <#else>
                       <img src="${_PATH}/res/images/elite/ID_certificate.png" class="photo-add" id="businessCertImg" style="width:160px;height:100px">
                   </#if>
                   <input type="file" name="file" id="businessCertFile" style="display:none;">
                </div>
                <p>营业执照</p>
            </div>

            <div class="photo-box">
                <div class="photo" onclick="document.companyFrom.jobCertFile.click()">
                   <#if obj.jobCert??>
                       <img src="${obj.jobCertPhoto.path}" class="photo-add" id="jobCertImg" style="width:160px;height:100px">
                   <#else>
                       <img src="${_PATH}/res/images/elite/ID_card.png" class="photo-add" id="jobCertImg" style="width:160px;height:100px">
                   </#if>
                   <input type="file" name="file" id="jobCertFile" style="display:none;">
                </div>
                <p>工作证或名片</p>
            </div>
            
    </div>
    
    <div class="btnBox" >
    	<div class="error_div" style="display:none;"><span class="error_icon"></span><span class="error_text"></span></div>
	    <button type="button" class="save">保存</button>
	    <button type="button" class="cancel">取消</button>
    </div>
    
</form>
</div>