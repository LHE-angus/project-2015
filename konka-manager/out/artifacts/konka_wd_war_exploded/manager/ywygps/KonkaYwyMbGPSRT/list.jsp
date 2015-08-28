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
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable1">
      <tr>
        <td width="10"></td>
        <td width="75%" style="text-align: left;">
        <form id="searchForm">
	        <input type="hidden" name="lat_max" id="lat_max" class="glatlng" />
			<input type="hidden" name="lng_max" id="lng_max" class="glatlng" />
			<input type="hidden" name="lat_min" id="lat_min" class="glatlng" />
		    <input type="hidden" name="lng_min" id="lng_min" class="glatlng" />
        	<strong class="fb">姓名/手机：</strong>
            <input type="text" name="user_mp_like" id="user_mp_like" style="width:150px;" />
            <strong class="fb">分公司：</strong>
            <select id="dept_id" name="dept_id">
            	<option value="">请选择</option>
            	<c:forEach var="cur" items="${deptList}" >
            		<option value="${cur.dept_id}">${cur.dept_name}</option>
            	</c:forEach>
            </select>
             &nbsp;&nbsp;
           	<input class="but1" type="button" name="search_btn" id="search_btn" style="cursor:pointer;" value="搜索" />
            <span style="color:#999;" id="note">提示：如果要更好的体验，请点击右上角“请新窗口打开”。</span>
            </form>
        </td>
        <td align="right" style="color:#999;padding-right:20px;" valign="middle">共有 <span id="count_sum">-</span>（<span id="fixed_dept_user_count">-</span>/<span id="all_dept_user_count">-</span>） 个结果符合您的查询要求. <span id="exc_time"></span></td>
      </tr>
    </table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:0px;">
  <tr>
    <td align="left" valign="top"><!-- 地图 -->
      <div style="width:100%;border:1px solid gray;" id="container"></div></td> 
    <td width="230" align="right" valign="top" class="sbox" style="padding-left:3px;">
    	<div id="shop_disp_div" class="listDiv">
	    	<ul id="shop_disp" class="pro_list lh30" style="overflow-y:auto;width:227px;"></ul>
			<div id="pager_control" style="height:32px;text-align:center;bottom:0;color:#666;width:100%;"></div>
		</div></td>
  </tr>
</table></div>
</div>
<ul style="display:none;">
	<li class="templete" style="display:none;text-align:left;padding-left:30px;padding-bottom:10px;">
		<span style="color:blue;font-weight:bolder;"></span><a href="#" style="color:red;">&lt;详细&gt;</a>
		<span style="float:right;"></span><br />
		<span></span>
		/
		<span></span><br />
	</li>
</ul>
<div id="loading" style="display:none;text-align:center;"><img src="${ctx}/styles/images/ajax-loader.gif" /> <span style="color:#999;">正在查询,请稍后...</span></div>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2&services=true"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.pager.js"></script>
<script type="text/javascript">
function setMapLayout($map) {
	if($("#mainFrame",parent.document.body).html() != null){
		$("#note").show();
		$(".listDiv").height($(parent.window).height() - $("#shop_disp_div").offset().top - 100);
		$(".sbox").height($("#shop_disp_div").height());
		$(".pro_list").height($("#shop_disp_div").height() - 30);
		$("#container").height($(parent.window).height() - $map.offset().top - 98);
	}else{   //新窗口打开时，全屏展示
		$("#note").hide();
		$(".listDiv").height($(parent.window).height() - $("#shop_disp_div").offset().top - 2);
		$(".sbox").height($("#shop_disp_div").height());
		$(".pro_list").height($("#shop_disp_div").height() - 30);
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

	$("#search_btn").click(function(){
		var gBounds = map.getBounds();   //获取可视区域
		$("#lat_max").val(gBounds.getNorthEast().lat);// 纬度（东西方向，横向）最大值
		$("#lng_max").val(gBounds.getNorthEast().lng);// 经度（南北方向，纵向）最大值
		$("#lat_min").val(gBounds.getSouthWest().lat);
		$("#lng_min").val(gBounds.getSouthWest().lng);
		resetGMarkersToMap(map,$("#searchForm").json());
	}).click();

	$("#dept_id").change(function(){
		// 创建地址解析器实例
		var addr = $(this).find("option:selected").text() + "市";
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint(addr, function(point){
			  if(point) {
			    map.centerAndZoom(point, 16);
			    //map.addOverlay(new BMap.Marker(point));
			  }
			}, addr);
		var gBounds = map.getBounds();   //获取可视区域
		$("#lat_max").val(gBounds.getNorthEast().lat);// 纬度（东西方向，横向）最大值
		$("#lng_max").val(gBounds.getNorthEast().lng);// 经度（南北方向，纵向）最大值
		$("#lat_min").val(gBounds.getSouthWest().lat);
		$("#lng_min").val(gBounds.getSouthWest().lng);
		$("#search_btn").click();
	});
	
});

//全局变量
var G_DelaySeconds = 2000; //延时毫秒数, 设置此项为了减少客户端对服务器的请求次数，同时增强客户端用户体验，此项过大或过小均不好。
var G_timer; // 地图缩放或拖动的计时器
var G_marks = {}; // 全局GMark数组
var opts = {width : 0, height: 0};//默认参数
var persons = [];//存放中{marker:_marker, infoBox:infoBox};
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
//	var s_time = new Date();
	var requestPageSize = $("#pageSize").val();
	requestPageSize = /^\d+$/g.test(requestPageSize) ? requestPageSize : 10;
	$("#shop_disp").empty().append("<li style='padding:50px 0;border-bottom:none;text-align:center;'>" + ($("#loading").clone().show()).html() + "<\/li>");
	$("#pager_control").pager("${ctx}/manager/ywygps/KonkaYwyMbGPSRT.do", 
			$.extend({}, {method : 'ajaxGetYwyJson'}, options), 
			$.extend({}, {showPagesCount : 5,	// 共显示页面下标数
		                  pageSize : requestPageSize // 每页显示数据条数
                         },options), 
            function(ret) {
		map.clearOverlays(); //标注清空；
		$("#count_sum").text(ret.count);// 设置查询结果数
		$("#all_dept_user_count").text(ret.all_dept_user_count);// 设置分公司人数
		$("#fixed_dept_user_count").text(ret.fixed_dept_user_count);// 设置分公司已定位人数
		writeDisplayYwyListHtml(map, ret.list);
		
	});
}

/**
 * 用HTML输出查询出来的业务员列表
 */
function writeDisplayYwyListHtml(map, ywy_list){
	var $container = $("#shop_disp").empty();
	if (ywy_list == null || ywy_list.length == 0) {
		$container.append("<li><span style=\"color:#F00;padding:20px 0;display:block;\">很抱歉！没有符合您的查询结果！</span></li>");
	}
	// 循环生成TR标签元素
	for (var i = 0; i < ywy_list.length; i++) {
		var cur = ywy_list[i];
		var letter = String.fromCharCode("A".charCodeAt(0) + i);
		letter = i > 9 ? "" : letter;
		var $li = $(".templete").clone().attr("id",letter).attr("lng",cur.lng).attr("lat",cur.lat);
		$li[0].style.backgroundImage = "url(${ctx}\/styles\/images\/letter\/" + letter + ".png)";//("background-image", );
		$li.attr("title",cur.user_name).find("span").eq(0).text(cur.user_name.substring(0,3));
		$li.find("span").eq(1).text(cur.update_time);
		$li.find("span").eq(2).text(cur.dept_name);
		$li.find("span").eq(3).text(cur.role_name);

		var href = "${ctx}/manager/ywygps/KonkaYwyMbGPSOR.do?user_mp_like=" + cur.mp_sn + "&trace_date=" + cur.update_time.split(' ')[0];
		$li.find("a").eq(0).attr("href",href);
		
		$li.hover(function() {
			$(this).css("cursor", "pointer");
			$(this).css("background-color", "#eee");
		}, function() {
			$(this).css("background-color", "");
		});
		$li.removeClass("templete").show();
		$container.append($li);

		//与地图交互
		var icon = new BMap.Icon("${ctx}\/styles\/images\/letter\/" + letter + ".png", new BMap.Size(28, 36), {  
						anchor: new BMap.Size(10, 10),
						infoWindowAnchor: new BMap.Size(10, 0) 
					});
		var bpoint = new BMap.Point(cur.lng, cur.lat); 
		getAddrAndAddMarker(map,bpoint,icon,letter,cur.user_name,cur.dept_name,cur.role_name,cur.mp_sn,cur.update_time);//letter作为persons[]主键

		$li.click(function(){
			map.centerAndZoom(new BMap.Point($(this).attr("lng"), $(this).attr("lat")), 16);
			showInfoWindow($(this).attr("id"));
		});
	}
	
	return $container;
}

//在地图上面增加标注
function addMarker(_marker,map,letter,user_name,dept_name,role_name,mp_sn,update_time,addr){
	var infoBox = personInfoBox(user_name,dept_name,role_name,mp_sn,update_time,addr);
	persons[letter] = {marker:_marker, infoBox:infoBox};
	_marker.addEventListener("click", function(e){
		showInfoWindow(letter);
	});
	_marker.setTop(true,27000000);  //字母标注在层的最上面
	map.addOverlay(_marker);
}

//点击标注后显示的框
function showInfoWindow(letter) {
	var info = persons[letter].infoBox;
	var _marker = persons[letter].marker;
	var infoWindow = new BMap.InfoWindow(info.join(""), opts);
	_marker.openInfoWindow(infoWindow, _marker.getPosition());
}

//获取addr并且添加标记
function getAddrAndAddMarker(map,point,icon,letter,user_name,dept_name,role_name,mp_sn,update_time){
	var gc = new BMap.Geocoder();    
    gc.getLocation(point, function(rs){
        addr = rs.address;
        addMarker(new BMap.Marker(point,{icon:icon}),map,letter,user_name,dept_name,role_name,mp_sn,update_time,addr);
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