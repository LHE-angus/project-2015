<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1" style="overflow-x: auto;">
    <table width="100%" border="1" cellspacing="0" cellpadding="0"
	class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap">序号</td>       
        <td width="6%" nowrap="nowrap" align="center">消费者姓名</td>
        <td width="6%" nowrap="nowrap" align="center">电话</td>
        <td width="10%" nowrap="nowrap" align="center">身份证</td>
        <td width="15%" nowrap="nowrap" align="center">地址</td>
        <td width="3%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">金额</td>
        <td width="6%" nowrap="nowrap" align="center">状态</td>
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr class="list-tr">
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          
          <td align="center" nowrap="nowrap">${cur.consumer_name}</td>
          <td align="left" nowrap="nowrap">${cur.consumer_phone}</td>
          <td align="left" nowrap="nowrap">${cur.master_code}</td>
          <td align="left" nowrap="nowrap">${cur.consumer_addr}</td>
          <td align="right" nowrap="nowrap"><a title="点击可查详情" style="cursor:pointer;" class="fblue" onclick="showDetails('${cur.consumer_phone}');">${cur.num}</a></td>
          <td align="right" nowrap="nowrap">
          	<fmt:formatNumber value="${cur.price}" pattern="#,##0.00"/>
          </td>
          <td align="center">
          	<c:if test="${cur.is_del eq 0 }">
            	有效
            </c:if>
            <c:if test="${cur.is_del eq 1 }">
            	无效
            </c:if>
          </td>
        </tr>
        <c:if test="${vs.last eq true}">
          <c:set var="i" value="${vs.count}" />
        </c:if>
      </c:forEach>
    </table>
  </div>
</div>
</body>
</html>
