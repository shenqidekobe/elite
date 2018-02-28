 <div class="personage_experience">
        <form class="form-group form-horizontal my-form" role="form" id="addProjectForm" >
            <div class="form-group company_div">
                <label class="control-label text_public">公司或项目名称</label>
                <div class="text_input">
                    <input type="hidden" id="project_id" name="id" value="${project.id}">
                    <input class="form-control" type="text" id="project" name="project" value="${project.project}"  placeholder="公司或项目名称" maxlength="20" style="width:486px;">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label text_public">担任的职位</label>
                <div class="text_input">
                    <input class="form-control" type="text" id="position" name="position" value="${project.position}" placeholder="担任的职位" maxlength="11" style="width:357px;">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label text_public"> 在职或项目时间</label>
                <div class="text_input">
                     <span class="date-select" id="startTimeSelect" style="position: relative; z-index: 999;">
                        <img src="${_PATH}/res/images/date_icon.png" class="date_icon_img">
                        <input type="text" class="form-control" id="startTime" name="startTime" <#if project.startTime??>value="${project.startTime?string('yyyy-MM')}"</#if>  readonly  placeholder="起始时间" style="background-color: white;"/>
                    </span>

                    <span style="float:left;color:white;line-height: 34px;margin:0 5px;"> ~ </span>
                     <span class="date-select" id="endTimeSelect" style="position: relative; z-index: 999;">
                        <img src="${_PATH}/res/images/date_icon.png" class="date_icon_img">
                        <input type="text" class="form-control" id="endTime" name="endTime" <#if project.endTime??>value="${project.endTime?string('yyyy-MM')}"</#if>  readonly  placeholder="截止时间" style="background-color: white;"/>
                    </span>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label text_public"> 项目描述</label>
                <div class="text_input">
                	<input type="hidden" value="" id="intro" name="intro"/>
                	<script id="editor" type="text/plain" style="width:500px;height:200px;">${project.intro}</script>
                    <#--<textarea style="width:486px;" class="form-control" rows="5" id="intro" name="intro" placeholder="简要填写项目核心功能和自己参与的模块，不超过500个字符" length="500">${project.intro}</textarea>-->
                </div>
            </div>
            <div class="modal-opt">
            	<div class="error-tip"></div>
                <button type="button" class="btn-ok btn_save" id="save">保存</button>
                <button type="button" class="btn-no btn_cancel" data-dismiss="modal" id="cancel" aria-label="Close">取消</button>
                <#if project.id??>
                	<button type="button" class="btn-no btn_delete" data-dismiss="modal" id="del" data="${project.id}" aria-label="Close">删除</button>
            	</#if>
            </div>
        </form>
    </div>

