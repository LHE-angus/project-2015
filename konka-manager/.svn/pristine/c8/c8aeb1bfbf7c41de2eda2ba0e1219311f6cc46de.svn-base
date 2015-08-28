<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳网上直销商城</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/shoppingcar.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- top start -->
<div class="topbox">
<jsp:include page="/zxmall/__inc/top.jsp" flush="true" />
<jsp:include page="/zxmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/zxmall/__inc/nav.jsp" flush="true" />
<!-- top end --> 

<!-- buy start -->
<div class="card_center">
  <div class="card_a">我 的 购 物 车</div>
  <div class="card_b"><span style="font-size:12px;">当前地区:${p_name }</span>
    <div class="card_c">
      <ul>
        <li><b>1、我的购物车</b></li>
        <li>2、确认订单信息</li>
        <li>3、提交订单</li>
      </ul>
    </div>
  </div>
  <div class="card_box">
    <ul>
      <li class="xh">序号</li>
      <li class="mc">商品名称</li>
      <li class="tp">商品图片</li>
      <li class="jg">数量</li><c:if test="${zxmall.user_type eq 2 or empty zxmall}">
      <li class="sl">价格</li></c:if><c:if test="${zxmall.user_type eq 22 }">
      <li class="sl">市场终端价</li></c:if>
      <li class="hj">合计</li>
      <li class="sc">增值服务</li>
      <li class="ew">物流费</li>
      <li class="ew">优惠</li>
      <li class="kc">提示</li>
      <li class="cz">操作</li>
    </ul>
  </div>
  <form action="<c:url value='/zxmall/ShoppingCar.do' />" method="post" id="shopping_car_form">
  	<input type="hidden" name="method" value="stepTwo" />
  	<input type="hidden" name="p_index" value="${p_index}" />
    <c:forEach items="${konkaBcompPdList}" var="cur" varStatus="vs">
	  <div class="card_content" id="div_${cur.id}">
	  	<input type="hidden" name="goods_id" value="${cur.id}"  />
	  	<input type="hidden" name="price_${cur.id}" id="price_${cur.id}" value="${cur.ecGoodsPrice.price}"  />
	  	<input type="hidden" name="original_price_${cur.id}" id="original_price_${cur.id}" value="${cur.ecGoodsPrice.original_price}"  />
	  	<input type="hidden" name="pd_name_${cur.id}" value="${cur.pd_name}"  />
	  	<input type="hidden" name="service_ids_${cur.id}" id="service_ids_${cur.id}" value="${cur.map.service_ids}" />
	    <ul>
	      <li class="c_one li_num_class">${vs.count}</li>
	      <li class="c_two">
	       <a href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="${cur.pd_name}">${fnx:abbreviate(cur.pd_name, 2 * 16, '...')}</a>
	       <c:if test="${cur.map.xsqy_flg eq 1 }"><span style="font-size:11px;font-color:red;">不在销售区域</span></c:if>
	      </li>
	      <li class="c_three"><table border="0" cellspacing="0" cellpadding="0"><tr><td height="70" width="100" style="overflow:hidden;" valign="middle" align="center"><a href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />"><img alt="${cur.pd_name}" src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}_060.jpg" style="max-width:60px;max-height:60px;" /></a></td></tr></table></li>
	      <li class="c_four">
	        <div class="fout_left"><img src="${ctx}/styles/zxmall/images/jian_1.jpg" width="11" height="11" class="num_jian_class" data="${cur.id}" style="cursor:pointer;" /></div>
	        <div class="four_center">
	          <input name="buy_num_${cur.id}" id="buy_num_${cur.id}" type="text" class="four_bd buy_num_class" value="${cur.ecShoppingCart.pd_num}" data="${cur.id}" onfocus="javascript:setOnlyInt(this);" maxlength="3" lang="${cur.ecStocks.stocks}" />
	        </div>
	        <div class="fout_right"><img src="${ctx}/styles/zxmall/images/jia_1.jpg" width="11" height="11" class="num_jia_class" data="${cur.id}" style="cursor:pointer;" /></div>
	      </li><c:if test="${zxmall.user_type eq 2}"> 
	      <li class="c_five"><font class="red12px">￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="#,#00" /></font></li>
	      <li class="c_six"><font class="red12px">￥<font id="all_price_${cur.id}"><fmt:formatNumber value="${cur.ecGoodsPrice.price * cur.ecShoppingCart.pd_num}" pattern="#,#00" /></font></font></li>
	       </c:if>
	       <c:if test="${zxmall.user_type eq 22 }">
	      <li class="c_five"><font class="red12px">￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="#,#00" /></font></li>
	      <li class="c_six"><font class="red12px">￥<font id="all_price_${cur.id}"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price * cur.ecShoppingCart.pd_num}" pattern="#,#00" /></font></font></li>
	       </c:if>
	      <li class="c_service" style="padding:0;">
	      	<c:if test="${empty cur.serviceIds}"><span style="margin-left:10px;line-height:70px;">没选择增值服务</span></c:if>
	      	<c:forEach items="${cur.ecBindingPdListForService}" var="cur1">
	      		<c:forEach items="${cur.serviceIds}" var="cur2">
	      			<c:if test="${cur1.id eq cur2}"><span style="margin-left:5px;line-height:18px;">${cur1.goods_name} ￥<fmt:formatNumber value="${cur1.price}" pattern="#0.00" /></span><br /></c:if>
	      		</c:forEach>
	      	</c:forEach>	      	
	      	<input type="hidden" name="store_id_${cur.id}" value="${cur.ecStocks.store_id}"  />
	      </li>
	      <li class="c_ew" >
	      	<font class="red12px">
	      	 <c:if test="${cur.map.rule_price eq '0.00'}">无</c:if>
	      	  <c:if test="${cur.map.rule_price ne '0.00'}">
	      	￥<fmt:formatNumber value="${cur.map.rule_price}" pattern="#,#00" />/台
	      	  </c:if>
	      	</font>
	      </li>
	      <li class="c_ew" >
	      	<font class="red12px">
	      	 <c:if test="${cur.map.rule_price_2 eq '0.00'}">无</c:if>
	      	  <c:if test="${cur.map.rule_price_2 ne '0.00'}">
	      	￥<fmt:formatNumber value="${cur.map.rule_price_2}" pattern="#,#00" />/台
	      	  </c:if>
	      	</font>
	      </li>
	      <li class="c_kucu" id="stocks_flag_${cur.id}">
	      	<c:if test="${not empty cur.ecStocks.stocks and cur.ecStocks.stocks ge cur.ecShoppingCart.pd_num}"><font style="margin-left:10px;line-height:30px;" class="stocks_flag">现货，下单后发货！</font></c:if>
	      	<c:if test="${empty cur.ecStocks.stocks or cur.ecStocks.stocks lt cur.ecShoppingCart.pd_num}"><font color="red" style="margin-left:10px;line-height:30px;">抱歉，暂时缺货！</font></c:if>
	      </li>
	      <li class="c_seven"><span style="cursor:pointer;" class="del_shopping_car" data="${cur.id}">删除</span></li>
	    </ul>
	  </div>
    </c:forEach>
  </form>
  <div class="clear"></div>
  <div class="ts"><font class="red12px">提示：</font>1、加入购物车的商品请及时购买；2、商品价格以订单提交时的价格为准。</div>
  <div class="but_card" style="font-size:14px;font-weight:bold;">
    <ul>
      <li><a href="<c:url value='/zxmall/Index.do' />" id="step_one_go_shopping">&lt;&lt; 继续购物</a></li>
      <li><a style="cursor:pointer;" id="go_step_two_btn">去结算 &gt;&gt;</a></li>
    </ul>
  </div>
</div>
<!-- buy end --> 

<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer --> 

<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	// 点击去结算
	$(document).delegate("#go_step_two_btn", "click", function(){
		var goodsCount = $("input[name='goods_id']").length; // 商品个数
		var stocksFlagCount = $(".stocks_flag").length; // 有库存标识
		<c:if test="${not empty xsqy_flg and xsqy_flg eq 1}">
		alert("提示，购物车中商品不在销售区域！");
		return false;
		</c:if>
		// 判断是否有商品
		if(goodsCount == 0){
			alert("提示，没有商品，不能提交结算请求！");
			return false;
		}
		
		// 判断库存是不有
		if(goodsCount != stocksFlagCount){
			alert("提示，有商品库存不足，不能提交结算请求！");
			return false;
		}
		
		$("#step_one_go_shopping").attr("disabled", true).html("提交中...");
		$("#go_step_two_btn").attr("disabled", true).html("提交中...");
		$("#shopping_car_form").submit();
	});
	
	// 删除购物车
	$(document).delegate(".del_shopping_car", "click", function(){
		if(!confirm("确定删除？")) return false;
		var goods_id = $(this).attr("data");
		
		// 处理COOKIE中的值
		try {
		   var value = "["; 
		   var SHOPING_CAR_COOKIE = $.cookie("SHOPING_CAR_COOKIE"); 
		   var cookie_count = 0; // 记录cookie中的购物车商品数
		   if(null != SHOPING_CAR_COOKIE && "" != SHOPING_CAR_COOKIE) {
		      var json = eval("(" + SHOPING_CAR_COOKIE + ")"); 
		      cookie_count = json.length;
		      for(var i = 0; i < json.length; i ++) {
		         if(json[i].goods_id != goods_id) {
		        	 value += "{\"goods_id\":\"" + json[i].goods_id + "\",\"p_index\":\"" + json[i].p_index + "\",\"md_name\":\"" + json[i].md_name + "\",\"buy_num\":\"" + json[i].buy_num + "\",\"price\":\"" + json[i].price + "\",\"service_ids\":\"" + json[i].service_ids + "\",\"img_url\":\"" + json[i].img_url + "\"},"
		         } 
		      }
		   }
		   if(cookie_count > 1){
			   value = value.substring(0, value.length - 1) + "]"; 
		   } else{
			   value += "]"; 
		   }
		   
		   // 将处理过后的数据存入COOKIE
		   $.cookie("SHOPING_CAR_COOKIE", value, { expires : 30 }); 
		}catch(e) { alert("提示，请启用浏览器Cookie.");}
		
		// Ajax后台删除数据
		$.ajax({
			type: "POST",
			url: "<c:url value='/zxmall/ShoppingCar.do' />",
			data: { "method":"ajaxDelCar", "goods_id":goods_id, "timestamp":new Date().getTime() },
			dataType: "json",
			sync: true,
			error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
			success: function(result) {
				if(result.status == '0'){
					alert(status.msg);
				} else {
					$("#div_" + goods_id).remove();
					
					// 处理序列号
					var  i = 1;
					$(".li_num_class").each(function(){
						$(this).html(i++);
					});
					alert("提示，删除成功！");
					location.href="<c:url value='/zxmall/ShoppingCar.do' />";
				}
			}
		});
	});
	
	// 注册绑定事件，监测数量改变计算合计  
	$(".buy_num_class").bind("input propertychange", function() {
		var goods_id = $(this).attr("data");
		var buy_num = parseInt($("#buy_num_" + goods_id).val());
		if(buy_num<1 || $("#buy_num_" + goods_id).val() == ""){
			buy_num=1;
			$("#buy_num_" + goods_id).val(1);
		}
		var price = $("#price_" + goods_id).val();
		var original_price = $("#original_price_" + goods_id).val();
		if(isNaN(buy_num)) return;
		<c:if test="${zxmall.user_type eq 2 or empty zxmall }">
		$("#all_price_" + goods_id).html(buy_num * price);
		</c:if>
		<c:if test="${zxmall.user_type eq 22 }">
		$("#all_price_" + goods_id).html(buy_num * original_price);
		</c:if>
		
		// 判断库存是否够
		var had_stock_num = parseInt($("#buy_num_" + goods_id).attr("lang")); // 获取现有库存数		
		if(buy_num > had_stock_num){
			$("#stocks_flag_" + goods_id).empty().html('<font color="red" style="margin-left:10px;line-height:30px;">抱歉，暂时缺货！</font>');
		} else {
			$("#stocks_flag_" + goods_id).empty().html('<font style="margin-left:10px;line-height:30px;" class="stocks_flag">现货，下单后发货！</font>');
		}
	});
	
	// 减少一个数量
	$(document).delegate(".num_jian_class", "click", function() { 
		var goods_id = $(this).attr("data");
		var num = parseInt($("#buy_num_" + goods_id).val()) - 1;
		if(num <= 0) num = 1;
		$("#buy_num_" + goods_id).val(num);
		
		// 判断库存是否够
		var had_stock_num = parseInt($("#buy_num_" + goods_id).attr("lang")); // 获取现有库存数
		if(num<1){
			$("#buy_num_" + goods_id).val(1);
		}
		if(num > had_stock_num){
			$("#stocks_flag_" + goods_id).empty().html('<font color="red" style="margin-left:10px;line-height:30px;">抱歉，暂时缺货！</font>');
		} else {
			$("#stocks_flag_" + goods_id).empty().html('<font style="margin-left:10px;line-height:30px;" class="stocks_flag">现货，下单后发货！</font>');
		}
		
		// 计算合计价格
		var price = $("#price_" + goods_id).val();
		var original_price = $("#original_price_" + goods_id).val();
		<c:if test="${zxmall.user_type eq 2 or empty zxmall }">
		$("#all_price_" + goods_id).html(num * price);
		</c:if>
		<c:if test="${zxmall.user_type eq 22 }">
		$("#all_price_" + goods_id).html(num * original_price);
		</c:if>
	}); 
	
	// 增一个数量
	$(document).delegate(".num_jia_class", "click", function(){
		var goods_id = $(this).attr("data");
		var num = parseInt($("#buy_num_" + goods_id).val()) + 1;
		if(num >= 999) num = 999;
		$("#buy_num_" + goods_id).val(num);
		
		// 判断库存是否够
		var had_stock_num = parseInt($("#buy_num_" + goods_id).attr("lang")); // 获取现有库存数据
		if(num > had_stock_num){
			$("#stocks_flag_" + goods_id).empty().html('<font color="red" style="margin-left:10px;line-height:30px;">抱歉，暂时缺货！</font>');
		} else {
			$("#stocks_flag_" + goods_id).empty().html('<font style="margin-left:10px;line-height:30px;" class="stocks_flag">现货，下单后发货！</font>');
		}
		
		// 计算合计价格
		var price = $("#price_" + goods_id).val();
		var original_price = $("#original_price_" + goods_id).val();
		<c:if test="${zxmall.user_type eq 2 or empty zxmall }">
		$("#all_price_" + goods_id).html(num * price);
		</c:if>
		<c:if test="${zxmall.user_type eq 22 }">
		$("#all_price_" + goods_id).html(num * original_price);
		</c:if>
		
	});
});

//正则表达式：只能输入数字
function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
	});
}
//]]></script>
</body>
</html>