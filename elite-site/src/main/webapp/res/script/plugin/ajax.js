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
				}else if(data.result=="UNAUTHORIZED"||flag=='unauthorized'){
					window.location.href=ctx+'/common/unauthorized';
				}else{
					ajaxOptions.success(data);
				}
				/*else if(typeof(data)!="object"&&data.indexOf("技术精英和投资人会有邀请码")!=-1){
					window.location.href=ctx+'/visit';
				}*/
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				if(ajaxOptions.error){
					ajaxOptions.error(XMLHttpRequest, textStatus, errorThrown);
				}
				//tips
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