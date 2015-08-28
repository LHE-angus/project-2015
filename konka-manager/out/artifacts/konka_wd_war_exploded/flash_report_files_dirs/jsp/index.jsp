<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request['contextPath']}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图形报表</title>
</head>
<body title="双击放大图表" style="padding:0px;margin:0px;">
<script type="text/javascript" src="${ctx}/${static_files_dir}/FusionCharts.js"></script>
<!-- 处理图像高度和宽度 和数据的URL拼接-->
<script type="text/javascript">    

    // 请求需要的高度宽度
	var width = "${width}";
	var height = "${height}"; 

	// 页面高度宽度
	var winWidth;
	var winHeight;
	if("" == width || "" == height){
		//获取窗口宽度 
		if (window.innerWidth) winWidth = window.innerWidth; 
		else if ((document.body) && (document.body.clientWidth)) winWidth = document.body.clientWidth; 
		//获取窗口高度 
		if (window.innerHeight) winHeight = window.innerHeight; 
		else if ((document.body) && (document.body.clientHeight)) winHeight = document.body.clientHeight; 
		//通过深入Document内部对body进行检测，获取窗口大小 
		if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) { 
			winHeight = document.documentElement.clientHeight; 
			winWidth = document.documentElement.clientWidth; 
		}

		if("" == width) width = winWidth;
		if("" == height) height = winWidth;
	} 
	// 处理数据的RUL
	var sourceDataUrl = escape("${dataUrl}");
	var trendlines = encodeURIComponent("${trendlines}");
	var dataUrl = "${www_url}?chartMethod=getData&colspan=${colspan}&type=${type}&showBorder=${showBorder}&decimals=${decimals}&unitSite=${unitSite}&formatNumberScale=${formatNumberScale}&trendlines=" + trendlines + "&dataUrl=" + sourceDataUrl + "&t=" + new Date().getTime();
</script>
 
<!-- 单柱状，多柱状图 -->
<c:if test="${type eq 'h'}">
 <div style="margin:0 auto;">
 	<div id="mSColumn3D" align="center"> </div>
    <script type="text/javascript">
	   var chart = new FusionCharts("${ctx}/${static_files_dir}/swf/MSColumn3D.swf", "MSColumn3DId", width, height, "0", "0");
	   chart.setDataURL(dataUrl);
	   chart.render("mSColumn3D");
    </script>
 </div>
</c:if>

<!-- 单折现，多折线图 -->
<c:if test="${type eq 'l'}">
 <div style="margin:0 auto;">
 	<div id="mSLine" align="center"> </div>
    <script type="text/javascript">
	   var chart = new FusionCharts("${ctx}/${static_files_dir}/swf/MSLine.swf", "MSLineId", width, height, "0", "0");
	   chart.setDataURL(dataUrl);
	   chart.render("mSLine");
    </script>
 </div>
</c:if>

<!-- 饼状图 -->
<c:if test="${type eq 'p'}">
 <div style="margin:0 auto;">
 	<div id="pie3D" align="center"> </div>
    <script type="text/javascript">
	   var chart = new FusionCharts("${ctx}/${static_files_dir}/swf/Pie3D.swf", "Pie3DId", width, height, "0", "0");
	   chart.setDataURL(dataUrl);
	   chart.render("pie3D");
    </script>
 </div>
</c:if>

<!-- 柱状图和曲线图融合 -->
<c:if test="${type eq 'hl' or type eq 'lh'}">
 <div style="margin:0 auto;">
 	<div id="mSColumn3DLineDY" align="center"> </div>
    <script type="text/javascript">
	   var chart = new FusionCharts("${ctx}/${static_files_dir}/swf/MSColumn3DLineDY.swf", "MSColumn3DLineDYId", width, height, "0", "0");
	   chart.setDataURL(dataUrl);
	   chart.render("mSColumn3DLineDY");
    </script>
 </div>
</c:if>

<script type="text/javascript">//<![CDATA[

// 辅助JS方法，IFRAME调用的时候调用父页面方法
function js_method(parent_method_name, params){
	parent.window.eval(parent_method_name)(params);
}

document.body.ondblclick = function(){
	window.open(location.href.replace(/&width=\d+&/, '&width=800&').replace(/&height=\d+&/, '&height=600&'), 'max chart', 'height=' + 600 + ',width=' + 800 + ',scrollbars=no,location=no,toolbar:no,status:no');
};

String.prototype.getParameter = function (key) {  
    var re = new RegExp(key + '=([^&]*)(?:&)?');  
    return this.match(re) && this.match(re)[1];  
}; 
//]]></script>
</body>
</html>