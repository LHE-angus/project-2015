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
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaR3Shop" enctype="multipart/form-data">
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="g_lat" styleId="g_lat" />
      <html-el:hidden property="g_lng" styleId="g_lng" />
      <html-el:hidden property="method" styleId="method" value="saveMmtShop" />
		<div id="whzb_map"  title="拖动（下面 ↓）地图中的点，标注网点坐标" style="width:100%;">
		<div align="left"><span style="font-weight: bold">关键字:</span>
		      <input type="text" size="60" id="query"/>
		      <input type="button" value="查 询" id="querySub" class="bgButton" />
		</div>
		 <div align="left" id="results"></div>
		 <div id="map_canvas" style="width :100%; height : 400px;"></div>
		</div>
    <html-el:button property="btn_submit" styleId="btn_submit" value="确&nbsp;定" styleClass="but4"/>
    <html-el:button property="" value="返&nbsp;回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" />
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/google.map.geocoder.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	initialize();
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
		 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
         $("#btn_reset").attr("disabled", "true");
         $("#btn_back").attr("disabled", "true");
		 this.form.submit();
		}
	});
});
$("input[type='text'][readonly]").css("color","#ccc");
var typeIndex = 1;
var submitFlag = false;
var map;
var marker;
var markersArray = [];
var infowindowLevel = 0;
function initialize() {
	var myLatlng = new google.maps.LatLng(34.15480360960961, 108.93901360614018);
	$("#g_lat").val(34.15480360960961);
	$("#g_lng").val(108.93901360614018);
	var myOptions = {
		zoom : 5,
		center : myLatlng,
		navigationControl : true,
		scaleControl : true,
		streetViewControl : true,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

	map.setCenter(myLatlng);
	marker = new google.maps.Marker( {
		map : map,
		title : "拖动地图中的点，维护网点坐标",
		position : myLatlng,
		icon : '${ctx}/images/traffic_info_type_10.png',
		draggable : true
	});
	google.maps.event.addListener(marker, 'drag', function(i) {
		marker.setPosition(this.getPosition());
		var newPoint = this.getPosition().lat() + ","
				+ this.getPosition().lng();
		$("#g_lat").val(this.getPosition().lat());
		$("#g_lng").val(this.getPosition().lng());
	});
}

function clearOverlays(infowindow) {
	$("#results").empty();
	if (markersArray) {
		for (i in markersArray) {
			markersArray[i].setMap(null);
		}
		markersArray.length = 0;
	}
	if (infowindow) {
		infowindow.close();
	}
}


$("#querySub").click(function() {
	var paras = {
		showInfo : true
	}
	submitQuery(paras);
});



//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
