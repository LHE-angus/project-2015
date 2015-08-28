<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
</head>
<body >
   <div>
    <table width="100%" border="1" cellpadding="0" cellspacing="1">
      <tr class="tabtt1">
        <td align="center" nowrap="nowrap">序号</td>        
        <td align="center" nowrap="nowrap">订单号</td>
        <td align="center" nowrap="nowrap">商品类型</td>
        <td align="center" nowrap="nowrap">商品/型号</td>
        <td align="center" nowrap="nowrap">业务类型</td>
        <td align="center" nowrap="nowrap">数量</td>
        <td align="center" nowrap="nowrap">单位</td>
        <td align="center" nowrap="nowrap">单价</td>
        <td align="center" nowrap="nowrap">订单金额</td>
        <td align="center" nowrap="nowrap">实际金额</td>        
        <td align="center" nowrap="nowrap">仓库名</td>
        <td align="center" nowrap="nowrap">供应商</td>
        <td align="center" nowrap="nowrap">创建时间</td>
        <td align="center" nowrap="nowrap">单据状态</td>
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td align="center" nowrap="nowrap">${cur.ORDER_SN}</td>
          <td align="center" nowrap="nowrap">${cur.GOODS_TYPE}</td>
          <td align="center" nowrap="nowrap">${cur.GOODS_NAME}</td>
          <td align="center" nowrap="nowrap">
          	<c:if test="${cur.BES_TYPE eq 1}">
          		退货
          	</c:if>
          	<c:if test="${cur.BES_TYPE eq 0}">
          		进货
          	</c:if>
          </td>
          <td align="center" nowrap="nowrap">${cur.GOODS_NUM}</td>
          <td align="center" nowrap="nowrap">${cur.GOODS_UNIT}</td>
          <td align="center" nowrap="nowrap">${cur.GOODS_PRICE}</td>
          <td align="center" nowrap="nowrap">${cur.ORDER_MONEY}</td>    
          <td align="center" nowrap="nowrap">${cur.TRUE_MONEY}</td>
          <td align="center" nowrap="nowrap">${cur.STORE_NAME}</td>
          <td align="center" nowrap="nowrap">${cur.PARTNER_NAME}</td>
          <td align="center" nowrap="nowrap">${cur.ORDER_DATE}</td>
          <td align="center" nowrap="nowrap">
          	<c:if test="${cur.AUDIT_STATE eq 1}">
          		未提交
          	</c:if>
          	<c:if test="${cur.AUDIT_STATE eq 2}">
          		审核中
          	</c:if>
          	<c:if test="${cur.AUDIT_STATE eq 3}">
          		已确认
          	</c:if>
          	<c:if test="${cur.AUDIT_STATE eq 4}">
          		已完结
          	</c:if>
          </td>
        </tr>
      </c:forEach>
    </table>
</div>
<script type="text/javascript"><!--//<![CDATA[
//]]>--></script>
</body>
</html>
