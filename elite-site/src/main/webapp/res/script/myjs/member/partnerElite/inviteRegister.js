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
	// 人才属性
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
	

    //精英注册，人才渠道注册， 左右切换
    $("#inviteContainer .title_ul li").click(function() {
        var data = $(this).attr("data");
        $("#inviteContainer .title_ul li").removeClass("active_color");
        $(this).addClass("active_color");
        $("#searchTypeCountId").val("true")
        if (data == "elite") {
            $("#elite_choose").show();
            $("#channel_choose").hide();
            $("#searchTypeId").val("elite");
            $(this).parent().next().animate({"left": "0px"});
           var inviteUrl=$("#codeHrefId").val()+"&ic="+$("#inviteCode").html();
           $("#inviteUrl").html(inviteUrl);
           $("#inviteUrl").next().attr(inviteUrl) 
        } else if (data == "channel") {
            $("#elite_choose").hide();
            $("#channel_choose").show();
            $("#searchTypeId").val("channel");
            $(this).parent().next().animate({"left": "220px"});
            var inviteUrl= $("#codeHrefId").val().split("?")[0] +"/p?ts=partnerElite&ic="+$("#inviteCode").html();
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
	//新增人才显示
  $("#elite_add").click(function(){
	  var status=$("#eliteStatusId").val();
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
  $("#saveEliteBtn").click(function(){
	  saveEliteSubmit();
	});
	$("#cancelEliteBtn,#elite_close_btn").click(function(){
		$("#newRecord_elite").fadeOut();
	});
   //新增人才渠道显示
	$("#channel_add").click(function(){
		 var status=$("#eliteStatusId").val();
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
		$("#channelTipErrorId").hide();
	});
}
function showAddEliteView() {
	$("#newRecord_elite").fadeIn();
	$('#areaName').kuCity("eliteDiv");
	$('#addPersonForm')[0].reset();
	$("#eliteTipErrorId").hide()
	$("#resumeAtta_show").hide();
	$("#workYear").html("请选择");
	$("#defineRoleShow").html("请选择");
	$("#resumeAattaId").val("");
	// 上传简历
	upload.uploadFile($("#resumeAttaFile"), "elite", "resume_atta", function(data) {
		$("#resumeAtta_show").show();
		var prd = '<button type="button" class="upload-file">' + data.originalName + '<img src=' + ctx + '"/res/images/del.png" data="' + data.attaId
		+ '" id="pbimg" class="delete-cross"></button>';
		$("#resumeAtta_show").html(prd);
		$("img[id=pbimg]").unbind('click');
		$("img[id=pbimg]").bind('click', function(e) {
			var thiss = $(this);
			var bthis = thiss.parent();
			var id = thiss.attr("data");
			upload.removeFile("resume_atta", function(url) {
				jsonAjax.post({
					url : url,
					data : {
						attaId : id
					},
					success : function(drsp) {
						if (drsp.result == "SUCCESS") {
							$("#resumeAattaId").val("");
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
	$('#areaNameChannel').kuCity("channelDiv");
	$('#addChannelForm_elite')[0].reset();
	$("#channelTipErrorId").hide()
	$("#channelDefineRoleShow").text("请选择")
	$("#channelDefineRoleId").val("")
}
//新增渠道
function saveChannelSubmit() {
	$("#channelTipTextId").html("")
	$("#channelTipErrorId").hide()
	var name = $("#channelNameId").val();
	if (name == '') {
		$("#channelTipTextId").html("请填写姓名")
		showError();
		return false;
	}
	var mobileNum = $("#channelPhoneId").val();
	if (!validatePhone(mobileNum)) {
		$("#channelTipTextId").html("请填写正确手机号")
		showError();
		return false;
	}
	var  defineRole=$("#channelDefineRoleId").val();
	if(defineRole==null||defineRole==""){
		$("#channelTipTextId").html("请选择人才属性")
		showError();
		return false;
	}
	
	var attaId = $("#resumeAattaId").val();
	jsonAjax.post({
		url : ctx + '/partner/memberPartnerElite/saveMemberPartnerElite',
		data : $("#addChannelForm_elite").serialize(),
		success : function(data) {
			if (data.result == "SUCCESS") {
				$("#channelTipTextId").html("保存成功")
				showError();
				window.setTimeout(function() {
					selectStatusPaneFun();
				}, 50);
				$("#newRecord_channel").fadeOut();
			} else {
				//tips("phone", data.msg);
				$("#channelTipTextId").html(data.msg)
				showError();
			}

		}
	});
	
	
}

//错误提示显示
function showError(){
	$(".error_div").show();
	$("body").bind("mousedown",function(){
		$(".error_div").hide();
		$("body").unbind("mousedown");
	});
}



// 新增人才
 function saveEliteSubmit() {
	 $("#eliteTipErrorId").hide()
	var name = $("#name").val();
	if (name == '') {
		$("#eliteTipTextId").html("请填写姓名")
		$("#eliteTipErrorId").show()
		return false;
	}
	var mobileNum = $("#phone").val();
	if (!validatePhone(mobileNum)) {
		$("#eliteTipTextId").html("请填写正确手机号")
		$("#eliteTipErrorId").show()
		return false;
	}
	var idCard = $("#idCard").val();
	if (idCard != "" && !validateCard(idCard)) {
		$("#eliteTipTextId").html("请填写正确身份证")
		$("#eliteTipErrorId").show()
		return false;
	}
	var  defineRole=$("#defineRoleId").val();
	if(defineRole==null||defineRole==""){
		$("#eliteTipTextId").html("请选择人才属性")
		$("#eliteTipErrorId").show()
		return false;
	}
	var  jobAge=$("#jobAge").val();
	if(jobAge==null||jobAge==""){
		$("#eliteTipTextId").html("请选择从业年限")
		$("#eliteTipErrorId").show()
		return false;
	}
	
	var attaId = $("#resumeAattaId").val();
	jsonAjax.post({
		url : ctx + '/partner/partnerElite/saveElite',
		data : $("#addPersonForm").serialize(),
		success : function(data) {
			if (data.result == "SUCCESS") {
				$("#eliteTipTextId").html("添加成功")
				$("#eliteTipErrorId").show()
				window.setTimeout(function() {
					selectStatusPaneFun();
				}, 1000);
				$("#newRecord_elite").fadeOut();
			} else {
				tips("phone", data.msg);
				$("#eliteTipTextId").html(data.msg)
				$("#eliteTipErrorId").show()
			}

		}
	});
}
function selectStatusPaneFun() {
	var status=$("#statusId").val();
	var searchType=$("#searchTypeId").val();
	jsonAjax.post({
		url : ctx + "/partner/partnerElite/inviteRegister/listData",
		data : $("#searchForm").serialize(),
		success : function(data) {
			$("#datalist").html(data);
			// 分页组件js
			initPageFun();
			$("button[id=downAtta]").click(downAtta);
			var count = $("#pager_rowCount").val();
			var  showdata="精英";
			if("channel"==searchType){
				showdata="渠道";
			}
			var changeCount=$("#searchTypeCountId").val();
			$("#searchTypeCountId").val("false");
			if(changeCount=="true"){
				if("channel"==searchType){
					$("#registeredCountId").html("已注册"+showdata+" (" + $("#listregisterCountId").val() + ")");
					$("#enterCountId").html("已入驻"+showdata+"(" + $("#listenterCountId").val() + ")")
					$("#allCountId").html("已备案"+showdata+" (" + $("#listallCountId").val() + ")");
                  }
				else{
					$("#registeredCountId").html("已注册"+showdata+" (" + $("#listregisterCountId").val() + ")");
					$("#enterCountId").html("已入驻"+showdata+"(" + $("#listenterCountId").val() + ")")
					$("#allCountId").html("已备案"+showdata+" (" + $("#listallCountId").val() + ")");
				}
			}
					if (status == "audit_pass") {
						$("#enterCountId").html("已入驻"+showdata+"(" + count + ")")
					}
					else if (status == "registered") {
						$("#registeredCountId").html("已注册"+showdata+" (" + count + ")");
						$("#channel_registered").show();
						$("#channel_record").hide();
					}else{
						$("#allCountId").html("已备案"+showdata+" (" + count + ")");
						$("#channel_registered").hide();
						$("#channel_record").show();
					}
			if (status == "audit_pass") {
				$("#enterCountId").html("已入驻"+showdata+"(" + count + ")")
			}
			else if (status == "registered") {
				$("#registeredCountId").html("已注册"+showdata+" (" + count + ")")
			}else if(status==""){
				$("#allCountId").html("已备案"+showdata+" (" + count + ")")
			}
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
