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
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div style="padding-left: 5px;padding-top: 3px;">
  	<input class="but5" type="button" value="返回" id ="ret_btn" onclick="history.back()" />
  </div>
  <div align="center" class="rtabcont1">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
	    <tr class="tabtt1">
	    	<td width="5%" nowrap="nowrap" align="center">序号</td>
	    	<td width="10%" nowrap="nowrap" align="center">分公司</td>
	    	<td width="20%" nowrap="nowrap" align="center">标题</td>
	    	<td nowrap="nowrap" align="center">详细</td>
	    	<td width="16%" nowrap="nowrap" align="center">操作时间</td>
	    	<td width="8%" nowrap="nowrap" align="center">操作人</td>
	    </tr>
	    <c:forEach var="cur" items="${entityList}" varStatus="vs">
	    	<tr>
	    		<td align="center">${vs.count}</td>
	    		<td align="left">${cur.dept_name}</td>
	    		<td align="left">${cur.opr_title}</td>
	    		<td align="left" title="${cur.opr_note}">${fn:substring(cur.opr_note,-1,15)}</td>
	    		<td align="left"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd hh:mm:ss"/> </td>
	    		<td align="left">${cur.map.user_name}</td>
	    	</tr>
	    </c:forEach>
	  </table>
  </div>
</div>
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
  