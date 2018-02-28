$(function() {
	list.init();
});
var list = {
	init : function() {
		$("#back").click(list.back)
		list.search()
		list.dateTimePicker();
		$("#searchOutOrder").click(list.search)
	},
	search : function() {
		jsonAjax.post({
			url : ctx + '/settlement/payable/detail/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#detailListData").html(data);
				  $("#addRemarks").click(remarkslist.showForm)
			}
		})
	},
	back : function() {
		window.location.href = ctx + '/settlement/payable'
	},
	 dateTimePicker : function() {
			$('#startTimeId').datepicker({
				language : 'zh-CN',
				autoclose : true,
				format : "yyyy-MM-dd",
				todayHighlight : true,
			})
			$('#endTimeId').datepicker({
				language : 'zh-CN',
				autoclose : true,
				format : "yyyy-MM-dd",
				todayHighlight : true
			})
	    }
};