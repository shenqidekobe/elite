function initProjectWeekly(projectId){
	loadWeekly(projectId,function(){
		
	});
}
function loadWeekly(pid,callback){
	jsonAjax.post({
		url : ctx + '/project/c/weekly',
		data:{projectId:pid},
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
			$(".col-xs-6").click(function() {
				$(this).addClass("active-tab").siblings().removeClass("active-tab");
				$("#type").val($(this).attr("data"));
				var index=parseInt($(this).attr("index"));
				var left=260*index;
				$(".active-line").css({'left':left+'px'});
				loadWeeklyList();
			});
			loadWeeklyList();
			callback();
		}
	});
}

var weeklyTime;
function loadWeeklyList() {
	jsonAjax.post({
		url : ctx + '/project/c/weekly/listData',
		data : $("#weeklyListForm").serialize(),
		success : function(data) {
			$("#dataSecondList").html(data);
			var monthCount=$("#monthCount").val();
			var monthUnCount=$("#monthUnCount").val();
			$("#allCount").text(monthCount);
			$("#unreadCount").text(monthUnCount);
			$("button[id=blag_weekly]").click(claimWeekly);
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
		url : ctx + '/project/c/weekly/save',
		data : {projectId:$("#projectId").val(),attaId:attaId,weeklyTime:weeklyTime},
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

function claimWeekly(){
	var thiss=$(this);
	var content = $(this).attr("content");
	var taskId = $(this).attr("data");
	var time = new Date();
	var day=time.getFullYear()+time.getMonth()+time.getDate();
	var memberId=$(this).attr("memberId");
	var month=$(this).attr("month");
	var week=$(this).attr("week");
	var claimflag=day+"-"+memberId+"-"+taskId+"-"+month+"-"+week;
	var claimWeeklyFlag=localStorage.getItem(claimflag);
	var fix=(claimWeeklyFlag)==null?0:claimWeeklyFlag.split("-")[0];
	if(fix==day){
		tostHint("提示","今天您已经索取过了！");
		return false;
	}
	jsonAjax.post({
		url:ctx+'/project/c/claim/weekly',
		data:{taskId:taskId,content:content},
		success:function(data){
			if(data.result=="SUCCESS"){
				localStorage.setItem(claimflag,claimflag);
				thiss.text("索取成功");
				thiss.attr({"disabled":"disabled"});
				tostHint("索取成功","周报索取成功,请耐心等待！");
				//$(".col-xs-6:eq(1)").click();
			}
		}
	});
}
