<@extend name="inviteRegisterLayout">
    <@block name="cs">
    <link rel="stylesheet" type="text/css" href="${static('/styles/datePicker.css')}"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/leftSide/leftSide.css">
    <style type="text/css">


        .opt-and-search{
            width:731px;
        }

        .add-btn{
            width:240px;
            height:40px;
            line-height: 40px;
            cursor: pointer;
            border:1px solid #FEA600;
            border-radius: 4px;
            color:#FEA600;
            float:right;
        }

        .add-title{
            font-size: 18px;
            line-height: 54px;
        }

        .add-btn img{
            width:24px;
            height:24px;
            margin-left:20px;
            vertical-align: middle;
            margin-bottom: 3px;
        }

        .split-line{
            width:100%;
            border-bottom: 1px solid #E6E6E6;
        }


        /*模态框样式*/
        .modal-body{
            background-image:url('/res/images/pic_shadow3.png');
        }

        .close-icon{
            width:24px;
            height:24px;
            float:right;
            margin-top:6px;
            margin-right:6px;
            cursor: pointer;
        }

        #addPersonForm{
            padding-top:30px;
        }

        #addPersonForm label{
            color:white;
        }

        .hr-line2{
            width:80%;
            border-bottom: 1px dashed #CCCCCC;
            margin-left:48px;
            margin-top:30px;
            margin-bottom: 20px;
        }

        #addPersonForm .btn-group{
            display: block;
        }
        #addPersonForm .btn,.btn-default{
            border-color:#2CB7C9;
            outline: none;
        }

        .active-title{
            background-color: #2CB7C9!important;
            color:white!important;
            border:1px solid #2CB7C9!important;
        }


        .birth-box{
            position: relative;
        }

        .year-select{
            position: absolute;
            top:33px;
            left:0px;
            z-index: 100;
            border:1px solid #2CB7C9;

        }

        .year-select{
            display: none;
            height:266px;
            overflow: auto;
        }

        .year-select li{
            padding:0 16px;
            background-color: white;

        }

        .year-select li:hover{
            background-color: #2CB7C9;
            color:white;
            cursor: pointer;
        }
        .birth-year{
            position: absolute;
            top:7px;
            left:16px;
        }


        .modal-triangle{
            position: absolute;
            bottom: 6px;
            left:58px;
            cursor: pointer;
        }
        .modal-trigger {
            display: inline-block;
            height: 0;
            width: 0;
            overflow: hidden;
            border-style: solid;
            margin-left: 4px;
            margin-right: 4px;
            position: relative;
            top:0;
            border-width:6px 6px 0;
            border-color:#D8D8D8 transparent transparent;
            -webkit-transition:all .4s ease 0s;
            -moz-transition:all .4s ease 0s;
            -o-transition:all .4s ease 0s;
            transition:all .4s ease 0s;
        }

        .trigger-inverse{
            -webkit-transform: rotate(180deg);
            -moz-transform: rotate(180deg);
            -ms-transform: rotate(180deg);
            transform: rotate(180deg);
            animation-fill-mode: forwards;
        }

        .location{
            position: absolute;
            top:8px;
            left:12px;
            width:auto;
            height:20px;
        }

        .upload-resume{
            width:100%;
            height:34px;
            line-height: 34px;
            background-color: white;
            border-radius: 4px;
            text-align: center;
            cursor: pointer;
        }

        .modal-opt{
            text-align: center;
            margin-top:10px;
        }

        .opt-btn-ok{
            width:126px;
            height:40px;
            background-color: #FEA600;
            border:1px solid #FEA600;
            border-radius: 20px;
            color:white;
        }

        .opt-btn-cancel{
            width:126px;
            height:40px;
            background-color: transparent;
            border:1px solid #CCCCCC;
            border-radius: 20px;
            color:white;
        }

        .error-tip{
            text-align: center;
            height:24px;
            color:red;
        }

       .your-code {
    		font-size: 14px;
    		color: #4A4A4A;
    		padding-top: 10px;
    		width: 200px;
    		height: 160px;
    		background: #F5FBFC;
    		border: 1px solid #E6E6E6;
    		box-shadow: 0px 2px 6px 0px rgba(0,0,0,0.20);
    		border-radius: 6px;
    		
		}

        .copy-btn{
            width:155px;
            height:40px;
            background-color: #7DD2E1;
            border:none;
            border-radius: 4px;
            color:white;
            float:right;
        }

        .invite-code-input{
            width:200px;
            background-color: #F2F2F2;
            text-align: center;
            float:left;
            margin-top:7px;
        }

        .table-search >*{
            display: inline-block;
        }

        .search-btn{
            width:120px;
            height:36px;
            background-color: #FEA600;
            border:none;
            border-radius: 18px;
            color:white;
            font-size: 14px;
            margin-left: 20px;
        }

        .search-icon{
            width:20px;
            height:20px;
        }

     /****************************时间控件样式*************************/
        .date-select{
            /*display: block;*/
            width:176px;
            height:34px;
            position: relative;
            margin-left:20px;
            cursor: pointer;
        }

        .date-icon{
            width:20px;
            height:20px;
            position: absolute;
            top:7px;
            right:16px;
        }


    /****************************时间控件样式*************************/

        .search-status{
            margin-top:30px;
            width:100%;
        }
        .active-status{
            color:#FEA600;
        }

        .person-table thead tr th{
            background-color: #F5F5F5;
            color:#9B9B9B;
            font-weight: normal;
            border-bottom: none;
            height: 19px;
            padding-top: 10px;
    		padding-bottom: 10px;
        }

        .person-table tbody tr td{
           border-top:none;
            height:40px;
            line-height: 40px;
            font-size: 14px;
            color:#4A4A4A;
        }

        .active-line{
            position: absolute;
            top:0;
            left:0;
            width:136px;
            border-bottom: 2px solid #FEA600;
        }

        .other-select{
            cursor: pointer;
        }

        .table-btn{
            width:60px;
            height:22px;
            line-height:18px;
            border:1px solid #9B9B9B;
            color:#4A4A4A;
            border-radius: 10px;
            vertical-align: middle;
        }
        /****************************分页样式*************************/

        .page >*{
            display: inline-block;
        }
        .prev{
            color:#CCCCCC;
        }

        .next{
            color:#CCCCCC;
        }

        .page-circle{
            width: 20px;
            height: 20px;
            line-height: 20px;
            border: 1px solid #CCCCCC;
            text-align: center;
            color:#CCCCCC;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            font-size: 12px;
        }

        .active-page{
            border:1px solid #4A4A4A;
            color:#4A4A4A;
        }

        .goto-page{
            width:40px;
            height:20px;
            border:1px solid #9B9B9B;
            border-radius: 4px;
            outline: none;
            font-size: 12px;
            text-align: center;
        }
        .go-circle{
            width: 20px;
            height: 20px;
            /*line-height: 20px;*/
            border: 1px solid #979797;
            text-align: center;
            background-color: #979797;
            color:white;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            font-size: 12px;
        }

        /****************************分页样式*************************/

        .table-head{
            width: 40px;
            height: 40px;
            background:url("/res/images/test.jpg") no-repeat;
            background-size: 40px 40px;
            border: 1px solid #979797;
            -moz-border-radius: 40px;
            -webkit-border-radius: 40px;
            border-radius: 40px;
        }
        
	    /****************************新版样式*************************/    
	    
	    body, div, dl, dt, dd, p, th, td, h1, h2, h3, h4, h5, h6, ol, ul, li, form, button, input, select, label, textarea {
			    margin: 0px;
			    padding: 0px;
			    font-family: "Helvetica Neue",Helvetica,Arial,"Hiragino Sans GB","华文细黑","STHeiti","微软雅黑","Microsoft YaHei",SimHei,sans-serif !important;
			    font-size: 14px;
			    color: #333333;
			}
	    
	    
	       a.copy-code {
			    width: 150px;
			    height: 40px;
			    border: 1px solid #95D6E2;
			    border-radius: 6px;
			    display: block;
			    line-height: 40px;
			    text-align: center;
			    color: #95d6e2;
			    margin-left: 20px;
			}
	
	.copy-result {
		    font-size: 14px;
		    color: #262626;
		    margin-left: 20px;
		    width: 182px;
		    height: 50px;
		    word-break: break-word;
		    overflow-y: auto;
		}

	.code-text {
	    margin-left: 20px;
	    margin-top: 10px;
	    margin-bottom: 10px;
	}
	
	.add-btn-box {
	    width: 200px;
	    height: 160px;
	    border-radius: 6px;
	    border: 1px solid #fea600;
	}

	span.add-icon {
	    width: 30px;
	    height: 30px;
	    display: block;
	    background:url("/res/images/ceo/add-icon.png") no-repeat;
	    margin: 15px auto;
	    cursor: pointer;
	}

	span.add-icon-b {
	    width: 20px;
	    height: 20px;
	    background:url("/res/images/ceo/add-icon-b.png") no-repeat;
	    display: block;
	    float: left;
	}

	.add-txt-b p {
	    font-size: 12px;
	    color: #9b9b9b;
	}

	.add-txt {
	    width: 162px;
	    height: 22px;
	    font-size: 16px;
	    color: #fea600;
	    margin: 15px auto;
	}

	.add-txt-b {
	    margin-left: 8px;
	}

	.fl{
		float:left;
	}

	.code-a {
	    margin-right: 20px;
	}
	.code-b {
	    margin-right: 40px;
	}



	th {
	    text-align: center;
	}

	.search-box-a {
	    width: 731px;
	    height: 231px;
	}

	.search-box-a-content {
	    margin-top: 30px;
	}
	a.rule-btn {
	    color: #4a90e2;
	    cursor: pointer;
	}

/*左侧样式修改*/

	.invite-l-box {
	    height: 198px;
	}

	.invite-l-box ul li {
	    height: 39px;
	    line-height: 39px;
	    width: 170px;
	    margin-left: 70px;
	    cursor: pointer;
	}
	.clearfloat:after {
	    display: block;
	    clear: both;
	    content: "";
	    visibility: hidden;
	    height: 0;
	}
	.clearfloat {
	    zoom: 1;
	}
	.invite-l-box .select {
	    height: 39px;
	    line-height: 39px;
	    width: 170px;
	    border-right: 3px solid #2cb7c9;
	    color: #2cb7c9;
	}
	.clearfloat {
	    zoom: 1;
	}

	li {
	    list-style: none;
	    float: left;
	}



	.li-box {
	    height: 24px;
	    width: 105px;
	    padding-top: 7.5px;
	}

	.icon-a {
	    background: url("/res/images/ceo/icon-a-s.png") no-repeat;
	}

	.txt-box {
	    width: 65px;
	    height: 24px;
	    display: block;
	    font-size: 16px;
	    float: left;
	    line-height: 24px;
	}

	.icon-box {
	    width: 22px;
	    height: 24px;
	    display: block;
	    float: left;
	    margin-right: 15px;
	}
	.invite-a-s {
	    background: url("/res/images/layout/invite-a-s.png") no-repeat;
	}
	.invite-b {
	    background: url("/res/images/layout/invite-b.png") no-repeat;
	}
	.invite-c {
	    background: url("/res/images/layout/invite-c.png") no-repeat;
	}
	.invite-d {
	    background: url("/res/images/layout/invite-d.png") no-repeat;
	}
		
		
	.view {
		padding:5px 20px;
		border:1px solid #999;
		border-radius:28px;
		position:relative;
	}	
	.view .triangle {
		position: absolute;
		top:8px;
		right:5px
	}

/*精英报名邀请弹窗*/
.boxt {
	width:100%;
	padding:10px 0;
}
.contean {
	width:620px;
	padding:50px;
	margin:0 auto;
	border-radius:20px;
	background:#999;
	position:relative;
}
.contean h1 {
	text-align:center;
	font-size:20px;
	padding-bottom:15px;
	border-bottom:2px solid #95d6e2;
}
.contean label {
	width:17%;
	line-height: 50px;
	margin-right:19px;
	text-align:right;
	display: inline-block;
	float:left;
}
.contean form {
	margin:40px 0;
}
.contean form .for {
	margin:15px 0;
	color:#fff;
	position:relative;
}
 .detu {
	display:block;
	width:20px;
	height:27px;
	position: absolute;
	top:11px;
	left:132px;
	background:url(/res/images/location_icon.png) no-repeat center;
}
 .for-btn {
	padding-bottom:30px;
	border-bottom:1px dashed #fff;
}

/*倒三角*/
.triangle {
	display:block;
	width:0px;
	height:0px;
	border-top:7px solid #d8d8d8;
	border-left:7px solid transparent;
	border-right:7px solid transparent;
	position: absolute;
	top:23px;
}
.contean .for-btn-triangel {
	left:172px;
}
.contean .for-btn-triangela {
	left:253px;
}
.contean .for-btn-triangelb {
	position: absolute;
	top:23px;
	left:435px;
}
.contean form .for-sbt {
	width:410px;
	margin:35px 0 35px 113px;
	overflow:hidden;
}
.contean .for-sbt button {
	width:180px;
	height:60px;
	float:left;
	border:none;
	color:#fff;
	border:1px solid #fff;
	border-radius:100px;
	background:none;
	font-size:18px;
}
.contean .for-sbt button:hover {
	background:#fea600;
	border:none;
}
.contean .for-sbt input[type="reset"] {
	margin-left: 50px
}
.contean .tab,
.contean form .for input {
	width:360px;
	height:50px;
	border-radius:8px;
	outline: none;
	text-indent:.5cm;
	border:1px solid #95d6e2;
}
 .tab {
	border:none;
	list-style:none;
	border-radius:8px;
	border:1px solid #95d6e2;
	overflow: hidden;
}

 .tab li {
	width:33.33%;
	line-height: 50px;
	float:left;
	text-align:center;
	font-size:14px;
	background:#fff;
	color:#333;
}
.contean .td-border {
	border-right:1px solid #95d6e2;
}
.contean .tab li:hover {
	background:#95d6e2;
	color:#fff;
}
.contean .cop {
	display: block;
	width:30px;
	height:30px;
	background:url(/res/images/copy.png) no-repeat center;
	position: absolute;
	top:20px;
	right:25px;
}



.table tr td form select {
	font-size:16px;
	border-radius:37px;
	padding:5px 15px;
	color:#2cb7c9;
	outline: none;
	border:1px solid #2cb7c9;
}
.table tr td form select > option {
	font-size:16px;
	border-radius:8px;
	border:none;
	border:1px solid yellow;
}
/*精英邀请弹窗*/
    </style>
    </@block>
        <#--右边的操作区-->
        <@block name="rightContent">
            <div class="opt-and-search">
                <div class="add">
                    <span class="add-title">精英注册邀请</span>
                </div>
            </div>

            <div class="split-line" style="margin:0 0;"></div>

            <div class="opt-and-search  search-box-a">
            	<div class="search-box-a-content">
                <div class="your-code code-a fl">
                	<div class="code-text">您的邀请码：</div>
					<div class="copy-result">20JV755IJLDjkJH</div>
					<a class="copy-code" href="#">复制邀请码</a>
                </div>
                

                <div class="your-code code-b fl" >
                	<div class="code-text">您的邀请连接</div>
					<div class="copy-result">http://www.yunyinghui.com/regster.html?20JV755IJLDjkJH</div>
					<a class="copy-code" href="#">复制邀请网址</a>
                </div>
                
                
                <div class="add-btn-box fl">
                        <span class="add-icon"></span>
                        <div class="add-txt">新增推荐精英人才备案</div>
                        <div class="add-txt-b">
                        	<p><span class="add-icon-b" ></span>  精英注册干活，您可获得永久</p>
                        	<p>推荐收益，<a class="rule-btn">邀请规则</a></p>
                        </div>
                 </div>
                
               </div> 
                </div>
                

                <div class="search-box table-search">
                	
                	<div class=""><input class="form-control" type="text" id="searchWord" placeholder="精英/手机号/角色" maxlength="11" style="width:200px;"></div>
                	<div class=""></div>
                	<div class=""></div>
                    

                    <span class="date-select">
                        <img src="/res/images/date_icon.png" class="date-icon">
                        <input type="text" class="form-control" id="receiptdate"  readonly  placeholder="选择备案时间" style="background-color: white;"/>
                    </span>
                    <button type="button" class="search-btn">
                        <img src="/res/images/search_icon.png" class="search-icon">&nbsp;&nbsp;搜索
                    </button>

                </div>
                
                
                
                 <#--表格专区-->
            <div class="search-status">
                <div style="padding-left:30px;">
                    <span class="has-record ">已备案精英 (23)</span>
                    <span class="has-register other-select active-status" style="margin-left:60px;">已注册精英  (40)</span>
                </div>
                <div  style="margin:20px 0;position: relative;">
                	<div class="active-line"></div>
                    <div class="split-line" style="margin:0 0;"></div>
                    
                </div>

                <table class="person-table table" id="table1">
                    <thead>
                    <tr>
                        <th>备案时间</th>
                        <th>姓名</th>
                        <th style="border-right:1px solid #D9D9D9;">手机号</th>
                        <th>角色定位</th>
                        <th>工作年限</th>
                        <th style="border-right:1px solid #D9D9D9;">身份证号</th>
                        <th>附件简历</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                       	<td>aaaa</td>
                        <td ><a href="#" class="view">查看</a></td>
                    </tr>
                    
                    <tr>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                       	<td>aaaa</td>
                        <td style="display:none;"><a href="#" class="view">查看</a></td>
                        <td ><a href="#" class="view">发邮件<span class="triangle"></span></a></td>
                     	
                    </tr>
                    <tr>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                       	<td>aaaa</td>
                        <td style="display:none;"><a href="#" class="view">查看</a></td>
                        <td>
                     		<form>
								<select name="cars">
								<option value="发邮件">发邮件</option>
								<option value="查看访问">查看访问</option>
								<option value="发邮件">发邮件</option>
								<option value="查看访问">查看访问</option>	
								</select>
								</form>
						</td>
                    </tr>
                    <tr>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                       	<td>aaaa</td>
                        <td ><a href="#" class="view">查看</a></td>
                    </tr>
                    <tr>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                       	<td>aaaa</td>
                        <td ><a href="#" class="view">查看</a></td>
                    </tr>

                    </tbody>
                </table>

                <table class="person-table table" id="table2" style="display: none;">
                    <thead>
                    <tr>
                        <th>头像</th>
                        <th style="border-right:1px solid #D9D9D9;">用户名</th>
                        <th>注册手机号</th>
                        <th>账号流程</th>
                        <th style="border-right:1px solid #D9D9D9;">注册日期</th>
                        <th>征信情况</th>
                        <th>目前状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <div style="padding-left:22px;">
                                <div class="table-head"></div>
                            </div>
                        </td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                    </tr>
                    <tr>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                    </tr>
                    <tr>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                    </tr>

                    </tbody>
                </table>






<!-- 精英注册邀请 弹窗开始 -->
<div class="boxt">
	<div class="contean">
		<form action="">
			<div class="for">
				<label for="">姓名</label>
				<input type="text" placeholder="请输入您的姓名">
			</div>
			<div class="for">
				<label for="">手机号</label>
				<input type="text" placeholder="请输入11位手机号">
			</div>
			<div class="for for-btn">
				<label for="">身份证号</label>
				<input type="email" placeholder="请输入身份证号">
			</div>
			<div class="for">
				<label for="">人才属性</label>
				<ul class="tab">
					<li class="td-border">工程师</li>
					<li class="td-border">设计师</li>
					<li>产品经理</li>
				</ul>
			</div>
			<div class="for ">
				<label for="">相关工作年限</label>
				<input type="email" placeholder="6年" style="width:180px">
				<span class="triangle for-btn-triangel"></span>
			</div>
			<div class="for">
				<label for="">所在城市</label>
				<span class="detu"></span>
				<ul class="tab tab-li">
					<li style="width:50%">上海</li>
					<li style="width:50%">上海市</li>
				</ul>
				<span class="triangle for-btn-triangela"></span>
				<span class="triangle for-btn-triangelb"></span>
			</div>
			<div class="for for-sbt">
				<button type="submit" value="保存" style="margin-right:50px;">保存</button> 
				<button type="reset" value="取消">取消</button> 
			</div>
		</form>
		<span class="cop"></span>
	</div>
	
</div>
<!-- 精英注册邀请 弹窗 结束 -->



                <#--分页组件(试用)-->
                <div class="page">
                    <span class="prev">&lt;</span>
                    <div class="page-circle">1</div>
                    <div class="page-circle active-page">2</div>
                    <div class="page-circle">3</div>
                    <span class="next">&gt;</span>
                    <input type="text" class="goto-page">
                    <div class="go-circle">go</div>
                </div>

            </div>
                
                
                
                
                
                
            </div>

           

            <#--新增精英模态框-->
            <div class="container">
                <div class="row">
                    <div class="modal fade" data-backdrop="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  id="addPersonDialog" style="top:50%;margin-top:-250px;">
                        <div class="modal-dialog" style="margin:0 auto;">
                <div class="modal-content" style="width:480px; height:500px;margin:0 auto;">
                    <div class="modal-body" style="padding:0;height:100%;border-radius: 4px;">
                        <#--<button type="button" class="close" style="color:white;top: -18px;right: -20px;position: absolute;outline: none;" data-dismiss="modal" aria-label="Close">-->
                            <#--<span class="glyphicon glyphicon-remove-circle closeBtn" aria-hidden="true"></span>-->
                        <#--</button>-->

                        <img src="/res/images/close_icon.png" class="close-icon" data-dismiss="modal" aria-label="Close">
                    <#--正文-->

                        <form class="row form-group form-horizontal" role="form" id="addPersonForm">

                            <div class="form-group">
                                <label class="col-xs-4 col-md-4 control-label"><span class="required">* </span> 姓名</label>
                                <div class="col-xs-6 col-md-6">
                                    <input class="form-control" type="text" id="name" name="name" placeholder="请输入姓名" maxlength="20">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-4 col-md-4 control-label"><span class="required">* </span> 手机号</label>
                                <div class="col-xs-5 col-md-6">
                                    <input class="form-control" type="text" id="phone" name="phone" placeholder="请输入11位手机号" maxlength="11">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-4 col-md-4 control-label"><span class="required">* </span> 身份证号</label>
                                <div class="col-xs-6 col-md-6">
                                    <input class="form-control" type="text" id="idnumber" name="idnumber" placeholder="请输入身份证号码" maxlength="19">
                                </div>
                            </div>

                            <div class="hr-line2"></div>

                            <div class="form-group">
                                <label class="col-xs-4 col-md-4 control-label">人才属性</label>
                                <div class="col-xs-6 col-md-6">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default" data-title="engineer">工程师</button>
                                        <button type="button" class="btn btn-default active-title" data-title="UI">设计师</button>
                                        <button type="button" class="btn btn-default" data-title="product">产品经理</button>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-4 col-md-4 control-label">相关工作年限</label>
                                <div class="col-xs-6 col-md-6">
                                    <div class="birth-box">
                                        <input class="form-control" type="text" id="birth"  style="width:110px;background-color: white;" disabled>
                                        <div class="birth-year" id="workYear">6&nbsp;年</div>
                                        <div>
                                            <ul class="year-select">
                                                <#list 1..18 as i>
                                                    <li data-type="workYear" style="padding-right:26px;">${i?c}&nbsp;年</li>
                                                </#list>
                                            </ul>

                                            <div class="modal-triangle" style="left:70px;top:5px;width:23px;">
                                                <div class="modal-trigger"></div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-xs-4 col-md-4 control-label">所在城市</label>
                                <div class="col-xs-6 col-md-6">
                                    <div class="birth-box">
                                        <input class="form-control" type="text"  style="background-color: white;" disabled>
                                        <img src="/res/images/location_icon.png" class="location">
                                        <div class="birth-year" id="province" style="left:50px;">上海</div>
                                        <div>
                                            <ul class="year-select" style="left:40px;">
                                                <li data-type="province">上海</li>
                                                <li data-type="province">北京</li>
                                                <li data-type="province">深圳</li>
                                            </ul>

                                            <div class="modal-triangle" style="left:104px;top:6px;width:22px;">
                                                <div class="modal-trigger"></div>
                                            </div>
                                        </div>

                                        <div class="birth-year" id="city" style="left:134px;top:7px;">上海市</div>
                                        <div>
                                            <ul class="year-select" style="left:106px;">
                                                <li data-type="city" style="padding:0 28px;">上海市</li>
                                                <li data-type="city" style="padding:0 28px;">上海市</li>
                                                <li data-type="city" style="padding:0 28px;">上海市</li>
                                            </ul>

                                            <div class="modal-triangle" style="left:206px;top:6px;width:22px;">
                                                <div class="modal-trigger"></div>
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-4 col-md-4 control-label"><span class="required">* </span> 上传简历</label>
                                <div class="col-xs-6 col-md-6">
                                    <div class="upload-resume">
                                        <img src="/res/images/upload_icon.png" width="20" height="20">
                                        <span style="margin-left:10px;">上传附件简历</span>
                                    </div>
                                </div>
                            </div>

                            <div class="error-tip">请输入姓名</div>

                            <div class="modal-opt">
                                <button type="button" class="opt-btn-ok" id="saveBtn">保存</button>
                                <button type="button" class="opt-btn-cancel" data-dismiss="modal" aria-label="Close">取消</button>
                            </div>
                        </form>

                    </div>
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