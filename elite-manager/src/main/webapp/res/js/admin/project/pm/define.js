$(function() {
	list.init();
});
var list = {
	init : function() {
		list.ueEditorInit();
		$("#back,#cancelBtn").click(list.back)
		$("#submitBtn").click(list.submit);
		$("span[id=defineNameId]").click(list.addremoveInput);
		list.validate();
		list.dateTimePicker();
		$('#expecTimeId').datepicker({
			language : 'zh-CN',
			autoclose : true,
			format : "yyyy-MM-dd",
			todayHighlight : true,
		})
		$('#deliveryTimeId').datepicker({
			language : 'zh-CN',
			autoclose : true,
			format : "yyyy-MM-dd",
			todayHighlight : true,
		})
		upload.uploadFile($("#defineFile"), "project", "resume_atta",
				function(data) {
					$("#fileName").html(data.originalName);
					$("#removeSpan").attr("style","");
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
										$("#removeSpan").attr("style","display:none");
									}
								}
							});
						});
					});
					$("#defineFileId").val(data.attaId);
				});
	},
	ueEditorInit:function(){
	 ueEditor = UE.getEditor('content',{
		 initialFrameWidth:"100%" //初始化选项
	 })}
	 ,
	 
	search : function() {
		$('#myModel').modal('hide');
	},
	back : function() {
		window.history.back(-1);
	},
	validate : function() {
		$("#defineForm").validate({
			debug : false,
			rules : {
				totalAmount : {
					required : true,
					min:0,
					number:true,
				},
				otherDesc:{
					required:true,
				},
			},
			messages : {
				totalAmount : {
					required : '请输入研发费用 ',
					min:'请输入正确金额',
					number:'请输入正确金额'
				},otherDesc:{
					required:"请输入其他说明",
				}
			},
			submitHandler : function(form) {
				list.submitForm();
			}
		});
	},submitForm:function(){
		 var selectTable=$("#selectTableId").children().children();
		 var lastStageEndTime;
	    if(selectTable.length==1){
	    	$.tips({
				content : "项目没有设置研发阶段，请相应项目经理先完善项目研发阶段，",
				timeout:4000
			});
	    	return;
	    }else{
	    	var submitstatus=true;
	    	var allcount=0;
	    	 var proTimeval=0;
	    	 var today=new Date();
	      for(var i=1;i<selectTable.length;i++){
	    	  var  selecttinput=$(selectTable[i]).children().last().children().last();
	    	  var   selectTime=$(selectTable[i]).children().eq(1).children().last().val();
	    	  var   title=$(selectTable[i]).children().eq(0).children().eq(0).html();
	 	    if (selecttinput.attr("disabled")!="disabled"){
	 	    		 if(selectTime=="")
	     			  {
	     			  $.tips({
	 						content : "请选择"+title+"阶段时间",
	 					});
	 		    	  return;
	     			  }
	     		  else{
	     			  var selectTimeval=selectTime.split("-");
	     			  var startTime=selectTimeval[0];
	     			  var endTime=selectTimeval[1];
	     			  var start=new Date(startTime);
	 				  var end=new Date(endTime);
	 				 lastStageEndTime=end;
	 				 if(start<today){
						  $.tips({
								content : title+"时间应大于当前时间",
							  });
				    	      return;
					  }else
	     			  if(proTimeval!=0&&start<proTimeval){
	     					  $.tips({
	   							content : title+"开始时间应晚于前一阶段的结束时间",
	   						  });
	   			    	      return;
	     				  }
	     			    proTimeval=end;
	     				  
	     			  }
	    		  var  amoutval=selecttinput.val();
			      if(amoutval==""){
			    	  $.tips({
							content : "请输入"+title+"所需费用",
						});
			    	  return;
			      }else if(isNaN(amoutval)){
			    	  $.tips({
							content : "请输入正确所需金额",
						});
			    	  return;
			      }else{
			    	  allcount=allcount+ Number(amoutval);
			      }
	    	  }
	      }
	     var totalAmount= $("#totalAmountId").val();
	     var attaId=$("#defineFileId").val();
	     var deliveryvalue=$("#deliveryTimeId").val();
	     var deliveryTime=new Date($("#deliveryTimeId").val());
	     if(deliveryvalue==null||deliveryvalue==""){
	    	 $.tips({
					content : "请选择期望交付时间",
					timeout:4000
				});
	    	  return;
	     }else
	     if(totalAmount!=allcount){
	    	 $.tips({
					content : "各个阶段所需费用总和为："+allcount+" 与所输入的研发费用不同",
					timeout:4000
				});
	    	  return;
	     }else if(deliveryTime<lastStageEndTime){
	    	 $.tips({
					content : "期望交付时间应大于项目阶段结束时间",
					timeout:4000
				});
	    	  return;
	     }else if(attaId==""){
	    	 $.tips({
					content : "请上传说明文档",
					timeout:4000
				});
	    	  return;
	     }
	     else{
	    	jsonAjax.post({
				url : ctx + '/project/pm/define/save',
				data : $("#defineForm").serialize(),
				success : function(data) {
					if (BASE.JS_RESULT.SUCCESS == data.result) {
						list.findDefineDetail();
					} else {
						$.tips({
							content : data.msg
						});
					}
				}
			})
	    }
	  }
	},
	
	addremoveInput:function()
	{
		var id = $(this).attr("data");
		var inputid="input[id="+id+"]";
		if($(inputid).prop("disabled")==true){
			$(inputid).attr("disabled",false)
		}else{
			$(inputid).attr("disabled",true)
		}
	},
	dateTimePicker : function() {
		$('input[checked=false]').daterangepicker({
			format : "YYYY/MM/DD"
		},
		function(start, end, label) {
          })
	},findDefineDetail:function(){
		$('#detailForm').attr('action', ctx + "/project/pm/finddefine/view").submit();
	}
};