function initProjectWeekly(taskId){
	loadWeekly(taskId,function(){
		
	});
}
function loadWeekly(taskId,callback){
	jsonAjax.post({
		url : ctx + '/task/weekly',
		data:{taskId:taskId},
		success : function(data) {
			$("#dataList").html(data);
			$('.date-select').datepicker({
				format: 'yyyy-mm',
                autoclose : true,
                startView: 'year',
		        minViewMode:'months',
		        startDate:$("#startTime").val(),
				endDate:$("#endTime").val(),
		    }).on("changeDate",function(e){
		    	var time=e.date;
		    	$("#monthVal").val(time.Format("yyyy-MM"));
            	var month=time.Format("MM");
            	var first=month.substring(0,1);
            	if(first=='0'){
            		month=month.substring(1,2);
            	}
            	$("#month").val(month);
		    });
			$('#searchForm').click(loadWeeklyList);
			loadWeeklyList();
			callback();
		}
	});
}

var weeklyTime;
function loadWeeklyList() {
	jsonAjax.post({
		url : ctx + '/task/weekly/listData',
		data : $("#weeklyListForm").serialize(),
		success : function(data) {
			$("#dataSecondList").html(data);
			var monthCount=$("#monthCount").val();
			$("#allCount").text(monthCount);
			$("img[id=download]").click(downloadWeekly);
			$("img[id=delWeekly]").click(delWeekly);
			$("img[id^=upload_weekly_]").click(function(){
				weeklyTime = $(this).attr("data");
				$("#weeklyFile").click();
			});
			
			upload.uploadFile($("#weeklyFile"), "project", "material_atta",function(data) {
				saveWeekly(data.attaId);
			});
		}
	});
}

function saveWeekly(attaId,fileName){
	jsonAjax.post({
		url : ctx + '/task/weekly/save',
		data : {taskId:$("#taskId").val(),attaId:attaId,weeklyTime:weeklyTime},
		success : function(data) {
			loadWeeklyList();
		}
	});
}

function  delWeekly(){
	var weeklyId=$(this).attr("data");
	tostConfirm("确定要删除该周报吗？",function(){
		jsonAjax.post({
			url : ctx + '/project/c/weekly/delete',
			data : {id:weeklyId},
			success : function(data) {
				loadWeeklyList();
			}
		});
	});	
}


function downloadWeekly(){
	var id=$(this).attr("data");
	console.info(id);
	var href=ctx+'/project/r/read/weekly/'+id;
	window.open(href);
}

