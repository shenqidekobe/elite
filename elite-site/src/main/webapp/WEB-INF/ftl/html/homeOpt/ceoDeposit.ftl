<@extend name="layout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/pay/pay.css">
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
            background-color: #ee7068;
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

        .deposit{
            text-align: right;
            margin-top:20px;
            padding-right:10px;
        }

        .deposit-count{
            font-size:20px;
            color:#FEA600;
            margin-left:10px;
        }
        .required{
            color:#FEA600!important;
            vertical-align: sub;
        }

        .deposit-tip{
            font-size: 12px;
            color:#9B9B9B;
        }

        .pay-box{
            width:100%;
            height:134px;
            border:1px solid #95D6E2;
            background-color: #F5FBFC;
            border-radius: 6px;
            position: relative;
        }

        .radio-box{
            width:18px;
            height:18px;
            position: absolute;
            top:54px;
            left:50px;
            cursor: pointer;
        }

        .two-ways{
            width:184px;
            height:90px;
            border:1px solid #E6E6E6;
            border-radius: 6px;
            position: absolute;
            top:22px;
            left:90px;
            text-align: center;
            font-size: 18px;
        }

        .current-way{
            border:1px solid #FEA600;
        }

        .ali-pay{
            width:145px;
            height:44px;
            margin-top:10px;
        }

        .bank-card{
            width:auto;
            height:56px;
            margin-top:3px;
        }

        .account-box{
            width:100%;
            height:146px;
            border:1px solid #95D6E2;
            background-color: #F5FBFC;
            padding-top:20px;
        }

        .certificate{
            font-size: 12px;
            color:#9B9B9B;
            margin-top:20px;
            cursor: pointer;
        }

        .certificate img{
            width:16px;
            height:16px;
            vertical-align: sub;
        }

        .certificate-box{
            width:100%;
            border:1px solid #95D6E2;
            background-color: #F5FBFC;
            padding:10px 30px;
            font-size: 12px;
        }
        .change-company{
            color:#2CB7C9;
            cursor: pointer;
            margin-left:10px;
        }

        .goto-pay-btn{
            width:140px;
            height:40px;
            background-color: #FEA600;
            border:none;
            border-radius: 20px;
            color:white;
            float:right;
            margin-top:20px;
            margin-bottom: 60px;
        }
        
         
       
        

    </style>
    </@block>
    <@block name="body">

    <#--头部-->
        <@accounthead opt=""/>


    <div class="content">
        <div class="flag">
            <span>个人主页 &gt; 我的项目 &gt; 堆糖网 项目详情 &gt; <span style="color:#2CB7C9;">提交项目意向金</span></span>
        </div>

        <div class="content-box">
            <div class="process">
                <div class="process-circle active-process">1</div>
                <div class="process-circle active-process" style="left:230px;">2</div>
                <div class="process-circle" style="left:440px;">3</div>
                <div class="process-circle" style="left:650px;">
                    <img src="/res/images/tick.png" width="24" height="auto">
                </div>
                <p class="process-title active-title">待审核</p>
                <p class="process-title active-title" style="left:224px;">审核中</p>
                <p class="process-title" style="left:410px;">审核通过,待立项</p>
                <p class="process-title" style="left:636px;">立项成功</p>

                <div class="process-line finish-line"></div>
                <div class="process-line finish-line" style="left:280px;"></div>
                <div class="process-line" style="left:490px;"></div>

                <p class="process-tip">项目经理对接</p>
                <p class="process-tip" style="left:310px;color:#FEA600;">提交项目意向金</p>
                <p class="process-tip" style="left:524px;">核实立项确认书</p>
            </div>

            <p style="margin-top:30px;">意向金服务对象</p>
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
                            <div style="padding-top:60px;">
                                <span>项目状态：</span><span style="color:#2CB7C9;">审核中</span>
                            </div>
                        </div>
                    </div>
            </div>

            <div class="deposit">
                <span style="font-size:14px;color:#4A4A4A;">意向金金额</span>&nbsp;&nbsp;<span class="deposit-count">￥3000</span>
                <div style="margin-top:8px;">
                    <span class="required">*</span>&nbsp;<span class="deposit-tip">意向金可充当项目费用</span>
                </div>
                <span class="deposit-tip">意向金仅代表你的意向</span>
            </div>

            <p style="margin-top:20px;">选择支付方式</p>
            <div class="pay-box">
                <img src="/res/images/radio_check_icon.png" class="radio-box">
                <div class="two-ways current-way">
                    <img src="/res/images/ali_pay.png" class="ali-pay">
                    <p style="margin-top:6px;">支付宝</p>
                </div>

                <img src="/res/images/radio_empty_icon.png" class="radio-box" style="left:345px;">
                <div class="two-ways" style="left:400px;">
                    <img src="/res/images/bank_card.png" class="bank-card">
                    <p style="margin-top:2px;">银行转账<span style="font-size: 14px;">(线下打款)</span></p>
                </div>
            </div>

            <div style="margin-top:20px;">
                <p style="font-size: 12px;color:#9B9B9B;">填写账户</p>
                <div class="account-box form-horizontal">
                    <div class="col-xs-8 col-md-8">
                        <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span> 打款账户</label>
                        <div class="col-xs-6 col-md-6">
                            <input class="form-control" type="text" id="bankcard" name="bankcard" placeholder="请输入您的打款账户" maxlength="30">
                        </div>
                    </div>

                    <div class="col-xs-8 col-md-8" style="margin-top:20px;">
                        <label class="col-xs-2 col-md-2 control-label">收款账户</label>
                        <div class="col-xs-10 col-md-10">
                            <div style="line-height: 34px;color:#2CB7C9;">
                                <span>云英汇</span>
                                <span style="margin-left:20px;">622279 7889340 9302035</span>
                                <span style="margin-left:20px;">中国农业银行</span>
                            </div>
                            <div style="line-height: 34px;color:#2CB7C9;">
                                <span>云英汇</span>
                                <span style="margin-left:20px;">622279 7889340 9302035</span>
                                <span style="margin-left:20px;">中国银行</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <p class="certificate">索要发票&nbsp;&nbsp;<img src="/res/images/expand_icon.png"></p>
            <div class="certificate-box">
                <p><span class="index">索要发票：</span>&nbsp;<span class="value">纸质发票</span></p>
                <p><span class="index">发票信息：</span>&nbsp;<span class="value">服务费</span></p>
                <span class="index">发票抬头：</span>&nbsp;<span class="value">拉不拉卡科技有限公司</span>
                <span class="change-company">修改</span>
            </div>

            <button type="button" class="goto-pay-btn">去支付</button>
            
	            <div class="payBox paySuccess" id="paySuccess">
	    			<div class="box-a clearfloat">
	        			<span class="box-a-logo successLogo"></span>
	        			<span class="box-a-txt color2c">支付成功！</span>
	    			</div>
	    			<div class="box-b">
	    				<div class="box-b-content">
	    						<span class="color2c">6s</span>
	        					<span>回到个人主页</span>
	    				</div>
	        			
	    			</div>
	    		<div class="box-c"><a class="box-c-content" href="javascript:history.back()">立即返回</a></div>
			</div>
            
            
            
            
            <div class="payBox payFaiure" id="payFaiure">
    			<div class="box-a clearfloat">
        			<span class="box-a-logo failureLogo"></span>
        			<span class="box-a-txt colorfe">支付失败！</span>
    			</div>
    			<div class="box-b">
    				<div class="box-b-content">
    					<span class="colorfe">6s</span>
        				<span>回到个人主页</span>
    				</div>
        		
    			</div>
    			<div class="box-c"><a class="box-c-content" href="javascript:history.back()">立即返回</a></div>
    			
			</div>

            
            
            <div class="payBank" id="payBank">
	    <div class="payClose"></div>
	    <div class="box-a clearfloat paddingbottom20">
	        <span class="box-a-logo successLogo"></span>
	        <span class="box-a-txt color2c">您已选择：银行转账（线下打款）</span>
	    </div>
	    <div class="box-b paddingbottom33">
	        <div class="box-b-content">
	            完成后记得提供
	            <span class="colorfe"> 转账流水号、打款账号</span>
	            给您的项目经理哦~
	        </div>
	    </div>
	    <div class="box-c">
	        <div class="box-c-box">
	            <div class="box-c-l">
	                <span class="colorfe">6s</span>
	                <span class="bo-c-txt">后回到个人主页</span>
	            </div>
	            <a class="box-c-content" href="javascript:history.back()">立即返回</a>
	        </div>

    </div>

</div>

            
            
            
            

        </div>
    </div>


    </@block>
    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/index.js')}"></script>
    </@block>
</@extend>