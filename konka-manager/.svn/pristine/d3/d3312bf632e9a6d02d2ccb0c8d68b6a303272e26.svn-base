<%@ page language="java"
	contentType="application/octet-stream;charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<td width="5%">序号</td>
		<td>日期</td>
		<td width="4%">分公司</td>
<!--		<td width="6%">客户所属经办</td>-->
		<td>客户名称</td>
		<td>客户R3编码</td>
		<td width="6%">客户类型</td>
		<td width="6%">细分类型</td>
		<td width="4%">上报人</td>
		<td>上报门店</td>
		<td width="4%">门店ID</td>
		<td width="4%">门店所属经办</td>
		<td width="4%">送达方</td>
		<td width="3%">尺寸</td>
		<td width="8%">产品型号</td>
		<td width="4%">数量</td>
		<td width="3%">单价</td>
		<td width="6%">金额</td>
		<td width="6%">参考单价</td>
		<td width="6%">参考金额</td>
		<td width="6%">消费者姓名</td>
		<td width="6%">电话</td>
		<td>身份证</td>
		<td width="12%">地址</td>
		<td width="12%">备注</td>
		<td width="5%">数据来源</td>
		<td width="10%">附件</td>
	</tr>
	<c:forEach var="cur" items="${allList}" varStatus="vs">
		<tr>
			<td>${(af.map.pager.currentPage - 1) * af.map.pager.pageSize +
			vs.count}</td>
			<td><fmt:formatDate value="${cur.report_date}"
				pattern="yyyy/MM/dd" /></td>
			<td>${cur.subcomp_name}</td>
<!--			<td>${cur.cust_office_name}</td>-->
			<td>${cur.customer_name}</td>
			<td>${cur.customer_r3_code}</td>
			<td>${cur.map.c_comm}</td>
			<td>${cur.map.c_name}</td>
			<td>${cur.report_name}</td>
			<td>${cur.dept_name}</td>
			<td>${cur.dept_id}</td>
			<td>${cur.office_name}</td>
			<td>${cur.map.r3_sdf_sn}</td>
			<td>${cur.measure_name}</td>
			<td>${cur.model_name}</td>
			<td>${cur.num}</td>
			<td><c:if
				test="${(not empty cur.all_price) and (not empty cur.num)}">
				<fmt:formatNumber
					value="${cur.num ne 0 ? (cur.all_price / cur.num) : 0 }" />
			</c:if><c:if test="${(empty cur.all_price) or (empty cur.num)}">0</c:if></td>
			<td><c:if test="${not empty cur.all_price}">
				<fmt:formatNumber value="${cur.all_price}" />
			</c:if><c:if test="${empty cur.all_price}">-</c:if></td>
			<td><c:if test="${empty cur.price_ref}">-</c:if><c:if
				test="${not empty cur.price_ref}">
				<fmt:formatNumber value="${cur.price_ref}" />
			</c:if></td>
			<td><fmt:formatNumber
				value="${cut.num ne 0 ? cur.price_ref * cur.num : 0}" /></td>
			<td>${cur.realname}</td>
			<td>${cur.phonenum}</td>
			<td>${cur.mastercode}</td>
			<td>${cur.addresss}</td>
			<td>${cur.memo}</td>
			<td>${fn:split('手机端,WEB端,IOS手机端', ',')[cur.data_source]}</td>
			 <c:if test="${not empty cur.map.fapiaos}">
			 <c:set var="fapiao" value="${fn:split(cur.map.fapiaos, ',')}" />
			<c:forEach items="${fapiao}" var="tt" varStatus="vs1">
				<td><a href="http://qdgl.konka.com/${tt}"
					target="_blank">附件${vs1.count}</a></td>
			</c:forEach>
			</c:if>
		</tr>
	</c:forEach>
</table>
</body>
</html>
