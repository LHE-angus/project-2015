/**
 * version: 1.0
 * 文件附件添加和回显用
 */
// 创建一个闭包  
(function($) {  
  // 插件的定义  
  $.fn.imgshow = function(options) {
     //debug(this);
    // build main options before element iteration  
    var opts = $.extend({}, $.fn.imgshow.defaults, options);  
    // iterate and reformat each matched element  
    return this.each(function() {  
      $this = $(this);
      // build element specific options  
      var o = $.meta ? $.extend({}, opts, $this.data()) : opts;  
      // update element styles  
	 
     /** $this.css({  
        backgroundColor: o.background,  
        color: o.foreground  
      });
      **/
	  //测试
      var markup = $this.html();  
      // call our format function  
      markup = $.fn.imgshow.format(markup);  
      $this.empty();
      if(opts.isAdd){
    	  $this.append(addImgs(opts));//创建附件添加
      }
      $this.append(show(opts));//显示已有照片
    });  
  }; 
  
   // 添加新的附件
  function addImgs(opts) {
	var imgtr=$('<div id="uploading"></div>');
	var img_ol=$('<ol></ol>');
	var adddiv=$('<div style="float:right;"></div>');
	var addimg=$('<a href="javascript:void(0);" style="margin-left:250px"><img src="'+opts.ctx+'/commons/styles/admin/images/_+.gif"/></a>').click(function () {
		    if(opts.isChangeParent){resizeFrameHeight();}
		    var img_li=$('<li><input name="activity_policy'+Math.ceil(Math.random()*1000000)+'" type="file" id="activity_policy" style="width: 250px;" /></li>');
		    var imgdel=$('<a>删除</a>').click(function () {
		    	$(this).parent().remove();
		    	 if(opts.isChangeParent){resizeFrameHeight();}
			});
		    img_li.append(imgdel);
		    img_ol.append(img_li);
	  });
	adddiv.append(addimg);
	imgtr.append(addimg);
	imgtr.append(img_ol);
	return imgtr;
  }
   // 回显已有附件 
  function show(opts) {
     var  dlist=opts.data;
     var    ctx=opts.ctx;
     var delUrl=opts.delUrl;
	 //必须是数组
	  if(!(dlist&&$.isArray(dlist)))return;
      var img_ol=$('<ol></ol>');
       $.each(dlist,function(i, n){
          var img_li=$('<li></li>');
		      img_li.append('<a href="'+ctx+"/"+n.save_path+'" target="_blank">'+n.file_name+'<a/>');
		   var img_del=$('<a href="javascript:void(0)" delval="'+n.id+'"> 删除<a/>').click(function () {
		      //删除图片
			  var delv=$(this).attr('delval');
			  var a = this;
			  if (!delUrl) {
		    		alert("只能查看");
					return;
				}
			  var a = this;
			  if(confirm('确实要删除此附件？')){
				    $.ajax({
			    	   type: "POST",
			    	   url: delUrl,
			    	   data: "&id="+n.id,
			    	   success: function(result){
				    	 if(result=="success"){
		    		    	  alert( "恭喜您，删除附件成功! ");
							  $(a).parent().remove();	   
		    		       }
			    	   },
			    	   error:function(XMLHttpRequest, textStatus, errorThrown) {
			    		    alert("删除异常");
			    		}
			       });
			  }else{
			    return;
			  }
		   });
		   img_li.append(img_del);
		   img_ol.append(img_li);
		});
	return img_ol;
  }
  
  // 私有函数：debugging  
  function debug($obj) {  
    if (window.console && window.console.log)  
      window.console.log('imgshow selection count: ' + $obj.size());  
  }; 
  //处理特殊影响显示的字符
	function HTMLEncode(text){
		text = text.replace(/"/g, "&#34;")
				.replace(/'/g, "&#35;")
				.replace(/[$]/g, "&#36;")
				.replace(/</g, "&#60;")
				.replace(/>/g, "&#62;")
				.replace(/\\/g, "&#92;")
				.replace(/[\n]/g,"\\n")
				.replace(/[\r]/g,"\\r");
      return text;
 	}
  //自动改变外层框架的大小
  function resizeFrameHeight(offset, min_height) {
		// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
		$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
  }
  // 定义暴露format函数  
  $.fn.imgshow.format = function(txt) {  
    return '<strong>' + txt + '</strong>';  
  };
  // 插件的defaults  
  $.fn.imgshow.defaults = {  
    foreground: 'red',  
    background: 'yellow',
    isAdd:true,
    isChangeParent:false
  };  
// 闭包结束  
})(jQuery);   
