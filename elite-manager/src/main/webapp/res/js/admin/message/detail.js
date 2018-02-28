$(function(){
    detail.init();
});

var detail = {
    init : function() {
        $('#remove').click(detail.remove);
        $('#backMessageList').click(detail.back);
    },
    remove : function() {
        var id = $("#remove").val();
        $.confirm({
            title : "提示",
            content : "您确定要删除",
            iconSmall : "icon-warning-sign",
            callback : function() {
                jsonAjax.post({
                    url : ctx + "/message/remove",
                    data : {messageId : id},
                    success : function(data) {
                        if (BASE.JS_RESULT.SUCCESS == data.result) {
                            $.tips({
                                content : BASE.JS_RESULT.SUCCESS_DELETED
                            });
                            setTimeout('window.location = "/message/list"',1000);
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
    back:function(){
    	window.location = ctx+"/message/list";
    }
};