
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="partnerFormId"/>
<table class="table table-bordered" >
 <section class="content">
                    <div class="col-md-12" style="min-width: 950px;">
                                
                                <div>
	                                <ul class="nav nav-tabs">
										<li class="active"><a href="#tab_4" data-toggle="tab">渠道设置</a></li>
									</ul>
									<div id="myTabContent" class="tab-content">
										<div id="tab_3" class="tab-pane fade in active"">
											<div>
												<h4 class="col-xs-3">项目渠道设置</h4>
											</div>
											<br />
											<hr class="setting-hr" />
											<div class="box-body" style="margin-top: 20px;">
												<div class="channel-row">
													<div class="channel-item">
														<span>备案数 > </span>
														<input hidden="true" name="dicts[0].dictKey" value="project_firstrecordcount"></input>
													<input class="channel-item-input" name="dicts[0].dictVal" value="${project_firstrecordcount}"></input>
														<span>个</span>
													</div>
													<div class="channel-item">
														<span>入驻项目方托管费用合计 ></span>
													<input hidden="true" name="dicts[1].dictKey" value="project_firstSettledtotalcost"></input>
													<input class="channel-item-input" name="dicts[1].dictVal" value="${project_firstSettledtotalcost}"></input>
														<span>元</span>
													</div>
													<div class="channel-item">
														<span>渠道佣金比例 > </span>
												<input hidden="true" name="dicts[2].dictKey" value="project_firstcommissionscale"></input>
													<input class="channel-item-input" name="dicts[2].dictVal" value="${project_firstcommissionscale}"></input>
														<span>%</span>
													</div>
												</div>
												<br/>
												<div class="channel-row">
													<div class="channel-item">
														<span>备案数 > </span>
														<input hidden="true" name="dicts[3].dictKey" value="project_secondrecordcount"></input>
													<input class="channel-item-input" name="dicts[3].dictVal" value="${project_secondrecordcount}"></input>
														<span>个</span>
													</div>
													<div class="channel-item">
														<span>入驻项目方托管费用合计 ></span>
														<input hidden="true" name="dicts[4].dictKey" value="project_sceondSettledtotalcost"></input>
													<input class="channel-item-input" name="dicts[4].dictVal" value="${project_sceondSettledtotalcost}"></input>
														<span>元</span>
													</div>
													<div class="channel-item">
														<span>渠道佣金比例 > </span>
														<input hidden="true" name="dicts[5].dictKey" value="project_secondcommissionscale"></input>
													<input class="channel-item-input" name="dicts[5].dictVal" value="${project_secondcommissionscale}"></input>
														<span>%</span>
													</div>
												</div>
												<br/>
												<div class="channel-row">
													<div class="channel-item">
														<span>备案数 > </span>
														<input hidden="true" name="dicts[6].dictKey" value="project_thirdrecordcount"></input>
													<input class="channel-item-input" name="dicts[6].dictVal" value="${project_thirdrecordcount}"></input>
														<span>个</span>
													</div>
													<div class="channel-item">
														<span>入驻项目方托管费用合计 ></span>
														<input hidden="true" name="dicts[7].dictKey" value="project_thirdSettledtotalcost"></input>
													<input class="channel-item-input" name="dicts[7].dictVal" value="${project_thirdSettledtotalcost}"></input>
														<span>元</span>
													</div>
													<div class="channel-item">
														<span>渠道佣金比例 > </span>
														<input hidden="true" name="dicts[8].dictKey" value="project_thirdcommissionscale"></input>
													<input class="channel-item-input" name="dicts[8].dictVal" value="${project_thirdcommissionscale}"></input>
														<span>%</span>
													</div>
												</div>
												<br/>
												<div class="channel-row">
													<div class="channel-item">
														<span>项目渠道佣金 = 备案数 *  </span>
														<input hidden="true" name="dicts[9].dictKey" value="project_recordcount"></input>
													<input class="channel-item-input" name="dicts[9].dictVal" value="${project_recordcount}"></input>
														<span>元 + 入驻数 * </span>
														<input hidden="true" name="dicts[10].dictKey" value="counter_donetask"></input>
													<input class="channel-item-input" name="dicts[10].dictVal" value="${counter_donetask}"></input>
														<span>元 + 入驻人员所获酬金合计 * 渠道佣金比例</span>
													</div>
												</div>
											</div>
											<br/>
											<div class="box-footer">
												<button style="margin-top: 0px; margin-left: 62%;" type="button" id="partnerCouBtn" class="btn btn-primary"> 保存 </button>
											</div>

											<div>
												<h4 class="col-xs-3">精英渠道设置</h4>
											</div>
											<br />
											<hr class="setting-hr" />
											<div class="box-body" style="margin-top: 20px;">
												<div class="channel-row">
													<div class="channel-item">
														<span>备案数 > </span>
														<input hidden="true" name="dicts[11].dictKey" value="elite_firstrecordcount"></input>
													<input class="channel-item-input" name="dicts[11].dictVal" value="${elite_firstrecordcount}"></input>
														<span>个</span>
													</div>
													<div class="channel-item">
														<span>入驻人员所获酬金合计 ></span>
														<input hidden="true" name="dicts[12].dictKey" value="elite_firstSettledtotalcost"></input>
													<input class="channel-item-input" name="dicts[12].dictVal" value="${elite_firstSettledtotalcost}"></input>
														<span>元</span>
													</div>
													<div class="channel-item">
														<span>渠道佣金比例 > </span>
														<input hidden="true" name="dicts[13].dictKey" value="elite_firstcommissionscale"></input>
													<input class="channel-item-input" name="dicts[13].dictVal" value="${elite_firstcommissionscale}"></input>
														<span>%</span>
													</div>
												</div>
												<br/>
												<div class="channel-row">
													<div class="channel-item">
														<span>备案数 > </span>
														<input hidden="true" name="dicts[14].dictKey" value="elite_secondrecordcount"></input>
													<input class="channel-item-input" name="dicts[14].dictVal" value="${elite_secondrecordcount}"></input>
														<span>个</span>
													</div>
													<div class="channel-item">
														<span>入驻人员所获酬金合计 ></span>
														<input hidden="true" name="dicts[15].dictKey" value="elite_sceondSettledtotalcost"></input>
													<input class="channel-item-input" name="dicts[15].dictVal" value="${elite_sceondSettledtotalcost}"></input>
														<span>元</span>
													</div>
													<div class="channel-item">
														<span>渠道佣金比例 > </span>
														<input hidden="true" name="dicts[16].dictKey" value="elite_secondcommissionscale"></input>
													<input class="channel-item-input" name="dicts[16].dictVal" value="${elite_secondcommissionscale}"></input>
														<span>%</span>
													</div>
												</div>
												<br/>
												<div class="channel-row">
													<div class="channel-item">
														<span>备案数 > </span>
														<input hidden="true" name="dicts[17].dictKey" value="elite_thirdrecordcount"></input>
													<input class="channel-item-input" name="dicts[17].dictVal" value="${elite_thirdrecordcount}"></input>
														<span>个</span>
													</div>
													<div class="channel-item">
														<span>入驻人员所获酬金合计 ></span>
														<input hidden="true" name="dicts[18].dictKey" value="elite_thirdSettledtotalcost"></input>
													<input class="channel-item-input" name="dicts[18].dictVal" value="${elite_thirdSettledtotalcost}"></input>
														<span>元</span>
													</div>
													<div class="channel-item">
														<span>渠道佣金比例 > </span>
														<input hidden="true" name="dicts[19].dictKey" value="elite_thirdcommissionscale"></input>
													<input class="channel-item-input" name="dicts[19].dictVal" value="${elite_thirdcommissionscale}"></input>
														<span>%</span>
													</div>
												</div>
												<br/>
												<div class="channel-row">
													<div class="channel-item">
														<span>项目渠道佣金 = 备案数 *  </span>
														<input hidden="true" name="dicts[20].dictKey" value="elite_recordcount"></input>
													<input class="channel-item-input" name="dicts[20].dictVal" value="${elite_recordcount}"></input>
														<span>元 + 入驻数 * </span>
													<input hidden="true" name="dicts[21].dictKey" value="elite_settledcost"></input>
													<input class="channel-item-input" name="dicts[21].dictVal" value="${elite_settledcost}"></input>
														<span>元 + 入驻人员所获酬金合计 * 渠道佣金比例</span>
													</div>
												</div>
											</div>
											<br/>
											<div class="box-footer">
												<button style="margin-top: 0px; margin-left: 62%;"  id="partnerBtn" type="button" class="btn btn-primary"> 保存 </button>
											</div>

										</div>
									</div>
                        </div>
                </section>  
</table>
</form>
