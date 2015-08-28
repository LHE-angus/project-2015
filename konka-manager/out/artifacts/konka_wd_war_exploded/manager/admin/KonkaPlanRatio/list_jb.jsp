<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
      <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
    </tr>
  </table>
</div>
<div class="rtabcont1">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
		<tr class="tabtt1" align="center">
			<td width="5%" align="center" nowrap="nowrap">序号</td>
			<td width="10%" align="center" nowrap="nowrap">年度</td>
			<td width="10%" align="center" nowrap="nowrap">分公司编码</td>
			<td width="10%" align="center" nowrap="nowrap">分公司</td>
			<td width="10%" align="center" nowrap="nowrap">经办编码</td>
			<td width="10%" align="center" nowrap="nowrap">经办</td>
			<td width="10%" align="center" nowrap="nowrap">任务系数</td>
			<td width="10%" align="center" nowrap="nowrap">操作</td>
		</tr>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
			<tr>
				<td align="center">${vs.count}</td>
				<td align="center">${cur.y}</td>
				<td align="center">${cur.fgs_sn}</td>
				<td align="center">${cur.map.fgs_name}</td>
				<td align="center">${cur.dept_sn}</td>
				<td align="center">${cur.dept_name}</td>
				<td align="center">${cur.ratio}</td>
				<td align="center"><c:if test="${af.map.this_year eq  cur.y}"><a href="KonkaPlanRatio.do?method=edit_jb&mod_id=${af.map.mod_id}&y=${cur.y}&id=${cur.id}">修改</a></c:if>
					<c:if test="${af.map.this_year ne  cur.y}"><span style="color:#ccc;">修改</span></c:if>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td align="center" colspan="8"><input class="but5" type="button" name="Submit5" value="返回" onclick="location.href='KonkaPlanRatio.do?mod_id=${af.map.mod_id}&year=${af.map.y}&fgs_sn=${af.map.fgs_sn}';" /></td>
		</tr>
	</table>
</div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>