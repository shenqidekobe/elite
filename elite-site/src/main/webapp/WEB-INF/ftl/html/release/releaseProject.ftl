<@extend name="completeLayout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/public/public.css">
	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/ceo-main.css">
    </@block>

<#--中间正文部分-->
    <@block name="content">

    <div class="process-release">
    		<div class="release-title">
			    <p>发布项目</p>
			    <p>说出你的梦想，云英汇为你搞定产品、CTO、团队和资金</p>
			</div>
    </div>

    <div class="content" style="margin-top: -100px;">
 
        <form class="form-group form-horizontal form-release" role="form" id="releaseFrom">
            <p><span class="required">*</span>&nbsp;&nbsp;<span class="font-size16">解决方案类型&nbsp;&nbsp;</span><span style="font-size:12px;color:#9B9B9B;">(可多选)</span></p>
            <div class="margintop15">
                <button type="button" class="solution-btn active-btn">PC网站</button>
                <button type="button" class="solution-btn active-btn">移动App</button>
                <button type="button" class="solution-btn">微信</button>
                <button type="button" class="solution-btn">H5</button>
                <button type="button" class="solution-btn">硬件</button>
            </div>
            <div class="margintop20">
                <span class="font-size14">已选</span><button type="button" class="selected-solution">PC网站+移动App</button>
            </div>

            <div class="margintop40">
                <p class="marginbottom11"><span class="required">*</span>&nbsp;&nbsp;<span class="font-size16">项目预算</span></p>
                <div style="position: relative;width:240px;">
                    <input type="text" class="form-control  input-unified width240" placeholder="请选择">
                    <div class="form-triangle-right20">
                        <div class="form-trigger"></div>
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
            </div>
			<div class="popupsw">
				!请输入正确的文字
			</div>
            <div class="margintop30">
                <p class="marginbottom11"><span class="required">*</span>&nbsp;&nbsp;<span class="font-size16">项目名称</span><span class="font-size12 color9b">(少于15个字)</span></p>
                <input type="text" class="form-control input-unified width500" placeholder="简洁且能概括产品的项目标题" >
            </div>
			<div class="popupsw">
				!请输入正确的文字
			</div>
            <div style="margin-top:40px;">
                <p class="marginbottom15"><span class="required">*</span>&nbsp;&nbsp;<span class="font-size16">项目简介</span>&nbsp;&nbsp;<span class="font-size12 color9b">（50-1000字）</span></p>
                <textarea class="form-control input-unified width500" rows="5" placeholder="简单描述一下你的创意/功能/需求"></textarea>
            </div>
			<div class="popupsw">
				!请输入正确文字
			</div>
            <div class="hr-line"></div>

            <div>
                <p><span class="font-size16 color4a">其他描述类文档</span>&nbsp;&nbsp;<span class="font-size12 color9b">(任何能够辅助描述项目的文档如<span style="color:#000;">产品需求文档、功能介绍说明、草图</span>等 ，小于50M)</span></p>
                
                <div class="margintop15">
                    <div class="upload-resume">
                        <img src="/res/images/upload_icon.png" width="20" height="20">
                        <span style="margin-left:10px;">上传附件描述文档</span>
                    </div>
                    
                </div>
            </div>
            
            
            <div class="clearfloat margintop20">
            	<div class="fl ptre"><button type="button" class="upload-file">堆糖网需求文档.doc</button><span class="close-a"></span></div>
                <div class="fl ptre"><button type="button" class="upload-file">堆糖网需求文档.doc</button><span class="close-a"></span></div>
                <div class="fl ptre"><button type="button" class="upload-file">堆糖网需求文档.doc</button><span class="close-a"></span></div>   
            
            </div>
            
         

            <div style="margin-top:40px;">
                <p class="font-size16 marginbottom15">推荐人</p>
                <input type="text" class="form-control input-unified width400" placeholder="如果有，请填写推荐人的手机号或邀请码" >
            </div>

            <div style="margin-top:40px;">
                <div class="link-line"></div>
                <span style="margin:0 20px;" class="color2c font-size16">如果你有更多细节要告诉我们</span>
                <div class="link-line"></div>
            </div>

            <div class="release-more">
                <div>
                    <span>期望交付时间</span><img src="/res/images/expand_icon.png" class="expand-icon">
                    <div class="margintop15">
                          <span class="date-select" style="width:160px;margin-right:37px;">
		                            <img src="/res/images/date_icon.png" class="date-icon-right20 " >
		                            <input type="text" class="form-control input-unified width160" id="receiptdate"  readonly  placeholder="起始日期" style="background-color: white;"/>
                          </span>
                          
                          
                       	
                          <span class="date-select" style="width:160px;">
		                            <img src="/res/images/date_icon.png" class="date-icon-right20" >
		                            <input type="text" class="form-control input-unified width160" id="receiptdate"  readonly  placeholder="交付日期" style="background-color: white;"/>
                          </span>
                        <span class="marginleft10 time-total">共&nbsp;<input type="text" class="form-control input-sm work-days"  maxlength="3">&nbsp;个工作日</span>
                    </div>
                </div>

                <div style="margin-top:20px;">
                    <span>项目紧急联系人</span><img src="/res/images/expand_icon.png" class="expand-icon">
                    <div class="margintop10">
                        <input type="text" class="form-control input-unified" placeholder="姓名" style="width:130px;display: inline-block;">
                        <input type="text" class="form-control input-unified" placeholder="电话或邮箱等联系方式" style="width:300px;display: inline-block;margin-left:20px;">
                    </div>
                </div>

                <div style="margin-top:20px;">
                    <span>参考项目</span><img src="/res/images/expand_icon.png" class="expand-icon">
                    <div style="margin-top:10px;">
                        <input type="text" class="form-control input-unified width450" placeholder="其他相似功能的产品名称或网址" style="display: inline-block;">
                        <img src="/res/images/add_icon2.png" class="add-url">
                    </div>
                </div>

                <div style="margin-top:20px;">
                    <span>项目所在城市</span><img src="/res/images/expand_icon.png" class="expand-icon">
                    <div style="margin-top:10px;position: relative;width:250px;">
                        <input type="text" class="form-control input-unified width300" placeholder="所在城市" style="padding-left:65px;">
                        <img src="/res/images/location_icon.png" class="location">
                        <div class="form-triangle"><div class="form-trigger"></div></div>
                    </div>
                </div>

                <div style="margin-top:20px;">
                    <span>行业分类</span><img src="/res/images/expand_icon.png" class="expand-icon">
                    <div style="margin-top:10px;">
                        <div class="belong-class">
                            <div class="select-box">
                                <button type="button" class="active-btn" data-class="education">教育</button>
                                <button type="button" data-class="finance">金融</button>
                                <button type="button" data-class="car">汽车</button>
                                <button type="button" data-class="logistics">物流</button>
                                <button type="button" data-class="coffee">餐饮</button>
                                <button type="button" data-class="tour">旅游</button>
                                <button type="button" data-class="tour">游戏</button>
                                <button type="button" data-class="tour">硬件</button>
                                <button type="button" data-class="tour">视频</button>
                                <button type="button" data-class="tour">音乐</button>
                                <button type="button" data-class="tour">体育</button>
                                <button type="button" data-class="tour">社交</button>
                                <button type="button" data-class="tour">服务器</button>
                                <button type="button" data-class="tour">数据库</button>
                                <button type="button" data-class="tour">房产服务</button>
                                <button type="button" data-class="tour">医疗健康</button>
                                <button type="button" data-class="tour">信息分类</button>
                                <button type="button" data-class="tour">电子商务</button>
                                <button type="button" data-class="tour">企业服务</button>
                                <button type="button" data-class="tour">其他</button>
                            </div>
                        </div>
                    </div>
                </div>
				<div class="popupsw">
					!请输入正确文字
				</div>
                <div style="margin-top:20px;">
                    <span>功能分类</span><img src="/res/images/expand_icon.png" class="expand-icon">
                    <div style="margin-top:10px;">
                        <button type="button" class="function-btn">社交</button>
                        <button type="button" class="function-btn">平台</button>
                        <button type="button" class="function-btn">电商</button>
                    </div>
                </div>

                <div style="margin-top:20px;">
                    <span>是否参与云英汇《明星开发团队持股开发计划》</span><img src="/res/images/expand_icon.png" class="expand-icon">
                    <div style="margin-top:10px;">
                        <div class="btn-group fl">
                            <button type="button" class="btn btn-default active-btn">是</button>
                            <button type="button" class="btn btn-default">否</button>
                        </div>
                        <div class="upload-resume fl" style="margin-left:20px;">
                            <img src="/res/images/upload_icon.png" width="20" height="20">
                            <span style="margin-left:10px;">上传商业计划书</span>
                        </div>
                    </div>
                </div>

            </div>

            <button type="button" class="btn-ok" style="margin-left:0;margin-top:60px;">完成并发布</button>
           
        </form>
        
       
        
        
        
        
        
        
    </div>
    
    
    
    
<div class="complete-success" style="display: none">
    <div class="resu">
        <div class="paddingtop50 padingleft58 lineh50"><span class="resu-bg fl marginright36"></span><span class="color2c fl fontsize24">恭喜！你已成功发布项目！</span></div>
        <div class="margintop69 resu-b"><span class="colorFE fontsize14 fl db lineh40">6s</span><span class="color9B fl db lineh40 marginright30">后进入个人主页</span><a class="resu-btn fl db">进入个人主页</a></div>
    </div>
</div>
    
    
    
    </@block>




    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>