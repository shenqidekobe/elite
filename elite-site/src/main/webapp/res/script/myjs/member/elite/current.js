require([ "jquery", "ajax", "jsonAjax","commons","map"], function($) {
	//重写数组remove方法
	Array.prototype.remove = function(val) {
		var index = this.indexOf(val);
		if (index > -1) {
		    this.splice(index, 1);
	    }
	};
	//胜任角色选择
	var selectKey=$("#selectKey").val(),selectSecond=$("#selectSecond").val(),selectText="";
	var selectCount=0;
	var selectArray=new Array();
	var prevFlag=false;//是否是上一步返回
	var refreshFlag=false;
	var prevFirst="";//上一个选中的角色
	if(selectSecond!=""){
		selectArray=selectSecond.split(',');
		selectCount=selectArray.length;
		prevFlag=true;
		refreshFlag=true;
		prevFirst=selectKey;
	}
	var maxCount=5;
	$("#jobRole_div button").click(function(){
		if($(this).hasClass("active-btn")){
			return false;
		}
		var thiss=$(this);
		var text=thiss.text();
		var val=thiss.attr("data");
		var orders=thiss.attr("orders");
		var pid=thiss.attr("pid");
		
		$("#jobRole_div button").removeClass("active-btn");
		thiss.addClass("active-btn");
		
		$(".skill-select").show();
		var parentId=thiss.attr("pid");
		loadDictList(parentId,function(data){
			$(".skill-select").show();
			$(".skill-select").html(data);
			$("#rolesUl li").click(function(){
				selectKey=val;//点击二级菜单才记住一级key
				var id=$(this).attr("id");
				var dkey=$(this).attr("data");
				//如果当前的角色和上一次选择的不一致，则删掉所有的
				if(prevFirst!=selectKey){
					selectCount=0;
					selectArray=new Array();
					$(".theChoice").html('');
				}
				console.info("selectCount = "+selectCount+",maxCount = "+maxCount);
				$(".theChoice").show();
				var li='<li id="'+dkey+'">'+$(this).text()+'</li>';
				if(prevFlag){
					$(this).addClass("skill-checkbox");
					return false;
				}
				if($(this).hasClass("skill-checkbox")){
					$(this).removeClass("skill-checkbox");
					$(".theChoice li[id='"+dkey+"']").remove();
					selectCount--;
					selectArray.remove(dkey);
				}else{
					selectCount++;
					if(selectCount>maxCount){
						if($(this).hasClass("skill-checkbox")){
							$(this).removeClass("skill-checkbox");
							$(".theChoice li[id='"+dkey+"']").remove();
							selectCount--;
							selectArray.remove(dkey);
						}else{
							selectCount--;
						}
					}else{
						$(this).addClass("skill-checkbox");
						$(".theChoice").append(li);
						selectArray.push(dkey);
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
	//是否参与CTO签约计划
	$(".agree-checkbox").click(function(){
		var thiss=$(this);
		if(thiss.hasClass("a_b")){
			thiss.removeClass("a_b");
			thiss.find("img").attr("src",ctx+'/res/images/checkbox_untick.png');
			$("#ctoSigned").val(false);
		}else{
			thiss.addClass("a_b");
			thiss.find("img").attr("src",ctx+'/res/images/checkbox_tick.png');
			$("#ctoSigned").val(true);
		}
	});
	$("#cto_signed_href").click(function(){
		//window.open('http://yunyinghui.com');
	});
	//工作年限选择事件
	$("#jobAge_span li").hover(function(){
        $(this).addClass("li_text_active").siblings().removeClass("li_text_active");
    });
	$("#year_text").text($("#year_val").val());
	$("#year_div").click(function(){
		$("#jobAge_span").toggle();
		
		 $("body").bind("mousedown", function(event) {
 			$("#jobAge_span").fadeOut("fast");
 			$("body").unbind("mousedown");
 		});
		 
		$("#jobAge_span li").unbind("click");
		$("#jobAge_span li").click(function(){
			var thiss=$(this);
			var data=thiss.attr("data");
			$("#jobAge").val(data);
	        $("#year_text").text(thiss.text());
	        thiss.addClass("li_text_active").siblings().removeClass("li_text_active");
	        $("#jobAge_span").hide();
		});
	});
	//工作年限回显
	if($("#jobAge").val()!=""){
		$("span[id=jobAge_span]").each(function(){
			if($(this).children("span").attr("data") == $("#jobAge").val()){
				$(this).find("img").attr("src",ctx+"/res/images/radio_check_icon.png");
			}
		});
	}
	//可分配时长选择事件
	$("span[id=duration_span]").click(function(){
		$("#duration_span >img").attr("src",ctx+"/res/images/radio_empty_icon.png");
		var thiss=$(this);
		thiss.find("img").attr("src",ctx+"/res/images/radio_check_icon.png");
		var numKey=thiss.find("span").attr("data");
		$("#allotDuration").val(numKey);
	});
	//可分配时长回显
	if($("#allotDuration").val()!=""){
		$("span[id=duration_span]").each(function(){
			if($(this).children("span").attr("data") == $("#allotDuration").val()){
				$(this).find("img").attr("src",ctx+"/res/images/radio_check_icon.png");
			}
		});
	}
	//是否在职事件
	$(".is-work button").click(function(){
		$(".is-work button").removeClass("active-btn");
		$(this).addClass("active-btn");
		if($(this).attr("data")=='Y'){
			$("#onjobed").val(true);
		}else{
			$("#onjobed").val(false);
		}
	});
	//是否在职回显
	$(".is-work button").each(function(){
		var data = $(this).attr("data");
		var str = $.trim($("#onjobed").val());
		//$(".is-work button").removeClass("active-btn");
		if(str=="false"){
			$("#nBtn").addClass("active-btn");
		}else{
			$("#yBtn").addClass("active-btn");
		}
	});
	//邀请码输入框事件
	$(".number-table input").keyup(function(){
		if($(this).attr("index")!='1'){
			var preInput=$(this).parents('td').prev('td').find("input");
			if(preInput.val()==""){
				preInput.focus();
				return false;
			}
		}
		if($(this).val()==""){
			$(this).focus();
		}else{
			var nextInput=$(this).parents('td').next('td').find("input");
			nextInput.focus();
		}
		
	});
	//下一步
	$("#saveBtn").click(function(){
		var jobAge=$.trim($("#jobAge").val());
		var allotDuration=$.trim($("#allotDuration").val());
		var onjobed=$.trim($("#onjobed").val());
		var inviteCode="";
		$(".number-table input").each(function(){
			inviteCode+=$(this).val();
		});
		if(selectKey==""||selectCount==0){
			if(selectKey==""){
				tips("selectKey","请选择至少一个胜任角色");
			}else if(selectCount==0){
				tips("selectSecond","请选择您的二级角色");
			}
			return false;
		}else if(selectCount>maxCount){
			tips("selectSecond","技能选择不能超过"+maxCount+"个");
			return false;
		}else if(jobAge==""){
			tips("jobAge","请选择您的工作年限");
			return false;
		}else if(allotDuration==""){
			tips("allotDuration","请选择您每周可分配的时长");
			return false;
		}else if(inviteCode!=""&&inviteCode.length!=6){
			tips("inviteCode","请输入六位数的邀请码");
			return false;
		}
		var jobMap = new Map();
		jobMap.put(selectKey,selectArray);
		$("#eliteJobMaps").val(jobMap.toString());
		
		$("#inviteCode").val(inviteCode)
		jsonAjax.post({
			url:ctx+'/member/elite/current/save',
			data:$("#personFrom").serialize(),
			success:function(data){
				if(data.result=="SUCCESS"){
					window.location.href=ctx+'/member/basic?'+jsonAjax.random();
				}else{
					tips("allotDuration",data.msg);
				}
			}
		});
	});
	$("#skip").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
});