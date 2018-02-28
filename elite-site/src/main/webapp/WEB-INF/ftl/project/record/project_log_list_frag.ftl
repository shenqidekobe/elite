<div class="project-log">
    <#assign stageName=""/>
	<#list list as lpt>
		<div class="log">
		    <#assign title="${lpt.stageName}"/>
		    <#assign tflag="false"/>
		    <#assign ctime="${lpt.createTime?string('yyyy-MM-dd')}"/>
		    <#if title!=prevName||lpt_index==0>
                <div class="period-btn">${title}</div>
                <span class="log-date">${ctime}</span>
               <#assign prevName=""/>
               <#assign tflag="true"/>
		    </#if>
            <#assign prevName=title/>

            
		    <#if (ctime!=prevTime||lpt_index==0)&&tflag=="false">
               <span class="log-date">${ctime}</span>
               <#assign prevTime=""/>
		    </#if>
            <#assign prevTime=ctime/>
		    <span class="log-time">&nbsp;&nbsp;&nbsp;${lpt.createTime?string("HH:mm")}</span>
		    <div class="log-point"></div>
		    <span class="log-content">${lpt.content}</span>
		</div>
	</#list>
</div>
