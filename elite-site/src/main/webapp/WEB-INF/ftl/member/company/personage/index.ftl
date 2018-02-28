<@extend name="companyInfoLayout">
    <@block name="cs">
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/company/companyContainer.css"/>
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/company_info.css"/>
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/ceo_main.css"/>
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/settlement/unfold.css"/>
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/script/plugin/datepicker/datepicker3.css"/>
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/kuCity.css"/>
    </@block>

    <#--右边的操作区-->
    <@block name="rightContent">
       <div id="dataList"></div>
    </@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/company/personage/index.js"></script>
        <script src="${_PATH}/res/script/plugin/ueditor/ueditor.config.js"></script>
        <script src="${_PATH}/res/script/plugin/ueditor/ueditor.all.min.js"></script>
        <script src="${_PATH}/res/script/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
    </@block>
</@extend>