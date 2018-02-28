<@extend name="eliteInfoLayout">
    <@block name="cs">
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/elite_info.css"/>
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/script/plugin/datepicker/datepicker3.css"/>
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/kuCity.css"/>
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/elite/personage_introduce.css"/>
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/elite/personage_current.css"/>
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/elite/personage_experience.css"/>
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/elite/personage_education.css"/>
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/elite/personage_credit.css"/>
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/elite/personage_basic.css"/>
    </@block>

    <#--右边的操作区-->
    <@block name="rightContent">
       <div id="dataList" class="personage_current"></div>
    </@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/elite/personage/index.js"></script>
        <script src="${_PATH}/res/script/plugin/ueditor/ueditor.config.js"></script>
        <script src="${_PATH}/res/script/plugin/ueditor/ueditor.all.min.js"></script>
        <script src="${_PATH}/res/script/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
    </@block>
</@extend>