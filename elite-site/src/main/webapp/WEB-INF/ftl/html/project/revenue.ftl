<@extend name="personLayout">
    <@block name="cs">
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

        .revenue-report{
            width:442px;
            color:#9B9B9B;
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

        /****************************分页样式*************************/


    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
    <div class="opt-and-search">
        <div class="add">
            <span class="add-title">收益中心</span>
        </div>
    </div>

    <div class="split-line"></div>

    <div class="opt-and-search" style="margin-top:24px;">
        <div class="search-box table-search">
            <div class="revenue-report">
                <p>已有<span class="keyword">5</span>个项目共计托管费用<span class="keyword">230000</span>元，其中已验收<span class="keyword">130000</span>元，扣除平台成本后，您的可提现收益<span class="keyword">80000</span>元</p>
            </div>
            <button type="button" class="search-btn">提现</button>
        </div>

        <div class="revenue-detail">
            <ul>
                <li>
                    <div class="revenue-index">
                        <p class="index-title">本月新签约项目</p>
                        <p class="keyword">111</p>
                    </div>
                </li>
                <li>
                    <div class="revenue-index">
                        <p class="index-title">本月已托管费用总计</p>
                        <p class="keyword">111111元 &nbsp;<a href="#" target="_blank">详情</a></p>
                    </div>
                </li>

                <li>
                    <div class="revenue-index"  style="padding-top:24px;">
                        <p>我的本月收益</p>
                        <p style="margin-top:19px;" class="keyword">111&nbsp;<a href="#" target="_blank">详情</a></p>
                    </div>
                </li>
                <li>
                    <div class="revenue-index" style="padding-top:24px;">
                        <p>我的本月收益</p>
                        <p style="margin-top:19px;" class="keyword">111</p>
                    </div>
                </li>
                <li>
                    <div class="revenue-index" style="padding-top:24px;">
                        <p>我的累计收益</p>
                        <p style="margin-top:19px;" class="keyword">111</p>
                    </div>
                </li>
                <li style="border-right: none;">
                    <div class="revenue-index" style="padding-top:24px;">
                        <p>当前可提现</p>
                        <p style="margin-top:19px;" class="keyword">111</p>
                    </div>
                </li>
            </ul>
        </div>

    </div>

    <#--表格专区-->
    <div class="search-status">
        <table class="person-table table" id="table1">
            <thead>
            <tr>
                <th>项目名</th>
                <th>联系人</th>
                <th style="border-right:1px solid #D9D9D9;">手机号</th>
                <th>项目阶段</th>
                <th style="border-right:1px solid #D9D9D9;">项目费用总计</th>
                <th>已托管费用总计</th>
                <th>已验收阶段费用</th>
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