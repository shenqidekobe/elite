<div id="projectlManage">
	<h1>项目管理</h1>
    <form id="searchForm" class="searchForm">
    			<input style='display:none' />
    	<div class="searchBox">
    		<div class="searchDiv">
	            <input class="searchInput" type="text" id="searchWord" name="keyword" placeholder="项目名/联系人/手机号" maxlength="20">
	            <button type="button" class="searchBtn" id="projectManageSearchBtn">
	            	<span class="search_icon"></span>    
	            	<span class="search_text">搜索</span>
	            </button>
	    	</div>
    	</div>
	    <#--表格专区-->
	    <div id="datalist"></div>
  </form>
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