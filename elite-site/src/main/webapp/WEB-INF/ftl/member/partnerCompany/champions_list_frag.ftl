<#if toplist?size lt 1>
 	暂无数据
   <div hidden>
   <#else>
   <div>
   </#if>
   <ul class="show">
   <input type="hidden" value="${myIncome}" id="listmyIncomeId">
   <input type="hidden" value="${partnerElite.memberPassport.nickName}" id="parentNameId">
   <input type="hidden" value="${ranking}" id="rankingId">
                               
                                <#if toplist[0]??>
                                <li class="show_li1">
                                    <img src="../../../../../res/images/partner/crown.png" class="crown" alt="">
                                    <div class="head">
                                         <#if toplist[0].memberBasic.photoId??>
                                        <img src="${toplist[0].memberBasic.memberPhoto.path}">   
                                          <#else>
                                        <img src="${_PATH}/res/images/default.jpg" >
                                           </#if>
                                    </div>
                                    <div class="name">${toplist[0].memberPassport.nickName}</div>
                                    <div class="statistical">
                                        <ul class="clearfloat number_ul">
                                            <li>${toplist[0].partnerIncome}</li>
                                            <li>${toplist[0].putCount}</li>
                                            <li>${toplist[0].putParnterCount}</li>
                                        </ul>
                                        <ul class="clearfloat text_ul">
                                            <li>总收益</li>
                                            <li>推荐项目</li>
                                            <li>推荐渠道</li>
                                        </ul>
                                    </div>
                                </li>
                                 </#if>
                                <#if toplist[1]??>
                                <li class="show_li2">
                                    <span class="ranking">2</span>
                                    <div class="head">
                                          <#if toplist[1].memberBasic.photoId??>
                                        <img src="${toplist[1].memberBasic.memberPhoto.path}">   
                                          <#else>
                                        <img src="${_PATH}/res/images/default.jpg" >
                                           </#if>
                                    </div>
                                    <div class="name">${toplist[1].memberPassport.nickName}</div>
                                    <div class="statistical">
                                        <ul class="clearfloat number_ul">
                                            <li>${toplist[1].partnerIncome}</li>
                                            <li>${toplist[1].putCount}</li>
                                            <li>${toplist[1].putParnterCount}</li>
                                        </ul>
                                        <ul class="clearfloat text_ul">
                                            <li>总收益</li>
                                            <li>推荐项目</li>
                                            <li>推荐渠道</li>
                                        </ul>
                                    </div>
                                </li>
                                 </#if>
                                <#if toplist[2]??>
                                <li class="show_li3">
                                    <span class="ranking"></span>
                                    <div class="head">
                                         <#if toplist[2].memberBasic.photoId??>
                                        <img src="${toplist[2].memberBasic.memberPhoto.path}">   
                                          <#else>
                                        <img src="${_PATH}/res/images/default.jpg" >
                                           </#if>
                                    </div>
                                    <div class="name">${toplist[2].memberPassport.nickName}</div>
                                    <div class="statistical">
                                        <ul class="clearfloat number_ul">
                                            <li>${toplist[2].partnerIncome}</li>
                                            <li>${toplist[2].putCount}</li>
                                            <li>${toplist[2].putParnterCount}</li>
                                        </ul>
                                        <ul class="clearfloat text_ul">
                                            <li>总收益</li>
                                            <li>推荐项目</li>
                                            <li>推荐渠道</li>
                                        </ul>
                                    </div>
                                </li>
                                 </#if>
                            </ul>
                            <div class="precedenceTable">
                                <h2><font size="2"><span >用户名</span><span>总收益</span><span>推荐项目</span><span>推荐渠道</span></font></h2>
                                <ul id="" class="table_ul">
                                 <#if list?size lt 1>
	暂无数据
	</#if>
    <#list list as it>
	  <li>
        <span>
        <div class="li_head">
          <#if it.memberBasic.photoId??>
            <img src="${it.memberBasic.memberPhoto.path}">   
          <#else>
              <img src="${_PATH}/res/images/default.jpg">
           </#if>
        </div>
            ${it.memberPassport.nickName}</span>
        <span>￥${it.partnerIncome}</span>
        <span>${it.putCount}</span>
        <span>${it.putParnterCount}</span>
        <div class="number">${it_index+4+(pagination.pageth-1)*pagination.pageSize}</div>
      </li>
    </#list>
<#include "/init/pager.ftl"/>
</div>