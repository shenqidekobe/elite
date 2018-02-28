var list = {
	init : function() {
		$("#enterProject").click(list.enterProjectView);
		$("#putProject").click(list.putProjectView);
		$("#search").click(list.searchCompany);
		$("a[id=projectIntro]").click(list.showIntro)
	},
	search : function() {
		list.searchCompany();
	},
	searchCompany : function() {
		var status = $("#status").val();
		jsonAjax.post({
			url : ctx + '/channel/company/project/listData',
			data : $("#projectListForm").serialize(),
			success : function(data) {
				if(status=='register'){
					$("#putProjectList").html(data);
				}else{
					$("#projectListData").html(data);
					$("#partnerListData").html("");
					list.init();
				}
			}
		})
	},
	showIntro: function() {
		var data = $(this).attr("data");
		$("#workRecordContent").html(data);
		$('#projectDeatil').modal('show');
	},
	enterProjectView : function() {
		$("#pager_pageth").val(1);
		$("#status").val("registered");
		jsonAjax.post({
			url : ctx + '/channel/company/project/listData',
			data : $("#projectListForm").serialize(),
			success : function(data) {
				$("#enterProjectList").html(data);
				$("#putProjectList").html("");
			}
		})
	},
	putProjectView : function() {
		$("#pager_pageth").val(1);
		$("#status").val("register");
		jsonAjax.post({
			url : ctx + '/channel/company/project/listData',
			data : $("#projectListForm").serialize(),
			success : function(data) {
				$("#putProjectList").html(data);
				$("a[id=projectIntro]").click(list.showIntro)
				$("#enterProjectList").html("");
			}
		})
	}
};