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
            margin-left:20px;
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
            line-height: 30px;
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


    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
    <div class="opt-and-search">
        <div class="add">
            <span class="add-title">您推荐的项目进展情况</span>
        </div>
    </div>

    <div class="split-line"></div>

    <div class="opt-and-search" style="margin-top:24px;">
        <div class="search-box table-search">

            <input class="form-control" type="text" id="searchWord" placeholder="项目/联系人/手机号" maxlength="20" style="width:234px;">

            <button type="button" class="search-btn">
                <img src="/res/images/search_icon.png" class="search-icon">&nbsp;&nbsp;搜索
            </button>
        </div>

    </div>

    <#--表格专区-->
    <div class="search-status">
        <table class="person-table table" id="table1">
            <thead>
            <tr>
                <th>项目名称</th>
                <th style="border-right:1px solid #D9D9D9;">联系人</th>
                <th>项目预算</th>
                <th>项目所处阶段</th>
                <th style="border-right:1px solid #D9D9D9;">费用托管情况</th>
                <th>已验收费用合计</th>
                <th>我的受益</th>
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