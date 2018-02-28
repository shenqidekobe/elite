<div class="skill-box">
   <div class="skills">
       <#list map.keySet() as wk>
           <div class="skill-group clearfloat">
    			<span data="${wk.dictKey}" pid="${wk.id}" pdata="${wk.parent.dictKey}">${wk.dictVal}</span>
    			<ul id="rolesUl">
    			   <#list map.get(wk) as lvs>
    			       <li class="" data="${lvs.dictKey}" id="${lvs.id}" pdata="${lvs.parent.dictKey}">${lvs.dictVal} </li>
    			   </#list>
    			</ul>
           </div>
       </#list>
    </div>
</div>