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
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-size:14px;font-family:sans-serif;">促销员：${user_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：${this_time}</font></div>
<div class="rtabcont1" style="overflow-x: auto;">
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
	<tr class="tabtt1">
		<td width="5%" align="center" nowrap="nowrap">序号</td>
		<td width="4%" align="center" nowrap="nowrap">发票编号</td>
		<td width="4%" nowrap="nowrap" align="center">申请金额</td>
		<td width="4%" nowrap="nowrap" align="center">初审金额</td>
		<td width="4%" nowrap="nowrap" align="center">终审金额</td>
		<td width="12%" nowrap="nowrap" align="center">备注</td>
		<td width="5%" nowrap="nowrap" align="center">数据来源</td>
		<td width="5%" nowrap="nowrap" align="center">是否参与提成核算</td>
		<td width="5%" nowrap="nowrap" align="center">状态</td>
		<td width="5%" nowrap="nowrap" align="center">附件</td>
		
	</tr>
	<c:forEach var="cur" items="${entityList}" varStatus="vs">
		<tr class="list-tr">
			<td align="center" nowrap="nowrap">${vs.count}</td>
			<td align="center" nowrap="nowrap">${cur.bill_no}</td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.dec_money}" type="currency" /> </span></td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.audit_money}" type="currency" /> </span></td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.final_audit_money}" type="currency" /> </span></td>
			<td align="left" nowrap="nowrap">${cur.bill_mem}</td>
			<td align="center" nowrap="nowrap">${fn:split('手机端,WEB端,IOS手机端,外部导入',',')[cur.data_source]}</td>
           <td align="center" nowrap="nowrap">${fn:split('是,否',',')[cur.is_valid_for_pay]}</td>
           <td align="center" nowrap="nowrap">
           <c:choose>
									<c:when test="${cur.state eq 0}">上传中</c:when>
									<c:when test="${cur.state eq 2}">初审通过</c:when>
									<c:when test="${cur.state eq 4}">初审不通过</c:when>
									<c:when test="${cur.state eq 6}">初审通过并转单</c:when>
									<c:when test="${cur.state eq 8}">终审通过</c:when>
									<c:when test="${cur.state eq 10}">终审不通过</c:when>
									
								</c:choose>
								
           </td>
            <td align="center" nowrap="nowrap"><a href="${ctx}/MobileList.do?method=downloadFile1&save_name=${cur.map.save_name}" target="_blank">${cur.map.file_name}</a>
            </td>
		</tr>
	</c:forEach>
</table>
</div>
<div align="center"><input 
			value="关闭" class="but5" id="btn_back"
			onclick="window.close();" /></div>
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
