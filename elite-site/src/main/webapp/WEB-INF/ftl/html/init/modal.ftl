<#macro optmodal title ask>
    <#local title=title/>
    <#local ask=ask/>

<#--操作模态框-->
<div class="modal fade" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true"  id="optDialog" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="false">
                    &times;
                </button>
                <h4 class="modal-title">${title}</h4>
            </div>
            <div class="modal-body" style="word-break: break-all;">
            ${ask}
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="ok-btn">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>
<#global optmodal=optmodal/>

<#macro tipmodal tip>
    <#local tip=tip/>

<#--提示模态框-->
<div class="container">
    <div class="row">
        <div class="modal fade" id="tipDialog" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" style="top:200px;height:150px;">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <br>
                    <div class="modal-body" style="text-align: center;">
                        <div>
                            <img src="/res/images/tick_icon.png" style="width:27px;height:27px;margin-top:-5px;"/>
                            <label style="color:#ff624f;">${tip}</label>
                        </div>
                        <div class="col-md-offset-4" style='margin-top:20px;'>
                            <button type="button" class="btn btn-default col-md-6" data-dismiss="modal" style="background-color: #ff624f;color:white;">确&nbsp;定</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>
<#global tipmodal=tipmodal/>