<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>人员列表</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
	    <tr class="tabtt1">
	    	<td width="10%" nowrap="nowrap" align="center">序号</td>
	    	<td nowrap="nowrap" align="center">姓名</td>
	    </tr>
	    <c:forEach var="cur" items="${userList}" varStatus="vs">
	    	<tr>
	    		<td align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	    		<td align="center">${cur.user_name}</td>
	    	</tr>
	    </c:forEach>
	  </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[    
$(document).ready(function(){
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>


