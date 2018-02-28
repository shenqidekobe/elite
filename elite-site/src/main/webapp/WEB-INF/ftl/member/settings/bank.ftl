<@extend name="layout">
    <@block name="cs">
	    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
	    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/person/personContainer.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
	<div class="y-bd">
	    <div id="personSetting">
	        <input type="hidden" id="isCard" value="${credit.isCard}">
	        <div class="set_left">
	            <div class="nav_title">
	               <span style="cursor:pointer;" id="crumbs_main">个人主页</span>&gt;
	               <span class="selectText" id="selectText">帐号设置</span>
	            </div>
	            <div class="navBox">
	                <ul id="navUl">
	                    <li data="account" id="accountLi"><span class="liIcon account_select"></span><span class="liText active_color">账号设置</span></li>
	                    <li data="card" id="cardLi"><span class="liIcon card_noselect" style="top:3px;"></span><span class="liText">银行卡账户绑定</span></li>
	                </ul>
	                <div class="activeLine" id="activeLine"></div>
	            </div>
	        </div>
	        <div class="set_right">
	            <div class="passWord" id="passWord">
	                <h2>密码修改</h2>
	                <form id="passForm">
	                    <div class="labelGroup">
	                        <label>当前密码</label>
	                        <input type="password" placeholder="请输入原始密码" id="oldPass">
	                    </div>
	                    <div class="labelGroup">
	                        <label>新密码</label>
	                        <input type="password" placeholder="请输入新密码" id="newPass">
	                    </div>
	                    <div class="labelGroup">
	                        <label>确认密码</label>
	                        <input type="password" placeholder="请再次确认密码" id="newPass1">
	                    </div>
	                    <div class="btnDiv">
	                        <div class="error_div" id="passError"><span class="error_icon"></span><span class="error_text"></span></div>
	                        <button type="button" class="affirmBtn" id="editPass">确认修改</button>
	                    </div>
	                </form>
	            </div>
	            <div class="bankCard"  id="bankCardBox" style="display: none">
	                <h2>银行卡绑定<span>（用于账户提现）</span></h2>
	                <form>
	                    <div id="bankList"></div>
	                    <button type="button" class="addCardBtn" id="addCardBtn">
	                        <span class="addIcon"></span>
	                        <span class="addText">添加银行卡</span>
	                    </button>
	                </form>
	            </div>
	        </div>
	
	
	
	        <!--添加银行卡弹窗-->
	        <div class="window" style="display: none" id="cardWindow" >
	            <form class="cardForm" id="cardForm">
	                <div class="form_bg"></div>
	                <div class="closeBtn" id="closeBtnCard"></div>
	                <h1>添加银行卡</h1>
	                <div class="labelGroup clearfloat">
	                    <label>姓名</label>
	                    <input type="text" placeholder="请输入你的真实姓名" id="name" readonly value="${credit.realName}">
	                </div>
	                <div class="labelGroup clearfloat">
	                    <label>身份证</label>
	                    <input type="text" placeholder="请输入你的身份证号码" maxlength="18" id="idCard" readonly value="${credit.idCard}">
	                </div>
	                <div class="labelGroup clearfloat">
	                    <label>储蓄卡卡号</label>
	                    <input type="text" placeholder="请输入你的卡号" id="bankCard" maxlength="25">
	                </div>
	                <div class="labelGroup clearfloat">
	                    <label>手机号</label>
	                    <input type="text" placeholder="请输入银行预留的手机号" id="bankPhone" maxlength="11">
	                </div>
	                <div class="labelGroup clearfloat">
	                    <label>验证码</label>
	                    <input type="text" placeholder="手机验证码" style="width: 256px" id="verifyCode">
	                    <button type="button" class="obtainBtn" id="sendBank">获取</button>
	                </div>
	                <div class="btnBox">
	                    <div class="error_div" id="bankError"><span class="error_icon"></span><span class="error_text"></span></div>
	                    <button type="button" class="validationBtn" id="saveBank">验证并添加</button>
	                </div>
	            </form>
	        </div>
	
	    </div>
	</div>

    </@block>
    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/settings/bank.js"></script>
    </@block>
</@extend>