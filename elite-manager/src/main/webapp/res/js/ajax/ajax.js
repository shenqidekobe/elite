var Ajax = {
	ajax : function(ajaxOptions){
		$.ajax({
			url : ajaxOptions.url,
			type : ajaxOptions.type,
			data : ajaxOptions.data,
			global : ajaxOptions.global == null ? true : ajaxOptions.global,
			beforeSend : function(xhr) {
				var headers = ajaxOptions.headers;
				if(headers){
					for(var name in headers){       
						xhr.setRequestHeader(name,headers[name]);
					};
				};
			},
			success : function(data,textStatus,request) {
				var flag=request.getResponseHeader('flag');
				if(data.result=="NOLOGIN"||flag=='login'){
					window.location.href=ctx+'/login';
				}else{
					ajaxOptions.success(data);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				if(ajaxOptions.error){
					ajaxOptions.error(XMLHttpRequest, textStatus, errorThrown);
				}
				$.tips({
 	    			title:"提示",
 	    			content:JSON.parse(XMLHttpRequest.responseText).msg,
 	    			iconSmall:"icon-warning-sign",
 	    			timeout:3000
 	    		});
			}
		});
	},
	hiddenHttpMethod : function(url, type){
		if(-1<url.indexOf("?")){
			url += "&_method="+type;
		}else{
			url += "?_method="+type;
		}
		return url;
	},
	get : function(ajaxOptions){
		ajaxOptions.type = "get";
		this.ajax(ajaxOptions);
	},
	post : function(ajaxOptions){
		ajaxOptions.type = "post";
		this.ajax(ajaxOptions);
	},
	del : function(ajaxOptions){
		ajaxOptions.url = this.hiddenHttpMethod(ajaxOptions.url, "delete");
		ajaxOptions.type = "get";
		this.ajax(ajaxOptions);
	},
	put : function(ajaxOptions){
		ajaxOptions.url = this.hiddenHttpMethod(ajaxOptions.url, "put");
		ajaxOptions.type = "post";
		this.ajax(ajaxOptions);
	}
};