require([ "jquery", "ajax", "jsonAjax", "md5","commons"],function($) {
	//面包屑地址
	$("#index").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	//类型和状态切换
	loadMessage('system');
	var currentType='system';
	$(".mbox-left li").click(function(){
		var thiss=$(this);
		var type=thiss.attr("data");
		if(type=="system"){
			thiss.find(".img_span").removeClass("system").addClass("system1");
			$(".mbox-left li:nth-child(2)").find(".img_span").removeClass("project1").addClass("project");
			$(".mbox-left li:nth-child(3)").find(".img_span").removeClass("activity1").addClass("activity");
			$(".project-btn").hide();
		}
		else if(type=="project"){
			thiss.find(".img_span").removeClass("project").addClass("project1");
			$(".mbox-left li:nth-child(1)").find(".img_span").removeClass("system1").addClass("system");
			$(".mbox-left li:nth-child(3)").find(".img_span").removeClass("activity1").addClass("activity");
			$(".project-btn").hide();
		}
		else if(type=="activity"){
			thiss.find(".img_span").removeClass("activity").addClass("activity1");
			$(".mbox-left li:nth-child(1)").find(".img_span").removeClass("system1").addClass("system");
			$(".mbox-left li:nth-child(2)").find(".img_span").removeClass("project1").addClass("project");
			$(".project-btn").hide();
		}
		
	
		var imgCss=thiss.attr("imgCss");
		$("#type").val(type);
		$(".mbox-left li ").find(".line").removeClass("active_line");
		$(".mbox-left li a").removeClass("active_color");
		thiss.find(".line").addClass("active_line");
		thiss.find("a").addClass("active_color");
		
		loadMessage(type);
	});
	function loadMessage(type){
		type=type||'system';
		$("#msgBar").show();
		jsonAjax.post({
			url:ctx+'/member/message/list',
			data:{type:type},
			success:function(html){
				$("#dataList").html(html);
				$("#type").val(type);
				$("#titleSpan").text($(".mbox-left li").find(".activ").text());
				loadMessageData();
				if(type=="project"){
					$(".project-btn").unbind('click');
					console.info("click");
					$(".project-btn").click(function(e){
						if(e.target.nodeName=='UL'||e.target.nodeName=='LI'||e.target.nodeName=='A'){return false;}
						$(".proect").show();
						$(".proect li").unbind("click");
						$(".proect li").click(function(){
							var data=$(this).attr("data");
							$("#pid").val(data);
							var name=$(this).text();
							if(name.length>8){
								name=name.substring(0,8);
							}
							$("#proect_name").text(name);
							$(".proect").hide();
							loadMessageData(examineUnreadMessage());
						});
					});
				}
				$(".message-select").click(function(){
					$("#type").val(type);
					var data=$(this).attr("data");
					$(".message-select").removeClass("current-select");
					$(this).addClass("current-select");
					$("#status").val(data);
					loadMessageData();
				});
				currentType=type;
			}
		});
	}
	function loadMessageData(callback){
		jsonAjax.post({
			url:ctx+'/member/message/listData',
			data:$("#messageForm").serialize(),
			success:function(html){
				$("#dataSecondList").html(html);
				$(".message-frame").click(viewMessage);//查看消息详情
				
				//分页组件js
				pagination(loadMessageData);
				
				if(callback)
					callback();
			}
		});
	}
	function viewMessage(){
		var id=$(this).attr("data");
		jsonAjax.post({
			url:ctx+'/member/message/'+id+'/view',
			success:function(html){
				$("#msgBar").hide();
				$("#dataSecondList").html(html);
				//examineUnreadMessage();
				//loadMessage(currentType);
			}
		});
		$('html, body').animate({scrollTop: 0}); 
	}
	function loadMyProjectList(){
		jsonAjax.post({
			url:ctx+'/project/company/myprojects',
			success:function(data){
				for(var i in data){
				   console.info(data[i]);	
				}
			}
		});
	}
	function examineUnreadMessage(){
		var projectId=null;
		if(currentType=="project"){
			projectId=$("#pid").val();
		}
		jsonAjax.post({
			url:ctx+'/member/message/examine',
			data:{type:currentType,projectId:projectId},
			success:function(data){
				var typeUnread=data.typeUnread;
				var typeRead=data.typeRead;
				var unreadCount=data.unreadCount;
				var messageCount=data.messageCount;
				if(parseInt(unreadCount)==0){
					$("#message_btn div>span").removeClass("point");
				}
				$("#messageCount").text(messageCount);
				$("#unreadCount").text(typeUnread);
				$("#readCount").text(typeRead);
				$("#head_unread").text(typeUnread);
			}
		});
	}
});
