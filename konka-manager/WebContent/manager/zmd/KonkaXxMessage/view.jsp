<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<!--<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />-->
<!--<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />-->
<!--<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />-->
<!--<link href="${ctx}/styles/jGrowl.css" rel="stylesheet" type="text/css" />-->
<style type="text/css">
body {font:12px/20px "宋体","\5b8b\4f53",sans-serif;color:#1e3257;overflow-x:hidden;}
a {text-decoration: none;color:#1e3257;}
a:link {text-decoration: none;color:#1e3257;}
a:hover {text-decoration: none;color: #f00;}
</style>
</head>
<body bgcolor="#FCF6EA">
<html-el:form action="/zmd/KonkaXxMessage">
	<html-el:hidden property="id" />
	<table width="90%" align="center" border="0" cellpadding="0" cellspacing="1" class="datagrid">
		<c:if test="${empty af.map.id}">
			<div align="center" style="margin-top:130px;">没有消息可预览。</div>
		</c:if>
		<tr>
			<td align="center" style="font-size:14px;font-weight:bold;">${af.map.msg_title}</td>
		</tr>
		<tr>
			<td style="padding-top:10px;">
				<c:if test="${af.map.msg_type eq 0}">${af.map.msg_content}</c:if>
				<c:if test="${af.map.msg_type eq 1}">${af.map.map.notice}</c:if>
			</td>
		</tr>
	</table>
</html-el:form>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".datagrid tr").mouseover(function(){  
		$(this).addClass("over");
	}).mouseout(function(){
		$(this).removeClass("over");
	})
	$(".datagrid tr:even").addClass("alt");
});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>