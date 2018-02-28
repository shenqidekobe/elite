<@extend name="layout">
    <@block name="cs">
    <#--<style type="text/css">-->

        <#--.wrap-head{-->
            <#--width:100%;-->
            <#--height:120px;-->
            
            <#--text-align: center;-->
        <#--}-->

        <#--.wrap-head img{-->
            <#--height:50px;-->
            <#--width:auto;-->
            <#--margin-top:20px;-->
        <#--}-->
        <#--.login-register{-->
            <#--color:white;-->
            <#--margin-top:10px;-->
        <#--}-->

        <#--.login-register a{-->
            <#--color:white;-->
        <#--}-->

        <#--.bottom-icons{-->
            <#--width:100%;-->
            <#--height:200px;-->
            <#--background-color: #FAFAFA;-->
        <#--}-->

        <#--.icons-box{-->
            <#--width:1000px;-->
            <#--margin:0 auto;-->
            <#--padding-top:36px;-->
        <#--}-->

        <#--.icons-box >*{-->
            <#--float: left;-->
        <#--}-->

        <#--.icon{-->
            <#--width:33%;-->
            <#--text-align: center;-->
            <#--letter-spacing: 1px;-->
            <#--position: relative;-->
        <#--}-->

        <#--.icon img{-->
            <#--width:100px;-->
            <#--height:100px;-->
        <#--}-->

        <#--.icon p{-->
            <#--margin-top:16px;-->
        <#--}-->

    <#--</style>-->
    </@block>
    <@block name="body">
    <#--头部-->
        <#--<div id="completeHead">-->
            <#--<div class="wrap-head">-->
                <#--<img src="/res/images/logo.png">-->
                <#--<div class="login-register">-->
                    <#--<span>-->
                        <#--<a href="#" target="_blank" style="color:#FEA600;">注册</a>-->
                    <#--</span>-->
                    <#--<span style="margin:0 5px;">|</span>-->
                    <#--<span>-->
                        <#--<a href="#" target="_blank">登录</a>-->
                    <#--</span>-->
                <#--</div>-->

            <#--</div>-->
        <#--</div>-->

        <@mainhead clazz="" picpath="" pagetype="" user=""/>


        <#--正文开始-->
        <@block name="content"></@block>

        <#--底部图标-->
        <div id="completeBottom">
            <div class="bottom-icons">
                <div class="icons-box">
                    <div class="icon">
                        <img src="${_PATH}/res/images/bottom_icon1.png">
                        <p>信任与安全</p>
                    </div>

                    <div class="icon">
                        <img src="${_PATH}/res/images/bottom_icon2.png">
                        <p>1000位云英汇明星开发成员</p>
                    </div>

                    <div class="icon">
                        <img src="${_PATH}/res/images/bottom_icon3.png">
                        <p>完美交付</p>
                    </div>
                </div>

            </div>
        </div>


    </@block>
    <@block name="script">
    </@block>
</@extend>