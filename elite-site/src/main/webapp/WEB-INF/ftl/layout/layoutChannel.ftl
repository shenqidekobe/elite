<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>云英汇，领先的IT技术人才共享平台</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
    <meta name="keywords" content="云英汇"/>
    <meta name="description" content="云英汇"/>
    <link type="text/css" rel="shortcut icon" href="${_PATH}/res/images/index/favicon.png" type="image/x-icon"/>
    <link type="text/css" rel="stylesheet"    href="${_PATH}/res/style/css/index/index.css"/>
    <link type="text/css" rel="stylesheet"    href="${_PATH}/res/style/css/bootstrap.min.css"/>
    
    <script src="${_PATH}/res/script/plugin/html5shiv.min.js"></script>
    <script src="${_PATH}/res/script/plugin/respond.min.js"></script>
    <script>var ctx= '${_PATH}';var uploadPath= '${_UPLOADPATH}';</script>
    <!--<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan style='display:none;' id='cnzz_stat_icon_1260218081'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1260218081%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>-->
<@block name="head">
</@block>
<@block name="cs">
</@block>
</head>
<body>
<div id="isUpgrade" style="display: none;">
    <p class="browsehappy">您使用的浏览器<strong>版本过低</strong>。请<strong>升级您的浏览器</strong>以获得更加的良好浏览体验。</p>
    <p class="browsehappy">我们建议使用Google的Chrome浏览器   或者  百度的百度浏览器  或者 微软的IE浏览器 对应最新版本。</p>
    <p class="browsehappy"><strong>【Windows XP操作系统用户】</strong></p>
    <p class="browsehappy"><a href="http://rj.baidu.com/soft/detail/11339.html" target=_blank><font color=blue> [从百度软件中心]下载最新版本百度浏览器 </font></a></p>
    <p class="browsehappy"><a href="http://rj.baidu.com/soft/detail/14744.html" target=_blank><font color=blue> [从百度软件中心]下载最新版本Chrome浏览器 </font></a></p>
    <p class="browsehappy"><strong>【Windows 7以及以上版本操作系统用户】</strong></p>
    <p class="browsehappy"><a href="http://rj.baidu.com/soft/detail/11339.html" target=_blank><font color=blue> [从百度软件中心]下载最新版本百度浏览器 </font></a></p>
    <p class="browsehappy"><a href="http://rj.baidu.com/soft/detail/14744.html" target=_blank><font color=blue> [从百度软件中心]下载最新版本Chrome浏览器 </font></a></p>
    <p class="browsehappy"><a href="http://windows.microsoft.com/zh-CN/internet-explorer/download-ie" target=_blank><font color=blue> [从微软中国官网]下载最新版本IE浏览器 </font></a></p>
</div>

<div class="wrap">

<!--main content begin -->
<@block name="body"></@block>



<@block name="footer">
	<div class="y-ft">
	    <div class="ft-information">
	        <div class="information-box">
	        	<div class="logoBox"><img src="/res/images/logo.png"><span>领先的技术人才共享平台</span></div>
	            <div class="information-box-l">
	                <div class="about">
		                <a href="${_PATH}/index/about">关于我们</a>
		                <a href="${_PATH}/register/partner">我有项目推荐</a>
		                <a href="${_PATH}/register/partner?rp=elite">我有人才推荐</a>
		                <a href="${_PATH}/help">常见问题</a>
		                <a href="${_PATH}/invite" style="display:none;">意见反馈</a>
	                </div>
	                <span class="record">&copy;Copy Right By YUNYINGHUI.COM</span>
	            </div>
	            <div class="wechat"><img src="/res/images/index/wechat.jpg" alt="微信二维码" width="120" height="120"><span>欢迎关注云英汇微信号</span></div>
	            
	        </div>
	    </div>
	    
	</div>
</@block>

</div>

<#--浮动元素-->
<@block name="flow">
    <#--<div class = "online-helper">
        <ul>
            <li class="online-helper-service"><a href="http://wpa.b.qq.com/cgi/wpa.php?ln=1&key=XzkzODA0MTkwOV8yOTk2ODhfNDAwOTYwMTE4MF8yXw" target="_blank"><span>在线客服</span><i></i></a></li>
            <li class="online-helper-noob"><a href="/footer/startOff" target="_blank"><span>新手指导</span><i></i></a></li>
            <li class="online-helper-gotop"><a href="javascript:;" onclick="top.scrollTo(0,0);" target="_self"><span>返回顶部</span><i></i></a></li>
        </ul>
    </div>-->
</@block>

<#--登录模态框-->
<div class="modal fade modal4login" id="modal-login" role="dialog" tabindex="-1"  aria-labelledby="myModalLabel" data-backdrop="false" aria-hidden="true">
    <div class="modal4login-overlay"></div>
    <div class="modal-dialog modal4login-dialog">
        <div class="modal-content modal4login-content">
            <div class="modal-header modal4login-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">您尚未登录</h4>
            </div>
            <div class="modal-body modal4login-body">
                <form class="form-horizontal" action="/login" role="form" id="login-form" method="post">
                    <input id="gotoUrl" name="gotoUrl"  type="hidden"/>
                    <div class="inp-wrap inp-wrap4securephone">
                        <input type="text"  id="login-securephone" name="securephone" placeholder="请输入登录账号">
                    </div>
                    <div class="error-info" id="username-error"></div>
                    <div class="inp-wrap  inp-wrap4password">
                        <input type="password" maxlength="16"  id="login-password" name="password" placeholder="请输入登录密码">
                    </div>
                    <div class="error-info" id="pwd-error"></div>
                    <div class="pwd-forget-wrap">
                    <#--<label for="isChecked">记住用户名</label><input type="checkbox" id="isChecked" checked>-->
                        <a href="${_PATH}/forget">忘记密码？</a>
                    </div>
                    <div class="btn-wrap">
                        <button type="button" id="login-btn">登&nbsp;&nbsp;录</button>
                    </div>
                    <div class="modal-tr"><span>没有账号？</span><a href="${_PATH}/register">现在注册</a></div>
                </form>
            </div>
        </div>
    </div>
</div>

<script  src="${_PATH}/res/script/plugin/copyJS/clipboard.min.js"></script>
<script src="${_PATH}/res/script/plugin/require.min.js"></script>
<script src="${_PATH}/res/script/plugin/config.js"></script>
<script src="${_PATH}/res/script/js/layout.js"></script>
<script src="${_PATH}/res/script/myjs/member/header.js"></script>
<@block name="script"></@block>
</body>
</html>