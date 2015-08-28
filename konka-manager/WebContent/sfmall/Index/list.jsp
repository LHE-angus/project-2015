<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳&顺丰网上直销商城</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/sfmall/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/sfmall/css/epp.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/sfmall/css/slider.css" />
<link rel="apple-touch-icon-precomposed" href="http://sf.konka.com/images/happycart72.png" />
<link rel="apple-touch-icon-precomposed" sizes="512*512" href="http://sf.konka.com/images/happycart512.png" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<style type="text/css">
#bg{ display: none;  position: absolute;  top: 0%;  left: 0%;  width: 100%;  height: 3500px;  background-color: black;  z-index:10001;  -moz-opacity: 0.7;  opacity:.70;  filter: alpha(opacity=70);} 
.palyer_video_tc {display: none;  position: absolute;  top:20%;  left: 22%; z-index:10002;border: 6px solid;height: 420px;padding-top: 0px;width: 720px;margin:80px auto;color: #000000;}
.palyer_right{ float:right;}
.palyer_video_tc .palyer_close {background: none repeat scroll 0 0 #000000; font-size: 14px;line-height: 30px; margin-right: -40px; margin-top: 0;text-align: center; width: 40px;}
.palyer_video_tc .palyer_close a {color: #FFFFFF;	text-decoration:none;}
</style>
</head>
<body>
<!-- top start -->
<div class="topbox">
<jsp:include page="/sfmall/__inc/top.jsp" flush="true" />
<jsp:include page="/sfmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/sfmall/__inc/_nav.jsp" flush="true" />
<div class="hotsell" style="margin-bottom:5px;">
	<div class="hotsell_l">
    	<div class="hotsell_tit"><span>热卖推荐</span></div>
        <div class="hotsell_cont">
        <c:forEach items="${bcomp_pd_list_tj_5}" var="cur" varStatus="vs" end="4">
        	<ul <c:if test="${vs.count eq 1}">style="margin-left:9px;"</c:if>>
            	<li><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${cur.main_pic}" width="160" height="105" /></a></li>
                <li class="name"><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />"><span style="word-wrap: break-word; word-break: break-all;">${fnx:abbreviate(cur.pd_name, 2 * 30, '')}</span></a></li>
                  <li class="price" style="margin-left:0"><b>￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></b><font color="#005AA0" > 已售出：${cur.sale_num}件</font> </li> 
            </ul>
        </c:forEach>    
        </div>
    </div>
    <div class="hotsell_r">
    	<div class="information" style="width:316px;">
	     	<div class="information_tit" style="width:316px;"><span>康佳视讯</span></div>
		    <div class="information_cont" style="margin-left:-5px;margin-top:-5px;width:316px;height: 252px;">
		    <iframe border="0" src="${ctx }/sfmall/FlvPlayer.do?method=showImg&id=838505&height=249" frameborder="0" marginheight="0" marginwidth="0" width="312" height="249" scrolling="no"></iframe>
		    </div>
	    </div>
    </div> 
</div>
<div class="clear"></div> 
<div class="logo4">  <div class="lef"><a href="${ctx}/sfmall/KonkaBcompPd.do?prod_type=0&label_of_cate=3">更多</a></div></div>
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_3}" var="cur" varStatus="vs" begin="0" end="3">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}">康佳<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
            <div class="pri"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             &nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div>
<div class="logo2">
  <div class="lef"><a href="${ctx}/sfmall/KonkaBcompPd.do?prod_type=0&label_of_cate=0">更多</a></div>
</div> 
<c:if test="${fn:length(bcomp_pd_list_0) ge 7}">
<div class="main1" style="height:608px;">
  <div class="m1-l">
    <table border="0" width="1200" height="608"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_0}" var="cur" varStatus="vs" begin="0" end="7">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}">康佳<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
            <div class="pri">  
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             &nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
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
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}">康佳<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
            <div class="pri"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             &nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div></c:if>
<!-- 
<div class="logo5">
  <div class="lef"><a href="${ctx}/sfmall/KonkaBcompPd.do?prod_type=0&label_of_cate=7">更多</a></div>
</div>
<c:if test="${fn:length(bcomp_pd_list_7) gt 6}">
<div class="main1" style="height:608px;">
  <div class="m1-l">
    <table border="0" width="1200" height="608"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_7}" var="cur" varStatus="vs" begin="0" end="7">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}">康佳<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri">  
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
           &nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
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
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}">康佳<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri">  
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
              &nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div></c:if> 
 -->
<div class="logo3">
  <div class="lef"><a href="${ctx}/sfmall/KonkaBcompPd.do?prod_type=0&label_of_cate=2">更多</a></div>
</div>
<c:if test="${fn:length(bcomp_pd_list_2) gt 6}">
<div class="main1" style="height:608px;">
  <div class="m1-l">
    <table border="0" width="1200" height="608"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_2}" var="cur" varStatus="vs" begin="0" end="7">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}">康佳<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             &nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
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
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}">康佳<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
              <div class="pri"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
              &nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
          </div></td>
          <c:if test="${vs.count mod 4 eq 0 and not vs.last}"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
          <c:if test="${vs.count mod 4 ne 0 and  vs.last}"><td colspan="${4-vs.count mod 4}"> </td></c:if>
          </c:forEach> 
         </tr>
    </table>
  </div>
</div></c:if>  
<div class="logo_shdq1">
  <div class="lef"><a href="${ctx}/sfmall/KonkaBcompPd.do?prod_type=3">更多生活电器</a></div>
</div>
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_31}" var="cur" varStatus="vs" begin="0" end="3">
        <td height="276" width="294px"><div class="com"><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}"><img src="${ctx}/${cur.main_pic}" height="216" width="288"/></a>
           <div class="price">
	         <a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}&nbsp;${cur.pd_name}">康佳<c:out value="${cur.pd_sn}" /> 
              <div><font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 16, '')}</font></div></a>
             </div>
               <div class="pri"> 
             <font color="#d40207" style="font-size:14px">￥<font style="font-size:18px"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></font></font>
             &nbsp;&nbsp;<font color="#005AA0" >已售出：${cur.sale_num}件</font></div>
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
      <div class="sz"><a title="${cur.pd_name}" href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />"><img alt="${cur.pd_name}" height="92" width="128" src="${ctx}/${cur.main_pic}" style="max-height:100px;" /></a></div>
      <div style="text-align:center; margin-top:10px;"><a href="<c:url value='/sfmall/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}" style="font-size:13px;font-color:#666666;">康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}</a></div>
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
<jsp:include page="/sfmall/__inc/footer.jsp" flush="true" />
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
