<#macro mainhead clazz picpath pagetype user>
    <#local clazz=clazz/>
    <#local picpath=picpath/>
    <#local pagetype=pagetype/>
    <#local user=user/>
    <#if clazz=='index'>

    </#if>

    <div class="wrap-head" id="mainHead">
        <#--<img src="${_PATH}/res/images/banner_office.png" class="head-banner">-->
            <input type="hidden" value="${picpath}" id="picpath">
        <#if picpath?has_content>
            <img src="${picpath}" class="head-banner">
        </#if>
        <div class="head">
            <div class="theme-ban">
                <div class="theme">
                    <a href="${_PATH}/">
                        <img src="${_PATH}/res/images/logo.png" class="logo">
                    </a>
                    <#--<div class="title">-->
                        <#--<p>-->
                            <#--<span class="keyword">一次</span>推荐-->
                        <#--</p>-->
                        <#--<p>就能获得<span class="keyword">一劳永逸</span>的持续回报</p>-->
                    <#--</div>-->
                    <#if clazz=='partner' && pagetype=='form' && user=='person'>
                        <div class="recommend-form" id="personnelStep1">
                            <div class="form-box">
                                <div class="title">人才推荐方注册-基本信息</div>
                                <div class="hr-line"></div>

                                <form class="row form-group form-horizontal" role="form" id="personnel-form1">
                                    <input type="hidden" id="title" value="person">
                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label">您的身份</label>
                                        <div class="col-xs-7 col-md-7">
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-default" data-title="company">猎头公司</button>
                                                <button type="button" class="btn btn-default active-title" data-title="person">个人猎头</button>
                                                <button type="button" class="btn btn-default" data-title="hr">HR</button>
                                                <button type="button" class="btn btn-default" data-title="other">其他</button>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 姓名</label>
                                        <div class="col-xs-7 col-md-7">
                                            <input class="form-control" type="text" id="name" name="realName" placeholder="请输入姓名" maxlength="20" style="width:264px;">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 手机号</label>
                                        <div class="col-xs-7 col-md-7">
                                            <input class="form-control" type="text" id="phone" name="phone" placeholder="请输入11位手机号" maxlength="11" style="width:264px;">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 手机验证码</label>
                                        <div class="col-xs-7 col-md-7">
                                            <input class="form-control" type="text" id="verifycode" name="verifycode" placeholder="手机验证码" maxlength="6" style="width:134px;float:left;">
                                            <input type="button" class="send-msg-btn" id="send-msg-btn" value="获取验证码">
                                        </div>
                                    </div>

                                    <div class="hr-line2"></div>

                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label">出生年月</label>
                                        <div class="col-xs-7 col-md-7">
                                            <div class="birth-box">
                                                <input class="form-control" type="text" id="birth"  style="width:180px;background-color: white;" disabled>
                                                <div class="birth-year" id="birthYear">1988</div>
                                                <div>
                                                    <ul class="year-select">
                                                        <#--<li data-type="year">1990</li>-->
                                                        <#--<li data-type="year">1991</li>-->
                                                        <#--<li data-type="year">1992</li>-->
                                                        <#--<li data-type="year">1993</li>-->
                                                         <#list 1998..1950 as i>
                                                             <li data-type="year" style="padding-right:26px;">${i?c}</li>
                                                         </#list>
                                                    </ul>

                                                    <div class="triangle">
                                                        <div class="trigger"></div>
                                                    </div>
                                                </div>

                                                <div class="birth-year" id="birthMonth" style="left:98px;">01</div>
                                                <div>
                                                    <ul class="year-select" style="left:80px;">
                                                        <li data-type="month">01</li>
                                                        <li data-type="month">02</li>
                                                        <li data-type="month">03</li>
                                                        <li data-type="month">04</li>
                                                        <li data-type="month">05</li>
                                                        <li data-type="month">06</li>
                                                        <li data-type="month">07</li>
                                                        <li data-type="month">08</li>
                                                        <li data-type="month">09</li>
                                                        <li data-type="month">10</li>
                                                        <li data-type="month">11</li>
                                                        <li data-type="month">12</li>
                                                    </ul>

                                                    <div class="triangle" style="left:120px;">
                                                        <div class="trigger"></div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 邮箱</label>
                                        <div class="col-xs-7 col-md-7">
                                            <input class="form-control" type="text" id="email" name="email" placeholder="请输入您的邮箱账号" maxlength="50" style="width:264px;">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-9 col-md-9 col-md-offset-3" style="padding-left:0;">
                                            <label class="checkbox-inline" style="padding-left:0;">
                                                <input type="checkbox" id="inlineCheckbox" value="1" checked>
                                                <span class="agree">我已阅读并同意《猎头注册协议》和《猎头注册承诺书》</span>
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-9 col-md-9 col-md-offset-3" style="padding-left:0;">
                                           <div class="error-tip">请填写姓名</div>
                                        </div>
                                    </div>

                                    <div class="next-step">
                                        <button type="button" class="next-step-btn" id="nextStepBtn1">下一步</button>
                                    </div>
                                </form>
                            </div>

                        </div>

                        <div class="recommend-form" id="personnelStep2" style="width:564px;display: none;">
                            <div class="form-box">
                                <div class="title">人才推荐方注册-行业信息</div>
                                <div class="hr-line"></div>

                                <form class="row form-group form-horizontal" role="form" id="personnel-form2">
                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label">从业年限</label>
                                        <div class="col-xs-7 col-md-7">
                                            <div class="birth-box">
                                                <input class="form-control" type="text" id="birth"  style="width:110px;background-color: white;" disabled>
                                                <div class="birth-year" id="workYear">6&nbsp;年</div>
                                                <div>
                                                    <ul class="year-select">
                                                        <#list 1..18 as i>
                                                            <li data-type="workYear" style="padding-right:26px;">${i?c}&nbsp;年</li>
                                                        </#list>
                                                    </ul>

                                                    <div class="triangle" style="left:70px;">
                                                        <div class="trigger"></div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="two-column-tr">
                                            <label class="two-labels">聚焦行业</label>
                                            <div class="birth-box" style="margin-left:24px;">
                                                <input class="form-control" type="text" id="birth"  style="width:110px;background-color: white;" disabled>
                                                <div class="birth-year" id="workProfession">互联网</div>
                                                <div>
                                                    <ul class="year-select" style="height:auto;">
                                                        <li data-type="workProfession" style="padding:0 22px;">人工智能</li>
                                                        <li data-type="workProfession" style="padding:0 22px;">工业制造</li>
                                                        <li data-type="workProfession" style="padding:0 22px;">教育艺术</li>
                                                    </ul>

                                                    <div class="triangle" style="left:82px;">
                                                        <div class="trigger"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <label class="two-labels" style="margin-left:20px;">擅长职位</label>
                                            <div class="birth-box" style="margin-left:24px;">
                                                <input class="form-control" type="text" id="birth"  style="width:142px;background-color: white;" disabled>
                                                <div class="birth-year" id="workTitle">开发工程师</div>
                                                <div>
                                                    <ul class="year-select" style="height:auto;">
                                                        <li data-type="workTitle" style="padding:0 28px;">开发工程师</li>
                                                        <li data-type="workTitle" style="padding:0 28px;">UI设计师</li>
                                                        <li data-type="workTitle" style="padding:0 28px;">产品设计师</li>
                                                    </ul>

                                                    <div class="triangle" style="left:106px;">
                                                        <div class="trigger"></div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label">所在城市</label>
                                        <div class="col-xs-7 col-md-7">
                                            <div class="birth-box">
                                                <input class="form-control" type="text"  style="width:300px;background-color: white;" disabled>
                                                <img src="${_PATH}/res/images/location_icon.png" class="location">
                                                <div class="birth-year" id="province" style="left:50px;">上海</div>
                                                <div>
                                                    <ul class="year-select" style="left:40px;">
                                                        <li data-type="province">上海</li>
                                                        <li data-type="province">北京</li>
                                                        <li data-type="province">深圳</li>
                                                    </ul>

                                                    <div class="triangle" style="left:104px;">
                                                        <div class="trigger"></div>
                                                    </div>
                                                </div>

                                                <div class="birth-year" id="city" style="left:160px;">上海市</div>
                                                <div>
                                                    <ul class="year-select" style="left:160px;">
                                                        <li data-type="city" style="padding:0 28px;">上海市</li>
                                                        <li data-type="city" style="padding:0 28px;">上海市</li>
                                                        <li data-type="city" style="padding:0 28px;">上海市</li>
                                                    </ul>

                                                    <div class="triangle" style="left:222px;">
                                                        <div class="trigger"></div>
                                                    </div>
                                                </div>

                                            </div>

                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label">公司名称</label>
                                        <div class="col-xs-7 col-md-7">
                                            <input class="form-control" type="text" id="companyname" name="companyname" placeholder="请填写所在公司名称" maxlength="50" style="width:264px;">
                                        </div>
                                    </div>

                                    <div class="next-step" style="margin-top:40px;">
                                        <button type="button" class="next-step-btn" id="nextStepBtn2">下一步</button>
                                    </div>
                                </form>
                            </div>

                        </div>

                        <div class="recommend-form" id="personnelStep3" style="width:564px;display: none;">
                            <div class="form-box">
                                <div class="title">人才推荐方注册-上传认证信息</div>
                                <div class="hr-line"></div>

                                <#--隐藏表单域,用于提交图片-->
                                <form id="picForm" target="picForm" action="/account/saveCompanyPic" method="post" enctype="multipart/form-data">
                                    <input type="file" id="idPic" name="file" accept="image/x-png,image/jpeg,image/x-ms-bmp, image/bmp">
                                </form>

                                <form class="row form-group form-horizontal" role="form" id="personnel-form3">
                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label">身份证号</label>
                                        <div class="col-xs-8 col-md-8">
                                            <input class="form-control" type="text" id="idnumber" name="idnumber" placeholder="请输入您的身份证号" maxlength="19">

                                            <div>
                                                <div class="ID-pic-add">
                                                   <span class="glyphicon glyphicon-plus"></span>
                                                        &nbsp;<span>上传身份证正面</span>
                                                </div>

                                                <img src="${_PATH}/res/images/bulb.jpg" class="ID-pic">

                                                <#--<button type="button" class="reset-btn">修改</button>-->
                                            </div>

                                        </div>
                                    </div>

                                    <div class="hr-line2"></div>

                                    <div class="form-group">
                                        <div class="ID-pic-add">
                                            <span class="glyphicon glyphicon-plus"></span>
                                            &nbsp;<span>上传所在公司名片或工作证</span>
                                        </div>

                                        <div class="ID-pic-add" style="margin-left:20px;">
                                            <span class="glyphicon glyphicon-plus"></span>
                                            &nbsp;<span>上传公司营业执照复印件</span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="ID-pic-add">
                                            <span class="glyphicon glyphicon-plus"></span>
                                            &nbsp;<span>上传我的简历<span style="font-size: 12px;">(含项目和工作描述)</span></span>
                                        </div>

                                        <div class="ID-pic-add" style="margin-left:20px;">
                                            <span class="glyphicon glyphicon-plus"></span>
                                            &nbsp;<span>人力资源相关行业培训证书</span>
                                        </div>
                                    </div>

                                    <div class="next-step" style="margin-top:40px;">
                                        <button type="button" class="next-step-btn" id="subBtn">确认提交</button>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </#if>

                    <#if clazz=='partner' && pagetype=='form' && user=='project'>
                        <div class="recommend-form" id="projectStep1">
                            <div class="form-box">
                            <div class="title">项目推荐方注册</div>
                                <div class="hr-line"></div>

                                <form class="row form-group form-horizontal" role="form" id="project-form1">
                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 手机号</label>
                                        <div class="col-xs-7 col-md-7">
                                            <input class="form-control" type="text" id="phone" name="phone" placeholder="请输入11位手机号" maxlength="11" style="width:264px;">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 密码</label>
                                        <div class="col-xs-7 col-md-7">
                                            <input class="form-control" type="text" id="password" name="password" placeholder="请输入密码" maxlength="16" style="width:264px;">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 手机验证码</label>
                                        <div class="col-xs-7 col-md-7">
                                            <input class="form-control" type="text" id="verifycode" name="verifycode" placeholder="手机验证码" maxlength="6" style="width:134px;float:left;">
                                            <input type="button" class="send-msg-btn" id="send-msg-btn" value="获取验证码">
                                        </div>
                                    </div>

                                    <div class="hr-line2"></div>

                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 您的姓氏</label>
                                        <div class="col-xs-7 col-md-7">
                                            <input class="form-control" type="text" id="surname" name="surname" placeholder="请输入您的姓氏" maxlength="2" style="width:264px;">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label">所在城市</label>
                                        <div class="col-xs-7 col-md-7">
                                            <div class="birth-box">
                                                <input class="form-control" type="text"  style="width:300px;background-color: white;" disabled>
                                                <img src="${_PATH}/res/images/location_icon.png" class="location">
                                                <div class="birth-year" id="province" style="left:50px;">上海</div>
                                                <div>
                                                    <ul class="year-select" style="left:40px;">
                                                        <li data-type="province">上海</li>
                                                        <li data-type="province">北京</li>
                                                        <li data-type="province">深圳</li>
                                                    </ul>

                                                    <div class="triangle" style="left:104px;">
                                                        <div class="trigger"></div>
                                                    </div>
                                                </div>

                                                <div class="birth-year" id="city" style="left:160px;">上海市</div>
                                                <div>
                                                    <ul class="year-select" style="left:160px;">
                                                        <li data-type="city" style="padding:0 28px;">上海市</li>
                                                        <li data-type="city" style="padding:0 28px;">上海市</li>
                                                        <li data-type="city" style="padding:0 28px;">上海市</li>
                                                    </ul>

                                                    <div class="triangle" style="left:222px;">
                                                        <div class="trigger"></div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 公司名称</label>
                                        <div class="col-xs-7 col-md-7">
                                            <input class="form-control" type="text" id="companyname" name="companyname" placeholder="请输入您的公司名称" maxlength="50" style="width:264px;">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 邮箱</label>
                                        <div class="col-xs-7 col-md-7">
                                            <input class="form-control" type="text" id="email" name="email" placeholder="请输入您的邮箱账号" maxlength="50" style="width:264px;">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-9 col-md-9 col-md-offset-3" style="padding-left:0;">
                                            <label class="checkbox-inline" style="padding-left:0;">
                                                <input type="checkbox" id="inlineCheckbox" value="1" checked>
                                                <span class="agree">我已阅读并同意《项目方注册协议》和《项目发布承诺书》</span>
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-9 col-md-9 col-md-offset-3" style="padding-left:0;">
                                            <div class="error-tip">请填写姓名</div>
                                        </div>
                                    </div>

                                    <div class="next-step">
                                        <button type="button" class="next-step-btn" id="register-btn">提交注册</button>
                                    </div>
                                </form>
                            </div>

                        </div>

                    </#if>


                    <div class="nav">
                        <ul>
                            <li <#if clazz=='index'>class="activeTheme"</#if>>
                                <a href="${_PATH}/">首页</a>
                            </li>
                            <li <#if clazz=='release'>class="activeTheme"</#if>>
                                <a href="${_PATH}/project/publish">发布项目</a>
                            </li>
                            <li <#if clazz=='draw'>class="activeTheme"</#if>>
                                <a href="${_PATH}/">认领任务</a>
                            </li>
                            <li <#if clazz=='elite'>class="activeTheme"</#if>>
                                <a href="${_PATH}/">精英入驻</a>
                            </li>
                            <li <#if clazz=='partner'>class="activeTheme"</#if>>
                                <a href="/partner">合作伙伴</a>
                            </li>
                        </ul>
                    </div>
                    <#if session().memberId?exists>
                                                                                         欢迎您：${(session().nickName)}
                    <#else>
	                    <div class="nav-button">
	                    	<a href="${_PATH}/login" target="_self">
	                        	<button type="button" class="login-btn" id="login-btn">登录</button>
	                        </a>
	                        <a href="${_PATH}/member/register" target="_self">
	                        	<button type="button" class="register-btn" id="register-btn">立即注册</button>
	                        </a>
	                    </div>
                    </#if>
                </div>
            </div>
        </div>
    </div>

</#macro>
<#global mainhead=mainhead/>

<#macro accounthead opt>
    <#local opt=opt/>

<div class="wrap-head" id="accountHead">

    <div class="head" style="background-image:url('${_PATH}/res/images/pic_shadow2.png')">
        <div class="theme-ban">
            <div class="theme" style="position: relative;">
                <a href="${_PATH}/">
                    <img src="${_PATH}/res/images/logo.png" class="logo">
                </a>
                <div class="email">
                    <a href="javascript:void(0);" id="message_btn">
                        <div style="position: relative;">
                            <img src="${_PATH}/res/images/email_icon.png">
                            <#if unreadCount gt 0>
                              <img src="${_PATH}/res/images/orange_circle.png" class="orange-circle">
                            </#if>
                        </div>
                    </a>
                </div>
                <div class="head-portrait"></div>
                <div class="nav-triangle">
                    <div class="nav-trigger"></div>
                </div>

            </div>
        </div>
    </div>
</div>

</#macro>
<#global accounthead=accounthead/>