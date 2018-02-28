<@extend name="layout">
    <@block name="cs">
    <#--<style type="text/css">-->


        <#--/*正文样式*/-->
        <#--.content{-->
            <#--width:1000px;-->
            <#--min-height:500px;-->
            <#--margin:0 auto;-->
        <#--}-->

        <#--.location-nav{-->
            <#--margin:20px 0;-->
        <#--}-->
        <#--.left-column{-->
            <#--float:left;-->
            <#--width:240px;-->
            <#--border:1px solid #E6E6E6;-->
            <#--border-radius: 4px;-->
            <#--text-align: center;-->
            <#--background-color: #FAFAFA;-->
        <#--}-->

        <#--.column-head{-->
            <#--width: 80px;-->
            <#--height: 80px;-->
            <#--background:url("/res/images/test.jpg") no-repeat;-->
            <#--background-size: 80px 80px;-->
            <#--border: 1px solid #979797;-->
            <#---moz-border-radius: 50px;-->
            <#---webkit-border-radius: 50px;-->
            <#--border-radius: 50px;-->
        <#--}-->

        <#--.circle-box{-->
            <#--padding-left:80px;-->
            <#--padding-top:20px;-->
        <#--}-->

        <#--.username{-->
            <#--margin-top:14px;-->
        <#--}-->

        <#--.split-line{-->
            <#--width:100%;-->
            <#--border-bottom: 1px solid #E6E6E6;-->
            <#--margin:25px 0;-->
        <#--}-->

        <#--.left-menu li{-->
            <#--line-height: 40px;-->
            <#--cursor: pointer;-->
        <#--}-->

        <#--.left-menu li>*{-->
            <#--display: inline-block;-->
        <#--}-->

        <#--.menu-icon{-->
            <#--display: block;-->
            <#--width:20px;-->
            <#--height:20px;-->
            <#--/*background-color: red;*/-->
            <#--vertical-align: middle;-->
            <#--margin-right:15px;-->
        <#--}-->

        <#--.active-menu{-->
            <#--color:#2CB7C9;-->
        <#--}-->

        <#--.right-opt{-->
            <#--float:right;-->
            <#--width:730px;-->
        <#--}-->

    <#--</style>-->
    </@block>
    <@block name="body">
    <#--头部-->
        <@accounthead opt=""/>

    <#--正文开始-->
    <div class="content" id="personRecommend">
        <div class="location-nav"><span style="font-size: 18px;">人才推荐方页</span>&nbsp;&gt;&nbsp;<span style="color:#2CB7C9;">精英管理</span></div>
        <div class="content-box">

        <#--左边的资料区-->
            <div class="left-column">
                <div class="circle-box">
                    <div class="column-head"></div>
                </div>

                <div class="username">
                    <span style="font-size: 20px;">Olivia</span><span style="font-size: 14px;margin-left:15px;">上海</span>
                </div>

                <div class="split-line"></div>

                <div>
                    <ul class="left-menu">
                        <li class="active-menu"><span class="menu-icon" ></span>邀请注册</li>
                        <li><span class="menu-icon"></span>精英管理</li>
                        <li><span class="menu-icon"></span>收益中心</li>
                        <li><span class="menu-icon"></span>账户安全</li>
                    </ul>
                </div>
            </div>

        <#--右边的操作区-->

            <div class="right-opt">
                <@block name="rightContent">

                </@block>
            </div>

        </div>
    </div>


    </@block>
    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>