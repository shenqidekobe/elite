<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>云英汇-您身边的技术合伙人</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="keywords" content="云英汇"/>
    <meta name="description" content="云英汇"/>
    <link rel="shortcut icon" href="/res/images/favicon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/res/style/css/main.css"/>
    <link rel="stylesheet" href="/res/style/css/bootstrap.min.css"/>
    
    <script src="/res/script/plugin/html5shiv.min.js"></script>
     <script src="/res/script/plugin/respond.min.js"></script>

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
    <div id="footer">
        <div class="wrap-footer">
            <div class="footer-top">
                <div class="footer-box">
                    <div class="footer-logo">
                        <img src="/res/images/logo.png">
                    </div>
                    <div class="slogan">您身边的技术合伙人6666666666!</div>
                </div>
            </div>
            <div class="footer-bottom">
                <div class="footer-box">的司法
                    <div style="position: relative;">
                        <div class="contact">
                            <ul>
                                <li style="padding-left:0;">
                                    <a href="#">关于我们</a>
                                </li>
                                <li>
                                    <a href="#">在线咨询</a>
                                </li>
                                <li>
                                    <a href="#">商务合作</a>
                                </li>
                                <li>
                                    <a href="#">投诉建议</a>
                                </li>
                                <li style="background: none;">
                                    <a href="#">联系我们</a>
                                </li>
                            </ul>
                        </div>

                        <div class="online">
                            <p>客服电话：400-168-1088</p>
                            <p>工作时间：周一至周五  9:00-18:00</p>
                        </div>

                        <div class="friend-link">
                            <p class="friend-link-label">友情链接</p>
                            <ul>
                                <li>
                                    <a href="#" target="_blank">上海投资人中心</a>
                                </li>
                                <li>
                                    <a href="#" target="_blank">宁波乐育IT中心</a>
                                </li>
                                <li>
                                    <a href="#" target="_blank">宁波天使投资NGO组织</a>
                                </li>
                            </ul>
                            <ul>
                                <li>
                                    <a href="#" target="_blank">宁波天使投资俱乐部</a>
                                </li>
                                <li>
                                    <a href="#" target="_blank">宁波教育</a>
                                </li>
                                <li>
                                    <a href="#" target="_blank">上海360</a>
                                </li>
                            </ul>
                        </div>

                        <div class="qrcode">
                            <img src="/res/images/qr_code.png">
                            <div class="qrcode-tip">
                                <p>关注“云英汇”</p>
                                <p>了解最新动态</p>
                            </div>
                        </div>
                    </div>

                     <#--版权声明-->
                    <div class="copyright">©Copy Right By YUNYINGHUI.COM</div>
                </div>
            </div>
        </div>
    </div>
</@block>

</div>

<#--浮动元素-->
<@block name="flow">
    <div class = "online-helper">
        <ul>
            <li class="online-helper-service"><a href="http://wpa.b.qq.com/cgi/wpa.php?ln=1&key=XzkzODA0MTkwOV8yOTk2ODhfNDAwOTYwMTE4MF8yXw" target="_blank"><span>在线客服</span><i></i></a></li>
            <li class="online-helper-noob"><a href="/footer/startOff" target="_blank"><span>新手指导</span><i></i></a></li>
            <li class="online-helper-gotop"><a href="javascript:;" onclick="top.scrollTo(0,0);" target="_self"><span>返回顶部</span><i></i></a></li>
        </ul>
    </div>
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
                        <a href="/reset-password">忘记密码？</a>
                    </div>
                    <div class="btn-wrap">
                        <button type="button" id="login-btn">登&nbsp;&nbsp;录</button>
                    </div>
                    <div class="modal-tr"><span>没有账号？</span><a href="/register">现在注册</a></div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="/res/script/plugin/require.min.js"></script>
<script src="/res/script/plugin/config.js"></script>
<script src="/res/script/js/layout.js"></script>

<@block name="script"></@block>
</body>
</html>