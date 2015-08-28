<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
</head>
<body>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>
        <td align="center" nowrap="nowrap">销售单号</td>
        <td align="center" nowrap="nowrap">单据类型</td>
        <td align="center" nowrap="nowrap">销售日期</td>
        <td align="center" nowrap="nowrap">销售单位</td>
        <td align="center" nowrap="nowrap">销售单位编码</td>
        <td align="center" nowrap="nowrap">上报人</td>
        <td width="8%" nowrap="nowrap" align="center">商品类型</td>
        <td width="8%" nowrap="nowrap" align="center">机型</td>
        <td width="3%" nowrap="nowrap" align="center">尺寸</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">单价</td>
        <td width="6%" nowrap="nowrap" align="center">销售参考单价</td>
        <td width="6%" nowrap="nowrap" align="center">金额</td>
        <td width="6%" nowrap="nowrap" align="center">出货仓库</td>
        <td width="6%" nowrap="nowrap" align="center">顾客姓名</td>
        <td width="6%" nowrap="nowrap" align="center">顾客电话</td>
		<td width="12%" nowrap="nowrap" align="center">地址</td>
		<td width="12%" nowrap="nowrap" align="center">附件</td>
        <td width="5%" nowrap="nowrap" align="center">数据来源</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.BILL_SN}</td>
          <td align="left" nowrap="nowrap">
          	<c:if test="${cur.BILL_TYPE eq 20 }">零售</c:if>
          	<c:if test="${cur.BILL_TYPE eq 21 }">零售退货</c:if>
          	<c:if test="${cur.BILL_TYPE eq 30 }">零售通</c:if>
          </td>
          <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.OPR_DATE}" pattern="yyyy/MM/dd" /></td>
          <td align="left" nowrap="nowrap">${cur.P_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.P_ID}</td>
          <td align="left" nowrap="nowrap">${cur.ADD_USER_NAME }</td>
          <td align="left" nowrap="nowrap">${cur.TYPE_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.GOODS_NAME}</td>
          <td align="center" nowrap="nowrap">${cur.MD_SIZE }</td>
          <td align="right" nowrap="nowrap">${cur.NUM }</td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">${cur.PRICE }</span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">${cur.PRICE}</span></td>
          <td align="left" nowrap="nowrap">${cur.NUM*cur.PRICE}</td>
          <td align="left" nowrap="nowrap">${cur.STORE_NAME }</td>
          <td align="left" nowrap="nowrap">${cur.PARTNER_NAME}</td>
          <td align="left" nowrap="nowrap">${cur.LINK_MOBILE}</td>
          <td align="left" nowrap="nowrap">${cur._PROVINCE }${cur.CITY }${cur._COUNTRY }${cur._TOWN }${cur.CONSIGNEE_STREET }</td>
          <td align="left" nowrap="nowrap">
          	<c:if test="${not empty cur.FAPIAOS}">
           		<c:set var="fapiao" value="${fn:split(cur.FAPIAOS,',')}" />
          		<c:forEach items="${fapiao}" var="tt" varStatus="vs1">
          			<c:set var="num" value="${fn:length(tt)}" />
          				<a href=/${tt} target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
          		</c:forEach>
          	</c:if>
          </td>
          <td align="center" nowrap="nowrap">
          	<!-- ${fn:split('手机端,WEB端,IOS手机端,客户端', ',')[cur.DATA_SOURCE-1]} -->
          	<c:if test="${cur.BILL_TYPE eq 20 or cur.BILL_TYPE eq 21 }">客户端</c:if>
          	<c:if test="${cur.BILL_TYPE eq 30 }">零售通</c:if>
          </td>
        </tr>
      </c:forEach>
    </table>
</body>
</html>