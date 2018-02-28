<@extend name="personLayout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/earnings.css">
    
    <style type="text/css">

        #personRecommend{
            min-height: 450px!important;
        }
        .opt-and-search{
            width:100%;
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
            width:94px;
            height:36px;
            background-color: #FEA600;
            border:none;
            border-radius: 18px;
            color:white;
            font-size: 14px;
            margin-left:20px;
            vertical-align: top;
        }

        .revenue-report {
    		width: 503px;
    		height: 32px;
    		color: #9B9B9B;
    		line-height: 32px;
    		border-radius: 6px;
    		background: #F0EEEC;
		}

        .keyword{
            color:#000;
        }

        .revenue-detail{
            width:100%;
            height:120px;
            background-color: #F5FBFC;
            border-radius: 4px;
        }

        .revenue-detail ul{
            padding:20px;
            /*padding-top:20px;*/
            /*padding-left:20px;*/
        }

        .revenue-detail ul li{
            float:left;
            width:114px;
            height:80px;
            text-align: center;
            border-right:1px solid #D9D9D9;
        }

        .revenue-index{
            font-size: 14px;
            color:#9B9B9B;
        }

        .revenue-index a{
            font-size: 12px;
            /*color:#000;*/
        }

        .index-title{
            padding-top:14px;
            padding-right:14px;
            padding-left:14px;
        }


        .search-status{
            margin-top:30px;
            width:100%;
        }

        .person-table thead tr th{
            background-color: #F5F5F5;
            color:#9B9B9B;
            font-weight: normal;
            border-bottom: none;
            line-height: 30px;
            font-size: 12px;
        }

        .person-table tbody tr td{
            border-top:none;
            height:40px;
            line-height: 40px;
            font-size: 14px;
            color:#4A4A4A;
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
        
         .color4a{
            color: #4A90E2;
        }
        
        .marginright30{
            margin-right: 30px;
        }
        
        .report-icon{
    		width: 20px;
    		height: 20px;
    		display: block;
    		margin-top: 6px;
            margin-left: 15px;
    		background: url("${_PATH}/res/images/report-icon.png");
		}
		
		.fl{
			float: left;
		}
        
        .marginleft12 {
   			 margin-left: 12px;
		}
		
		.color2c{
    		color:#2CB7C9;
		}

        /****************************分页样式*************************/


    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
    <div class="opt-and-search">
        <div class="add">
            <span class="add-title marginright30">收益中心</span><a href="#"  class="color4a">收益规则帮助</a>
        </div>
    </div>

    <div class="split-line"></div>

    <div class="opt-and-search" style="margin-top:24px;">
        <div class="search-box table-search">
            <div class="revenue-report">
               <div class="report-icon fl"></div>
               <div class="fl"><span class="marginleft12">已有</span><span class="keyword color2c">230</span>名精英获得<span class="keyword color2c">2300000</span>元酬金，其中已提现<span class="keyword color2c">13000</span>元</div>
            </div>
            
            <div class="money-box">
    			<div class="earn-box">
        			<div class="earn-text">我的累计收益</div>
        			<div class="earn-number">40000.0元</div>
    			</div>

    			<div class="withdrawal-box">
        			<div class="withdrawal-number">
            				<span class="number-t">当前可提现</span>
            				<span class="number-n">10000.0元</span>
        			</div>

        			<div class="withdrawal-btn">
           			 提现
        			</div>
    </div>
</div>
            
        </div>

        

    </div>

    <#--表格专区-->
    <div class="search-status">
        <table class="person-table table" id="table1">
            <thead>
            <tr>
                <th style="border-right:1px solid #D9D9D9;">用户名</th>
                <th>进行中的任务/项目</th>
                <th style="border-right:1px solid #D9D9D9;">进行中的任务/项目<span style="font-size: 12px;">(金额)</span></th>
                <th>已完成的任务/项目</th>
                <th style="border-right:1px solid #D9D9D9;">已完成的任务/项目<span style="font-size: 12px;">(金额)</span></th>
                <th>本月已提现金额</th>
                <th>我的收益</th>
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

    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>