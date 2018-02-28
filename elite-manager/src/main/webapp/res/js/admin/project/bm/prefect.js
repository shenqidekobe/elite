var projectprefectlist = {

	showForm : function() {
		var id = $(this).attr("data");
		jsonAjax.post({
			url : ctx + '/project/bm/prefect/view',
			data : {
				projectId : id
			},
			success : function(data) {
				$("#auditData").html(data);
				$('#myModel').modal('show');
				//百度编辑器
				UE.delEditor('content');
				ueEditor = UE.getEditor('content',{
						 initialFrameWidth:"100%",//初始化选项
						 toolbars:[],
						 autoHeightEnabled: true,//高度自动增长
					     initialFrameHeight:200
					 })
				ueEditor.ready(function(){
					ueEditor.focus(true)
					ueEditor.setHeight(200)
                     });
				$("button[name=projectTypeSelect]").click(
						projectprefectlist.selectType);
				$("button[name=dustry_btn]").click(
						projectprefectlist.selectDustry);
				$("button[name=func_btn]").click(
						projectprefectlist.selectfunction);
				$("a[id=budgeSelectId]").click(projectprefectlist.selectBudge);
				projectprefectlist.validate();
				//解决方案显示
				projectprefectlist.initSelectType();
				//功能分类显示
				projectprefectlist.initFuncType();
				//行业分类显示
				projectprefectlist.initDustryType();
				//城市选择器
				$('#areaName').kuCity();
				//期望交付时间选择器
				$('#startTimeId').datepicker({
					language : 'zh-CN',
					autoclose : true,
					format : "yyyy-MM-dd",
					todayHighlight : true,
				}).on("changeDate",function(e){
					endTime=$("#expectTimeId").val();
					var startTime=projectprefectlist.format(e.date,"yyyy-MM-dd");;
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
				//期望交付时间选择器
				$('#expectTimeId').datepicker({
					language : 'zh-CN',
					autoclose : true,
					format : "yyyy-MM-dd",
					todayHighlight : true,
				}).on("changeDate",function(e){
			    	var startTime=$("#startTimeId").val();
			    	var endTime=projectprefectlist.format(e.date,"yyyy-MM-dd");;
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
			}
		})
	},
	initSelectType : function() {
		var data = $("#typeShow").text();
		var btns = $("#typeBtnGroups").children();
		var length = btns.length;
		if (data.length > 0) {
			for (var i = 0; i < btns.length; i++) {
				var dict = $(btns[i]).attr("data");
				if (data.indexOf(dict) >= 0) {
					$($(btns[i]).children()[0]).addClass("btn-info")
							.removeClass("btn-default");
				}
			}
		}
	},
	initFuncType : function() {
		var data = $("#funcShowId").text();
		var btns = $("#funcBtnGroups").children();
		var length = btns.length;
		if (data.length > 0) {
			for (var i = 0; i < btns.length; i++) {
				var dict = $(btns[i]).attr("data");
				if (data.indexOf(dict) >= 0) {
					$($(btns[i]).children()[0]).addClass("btn-info")
					.removeClass("btn-default");
				}
			}
		}
	},
	initDustryType:function(){
		var data=$("#dustryShowId").text();
		var btns=$("div[id='dustrysGroupsId']");
		var length=btns.length;
		if (data.length > 0) {
			for (var i = 0; i < btns.length; i++) {
				var dict = $(btns[i]).attr("data");
				if (data.indexOf(dict) >= 0) {
					$($(btns[i]).children()[0]).addClass("btn-info")
					.removeClass("btn-default");
				}
			}
		}
	},
	//行业分类
	selectDustry: function() {
		var data = $(this).text();
		var datavalue =$(this).attr("data");
		var value = $("#dustryShowId").html();
		var postValue=$("#productIndustryId").val();
		if (value.indexOf(data) >= 0) {
			var newvalue = value.replace(data, '');
			$("#dustryShowId").html(newvalue);
			var replacestr=datavalue;
			if(postValue.indexOf(","+datavalue)>0){
				replacestr=","+datavalue;
			}
			postValue=postValue.replace(replacestr,'');
			if(postValue.indexOf(",")==0)
			{
				postValue=postValue.replace(',','');
			}
			$("#productIndustryId").val(postValue)
		} else {
			if(postValue!=null){
				var postValues=postValue.split(",");
				if(postValues.length>=3){
					$.tips({
						content : "行业分类最多选三个"
					});
					return ;
				}
			}
			$("#dustryShowId").append(" " + data);
			if(postValue==null||postValue.length==0){
				$("#productIndustryId").val(datavalue)
			}
			else{
				$("#productIndustryId").val(postValue+","+datavalue)
			}
			
		}
		if ($(this).hasClass("btn-info")) {
			$(this).removeClass("btn-info").addClass("btn-default");
		} else {
			$(this).addClass("btn-info").removeClass("btn-default")
		}
		
	},
	//解决方案类型
	selectType:function() {
		var data = $(this).text();
		var datavalue =$(this).attr("data");
		var value = $("#typeShow").html();
		var postValue=$("#projectTypeId").val();
		if (value.indexOf(data) >= 0) {
			var newvalue = value.replace(data, '');
			$("#typeShow").html(newvalue);
			var replacestr=datavalue;
			if(postValue.indexOf(","+datavalue)>0){
				replacestr=","+datavalue;
			}
			postValue=postValue.replace(replacestr,'');
			if(postValue.indexOf(",")==0)
				{
				postValue=postValue.replace(',','');
				}
			$("#projectTypeId").val(postValue)
		} else {
			$("#typeShow").append(" " + data);
			if(postValue==null||postValue.length==0){
				$("#projectTypeId").val(datavalue)
			}
			else{
				$("#projectTypeId").val(postValue+","+datavalue)
			}
			
		}
		if ($(this).hasClass("btn-info")) {
			$(this).removeClass("btn-info").addClass("btn-default");
		} else {
			$(this).addClass("btn-info").removeClass("btn-default")
		}

	},
	//功能分类选择
	selectfunction : function() {
		var data = $(this).text();
		var datavalue =$(this).attr("data");
		var value = $("#funcShowId").html();
		var postValue=$("#postfuncId").val();
		if (value.indexOf(data) >= 0) {
			var newvalue = value.replace(data, '');
			$("#funcShowId").html(newvalue);
			var replacestr=datavalue;
			if(postValue.indexOf(","+datavalue)>0){
				replacestr=","+datavalue;
			}
			postValue=postValue.replace(replacestr,'');
			if(postValue.indexOf(",")==0)
			{
				postValue=postValue.replace(',','');
			}
			$("#postfuncId").val(postValue)
		} else {
			$("#funcShowId").append(" " + data);
			if(postValue==null||postValue.length==0){
				$("#postfuncId").val(datavalue)
			}
			else{
				$("#postfuncId").val(postValue+","+datavalue)
			}
			
		}
		if ($(this).hasClass("btn-info")) {
			$(this).removeClass("btn-info").addClass("btn-default");
		} else {
			$(this).addClass("btn-info").removeClass("btn-default")
		}
		
	},
	//项目预算选择
	selectBudge : function() {
		var data = $(this).attr("data");
		var indata = $(this).attr("value");
		$("#bugetShowId").html(data);
		$("#projectBudgetId").val(indata);
	},
	//验证
	validate : function() {
		$("#prefectForm").validate({
			debug : false,
			rules : {
				"name" : {
					required : true,
					minlength : 1,
					maxlength : 15,
				},
				intro : {
					required : true
				},
				areaName : {
					required : true,
				}
			},
			messages : {
				name : {
					required : '请输入项目名称',
					minlength : '项目名称只允许1~15个字符',
					maxlength : '项目名称只允许1~15个字符',
				},
				intro : {
					required : '请输入项目简介'
				},
				areaName : {
					required : '请输入城市'
				}

			},
			submitHandler : function(form) {
				var startTime=$("#startTimeId").val();
				var endTime=$("#expectTimeId").val();
				 var today=new Date();
			/*	if(startTime==""){
					$.tips({
						content : "请选择开始时间"
					});
					return;
				}else if(endTime==""){
					$.tips({
						content : "请选择期望交付时间"
					});
					return;
				}
				else if(new Date(endTime)<new Date(startTime))
					{
					
					$.tips({
						content : "期望交付时间应大于开始时间"
					});
					return;
					}*/
					
				jsonAjax.post({
					url : ctx + '/project/bm/prefect/save',
					data : $("#prefectForm").serialize(),
					success : function(data) {
						if (BASE.JS_RESULT.SUCCESS == data.result) {
							$('#myModel').modal('hide');
							list.search();
						} else {
							$.tips({
								content : data.msg
							});
						}
					}
				})
			}
		});
	},
	format:function(data,fmt){
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