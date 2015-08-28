/*
 * Extended API for Google Maps v3
 *
 * @author  Wu,Yang
 * @version 2010-08-10
 *
 */
// LatLng
/******************************************************************************/
google.maps.LatLng.prototype.distanceFrom = function(latlng) {
	var lat = [this.lat(), latlng.lat()];
	var lng = [this.lng(), latlng.lng()]; //var R = 6371; // km (change this constant to get miles)
	var R = 6378137; // In meters
	var dLat = (lat[1] - lat[0]) * Math.PI / 180;
	var dLng = (lng[1] - lng[0]) * Math.PI / 180;
	var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat[0] * Math.PI / 180) * Math.cos(lat[1] * Math.PI / 180) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	var d = R * c;
	return Math.round(d);
} 
google.maps.LatLng.prototype.getMiddle = function(latlng) {
	var lat = (this.lat() + latlng.lat()) / 2;
	var lng = this.lng() - latlng.lng(); // Distance between
	// To control the problem with +-180 degrees.
	if (lng <= 180 && lng >= -180) {
		lng = (this.lng() + latlng.lng()) / 2;
	} else {
		lng = (this.lng() + latlng.lng() + 360) / 2;
	}
	return new google.maps.LatLng(lat, lng);
} 
// Marker
/******************************************************************************/
google.maps.Marker.prototype.distanceFrom = function(marker) {
	return this.getPosition().distanceFrom(marker.getPosition());
}
google.maps.Marker.prototype.getMiddle = function(marker) {
	return this.getPosition().getMiddle(marker.getPosition());
} 
// Polyline
/******************************************************************************/
google.maps.Polyline.prototype.deleteVertex = function(i) {
	this.getPath().removeAt(i);
}
google.maps.Polyline.prototype.getBounds = function() {
	var latlngBounds = new google.maps.LatLngBounds();
	var path = this.getPath();
	for (var i = 0; i < path.getLength(); i++) {
		latlngBounds.extend(path.getAt(i));
	}
	return latlngBounds;
}
google.maps.Polyline.prototype.getLength = function() {
	var d = 0;
	var path = this.getPath();
	var latlng;
	for (var i = 0; i < path.getLength() - 1; i++) {
		latlng = [path.getAt(i), path.getAt(i + 1)];
		d += latlng[0].distanceFrom(latlng[1]);
	}
	return d;
}
google.maps.Polyline.prototype.getVertex = function(i) {
	return this.getPath().getAt(i);
}
google.maps.Polyline.prototype.getVertexCount = function() {
	return this.getPath().getLength();
}
google.maps.Polyline.prototype.getVisible = function() {
	return (this.getMap()) ? true: false;
}
google.maps.Polyline.prototype.insertVertex = function(i, latlng) {
	this.getPath().insertAt(i, latlng);
}
google.maps.Polyline.prototype.lastMap = false;
google.maps.Polyline.prototype.setVertex = function(i, latlng) {
	this.getPath().setAt(i, latlng);
}
google.maps.Polyline.prototype.setVisible = function(visible) {
	if (visible === true && !this.getVisible()) {
		this.setMap(this.lastMap);
	} else if (visible === false && this.getVisible()) {
		this.lastMap = this.getMap();
		this.setMap(null);
	}
} 
// Polygon
/******************************************************************************/
google.maps.Polygon.prototype.deleteVertex = function(i) {
	this.getPath().removeAt(i);
}
google.maps.Polygon.prototype.getBounds = function() {
	var latlngBounds = new google.maps.LatLngBounds();
	var path = this.getPath();
	for (var i = 0; i < path.getLength(); i++) {
		latlngBounds.extend(path.getAt(i));
	}
	return latlngBounds;
}
google.maps.Polygon.prototype.getPerimeter = function() {
	var d = 0;
	var path = this.getPath();
	var latlng, first;
	if (path.getLength()) {
		first = path.getAt(1);
	}
	for (var i = 0; i < path.getLength(); i++) {
		if (i < path.getLength() - 1) {
			latlng = [path.getAt(i), path.getAt(i + 1)];
		} else {
			if (first == path.getAt[i]) {
				break;
			} else {
				latlng = [path.getAt(i), path.getAt(0)];
			}
		}
		d += latlng[0].distanceFrom(latlng[1]);
	}
	return d;
}
google.maps.Polygon.prototype.getVertex = function(i) {
	return this.getPath().getAt(i);
}
google.maps.Polygon.prototype.getVertexCount = function() {
	var path = this.getPath();
	var length = path.getLength();
	if (!path.getAt(0).equals(path.getAt(length - 1))) {
		return length;
	} else {
		return length - 1;
	}
}
google.maps.Polygon.prototype.getVisible = function() {
	return (this.getMap()) ? true: false;
}
google.maps.Polygon.prototype.insertVertex = function(i, latlng) {
	this.getPath().insertAt(i, latlng);
}
google.maps.Polygon.prototype.lastMap = false;
google.maps.Polygon.prototype.setVertex = function(i, latlng) {
	this.getPath().setAt(i, latlng);
}
google.maps.Polygon.prototype.setVisible = function(visible) {
	if (visible === true && !this.getVisible()) {
		this.setMap(this.lastMap);
	} else if (visible === false && this.getVisible()) {
		this.lastMap = this.getMap();
		this.setMap(null);
	}
}

google.maps.Polygon.prototype.getArea = function() {
	var area = 0;
	var path = this.getPath();
	var i, j;
	var N = path.getLength();
	for (i = 0; i < N; i++) {
		j = (i + 1) % N;
		area += path.getAt(i).lat() * path.getAt(j).lng();
		area -= path.getAt(i).lng() * path.getAt(j).lat();
	}
	area /= 2;
	area = area * 10000;
	return Math.abs(area);
	//	var area = 0;
	//	var path = this.getPath();
	//	if (path.getLength() >= 3) {
	//		p1 = path.getAt(0);
	//		for (var i = 1; i < path.getLength() - 1; i++) {
	//			p2 = path.getAt(i);
	//			p3 = path.getAt(i + 1);
	//			area += ((p1.lat() * p2.lng() + p2.lat() * p3.lng() + p3.lat() * p1.lng()) - (p1.lat() * p3.lng() + p2.lat() * p1.lng() + p3.lat() * p2.lng())) / 2;
	//		}
	//		area = area * 10000;
	//	}
	//	return Math.abs(area);
}

function FC(x1, x2) {
	if (x1 - x2 < 0.000002 && x1 - x2 > -0.000002) {
		return 1;
	} else {
		return 0;
	}
}
/*
* 判断点是否在线上
* 参数
* p1、p2：线段的两个端点
* p：	被判断点
* 返回值：	false：点在不在线段上；true：点在线段上
*/
function isPointOnLine(p1, p2, p) {
	var x1, y1, x2, y2;
	x1 = p.lat() - p1.lat();
	x2 = p2.lat() - p1.lat();
	y1 = p.lng() - p1.lng();
	y2 = p2.lng() - p1.lng();
	if (FC(x1 * y2 - x2 * y1, 0) == 0) {
		return false;
	}
	if ((Math.min(p1.lat(), p2.lat()) <= p.lat() && p.lat() <= Math.max(p1.lat(), p2.lat())) && (Math.min(p1.lng(), p2.lng()) <= p.lng() && p.lng() <= Math.max(p1.lng(), p2.lng()))) {
		return true;
	} else {
		return false;
	}
}
/*
* 射向法判断点是否在多边形内部
* 参数
* latLngPoints 多边形顶点 
* latLngPoint： 被判断点
* 返回值：	false：点不在多边形内；true：点在多边形内或边上
*/
function isPointInPolygon() {

	var latLngPoints = [];
	var latLngPoint;

	if (markersArray) {
		for (i in markersArray) {
			var point = markersArray[i].getPosition();
				latLngPoints.push(point);			
		}
	}

	if (null != markerPoint) {
			var point = markerPoint.getPosition();
			latLngPoint = point;
	}

	if (null == latLngPoints || latLngPoints.length == 0 || null == latLngPoint) {
		return -1;
	}
	var counter = 0;
	var i;
	var xinters;
	var p1 = null;
	var p2 = null;
	var isPointOnLineFlag = false;
	p1 = latLngPoints[0];
	for (i = 1; i <= latLngPoints.length; i++) { //p2 = latLngPoints + (i % nCount);
		p2 = latLngPoints[i % latLngPoints.length];
		if (isPointOnLine(p1, p2, latLngPoint)) {
			isPointOnLineFlag = true;
		}
		if (latLngPoint.lng() > Math.min(p1.lng(), p2.lng())) {
			if (latLngPoint.lng() <= Math.max(p1.lng(), p2.lng())) {
				if (latLngPoint.lat() <= Math.max(p1.lat(), p2.lat())) {
					if (p1.lng() != p2.lng()) {
						xinters = (latLngPoint.lng() - p1.lng()) * (p2.lat() - p1.lat()) / (p2.lng() - p1.lng()) + p1.lat();
						if ((p1.lat() == p2.lat()) || (latLngPoint.lat() < xinters) || (latLngPoint.lat() == xinters)) {
							counter++;
						}
					}
				}
			}
		}
		p1 = p2;
	}
	if (isPointOnLineFlag) {
		//$("#relation").html("<b color='blue'>点在边上</b>");
		return true;
	}
	if (counter % 2 == 0) {
		//$("#relation").html("<b color='red'>点在多边形外</b>");
		return false;
	} else {
		//$("#relation").html("<b color='green'>点在多边形内</b>");
		return true;
	}
}

// Define the overlay, derived from google.maps.OverlayView
function Label(opt_options) { // Initialization
	this.setValues(opt_options); // Label specific
	var span = this.span_ = document.createElement('span');
	span.style.cssText = 'position: relative; left: 0%; top: -8px; ' + 'white-space: nowrap; border: 0px; font-family:arial; font-weight:bold;' + 'padding: 2px; background-color: #ddd; ' + 'opacity: .75; ' + 'filter: alpha(opacity=75); ' + '-ms-filter: "alpha(opacity=75)"; ' + '-khtml-opacity: .75; ' + '-moz-opacity: .75;';
	var div = this.div_ = document.createElement('div');
	div.appendChild(span);
	div.style.cssText = 'position: absolute; display: none';
};
Label.prototype = new google.maps.OverlayView; // Implement onAdd
Label.prototype.onAdd = function() {
	var pane = this.getPanes().overlayLayer;
	pane.appendChild(this.div_); // Ensures the label is redrawn if the text or position is changed.
	var me = this;
	this.listeners_ = [google.maps.event.addListener(this, 'position_changed',
	function() {
		me.draw();
	}), google.maps.event.addListener(this, 'text_changed',
	function() {
		me.draw();
	})];
}; // Implement onRemove
Label.prototype.onRemove = function() {
	this.div_.parentNode.removeChild(this.div_); // Label is removed from the map, stop updating its position/text.
	for (var i = 0,
	I = this.listeners_.length; i < I; ++i) {
		google.maps.event.removeListener(this.listeners_[i]);
	}
}; // Implement draw
Label.prototype.draw = function() {
	var projection = this.getProjection();
	var position = projection.fromLatLngToDivPixel(this.get('position'));
	var div = this.div_;
	div.style.left = position.x + 'px';
	div.style.top = position.y + 'px';
	div.style.display = 'block';
	if (null == this.get('text').toString() || "" == this.get('text').toString()) {
		this.span_.style.cssText = "";
		this.span_.innerHTML = "";
	} else {
		this.span_.innerHTML = this.get('text').toString();
	}
};
