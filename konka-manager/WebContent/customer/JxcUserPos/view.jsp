<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.sbox{ border-bottom:1px #e1d7be solid; border-left:1px #e1d7be solid;border-right:1px #e1d7be solid;}
.sbox ul li{ line-height:22px; background:left no-repeat; padding-left:10px;}
</style>
<style type="text/css">
body::-webkit-scrollbar-track-piece { background-color: white;}
::-webkit-scrollbar { width: 6px; height: 6px;}
::-webkit-scrollbar-track-piece { background-color: transparent;}
::-webkit-scrollbar-track-piece:no-button {}
::-webkit-scrollbar-thumb { background-color: #e60012; border-radius: 3px;}
::-webkit-scrollbar-thumb:hover { background-color: #e65512;}
::-webkit-scrollbar-thumb:active { background-color: #e65512;}

::-webkit-scrollbar-button:vertical { width: 6px;}
::-webkit-scrollbar-button:horizontal { width: 6px;}
::-webkit-scrollbar-button:vertical:start:decrement { background-color: white;width:0px;height:0px;}
::-webkit-scrollbar-button:vertical:end:increment { background-color: white;width:0px;height:0px;}
::-webkit-scrollbar-button:horizontal:start:decrement { background-color: white;width:0px;height:0px;}
::-webkit-scrollbar-button:horizontal:end:increment { background-color: white;width:0px;height:0px;}
html {
overflow-y:hidden;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
<div>
<form action="JxcUserPos.do" id="mapForm">
</form>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:0px;">
  <tr>
    <td align="left" valign="top"><!-- 地图 -->
      <div style="width:100%;border:1px solid gray;" id="container"></div></td> 
  </tr>
</table></div>
</div>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2&services=true"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">
function setMapLayout($map) {
	if($("#mainFrame",parent.document.body).html() != null){
		$("#container").height($(parent.window).height() - $map.offset().top - 98);
	}else{   //新窗口打开时，全屏展示
		$("#container").height($(parent.window).height() - $map.offset().top - 2);
	}
}

$(document).ready(function() {
	var $map = $("#container").css({"border-right":"1px solid #ccc", "border-top":"1px solid #ccc"});
	setMapLayout($map);

	$(parent.window).resize(function() {
		setMapLayout($map);
	});

	var map = new BMap.Map("container");   // 创建Map实例

	// 下载地图
	loadGMap(map);

	var b_lng = "${b_lng}";
	var b_lat = "${b_lat}";
	var cust_name = "${cust_name}";
	var G_content;  //保存已标记过的网点的显示内容
	
	if($.trim(cust_name).length > 0){
		G_content = "<span><b>店铺名称：</b>" + cust_name + "</span><br/>";
	} 
	
	if($.trim(b_lng).length > 0 && $.trim(b_lat).length > 0){
		initMarker(map, b_lng, b_lat, G_content);  //初始化标注
	}else{
		addDefaultMarker(map, G_content);  //添加默认标注
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

function addDefaultMarker(map,content){
	var point = new BMap.Point(116.416648, 39.907473); 
	var marker = new BMap.Marker(point); 
	map.addOverlay(marker);
	marker.enableDragging();    //可拖拽

	showInfoWindow(marker, "<span>您还没有在地图上给自己标记位置，赶快拖动标注来标记位置吧！</span>");

	marker.addEventListener("dragend", function(){
		 saveLngLat(marker.getPosition().lng, marker.getPosition().lat);
		 showInfoAndAddrWindow(marker, content);
	});

	marker.addEventListener("click", function(){
		showInfoAndAddrWindow(marker, content);
	});
}

function initMarker(map, b_lng, b_lat, content){
	var point = new BMap.Point(b_lng, b_lat); // 创建点坐标
	map.centerAndZoom(point,16); 
	
	var marker = new BMap.Marker(point); 
	map.addOverlay(marker);
	marker.enableDragging();    //可拖拽

	showInfoAndAddrWindow(marker, content)

	marker.addEventListener("dragend", function(){
		 saveLngLat(marker.getPosition().lng, marker.getPosition().lat);
		 showInfoAndAddrWindow(marker, content);
	});

	marker.addEventListener("click", function(){
		showInfoAndAddrWindow(marker, content);
	});
}

//点击标注后显示的框
function showInfoWindow(_marker,content) {
	var infoWindow = new BMap.InfoWindow(content);
	_marker.openInfoWindow(infoWindow, _marker.getPosition());
}

//反解析地址后显示的框
function showInfoAndAddrWindow(_marker,content){
	var gc = new BMap.Geocoder();
    gc.getLocation(_marker.getPosition(), function(rs){
    	var addr = rs.address;
    	content = content + "<span><b>地址：</b>" + addr + "</span>";
    	showInfoWindow(_marker,content);
    });
}

function saveLngLat(b_lng,b_lat){
	$.ajax({
		   type: "POST",
		   url: "JxcUserPos.do",
		   data: {"method":"ajaxSaveLngLat", "b_lng":b_lng, "b_lat":b_lat},
		   dataType: "json",
		   cache: false,
		   error: function(request, settings) {alert("数据加载请求失败！"); },
		   success: function(ret) {
			   if (ret){
					if(ret.status == 0){
						alert("位置无法保存，请检查是否已关联R3网点！");
					}
			   }
		   }
	  });
}

//以下为自定义的jQuery插件
$.fn.extend({
	textInputWithVal : function(val){
		var $this = this;
		return $this.attr("emptyValue", val).blur(function() {
			if ($.trim($this.val()).length == 0) { $this.val(val); }
		}).focus(function() {
			if ($.trim($this.val()) == val) { $this.val(""); }
		}).val(val);
	},
	json : function(){
		var obj = {};
		var ss = $(this).serialize().split("&");
		for (var i = 0; i < ss.length; i++) {
			obj[ss[i].split("=")[0]] = ss[i].split("=")[1];
		}
		return obj;
	}
});
$.extend({
	isBlank : function(s) { return (s === undefined) || (s === null) || /^\s*$/g.test(s.replace("null", "")); },
	isNotBlank : function(s) { return !$.isBlank(s); }
});
</script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>