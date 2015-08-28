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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
<div class="rtabcont1">
<%@ include file="/commons/pages/messages.jsp" %>
</div>
<div class="rtabcont1">
     <input type="hidden" name="method" id="method" value="delete" />
     <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
     <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
		<thead>
		<tr class="tabtt1">
			<td nowrap="nowrap" align="center">仓库</td>
			<td nowrap="nowrap" align="center">存品</td>
			<td nowrap="nowrap" align="center">操作事务</td>
			<td nowrap="nowrap" align="center">发生数/业务数</td>
			<td nowrap="nowrap" align="center">操作人</td>
			<td nowrap="nowrap" align="center">操作时间</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
		<tr>
			<td align="center" nowrap="nowrap">${cur.map.storage_name}</td>
			<td align="center" nowrap="nowrap">${cur.map.coll_name}</td>
			<td align="center" nowrap="nowrap">
			<c:choose>
				<c:when test="${cur.op_type eq 0}">存品出库</c:when>
				<c:when test="${cur.op_type eq 1}">存品入库</c:when>
				<c:when test="${cur.op_type eq 2}">锁定存品</c:when>
				<c:when test="${cur.op_type eq 3}">解锁存品</c:when>
				<c:when test="${cur.op_type eq 4}">重置初始</c:when>
			</c:choose>
			</td>
			<td align="right" nowrap="nowrap">${cur.op_num}</td>
			<td align="center" nowrap="nowrap">${cur.map.op_man_name}</td>
			<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.op_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<c:if test="${vs.last eq true}">
			<c:set var="i" value="${vs.count}" />
		</c:if>
		</c:forEach>
		<c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
		<tr align="center">
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileStocksHis.do">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="40">
					<div style="text-align:right; padding-right:5px;">
					<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
					<script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						pager.addHiddenInputs("method", "list");
						pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						pager.addHiddenInputs("coll_id", "${af.map.coll_id}");
						pager.addHiddenInputs("storage_id", "${af.map.storage_id}");
						document.write(pager.toString());
					</script>
					</div>
				</td>
			</tr>
		</table>
	</form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#storage_name").blur(function() {
		$(this).val(this.value.trim());						   
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>