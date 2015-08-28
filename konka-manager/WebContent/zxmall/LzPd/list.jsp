<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳网上直销商城</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/zxmall/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/zxmall/css/epp.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/slider.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/commons/styles/pager3.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/purchase.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<style type="text/css">

#topFilterSelected a {
display: inline;
float: left;
height: 18px;
line-height: 22px\9;
_line-height: 18px;
margin: 0px 4px 5px 0px;;
padding: 0 0 0 6px;
color: #fff;
white-space: nowrap;
background: url(${ctx}/images/repeatX.png) repeat-x 0 -120px;
border-left: 1px solid #fe6e0d;
border-bottom: 1px solid #ff6501;
border-radius: 3px;
}

#topFilterSelected a em {
cursor: pointer;
float: left;
font-style: normal;
}

#topFilterSelected a i {
cursor: pointer;
display: inline;
float: left;
width: 19px;
height: 19px;
background: url(${ctx}/images/topF.png) no-repeat;
background: url(${ctx}/images/topFIe.png) no-repeat\9;
}

.i {
color: #f60;
font-style: normal;
}

#resetFilter {
float: left;
margin-top: 1px;
margin-top: 3px\9;
_margin-top: 1px;
white-space: nowrap;
}

.buttonPBSearch  {
width:46px;
height:20px;
repeat-x;
font:normal 12px/20px "宋体";
text-align:center;
color:#fff;
border:1px #ccc solid;
border-left:0;
background: #F00B0B;
}
</style>
</head>
<body>
<!-- head start -->
<!-- top start -->
<div class="topbox">
<jsp:include page="/zxmall/__inc/top.jsp" flush="true" />
<jsp:include page="/zxmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/zxmall/__inc/nav.jsp" flush="true" />
<!-- top end -->  
<!-- first end -->
<!-- second start -->
<div class="maincont margintop10">
  <!--left-->
  <div class="listleft">
    <div class="liscont1">
      <div class="listit1">推荐热卖</div>
      <div class="listbox1">
        <ul class="listul4">
          <c:forEach items="${bcomp_pd_list_top_5}" var="cur" varStatus="vs">
          	<c:set var="li_class" value="" />
            <c:if test="${vs.last}"><c:set var="li_class" value="noline4" /></c:if>
            <li style="padding:0;" class="${li_class}">
              <div class="listpic1 box_117_80" style="height:110px;">
              	<table border="0" cellspacing="0" cellpadding="0" width="120"><tr><td height="110" style="overflow:hidden;" width="120" valign="middle" align="center"><a href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />"><img src="${ctx}/${cur.main_pic}" align="absmiddle"  width="100"/></a></td></tr></table>
              </div>
              <div class="listrxt1">
                <h3><a href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />"><font style="font-size:13px;">康佳${fnx:abbreviate(cur.pd_sn, 2 * 7, '.')}<br/>${fn:escapeXml(cur.pd_size)}英寸</font></a></h3>
                <h4>价格：￥<font style="font-size:18px;"><c:if test="${zxmall.user_type eq 1 }"><fmt:formatNumber value="${cur.ecGoodsPrice.price}" pattern="0" /></c:if>
                <c:if test="${zxmall.user_type eq 2 }"><fmt:formatNumber value="${cur.ecGoodsPrice.original_price}" pattern="0" /></c:if></font></h4>
              </div>
              <div class="clear"></div>
            </li>
          </c:forEach>
        </ul>
      </div>
      <div class="clear"></div>
    </div>
  </div>
  <!--right-->
  <div class="listright">
    <div class="liscont2">
      <div class="listbox2">
        <div class="listbox3">
          <form id="listForm" name="listForm" method="post" action="LzPd.do" >
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
            <h3 class="lsitit2">荔枝专区 </h3>  
          </form>
        </div>
        <div class="clear"></div>
      </div>
    </div>
    <div class="clear"></div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabs_list2 margintop10">
      <tbody>
        <tr>
          <c:forEach items="${entityList}" var="cur" varStatus="vs">
          <td><div class="listul2">
              <div class="rpic2 box"><a title="" href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />"><img alt="" src="${ctx}/${fn:substringBefore(cur.main_pic, '.')}.jpg" align="absmiddle" style="max-height:216px;" width="288"/></a></div>
              <div class="ltt2" ><a href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />" title="${fn:escapeXml(cur.pd_name)}">
               <font style="font-szie:13px;">	  康佳&nbsp;${fn:escapeXml(cur.pd_sn)}  &nbsp;${fn:escapeXml(cur.pd_size)}英寸<br/></font>
               <font class="redfont">${fnx:abbreviate(cur.pd_name, 2 * 18, '')}</font></a></div>
              <div class="ltt3"><c:if test="${zxmall.user_type eq 1 }">
                <p style="color:#666666;"><font color="#d40207" style="font-size:12px;">￥<a href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />" style="color:red;font-size:18px;font-family:arial,宋体b8b\4f53;"><fmt:formatNumber value="${cur.map.price}" pattern="0" />
                </a></font>&nbsp; <del >市场价：￥<fmt:formatNumber value="${cur.map.original_price}" pattern="0" /></del></c:if>
                <c:if test="${zxmall.user_type eq 2 }">
                <p style="color:#666666;"><font color="#d40207" style="font-size:12px;">￥<a href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />" style="color:red;font-size:18px;font-family:arial,宋体b8b\4f53;"><fmt:formatNumber value="${cur.map.original_price}" pattern="0" />
                </a></font>&nbsp; </c:if>
                &nbsp;&nbsp;<font color="#005AA0" >销量：${cur.sale_num}件</font></p>
              </div>
            </div></td>
          <c:if test="${vs.count mod 3 eq 0 and not vs.last}">
        </tr>
        <tr></c:if>
          </c:forEach>
        </tr>
      </tbody>
    </table>
    <!--page-->
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="LzPd.do">
      <div class="pagebox">
        <script type="text/javascript" src="${ctx}/commons/scripts/pager3.js">;</script>
        <script type="text/javascript">
			var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			pager.addHiddenInputs("method", "list");
			pager.addHiddenInputs("pd_type", "${fn:escapeXml(af.map.pd_type)}");
			pager.addHiddenInputs("pd_size_type", "${fn:escapeXml(af.map.pd_size_type)}");
			pager.addHiddenInputs("label_of_cate", "${fn:escapeXml(af.map.label_of_cate)}");
			pager.addHiddenInputs("pd_price", "${fn:escapeXml(af.map.pd_price)}");
			pager.addHiddenInputs("pd_sn_or_pd_name_like", "${fn:escapeXml(af.map.pd_sn_or_pd_name_like)}");
			pager.addHiddenInputs("order_by_sale_num_desc", "${fn:escapeXml(af.map.order_by_sale_num_desc)}");
			pager.addHiddenInputs("order_by_price_desc", "${fn:escapeXml(af.map.order_by_price_desc)}");
			pager.addHiddenInputs("order_by_price_asc", "${fn:escapeXml(af.map.order_by_price_asc)}");
			pager.addHiddenInputs("prod_type", "${fn:escapeXml(af.map.prod_type)}");
			pager.addHiddenInputs("prod_name", "${fn:escapeXml(af.map.prod_name)}");
			pager.addHiddenInputs("brand_name", "${fn:escapeXml(af.map.brand_name)}"); 
			document.write(pager.toString());
		</script>
      </div>
    </form>
  </div>
  <div class="clear"></div>
</div>
<!-- second end -->
<!-- six footer -->
<jsp:include page="../__inc/footer.jsp" flush="true" />
<!-- six footer -->
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){ 
	if($("#topFilter").children().length <= 2){
		$("#topFilter").attr("style","display:none");
	}else {
		$("#topFilter").attr("style","display:''");
	} 
});

//]]></script>
</body>
</html>
