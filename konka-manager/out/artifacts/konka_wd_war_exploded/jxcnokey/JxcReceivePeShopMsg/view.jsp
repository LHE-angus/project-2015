<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>信息接收 &gt; ${navString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div class="oarcont">
	<div class="oartop">
	  <table width="500" border="0" cellpadding="0" cellspacing="0">
	    <tr>
	      <td width="20"><img src="${ctx}/images/manager/arrow3.gif" alt="" style="vertical-align:middle;" /></td>
	      <td>当前位置：&nbsp;&gt;&nbsp;信息接收&nbsp;&gt;&nbsp;站内信息</td>
	    </tr>
	  </table>
	</div>
	<div class="rtabcont1">
	  <%@ include file="/jxcnokey/JxcReceivePeShopMsg/shop_msg_top.jsp" %>
	</div>
	<div class="rtabcont1">
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	    <tr>
	      <th colspan="2" align="right">站内信息查看</th>
	    </tr>
	    <tr >
	      <td height="28" width="15%" nowrap="nowrap" class="title_item">发件人：</td>
	      <td><c:out value="${entity.send_user_name}" /></td>
	    </tr>
	    <c:if test="${tag_id eq 'b'}">
	    	<tr >
		      <td height="28" width="15%" nowrap="nowrap" class="title_item">收件件人：</td>
		      <td><c:out value="${entity.map.receive_name}" /></td>
		    </tr>
	    </c:if>
	    <tr >
	      <td height="28" width="15%" nowrap="nowrap" class="title_item">标题：</td>
	      <td><c:out value="${entity.title}" /></td>
	    </tr>
	    <tr >
	      <td height="28" width="15%" nowrap="nowrap" class="title_item">内容：</td>
	      <td><c:out value="${entity.content}" /></td>
	    </tr>
	    <tr >
	      <td height="28" width="15%" nowrap="nowrap" class="title_item">发送时间：</td>
	      <td><fmt:formatDate value="${entity.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	    </tr>
	    <tr>
	      <td colspan="2" align="center"><html-el:button property="back" styleClass="bgButtonBack" value="返回" onclick="history.back();" /></td>
	    </tr>
	  </table>
	</div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
