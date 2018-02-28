 <form class="form-group form-horizontal form-person personage_credit" role="form" id="companyFrom" name="companyFrom">
    <div>
    
           <div class="form-group">
                <label class="text_public">
                                                                       真实姓名 <span class="secret-circle">密</span>
                </label>
                <div class="text_input">
                    <input class="form-control" type="text" id="realName" name="realName" placeholder="与身份证上姓名保持一致"  <#if obj.isCard>readonly</#if> maxlength="10" value="${obj.realName}">
                </div>
           </div>
        
            <div class="form-group">
                <label class="text_public">
                                                                      身份证号 <span class="secret-circle">密</span>
                    <input type="hidden" name="id" value="${obj.id}">
	                <input type="hidden" name="cardJust" id="cardJust" value="${obj.cardJustPhoto.id}">
	                <input type="hidden" name="cardReverse" id="cardReverse" value="${obj.cardReversePhoto.id}">
	                <input type="hidden" name="businessCert" id="businessCert" value="${obj.businessCert.id}">
	                <input type="hidden" name="jobCert" id="jobCert">
	                <input type="hidden" name="visitingCert" id="visitingCert">
                </label>
                <div class="text_input">
                    <input class="form-control" type="text" id="idCard" name="idCard" value="${obj.idCard}" <#if obj.isCard>readonly</#if> placeholder="18位二代身份证号" maxlength="18" style="width:300px;">
                </div>
            </div>

            <div class="form-group" style="margin-bottom: 10px;">
                <label class="text_public">上传认证资料<span class="secret-circle" style="right:-10px;">密</span></label>
            </div>

          

            <div class="photos">
                <div class="photo-row">
                   <div class="photo-box">
                        <div class="photo" onclick="document.companyFrom.cardJustFile.click()">
                        <#if obj.cardJust??>
	                        <img src="${obj.cardJustPhoto.path}" class="photo-add" id="cardJustImg" style="width:160px;height:100px">
	                    <#else>
	                        <img src="${_PATH}/res/images/elite/ID_front.png" class="photo-add" id="cardJustImg">
	                    </#if>
                        <input type="file" name="file" id="cardJustFile" style="display:none;">
                        </div>
                        <p>身份证正面</p>
                    </div>
                    <div class="photo-box">
                        <div class="photo" onclick="document.companyFrom.cardReverseFile.click()">
                        <#if obj.cardReverse??>
	                       <img src="${obj.cardReversePhoto.path}" class="photo-add" id="cardReverseImg" style="width:160px;height:100px">
	                    <#else>
	                        <img src="${_PATH}/res/images/elite/ID_back.png" class="photo-add" id="cardReverseImg">
	                    </#if>
                        <input type="file" name="file" id="cardReverseFile" style="display:none;">
                        </div>
                        <p>身份证反面</p>
                    </div>
                    
                    <div class="photo-box">
                        <div class="photo" onclick="document.companyFrom.businessCertFile.click()">
                        <#if obj.businessCert??>
	                       <img src="${obj.businessCertPhoto.path}" class="photo-add" id="businessCertImg" style="width:160px;height:100px">
	                    <#else>
	                       <img src="${_PATH}/res/images/elite/ID_certificate.png" class="photo-add" id="businessCertImg">
	                    </#if>
                        <input type="file" name="file" id="businessCertFile" style="display:none;">
                        </div>
                        <p>其他证书</p>
                    </div>
                    
                </div>

                 <#--<div class="photo-box">
                        <p>工作证正面</p>
                        <div class="photo" onclick="document.companyFrom.jobCertFile.click()">
                           <img src="${obj.credit.businessCertPhoto.path/res/images/elite/certificate.png}" class="photo-add" id="jobCertImg">
                           <input type="file" name="file" id="jobCertFile" style="display:none;">
                        </div>
                    </div>

                    <div class="photo-box">
                        <p>名片正面</p>
                        <div class="photo" onclick="document.companyFrom.visitingCertFile.click()">
                           <img src="${_PATH}/res/images/target_icon.png" class="photo-add" id="visitingCertImg">
                           <input type="file" name="file" id="visitingCertFile" style="display:none;">
                        </div>
                    </div>-->
                
            </div>

            <#--<div class="form-group">
                <label class="col-xs-2 col-md-2 control-label">
                    <span class="required">* </span> 支付宝账号 <span class="secret-circle">密</span>
                </label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="alipayAccount" name="alipayAccount" placeholder="只调用支付宝的信用积分，不影响账户" maxlength="18">
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label">
                    <span class="required">* </span> 银行卡绑定 <span class="secret-circle">密</span>
                </label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="bankCard" name="bankCard" placeholder="用于个人征信及项目账款的结算" maxlength="24">
                </div>
            </div>-->
            <div class="error-tip" style="color:red"></div>
            <div class="form-opt">
                <button type="button" class="btn-ok" id="saveBtn">保存</button>
                <button type="button" class="btn-no" id="cancel">取消</button>
            </div>

    </div>
    </div>
 </form>
 