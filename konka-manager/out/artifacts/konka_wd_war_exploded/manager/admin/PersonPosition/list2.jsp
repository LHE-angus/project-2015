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
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
	<div style="padding:10px 5px;">
	  <div id="show0" style="display:none">请选择人员：
	    <select name="position0" id="position0" onchange="searchName(this);">
	      <option value="">请选择...</option>
	    </select>
	  </div>
	  <div id="show1"> 请选择人员：
	    <select name="position1" id="position1">
	      <option value="">请选择...</option>
	    </select>
	    请选择时间：
	    <input type="text" name="begin_date" value="2010-11-01" readonly="readonly" style="width:80px;" />
	    至
	    <input type="text" name="end_date" value="2010-11-30" readonly="readonly" style="width:80px;" />
	    <input type="button" value="显示轨迹" onclick="showLine();" />
	  </div>
	  <div id="show2" style="display:none"> 请选择人员：
	    <select name="position2" id="position2">
	      <option value="">请选择...</option>
	    </select>
	    <input type="button" value="数据导出" onclick="expData();" />
	  </div>
	</div>
  </div>
  <div class="rtabcont1">
	<div style="width:100%;height:500px;;border:1px solid gray" id="container"> </div>
	<div style="width:100%;height:100%;display:none;" id="expDate">
	   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
		   <tr class="tabtt1">
		      <td width="5%" nowrap="nowrap">序列</td>
		      <td width="30%" nowrap="nowrap">时间</td>
		      <td>位置</td>
		    </tr>
		    <tbody id="data">
	    </tbody>
	  </table>
	</div>  
  </div>
</div>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2&services=true"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">
var map = new BMap.Map("container");            // 创建Map实例
var point = new BMap.Point(114.061686, 22.539857);    // 创建点坐标
var ps = [];
map.centerAndZoom(point,12);                    // 初始化地图，设置中心点坐标和地图级别。
map.addControl(new BMap.NavigationControl());   // 添加控件
map.enableScrollWheelZoom();
map.enableContinuousZoom();

var persons = [];

<%@ include file="_data.jsp" %>

for(var i in line){
	addMarker(new BMap.Marker(line[i].points[line[i].points.length-1]),line[i].name,line[i].phone,line[i].date);
}


var opts = {
	  width : 0,  
	  height: 0   
    }

function addMarker(_marker, name, phone, date){
	persons[name] = {marker:_marker, name:name, phone:phone, date:date};

	_marker.addEventListener("click", function(e){
		showInfoWindow(_marker, name, phone, date);
	  });
	map.addOverlay(_marker)
}

function showInfoWindow(_marker, name, phone, date) {
	var info = [];
	info[info.length] = '<span style="font-weight:bold;font-size:14px;">';
	info[info.length] = name;
	info[info.length] = '</span><br /><b>手机号码：</b>';
	info[info.length] = phone;
	info[info.length] = '<br /><b>时　　间：</b>';
	info[info.length] = date;
	info[info.length] = '<br /><b>位　　置：</b>';
	
	var myGeo = new BMap.Geocoder();
	myGeo.getLocation(_marker.getPosition(), function(result){
	  if (result){
		info[info.length] = result.address;
	  } else {
		info[info.length] = '无法获取';
	  }
	  
	  var infoWindow = new BMap.InfoWindow(info.join(""), opts);
	  map.openInfoWindow(infoWindow, _marker.getPosition()); 
	});	
}

var position0 = document.getElementById("position0");
var position1 = document.getElementById("position1");
var position2 = document.getElementById("position2");
for(var i in persons){
	position0.options[position0.options.length] = new Option(i, i);
	position1.options[position1.options.length] = new Option(i, i);
	position2.options[position2.options.length] = new Option(i, i);
}

function searchName(o){
	if(o.value == '')return;
	showInfoWindow(persons[o.value].marker,persons[o.value].name,persons[o.value].phone,persons[o.value].date);
	map.setCenter(persons[o.value].marker.getPosition());
}

function showLine(){
	if(position1.value == '') {
		alert('请选择人员');
		return;
	}
	map.clearOverlays();
	map.addOverlay(new BMap.Polyline(line[position1.value].points, 
                   {strokeColor:"#00f", strokeWeight:6, strokeOpacity:0.5}));
	var end_marker = new BMap.Marker(line[position1.value].points[line[position1.value].points.length-1]);
	addMarker(end_marker,line[position1.value].name,line[position1.value].phone,line[position1.value].date);			   
	map.setCenter(line[position1.value].points[line[position1.value].points.length-1]);	
	showInfoWindow(end_marker ,line[position1.value].name,line[position1.value].phone,line[position1.value].date);
}

$("#type0,#type1,#type2").click(function(){
	for(var i = 0; i <3; i++){
		if(this.value == i){
			$("#show" + i).show();
		} else {
			$("#show" + i).hide();
		}
		if(this.value == 0){
			initMarkers();
		}
		if(this.value != 2){
			$("#container").show();
			$("#datatab").hide();
		}
		
	}
});


function expData() {
	if(position2.value == '') {
		alert('请选择人员');
		return;
	}
	$("#container").hide();
	var allPoints = line[position2.value].points;
	var count = 1;
	$("#datatab").show();
	$("#data").empty();
	for(var i in allPoints){
		var myGeo = new BMap.Geocoder();
		myGeo.getLocation(allPoints[i], function(result){
		  if (result){
			$("#data").append("<tr><td align='center'>"+(count++)+"</td><td align='center'>"+line[position2.value].date+"</td><td>"+result.address+"</td></tr>");
		  }
		})
	}
}
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>