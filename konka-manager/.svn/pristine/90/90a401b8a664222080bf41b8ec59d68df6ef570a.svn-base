<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳网上直销商城</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/zxmall.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/zxmall/__inc/top.jsp" flush="true" />
<jsp:include page="/zxmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/zxmall/__inc/nav.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt; 佣金查询</div>  
    <div class="zxmalltab3">
      <p style="margin-top:15px;font-size:14px;"> 佣金查询</p>
      <html-el:form action="/center/EcGoodsRebate">
        <html-el:hidden property="method" styleId="method" value="list" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  class="zxmall_form_table0">
          <td width="40%">商品型号： <html-el:text property="pd_sn_or_pd_name_like" maxlength="20" styleClass="input_txt" style="width:160px;"></html-el:text> </td>
            <td width="80%"><input class="inputbtn" type="submit" name="Submit" value="查询" />
            </td>
          </tr>
        </table>
      </html-el:form>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="zxmall_form_table2">
        <tr class="tr1">
         <td width="5%" nowrap="nowrap" align="center">序号</td> 
          <td width="20%" nowrap="nowrap" align="center">商品名称</td>
          <td width="10%" nowrap="nowrap" align="center">销售金额(元)</td> 
          <td width="7%" nowrap="nowrap" align="center">佣金(元)</td> 
          <td width="15%" nowrap="nowrap" align="center">佣金比例</td> 
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td align="center" style="line-height:22px;">${vs.count }</td> 
              <td align="left" nowrap="nowrap"><a href="<c:url value='/zxmall/PdShow.do?goods_id=${cur.id}' />" title="${fn:escapeXml(cur.pd_name)}">${fn:escapeXml(cur.pd_name)}</a></td>
              <td align="center" nowrap="nowrap"><fmt:formatNumber value="${cur.map.price}" pattern="0" /> </td> 
              <c:if test="${empty cur.map.ecGoodsRebate}">
              <td align="center" nowrap="nowrap"> - </td> 
              <td align="center" nowrap="nowrap"> - </td> 
              </c:if>
              <c:if test="${not empty cur.map.ecGoodsRebate}">
              <td align="center" nowrap="nowrap">
              <c:if test="${cur.map.ecGoodsRebate.rebate_way eq 2  }"><fmt:formatNumber value="${cur.map.price/100.0*cur.map.ecGoodsRebate.rebate_value}" pattern="0.0" /></c:if>
              <c:if test="${cur.map.ecGoodsRebate.rebate_way eq 1  }"><fmt:formatNumber value="${cur.map.ecGoodsRebate.rebate_value}" pattern="0.0" /></c:if>
              </td> 
              <td align="center" nowrap="nowrap">
              <c:if test="${cur.map.ecGoodsRebate.rebate_way eq 2  }"><fmt:formatNumber value="${cur.map.ecGoodsRebate.rebate_value}" pattern="0.00" /></c:if>
              <c:if test="${cur.map.ecGoodsRebate.rebate_way eq 1 and not empty cur.map.price }"><fmt:formatNumber value="${cur.map.ecGoodsRebate.rebate_value*100.0/cur.map.price}" pattern="0.00" /></c:if>%</td> 
              </c:if>
            </tr>
          </c:forEach>
          <c:if test="${empty entityList }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="7"> 暂无记录 </td>
            </tr>
          </c:if>
        </tbody>
      </table>
      <c:if test="${af.map.pager.pageCount>1}">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40"><div style="text-align: right; padding-right: 5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
			                     var pager = new Pager(document.bottomPageForm, parseInt('${af.map.pager.recordCount}'), parseInt('${af.map.pager.pageSize}'), parseInt('${af.map.pager.currentPage}'));
			                     pager.addHiddenInputs("method", "list");
			                     pager.addHiddenInputs("pd_sn_or_pd_name_like", "<c:out value='${af.map.pd_sn_or_pd_name_like}'/>"); 	 
			                     document.write(pager.toString());
			                 </script>
                </div></td>
            </tr>
          </table>
        </form>
      </c:if>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/zxmall/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
</html>
