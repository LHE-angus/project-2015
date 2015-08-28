<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script type="text/javascript">//<![CDATA[
//全局变量
var G_DelaySeconds = 500; //延时毫秒数, 设置此项为了减少客户端对服务器的请求次数，同时增强客户端用户体验，此项过大或过小均不好。
var G_timer; // 地图缩放或拖动的计时器
var G_FirstLoader = true; // 首次加载地图
var G_marks = []; // 全局GMark数组
var defaultMapZoom = 8; //地图默认缩放级别8
var A_Z_letters = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
// 全局变量结束
function loadGMap(map) {
	if (GBrowserIsCompatible()) {
		map.enableScrollWheelZoom(); // 开启鼠标滚轮放大功能   
		map.addControl(new GLargeMapControl()); // 添加一个带有可在四个方向平移、放大、缩小的按钮以及缩放滑块的控件。   
		map.addControl(new GMapTypeControl());  // 添加一个地图类型的控制条（位于右上方）   
		// map.addControl(new GOverviewMapControl());  // 在主地图的一角创建可折叠的迷你型概览地图，以便通过拖动提供位置参考和导航。GOverviewMapControl 创建单像素黑边界的概览地图。注意：与其他控件不同，您只能将此控件放在地图的右下角 (G_ANCHOR_BOTTOM_RIGHT)。   
		map.addControl(new GScaleControl()); // 创建显示地图比例尺的控件。   

		setMapCenterByPIndex(map, "${empty af.map.p_index ? 0 : af.map.p_index}");
		
		return map;
	}
}

//1.地图上添加网点图标
function resetGMarkersOfEntpShopToMap(map, entp_shop_list_json){
	map.closeInfoWindow();
	map.clearOverlays();
	var fisrtEntpShop, sureFirstMarker = false;

	var baseIcon = new GIcon(G_DEFAULT_ICON); 
	baseIcon.shadow = "http:\/\/www.google.cn\/mapfiles\/shadow50.png"; 
	baseIcon.iconSize = new GSize(20, 34); 
	baseIcon.shadowSize = new GSize(37, 34); 
	baseIcon.iconAnchor = new GPoint(9, 34); 
	baseIcon.infoWindowAnchor = new GPoint(9, 2); 

	for (var i = 0; i < entp_shop_list_json.length; i++) {
		var cur = entp_shop_list_json[i];
		
		if ($.trim(cur.g_lat).length == 0 || $.trim(cur.g_lat) == 'null') {
			continue;
		}
	
		if (!sureFirstMarker) {
			fisrtEntpShop = cur;
			sureFirstMarker = true;		
		}

		var letter = i > 25 ? "" : A_Z_letters[i];
		 
		var letteredIcon = new GIcon(baseIcon);
		letteredIcon.image = "http:\/\/www.google.cn\/mapfiles\/marker" + letter + ".png";

		letteredIcon.shadowSize = new GSize(0, 0);
		
		var latlng = new GLatLng(parseFloat($.trim(cur.g_lat)), parseFloat($.trim(cur.g_lng)));
		
		var gMarker = new GMarker(latlng, { icon:letteredIcon  });

		var entp_shop = {};
		$.extend(entp_shop, cur);
		gMarker.entp_shop = entp_shop;
		gMarker.letter = letter;
		
		map.addOverlay(gMarker);
		G_marks[letter] = gMarker;

		// 为每一个gMarker标记绑定click事件
		GEvent.addListener(gMarker, "click", function(latlng) {
            var lat = latlng.lat();
            var lng = latlng.lng();

            // 获取商铺对象
            var entp_shop = this.entp_shop;
            var $openWindow = $("#box").clone().attr("id", "").show();

            if ($.isBlank(entp_shop.shop_name)) entp_shop.shop_name = "未填写";

            $openWindow.find("#shop_name").text(entp_shop.shop_name).attr("href", $openWindow.find("#shop_name")[0].href.replace("000000000", entp_shop.shop_id));
            
            var $jyqk = $openWindow.find("#shop_jyqk");
            var a_tag = "<a  title=\"查看经营情况\" href=\"\" onclick=\"sellAnalysisSubmit('bottomPageForm','page_id=EntpShopSearch5W&shop_id="+entp_shop.shop_id+"');\">经营情况</a>";
            $jyqk.append(a_tag);
            if (entp_shop.g_is_audit == 3) {
            	$openWindow.find("#g_is_audit").text('该网点地理位置需要再次审核！');
            } else if (entp_shop.g_is_audit == 1) {
            	$openWindow.find("#g_is_audit").text('该网点地理位置审核不通过！');
            } else if (entp_shop.g_is_audit == 2) {
            	$openWindow.find("#g_is_audit").text('该网点地理位置审核通过！');
            } else {
            	$openWindow.find("#g_is_audit").text('该网点地理位置待审核！');
            }
            
			var $shopMarks = $openWindow.find("#shop-marks");	
			var __ctx = "${ctx}";		
            if ("1" == entp_shop.is_sall) {
            	$shopMarks.append("<img src=\"" + __ctx + "\/images\/marks\/xs.jpg\" title=\"该商铺为销售网点\" style=\"margin-left:8px;\" \/>");
            }
            if ("1" == entp_shop.is_rural) {
            	$shopMarks.append("<img src=\"" + __ctx + "\/images\/marks\/jdxx.jpg\" title=\"该商铺为家电下乡指定网点\" style=\"margin-left:8px;\" \/>");
            }
            if ("1" == entp_shop.is_otn && "1" == entp_shop.is_sall) {
            	$shopMarks.append("<img src=\"" + __ctx + "\/images\/marks\/yjhxxs.jpg\" title=\"该商铺为以旧换新销售指定网点\" style=\"margin-left:8px;\" \/>");
            }
            if ("1" == entp_shop.is_maint) {
            	$shopMarks.append("<img src=\"" + __ctx + "\/images\/marks\/shop-xiu.gif\" title=\"该商铺为维修网点\" style=\"margin-left:8px;\" \/>");
            }
            if ("1" == entp_shop.is_otn && "1" == entp_shop.is_callb) {
            	$shopMarks.append("<img src=\"" + __ctx + "\/images\/marks\/yjhxhs.jpg\" title=\"该商铺为以旧换新回收指定网点\" style=\"margin-left:8px;\" \/>");
            }

            // qq
            entp_shop.online_qqs = entp_shop.online_qq.replace("，", ",").split(",");

            var $qqWrap = $("<div><\/div>");
            var $qq_templete = $openWindow.find(".qq_a").eq(0);
            for (var j = 0; j < entp_shop.online_qqs.length; j++) {
                var qq_code = entp_shop.online_qqs[j];
                if ($.isNotBlank(qq_code)) {
            		var $qq = $qq_templete.clone().show();
            		$qqWrap.append($qq.attr("href", $qq[0].href.replace("qq_code", qq_code))).append("&nbsp;&nbsp;");
                }
            }
            $openWindow.find("#qq_list").empty().append($qqWrap);

            //获取商铺的其它相关
        	
			map.openInfoWindow(latlng, $openWindow[0]);
        });
	}

	var $Container = writeDisplayEntpShopListHtml("${ctx}", entp_shop_list_json);

	// 为每一个新添加的商铺HTML列表中的LI元素绑定click事件
	$Container.find("li").click(function() {
		var lat = $(this).attr("g_lat"), lng = $(this).attr("g_lng");
		var latlng = new GLatLng(parseFloat(lat), parseFloat(lng));
		var letter = $(this).attr("id").replace("li_", "");
		
		if(!/^\d+\.\d+$/.test(lat) || !/^\d+\.\d+$/.test(lng)) {
			alert("很抱歉，该店铺还没有指定地理位置！");
			return;
		}

		// 显示地图
		map.panTo(latlng);

		// 触发标记click事件
		GEvent.trigger(G_marks[letter], "click", latlng);
	});

	// default panto 默认是地图中心定位到到第一个商铺所在位置， 暂时关闭
	if (fisrtEntpShop && sureFirstMarker != false) {
		var defalutPoint = new GLatLng(parseFloat(fisrtEntpShop.g_lat), parseFloat(fisrtEntpShop.g_lng));
		  
        window.setTimeout(function() {
        	map.panTo(defalutPoint);
        }, 1000);

		//以首个地图上网点的图标为中心
		map.setCenter(defalutPoint, defaultMapZoom); 
	}
}

/**
 * 在地图中添加标记
 */
function resetGMarkersToMap(map, options) {
	var s_time = new Date();

	var requestPageSize = $("#pageSize").val();
	requestPageSize = /^\d+$/g.test(requestPageSize) ? requestPageSize : 26;
	$("#shop_disp").empty().append("<li style='padding:50px 0;border-bottom:none;text-align:center;'>" + ($("#loading").clone().show()).html() + "<\/li>");
	$("#pager_control").pager("${ctx}/manager/admin/EntpShopSearch5W.do", $.extend({}, {
		method		: 'ajaxGetEntpShopJson'
	}, options), $.extend({}, {
		showPagesCount : 5,	// 共显示页面下标数
		pageSize : requestPageSize // 每页显示数据条数
	}, options), function(ret) {
		$("#count_sum").text(ret.count);// 设置查询结果数
		// $(".glatlng").val("");// 清空经纬度缓存
				
		// List
		resetGMarkersOfEntpShopToMap(map, ret.list);
		var e_time = new Date();
		$("#exc_time").text("共耗时 " + (e_time.getTime() - s_time.getTime())/(1000) + " 秒.");

		// 网点销售额柱状图
		var shop_list_ids = ret.shop_list_ids;
		openColumn3D(shop_list_ids);
	});
}

/**
 * 将地图定位到China中心，显示China版图
 */
function resetMapToChina(map) {
	var center = new GLatLng(36.3002752813443, 104.677734375);
	map.setCenter(center, defaultMapZoom);
	map.panTo(center);

	setAddressOfMapCenter(map);
	
	G_FirstLoader = false;// 默认首次加载为打开中国版图全图,首次执行resetMapToChina即置G_FirstLoader为false。
}

/**
 * 设置地图中心位置的当地地理位置名称
 */
function setAddressOfMapCenter(map){
	var center = map.getCenter();

	if (map.getZoom() < 8) {
		$("#current_city").text("全国");
		return;
	}

	var geocoder = new GClientGeocoder();
	geocoder.getLocations(center, function(response) {
		if (!response || response.Status.code != 200) { 
			// alert("Status Code:" + response.Status.code); 
			$("#current_city").text("未定义区域");
		} else {
			place = response.Placemark[0];
			$("#current_city").text(place.address.replace(/^中国/, ''));
		}
		return;
	});
}

/**
 * 根据地区代码设置地图中心点位置，缩放级别按照地区代码级别而定
 */
function setMapCenterByPIndex(map, p_index) {
	
	if (/^\s*$/.test(p_index)) return; // if p_index is blank.
	
	$.post("${ctx}/manager/admin/EntpShopSearch5W.do", {method : 'ajaxGetPIndexNamesStr', p_index : p_index}, function(ret){
		if (!/^\s*$/.test(ret)) {
			if ("error" === ret) {alert("很抱歉，查询出错！"); return;}

			if (p_index == 0) {
				resetMapToChina(map);
				return;
			}

			var address = ret;
			var geocoder = new GClientGeocoder();
			if (geocoder) {
				geocoder.getLatLng(address, function(point) {
					if (!point) {
						alert("不能解析: " + address);
						return;
					} else {
						map.setCenter(point, /\d{2}0000$/g.test(p_index) ? 8 : (/\d{4}00$/g.test(p_index) ? 11 : 13));
						map.panTo(point);
						
						setAddressOfMapCenter(map);
						return;
					}
				});
			}
		}
	});
}

/**
 * 用HTML输出查询出来的商铺列表
 */
function writeDisplayEntpShopListHtml(ctx, entp_shop_list){
	var $container = $("#shop_disp").empty();

	// 循环生成LI标签元素
	for (var i = 0; i < entp_shop_list.length; i++) {
		var cur = entp_shop_list[i];
		var $li = $(".templete").clone();
		var letter = i > 25 ? "" : A_Z_letters[i];
		
		if ($.trim(cur.g_lat).length == 0 || $.trim(cur.g_lat) == 'null') {
			$li.find("span").text("* "+cur.shop_name);
			$li.find("span")[0].style.color = "red";
		}else{
			$li.find("span").text(letter+" "+cur.shop_name);
		}
				
		if (!!cur.log_pic) {
			$li.find("img").attr("src", ctx + "\/" + cur.log_pic);
		}
		
		$li.attr("g_lat", cur.g_lat).attr("g_lng", cur.g_lng).attr("id", "li_" + letter);
		$li.hover(function() {
			$(this).css("cursor", "pointer");
			$(this).css("background-color", "#eee");
		}, function() {
			$(this).css("background-color", "");		
		});
				
		$li.removeClass("templete").show();
		$container.append($li);
	}

	if (entp_shop_list.length == 0) {
		$container.append("<li><span style=\"color:#F00;padding:20px 0;display:block;\">很抱歉！没有符合您的查询结果！</span></li>");
	}
	
	return $container;
}

var img_flg = 0;
var sub_src = "../../images/manager/arrow_";
var img_arr = ["right.gif","left.gif","","none","隐藏列表","显示列表"];
var map_width = $("#map").width();
var shop_list_width = $("#shop_disp_div").width();
var imgobj = document.getElementById("imgMap");
function hidList(){
	img_flg = 1 - img_flg;
	imgobj.src = sub_src+img_arr[img_flg];
	imgobj.alt = img_arr[img_flg+4];
	document.getElementById("shop_disp_td").style.display = img_arr[img_flg+2];
	$("map").width(map_width + img_flg*shop_list_width - 12);
	window.resize();
}

var chart_flg = 0;
var chart_arr = ["","none","隐藏销售额柱状图","显示销售额柱状图"];
function hidchart(obj){
	chart_flg = 1 - chart_flg;
	var chart_tr = document.getElementById("chart_tr");
	chart_tr.style.display = chart_arr[chart_flg];
	obj.innerHTML = chart_arr[chart_flg+2];
}

// 销售额柱状图
function openColumn3D(shop_id_list){
	  if(shop_id_list != "" ){
		var chart = new FusionCharts("${ctx}/manager/scripts/Charts/Column3D.swf", "ChartId","100%", "400", "0", "0");
		var dataStr = "EntpShopSellAnalysis.do?method=column3D&shop_id_list="+shop_id_list;
		$.ajax({
		    type: "POST",
		    url: dataStr,
		    error: function(request, settings) {alert("数据加载请求失败"); },
		    success: function(data) {
		        if (data != ''){
		            chart.setDataXML(data);
		            chart.render("chartdiv");
		        }
		    }
		});
	  }
}

//]]></script>