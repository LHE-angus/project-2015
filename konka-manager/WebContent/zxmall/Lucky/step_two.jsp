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
  <div class="card_b">
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
      <li class="xh" style="width:90px;">序号</li>
      <li class="mc" style="width:410px;">商品名称</li>
      <li class="tp" style="width:170px;">商品图片</li>
      <li class="jg" style="width:140px;">数量</li> 
      <li class="sl" style="width:140px;">价格</li>
      <li class="hj" style="width:200px;">合计</li>  
    </ul>
  </div>
  <form action="<c:url value='/zxmall/Lucky.do' />" method="post" id="shopping_car_form">
  	<input type="hidden" name="method" value="createOrder" />
  	<input type="hidden" name="p_index" value="${p_index}" />  
	<input type="hidden" name="lucky_id" value="${ecLuckyMain.id}" />  
	<input type="hidden" name="buy_json_object" value="${buy_json_object}" />
	 <div class="card_content" id="div_${ecLuckyMain.id}">
	    <ul>
	      <li class="c_one li_num_class" style="width:90px;">1</li>
	      <li class="c_two" style="width:410px;"> <a href="<c:url value='/zxmall/Lucky.do?method=view&lucky_id=${ecLuckyMain.id}' />" target="_blank" title="${ecLuckyMain.title}">${fnx:abbreviate(ecLuckyMain.title, 2 * 16, '...')}</a> </li>
	      <li class="c_three" style="width:170px;"><table border="0" cellspacing="0" cellpadding="0"><tr><td height="70" width="100" style="overflow:hidden;" valign="middle" align="center"><a href="<c:url value='/zxmall/Lucky.do?method=view&lucky_id=${ecLuckyMain.id}' />"><img alt="${ecLuckyMain.title}" src="${ctx}/${fn:substringBefore(ecLuckyMain.main_pic, '.')}_060.jpg" style="max-width:60px;max-height:60px;" /></a></td></tr></table></li>
	      <li class="c_ew" style="width:140px;">${ecLuckyMain.min_num}</li>  
	      <li class="c_ew" style="width:140px;"><font class="red12px"> ￥<fmt:formatNumber value="${ecLuckyMain.price}" pattern="#0" />  </font> </li>
	      <li class="c_ew" style="width:200px;"><font class="red12px"> ￥<fmt:formatNumber value="${ecLuckyMain.price}" pattern="#0" />  </font> </li>
	    </ul>
	  </div> 
  </form>
  <div class="clear"></div>
  <div class="ts"><font class="red12px">提示：</font>1、加入购物车的商品请及时购买；2、商品价格以订单提交时的价格为准。</div>
  <div class="but_card" style="font-size:14px;font-weight:bold;">
    <ul>
      <li><a href="javascript:void(0);" onclick="history.back();" id="step_one_go_shopping" >&lt;&lt; 返回</a></li>
      <li><a style="cursor:pointer;" id="go_step_two_btn">确认提交 &gt;&gt;</a></li>
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
		$("#step_one_go_shopping").attr("disabled", true).html("提交中...");
		$("#go_step_two_btn").attr("disabled", true).html("提交中...");
		$("#shopping_car_form").submit();
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