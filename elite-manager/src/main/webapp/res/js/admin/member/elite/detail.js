$(function() {
	list.init();
});
var list = {
	init : function() {
		$("#back").click(list.back)
		$("a[id=audit]").click(auditlist.showAuditForm);
		$("#level").click(levellist.showLevelForm);
	},
	search : function() {
		$('#myModel').modal('hide');
		window.location.reload();
		
	},
	back : function() {
		window.location.href = ctx + '/member/elite'
	}
};