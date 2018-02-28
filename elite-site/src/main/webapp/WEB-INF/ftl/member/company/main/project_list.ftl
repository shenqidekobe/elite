<form id="projectListForm" name="projectListForm">
<div style="position: relative;" id="my_p_tabs">
    <div class="tabs">
        <div class="col-xs-4 col-md-4" data="wait_audit" index="0">
            <div class="tab">待审核&nbsp;<span class="tab-number" id="waittab">${waitCount}</span></div>
        </div>
        <div class="col-xs-4 col-md-4" data="audit_in" index="1">
            <div class="tab">审核中&nbsp;<span class="tab-number" id="auditintab">${auditInCount}</span></div>
        </div>
        <div class="col-xs-4 col-md-4" data="audited" index="2">
            <div class="tab">已审核&nbsp;<span class="tab-number" id="auditedtab">${auditedCount}</span></div>
        </div>
    </div>
    <div class="active-line"></div>
</div>
<input type="hidden" name="status" id="projectStatus">
<div id="dataSecondList"></div>
</form>