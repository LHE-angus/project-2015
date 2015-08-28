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
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/BrandGridsAnalysis">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mdas_mod_id" />
      <html-el:hidden property="lat" styleId="lat"/>
      <html-el:hidden property="lng" styleId="lng"/>
      <html-el:hidden property="szom" styleId="szom"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td width="15" style="height:25px;"></td>
          <td style="height:25px;"><ul>
              <li>
                <strong class="fb">所属区域：</strong>
                <html-el:select property="province" styleId="province">
                  <html-el:option value="">-请选择省/直辖市/自治区-</html-el:option>
                  <c:forEach var="cur" items="${mdasModPermList_p_index}">
                    <html-el:option value="${cur.p_index}">${cur.map.p_name}</html-el:option>
                  </c:forEach>
                </html-el:select>
                <html-el:select property="city" styleId="city">
                  <html-el:option value="">-请选择市-</html-el:option>
                </html-el:select>
                <html-el:select property="country" styleId="country">
                  <html-el:option value="">-请选择县-</html-el:option>
                </html-el:select>
              </li>            
              <li>
                 <strong class="fb">网点状态：</strong>
                 <html-el:select property="own_sys" styleId="own_sys" style="width:164px;">
		      	   <html-el:option value="">-请选择网点状态-</html-el:option>
		      	   <html-el:option value="1">家电下乡销售</html-el:option>
		      	   <html-el:option value="2">以旧换新销售</html-el:option>
		         </html-el:select>
              </li>
              <li>
                 <strong class="fb">产品品牌：</strong>
                 <html-el:text property="brand_name" value="${empty af.map.brand_name?'请选择品牌名称':af.map.brand_name}" styleId="brand_name" readonly="true" onclick="openChild();" style="cursor:pointer;width:120px;border:1px solid #ccc;" />
			     <html-el:hidden property="brand_id" styleId="brand_id" />&nbsp;
			     <html-el:button property=" 清空 " value="清 空 " onclick="$('#brand_name').val('请输入品牌名称');$('#brand_id').val('')" />
              </li>
              <li>
                 <strong class="fb">选择时间：</strong>
                 <html-el:select property="year" styleId="year">
			  	    <html-el:option value="">-请选择年-</html-el:option>
			     </html-el:select>&nbsp;
			     <html-el:select property="month" styleId="month">
			  	    <html-el:option value="">-请选择月-</html-el:option>
			     </html-el:select>
                 &nbsp;&nbsp;
                 <input class="but1" type="submit" name="Submit" value="搜索" />
              </li>             
            </ul></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td align="center" nowrap="nowrap" ><font class="blue">网点地图信息</font></td>          
          </tr>  
        </tbody>
      </table>
      <c:if test="${not empty af.map.points_limit}" var="points_limit">
            <table border="0" cellpadding="0" cellspacing="1" class="datagrid" style="width : 100%">
              <tr>
                <td width="85%"><div id="map_canvas" style="width : 100%; height : 600px;"></div></td>
              </tr>
              <tr>
              	<td width="15%" nowrap="nowrap" valign="top">
                  <form id="bottomPageForm" name="bottomPageForm" method="post" action="BrandGridsAnalysis.do">
                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td height="10" align="center">
                        <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
                          <script type="text/javascript">
							var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
							pager.addHiddenInputs("method", "list");
							pager.addHiddenInputs("mdas_mod_id", "${(af.map.mdas_mod_id)}");
							pager.addHiddenInputs("province", "${(af.map.province)}");
							pager.addHiddenInputs("city", "${(af.map.city)}");
							pager.addHiddenInputs("country", "${(af.map.country)}");
							pager.addHiddenInputs("own_sys", "${(af.map.own_sys)}");
							pager.addHiddenInputs("brand_id", "${(af.map.brand_id)}");
							pager.addHiddenInputs("sell_price_min", "${(af.map.sell_price_min)}");
							pager.addHiddenInputs("sell_price_max", "${(af.map.sell_price_max)}");
							pager.addHiddenInputs("year", "${(af.map.year)}");
							pager.addHiddenInputs("month", "${(af.map.month)}");
							document.write(pager.toString());
            	          </script>
            	       </td>
                      </tr>
                    </table>
                  </form>
                 </td>
              </tr>
            </table>
          </c:if>
          <c:if test="${not points_limit}">
            <div id="map_canvas" style="width : 100%; height : 600px;"></div>
          </c:if>
  </div>
  <div class="rtabcont1">
    <div id="mainDiv" style="text-align:left;display:none;">
       <html-el:form action="/admin/BrandGridsAnalysis">
        <table width="100%" border="0" align="left" cellpadding="1" cellspacing="1" class="datagrid">
          <tr>
            <th colspan="2" class="form_title"><span id="shop_name"></span></th>
          </tr>
          <tr>
            <td width="175px;" class="title_item">联系人：</td>
            <td><span id="link_user"></span></td>
          </tr>
          <tr>
            <td class="title_item">联系电话：</td>
            <td><span id="link_phone"></span></td>
          </tr>
          <tr>
            <td class="title_item">地址：</td>
            <td><span id="street_addr"></span></td>
          </tr>
          <tr>
            <td class="title_item">销售额：</td>
            <td><span id="sumMoney"></span></td>
          </tr>
          <tr>
            <td class="title_item">主营产品：</td>
            <td><span id="main_pd"></span></td>
          </tr>
          <tr>
            <td colspan="2" align="center"><span id="buttonLink"></span></td>
          </tr>
         </table>
        </html-el:form>
      </div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.timers.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/google.map.plugin.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}", "datatype": "Require", "msg": "请选择省名称"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	$("#province").change();
	
	$("#year").attr({"subElement": "month", "defaultText": "-请选择年-", "defaultValue": "", "selectedValue": "${af.map.year}", "datatype": "Require", "msg": "请选择年"});
	$("#month" ).attr({"defaultText": "-请选择月-", "defaultValue": "", "selectedValue": "${af.map.month}"});
	$("#year").cs("${ctx}/manager/admin/BrandGridsAnalysis.do?method=getDateForSelectList&mdas_mod_id=10501", "year", "0", false);
	$("#year").change();

	$("#pageUl li").mouseover(function(){ 
		$(this).css("cursor", "pointer");
		$(this).css("background-color", "#bcd4ec");
	}).mouseout(function(){
		$(this).css("background-color", "");
	});
});

function openChild(){
    var returnValue = window.showModalDialog("BrandGridsAnalysis.do?method=listBrand&mdas_mod_id=${af.map.mdas_mod_id}", window, "dialogWidth:800px;status:no;dialogHeight:540px"); 
    if(returnValue != null) {
    	var brand_info = returnValue.split(",");
    	$("#brand_id").val(brand_info[0]);
		$("#brand_name").val(brand_info[1]);
    } 
}

var map;
var infowindowLevel = 0;
function initialize() {
	var lat,lng,szom;
	 lat = $("#lat").val();
	 lng = $("#lng").val();
	 szom = $("#szom").val();
	if(lat=="" || lat==undefined || lng=="" || lng==undefined) {
		lat = 31.845774048898953;
		lng = 117.23888397216797;
		szom = 4;
	}
	if(szom != 4) {
		szom = parseInt(szom);
	}
	geocoder = new google.maps.Geocoder();
	var myLatlng = new google.maps.LatLng(lat,lng);
	var myOptions = {
		zoom: szom,
		center: myLatlng,
		scrollwheel: false,
		navigationControl: true,
		scaleControl: true,
		streetViewControl: true,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

	$(document).oneTime('2s',function(){
		addMarkers();
	});
} 
google.maps.event.addDomListener(window, 'load', initialize);

function addMarkers() {
	<c:forEach items="${entpShopList}" var="cur" varStatus="vs">
	  <c:if test="${not empty cur.g_lat_t && not empty cur.g_lng_t}">
	    var location = new google.maps.LatLng(${cur.g_lat_t},${cur.g_lng_t});
		//alert(location);
	    var marker = new google.maps.Marker({
	        position: location,
	        //TODO 销售不同品牌的网点增加 不同的图标
	        //icon: '${ctx}/images/mm_20_red.png',
	        map: map
	    });
	    marker.setTitle("${cur.shop_name}");
	    
	    attachInfowindow(marker, "${cur.shop_id}");

		addPagesEntpInfoTrigger("#entp_shop_${cur.shop_id}", marker);

		//markers.push(marker);
	  </c:if>
	</c:forEach>
}

//增加分页的数据网点的click事件
function addPagesEntpInfoTrigger(obj, marker) {
    $(obj).click(function(){
    	google.maps.event.trigger(marker, "click");
    	map.setCenter(marker.getPosition());
    });
}

function attachInfowindow(marker, shop_id) {

	google.maps.event.addListener(marker, 'click', function() {
		$.ajax({//ajax 取数据：
			type: "POST",
			url: "BrandGridsAnalysis.do",
			data: "method=getShopInfo&shop_id=" + shop_id,
			dataType: "json",
			error: function(request, settings) {alert();},
			success: function(Datas) {
				if(Datas.length > 1){
					var _shop_id = Datas[0].shop_id;
					var shop_name = Datas[0].shop_name;
					var link_user = Datas[0].link_user;
					var link_phone = Datas[0].link_phone;
					var street_addr = Datas[0].street_addr;
					var sumMoney = Datas[0].sumMoney;
					var main_pd = Datas[0].main_pd;
				  	$("#shop_name").empty().text(shop_name);
				    $("#link_user").empty().text(link_user);
				    $("#link_phone").empty().text(link_phone);
				    $("#street_addr").empty().text(street_addr);
				    $("#sumMoney").empty().text(sumMoney + "元");
				    $("#main_pd").empty().text(main_pd);
				    var link = '<a href="#" onclick="showPie3D(' + _shop_id + ')">查看网点销售额对比图 </a>';
					$("#buttonLink").empty().html(link);
					
				    var htmlData = $("#mainDiv").html();
					var infowindow = new google.maps.InfoWindow({ 
						   content: htmlData
					});
				    infowindow.setZIndex(++infowindowLevel);
				    infowindow.open(map, marker);			    
				}
			}
		});
	});
}

function showPie3D(shop_id){
	var now= new Date();
	window.showModalDialog("BrandGridsAnalysis.do?method=view&shop_id="+ shop_id +"&mdas_mod_id=10501&year=${af.map.year}&month=${af.map.month}&time="+now, window, "dialogWidth:800px;status:no;dialogHeight:540px");
}

$("li", "#menu").each(function(){
	$(this).mouseover(function(){
		$(this).addClass("alloneSpan");
	}).mouseout(function(){$(this).removeClass("alloneSpan");}).click(function(){
		$(".alloneSpan").removeClass("alloneSpan");
		$(this).addClass("alloneSpan");
	});
	//onmouseover="" onmouseout=""
});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>