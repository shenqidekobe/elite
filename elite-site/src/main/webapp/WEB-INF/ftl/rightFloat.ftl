<style type="text/css">
    /*右侧浮动*/
    .right-float{
        display:none;
        position:fixed;
        bottom:5px;
        right:5px;
        z-index:1000;
    }

    .float-box{
        position: relative;
    }

    .float-icon{
        position: absolute;
        right:0;
        bottom: 0;
    }

    .float-icon img{
        width:50px;
        height:50px;
        display: block;
        margin-top:10px;
        cursor: pointer;
    }

    .phone-tip{
        position: absolute;
        right:60px;
        bottom: 0;
    }

    .phone-tip img{
        width:185px;
        height:50px;
    }

    .questions-tip{
        position: absolute;
        right:60px;
        bottom: 60px;
    }

    .questions-tip img{
        width:185px;
        height:167px;
    }

    .bird{
        position: absolute;
        right:60px;
        bottom: 216px;
    }

    .bird img{
        width:75px;
        height:71px;
    }

    .ask-tip{
        position: absolute;
        right:134px;
        bottom: 275px;
    }

    .ask-tip img{
        width:96px;
        height:44px;
    }

    .ask-me{
        position: absolute;
        top:10px;
        left:8px;
        font-size: 12px;
        color:#2CB7C9;
    }

    .telephone{
        position: absolute;
        top:15px;
        left:18px;
        color:white;
    }
    .questions{
        position: absolute;
        top:0;
        left:0;
        width:180px;
        height:167px;
        font-size: 12px;
    }

    .guess{
        color:#2CB7C9;
        padding-top:10px;
        padding-left:20px;
    }

    .questions ul{
        padding-left:24px;
        padding-right:12px;
        margin-top:6px;
    }

    .questions ul li{
        color:white;
        line-height: 20px;
    }

    .more-questions{
        color:white;
        text-align: center;
        margin-top:7px;
    }

</style>


<div class="right-float">
    <div class="float-box">
        <div class="float-icon">
            <img src="${_PATH}/res/images/service_icon.png">
            <img src="${_PATH}/res/images/telephone_icon.png">
        </div>
        <div class="ask-tip">
            <div style="position: relative;">
                <img src="${_PATH}/res/images/tip_frame.png">
                <span class="ask-me">有问题找我哦~</span>
            </div>
        </div>

        <div class="bird">
            <img src="${_PATH}/res/images/bird.png">
        </div>

        <div class="questions-tip">
            <div style="position: relative;">
                 <img src="${_PATH}/res/images/float_big.png">
                <div class="questions">
                    <div class="guess">我猜您想问:</div>
                    <ul style="list-style: inherit;">
                        <li>产品未达到预期？</li>
                        <li>发布遇到问题？</li>
                        <li>对项目有异议？</li>
                        <li>可以授权我的员工来发布任务吗？</li>
                    </ul>
                    <hr style="margin-bottom: 0;margin-top:-8px;">
                   <p class="more-questions">更多问题咨询</p>
                </div>
            </div>
        </div>

        <div class="phone-tip">
            <div style="position: relative;">
                <img src="${_PATH}/res/images/float_small.png">
                <span class="telephone">请拨 400 666 6666</span>
            </div>
        </div>
    </div>
</div>