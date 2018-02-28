require([ "jquery", "ajax", "jsonAjax","md5","bootstrap-datepicker","customhead","city","commons","map"], function($) {
	
	//加载面包屑和左侧菜单栏事件
	var boxNextClass="icon-a";
	//返回个人主页
	$("#myMain").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	})
	
	$(".ceoInfo-l-a-content li").click(function(){
		var thiss=$(this);
		var data=thiss.attr("data");
		$(".ceoInfo-l-a-content li").removeClass("select");
		$(".ceoInfo-l-a-content li").find(".li-box").find(".txt-box").removeClass("active_color");
		thiss.addClass("select");
		thiss.find(".li-box").find(".txt-box").addClass("active_color");
		var boxClass="";
		if(data=="basic"){
			boxClass="icon-a";
			//loadBasic();
			offset("anchor0");
		}else if(data=="current"){
			boxClass="icon-b";
			$("#icon-a-s").find(".li-box").find(".icon-box").removeClass("icon-a-s").addClass("icon-a");
			//loadCurrent();
			offset("anchor1");
		}else if(data=="selfDescrip"){
			boxClass="icon-h";
			$("#icon-a-s").find(".li-box").find(".icon-box").removeClass("icon-a-s").addClass("icon-a");
			//loadCurrent();
			offset("anchor2");
		}else if(data=="education"){
			boxClass="icon-d";
			$("#icon-a-s").find(".li-box").find(".icon-box").removeClass("icon-a-s").addClass("icon-a");
			//loadEducation();
			offset("anchor4");
		}else if(data=="experience"){
			boxClass="icon-c";
			$("#icon-a-s").find(".li-box").find(".icon-box").removeClass("icon-a-s").addClass("icon-a");
			//loadExperience();
			offset("anchor3");
		}else if(data=="credit"){
			boxClass="icon-e";
			$("#icon-a-s").find(".li-box").find(".icon-box").removeClass("icon-a-s").addClass("icon-a");
			//loadCredit();
			offset("anchor5");
		}
		$("."+boxNextClass).addClass(boxNextClass);
		$("."+boxNextClass).removeClass(boxNextClass+"-s");
		thiss.find(".icon-box").removeClass(boxClass+"-s");
		thiss.find(".icon-box").addClass(boxClass+"-s");
		boxNextClass=boxClass;
		
		
	});
	loadAllInfo();
	
	function loadAllInfo(){
		jsonAjax.post({
			url:ctx+'/member/personage/frag',
			success:function(html){
				$("#dataList").html(html);
				$("#editBasic").click(function(){
					loadBasic();
				});
				$("#editCurrent").click(function(){
					loadCurrent();
				});
				$("#selfDescrip").click(function(){
					loadDescripInfo();
				});
				$("#addProject").click(function(){
					loadExperience();
				});
				$("#addEducation").click(function(){
					loadEducation();
				});
				$("#editCredit").click(function(){
					loadCredit();
				});
				$("#editAccount").click(function(){
					loadSetting();
				});
				//编辑项目
				$("div[id^='editProject_']").each(function(){
					$(this).bind('click',function(){
						editProject($(this).attr("data"));
					});	
				});
				//编辑教育信息
				$("div[id^='editEducation_']").each(function(){
					$(this).bind('click',function(){
						editEducation($(this).attr("data"));
					});	
				});
				//上传头像
				initCustomHead();
			}
		});
	}
	
	//加载基本信息
	function loadBasic(){
		$(".editor_box,.c-box-b-rt,.c-box-b-rt,.edit").hide();
//		if(clickEvent('basicForm'))
		jsonAjax.post({
			url:ctx+'/member/personage/basic',
			success:function(html){
				$("#basicBox").hide();
				$("#basicContent").html("");
				$("#basicContent").show();
				$("#basicContent").html(html);
				
				//基本信息操作
				var kucity=$('#areaName').kuCity();
				$("#cityIcon").click(function(){
					kucity.focus();
				});
				$('.date-select').datepicker({
			        autoclose: true,
			    	startDate:new Date(1950,1,1),
					endDate:new Date(),
					startView: 'decade',
			    }).on("changeDate",function(e){
			    	var time=e.date;
			    	$("#birthday").val(time.Format("yyyy-MM-dd"));
			    });
				upload.uploadImg($("#headFile"),"head",function(data){
					$("#headImg").attr("src",data.url);
					$("#photoId").val(data.attaId);
				});

				//性别切换
				$(".sex button").click(function(){
					$(".sex button").removeClass("active-btn");
					$(this).addClass("active-btn");
					$("#sex").val($(this).attr("data"));
				});
				//性别回显
				if($("#sex").val()!=""){
					$(".sex button").each(function(){
						if($(this).attr("data") == $("#sex").val()){
							$(this).addClass("active-btn");
						}else{
							$(this).removeClass("active-btn");
						}
						
					});
				}
				
				$("#birthday").change(function(){
					//生日不能大于当前日期
					var db = new Date($(this).val().replace(/-/g, "/"));
					if (new Date() <= Date.parse(db)) {
						tips("birthday","生日年月不能大于当前日期");
						$(this).val("");
					    return false;
					} 
				});
				
				$("#saveBtn").click(function(){
					var birthday=$.trim($("#birthday").val());
					var areaName=$.trim($("#areaName").val());
					var email=$.trim($("#email").val());
					if(birthday==""){
						$(".error-tip").show();
						$("#tipError").text("请选择您的出生年月");
						return false;
					}else if(areaName==""){
						$(".error-tip").show();
						$("#tipError").text("请选择您的常驻城市");
						return false;
					}
					else if(email==""){
						$(".error-tip").show();
						$("#tipError").text("请填写您的常用邮箱");
						return false;
					}else if(!validateEmail(email)){
						$(".error-tip").show();
						$("#tipError").text("请输入正确的邮箱格式");
						return false;
					}
					jsonAjax.post({
						url:ctx+'/member/basic/save',
						data:$("#basicForm").serialize(),
						success:function(data){
							if(data.result=="SUCCESS"){
								loadAllInfo();
							}else {
								$(".error-tip").show();
								$("#tipError").text("邮箱已经存在");
							}
						}
					});
				});
				$("#cancel").click(function(){
					loadAllInfo();
				});
			}
		});
	}
	
	//加载当前信息
	function loadCurrent(){
		$(".editor_box,.c-box-b-rt,.c-box-b-rt,.edit").hide();
//		if(clickEvent('currentForm'))
		jsonAjax.post({
			url:ctx+'/member/personage/current',
			success:function(html){
				$("#currentBox").hide();
				$("#currentInfo").html("");
				$("#currentInfo").html(html);
				
				Array.prototype.remove = function(val) {
					var index = this.indexOf(val);
					if (index > -1) {
					    this.splice(index, 1);
				    }
				};
				var selectKey=$("#selectKey").val(),selectSecond=$("#selectSecond").val(),selectText="";
				var selectCount=0;
				var selectArray=new Array();
				var prevFlag=false;//是否是上一步返回
				var refreshFlag=false;
				var prevFirst="";//上一个选中的角色
				if(selectSecond!=""){
//					selectArray=selectSecond.split(',');
//					selectCount=selectArray.length;
					prevFlag=true;
					refreshFlag=true;
					prevFirst=selectKey;
				}
				$("#jobRole_div button").click(function(){
					var thiss=$(this);
					var text=thiss.text();
					var val=thiss.attr("data");
					var orders=thiss.attr("orders");
					var pid=thiss.attr("pid");
					var flag = $("#roleFlag").val();
					if(val!=selectKey && !flag){
						return false;
					}
					if($(this).hasClass("active-btn")){
						return false;
					}
					if(val!=prevFirst){
						selectCount=0;
					}else{
						selectArray=selectSecond.split(',');
						selectCount=selectArray.length;
					}
					selectKey=val;
					$("#jobRole_div button").removeClass("active-btn");
					thiss.addClass("active-btn");
					
					$(".skill-select").show();
					var parentId=thiss.attr("pid");
					loadDictList(parentId,function(data){
						$(".skill-select").show();
						$(".skill-select").html(data);
						$("#rolesUl li").click(function(){
							var id=$(this).attr("id");
							var dkey=$(this).attr("data");
							$(".theChoice").show();
							var li='<li id="'+dkey+'">'+$(this).text()+'</li>';
							if(prevFlag){
								$(this).addClass("skill-checkbox");
								return false;
							}
							//如果当前的角色和上一次选择的不一致，则删掉所有的
							if(prevFirst!=selectKey){
								selectCount=0;
								selectArray=new Array();
								$(".theChoice").html('');
							}
							if($(this).hasClass("skill-checkbox")){
								$(this).removeClass("skill-checkbox");
								$(".theChoice li[id='"+dkey+"']").remove();
								selectCount--;
								selectArray.remove(dkey);
							}else{
								if(selectCount<5){
									selectCount++;
									$(this).addClass("skill-checkbox");
									$(".theChoice").append(li);
									selectArray.push(dkey);
								}else{
									alert("最多只能选择5项");
								}
							}
							if(selectCount>0){
								prevFirst=selectKey;
							}
						});
						for(var k in selectArray){
							var obj=selectArray[k];
							$("#rolesUl li").each(function(){
								var dsf=$(this).attr("data");
								if(dsf==obj){
									$(this).click();
								}
							});
						}
						prevFlag=false;
					});
					prevFlag=true;
				});
				
				if(selectKey!=""){
					var i=$("#btn_index").val();
					$("#jobRole_div button:eq("+i+")").click();
				}
				function loadDictList(pid,callback){
					jsonAjax.post({
						url:ctx+'/common/dict/jobrole',
						data:{parentId:pid},
						success:function(data){
							callback(data);
						}
					});
				}				
				
				
				//关注行业
				var industryArr = new Array();
				$("#industry_ul li").each(function(){
					var attentionIndustry = $("#attentionIndustry").val();
					if(attentionIndustry.indexOf($(this).attr("data"))>=0){
						$(this).addClass("industry_li_active");
					}
					if($(this).hasClass("industry_li_active"))
					industryArr.push($(this).attr("data"));
				})
				$("#industry_ul li").click(function(){
					if(!$(this).hasClass("industry_li_active") && industryArr.length<3){
						industryArr.push($(this).attr("data"));
						$(this).addClass("industry_li_active");
					}else{
						if($.inArray($(this).attr("data"),industryArr)>=0){
							industryArr.splice($.inArray($(this).attr("data"),industryArr),1);
							$(this).removeClass("industry_li_active");
						}else{
							alert("最多选择三项");
						}
					}
					$("#attentionIndustry").val(industryArr.join(','));
	            });				
				
				//相关年限 光标移动
				$(".text_box .text_ul li").hover(function(){
		            $(this).addClass("li_text_active").siblings().removeClass("li_text_active");
		        });
				

				//相关年限
				$("#year_text").text($("#year_val").val());
				$("#year_div").click(function(){
		            $("#year_ul").toggle();
		            year();
		            $("#state_ul").hide();
		            $("#week_ul").hide();
		            $("body").bind("mousedown", function(event) {
		    			$("#year_ul").fadeOut("fast");
		    			$("body").unbind("mousedown");
		    		});
		        });

				function year(){
		            $("#year_ul li").click(function(){
		                var li_text=$(this).text();
		                $("#year_text").text(li_text);
		                $("#jobAge").val($(this).attr("data"));
		                $(this).addClass("li_text_active").siblings().removeClass("li_text_active");
		                $("#year_ul").hide();
		            });
		        };
		        
		        
		        
		        //擅长领域
		        $("#apply_yes").click(function(){
		            $("#conditions_content").show();
		        });
				
		        $("#consider_yes").click(function(){
		        	jsonAjax.post({
						url:ctx+'/member/elite/update',
						success:function(data){
							if(data.result=="SUCCESS"){
								$("#applyDate").text("（您下一次申请时间:"+data.msg+"）");
							}
						}
					});
		        	$("#apply_yes").unbind('click');
		        	$("#apply_yes").removeClass("yes").addClass("no");
		        	$("#apply_yes").text('已申请');
		            $("#conditions_content").hide();
		        });
		        
		        $("#consider_no").click(function(){
		        	$("#conditions_content").hide();
		        	$("#apply_yes").removeClass("no").addClass("yes");
		        });
		        
				
		        
		        //是否在职
		        $("#state_div").click(function(){
		            $("#state_ul").toggle();
		            state();
		            $("#year_ul").hide();
		            $("#week_ul").hide();
		            $("body").bind("mousedown", function(event) {
		    			$("#state_ul").fadeOut("fast");
		    			$("body").unbind("mousedown");
		    		});
		        });

				function state(){
		            $("#state_ul li").click(function(){
		                var li_text=$(this).text();
		                $("#state_text").text(li_text);
		                $("#onjobed").val($(this).attr("data"));
		                $(this).addClass("li_text_active").siblings().removeClass("li_text_active");
		                $("#state_ul").hide();
		            });
		        };
				
		        //每周可分配时长
		        $("#week_text").text( $("#week_val").val());
		        $("#week_div").click(function(){
		            $("#week_ul").toggle();
		            week();
		            $("#year_ul").hide();
		            $("#state_ul").hide();
		            $("body").bind("mousedown", function(event) {
		    			$("#week_ul").fadeOut("fast");
		    			$("body").unbind("mousedown");
		    		});
		        });

				function week(){
		            $("#week_ul li").click(function(){
		                var li_text=$(this).text();
		                $("#week_text").text(li_text);
		                $("#allotDuration").val($(this).attr("data"));
		                $(this).addClass("li_text_active").siblings().removeClass("li_text_active");
		                $("#week_ul").hide();
		            });
		        };
				
		        function tip(prompt){
		        	$(".error-tip").show();
		        	$("#tipError").text(prompt);
		        }
				
				$("#saveBtn").click(function(){
					var jobAge=$.trim($("#jobAge").val());
					var allotDuration=$.trim($("#allotDuration").val());
					var onjobed=$.trim($("#onjobed").val());
					var attentionIndustry=$.trim($("#attentionIndustry").val());
					if(selectKey==""||selectCount==0){
						if(selectKey==""){
							tip("请选择至少一个胜任角色");
						}else if(selectCount==0){
							tip("请选择您的二级角色");
						}
						return false;
					}else if(selectCount>5){
						tip("技能选择不能超过5个");
						return false;
					}else if(jobAge==""){
						tip("请选择您的工作年限");
						return false;
					}else if(attentionIndustry==""){
						tip("请至少选择一项关注的行业");
						return false;
					}else if(allotDuration==""){
						tip("请选择您每周可分配的时长");
						return false;
					}
					var jobMap = new Map();
					jobMap.put(selectKey,selectArray);
					$("#eliteJobMaps").val(jobMap.toString());
					jsonAjax.post({
						url:ctx+'/member/elite/current/save',
						data:$("#currentForm").serialize(),
						success:function(data){
							if(data.result=="SUCCESS"){
								loadAllInfo();
							}
						}
					});
				});
				$("#cancel").click(function(){
					loadAllInfo();
				});
			}
		});
	}
	
	function loadDictList(pid,callback){
		jsonAjax.post({
			url:ctx+'/common/dict/jobrole',
			data:{parentId:pid},
			success:function(data){
				callback(data);
			}
		});
	}
	
	//加载个人简介
	function loadDescripInfo(){
		$(".editor_box,.c-box-b-rt,.c-box-b-rt,.edit").hide();
		jsonAjax.post({
			url:ctx+'/member/elite/intro',
			success:function(html){
				$("#selfDescripBox").hide();
				$("#selfDescripInfo").html("");
				$("#selfDescripInfo").html(html);
				UE.delEditor('container');
				UE.getEditor('container', {
				    toolbars: [
				        ['insertorderedlist', 'insertunorderedlist']
				    ]
				});
				$("#save").click(saveDescripInfo);
				$("#cancel").click(function(){
					loadAllInfo();
				});
				
			}
		});
	}
	
	
	function saveDescripInfo(){
		var intro =UE.getEditor('container').getContent();
		var content = UE.getEditor('container').getContentTxt();
		if(content=='' || content.length>1000){
			$(".error-tip").show();
			$("#tipError").text("不能为空且字数不能大于1000字");
			return false;
		}
		jsonAjax.post({
			url:ctx+'/member/elite/save',
			data:{intro:intro},
			success:function(data){
				if(data.result=="SUCCESS"){
					loadAllInfo();
				}
			}
		});
		
	}
	
	//加载项目经验
	function loadExperience(){
		$(".editor_box,.c-box-b-rt,.c-box-b-rt,.edit").hide();
//		if(clickEvent('addProjectForm'))
		jsonAjax.post({
			url:ctx+'/member/personage/experience',
			success:function(html){
				$("#projectBox").hide();
				$("#projectInfo").html("");
				$("#projectInfo").html(html);
				
				$('#startTimeSelect').datepicker({
					format:"yyyy-mm",
					endDate : new Date() ,
			        autoclose: true,
			        startView: 'year',
			        minViewMode:'months'
			    }).on("changeDate",function(e){
			    	var time=e.date;
			    	$("#startTime").val(time.Format("yyyy-MM"));
			    });
				$('#endTimeSelect').datepicker({
					format:"yyyy-mm",
					endDate : new Date() ,
			        autoclose: true,
			        startView: 'year',
			        minViewMode:'months'
			    }).on("changeDate",function(e){
			    	var time=e.date;
			    	$("#endTime").val(time.Format("yyyy-MM"));
			    });
				UE.delEditor('editor');
				UE.getEditor('editor', {
				    toolbars: [
				        ['insertorderedlist', 'insertunorderedlist']
				    ]
				});
				$("#save").click(saveProject);
				
				$("#cancel").click(function(){
					loadAllInfo();
				});
				
			}
		});
	}
	//保存项目
	function saveProject(){
		var project=$.trim($("#addProjectForm #project").val());
		var position=$.trim($("#addProjectForm #position").val());
		var startTime=$.trim($("#addProjectForm #startTime").val());
		var endTime=$.trim($("#addProjectForm #endTime").val());
		var intro=UE.getEditor('editor').getContentTxt();
		$("#intro").val(UE.getEditor('editor').getContent());
		var tips=$("#addProjectForm .error-tip");
	
		tips.text('');
		if(project==""){
			tips.text("请填写项目名称");
			return false;
		}else if(position==""){
			tips.text("请填写你所担任的职位");
			return false;
		}else if(startTime==""){
			tips.text("请填写项目开始时间");
			return false;
		}else if(endTime==""){
			tips.text("请填写项目结束时间");
			return false;
		}else if(endTime<startTime){
			tips.text("项目结束时间必须大于项目开始时间");
			return false;
		}else if(new Date() < Date.parse(new Date(endTime.replace(/-/g, "/")))){
			tips.text("项目结束时间必须小于当前时间");
			return false;
		}else if(new Date() < Date.parse(new Date(startTime.replace(/-/g, "/")))){
			tips.text("项目开始时间必须小于当前时间");
			return false;
		}
		else if(intro=="" || intro.length>1000){
			tips.text("描述不能为空且字数不能超过1000字");
			return false;
		}
		jsonAjax.post({
			url:ctx+'/member/project/save',
			data:$("#addProjectForm").serialize(),
			success:function(data){
				if(data!=null&&data.id!=null){
					loadAllInfo();
				}
			}
		});
	}
	
	//编辑项目
	function editProject(id){
		$(".editor_box,.c-box-b-rt,.c-box-b-rt,.edit").hide();
		jsonAjax.post({
			url:ctx+'/member/personage/experience',
			data:{id:id},
			success:function(html){
				$("#projectBox").hide();
				$("#projectInfo").html("");
				$("#projectInfo").html(html);
				
				$('#startTimeSelect').datepicker({
					format:"yyyy-mm",
					endDate : new Date() ,
			        autoclose: true,
			        startView: 'year',
			        minViewMode:'months'
			    }).on("changeDate",function(e){
			    	var time=e.date;
			    	$("#startTime").val(time.Format("yyyy-MM"));
			    });
				$('#endTimeSelect').datepicker({
					format:"yyyy-mm",
					endDate : new Date() ,
			        autoclose: true,
			        startView: 'year',
			        minViewMode:'months'
			    }).on("changeDate",function(e){
			    	var time=e.date;
			    	$("#endTime").val(time.Format("yyyy-MM"));
			    });
				UE.delEditor('editor');
				UE.getEditor('editor', {
				    toolbars: [
				        ['insertorderedlist', 'insertunorderedlist']
				    ]
				});
				$("#save").click(saveProject);
				$("#del").click(delProject);
				
				$("#cancel").click(function(){
					loadAllInfo();
				});
				
			}
		});
	}
	//删除项目
	function delProject(){
		var thiss=$(this);
		var id=thiss.attr("data");
		if(id==""){
			return false;
		}
		tostConfirm("确定要删除吗？",function(){
			jsonAjax.post({
				url:ctx+'/member/project/remove',
				data:{id:id},
				success:function(data){
					if(data.result=="SUCCESS"){
						loadAllInfo();
						
					}
				}
			});
		});
		
	}

	
	//加载征信信息
	function loadCredit(){
		$(".editor_box,.c-box-b-rt,.c-box-b-rt,.edit").hide();
//		if(clickEvent('companyFrom'))
		jsonAjax.post({
			url:ctx+'/member/personage/credit',
			success:function(html){
				$("#creditInfo").hide();
				$("#creditBox").html("");
				$("#creditBox").html(html);
				$("#bankCard").bind("keyup blur",function(){
					$(this).val($(this).val().replace(/\D/g,''));
				}); 
				
	
				upload.uploadImg($("#cardJustFile"),"credit",function(data){
					$("#cardJustImg").attr("src",data.url);
					$("#cardJustImg").css({width:'160px',height:'100px'});
					$("#cardJust").val(data.attaId);
				});
				upload.uploadImg($("#cardReverseFile"),"credit",function(data){
					$("#cardReverseImg").attr("src",data.url);
					$("#cardReverseImg").css({width:'160px',height:'100px'});
					$("#cardReverse").val(data.attaId);
				});
				upload.uploadImg($("#businessCertFile"),"credit",function(data){
					$("#businessCertImg").attr("src",data.url);
					$("#businessCertImg").css({width:'160px',height:'100px'});
					$("#businessCert").val(data.attaId);
				});
				upload.uploadImg($("#jobCertFile"),"credit",function(data){
					$("#jobCertImg").attr("src",data.url);
					$("#jobCertImg").css({width:'160px',height:'100px'});
					$("#jobCert").val(data.attaId);
				});
				upload.uploadImg($("#visitingCertFile"),"credit",function(data){
					$("#visitingCertImg").attr("src",data.url);
					$("#visitingCertImg").css({width:'160px',height:'100px'});
					$("#visitingCert").val(data.attaId);
				});
				$("#cancel").click(function(){
					loadAllInfo();
				});
				$("#saveBtn").click(function() {
					var realName=$.trim($("#realName").val());
					var idCard=$.trim($("#idCard").val());
					var cardJust=$.trim($("#cardJust").val());
					var cardReverse=$.trim($("#cardReverse").val());
					var tips=$("#companyFrom .error-tip");
					if(realName==""){
						tips.show();
						tips.text("请输入您的真实姓名");
						return false;
					}else if(idCard==""){
						tips.show();
						tips.text("请输入您的身份证号码");
						return false;
					}else if(!validateCard(trimMiddle(idCard))){
						tips.show();
						tips.text("请输入正确的身份证号码");
						return false;
					}else if(cardJust==""){
						tips.show();
						tips.text("请上传您的身份证正面照");
						return false;
					}else if(cardReverse==""){
						tips.show();
						tips.text("请上传您的身份证反面照");
						return false;
					}
					jsonAjax.post({
						url : ctx + '/member/credit/save',
						data : $("#companyFrom").serialize(),
						success : function(data) {
							console.info(data);
							if (data.result == "SUCCESS") {
								loadAllInfo();
							}else{
								tips.show();
								tips.text(data.msg);
							}
						}
					});
				});
			}
		});
	}
	
	//加载教育信息
	function loadEducation(){
		$(".editor_box,.c-box-b-rt,.c-box-b-rt,.edit").hide();
//		if(clickEvent('educationForm'))
		jsonAjax.post({
			url:ctx+'/member/personage/education',
			success:function(html){
				$("#educationBox").hide();
				$("#educationInfo").html(""); 
				$("#educationInfo").show();
				$("#educationInfo").html(html);
				
				$('#graduateTime').datepicker({
					format:"yyyy-mm",
			        autoclose: true,
			        startView: 'year',
			        minViewMode:'months',
			    	startDate:new Date(1950,1,1),
					endDate:new Date(),
			    });
				
				$("#chooseEducation").click(function(){
					$("#educationType").show();
					$("body").bind("mousedown", function(event) {
						$("#educationType").fadeOut("fast");
						$("body").unbind("mousedown");
					});
				});
				
				//最高学历光标移动
				$(".educationType ul li").hover(function(){
			        $(this).addClass("active_education").siblings().removeClass("active_education");
			    });
				 
				$("#educationType ul li").click(function(){
					$(this).addClass("active_education").siblings().removeClass("active_education");
	                var li_text=$(this).text();
	                $("#educationval").val(li_text);
	                $("#education").val($(this).attr("data"));
	                $("#educationType").hide();
	            });
				$("#save").click(saveEducation);
				$("#cancel").click(function(){
					loadAllInfo();
				});
			}
		});
	}
	
	
	
	//保存教育经验
	function saveEducation(){
		var organ=$.trim($("#educationForm #organ").val());
		var major=$.trim($("#educationForm #major").val());
		var education=$.trim($("#educationForm #education").val());
		var graduateTime=$.trim($("#educationForm #graduateTime").val());
		var tips=$("#educationForm .error-tip");
	
		tips.text('');
		if(organ==""){
			tips.text("请填写毕业院校");
			return false;
		}else if(major==""){
			tips.text("请填写专业");
			return false;
		}else if(education==""){
			tips.text("请填写最高学历");
			return false;
		}else if(graduateTime==""){
			tips.text("请填写毕业时间");
			return false;
		}
		jsonAjax.post({
			url:ctx+'/member/education/save',
			data:$("#educationForm").serialize(),
			success:function(data){
				if(data!=null&&data.id!=null){
					loadAllInfo();
				}
			}
		});
	}
	//删除教育
	function delEducation(){
		var thiss=$(this);
		var id=thiss.attr("data");
		if(id==""){
			return false;
		}
		tostConfirm("确定要删除吗？",function(){
			jsonAjax.post({
				url:ctx+'/member/education/remove',
				data:{id:id},
				success:function(data){
					if(data.result=="SUCCESS"){
						loadAllInfo();
						
					}
				}
			});
		});
		
	}
	//编辑教育信息
	function editEducation(id){
		$(".editor_box,.c-box-b-rt,.c-box-b-rt,.edit").hide();
		jsonAjax.post({
			url:ctx+'/member/personage/education',
			data:{id:id},
			success:function(html){
				$("#educationBox").hide();
				$("#educationInfo").html(""); 
				$("#educationInfo").show();
				$("#educationInfo").html(html);
				
				$('#graduateTime').datepicker({
					format:"yyyy-mm",
			        autoclose: true,
			        startView: 'year',
			        minViewMode:'months',
			    	startDate:new Date(1950,1,1),
					endDate:new Date(),
			    });
				
				$("#chooseEducation").click(function(){
					$("#educationType").show();
					$("body").bind("mousedown", function(event) {
						$("#educationType").fadeOut("fast");
						$("body").unbind("mousedown");
					});
				});
				
				//最高学历光标移动
				$(".educationType ul li").hover(function(){
			        $(this).addClass("active_education").siblings().removeClass("active_education");
			    });
				$("#educationType ul li").click(function(){
					$(this).addClass("active_education").siblings().removeClass("active_education");
	                var li_text=$(this).text();
	                $("#educationval").val(li_text);
	                $("#education").val($(this).attr("data"));
	                $("#educationType").hide();
	            });
				
				$("#del").click(delEducation);
				
				$("#save").click(saveEducation);
				
				$("#cancel").click(function(){
					loadAllInfo();
				});
			}
		});
	}

	function offset(id){
		var top=$("#"+id).offset().top;
		$("body,html").animate({
	       scrollTop: top-100
	    }, 1000);
	}

});