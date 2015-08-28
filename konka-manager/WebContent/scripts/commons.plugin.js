/**
 * @author Wu,Yang
 * @version 2010-08-30
 * @param para 需要合并的td
 * @param parentObj td所属的父元素
 * @desc 相同元素的行合并
 */
function trMerge(para, parentObj) {
	var that;
	$("." + para, parentObj).each(function(){
		if ((that != undefined) && ($(this).html() == $(that).html())) {
			rowspan = $(that).attr("rowSpan");
			if (rowspan == undefined) {
				$(that).attr("rowSpan", 1);   
				rowspan = $(that).attr("rowSpan");   
			}
			rowspan = Number(rowspan) + 1;
			$(that).attr("rowSpan", rowspan); // do your action for the colspan cell here
			$(this).remove(); // .remove(); // do your action for the old cell here
	    } else {
			that = this;
	    }
	});
}

/**
 * @author Wu,Yang
 * @version 2010-08-30
 * @desc select的ajax联动
 */
function doSelectAjax(url, parentElement, parentValue, sonElement, sonValue, methodValue){
	parentElement.change(function(){
		var TrimParentElementValue = $.trim(parentElement.val());
		if("" != TrimParentElementValue){
			$.ajax({
					type: "POST",
					url: url,
					data: "method=" + methodValue + "&parentElementValue=" + TrimParentElementValue,
					dataType: "json",
					error: function(request, settings) {alert("数据加载请求失败！"); },
					success: function(Dates) {
						if (Dates.length > 1) {
							sonElement.empty().show();
							sonElement.get(0).options.add(new Option("请选择...", ""));
							for(var i = 0; i < Dates.length - 1; i++) {
								var opt = new Option(Dates[i].date_desc, Dates[i].date_id); 
								opt.title = Dates[i].date_desc;
								sonElement.get(0).options.add(opt);
							}
							sonElement.attr("value", sonValue);
						} 
					}
			});
		}
	});
	if ("" != parentValue) {
		parentElement.change();
	}
}

$("#pageUl li").mouseover(function(){ 
	$(this).css("cursor", "pointer");
	$(this).css("background-color", "#bcd4ec");
}).mouseout(function(){
	$(this).css("background-color", "");
});

function removeRepeatPoints(markers){
	var newMarkers = [];
	if (markers) {
		for(i = 0; i < markers.length; i++) {
			var point = markers[i];
		    var yap = false;
		    for(var j = 0; j < newMarkers.length; j++){
		        if(newMarkers[j].getPosition().equals(point.getPosition())){yap=true;break;}
		    }
		    if(!yap) {newMarkers.push(point);}
		}
	}
	return newMarkers;
}


var isFullScreen = false;
var isDisplayCondition = false;
var isDisplayTraffic = false;

function displayCondition()
{
	if (isDisplayCondition){
		$("#divSearchCondition").slideUp("middle");
		$("#btnDisplayCondition").val("显示条件");
		isDisplayCondition = false;
	}else{
		$("#divSearchCondition").slideDown("middle"); 
		$("#btnDisplayCondition").val("隐藏条件");
		isDisplayCondition = true;
	}
}

function fullScreen(){
	var mainFrameset = top.document.getElementById("mainFrameset");
	
	if (isFullScreen){
		mainFrameset.cols = "240,13,*";
		$(mainFrameset).parent().attr("rows","120,*");
		isFullScreen = false;
		$("#divPageIndex").show();
		$("#full_screen").val("0");
		$("#divSearchCondition").show();
		if (isDisplayTraffic)
			$("#divSearchTraffic").show();
		$("#map_canvas").height(600);
		$("#btnFullScreen").val(" 全屏 ");
		$("#btnDisplayCondition").val("显示条件");
		isDisplayCondition = false;
		$("#btnDisplayCondition").hide();
	}else{
		mainFrameset.cols = "0,0,*";
		$(mainFrameset).parent().attr("rows","0,*");
		isFullScreen = true;
		$("#full_screen").val("1");
		var mapHeight = mainFrameset.offsetHeight + 100;
		$("#map_canvas").height(mapHeight);
		$("#divPageIndex").hide();
		$("#divSearchTraffic").hide();
		$("#divSearchCondition").hide();
		$("#btnFullScreen").val("退出全屏");
		$("#btnDisplayCondition").show();
	}
}

function showTrafficSearchInfo(){
	$("#trafficDisplayInfo").toggle();
	if ($("#divSearchTraffic").is(":hidden")) {
		$("#divSearchTraffic").slideDown();
		isDisplayTraffic = false;   
	} else {
		$("#divSearchTraffic").slideUp();
		isDisplayTraffic = true;
	}
	
}
function showMapSearchInfo(){
	if ($("#divMapSearch").is(":hidden")) {
		$("#divMapSearch").slideDown();
	} else {
		$("#divMapSearch").slideUp();
		clearOverlaysForGeocode();
	}
}
function showLayOutTool(){
	if ($("#divLayOutTool").is(":hidden")) {
		$("#divLayOutTool").slideDown();
	} else {
		$("#divLayOutTool").slideUp();
	}
}

function initFullScreen(full_screen){
	if ("1" == full_screen){
		$("#divPageIndex").hide();
		isFullScreen = true;
		isDisplayCondition = true;
		$("#btnDisplayCondition").show();
		$("#btnDisplayCondition").val("隐藏条件");
		$("#btnFullScreen").val("退出全屏");
		$("#full_screen").val("1");
	}
}


var title = "网点信息";
var useSlide = true;// 淡入淡出
var winPos = "rb";//右下角显示
var closeBtn = true;//是否显示关闭按钮
var msgCls = "tipCss";//内容样式
var allowRightMenu = false;//允许右键
var allowSelect = false;//允许选择
var width = 380;
var height = 500;
function ymPromptWin(message) {
	ymPrompt.win({message:message,width:width,height:height,title:title,winPos:winPos,useSlide:useSlide,btn:[['关闭','yes']],closeBtn:closeBtn,msgCls:msgCls,allowRightMenu:allowRightMenu,allowSelect:allowSelect});
}
function ymPromptWinForTraffic(message) {
	ymPrompt.win({message:message,width:380,height:200,title:"交通点信息",winPos:winPos,useSlide:useSlide,btn:[['关闭','yes']],closeBtn:closeBtn,msgCls:msgCls,allowRightMenu:allowRightMenu,allowSelect:allowSelect});
}

function showPeopleInfo(area_id){
	var select_area_id = "";
	var select_area_name = "----";
	if (area_id != null && typeof(area_id) != "undefined" && area_id != ""){
		select_area_id = $("#" + area_id).val();
		select_area_name = $("#" + area_id).find("option:selected").text();
	}
	
	//查询人口信息
	$.ajax({
		type: "POST",
		url: "Cs.do",
		data: "method=getPeopleInfo&area_id=" + $.trim(select_area_id),
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(peopleInfoData) {
			var sPeopleInfo = "";
			sPeopleInfo += "<table border='0' cellpadding='0' cellspacing='1' class='datagrid'>";
			sPeopleInfo += 	"<tr>";
			sPeopleInfo +=		"<td nowrap='nowrap'><strong>序号</strong></td>";
			sPeopleInfo +=		"<td nowrap='nowrap'><strong>地区</strong></td>";
			sPeopleInfo +=		"<td nowrap='nowrap'><strong>人口状况(万人)</strong></td>";
			sPeopleInfo += 	"</tr>";
			if (peopleInfoData.length > 1) {
				for(var i = 0; i < peopleInfoData.length - 1; i++) {
					if (select_area_name.indexOf(peopleInfoData[i].area_name) >= 0){
						sPeopleInfo += 	"<tr style='color:red;'>";
					} else {
						sPeopleInfo += 	"<tr>";
					}
					sPeopleInfo +=		"<td nowrap='nowrap'>" + (i + 1) + "</td>";
					sPeopleInfo +=		"<td nowrap='nowrap'>" + peopleInfoData[i].area_name + "</td>";
					sPeopleInfo +=		"<td nowrap='nowrap'>" + peopleInfoData[i].people_sum_cur + "</td>";
					sPeopleInfo += 	"</tr>";
				}
			}
			sPeopleInfo += "</table>";
			var obj = $("#divPeopleInfo");
		    //隐藏信息提示DIV
			if ($("#divPeopleInfo").length > 0)
			{
				$("#divPeopleInfo").empty();
			}
			else {
		        /** 创建DIV */
		        var sDivInfo = "<div id='divPeopleInfo' style='font-size:12pt;' title='人口状况'></div>";
		        //插入DIV
		        $(sDivInfo).appendTo(document.body);
			}
		    $("#divPeopleInfo").append(sPeopleInfo);
			$("#divPeopleInfo").dialog( {
				width: 400,
				buttons : {"关闭" : function() {$(this).dialog("close");}},
				show: 'blind',
				hide: 'blind',
				position:'right',
				modal : true
			}).dialog("open");
			
		}
	});
}
var displayTraffic = false;
function initLineInfo(obj, line_info_id){
	var type = obj.value;
	//查询人口信息
	$.ajax({
		type: "POST",
		url: "Cs.do",
		data: "method=getLineInfo&type_id=" + type,
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(lineInfoList) {
			var sonElement = $("#" + line_info_id);
			if (lineInfoList.length > 1) {
				sonElement.empty().show();
				sonElement.get(0).options.add(new Option("请选择...", ""));
				sonElement.parent().show();
				for(var i = 0; i < lineInfoList.length; i++) {
					var opt = new Option(lineInfoList[i].line_name, lineInfoList[i].line_id); 
					opt.title = lineInfoList[i].line_name;
					sonElement.get(0).options.add(opt);
				}
			} 
		}
	});
	
	if (type == "10"){
		$("#span_traffic_type_name").html("地铁线路：");
	} else if (type == "20"){
		$("#span_traffic_type_name").html("高速名称：");
	} else if (type == "30"){
		$("#span_traffic_type_name").html("快速线路：");
	} else if (type == "40"){
		$("#span_traffic_type_name").html("铁路：");
	}
	$("#divLineInfo").show();
}
var markersForTrafficInfo = [];
var polylinesForTrafficInfo = [];
function showTrafficInfo(trafficType, lineId, area_id){
	var type_id = $("#" + trafficType).val();
	if (type_id == null || typeof(type_id) == "undefined" || type_id == ""){
		alert("请选择交通类型");
		return;
	}
	var line_id = $("#" + lineId).val();
	var area = "";
	if (typeof(area_id) != "undefined" && null != area_id && "" != area_id){
		area = $("#" + area_id).val();
	}
	//查询交通

	//清空交通信息
    $("#loading").ajaxStart(function(){/*clearMakersTrafficInfo();*/$(this).show();});
	$("#loading").ajaxStop(function(){$(this).hide();});
	$.ajax({
		type: "POST",
		url: "Cs.do",
		data: "method=getTrafficInfo&type_id=" + type_id + "&line_id=" + line_id + "&area_id=" + $.trim(area) + "&gettime=" + new Date().getTime(),
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(lineInfoList) {
			if (lineInfoList.length > 0) {
				
				var divInfo = "";
				divInfo += "<div id='divTraaficDetail' style='text-align:left;display:none;'>";
				divInfo += "    <table width='50%' border='0' align='left' cellpadding='1' cellspacing='1' class='datagrid'> ";
				divInfo += "      <tr>                                                                                       ";
				divInfo += "        <th colspan='2' class='form_title'>交通信息</th>                                         ";
				divInfo += "      </tr>                                                                                      ";
				divInfo += "      <tr>                                                                                       ";
				divInfo += "        <td width='80px;'  class='title_item'>所在区域：</td>                                    ";
				divInfo += "        <td><span id='area_name'></span></td>                                                    ";
				divInfo += "      </tr>                                                                                      ";
				divInfo += "      <tr>                                                                                       ";
				divInfo += "        <td class='title_item'>名称：</td>                                                       ";
				divInfo += "        <td><span id='traffic_name'></span></td>                                                 ";
				divInfo += "      </tr>                                                                                      ";
				divInfo += "      <tr>                                                                                       ";
				divInfo += "        <td class='title_item'>地址：</td>                                                       ";
				divInfo += "        <td><span id='traffic_addr'></span></td>                                                 ";
				divInfo += "      </tr>                                                                                      ";
				divInfo += "    </table>                                                                                     ";
				divInfo += "</div>                                                                                           ";
				$(divInfo).appendTo(document.body);
				
				var lineArray = new Array();
				var lineColorArray = new Array();
				var lineMarkerArray = new Array();
				var polylineForCalcWithSum = [];
				var infoWindowZindex = 0;
				var ctxPath = $("#ctxPath").val();
				var first180 = true;
				var first181 = true;
				var first202 = true;
				var first203 = true;
				var first204 = true;
				var first205 = true;
				var first206 = true;
				var first207 = true;
				var first208 = true;
				$("#trafficDisplayInfoData").empty();
				for(var i = 0; i < lineInfoList.length; i++) {
					var is_site = lineInfoList[i].is_site;
					if (is_site == "1"){
						var lineType = "";
						if ("10" == lineInfoList[i].type){
							lineType = "地铁";
						} else if ("20" == lineInfoList[i].type){
							lineType = "高速";
						} else if ("30" == lineInfoList[i].type){
							lineType = "快速";
						} else if ("40" == lineInfoList[i].type){
							lineType = "铁路";
						}
						$("<tr></tr>").append(
								"<td align='left'>" + lineType + "</td>"
							  + "<td align='left'>" + lineInfoList[i].line_name + "</td>"
							  + "<td align='left'>" + lineInfoList[i].area_name + "</td>"
							  + "<td align='left'>" + lineInfoList[i].name + "</td>"
							  + "<td align='left'>" + lineInfoList[i].addr + "</td>"
						).appendTo($("#trafficDisplayInfoData"));
					}
					
					
					var sLatlng = lineInfoList[i].latlng;
					var latlng = sLatlng.split(",");
					var location = new google.maps.LatLng(latlng[0], latlng[1]);
					var iconUrl = ctxPath + "/images/train-icon.png";
					var type = lineInfoList[i].type;
				    var line_info_id = lineInfoList[i].line_info_id;

					if (type == "10"){
						iconUrl = ctxPath + "/images/subway-icon.png";
					} else if (type == "30"){
						iconUrl = ctxPath + "/images/fast-icon.png";
					} else if (type == "20"){
						if (first180 && line_info_id == "180" && is_site == "1"){
							first180 = false;
							iconUrl = ctxPath + "/images/tram-icon-180.png";
						} else if (first181 && line_info_id == "181" && is_site == "1") {
							first181 = false;
							iconUrl = ctxPath + "/images/tram-icon-181.png";
						} else if (first202 && line_info_id == "202" && is_site == "1") {
							first202 = false;
							iconUrl = ctxPath + "/images/tram-icon-202.png";
						} else if (first203 && line_info_id == "203" && is_site == "1") {
							first203 = false;
							iconUrl = ctxPath + "/images/tram-icon-203.png";
						} else if (first204 && line_info_id == "204" && is_site == "1") {
							first204 = false;
							iconUrl = ctxPath + "/images/tram-icon-204.png";
						} else if (first205 && line_info_id == "205" && is_site == "1") {
							first205 = false;
							iconUrl = ctxPath + "/images/tram-icon-205.png";
						} else if (first206 && line_info_id == "206" && is_site == "1") {
							first206 = false;
							iconUrl = ctxPath + "/images/tram-icon-206.png";
						} else if (first207 && line_info_id == "207" && is_site == "1") {
							first207 = false;
							iconUrl = ctxPath + "/images/tram-icon-207.png";
						} else if (first208 && line_info_id == "208" && is_site == "1") {
							first208 = false;
							iconUrl = ctxPath + "/images/tram-icon-208.png";
						} else 
							iconUrl = ctxPath + "/images/tram-icon.png";
					}
				    var marker = new google.maps.Marker({
				        position: location,
				        icon: iconUrl,
				        map: map
				    });
				    marker.setTitle(lineInfoList[i].name);
				    
				    if (is_site == "0"){
				    	marker.setVisible(false);
				    }
				    
				    $("#area_name").empty().text(lineInfoList[i].area_name);
				    $("#traffic_name").empty().text(lineInfoList[i].name);
				    $("#traffic_addr").empty().text(lineInfoList[i].addr);
				    
				    attachTraficInfowindow(marker, $("#divTraaficDetail").html());
				    markersForTrafficInfo.push(marker);
				}
				
				
			}
			
		}
	});
	
}

function attachTraficInfowindow(marker, htmlDate) {
	  var infowindow = new google.maps.InfoWindow({ 
		  content: htmlDate
	  });
	  google.maps.event.addListener(marker, 'click', function() {
			var message = infowindow.getContent();
			ymPromptWinForTraffic(message);
	  });
	  // google.maps.event.addListener(marker, 'click', function() {
	    // infowindow.setZIndex(++infowindowLevel);
	    // infowindow.open(map, marker);
	  // );
}

//清空交通信息
function clearMakersTrafficInfo() {
	if (markersForTrafficInfo) {
		for (i in markersForTrafficInfo) {
			markersForTrafficInfo[i].setMap(null);
		}
		markersForTrafficInfo.length = [];
	}
	if (polylinesForTrafficInfo) {
		for (i in polylinesForTrafficInfo) {
			polylinesForTrafficInfo[i].setMap(null);
		}
		polylinesForTrafficInfo = [];
	}

}

function openChild(){
    var returnValue = window.showModalDialog("Cs.do?method=getBizTypeTree", window, "dialogWidth:600px;status:no;dialogHeight:430px"); 

    if(returnValue != null) {
		var value = new Array();
		value = returnValue.split("|");
		$("#type_id_list").val(value[0]);
		$("#type_name_list").val(value[1]);
    } 
}

/**
 * @author Wu,Yang
 * @version 2010-11-19
 * @desc 规划工具 BEGIN
 */
var LayOutToolMakers = [];
var LayOutToolInfowindows = [];
var listenerHandleArrayForLayOutTool = [];
var addCircleMarker = false;
var distanceWidgetForLayouts;
function drawCircleInfoForLayout(ctx){
	
	var title_info = "请拖动红色图标，在地图上划定圆圈的范围";
	$.jGrowl(title_info);
	var distanceWidgetForLayout = new DistanceWidget(map, ctx);
	distanceWidgetForLayouts.push(distanceWidgetForLayout);
}
var marker1ForRectangles = [];
var marker2ForRectangles = [];
var rectangleForLayouts = [];
var RectangleInfowindows1 = [];
var RectangleInfowindows2 = [];
function drawRectangleForLayout(ctx){
	
	var title_info = "请拖动蓝色图标，在地图上划定矩形的范围";
	$.jGrowl(title_info);

	var len = rectangleForLayouts.length;
	var position = map.getCenter();
	var latCenter = position.lat() - 0.001 - 0.001 * len;
	var lngCenter = position.lng() - 0.001 - 0.001 * len;
	var latlngCenter = new google.maps.LatLng(latCenter, lngCenter);
	
	var marker1ForRectangle = new google.maps.Marker({
        map: map,
  	    icon: ctx + '/images/icon/small/NeedleWhite-icon-blue.gif',
        position: latlngCenter,
        draggable: true,
        title: '拖动变换位置'
	});
	var lat = position.lat() + 0.001 + 0.001 * len;
	var lng = position.lng() + 0.001 + 0.001 * len;
	var latlng = new google.maps.LatLng(lat, lng);
	var marker2ForRectangle = new google.maps.Marker({
        map: map,
  	    icon: ctx + '/images/icon/small/NeedleWhite-icon-blue.gif',
        position: latlng,
        draggable: true,
        title: '拖动变换位置'
	});
	
    var rectangleForLayout = new google.maps.Rectangle({
		//strokeColor: "#FF7575",
		strokeOpacity: 0.9,
		strokeWeight: 1.5,
		//fillColor: "#FF95CA",
		fillOpacity: 0.3,
		zIndex:1,
        map: map
    });
    rectangleForLayouts.push(rectangleForLayout);
    marker1ForRectangles.push(marker1ForRectangle);
    marker2ForRectangles.push(marker2ForRectangle);
    var index = rectangleForLayouts.length - 1;
    redrawRectangle(index);

	var delString = '矩形范围&nbsp;<a href="javascript:void(0);" onclick="removeRectangleMarker(' + index + ');">删除</a>';
	attachRectangleInfowindow(marker1ForRectangle, delString, 1);
	attachRectangleInfowindow(marker2ForRectangle, delString, 2);
	
	google.maps.event.addListener(marker1ForRectangles[index], 'drag', function() {redrawRectangle(index);});
    google.maps.event.addListener(marker2ForRectangles[index], 'drag', function() {redrawRectangle(index);});  
}
var RectangleInfowindows = [];
function removeRectangleMarker(index){
	if (index < 0) return;
    rectangleForLayouts[index].setBounds(null);
    marker1ForRectangles[index].setMap(null);
    marker2ForRectangles[index].setMap(null);
    rectangleForLayouts[index] = null;
    RectangleInfowindows1[index].close();
    RectangleInfowindows2[index].close();
}
function attachRectangleInfowindow(marker, htmlDate, flag) {
	 var infowindow = new google.maps.InfoWindow({ 
		 content: htmlDate
	 });
	 google.maps.event.addListener(marker, 'click', function() {
		 infowindow.open(map, marker);
	 });
	 if (flag == 1){
		 RectangleInfowindows1.push(infowindow);
	 } else {
		 RectangleInfowindows2.push(infowindow);
	 }
}

function redrawRectangle(index) {
	if (index < 0) return;
    var latLngBounds = new google.maps.LatLngBounds(
    		marker1ForRectangles[index].getPosition(),
    		marker2ForRectangles[index].getPosition()
    );
    rectangleForLayouts[index].setBounds(latLngBounds);
}
function addListenerHandleForLayOutTool(ctx, state_type_id) {
	removeListenerHandleForLayOutTool();
	var listenerHandle = google.maps.event.addListener(map, 'click', function(event) {
		addLayOutToolMaker(ctx, state_type_id, event.latLng);
	}); 
	listenerHandleArrayForLayOutTool.push(listenerHandle);
	addAreaListernerHandleForLayOutTool(ctx, state_type_id);
	var JQ_LayOutTool = $("#LayOutTool_" + state_type_id);
	var title_info = "请点击地图，在地图上添加";
	$.jGrowl(title_info + JQ_LayOutTool.text() + "小图标。");
}

function addAreaListernerHandleForLayOutTool(ctx, state_type_id){//给区域增加click时间
	if (polylineForCalcWithSum){
		for (i in polylineForCalcWithSum) {
			var listenerHandle = google.maps.event.addListener(polylineForCalcWithSum[i], 'click', function(event) {
				addLayOutToolMaker(ctx, state_type_id, event.latLng);
		    }); 
			listenerHandleArrayForLayOutTool.push(listenerHandle);
		}
	}
}

function addLayOutToolMaker(ctx, state_type_id, latLng) {
	if(!map) return;
	var marker = new google.maps.Marker({
        position: latLng, 
  	    icon: ctx + '/images/icon/small/state_type_' + state_type_id + '.gif',
        map: map,
        draggable: true
	});
	var title = $("#LayOutTool_" + state_type_id).text();
	marker.setTitle(title);
	var delString = '&nbsp;<a href="javascript:void(0);" onclick="removeLayOutMarker(' + LayOutToolMakers.length + ');">删除</a>';
	attachLayOutToolInfowindow(marker, title + delString);
	LayOutToolMakers.push(marker);
}

function attachLayOutToolInfowindow(marker, htmlDate) {
	 var infowindow = new google.maps.InfoWindow({ 
		 content: htmlDate
	 });
	 google.maps.event.addListener(marker, 'click', function() {
		 infowindow.open(map, marker);
	 });
	 LayOutToolInfowindows.push(infowindow);
}

function removeListenerHandleForLayOutTool() {
	if (listenerHandleArrayForLayOutTool) {
		for (i in listenerHandleArrayForLayOutTool) {
			google.maps.event.removeListener(listenerHandleArrayForLayOutTool[i]);
		}
		listenerHandleArrayForLayOutTool = [];
	}
}

function removeLayOutMarker(index) {
	removeListenerHandleForLayOutTool();//不要删除此行!
	if (LayOutToolMakers) {
		LayOutToolMakers[index].setMap(null);
	}
	if (LayOutToolInfowindows) {
		for (i in LayOutToolInfowindows) {
			LayOutToolInfowindows[i].close();
		}
	}
	//让radio的控件不选中任何值
	$("input[type='radio'][name='layouttool_type']").attr("checked", "");
}

function clearAllLayOutMarkers(){
	if (LayOutToolMakers) {
		for (i in LayOutToolMakers) {
			LayOutToolMakers[i].setMap(null);
		}
		LayOutToolMakers.length = [];
	}
}


var isDisplayEntpName = false;
var markerLabels = [];
function displayEntpName(){
	if (markers){
		for (var ii = 0; ii < markers.length; ii++){
			if (isDisplayEntpName){
				if(markerLabels){
					for (var k = 0; k < markerLabels.length; k++){
						markerLabels[k].setMap(null);
					}
				}
				markerLabels = [];
			} else {
			    var markerLabel = new Label({ map: map });
				markerLabel.bindTo('position', markers[ii], 'position');
				var divHtml = "<span style='color:red;border:#666 solid 1px;'>" + markers[ii].getTitle() + "</span>";
				markerLabel.set("text", divHtml);
				markerLabels.push(markerLabel);
			}
		}
		if (isDisplayEntpName){
			isDisplayEntpName = false;
		} else {
			isDisplayEntpName = true;
		}
	}
}


/**
 * @desc 规划工具 END
 */



//天津边界 各区边界
function drawTianjinArea(polylineForCalcWithSum) {
	$.ajax({
		type: "POST",
		url: "Cs.do",
		data: "method=drawTianjinAreaAjax&" + $(".searchForm").serialize(),
		dataType: "json",
		error: function(request, settings) {},
		success: function(datas) {
			if(datas.length > 1){
				for(var i = 0; i < datas.length - 1; i++) {
					var area_id = datas[i].area_id;
					var area_color = datas[i].area_color;
					var area_points = datas[i].area_points;
					var paths = [];
					if(area_points.length > 0){
						for(var j = 0; j < area_points.length; j++) {
							var latlng = area_points[j].split(",");
							var new_latlng =  new google.maps.LatLng(latlng[0],latlng[1]);
							paths.push(new_latlng);
						}
					}
					if (map) {
						area_color = "#" == area_color ? "#FF0000" : area_color;
					    var polygon = new google.maps.Polygon({
					  		path: paths,
					  		//strokeColor: "#990099",
					  		//strokeOpacity: 1.0,
					  		//strokeWeight: 2
					  	    strokeColor: "#FF0000",
					  	    strokeOpacity: 0.8,
					  	    strokeWeight: 1,
					  	    fillColor: area_color,
					  	    fillOpacity: 0.4
					     });
					    polygon.setMap(map);
					    if (polylineForCalcWithSum){
					    	polylineForCalcWithSum.push(polygon);
					    }
					}					
				}
			}
		}
	});
}

function checkIsInsterKeyForIsLogin(width, height, sessionKeyValue) {
	$("body").append('<div id="dialogTjgis" align="left" title="天津市地理信息系统" style="display:none;color:red;font-size: 13px;"></div>');
	$(document).everyTime(1000, function(i) {// 1秒，检测一次
		var title = "";
		var info = "";
		var isIe = false;
		var browserInfo = "“未知”";
		if($.browser.msie) {
			isIe = true;
			var jdxxKey = jdxxusbkey.GetInfo();
			if (!(jdxxKey=='-1' || jdxxKey=='-2' || jdxxKey=='-4')) {
				if (undefined != sessionKeyValue && sessionKeyValue != jdxxKey) {
					info = "系统检测到当前插入的密钥值和你用户所绑定的密钥值不匹配，请核查！"; 
					$("#dialogTjgis").text(info).dialog({
						width: width,
						height: height,
						resizable: false,
						open: function() {
							$(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar-close").remove();
						},
						modal : true
					});
				} else {
					$("#dialogTjgis").dialog("close");
				}
			} else {
				info = "未检测到USBKEY设备，请插入USBKEY设备！"; 
				$("#dialogTjgis").text(info).dialog({
					width: width,
					height: height,
					resizable: false,
					open: function() {
						$(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar-close").remove();
					},
					modal : true
				});
			}
		} else if($.browser.safari) {
			browserInfo = "“safari or chrome”";
		} else if($.browser.mozilla) {
			browserInfo = "“mozilla”";
		} else if($.browser.opera) {
			browserInfo = "“opera”";
		}
		if(!isIe){
			info = '系统检测到您当前使用的是<span id="browserInfo" style="font-weight:bold;">'+browserInfo+'</span>浏览器，由于系统升级使用密钥登陆，所以请使用<span style="font-weight:bold;">“IE”</span>浏览器登陆！';
			$("#dialogTjgis").html(info).dialog({
				width: width,
				height: height,
				resizable: false,
				//show: 'blind',
				//hide: 'blind',
				//position:['right','bottom'],
				open: function() {
					$(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar-close").remove();
				},
				modal : true
			});
		}
	});
}

function setYmPromptWinMessage(message){
	ymPrompt.setMessage(message);
}

function setEntpInfoData(parObj, findObjId, findObjValue) {
    if (findObjValue != "null" && findObjValue != ""){
    	parObj.find("#" + findObjId).text(findObjValue).parent().parent().show();
	}
}
function attachInfowindowWithAjax(marker, id, entp_name) {
  	google.maps.event.addListener(marker, 'click', function() {
		 var openWindow = $("#mainDiv").clone();
		 openWindow.find("#entp_name").append(entp_name);
		 ymPromptWin(openWindow.html());
		 $(document).oneTime('1s',function(){
			 $.ajax({
					type: "POST",
					url: "Cs.do",
					data: "method=getSingleEntpInfo&id=" + id,
					dataType: "json",
					error: function(request, settings) {alert("数据加载请求失败！");$("#loading").hide();},
					success: function(Datas) {
						openWindow.find("#datas_is_loading").hide();
						openWindow.find("#tbody_datas").show();
						openWindow.find("#entp_addr").text(Datas.entp_addr);
						openWindow.find("#link_tel").text(Datas.link_tel);
					    setEntpInfoData(openWindow, "is_main_biz", Datas.is_main_biz);
					    setEntpInfoData(openWindow, "bed_num", Datas.bed_num);
					    setEntpInfoData(openWindow, "biz_area", Datas.biz_area);
					    setEntpInfoData(openWindow, "area_info_id", Datas.area_info_id);
					    setEntpInfoData(openWindow, "state_type_name", Datas.state_type_name);
					    setEntpInfoData(openWindow, "spel_biz_area_id", Datas.spel_biz_area_id);
					    setEntpInfoData(openWindow, "biz_circle_id", Datas.biz_circle_id);
					    setEntpInfoData(openWindow, "haihe_biz_area_id", Datas.haihe_biz_area_id);
					    setEntpInfoData(openWindow, "owner_type_id", Datas.owner_type_id);
					    setEntpInfoData(openWindow, "biz_travel_id", Datas.biz_travel_id);
					    setEntpInfoData(openWindow, "imp_industry_id", Datas.imp_industry_id);
					    setEntpInfoData(openWindow, "imp_flow_id", Datas.imp_flow_id);
					    setEntpInfoData(openWindow, "biz_land_id", Datas.biz_land_id);
					    setEntpInfoData(openWindow, "star_level", Datas.star_level);
					    setEntpInfoData(openWindow, "entp_desc", Datas.entp_desc);
					    
						if(Datas.entpDetailInfoList.length > 1){
							var openTbodyData = $("#tbody_data", openWindow);
							var openTrData = $("#tr_data", openWindow);
							for (var i = 0; i < Datas.entpDetailInfoList.length -1; i++) {
								var curData = Datas.entpDetailInfoList[i];
								var trData = openTrData.clone();
								trData.find("#year").text(curData.year);
								trData.find("#type_name").text(curData.type_name);
								trData.find("#type_value").text(curData.type_value);
								trData.find("#unit").text(curData.unit);
								trData.appendTo(openTbodyData).show();
							}
							trMerge("trMergeYear", openTbodyData);//行合并
						}
						//alert(openWindow.html());
						setYmPromptWinMessage(openWindow.html());  
					}
			});
		 });
  	});
}

//清空网点信息
function clearMakersWithEntpInfo() {
	if (markers) {
		for (i in markers) {
			markers[i].setMap(null);
		}
		markers.length = [];
	}
}

function setOnlyCurrency() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "").bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = 0;
	});
}

//begin search for person
var circle;
var circleArrayForPerson = [];
var markerArrayForPerson = [];
function drawCircle(ctx){
	var radius = $.trim($("#radius_p").val());
	if("" == radius){
  		$("#radius").val("");
		alert("半径必须填写,且必须是正数");
		return false;
	}
	radius = Number(radius);
	if (isNaN(radius) || radius < 0) {
		alert("半径必须填写,且必须是正数");
		return false;
	}
	var location = new google.maps.LatLng(39.117691010918016,117.21476567370605);
	map.setCenter(location);
	if (markersArray) {//清空
    	for (i in markersArray) {
    		markersArray[i].setMap(null);
     	}
    	markersArray.length = 0;
	}
	var marker = new google.maps.Marker({
	      position: location, 
	  	  icon: ctx+'/images/traffic_info_type_10.png',
	      map: map,
	      draggable: true
	  });
	markersArray.push(marker);
	clearMap(circleArray);
	circle = new google.maps.Circle({
	        map: map,
			strokeColor: "#FF7575",
			strokeOpacity: 0.9,
			strokeWeight: 1.5,
			fillColor: "#FF95CA",
			fillOpacity: 0.3,
			radius: radius*1000
	});
	circle.bindTo('center', marker, 'position');
	circleArray.push(circle);
	$("#latlng_p").val(marker.getPosition().lat() + "," + marker.getPosition().lng());
	google.maps.event.addListener(marker, 'drag', function(i) {
		marker.setPosition(this.getPosition());
		var newPoint = this.getPosition().lat() + "," + this.getPosition().lng();
		$("#latlng_p").val(newPoint);
	});	
}

function selectPersonByAjax(ctx){
	var latlng_p = $("#latlng_p").val();
	var radius_p = $("#radius_p").val();
	if("" == radius_p){
  		$("#radius_p").val("");
		alert("半径必须填写,且必须是正数");
		return false;
	}
	radius_p = Number(radius_p);
	if (isNaN(radius_p) || radius_p < 0) {
		alert("半径必须填写,且必须是正数");
		return false;
	}
	if("" == latlng_p){
		latlng_p="39.22412419257877,117.13193905932617";
	}
	$("#loading").show();
	$.ajax({
		type: "POST",
		url: "Cs.do",
		data: "method=getPersonNum&latlng="+latlng_p+"&radius="+radius_p,
		dataType: "json",
		error: function(request, settings) {alert("数据加载请求失败！"); },
		success: function(datas) {
			clearMap(circleArrayForPerson);
			clearMap(markerArrayForPerson);
			var person_num_total = 0;
			var actual_pnum_total = 0;
			var personZbInfo = "";
			if (datas.length > 1) {
				for(var i = 0; i < datas.length - 1; i++) {
					var name = datas[i].name;
					var area_name = datas[i].area_name;
					var entp_latlng = datas[i].latlng;
					var radius = Number(datas[i].radius);
					var person_num = Number(datas[i].penson_num);
					var rate = Number(datas[i].rate);
					var actual_pnum = Number(datas[i].actual_pnum);
					var latlng = entp_latlng.split(",");
					var position = new google.maps.LatLng(latlng[0], latlng[1]);
					var marker_for_circle = new google.maps.Marker({
						map: map,
						position: position, 
					  	icon: ctx+'/images/cricle.png',
					  	title: name
					});
					markerArrayForPerson.push(marker_for_circle);
					var circle = new google.maps.Circle({
					    map: map,
						//strokeColor: "#FF7575",
						strokeOpacity: 0.9,
						strokeWeight: 1,
						//fillColor: "#284aff",
						fillOpacity: 0.3,
						radius: radius * 1000
					});
					circle.bindTo('center', marker_for_circle, 'position');
					circleArrayForPerson.push(circle);
					personZbInfo += "<tr>";
					personZbInfo += "<td>" + (i+1) + "</td>";
					personZbInfo += "<td>" + name + "</td>";
					personZbInfo += "<td>" + area_name + "</td>";
					//personZbInfo += "<td>" + entp_latlng + "</td>";
					//personZbInfo += "<td>" + radius + "</td>";
					personZbInfo += "<td>" + person_num + "</td>";
					personZbInfo += "<td>" + rate + "%</td>";
					personZbInfo += "<td>" + actual_pnum + "</td>";
					personZbInfo += "</tr>";
					person_num_total += person_num;
					actual_pnum_total += actual_pnum;
				}
				personZbInfo += "<tr>";
				personZbInfo += "<td><strong>合计</strong></td>";
				personZbInfo += "<td >-</td>";
				personZbInfo += "<td >-</td>";
				personZbInfo += "<td align='center'><strong>" + person_num_total.toFixed(2)+ "万人</strong></td>";
				personZbInfo += "<td >-</td>";
				personZbInfo += "<td align='center'><strong>" + actual_pnum_total.toFixed(2) + "万人</strong></td>";
				personZbInfo += "</tr>";
			} else {
				personZbInfo += "<tr>";
				personZbInfo += "<td colspan='6'><span style='color:#f00;'>没有数据</span></td>";
				personZbInfo += "</tr>";
			}

			$("#personNumList").empty().append(personZbInfo);
			$("#divPersonDensity").dialog({
				width: 640,
				height: 300,
				buttons : {"关闭" : function() {$(this).dialog("close");}},
				show: 'blind',
				hide: 'blind',
				// modal : true
				position: ['right','bottom']
			}).dialog("open");
			$("#loading").hide();
		}
	});
}

function changeCircleCenter(position){
	circle.setCenter(position);
}


function clearMap(circleArray){
	 if (circleArray) {//清空
	    	for (i in circleArray) {
	    		circleArray[i].setMap(null);
	     	}
	    circleArray.length = 0;
	  }
}

function judgeSelectedValueIsRepeat(objId, parObj){
	var isRepeat = false;
	var ary = new Array();
	$("#" + objId, parObj).each(function() {
		if ("" != $(this).val()) {
			ary.push($(this).val());
		}
	});
	var nary = ary.sort();
	for(var i = 0; i < nary.length - 1; i++) {
		if (nary[i] == nary[i + 1]) {
			isRepeat = true;
			break;
		}  
    }
	return isRepeat;
}

/**
 * @author Wu,ShangLong
 * @version 2012-01-12
 * @param num 需要转换的数字型金额
 * @desc 数字型金额转换成中文大写金额
 */
function toUpperRMB(num)  //将阿拉伯数字翻译成中文的大写的金额
{
    if(!/^\d*(\.\d*)?$/.test(num)) throw(new Error(-1, "Number is wrong!"));

    var AA = new Array("零","壹","贰","叁","肆","伍","陆","柒","捌","玖");
    var BB = new Array("","拾","佰","仟","萬","億","圆","");
    var CC = new Array("角", "分", "厘");
    
    var a = (""+ num).replace(/(^0*)/g, "").split("."), k = 0, re = "";

    for(var i=a[0].length-1; i>=0; i--)  //author: meizz
    {
        switch(k)
        {
            case 0 : re = BB[7] + re; break;
            case 4 : if(!new RegExp("0{4}\\d{"+ (a[0].length-i-1) +"}$").test(a[0]))
                     re = BB[4] + re; break;
            case 8 : re = BB[5] + re; BB[7] = BB[5]; k = 0; break;
        }
        if(k%4 == 2 && a[0].charAt(i)=="0" && a[0].charAt(i+2) != "0") re = AA[0] + re;
        if(a[0].charAt(i) != 0) re = AA[a[0].charAt(i)] + BB[k%4] + re; k++;
    }

    if(a.length>1) //加上小数部分(如果有小数部分)
    {
        re += BB[6];
        for(var i=0; i<a[1].length; i++)
        {
          re += AA[a[1].charAt(i)] + CC[i];
          if(i==2) break;
        }
    } else {
    	re += "圆整";
    }
   return re;
}
//end