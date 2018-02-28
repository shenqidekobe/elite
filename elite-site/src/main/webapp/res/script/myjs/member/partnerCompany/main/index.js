require([ "jquery", "ajax", "jsonAjax", "bootstrap-datepicker", "customhead", "md5", "city", "commons","highcharts"], function($) {
	// 头部点击事件
	var currentStatus;
	// 初始化加载
	// 解决文本框设置readonly后按删除键后退页面问题
	$("input[readOnly]").keydown(function(e) {
		e.preventDefault();
	});
	//申请审核
	$("#apalyAduit").click(function() {
		 jsonAjax.post({
	 			url:ctx+'/partner/valid',
	 			success:function(data){
	 				if (data.result == "SUCCESS") {
	 					tostConfirm("终于等到你,确认申请审核吗?",function(){
	 						jsonAjax.post({
	 				 			url:ctx+'/partner/partnerCompany/audit',
	 				 			success:function(data){
	 				 				if (data.result != "SUCCESS") {
	 				 					tostHint("申请失败","");
	 				 				}else{
	 				 					$("#checkContent").html("审核中");
	 				 				}
	 				 			}
	 				 		});
	 					});
	 				}else{
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
	// 头像修改
	initCustomHead();
	// 左侧菜单点击事件
	$("#left-menu li").click(function() {
		$(this).addClass("select").siblings().removeClass("select");
		var type = $(this).attr("data");
		var title = $(this).attr("text");
		$("#left-menu li").find(".li-box").find(".txt-box").removeClass("active_color");
		$(this).find(".li-box").find(".txt-box").addClass("active_color");
		if(type=="champions"){
							$("#left-menu li:eq(0)").find(".li-box").find(".icon-box").removeClass("champion_no").addClass("champion");
							$("#left-menu li:eq(1)").find(".li-box").find(".icon-box").removeClass("invitation").addClass("invitation_no");
							$("#left-menu li:eq(2)").find(".li-box").find(".icon-box").removeClass("elite").addClass("elite_no");
							$("#left-menu li:eq(3)").find(".li-box").find(".icon-box").removeClass("channel").addClass("channel_no");
							$("#left-menu li:eq(4)").find(".li-box").find(".icon-box").removeClass("earnings").addClass("earnings_no");
							$("#left-menu li:eq(5)").find(".li-box").find(".icon-box").removeClass("data").addClass("data_no");
							$("#personRecommend .left-column .line_active").animate({"top":"-13px"},100);
						}else if(type=="inviteRegister"){
							$("#left-menu li:eq(0)").find(".li-box").find(".icon-box").removeClass("champion").addClass("champion_no");
							$("#left-menu li:eq(1)").find(".li-box").find(".icon-box").removeClass("invitation_no").addClass("invitation");
							$("#left-menu li:eq(2)").find(".li-box").find(".icon-box").removeClass("elite").addClass("elite_no");
							$("#left-menu li:eq(3)").find(".li-box").find(".icon-box").removeClass("channel").addClass("channel_no");
							$("#left-menu li:eq(4)").find(".li-box").find(".icon-box").removeClass("earnings").addClass("earnings_no");
							$("#left-menu li:eq(5)").find(".li-box").find(".icon-box").removeClass("data").addClass("data_no");
							$("#personRecommend .left-column .line_active").animate({"top":"34px"},100);
						}
						else if(type=="projectManage"){
							$("#left-menu li:eq(0)").find(".li-box").find(".icon-box").removeClass("champion").addClass("champion_no");
							$("#left-menu li:eq(1)").find(".li-box").find(".icon-box").removeClass("invitation").addClass("invitation_no");
							$("#left-menu li:eq(2)").find(".li-box").find(".icon-box").removeClass("elite_no").addClass("elite");
							$("#left-menu li:eq(3)").find(".li-box").find(".icon-box").removeClass("channel").addClass("channel_no");
							$("#left-menu li:eq(4)").find(".li-box").find(".icon-box").removeClass("earnings").addClass("earnings_no");
							$("#left-menu li:eq(5)").find(".li-box").find(".icon-box").removeClass("data").addClass("data_no");
							$("#personRecommend .left-column .line_active").animate({"top":"81px"},100);
						}
						else if(type=="channelManage"){
							$("#left-menu li:eq(0)").find(".li-box").find(".icon-box").removeClass("champion").addClass("champion_no");
							$("#left-menu li:eq(1)").find(".li-box").find(".icon-box").removeClass("invitation").addClass("invitation_no");
							$("#left-menu li:eq(2)").find(".li-box").find(".icon-box").removeClass("elite").addClass("elite_no");
							$("#left-menu li:eq(3)").find(".li-box").find(".icon-box").removeClass("channel_no").addClass("channel");
							$("#left-menu li:eq(4)").find(".li-box").find(".icon-box").removeClass("earnings").addClass("earnings_no");
							$("#left-menu li:eq(5)").find(".li-box").find(".icon-box").removeClass("data").addClass("data_no");
							$("#personRecommend .left-column .line_active").animate({"top":"128px"},100);
						}
						else if(type=="revenue"){
							$("#left-menu li:eq(0)").find(".li-box").find(".icon-box").removeClass("champion").addClass("champion_no");
							$("#left-menu li:eq(1)").find(".li-box").find(".icon-box").removeClass("invitation").addClass("invitation_no");
							$("#left-menu li:eq(2)").find(".li-box").find(".icon-box").removeClass("elite").addClass("elite_no");
							$("#left-menu li:eq(3)").find(".li-box").find(".icon-box").removeClass("channel").addClass("channel_no");
							$("#left-menu li:eq(4)").find(".li-box").find(".icon-box").removeClass("earnings_no").addClass("earnings");
							$("#left-menu li:eq(5)").find(".li-box").find(".icon-box").removeClass("data").addClass("data_no");
							$("#personRecommend .left-column .line_active").animate({"top":"175px"},100);
						}
						else if(type=="accountSecurity"){
							$("#left-menu li:eq(0)").find(".li-box").find(".icon-box").removeClass("champion").addClass("champion_no");
							$("#left-menu li:eq(1)").find(".li-box").find(".icon-box").removeClass("invitation").addClass("invitation_no");
							$("#left-menu li:eq(2)").find(".li-box").find(".icon-box").removeClass("elite").addClass("elite_no");
							$("#left-menu li:eq(3)").find(".li-box").find(".icon-box").removeClass("channel").addClass("channel_no");
							$("#left-menu li:eq(4)").find(".li-box").find(".icon-box").removeClass("earnings").addClass("earnings_no");
							$("#left-menu li:eq(5)").find(".li-box").find(".icon-box").removeClass("data_no").addClass("data");
							$("#personRecommend .left-column .line_active").animate({"top":"222px"},100);
						}
		// 点击类型
		$("#selectRightTypeId").val(type)
		$("#itemId").html(title);
		loadProjectList();
	});
	// 处理回调
	var rcp = sessionStorage.getItem('rsp_intent_isd');
	var rc=getUrlParams("rp");
	if((rcp==null|| rcp=="")&&rc!=null){
		rcp=rc;
	}
	if (rcp != null && rcp != "") {
		if (rcp == 'inviteRegister') {
			$("#left-menu li:eq(1)").click();
		} else if (rcp == 'manager') {
			$("#left-menu li:eq(2)").click();
		}
		else if (rcp == 'channel') {
			$("#left-menu li:eq(3)").click();
		}
		else if (rcp == 'settlement') {
			$("#left-menu li:eq(4)").click();
		}
		else if (rcp == 'info'||rcp=="accountSecurity") {
			$("#left-menu li:eq(5)").click();
		}else{
			$("#left-menu li:eq(0)").click();
		}
		sessionStorage.removeItem('rsp_intent_isd');
	} else {
		$("#left-menu li:eq(0)").click();

	}

	$("#message_btn").click(function() {
		$("#my_p_tabs").hide();
		$(".left-menu li").removeClass("active-menu");
	});

	function loadProjectList() {
		currentStatus = status;
		var type = $("#selectRightTypeId").val();
		jsonAjax.post({
			url : ctx + '/partner/partnerCompany/' + type,
			success : function(data) {
				$("#context").html(data);
				
				//冠军榜单
				if(type=="champions"){
					loadListDate();
					initChampionsActive();
				}
				//邀请注册
				else if (type=="inviteRegister"){
					initInviteRegister()
				}
				//邀请注册
				else if(type =="channelManage"){
					initChannelManage();
				}
				else if (type == "accountSecurity") {
					initAccountSecurity();
				}else if (type == "revenue") {
					 initRevenue();
				} else {
					  loadListDate();
				}
				// entry键盘使用
				$("#searchForm").keydown(function(event) {
					if (event.keyCode == 13) {
						$("#projectManageSearchBtn").click();
					}
				})
				$("#projectManageSearchBtn").click(function() {
					$("#pager_pageth").val(1);
					loadListDate();
				});
				$("#send-msg-btn").click(sendCodeFuncton);
				// 复制
				initCopy();
			}
		});
	}

	// 查询list
	function loadListDate() {
		var type = $("#selectRightTypeId").val()
		jsonAjax.post({
			url : ctx + "/partner/partnerCompany/" + type + "/listData",
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
			}
		});
	}
	
	// 获取验证码
	function sendCodeFuncton() {
		$(".error-tip").text("");
		var $this = $("#send-msg-btn");
		var account = $("#phone").val();
		var mobileReg = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
		if (!mobileReg.test(account)) {
			$("#.error-tip").text("手机号无效");
			return;
		}
		// 发送验证码
		jsonAjax.post({
			url : ctx + '/common/sms/send',
			data : {
				phone : account,
				type : "register"
			},
			success : function(data) {
				if (data.result == "SUCCESS") {
					sendSmsCode($this);
				}
			}
		});
	}
	;
	// 发送验证码倒计时
	var wait = 60;
	function sendSmsCode(btnElement) {
		if (wait == 0) {
			btnElement.val("获取验证码");
			btnElement.removeAttr("disabled");
			wait = 60;
		} else {
			return timeLoop(btnElement, wait);
		}
	}

	function timeLoop(btnElement, wait) {
		btnElement.attr({
			"disabled" : "disabled"
		});
		timeoutProcess = setTimeout(function() {
			btnElement.val("重新发送(" + wait + ")");
			wait--;
			if (wait < 0) {
				btnElement.val("获取验证码");
				btnElement.removeAttr("disabled");
				return;
			}
			timeLoop(btnElement, wait);
		}, 1000);
	}


	$(document).on('click', '.table-btn', function() {
		$("#infoDetail").modal({
			backdrop : 'static'
		});
		$("#bodyModal").text($(this).attr("data"));
	});

	// 复制
	/*
	 * $(document).on('click','.copy-code',function(){ var str =
	 * $(this).attr("data"); jsonAjax.post({ url : ctx + '/common/clipboard',
	 * data : {"str":str}, success : function(data) { } }); });
	 */
	// 保存信息
	
	// 提现
	function initCopy() {
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
