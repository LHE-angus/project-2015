<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no"> 
<title>触网</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" /> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<style type="text/css">
.new-header .new-srch-box{top:6px}
.maincont{padding:5px 12px}
.maincont.new-mu_l2 .new-mu_tmb{width:100px;height:100px;margin:0 10px 0 0}
.maincont .new-mu_l2a{padding:0}
.maincont .new-mu_l2w{margin:0;border:none}
.maincont .new-mu_l2{padding:5px 0;border-bottom:1px solid #e8e5e5}
.maincont .new-mu_l2h{max-height:40px}
.maincont .new-mu_l2h .new-txt-rd2{min-width:186px;font-size:12px;color:#e4393c}
.maincont .new-mu_l2c{display:block}
.maincont .new-mu_l2c .new-txt{font-size:12px}
.maincont .new-mu_l2c .new-sale-icon{position:absolute;right:0}
.maincont .new-mu_l2c .new-add,.maincont .new-mu_l2c .new-del,.maincont .new-mu_l2c .new-add2,.maincont .new-mu_l2c .new-del2{display:inline-block;width:18px;height:18px;margin-left:5px;background-color:#fd9d00;line-height:18px;font-size:12px;color:#fff;text-align:center}
.maincont .new-mu_l2c .new-del{margin-left:5px;background-color:#25b504}
.maincont .new-mu_l2c .new-add2{margin-left:5px;background-color:#e4393c}
.maincont .new-mu_l2c .new-del2{margin-left:5px;background-color:#b87404} 
.new-mu_tmb{_display:inline;float:left;clear:both;margin:.7em 10px .6em}
.new-mu_tmb2{_display:inline;float:left;clear:both;margin:.new-79em 10px .6em}
.new-mu_l2w{clear:both;margin:.5em 10px;border-top:1px dotted #bebebe}
.new-mu_l2a{display:block;overflow:hidden;clear:both;padding:.22em 5px}
.new-mu_l2{_zoom:1}.new-mu_l2 .new-mu_tmb{margin:.2em 10px .1em 0}
.new-mu_l2cw{display:block;overflow:hidden;_zoom:1}
.new-mu_l2h{display:block;margin:5px 0 .3em;font-size:12px;font-weight:400;color:#6e6e6e}
.new-mu_l2c{font-size:16px;color:#6e6e6e}
.new-mu_l2c del{padding-left:20px}
.new-txt-rd2 {color: #E4393C;}  
</style>
</head>
<body><div class="top_class"><span class="lspan"><a href="javascript:void(0);history.back();"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>产品中心</h3> 
	<a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"><span>触网</span></a>
	<div class="new-jd-tab" style="top:45px;display:none;" id="jdkey">
	<div class="topsearch">
		<form method="post" action="<c:url value='/webservice/wap/KonkaBcompPd.do' />" id="search_form_top">
		    <table style="width:100%">
			    <tr>
			    <td><div class="searcharea"><input name="pd_sn_or_pd_name_like" id="pd_sn_or_pd_name_like" class="input_search" type="text" maxlength="20" value="搜索商品" onfocus="if(value=='搜索商品') {value='';}" onblur="if (value=='') {value='搜索商品';}"/></div></td>
			    <td class="padl5" width="60">
			    <a href="javascript:void(0);" id="search_btn_sub_top" class="new-tbl-cell"><span class="icon2">搜索</span></a>
			    <!-- a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"  style="margin-top:0;" ><span>触网</span></a -->
			    </td>
			    </tr>
		    </table>
	    </form> 
    </div>	
	<div class="new-tbl-type">
	<a href="<c:url value='/webservice/wap/Index.do' />" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p>	</a>
	<a href="<c:url value='/webservice/wap/ProdType.do' />" class="new-tbl-cell"><span class="icon2">产品分类</span><p style="color:#6e6e6e;">产品分类</p></a>
	<a href="<c:url value='/webservice/wap/ShoppingCar.do' />" id="html5_cart" class="new-tbl-cell"><span class="icon3 on">购物车</span><p style="color:#6e6e6e;" class="on">购物车</p></a>
	<a href="<c:url value='/webservice/wap/center/Index.do' />" class="new-tbl-cell"><span class="icon4">会员中心</span> <p style="color:#6e6e6e;">会员中心</p></a>
	</div>
	</div>
</div>
<div id="content"> 
<div class="new-jd-tab" >
<div class="new-tbl-type" >
<a href="#"  class="new-tbl-cell sale_num_class" title="" ${not empty af.map.order_by_sale_num_desc ? "style='background:#D3DDCF;color:#fff;'":""} ><p style="color:#6e6e6e;font-size:14px;">销量</p></a>
              <a href="#"  class="new-tbl-cell order_price_class" title="" ${not empty af.map.order_by_price_asc or not empty af.map.order_by_price_desc? "style='background:#D3DDCF;color:#fff;'":""}>
              <p style="color:#6e6e6e;font-size:14px;">价格    ${not empty af.map.order_by_price_asc ?"↑":""} ${not empty af.map.order_by_price_desc ?"↓":""}</p></a>
</div></div>
<div class="mainbox">  
<div class="maincont">
	<form id="listForm" name="listForm" method="post" action="KonkaBcompPd.do" >
            <input type="hidden" name="method" value="list" />
            <input type="hidden" name="prod_type" id="prod_type" value="${fn:escapeXml(af.map.prod_type)}"/>
            <input type="hidden" name="pd_type" id="pd_type" value="${fn:escapeXml(af.map.pd_type)}"/>
            <input type="hidden" name="pd_size_type" id="pd_size_type" value="${fn:escapeXml(af.map.pd_size_type)}"/>
            <input type="hidden" name="label_of_cate" id="label_of_cate" value="${fn:escapeXml(af.map.label_of_cate)}"/>
            <input type="hidden" name="pd_price" id="pd_price" value="${fn:escapeXml(af.map.pd_price)}"/>
            <input type="hidden" name="pd_sn_or_pd_name_like" id="pd_sn_or_pd_name_like" value="${fn:escapeXml(af.map.pd_sn_or_pd_name_like)}"/>
            <input type="hidden" name="order_by_sale_num_desc" id="order_by_sale_num_desc"  value="${fn:escapeXml(af.map.order_by_sale_num_desc)}"/>
            <input type="hidden" name="order_by_price_desc" id="order_by_price_desc" value="${fn:escapeXml(af.map.order_by_price_desc)}" />
            <input type="hidden" name="order_by_price_asc" id="order_by_price_asc" value="${fn:escapeXml(af.map.order_by_price_asc)}"/>
            <input type="hidden" name="brand_name" id="brand_name"  value="${fn:escapeXml(af.map.brand_name)}"/>
         	<input type="hidden" name="no_title" value="${fn:escapeXml(af.map.no_title)}"/>	 	
			<c:if test="${empty entityList }"><p style="height:70px;line-height:40px;text-align:center;">暂无商品</p></c:if>
			
			<ul class="new-mu_l2w"> 
			
			<c:forEach items="${entityList}" var="cur" varStatus="vs">	 
				<li class="new-mu_l2">
					<a href="<c:url value='/webservice/wap/PdShow.do?goods_id=${cur.id}' />" class="new-mu_l2a" title="${fn:escapeXml(cur.pd_name)}">
					<span class="new-mu_tmb"><img src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}.jpg" width="100" ></span>
					<span class="new-mu_l2cw"><c:if test="${empty no_title }">
					<strong class="new-mu_l2h">${cur.pd_name}</strong></c:if>
					<span class="new-txt-rd2 new-elps"></span>
					<span class="new-mu_l2c"><strong class="new-txt-rd2">￥<fmt:formatNumber value="${cur.map.price}" pattern="0" /></strong></span>
					<span class="new-mu_l2c new-p-re"><span class="new-txt">销量：${cur.sale_num}件</span><span class="new-sale-icon"></span></span>
					</span>
					 </a>
				 </li> 
			 </c:forEach>
			</ul>
	</form> 
	<div style="margin:15px 0;text-align:center;"><form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaBcompPd.do">
	 	 <input type="hidden" name="method" value="list" />
	 	 <input type="hidden" id="requestPage" name="pager.requestPage" value="${fn:escapeXml(af.map.pager.currentPage)}" />
	 	 <input type="hidden" name="prod_type" value="${fn:escapeXml(af.map.prod_type)}"/>
         <input type="hidden" name="pd_type" value="${fn:escapeXml(af.map.pd_type)}"/>
         <input type="hidden" name="pd_size_type" value="${fn:escapeXml(af.map.pd_size_type)}"/>
         <input type="hidden" name="label_of_cate" value="${fn:escapeXml(af.map.label_of_cate)}"/>
         <input type="hidden" name="pd_price" value="${fn:escapeXml(af.map.pd_price)}"/>
         <input type="hidden" name="pd_sn_or_pd_name_like" value="${fn:escapeXml(af.map.pd_sn_or_pd_name_like)}"/>
         <input type="hidden" name="order_by_sale_num_desc" value="${fn:escapeXml(af.map.order_by_sale_num_desc)}"/>
         <input type="hidden" name="order_by_price_desc" value="${fn:escapeXml(af.map.order_by_price_desc)}" />
         <input type="hidden" name="order_by_price_asc" value="${fn:escapeXml(af.map.order_by_price_asc)}"/>
         <input type="hidden" name="brand_name" value="${fn:escapeXml(af.map.brand_name)}"/>
         <input type="hidden" name="no_title" value="${fn:escapeXml(af.map.no_title)}"/>	 	
		 <span style="margin:0 10px;text-align:center;">当前页${af.map.pager.currentPage}/${af.map.pager.pageCount}</span> 
		 <c:if test="${af.map.pager.currentPage le 1}"><span style="margin:0 10px;text-align:center;"><span>上一页</span></span></c:if> 
		 <c:if test="${af.map.pager.currentPage gt 1}"><span style="margin:0 10px;text-align:center;"><a href='#' onclick="gotoPage(-1);"><span>上一页</span></a></span></c:if>   
         <c:if test="${af.map.pager.currentPage lt af.map.pager.pageCount}">
         <span style="margin:0 10px;text-align:center;"><a href='#' onclick="gotoPage(1);"><span>下一页</span></a></span>
         </c:if><c:if test="${af.map.pager.currentPage ge af.map.pager.pageCount}">
         <span style="margin:0 10px;text-align:center;"><span>下一页</span></span>
         </c:if>
    </form>
	</div>
</div>
<div class="clear"></div>
</div>
</div>
<script type="text/javascript">//<![CDATA[

$(document).ready(function(){
//价格
$(".sale_num_class").click(function(){
	if($("#order_by_sale_num_desc").val()==""){
		$("#order_by_sale_num_desc").val("1");
		$("#order_by_price_desc").val("");
		$("#order_by_price_asc").val("");
	}else{
		$("#order_by_sale_num_desc").val("");
		$("#order_by_price_desc").val("");
		$("#order_by_price_asc").val("");	
	}
	listForm.submit();
});

$(".order_price_class").click(function(){
	$("#order_by_sale_num_desc").val("");
	var price1=$("#order_by_price_asc").val();
	if(price1==""){
		$("#order_by_price_desc").val("");
		$("#order_by_price_asc").val("1");
	}else{
		$("#order_by_price_desc").val("1");
		$("#order_by_price_asc").val("");
	}
	listForm.submit();
});

$(document).delegate("#search_btn_sub_top", "click", function(){
	if($("#pd_sn_or_pd_name_like").val()=='搜索商品'){
		$("#pd_sn_or_pd_name_like").val('');
	}
	$("#search_form_top").submit();
});

});
//分页                                         
function gotoPage(v){
	var page=parseInt(bottomPageForm.requestPage.value)+v;
	bottomPageForm.requestPage.value = page; 
	bottomPageForm.submit();
} 
function showNav(){  
	if(document.getElementById("jdkey").style.display=='none'){ 
		document.getElementById("jdkey").style.display='block';
	}else{
		document.getElementById("jdkey").style.display='none';
	} 
}

//]]></script>
</body>
</html>
