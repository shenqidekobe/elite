<@extend name="layout">
    <@block name="cs">
	    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
    </@block>
    <@block name="body">
    <#--正文开始-->
    <div class="y-bd" style="float:right;margin-top:300px;">
       <div id="container" style="min-width:400px;height:200px"></div>
       <br/>
       <a href="javascript:void(0);" id="pay">去支付</a>
       <br/><br/>
       <a href="javascript:void(0);" id="withdraw">提现申请</a>
       <br/><br/>
       <a href="javascript:void(0);" id="queryWithdraw">查询提现</a>
       
       <br/><br/><br/>
       <a href="javascript:void(0);" id="queryVisite">获取一个邀请码</a>
       <span id="invites" style="color:red;"></span>
       <br/><br/><br/>
       <select id="type">
          <option value="register">注册短信</option>
          <option value="forget">找回密码</option>
          <option value="update_phone">更换手机号</option>
          <option value="valid_bank">验证银行卡</option>
       </select>
       <input type="text" id="phone" placeholder="请输入您的手机号">
       <a href="javascript:void(0);" id="queryCode">查询</a>
       <span id="codes" style="color:red;"></span>
       <br/><br/><br/>
       <input type="text" id="name" placeholder="请输入您的姓名">
       <input type="text" id="idCard" placeholder="请输入您的身份证" maxlength="18">
       <br/>
       <input type="text" id="bankCode" placeholder="请输入您的银行卡号" maxlength="19">
       <input type="text" id="phone" placeholder="请输入您的银行卡预留手机号">
       <a href="javascript:void(0);" id="valiCard">验证身份证</a>
       <a href="javascript:void(0);" id="valiBank">验证银行卡</a>
       <br/><br/><br/>
       <input type="text" id="withdrawId" placeholder="请输入提现ID">
       <a href="javascript:void(0);" id="affirmWithdraw">确认提现</a>
       
       <br/>
       <img id="wxImg"/>
	</div>
	<@block name="footer"></@block>
    </@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/pay.js"></script>
        <script src="${_PATH}/res/script/myjs/pie.js"></script>
    </@block>
</@extend>