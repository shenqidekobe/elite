function initCustomHead(){
	//修改头像
	$(".modifyBtn,#headImg").click(function(){
		loadHead();
	});
	function loadHead(){
		jsonAjax.post({
			url:ctx+'/member/custom/head',
			success:function(data){
				$(".switchWindow").hide();
				$(".headWindow").html(data);
				$(".headWindow").show();
				$(".customBtn").click(function(){
					$("#headFile").click();
				});
				$("#closeBtnHead").click(function(){
					$(".headWindow").hide();
				});
				$("#headImgUl li").click(function(){
					var dataId=$(this).attr("data");
					var url=$(this).find("img").attr('src');
					$("#headImg").attr("src",url);
					$("#photoUrl").val(url);
					$("#photoId").val(dataId);
					
					savePhoto(dataId,url);
					$("#closeBtnHead").click();
				});
			}
		});
	}
	
	//上传文件
	upload.uploadImg($("#headFile"),"head",function(data){
		$("#headImg").attr("src",data.url);
		$("#photoUrl").val(data.url);
		$("#photoId").val(data.attaId);
		
		savePhoto(data.attaId,data.url);
		$("#closeBtnHead").click();
	});
	
	//修改头像
	function savePhoto(photoId,imgSrc){
		var isSave=$(".headWindow").attr("save");
		if('Y'==isSave){
			jsonAjax.post({
				url:ctx+'/member/update/photo',
				data:{photoId:photoId},
				success:function(data){
					if(data.result=="SUCCESS"){
						$(".head-portrait img").attr('src',imgSrc);
					}
				}
			});
		}
	}
	
};
