<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进货管理 &gt; 进货登记</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcStockBill">
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="7">进货单详细信息</th>
        </tr>
        <c:if test="${roleId eq 30}">
          <tr>
            <td class="title_item">上级部门：</td>
            <td colspan="6">${fn:escapeXml(af.map.map.dept_name)}</td>
          </tr>
        </c:if>
        <tr>
          <td class="title_item">进货编号：</td>
          <td colspan="3">${fn:escapeXml(af.map.sn)}</td>
          <td class="title_item">进货日期：</td>
          <td colspan="2"><fmt:formatDate value="${af.map.jh_date }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        </tr>
        <tr>
          <td class="title_item">进货总数：</td>
          <td>${fn:escapeXml(af.map.jh_sum_count)}</td>
          <td class="title_item">应付金额：</td>
          <td><fmt:formatNumber value="${af.map.money_must}" pattern="0.00" /></td>
          <td class="title_item">实付金额：</td>
          <td colspan="2"><fmt:formatNumber value="${af.map.money_result}" pattern="0.00" /></td>
        </tr>
        <tr>
          <th colspan="7">进货单明细</th>
        </tr>
        <tr class="title_top">
          <td width="15%" nowrap="nowrap">入库仓库</td>
          <td width="15%" nowrap="nowrap">产品大类</td>
          <td width="15%" nowrap="nowrap">产品品牌</td>
          <td width="15%" nowrap="nowrap">产品型号</td>
          <td width="12%" nowrap="nowrap">进货数量</td>
          <td width="13%" nowrap="nowrap">单价</td>
          <td nowrap="nowrap">备注</td>
        </tr>
        <c:forEach items="${konkaJxcStockBillDetailsList}" var="cur">
          <tr>
            <td align="center">${fn:escapeXml(cur.map.store_name)}</td>
            <td align="center">${fn:escapeXml(cur.pd_type_name)}</td>
            <td align="center">${fn:escapeXml(cur.brand_name)}</td>
            <td align="center">${fn:escapeXml(cur.pd_name)}</td>
            <td align="center">${fn:escapeXml(cur.count)}</td>
            <td align="center"><fmt:formatNumber value="${cur.price}" pattern="0.00" /></td>
            <td align="center">${fn:escapeXml(cur.remark)}</td>
          </tr>
        </c:forEach>
        <tr>
          <td colspan="7" align="center"><html-el:button property="back" value=" 返回 " onclick="history.back();" styleClass="bgButtonBack"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript"><!--//<![CDATA[

//]]>--></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
