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
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
<div class="oartop">
<table width="400" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="3%"><img
			src="${ctx}/styles/admin-index/images/k_tup.jpg"
			style="vertical-align: middle;" /></td>
		<td>当前位置：${naviString}</td>
	</tr>
</table>
</div>
<div class="rtabcont2"><html-el:form
	action="/admin/KonkaPhotoUploadType.do" enctype="multipart/form-data">
	<html-el:hidden property="id" styleId="id" />
	<html-el:hidden property="method" styleId="method" value="save" />
	<html-el:hidden property="queryString" styleId="queryString" />
	<table class="rtable3" width="100%" border="0" cellspacing="1"
		cellpadding="0">
		<tr>
			<th width="15%" height="45"
				style="font-size: 15px; font-weight: bold; font-family: Microsoft YAHEI, '黑体', '宋体';"><span>详细信息</span></th>
			<th width="10%">&nbsp;</th>
			<th width="10%">&nbsp;</th>
			<th width="20%">&nbsp;</th>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">分公司：</td>
			<td><c:out value="${af.map.map.dept_name}" /></td>
			
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">任务名称：</td>
			<td><c:out value="${af.map.type_tilte}" /></td>
			<td nowrap="nowrap" class="title_item" align="right">任务描述：</td>
			<td><c:out value="${af.map.type_memo}" /></td>
		</tr>
		<tr>
		    <td nowrap="nowrap" class="title_item" align="right">添加人：</td>
			<td><c:out value="${af.map.add_user_name}" /></td>
			<td nowrap="nowrap" class="title_item" align="right">添加日期：</td>
			<td><fmt:formatDate value="${af.map.add_date}"
				pattern="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">任务状态：</td>
			<td>
			<c:if test="${af.map.state eq 0}">正常</c:if>
			<c:if test="${af.map.state eq 1}">停用</c:if>
			</td>
		</tr>
	</table>
	<table>
	<tr>
			<td style="width:500px;"></td>
			<td align="center"><html-el:button property="" value="返 回" styleClass="but5"
				styleId="btn_back" onclick="history.back();return false;" /></td>
		</tr>
	</table>
</html-el:form></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
})
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
