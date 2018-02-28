$(function() {
	$("a[id=projectIntro]").click(function(){
		var data = $(this).attr("data");
		$("#workRecordContent").html(data);
		$('#projectDeatil').modal('show');
	})
});
