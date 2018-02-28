<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="partnerCompanyFromId" />
<table class="table table-bordered">
	<section class="content">
		<div class="col-md-12" style="min-width: 950px;">

			<div>
				<ul class="nav nav-tabs">
					<li class="active"><a href="#tab_1" data-toggle="tab">人才渠道本人发展的规则</a></li>
					<li class=""><a href="#tab_2" data-toggle="tab">直接渠道规则</a></li>
					<li class=""><a href="#tab_3" data-toggle="tab">间接渠道规则</a></li>
				</ul>
				<div id="myTabContent" class="tab-content">
					<div id="tab_1" class="tab-pane fade in active"">
						<div>
							<h4 class="col-xs-3">人才渠道本人发展的规则</h4>
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
									<span>最小人才数</span> <input class="channel-item-input" name="partnerOwnRule.firstLadder.minElite" 
										value="${it.partnerOwnRule.firstLadder.minElite}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大人才数</span> <input class="channel-item-input" name="partnerOwnRule.firstLadder.maxElite" 
										value="${it.partnerOwnRule.firstLadder.maxElite}"></input> <span> </span>
								</div>
							</div>
							<br/>
							<div class="channel-row" style="margin-left: 60px;">
					     	  <div class="channel-item">
									<span>最小任务数</span> <input class="channel-item-input" name="partnerOwnRule.firstLadder.minTask" 
										value="${it.partnerOwnRule.firstLadder.minTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大任务数</span> <input class="channel-item-input" name="partnerOwnRule.firstLadder.maxTask" 
										value="${it.partnerOwnRule.firstLadder.maxTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>适用比例</span> <input  class="channel-item-input" name="partnerOwnRule.firstLadder.commissionRatio"
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
									<span>最小人才数</span> <input class="channel-item-input" name="partnerOwnRule.secondLadder.minElite" 
										value="${it.partnerOwnRule.secondLadder.minElite}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大人才数</span> <input class="channel-item-input" name="partnerOwnRule.secondLadder.maxElite" 
										value="${it.partnerOwnRule.secondLadder.maxElite}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
					     	  <div class="channel-item">
									<span>最小任务数</span> <input class="channel-item-input" name="partnerOwnRule.secondLadder.minTask" 
										value="${it.partnerOwnRule.secondLadder.minTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大任务数</span> <input class="channel-item-input" name="partnerOwnRule.secondLadder.maxTask" 
										value="${it.partnerOwnRule.secondLadder.maxTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>适用比例</span> <input  class="channel-item-input" name="partnerOwnRule.secondLadder.commissionRatio"
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
									<span>最小人才数</span> <input class="channel-item-input" name="partnerOwnRule.threeLadder.minElite" 
										value="${it.partnerOwnRule.threeLadder.minElite}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大人才数</span> <input class="channel-item-input" name="partnerOwnRule.threeLadder.maxElite" 
										value="${it.partnerOwnRule.threeLadder.maxElite}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
					     	  <div class="channel-item">
									<span>最小任务数</span> <input class="channel-item-input" name="partnerOwnRule.threeLadder.minTask" 
										value="${it.partnerOwnRule.threeLadder.minTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大任务数</span> <input class="channel-item-input" name="partnerOwnRule.threeLadder.maxTask" 
										value="${it.partnerOwnRule.threeLadder.maxTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>适用比例</span> <input  class="channel-item-input" name="partnerOwnRule.threeLadder.commissionRatio"
										value="${it.partnerOwnRule.threeLadder.commissionRatio}"></input> <span> </span>
								</div>
							</div>
							<br/>
					</div>
					</div>
					<div id="tab_2" class="tab-pane fade">
						<div>
							<h4 class="col-xs-3">直接收益规则</h4>
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
									<span>最小人才数</span> <input class="channel-item-input" name="partnerDirectRule.firstLadder.minElite" 
										value="${it.partnerDirectRule.firstLadder.minElite}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大人才数</span> <input class="channel-item-input" name="partnerDirectRule.firstLadder.maxElite" 
										value="${it.partnerDirectRule.firstLadder.maxElite}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
					     	  <div class="channel-item">
									<span>最小任务数</span> <input class="channel-item-input" name="partnerDirectRule.firstLadder.minTask" 
										value="${it.partnerDirectRule.firstLadder.minTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大任务数</span> <input class="channel-item-input" name="partnerDirectRule.firstLadder.maxTask" 
										value="${it.partnerDirectRule.firstLadder.maxTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>适用比例</span> <input  class="channel-item-input" name="partnerDirectRule.firstLadder.commissionRatio"
										value="${it.partnerDirectRule.firstLadder.commissionRatio}"></input> <span> </span>
								</div>
							</div>
							<br/>
							<div class="channel-row">
								<div class="channel-item">
									<span><strong>第二梯队：</strong></span> <span>最小签单额 </span> <input class="channel-item-input"
										name="partnerDirectRule.secondLadder.minAmount" value="${it.partnerDirectRule.secondLadder.minAmount}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大签单额</span> <input class="channel-item-input" name="partnerDirectRule.secondLadder.maxAmount" 
										value="${it.partnerDirectRule.secondLadder.maxAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最小人才数</span> <input class="channel-item-input" name="partnerDirectRule.secondLadder.minElite" 
										value="${it.partnerDirectRule.secondLadder.minElite}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大人才数</span> <input class="channel-item-input" name="partnerDirectRule.secondLadder.maxElite" 
										value="${it.partnerDirectRule.secondLadder.maxElite}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
					     	  <div class="channel-item">
									<span>最小任务数</span> <input class="channel-item-input" name="partnerDirectRule.secondLadder.minTask" 
										value="${it.partnerDirectRule.secondLadder.minTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大任务数</span> <input class="channel-item-input" name="partnerDirectRule.secondLadder.maxTask" 
										value="${it.partnerDirectRule.secondLadder.maxTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>适用比例</span> <input  class="channel-item-input" name="partnerDirectRule.secondLadder.commissionRatio"
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
									<span>最小人才数</span> <input class="channel-item-input" name="partnerDirectRule.threeLadder.minElite" 
										value="${it.partnerDirectRule.threeLadder.minElite}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大人才数</span> <input class="channel-item-input" name="partnerDirectRule.threeLadder.maxElite" 
										value="${it.partnerDirectRule.threeLadder.maxElite}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
					     	  <div class="channel-item">
									<span>最小任务数</span> <input class="channel-item-input" name="partnerDirectRule.threeLadder.minTask" 
										value="${it.partnerDirectRule.threeLadder.minTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大任务数</span> <input class="channel-item-input" name="partnerDirectRule.threeLadder.maxTask" 
										value="${it.partnerDirectRule.threeLadder.maxTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>适用比例</span> <input  class="channel-item-input" name="partnerDirectRule.threeLadder.commissionRatio"
										value="${it.partnerDirectRule.threeLadder.commissionRatio}"></input> <span> </span>
								</div>
							</div>
							<br/>
					</div>
					</div>
					<div id="tab_3" class="tab-pane ">
						<div>
							<h4 class="col-xs-3">间接收益规则</h4>
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
									<span>最小人才数</span> <input class="channel-item-input" name="partnerIndirectRule.firstLadder.minElite" 
										value="${it.partnerIndirectRule.firstLadder.minElite}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大人才数</span> <input class="channel-item-input" name="partnerIndirectRule.firstLadder.maxElite" 
										value="${it.partnerIndirectRule.firstLadder.maxElite}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
					     	  <div class="channel-item">
									<span>最小任务数</span> <input class="channel-item-input" name="partnerIndirectRule.firstLadder.minTask" 
										value="${it.partnerIndirectRule.firstLadder.minTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大任务数</span> <input class="channel-item-input" name="partnerIndirectRule.firstLadder.maxTask" 
										value="${it.partnerIndirectRule.firstLadder.maxTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>适用比例</span> <input  class="channel-item-input" name="partnerIndirectRule.firstLadder.commissionRatio"
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
									<span>最小人才数</span> <input class="channel-item-input" name="partnerIndirectRule.secondLadder.minElite" 
										value="${it.partnerIndirectRule.secondLadder.minElite}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大人才数</span> <input class="channel-item-input" name="partnerIndirectRule.secondLadder.maxElite" 
										value="${it.partnerIndirectRule.secondLadder.maxElite}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
					     	  <div class="channel-item">
									<span>最小任务数</span> <input class="channel-item-input" name="partnerIndirectRule.secondLadder.minTask" 
										value="${it.partnerIndirectRule.secondLadder.minTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大任务数</span> <input class="channel-item-input" name="partnerIndirectRule.secondLadder.maxTask" 
										value="${it.partnerIndirectRule.secondLadder.maxTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>适用比例</span> <input  class="channel-item-input" name="partnerIndirectRule.secondLadder.commissionRatio"
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
									<span>最小人才数</span> <input class="channel-item-input" name="partnerIndirectRule.threeLadder.minElite" 
										value="${it.partnerIndirectRule.threeLadder.minElite}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大人才数</span> <input class="channel-item-input" name="partnerIndirectRule.threeLadder.maxElite" 
										value="${it.partnerIndirectRule.threeLadder.maxElite}"></input> <span> </span>
								</div>
							</div>
							<br />
							<div class="channel-row" style="margin-left: 60px;">
					     	  <div class="channel-item">
									<span>最小任务数</span> <input class="channel-item-input" name="partnerIndirectRule.threeLadder.minTask" 
										value="${it.partnerIndirectRule.threeLadder.minTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大任务数</span> <input class="channel-item-input" name="partnerIndirectRule.threeLadder.maxTask" 
										value="${it.partnerIndirectRule.threeLadder.maxTask}"></input> <span> </span>
								</div>
								<div class="channel-item">
									<span>适用比例</span> <input  class="channel-item-input" name="partnerIndirectRule.threeLadder.commissionRatio"
										value="${it.partnerIndirectRule.threeLadder.commissionRatio}"></input> <span> </span>
								</div>
							</div>
							<br/>
					</div>
					</div>
					</div>
					 <hr/>
					<div class="channel-row">
								<div class="channel-item">
									<span><strong>CTO项目首单第一阶梯奖励梯：</strong></span> <span>最小金额 </span> <input class="channel-item-input"
										name="firstProjectOrderAward.minAmount" value="${it.firstProjectOrderAward.minAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大金额</span> <input class="channel-item-input" name="firstProjectOrderAward.maxAmount" 
										value="${it.firstProjectOrderAward.maxAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>订单奖励</span> <input class="channel-item-input" name="firstProjectOrderAward.orderAward" 
										value="${it.firstProjectOrderAward.orderAward}"></input> <span> </span>
								</div>
				 </div>
				 <br/>
					<div class="channel-row">
								<div class="channel-item">
									<span><strong>CTO项目首单第二阶梯奖励梯：</strong></span> <span>最小金额 </span> <input class="channel-item-input"
										name="secondProjectOrderAward.minAmount" value="${it.secondProjectOrderAward.minAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大金额</span> <input class="channel-item-input" name="secondProjectOrderAward.maxAmount" 
										value="${it.secondProjectOrderAward.maxAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>订单奖励</span> <input class="channel-item-input" name="secondProjectOrderAward.orderAward" 
										value="${it.secondProjectOrderAward.orderAward}"></input> <span> </span>
								</div>
				    </div>
				    <br/>
					<div class="channel-row">
								<div class="channel-item">
									<span><strong>CTO项目首单第三阶梯奖励梯：</strong></span> <span>最小金额 </span> <input class="channel-item-input"
										name="threeProjectOrderAward.minAmount" value="${it.threeProjectOrderAward.minAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>最大金额</span> <input class="channel-item-input" name="threeProjectOrderAward.maxAmount" 
										value="${it.threeProjectOrderAward.maxAmount}">元</input> <span> </span>
								</div>
								<div class="channel-item">
									<span>订单奖励</span> <input class="channel-item-input" name="threeProjectOrderAward.orderAward" 
										value="${it.threeProjectOrderAward.orderAward}"></input> <span> </span>
								</div>
				 </div>
				 <br/>
								<div class="channel-item">
									<span><strong>统计周期 </strong></span> <input  name="statisticsPeriod" value="${it.statisticsPeriod}" class="channel-item-input" ></input>
									<span><strong>精英人才任务首单奖励 </strong></span> <input  name="taskOrderAward" value="${it.taskOrderAward}" class="channel-item-input" ></input>
								</div>
							</div>
					<div class="box-footer">
						<button style="margin-top: 0px; margin-left: 750 ;" type="button" id="partnerIncomeBtn" class="btn btn-primary" data="partnerEliteRule">保存</button>
					</div>


			</div>
		</div>
		</div>
	</section>
</table>
</form>
