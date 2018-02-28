var upload = {
	path:uploadPath,
	uploadImg : function(target,app,callback) {
		target.fileupload({
			url : upload.path + '/upload/picture/' + app + '/to',
			dataType: 'json',
			add: function(e, data) {
		        var uploadErrors = [];
		        var acceptFileTypes = /^image\/(gif|jpe?g|png)$/i;
		        if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
		            uploadErrors.push('不支持的文件上传上传类型');
		        }
		        if(data.originalFiles[0]['size'].length && data.originalFiles[0]['size'] > 5000000) {
		            uploadErrors.push('文件大小超过限制');
		        }
		        if(uploadErrors.length > 0) {
		            alert(uploadErrors.join("\n"));//弹出消息提示框
		        } else {
		            data.submit();
		        }
		    },
			acceptFileTypes:/^image\/(gif|jpe?g|png)$/i,
			done: function (e, rsp) {
				var data=rsp.result;
				if(callback){
					if(data.result=="SUCCESS"){
						callback(data);
					}else{
						if(data.code=="1601"){
							$.tips({
								content : "上传失败,不支持的图片类型!"
							});
						}else if(data.code=="1602"){
							$.tips({
								content : "上传失败,图片上传超过大小限制!"
							});
						}else{
							$.tips({
								content : "上传失败,请检查网络或系统错误!"
							});
						}
					}
				}
	        }
		})
		return false;
	},
	uploadFile : function(target,app,type,callback){
		target.fileupload({
			url : upload.path + '/upload/accessory/' + app + '/to',
			dataType: 'json',
			change: function(e, data) {
				console.info(target)
				if(type=="project_atta"){
					var len=$("#attaFile_show div").length;
	                if(len>=2){
	                    $("#fcDialog").modal('show');
	                    return false;
	                }
				}else if(type=="project_planbook"){
					var len=$("#planBook_show div").length;
	                if(len>=1){
	                    $("#fcDialog").modal('show');
	                    return false;
	                }
				}
            },
            progressall: function (e, data) {
            	console.info(data);
            	$("#progressDialog").modal({backdrop: 'static', keyboard: false});
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('.progress .progress-bar').css(
                    'width',
                    progress + '%'
                );
                $('.progress .progress-bar').text(progress + '%');
            },
			done: function (e, rsp) {
				var data=rsp.result;
				if(callback){
					if(data.result=="SUCCESS"){
						callback(data);
					}else{
						console.info(data.msg);
					}
				}
				$('.progress .progress-bar').text("上传完成");
				$("#progressDialog").modal("hide");
	        },
	        fail: function(e, data) {  
	        	if(e.type='fileuploadfail'){
	        		$.tips({
						content : "上传失败,请检查网络或系统错误!"
					});
	        	}
	        	$('.progress .progress-bar').text("上传失败");
	        	window.setTimeout(function(){
	        		$("#progressDialog").modal("hide");
	        	},3000);
	        }  
		})
		return false;
	},
	removeFile:function(app,callback){
		var url=upload.path+'/upload/'+app+'/delete';
		callback(url);
	}
}