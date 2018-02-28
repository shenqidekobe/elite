<@extend name="layout">
    <@block name="cs">
    </@block>
    <@block name="body">
         <@accounthead opt=""/>

        <#--正文开始-->
        <@block name="content"></@block>

        <#--底部图标-->
        <div id="completeBottom">
            <div class="bottom-icons">
                <div class="icons-box">
                    <div class="icon">
                        <img src="${_PATH}/res/images/index/elite_a.png">
                        <p>项目管家</p>
                    </div>

                    <div class="icon">
                        <img src="${_PATH}/res/images/index/elite_b.png">
                        <p>靠谱精英</p>
                    </div>

                    <div class="icon">
                        <img src="${_PATH}/res/images/index/elite_c.png">
                        <p>信用保障</p>
                    </div>
                </div>

            </div>
        </div>


    </@block>
    <@block name="script">
    </@block>
</@extend>