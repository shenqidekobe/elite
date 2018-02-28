$(function() {
	list.init();
});
var list = {
	init : function() {
		$("#back").click(list.back)
		$("a[id=audit]").click(auditlist.showAuditForm);
		$("#assist_perfect").click(prefectlist.showPrefectForm);
		
	},
	search : function() {
		window.location.reload();
		$('#myModel').modal('hide');
		
	},
	back : function() {
		window.location.href = ctx + '/member/company'
	}
};