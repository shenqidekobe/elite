<form id="projectForm">
<div style="position: relative;" id="my_p_tabs">
    <div class="tabs">
        <div class="col-xs-4 col-md-4 active-tab" data="recommend"  index="0">
            <div class="tab">推荐给我的&nbsp;<span class="tab-number" id="recommendtab">${recommendcount}</span></div>
        </div>
        <div class="col-xs-4 col-md-4"  data="tenderIn" index="1">
            <div class="tab">竞标项目&nbsp;<span class="tab-number" id="tenderintab">${tenderInCount}</span></div>
        </div>
        <div class="col-xs-4 col-md-4" data="tender" index="2">
            <div class="tab">中标项目&nbsp;<span class="tab-number" id="tendertab">${tenderCount}</span></div>
        </div>
    </div>
    <div class="active-line"></div>
</div>
<div id="dataList"></div>
<input type="hidden" id="projectStatus" />
<input type="hidden" name="type" id="type"/>
</form>
