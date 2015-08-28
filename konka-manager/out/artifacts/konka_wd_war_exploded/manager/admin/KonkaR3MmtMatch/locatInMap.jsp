<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/redmond/jquery.ui.all.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
    <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="40"><a onclick="javascript:history.back();" style="cursor:pointer;">返回</a></td>
      </tr>
    </table>
  </div>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:0px;">
	  <tr>
	    <td align="left" valign="top"><!-- 地图 -->
	      <div style="width:100%;border:1px solid gray;" id="container"></div></td> 
	  </tr>
	</table>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2&services=true"></script>
<script type="text/javascript">//<![CDATA[
function setMapLayout($map) {
	if($("#mainFrame",parent.document.body).html() != null){
		$("#container").height($(parent.window).height() - $map.offset().top - 100);
	}else{   //新窗口打开时，全屏展示
		$("#container").height($(parent.window).height() - $map.offset().top - 2);
	}
}
$(document).ready(function(){
	var $map = $("#container").css({"border-right":"1px solid #ccc", "border-top":"1px solid #ccc"});
	setMapLayout($map);

	$(parent.window).resize(function() {
		setMapLayout($map);
	});

	var map = new BMap.Map("container");   // 创建Map实例

	// 下载地图
	loadGMap(map);

	var b_lng = "${shop.b_lng}";
	var b_lat = "${shop.b_lat}";
	var cust_name = "${shop.customer_name}";
	var G_content;  //保存已标记过的网点的显示内容

	if($.trim(cust_name).length > 0){
		G_content = "<span><b>店铺名称：</b>" + cust_name + "</span><br/>";
	} 
	
	if($.trim(b_lng).length > 0 && $.trim(b_lat).length > 0){
		initMarker(map, b_lng, b_lat,G_content);  //初始化标注
	}
});

function loadGMap(map){
	var point = new BMap.Point(108.523588, 38.060338); // 创建点坐标
	map.centerAndZoom(point,5);                       // 初始化地图，设置中心点坐标和地图级别。
	map.addControl(new BMap.NavigationControl());      // 添加控件
	map.enableScrollWheelZoom();
	map.enableContinuousZoom();

	return map;
}

function initMarker(map, b_lng, b_lat, content){
	var point = new BMap.Point(b_lng, b_lat); // 创建点坐标
	map.centerAndZoom(point,16); 
	
	var marker = new BMap.Marker(point); 
	map.addOverlay(marker);

	showInfoAndAddrWindow(marker, content);
}

//点击标注后显示的框
function showInfoWindow(_marker,content) {
	var infoWindow = new BMap.InfoWindow(content);
	_marker.openInfoWindow(infoWindow, _marker.getPosition());
}

//反解析地址后显示的框
function showInfoAndAddrWindow(_marker, content){
	var gc = new BMap.Geocoder();
    gc.getLocation(_marker.getPosition(), function(rs){
    	var addr = rs.address;
    	content = content + "<span><b>地址：</b>" + addr + "</span>";
    	showInfoWindow(_marker,content);
    });
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
