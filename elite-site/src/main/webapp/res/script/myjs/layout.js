require([ "jquery", "bootstrap", "ajax", "jsonAjax" ], function($) {
	
	initNav();
	function initNav() {
		var picpath = $("#picpath").val();
		if (picpath == "") {
			$(".head").css("background-image",
					"url(" + ctx + "'/res/images/pic_shadow2.png')");
		}
	}
	
	//判断浏览器是否支持placeholder属性
    supportPlaceholder='placeholder'in document.createElement('input');
        placeholder=function(input){
            var text = input.attr('placeholder'),
                defaultValue = input.defaultValue;

            if(!defaultValue){
                input.val(text).addClass("phcolor");
            }

            input.focus(function(){
                if(input.val() == text){
                    $(this).val("");
                }
            });

            input.blur(function(){
                if(input.val() == ""){
                    $(this).val(text).addClass("phcolor");
                }
            });

            //输入的字符不为灰色
            input.keydown(function(){
                $(this).removeClass("phcolor");
            });
        };

    //当浏览器不支持placeholder属性时，调用placeholder函数
    if(!supportPlaceholder){
        $('input').each(function(){
            text = $(this).attr("placeholder");
            if($(this).attr("type") == "text"){
                placeholder($(this));
            }
        });
    }
	
	
	function banBackSpace(e){     
	    var ev = e || window.event;
	    var obj = ev.target || ev.srcElement;
	    var t = obj.type || obj.getAttribute('type');
	    var vReadOnly = obj.getAttribute('readonly');  
	    var vEnabled = obj.getAttribute('enabled');  
	    vReadOnly = (vReadOnly == null) ? false : vReadOnly;//处理null值情况  
	    vEnabled = (vEnabled == null) ? true : vEnabled;  
	    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
	    //并且readonly属性为true或enabled属性为false的，则退格键失效  
	    var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea"|| t=="email"|| t=="tel"|| t=="number")   
	                && (vReadOnly==true || vEnabled!=true))?true:false;  
	    var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea"&& t != "email"&& t != "tel"&& t != "number")  
	                ?true:false;          
	    if(flag2){  
	        return false;  
	    }  
	    if(flag1){     
	        return false;     
	    }     
	}  
	//禁止后退键 作用于Firefox、Opera  
	document.onkeypress=banBackSpace;  
	//禁止后退键  作用于IE、Chrome  
	document.onkeydown=banBackSpace;  

});