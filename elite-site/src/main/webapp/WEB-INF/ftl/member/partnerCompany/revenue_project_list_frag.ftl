	<!--间接渠道，直接渠道 所推荐的项目列表-->
	<div class="template template_other">
					<div class="template_body">
						<div class="tableBox tableBox_other">
							<div class="table_head">
								<div class="part" style="width:25%;border-right:1px solid #fff;">项目编号</div>
								<div class="part" style="width:25%;border-right:1px solid #fff;">项目名称</div>
								<div class="part" style="width:15%;border-right:1px solid #fff;">项目总额<span>(元)</span></div>
								<div class="part" style="width:20%;border-right:1px solid #fff;">已验收总额<span>(元)</span></div>
								<div class="part" style="width:15%;solid #fff;">我的收益额<span>(元)</span></div>
							</div>
							<div class="table_body">
							 <#list list as it>
								<div class="group">
									<div class="part" style="width:25%">${it.projectNum}</div>
									<div class="part" style="width:25%">${it.name}</div>
									<div class="part" style="width:15%">${it.totalAmount}</div>
									<div class="part" style="width:20%">${it.trustedAmount}</div>
									<div class="part" style="width:15%">${it.partnerIncome}</div>
								</div>
								</#list>
							</div>
						</div>
					</div>
    </div>