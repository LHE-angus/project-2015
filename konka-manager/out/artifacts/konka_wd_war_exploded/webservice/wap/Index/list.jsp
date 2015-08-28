<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" /> 
<title>触网</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/lrtk.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/swiper/swiper.min.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="${ctx}/styles/epp/mobile/swiper/swiper.min.js"></script>
<style type="text/css"> 
.menutab li img{padding: 5px 0px;}
.swiper-container {width:100%;height:100%;}
.swiper-slide { text-align: center;font-size: 18px; background: #fff;/* Center slide text vertically */display: -webkit-box;display: -ms-flexbox;display: -webkit-flex;display: flex;-webkit-box-pack: center;-ms-flex-pack: center;-webkit-justify-content: center;justify-content: center;-webkit-box-align: center; -ms-flex-align: center;-webkit-align-items: center; align-items: center;}
</style>
</head>
<body onclick="document.getElementById('search_top').class='top_class';">
<!--top start-->
<div class="top_class" id="search_top" >
	<div class="topsearch"><form method="post" action="<c:url value='/webservice/wap/KonkaBcompPd.do' />" id="search_form_top">
    <table style="width:100%">
	    <tr>
	    <td width="45"><img alt="触网" src="${ctx}/styles/epp/mobile/images/wap_logo_k.png" width="33" /></td>
	    <td><div class="searcharea"><input name="pd_sn_or_pd_name_like" id="pd_sn_or_pd_name_like" class="input_search" type="text" maxlength="20" value="搜索商品" onfocus="if(value=='搜索商品') {value='';}" onblur="if (value=='') {value='搜索商品';}"/></div></td>
	    <td class="padl5" width="60"><img id="search_btn_sub_top" src="${ctx}/styles/epp/mobile/images/ico_search2.gif" width="20" height="19" />
	    <!-- a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"  style="margin-top:0;" ><span>触网</span></a -->
	    </td>
	    </tr>
    </table></form> 
    </div> 
    <div class="new-jd-tab" style="top:55px;display:none;" id="jdkey">
		<div class="new-tbl-type">
		<a onclick="location.href='<c:url value='/webservice/wap/Index.do' />'" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p>	</a>
		<a onclick="location.href='<c:url value='/webservice/wap/ProdType.do' />'"  class="new-tbl-cell"><span class="icon2">产品分类</span><p style="color:#6e6e6e;">产品分类</p></a>
		<a onclick="location.href='<c:url value='/webservice/wap/ShoppingCar.do' />'"  id="html5_cart" class="new-tbl-cell"><span class="icon3 on">购物车</span><p style="color:#6e6e6e;" class="on">购物车</p></a>
		<a onclick="location.href='<c:url value='/webservice/wap/center/Index.do' />'"  class="new-tbl-cell"><span class="icon4">会员中心</span> <p style="color:#6e6e6e;">会员中心</p></a>
		</div>
	</div>
</div>
<!--top end-->
<!--first start-->
<div id="content" style="width:100%;min-width:320px;max-width:640px;margin: 0 auto;">
<div id="img_div">
	<div class="swiper-container">
		<div class="swiper-wrapper"><c:forEach var="cur" items="${imgList}"  varStatus="vs">
	        <div class="swiper-slide"><a href="<c:url value='${cur.map.url}' />"><img style="width:100%;min-width: 320px;max-width: 640px;max-height:260px;" src="${ctx}/${cur.image_path}"/></a></div>
	  </c:forEach></div>
	        <!-- Add Pagination -->
	        <div class="swiper-pagination"></div><!-- Add Arrows -->
	        <!--<div class="swiper-button-next"></div><div class="swiper-button-prev"></div> -->
	</div>
	<script type="text/javascript">  
	var swiper = new Swiper('.swiper-container', {pagination: '.swiper-pagination',nextButton: '.swiper-button-next',prevButton: '.swiper-button-prev',paginationClickable: true,spaceBetween: 0,centeredSlides: true,autoplay: 2500,autoplayDisableOnInteraction: false});
	</script>
</div>
<div class="mainbox"><!--主菜单-->
  <div class="maincont2"  >
	    <ul class="menutab">
		    <li onclick="location.href='<c:url value='/webservice/wap/ProdType.do?' />'"><a><img alt="产品中心" src="${ctx}/styles/epp/mobile/images/2015_icons-type.png" style="width:40px;height:40px;"/></a><h3><a >分类</a></h3></li>
		    <li onclick="location.href='<c:url value='/webservice/wap/ShoppingCar.do?' />'"><a><img alt="购物车" src="${ctx}/styles/epp/mobile/images/2015_icons-cart.png" style="width:40px;height:40px;"/></a><h3><a >购物车</a></h3></li>
		    <li onclick="location.href='<c:url value='/webservice/wap/center/Orders.do?' />'"><a><img alt="订单查询" src="${ctx}/styles/epp/mobile/images/2015_icons-order.png" style="width:40px;height:40px;"/></a><h3><a >订单</a></h3></li>
		    <li onclick="location.href='<c:url value='/webservice/wap/center/Index.do?' />'"><a><img alt="会员中心" src="${ctx}/styles/epp/mobile/images/2015_icons-my.png" style="width:40px;height:40px;"/></a><h3><a >我的</a></h3></li>
	    </ul> 
    <div class="clear"></div>
  </div> 
  <div>
 	${ecArticleInfo.content } 
  </div>  
  <div class="clear"></div>
  <!--  div class="maincont2" style="text-align:center;" >  
  <img style="width: 49%;min-width: 150px;max-width: 300px;" src="${ctx}/styles/epp/mobile/images/konka_category_bingxiang.png" onclick="location.href='${ctx}/webservice/wap/KonkaBcompPd.do?prod_type=4'"/>
  <img style="width: 49%;min-width: 150px;max-width: 300px;" src="${ctx}/styles/epp/mobile/images/konka_category_kongtiao.png" onclick="location.href='${ctx}/webservice/wap/KonkaBcompPd.do?prod_type=6'"/>
  <img style="width: 49%;min-width: 150px;max-width: 300px;" src="${ctx}/styles/epp/mobile/images/konka_category_xiyiji.png" onclick="location.href='${ctx}/webservice/wap/KonkaBcompPd.do?prod_type=5'"/>
  <img style="width: 49%;min-width: 150px;max-width: 300px;" src="${ctx}/styles/epp/mobile/images/konka_category_shenghuodianqi.png" onclick="location.href='${ctx}/webservice/wap/KonkaBcompPd.do?prod_type=3'"/>
  <img style="width: 49%;min-width: 150px;max-width: 300px;" src="${ctx}/styles/epp/mobile/images/konka_category_peijian.png" onclick="location.href='${ctx}/webservice/wap/KonkaBcompPd.do?prod_type=10'"/>
  <img style="width: 49%;min-width: 150px;max-width: 300px;" src="${ctx}/styles/epp/mobile/images/konka_category_yanbao.png" />
  </div-->
</div>
</div>
<!--first end-->
<script type="text/javascript">
$(document).ready(function(){
	$(document).delegate("#search_btn_sub_top", "click", function(){
		if($("#pd_sn_or_pd_name_like").val()=='搜索商品'){
			$("#pd_sn_or_pd_name_like").val('');
		}
		$("#search_form_top").submit();
	});
});	

function exit(){
	location.href = "${ctx}/webservice/wap/login.do?method=logout";
} 
function showNav(){  
	if(document.getElementById("jdkey").style.display=='none'){ 
		document.getElementById("jdkey").style.display='block';
	}else{
		document.getElementById("jdkey").style.display='none';
	} 
}
</script>
</body>
</html>