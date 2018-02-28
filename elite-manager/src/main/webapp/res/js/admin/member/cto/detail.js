$(function() {
	list.init();
});
var list = {
	init : function() {
		$("#back").click(list.back)
		$("#level").click(levellist.showLevelForm);
		$("#audit").click(auditlist.showAuditForm);
	},
	search : function() {
		$('#myModel').modal('hide');
		window.location.reload();
	},
	back : function() {
		window.location.href = ctx + '/member/cto'
	}
};