    <div class="personage_introduce" style="padding-top:0px;">
			<div class="form-group">
                <#--<label class="col-xs-2 col-md-2 control-label text_public"> 自我描述</label>-->
                <div class="col-xs-10 col-md-10">
                	<script id="container" type="text/plain" style="width:640px;height:300px;">${obj.intro}</script>
                    <#--<textarea style="width:486px;"  class="form-control" rows="5" id="intro" name="intro" placeholder="不超过500个字符" length="500">${obj.intro}</textarea>-->
                </div>
            </div>
            
            
            
            <dl class="sample">
			    <dt>范例</dt>
			    <dd>1.独立负责过大型项目，非常成功的规划了项目 A 工作</dd>
			    <dd>2.B 项目，从开始到完成，极大地加强了我的逻辑思考能力</dd>
			    <dd>3.具备艺术家的灵感创作能力，公司公认的首席 PPT 制作人</dd>
			    <dd>4.  非常优秀的用户心理洞察力，配合丰富的创意及文案能力极大地提高了我的执行力</dd>
			</dl>
            
            

            <div class="modal-opt">
            	<div class="error-tip" style="display:none;">
			        <img class="error_icon" src="/res/images/elite/error_icon.png" alt="">
			        <span id="tipError" class="tipError">错误提示</span>
			    </div>
                <button type="button" class="btn-ok" id="save">保存</button>
                <button type="button" class="btn-no" data-dismiss="modal" id="cancel" aria-label="Close">取消</button>
            </div>
    </div>

