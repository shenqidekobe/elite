<@extend name="layout">
    <@block name="cs">
	   <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
	   <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/projectMain.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <#--正文开始-->
    <div class="y-bd">
	    <div id="projectMain">
	        <div class="platform">
	            <div class="platform_text_box">
	                <div class="platform_bg"></div>
	                <div class="div1">这可能是目前互联网</div>
	                <div class="div2"> 项目孵化，体验较好<span>的平台</span></div>
	            </div>
	        </div>
	        <div class="flow_big">
	            <div class="headline">
	                <span class="headline_text">秒懂，云英汇项目服务大流程</span>
	                <span class="headline_line"></span>
	            </div>
	            <div class="img_box">
	                <img src="${_PATH}/res/images/project/flow_big.png">
	            </div>
	            <div class="text_box">
	                <span class="number1 number">1</span>
	                <span class="number2 number">2</span>
	                <span class="number3 number">3</span>
	                <span class="number4 number">4</span>
	                <span class="text1 text">项目方发布项目</span>
	                <span class="text2 text">平台受理，任务分解</span>
	                <span class="text3 text">精英认领</span>
	                <span class="text4 text">阶段付款，验收</span>
	            </div>
	        </div>
	        <div class="flow_thin">
	            <div class="flow_thin_content">
	                <div class="headline">
	                    <span class="headline_text">看一遍就懂的细化流程</span>
	                    <span class="headline_line"></span>
	                </div>
	                <div class="img_box"><img src="${_PATH}/res/images/project/flow_thin.png"></div>
	            </div>
	        </div>
	        <div class="heart">
	            <div class="heart_content">
	                <div class="headline">
	                    <span class="headline_text">云英汇的初心，解决项目方的痛点</span>
	                    <span class="headline_line"></span>
	                </div>
	                <div class="heart_group_box">
	                    <div class="heart_group">
	                    <div class="before">
	                        <div class="triangle"></div>
	                        <div class="before_a">Before</div>
	                        <div class="before_b">
	                            <dl>
	                                <dd class="clearfloat">
	                                    <img class="dd_icon" src="${_PATH}/res/images/project/before_icon.png" alt="">
	                                    <span class="dd_text">有创意，不懂技术，担心各种坑</span>
	                                </dd>
	                                <dd class="clearfloat">
	                                    <img class="dd_icon" src="${_PATH}/res/images/project/before_icon.png" alt="">
	                                    <span class="dd_text">懂技术有团队，但某些技术岗位暂时缺失</span>
	                                </dd>
	                            </dl>
	                        </div>
	                    </div>
	                    <div class="costing">
	                        <img src="${_PATH}/res/images/project/skill.png" alt="">
	                        <span class="text">技术成本</span>
	                    </div>
	                    <div class="now">
	                        <div class="triangle"></div>
	                        <div class="now_a">Now</div>
	                        <div class="now_b">
	                            <dl>
	                                <dt>项目管家</dt>
	                                <dd class="clearfloat">
	                                    <img class="dd_icon" src="${_PATH}/res/images/project/now_icon.png" alt="">
	                                    <span class="dd_text">每个项目有专属的项目经理和签约CTO及相关智囊团，为您梳理需求，规避可避免的错误，保障开发质量</span>
	                                </dd>
	                                <dd class="clearfloat">
	                                    <img class="dd_icon" src="${_PATH}/res/images/project/now_icon.png" alt="">
	                                    <span class="dd_text">平台聚集大量具有项目开发经验，实名制IT技术精英团队，满足80%的互联网项目开发岗位需求</span>
	                                </dd>
	                            </dl>
	                        </div>
	                    </div>
	                </div>
	                    <div class="heart_group">
	                        <div class="before">
	                            <div class="triangle"></div>
	                            <div class="before_a">Before</div>
	                            <div class="before_b">
	                                <dl>
	                                    <dt>金钱成本太大</dt>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/before_icon.png" alt="">
	                                        <span class="dd_text">招人组团队，开发一个APP成本在100万左右，起步成本大</span>
	                                    </dd>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/before_icon.png" alt="">
	                                        <span class="dd_text">某些岗位不需要长期雇佣，想节约成本</span>
	                                    </dd>
	                                </dl>
	                            </div>
	                        </div>
	                        <div class="costing">
	                            <img src="${_PATH}/res/images/project/money.png" alt="">
	                            <span class="text">金钱成本</span>
	                        </div>
	                        <div class="now">
	                            <div class="triangle"></div>
	                            <div class="now_a">Now</div>
	                            <div class="now_b">
	                                <dl>
	                                    <dt>如何节约金钱成本</dt>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/now_icon.png" alt="">
	                                        <span class="dd_text">兼职开发：技能共享，人才兼职的方式</span>
	                                    </dd>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/now_icon.png" alt="">
	                                        <span class="dd_text">技术沉淀：大量技术成果、框架、相似的开发经验</span>
	                                    </dd>
	                                </dl>
	                                <div class="prompt"><span class="span1">通过非精准计算，这种方式开发一个APP的成本能够减少</span><span class="span2">30%-70%</span></div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="heart_group">
	                        <div class="before">
	                            <div class="triangle"></div>
	                            <div class="before_a">Before</div>
	                            <div class="before_b">
	                                <dl>
	                                    <dt>时间成本</dt>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/before_icon.png" alt="">
	                                        <span class="dd_text">大量的项目忙碌于找到合适的人：找人、面试、入职、试用；不合适？换，又是一个循环，导致延期</span>
	                                    </dd>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/before_icon.png" alt="">
	                                        <span class="dd_text">全新的项目，开发质量不行，导致后期的迭代延期</span>
	                                    </dd>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/before_icon.png" alt="">
	                                        <span class="dd_text">消耗内部大量的项目管理时间，其他事情无精力同步推进</span>
	                                    </dd>
	                                </dl>
	                            </div>
	                        </div>
	                        <div class="costing">
	                            <img src="${_PATH}/res/images/project/time.png" alt="">
	                            <span class="text">时间成本</span>
	                        </div>
	                        <div class="now">
	                            <div class="triangle"></div>
	                            <div class="now_a">Now</div>
	                            <div class="now_b">
	                                <dl>
	                                    <dt>如何缩短时间</dt>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/now_icon.png" alt="">
	                                        <span class="dd_text">平台根据任务要求匹配到合适的技术人才</span>
	                                    </dd>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/now_icon.png" alt="">
	                                        <span class="dd_text">平台优先选择匹配具有相似项目开发经验的精英</span>
	                                    </dd>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/now_icon.png" alt="">
	                                        <span class="dd_text">专属项目管家全称跟进，您只需关注项目进展即可</span>
	                                    </dd>
	                                </dl>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="heart_group">
	                        <div class="before">
	                            <div class="triangle"></div>
	                            <div class="before_a">Before</div>
	                            <div class="before_b">
	                                <dl>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/before_icon.png" alt="">
	                                        <span class="dd_text">对陌生的外包商，担心质量的同时，又不放心先打项目款</span>
	                                    </dd>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/before_icon.png" alt="">
	                                        <span class="dd_text">传统外包后期维护沟通困难</span>
	                                    </dd>
	                                </dl>
	                            </div>
	                        </div>
	                        <div class="costing">
	                            <img src="${_PATH}/res/images/project/trust.png" alt="">
	                            <span class="text">信任成本</span>
	                        </div>
	                        <div class="now">
	                            <div class="triangle"></div>
	                            <div class="now_a">Now</div>
	                            <div class="now_b">
	                                <dl>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/now_icon.png" alt="">
	                                        <span class="dd_text">平台托管费用，分阶段打款、验收，解决信任问题</span>
	                                    </dd>
	                                    <dd class="clearfloat">
	                                        <img class="dd_icon" src="${_PATH}/res/images/project/now_icon.png" alt="">
	                                        <span class="dd_text">技术精英开发完成后，平台会冻结质保金，保障项目方在质保期内提出的合理要求</span>
	                                    </dd>
	                                </dl>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="effect">
	            <div class="effect_text">项目进展顺畅得，根本不像是外包</div>
	        </div>
	        <div class="type">
	            <div class="type_content">
	                <div class="headline">
	                    <span class="headline_text">当前，云英汇提供以下类型的项目开发</span>
	                    <span class="headline_line"></span>
	                </div>
	                <ul class="type_ul">
	                    <li>
	                        <div class="type_div">
	                            <div class="type_img"><img src="${_PATH}/res/images/project/web.png"></div>
	                            <div class="type_text">web网站</div>
	                        </div>
	                    </li>
	                    <li>
	                        <div class="type_div">
	                            <div class="type_img"><img src="${_PATH}/res/images/project/ios.png"></div>
	                            <div class="type_text">ios</div>
	                        </div>
	                    </li>
	                    <li>
	                        <div class="type_div">
	                            <div class="type_img"><img src="${_PATH}/res/images/project/android.png"></div>
	                            <div class="type_text">Android</div>
	                        </div>
	                    </li>
	                    <li>
	                        <div class="type_div">
	                            <div class="type_img"><img src="${_PATH}/res/images/project/wechat.png"></div>
	                            <div class="type_text">微信</div>
	                        </div>
	                    </li>
	                    <li>
	                        <div class="type_div">
	                            <div class="type_img"><img src="${_PATH}/res/images/project/h5.png"></div>
	                            <div class="type_text">H5</div>
	                        </div>
	                    </li>
	                    <li style="margin-right:0;">
	                        <div class="type_div">
	                            <div class="type_img"><img src="${_PATH}/res/images/project/other.png"></div>
	                            <div class="type_text">其它</div>
	                        </div>
	                    </li>
	                </ul>
	            </div>
	
	        </div>
	        <div class="release">
	            <div class="person_div">
	                <img class="" src="${_PATH}/res/images/project/project.png">
	                <div class="text_person">我是项目方</div>
	                <button class="btn_person" type="button" id="me_project">发布项目</button>
	            </div>
	        </div>
	    </div>
	</div>
    </@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/index.js"></script>
    </@block>
</@extend>