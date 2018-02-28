var upload = {
	path:uploadPath,
	uploadImg : function(target,app,callback) {
		target.fileupload({
			url : upload.path + '/upload/picture/' + app + '/to',
			dataType: 'json',
			add: function(e, data) {
		        var uploadErrors = [];
		        var acceptFileTypes = /^image\/(jpe?g|png|bim|bmp|JPE?G|PNG|BIM|BMP)$/i;
		        if(data.originalFiles[0]['type']==""){
		        	 uploadErrors.push('不支持的文件上传类型');
		        }else if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
		            uploadErrors.push('不支持的文件上传类型');
		        }
		        if(data.originalFiles[0]['size'].length && data.originalFiles[0]['size'] > 5240000) {
		            uploadErrors.push('文件大小超过限制,请控制在5M以内');//最大5M的文件
		        }
		        if(uploadErrors.length > 0) {
		        	tostHint("上传失败",uploadErrors.join("\n"));//弹出消息提示框
		        } else {
		            data.submit();
		        }
		    },
			acceptFileTypes:/^image\/(jpe?g|png|bim|bmp|JPE?G|PNG|BIM|BMP)$/i,
			done: function (e, rsp) {
				var data=rsp.result;
				if(callback){
					if(data.result=="SUCCESS"){
						callback(data);
					}else{
						if(data.code=="1601"){
							tostHint("上传失败","不支持的图片类型");
						}else if(data.code=="1602"){
							tostHint("上传失败","图片上传超过大小限制");
						}else{
							tostHint("上传失败","请检查网络或系统错误!");
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
				if(type=="project_atta"){
					var len=$("#attaFile_show div").length;
	                if(len>=2){
	                	tostHint("上传失败","附件描述文档超过数量限制");
	                    return false;
	                }
				}else if(type=="project_planbook"){
					var len=$("#planBook_show div").length;
	                if(len>=1){
	                	tostHint("上传失败","商业计划书只能上传一份");
	                    return false;
	                }
				}
            },
            progressall: function (e, data) {
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
						console.info(data);
						if(data.code=="1601"){
							tostHint("上传失败","不支持的上传类型");
						}else if(data.code=="1602"){
							tostHint("上传失败","文件上传超过大小限制");
						}
					}
				}
				$('.progress .progress-bar').text("上传完成");
				$("#progressDialog").modal("hide");
				$('body').removeClass("modal-open");
	        },
	        fail: function(e, data) {
	        	if(e.type='fileuploadfail'){
	        		tostHint("上传失败","请检查网络或系统错误!");
	        	}
	        	$('.progress .progress-bar').text("上传失败");
	        	window.setTimeout(function(){
	        		$("#progressDialog").modal("hide");
	        		$('body').removeClass("modal-open");
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