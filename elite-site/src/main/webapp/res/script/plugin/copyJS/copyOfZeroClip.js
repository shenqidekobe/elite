var clip = new ZeroClipboard(document.getElementById("d_clip_button"), {
  moviePath: "ZeroClipboard.swf"
});

// 复制内容到剪贴板成功后的操作
clip.on('complete', function(client, args) {
   alert("复制成功，复制内容为："+ args.text);
} );

