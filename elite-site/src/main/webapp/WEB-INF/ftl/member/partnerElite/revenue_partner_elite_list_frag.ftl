	<!--间接渠道，直接渠道 所推荐精英列表-->
<div class="template template_other"  >
					<div class="template_body">
						<div class="tableBox tableBox_other">
							<div class="table_head">
								<div class="part" style="width:30%;border-right:1px solid #fff;">人才编号</div>
								<div class="part" style="width:20%;border-right:1px solid #fff;">人才名称</div>
								<div class="part" style="width:15%;border-right:1px solid #fff;">身份类型</div>
								<div class="part" style="width:20%;border-right:1px solid #fff;">人才收益总金额<span>(元)</span></div>
								<div class="part" style="width:15%">我的收益额<span>(元)</span></div>
							</div>
							<div class="table_body">
							
								     <#list list as it>
								     <div class="group">
									<div class="part" style="width:30%"><#if it.memberPassport??>${it.memberPassport.memberNum}<#else><span style="color:red">未注册</span></#if></div>
									<div class="part" style="width:20%">${it.memberPassport.nickName}</div>
									<div class="part" style="width:15%">
                                      <#if it.memberPassport.currentType='cto'>
                                        CTO
                                      <#else>
                                       	精英
                                      </#if>
 
                                     </div>
									<div class="part" style="width:20%">${it.tasksInAmount}</div>
									<div class="part" style="width:15%">${it.partnerAmount}</div>
									</div>
									 </#list>
							</div>
						</div>
					</div>
				</div>
