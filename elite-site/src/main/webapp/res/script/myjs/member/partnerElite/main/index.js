require([ "jquery", "ajax", "jsonAjax", "bootstrap-datepicker", "customhead", "md5", "city", "commons", "highcharts" ], function($) {
	// 头部点击事件
	var currentStatus;
	$("#apalyAduit").click(function() {
		jsonAjax.post({
			url : ctx + '/partner/valid',
			success : function(data) {
				if (data.result == "SUCCESS") {
					tostConfirm("终于等到你,确认申请审核吗?", function() {
						jsonAjax.post({
							url : ctx + '/partner/partnerElite/audit',
							success : function(data) {
								if (data.result != "SUCCESS") {
									tostHint("申请失败", "");
								} else {
									$("#checkContent").html("审核中");
								}
							}
						});
					});
				} else {
					$("#goprefectBoxId").show();
 					$("#goPrefectBtn").click(function(){
 							$("#left-menu li:eq(5)").click()});
 					$("#gocloseId").click(function(){
 						$("#goprefectBoxId").hide();
 					})
				}
			}
		});
	});

	$("#message_btn").click(function() {
		$("#my_p_tabs").hide();
		$(".left-menu li").removeClass("active-menu");

	});
	initCustomHead();
	// 左侧菜单点击事件
	$("#left-menu li").click(function() {
		$(this).addClass("select").siblings().removeClass("select");
		var type = $(this).attr("data");
		var title = $(this).attr("text")
		$("#left-menu li").find(".li-box").find(".txt-box").removeClass("active_color");
		$(this).find(".li-box").find(".txt-box").addClass("active_color");
		if (type == "champions") {
			$("#left-menu li:eq(0)").find(".li-box").find(".icon-box").removeClass("champion_no").addClass("champion");
			$("#left-menu li:eq(1)").find(".li-box").find(".icon-box").removeClass("invitation").addClass("invitation_no");
			$("#left-menu li:eq(2)").find(".li-box").find(".icon-box").removeClass("elite").addClass("elite_no");
			$("#left-menu li:eq(3)").find(".li-box").find(".icon-box").removeClass("channel").addClass("channel_no");
			$("#left-menu li:eq(4)").find(".li-box").find(".icon-box").removeClass("earnings").addClass("earnings_no");
			$("#left-menu li:eq(5)").find(".li-box").find(".icon-box").removeClass("data").addClass("data_no");
			$("#personRecommend .left-column .line_active").animate({
				"top" : "-13px"
			}, 100);
		} else if (type == "inviteRegister") {
			$("#left-menu li:eq(0)").find(".li-box").find(".icon-box").removeClass("champion").addClass("champion_no");
			$("#left-menu li:eq(1)").find(".li-box").find(".icon-box").removeClass("invitation_no").addClass("invitation");
			$("#left-menu li:eq(2)").find(".li-box").find(".icon-box").removeClass("elite").addClass("elite_no");
			$("#left-menu li:eq(3)").find(".li-box").find(".icon-box").removeClass("channel").addClass("channel_no");
			$("#left-menu li:eq(4)").find(".li-box").find(".icon-box").removeClass("earnings").addClass("earnings_no");
			$("#left-menu li:eq(5)").find(".li-box").find(".icon-box").removeClass("data").addClass("data_no");
			$("#personRecommend .left-column .line_active").animate({
				"top" : "34px"
			}, 100);
		} else if (type == "personActive") {
			$("#left-menu li:eq(0)").find(".li-box").find(".icon-box").removeClass("champion").addClass("champion_no");
			$("#left-menu li:eq(1)").find(".li-box").find(".icon-box").removeClass("invitation").addClass("invitation_no");
			$("#left-menu li:eq(2)").find(".li-box").find(".icon-box").removeClass("elite_no").addClass("elite");
			$("#left-menu li:eq(3)").find(".li-box").find(".icon-box").removeClass("channel").addClass("channel_no");
			$("#left-menu li:eq(4)").find(".li-box").find(".icon-box").removeClass("earnings").addClass("earnings_no");
			$("#left-menu li:eq(5)").find(".li-box").find(".icon-box").removeClass("data").addClass("data_no");
			$("#personRecommend .left-column .line_active").animate({
				"top" : "81px"
			}, 100);
		} else if (type == "channelManage") {
			$("#left-menu li:eq(0)").find(".li-box").find(".icon-box").removeClass("champion").addClass("champion_no");
			$("#left-menu li:eq(1)").find(".li-box").find(".icon-box").removeClass("invitation").addClass("invitation_no");
			$("#left-menu li:eq(2)").find(".li-box").find(".icon-box").removeClass("elite").addClass("elite_no");
			$("#left-menu li:eq(3)").find(".li-box").find(".icon-box").removeClass("channel_no").addClass("channel");
			$("#left-menu li:eq(4)").find(".li-box").find(".icon-box").removeClass("earnings").addClass("earnings_no");
			$("#left-menu li:eq(5)").find(".li-box").find(".icon-box").removeClass("data").addClass("data_no");
			$("#personRecommend .left-column .line_active").animate({
				"top" : "128px"
			}, 100);
		} else if (type == "revenue") {
			$("#left-menu li:eq(0)").find(".li-box").find(".icon-box").removeClass("champion").addClass("champion_no");
			$("#left-menu li:eq(1)").find(".li-box").find(".icon-box").removeClass("invitation").addClass("invitation_no");
			$("#left-menu li:eq(2)").find(".li-box").find(".icon-box").removeClass("elite").addClass("elite_no");
			$("#left-menu li:eq(3)").find(".li-box").find(".icon-box").removeClass("channel").addClass("channel_no");
			$("#left-menu li:eq(4)").find(".li-box").find(".icon-box").removeClass("earnings_no").addClass("earnings");
			$("#left-menu li:eq(5)").find(".li-box").find(".icon-box").removeClass("data").addClass("data_no");
			$("#personRecommend .left-column .line_active").animate({
				"top" : "175px"
			}, 100);
		} else if (type == "accountSecurity") {
			$("#left-menu li:eq(0)").find(".li-box").find(".icon-box").removeClass("champion").addClass("champion_no");
			$("#left-menu li:eq(1)").find(".li-box").find(".icon-box").removeClass("invitation").addClass("invitation_no");
			$("#left-menu li:eq(2)").find(".li-box").find(".icon-box").removeClass("elite").addClass("elite_no");
			$("#left-menu li:eq(3)").find(".li-box").find(".icon-box").removeClass("channel").addClass("channel_no");
			$("#left-menu li:eq(4)").find(".li-box").find(".icon-box").removeClass("earnings").addClass("earnings_no");
			$("#left-menu li:eq(5)").find(".li-box").find(".icon-box").removeClass("data_no").addClass("data");
			$("#personRecommend .left-column .line_active").animate({
				"top" : "222px"
			}, 100);
		}
		// 点击类型
		$("#selectRightTypeId").val(type)
		$("#itemId").html(title);
		loadProjectList();
	});
	var rcp = sessionStorage.getItem('rsp_intent_isd');
	//url是否有邀请码
	var rc=getUrlParams("rp");
	if((rcp==null|| rcp=="")&&rc!=null){
		rcp=rc;
	}
	if (rcp != null && rcp != "") {
		if (rcp == 'inviteRegister') {
			$("#left-menu li:eq(1)").click();
		} else if (rcp == 'manager') {
			$("#left-menu li:eq(2)").click();
		} else if (rcp == 'channel') {
			$("#left-menu li:eq(3)").click();
		} else if (rcp == 'settlement') {
			$("#left-menu li:eq(4)").click();
		} else if (rcp == 'info'||rcp=="accountSecurity") {
			$("#left-menu li:eq(5)").click();
		}else{
			$("#left-menu li:eq(0)").click();
		}
		sessionStorage.removeItem('rsp_intent_isd');
	} else {
		$("#left-menu li:eq(0)").click();
	}
    
	function loadProjectList() {
		currentStatus = status;
		var type = $("#selectRightTypeId").val()
		jsonAjax.post({
			url : ctx + '/partner/partnerElite/' + type,
			success : function(data) {
				$("#context").html(data);
				// 复制功能
				initCopy();

				$("#projectManageSearchBtn").click(loadListDate);

				// 搜索按钮
				$(".search-btn").click(function() {
					// 搜索默认第一页
					$("#pager_pageth").val(1);
					loadListDate();
				})
				$("#send-msg-btn").click(sendCodeFuncton);
				// entry键盘使用
				$("#searchForm").keydown(function(event) {
					if (event.keyCode == 13) {
						loadListDate()
					}
				})
				$("#repeatNewPasswordId").blur(repeatNewPasswordValidate)
				$("#resetPassWordBtn").click(restPassSubmit);
				$("#saveBasicBtn").click(updateBasic);
				if (type == "accountSecurity") {
					initAccountSecurity();
				} else if (type == "inviteRegister") {
					initInviteRegister();
				} else if (type == "personActive") {
					initPersonActive();
				} else if (type == "champions") {
					initChampionsActive();
				} else if (type == "channelManage") {
					initChannelManage();
				} else if (type == "revenue") {
					initRevenue();
				}
				if (type != "accountSecurity" && type !== "revenue") {
					loadListDate();
				}
			}
		});
	}
	function loadListDate() {
		var type = $("#selectRightTypeId").val()
		jsonAjax.post({
			url : ctx + "/partner/partnerElite/" + type + "/listData",
			data : $("#searchForm").serialize(),
			success : function(data) {
				$("#datalist").html(data);
				// 分页组件js
				$(".pagination #prev_pager").click(function() {
					var currentPageth = $("#pager_pageth").val();
					if (currentPageth - 0 <= 1) {
						return;
					}
					$("#pager_pageth").val(currentPageth - 1);
					loadListDate();
				});
				$(".pagination #jump_pager").click(function() {
					var pageth = $(this).attr("data");
					$("#pager_pageth").val(pageth - 0);
					loadListDate();
				});
				$(".pagination #next_pager").click(function() {
					var currentPageth = $("#pager_pageth").val();
					if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
						return;
					}
					console.info($("#pager_pageth").val());
					$("#pager_pageth").val(currentPageth - 0 + 1);
					loadListDate();
				});
				var searchType=$("#searchTypeId").val();
				if (type == "inviteRegister") {
					var status = $("#statusId").val();
					var count = $("#pager_rowCount").val();
					var  showdata="精英";
					if("channel"==searchType){
						showdata="渠道";
					}
					if (status == "audit_pass") {
						$("#enterCountId").html("已入驻"+showdata+"(" + count + ")")
					} else if (status == "registered") {
						$("#registeredCountId").html("已注册"+showdata+" (" + count + ")")
					} else if (status == "") {
						$("#allCountId").html("已备案"+showdata+" (" + count + ")")
					}
					$("button[id=uploadAttaBtnId]").click(function() {
						var account = $(this).attr("data");
						upload.uploadFile($("#eliteAttaId"), "elite", "resume_atta", function(data) {
							if (typeof (data.attaId) != "undefined") {
								jsonAjax.post({
									url : ctx + '/partner/partnerElite/save/resume',
									data : {
										resumeId : data.attaId,
										account : account
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
			}
		});
	}
	// 查看详情
	$(document).on('click', '.table-btn', function() {
		var path = $(this).attr("data");
		window.location.href = ctx + path;
	});

	// 复制功能
	function initCopy() {
		// 复制
		if (myBrowser() == "IE") {
			// IE
			$(document).on("click", ".copy-code", function() {
				var Url = $(this).prev(".copy-result").text();
				copyToClipboard(Url);
			});

			function copyToClipboard(maintext) {
				if (window.clipboardData) {
					window.clipboardData.setData("Text", maintext);
				} else if (window.netscape) {
					try {
						netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
					} catch (e) {
						// alert("该浏览器不支持一键复制！n请手工复制文本框链接地址～");
					}
					var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
					if (!clip)
						return;
					var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
					if (!trans)
						return;
					trans.addDataFlavor('text/unicode');
					var str = new Object();
					var len = new Object();
					var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
					var copytext = maintext;
					str.data = copytext;
					trans.setTransferData("text/unicode", str, copytext.length * 2);
					var clipid = Components.interfaces.nsIClipboard;
					if (!clip)
						return false;
					clip.setData(trans, null, clipid.kGlobalClipboard);
				}
				tostHint("复制成功", "已经复制到剪贴板");
			}
		} else {
			$(".copy-code").each(function() {
				var clipboard = new Clipboard($(this)[0]);
				clipboard.on('success', function(e) {
					console.log(e);
					tostHint("复制成功", "已经复制到剪贴板");
				});

				clipboard.on('error', function(e) {
					console.log(e);
				});

			});
		}
	}
	// 浏览器类型
	function myBrowser() {
		var userAgent = navigator.userAgent; // 取得浏览器的userAgent字符串
		var isOpera = userAgent.indexOf("Opera") > -1;
		if (isOpera) {
			return "Opera"
		}// 判断是否Opera浏览器
		if (userAgent.indexOf("Firefox") > -1) {
			return "FF";
		} // 判断是否Firefox浏览器
		if (userAgent.indexOf("Chrome") > -1) {
			return "Chrome";
		}
		if (userAgent.indexOf("Safari") > -1) {
			return "Safari";
		} // 判断是否Safari浏览器
		if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
			return "IE";
		}// 判断是否IE浏览器

	}

});
