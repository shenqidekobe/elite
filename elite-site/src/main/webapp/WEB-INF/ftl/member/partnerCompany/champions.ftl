  
  <div id="channelMain">
  <div class="channelRight">
   <form id="searchForm">
 <ul class="rolling">
 
                   <!-- <li>恭喜Rose排名升级为第3名</li>
                    <li>恭喜Rose获得1000元收益</li>-->
                </ul>
             
                <div id="championBox">
                    <div class="choose_title" style="padding-top:20px">
                        <ul class="title_ul"style="padding-top:0px" id="selectChampionsPan">
                            <li class="active_color" data="myTeam">我推荐的人组成的团队</li>
                            <#if company.parentId??>
                            <li data="myParentTeam">我所在的团队</li>
                            </#if>
                        </ul>
                        <div class="title_line" id="title_line"></div>
                    </div>
                    <div class="choose_content" >
                        <div class="myTeam" >
                            <div class="billboard" id="teamTitleId">我的团队龙虎榜</div>
                            <div class="money_div">
                               <input type="hidden" id="searchTypeId" name="searchType" value="myTeam">
                                <div class="money" id="myTeam">
                                    <div class="div1" id="showTime">&nbsp</div>
                                    <div class="div2"><span>我的团队为我创造的收益总额</span><span id="myIncomeId">${myIncome}元</span></div>
                                </div>
                                <div class="money" id="myParentTeam" hidden>
                                    <div class="div1">目前为止</div>
                                    <div class="div2"><span>您的排名</span><span id="myRankingId"></span></div>
                                </div>
                                <div class="time">
                                    <div class="start">
                                        <input type="text" placeholder="选择开始时间" id="startTimeId" name="startTime">
                                        <span class="time_icon" id="startTimeIconId"></span>
                                    </div>
                                    <div class="form_line"></div>
                                    <div class="end">
                                        <input type="text" placeholder="选择结束时间" id="endTimeId" name="endTime">
                                        <span class="time_icon" id="endTimeIconId"></span>
                                    </div>
                                    <button type="button" class="search" id="searChampions"><span class="search_icon"></span><span class="search_text">搜索</span></button>
                                </div>
                            </div>
                          <div id="datalist">
                          </div>
                        </div>
                    </div>
                    <div class="rule">
                        <h1><img src="../../../../../res/images/partner/tips.png">收益规则说明</h1>
                        <dl class="yieldStructure">
                            <dt>渠道收益结构:<br/>A=L1*Q1+L2*Q2+L3*Q3</dt>
                            <dd>L：代表层级，Q代表提成比例</dd>
                            <dd>L1：我推荐的项目总额</dd>
                            <dd>L2：我推荐的人（渠道）产生的项目总额（二级）</dd>
                            <dd>L3：二级推荐的人（渠道）产生的项目总额（三级）</dd>
                            <dd>Q1：L1适用的比例,&nbsp;&nbsp;&nbsp;&nbsp;Q2：L2适用的比例，&nbsp;&nbsp;&nbsp;&nbsp;Q3：L3适用的比例。</dd>
                        </dl>
                        <h2>本人发展项目提成比例Q核算准则表</h2>

                        <div class="table_div" id="companyTable">
                            <div class="text1">层次</div>
                            <div class="line"></div>
                            <div class="text2"><span>累计项目总额</span><span>和订单数</span></div>
                            <table>
                                <thead>
                                <tr>
                                    <th width="72"></th>
                                	<th width="170" style="border-right: 1px solid #fff;text-align:center;"><span class="th_span1" style="margin-left: 60px;">${rule.partnerOwnRule.firstLadder.minAmount/10000?round}-${rule.partnerOwnRule.firstLadder.maxAmount/10000?round}万<span class="small">(不含)</span></span><span class="th_span2" style="margin-left: 60px;">${rule.partnerOwnRule.firstLadder.minOrder}-${rule.partnerOwnRule.firstLadder.maxOrder}单</span></th>
                                    <th style="border-right: 1px solid #fff ;text-align: center;"><span class="th_span1">${rule.partnerOwnRule.secondLadder.minAmount/10000?round}万-${rule.partnerOwnRule.secondLadder.maxAmount/10000?round}万<span class="small">(不含)</span><span><span class="th_span2">${rule.partnerOwnRule.secondLadder.minOrder}-${rule.partnerOwnRule.secondLadder.maxOrder}单</span></th>
                                    <th style="text-align: center;"><span class="th_span1">${rule.partnerOwnRule.threeLadder.minAmount/10000?round}万-${rule.partnerOwnRule.threeLadder.maxAmount/10000?round}万</span><span class="th_span2">${rule.partnerOwnRule.threeLadder.minOrder}-${rule.partnerOwnRule.threeLadder.maxOrder}单</span></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td style="border-bottom: 1px solid #fff">L1</td>
                                    <td style="padding-left: 60px;">${rule.partnerOwnRule.firstLadder.commissionRatio*100}%</td>
                                    <td>${rule.partnerOwnRule.secondLadder.commissionRatio*100}%</td>
                                    <td>${rule.partnerOwnRule.threeLadder.commissionRatio*100}%</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <h2>直接渠道项目提成比例Q核算准则表</h2>

                        <div class="table_div" id="companyTable">
                            <div class="text1">层次</div>
                            <div class="line"></div>
                            <div class="text2"><span>累计项目总额</span><span>和订单数</span></div>
                            <table>
                                <thead>
                                <tr>
                                    <th width="72"></th>
                                	<th width="170" style="border-right: 1px solid #fff;text-align:center;"><span class="th_span1" style="margin-left: 60px;">${rule.partnerDirectRule.firstLadder.minAmount/10000?round}-${rule.partnerDirectRule.firstLadder.maxAmount/10000?round}万<span class="small">(不含)</span></span><span class="th_span2" style="margin-left: 60px;">${rule.partnerDirectRule.firstLadder.minOrder}-${rule.partnerDirectRule.firstLadder.maxOrder}单</span></th>
                                    <th style="border-right: 1px solid #fff ;text-align: center;"><span class="th_span1">${rule.partnerDirectRule.secondLadder.minAmount/10000?round}万-${rule.partnerDirectRule.secondLadder.maxAmount/10000?round}万<span class="small">(不含)</span><span><span class="th_span2">${rule.partnerDirectRule.secondLadder.minOrder}-${rule.partnerDirectRule.secondLadder.maxOrder}单</span></th>
                                    <th style="text-align: center;"><span class="th_span1">${rule.partnerDirectRule.threeLadder.minAmount/10000?round}万-${rule.partnerDirectRule.threeLadder.maxAmount/10000?round}万</span><span class="th_span2">${rule.partnerDirectRule.threeLadder.minOrder}-${rule.partnerDirectRule.threeLadder.maxOrder}单</span></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td style="border-bottom: 1px solid #fff">L2</td>
                                    <td style="padding-left: 60px;">${rule.partnerDirectRule.firstLadder.commissionRatio*100}%</td>
                                    <td>${rule.partnerDirectRule.secondLadder.commissionRatio*100}%</td>
                                    <td>${rule.partnerDirectRule.threeLadder.commissionRatio*100}%</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <h2>间接渠道项目提成比例Q核算准则表</h2>

                        <div class="table_div" id="companyTable">
                            <div class="text1">层次</div>
                            <div class="line"></div>
                            <div class="text2"><span>累计项目总额</span><span>和订单数</span></div>
                            <table>
                                 <thead>
                                <tr>
                                    <th width="72"></th>
                                	<th width="170" style="border-right: 1px solid #fff;text-align:center;"><span class="th_span1" style="margin-left: 60px;">${rule.partnerIndirectRule.firstLadder.minAmount/10000?round}-${rule.partnerIndirectRule.firstLadder.maxAmount/10000?round}万<span class="small">(不含)</span></span><span class="th_span2" style="margin-left: 60px;">${rule.partnerIndirectRule.firstLadder.minOrder}-${rule.partnerIndirectRule.firstLadder.maxOrder}单</span></th>
                                    <th style="border-right: 1px solid #fff ;text-align: center;"><span class="th_span1">${rule.partnerIndirectRule.secondLadder.minAmount/10000?round}万-${rule.partnerIndirectRule.secondLadder.maxAmount/10000?round}万<span class="small">(不含)</span><span><span class="th_span2">${rule.partnerIndirectRule.secondLadder.minOrder}-${rule.partnerIndirectRule.secondLadder.maxOrder}单</span></th>
                                    <th style="text-align: center;"><span class="th_span1">${rule.partnerIndirectRule.threeLadder.minAmount/10000?round}万-${rule.partnerIndirectRule.threeLadder.maxAmount/10000?round}万</span><span class="th_span2">${rule.partnerIndirectRule.threeLadder.minOrder}-${rule.partnerIndirectRule.threeLadder.maxOrder}单</span></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td style="border-bottom: 1px solid #fff">L3</td>
                                    <td style="padding-left: 60px;">${rule.partnerIndirectRule.firstLadder.commissionRatio*100}%</td>
                                    <td>${rule.partnerIndirectRule.secondLadder.commissionRatio*100}%</td>
                                    <td>${rule.partnerIndirectRule.threeLadder.commissionRatio*100}%</td>
                                </tr>
                                </tbody>
                                </tbody>
                            </table>
                        </div>

                       
                        <dl class="ruleDescription">
                            <dt>规则说明：</dt>
                            <dd><span class="dd_icon"></span><span class="dd_text">渠道收益采用累积清空制，</span></dd>
                            <dd><span class="dd_icon"></span><span class="dd_text">累积标准以项目各阶段验收为准。</span></dd>
                            <dd><span class="dd_icon"></span><span class="dd_text">每阶段验收通过，渠道收益增加。</span></dd>
                            <dd><span class="dd_icon"></span>进入下一个额度量级，则全部收益Q变化，非分段计算。</span></dd>
                        </dl>
                    </div>
                </div>
   </form>  
</div>
</div>

  <!-- 待完善弹窗 -->
    <div class="window"  id="goprefectBoxId" hidden>
    	<div class="window_bg"></div>
    	<form class="windowForm" id="gocloseId">
    		<div class="close_btn"></div>
    		<div class="logoDiv"><span class="logo failure"></span></div>
    		<div class="textDiv failure_text">你的资料完善度还没达到审核要求，先完善哦~</div>
    		<div class="btnDiv"><button type="button" class="saveBtn" id="goPrefectBtn">去完善</button></div>
    	</form>
    </div>
