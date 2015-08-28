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
<link href="${ctx}/styles/EntpShopGSite/fonts.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/EntpShopGSite/main.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/EntpShopGSite/map.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/EntpShopGSite/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap" style="text-align:left;">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
 <div class="rtabcont1" style="background-image:url('${ctx}/images/entpshopgsite/images/ssuo_3.gif');background-repeat: repeat-x; 
 margin:0px;padding:0px;">
   <form id="searchForm" style="text-align:left;">
   		<input type="hidden" name="lat_max" id="lat_max" class="glatlng" />
		<input type="hidden" name="lng_max" id="lng_max" class="glatlng" />
		<input type="hidden" name="lat_min" id="lat_min" class="glatlng" />
		<input type="hidden" name="lng_min" id="lng_min" class="glatlng" />
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td width="34%" align="center"><input name="search_txt" type="text" class="ssou" id="search_txt" maxlength="32" /></td>
         <td width="8%" height="45" align="center"><input name="button" type="button" class="ssoumenu" id="search_btn" value="立刻搜索" /></td>
         <td width="30%" align="left" nowrap="nowrap">
          <span class="sred">
            <a href="javascript:void();" id="show_searchbox">高级搜索 点击展开↓</a>
            <a href="javascript:void();" style="display:none;" id="hide_searchbox">高级搜索 点击收起↑</a>
          </span>
         </td>
      </tr>
     </table>
	<div id="search_box" style="display:none;margin-left:10px;height:40px;overflow:hidden;">
		选择城市:
		<select name="province" id="province">
		  <option value="">-请选择省/直辖市/自治区-</option>
		</select>
		<select name="city" id="city">
		  <option value="">-请选择市-</option>
		</select>
		<select name="country" id="country">
		  <option value="">-请选择县-</option>
		</select><br/>
		网点类型:
		<select name="is_rural" id="is_rural">
		    <option value="">家电下乡?</option>
		    <option value="1">家电下乡-是</option>
		    <option value="0">家电下乡-否</option>
		</select>
		<select name="is_otn" id="is_otn">
		    <option value="">以旧换新?</option>
		    <option value="1">以旧换新-是</option>
		    <option value="0">以旧换新-否</option>
		</select>
		<select name="is_sall" id="is_sall">
		    <option value="">销售网点?</option>
		    <option value="1">销售网点-是</option>
		    <option value="0">销售网点-否</option>
		</select>
		<select name="is_callb" id="is_callb">
		    <option value="">回收网点?</option>
		    <option value="1">回收网点-是</option>
		    <option value="0">回收网点-否</option>
		</select>
		<select name="is_maint" id="is_maint">
		    <option value="">维修网点?</option>
		    <option value="1">维修网点-是</option>
		    <option value="0">维修网点-否</option>
		</select>
		每页显示
		<select name="pageSize" id="pageSize">
		    <option value="26">默认</option>
		    <option value="10">10个</option>
		    <option value="20">20个</option>
		    <option value="50">50个</option>
		    <option value="100">100个</option>
		    <option value="200">200个</option>
		    <option value="500">500个</option>
		    <option value="1000">1000个</option>
		</select>
	</div>
</form>
</div>
<div class="rtabcont1" style="margin:0px;padding:0px;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:0px;">
        <tr>
           <td width="1%" class="oartop">&nbsp;</td>
           <td width="10%" valign="bottom" bgcolor="#FFFFFF" nowrap="nowrap" valign="middle">
             <table width="100%" border="0" cellspacing="0" cellpadding="5">
               <tr>
                 <td height="20" align="center" nowrap="nowrap" bgcolor="#FFFFFF" valign="bottom">
                    <span id="current_city" style="span-weight:700;">
                      <img src="${ctx}/images/ajax-loader.gif" />
                    </span>
                 </td>
               </tr>
            </table>
           </td>
           <td class="oartop" align="left" style="padding-left:20px;" valign="middle">共有
               <span id="count_sum">-</span> 个结果符合您的查询要求 
               (当前页直营网点<span id="count_B" style="color:red;">-</span>个,
                 经销网点(不含直营)<span id="count_A" style="color:red;">-</span>个,
                                          其他网点<span id="count_C" style="color:red;">-</span>个).
               <span id="exc_time"></span>
           </td>
        </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:1px;margin-bottom:0;">
	   <tr>
		<td align="left" valign="top"><!-- 地图 -->
		<div id="map" style="width: 100%; background: #f1f1f1; margin-right: 7px;"></div>
		</td>
		<td width="225" align="right" valign="top" style="padding-right: 1px;margin-right:0px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="225" height="27" align="center" valign="top"
					style="background-image:url('${ctx}/images/entpshopgsite/images/ssuo_right.gif');">
				<span class="sbrown">网点展示</span></td>
			</tr>
			<tr>
				<td align="left" valign="top" class="sbox">
				<div id="shop_disp_div" class="listDiv" style="position: absolute;">
				<ul id="shop_disp" class="pro_list lh30" style="overflow-y: auto; width: 213px;">

				</ul>
				<div id="pager_control"
					style="height: 32px; text-align: center; color: #666; position: absolute; bottom: 0; width: 100%;"></div>
				</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<div id="loading" style="display:none;text-align:center;"><img src="${ctx}/images/ajax-loader.gif" /> <span style="color:#999;">正在查询,请稍后...</span></div>
<div id="box">
  <table width="300" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="34">
      <img style="padding-left:8px;vertical-align:middle;" src="${ctx}/images/entpshopgsite/images/owin/jinpai.jpg" width="24" height="27" />
      <span style="padding-left:4px;vertical-align:middle;"><a id="shop_name" title="点击快速进入商铺" href="javascript:void(0);"></a></span>
      </td>
    </tr>
    <tr>
      <td valign="top">
           <table width="295" border="0" align="center" cellpadding="0" cellspacing="0">
             <tr>
        	   <td colspan="4"></td>
            </tr>
            <tr>
              <td colspan="4" style="padding-left:8px;padding-top:4px;text-align:left;" id="qq_list">
          	    <a class="qq_a" href="tencent://message/?uin=qq_code" style="display:none;">
          	      <img src="${ctx}/images/entpshopgsite/images/owin/QQonline.jpg" width="74" height="22" />
          	   </a>
              </td>
            </tr>
            <tr id="onsaleInfoBox" style="display:none;">
               <td colspan="4" style="text-align:center;" id="onsale_info" title=""></td>
            </tr>
            <tr id="productBox" style="display:none;">
              <td width="50" class="product">
                 <a class="imgWrap" href="javascript:void(0);">
                   <img id="img_0" width="46" height="45" />
                 </a>
              </td>
              <td width="95" class="product" style="color:#555;padding-left:5px;">
          		<a id="md_name_0" href="javascript:void(0);">&nbsp;</a>
          		<br />
          		<a id="price_0" href="javascript:void(0);">&nbsp;</a> 元
          	  </td>
              <td width="50" class="product" id="product3">
                <a class="imgWrap" href="javascript:void(0);">
                <img id="img_1" width="46" height="45" /></a>
              </td>
              <td width="95" class="product" style="color:#555;padding-left:5px;" id="product4">
          		<a id="md_name_1" href="javascript:void(0);">&nbsp;</a><br />
          		<a id="price_1" href="javascript:void(0);">&nbsp;</a> 元
          	  </td>
             </tr>
           </table>
      </td>
    </tr>
  </table>
</div>
<ul style="display:none;">
	<li class="templete" style="display:none;">
		<span class="sbluer"></span>
		<div class="img_wrap" style="display:none;"><img width="140" height="97" /></div>
	</li>
</ul>
</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.pager.js"></script>
<%@include file="__gmap.jsp" %>
<script type="text/javascript">//<![CDATA[
function setMapLayout($map) {
	$(".listDiv").height($(window).height() - $("#shop_disp_div").offset().top - 14);
	$(".sbox").height($("#shop_disp_div").height());
	$(".pro_list").height($("#shop_disp_div").height() - 40);
	$("#map").height($(window).height() - $map.offset().top - 13);
}

$(document).ready(function() {
	var $map = $("#map").css({"border-right":"1px solid #ccc", "border-top":"1px solid #ccc"});

	setMapLayout($map);
	
	$(window).resize(function() {
		setMapLayout($map);
	});

	$("#hide_searchbox").click(function() {
		$('#search_box').slideToggle();$(this).hide().prev().show();
	});
	$("#show_searchbox").click(function() {
		$('#search_box').slideToggle();$(this).hide().next().show();
	});
	
	var map = new GMap2($map[0]);

	// 下载地图
	loadGMap(map);

	// 省市县级联Select initializing.
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}", "datatype": "Require", "msg": "请选择省名称"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	$("#province").change();
	$("#province, #city, #country").change(function() {
		if (this.id == 'province') $("#city, #country").val("");
		
		var sub_val = $.trim($("#search_txt").val());
		// resetGMarkersToMap(map,  $.extend($("#searchForm").json(), { search_txt : sub_val == $("#search_txt").attr("emptyValue") ? '' : sub_val }));
		setMapCenterByPIndex(map, $(this).val());
	});

	$("#is_rural, #is_otn, #is_sall, #is_callb, #is_maint, #pageSize").change(function() {
		var sub_val = $.trim($("#search_txt").val());
		resetGMarkersToMap(map,  $.extend($("#searchForm").json(), { search_txt : sub_val == $("#search_txt").attr("emptyValue") ? '' : sub_val }));
	});

	$("#search_txt").keydown(function(e){ if (e.keyCode == 13) $("#search_btn").click(); }).textInputWithVal("请输入商铺名称/商铺产品名称/商铺简介搜索！");

	$("#search_btn").click(function() {
		var sub_val = $.trim($("#search_txt").val());
		resetGMarkersToMap(map, $.extend($("#searchForm").json(), { search_txt : sub_val == $("#search_txt").attr("emptyValue") ? '' : sub_val }));
	}).click();

	$(".rec_entpshop").click(function(){
		var p = $(this).attr("id").split(",");
		if (p.length == 2) {
			if (/^\s*$/.test(p[0]) || /^\s*$/.test(p[1])) { alert("很抱歉，该店铺还没有指定地理位置！"); return false; }
			map.panTo(new GLatLng(parseFloat(p[0]), parseFloat(p[1])));
		}
	}).hover(function() {
		$(this).css("background-color", "#eee");
	}, function() {
		$(this).css("background-color", "");
	});

});

// 全局变量
var G_DelaySeconds = 500; //延时毫秒数, 设置此项为了减少客户端对服务器的请求次数，同时增强客户端用户体验，此项过大或过小均不好。
var G_timer; // 地图缩放或拖动的计时器
var G_FirstLoader = true; // 首次加载地图
var G_marks = []; // 全局GMark数组
// 全局变量结束

function loadGMap(map) {
	if (GBrowserIsCompatible()) {   
		map.enableScrollWheelZoom(); // 开启鼠标滚轮放大功能   
		map.addControl(new GLargeMapControl()); // 添加一个带有可在四个方向平移、放大、缩小的按钮以及缩放滑块的控件。   
		map.addControl(new GMapTypeControl());  // 添加一个地图类型的控制条（位于右上方）   
		// map.addControl(new GOverviewMapControl());  // 在主地图的一角创建可折叠的迷你型概览地图，以便通过拖动提供位置参考和导航。GOverviewMapControl 创建单像素黑边界的概览地图。注意：与其他控件不同，您只能将此控件放在地图的右下角 (G_ANCHOR_BOTTOM_RIGHT)。   
		map.addControl(new GScaleControl()); // 创建显示地图比例尺的控件。   

		setMapCenterByPIndex(map, "${empty af.map.p_index ? 0 : af.map.p_index}");
		
		// 在map上绑定缩放事件
		GEvent.addListener(map, "zoomend", function(oldLevel, newLevel) {

			if (G_FirstLoader) return; // 首次加载，解决zoomend事件的首次加载地图自动执行该事件方法的问题.

			var gBounds = map.getBounds();
			$("#lat_max").val(gBounds.getNorthEast().lat());// 纬度（东西方向，横向）最大值
			$("#lng_max").val(gBounds.getNorthEast().lng());// 经度（南北方向，纵向）最大值
			$("#lat_min").val(gBounds.getSouthWest().lat());
			$("#lng_min").val(gBounds.getSouthWest().lng());
			
			// if (newLevel  == 7) setAddressOfMapCenter(map.getCenter());
			setAddressOfMapCenter(map);

			// 延时执行脚本
			clearTimeout(G_timer);
			G_timer = setTimeout(delay, G_DelaySeconds);
			
			function delay() {
				$("#search_btn").click();
				clearTimeout(G_timer);
			}
		});

		// 在map上绑定拖动事件
		GEvent.addListener(map, "dragend", function() {
			// 延时执行脚本
			clearTimeout(G_timer);
			G_timer = setTimeout(delay, G_DelaySeconds);

			var gBounds = map.getBounds();
			$("#lat_max").val(gBounds.getNorthEast().lat());// 纬度（东西方向，横向）最大值
			$("#lng_max").val(gBounds.getNorthEast().lng());// 经度（南北方向，纵向）最大值
			$("#lat_min").val(gBounds.getSouthWest().lat());
			$("#lng_min").val(gBounds.getSouthWest().lng());

			setAddressOfMapCenter(map);
			
			function delay() {
				$("#search_btn").click();
				clearTimeout(G_timer);
			}
		});
		return map;
	}
}

//1.
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
		var latlng = new GLatLng(parseFloat($.trim(cur.g_lat)), parseFloat($.trim(cur.g_lng)));

		var letter = String.fromCharCode("A".charCodeAt(0) + i);

		letter = i > 25 ? "" : letter;
		 
		var letteredIcon = new GIcon(baseIcon);
		letteredIcon.image = "http:\/\/www.google.cn\/mapfiles\/marker" + letter + ".png";
		// 直营网点
		if(cur.is_R3Shop == "1"){
			letteredIcon.image = "${ctx}/images/marks/icon_a/icon_"+letter+".gif";
		}else if(cur.is_R3Shop == "2"){
			letteredIcon.image = "${ctx}/images/marks/icon_b/icon_"+letter+".gif";
		}

		letteredIcon.shadowSize = new GSize(0, 0);

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
        	$.ajax({
        		type: "POST",
        		url: "${ctx}/manager/admin/EntpShopGSite.do",
        		data: "method=ajaxGetEntpShopInfo&shop_id=" + entp_shop.shop_id,
        		async: true,
        		dataType: "json",
        		error: function(request, settings) {},
        		success: function(data) {
        			if("true" == data.result){
        				//main_pd
        	            var ptmList = data.pdTypeModelList;
        	            if(ptmList.length > 1){
        		           var ptmArr = [];
        		           var tag = false; 
        		           for (var i = 0; i < ptmList.length - 1; i++) {
        			            var ptm = ptmList[i];
        				        var brandList = ptm.brandList;
        				        
        				        if(brandList.length > 1 ){
        					        tag = true;
        					        
        				           	var brandArr = [];
        				           	for (var j = 0; j < brandList.length - 1; j++) {
        				           		brandArr[j] = brandList[j].brand_name;
        				           	}
        				           	ptmArr[ptmArr.length] = "<b>" + ptm.pd_name + "<\/b>：" + brandArr.join("、 ") + "<br \/>";
        			        	}
        		           }
        		           if(tag){
        		        	   $openWindow.find("#main_pd").empty();
        		        	   $openWindow.find("#main_pd").append($("<div \/>").append(ptmArr.join("")));
        			       }else{
        			    	   $openWindow.find("#main_pd").text('暂无！');
        				   }
        	            }else{
        	            	$openWindow.find("#main_pd").text('暂无！');
        	            }

        	            // product
        	            for (var j = 0; j < data.shopPdList.length; j++) {
        	                var shop_pd = data.shopPdList[j];
        	                if ($.isNotBlank(shop_pd.main_pic)) {
            	                shop_pd.main_pic = shop_pd.main_pic.substring(0, shop_pd.main_pic.indexOf("."))+ "_120.jpg";
            	            }else{
            	            	shop_pd.main_pic = "${ctx}/images/default/default_small_img_"+ shop_pd.pd_type +".jpg";
                	        }
        	                if ($.isBlank(shop_pd.price)) shop_pd.price = " - ";
        	                if ($.isBlank(shop_pd.md_name)) shop_pd.md_name = " - ";

        	                var $img = $openWindow.find("#img_" + j).attr("src", shop_pd.main_pic);
        	                var $price = $openWindow.find("#price_" + j).text(shop_pd.price);
        	                var $md_name = $openWindow.find("#md_name_" + j).text(shop_pd.md_name);

        	                var $img_a = $img.parent();
        	                $img_a.attr("href", $img_a[0].href.replace("000000000", entp_shop.shop_id).replace("000000001", shop_pd.id));
        	                $price.attr("href", $price[0].href.replace("000000000", entp_shop.shop_id).replace("000000001", shop_pd.id));
        	                $md_name.attr("href", $md_name[0].href.replace("000000000", entp_shop.shop_id).replace("000000001", shop_pd.id));
        	            }
        	            if (data.shopPdList.length == 0) {
            	            $openWindow.find(".product").empty();
        	            }else{
            	             //当是一个商品的时候处理
            	            if(data.shopPdList.length == 1){
									$("#product3").html("");
									$("#product4").html("");
                	         }
               	         
        	            	document.getElementById("productBox").style.display = "";
                	    }
        	            
        	            // shopOnsaleInfo
        	            var $onsale_info = $openWindow.find("#onsale_info").empty();
        	            var href = $onsale_info[0].title;
        	            for (var j = 0; j < data.shopOnsaleInfoList.length; j++) {
        					var info = data.shopOnsaleInfoList[j];
        					$onsale_info.append($("<a><\/a>").attr("href", href.replace("000000000", info.shop_id).replace("000000001", info.id)).attr("target", "_blank").text(info.title)).append("&nbsp;&nbsp;");
        	            }
        	            if (data.shopOnsaleInfoList.length == 0){ 
            	            $onsale_info.empty();
            	        }else{
            	        	document.getElementById("onsaleInfoBox").style.display = "";
            	        }

        	            map.closeInfoWindow();
        	            map.openInfoWindow(latlng, $openWindow[0]);
        			}else{
        				//alert("数据加载失败，请刷新后重新尝试！");
        				alert("取店铺商品功能暂未做！");
        			}
        		}
        	});
        	
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
		/*
		if (map.getZoom() != 9) {
			map.zoomIn(latlng, true);
		} else {
			map.panTo(latlng);
		}*/

		// 触发标记click事件
		GEvent.trigger(G_marks[letter], "click", latlng);
	});

	// default panto 默认是地图中心定位到到第一个商铺所在位置， 暂时关闭
	if (fisrtEntpShop && false) {
		var defalutPoint = new GLatLng(parseFloat(fisrtEntpShop.g_lat), parseFloat(fisrtEntpShop.g_lng));
		  
        window.setTimeout(function() {
        	map.panTo(defalutPoint);
        }, 1000); 
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
	$("#pager_control").pager("${ctx}/manager/admin/EntpShopGSite.do", $.extend({}, {
		method		: 'ajaxGetEntpShopJson'
	}, options), $.extend({}, {
		showPagesCount : 5,	// 共显示页面下标数
		pageSize : requestPageSize // 每页显示数据条数
	}, options), function(ret) {
		$("#count_sum").text(ret.count);// 设置查询结果数
		$("#count_A").text(ret.count_A);// 设置5.5W网点(不含直营)
		$("#count_B").text(ret.count_B);// 设置直营网点数
		$("#count_C").text(ret.count_C);// 设置其他网点数		
		// $(".glatlng").val("");// 清空经纬度缓存
		// List
		resetGMarkersOfEntpShopToMap(map, ret.list);


		var e_time = new Date();
		$("#exc_time").text("共耗时 " + (e_time.getTime() - s_time.getTime())/(1000) + " 秒.");
	});
}

/**
 * 将地图定位到China中心，显示China版图
 */
function resetMapToChina(map) {
	var center = new GLatLng(36.3002752813443, 104.677734375);
	map.setCenter(center, 4);
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
	
	$.post("${ctx}/manager/admin/EntpShopGSite.do", {method : 'ajaxGetPIndexNamesStr', p_index : p_index}, function(ret){
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
		var letter = String.fromCharCode("A".charCodeAt(0) + i);

		letter = i > 25 ? "" : letter;
		
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

			// [直营网点 :1 ] [5.5W网点(不含直营): 2]
            if($(this).attr("is_R3Shop") == "1"){
            	$(this).css("background-color", "#ECFFFF");
            }else if($(this).attr("is_R3Shop") == "2"){
            	$(this).css("background-color", "#F5FFE8");
            }		
		});
		
		// [直营网点 :1 ] [5.5W网点(不含直营): 2] [上述以外网点 : 0]
		if(cur.is_R3Shop == "1"){
			$li.attr("is_R3Shop", "1");
			$li[0].style.backgroundColor = "#ECFFFF";
		}else if(cur.is_R3Shop == "2"){
			$li.attr("is_R3Shop", "2");
			$li[0].style.backgroundColor = "#F5FFE8";
		}else{
			$li.attr("is_R3Shop", "0");
		}
				
		$li.removeClass("templete").show();
		$container.append($li);
	}

	if (entp_shop_list.length == 0) {
		$container.append("<li><span style=\"color:#F00;padding:20px 0;display:block;\">很抱歉！没有符合您的查询结果！</span></li>");
	}
	
	return $container;
}

// 以下为自定义的jQuery插件
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
//]]></script>
<%@ include file="__ga_web.jsp" %>
<jsp:include page="/__analytics.jsp" />
</body>
</html>