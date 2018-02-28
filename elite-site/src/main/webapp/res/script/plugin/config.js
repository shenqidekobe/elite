require.config({
	baseUrl : ctx+"/res/script/plugin",
	shim : {
		"jquery-form" : {
			deps : [ "jquery" ]
		},
		"bootstrap-datepicker" : {
			deps : [ "jquery" ]
		},
		ajaxfileupload:{
			deps : [ "jquery" ]
		},
		"jquery.fileupload":{
			deps : [ "jquery" ]
		},
		"jquery.ui.widget":{
			deps : [ "jquery" ]
		},
		webupload:{
			deps : [ "jquery","jquery.fileupload" ]
		},
		bootstrap : {
			deps : [ "jquery" ]
		},
		ajax : {
			deps : [ "jquery" ]
		},
		jsonAjax : {
			deps : [ "jquery" ]
		},
		md5 : {
			deps : [ "jquery" ]
		},
		city :{
			deps : [ "jquery" ]
		}, 
		copyjs:{
			deps : [ "jquery" ]
		},
		autocomplete:{
			deps : [ "jquery" ]
		},
		highcharts:{
			deps : [ "jquery" ]
		},
		customhead:{
			deps : [ "ajax","jsonAjax","jquery","webupload" ]
		}
	},

	packages : [

	],
	paths : {
		jquery : "jquery.min",
		ajax : "ajax",
		jsonAjax : "jsonAjax",
		md5 : "jquery.md5",
		ajaxfileupload:"ajaxfileupload",
		"jquery.fileupload":"jquery.fileupload",
		"jquery.ui.widget":"jquery.ui.widget",
		webupload:"webupload",
		"bootstrap-datepicker" : "datepicker/bootstrap-datepicker",
		commons:"commons",
		map:"map",
		city:"city/kuCity",
		copyjs:"copyJS/clipboard.min",
		requirejs : "require.min",
		"jquery-form" : "jquery.form.min",
		bootstrap : "bootstrap.min",
		autocomplete:"autocomplete/jquery-ui",
		"ueditor-config":"ueditor/ueditor.config",
		"ueditor-all":"ueditor/ueditor.all.min",
		"ueditor-msg":"ueditor/lang/zh-cn/zh-cn",
		pingpp : "pingpp/pingpp",
		"highcharts":"highcharts/highcharts",
		customhead:"customHead"
	},
	priority: [
       'jquery',
       'bootstrap',
    ]
});
