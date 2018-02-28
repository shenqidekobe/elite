//项目阶段选择点击事件
function initProjectLog(projectId){
	loadProjectLog(projectId,function(){
		var stageId = '';
		$("#stageBudget,.triangle").click(function() {
			$(".budget").fadeIn();
			$(".budget li").click(function() {
				var thiss = $(this);
				var pts = thiss.text();
				$(".budget").fadeOut("fast");
				stageId = thiss.attr("data");
				$("#stageId").val(stageId);
				$("#stageBudget").val(pts);
			});
			$("body").bind("mousedown", function(event) {
				$(".budget").fadeOut("fast");
				$("body").unbind("mousedown");
			});
		});
		
		$('#startSelect').datepicker({
			autoclose : true,
		}).on("changeDate",function(e){
	    	var time=e.date;
	    	$("#startTime").val(time.Format("yyyy-MM-dd"));
	    });
		
		$('#endSelect').datepicker({
			autoclose : true,
		}).on("changeDate",function(e){
	    	var time=e.date;
	    	$("#endTime").val(time.Format("yyyy-MM-dd"));
	    });

		$("#search").click(function() {
			loadProjectLogList();
		});
	});
}
function loadProjectLog(projectId,callback){
	jsonAjax.post({
		url : ctx + '/project/c/log',
		data : {projectId:projectId},
		success : function(data) {
			$("#dataList").html(data);
			loadProjectLogList();
			callback();
		}
	});
}
function loadProjectLogList() {
	jsonAjax.post({
		url : ctx + '/project/r/log/listData',
		data : $("#logListForm").serialize(),
		success : function(data) {
			$("#dataSecondList").html(data);
		}
	});
}
