<@extend name="personLayout">
    <@block name="cs">
    <link rel="stylesheet" type="text/css" href="${static('/styles/datePicker.css')}"/>
    <style type="text/css">

        #personRecommend{
            min-height: 450px!important;
        }
        .opt-and-search{
            width:100%;
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
            float:right;
        }

        .search-icon{
            width:20px;
            height:20px;
        }

        .active-order{
            font-size: 12px;
            color:#9B9B9B;
            margin-top:20px;
        }

        .active-order span{
            cursor: pointer;
        }

        .table-triangle{
            position: absolute;
            bottom: 2px;
            right:3px;
            /*width:22px;*/
            /*height:22px;*/
            cursor: pointer;
        }
        .table-trigger {
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

        /****************************时间控件样式*************************/
        .date-select{
            /*display: block;*/
            width:176px;
            height:34px;
            position: relative;
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
        .active-status {
    		olor: #FEA600;
    		border-bottom: 3px solid #fea600;
		}

        .person-table thead tr th{
            background-color: #F5F5F5;
            color:#9B9B9B;
            font-weight: normal;
            border-bottom: none;
            line-height: 30px;
            text-align: center;
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
            width:76px;
            height:22px;
            line-height:18px;
            border:1px solid #95D6E2;
            background-color: white;
            color:#2CB7C9;
            border-radius: 10px;
            vertical-align: middle;
            position: relative;
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
        
        .select{
            color: #2CB7C9;
        }
        
         .marginright14{
            margin-right: 14px;
        }
        
        .marginleft14{
            margin-left: 14px;
        }
        
       .search-box li {
    		width: 140px;
    		height: 40px;
    		float: left;
    		text-align: center;
		}
		
		.page {
    		width: 240px;
    		margin: auto;
		}
		
		.search-box {
    		width: 730px;
    		height: 29px
		}
		
		.search-line {
    		border-bottom: 1px solid #E6E6E6;
            margin-bottom: 30px;
         }

    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
    <div class="opt-and-search">
        <div class="add">
            <span class="add-title">精英活跃度管理</span>
        </div>
    </div>

    <div class="split-line"></div>

    <div class="opt-and-search" style="margin-top:24px;">
        <div class="search-box table-search">
            <span>日期选择:</span>
            <span class="date-select">
                <img src="/res/images/date_icon.png" class="date-icon">
                <input type="text" class="form-control" id="receiptdate"  readonly  placeholder="选择开始时间" style="background-color: white;"/>
            </span>

            <span>-</span>
             <span class="date-select">
                <img src="/res/images/date_icon.png" class="date-icon">
                <input type="text" class="form-control" id="receiptdate"  readonly  placeholder="选择开始时间" style="background-color: white;"/>
            </span>

            <input class="form-control" type="text" id="searchWord" placeholder="搜索用户名/姓名/手机" maxlength="20" style="width:170px;">

            <button type="button" class="search-btn">
                <img src="/res/images/search_icon.png" class="search-icon">&nbsp;&nbsp;搜索
            </button>
        </div>

        <div class="active-order">
            <span class="select">活跃度排名倒序</span><span class="marginleft14 marginright14">|</span>
            <span>活跃度排名正序</span>
        </div>
    </div>

    <#--表格专区-->
    <div class="search-status">
        <ul class="search-box">
    		<li class="all-login active-status span_login">全部</li>
    		<li class="no-login span_login">注册后从未登录</li>
    		<li class="no-login-7days other-select span_login">近七日未登录</li>
    		<li class="no-deal other-select span_login">注册后从未接单</li>
    		<li class="no-deal-7days other-select span_login">近14日未接单且无进行中项目</li>
		</ul>
		
		
		<div class="search-line"></div>
       

        <table class="person-table table" id="table1">
            <thead>
            <tr>
                <th>用户名</th>
                <th style="border-right:1px solid #D9D9D9;">手机号</th>
                <th>状态</th>
                <th >最近一次登录</th>
                <th style="border-right:1px solid #D9D9D9;">最近一次未接单</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>aaaa</td>
                <td>aaaa</td>
                <td>aaaa</td>
                <td>aaaa</td>
                <td>aaaa</td>
                
                <td>
                    <button type="button" class="table-btn">
                        <div class="table-triangle">
                            <div class="table-trigger"></div>
                        </div>发邮件
                    </button>
                </td>
            </tr>

            <tr>
                <td>aaaa</td>
                <td>aaaa</td>
                <td>aaaa</td>
                <td>aaaa</td>
                <td>aaaa</td>
                <td>
                    <button type="button" class="table-btn">
                        <div class="table-triangle">
                            <div class="table-trigger"></div>
                        </div>发邮件
                    </button>
                </td>
            </tr>

            </tbody>
        </table>

    <#--分页组件(试用)-->
        <div class="page">
            <span class="prev">&lt;</span>
            <div class="page-circle">1</div>
            <div class="page-circle active-page">2</div>
            <div class="page-circle">3</div>
            <div class="page-circle">4</div>
            <div class="page-circle">5</div>
            <span class="next">&gt;</span>
            <input type="text" class="goto-page">
            <div class="go-circle">go</div>
        </div>

    </div>

    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>