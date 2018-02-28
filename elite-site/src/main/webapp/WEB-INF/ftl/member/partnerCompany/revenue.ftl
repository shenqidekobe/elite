 <div id="revenue">
	    <div class="earnings_center">
	    	<h1>收益中心</h1>
	        <div class="earnBox">
	        	<span class="earn_text">我的累计收益:</span>
	        	<span class="earn_number">￥${account.totalIncome}</span>
	    	</div>			
	    	<div class="withdrawal_number">
	            <span class="number_t">当前可提现</span>
	            <span class="number_n">￥${account.balance}</span>
	            <input type="hidden" value="${account.balance}" id="withdrawAccount"/>
	            <input type="hidden" id="iscardId" value="${credit.isCard}">
	        </div>
	        <div class="withdrawal-btn" id="withdrawBtn">提现</div>
	    </div>
	    
	    <#--表格专区-->
	    <div class="datalistBox" >
	    <div class="earnings_construction" >
	    <div>
	    <form class="form_money" id="detail_form">
		<h1>
			<span>收益结构分布</span>
				<div class="timeDivleft" >
				<span class="time_text" id="startTimeShow">开始时间</span>
				<span class="time_icon" id="startTimeIcon"></span>
				<input type="hidden" name="startTime" id="startTimeId"/>
			</div>
				<div class="timeDiv" >
				<span class="time_text" id="endTimeShow">结束时间</span>
				<span class="time_icon" id="endTimeIcon"></span>
				<input type="hidden" name="endTime" id="endTimeId"/>
			</div>
			<div class="searchBtn" id="revenueDetailBtn">
				<span class="search_icon"></span>
				<span class="search_text">搜索</span>
			</div>
		</h1>
		<div id="pigNodataId" hidden>&nbsp;&nbsp;暂无数据</div>
		<div class="distribution" id="pigChatId">
			<div class="box1">
				<div class="imgDiv">
					  <div id="container" style="min-width:500px;height:300px"></div>
				</div>
				<input type="hidden" name="searchType" id="searchTypeId" value="partner_own"/>
				<h2><span>我的收益结构分布图</span><span></span></h2>
			</div>
			<br/>
			<div class="box2">
			 <div id="revenuedetailData">
		         <#--我推荐的人才收益-->
                <div class="template template_mine">
					<div class="template_head">
						<div class="channel_div"><span class="channel_title" id="channelTitleId">我推荐的项目收益:</span><span class="channel_money" id="channelMoneyId"></span></div>
						<div class="results_div">
							<span class="results_title">已验收金额:</span><span class="results_money" id="partnerEarnId"></span>
							<span class="results_title">,适用提成比例:</span><span class="results_money" id="partnerProportionId"></span>
						</div>
					</div>
					<div class="template_body">
						<div class="tableBox tableBox_mine" id="earnListData">
							
						</div>
					</div>
				</div>
				
				
				<#--直接渠道收益-->
			
				<#--间接渠道收益-->
			
				<#--其他人推荐-->
               <div id="projectListData">
			
				</div>
			</div>
		</div>
	</form>
	    </div>
	    <div>
	  <form class="form_accounts" id="incomeForm">
		<h1>
			<span>结算记录</span>
			<div class="recordBox">
				<ul class="record_ul" id="selectRecordUl">
					<li class="active_color" data="income">收益记录</li>
					<li data="withdraw">提现记录</li>
				</ul>
				<div class="li_line"></div>
			</div>
		</h1>
		
		<#--搜索-->
		
		<div class="searchBox" id="incomeSearchBoxId">
			<input type="text" placeholder="来源姓名/项目/任务" class="search_input" name="keyword" style="width:142px">
			<input type="hidden" name="searchType" id="incomeOwnSearchTypeId" value=""/>
			<input type="hidden" name="searchType" id="incomeDirectSearchTypeId" value=""/>
			<input type="hidden" name="searchType" id="incomeIndirectSearchTypeId" value=""/>
			<ul class="search_ul" id="incomeSearchType" style="left:148px">
				<li><span class="li_icon nocheck" data="partner_own" id="ownselectspan"><span class="check_icon"></span></span><span class="li_text">项目备案</span></li>
				<li><span class="li_icon nocheck" data="partner_direct" id="directselectspan"><span class="check_icon"></span></span><span class="li_text">直接渠道</span></li>
				<li><span class="li_icon nocheck" data="partner_indirect" id="indirectselectspan"><span class="check_icon"></span></span><span class="li_text">间接渠道</span></li>
			</ul>
		   <div class="time_divleft" style="right:238px;width:125px" >
				<span class="time_text" id="inComeStartTShow">选择时间</span>
				<span class="time_icon" id="inComeStartTicon"></span>
				<input type="hidden" name="startTime" id="inComeStartTimeValId"/>
			</div>
			<div class="time_div" style="right:115px;width:125px" >
				<span class="time_text" id="inComeEndTShow">结束时间</span>
				<span class="time_icon" id="inComeEndTicon"></span>
				<input type="hidden" name="endTime" id="inComeEndTimeValId"/>
			</div>
			<div class="searchBtn" style="right:12px">
				<span class="search_icon" id="incomeSearchBtnIcon"></span>
				<span class="search_text" id="incomeSearchBtn">搜索</span>
			</div>
		</div>
		<#--核算时间-->
		<div class="table_accounting" id="incomeListData">
		</div>
		
		<#--提现时间-->
		
	</form>
	</div>
	         
	    
	    </div>
	    </div>
 </div>

  <!-- 待完善弹窗 -->
    <div class="window"  id="goprefectBoxId" hidden>
    	<div class="window_bg"></div>
    	<form class="windowForm">
    		<div class="close_btn" id="gocloseId"></div>
    		<div class="logoDiv"><span class="logo failure"></span></div>
    		<div class="textDiv failure_text">你的资料完善度还没达到审核要求，先完善哦~</div>
    		<div class="btnDiv"><button type="button" class="saveBtn" id="goPrefectBtn">去完善</button></div>
    	</form>
    </div>