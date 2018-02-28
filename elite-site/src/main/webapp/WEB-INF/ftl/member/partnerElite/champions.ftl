  
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
                             <#if elite.parentId??>
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
                            <dd>L1：第一级推荐人才效益额合计（自身）</dd>
                            <dd>L2：第二级推荐人才效益额合计（直接渠道）</dd>
                            <dd>L3：第三级推荐人才效益额合计（间接渠道）</dd>
                            <dd>Q1：L1适用的比例,    Q2：L2适用的比例，  Q3：L3适用的比例。</dd>
                        </dl>
                        <h2>本人发展人才渠道之精英</h2>
                            <div class="table_div" id="companyTable">
                            <div class="text1">层次</div>
                            <div class="line"></div>
                              <div class="text2"><span>累计项目总额</span><span>和推荐人才数</span></div>
                            <table>
                                 <thead>
                                <tr>
                                    <th width="72"></th>
                                	<th width="170" style="border-right: 1px solid #fff;text-align:center;"><span class="th_span1" style="margin-left: 60px;">${rule.partnerOwnRule.firstLadder.minAmount/10000?round}-${rule.partnerOwnRule.firstLadder.maxAmount/10000?round}万<span class="small">(不含)</span></span><span class="th_span2" style="margin-left: 60px;">${rule.partnerOwnRule.firstLadder.minElite}-${rule.partnerOwnRule.firstLadder.maxElite}个</span></th>
                                    <th style="border-right: 1px solid #fff ;text-align: center;"><span class="th_span1">${rule.partnerOwnRule.secondLadder.minAmount/10000?round}万-${rule.partnerOwnRule.secondLadder.maxAmount/10000?round}万<span class="small">(不含)</span><span><span class="th_span2">${rule.partnerOwnRule.secondLadder.minElite}-${rule.partnerOwnRule.secondLadder.maxElite}个</span></th>
                                    <th style="text-align: center;"><span class="th_span1">${rule.partnerOwnRule.threeLadder.minAmount/10000?round}万-${rule.partnerOwnRule.threeLadder.maxAmount/10000?round}万</span><span class="th_span2">${rule.partnerOwnRule.threeLadder.minElite}-${rule.partnerOwnRule.threeLadder.maxElite}个</span></th>
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
                                </tbody>
                            </table>
                        </div>
                        <h2>直接渠道人才渠道之精英</h2>

                       <div class="table_div" id="companyTable">
                            <div class="text1">层次</div>
                            <div class="line"></div>
                              <div class="text2"><span>累计项目总额</span><span>和推荐人才数</span></div>
                            <table>
                                 <thead>
                                <tr>
                                    <th width="72"></th>
                                	<th width="170" style="border-right: 1px solid #fff;text-align:center;"><span class="th_span1" style="margin-left: 60px;">${rule.partnerDirectRule.firstLadder.minAmount/10000?round}-${rule.partnerDirectRule.firstLadder.maxAmount/10000?round}万<span class="small">(不含)</span></span><span class="th_span2" style="margin-left: 60px;">${rule.partnerDirectRule.firstLadder.minElite}-${rule.partnerDirectRule.firstLadder.maxElite}个</span></th>
                                    <th style="border-right: 1px solid #fff ;text-align: center;"><span class="th_span1">${rule.partnerDirectRule.secondLadder.minAmount/10000?round}万-${rule.partnerDirectRule.secondLadder.maxAmount/10000?round}万<span class="small">(不含)</span><span><span class="th_span2">${rule.partnerDirectRule.secondLadder.minElite}-${rule.partnerDirectRule.secondLadder.maxElite}个</span></th>
                                    <th style="text-align: center;"><span class="th_span1">${rule.partnerDirectRule.threeLadder.minAmount/10000?round}万-${rule.partnerDirectRule.threeLadder.maxAmount/10000?round}万</span><span class="th_span2">${rule.partnerDirectRule.threeLadder.minElite}-${rule.partnerDirectRule.threeLadder.maxElite}个</span></th>
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
                                </tbody>
                            </table>
                        </div>
                        <h2>间接渠道人才渠道之精英</h2>
                        <div class="table_div" id="companyTable">
                            <div class="text1">层次</div>
                            <div class="line"></div>
                              <div class="text2"><span>累计项目总额</span><span>和推荐人才数</span></div>
                            <table>
                                 <thead>
                                <tr>
                                    <th width="72"></th>
                                	<th width="170" style="border-right: 1px solid #fff;text-align:center;"><span class="th_span1" style="margin-left: 60px;">${rule.partnerIndirectRule.firstLadder.minAmount/10000?round}-${rule.partnerIndirectRule.firstLadder.maxAmount/10000?round}万<span class="small">(不含)</span></span><span class="th_span2" style="margin-left: 60px;">${rule.partnerIndirectRule.firstLadder.minElite}-${rule.partnerIndirectRule.firstLadder.maxElite}个</span></th>
                                    <th style="border-right: 1px solid #fff ;text-align: center;"><span class="th_span1">${rule.partnerIndirectRule.secondLadder.minAmount/10000?round}万-${rule.partnerIndirectRule.secondLadder.maxAmount/10000?round}万<span class="small">(不含)</span><span><span class="th_span2">${rule.partnerIndirectRule.secondLadder.minElite}-${rule.partnerIndirectRule.secondLadder.maxElite}个</span></th>
                                    <th style="text-align: center;"><span class="th_span1">${rule.partnerIndirectRule.threeLadder.minAmount/10000?round}万-${rule.partnerIndirectRule.threeLadder.maxAmount/10000?round}万</span><span class="th_span2">${rule.partnerIndirectRule.threeLadder.minElite}-${rule.partnerIndirectRule.threeLadder.maxElite}个</span></th>
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
                            <h2>人才渠道之CTO</h2>

                        <div class="table_div">
                            <div class="text1">层次</div>
                            <div class="line"></div>
                            <div class="text2">收益额<br/>合计</div>
                            <table>
                                <thead>
                                <tr>
                                    <th width="80"></th>
                                   <th width="100" style="border-right: 1px solid #fff;text-align:center;">${rule.firstProjectOrderAward.minAmount/10000?round}-${rule.firstProjectOrderAward.maxAmount/10000?round}万<span class="small">(不含)</span></th>
                                    <th style="border-right: 1px solid #fff ;text-align: center;">${rule.secondProjectOrderAward.minAmount/10000?round}-${rule.secondProjectOrderAward.maxAmount/10000?round}万<span class="small">(不含)</span></th>
                                    <th style="text-align: center;">${rule.threeProjectOrderAward.minAmount/10000?round}-${rule.threeProjectOrderAward.maxAmount/10000?round}万</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td width="60" style="border-bottom: 1px solid #fff">L1</td>
                                    <td width="100">${rule.firstProjectOrderAward.orderAward}</td>
                                    <td>${rule.secondProjectOrderAward.orderAward}</td>
                                    <td>${rule.threeProjectOrderAward.orderAward}</td>
                                </tr>
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
    	<form class="windowForm">
    		<div class="close_btn" id="gocloseId"></div>
    		<div class="logoDiv"><span class="logo failure"></span></div>
    		<div class="textDiv failure_text">你的资料完善度还没达到审核要求，先完善哦~</div>
    		<div class="btnDiv"><button type="button" class="saveBtn" id="goPrefectBtn">去完善</button></div>
    	</form>
    </div>
