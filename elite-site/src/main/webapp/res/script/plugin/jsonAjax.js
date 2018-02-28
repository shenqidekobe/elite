var jsonAjax = {
	headers : {
		"Accept" : "application/json, text/javascript, */*",
		//"Content-Type" : "application/json;charset=UTF-8",
	},
	dataToJson : function(data){
		return data;
	},
	setData : function(ajaxOptions){
		ajaxOptions.headers = this.headers;
		var ssoToken=localStorage.ssoToken?localStorage.ssoToken:'';
		ajaxOptions.headers=$.extend({},ajaxOptions.headers,{ssoToken : ssoToken});
		ajaxOptions.data = this.dataToJson(ajaxOptions.data);
	},
	get : function(ajaxOptions){
		this.setData(ajaxOptions);
		Ajax.get(ajaxOptions);
	},
	post : function(ajaxOptions){
		this.setData(ajaxOptions);
		Ajax.post(ajaxOptions);
	},
	del : function(ajaxOptions){
		this.setData(ajaxOptions);
		Ajax.del(ajaxOptions);
	},
	put : function(ajaxOptions){
		this.setData(ajaxOptions);
		Ajax.put(ajaxOptions);
	},
	getUrlParams:function(paramName){
		var url=window.location.href;
		var reg = new RegExp("(^|&)"+ paramName +"=([^&]*)(&|$)");
	    var r = url.substring(url.indexOf('?')).substr(1).match(reg);
	    if(r!=null)return  r[2]; return '';
	},
	random:function(len){
		len = len || 32;
		var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
		var maxPos = $chars.length;
		var tmp = '';
		var timestamp = new Date().getTime();
		for (i = 0; i < len; i++) {
			tmp += $chars.charAt(Math.floor(Math.random() * maxPos));
		}
		return  timestamp+tmp;
	}
};
