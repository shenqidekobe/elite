var jsonAjax = {
	headers : {
		"Accept" : "application/json, text/javascript, */*",
		//"Content-Type" : "application/json;charset=UTF-8",
	},
	dataToJson : function(data){
		//return JSON3.stringify(data);
		return data;
	},
	setData : function(ajaxOptions){
		ajaxOptions.headers = this.headers;
		ajaxOptions.headers=$.extend({},ajaxOptions.headers,{ssoToken : ''});
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
	}
};
