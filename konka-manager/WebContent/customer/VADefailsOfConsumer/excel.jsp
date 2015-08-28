<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
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
  <div class="rtabcont1" id="divExcel" title="明细表${date}">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
 		<td width="5%" align="center" nowrap="nowrap">序号</td>
        <td align="center" nowrap="nowrap">日期</td>
        <td width="4%" align="center" nowrap="nowrap">分公司</td>
        <td width="6%" align="center" nowrap="nowrap">经办</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">客户R3编码</td>
        <td width="4%" nowrap="nowrap" align="center">上报人</td>
        <td align="center" nowrap="nowrap">门店</td>
        <td width="3%" nowrap="nowrap" align="center">尺寸</td>
        <td width="8%" nowrap="nowrap" align="center">产品型号</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">金额</td>
        <td width="6%" nowrap="nowrap" align="center">消费者姓名</td>
        <td width="6%" nowrap="nowrap" align="center">电话</td>
        <td nowrap="nowrap" align="center">身份证</td>
        <td width="12%" nowrap="nowrap" align="center">地址</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
	      <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.sell_date}" pattern="yyyy/MM/dd" /></td>
          <td align="left" nowrap="nowrap">${cur.dept_name}</td>
          <td align="left" nowrap="nowrap">${cur.l4_dept_name}</td>
          <td align="left" nowrap="nowrap">${cur.customer_name}</td>
          <td align="left" nowrap="nowrap">${cur.r3_code}</td>
          <td align="left" nowrap="nowrap">${cur.add_user_name}</td>
          <td align="left" nowrap="nowrap">${cur.store_name}</td>
          <td align="center" nowrap="nowrap"></td>
          <td align="left" nowrap="nowrap">${cur.md_name }</td>
          <td align="right" nowrap="nowrap">${cur.counts }</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.total_money}" type="currency" /> </span></td>
          <td align="left" nowrap="nowrap">${cur.buyer_name}</td>
          <td align="left" nowrap="nowrap">${cur.buyer_tel}</td>
          <td align="left" nowrap="nowrap">${cur.buyer_id}</td>
          <td align="left" nowrap="nowrap">${cur.buyer_link_addr}</td>
          </tr>
        </c:forEach>
      </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	window.onload=function(){
		toExcel('divExcel', '?method=toExcel');
	};
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
