$(function() {
	list.init()
});
var list = {
	init : function() {
		$("#showWorkLogs").click(list.showWorkLogs);
		$("#back").click(list.back);
		$("label[name='showCTOInfo']").click(function(){
			$(this).next().toggle();
			
		})
	},
	back : function() {
		histroy.back();
	},
	showWorkLogs : function() {
		var memberId = $(this).attr("data");
		if (null != memberId && "" != memberId) {
			$("#workRecordForeignid").val(memberId);
		} else {
			memberId = $("#workRecordForeignid").val();
		}
		$('#workLogsModel').modal('show');
		jsonAjax.post({
			url : ctx + '/work/listData',
			data : {
				foreignId : memberId,
				type : "partnerElite"
			},
			success : function(data) {
				if ("EXCEPTION" == data.code) {
					$.tips({
						content : data.msg
					});
				} else {

					$('#workLogsModel').html(data);
					$('#workLogsModel').modal('show');

					$('#removeWorkRecord').click(list.removeWorkRecord);
					$("#workRecordForeignid").val(memberId);
				}

			},
			error : function(data) {
				$('#removeWorkRecord').click(list.removeWorkRecord);
				$.tips({
					content : data.msg
				});
			},
		})

	},
	addWorkRecord : function() {
		$('#workRecordType').val('partnerElite');
		jsonAjax.post({
			url : ctx + '/work/save',
			data : $("#workRecordForm").serialize(),
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					list.showWorkLogs();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		})
	},
	removeWorkRecord : function(id) {
		jsonAjax.post({
			url : ctx + '/work/remove',
			data : {
				id : id
			},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					list.showWorkLogs();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		})
	},

};