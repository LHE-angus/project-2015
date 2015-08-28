<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/commons/styles/themes/blue/styles/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div align="center">
<div align="left" style="width: 99%">
  <div align="left" class="nav">${naviString}</div>
  <br />
  <input type="button" value=" 返回 " onclick="history.back();" /> <br/>
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
  	<tr>
  		<th width="30">序号</th>
  		<th width="15%">产品类别</th>
  		<th>属性</th>
  		<th width="20%">产品类别（全称）</th>
  	</tr>
    <c:forEach var="cur" items="${entityList}" varStatus="vs">
	    <tr>
	        <td align="center" nowrap="nowrap">${vs.count}</td>
	        <td align="left" nowrap="nowrap" title="${cur.cls_name}">${cur.tree_name}</td>
	        <td align="left"><c:if test="${empty cur.map.prop_names}"><span style="color:#F00;">未检索到属性</span></c:if>${cur.map.prop_names}</td>
	        <td align="left" nowrap="nowrap">${fn:replace(cur.full_name, ',', '&gt;')}</td>
	    </tr>
    </c:forEach>
  </table>
  <br />
  <input type="button" value=" 返回 " onclick="history.back();" />
</div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
