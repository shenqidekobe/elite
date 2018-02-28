
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="eliteFormId"/>
	<table class="table table-bordered" id="tab">
 <section class="content">
              <div class="col-md-12" style="min-width: 950px;">
                          
                          <div>
                           <ul class="nav nav-tabs">
				<li class="active"><a href="" data-toggle="tab">人才设置</a></li>
			</ul>
			<div id="myTabContent" class="tab-content ">
				<div id="tab_2" class="tab-pane fade in active">
					<div>
						<h4 class="col-xs-3">精英活跃度规则设置</h4>
					</div>
					<br />
					<hr class="setting-hr" />
					<div class="box-body" style="margin-top: 20px;">
						<div class="channel-row">
							<div class="channel-item">
								<span>活跃分 = 已完成任务数 *  </span>
							<input hidden="true" name="dicts[0].dictKey" value="counter_donetask"></input>
							<input class="channel-item-input" name="dicts[0].dictVal" value="${counter_donetask}"></input>
								<span>分 + 运行中任务数 * </span>
								<input hidden="true" name="dicts[1].dictKey" value="counter_doingtask"></input>
							<input class="channel-item-input" name="dicts[1].dictVal" value="${counter_doingtask}"></input>
								<span>分</span>
							</div>
						</div>
					</div>
					<br />
					<div class="box-footer">
						<button style="margin-top: 0px; margin-left: 62%;" type="button" id="eliteCouBtn" class="btn btn-primary"> 保存 </button>
					</div>
					<div>
						<h4 class="col-xs-3">明星团队基础条件</h4>
					</div>
					<br />
					<hr class="setting-hr" />
					<div class="box-body" style="margin-top: 0px;">
						<div class="person-row">
							<div class="person-title">CTO:</div>
							<div class="person-content">
								<div class="person-item">
									<span>评分 > </span>
									<input hidden="true" name="dicts[2].dictKey" value="cto_score"></input>
							<input class="channel-item-input" name="dicts[2].dictVal" value="${cto_score}"></input>
									<span>个</span>
								</div>
								<div class="person-item">
									<span>已完成项目数 > </span>
								<input hidden="true" name="dicts[3].dictKey" value="cto_doneproject"></input>
							<input class="channel-item-input" name="dicts[3].dictVal" value="${cto_doneproject}"></input>
									<span>个</span>
								</div>
								<div class="person-item">
									<span>已完成任务数 > </span>
									<input hidden="true" name="dicts[4].dictKey" value="cto_donetask"></input>
							<input class="channel-item-input" name="dicts[4].dictVal" value="${cto_donetask}"></input>
									<span>个</span>
								</div>
								<div class="person-item">
									<span>已完成项目额 > </span>
								<input hidden="true" name="dicts[5].dictKey" value="cto_doneprojectcost"></input>
							<input class="channel-item-input" name="dicts[5].dictVal" value="${cto_doneprojectcost}"></input>
									<span>元</span>
								</div>
								<div class="person-item">
									<span>已完成任务额 > </span>
									<input hidden="true" name="dicts[6].dictKey" value="cto_donetaskcost"></input>
							     <input class="channel-item-input" name="dicts[6].dictVal" value="${cto_donetaskcost}"></input>
									<span>元</span>
								</div>
							</div>
						</div>
						<div class="person-row">
							<div class="person-title">精英:</div>
							<div class="person-content">
								<div class="person-item">
									<span>评分 > </span>
									<input hidden="true" name="dicts[7].dictKey" value="elist_score"></input>
							<input class="channel-item-input" name="dicts[7].dictVal" value="${elist_score}"></input>
									<span>个</span>
								</div>
								<div class="person-item">
									<span>已完成任务数 > </span>
								<input hidden="true" name="dicts[8].dictKey" value="elist_donetask"></input>
							<input class="channel-item-input" name="dicts[8].dictVal" value="${elist_donetask}"></input>
									<span>个</span>
								</div>
								<div class="person-item">
									<span>已完成任务额 > </span>
									<input hidden="true" name="dicts[9].dictKey" value="elist_donetaskcost"></input>
							<input class="channel-item-input" name="dicts[9].dictVal" value="${elist_donetaskcost}"></input>
									<span>元</span>
								</div>
							</div>
						</div>
					</div>
					<br />
					<div class="box-footer">
						<button style="margin-top: 0px; margin-left: 62%;" type="button"  id="eliteBtn"class="btn btn-primary"> 保存 </button>
						
					</div>
				</div>
			
				
			</div>
		</div>
       </div>
</section>  
</table>