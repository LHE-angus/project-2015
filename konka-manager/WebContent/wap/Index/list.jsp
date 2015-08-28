<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" /> 
<title>开心猫</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/lrtk.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="${ctx}/styles/epp/mobile/js/pptBox.js"></script> 
</head>
<body onclick="document.getElementById('search_top').class='top_class';">
<!--top start-->
<div class="top_class" id="search_top">
	<div class="topsearch"><form method="post" action="<c:url value='/wap/KonkaBcompPd.do' />" id="search_form_top">
    <table style="width:100%">
	    <tr>
	    <td width="60"><img alt="开心猫" src="${ctx}/styles/epp/mobile/images/wap_logo2.gif" width="33"  /></td>
	    <td><div class="searcharea"><input name="pd_sn_or_pd_name_like" id="pd_sn_or_pd_name_like" class="input_search" type="text" maxlength="20" value="搜索开心猫商品" onfocus="if(value=='搜索开心猫商品') {value='';}" onblur="if (value=='') {value='搜索开心猫商品';}"/></div></td>
	    <td class="padl5"><img id="search_btn_sub_top" src="${ctx}/styles/epp/mobile/images/ico_search2.gif" width="30" height="29" /></td>
	    </tr>
    </table></form>
    </div>
</div>
<!--top end-->
<!--first start-->
<div id="content">
<div id="img_div">
<script> 
var box =new PPTBox();
var width=document.documentElement.clientWidth;
if(width>680){
	width=680;
}
box.width = width; //宽度
box.height = width*11/30;;//高度
box.autoplayer = 4;//自动播放间隔时间  
<c:forEach var="cur" items="${imgList}"  varStatus="vs">
box.add({"url":"${ctx }/${cur.image_path}","href":"<c:url value='${cur.map.url}' />","title":"${vs.count+1}"}); 
</c:forEach>
box.show(); 
</script>
</div>
<div class="mainbox">
	<!--主菜单-->
  <div class="maincont2">
	    <ul class="menutab">
	    <li><a href="<c:url value='/wap/ProdType.do?'/>"><img alt="产品中心" src="${ctx}/styles/epp/mobile/images/ico_product.gif" /></a><h3><a href="#">产品中心</a></h3></li>
	    <li><a href="#"><img alt="优惠活动" src="${ctx}/styles/epp/mobile/images/ico_prefer.gif" /></a><h3><a href="#">优惠活动</a></h3></li>
	    <li><a href="<c:url value='/wap/center/Orders.do?'/>"><img alt="订单查询" src="${ctx}/styles/epp/mobile/images/ico_order.gif" /></a><h3><a href="#">订单查询</a></h3></li>
	    <li><a href="<c:url value='/wap/center/Index.do?'/>" ><img alt="会员中心" src="${ctx}/styles/epp/mobile/images/ico_member.gif" /></a><h3><a href="#">会员中心</a></h3></li>
	    </ul> 
    <div class="clear"></div>
  </div>
  <!--热卖推荐-->  
  <div class="maincont2 martop10">
  	<h3 class="protit4"><a href="${ctx}/wap/KonkaBcompPd.do?prod_type=0&is_tj=1">热卖推荐</a> <span style="float:right;"><a href="${ctx}/wap/KonkaBcompPd.do?prod_type=0&is_tj=1">更多</a></span></h3>
    <ul class="productul"><c:forEach items="${bcomp_pd_list_tj_5}" var="cur" varStatus="vs" begin="0" end="2">
    <li style="width:33%"><div class="pic" ><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${cur.main_pic}" width="100"/></a></div><h4 class="protit6"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />"><font color="red">￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></a></h4></li>
    </c:forEach>
    </ul>
    <div class="clear"></div>
  </div>
  <!--新品电视-->
  <div class="maincont2 martop10">
  	<h3 class="protit4"><a href="${ctx}/wap/KonkaBcompPd.do?prod_type=0&label_of_cate=0">新品电视</a><span style="float:right;"><a href="${ctx}/wap/KonkaBcompPd.do?prod_type=0&label_of_cate=0">更多</a></span></h3>
    <ul class="productul"><c:forEach items="${bcomp_pd_list_0}" var="cur" varStatus="vs" begin="0" end="1">
    <li><div class="pic"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${cur.main_pic}" width="140" /></a></div><h3 class="protit5"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />">${fnx:abbreviate(cur.pd_name, 2 * 22, '')}</a></h3><h4 class="protit6"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />">￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></a></h4></li>
    </c:forEach></ul>
  <div class="clear"></div>
  </div>
  <!--精品电视-->
  <div class="maincont2 martop10">
  	<h3 class="protit4"><a href="${ctx}/wap/KonkaBcompPd.do?prod_type=0&label_of_cate=7">精品电视</a><span style="float:right;"><a href="${ctx}/wap/KonkaBcompPd.do?prod_type=0&label_of_cate=7">更多</a></span></h3>
    <ul class="productul"><c:forEach items="${bcomp_pd_list_7}" var="cur" varStatus="vs" begin="0" end="1">
    <li><div class="pic"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${cur.main_pic}" width="140" /></a></div><h3 class="protit5"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />">${fnx:abbreviate(cur.pd_name, 2 * 22, '')}</a></h3><h4 class="protit6"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />">￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></a></h4></li>
    </c:forEach></ul>
  <div class="clear"></div>
  </div>
    <!--热销电视-->
  <div class="maincont2 martop10">
  	<h3 class="protit4"><a href="${ctx}/wap/KonkaBcompPd.do?prod_type=0&label_of_cate=2">热销电视</a><span style="float:right;"><a href="${ctx}/wap/KonkaBcompPd.do?prod_type=0&label_of_cate=2">更多</a></span></h3>
    <ul class="productul"><c:forEach items="${bcomp_pd_list_2}" var="cur" varStatus="vs" begin="0" end="1">
    <li><div class="pic"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${cur.main_pic}" width="140" /></a></div><h3 class="protit5"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />">${fnx:abbreviate(cur.pd_name, 2 * 22, '')}</a></h3><h4 class="protit6"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />">￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></a></h4></li>
    </c:forEach></ul>
  <div class="clear"></div>
  </div>
  <!--特惠电视-->
  <div class="maincont2 martop10">
  	<h3 class="protit4"><a href="${ctx}/wap/KonkaBcompPd.do?prod_type=0&label_of_cate=3">特惠电视</a><span style="float:right;"><a href="${ctx}/wap/KonkaBcompPd.do?prod_type=0&label_of_cate=3">更多</a></span></h3>
    <ul class="productul"><c:forEach items="${bcomp_pd_list_3}" var="cur" varStatus="vs" begin="0" end="1">
    <li><div class="pic"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${cur.main_pic}" width="140" /></a></div><h3 class="protit5"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />">${fnx:abbreviate(cur.pd_name, 2 * 22, '')}</a></h3><h4 class="protit6"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />">￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></a></h4></li>
    </c:forEach></ul>
  <div class="clear"></div>
  </div><c:if test="${not empty  bcomp_pd_list_31}">
   <!--小家电-->
  <div class="maincont2 martop10">
  	<h3 class="protit4"><a href="${ctx}/wap/KonkaBcompPd.do?prod_type=3">生活电器</a><span style="float:right;"><a href="${ctx}/wap/KonkaBcompPd.do?prod_type=3">更多</a></span></h3>
    <ul class="productul"><c:forEach items="${bcomp_pd_list_31}" var="cur" varStatus="vs" begin="0" end="1">
    <li><div class="pic"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${cur.main_pic}" width="140" /></a></div><h3 class="protit5"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />">${fnx:abbreviate(cur.pd_name, 2 * 22, '')}</a></h3><h4 class="protit6"><a href="<c:url value='/wap/PdShow.do?goods_id=${cur.id}' />">￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></a></h4></li>
    </c:forEach></ul>
  <div class="clear"></div>
  </div></c:if>
  <div class="clear"></div>
</div>
</div>
<!--first end-->
<script type="text/javascript">
$(document).ready(function(){
	$(document).delegate("#search_btn_sub_top", "click", function(){
		if($("#pd_sn_or_pd_name_like").val()=='搜索开心猫商品'){
			$("#pd_sn_or_pd_name_like").val('');
		}
		$("#search_form_top").submit();
	});
});	
</script>
</body>
</html>