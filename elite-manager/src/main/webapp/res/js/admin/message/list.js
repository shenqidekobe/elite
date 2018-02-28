$(function(){
    list.init();
});

var list = {
    init : function() {
        list.search();
        $('#remove').click(list.remove);
        $('#markRead').click(list.markRead);
        $('#allUnread').click(list.allUnread);
        $('#allread').click(list.allread);
    },
    search : function() {
        jsonAjax.post({
            url : ctx + '/message/listData',
            data : $('#listForm').serialize(),
            success : function(data) {
                $('#listData').html(data);
                $('#markAll').change(list.markAll);
                $('#messageBox tr td').click(function(){
                	if($(this).index!=0){
                		var id=$(this).parent().attr("data");
                		$("#inputId").val(id);
                		list.viewDetail();
                	}
                	
                });
            }
        });
    },
    markAll : function() {
        $("input:checkbox").prop('checked',$(this).prop("checked"));
      
    },
    messageId : function() {
        return $('input[name="messageId"]').serialize();
    },
    remove : function() {
    	  var ids = list.messageId();
          if(ids.length==0){
          	 $.tips({
                   content : "请选择要删除的信息"
               });
          	 return;
          }
        $.confirm({
            title : "提示",
            content : "您确定要删除",
            iconSmall : "icon-warning-sign",
            callback : function() {
                jsonAjax.post({
                    url : ctx + "/message/remove",
                    data : ids,
                    success : function(data) {
                        if (BASE.JS_RESULT.SUCCESS == data.result) {
                            list.search();
                        }else{
                            $.tips({
                                content : data.msg
                            });
                        }
                    }
                });
            }
        });
    },
    markRead : function() {
        var ids = list.messageId();
        if(ids.length==0){
        	 $.tips({
                 content : "请选择要标记的信息"
             });
        	 return;
        }
        $.confirm({
            title : "提示",
            content : "您确定要标记为已读",
            iconSmall : "icon-warning-sign",
            callback : function() {
                jsonAjax.post({
                    url : ctx + "/message/markRead",
                    data : ids,
                    success : function(data) {
                        if (BASE.JS_RESULT.SUCCESS == data.result) {
                            list.search();
                        }else{
                            $.tips({
                                content : data.msg
                            });
                        }
                    }
                });
            }
        });
    },
    allUnread: function() {
        jsonAjax.post({
            url : ctx + '/message/listData',
            data : $('#listForm').serialize()+"&unread=true",
            success : function(data) {
                $('#listData').html(data);
                $('#markAll').change(list.markAll);
                $('#messageBox tr td').click(function(){
                	if($(this).index!=0){
                		var id=$(this).parent().attr("data");
                		$("#inputId").val(id);
                		list.viewDetail();
                	}
                	
                });
            }
        });
    },
    allread: function() {
    	jsonAjax.post({
    		url : ctx + '/message/listData',
    		data : $('#listForm').serialize()+"&unread=false",
    		success : function(data) {
    			$('#listData').html(data);
    			$('#markAll').change(list.markAll);
    			   $('#messageBox tr td').click(function(){
                   	if($(this).index!=0){
                   		var id=$(this).parent().attr("data");
                   		$("#inputId").val(id);
                   		list.viewDetail();
                   	}
                   	
                   });
    		}
    	});
    },
    viewDetail:function(){
		$('#detailForm').attr('action', ctx + "/message/detail").submit();
    }
};