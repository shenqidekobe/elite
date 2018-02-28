$(function() {
	ueEditor = UE.getEditor('content', {
		initialFrameWidth : "100%" // 初始化选项
	});
	ueEditor2 = UE.getEditor('contentIntro', {
		initialFrameWidth : "100%" // 初始化选项
	});
	list.init();
});
var list = {
	init : function() {
		$("#back").click(list.back)
		$("#submitBtn").click(list.submit);
		$("span[id=defineNameId]").click(list.addremoveInput)
		list.dateTimePicker();
		list.validate();
		// 取消
		$("#cancel").click(function() {

			window.location.href = ctx + '/project/pm'
		})
		
		//招标结束时间
		$('#tenderoverTime').datepicker({
			language : 'zh-CN',
			autoclose : true,
			format : "yyyy-MM-dd",
			todayHighlight : true,
		})
			//期望交付时间 开始时间选择器
				$('#startTimeId').datepicker({
					language : 'zh-CN',
					autoclose : true,
					format : "yyyy-MM-dd",
					todayHighlight : true,
				}).on("changeDate",function(e){
					endTime=$("#endTimeId").val();
					var startTime=list.format(e.date,"yyyy-MM-dd");
					if(startTime!=0&&endTime!=0){
						if(new Date(endTime)>=new Date(startTime)){
						var interval=new Date(endTime)-new Date(startTime);
						var day=interval/(24 * 60 * 60 * 1000);
						$("#expecTimeDayId").text(day);}
						else{
							$.tips({
								content : "开始时间应小于结束时间"
							});
							$("#expecTimeDayId").text(0);
							
						}
					}
				})
				//期望交付时间 结束时间选择器
				$('#endTimeId').datepicker({
					language : 'zh-CN',
					autoclose : true,
					format : "yyyy-MM-dd",
					todayHighlight : true,
				}).on("changeDate",function(e){
			    	var startTime=$("#startTimeId").val();
			    	var endTime=list.format(e.date,"yyyy-MM-dd");;
			    	if(startTime!=0&&endTime!=0){
			    		if(new Date(endTime)>=new Date(startTime)){
							var interval=new Date(endTime)-new Date(startTime);
							var day=interval/(24 * 60 * 60 * 1000);
							$("#expecTimeDayId").text(day);}
							else{
								$.tips({
									content : "开始时间应小于结束时间"
								});
								$("#expecTimeDayId").text(0);
								
							}
			    	}
			    })

		upload.uploadFile($("#defineFile"), "project", "resume_atta", function(
				data) {
			$("#fileName").html(data.originalName);
			$("#removeSpan").attr("style", "");
			$("#removeSpan").bind('click', function(e) {
				var id = $("#defineFileId").val();
				upload.removeFile("resume_atta", function(url) {
					jsonAjax.post({
						url : url,
						data : {
							attaId : id
						},
						success : function(drsp) {
							if (drsp.result == "SUCCESS") {
								$("#fileName").html("");
								$("#defineFileId").val("");
								$("#removeSpan").attr("style", "display:none");
							}
						}
					});
				});
			});
			$("#defineFileId").val(data.attaId);
		});

	},
	search : function() {
		$('#myModel').modal('hide');
	},
	back : function() {
		window.location.href = ctx + '/project/pm'
	},
	validate : function() {
		$("#form").validate({
			debug : false,
			rules : {
				platformIntro : {
					required : true,
				},
				name : {
					required : true,
				},
				endTime : {
					required : true,
				},
				referProject : {
					required : true,
				},
				stockRate : {
					range : [ 0, 100 ]
				},
				intro : {
					required : true
				},
				tenderoverTime : {
					required : true
				},
				qualitTyMonth : {
					required : true,
					range : [ 0, 10000],
			     	digits:true
				},
				startTime:{
					required:true,
				},
				endTime:{
					required:true,
				}

			},
			messages : {
				platformIntro : {
					required : '请输入平台PM说明 ',
				},
				name : {
					required : '项目名不能为空 ',
				},
				endTime : {
					required : '请选择期望完成时间 ',
				},
				referProject : {
					required : '请输入参考项目 ',
				},
				stockRate : {
					range : "让出股权输入值在0到100之间"
				},
				intro : {
					required : "请输入项目简介"
				},
				tenderoverTime : {
					required : "请选择截止时间"
				},
				qualitTyMonth : {
					required : "请输入质保期",
					range : "请输入正确请输入质保期",
					digits: "请输入正确请输入质保期(整数)"
				},	startTime:{
					required:"请选择开始时间",
				},
				endTime:{
					required:"请选择结束时间",
				}

			},
			submitHandler : function(form) {
				list.formSumbit();
			}
		});
	},
	formSumbit : function() {
		var content = UE.getEditor('content').getContent();
		$("#content_inputId").val(content);
		var endtimeStr = $("#endTimeId").val();
		var starttimeStr = $("#startTimeId").val();
		var tenderoverTimeStr = $("#tenderoverTime").val();
		var endtime = new Date(endtimeStr);
		var starttime = new Date(starttimeStr);
		var tenderoverTime = new Date(tenderoverTimeStr);
		var selectTable = $("#selectTableId").children().children();
		var today=new Date();
		
		if(starttime<today){
			$.tips({
				content : "期望交付时间开始时间要晚于现在时间",
				timeout : 4000
			});
			return;
		}else if(starttime>endtime){
			$.tips({
				content : "期望交付时间开始时间应小于结束时间",
				timeout : 4000
			});
			return;
		}
		if (tenderoverTime>endtime) {
			$.tips({
				content : "截止时间应早于项目期望交付时间结束时间",
				timeout : 4000
			});
			return;
		}
		else if (tenderoverTime<today) {
			$.tips({
				content : "截止时间不应早于当前时间",
				timeout : 4000
			});
			return;
		}
		else if (selectTable.length == 1) {
			$.tips({
				content : "项目没有设置研发阶段，请相应项目经理先完善项目研发阶段，",
				timeout : 4000
			});
			return;
		} else {
			jsonAjax.post({
				url : ctx + '/project/pm/tender/save',
				data : $("#form").serialize(),
				success : function(data) {
					if (BASE.JS_RESULT.SUCCESS == data.result) {
						sessionStorage.setItem('defineBackPage','pmlist')
						list.findDefineDetail();
					} else {
						$.tips({
							content : data.msg
						});
					}
				}
			})
		}
	},
	addremoveInput : function() {
		var id = $(this).attr("data");
		var inputid = "input[id=" + id + "]";
		if ($(inputid).prop("disabled") == true) {
			$(inputid).attr("disabled", false)
		} else {
			$(inputid).attr("disabled", true)
		}
	},
	dateTimePicker : function() {
		$('input[checked=false').datepicker({
			language : 'zh-CN',
			autoclose : true,
			format : "yyyy-MM-dd",
			todayHighlight : true,
		})

	},
	findDefineDetail : function() {
		$('#detailForm').attr('action', ctx + "/project/tender/findeview")
				.submit();
	},format:function(data,fmt){
		 var o = {   
				    "M+" : data.getMonth()+1,                 //月份   
				    "d+" : data.getDate(),                    //日   
				    "h+" : data.getHours(),                   //小时   
				    "m+" : data.getMinutes(),                 //分   
				    "s+" : data.getSeconds(),                 //秒   
				    "q+" : Math.floor((data.getMonth()+3)/3), //季度   
				    "S"  : data.getMilliseconds()             //毫秒   
				  };   
				  if(/(y+)/.test(fmt))   
				    fmt=fmt.replace(RegExp.$1, (data.getFullYear()+"").substr(4 - RegExp.$1.length));   
				  for(var k in o)   
				    if(new RegExp("("+ k +")").test(fmt))   
				  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
				  return fmt; 
	}
};