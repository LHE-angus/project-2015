<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<script type="text/javascript"> !window.jQuery && document.write('<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"><\/script >'); </script>  
<script type="text/javascript"> !window.jQuery && document.write('<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"><\/script >'); </script>
<script type="text/javascript">
String.prototype.repeat		= function(size){ return size === undefined ? "" : new Array(size + 1).join(this); };
String.prototype.leftPad	= function(size, padChar){ return padChar.repeat(size).concat(this); };
String.prototype.rightPad	= function(size, padChar){ return this.concat(padChar.repeat(size)); };
String.prototype.trim		= function(chr){ return this.replace((!chr) ? new RegExp('^\\s+|\\s+$', 'g') : new RegExp('^' + chr + '+|' + chr + '+$', 'g'), ""); };
String.prototype.startsWith	= function(prefix){ return this.indexOf(prefix) === 0; };
String.prototype.endsWith	= function(suffix){ return !!suffix && this.length >= suffix.length && this.slice(-1 * suffix.length) == suffix; };

Frame = {
	mainFrameset : top.document.getElementById("mainFrameset"),
	topFrameset : top.document.getElementById("topFrameset"),
	lrFrame : top.document.getElementById("lrFrame"),
	zoomOut : function(){
		if (!this.mainFrameset || !this.mainFrameset.cols) return;
		this.mainFrameset.cols = '196,8,*';
		this.topFrameset.rows = '142,*';
		
		var switchImage = this.lrFrame.contentDocument.getElementById("switchImage");
		if (switchImage) switchImage.src = "${ctx}/commons/scripts/jqztree/styles/green/switch/s_0.png";
	},
	zoomIn : function(full){
		if (!this.mainFrameset || !this.mainFrameset.cols) return;
		full = full || false;
		this.mainFrameset.cols = full ? '0,0,*' : '0,8,*';
		this.topFrameset.rows = '0,*';

		var switchImage = this.lrFrame.contentDocument.getElementById("switchImage");
		if (switchImage) switchImage.src = "${ctx}/commons/scripts/jqztree/styles/green/switch/s_1.png";
	},
	toggle : function(){
		this.topFrameset.rows == '0,*' ? this.zoomOut() : this.zoomIn();
	}
};

/**
 * A simple overlay tip box.
 * @Author Xing,Xiudong
 * @Date 2013-1-24
 */
$.extend({
	overlay : function(options){
		var opts = $.extend({
			zindex : 99999,
			width : 300,
			height : 140
		}, options);

		this.initialize = function(){
			if (!!this.$overlay) return this;
			var html = [];
			html[html.length] = "<div id=\"overlay-" + new Date().getTime() + "\" style=\"z-index:" + opts.zindex + ";position:absolute;display:none;width:100%;height:" + Math.max($(top).height(), $(document).height()) + "px;margin:0px;padding:0px;background-color:#000;top:0px;left:0px;text-align:center;\">";
			html[html.length] = "<div id='tip-msg-" + new Date().getTime() + "' style='width:" + opts.width + "px;height:" + opts.height + "px;border:10px solid #333;border-radius:5px;background:#FFF;text-align:left;color:#000;padding:10px;margin:200px auto 0px auto;'></div>";
			html[html.length] = "</div>";
			this.$overlay = $(html.join('')).appendTo("body");
			this.$tipmsg = this.$overlay.children(":first-child");
			return this;
		};

		this.hide = function(){
			if (!!this.$overlay && this.$overlay.is(":hidden")) return;
			$("body").css('overflow', 'auto');
			this.$overlay.fadeOut();
			this.$tipmsg.empty();
			this.$focus && this.$focus.focus();
			return this;
		};
		
		this.show = function(html){
			if (!!this.$overlay && this.$overlay.is(":visible")) {
				this.$tipmsg.html(html);
				return;
			}
			$("body").css('overflow', 'hidden');
			this.$overlay.fadeTo(500, 0.85);
			this.$tipmsg.html(html);
			this.$focus = $("*:focus");
			this.$focus.blur();
			return this;
		};
		
		return this.initialize();
	}
});
</script>
<script type="text/javascript">
$(function(){
	if ($.browser.msie && $.browser.version == "6.0") {
		var hs = [];
		hs[hs.length] = "<span style='color:#F00;font-weight:800;'>很抱歉，您的浏览器（IE6.0）不被支持！</span>";
		hs[hs.length] = "请<a href='http://windows.microsoft.com/zh-CN/internet-explorer/download-ie' target='_blank'>下载并安装最新的  Internet Explore 浏览器</a>。";
		hs[hs.length] = "建议您<a href='http://windows.microsoft.com/zh-CN/internet-explorer/download-ie' target='_blank'>下载并安装最新32位版本的 Internet Explore 9 浏览器</a>，可获得更佳的用户体验。";
		$.overlay().show(hs.join("<br/><br />　　"));
		
		$(".mian_mian").remove();
		$(".mian_mian1").remove();
		
		return;
	}
});
</script>
<noscript>
<div>
您好，您的浏览器禁用了脚本，为了保障更佳的用户体验和安全性，本页面采用了大量的脚本，建议你采取下面的步骤开启脚本功能。<br />
步骤：Internet选项 -&gt; 安全 -&gt; 自定义级别（或者选择恢复到“默认级别”点击确定即可） -&gt; 脚本 -&gt; 活动脚本 -&gt; 选择“启用” -&gt; 确定。
</div>
</noscript>