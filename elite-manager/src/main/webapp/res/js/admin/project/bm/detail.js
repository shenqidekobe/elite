$(function() {
	list.init();
});
var list = {
	init : function() {
		$("#back").click(list.back)
		$("a[id=audit_company]").click(auditlist.showAuditForm);
		$("a[id=add_business]").click(businesslist.showForm);
		$("a[id=assist_perfect]").click(projectprefectlist.showForm);
		$("a[id=assistelite_prefect]").click(prefectlist.showPrefectForm);
		
		$("a[id=add_define]").click(list.addfine);
		$("a[id=search_define]").click(list.searchfine);
		//分配pm
		$("a[id=allot_pm]").click(list.allotPm);
	},
	search : function() {
		$('#myModel').modal('hide');
	},
	back : function() {
		window.location.href = ctx + '/project/bm';
	},
	addfine:function(){
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/bm/adddefine/view").submit();
	},
	searchfine:function(){
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/bm/finddefine/view").submit();
	},
	allotPm:function(){
		var id = $(this).attr("data");
		$("#detailFormInputId").val(id);
		$('#detailForm').attr('action', ctx + "/project/bm/allot/view").submit();
	}
};