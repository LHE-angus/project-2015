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
</head>
<body>
	<table width="100%" border="1">
		<tr align="center">
			<td nowrap="nowrap" width="3%">序号</td>
			<td nowrap="nowrap">主题</td>
			<td nowrap="nowrap" width="5%">单据编号</td>
			<td nowrap="nowrap" width="5%">活动类型</td>
			<td nowrap="nowrap" width="5%">活动状态</td>
			<td nowrap="nowrap" width="5%">单据状态</td>
			<td nowrap="nowrap" width="5%">分公司</td>
			<td nowrap="nowrap" width="5%">经营部</td>
			<td nowrap="nowrap" width="5%">主要客户</td>
			<td nowrap="nowrap" width="5%">主要R3编码</td>
			<td nowrap="nowrap" width="5%">客户类型</td>
			<td nowrap="nowrap" width="5%">细分类型</td>
	        <td nowrap="nowrap" align="center">任务金额(万元)</td>
	        <td nowrap="nowrap" align="center">任务销量(台)</td>
	        <td nowrap="nowrap" align="center">活动渠道排名</td>
			<td nowrap="nowrap" width="5%">业务员</td>
			<td nowrap="nowrap" width="5%">其他客户</td>
			<td nowrap="nowrap" width="5%">其他R3编码</td>
			<td nowrap="nowrap" width="5%">活动目标</td>
			<td nowrap="nowrap" width="5%">准备期起</td>
			<td nowrap="nowrap" width="5%">准备期止</td>
			<td nowrap="nowrap" width="5%">爆发期起</td>
			<td nowrap="nowrap" width="5%">爆发期止</td>
			<td nowrap="nowrap" width="5%">预约数</td>
			<td nowrap="nowrap" width="5%">文字备注</td>
			<td nowrap="nowrap" width="5%">销售总金额</td>
			<td nowrap="nowrap" width="5%">销售总数量</td>
			<td nowrap="nowrap" width="5%">指定机型品类/型号</td>
          	<td nowrap="nowrap" width="5%">指定机型订货额（万元）</td>
          	<td nowrap="nowrap" width="5%">指定机型订货量</td>
			<td nowrap="nowrap" width="5%">负责人</td>
			<td nowrap="nowrap" width="5%">联系电话</td>
			<td nowrap="nowrap" width="5%">上报人</td>
			<td nowrap="nowrap" width="5%">创建时间</td>
			<td nowrap="nowrap" width="5%">地址</td>
		    <td nowrap="nowrap" width="10%">附件</td>
		</tr>
		<c:forEach var="cur" items="${entityList1}" varStatus="vs">
			<tr>
				<td align="center" nowrap="nowrap">${vs.count}</td>
				<td align="left" nowrap="nowrap">${cur.sp_name}</td>
				<td align="left" nowrap="nowrap">${cur.sp_sn}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.map.hd_type)}</td>

				<td align="left" nowrap="nowrap">
					<c:choose>
						<c:when test="${fn:escapeXml(cur.hd_status) eq 0}">未上报</c:when>
						<c:when test="${fn:escapeXml(cur.hd_status) eq 30}">进行中</c:when>
						<c:when test="${fn:escapeXml(cur.hd_status) eq 50}">已结束</c:when>
					</c:choose>
				</td>
				<td align="center" nowrap="nowrap">
		            <c:if test="${cur.file_status eq 0}">无效</c:if>
		            <c:if test="${cur.file_status ne 0}">有效</c:if>
		        </td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.dept_name)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.jyb_name)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.main_customer)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.main_r3_code)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.par_customer_type_name)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.customer_type_name)}</td>
	            <td align="right" nowrap="nowrap">${cur.task_money}</td>
	            <td align="right" nowrap="nowrap">${cur.task_sail_num}</td>
	            <td align="right" nowrap="nowrap">${cur.activity_ranking}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.ywy_name)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.other_customer)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.other_r3_code)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.target)}</td>
				<td align="left" nowrap="nowrap"><fmt:formatDate
						value="${cur.zb_s_date}" pattern="yyyy-MM-dd" /></td>
				<td align="left" nowrap="nowrap"><fmt:formatDate
						value="${cur.zb_e_date}" pattern="yyyy-MM-dd" /></td>
				<td align="left" nowrap="nowrap"><fmt:formatDate
						value="${cur.bf_s_date}" pattern="yyyy-MM-dd" /></td>
				<td align="left" nowrap="nowrap"><fmt:formatDate
						value="${cur.bf_e_date}" pattern="yyyy-MM-dd" /></td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.yy_num)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.file_remark)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.total_sail_money)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.total_sail_num)}</td>
				<td align="center" nowrap="nowrap" colspan="3">
		            <table border="1">
		             <c:forEach items="${cur.konkaSpMdSailList}" var="_cur">
		             	<tr>
		             		<td align="right" nowrap="nowrap">${fn:escapeXml(_cur.md_name)}</td>
		             		<td align="right" nowrap="nowrap">${fn:escapeXml(_cur.money)}</td>
		             		<td align="right" nowrap="nowrap">${fn:escapeXml(_cur.num)}</td>
		             	</tr>
		             </c:forEach>
		            </table>
		        </td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.charge_person)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.charge_person_tel)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.report_user_name)}</td>
				<td align="left" nowrap="nowrap"><fmt:formatDate
						value="${cur.add_sp_date}" pattern="yyyy-MM-dd" /></td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.hd_addr)}</td>
				<c:if test="${not empty cur.map.files}">
				 <c:set var="file" value="${fn:split(cur.map.files, ',')}" />
				<c:forEach items="${file}" var="tt" varStatus="vs1">
					<td><a href="http://qdgl.konka.com/${tt}"
						target="_blank">附件${vs1.count}</a></td>
				</c:forEach>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>