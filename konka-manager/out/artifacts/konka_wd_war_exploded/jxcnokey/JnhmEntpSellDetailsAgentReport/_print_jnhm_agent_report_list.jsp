<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>打印</title>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="printTabTop">
  <tr>
    <td colspan="7" align="center" style="font-weight:bold;font-size: 18px;">  ${af.map.yyyy}年${af.map.mm}月${customer_name}销售机构出货信息汇总表</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
         <tr>
            <th nowrap="nowrap" align="center">终端销售机构名称</th>
            <th nowrap="nowrap" align="center">终端销售机构组织机构代码</th>
            <th nowrap="nowrap" align="center">出货开发票日期</th>
            <th nowrap="nowrap" align="center">购货单位注册名称</th>
            <th nowrap="nowrap" align="center">购货单位组织机构代码</th>
            <th nowrap="nowrap" align="center">产品型号</th>
            <th nowrap="nowrap" align="center">产品唯一编码</th>
            <th nowrap="nowrap" align="center">增值税（销售）发票号</th>
          </tr>
        <c:if test="${not empty entityList}">
	        <c:forEach var="cur" items="${entityList}" varStatus="vs">
	          <tr align="center">
	            <td align="left">${fn:escapeXml(cur.map.shop_name)}</td>
	            <td align="left">${fn:escapeXml(cur.map.org_sn)}</td>
	            <td align="left"><fmt:formatDate value="${cur.jxcSellBillDetailsEntity.ch_invoice_date}" pattern="yyyy-MM-dd"/></td>
	            <td align="left">${fn:escapeXml(cur.map.customer_name)}</td>
	            <td align="left" nowrap="nowrap" style="mso-number-format:'\@';">${cur.map.customer_cus_idcard}</td>
	            <td align="left">${fn:escapeXml(cur.jxcSellBillDetailsEntity.pd_name)}</td>
	            <td align="left" style="mso-number-format:'\@';">${cur.jxcSellBillDetailsEntity.pd_unique_code}</td>
	            <td align="left" style="mso-number-format:'\@';">${cur.jxcSellBillDetailsEntity.invoice_bh}</td>
	          </tr>
	        </c:forEach>
        </c:if>
        <c:if test="${empty entityList}">
        <tr><td height="30" align="center" nowrap="nowrap" colspan="8"><font color="red">无数据</font></td></tr>
        </c:if>
      </table>
<c:if test="${not empty entityList}">
<div align="center">
  <input name="button" type="button" class="bgButtonPrint" value="打印" onclick="this.style.display='none';window.print();"/>&nbsp;&nbsp;
  <input name="button" type="button" class="bgButton" value="关闭" style="cursor: pointer; " onclick="window.close();" />
</div>
</c:if>
<c:if test="${empty entityList}">
  <div align="center">
  	<input name="button" type="button" class="bgButton" value="关闭" onclick="window.close();" />
  </div>
</c:if>
<jsp:include page="/__analytics.jsp" />
</body>
</html>