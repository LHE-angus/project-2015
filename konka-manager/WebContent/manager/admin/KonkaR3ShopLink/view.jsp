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
	action="/admin/KonkaR3MmtMatch.do" enctype="multipart/form-data">
	<html-el:hidden property="link_id" styleId="link_id" />
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
			<td nowrap="nowrap" class="title_item" align="right">客户名称：</td>
			<td><c:out value="${af.map.map.customer_name}" /></td>
			<td nowrap="nowrap" class="title_item" align="right">R3编码：</td>
			<td><c:out value="${af.map.map.r3_code}" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">分公司：</td>
			<td><c:out value="${af.map.map.dept_name}" /></td>
			<td nowrap="nowrap" class="title_item" align="right">姓名：</td>
			<td><c:out value="${af.map.real_name}" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">职务：</td>
			<td>
			<c:if test="${af.map.position eq 1}">
        				付款
	            	</c:if>
	            	<c:if test="${af.map.position eq 2}">
	            		对账
	            	</c:if>
	            	<c:if test="${af.map.position eq 3}">
	            		业务
	            	</c:if>
	            	<c:if test="${af.map.position eq 4}">
	            		法人
	            	</c:if>
	            	<c:if test="${af.map.position eq 5}">
	            		售后
	            	</c:if>
	            	<c:if test="${af.map.position eq 6}">
	            		收货
	            	</c:if>
	            	<c:if test="${af.map.position eq 7}">
	            		送货
	            	</c:if>
	            	<c:if test="${af.map.position eq 8}">
	            		发票
	            	</c:if>
	            	<c:if test="${af.map.position eq 9}">
	            		其他
	            	</c:if></td>
			<td nowrap="nowrap" class="title_item" align="right">岗位：</td>
			<td><c:out value="${af.map.job}" /></td>
		</tr>
		<tr>
		    <td nowrap="nowrap" class="title_item" align="right">性别：</td>
			<td><c:if test="${af.map.sex eq 0}">男</c:if>
			<c:if test="${af.map.sex eq 1}">女</c:if> 
			<c:if test="${af.map.sex eq 2}">未知</c:if></td>
			<td nowrap="nowrap" class="title_item" align="right">生日：</td>
			<td><fmt:formatDate value="${af.map.birthday}"
				pattern="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">移动电话：</td>
			<td><c:out value="${af.map.tel}" /></td>
			<td nowrap="nowrap" class="title_item" align="right">固定电话：</td>
			<td><c:out value="${af.map.telephone}" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">电子邮箱：</td>
			<td><c:out value="${af.map.email}" /></td>
			<td nowrap="nowrap" class="title_item" align="right">传真：</td>
			<td><c:out value="${af.map.fax}" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">QQ号：</td>
			<td><c:out value="${af.map.qq}" /></td>
			<td nowrap="nowrap" class="title_item" align="right">微信号：</td>
			<td><c:out value="${af.map.weixin}" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">是否默认：</td>
			<td><c:if test="${af.map.is_default eq 0}">
				<c:out value="是" />
			</c:if> <c:if test="${af.map.is_default eq 1}">
				<c:out value="否" />
			</c:if></td>
			<td nowrap="nowrap" class="title_item" align="right">是否有效：</td>
			<td><c:if test="${af.map.is_valid eq 0}">
				<c:out value="是" />
			</c:if> <c:if test="${af.map.is_valid eq 1}">
				<c:out value="否" />
			</c:if> </td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">维护人：</td>
			<td><c:out value="${af.map.map.user_name}" /></td>
			<td nowrap="nowrap" class="title_item" align="right">维护时间：</td>
			<td><fmt:formatDate value="${af.map.add_date}"
				pattern="yyyy-MM-dd" /></td>
		</tr>
		<tr>
		    <td nowrap="nowrap" class="title_item" align="right">客户喜好：</td>
		    <td><c:out value="${af.map.customer_preferences}" /></td>
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
