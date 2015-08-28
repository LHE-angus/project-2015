
/*
 * Extended API for Google Maps v3
 *
 * @author  Wu,Yang
 * @version 2010-09-06
 * 
 * 首先引入google map js需要先定义一些参数
 * var map;
 * var markerForGeocode;
 * var markersArrayForGeocode = [];
 * var infowindowLevelForGeocode = 0;
 * var geocoderForGeocode = new google.maps.Geocoder();
 * 页面：
 * <div> 
 * <span style="font-weight: bold">关键字:</span> 
 * <input type="text" size="60" id="query"/> 
 * <input type="button" value="查 询" onclick="submitQuery()" class="bgButton" /> 
 * </div> 
 * <div id="results"></div>
 * paras 可以传一些自定义的参数，完成自定义的需求
 */
var markerForGeocode;
var infowindowLevelForGeocode = 0;
var markersArrayForGeocode = [];
var infowindowsArrayForGeocode = [];
var geocoderForGeocode = new google.maps.Geocoder();

function submitQuery(paras) {
	var query = $("#query").val();
	if (/\s*^\-?\d+(\.\d+)?\s*\,\s*\-?\d+(\.\d+)?\s*$/.test(query)) {
		var latlng = parseLatLng(query);
		if (latlng == null) {
			$("#query").val("");
		} else {
			geocode({
				'latLng': latlng
			});
		}
	} else {
		geocode({
			'address': query
		});
	}
	if (paras) {
		if (paras.showInfo) {
			$("#results").after("<div style='color:#ff6000;' align='left'>提示：如果找到你需要的位置，可将<span style='color:blue;font-weight:bold;'>蓝色圆点</span>拖至<span style='color:red;font-weight:bold;'>红色圆点</span>出</div>");
		}
	}
}

function geocode(request) {
	clearOverlaysForGeocode();
	if (request.latLng) {
		markerForGeocode = new google.maps.Marker({
			'position': request.latLng,
			'map': map,
			'title': request.latLng.toString()
		});
		if (null == markerForGeocode) {
			alert("no point!");
		} else {
			markersArrayForGeocode.push(markerForGeocode);
			map.setCenter(request.latLng);
		}
	} else {
		geocoderForGeocode.geocode(request, showResults);
	}
}

function showResults(results, status) {
	var reverse = (markerForGeocode != null);
	if (!results) {
		alert("Geocoder did not return a valid response");
	} else {
		if (status == google.maps.GeocoderStatus.OK) {
			plotMatchesOnMap(results, reverse);
		}
	}
}

function plotMatchesOnMap(results, reverse) {
	$("#results").empty();
	for (var i = 0; i < results.length; i++) {
		var marker_g = new google.maps.Marker({
			'position': results[i].geometry.location,
			'map': map
		});
		var address = results[i].formatted_address;
		attachInfowindowForG(marker_g, address);
		$("#results").append("<a href='#' id=span_" + i + ">" + address + "</a><br/>");
		addMarkerTrigger($("#span_" + i), marker_g);
		markersArrayForGeocode.push(marker_g);
		if (i == 0) {
			map.setCenter(results[i].geometry.location);
		}
	}
}

function addMarkerTrigger(obj, markerForGeocode) {
	$(obj).click(function() {
		google.maps.event.trigger(markerForGeocode, "click");
		map.setCenter(markerForGeocode.getPosition);
	});
}

function attachInfowindowForG(markerForGeocode, htmlData) {
	var infowindow = new google.maps.InfoWindow({
		content: htmlData
	});
	google.maps.event.addListener(markerForGeocode, 'click',
	function() {
		infowindow.setZIndex(++infowindowLevelForGeocode);
		infowindow.open(map, markerForGeocode);
	});
	infowindowsArrayForGeocode.push(infowindow);
}
//格式化 坐标
function parseLatLng(value) {
	value.replace('/\s//g');
	var coords = value.split(',');
	var lat = parseFloat(coords[0]);
	var lng = parseFloat(coords[1]);
	if (isNaN(lat) || isNaN(lng)) {
		return null;
	} else {
		return new google.maps.LatLng(lat, lng);
	}
}

function clearOverlaysForGeocode() {
	$("#results").empty();
	if (markersArrayForGeocode) {
	   for (i in markersArrayForGeocode) {
		   markersArrayForGeocode[i].setMap(null);
	   }
	   markersArrayForGeocode.length = 0;
	}
	if (infowindowsArrayForGeocode) {
		for (i in infowindowsArrayForGeocode) {
			infowindowsArrayForGeocode[i].close();
		}
		infowindowsArrayForGeocode.length = 0;
	}
}