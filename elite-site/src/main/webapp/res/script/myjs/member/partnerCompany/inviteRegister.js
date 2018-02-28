function initInviteRegister() {
	//时间选择器
	var receiptStart="";
	var receiptEnd="";
	$('#receiptdateStart,#receiptdateStart-date-icon').datepicker({
		autoclose : true
	}).on("changeDate", function(e) {
		var time = e.date;
		receiptStart = time.getTime();
		if (receiptEnd != "" && receiptEnd < receiptStart) {
			tostHint("时间错误", "开始时间不能晚于结束时间");
			receiptStart="";
			$("#receiptdateStart").val("");
			return;
		} else {
		   $("#receiptdateStart").val(time.Format("yyyy-MM-dd"));
		}
	});
	$('#receiptdateEnd,#receiptdateEnd-date-icon').datepicker({
		autoclose : true
	}).on("changeDate", function(e) {
		var time = e.date;
		receiptEnd = time.getTime();
		if (receiptStart != "" && receiptEnd < receiptStart) {
			tostHint("时间错误", "开始时间不能晚于结束时间");
			receiptEnd="";
			 $("#receiptdateEnd").val("")
			return;
		} else {
		    $("#receiptdateEnd").val(time.Format("yyyy-MM-dd"));
		}
	});
	// 从业年限
	$(document).on('click', '#workYear,#workyear-triangle,#birth', function() {
		$("#workYear-select").fadeIn();
		$("#workYear-select li").click(function() {
			var thiss = $(this);
			$("#workYear").text(thiss.text());
			$("#jobAge").val(thiss.attr("data"))
			$("#workYear-select").fadeOut("fast");
		});
		$("body").bind("mousedown", function(event) {
			$("#workYear-select").fadeOut("fast");
			$("body").unbind("mousedown");
		});
	});
	$("#inviteRegisterBtn").click(function(){
		selectStatusPaneFun();
	})
	$('#areaNameChannel').kuCity("channelDiv");
	$('#areaName').kuCity("eliteDiv");
	// 人才属性
	$(document).on('click', '#defineRoleShow,#defineRole,#defineRole-triangle', function() {
		$("#defineRole-select").fadeIn();
		$("#defineRole-select li").click(function() {
			var thiss = $(this);
			$("#defineRoleShow").text(thiss.text());
			$("#defineRoleId").val(thiss.attr("data"))
			$("#defineRole-select").fadeOut("fast");
		});
		$("body").bind("mousedown", function(event) {
			$("#defineRole-select").fadeOut("fast");
			$("body").unbind("mousedown");
		});
	});
	// 
	$(document).on('click', '#channelDefineRoleShow,#channelDefineRole,#channelDefineRole-triangle', function() {
		$("#channelDefineRole-select").fadeIn();
		$("#channelDefineRole-select li").click(function() {
			var thiss = $(this);
			$("#channelDefineRoleShow").text(thiss.text());
			$("#channelDefineRoleId").val(thiss.attr("data"))
			$("#channelDefineRole-select").fadeOut("fast");
		});
		$("body").bind("mousedown", function(event) {
			$("#channelDefineRole-select").fadeOut("fast");
			$("body").unbind("mousedown");
		});
	});
    //项目推荐方>邀请注册  左右切换
    $("#inviteContainer .title_ul li").click(function() {
        var data = $(this).attr("data");
        $("#searchTypeCountId").val("true")
        $("#inviteContainer .title_ul li").removeClass("active_color");
        $(this).addClass("active_color");
        if (data == "elite") {
            $("#elite_choose").show();
            $("#channel_choose").hide();
            $("#searchTypeId").val("elite");
            $(this).parent().next().animate({"left": "0px"});
    		$("#enterCountId").hide();
    		$("#ulBordrId").css("width","380px");
    		   var inviteUrl=$("#codeHrefId").val()+"&ic="+$("#inviteCode").html();
               $("#inviteUrl").html(inviteUrl);
               $("#inviteUrl").next().attr(inviteUrl) 
        } else if (data == "channel") {
            $("#elite_choose").hide();
            $("#channel_choose").show();
            $("#searchTypeId").val("channel");
            $(this).parent().next().animate({"left": "220px"});
            $("#enterCountId").show();
            $('#ulBordrId').removeAttr("style")
             var inviteUrl= $("#codeHrefId").val().split("?")[0] +"/p?ts=partnerCompany&ic="+$("#inviteCode").html();
            $("#inviteUrl").html(inviteUrl);
            $("#inviteUrl").next().attr(inviteUrl) 
          
        }
        $("#pager_pageth").val(1);
        $("#selectStatusPane li:first").click();
    });

	//新增精英，渠道，选择框查询
	$("#selectStatusPane li").click(function() {
		var record = $(this).attr("data");
		$("#statusId").val(record);
		$("#pager_pageth").val(1);
		$(this).addClass("active-status").siblings().removeClass("active-status");
		selectStatusPaneFun();
	})
	//新增项目显示
  $("#elite_add").click(function(){
	  var status=$("#companyStatusId").val();
	  if(status=="normal"){
		  showAddEliteView();
	  }else if(status=="waitAduit"){
		  tostHint("", "您还未通过审核，不能进行备案操作，请去申请审核");
	  }else if(status=="aduitIn"){
		  tostHint("", "您还未通过审核，不能进行备案操作，请耐心等待审核");
	  }else {
		  tostHint("", "您未通过审核，不能进行备案操作");
	  }
	
  })
	$("#cancelEliteBtn,#elite_close_btn").click(function(){
		$("#newRecord_elite").fadeOut();
	});
	 $("#saveProjectBtn").click(function(){
		  saveProjectSubmit();
		});
   //新增渠道显示
	$("#channel_add").click(function(){
		  var status=$("#companyStatusId").val();
		  if(status=="normal"){
			  showAddChannelView();
		  }else if(status=="waitAduit"){
			  tostHint("", "您还未通过审核，不能进行备案操作，请去申请审核");
		  }else if(status=="aduitIn"){
			  tostHint("", "您还未通过审核，不能进行备案操作，请耐心等待审核");
		  }else {
			  tostHint("", "您未通过审核，不能进行备案操作");
		  }
		
	})
	 $("#saveChannelBtn").click(function(){
	  saveChannelSubmit();
	});
	$("#cancelChannelBtn,#channel_close_btn").click(function(){
		$("#newRecord_channel").fadeOut();
	});
	selectStatusPaneFun();
	
	//项目所处阶段选择
	$("#projectStageShowId,#projectStageSelectIconId").click(function(){
		$("#projectStage_selectUI").fadeIn();
		$("#projectStage_selectUI li").click(function() {
			var thiss = $(this);
			$("#projectStageShowId").html(thiss.text());
			$("#productStageId").val(thiss.attr("data"))
			$("#projectStage_selectUI").fadeOut("fast");
		});
		$("body").bind("mousedown", function(event) {
			$("#projectStage_selectUI").fadeOut("fast");
			$("body").unbind("mousedown");
		})
	})
	
	// 选择项目所属行业
	$("#industryUlId li").click(function(){
		var industrys=new Array();
		$("#industryUlId li").each(function(){
			if($(this).hasClass("active")){
				industrys.push($(this).attr('data'));
			}
		})
		if($(this).hasClass("default")){
			if(industrys.length < 3){
			$(this).attr("class","active");
			$("#productIndustryId").val($(this).attr("data"))
			}
			else{
				alert("所属行业最多3个");
			}
		}else{
			$(this).attr("class","default");
		}
	})
	//项目开发类型
	$("#projectTypeSelectId li").click(function(){
		if($(this).hasClass("default")){
			$(this).attr("class","active");
		}else{
			$(this).attr("class","default");
		}
		
	})
	//是否以你的名义联系
	$("#selectContactId li").click(function(){
		$("#contactpartnerId").val($(this).attr("data"));
		$(this).children().eq(0).attr("class","li_icon contact_yes")
		$(this).siblings().children().eq(0).attr("class","li_icon contact_no")
	})
}
function showAddEliteView() {

	$("#newRecord_elite").fadeIn();
	//清除缓存
	$('#addPersonForm')[0].reset();
	$("#projectTypeSelectId").children().attr("class","default");
	$("#industryUlId").children().attr("class","default");
	$("#projectStageShowId").html("请选择项目所处阶段");
	$("#productStageId").val("");
	$("#companyTipErrorId").hide();
	
	//调到最上面
    $("#addPersonForm").scrollTop(0)
	
	// 上传简历
	upload.uploadFile($("#resumeAttaFile"), "elite", "resume_atta", function(data) {
		$("#resumeAtta_show").show();
		var prd = '<button type="button" class="upload-file">' + data.originalName + '</button><img src=' + ctx + '"/res/images/del.png" data="' + data.attaId
				+ '" id="pbimg" class="delete-cross">';
		$("#resumeAtta_show").html(prd);
		$("img[id=pbimg]").unbind('click');
		$("img[id=pbimg]").bind('click', function(e) {
			var thiss = $(this);
			var bthis = thiss.prev();
			var id = thiss.attr("data");
			upload.removeFile("resume_atta", function(url) {
				jsonAjax.post({
					url : url,
					data : {
						attaId : id
					},
					success : function(drsp) {
						if (drsp.result == "SUCCESS") {
							$("#resumeAttaId").val('');
							thiss.remove();
							bthis.remove();
						}
					}
				});
			});
		});
		$("#resumeAattaId").val(data.attaId);
	});
}
function showAddChannelView() {
	$("#newRecord_channel").fadeIn();
	$('#addChannelForm')[0].reset();
	$("#channelTipErrorId").hide()
	$("#channelDefineRoleShow").text("请选择")
	$("#channelDefineRoleId").val("")

}
//新增渠道
function saveChannelSubmit() {
	$("#channelErrorTextId").html("")
	$("#channelErrorTipId").hide();
	var name = $("#channelNameId").val();
	if (name == '') {
		$("#channelErrorTextId").html("请填写姓名")
		showError();
		return false;
	}
	var mobileNum = $("#channelPhoneId").val();
	if (!validatePhone(mobileNum)) {
		$("#channelErrorTextId").html("请填写正确手机号")
		showError();
		return false;
	}
	var company = $("#channelDefineRoleId").val();
	if (company=="") {
		$("#channelErrorTextId").html("请选择身份类型")
		showError();
		return false;
	}
	
	var attaId = $("#resumeAattaId").val();
	jsonAjax.post({
		url : ctx + '/partner/partnerCompany/saveMemberPartnerCompany',
		data : $("#addChannelForm").serialize(),
		success : function(data) {
			if (data.result == "SUCCESS") {
				$("#channelErrorTextId").html("保存成功")
				showError();
				window.setTimeout(function() {
					$("#channelErrorTipId").hide();
					selectStatusPaneFun();
				}, 50);
				$("#newRecord_channel").fadeOut();
			} else {
				$("#channelErrorTextId").html(data.msg)
				showError();
			}

		}
	});
	
	function showError(){
		$("#channelErrorTipId").show();
		$("body").bind("mousedown",function(){
			$("#channelErrorTipId").hide();
			$("body").unbind("mousedown");
		});
	}

	
}

//错误提示显示


//新增项目备案
 function saveProjectSubmit() {
		$("#companyTipErrorTextId").html("")
		$("#companyTipErrorId").hide();
	var name = $("#name").val();
	if (name == '') {
		$("#companyTipErrorTextId").html("请填写姓名")
		showError();
		return;
		
	}
	var mobileNum = $("#phone").val();
	if (!validatePhone(mobileNum)) {
		$("#companyTipErrorTextId").html("请填写正确手机号")
		showError();
		return false;
	}
	var industrys = new Array();
	$('#industryUlId li').each(function() {
		if ($(this).hasClass("active")) {
			industrys.push($(this).attr('data'));
		}
	})
	$("#productIndustryId").val(industrys.join(','));
	if (industrys.length == 0) {
		$("#companyTipErrorTextId").html("行业不能为空")
		showError();
		return false;
	}
	var types =new Array()
	$('#projectTypeSelectId li').each(function() {
		if ($(this).hasClass("active")) {
			types.push($(this).attr('data'));
		}
	})
	$("#projectSolutionId").val(types.join(','));
	if (types.length == 0) {
		$("#companyTipErrorTextId").html("开发类型不能为空")
		showError();
		return false;
	}
	
	var projectIntro = $("#projectIntro").val();
	if (projectIntro == '' || projectIntro.length > 1000) {
		$("#companyTipErrorTextId").html("请必须填写且少于1000")
		showError();
		return false;
	}
	jsonAjax.post({
		url : ctx + '/partner/partnerCompany/project/save',
		data : $("#addPersonForm").serialize(),
		success : function(data) {
			if (data.result == "SUCCESS") {
				$("#companyTipErrorTextId").html("添加成功")
				showError();
				window.setTimeout(function() {
					selectStatusPaneFun();
					$("#companyTipErrorId").hide();
				}, 50);
				$("#newRecord_elite").fadeOut();
			} else {
				$("#companyTipErrorTextId").html(data.msg)
				showError();
			}
		}
	});
	
	
	function showError(){
		$("#companyTipErrorId").show();
		$("body").bind("mousedown",function(){
			$("#companyTipErrorId").hide();
			$("body").unbind("mousedown");
		});
	};

	
	
	
};

//显示错误


function selectStatusPaneFun() {
	var status=$("#statusId").val();
	var searchType=$("#searchTypeId").val();
	jsonAjax.post({
		url : ctx + "/partner/partnerCompany/inviteRegister/listData",
		data : $("#searchForm").serialize(),
		success : function(data) {
			$("#datalist").html(data);
			
			// 分页组件js
			initPageFun();
			$("button[id=viewProjectInfoId]").click(function(){
				$("#projectInfoContetnId").html($(this).attr("data"));
				$("#projectInfoBoxId").fadeIn();
				$("#projectInfoBoxCloseBtn").click(function(){
					$("#projectInfoBoxId").fadeOut();
				})
			});
			var count = $("#pager_rowCount").val();
			var changeCount=$("#searchTypeCountId").val();
			$("#searchTypeCountId").val("false");
			var showdata="项目";
			if("channel"==searchType){
				showdata="项目渠道";
			}
			if(changeCount=="true"){
				if("channel"==searchType){
					$("#registeredCountId").html("已注册"+showdata+" (" + $("#listregisterCountId").val() + ")");
					$("#enterCountId").html("已入驻"+showdata+"(" + $("#listenterCountId").val() + ")")
					$("#allCountId").html("已备案"+showdata+" (" + $("#listallCountId").val() + ")");
                  }
				else{
					$("#registeredCountId").html("已入驻"+showdata+"(" + $("#listenterCountId").val() + ")")
					$("#allCountId").html("已备案"+showdata+" (" + $("#listallCountId").val() + ")");
				}
			}
			
					if (status == "audit_pass") {
						$("#enterCountId").html("已入驻"+showdata+"(" + count + ")")
					}
					else if (status == "registered") {
						if("channel"==searchType){
							showdata="已注册"+showdata;
						}else{
							showdata="已入驻"+showdata
						}
						$("#registeredCountId").html(showdata+" (" + count + ")");
						$("#channel_registered").show();
						$("#channel_record").hide();
					}else{
						$("#allCountId").html("已备案"+showdata+" (" + count + ")");
						$("#channel_registered").hide();
						$("#channel_record").show();
					}
			$("span[id=solutionsId]").hover( function(event){
				var data=$(this).attr("data");
				if(data.length>7){
			    $(this).css("color", "red");  
			    $(this).html(data);
				}
			}, function(event){
				var data=$(this).attr("data");
				if(data.length>7){
			    $(this).css("color", "black");
			    $(this).html(data.substr(0,8)+"...")
				}
			});	
			
			$("button[id=uploadAttaBtnId]").click(function(){
				var account=$(this).attr("data");
				upload.uploadFile($("#eliteAttaId"), "elite", "resume_atta", function(data) {
					    if(typeof(data.attaId) != "undefined"){
						jsonAjax.post({
							url : ctx + '/partner/partnerElite/save/resume',
							data : {
								resumeId : data.attaId,
								account:account
							},
							success : function(data) {
								if (data.result == "SUCCESS") {
									selectStatusPaneFun();
								}
							}
						});
					    }
					});
				$("#eliteAttaId").click();
			})
		}
	});
}
// 下载简历
function downAtta() {
	var href = $(this).attr("data");
	window.open(href);
}
function initPageFun(){
	$(".pagination #prev_pager").click(function() {
		var currentPageth = $("#pager_pageth").val();
		if (currentPageth - 0 <= 1) {
			return;
		}
		$("#pager_pageth").val(currentPageth - 1);
		selectStatusPaneFun();
	});
	$(".pagination #jump_pager").click(function() {
		var pageth = $(this).attr("data");
		$("#pager_pageth").val(pageth - 0);
		selectStatusPaneFun();
	});
	$(".pagination #next_pager").click(function() {
		var currentPageth = $("#pager_pageth").val();
		if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
			return;
		}
		console.info($("#pager_pageth").val());
		$("#pager_pageth").val(currentPageth - 0 + 1);
		selectStatusPaneFun();
	});
	
}

