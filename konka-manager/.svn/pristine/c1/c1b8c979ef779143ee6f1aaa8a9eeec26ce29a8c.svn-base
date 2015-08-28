<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script type="text/javascript">//<![CDATA[
//下载地图
var map= loadGMap(map);
//全局变量
var G_FirstLoader = true; // 首次加载地图
var G_marks = []; // 全局GMark数组
var defaultMapZoom = 12; //地图默认缩放级别8
// 全局变量结束
var center_g_lat = '${center_g_lat}';
var center_g_lng = '${center_g_lng}';

function loadGMap() {
	if(center_g_lat == "" || center_g_lng == "") return ;
	if (GBrowserIsCompatible()) {
		var map_div = document.getElementById("map");
		map_div.style.height = $("#tb_shop_order").height() + 'px';
		var map = new GMap2(map_div);
		map.enableScrollWheelZoom(); // 开启鼠标滚轮放大功能  
		var latlng = new GLatLng(31.722191,117.168106);
		map.setCenter(latlng,defaultMapZoom);
		return map;
	}
}
var A_Z_letters = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
//1.地图上添加网点图标
function setGMarkersToMap(list){
	map.closeInfoWindow();
	map.clearOverlays();
	var baseIcon = new GIcon(G_DEFAULT_ICON); 
	baseIcon.shadow = "http:\/\/www.google.cn\/mapfiles\/shadow50.png"; 
	baseIcon.iconSize = new GSize(20, 34); 
	baseIcon.shadowSize = new GSize(37, 34); 
	baseIcon.iconAnchor = new GPoint(9, 34); 
	baseIcon.infoWindowAnchor = new GPoint(9, 2);
	var flg = 0;
	for (var i = 0; i < list.length; i++) {
		var cur = shop_list[i];
		if ($.trim(cur.g_lat).length == 0 || $.trim(cur.g_lat) == 'null') {
			continue;
		}
		var letter = i > 25 ? "" : A_Z_letters[i];
		 
		var letteredIcon = new GIcon(baseIcon);
		letteredIcon.image = "http:\/\/www.google.cn\/mapfiles\/marker" + letter + ".png";

		letteredIcon.shadowSize = new GSize(0, 0);
		
		var latlng = new GLatLng(parseFloat($.trim(cur.g_lat)), parseFloat($.trim(cur.g_lng)));
		var gMarker = new GMarker(latlng, { icon:letteredIcon  });

		map.addOverlay(gMarker);
		if(flg == 0){
			map.setCenter(latlng,defaultMapZoom);
			flg = 1;
		}
	}
}

var shop_list = [];
<c:forEach var="cur" items="${xlpm_msbList}" varStatus="vs">
    shop_list[shop_list.length] = {
   		 shop_id:'${cur.map.shop_info.shop_id}',
	     shop_name:'${cur.map.shop_info.shop_name}',
	     g_lat:'${cur.map.shop_info.g_lat}',
	     g_lng:'${cur.map.shop_info.g_lng}'
	};
</c:forEach>
if(shop_list.length >0){
	setGMarkersToMap(shop_list);
}
//]]></script>