<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/epp.css" />
<style type="text/css">
#bg{display:none;position: absolute;top:0%;left: 0%; width: 100%;height: 5500px;background-color: black;z-index:10001;-moz-opacity: 0.7;opacity:.70;filter: alpha(opacity=70);} 
.palyer_video_tc {display: none;  position: absolute;  top:20%;  left: 22%; z-index:10002;border: 6px solid;height: 420px;padding-top: 0px;width:720px;margin:80px auto;color: #000000;}
.palyer_right{float:right;}
.palyer_video_tc .palyer_close {background: none repeat scroll 0 0 #000000; font-size: 14px;line-height: 30px; margin-right:-40px; margin-top: 0;text-align: center; width: 40px;}
.palyer_video_tc .palyer_close a {color: #FFFFFF;text-decoration:none;}
</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body> 
<!-- top start -->
<div class="topbox">
<!--  div id="www_necc_com" style="position:absolute;z-index:99999;"> 
<img src="${ctx}/styles/member/images/guanggaotu.jpg"  border="0" /> 
</div>
<script>//广告飞窗
var x = 50,y = 60;
var xin = true, yin = true;
var step = 1;
var delay = 10;
var obj=document.getElementById("www_necc_com");
function floatwww_necc_com() {
var L=T=0;
//by www.necc.com
var R = document.body.clientWidth-obj.offsetWidth;
var B = document.body.clientHeight-obj.offsetHeight;
if(B>4000){
	B=$(window).height()-obj.offsetHeight;
}
obj.style.left = x + document.body.scrollLeft;
obj.style.top = y +$(window).scrollTop();// document.body.scrollTop; 
x = x + step*(xin?1:-1);
if (x < L) { xin = true; x = L;}
if (x > R){ xin = false; x = R;}
y = y + step*(yin?1:-1);
if (y < T) { yin = true; y = T;}
if (y >B ) { yin = false; y = B;} 
}
var itl= setInterval("floatwww_necc_com()", delay);
obj.onmouseover=function(){clearInterval(itl);}
obj.onmouseout=function(){itl=setInterval("floatwww_necc_com()", delay);}
</script-->
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/_nav.jsp" flush="true" />
<div class="hotsell" style="margin-bottom:5px;">
	<div class="hotsell_l" style="width:876px;">
    	<div class="hotsell_tit"><span>热卖推荐</span></div>
        <div class="hotsell_cont">
        <c:forEach items="${bcomp_pd_list_tj_5}" var="cur" varStatus="vs" end="4">
        	<ul>
            	<li><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank"><img src="${ctx}/${cur.main_pic}" width="160" height="105" /></a></li>
                <li class="name"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank"><span style="word-wrap:break-word;word-break:break-all;">${fnx:abbreviate(cur.pd_name, 2 * 30, '')}</span></a></li>
                <c:if test="${ecUser.user_type eq 1 }"> <li class="price" style="margin-left:0"><b>￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></b> <span>市场价 ￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></span></li></c:if>
           		<c:if test="${ecUser.user_type eq 2 }"> <li class="price" style="margin-left:0"><b>￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></b><font color="#005AA0" > 已售出：${cur.sale_num}件</font> </li></c:if>
            </ul>
        </c:forEach>    
        </div>
    </div>
    <div class="hotsell_r" style="width:316px;">
    	<div class="information" style="width:316px;">
	     	<div class="information_tit" style="width:316px;"><span>康佳视讯</span></div>
		    <div class="information_cont" style="margin-left:-5px;margin-top:-5px;width:316px;height: 252px;">
		    <iframe border="0" src="${ctx }/member/FlvPlayer.do?method=showImg&id=838505&height=249" frameborder="0" marginheight="0" marginwidth="0" width="312" height="249" scrolling="no"></iframe>
		    </div>
	    </div>
    </div> 
</div>
<div class="logo2">
  <div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=0&label_of_cate=0" title="更多新品" target="_blank"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多新品&nbsp;&nbsp;&nbsp;&nbsp;</font></a></div>
</div><c:if test="${fn:length(bcomp_pd_list_0) ge 7}">
<div class="main1" style="height:608px;">
  <div class="m1-l">
    <table border="0" width="1200" height="608"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_0}" var="cur" varStatus="vs" begin="0" end="7">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if><c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
            <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;<font color="#666666" style="font-size:12px"><del>市场价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></del></font>
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div></c:if><c:if test="${fn:length(bcomp_pd_list_0) le 6}">
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_0}" var="cur" varStatus="vs" begin="0" end="3">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if><c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
            <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;<font color="#666666" style="font-size:12px"><del>市场价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></del></font>
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div></c:if>
<div class="logo5">
  <div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=0&label_of_cate=7"  title="更多精品" target="_blank"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多精品&nbsp;&nbsp;&nbsp;&nbsp;</font></a></div>
</div>
<c:if test="${fn:length(bcomp_pd_list_7) gt 6}">
<div class="main1" style="height:608px;">
  <div class="m1-l">
    <table border="0" width="1200" height="608"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_7}" var="cur" varStatus="vs" begin="0" end="7">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if><c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;<font color="#666666" style="font-size:12px"><del>市场价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></del></font>
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div></c:if><c:if test="${fn:length(bcomp_pd_list_7) le 6}">
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_7}" var="cur" varStatus="vs" begin="0" end="3">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if><c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;<font color="#666666" style="font-size:12px"><del>市场价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></del></font>
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div></c:if>
<div class="logo3">
  <div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=0&label_of_cate=2"  title="更多热销" target="_blank"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多热销&nbsp;&nbsp;&nbsp;&nbsp;</font></a></div>
</div><c:if test="${fn:length(bcomp_pd_list_2) gt 6}">
<div class="main1" style="height:608px;">
  <div class="m1-l">
    <table border="0" width="1200" height="608"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_2}" var="cur" varStatus="vs" begin="0" end="7">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if><c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;<font color="#666666" style="font-size:12px"><del>市场价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></del></font>
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div></c:if><c:if test="${fn:length(bcomp_pd_list_2) le 6}">
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_2}" var="cur" varStatus="vs" begin="0" end="3">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if><c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;<font color="#666666" style="font-size:12px"><del>市场价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></del></font>
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div></c:if>  
<div class="clear"></div> 
<div class="logo4"><div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=0&label_of_cate=3"  title="更多特惠" target="_blank"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多特惠&nbsp;&nbsp;&nbsp;&nbsp;</font></a></div></div>
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_3}" var="cur" varStatus="vs" begin="0" end="3">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳</c:if>&nbsp;${cur.pd_sn}&nbsp;${cur.pd_name}"><c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳</c:if>&nbsp;<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;<font color="#666666" style="font-size:12px"><del>市场价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></del></font>
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div>
<!-- 
<div class="logo_dzcg">
  <div class="lef"><a href="${ctx}/member/Dzcg.do"  title="更多大宗采购" target="_blank"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多大宗采购&nbsp;</font></a></div>
</div>
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_x}" var="cur" varStatus="vs" begin="0" end="3">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}&nbsp;${cur.pd_name}"><c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if><c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div>
 -->
<div class="logo_shdq">
  <div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=3" title="更多生活电器" target="_blank"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多生活电器&nbsp;</font></a></div>
</div>
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_31}" var="cur" varStatus="vs" begin="0" end="3">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}">康佳<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;<font color="#666666" style="font-size:12px"><del>市场价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></del></font>
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div>
<div class="logo_bx">
  <div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=4" title="更多冰箱" target="_blank"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多冰箱&nbsp;&nbsp;&nbsp;&nbsp;</font></a></div>
</div>
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_4}" var="cur" varStatus="vs" begin="0" end="3">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}">康佳<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;<font color="#666666" style="font-size:12px"><del>市场价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></del></font>
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div>
<div class="logo_xyj">
  <div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=5"  title="更多洗衣机" target="_blank"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多洗衣机&nbsp;&nbsp;&nbsp;</font></a></div>
</div>
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_5}" var="cur" varStatus="vs" begin="0" end="3">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}">康佳<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;<font color="#666666" style="font-size:12px"><del>市场价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></del></font>
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div>
<!--div class="logo_kt">
  <div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=6" target="_blank">更多</a></div>
</div>
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_6}" var="cur" varStatus="vs" begin="0" end="3">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}">康佳<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;<font color="#666666" style="font-size:12px"><del>市场价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></del></font>
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div  -->
<div class="logo_sj">
  <div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=7"  title="更多手机" target="_blank"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多手机&nbsp;&nbsp;&nbsp;</font></a></div>
</div>
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_9}" var="cur" varStatus="vs" begin="0" end="3">  
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}">康佳<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;<font color="#666666" style="font-size:12px"><del>市场价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></del></font>
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div>
<div class="logo_bjb">
  <div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=9"  title="更多戴尔笔记本" target="_blank"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多戴尔笔记本</font></a></div>
</div>
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_10}" var="cur" varStatus="vs" begin="0" end="3">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="戴尔${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288" /></a>
           <div class="price">
	         <a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="戴尔${cur.pd_sn}&nbsp;${cur.pd_name}">戴尔<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> <c:if test="${ecUser.user_type eq 1 }">
            <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></font></font>
             &nbsp;<font color="#666666" style="font-size:12px"><del>市场价：￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></del></font>
             </c:if><c:if test="${ecUser.user_type eq 2 }"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             </c:if>&nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div>
<div class="clear"></div>
<div class="tab">
  <table border="1" cellpadding="0" cellspacing="0" bordercolor="#e3e3e3" >
    <tr ><td height="39px" colspan="8" class="bj" ></td></tr>
    <tr>     
    <c:forEach items="${bcomp_pd_list_top_8}" var="cur">
      <td align="center">
      <div class="sz"><a title="${cur.pd_name}" href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank"><img alt="${cur.pd_name}" height="92" width="128" src="${ctx}/${cur.main_pic}" style="max-height:100px;" /></a></div>
      <div style="text-align:center; margin-top:10px;"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" target="_blank" title="<c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>${cur.pd_sn}" style="font-size:13px;font-color:#666666;"><c:if test="${fn:contains(cur.pd_name, 'HYUNDAI')}">HYUNDAI&nbsp;</c:if>
               <c:if test="${not fn:contains(cur.pd_name, 'HYUNDAI')}">康佳&nbsp;</c:if>&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}</a></div>
      </td> 
    </c:forEach>
    </tr>
  </table>
</div>
<div id="bg"></div>
<div id="show" class="palyer_video_tc palyer_clear"> 
	<div class="palyer_close palyer_right"><a id="wjPop-close" href="javascript:void(0);"  onclick="hidediv();">关闭</a></div> 
	 <iframe border="0" id="player" src="" frameborder="0" marginheight=0 marginwidth=0 width="720" height="426" scrolling="no"></iframe>
</div>   
<jsp:include page="/member/__inc/footer.jsp" flush="true" />
<script type="text/javascript">
//循环显示咨询信息
var zx=0;
function showZx(){
	 <c:forEach items="${articleList}" var="cur" begin="9" end="9">
	if(zx==0){
	zx=1;
	$("#zx1").hide();
	$("#zx2").show();
	}else{
	zx=0;
	$("#zx2").hide();
	$("#zx1").show();	
	}
	</c:forEach>
}
function hidediv() {
    window.top.document.getElementById("bg").style.display ='none';
    window.top.document.getElementById("show").style.display ='none';
    window.top.document.getElementById("player").src="";
} 
</script>
</body>
</html>
