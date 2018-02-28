$(function() {
	$("a[id^=uploadResume_]").click(function(){
		var account = $(this).attr("data");
		$("#account").val(account);
		$("#defineFile").click();
	});
	//上传简历
	upload.uploadFile($("#defineFile"), "elite", "resume_atta", function(data) {
		if (typeof (data.attaId) != "undefined") {
			jsonAjax.post({
				url : ctx + '/channel/partnerElite/save/resume',
				data : {
					resumeId : data.attaId,
					account : $("#account").val()
				},
				success : function(data) {
					if (data.result == "SUCCESS") {
						list.searchElite();
					}else{
						$.tips({
							content : data.msg
						});
					}
				}
			});
		}
	});
});
