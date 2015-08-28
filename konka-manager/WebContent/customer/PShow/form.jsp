<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户端登录——康佳渠道管理系统</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/shop/css/global.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/shop/css/top.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/shop/css/font.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/shop/css/index.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/shop/css/bottom.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/smartFloat/smartFloat.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jquery.tabs.js"></script>
<style type="text/css">
   .tab_box .hide{display:none;}
   .buy_now_btn {background: url("${ctx}/styles/images/promsg.gif") no-repeat;background-position: 0px -149px;width: 132px;height: 38px;border: 0px;cursor: pointer;}
</style>
</head>
<body>
<!-- top -->
<jsp:include page="_top.jsp" flush="true" />
<!-- head:nav -->
<jsp:include page="_head.jsp" flush="true" />

<div class="house">首页 > 平板电视 > 康佳${af.map.md_name} </div>
<div class="detailed">
	<!-- 图片相册Begin -->
	<div class="detailed_l" style="position:relative;">
		<jsp:include page="_pdshow.jsp" flush="true" />
		<jsp:include page="_albums.jsp" flush="true" />
	</div>
  <html-el:form action="/manager/PShow" method="post" styleClass="class_form">
  	<html-el:hidden property="method" value="addOrder" />
  	<html-el:hidden property="id" value="${af.map.id}" />
	  <div class="detailed_r">
	    <div class="zi">
	    	<font class="black16px">【${af.map.dept_name}】康佳（KONKA）&nbsp;${af.map.md_name}&nbsp;${af.map.pd_size}英寸&nbsp;${af.map.pd_name}</font><br />
	      	<font class="red16px" style="padding-left:10px;">${af.map.pd_desc}</font>
	    </div>
	    <div class="zi_list">
	      <div class="z_l">尺寸：</div>
	      <div class="z_r">${af.map.pd_size}英寸</div>
	    </div>
	    <div class="zi_list">
	      <div class="z_l">分辨率：</div>
	      <div class="z_r">${fn:split(af.map.pd_res, ',')[0]}×${fn:split(af.map.pd_res, ',')[1]}</div>
	    </div>
	    <div class="zi_list">
	      <div class="z_l">商 城 价：</div>
	      <div class="z_r"><font class="red"><fmt:formatNumber value="${af.map.buy_price}" type="currency" /></font></div>
	    </div>
	    <div class="bank">
	      <div style="overflow:hidden;">
	        <div style="float:left; width:70px; padding-top:8px;">购买数量：</div>
	        <div style="float:left;padding-top:6px; padding-right:4px;"><img src="${ctx}/styles/customer/shop/images/jian.jpg" width="13" height="13" style="cursor:pointer;" id="num_jian" /></div>
	        <div style="float:left;">
	          <input type="text" name="num" id="num" class="bank_bottom" value="1" onfocus="javascript:setOnlyInt(this);" maxlength="3" />
	        </div>
	        <div style="float:left;padding-top:6px; padding-left:4px;"><img src="${ctx}/styles/customer/shop/images/jia.jpg" width="13" height="13" style="cursor:pointer;" id="num_jia" /></div>
	      </div>
	      <div class="clear"></div>
	      <div class="bank_box"><img src="${ctx}/styles/images/buy_now.jpg" id="btn_buy" style="cursor:pointer;" /><img src="${ctx}/styles/customer/shop/images/card_4.jpg" style="margin-left:20px;" /></div>
	    </div>
	  </div>
  </html-el:form>
  <!--right end-->
</div>
<!-- two start -->
<div class="xx_two">
	<div class="xx_left">
		<div class="category"><b>相同产品品类的商品</b></div>
		<div class="category_bottom">
			<ul>
				<li><img src="${ctx}/styles/customer/shop/images/7.jpg" width="70" height="70" /><p>康佳 DFDLA8432</p><p><font class="red12px"><b>￥2541.00</b></font></p></li>
				<li><img src="${ctx}/styles/customer/shop/images/7.jpg" width="70" height="70" /><p>康佳 DFDLA8432</p><p><font class="red12px"><b>￥2541.00</b></font></p></li>
				<li><img src="${ctx}/styles/customer/shop/images/7.jpg" width="70" height="70" /><p>康佳 DFDLA8432</p><p><font class="red12px"><b>￥2541.00</b></font></p></li>
				<li><img src="${ctx}/styles/customer/shop/images/7.jpg" width="70" height="70" /><p>康佳 DFDLA8432</p><p><font class="red12px"><b>￥2541.00</b></font></p></li>
				<li><img src="${ctx}/styles/customer/shop/images/7.jpg" width="70" height="70" /><p>康佳 DFDLA8432</p><p><font class="red12px"><b>￥2541.00</b></font></p></li>
				<li><img src="${ctx}/styles/customer/shop/images/7.jpg" width="70" height="70" /><p>康佳 DFDLA8432</p><p><font class="red12px"><b>￥2541.00</b></font></p></li>
			</ul>
		</div>
		<div class="category"><b>相同屏幕尺寸的商品</b></div>
		<div class="category_bottom">
			<ul>
				<li><img src="${ctx}/styles/customer/shop/images/7.jpg" width="70" height="70" /><p>康佳 DFDLA8432</p><p><font class="red12px"><b>￥2541.00</b></font></p></li>
				<li><img src="${ctx}/styles/customer/shop/images/7.jpg" width="70" height="70" /><p>康佳 DFDLA8432</p><p><font class="red12px"><b>￥2541.00</b></font></p></li>
				<li><img src="${ctx}/styles/customer/shop/images/7.jpg" width="70" height="70" /><p>康佳 DFDLA8432</p><p><font class="red12px"><b>￥2541.00</b></font></p></li>
				<li><img src="${ctx}/styles/customer/shop/images/7.jpg" width="70" height="70" /><p>康佳 DFDLA8432</p><p><font class="red12px"><b>￥2541.00</b></font></p></li>
				<li><img src="${ctx}/styles/customer/shop/images/7.jpg" width="70" height="70" /><p>康佳 DFDLA8432</p><p><font class="red12px"><b>￥2541.00</b></font></p></li>
				<li><img src="${ctx}/styles/customer/shop/images/7.jpg" width="70" height="70" /><p>康佳 DFDLA8432</p><p><font class="red12px"><b>￥2541.00</b></font></p></li>
			</ul>
		</div>
		<div class="category"><b>历史浏览</b></div>
		<div class="category_bottom"><ul><li><p>暂无记录，快去逛逛</p></li></ul></div>
	</div>
	
	<div class="xx_right">
		<ul class="gg_list tab_menu" id="nav" style="width:784px;">
			<li class="current"><a href="javascript:void(0);">产品介绍</a></li>
			<li><a href="javascript:void(0);">规格参数</a></li>
			<li><a href="javascript:void(0);">售后服务</a></li>
		</ul>
		<div class="tab_box">
			<div>${type_1}</div>
			<div class="hide">${type_2}</div>
			<div class="hide">${type_3}</div>
		</div>
		<script type="text/javascript">
			var div=document.getElementById("nav");
			var w = getStyle(div,'marginTop');
			var _top = $("#nav").position().top;

			$("#nav").smartFloat(w);
			
			$('.xx_right').Tabs({event:'click', callback:tabcallback});	
			function tabcallback(){
				$("html,body").animate({scrollTop: _top}, 1000);
			}
			
			function getStyle(obj,attr){   
				if(obj.currentStyle){ 
					return obj.currentStyle[attr];   
				} else { 
					return document.defaultView.getComputedStyle(obj, null)[attr]; 
				}   
			}
		</script>
	</div>
</div>

<!-- foot -->
<jsp:include page="_footer.jsp" flush="true" />
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 减少一个数量
	$(document).delegate("#num_jian", "click", function() { 
		var num = parseInt($("#num").val()) - 1;
		if(num <= 0) num = 1;
		$("#num").val(num);
	}); 
	
	// 增减一个数量
	$(document).delegate("#num_jia", "click", function(){
		var num = parseInt($("#num").val()) + 1;
		if(num >= 999) num = 999;
		$("#num").val(num);
	});
	
	// 购买按钮
	$(document).delegate("#btn_buy", "click", function(){
		$(".class_form").submit();
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
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>