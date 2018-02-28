<@extend name="companyInfoLayout">
    <@block name="cs">
    <style type="text/css">
        .part{
        background:red;
            height:50px;
            margin-bottom: 30px;
        }
        .link-line{
            float:left;
            width:35%;
            padding-top:6px;
        }
        .link-line hr{
            border-color: #65C9D6;
        }

        .theme-block{
            float:left;
            width:30%;
        }

        .theme-tab{
            width:162px;
            height:50px;
            line-height: 50px;
            text-align: center;
            background-color: #7DD2E1;
            color:white;
            font-size: 20px;
            border-radius: 26px;
            margin: 0 auto;
        }

        .title-company{
            width:100%;
            height:186px;
            background-color: #FAFAFA;
            border-radius: 3px;
            /*margin-top:30px;*/
        }

        .company-input{
            display: inline-block;
        }

        .opt-icon{
            width:20px;
            height:20px;
            vertical-align: sub;
        }
        .edit{
            line-height: 34px;
            cursor: pointer;
        }

        .credit{
            width:100%;
            height:554px;
            background-color: #FAFAFA;
            border-radius: 3px;
        }

        .secret-circle{
            width: 20px;
            height: 20px;
            border: 1px solid #4A90E2;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            text-align: center;
            line-height: 20px;
            font-size: 12px;
            color:#4A90E2;
            display: inline-block;
        }

        .use-tip{
            font-size: 12px;
            color:#9B9B9B;
            margin-left:20px;
        }
        li{
		    list-style: none;
		    float: left;
		}
		.clearfloat:after{
		    display:block;
		    clear:both;
		    content:"";
		    visibility:hidden;
		    height:0
		}
		.clearfloat{
		    zoom:1
		}
		.marginbottom10{
		    margin-bottom: 10px;
		}
		.marginbottom20{
		       margin-bottom: 20px;
		   }
		.marginbottom50{
		    margin-bottom: 50px;
		}
		
		.marginright20{
		    margin-right: 20px;
		}
		.marginright80{
		    margin-right: 80px;
		}
		.marginright15{
		    margin-right: 20px;
		}
		.name{
		    display: block;
		    float: left;
		}
		
		.name-logo{
		    width: 22px;
		    height: 22px;
		    display: block;
		    background: url("${_PATH}/res/images/ceo/man.png") no-repeat;
		    display: block;
		    float: left;
		    margin-left: 15px;
		}
		
		span.c-title {
      		width: 120px;
    		height: 20px;
    		font-size: 14px;
    		color: #9b9b9b;
    		float: left;
		}
		
		.c-logo{
		    width: 16px;
		    height: 20px;
		    display: block;
		    background: url("${_PATH}/res/images/.");
		}
		
		.c-box{
		    width: 730px;
		    min-height: 1050px;
		    background: #fafafa;
		    position: relative;
		}
		.c-box-a{
		    width: 730px;
		    height: 215px;
		    background: url("${_PATH}/res/images/c-box-a.png");
		}
		
		.badge-icon{
		    width: 18px;
		    height: 20px;
		    background: url("${_PATH}/res/images/ceo/badge.png");
		    position: absolute;
    		right: 0px;
		}
		
		
		
		
		
		.c-box-logo{
		    width: 193px;
		    height: 194px;
		    position: absolute;
		    top: 153px;
		    left: 264px;
		}
		.box-logo-a{
		    width: 120px;
		    height: 120px;
		    background: url("${_PATH}/res/images/box-logo-a.png") no-repeat;
		    margin: 0 auto;
		
		}
		
		.b-content-a{
		    width: 645px;
    		height: 478px;
    		padding-top: 172px;
    		margin: 0 auto;
		}
		
		.b-content-b{
		    width: 645px;
		    height: 275px;
		    margin: 0 auto;
		}
		.b-content-c{
		    width: 645px;
		    height: 297px;
		    margin: 0 auto;
		}
		
		.line-box{
		    width: 560px;
		    height: 36px;
		    margin: 0 auto;
		}
		.line{
		    width: 200px;
		    height: 18px;
		    border-bottom: 1px solid #95d6e2;
		    line-height: 36px;
		    display: block;
		    float: left;
		}
		.line-btn{
		    width: 120px;
		    height: 36px;
		    border-radius: 100px;
		    background: #2cb7c9;
		    color: #fff;
		    font-size: 16px;
		    display: block;
		    float: left;
		    line-height: 36px;
		    text-align: center;
		    margin-left: 20px;
		    margin-right: 20px;
		}
		
		.idbox{
		    width: 150px;
		    height: 105px;
		    border: 1px dashed #ccc ;
		    background: #fff;
		    cursor: pointer;
		    display: block;
		    margin-right: 8px;
		}
		.idlogo{
		    width: 30px;
		    height: 30px;
		    display: block;
		    background: url("${_PATH}/res/images/idlogo.png");
		    margin: 22px 60px 16px;
		}
		.idtxt{
		    font-size: 14px;
		    color: #9b9b9b;
		    margin-left: 40px;
		
		}
		.c-logo{
		    width: 15px;
		    height: 20px;
		    display: block;
		    background: url("${_PATH}/res/images/c-logo.png");
		    float: left;
    		cursor: pointer;
		}
		.c-tite{
		    display: block;
		    float: right;
		}
		.c-city{
		    display: block;
    		float: left;
    		font-size: 14px;
    		color: #9b9b9b;
    		margin-left: 10px;
		}
		
		.box-logo-b {
		    margin: auto;
		    width: 80px;
		    padding-top: 16px;
		    padding-bottom: 11px;
		}
		.cy-l{
		    display: block;
    		float: left;
    		margin-right: 80px;
    		font-size: 16px;
    		color: #4a4a4a;
    		width: 150px;
    		height: 22px;
    		line-height: 22px;
		}
		.cy-c{
		        display: block;
    			float: left;
    			width: 80px;
    			height: 22px;
    			font-size: 14px;
    			color: #4a4a4a;
    			line-height: 22px;
		}
		.cy-r{
		    display: block;
		    float: left;
		    margin-left: 285px;
		    cursor: pointer;
        }
        
        .pen{
    		display: block;
    		width: 20px;
    		height: 20px;
    		background: url("${_PATH}/res/images/pen.png");
    		float: left;
		}
		span.editor {
   			float: left;
		}
		
		.com {
    		margin-top: 40px;
		}
		
		.director {
    		width: 100%;
    		height: 20px;
    		margin-top: 10px;
		}
		
		.dottedLine {
    		width: 100%;
    		height: 25px;
    		border-bottom: 1px dotted #9d9d9d;
		}
		
		.margintop50 {
    		margin-top: 50px;
		}
		
		.margintop27 {
    		margin-top: 27px;
		}
		
		.marginbottom40 {
    		margin-bottom: 40px;
		}
		
		.margintop15 {
    		margin-top: 15px;
		}
		
		
		
		
		
		
		
		*{
    margin: 0;
    padding: 0;
}

li{
    list-style: none;
}

.ceoInfo-l-a{
    width: 240px;
    border: 1px solid #E6E6E6;
    border-radius: 4px;
    text-align: center;
    background-color: #FAFAFA;
    box-shadow: 2px 2px 6px 0px rgba(0,0,0,0.3);
    margin-bottom: 20px;
    height: 372px;
}


.ceoInfo-l-b{
    width: 240px;
    height: 70px;
    border: 1px solid #E6E6E6;
    border-radius: 10px;
    background-color: #fafafa;
    box-shadow: 2px 2px 6px 0px rgba(0,0,0,0.3);
    margin-bottom: 20px;
}




.ceoInfo-l-a ul li{
    height: 39px;
    line-height: 39px;
    width: 170px;
    margin-left: 70px;
    cursor: pointer;
}

.select{
    height: 39px;
    line-height: 39px;
    width: 170px;
    border-right:3px solid #2cb7c9;
    color: #2cb7c9;
}

.li-box {
    height: 24px;
    width: 105px;
    padding-top: 7.5px;
}

.ceoInfo-l-a ul li .icon-box{
    width: 22px;
    height: 24px;
    display: block;
    float: left;
    margin-right: 15px;
}

.ceoInfo-l-a ul li .txt-box{
    width: 65px;
    height: 24px;
    display: block;
    font-size: 16px;
    float:left;
    line-height: 24px;
}

.clearfloat:after{
    display:block;
    clear:both;
    content:"";
    visibility:hidden;
    height:0
}
.clearfloat{
    zoom:1
}

.icon-a{
    background: url("${_PATH}/res/images/ceo/icon-a-s.png") no-repeat;
}
.icon-b-s{
    background: url("${_PATH}/res/images/ceo/icon-b.png") no-repeat;
}
.icon-c-s {
    background: url("${_PATH}/res/images/ceo/icon-c.png") no-repeat;
}
.icon-d-s {
    background: url("${_PATH}/res/images/ceo/icon-d.png") no-repeat;
}
.icon-e-s {
    background: url("${_PATH}/res/images/ceo/icon-e.png") no-repeat;
}
.icon-f-s {
    background: url("${_PATH}/res/images/ceo/icon-f.png") no-repeat;
}
.icon-g-s {
    background: url("${_PATH}/res/images/ceo/icon-g.png") no-repeat;
}


/*ceoInfo-l-b*/


.num-txt{
    font-size: 14px;
    color: #4a4a4a;
}
.num-num{
    font-size: 14px;
    color:#2cb7c9;
    margin-left: 10px;
}

.progess-num {
    padding-top: 10px;
    padding-left: 8px;
}
		
.circle-box.ceoInfo-l-a {
    height: 372px;
}		
		
.ceoInfo-l-a-content {
    padding-top: 33px;
}		
		
.addbox {
    cursor: pointer;
}		
		
.c-box-logo-content {
    position: relative;
}		
		
.mark-icon {
    width: 44px;
    height: 18px;
    border-radius: 12px;
    background: #fea600;
    font-size: 12px;
    color: #fff;
    text-align: center;
    line-height: 18px;
    position: absolute;
    right: 25px;
    top: 2px;
}

    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
        
        
        <div class="c-box">
    <div class="c-box-logo">
    	<div class="c-box-logo-content">
    	<div class="mark-icon">项目方</div>
    	<div class="badge-icon"></div>
        <div class="box-logo-a"></div>
        <div class="box-logo-b clearfloat">
            <span class="name">Shirley</span><span class="name-logo"></span>
        </div>
        <div class="box-logo-c">
            <span class="c-title">阿里巴巴运营总监</span><span class="c-logo"></span><span class="c-city">杭州</span>
        </div>
        </div>
    </div>
    <div class="c-box-a"></div>
    <div class="c-box-b">
        <div class="box-b-content">
            <div class="b-content-a">
                <div class="line-box clearfloat marginbottom50">
                    <span class="line"></span>
                    <span class="line-btn">创业属性</span>
                    <span class="line"></span>
                </div>

                <div class="box-txt">
                    <div class="clearfloat com">
                        <span class="cy-l">腾讯科技广州分公司</span>
                        <span class="cy-c">51人-100人</span>
                        <div class="cy-r">
                            <span class="pen"></span>
                            <span class="editor">编辑</span>
                        </div>
                    </div>
                    <div class="director">运营总监</div>
                    <div class="dottedLine"></div>
                    <div class="editContent"></div>
                </div>

            </div>
            <div class="b-content-b marginbottom40">
                <div class="line-box clearfloat marginbottom50">
                    <span class="line"></span>
                    <span class="line-btn">征信信息</span>
                    <span class="line"></span>
                </div>
                <div class="box-txt">
                    <div class="margintop50"><span class="marginright20">身份证号</span><span class="marginright20">3210*********2690</span><span>*胜利</span></div>
                    <div class="margintop27">
                        <p>证件上传</p>
                        <ul class="margintop15">
                            <li><div class="idbox"><span class="idlogo"></span><span class="idtxt">身份证正面</span></div></li>
                            <li><div class="idbox"><span class="idlogo"></span><span class="idtxt">营业执照正面</span></div></li>
                            <li><div class="idbox"><span class="idlogo"></span><span class="idtxt">工作正面</span></div></li>
                            <li><div class="idbox"><span class="idlogo"></span><span class="idtxt">名片正面</span></div></li>
                        </ul>
                    </div>
                </div>

            </div>
            <div class="b-content-c">
                <div class="line-box clearfloat marginbottom50">
                    <span class="line"></span>
                    <span class="line-btn">账号设置</span>
                    <span class="line"></span>
                </div>
                <div class="box-txt">
                    <div class="marginbottom20 margintop50"><span class="marginright20">支付宝账号</span><span>157***2690</span></div>
                    <div class="marginbottom20"><span class="marginright20">收款银行卡账号绑定</span><span class="marginright15">中国建设银行</span><span>622871******789008479</span></div>
                    <div class="marginbottom20"><span class="marginright20">邮箱</span><span>18601651637@163.com</span></div>
                    <div class="marginbottom20"><span class="marginright20">手机号</span><span>186****2690</span></div>
                    <div><span class="marginright20">密码设置</span><span>******</span></div>
                </div>

            </div>
        </div>
    </div>
</div>
   
       

    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>