<@extend name="layout">
    <@block name="cs">
         <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/channel/channelContainer.css"/>
    </@block>
    <@block name="body">
        <#--头部-->
        <#if 'channel'==rps>
            <@accountheadChannel opt2=""/>
        <#else>
            <@accounthead opt=""/>
        </#if>
		<div class="y-bd" style="padding-bottom: 100px;">
		    <div class="bd_help">
		        <div class="help_cover">
		            <h1>欢迎您！我们如何能够帮助您？</h1>
		        </div>
		        <div class="help_content">
		            <div class="select_title">
		                <ul id="commonIssue">
		                    <li class="active_li" data="common">常见问题</li>
		                    <li data="project">项目方问题</li>
		                    <li data="elite">精英问题</li>
		                    <li data="channel">渠道问题</li>
		                </ul>
		            </div>
		        
		            <div id="commonHelp" style="display:block;">
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目方怎样发布项目需求？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>1、注册并登陆云英汇账号</dd>
			                                    <dd>2、点击页面顶部“发布项目”进入发布项目页面</dd>
			                                    <dd>3、按照引导录入项目信息，项目信息尽可能详尽，这样利于我们更精准地为您分配项目经理，给您提供更好的服务</dd>
			                                </div>
			                            </div>
			                        </div>
			                  
			           
			            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目方和平台确定合作之后，多长时间能进入开发？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>项目方确认立项书并缴纳第一阶段费用之后，需要1-3个工作日进行人员匹配，匹配完成大致3个工作日可进入项目开发</dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
			            
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">平台如何保障项目开发速度和质量，项目方是否可以随时了解项目开发进度？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                <dl class="hide_ul">
			                                    <dd>1、平台会为每个项目配备经验丰富的项目经理和CTO共同开发，确保项目保质保量完成 </dd>
			                                    <dd>2、平台上所有精英都需要按照开发计划定期提交开发成果，确保项目按计划执行，项目方会每周收到开发进度报告，了解项目开发进度 </dd
			                                    <dd>3、每个项目和任务都有质保期，质保期内如有遗漏bug会确保修改完善</dd>
			                                </div>
			                            </div>
			                        </div>
			                    
			                
			               
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">精英和CTO入驻后怎么样接项目和任务？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                               
			                                <dl class="hide_ul">
			                                    <dd>1、CTO在个人中心的“推荐给我的”切换卡中可以查看平台推荐给他的适合他的项目，点击“去竞标”提交竞标书即可</dd>
			                                    <dd>2、精英在可以从页面顶部“认领任务”入口进入任务大厅查看、筛选并认领任务</dd
			                                    <dd>3、CTO可以接项目也可以接任务，普通精英仅可以接任务，不可接项目</dd>
			                                    <dd>4、精英方所承接的所有项目和任务必须按要求保质保量完成，不良的项目和任务经历会严重影响此精英此后在平台承接项目和任务的成功率</dd>
			                                </div>
			                            </div>
			                        </div>
			                    
			                
			               
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">精英未审核通过可以接项目和任务吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>不可以，为了保障云英汇平台的项目完成质量，我们会对所有精英进行审核，只有审核通过了的精英才可以接项目和任务</dd>
			                                </div>
			                            </div>
			                        </div>
			                   
			               
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">精英可以发布项目吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>不可以，当前版本暂不支持精英发项目，此版本暂时需要您用不同手机号进行注册，工程师正在加紧开发身份切换功能 </dd>
			                                </div>
			                            </div>
			                        </div>
			          
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">精英如何入驻成为云英汇开发者？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>1、注册云英汇账号（平台在现阶段实行邀请注册制，需要拥有邀请码才能注册，获取方法依精英注册页引导即可获得）</dd>
			                                    <dd>2、提交个人信息、项目经历和认证信息</dd>
			                                    <dd>3、项目经验等履历信息尽量详细，便于我们审核并为你匹配到合适的开发任务</dd>
			                                </div>
			                            </div>
			                        </div>
			                
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">同一手机号注册了项目方身份之后还能注册精英或者渠道身份吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>不可以，此版本暂时需要您分别用不同的手机号进行注册，工程师正在加紧开发身份切换功能</dd>
			                                </div>
			                            </div>
			                       
			                            </div>
			                            </div>
			           
			                 <div id="channelHelp" style="display:none;">
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">什么是渠道？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                <dl class="hide_ul">
			                                    <dd>云英汇平台以开放的心态做平台，希望握有优质人才资源和优质互联网项目资源的用户也可以在平台上获取收益，人才渠道和项目渠道是平台优质用户和业务的重要来源，如果想要更深入地了解可以点击进入此页面<a href="https://yunyinghui.com/register/partner" target="_blank">https://yunyinghui.com/register/partner</a></dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			              
		           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">谁可以成为渠道？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>1、任何一个用户都可以成为平台的渠道，因为每个人身边都有或多或少的项目和精英资源，即使没有，您也可以利用业余时间去挖掘 </dd>
			                                    <dd>2、猎头公司和IT培训机构已经拥有了大量的精英资源，成为渠道可以使自己利益最大化 </dd>
			                                    <dd>3、互联网销售公司和各类孵化器自身已经积累了大量的客户资源和项目信息，成为渠道可以实现多赢 </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			              
		           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">如何成为渠道？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                               
			                                <dl class="hide_ul">
			                                    <dd>点击首页最底部的合作伙伴入口，进入渠道页面，根据自己所拥有的资源情况，选择人才渠道的“合作”，或者项目渠道的“合作” 进入注册页面依照引导进行注册和信息录入即可 平台会进行审核并会有渠道专员和您联络 </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
		           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目渠道如何获利？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>1、项目渠道首先需要注册成功并完善信息</dd>
			                                    <dd>2、在注册成功并审核通过后，可以在平台上备案项目信息</dd>
			                                    <dd>3、项目渠道需要向项目渠道推荐云英汇平台，引导其在平台上注册发项目 </dd>
			                                    <dd>4、被推荐的项目在平台上成功运行后，此项目验收通过的每一阶段，都有收益给到项目渠道，具体收益规则详见渠道个人中心 </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
		         
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">如何备案项目？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>在项目渠道个人中心点击备案并在弹窗中输入项目和项目方信息即可</dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			              
		           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目/人才渠道审核通过前可以备案吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>不可以备案，所有渠道都需要平台审核通过后才能做备案操作，请尽快完善资料并申请审核，如已完善，请耐心等待审核，我们会在1-3个工作日内完成审核工作。</dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
		           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目渠道收益如何计算？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>报酬=其所备案项目提交并验收通过的阶段费用总额*报酬率（具体报酬率见渠道个人中心）</dd>
			                                    <dd>核算时间：其所备案项目的各阶段被项目方验收通过时，分阶段核算</dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
		            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">平台会保护我以及我备案的项目信息/人才信息安全吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>平台上所有用户及其提供的信息都是严格保密的，除非经过本人同意，平台不会向任何第三方泄漏</dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
		            
		           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">人才渠道如何获利？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                               
			                                <dl class="hide_ul">
			                                    <dd>1、人才渠道首先需要注册成功并完善信息</dd>
			                                    <dd>2、在注册成功并审核通过后，可以在平台上备案人才信息</dd>
			                                    <dd>3、人才渠道需要向精英推荐云英汇平台，并引导其在平台上注册接活 </dd>
			                                      <dd>4、被备案人在平台上注册并申领任务或项目，待项目方验收通过，即可有收益给到人才渠道，具体收益规则详见渠道个人中心 </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
		            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">如何备案人才？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>在渠道个人中心点击“备案”并在弹窗中输入精英人才信息并上传简历附件即可</dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
		            
		            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">人才渠道收益如何计算？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>报酬=其所备案人才收益额*报酬率（具体报酬率见渠道个人中心）</dd>
			                                    <dd>核算时间：其所备案人才获得收益之时</dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
		           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">为什么备案了项目/人才而没有收益？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>1、另外有其他渠道在您之前做了备案，我们系统以先备案的时间作为核算依据 </dd>
			                                    <dd>2、被备案项目还没有实质性的完成任一阶段，或者被备案精英还没有通过平台获得任何收益 </dd>
			                                    <dd>3、项目或者精英出现异常情况 </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
		            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">渠道需要交税吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                               
			                                <dl class="hide_ul">
			                                    <dd>依法缴纳税费是每一位公民应尽的光荣义务，云英汇平台是合法正规平台，平台和平台上的用户都需要依法纳税 </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
		            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">平台代开发票的税率是多少？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                               
			                                <dl class="hide_ul">
			                                    <dd>我们依照相关税务规定梳理如下表，请依照计算 </dd>
			                           <table border=1>
			                           	<tr>
			                           		<td>单次提现额A（元）</td>
			                           		<td>税额（元）</td>
			                           		<td>税后收入（元）</td>
			                           	</tr>
			                           	 	<tr>
			                           		<td>A≤800</td>
			                           		<td>0.03A</td>
			                           		<td>0.97A</td>
			                           	</tr>
			                           	 	<tr>
			                           		<td>800＜A≤4000</td>
			                           		<td>0.23A-160</td>
			                           		<td>0.77A+160</td>
			                           	</tr>
			                           	 	<tr>
			                           		<td>4000＜A≤20000</td>
			                           		<td>0.19A</td>
			                           		<td>0.81A</td>
			                           	</tr>
			                           	 	<tr>
			                           		<td>20000＜A≤50000</td>
			                           		<td>0.27A-2000</td>
			                           		<td>0.73A+2000</td>
			                           	</tr>
			                           	 	<tr>
			                           		<td>A＞50000</td>
			                           		<td>0.35A-7000</td>
			                           		<td>0.65A+7000</td>
			                           	</tr>
			                           </table>
			                            <dd>举例：</dd>
			                            <dd>1、小明单次申请提现800元，可到账0.97*800=776元，需交税24元 </dd>
			                            <dd>2、小明单次申请提现3000元，可到账0.77*3000+160=2470元，需交税530元 </dd>
			                            <dd>3、小明单次申请提现10000元，可到账0.81*10000=8100元，需交税1900元 </dd>
			                            <dd>4、小明单次申请提现30000元，可到账0.73*30000+2000=23900元，需交税6100元</dd>
			                            <dd>5、小明单次申请提现100000元，可到账0.65*10000+7000=72000元，需交税28000元 </dd>
			                         
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
		            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">渠道如何提现？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>在个人中心-结算管理的余额处，点击提现按钮，进入提现页面，按照引导选择到账方式并输入金额和提现密码即可申请提现，系统会在3—7个工作日内将提现额打到您的账户内 </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			            
		            <div id="projectHelp" style="display:none;">
		         

			            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">为何项目方需要支付500元意向金？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>我们会认真对待发布到我们平台上的每一个项目，首先我们会有商务经理和您联络，沟通您的初步开发需求；然后，我们会安排专业的项目经理通过电话或者上门拜访的方式帮助您梳理开发需求，我们的服务资源有限，我们希望把资源集中在更真实的需求上，所以设置了意向金规则，如果达成了合作，则此意向金直接转为项目款的一部分；如果未达成合作意向，则会退回意向金，您只要跨出一小步，剩下的九十九步交给我们
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			               
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目方提交的意向金可以退吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>项目方提交的意向金是可以退的，如果达成了合作，意向金直接作为项目款的一部分，如果未能达成合作，根据我们工作量的投入核算协商退回金额，财务会在3-7个工作日内给您退回意向金 
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目价格如何定？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>项目价格是我们的商务经理和专业的项目经理，通过和您的详细沟通，梳理出功能列表之后，根据开发要求和人员成本核算的项目价格 由于平台上精英都是兼职工作，成本相对较低，我们的报价比市场价普遍要低，可以为创业团队节省部分开发费用 
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
			               
			            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">需要项目方联络和挑选精英吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>不需要，由于大多项目方对于技术的了解程度不够深，直接和开发人员沟通并不顺畅，平台有专职的项目经理和专业的CTO全程协助您做项目管理，您只需要把项目和资金托管给我们，我们帮您处理所有繁杂流程
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
			               
			          
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目方可以要求签订纸质合同吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>平台的项目立项书（电子版）和纸质合同具有同等法律效用，如需纸质版合同可以联络您的专属商务经理和您签订
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">为什么项目方要分阶段托管费用？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>为了降低项目风险，确保项目按照开发阶段一步一步踏踏实实地执行，我们平台把项目开发分成了四个阶段，分阶段支付、分阶段开发，分阶段验收 我们的用户群大多为创业公司，资金压力比较大，分阶段托管费用，更容易被广大用户接受
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
			          
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目方如何验收项目？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			           
			                                <dl class="hide_ul">
			                                    <dd>项目方可以在文件管理中查看交付物，每一阶段都有交付物，项目方需要根据自己当初提出的项目要求验收交付物，如有异议可以在验收前提出，我们会及时检查和修改，直至通过验收
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
			            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目方如何线下支付？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                <dl class="hide_ul">
			                                    <dd>项目方首先申请线下支付，然后通过网银、银行打款、支付宝转账到银行卡等方法支付项目款给平台并把相关打款记录发给商务经理即可
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			               
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">平台给项目方提供发票吗？项目方如何索要？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>平台可以提供发票，在支付的时候输入发票抬头，我们会有财务人员联络您；也可以通过电话联络您的专属商务经理索要发票
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			         
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目和相关资料能够保密吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                <dl class="hide_ul">
			                                    <dd>平台方和平台上所有负责开发的精英都确认过平台用户协议，协议中有相关保密条款保护您的权益，平台资料都是有针对性地分发给平台认证通过的CTO，平台项目完全封闭式对接
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目源代码所有权是项目方的吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                <dl class="hide_ul">
			                                    <dd>项目源代码所有权归项目方所有，平台可以协助对接软件著作权申请机构，以更好地保护产权
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			           
		            
		            
		            
		            <div id="eliteHelp" style="display:none;">
		            
		            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">云英汇平台收取精英佣金吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                <dl class="hide_ul">
			                                    <dd>根据项目以及平台运营阶段不同，平台会适当收取少量佣金</dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">同一手机号注册了精英身份之后还能注册项目方身份吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">  
			                                <dl class="hide_ul">
			                                    <dd>不可以，此版本暂时需要您分别用不同的手机号进行注册，工程师正在加紧开发身份切换功能
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
			            
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">为何注册了不能接活？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                <dl class="hide_ul">
			                                    <dd>因为您还没有完善资料或者还没有审核通过，为了确保平台上的项目保质保量的完成，平台对精英的挑选较为严格
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">认证身份多久能够审核完成？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>1-3个工作日
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
			            
			            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">为何审核不通过？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>请查看您的消息箱，其中有我们的审核意见，你可以依照审核意见进行修改和完善
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			            
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">如何接任务？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                               
			                                <dl class="hide_ul">
			                                    <dd>1、完善个人资料，平台审核通过
			                                    </dd>
			                                    <dd>2、在任务大厅查看任务，申请任务即可
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			            
			            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">精英如何执行任务？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>1、精英申请成功之后需要在三天内提交一份任务计划给CTO审核 然后，精英按照审核通过的任务计划执行即可
			                                    </dd>
			                                    <dd>2、计划执行完之后，CTO进行审核，审核通过后，任务进入质保期，质保期时长为任务时长的一半时间
			                                    </dd>
			                                    <dd>3、质保期如果CTO有问题或者小的修改需求，精英需要协助CTO完成，质保期到期后，任务自动完成
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			              
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">CTO如何接项目？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>1、只有CTO才能够接项目 ，所以需要您先申请成为认证CTO
			                                    </dd>
			                                    <dd>2、CTO可以在个人中心查看推荐给我的项目，选择合适的项目查看招标书并给感兴趣的项目发竞标书即可
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">CTO如何执行项目？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                              
			                                <dl class="hide_ul">
			                                    <dd>1、对于竞标成功的项目CTO需要前往确认定标书，定标书相当于平台和CTO签订的合同，和纸质合同具有同等法律效用
			                                    </dd>
			                                    <dd>2、CTO需要将项目分成若干小任务，创建任务并选择合适的精英执行任务 过程中您需要妥善管理众多精英及其任务
			                                    </dd>
			                                    <dd>3、CTO需要验收精英的成果并给平台PM提交周报和交付物
			                                    </dd>
			                                    <dd>4、整个项目进入质保期后，需要继续对项目进行质保
			                                    </dd>
			                                    
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">平台接活需要交税吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>依法缴纳税费是每一位公民应尽的光荣义务，云英汇平台是合法正规平台，平台和平台上的用户都需要依法纳税
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">税费如何收取？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>税费在您每次提现的时候扣取 如果您可以自己开发票，则不用扣税费，全额提现
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			            
			            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">提现时平台代开发票的税率是多少？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                               <dl class="hide_ul">
			                                    <dd>我们依照相关税务规定梳理如下表，请依照计算 </dd>
			                           <table border=1>
			                           	<tr>
			                           		<td>单次提现额A（元）</td>
			                           		<td>税额（元）</td>
			                           		<td>税后收入（元）</td>
			                           	</tr>
			                           	 	<tr>
			                           		<td>A≤800</td>
			                           		<td>0.03A</td>
			                           		<td>0.97A</td>
			                           	</tr>
			                           	 	<tr>
			                           		<td>800＜A≤4000</td>
			                           		<td>0.23A-160</td>
			                           		<td>0.77A+160</td>
			                           	</tr>
			                           	 	<tr>
			                           		<td>4000＜A≤20000</td>
			                           		<td>0.19A</td>
			                           		<td>0.81A</td>
			                           	</tr>
			                           	 	<tr>
			                           		<td>20000＜A≤50000</td>
			                           		<td>0.27A-2000</td>
			                           		<td>0.73A+2000</td>
			                           	</tr>
			                           	 	<tr>
			                           		<td>A＞50000</td>
			                           		<td>0.35A-7000</td>
			                           		<td>0.65A+7000</td>
			                           	</tr>
			                           </table>
			                            <dd>举例：</dd>
			                            <dd>1、小明单次申请提现800元，可到账0.97*800=776元，需交税24元 </dd>
			                            <dd>2、小明单次申请提现3000元，可到账0.77*3000+160=2470元，需交税530元</dd>
			                            <dd>3、小明单次申请提现10000元，可到账0.81*10000=8100元，需交税1900元</dd>
			                            <dd>4、小明单次申请提现30000元，可到账0.73*30000+2000=23900元，需交税6100元</dd>
			                            <dd>5、小明单次申请提现100000元，可到账0.65*100000+7000=72000元，需交税28000元 </dd>
			                         
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">精英用户如何提现？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                              
			                                
			                                <dl class="hide_ul">
			                                    <dd>1、在个人中心-结算管理的余额处，点击提现按钮，进入提现页面
			                                    </dd>
			                                    <dd>2、按照引导选择到账方式并输入金额和提现密码即可申请提现，系统会在3—7个工作日内将提现额打到您的账户内
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			               
			            
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">普通精英如何申请成为CTO？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                               
			                                <dl class="hide_ul">
			                                    <dd>1、在我的资料-当前状态-成为签约CTO中点击我要申请按钮，阅读相关说明后点击“确认申请”即可
			                                    </dd>
			                                    <dd>2、我们会有专业人员对您进行审核，如果符合CTO条件即可升级成为CTO，如果不符合您可以继续使用认证精英身份接任务
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			            
			           
			            
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">CTO是什么岗位？需要什么能力？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>CTO是平台上的项目负责人，对所承接项目的全流程负责 具体要求
			                                    </dd>
			                                    <dd>如下：
			                                    </dd>
			                                    <dd>1、需要有5年以上的项目开发经验，3年以上技术团队管理经验
			                                    </dd>
			                                    <dd>2、熟悉常见项目的开发执行全流程
			                                    </dd>
			                                    <dd>3、能够独立对项目进行报价 
			                                    </dd>
			                                    <dd>4、能够制定项目开发计划，并能把项目拆分成若干任务
			                                    </dd>
			                                    <dd>5、能够筛选合格的精英执行其任务，能够验收精英的工作成果，能够协调管理好整个团队，能够处理突发情况
			                                    </dd>
			                                    
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			              
			            
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">CTO如何赚钱？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>1、CTO对感兴趣的项目进行竞标
			                                    </dd>
			                                    <dd>2、CTO竞标成功后，需要把项目科学地拆分成不同定价的小任务，并招募精英执行
			                                    </dd>
			                                    <dd>3、CTO通过发挥自己项目管理的专业才能，赚取竞标价和任务开支之间的差价，当然，如果项目管理不当，也可能面临没有利润甚至平台的追责
			                                    </dd>
			                                   
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			            
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目和任务质保金是什么？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>项目和任务都有质保期，质保金是每一个项目和任务在质保期冻结的部分资金，待质保期结束自动解冻，具体质保金额度如下：
			                                    </dd>
			                                    <dd>1、项目质保金（冻结CTO项目款）比例为：15%
			                                    </dd>
			                                    <dd>2、任务质保金（冻结精英任务款）比例为：10%
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			            
			          
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目和任务质保期多长？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>1、项目质保期由平台PM根据用户要求来制定
			                                    </dd>
			                                    <dd>2、任务质保期为任务执行周期的一半时间
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                
			           
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">项目开发完成之后，代码如何交接？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>开发工作完成之后，CTO整理所有代码，按照规范进行整理归档，打包后上传到平台或电邮给其负责的项目经理，由平台检查后转交给客户
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			              
			            
			            
			                    <div class=" problem_group">
			                        <div class="group_show">
			                            <span class="show_icon show_img"></span>
			                            <span class="show_text">精英资料可以保密吗？</span>
			                        </div>
			                        <div class="group_hide" style="display:none;">
			                            <div class="hide_content">
			                                
			                                <dl class="hide_ul">
			                                    <dd>平台上的精英信息都是严格保密的，除非经过本人同意，平台不会向任何第三方泄漏精英资料
			                                    </dd>
			                                </dl>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			            
		        </div>
		    </div>
		</div>
		</div>
    </@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/help.js"></script>
    </@block>
</@extend>