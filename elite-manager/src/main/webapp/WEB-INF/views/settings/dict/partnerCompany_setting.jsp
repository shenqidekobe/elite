<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="partnerCompanyFromId" />
<table class="table table-bordered">
	<section class="content">
		<div class="col-md-12" style="min-width: 950px;">

			<div>
				<ul class="nav nav-tabs">
					<li class="active"><a href="#tab_1" data-toggle="tab">项目渠道本人发展的规则</a></li>
					<li class=""><a href="#tab_2" data-toggle="tab">直接渠道规则</a></li>
					<li class=""><a href="#tab_3" data-toggle="tab">间接渠道规则</a></li>
				</ul>
				<div id="myTabContent" class="tab-content">
					<div id="tab_1" class="tab-pane fade in active"">
						<div>
							<h4 class="col-xs-3">项目渠道本人发展的规则</h4>
						</div>
						<br />
						<hr class="setting-hr" />
						<div class="box-body" style="margin-top: 20px;">
							<div class="channel-row">
								<div class="channel-item">
									<span><strong>第一梯队：</strong></span> <span>最小签单额 </span> <input class="channel-item-input"
										name="partnerOwnRule.firstLadder.minAmount" value="${it.partnerOwnRule.firstLadder.minAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大签单额</span> <input class="channel-item-input" name="partnerOwnRule.firstLadder.maxAmount" 
										value="${it.partnerOwnRule.firstLadder.maxAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最小订单量</span> <input class="channel-item-input" name="partnerOwnRule.firstLadder.minOrder" 
										value="${it.partnerOwnRule.firstLadder.minOrder}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大订单量</span> <input class="channel-item-input" name="partnerOwnRule.firstLadder.maxOrder" 
										value="${it.partnerOwnRule.firstLadder.maxOrder}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
								<div class="channel-item">
									<span>收益比例提成</span> <input  class="channel-item-input" name="partnerOwnRule.firstLadder.commissionRatio"
										value="${it.partnerOwnRule.firstLadder.commissionRatio}"></input> <span> </span>
								</div>
							</div>
							<br/>
							<div class="channel-row">
								<div class="channel-item">
									<span><strong>第二梯队：</strong></span> <span>最小签单额 </span> <input class="channel-item-input"
										name="partnerOwnRule.secondLadder.minAmount" value="${it.partnerOwnRule.secondLadder.minAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大签单额</span> <input class="channel-item-input" name="partnerOwnRule.secondLadder.maxAmount" 
										value="${it.partnerOwnRule.secondLadder.maxAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最小订单量</span> <input class="channel-item-input" name="partnerOwnRule.secondLadder.minOrder" 
										value="${it.partnerOwnRule.secondLadder.minOrder}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大订单量</span> <input class="channel-item-input" name="partnerOwnRule.secondLadder.maxOrder" 
										value="${it.partnerOwnRule.secondLadder.maxOrder}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
								<div class="channel-item">
									<span>收益比例提成</span> <input  class="channel-item-input" name="partnerOwnRule.secondLadder.commissionRatio"
										value="${it.partnerOwnRule.secondLadder.commissionRatio}"></input> <span> </span>
								</div>
							</div>
							<br/>
							<div class="channel-row">
								<div class="channel-item">
									<span><strong>第三梯队：</strong></span> <span>最小签单额 </span> <input class="channel-item-input"
										name="partnerOwnRule.threeLadder.minAmount" value="${it.partnerOwnRule.threeLadder.minAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大签单额</span> <input class="channel-item-input" name="partnerOwnRule.threeLadder.maxAmount" 
										value="${it.partnerOwnRule.threeLadder.maxAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最小订单量</span> <input class="channel-item-input" name="partnerOwnRule.threeLadder.minOrder" 
										value="${it.partnerOwnRule.threeLadder.minOrder}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大订单量</span> <input class="channel-item-input" name="partnerOwnRule.threeLadder.maxOrder" 
										value="${it.partnerOwnRule.threeLadder.maxOrder}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
								<div class="channel-item">
									<span>收益比例提成</span> <input  class="channel-item-input" name="partnerOwnRule.threeLadder.commissionRatio"
										value="${it.partnerOwnRule.threeLadder.commissionRatio}"></input> <span> </span>
								</div>
							</div>
							<br/>
						</div>
					</div>
					<div id="tab_2" class="tab-pane"">
						<div>
							<h4 class="col-xs-3">直接渠道规则</h4>
						</div>
						<br />
						<hr class="setting-hr" />
						<div class="box-body" style="margin-top: 20px;">
							<div class="channel-row">
								<div class="channel-item">
									<span><strong>第一梯队：</strong></span> <span>最小签单额 </span> <input class="channel-item-input"
										name="partnerDirectRule.firstLadder.minAmount" value="${it.partnerDirectRule.firstLadder.minAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大签单额</span> <input class="channel-item-input" name="partnerDirectRule.firstLadder.maxAmount" 
										value="${it.partnerDirectRule.firstLadder.maxAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最小订单量</span> <input class="channel-item-input" name="partnerDirectRule.firstLadder.minOrder" 
										value="${it.partnerDirectRule.firstLadder.minOrder}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大订单量</span> <input class="channel-item-input" name="partnerDirectRule.firstLadder.maxOrder" 
										value="${it.partnerDirectRule.firstLadder.maxOrder}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
								<div class="channel-item">
									<span>收益比例提成</span> <input  class="channel-item-input" name="partnerDirectRule.firstLadder.commissionRatio"
										value="${it.partnerDirectRule.firstLadder.commissionRatio}"></input> <span> </span>
								</div>
							</div>
							<br/>
							<div class="channel-row">
								<div class="channel-item">
									<span><strong>第二梯队：</strong></span> <span>最小签单额 </span> <input class="channel-item-input"
										name="partnerDirectRule.secondLadder.minAmount" value="${it.partnerDirectRule.secondLadder.minAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大签单额</span> <input class="channel-item-input" name="partnerDirectRule.secondLadder.maxAmount" 
										value="${it.partnerDirectRule.secondLadder.maxAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最小订单量</span> <input class="channel-item-input" name="partnerDirectRule.secondLadder.minOrder" 
										value="${it.partnerDirectRule.secondLadder.minOrder}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大订单量</span> <input class="channel-item-input" name="partnerDirectRule.secondLadder.maxOrder" 
										value="${it.partnerDirectRule.secondLadder.maxOrder}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
								<div class="channel-item">
									<span>收益比例提成</span> <input  class="channel-item-input" name="partnerDirectRule.secondLadder.commissionRatio"
										value="${it.partnerDirectRule.secondLadder.commissionRatio}"></input> <span> </span>
								</div>
							</div>
							<br/>
							<div class="channel-row">
								<div class="channel-item">
									<span><strong>第三梯队：</strong></span> <span>最小签单额 </span> <input class="channel-item-input"
										name="partnerDirectRule.threeLadder.minAmount" value="${it.partnerDirectRule.threeLadder.minAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大签单额</span> <input class="channel-item-input" name="partnerDirectRule.threeLadder.maxAmount" 
										value="${it.partnerDirectRule.threeLadder.maxAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最小订单量</span> <input class="channel-item-input" name="partnerDirectRule.threeLadder.minOrder" 
										value="${it.partnerDirectRule.threeLadder.minOrder}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大订单量</span> <input class="channel-item-input" name="partnerDirectRule.threeLadder.maxOrder" 
										value="${it.partnerDirectRule.threeLadder.maxOrder}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
								<div class="channel-item">
									<span>收益比例提成</span> <input  class="channel-item-input" name="partnerDirectRule.threeLadder.commissionRatio"
										value="${it.partnerDirectRule.threeLadder.commissionRatio}"></input> <span> </span>
								</div>
							</div>
							<br/>
						</div>
					</div>
					<div id="tab_3" class="tab-pane"">
						<div>
							<h4 class="col-xs-3">间接渠道规则</h4>
						</div>
						<br />
						<hr class="setting-hr" />
						<div class="box-body" style="margin-top: 20px;">
							<div class="channel-row">
								<div class="channel-item">
									<span><strong>第一梯队：</strong></span> <span>最小签单额 </span> <input class="channel-item-input"
										name="partnerIndirectRule.firstLadder.minAmount" value="${it.partnerIndirectRule.firstLadder.minAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大签单额</span> <input class="channel-item-input" name="partnerIndirectRule.firstLadder.maxAmount" 
										value="${it.partnerIndirectRule.firstLadder.maxAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最小订单量</span> <input class="channel-item-input" name="partnerIndirectRule.firstLadder.minOrder" 
										value="${it.partnerIndirectRule.firstLadder.minOrder}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大订单量</span> <input class="channel-item-input" name="partnerIndirectRule.firstLadder.maxOrder" 
										value="${it.partnerIndirectRule.firstLadder.maxOrder}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
								<div class="channel-item">
									<span>收益比例提成</span> <input  class="channel-item-input" name="partnerIndirectRule.firstLadder.commissionRatio"
										value="${it.partnerIndirectRule.firstLadder.commissionRatio}"></input> <span> </span>
								</div>
							</div>
							<br/>
							<div class="channel-row">
								<div class="channel-item">
									<span><strong>第二梯队：</strong></span> <span>最小签单额 </span> <input class="channel-item-input"
										name="partnerIndirectRule.secondLadder.minAmount" value="${it.partnerIndirectRule.secondLadder.minAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大签单额</span> <input class="channel-item-input" name="partnerIndirectRule.secondLadder.maxAmount" 
										value="${it.partnerIndirectRule.secondLadder.maxAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最小订单量</span> <input class="channel-item-input" name="partnerIndirectRule.secondLadder.minOrder" 
										value="${it.partnerIndirectRule.secondLadder.minOrder}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大订单量</span> <input class="channel-item-input" name="partnerIndirectRule.secondLadder.maxOrder" 
										value="${it.partnerIndirectRule.secondLadder.maxOrder}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
								<div class="channel-item">
									<span>收益比例提成</span> <input  class="channel-item-input" name="partnerIndirectRule.secondLadder.commissionRatio"
										value="${it.partnerIndirectRule.secondLadder.commissionRatio}"></input> <span> </span>
								</div>
							</div>
							<br/>
							<div class="channel-row">
								<div class="channel-item">
									<span><strong>第三梯队：</strong></span> <span>最小签单额 </span> <input class="channel-item-input"
										name="partnerIndirectRule.threeLadder.minAmount" value="${it.partnerIndirectRule.threeLadder.minAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大签单额</span> <input class="channel-item-input" name="partnerIndirectRule.threeLadder.maxAmount" 
										value="${it.partnerIndirectRule.threeLadder.maxAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最小订单量</span> <input class="channel-item-input" name="partnerIndirectRule.threeLadder.minOrder" 
										value="${it.partnerIndirectRule.threeLadder.minOrder}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大订单量</span> <input class="channel-item-input" name="partnerIndirectRule.threeLadder.maxOrder" 
										value="${it.partnerIndirectRule.threeLadder.maxOrder}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
								<div class="channel-item">
									<span>收益比例提成</span> <input  class="channel-item-input" name="partnerIndirectRule.threeLadder.commissionRatio"
										value="${it.partnerIndirectRule.threeLadder.commissionRatio}"></input> <span> </span>
								</div>
							</div>
							<br/>
						</div>
					</div>
					<div class="channel-row">
								<div class="channel-item">
									<span><strong>统计周期 </strong></span> <input  name="statisticsPeriod" value="${it.statisticsPeriod}" class="channel-item-input" ></input>
									<span><strong>周期内的每单奖励 </strong></span> <input  name="orderAward" value="${it.orderAward}" class="channel-item-input" ></input>
									<span><strong>周期达标数量</strong></span><input class="channel-item-input" name="periodCount" value="${it.periodCount}">
								</div>
								</input> <span><strong>周期内达标奖励</strong><input class="channel-item-input" name="periodOrderAward" value="${it.periodOrderAward}"></span>
							</div>
					<div class="box-footer">
						<button style="margin-top: 0px; margin-left: 750 ;" type="button" id="partnerIncomeBtn" class="btn btn-primary" data="partnerCompanyRule">保存</button>
					</div>


			</div>
		</div>
		</div>
	</section>
</table>
</form>
