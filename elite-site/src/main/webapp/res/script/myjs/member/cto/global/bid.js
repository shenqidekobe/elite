require([ "jquery", "ajax", "jsonAjax","md5","commons","bootstrap-datepicker","webupload"], function($) {
	
	$("#myMain, #myProject").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#tenderDetail").click(function(){
		var id = $(this).attr("data");
		window.location.href=ctx+'/project/c/'+id+'/tender?'+jsonAjax.random();
	});
	var projectId=$("#projectId").val();
	var deadlineTime=$("#deadlineTime").val();
	addTimer("deadlineSpan",deadlineTime);
	//时间选择和计算
	var startTime=0,endTime=0;
	$('span[id=startTime]').datepicker({
		startDate:new Date(),
		todayHighlight : true, 
		format:"yyyy-MM-dd",
        autoclose: true,
    }).on("changeDate",function(e){
    	var date=e.date.Format("yyyy-MM-dd");
    	$(this).children(".date-block").text(date);
    	$(this).children("#sh").val(date);
    	startTime=e.date.getTime();
    	if(startTime>0&&endTime>0&&endTime>=startTime){
    		var interval=endTime-startTime;
        	var day=interval/(24 * 60 * 60 * 1000);
        	$(this).next().next().next().next().children("#deliveryDay").text(day+1);
    	}
    })
    $('span[id=endTime]').datepicker({
    	startDate:new Date(),
		todayHighlight : true, 
    	format:"yyyy-MM-dd",
        autoclose: true,
    }).on("changeDate",function(e){
    	var date=e.date.Format("yyyy-MM-dd");
    	$(this).children(".date-block").text(date);
    	$(this).children("#eh").val(date);
    	endTime=e.date.getTime();
    	if(startTime>0&&endTime>0&&endTime>=startTime){
    		var interval=endTime-startTime;
        	var day=interval/(24 * 60 * 60 * 1000);
        	$(this).next().next().children("#deliveryDay").text(day+1);
    	}
    });
	//服务方式事件
	$(".service-btn").click(function(){
		$(".service-btn").removeClass("active-btn");
		$(this).addClass("active-btn");
		var way=$(this).attr("data");
		$("#serviceWay").val(way);
	});
	$(".service-btn:first").click();
	//上传附件文档
	upload.uploadFile($("#attaFile"),"tender","tender_atta",function(data){
		$("#atta_show").show();
		var prd='<button type="button" class="upload-file">'+data.originalName+'</button><img src='+ctx+'"/res/images/del.png" data="'+data.attaId+'" id="pbimg" class="delete-cross">';
		$("#atta_show").html(prd);
		$("img[id=pbimg]").unbind('click');
		$("img[id=pbimg]").bind('click',function(e){
            var thiss=$(this);
            var bthis=thiss.prev();
			var id=thiss.attr("data");
			upload.removeFile("tender",function(url){
				jsonAjax.post({
					url:url,
					data:{attaId:id},
					success:function(drsp){
						if(drsp.result=="SUCCESS"){
							$("#attaId").val('');
							thiss.remove();
							bthis.remove();
						}
					}
				});
			});
		});
		$("#attaId").val(data.attaId);
	});
	
	//去竞标
	$(".go-bid-btn").click(function(){
		var datetime=""; var flag =false; var message="";
		$("span[id^='datetime_']").each(function(){
			if($(this).text()==''){
				flag = true;
				message="各阶段时间不能为空";
				return false
			}
			if(datetime!=''){
				if($(this).text()<datetime){
					flag = true;
					message="阶段结束时间不能小于开始时间且每阶段开始时间大于上一阶段结束时间";
					return false
				}
			}
			datetime = $(this).text();
		});
		if(flag){
			$(".error_div").text(message);
			return false;
		}
		var amount=$.trim($("#amount").val());
		var advantage=$.trim($("#advantage").val());
		var stageSize=$("#stageSize").val();
		if(amount==''){
			$(".error_div").text('金额不能为空')
			return false;
		}else if(!validateAmount(amount)){
			$(".error_div").text('请输入合理的金额')
			return false;
		}
		amount=Number(amount).toFixed(2);
		$("#realAmount").val(amount);
		
		var recordStageArray=[];
		var stageTotalAmount=0;

		$("tr[id=stageTr]").each(function(){
			var thiss=$(this);
			var code=thiss.attr("data");
			var title=thiss.find(".period-lab").text();
			var st=thiss.find("#sh").val();
			var et=thiss.find("#eh").val();
			var stageAmount=thiss.find("#stageAmount").val();
			if(st==""){
				$(".error_div").text("请输入"+title+"所需的费用");
				return false;
			}
			console.info(code+'---:'+st+'---'+et+'---'+stageAmount);
			stageAmount=Number(stageAmount).toFixed(2);
			stageTotalAmount=(Number(stageTotalAmount)+Number(stageAmount)).toFixed(2);
			var recordStage={
				stageCode:code,
				title:title,
				startTime:st,
				endTime:et,
				amount:stageAmount
			}
			recordStageArray.push(recordStage);
		});
		console.info(stageTotalAmount+'---'+amount);
		
		if(recordStageArray.length!=parseInt(stageSize)){
			return false;
		}else if(Number(stageTotalAmount)!=Number(amount)){
			$(".error_div").text("各阶段的金额总和不等于总额");
			return false;
		}
		if(advantage.length<50){
			$(".error_div").text("填写项目优势的字数不能小于50字左右");
			return false;
		}if(advantage.length>1000){
			$(".error_div").text("填写项目优势的字数不能大于1000字左右");
			return false;
		}
		var recordStages=JSON.stringify(recordStageArray);
		$("#recordStages").val(recordStages);
		$("#optDialog").modal('show');
		$("#optDialog #ok-btn").unbind('click');
		$("#optDialog #ok-btn").click(function(){
			jsonAjax.post({
				url:ctx+'/project/c/bid/save',
				data:$("#tenderForm").serialize(),
				success:function(data){
					console.info(data);
					$("#optDialog").modal('hide');
					if(data.result=="FAILURE"){
						tostHint("竞标失败","");
					}else if(data.result=="SUCCESS"){
						window.location.href=ctx+'/member/index?'+jsonAjax.random();
					}
				}
			});
		});
	});
});