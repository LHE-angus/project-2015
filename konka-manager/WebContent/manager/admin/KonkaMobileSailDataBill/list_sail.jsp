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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
.rtable1 td {
	padding: 2px 5px;
}

.filed_border {
	border-left: 1px solid #ccc;;
	border-right: 1px solid #ccc;;
	border-bottom: 1px solid #ccc;;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
<div class="rtabcont1">
<div class="rtabcont1" style="overflow-x: auto;">
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
	<tr class="tabtt1">
		<td width="5%" align="center" nowrap="nowrap">序号</td>

		<td width="4%" align="center" nowrap="nowrap">分公司</td>
		<td width="4%" nowrap="nowrap" align="center">上报人</td>
		<td align="center" nowrap="nowrap">上报门店</td>
		<td width="4%" align="center" nowrap="nowrap">门店所属经办</td>
		<td width="8%" nowrap="nowrap" align="center">产品型号</td>
		<td width="3%" nowrap="nowrap" align="center">数量</td>
		<td width="6%" nowrap="nowrap" align="center">单价</td>
		<td width="6%" nowrap="nowrap" align="center">金额</td>

		<td width="6%" nowrap="nowrap" align="center">消费者姓名</td>
		<td width="6%" nowrap="nowrap" align="center">电话</td>
		<td nowrap="nowrap" align="center">身份证</td>
		<td width="12%" nowrap="nowrap" align="center">地址</td>
		<td width="12%" nowrap="nowrap" align="center">备注</td>
		<td width="5%" nowrap="nowrap" align="center">数据来源</td>

	</tr>
	<c:forEach var="cur" items="${entityList}" varStatus="vs">
		<tr class="list-tr">
			<td align="center" nowrap="nowrap">${vs.count}</td>

			<td align="center" nowrap="nowrap">${cur.subcomp_name}</td>
			<td align="center" nowrap="nowrap">${cur.report_name}</td>
			<td align="left" nowrap="nowrap">${cur.dept_name}</td>
			<td align="left" nowrap="nowrap">${cur.office_name}</td>
			<td align="left" nowrap="nowrap">${cur.model_name}</td>
			<td align="right" nowrap="nowrap">${cur.num}</td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.single_price}" type="currency" /> </span></td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.all_price}" type="currency" /> </span></td>
			<td align="left" nowrap="nowrap">${cur.realname}</td>
			<td align="left" nowrap="nowrap">${cur.phonenum}</td>
			<td align="left" nowrap="nowrap">${cur.mastercode}</td>
			<td align="left" nowrap="nowrap">${cur.addresss}</td>
			<td align="left" nowrap="nowrap">${cur.memo}</td>
			<td align="center" nowrap="nowrap">${fn:split('手机端,WEB端,IOS手机端,外部导入',',')[cur.data_source]}</td>

		</tr>
	</c:forEach>
</table>
</div>
<div align="center"><input 
			value="关闭" class="but5" id="btn_back"
			onclick="window.close();" /></div>
</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
