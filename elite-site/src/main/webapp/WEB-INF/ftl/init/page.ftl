<#macro pager path params page pagesize count class="" id="">
    <#local page=page/>
    <#local pagesize=pagesize/>
    <#local pageNumber=pagesize />
    <#local maxpage=(count/pagesize)?ceiling/>
    <#if maxpage lt 1>
        <#local maxpage=1/>
    </#if>
    <#if page lt 1>
        <#local page=1/>
    </#if>
    <#if page gt maxpage>
        <#local maxpage=page/>
    </#if>
    <#local begin=page-2/>
    <#if begin lt 1>
        <#local begin=1/>
    </#if>
    <#local end=begin+4/>
    <#if end gt maxpage>
        <#local end=maxpage/>
        <#local begin=end-5/>
        <#if begin lt 1>
            <#local begin=1/>
        </#if>
    </#if>
<input type="hidden" id="count" value="${count!'0'}">
<input type="hidden" id="currentPage" value="${page!'0'}">
    <#if count gt 0 && maxpage gt 1>
    <ul class="pagination">
        <li class="prev <#if page == 1>disabled</#if>"  >
            <a href="<#if page == 1>javascript:;<#else>${url(path, params+{"page":page-1, "pagesize": pagesize})}</#if>">«</a>
        </li>
        <#-- maxpage <= 5 -->
        <#if maxpage lt 6>
        <#-- page=1 -->
            <#if page lt 2>
                <li class="active"><a href="javascript:;" class="current">${page}</a></li>
                <#if maxpage gt 1>
                    <#list 2 .. maxpage as index>
                        <li>
                            <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</a>
                        </li>
                    </#list>
                </#if>
            <#else>
            <#-- 2<= page <=5 -->
                <#list 1 .. page-1 as index>
                    <li>
                        <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</a>
                    </li>
                </#list>
                <li class="active"><a href="javascript:;" class="current">${page}</a></li>
                <#if page < maxpage>
                    <#list page+1 .. maxpage as index>
                        <li>
                            <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</a>
                        </li>
                    </#list>
                </#if>
            </#if>
        <#else>
        <#-- maxpage >= 6  -->
            <#if page lt 5>
            <#-- page = 1 -->
                <#if page lt 2>
                    <li class="active"><a href="javascript:;" class="current">${page}</a></li>
                    <#list 2 .. end-1 as index>
                        <li>
                            <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</a>
                        </li>
                    </#list>
                    <li>
                        <a href="${url(path, params+{"page":end, "pagesize": pagesize})}">${end}</a>
                    </li>
                    <li class="disabled ellipse"><span>…</span></li>
                <#-- page =4 -->
                <#elseif page gt 3>
                    <#list 1 .. page-1 as index>
                        <li>
                            <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</a>
                        </li>
                    </#list>
                    <li class="active"><a href="javascript:;" class="current">${page}</a></li>
                    <li>
                        <a href="${url(path, params+{"page":5, "pagesize": pagesize})}">5</a>
                    </li>
                    <li class="disabled ellipse"><span>…</span></li>
                <#-- 2<= page <=3 -->
                <#else>
                    <#list 1 .. page-1 as index>
                        <li>
                            <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</a>
                        </li>
                    </#list>
                    <li class="active"><a href="javascript:;" class="current">${page}</a></li>
                    <#list page+1 .. 4 as index>
                        <li>
                            <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index}</a>
                        </li>
                    </#list>
                    <li>
                        <a href="${url(path, params+{"page":5, "pagesize": pagesize})}">5</a>
                    </li>
                    <li class="disabled ellipse"><span>…</span></li>
                </#if>
            <#-- page >= 5 -->
            <#else>
                <li>
                    <a href="${url(path, params+{"page":1, "pagesize": pagesize})}">1</a>
                </li>
                <li>
                    <a href="${url(path, params+{"page":2, "pagesize": pagesize})}">2</a>
                </li>
                <li class="disabled ellipse"><span>…</span></li>
                <#if page+1 gt maxpage>
                    <#list page-2 .. page-1 as index>
                        <li>
                            <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index?c}</a>
                        </li>
                    </#list>
                    <li class="active"><a href="javascript:;" class="current">${page?c}</a></li>
                <#elseif page+2 gt maxpage>
                    <#list page-1 .. page-1 as index>
                        <li>
                            <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index?c}</a>
                        </li>
                    </#list>
                    <li class="active"><a href="javascript:;" class="current">${page?c}</a></li>
                    <#list page+1 .. page+1 as index>
                        <li>
                            <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index?c}</a>
                        </li>
                    </#list>
                <#elseif page+3 gt maxpage>
                    <#list page-1 .. page-1 as index>
                        <li>
                            <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index?c}</a>
                        </li>
                    </#list>
                    <li class="active"><a href="javascript:;" class="current">${page?c}</a></li>
                    <#list page+1 .. page+1 as index>
                        <li>
                            <a href="${url(path, params+{"page":index, "pagesize": pagesize})}">${index?c}</a>
                        </li>
                    </#list>
                    <li class="disabled ellipse"><span>…</span></li>
                <#else>
                    <li>
                        <a href="${url(path, params+{"page":page-1, "pagesize": pagesize})}">${(page-1)?c}</a>
                    </li>
                    <li class="active"><a href="javascript:;" class="current">${page?c}</a></li>
                    <li>
                        <a href="${url(path, params+{"page":page+1, "pagesize": pagesize})}">${(page+1)?c}</a>
                    </li>
                    <li class="disabled ellipse"><span>…</span></li>
                </#if>
            </#if>
        </#if>
        <#if maxpage gt 1>
            <li class="next <#if page == maxpage>disabled</#if>">
                <a href="<#if page == maxpage>javascript:;><#else>${url(path, params+{"page":page+1, "pagesize": pagesize})}</#if>">»</a>
            </li>
        </#if>
    </ul>
    <div class="clearfix"></div>
    <script>
        (function(){
            var input = document.getElementById("J_ym-pagination-input");
            var updateURLParameter = function(url, param, paramVal){
                var newAdditionalURL = "";
                var tempArray = url.split("?");
                var baseURL = tempArray[0];
                var additionalURL = tempArray[1];
                var temp = "";
                if (additionalURL) {
                    tempArray = additionalURL.split("&");
                    for (i=0; i<tempArray.length; i++){
                        if(tempArray[i].split('=')[0] != param){
                            newAdditionalURL += temp + decodeURIComponent(tempArray[i]);
                            temp = "&";
                        }
                    }
                }
                var rows_txt = temp + "" + param + "=" + paramVal;
                return baseURL + "?" + newAdditionalURL + rows_txt;
            }
            input.onkeyup = function(){
                this.value = this.value.replace(/\D|\s/g,"");
            };
            document.getElementById("J_ym-pagination-sbm-btn").onclick = function(){
                var val = parseInt(input.value);
                if(isNaN(val) || val === 0 || val> ${maxpage?c}){
                    input.select();
                    return;
                }
                location.href = updateURLParameter(location.href,"page",val);
            };
        }());
    </script>
    </#if>
</#macro>
<#global pager=pager/>