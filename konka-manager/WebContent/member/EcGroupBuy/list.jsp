<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/epp.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/togo.css" />
<link rel=stylesheet type=text/css  href="${ctx}/commons/styles/pager3.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/citybox.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" /> 
<div class="togo">
  <div class="togo_left">
  <table border="0" width="100%"  cellspacing="0" cellpadding="0">
     <tr><c:forEach items="${entityList}" var="cur" varStatus="vs">
       <td width="49%" >
	         <div style="padding:10px;width:420px;height:450px;border: 1px #F3F3F3 solid;">
	         	<a href="?method=view&group_id=${cur.id }" title="${fn:escapeXml(cur.title)}"><img src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}.jpg" height="336" width="425" /></a>
	            <div style="margin-right:5px;margin-left: 12px; margin-top: 10px;line-height:21px;font-size:13px;font-color:#666666; font-family:arial,宋体b8b\4f53;">
		         	<a href="?method=view&group_id=${cur.id }" title="${fn:escapeXml(cur.title)}">${fn:escapeXml(cur.title)}<div><font class="redfont">${cur.brief}</font></div></a>
	            </div>
	            <div class="goods-info" style="height: 50px;padding: 10px 15px 0;color: #b0b0b0;overflow: hidden;font-family: arial;">
                    		<span class="price1" style="display: inline-block;font-size: 26px;line-height: 30px;color: #f67208;padding-right: 15px;float: left;vertical-align: top;"><em style="color: #f67208;">¥</em>${cur.map.price}</span>       
	                        <span class="money" style="display: inline-block;height: 18px;font-size: 14px;line-height: 18px;padding-top: 10px;vertical-align: top;float: left;">¥<del>${cur.map.original_price}</del></span>
	                        <span class="number" style="font-family: Microsoft Yahei;display: inline-block;height: 18px;font-size: 14px;line-height: 18px;padding-top: 8px;vertical-align: top;float: right;">已售<i style="font-style: normal;color: #ec361e;">${cur.map.sale_num}</i></span>      
	            </div>  
	          </div>  
        </td><c:if test="${vs.count mod 2 eq 0 and not vs.last}">  
     </tr>
     <tr></c:if></c:forEach>
     </tr>
  </table>
  </div>
  <div class="togo_right"> 
    <div class="togo_box"><font class="orange14px">热卖推荐</font></div>
    <div class="togo_box_down">
    	<ul class="listul4">
          <c:forEach items="${bcomp_pd_list_top_5}" var="cur" varStatus="vs">
          	<c:set var="li_class" value="" />
            <c:if test="${vs.last}"><c:set var="li_class" value="noline4" /></c:if>
            <li style="padding:0;background:none;" class="${li_class}">
              <div class="listpic1 box_117_80" style="height:110px;">
              	<table border="0" cellspacing="0" cellpadding="0" width="120"><tr><td height="110" style="overflow:hidden;" width="120" valign="middle" align="center"><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${cur.main_pic}" align="absmiddle"  width="100"/></a></td></tr></table>
              </div>
              <div class="listrxt1">
                <h3><a href="<c:url value='/member/PdShow.do?goods_id=${cur.id}' />"><font style="font-size:13px;">康佳${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}<br/>${fn:escapeXml(cur.pd_size)}英寸</font></a></h3>
                <h4>价格：￥<font style="font-size:18px;"><c:if test="${ecUser.user_type eq 1 }"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></c:if>
                <c:if test="${ecUser.user_type eq 2 }"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></c:if></font></h4>
              </div>
              <div class="clear"></div>
            </li>
          </c:forEach>
        </ul>
    </div>
  </div>
</div>
<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer -->
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var form = document.listForm;
	
}); 
//]]></script>
</body>
</html>
