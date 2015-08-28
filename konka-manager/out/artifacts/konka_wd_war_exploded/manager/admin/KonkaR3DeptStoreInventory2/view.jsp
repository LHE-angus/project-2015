<%@ page language="java" contentType="application/octet-stream;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" nowrap="nowrap">序号</td>
          <td width="8%" nowrap="nowrap">工厂</td>
          <td width="8%" nowrap="nowrap">WhN</td>
          <td width="8%" nowrap="nowrap">类</td>
          <td width="8%" nowrap="nowrap">库位</td>
          <td width="8%" nowrap="nowrap">仓位</td>
          <td width="8%" nowrap="nowrap">仓储类型名</td>
          <td width="8%" nowrap="nowrap">名称</td>
          <td width="8%" nowrap="nowrap">物料(机型)</td>
          <td width="8%" nowrap="nowrap">可用库存</td>
          <td width="8%" nowrap="nowrap">交货日期</td>
          <td width="8%" nowrap="nowrap">物料机型(描述)</td>
          <td width="8%" nowrap="nowrap">移动价格</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs" >
          <tr>
            <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
            <td align="center" nowrap="nowrap" >${cur.WERKS}</td>
            <td align="center" nowrap="nowrap" >${cur.LGNUM}</td>
            <td align="center" nowrap="nowrap" >${cur.LGTYP}</td>
            <td align="center" nowrap="nowrap" >${cur.LGORT}</td>
            <td align="center" nowrap="nowrap" >${cur.LGPLA}</td>
            <td align="center" nowrap="nowrap" >${cur.LGTYP}</td>
            <td align="center" nowrap="nowrap" >${cur.NAME1}</td>
            <td align="center" nowrap="nowrap" >${cur.MATNR}</td>
            <td align="center" nowrap="nowrap" >${cur.VERME}</td>
            <td align="center" nowrap="nowrap" >${cur.EDATU}</td>
            <td align="center" nowrap="nowrap" >${cur.MAKTX}</td>
            <td align="center" nowrap="nowrap" >${cur.VERPR}</td>
          </tr>
        </c:forEach>
      </table>
</body>
</html>
