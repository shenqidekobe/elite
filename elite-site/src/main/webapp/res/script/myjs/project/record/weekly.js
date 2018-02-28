function initProjectWeekly(projectId){
	loadWeekly(projectId,function(){
		
	});
}
function loadWeekly(pid,callback){
	jsonAjax.post({
		url : ctx + '/project/r/weekly',
		data:{projectId:pid},
		success : function(data) {
			$("#dataList").html(data);
			loadWeeklyList();
			callback();
			$('#monthVal').datepicker({
                format: 'yyyy-mm',
                autoclose : true,
                startView: 'year',
		        minViewMode:'months',
		        startDate:$("#startTime").val(),
				endDate:$("#endTime").val(),
            }).on("changeDate",function(e){
            	var time=e.date;
            	var month=time.Format("MM");
            	var first=month.substring(0,1);
            	if(first=='0'){
            		month=month.substring(1,2);
            	}
            	$("#month").val(month);
            });
			$('.date-icon').datepicker({
				orientation:"right",
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
		}
	});
}
function loadWeeklyList() {
	jsonAjax.post({
		url : ctx + '/project/r/weekly/listData',
		data : $("#weeklyListForm").serialize(),
		success : function(data) {
			$("#dataSecondList").html(data);
			$("img[id=download]").click(downloadWeekly);
			$("button[id=blag_weekly]").click(claimWeekly);
			var monthCount=$("#monthCount").val();
			var monthUnCount=$("#monthUnCount").val();
			$("#allCount").text(monthCount);
			$("#unreadCount").text(monthUnCount);
		}
	});
}
function downloadWeekly(){
	var id=$(this).attr("data");
	var readed=$(this).attr("readed");
	var href=ctx+'/project/r/read/weekly/'+id;
	window.open(href);
	loadWeeklyList();
	//未读tab下载时候未读数减一，去除未读标致
	var selectType=$(".active-tab").attr("data");
	if(readed!='true'){
		var unreadCount=$("#unreadCount").text();
		$("#unreadCount").text((parseInt(unreadCount)-1));
		
		$(this).parent().prev().find("img[data=readed]").remove();
	}
}
function claimWeekly(){
	var time = new Date();
	var day=time.getFullYear()+time.getMonth()+time.getDate();
	var thiss=$(this);
	var projectId=thiss.attr("pid");
	var month=thiss.attr("month");
	var week=thiss.attr("week");
	var flag=day+"-"+projectId+"-"+month+"-"+week;
	var claimWeeklyFlag=localStorage.getItem(flag);
	var fix=(claimWeeklyFlag)==null?0:claimWeeklyFlag.split("-")[0];
	if(fix==day){
		tostHint("提示","今天您已经索取过了！");
		return false;
	}

	jsonAjax.post({
		url:ctx+'/project/r/claim/weekly',
		data:{projectId:projectId,month:month,week:week},
		success:function(data){
			if(data.result=="SUCCESS"){
				localStorage.setItem(flag,flag);
				thiss.text("索取成功");
				thiss.attr({"disabled":"disabled"});
				tostHint("索取成功","周报索取成功,请耐心等待！");
			}
		}
	});
}
