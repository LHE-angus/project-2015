<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{height:700px;width:90%;left:5%;top:5%}     
		#r-result{width:100%;}     
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=647d1ae13c913717f13a9a8a6a9d6de2"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/convertor2.js"></script> 
	<title>图块加载完毕</title>  
</head>
<body>
	<div id="allmap"></div>  
	<div style="margin-top: 80px;margin-right: auto; margin-left: auto;text-align: center; "><input type="button" value="返回" onclick="history.back();return false;"  /> </div>
 <script type="text/javascript">
//百度地图API功能
	    var lng = '${af.map.lng}';
		var lat = '${af.map.lat}';
		var map = new BMap.Map("allmap");
		var center_point1 = new BMap.Point(lng,lat); // 创建点坐标
		map.centerAndZoom(center_point1,15);           // 初始化地图，设置中心点坐标和地图级别。
		map.addControl(new BMap.NavigationControl());      // 添加控件
		map.enableScrollWheelZoom();
		map.enableContinuousZoom(); 

		//坐标转换完之后的回调函数
		translateCallback = function (point){  
			var center_point = new BMap.Point(point.lng,point.lat);
			map.centerAndZoom(center_point,15);     
			var marker = new BMap.Marker(center_point);
			map.addOverlay(marker); 
			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画      

			var gc = new BMap.Geocoder();
			var addr = "";  
			gc.getLocation(center_point, function(rs){
				var addComp = rs.addressComponents;
				addr = addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
			});     //真实经纬度转成百度坐标     
			setTimeout(function(){
				var opts = {
				  width : 200,     // 信息窗口宽度
				  height: 100,     // 信息窗口高度
				  title : "上报人的位置：" , // 信息窗口标题
				  enableMessage:false,//设置允许信息窗发送短息
				  message:"XXXX"
				};
				var infoWindow = new BMap.InfoWindow(addr, opts);  // 创建信息窗口对象 
				marker.addEventListener("click", function(){          
					map.openInfoWindow(infoWindow,center_point); //开启信息窗口
				});  
			}, 2000);  
			
		};

		BMap.Convertor.translate(center_point1,0,translateCallback);   
 
</script>
</body>
</html>
