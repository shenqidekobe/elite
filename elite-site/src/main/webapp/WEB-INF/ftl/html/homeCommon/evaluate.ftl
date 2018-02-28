<@extend name="layout">
    <@block name="cs">
    <style type="text/css">

        .content{
            width:1000px;
            margin:0 auto;
            padding-bottom: 60px;
        }

        .flag{
            font-size: 14px;
            color:#5C5C5C;
            padding-top:20px;
            margin-bottom: 20px;
        }

        .obj-box{
            width:100%;
            height:173px;
            background-color: #FAFAFA;
            border:1px solid #E6E6E6;
        }
        .obj{
            width:100%;
            height:115px;
            border-bottom:1px solid #E6E6E6;
        }

        .project-logo{
            width:60px;
            height:60px;
            line-height: 60px;
            text-align: center;
            background-color:#EE706B;
            font-size: 24px;
            color:white;
            float:left;
        }

        .project-index{
            float:left;
            margin-left:20px;
            color:#4A4A4A;
            padding-top:5px;
        }

        .current-status{
            font-size: 14px;
            color:#2CB7C9;
            float:right;
            padding-right:80px;
            padding-top:16px;
        }

        .stars{
            width:100%;
            height:55px;
            line-height: 55px;
            padding-left:20px;
            font-size: 14px;
        }

        .required{
            color:#FEA600!important;
        }

        .star-icon{
            width:24px;
            height:24px;
            margin-right:3px;
            cursor: pointer;
        }

        .head-logo{
            width: 60px;
            height: 60px;
            background:url("/res/images/test.jpg") no-repeat;
            background-size: 60px 60px;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            float:left;
        }

        .comment{
            width:100%;
            height: 220px;
            background-color: #F5FBFC;
            border:1px solid #E6E6E6;
        }
        .release-btn{
            width:160px;
            height:40px;
            background-color: #FEA600;
            border:none;
            border-radius: 20px;
            font-size: 18px;
            color:white;
            margin-top:20px;
        }

        .comment-lab{
            float:left;
            margin-left:60px;
            padding-top:30px;
            width:428px;
            height:120px;
        }

        .lab{
            padding:1px 8px;
            border:1px solid #E6E6E6;
            border-radius: 10px;
            background-color: transparent;
            font-size: 12px;
            color:#9B9B9B;
            cursor: default;
            float:left;
            margin-right:20px;
            margin-bottom: 20px;
        }


    </style>
    </@block>
    <@block name="body">

    <#--头部-->
        <@accounthead opt=""/>


    <div class="content">
        <div class="flag">
            <span>个人主页 &gt; 我的项目 &gt; 堆糖网 项目详情 &gt; <span style="color:#2CB7C9;">评价</span></span>
        </div>

        <p style="font-size: 14px;color:#9B9B9B;">对成果进行评价</p>
        <div class="obj-box">
            <div class="obj">
                <div style="padding:26px;">
                    <div class="project-logo">堆</div>
                    <div class="project-index">
                        <p>堆糖网</p>
                        <span style="font-size: 14px;">评价阶段：需求梳理阶段</span>
                    </div>
                    <div class="current-status">待评价</div>
                </div>
            </div>
            <div class="stars">
                <span class="required">*</span>&nbsp;成果评分:&nbsp;&nbsp;
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
            </div>
        </div>
        <div class="obj-box" style="border-top:none;">
            <div class="obj">
                <div style="padding:26px;">
                    <div class="head-logo"></div>
                    <div class="project-index">
                        <p>Shirley</p>
                        <span style="font-size: 14px;">项目经理</span>
                    </div>
                    <div class="current-status">待评价</div>
                </div>
            </div>
            <div class="stars">
                <span class="required">*</span>&nbsp;服务满意度评分:&nbsp;&nbsp;
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
            </div>
        </div>

        <br>

        <div class="obj-box">
            <div class="obj">
                <div style="padding:26px;">
                    <div class="project-logo">堆</div>
                    <div class="project-index">
                        <p>堆糖网</p>
                        <span style="font-size: 14px;">评价阶段：需求梳理阶段</span>
                    </div>
                    <div class="current-status">待评价</div>
                </div>
            </div>
            <div class="stars">
                <span class="required">*</span>&nbsp;成果评分:&nbsp;&nbsp;
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
            </div>
        </div>
        <div class="comment" style="border-top:none;">
            <div style="padding:10px 20px;">
                <div style="float:left;">
                    <textarea class="form-control" rows="6" placeholder="Hi~等你好久咯，快快给我的成果写个漂亮的评价吧!" style="width:468px;"></textarea>
                    <button type="button" class="release-btn">发表评论</button>
                </div>
                <div class="comment-lab">
                    <button type="button" class="lab">完美 ~</button>
                    <button type="button" class="lab">和预期相符</button>
                    <button type="button" class="lab">你的设计让我如痴如醉</button>
                    <button type="button" class="lab">果然是设计大师</button>
                </div>
            </div>
        </div>
        <div class="obj-box" style="border-top:none;">
            <div class="obj">
                <div style="padding:26px;">
                    <div class="head-logo"></div>
                    <div class="project-index">
                        <p>Shirley</p>
                        <span style="font-size: 14px;">项目经理</span>
                    </div>
                    <div class="current-status">待评价</div>
                </div>
            </div>
            <div class="stars">
                <span class="required">*</span>&nbsp;服务满意度评分:&nbsp;&nbsp;
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
                <img src="/res/images/star_dark_icon.png" class="star-icon">
            </div>
        </div>

    </div>


    </@block>
    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/index.js')}"></script>
    </@block>
</@extend>