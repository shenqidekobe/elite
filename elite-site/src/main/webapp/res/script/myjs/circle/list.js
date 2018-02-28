require([ "jquery", "ajax", "jsonAjax","commons"], function($) {
	
	$("#search").click(function(){
		var text=$.trim($("#searchText").val());
		$("#keyword").val(text);
		loadEliteCircleList();
	});
	$("#searchText").keyup(function(){
		var reg=/[`~！!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;  
        if(reg.test($(this).val())){  
        	$(this).val('');
            $(this).focus();  
            return false;
        }  
	});
	$('body').keydown(function(event) {
		if (event.keyCode == 13){
			$("#search").click();
		}
	});
	//角色选择
	$("#roleUl li").click(function(){
		var thiss=$(this);
		if(thiss.hasClass("selected")){
			return false;
		}
		$("#roleUl li").removeClass("selected");
		thiss.addClass("selected");
		var data=thiss.attr("data");
		$("#role").val(data);
		loadEliteCircleList();
	});
	//工作年限选择
	$("#jobageUl li").click(function(){
		var thiss=$(this);
		if(thiss.hasClass("selected")){
			return false;
		}
		$("#jobageUl li").removeClass("selected");
		thiss.addClass("selected");
		var data=thiss.attr("data");
		$("#jobAge").val(data);
		loadEliteCircleList();
	});
	//分配时长选择
	$("#durationUl li").click(function(){
		var thiss=$(this);
		if(thiss.hasClass("selected")){
			return false;
		}
		$("#durationUl li").removeClass("selected");
		thiss.addClass("selected");
		var data=thiss.attr("data");
		$("#duration").val(data);
		loadEliteCircleList();
	});
	//行业选择
	var industryVal="";
	$("#industryUl li").click(function(){
		var thiss=$(this);
		var data=thiss.attr("data");
		if(industryVal.split(",").length < 8){
			if(thiss.hasClass("selected")){
				thiss.removeClass("selected");
				industryVal=subStr(industryVal,data, ",");
				thiss.find(".close_btn").hide();
			}else{
				thiss.addClass("selected");
				industryVal=addStr(industryVal,data, ",");
				thiss.find(".close_btn").css({"display":"inline"});
			}
		}else{
			thiss.removeClass("selected");
			industryVal=subStr(industryVal,data, ",");
			thiss.find(".close_btn").hide();
		}
		if(data==""){
			industryVal="";
			$("#industryUl li").removeClass("selected");
			$("#industryUl li .close_btn").hide();
			$("#industryAll").addClass("selected");
			$("#industryAll .close_btn").show();
		}else{
			if(industryVal!=""){
				$("#industryAll").removeClass("selected");
				$("#industryAll .close_btn").hide();
			}else{
				$("#industryUl li").removeClass("selected");
				$("#industryUl li .close_btn").hide();
				$("#industryAll").addClass("selected");
				$("#industryAll .close_btn").show();
			}
		}
		$("#industry").val(industryVal);
		loadEliteCircleList();
	});
	//显示更多行业
	$("#industryMore").click(function(){
		$(".moreLi").toggle();
	});
	function loadEliteCircleList(){
		var text=$.trim($("#searchText").val());
		$("#keyword").val(text);
		jsonAjax.post({
			url:ctx+'/circle/listData',
			data:$("#circleForm").serialize(),
			success:function(data){
				$("#dataList").html(data);
				$(".imgBox,.skill_name").click(view);
				$(".attention_btn").click(attention);
				//绑定分页事件
				pagination(loadEliteCircleList);
				
				$("body,html").animate({
			       scrollTop: 440
			    }, 1000);
			}
		});
	}
	//查看详情
	function view(){
		var id=$(this).attr("data");
		var href=ctx+'/circle/view/'+id+'?'+jsonAjax.random();
		window.open(href);
	}
	//关注
	function attention(){
		var thiss=$(this);
		var id=thiss.attr("data");
		var oper=thiss.attr("oper");
		jsonAjax.post({
			url:ctx+'/member/attendtion/addOrRemoveAttention',
			data:{"type":oper,"attentionMemberId":id},
			success:function(data){
				if(data.result=="SUCCESS"){
					var count=thiss.prev().text();
					if(oper=="yes"){
						thiss.css({"background":"#999"});
						thiss.text("已关注");
						thiss.attr("oper","remove");
						count=parseInt(count)+1;
					}else{
						thiss.css({"background":"#fea600"});
						thiss.text("关注");
						thiss.attr("oper","yes");
						count=parseInt(count)-1;
					}
					thiss.prev().text(count);
				}else{
					tostHint("关注失败",data.msg);
				}
			}
		});
	}
	//分页处理
	function hallPaging(){
		$(".arrow_l_big").click(prev);
		$(".arrow_r_big").click(next);
	}
	function prev(){
		var currentPageth = $("#pager_pageth").val();
		if (currentPageth - 0 <= 1) {
			return;
		}
		$("#pager_pageth").val(currentPageth - 1);
		loadEliteCircleList();
	}
    function next(){
    	var currentPageth = $("#pager_pageth").val();
		if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
			return;
		}
		$("#pager_pageth").val(currentPageth - 0 + 1);
		loadEliteCircleList();
	}
	loadEliteCircleList();
});