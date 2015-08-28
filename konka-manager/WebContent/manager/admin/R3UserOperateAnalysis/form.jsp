<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<object
	id="jdxxusbkey"
	classid="clsid:D3AAD2B6-4C3D-43D4-927F-00AAA20158A4"
	codebase="${ctx}/files/plugs/activex/jdxxusbkey.cab#version=1,0,1,1"
	width="0"
	height="0"
	align="middle"
	hspace="0"
	vspace="0">
</object>
<object	
	id="jdhxusbkey" 
	classid="clsid:00FBB09D-B4F0-4A35-A0F3-EE3B6F2E63D4" 
	codebase="${ctx}/files/plugs/activex/jdhxusbkey.cab#version=1,0,0,0" 
	width="0" 
	height="0" 
	align="middle" 
	hspace="0" 
	vspace="0">
</object>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
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
      <html-el:form action="/admin/R3UserOperateAnalysis" styleClass="fromClass" enctype="multipart/form-data" onsubmit="save(this);return submitFlag;">
        <html-el:hidden property="shop_id" styleId="shpo_id" />
        <html-el:hidden property="mod_code" styleId="mod_code" />
        <html-el:hidden property="g_lat_t" styleId="g_lat_t" />
        <html-el:hidden property="g_lng_t" styleId="g_lng_t" />
        <html-el:hidden property="method" styleId="method" value="save" />
        <html-el:hidden property="queryString" styleId="queryString" />
        <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr>
            <th colspan="2" class="form_title">网点信息</th>
          </tr>
          <tr>
            <td width="90px;" align="right" class="title_item"><font color="red">*</font>网点名称：</td>
            <td><c:out value="${af.map.shop_name}"/></td>
          </tr>
          <tr>
            <td class="title_item" align="right"><font color="red">*</font>网点地址：</td>
            <td><c:out value="${af.map.street_addr}" /></td>
          </tr>
          <tr>
            <td class="title_item" align="right"><font color="red">*</font>网点坐标：</td>
            <td>
              <html-el:text property="shop_latlng" readonly="true" maxlength="128" style="width:250px;" styleClass="webinput" styleId="shop_latlng" value="${shop_latlng}"/>
              &nbsp;<input type="button" value="维护坐标" onclick="whzb()" class="bgButton" />
              
              </td>
          </tr>
          <tr>
          	<td></td>
            <td align="left"><input type="button" class="but5" value="保 存"  id="btn_sub"/> &nbsp;<html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" /></td>
          </tr>
        </table>
      </html-el:form>
    </div>
</div>
<div id="whzb_map" style="display:none;" title="拖动（下面 ↓）地图中的点，维护网点坐标" style="width:100%;">
<div align="left"><span style="font-weight: bold">关键字:</span>
      <input type="text" size="60" id="query"/>
      <input type="button" value="查 询" id="querySub" class="bgButton" />
</div>
 <div align="left" id="results"></div>
 <div id="map_canvas" style="width :100%; height : 540px;"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/google.map.geocoder.js"></script> 
<script type="text/javascript">//<![CDATA[
$("input[type='text'][readonly]").css("color","#ccc");
var typeIndex = 1;
var submitFlag = false;
var map;
var marker;
var markersArray = [];
var infowindowLevel = 0;
function initialize() {
	var myLatlng = new google.maps.LatLng(39.15543848919739, 117.19798577410889);
	var myOptions = {
		zoom : 13,
		center : myLatlng,
		navigationControl : true,
		scaleControl : true,
		streetViewControl : true,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

	var initLatlng = $("#shop_latlng").val();
	if ("" != initLatlng) {
		var latlng = initLatlng.split(",");
		myLatlng = new google.maps.LatLng(latlng[0], latlng[1]);
	}
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
		$("#shop_latlng").val(newPoint);
		$("#g_lat_t").val(this.getPosition().lat());
		$("#g_lng_t").val(this.getPosition().lng());
	});
}

$("#btn_sub").click(function(){
	$("#btn_sub").attr("disabled",true);
	var key1 = jdxxusbkey.GetInfo();
	var key2 = jdhxusbkey.GetInfo();
	var key = '';
	var own_sys = 0;
	var hasKey = false;
	if(!(key1=='-1' || key1=='-2' || key1=='-4')){
		key = key1;
		own_sys = 1;
		hasKey = true;
	}else if(!(key2=='-1' || key2=='-2' || key2=='-4')){
		key = key2;
		own_sys = 2;
		hasKey = true;
	}else{
		$("#btn_sub").removeAttr("disabled");
		alert("未检测到USBKEY设备，请插入USBKEY设备！");
		return false;
	}
	if(hasKey){
		$(document.forms[0]).append("<input type=\"hidden\" id=\"keySeq\" name=\"keySeq\" value=\""+key+"\" />");
		$(document.forms[0]).append("<input type=\"hidden\" id=\"own_sys\" name=\"own_sys\" value=\""+own_sys+"\" />");
			submitFlag = true;
			this.form.submit();
		
	}else{
		$("#btn_sub").removeAttr("disabled");
		return false;
	}
});

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

function whzb() {
	$("#whzb_map").dialog( {
		width : 620,
		height : 450,
		buttons : {"确认坐标" : function() {$(this).dialog("close");}},
		show : 'blind',
		hide : 'blind',
		position : [ 'right', 'top' ]
	}).dialog("open");
	initialize();
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
