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
<div><form id="searchForm"></form>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:0px;">
  <tr>
    <td align="left" valign="top"><!-- 地图 -->
      <div style="width:100%;border:1px solid gray;" id="container"></div></td> 
  </tr>
</table></div>
</div>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2&services=true"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.pager.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.timers.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=idialog"></script>
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

	var map = new BMap.Map("container");               // 创建Map实例

	// 下载地图
	loadGMap(map);
	
	resetGMarkersToMap(map,$("#searchForm").json());

});

//全局变量
var G_DeptList = []; //分公司
var G_DelaySeconds = 500; //延时毫秒数, 设置此项为了减少客户端对服务器的请求次数，同时增强客户端用户体验，此项过大或过小均不好。
var G_timer; // 地图缩放或拖动的计时器
var G_marks = {}; // 全局GMark数组
var opts = {width : 0, height: 0};//默认参数
var persons = [];//存放中{marker:_marker, infoBox:infoBox};
var G_index = 0;//加载地图标注的条数
var markers = [];
// 全局变量结束

function loadGMap(map){
	var point = new BMap.Point(108.523588, 38.060338); // 创建点坐标
	map.centerAndZoom(point,5);                       // 初始化地图，设置中心点坐标和地图级别。
	map.addControl(new BMap.NavigationControl());      // 添加控件
	map.enableScrollWheelZoom();
	map.enableContinuousZoom();

	return map;
	
}
/**
 * 在地图中添加标记
 */
function resetGMarkersToMap(map, options) {
	var dialog = $.dialog.tips('数据加载中...',600,'loading.gif').lock();
	var requestPageSize = $("#pageSize").val();
	requestPageSize = /^\d+$/g.test(requestPageSize) ? requestPageSize : 10;
	$("#pager_control").pager("${ctx}/manager/ywygps/KonkaYwyMbGPS.do", 
			$.extend({}, {method : 'ajaxGetYwyJson'}, options), 
			$.extend({}, {showPagesCount : 5,	// 共显示页面下标数
		                  pageSize : requestPageSize // 每页显示数据条数
                         },options), 
            function(ret) {
		map.clearOverlays(); //标注清空；
		G_DeptList = ret.deptlist;
		setOtherMarkersToMap(map, ret.list,dialog);
	});
}

function setOtherMarkersToMap(map,other_list,dialog){
	G_index = 0;
	for (var i = 0; i < other_list.length; i++) {
		var cur = other_list[i];
		var user_name = cur.user_name;
		var dept_name = cur.dept_name;
		var role_name = cur.role_name;
		var root_id = cur.root_id;
		var mp_sn = cur.mp_sn;
		var update_time = cur.update_time;

		var icon = new BMap.Icon("${ctx}\/styles\/images\/dot.png", new BMap.Size(12, 20), {  
				anchor: new BMap.Size(7, 7),
				infoWindowAnchor: new BMap.Size(7, 0)
			});
		var bpoint = new BMap.Point(cur.lng, cur.lat);
		G_index = G_index + 1;
		getAddrAndAddMarker(map,bpoint,icon,user_name,root_id,dept_name,role_name,mp_sn,update_time,G_index,other_list.length,dialog);
	}
	
}

//在地图上面增加标注
function addMarker(_marker,map,user_name,root_id,dept_name,role_name,mp_sn,update_time,addr,G_index,list_length,dialog){
	var infoBox = personInfoBox(user_name,dept_name,role_name,mp_sn,update_time,addr);
		_marker.addEventListener("click", function(e){
			showInfoWindow(infoBox,_marker);
	});
	//map.addOverlay(_marker);
	markers.push(_marker);
	if(G_index == list_length){
		dialog.close();
		getMarkerClusterer(map);
	}
}

/* 生成聚合marker */
var markerClusterer;
function getMarkerClusterer(map) {
	// 最简单的用法，生成一个marker数组，然后调用markerClusterer类即可。
	if (typeof (markerClusterer) != "undefined") {
		markerClusterer.clearMarkers();
	}

	markerClusterer = new BMapLib.MarkerClusterer(map, {
		markers : markers
	});

	markerClusterer.setMaxZoom(17);
	markerClusterer.setGridSize(60);
	markerClusterer.setMinClusterSize(2);
}

//点击标注后显示的框
function showInfoWindow(infoBox,_marker) {
	var infoWindow = new BMap.InfoWindow(infoBox.join(""), opts);
	_marker.openInfoWindow(infoWindow, _marker.getPosition());
}

//获取addr并且添加标记
function getAddrAndAddMarker(map,point,icon,user_name,root_id,dept_name,role_name,mp_sn,update_time,G_index,list_length,dialog){
	var gc = new BMap.Geocoder();   
    gc.getLocation(point, function(rs){
        addr = rs.address;
        addMarker(new BMap.Marker(point,{icon:icon}),map,user_name,root_id,dept_name,role_name,mp_sn,update_time,addr,G_index,list_length,dialog);
    });        
}

//显示框具体内容
function personInfoBox(user_name,dept_name,role_name,mp_sn,update_time,addr){
	var linker = "${ctx}/manager/ywygps/KonkaYwyMbGPSOR.do?user_mp_like=" + mp_sn + "&trace_date=" + update_time.split(' ')[0];
	var a_linker = "<a href=\"" + linker + "\"  style=\"color:red;\"><详细></a>";
	var info = [];
	info[info.length] = '<span style="font-weight:bold;color:blue;font-size:14px;">';
	info[info.length] = user_name;
	info[info.length] = '</span>';
	info[info.length] = a_linker;
	info[info.length] =	'<hr>';
	info[info.length] = '<b>手机号：</b>';
	info[info.length] = mp_sn;
	info[info.length] = '<br /><b>职位：</b>';
	info[info.length] = role_name;
	info[info.length] = '<br /><b>部门：</b>';
	info[info.length] = dept_name;
	info[info.length] = '<br /><b>地址：</b>';
	info[info.length] = addr;
	info[info.length] = '<br /><b>最后更新时间：</b>';
	info[info.length] = update_time;
	return info;
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
<jsp:include page="/__analytics.jsp" />
</body>
</html>