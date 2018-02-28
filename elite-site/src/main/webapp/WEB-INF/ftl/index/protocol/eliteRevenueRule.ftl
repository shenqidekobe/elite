<@extend name="layout">
    <@block name="cs">
	   <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <#--正文开始-->
    <div class="y-bd">
	    <div class="bd_rules">
	        <h1>云英汇人才渠道规则</h1>
	
	        <div class="rules_box">
	            <div class="rules_box_content">
	                <dl>
	                    <dt>一、渠道备案流程</dt>
	                    <dd>1、注册</dd>
	                    <dd>2、审核</dd>
	                    <dd>3、人才渠道在平台备案人才</dd>
	                    <dd>4、备案人才接任务或者项目</dd>
	                    <dd>5、待任务或项目完成验收后，渠道获得收益</dd>
	                    <dt>二、核算规则</dt>
	                    <dd>1、报酬率：<br/>
	                    		&nbsp;&nbsp;&nbsp;&nbsp;所备案人才在平台上为CTO身份，则报酬率为1.8%<br/>
     							&nbsp;&nbsp;&nbsp;所备案人才在平台上为精英身份，则报酬率为1.2%</dd>
	                    <dd>2、核算规则<br/>
						                        报酬=其所备案人才收益额*报酬率<br/>
						                        核算时间：其所备案人才获得收益之时（余额变化之时）<br/>
	                    </dd>
	                    <dd>3、异常情况<br/>
	                        根据人才的实际获益计算渠道报酬
	                    </dd>
	                </dl>
	                <p>***平台不会向任何第三方机构透露您和您的备案的信息，请放心备案***</p>
	                <div class="last">***本规则最终解释权归云英汇平台所有***</div>
	            </div>
	        </div>
	
	    </div>
	</div>
    </@block>

    <@block name="script">
    </@block>
</@extend>