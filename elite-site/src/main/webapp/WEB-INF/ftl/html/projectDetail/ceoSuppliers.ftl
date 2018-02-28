<@extend name="homepageLayout">
    <@block name="cs">
    <style type="text/css">
        .right-opt{
            margin-bottom: 60px;
        }
        .triangle{
            position: absolute;
            top: 16px;
            right:10px;
            cursor: pointer;
        }
        .trigger {
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

        .keyword{
            color:#FEA600;
        }

        .tab{
            margin-right:80px;
            cursor: pointer;
        }

        .period-select{
            position: relative;
            width:160px;
            display: inline-block;
            margin-right: 40px;
        }

        .right-opt .split-line{
            height:20px;
            border-right: 1px solid #E6E6E6;
            position: absolute;
            top:5px;
            left:344px;
        }

        .suppliers-box{
            width:100%;
            border:1px solid #CCCCCC;
            border-radius: 6px;
            margin-top:30px;
            font-size: 12px;
        }

        .suppliers-box table{
            margin-bottom: 0!important;
        }

        .suppliers-box table tr{
            border-top:1px solid #CCC;
        }

        .suppliers-box table tr td{
            border-top:0!important;
            vertical-align: middle;
          
        }

        .period-tag {
		    width: 86px;
		    height: 22px;
		    line-height: 22px;
		    text-align: center;
		    border: 1px solid #9B9B9B;
		    border-radius: 32px;
		    background-color: transparent;
		    color: #4a4a4a;
		    font-size: 12px;
		}

        .tag{
            width:40px;
            height:19px;
            position: absolute;
            top:3px;
            left:-22px;
        }

        .check-btn {
		    width: 50px;
		    height: 22px;
		    line-height: 22px;
		    border: none;
		    border-radius: 22px;
		    background-color: #FEA600;
		    color: white;
		    font-size: 12px;
		    text-align: center;
		}

        .upload-btn{
            width:300px;
            height:50px;
            border:1px solid #95D6E2;
            border-radius: 36px;
            background-color: white;
            color:#95D6E2;
            vertical-align: bottom;
        }

        .required{
            color:#FEA600!important;
        }
        .upload-tip{
            font-size: 12px;
            color:#FEA600;
            margin-left:20px;
        }
        
        .received-td {
		    width: 67px;
		    font-size: 12px;
		    color: #4a4a4a;
		}
		
		.time-td span {
		    display: block;
		    text-align: left;
		    font-size: 12px;
		    color: #9b9b9b;
		}
		
		.title-td {
		    width: 68px;
		    height: 16px;
		    font-size: 12px;
		    color: #2cb7c9;
		}
		
		.file-td  span:nth-child(2){
		    font-size: 12px;
		    color: #9b9b9b;
		}
		td.file-td {
    		font-size: 12px;
		}
		
		.feedback-btn {
		    border: none;
		    background: none;
		    font-size: 12px;
		    color: #2cb7c9;
		    padding: 0;
		    margin: 0;
		}
		
		span.timeout {
		    font-size: 12px;
		    color: #D0021B;
		    letter-spacing: 0.1px;
		    margin-left: 15px;
		}
		
		.acceptance {
		    font-size: 12px;
		    color: #2CB7C9;
		    letter-spacing: 0.1px;
		    margin-left: 45px;
		}
		
		span.confirm {
		    font-size: 12px;
		    font-size: 12px;
		    color: #2CB7C9;
		    letter-spacing: 0.1px;
		    margin-left: 10px;
		}
		
		span.unread {
		    position: absolute;
		    width: 40px;
		    height: 19px;
		    display: block;
		    background: url("/res/images/ceo/unread.png");
		    
		    font-size: 12px;
		    color: #fff;
		    left: -21px;
		    top: -4px;
		}


    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
        <span style="font-size: 18px;">文件管理</span>
        <hr style="margin-top:10px;">

    <div style="font-size: 14px;position: relative;">
        <div class="period-select">
            <input type="text" class="form-control" placeholder="请选择" style="width:160px;">
            <div class="triangle">
                <div class="trigger"></div>
            </div>
        <#--<div class="budget">-->
        <#--<ul>-->
        <#--<li>5万以下</li>-->
        <#--<li>5-10万</li>-->
        <#--<li>10-20万</li>-->
        <#--<li>20-30万</li>-->
        <#--<li>30-40万</li>-->
        <#--<li>40万以上</li>-->
        <#--</ul>-->
        <#--</div>-->
        </div>
        <span class="tab">我收到的&nbsp;&nbsp;<span class="keyword">21</span></span>
        <span class="tab">我上传的&nbsp;&nbsp;<span class="keyword">21</span></span>
        <span class="tab">未读&nbsp;&nbsp;<span class="keyword">21</span></span>
        <div class="split-line"></div>
        <div class="split-line" style="left:508px;"></div>
    </div>

    <div class="suppliers-box">
        <table class="table">
            <tbody>
                <tr style="border-top:none;">
                    <td  style="margin: 0;padding: 0">
                        <div class="period-tag">交付上线阶段</div>
                    </td>
                    <td style="margin: 0;padding: 0">
                    	<div class="time-td">
							    <span>2016-4-20</span>
							    <span>18:30</span>
						</div>
					</td>
                    <td style="margin: 0;padding: 0" class="received-td">
                        <img src="/res/images/down_icon.png" width="14" height="16">
                        	收到来自
                    </td>
                    <td style="margin: 0;padding: 0" class="title-td">项目经理LIly</td>
                    <td style="margin: 0;padding: 0" class="file-td">
                       
                        <img src="/res/images/zip_icon.png" width="17" height="23">
                        <span style="color:#4A90E2;">堆糖网项目文件集.zip</span>
                        <span style="color: #9B9B9B">12.2M</span>
                    </td>
                    <td style="width:120px;text-align: left;margin: 0;padding: 0">
                        <img src="/res/images/download_row_icon.png" width="20" height="20">
                        <button type="button" class="check-btn">验收</button>
                        <button type="button" class="feedback-btn">反馈</button>

                    </td>
                </tr>

                <tr>
                    <td style="margin: 0;padding: 0" >
                        <div class="period-tag">开发测试阶段</div>
                    </td>
                    <td style="color:#9B9B9B;margin: 0;padding: 0">
                    	<div class="time-td">
							    <span>2016-4-20</span>
							    <span>18:30</span>
						</div>
                    </td>
                    <td style="margin: 0;padding: 0"  class="received-td">
                        <img src="/res/images/down_icon.png" width="14" height="16">
                       		收到来自
                    </td>
                    <td style="margin: 0;padding: 0" class="title-td">项目经理LIly</td>
                    <td style="margin: 0;padding: 0" class="file-td">
                        <img src="/res/images/zip_icon.png" width="17" height="23">
                       <span style="color:#4A90E2;">堆糖网项目代码包.zip</span>
                 		<span style="color: #9B9B9B">12.2M</span>
                    </td>
                    <td style="width:120px;text-align: left;margin: 0;padding: 0">
                        <img src="/res/images/download_row_icon.png" width="20" height="20">
                        
                        <span class="timeout">超时未确认</span>
                    </td>
                </tr>

                <tr>
                    <td style="margin: 0;padding: 0">
                        <div class="period-tag">界面设计阶段</div>
                    </td>
                    <td style="margin: 0;padding: 0">
                    	<div class="time-td">
							    <span>2016-4-20</span>
							    <span>18:30</span>
						</div>	
                    </td>
                    <td style="margin: 0;padding: 0"  class="received-td">
                        <img src="/res/images/down_icon.png" width="14" height="16">
                        	收到来自
                    </td>
                    <td style="margin: 0;padding: 0" class="title-td">项目经理LIly</td>
                    <td style="position: relative;width:205px;" class="file-td">
                        <img src="/res/images/zip_icon.png" width="17" height="23">
                        <span style="color:#4A90E2;">堆糖网项目UI界面.zip</span>
                        <span style="color: #9B9B9B">12.2M</span>
                    </td>
                    <td style="width:120px;text-align: left;margin: 0;padding: 0">
                        <img src="/res/images/download_row_icon.png" width="20" height="20">
                        <span class="acceptance">已验收</span>
                    </td>
                </tr>

                <tr>
                    <td style="margin: 0;padding: 0">
                        <div class="period-tag">界面设计阶段</div>
                    </td>
                    <td style="margin: 0;padding: 0">
                    	<div class="time-td">
							    <span>2016-4-20</span>
							    <span>18:30</span>
						</div>
                    </td>
                    <td style="margin: 0;padding: 0"  class="received-td">
                        <img src="/res/images/upload-icon.png" width="14" height="16">
                        	收到来自
                    </td>
                    <td style="margin: 0;padding: 0" class="title-td">项目经理LIly</td>
                    <td style="position: relative;width:205px;" class="file-td">
                        <span class="unread">未读</span>
                        <img src="/res/images/ceo/doc-icon.png" width="17" height="23">
                        <span style="color:#4A90E2;">需求修改确认书.doc</span>
                        <span style="color: #9B9B9B">12.2M</span>
                    </td>
                    <td style="width:120px;text-align: left;margin: 0;padding: 0">
                        <img src="/res/images/download_row_icon.png" width="20" height="20">
                        <img src="/res/images/search_row_icon.png" width="20" height="20" style="margin-left:10px;">
                    </td>
                </tr>

                <tr>
                    <td style="margin: 0;padding: 0">
                        <div class="period-tag">需求梳理阶段</div>
                    </td>
                    <td style="margin: 0;padding: 0">
                    	<div class="time-td">
							    <span>2016-4-20</span>
							    <span>18:30</span>
						</div>
                    </td>
                    <td style="margin: 0;padding: 0"  class="received-td">
                        <img src="/res/images/down_icon.png" width="14" height="16">
                        	收到来自
                    </td>
                    <td style="margin: 0;padding: 0" class="title-td">项目经理LIly</td>
                    <td style="margin: 0;padding: 0" class="file-td">
                        <img src="/res/images/ceo/doc-icon.png" width="17" height="23">
                        <span style="color:#4A90E2;">堆糖网需求文档.doc</span>
                        <span style="color: #9B9B9B">12.2M</span>
                    </td>
                    <td style="width:120px;text-align: left;margin: 0;padding: 0">
                        <img src="/res/images/download_row_icon.png" width="20" height="20">
                        <img src="/res/images/search_row_icon.png" width="20" height="20" style="margin-left:10px;">
                    </td>
                </tr>
                
                
                 <tr>
                    <td style="margin: 0;padding: 0" >
                        <div class="period-tag">立项阶段</div>
                    </td>
                    <td style="color:#9B9B9B;margin: 0;padding: 0">
                    	<div class="time-td">
							    <span>2016-4-20</span>
							    <span>18:30</span>
						</div>
                    </td>
                    <td style="margin: 0;padding: 0"  class="received-td">
                        <img src="/res/images/down_icon.png" width="14" height="16">
                       		收到来自
                    </td>
                    <td style="margin: 0;padding: 0" class="title-td">项目经理LIly</td>
                    <td style="margin: 0;padding: 0" class="file-td">
                        <img src="/res/images/ceo/doc-icon.png" width="17" height="23">
                       <span style="color:#4A90E2;">堆糖网项目意向...doc</span>
                 		<span style="color: #9B9B9B">12.2M</span>
                    </td>
                    <td style="width:120px;text-align: left;margin: 0;padding: 0">
                        <img src="/res/images/download_row_icon.png" width="20" height="20">
                        <img src="/res/images/search_row_icon.png" width="20" height="20" style="margin-left:10px;">
                        <span class="confirm">已确认</span>
                    </td>
                </tr>
                
                
                
                
            </tbody>
        </table>
    </div>

    <div style="text-align: center;margin-top:40px;">
        <button type="button" class="upload-btn">
            <img src="/res/images/upload_icon2.png" width="19" height="19">&nbsp;&nbsp;上传文档
        </button>
        <span class="upload-tip"><span class="required">*</span>小于500M</span>
    </div>

    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>