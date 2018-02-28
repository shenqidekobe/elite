<div class="switchWindowForm" >
	<h1>切换身份</h1>
	<div class="closeBtn" id="closeSwitchIdentity"></div>
	<div class="switchImg">
		<ul id="switchImgUl" currentType="${currentType}">
			<li data="company"><div class="switchBox"><img src="${_PATH}/res/images/switch/ceo<#if currentType=='company'>_selected</#if>.png" alt=""></div></li>
			<li data="elite" style="margin-right:0;"><div class="switchBox"><img src="${_PATH}/res/images/switch/elite<#if currentType=='elite'||currentType=='cto'>_selected</#if>.png" alt=""></div></li>
			<li data="partnerElite"><div class="switchBox"><img src="${_PATH}/res/images/switch/person<#if currentType=='partnerElite'>_selected</#if>.png" alt=""></div></li>
			<li data="partnerCompany" style="margin-right:0;"><div class="switchBox"><img src="${_PATH}/res/images/switch/project<#if currentType=='partnerCompany'>_selected</#if>.png" alt=""></div></li>
		</ul>
	</div>
</div>
