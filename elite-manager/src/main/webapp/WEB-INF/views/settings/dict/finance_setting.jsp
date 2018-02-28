
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="financeFormId"/>
<table class="table table-bordered" >
 <section class="content">
            <div class="col-md-12" style="min-width: 950px;">
                        <div>
                         <ul class="nav nav-tabs">
		<li class="active"><a href="#tab_4" data-toggle="tab">财务设置</a></li>
	</ul>
	<div id="myTabContent" class="tab-content">
		<div id="tab_4" class="tab-pane fade in active"">`	
			<div>
				<h4 class="col-xs-3">提现设置</h4>
			</div>
			<br/>
			<hr class="setting-hr" />
			<div class="box-body" style="margin-top: 20px;">
		
				<div class="person-item">
					<span>每次提现额最低额: </span>
					<input hidden="true" name="dicts[0].dictKey" value="finance_withdrawalsmin" ></input>
					<input  class="channel-item-input"  id="financeKey"name="dicts[0].dictVal" value="${finance_withdrawalsmin}"></input>
					<span>元</span>
				</div>
				<div class="person-item">
				
					<span>提现所抽取佣金比例: </span>
						
					<input hidden="true" name="dicts[1].dictKey"  value="finance_commissionscale"></input>
					<input class="channel-item-input"  name="dicts[1].dictVal"value="${finance_commissionscale}"></input>
				
					<span>%</span>
				</div>
				<div class="person-item">
					<span>每日提现次数限制: </span>
					<input hidden="true" name="dicts[2].dictKey" value="finance_Withdrawalslimitcount"></input>
					<input class="channel-item-input" name="dicts[2].dictVal"value="${finance_Withdrawalslimitcount}"></input>
					<span>次</span>
				</div>
		  
                           </div>
                           <div class="box-footer">
				<button style="margin-top: 0px; margin-left: 20%;" type="button" class="btn btn-primary"  id="financeBtn"> 保存 </button>
			</div>
				
		</div>
	</div>
                </div>
        </section>  
</table>
</form>	