<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdReturnSalesOrder">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">订单流水号：</strong>
            <html-el:text property="sell_bill_id_like" styleId="sell_bill_id_like" size="16" maxlength="20"></html-el:text>
            &nbsp;&nbsp;
            <c:if test="${!empty dept_id}"> <strong class="fb">分公司：</strong>
              <html-el:select property="dept_id" styleId="dept_id" onchange="this.form.submit();">
                <html-el:option value="">==请选择==</html-el:option>
                <c:forEach var="cur" items="${konkaDeptList}">
                  <html-el:option value="${cur.dept_id}">${fn:escapeXml(cur.dept_name)}</html-el:option>
                </c:forEach>
              </html-el:select>
              &nbsp;&nbsp; </c:if>
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <c:if test="${!empty is_fgscw}">
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp"%>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><input class="but2" type="submit" name="Submit2" value="退货" onclick="location.href='KonkaXxZmdReturnSalesOrder.do?method=returnBill&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" /></td>
        </tr>
      </table>
    </div>
  </c:if>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="10%">订单流水号</td>
        <td nowrap="nowrap" align="center" width="8%">分公司</td>
        <td nowrap="nowrap" align="center" width="10%">专卖店编号</td>
        <td nowrap="nowrap" align="center">明细（型号，单价，数量）</td>
        <td nowrap="nowrap" align="center" width="10%">合计金额</td>
        <td nowrap="nowrap" align="center" width="8%">退货状态</td>
        <td nowrap="nowrap" align="center" width="12%">退货时间</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center"><c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" /></td>
            <td align="left">${fn:escapeXml(cur.map.dept_name)}</td>
            <td align="left">${fn:escapeXml(cur.zmd_sn)}</td>
            <td><c:forEach var="cur1" items="${cur.map.konkaXxSellBillDetailsList}" varStatus="vs"> [${fn:escapeXml(cur1.md_name)}，
                <fmt:formatNumber value="${cur1.price}" pattern="0.00" />
                元，${cur1.counts}台]
                <c:if test="${not vs.last}">；</c:if>
              </c:forEach></td>
            <td align="right" nowrap="nowrap"><span class="kz-price-12">
              <fmt:formatNumber type="currency" value="${cur.total_money}" />
              </span></td>
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${cur.return_state eq 0}">未退货</c:when>
                <c:when test="${cur.return_state eq 1}">已退货</c:when>
              </c:choose></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdReturnSalesOrder.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("sell_bill_id_like", "${af.map.sell_bill_id_like}");
				pager.addHiddenInputs("return_state", "${af.map.return_state}");
				pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
	
});
//]]>
</script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
