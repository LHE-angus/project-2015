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
<table width="100%" border="1" cellspacing="0" cellpadding="0">
<tr>
 		  <td nowrap="nowrap" width="5%" align="center">序号</td>
          <td align="center">物料机型</td>
          <td width="10%" nowrap="nowrap" align="center" >工厂</td>
          <td width="10%" nowrap="nowrap" align="center" >工厂名称</td>
          <td width="10%" nowrap="nowrap" align="center" >仓位</td>
          <td width="10%" nowrap="nowrap" align="center" >仓位名称</td>
          <td width="12%" nowrap="nowrap" align="center" >非限制使用库存</td>
		  <td width="12%" nowrap="nowrap" align="center" >交货单锁定库存</td>
          <td width="10%" nowrap="nowrap" align="center" >剩余量</td>
      </tr>
<c:forEach var="cur" items="${allList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
            <td align="left" nowrap="nowrap" >${cur.matnr}</td>
            <td align="left" nowrap="nowrap" >${cur.werks}</td>
            <td align="left" nowrap="nowrap" >${cur.name1}</td>
            <td align="left" nowrap="nowrap" >${cur.lgort}</td>
            <td align="left" nowrap="nowrap" >${cur.lgobe}</td>
            <td align="right" nowrap="nowrap" >${cur.labst}</td>
			<td align="right" nowrap="nowrap" >${cur.speme}</td>
            <td align="right" nowrap="nowrap" >${cur.lamount}</td>
          </tr>
        </c:forEach>
    </table>
</body>
</html>
