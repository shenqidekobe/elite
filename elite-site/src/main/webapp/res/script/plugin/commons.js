//字符串累加
function addStr(target, obj, join, orders) {
	if (target == "") {
		return obj;
	} else {
		if (orders == 0) {
			return obj + join + target;
		} else {
			return target + join + obj;
		}
	}
}
// 字符串相减
function subStr(target, obj, join, orders) {
	if (target == "") {
		return "";
	} else {
		target = target + '';
		var arrs = target.split(join);
		var opt = "";
		for ( var i in arrs) {
			if (arrs[i] != obj) {
				opt = addStr(opt, arrs[i], join, orders);
			}
		}
		return opt;
	}
}
function getUrlParams(paramName) {
	// 获取url指定参数值
	var url = window.location.href;
	var reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)");
	var r = url.substring(url.indexOf('?')).substr(1).match(reg);
	if (r != null)
		return r[2];
	return '';
}
// 表单提示信息
function tips(elementId, prompt) {
	var element = $("#" + elementId);
	$("#tipError").text(prompt);
	if (element.length > 0) {
		element.addClass("error-sign");
		element.focus(function() {
			element.removeClass("error-sign");
			$("#tipError").text("");
		});
	}
}
function validatePhone(phone) {
	// 验证手机号码合法性
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	if (reg.test(phone)) {
		return true;
	}
	return false;
}
function validateEmail(email) {
	var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	if (reg.test(email)) {
		return true;
	}
	return false;
}
function validateNum(num) {
	// 验证数字合法性
	var reg = /^\d+$/;
	if (reg.test(num)) {
		return true;
	}
	return false;
}
function validateBankNum(bankNum) {
	// 验证银行卡号合法性
	var reg = /^\d+$/;
	if (reg.test(bankNum)) {
		return true;
	}
	return false;
}
function validateCard(card) {
	// 验证身份证号码合法性
	// var
	// reg=/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i;
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	if (reg.test(card)) {
		return true;
	}
	return false;
}
function validateAmount(amount) {
	// 验证金额合法性，最多八位数?
	var reg = /^\d{1,8}(\.\d{0,2})?$/;
	if (reg.test(amount)) {
		return true;
	}
	return false;
}
function validateIllegalChar(chars) {
	// 非法字符
	var reg = /[`~!#$%^&*()_+<>?:"{},.\/;'[\]]+/g;
	if (reg.test(chars)) {
		return false;
	}
	return true;
}
function validateChinese(chars) {
	// 中文验证
	var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
	if (reg.test(chars)) {
		return true;
	}
	return false;
}
function validateContact(str) {
	// 验证连续方式，既非手机号也非邮箱的返回false
	if (!validateEmail(str) && !validatePhone(str)) {
		return false;
	}
	return true;
}
function trimMiddle(str) {
	var result;
	result = str.replace(/(^\s+)|(\s+$)/g, "");
	result = result.replace(/\s/g, "");
	return result;
}
function formatMoney(obj) {
	var p1 = /[^\d\.]/g; // 过滤非数字及小数点 /g :所有范围中过滤
	var p2 = /(\.\d{2})\d*$/g;
	var p4 = /(\.)(\d*)\1/g;
	obj.value = obj.value.replace(p1, "").replace(p2, "$1").replace(p4, "$1$2");
	obj.value = obj.value.replace(/[^0-9.]/g, '');
	var p5 = /\.+/g; // 多个点的话只取1个点，屏蔽1....234的情况
	obj.value = obj.value.replace(p5, ".")
	var p6 = /(\.+)(\d+)(\.+)/g; // 屏蔽1....234.的情况
	obj.value = obj.value.replace(p6, "$1$2");
}
function offsetMove(top) {
	top = top || 70;
	$("body,html").animate({
		scrollTop : top - 70
	}, 200);
}
var addTimer = function() {
	var list = [], interval;
	return function(id, time) {
		if (!interval)
			interval = setInterval(go, 1000);
		list.push({
			ele : document.getElementById(id),
			time : time
		});
	}
	function go() {
		for (var i = 0; i < list.length; i++) {
			if (list[i].ele != null) {
				list[i].ele.innerHTML = getTimerString(list[i].time ? list[i].time -= 1
						: 0);
				if (!list[i].time)
					list.splice(i--, 1);
			}
		}
	}
	function getTimerString(time) {
		var not0 = !!time, d = Math.floor(time / 86400), h = Math
				.floor((time %= 86400) / 3600), m = Math
				.floor((time %= 3600) / 60), s = time % 60;
		if (not0)
			return d + "天" + h + "小时" + m + "分" + s + "秒";
		else
			return "时间到";
	}
}();
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
function banBackSpace(e) {
	var ev = e || window.event;
	var obj = ev.target || ev.srcElement;
	var t = obj.type || obj.getAttribute('type');
	var vReadOnly = obj.getAttribute('readonly');
	var vEnabled = obj.getAttribute('enabled');
	vReadOnly = (vReadOnly == null) ? false : vReadOnly;// 处理null值情况
	vEnabled = (vEnabled == null) ? true : vEnabled;
	// 当敲Backspace键时，事件源类型为密码或单行、多行文本的，
	// 并且readonly属性为true或enabled属性为false的，则退格键失效
	var flag1 = (ev.keyCode == 8
			&& (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vEnabled != true)) ? true
			: false;
	var flag2 = (ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea") ? true
			: false;
	if (flag2) {
		return false;
	}
	if (flag1) {
		return false;
	}
}
function pagination(callback) {
	// 分页组件js
	$(".pagination #prev_pager").click(function() {
		var currentPageth = $("#pager_pageth").val();
		if (currentPageth - 0 <= 1) {
			return;
		}
		$("#pager_pageth").val(currentPageth - 1);
		callback();
	});
	$(".pagination #jump_pager").click(function() {
		var pageth = $(this).attr("data");
		$("#pager_pageth").val(pageth - 0);
		callback();
	});
	$(".pagination #next_pager").click(function() {
		var currentPageth = $("#pager_pageth").val();
		if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
			return;
		}
		$("#pager_pageth").val(currentPageth - 0 + 1);
		callback();
	});
}
var hintTimeOutFunction = null, confirmTimeOutFunction = null;
function tostHint(title, content) {
	var html = '<div class="warning_box">';
	html += '<div class="warning_close"></div>';
	html += '<div class="warning_icon"></div>';
	html += '<div class="warning_text1">' + title + '</div>';
	html += '<div class="warning_text2">' + content + '</div>';
	html += '<div class="warning_btn">确定</div></div>';
	var box = $(".warning_box");
	if (box.length == 0) {
		$(document.body).append(html);
	} else {
		$(".warning_text1").text(title);
		$(".warning_text2").text(content);
	}
	box.show();
	// 关闭事件
	$(".warning_close,.warning_btn").click(function() {
		$(".warning_box").hide();
	});
	// 定时关闭
	if (hintTimeOutFunction != null) {
		clearTimeout(hintTimeOutFunction);
	}
	hintTimeOutFunction = setTimeout(function() {
		$(".warning_close,.warning_btn").click();
	}, 3000);
}
function tostConfirm(content, callback) {
	var html = '<div class="confirm_box">';
	html += '<div class="confirm_close"></div>';
	html += '<div class="confirm_icon"></div>';
	html += '<div class="confirm_text1">' + content + '</div>';
	html += '<div class="confirm_btn1">确定</div>';
	html += '<div class="confirm_btn2">取消</div></div>';
	var box = $(".confirm_box");
	if (box.length == 0) {
		$(document.body).append(html);
	} else {
		$(".warning_text1").text(content);
	}
	box.show();
	// 确认事件
	$(".confirm_btn1").unbind("click");
	$(".confirm_btn1").click(function() {
		$(".confirm_box").hide();
		callback();
	});
	// 关闭事件
	$(".confirm_btn2,.confirm_close").click(function() {
		$(".confirm_box").hide();
	});
}

// Luhm校验规则：16位银行卡号（19位通用）:
// 1.将未带校验位的 15（或18）位卡号从右依次编号 1 到 15（18），位于奇数位号上的数字乘以 2。
// 2.将奇位乘积的个十位全部相加，再加上所有偶数位上的数字。
// 3.将加法和加上校验位能被 10 整除。
// bankno为银行卡号 banknoInfo为显示提示信息的DIV或其他控件
function luhmCheck(bankno) {
	if(bankno==""||bankno.length<12||bankno.length>19){
		return false;
	}
	var lastNum = bankno.substr(bankno.length - 1, 1);// 取出最后一位（与luhm进行比较）

	var first15Num = bankno.substr(0, bankno.length - 1);// 前15或18位
	var newArr = new Array();
	for (var i = first15Num.length - 1; i > -1; i--) { // 前15或18位倒序存进数组
		newArr.push(first15Num.substr(i, 1));
	}
	var arrJiShu = new Array(); // 奇数位*2的积 <9
	var arrJiShu2 = new Array(); // 奇数位*2的积 >9

	var arrOuShu = new Array(); // 偶数位数组
	for (var j = 0; j < newArr.length; j++) {
		if ((j + 1) % 2 == 1) {// 奇数位
			if (parseInt(newArr[j]) * 2 < 9)
				arrJiShu.push(parseInt(newArr[j]) * 2);
			else
				arrJiShu2.push(parseInt(newArr[j]) * 2);
		} else
			// 偶数位
			arrOuShu.push(newArr[j]);
	}

	var jishu_child1 = new Array();// 奇数位*2 >9 的分割之后的数组个位数
	var jishu_child2 = new Array();// 奇数位*2 >9 的分割之后的数组十位数
	for (var h = 0; h < arrJiShu2.length; h++) {
		jishu_child1.push(parseInt(arrJiShu2[h]) % 10);
		jishu_child2.push(parseInt(arrJiShu2[h]) / 10);
	}

	var sumJiShu = 0; // 奇数位*2 < 9 的数组之和
	var sumOuShu = 0; // 偶数位数组之和
	var sumJiShuChild1 = 0; // 奇数位*2 >9 的分割之后的数组个位数之和
	var sumJiShuChild2 = 0; // 奇数位*2 >9 的分割之后的数组十位数之和
	var sumTotal = 0;
	for (var m = 0; m < arrJiShu.length; m++) {
		sumJiShu = sumJiShu + parseInt(arrJiShu[m]);
	}

	for (var n = 0; n < arrOuShu.length; n++) {
		sumOuShu = sumOuShu + parseInt(arrOuShu[n]);
	}

	for (var p = 0; p < jishu_child1.length; p++) {
		sumJiShuChild1 = sumJiShuChild1 + parseInt(jishu_child1[p]);
		sumJiShuChild2 = sumJiShuChild2 + parseInt(jishu_child2[p]);
	}
	// 计算总和
	sumTotal = parseInt(sumJiShu) + parseInt(sumOuShu)
			+ parseInt(sumJiShuChild1) + parseInt(sumJiShuChild2);

	// 计算Luhm值
	var k = parseInt(sumTotal) % 10 == 0 ? 10 : parseInt(sumTotal) % 10;
	var luhm = 10 - k;

	if (lastNum == luhm) {
		return true;
	} else {
		return false;
	}
}

//日期前天昨天
function getDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    m = m<10 ? '0'+m : m;
    d = d<10 ? '0'+d : d;
    return y+"-"+m+"-"+d;
}