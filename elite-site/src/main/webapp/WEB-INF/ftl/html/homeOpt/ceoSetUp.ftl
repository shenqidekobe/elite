<@extend name="layout">
    <@block name="cs">
    <style type="text/css">

        .content{
            width:1000px;
            margin:0 auto;
        }

        .flag{
            font-size: 14px;
            color:#5C5C5C;
            padding-top:20px;
            margin-bottom: 20px;
        }

        .content-box{
            width:100%;
            background-color: #FAFAFA;
            padding-top:20px;
            padding-bottom: 130px;
        }

        .process{
            width:720px;
            height:100px;
            position: relative;
            margin:0 auto;
        }

        .process-line{
            width:150px;
            border-bottom: 2px solid #BFBFBF;
            position: absolute;
            top:36px;
            left:70px;
        }

        .process-circle{
            width: 40px;
            height: 40px;
            border:none;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            background-color: #BFBFBF;
            text-align: center;
            line-height: 40px;
            color:white;

            position: absolute;
            top:16px;
            left:20px;
            z-index: 200;
        }

        .active-process{
            background-color: #7DD2E1;
        }

        .process-title{
            position: absolute;
            top:68px;
            left:16px;
        }

        .active-title{
            color:#2CB7C9;
        }

        .finish-line{
            border-color: #95D6E2;
        }

        .process-tip{
            position: absolute;
            left:108px;
            top:16px;
            font-size: 12px;
            color:#9B9B9B;
        }

        .project-box{
            width:100%;
            height:175px;
            border:1px solid #E6E6E6;
            border-radius: 6px;
        }

        .part1{
            width:100%;
            height:40px;
            line-height: 40px;
            background-color: #F2F2F2;
            border-bottom: 1px solid #E6E6E6;
            font-size: 14px;
            color:#9B9B9B;
            border-top-left-radius: 5px;
            border-top-right-radius: 5px;
        }

        .vertical-line{
            height:28px;
            border-right:1px solid #E6E6E6;
            position: absolute;
            top:5px;
            left:240px;
        }

        .project-detail{
            width:100%;
            height:133px;
            background-color: #FAFAFA;
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
        }

        .main-info{
            float:left;
            width:54%;
            height:135px;
            border-right: 1px solid #E6E6E6;
        }

        .project-logo{
            width:95px;
            height:95px;
            line-height: 95px;
            text-align: center;
            background-color: #F29D70;
            color:white;
            font-size: 58px;
            float:left;
        }

        .project-info{
            float:left;
            margin-left:20px;
        }
        .project-name{
            display: inline-block;
            margin-right:10px;
        }
        .index{
            font-size: 12px;
            color:#9B9B9B;
        }

        .value{
            font-size: 12px;
        }

        .status{
            float:left;
            width:22%;
            height:133px;
            border-right: 1px solid #E6E6E6;
            font-size: 12px;
        }

        .opt{
            float:left;
            width:24%;
            height:133px;
            background-color: #F5FBFC;
            text-align: center;
        }


        .class-label{
            display: inline-block;
            width:40px;
            height:16px;
            line-height: 16px;
            text-align: center;
            border:1px solid #E6E6E6;
            border-radius: 10px;
            color:#9B9B9B;
            font-size: 12px;
            margin-right:5px;
        }

        .setup-agreement{
            width:860px;
            border-radius: 8px;
            background-color: #F5FBFC;
            margin-left:auto;
            margin-right:auto;
            margin-top: 40px;
            padding-top:20px;
            padding-bottom: 20px;
        }

        .ok-btn{
            width:150px;
            height:40px;
            border:none;
            border-radius: 18px;
            background-color: #FEA600;
            color:white;
            margin-top:30px;
        }
        
        
        
.setup-agreement-content{
    margin: 25px 92px;
}

.text-center{
    text-align: center;
}

/*确认书*/
.margintop20{
    margin-top: 20px;
}
.marginbottom12{
    margin-bottom: 12px;
}
.marginbottom20{
    margin-bottom: 20px;
}
.marginbottom25{
    margin-bottom: 25px;
}
.fontsize16{
    font-size: 16px;
}
.fontsize12{
    font-size: 12px;
}
.color4a{
    color: #4a4a4a;
}
.colorfe{
    color: #FEA600;
}

.color9b{
    color: #9b9b9b;
}

.marginleft20{
    margin-left: 20px;
}
/*表格*/


.td-btn{
    width: 90px;
    height: 26px;
    border-radius: 32px;
    font-size: 12px;
    background: #2cb7c9;
    color: #fff;
    line-height: 26px;
    text-align: center;

}

.three-box{
    width: 500px;
    height: 430px
}
.content-title .title-b{
    font-size: 12px;
    color: #4a4a4a;
    line-height: 30px;
}
.content-title .title-b span{
    color: #4a4a4a;
}
.content-title1{
    width: 500px;
    height: 30px;
}
.content-title{
    width: 500px;
    height: 30px;
    margin-bottom: 25px;
}
.title-a{
    width: 25%;
    height: 30px;
    float: left;
    text-align: center;
}

.title-b{
    width: 50%;
    height: 30px;
    float: left;
    text-align: center;
}
.title-c{
    width: 25%;
    height: 30px;
    float: left;
    text-align: center;
}

.four-box{
    width: 675px;
    height: 170px;
    border-radius: 6px;
    font-size: 16px;
    color: #4a4a4a;
    background: #fff;
    border: 1px solid #e6e6e6;
    margin-top: 15px;
    margin-left: 15px;
    margin-bottom: 15px;
    overflow: auto;
}
.four-box-content{
    margin: 20px;
    font-size: 14px;
    height: 1000px;
}
.fiv-box{
    width: 675px;
    height: 151px;
    border-radius: 6px;
    border: 1px solid #e6e6e6;
    background: #fff;
    margin-top: 15px;
    margin-left: 15px;
    margin-bottom: 15px;
}
.fiv-box-content{
    margin: 20px 110px 36px 20px;
    font-size: 14px;
}
.six-box{
    width: 673px;
    height: 38px;
    border-radius: 6px;
    border: 1px solid #e6e6e6;
    background: #fff;
    margin-top: 15px;
    margin-left: 15px;
    margin-bottom: 15px;
}
.six-box-content{
    margin-top: 10px;
    margin-bottom: 10px;
    margin-left: 25px;
}
.six-icon-a{
    width: 18px;
    height: 20px;
    display: block;
    background: url("${_PATH}/res/images/six-icon-a.png");
    margin-right: 9px;
    cursor: pointer;
    
}
.six-icon-b{
    width: 20px;
    height: 20px;
    display: block;
    background: url("${_PATH}/res/images/six-icon-b.png");
    cursor: pointer;
}
.fl{
    float: left;
}
.six-txt{
    margin-right: 49px;
 }       
        
        

    </style>
    </@block>
    <@block name="body">

    <#--头部-->
        <@accounthead opt=""/>


    <div class="content">
        <div class="flag">
            <span>个人主页 &gt; 我的项目 &gt; <span style="color:#2CB7C9;">确认立项书</span></span>
        </div>

        <div class="content-box">
            <div class="process">
                <div class="process-circle active-process">1</div>
                <div class="process-circle active-process" style="left:230px;">2</div>
                <div class="process-circle active-process" style="left:440px;">3</div>
                <div class="process-circle" style="left:650px;">
                    <img src="/res/images/tick.png" width="24" height="auto">
                </div>
                <p class="process-title active-title">待审核</p>
                <p class="process-title active-title" style="left:224px;">审核中</p>
                <p class="process-title active-title" style="left:410px;">审核通过,待立项</p>
                <p class="process-title" style="left:636px;">立项成功</p>

                <div class="process-line finish-line"></div>
                <div class="process-line finish-line" style="left:280px;"></div>
                <div class="process-line finish-line" style="left:490px;"></div>

                <p class="process-tip">项目经理对接</p>
                <p class="process-tip" style="left:310px;">提交项目意向金</p>
                <p class="process-tip" style="left:524px;color:#FEA600;">核实立项确认书</p>
            </div>

            
            <div class="project-box">
                <div style="position: relative;">
                    <div class="part1">
                        <div class="col-xs-3 col-md-3">
                            <span>创建时间：2016-03-02  9：30</span>

                        </div>
                        <div class="col-xs-9 col-md-9">
                            <span class="pull-left">任务编号：2016030200008</span>
                        </div>
                    </div>
                    <div class="vertical-line"></div>
                </div>

                <div class="project-detail">
                    <div class="main-info">
                        <div style="padding:20px;">
                            <div class="project-logo">堆</div>
                            <div class="project-info">
                                <div>
                                    <span class="project-name">堆糖网</span>
                                    <div class="class-label">电商</div>
                                    <div class="class-label">社交</div>
                                </div>
                                <div style="margin-top:10px;"><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                                <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                                <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02 &nbsp;共45个工作日</span></div>
                            </div>

                        </div>
                    </div>
                    <div class="status">
                        <div style="padding-top:36px;padding-left:50px;">
                            <p style="color:#9B9B9B;"></p>
                            <p><span class="index">已提交意向金：</span>&nbsp;<span class="value">100</span></p>
                            <p><span class="index">股权托管：</span>&nbsp;<span class="value">100</span></p>
                        </div>
                    </div>
                    <div class="opt">
                        <div style="padding-top:46px;">
                            <p style="color:#2CB7C9;">审核通过，待立项</p>
                            <a href="#" target="_blank" style="font-size: 14px;">查看详情</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="setup-agreement">

    <div class="setup-agreement-content">
        <p class="text-center">《堆糖网立项确认书》</p>
        <p class="marginbottom20 fontsize16 color4a">一、研发费用<span class="marginleft20">￥16000</span></p>
        <p class="fontsize16 color4a marginbottom20">二、股权<span class="marginleft20">20%</span></p>
        <div>
            <p class="fontsize16 color4a marginbottom12">三、研发计划</p>
            <div class="three-box">
                <div class="three-box-content">
                    <div class="content-title1">
                        <div class="title-a fontsize12 color9b">项目阶段</div>
                        <div class="title-b fontsize12 color9b">阶段时间规划</div>
                        <div class="title-c fontsize12 color9b">阶段所需费用</div>
                    </div>

                    <div class="content-txt">
                        <div class="content-title">
                            <div class="title-a">
                                <div class="td-btn">咨询策划阶段</div>
                            </div>
                            <div class="title-b">2015.2.12-2016.3.25<span>|</span>共<span class="colorfe">30</span>天</div>
                            <div class="title-c colorfe">10万</div>
                        </div>

                        <div class="content-title">
                            <div class="title-a">
                                <div class="td-btn">品牌设计阶段</div>
                            </div>
                            <div class="title-b">2015.2.12-2016.3.25<span>|</span>共<span class="colorfe">30</span>天</div>
                            <div class="title-c colorfe">10万</div>
                        </div>

                        <div class="content-title">
                            <div class="title-a">
                                <div class="td-btn">需求梳理阶段</div>
                            </div>
                            <div class="title-b">2015.2.12-2016.3.25<span>|</span>共<span class="colorfe">30</span>天</div>
                            <div class="title-c colorfe">10万</div>
                        </div>

                        <div class="content-title">
                            <div class="title-a">
                                <div class="td-btn">界面设计阶段</div>
                            </div>
                            <div class="title-b">2015.2.12-2016.3.25<span>|</span>共<span class="colorfe">30</span>天</div>
                            <div class="title-c colorfe">10万</div>
                        </div>

                        <div class="content-title">
                            <div class="title-a">
                                <div class="td-btn">开发测试阶段</div>
                            </div>
                            <div class="title-b">2015.2.12-2016.3.25<span>|</span>共<span class="colorfe">30</span>天</div>
                            <div class="title-c colorfe">10万</div>
                        </div>

                        <div class="content-title">
                            <div class="title-a">
                                <div class="td-btn">交付上线阶段</div>
                            </div>
                            <div class="title-b">2015.2.12-2016.3.25<span>|</span>共<span class="colorfe">30</span>天</div>
                            <div class="title-c colorfe">10万</div>
                        </div>

                        <div class="content-title">
                            <div class="title-a">
                                <div class="td-btn">运营阶段</div>
                            </div>
                            <div class="title-b">2015.2.12-2016.3.25<span>|</span>共<span class="colorfe">30</span>天</div>
                            <div class="title-c colorfe">10万</div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <div>
            <p>四、立项确认书详细条款</p>
            <div class="four-box">
                <div class="four-box-content">
                    <div >
                        <p>一，项目描述</p>
                        <p>一款阅读类应用开发，需要产品经理、设计、iOS 开发、安卓开发、Web 开发、后台开发、Web测试和APP测试
                        </p>
                    </div>
                    <div>
                        <p>二，项目需求</p>
                        <p>用户端主要功能描述</p>
                        <p>注册登录</p>
                    </div>
                    <div>
                        <p>二，项目需求</p>
                        <p>用户端主要功能描述</p>
                        <p>注册登录</p>
                    </div>
                    <div>
                        <p>二，项目需求</p>
                        <p>用户端主要功能描述</p>
                        <p>注册登录</p>
                    </div>
                    <div>
                        <p>二，项目需求</p>
                        <p>用户端主要功能描述</p>
                        <p>注册登录</p>
                    </div>
                    <div>
                        <p>二，项目需求</p>
                        <p>用户端主要功能描述</p>
                        <p>注册登录</p>
                    </div><div>
                    <p>二，项目需求</p>
                    <p>用户端主要功能描述</p>
                    <p>注册登录</p>
                </div>

                </div>
            </div>
        </div>


        <div>
            <p>五，其他说明</p>
            <dl class="fiv-box">
                <div class="fiv-box-content">
                    <dd>1、由于需求方是非IT行业，所以产品经理需要有较强的沟通能力、业务梳理与理解能力</dd>
                    <dd>2、深圳本地设计师，需能接受上门面谈</dd>
                    <dd>3、项目为服装设计类平台，故需求方对设计要求很严苛</dd>
                    <dd>4、参与项目的设计师需要提前熟掌握项目和代码管理工具coding的使用方法</dd>
                    <dd>5、能长期合作优先，需要介入后期项目开发的跟踪</dd>
                </div>
            </dl>
        </div>


        <div>
            <p>六、附件下载</p>
            <div class="six-box">
                <div class="six-box-content">
                    <span class="six-icon-a fl"></span>
                    <span class="six-txt fl">堆糖网项目招标书</span>
                    <span class="six-icon-b fl"></span>
                </div>
            </div>
        </div>

    </div>

</div>
            <div style="margin-top:20px;text-align: right;padding-right:20px;">
                <div>
                    <label class="checkbox-inline">
                        <input type="checkbox" id="inlineCheckbox1" value="option1" checked>
                        &nbsp;<span style="font-size:12px;">我已阅读并同意<span style="color:#D0021B;">《堆糖网立项确认书》</span></span>
                    </label>
                </div>
                <button type="button" class="ok-btn">确认</button>
            </div>

        </div>
    </div>


    </@block>
    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/index.js')}"></script>
    </@block>
</@extend>