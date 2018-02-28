function setUploadField(el, opt) {
    var fn = new Date().getTime();
    var defaults = {type: 'image',width:640};
    opt = $.extend({}, defaults, opt);
    var field = opt.field;
    var type = opt.type;
    var path = opt.path;
    var value = opt.value;
    var onChange = opt.onChange;
    var pbar = opt.bar;
    var filePath = path.toString().replace(new RegExp('\\.',"g"), '/');
    var pid='picker_'+new Date().getTime();
    opt.imageTpl =
        '<div>' +
        '   <input type="hidden"  name="' + field + '" value="' + value + '" />' +
        '	<a name="a_images" data-rel="colorbox" style="display: none;">'+
        '	    <img name="img_images" style="width: 100%;"/>'+
        '	</a>'+
        '	<div class="tools tools-right" style="display: none;">'+
        '	    <a name="btn_delete">'+
        '	        <i class="ace-icon fa fa-times red"></i>'+
        '	    </a>'+
        '	</div>'+
        '   <div>' +
        '      <div id="' + pid + '"  style="float: left;"></div>' +
        '   </div>' +
        '</div>';
    opt.videoTpl =
        '<div>' +
        '    <input type="hidden"  name="' + field + '" value="' + value + '" />' +
        '    <div style="max-width:120px;">' +
        '       <div name="vc"></div>' +
        '		<div class="progress progress-mini" style="display: none;width:120px;position: absolute;">'+
        '			<div class="progress-bar progress-success" style="width: 0%;"/>'+
        '		</div>'+
        '    </div>' +
        '    <div>' +
        '        <div id="' + pid + '"  style="float: left;"></div>' +
        '    </div>' +
        '</div>';
    var opts = {
        auto: true,
        swf: ctx + '/static/webuploader/Uploader.swf',// swf文件路径
        pick: '#' + pid,
        resize: false,// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        formData: {fn: fn, autoExt: '1'}
    }
    opts.server = uploadUrl + "/" + path + "/addbaidu";
    if (type == 'image') {
        opts.accept = {title: '图片', extensions: 'gif,jpg,jpeg,png', mimeTypes: 'image/*'};
        if (field == 'poster') {
            opts.server = opts.server + "/poster";
        }
    } else if (type == 'video') {
        opts.accept = {title: '视频', extensions: 'flv,mp4,f4v', mimeTypes: 'video/*'}
    }
    function getUrl(fnm){
        var url='';
        if (type == 'video') {
            url = uploadShowUrl + "/" + filePath + "/" + fnm;
        } else if (type == 'poster') {
            url = uploadShowUrl + "/" + filePath + "/poster";
        } else {
            url = uploadShowUrl + "/" + filePath + "/" + fnm;
        }
        return url;
    }
    function setVideo(url) {
        var vc=$(el).find('div[name="vc"]');
        vc.html('');
        vc.attr('id','vc'+Math.random());
        var flash = {
            f: url,
            c: '0',//是否读取文本配置,0不是，1是
            e: '5',//视频结束后的动作，0是调用js函数，1是循环播放，2是暂停播放并且不调用广告，3是调用视频推荐列表的插件，4是清除视频流并调用js功能和1差不多，5是暂停播放并且调用暂停广告
            p: '0'//视频默认0是暂停，1是播放，2是不加载视频
        };
        var params = {bgcolor: '#FFF', allowFullScreen: true, allowScriptAccess: 'always'};//这里定义播放器的其它参数如背景色（跟flashvars中的b不同），是否支持全屏，是否支持交互
        var video = [url + '->video/mp4'];
        if(height==null){
            height=width*9/16;
            $(el).css('height',height+'px');
        }
        CKobject.embed(ctx + '/static/ckplayer6.6/ckplayer.swf', vc.attr('id'), vc.attr('id'), width, height, false, flash, video, params);
    }
    $(el).html($(opt[type + 'Tpl']).html());
    if(type=='video' && value!=null && value!=''){
        $(el).find('input[name="' + field + '"]').val(value);
        setVideo(uploadShowUrl + "/" + filePath + "/" + value);
    }
    var uploader = WebUploader.create(opts);
    uploader.on('uploadComplete', function (file) {
        uploader.reset();
        progress.hide();
        var fnm = fn + '.' + file.ext;
        opt.name = fn;
        opt.ext = file.ext;
        opt.fullName = fnm;
        if (type == 'video') {
            url = uploadShowUrl + "/" + filePath + "/" + fnm;
            $(el).find('input[name="' + field + '"]').val(fnm);
            setVideo(url);
        } else if (type == 'poster') {
            url = uploadShowUrl + "/" + filePath + "/poster";
            var img = $('#img_poster');
            img.attr("src", url + '?_rd=' + Math.random());
            img.show();
            $('#hidden_poster').val("poster");
        } else {
            url = uploadShowUrl + "/" + filePath + "/" + fnm;
            $(el).find('img[name="img_images"]').attr("src", url).show();
            $(el).find('.tools').show();
            $(el).find('.tools a[name="btn_delete"]').click(function(){
                $(el).remove();
                if (onChange != null)
                    onChange(opt);
            });
            $(el).find('input[name="' + field + '"]').val(fnm);
            var a_images = $(el).find('a[name="a_images"]');
            a_images.attr('href',url);
            a_images.show();
            $(el).find('.tools').next().remove();
            a_images.click(function(){
                //$(this).find('img').clone().removeAttr('style').css('max-width',$('.page-content').width()*0.75+'px').lightbox_me({centered: true, closeClick: true,destroyOnClose:true});
            });
            var colorbox_params = {
                rel: 'colorbox',
                reposition:true,
                scalePhotos:true,
                scrolling:false,
                previous:'<i class="ace-icon fa fa-arrow-left"></i>',
                next:'<i class="ace-icon fa fa-arrow-right"></i>',
                close:'&times;',
                current:'{current} of {total}',
                maxWidth:'100%',
                maxHeight:'100%',
                onOpen:function(){
                    $overflow = document.body.style.overflow;
                    document.body.style.overflow = 'hidden';
                },
                onClosed:function(){
                    document.body.style.overflow = $overflow;
                },
                onComplete:function(){
                    $.colorbox.resize();
                }
            };

            a_images.colorbox(colorbox_params);
            var li_new_id='li_'+new Date().getTime();
            $(el).parents('ul').append('<li id="'+li_new_id+'"></li>');
            setUploadField('#'+li_new_id,opt);
        }
        if (onChange != null)
            onChange(opt);
    });
    var progress=$(pbar);
    var bar=progress.find('.progress-bar');
    uploader.on('uploadStart', function (file) {
        fn = new Date().getTime();
        uploader.option('formData', {fn: fn, autoExt: '1'});
        progress.show();
        bar.css({width:0});
    });
    uploader.on('uploadProgress', function (file, percentage) {
        bar.css('width',percentage*100+'%');
    });
    uploader.on('uploadError', function (file) {
        progress.hide();
        uploader.reset();
    });
    uploader.on('error', function (type) {
        uploader.reset();
        progress.hide();
        if (type == "Q_TYPE_DENIED") {
            bootbox.alert('不支持此类型文件');
        } else {
            bootbox.alert('上传失败');
        }
    });
}