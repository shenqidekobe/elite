/**
 * boxMessage简单封装
 */
var tipsDefaults = {
	title : "提示",
	content : "",
	iconSmall:"icon-ok",
	timeout : 1000
};
var confirmDefaults = {
	title : "确认框",
	content : "您确定要继续此操作吗？",
	callback : function() {
	},
	cancel : function() {
	}
};
$.extend({
	tips : function(tipsOptions) {
		var options = $.extend(true, {}, tipsDefaults, tipsOptions || {});
		$.smallBox({
			title : options.title,
			content : "<i class='icon-print'></i> <i>" + options.content
					+ "</i>",
			color : "#659265",
			iconSmall :options.iconSmall,
			timeout : options.timeout
		});
	},
	confirm : function(confirmOptions) {
		var options = $.extend(true, {}, confirmDefaults, confirmOptions || {});
		$.SmartMessageBox({
			title : options.title,
			content : options.content,
			buttons : '[否][是]'
		}, function(ButtonPressed) {
			if (ButtonPressed === "是") {
				options.callback();
			} else {
				options.cancel();
			}
		});
	}
});