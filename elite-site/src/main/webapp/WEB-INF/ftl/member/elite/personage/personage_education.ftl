<div class="personage_education">
        <form class="form-group form-horizontal my-form" role="form" id="educationForm" style="padding-top:30px;">
			<#if education??><input type="hidden" name="id" value="${education.id}" /></#if>
			<input type="hidden" name="type" value="edu" />
            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label text_public">毕业院校</label>
                <div class="col-xs-7 col-md-7">
                    <input type="hidden" id="project_id" name="id">
                    <input class="form-control" type="text" id="organ" name="organ" value="${education.organ}" placeholder="毕业院校" maxlength="20" style="width:486px;">
                </div>
            </div>
            
			<div class="form-group">
                <label class="col-xs-2 col-md-2 control-label text_public">所学专业</label>
                <div class="col-xs-7 col-md-7">
                    <input type="hidden" id="project_id" name="id">
                    <input class="form-control" type="text" id="major" name="major" value="${education.major}" placeholder="所学专业" maxlength="20" style="width:486px;">
                    
                </div>
            </div>
			
            <div class="form-group degree_group">
                <label class="col-xs-2 col-md-2 control-label text_public">最高学历</label>
                <div class="col-xs-4 col-md-4 relative">
                	<input type="hidden" id="education" name="education" value="${education.education}" />
                    <div id="chooseEducation"><input class="form-control" type="text" id="educationval" name="educationval" value="${education.educationVal}" placeholder="最高学历" maxlength="11" readonly style="width:180px;"></div>
                	<div id="educationType" style="display:none" class="educationType">
	                	<ul>
	                    	<@dict type="education_type"></@dict>
	                    	<#list dictList as dt>
	                    		<li data="${dt.dictKey}">${dt.dictVal}</li>
	                    	</#list>
	                    </ul>
                    </div>
                </div>
                
                
                <label class="col-xs-2 col-md-2 control-label text_public" style="margin-left:-50px;">毕业时间</label>
                <div class="col-xs-2 col-md-2">
                    <input class="form-control" style="width:180px;" type="text" id="graduateTime" name="graduateTime" value="${education.graduateTime?string('yyyy-MM')}" readonly placeholder="毕业时间" maxlength="11">
                </div>
                
            </div>
            
          

            <div class="error-tip"></div>

            <div class="modal-opt">
                <button type="button" class="btn-ok btn_save" id="save">保存</button>
                <button type="button" class="btn-no btn_cancel" data-dismiss="modal" id="cancel" aria-label="Close">取消</button>
	            <#if education.id??>    
	                <button type="button" class="btn-no btn_delete" data-dismiss="modal" id="del" data="${education.id}" aria-label="Close">删除</button>
	            </#if>	
            </div>
        </form>
    </div>

