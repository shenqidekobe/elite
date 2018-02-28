<@extend name="partnerCompanyLayout">
    <@block name="cs">
       <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/channel/channelContainer.css"/>
       <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/paging/paging.css">      
       <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/kuCity.css"/>
       <link type="text/css" rel="stylesheet" href="${_PATH}/res/script/plugin/datepicker/datepicker3.css"/>
    </@block>
        <#--右边的操作区-->
        <@block name="rightContent">
            <div id="context">
            
            </div>
        </@block>
    <@block name="script">
         <script  src='${_PATH}/res/script/myjs/member/partnerCompany/champions.js'></script>
         <script  src='${_PATH}/res/script/myjs/member/partnerCompany/inviteRegister.js'></script>
         <script  src='${_PATH}/res/script/myjs/member/partnerCompany/channelManage.js'></script>
         <script  src='${_PATH}/res/script/myjs/member/partnerCompany/accountSecurity.js'></script>
         <script  src='${_PATH}/res/script/myjs/member/partnerCompany/revenue.js'></script>
     	 <script  src='${_PATH}/res/script/myjs/member/partnerCompany/main/index.js'></script>
    </@block>
</@extend>