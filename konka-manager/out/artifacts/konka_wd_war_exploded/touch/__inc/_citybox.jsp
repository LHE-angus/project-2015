<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<script type="text/javascript"> !window.jQuery && document.write('<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"><\/script >'); </script>
<script type="text/javascript"> !window.jQuery && document.write('<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"><\/script >'); </script>

<tbody>
  <tr>
    <td width="9%" height="22" nowrap="nowrap">配送至：</td>
    <td width="28%"><div style="position:relative;display:inline;">
        <div class="proselect" style="width:220px;overflow:hidden;cursor:pointer;" id="p_full_name">${p_full_name}</div>
        <div style="position:absolute;left:0px;top:5px;z-index:99px;display:none;" id="more_province">
          <table width="385" border="0" cellspacing="0" cellpadding="0" style="border:1px #F60 solid; background:#FFF;">
            <tr>
              <td height="26">
              	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dizhi_nav">
              		<tr>
              			<td class="addr_td1" width="29%" id="addr_province" data="${province_index}" lang="${province_name}"><span>${province_name}</span></td>
              			<td class="addr_td2" width="28%" id="addr_city" data="" lang="">&nbsp;</td>
              			<td class="addr_td2" width="28%" id="addr_county" data="">&nbsp;</td>
              			<td class="addr_td2" width="15%"><span id="more_province_close" style="cursor:pointer;">【关闭】</span></td>
              		</tr>
              	</table>
                </td>
            </tr>
            <tr>
              <td height="107" align="left" valign="top" style="padding-left:20px; padding-bottom:10px;">
              	<dl class="dizhibox" id="province_dl">
              	  <c:forEach items="${baseProvinceListFourList}" var="cur">
              	  	<c:if test="${fn:length(cur.p_name) ge 5}">
              	  		<c:if test="${cur.p_index eq province_index}"><dd class="text_long"><a class="a_province" data="${cur.p_index}"><font>${cur.p_name}</font></a></dd></c:if>
              	  		<c:if test="${cur.p_index ne province_index}"><dd class="text_long"><a class="a_province" data="${cur.p_index}">${cur.p_name}</a></dd></c:if>
              	  	</c:if>
              	  	<c:if test="${fn:length(cur.p_name) lt 5}">
              	  		<c:if test="${cur.p_index eq province_index}"><dd><a class="a_province" data="${cur.p_index}"><font>${cur.p_name}</font></a></dd></c:if>
              	  		<c:if test="${cur.p_index ne province_index}"><dd><a class="a_province" data="${cur.p_index}">${cur.p_name}</a></dd></c:if>
              	  	</c:if>
              	  </c:forEach>
                </dl>
                <dl class="dizhibox" id="city_dl" style="display:none;"></dl>
                <dl class="dizhibox" id="county_dl" style="display:none;"></dl>
                <img src="${ctx}/images/loading45.gif" style="display:none;" id="img_loading" />
              </td>
            </tr>
          </table>
        </div>
      </div></td>
    <td width="63%" height="22">
    	<span id="had_stock" style="display:${konkaBcompPd.ecStocks.stocks > 0 ? '' : 'none'};"><strong class="f14b">现货</strong>&nbsp;<c:if test="${empty konkaBcompPd.ecBaseExpressReachDay}">付款后立即发货！</c:if><c:if test="${not empty konkaBcompPd.ecBaseExpressReachDay}">付款后预计${konkaBcompPd.ecBaseExpressReachDay.max_reach_day}天送达</c:if></span>
    	<span id="no_stock" style="display:${empty konkaBcompPd.ecStocks.stocks ? '' : 'none'};"><strong class="f14b">所选商品暂时无货，非常抱歉！</strong></span>
    	<img id="stocks_loading" style="display:none;" src="${ctx}/images/loading5.gif" />
   	</td>
  </tr>
</tbody>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 选择点击省份
	$(document).delegate(".a_province", "click", function(){
		$(this).attr("hover", "hover"); // 这里标记鼠标点击的是哪个
		var array = $(".a_province");
		for(var i = 0; i < array.length; i++){
			var html = $(array[i]).html().replace(/<\/?[^>]*>/g,''); // 正则过滤HTML TAG
			if($(array[i]).attr("hover") == "hover"){
				 $("#addr_province").attr("data", $(array[i]).attr("data"));
				 $("#addr_province").attr("lang", html);
				 $("#addr_province").html("<span>" +html + "</span>");
				 
				 $(array[i]).html("<font>" + html + "</font>");
			} else {
				 $(array[i]).html(html);
			}
		}
		$(this).removeAttr("hover");
		
		// 样式处理
		$("#addr_province").removeClass("addr_td1 addr_td2").addClass("addr_td2");
		$("#addr_city").removeClass("addr_td1 addr_td2").addClass("addr_td1").html("<span>请选择</span>");
		$("#addr_county").removeClass("addr_td1 addr_td2").addClass("addr_td2").html("");
		
		// 数据显示隐藏
		$("#province_dl").hide();
		$("#city_dl").hide();
		$("#county_dl").hide();
		$("#img_loading").show();
		
		// Ajax动态加载数据
		var province_index = $("#addr_province").attr("data");
		$.ajax({
			type: "POST",
			url: "<c:url value='/CsAjax.do' />",
			data: { "method":"getPindexAndNameJsonObjectByParIndex", "par_index":province_index, "timestamp":new Date().getTime() },
			dataType: "json",
			sync: true,
			error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
			success: function(data) {
				if(data == ""){
					alert("提示，数据加载失败！");
					return;
				} 
				
				// 循环处理数据
				$("#city_dl").empty();
				for(var i = 0; i< data.length; i++){
					var dd = "";
					if(data[i].p_name.length >= 5){
						dd = '<dd class="text_long"><a class="a_city" data="' + data[i].p_index + '">' + data[i].p_name + '</a></dd>';
					} else {
						dd = '<dd><a class="a_city" data="' + data[i].p_index + '">' + data[i].p_name + '</a></dd>';
					}
					
					$("#city_dl").append(dd);
				}
				
				// 数据显示隐藏
				$("#img_loading").hide();
				$("#city_dl").show();
			}
		});
	});
	
	// 省份标题点击
	$(document).delegate("#addr_province", "click", function(){
		// 样式处理
		$("#addr_province").removeClass("addr_td1 addr_td2").addClass("addr_td1");
		$("#addr_city").removeClass("addr_td1 addr_td2").addClass("addr_td2").html("");
		$("#addr_county").removeClass("addr_td1 addr_td2").addClass("addr_td2").html("");
		
		// 数据显示隐藏
		$("#province_dl").show();
		$("#city_dl").hide();
		$("#county_dl").hide();
		$("#img_loading").hide();
	});
	
	// 选择点击市
	$(document).delegate(".a_city", "click", function(){
		$(this).attr("hover", "hover"); // 这里标记鼠标点击的是哪个
		var array = $(".a_city");
		for(var i = 0; i < array.length; i++){
			var html = $(array[i]).html().replace(/<\/?[^>]*>/g,''); // 正则过滤HTML TAG
			if($(array[i]).attr("hover") == "hover"){
				 $("#addr_city").attr("data", $(array[i]).attr("data"));
				 $("#addr_city").attr("lang", html);
				 $("#addr_city").html("<span>" +html + "</span>");
				 
				 $(array[i]).html("<font>" + html + "</font>");
			} else {
				 $(array[i]).html(html);
			}
		}
		$(this).removeAttr("hover");
		
		// 样式处理
		$("#addr_province").removeClass("addr_td1 addr_td2").addClass("addr_td2");
		$("#addr_city").removeClass("addr_td1 addr_td2").addClass("addr_td2");
		$("#addr_county").removeClass("addr_td1 addr_td2").addClass("addr_td1").html("<span>请选择</span>");
		
		// 数据显示隐藏
		$("#province_dl").hide();
		$("#city_dl").hide();
		$("#county_dl").hide();
		$("#img_loading").show();
		
		// Ajax动态加载数据
		var province_index = $("#addr_city").attr("data");
		$.ajax({
			type: "POST",
			url: "<c:url value='/CsAjax.do' />",
			data: { "method":"getPindexAndNameJsonObjectByParIndex", "par_index":province_index, "timestamp":new Date().getTime() },
			dataType: "json",
			sync: true,
			error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
			success: function(data) {
				if(data == ""){
					alert("提示，数据加载失败！");
					return;
				} 
				
				// 循环处理数据
				$("#county_dl").empty();
				for(var i = 0; i< data.length; i++){
					var dd = "";
					if(data[i].p_name.length >= 5){
						dd = '<dd class="text_long"><a class="a_county" data="' + data[i].p_index + '">' + data[i].p_name + '</a></dd>';
					} else {
						dd = '<dd><a class="a_county" data="' + data[i].p_index + '">' + data[i].p_name + '</a></dd>';
					}
					$("#county_dl").append(dd);
				}
				
				// 数据显示隐藏
				$("#img_loading").hide();
				$("#county_dl").show();
			}
		});
	});
	
	// 市标题点击
	$(document).delegate("#addr_city", "click", function(){
		// 样式处理
		$("#addr_province").removeClass("addr_td1 addr_td2").addClass("addr_td2");
		$("#addr_city").removeClass("addr_td1 addr_td2").addClass("addr_td1");
		$("#addr_county").removeClass("addr_td1 addr_td2").addClass("addr_td2").html("");
		
		// 数据显示隐藏
		$("#province_dl").hide();
		$("#city_dl").show();
		$("#county_dl").hide();
		$("#img_loading").hide();
	});
	
	// 点击县，查询库存价格
	$(document).delegate(".a_county", "click", function(){
		var goods_id = $("#goods_id").val();
		var p_index = $(this).attr("data");
		
		// 地址回显
		var province = $("#addr_province").attr("lang");
		var city = $("#addr_city").attr("lang");
		var county = $(this).html().replace(/<\/?[^>]*>/g,''); // 正则过滤HTML TAG
		
		// 修改地址
		$("#p_full_name").html(province + city + county);
		$("#p_index").val(p_index);
		
		// 隐藏地址BOX
		$("#more_province").hide();
		
		// loading 图片显示处理
		$("#price_tag").html("");<c:if test="${ecUser.user_type eq 1 }">
		$("#price_tag_loading").show();</c:if>
		$("#original_price_tag").html("");
		$("#original_price_tag_loading").show();
		$("#had_stock").hide();
		$("#no_stock").hide();
		$("#stocks_loading").show();
		
		// AJAX获取商品信息
		$.ajax({
			type: "POST",
			url: "<c:url value='/touch/PdShow.do' />",
			data: { "method":"ajaxGetKonkaBcompPdByGoodsIdAndPindex", "goods_id":goods_id, "p_index":p_index, "timestamp":new Date().getTime() },
			dataType: "json",
			sync: true,
			error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
			success: function(data) {
				if(data == ""){
					alert("提示，数据加载失败！");
					return;
				} 
				
				// Loading 图片隐藏
				<c:if test="${ecUser.user_type eq 1 }">
				$("#price_tag_loading").hide();</c:if>
				$("#original_price_tag_loading").hide();
				$("#stocks_loading").hide();
				
				// 数据回显处理
				$("#price").val(data.price);
				
				$("#original_price").val(data.original_price);
				<c:if test="${ecUser.user_type eq 1 }">
				$("#price_tag").html("￥" + data.price);</c:if>
				$("#original_price_tag").html("￥" + data.original_price);
				$("#had_stock_num").val("0"); // 库存
				if(data.stocks > 0){
					$("#had_stock_num").val(data.stocks);
					if(data.max_reach_day > 0){
						$("#had_stock").empty().html('<strong class="f14b">现货</strong>&nbsp;付款后预计' + data.max_reach_day + '天送达').show();
					} else {
						$("#had_stock").empty().html('<strong class="f14b">现货</strong>&nbsp;付款后立即发货').show();
					}
					$("#no_stock").hide();
				} else {
					$("#had_stock").hide();
					$("#no_stock").show();
				}
			}
		});
	});
	
	
	// 切换地区
	$(document).delegate("#p_full_name", "click", function(){
		// 样式处理
		$("#addr_province").removeClass("addr_td1 addr_td2").addClass("addr_td1");
		$("#addr_city").removeClass("addr_td1 addr_td2").addClass("addr_td2").html("");
		$("#addr_county").removeClass("addr_td1 addr_td2").addClass("addr_td2").html("");
		
		// 数据显示隐藏
		$("#province_dl").show();
		$("#city_dl").hide();
		$("#county_dl").hide();
		$("#img_loading").hide();
		
		$("#more_province").show();
	});
	$(document).delegate("#more_province_close", "click", function(){
		$("#more_province").hide();
	});
});
//]]></script>