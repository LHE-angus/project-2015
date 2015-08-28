<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/epp.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/slider.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- top start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" />
<div class="hotsell" style="margin-top:5px;margin-bottom:5px;">
<br/>
	<div class="hotsell_l"  style="width:1197px;">
    	<div class="hotsell_tit"><span>热卖推荐</span></div>
        <div class="hotsell_cont">
        <c:forEach items="${bcomp_pd_list_tj_5}" var="cur" varStatus="vs">
        	<ul style="<c:if test="${vs.count eq 1}">margin-left:29px;</c:if>width:180px;">
            	<li><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${cur.main_pic}" width="160" height="105" /></a></li>
                <li class="name"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />">康佳${cur.pd_sn}<span style="word-break: break-all;">${fnx:abbreviate(cur.pd_name, 2 * 19, '')}</span></a></li>
                <c:if test="${ecUser.user_type eq 1 }"> <li class="price" style="margin-left:0"><b>￥<fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></b> <span>市场价 ￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></span></li></c:if>
           		<c:if test="${ecUser.user_type eq 2 }"> <li class="price" style="margin-left:0"><b>￥<fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></b><font color="#005AA0" > 已售出：${cur.sale_num}件</font> </li></c:if>
            </ul>
        </c:forEach>    
        </div>
    </div>
    <div class="hotsell_r" style="display:none">
    	<div class="information">
        	<div class="information_tit"><span>康佳资讯</span><p><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do'/>">更多</a></p></div>
            <div class="information_cont" style="height: 220px;">
            	<ul id="zx1">
            	 <c:forEach items="${articleList}" var="cur" begin="0" end="8">
		       	 	<li><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=${cur.id}' />" title="${cur.title}" target="_blank">${fnx:abbreviate(cur.title, 2 * 15, '...')}</a></li>
		      	  </c:forEach>
                </ul>
                <ul id="zx2" style="display:none;">
                 <c:forEach items="${articleList}" var="cur" begin="9" end="17">
		       	 	<li><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=${cur.id}' />" title="${cur.title}" target="_blank">${fnx:abbreviate(cur.title, 2 * 15, '...')}</a></li>
		      	  </c:forEach>
		      	</ul>  
            </div>
        </div>
        <div class="down"><img src="${ctx}/styles/member/images/pot3.jpg" onclick="showZx();"/></div>
    </div>
</div> 
<div class="logo2">
  <div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=0&label_of_cate=0" title="新品首发"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多新品&nbsp;&nbsp;&nbsp;&nbsp;</font></a></div>
</div> 
<div class="main1" style="height:608px;">
  <div class="m1-l">
    <table border="0" width="1200" height="608"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_0}" var="cur" varStatus="vs" begin="0" end="7">
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
</div>
<div class="logo5">
  <div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=0&label_of_cate=7" title="精品推荐"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多精品&nbsp;&nbsp;&nbsp;&nbsp;</font></a></div>
</div>
<div class="main1" style="height:608px;">
  <div class="m1-l">
    <table border="0" width="1200" height="608"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_7}" var="cur" varStatus="vs" begin="0" end="7">
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
</div>
<div class="logo3">
  <div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=0&label_of_cate=2"  title="热销"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多热销&nbsp;&nbsp;&nbsp;&nbsp;</font></a></div>
</div>
<div class="main1" style="height:608px;">
  <div class="m1-l">
    <table border="0" width="1200" height="608"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_2}" var="cur" varStatus="vs" begin="0" end="7">
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
</div> 
<div class="clear"></div> 
<div class="logo4">  <div class="lef"><a href="${ctx}/member/KonkaBcompPd.do?prod_type=0&label_of_cate=3" title="特惠"><font color="#ffffff" style="font-size:12px;font-weight: normal;font-family: 宋体;">更多特惠&nbsp;&nbsp;&nbsp;&nbsp;</font></a></div></div>
<div class="main1" style="height:308px;">
  <div class="m1-l">
    <table border="0" width="1200" height="308"  cellspacing="0" cellpadding="0">
      <tr>
        <c:forEach items="${bcomp_pd_list_3}" var="cur" varStatus="vs" begin="0" end="3">
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
</div>
<div class="clear"></div>
<div class="tab">
  <table border="1" cellpadding="0" cellspacing="0" bordercolor="#e3e3e3" >
    <tr ><td height="39px" colspan="8" class="bj" ></td></tr>
    <tr>     
    <c:forEach items="${bcomp_pd_list_top_8}" var="cur">
      <td align="center">
      <div class="sz"><a title="${cur.pd_name}" href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />"><img alt="${cur.pd_name}" height="92" width="128" src="${ctx}/${cur.main_pic}" style="max-height:100px;" /></a></div>
      <div style="text-align:center; margin-top:10px;"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />" title="康佳${cur.pd_sn}" style="font-size:13px;font-color:#666666;">康佳&nbsp;${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}</a></div>
      </td> 
    </c:forEach>
    </tr>
  </table>
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
</script>
</body>
</html>
