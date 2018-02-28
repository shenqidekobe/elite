$(function() {
	list.init();
});
var list = {
	init : function() {
		list.search();
		$("#create").click(list.create);
		$("#searchDict").click(function(){
			//搜索默认第一页
			$("#pager_pageth").val(1);
			list.search()
		});
		$("a[id=showByType]").click(function(){
			$("#pager_pageth").val(1);
			list.searchByType($(this).attr("data"))
		});
	},
	search : function() {
		jsonAjax.post({
			url : ctx + '/settings/dict/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id=remove]").click(list.remove);
				$("a[id=update]").click(list.update);
				$("a[id=upmove]").click(list.upmove);
				$("a[id=downmove]").click(list.downmove);
				var type=$("#TypeId").val();
				if(null==type||""==type){
					$("#TypeId").val("project_stage");
				}
				
			}
		})
	},
	showRight:function(){
		var id = $(this).parent().attr("data");
		showRightDialog(1)
	},
	searchByType : function(type) {
		$("li[name=listTypeLiName]").removeClass();
		$(this).parent().addClass("active");
		$("#TypeId").val(type);
		$("#dictTypeId").val(type);
		if (type.indexOf("_option")>0) {
			$("#serarchHeader").hide();
			list.optionSearch(type);
		} else {
			$("#serarchHeader").show();
			list.search();
		}
	

	},
	optionSearch:function(dictType){
			jsonAjax.post({
				url : ctx + '/settings/dict/option/view',
				data : {dictType:dictType},
				success : function(data) {
					$("#listData").html(data);
					$("#financeBtn").click(list.financeUpdate);
					$("#qualityBtn").click(list.qualityUpdate);
					$("#eliteCouBtn").click(list.eliteUpdate);
					$("#eliteBtn").click(list.eliteUpdate);
					$("#partnerCouBtn").click(list.partnerUpdate);
					$("#partnerIncomeBtn").click(list.partnerIncomeUpdate);
					$("#partnerBtn").click(list.partnerUpdate);
				}
			})
	},
	remove : function() {
		var id = $(this).parent().attr("data");
		$.confirm({
			title : "提示",
			content : "你确定要删除",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/settings/dict/remove',
					data : {
						id : id
					},
					success : function(data) {
						if ("SUCCESS" == data.result) {
							list.search();
						} else {
							$.tips({
								content : data.msg
							});
						}
					}
				});
			}
		});
	},
	update : function(user) {
	if($("#TypeId").val()=="job_role")
		{
		$('#jobDivId').show();
		}
	else{
		$('#jobDivId').hide();
		$('#parentName').val("");
		$('#parentId').val("");
	   }
		$("#userForm")[0].reset();
		$("#dictId").val($("#TypeId").val());
		var id = $(this).parent().attr("data");
		jsonAjax.post({
			url : ctx + '/settings/dict/view',
			data : {
				id : id
			},
			success : function(data) {
				var dict = data;
				$("#dictId").val(dict.id)
				$("#dictKeyId").val(dict.dictKey);
				$("#dictValId").val(dict.dictVal);
				$("#dictTypeId").val(dict.dictType);
				$("#dictDescId").val(dict.dictDesc);
				$("#parentId").val(dict.parentId);
				$("#parentName").val(dict.parentName);
				$('#myModel').modal('show');
				list.validate();
			}
		});

	},
	create : function() {
		$("#userForm")[0].reset();
		 $("#dictId").val("");
		$("#dictTypeId").val($("#TypeId").val());
	if($("#TypeId").val()=="job_role")
		{
		$('#jobDivId').show();
		}
	else{
		$('#jobDivId').hide();
		$('#parentName').val("");
		$('#parentId').val("");
	   }
	 
		$('#myModel').modal('show');
		list.validate();
	},
	validate : function() {
		$("#parentName").click(function() {
			selectDept.init();
			var deptObj = $("#parentName");
			var deptOffset = $("#parentName").offset();
			$("#menuContent").css({
				left : deptOffset.left + "px",
				top : deptOffset.top + deptObj.outerHeight() + "px"
			}).slideDown("fast");
			$("body").bind("mousedown", function(event) {
				if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" 
					|| $(event.target).parents("#menuContent").length>0)) {
					$("#menuContent").fadeOut("fast");
					$("body").unbind("mousedown");
				}
			});

		});
		
		$("#userForm").validate({
			debug : false,
			rules : {
				dictKey : {
					required : true,
					minlength : 1,
					maxlength : 16,
					remote : {
						url : ctx + "/settings/dict/check/key",
						type : "Post",
						data : {
							id : function() {
								return $("#dictId").val();
							},
						}
					}
				},
				dictVal : {
					required : true,
				},

			},
			messages : {
				dictKey : {
					required : '请输入Key',
					minlength : 'Key只允许1~16个字符',
					maxlength : 'Key只允许1~16个字符',
					remote : 'Key已存在',
				},
				dictVal : {
					required : '请输入val',
				},
			},
			submitHandler : function(form) {
				list.formSumbit();
			}
		});
	},
	formSumbit : function() {
		jsonAjax.post({
			url : ctx + '/settings/dict/save',
			data : $("#userForm").serialize(),
			success : function(data) {
				if ("SUCCESS" == data.result) {
					$('#myModel').modal('hide');
					list.search();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		});
	},
	upmove : function() {
		list.move("up", $(this).parent().attr("data"))
	},
	downmove : function(moveType) {
		list.move("down", $(this).parent().attr("data"))
	},
	move : function(moveType, id) {
		jsonAjax.post({
			url : ctx + '/settings/dict/move',
			data : {
				id : id,
				moveType : moveType
			},
			success : function(data) {
				if ("SUCCESS" == data.result) {
					list.search();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		});
	},
	optionValite:function(valsize,percentValite)
	{
		var data=$("#listForm").serialize();
		for(var i=0;i<=valsize;i++)
			{
			 var id="input[name='dicts["+i+"].dictVal']";
			  var value=$(id).val();
			  if(null==value||""==value){
				  $.tips({
						content : "请输入必须内容"
					});
				  return;
				  break;
			  }
			  else if(isNaN(value)){
				  $.tips({
						content : "请输入数字"
					});
				  return;
				  break;
			  }
			  else if(value!= Math.abs(value)){
				  $.tips({
						content : "请输入正数"
					});
				  return;
				  break;
			  }else if(percentValite.indexOf(i)!=-1){
				  if(value<0||value>100){
					  $.tips({
							content : "请输入正确的百分比,百分比范围在0到100之间",
							timeout:4000
						});
					  return;
					  break;
				  }
			  }
			  
			}
		list.optionUpdate(data);
	}
	,
	financeUpdate:function()
	{
		list.optionValite(2,[1]);
	},
	qualityUpdate:function()
	{
		list.optionValite(3,[0,1,2]);
	},
	eliteUpdate:function()
	{
		list.optionValite(9,[]);
	},
	partnerUpdate:function()
	{
		list.optionValite(21,[2,5,8,13,16,19]);
	},
	partnerIncomeUpdate:function(){
		var data= $("#listForm").serialize();
		var type=$(this).attr("data");
		jsonAjax.post({
			url : ctx + '/settings/dict/option/'+type+'/save',
			data :data,
			success : function(data) {
				if ("SUCCESS" == data.result) {
					$.tips({
						content : "更新成功"
					});
				} else {
					$.tips({
						content : "输入有误,所填内容该全为数字",
						timeout:4000
					});
				}
			}
		})
	},
	optionUpdate:function(requestdata)
	{
		jsonAjax.post({
			url : ctx + '/settings/dict/option/save',
			data :requestdata,
			success : function(data) {
				if ("SUCCESS" == data.result) {
					$.tips({
						content : "更新成功"
					});
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		})
		
	},
};