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
</style>
</head>
<body>
<!-- top -->
<!--<jsp:include page="_top.jsp" flush="true" />-->	
<!-- head:nav -->
<jsp:include page="_head.jsp" flush="true" />

<div class="house">首页 > 平板电视 > 康佳${af.map.md_name} </div>
<div class="detailed">
	<!-- 图片相册Begin -->
	<div class="detailed_l" style="position:relative;">
		<jsp:include page="_pdshow.jsp" flush="true" />
		<jsp:include page="_albums.jsp" flush="true" />
	</div>
  <!--<div class="detailed_l">
    <div class="d_box"><img src="${ctx}/styles/customer/shop/images/350.jpg" width="350" height="350" /></div>
    <div class="d_box_down">
      <div class="d_one"><img src="${ctx}/styles/customer/shop/images/b_l.jpg" width="6" height="11" /></div>
      <div class="d_two">
        <ul>
          <li class="bbr"><img src="${ctx}/styles/customer/shop/images/52.jpg" width="52" height="52" /></li>
          <li><img src="${ctx}/styles/customer/shop/images/52.jpg" width="52" height="52" /></li>
          <li><img src="${ctx}/styles/customer/shop/images/52.jpg" width="52" height="52" /></li>
          <li><img src="${ctx}/styles/customer/shop/images/52.jpg" width="52" height="52" /></li>
        </ul>
      </div>
      <div class="d_three"><img src="${ctx}/styles/customer/shop/images/b_r.jpg" width="6" height="11" /></div>
    </div>
  </div>-->
	<!-- 图片相册End -->
  <!--left end-->
	  <div class="detailed_r">
	    <div class="zi">
	    	<font class="black16px">【${af.map.dept_name}】康佳（KONKA）&nbsp;${af.map.md_name}&nbsp;${af.map.pd_size}英寸&nbsp;${af.map.pd_name}</font><br />
	      	<font class="red16px">${af.map.pd_desc}</font>
	    </div>
	    <!--<div class="zi_list">
	      <div class="z_l">商品编码：</div>
	      <div class="z_r">${af.map.pd_sn}</div>
	    </div>-->
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
	      <div class="z_r"><font class="red"><fmt:formatNumber value="${af.map.sale_price}" type="currency" /></font></div>
	    </div>
	    
	    <!--<div class="zi_list">
	      <div class="z_l">促销信息：</div>
	      <div class="z_r_1">
	        <div class="z_r_c">直降</div>
	        <div style=" float:left; width:500px; line-height:20px;">&nbsp;已优惠￥699325元</div>
	      </div>
	    </div>
	    <div class="zi_list">
	      <div class="z_l">配送区域：</div>
	      <div class="qxz">请选择</div>
	    </div>
	    <div class="zi_list">
	      <div class="z_l">用户评价：</div>
	      <div style="float:left; width:170px; padding-top:8px;"><img src="${ctx}/styles/customer/shop/images/star.jpg" width="83" height="12" />(已有1人评价)</div>
	      <div style="float:left; padding-top:4px;"><img src="${ctx}/styles/customer/shop/images/kf.jpg" width="85" height="21" /></div>
	    </div>
	    <div class="zi_list">
	      <div class="z_l">温馨提示：</div>
	      <div class="z_r_1">
	        <div class="z_r_c">补贴</div>
	        <div style=" float:left; width:500px; line-height:20px;">可参加节能补贴，签收后在返400元现金 <font class="blue">查看细则</font></div>
	      </div>
	    </div>-->
  		<form action="${ctx}/customer/manager/JxcKonkaOrderRegister.do" method="post" id="id_form">
  		<input type="hidden" name="method" value="add" />
  		<input type="hidden" name="mod_id" value="102010000" />
  		<input type="hidden" name="pd_id" value="${af.map.pd_spec}" />
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
	    </form>
	  </div>
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
		<!--<dl class="switchBox" id="switchBox2">
			<dt class="gg_list" id="nav" style="width:784px;">
				<span class="on"><a href="#">产品介绍</a></span>
				<span><a href="#">规格参数</a></span>
				<span><a href="#">装箱清单</a></span>
				<span><a href="#">售后服务</a></span>
			</dt>
			<dd>
				<div style="padding-top:20px; text-align:center;"><img src="${ctx}/styles/customer/shop/images/ys_2.jpg" width="762" height="699" /></div>
			</dd>
			<dd>1</dd>
			<dd>2</dd>
			<dd>3</dd>
		</dl>-->
		<ul class="gg_list tab_menu" id="nav" style="width:784px;">
			<li class="current"><a href="javascript:void(0);">产品介绍</a></li>
			<li><a href="javascript:void(0);">规格参数</a></li>
			<li><a href="javascript:void(0);">装箱清单</a></li>
			<li><a href="javascript:void(0);">售后服务</a></li>
		</ul>
		<div class="tab_box">
			<div><div style="padding-top:20px; text-align:center;"><img src="${ctx}/styles/customer/shop/images/ys_2.jpg" width="762" height="699" /></div></div>
			<div class="hide">图片</div>
			<div class="hide">军事</div>
			<div class="hide">美女</div>
		</div>
		<script type="text/javascript">
			var div=document.getElementById("nav");
			var w = getStyle(div,'marginTop');
			var _top = $("#nav").position().top;

			$("#nav").smartFloat(w);
			//$("#switchBox2").switchTab({effect: "slide"});
			
			$('.xx_right').Tabs({event:'click',callback:tabcallback});	
			function tabcallback(){
				//alert("我是回调函数 :)");
				$("html,body").animate({scrollTop: _top}, 1000);
			}
			
			function getStyle(obj,attr){   
				if(obj.currentStyle){ 
					return obj.currentStyle[attr];   
				} else { 
					return document.defaultView.getComputedStyle(obj, null)[attr]; 
				}   
			}

			
			//nav的margin-top值
			//$("#nav").css("margin-top");
			//document.getElementById("nav").currentStyle["marginTop"];
		</script>
		<!--<div class="gg_down">
			<ul>
				<li>商品名称：康佳 DADDSFSA</li>
				<li>商品名称：康佳 DADDSFSA</li>
				<li>商品名称：康佳 DADDSFSA</li>
				<li>商品名称：康佳 DADDSFSA</li>
				<li>商品名称：康佳 DADDSFSA</li>
				<li>商品名称：康佳 DADDSFSA</li>
			</ul>
		</div>-->
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
		$("#id_form").submit();
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