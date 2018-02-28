
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="qualityFormId"/>
<table class="table table-bordered" >
 <section class="content">
                    <div class="col-md-12" style="min-width: 950px;">
                                
                                <div>
	                                <ul class="nav nav-tabs">
									    <li class="active"><a href="#tab_1" data-toggle="tab">项目设置</a></li>
									</ul>
									<div id="myTabContent" class="tab-content">
										<div id="tab_1" class="tab-pane fade in active">
											<div>
												<h4 class="col-xs-3">质保设置</h4>
											</div>
											<br />
											<hr class="setting-hr" />
											<div class="box-body" style="margin-top: 20px;">
												<div class="person-item">
													<span>项目质保金比例: </span>
													<input hidden="true" name="dicts[0].dictKey" value="quality_projectscale"></input>
													<input class="channel-item-input" name="dicts[0].dictVal" value="${quality_projectscale}"></input>
													<span>%</span>
												</div>
												<div class="person-item">
													<span>任务质保金比例: </span>
													<input hidden="true" name="dicts[1].dictKey" value="quality_taskscale"></input>
													<input class="channel-item-input" name="dicts[1].dictVal" value="${quality_taskscale}"></input>
													<span>%</span>
												</div>
												<div class="person-item">
													<span>任务质保时长: </span>
													<input hidden="true" name="dicts[2].dictKey" value="quality_tasktime"></input>
													<input class="channel-item-input" name="dicts[2].dictVal" value="${quality_tasktime}"></input>
													<span>% * 任务周期</span>
												</div>
												<div class="person-item">
													<span>任务质保时长最短: </span>
													<input hidden="true" name="dicts[3].dictKey" value="quality_taskminday"></input>
													<input class="channel-item-input" name="dicts[3].dictVal" value="${quality_taskminday}"></input>
													<span>天</span>
												</div>
											</div>

											<div class="box-footer">
												<button style="margin-top: 0px; margin-left: 20%;" type="button"  id="qualityBtn" class="btn btn-primary"> 保存 </button>
											</div>


											<br /><br />
								
										</div>
									</div>
                        </div>
                </section>  
</table>
</form>
