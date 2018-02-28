var prefectlist = {
       
	showPrefectForm : function() {
		var id = $(this).attr("data");
		if(id==""){
			$.tips({
				content : "项目方为空"
			});
			return;
		}
		jsonAjax.post({
			url : ctx + '/member/company/prefect/view',
			data : {
				id : id
			},
			success : function(data) {
				$("#auditData").html(data);
				$('#myModel').modal('show');
				prefectlist.validate();
				$('#startTimeId').datepicker({
					language : 'zh-CN',
					autoclose : true,
					format : "yyyy-MM-dd",
					todayHighlight : true,
				})
				$("a[id=sacleSelect]").click(prefectlist.selectBudge);
				
				////城市选择器
				$('#areaName').kuCity();
				//头像上传
				upload.uploadImg($("#photoIdFile"), "head", function(data) {
					$("#photoImgId").attr("src", data.url);
					$("#photoId").val(data.attaId);
				});
				//身份证正面
				upload.uploadImg($("#cardJustPhotoFile"), "credit", function(data) {
					$("#cardJustPhotoId").attr("src", data.url);
					$("#cardJustId").val(data.attaId);
				});
				upload.uploadImg($("#cardReversePhotoFile"), "credit", function(data) {
					$("#cardReversePhotoId").attr("src", data.url);
					$("#cardReverseId").val(data.attaId);
				});
				upload.uploadImg($("#jobCertPhotoFile"), "credit", function(data) {
					$("#jobCertPhotoId").attr("src", data.url);
					$("#jobCertId").val(data.attaId);
				});
				upload.uploadImg($("#visitingCertPhotoFile"), "credit", function(data) {
					$("#visitingCertPhotoId").attr("src", data.url);
					$("#visitingCertId").val(data.attaId);
				});
				upload.uploadImg($("#businessCertPhotoFile"), "credit", function(data) {
					$("#businessCertPhotoId").attr("src", data.url);
					$("#businessCertId").val(data.attaId);
				});
			},error:function(data){
				$('#myModel').modal('hide');
				$('#startTimeId').datepicker({
					language : 'zh-CN',
					autoclose : true,
					format : "yyyy-MM-dd",
					todayHighlight : true,
				})
				$.tips({
					content : data.msg
				});
			}
		})
	},

	validate : function() {
		
	   //身份证验证
		$.validator.addMethod("isIdCard", function(value, element) {
		    var reg=/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i;
		    return this.optional(element) || reg.test(value);
		}, "请正确身份证号码")
		
		
		$("#prefectForm").validate({
			debug : false,
			rules : {
				"credit.realName" : {
					maxlength : 10,
				},
				
				"basic.memberSign" : {
					maxlength : 15,
				},
				"credit.idCard":{
					isIdCard :true,
				}
			
			},
			messages : {
				"credit.realName" : {
					maxlength : '姓名只允许2~10个字符',
				},
				"basic.memberSign" : {
					maxlength : '头衔应少于15字'
				},
				"credit.idCard":{
					isIdCard :'请输入合法身份证号'
				}
			},
			submitHandler : function(form) {

				jsonAjax.post({
					url : ctx + '/member/company/prefect/save',
					data : $("#prefectForm").serialize(),
					success : function(data) {
						if (BASE.JS_RESULT.SUCCESS == data.result) {
							$('#myModel').modal('hide');
						} else {
							$.tips({
								content : data.msg
							});
						}
					}
				})

			}
		});
	},
	selectBudge : function() {
		var title = $(this).attr("text");
		var key = $(this).attr("data");
		$("#bugetShowId").html(title);
		$("#companyScaleId").val(key);
		$("#teamNumId").val(key);
	},
};