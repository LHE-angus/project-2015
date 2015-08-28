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
<style type="text/css">
.rtable1 td {
	padding: 2px 5px;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
<div class="oartop">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="3%"><img
			src="${ctx}/styles/admin-index/images/k_tup.jpg"
			style="vertical-align: middle;" /></td>
		<td>当前位置：${naviString}</td>
		<td width="60"><img src="${ctx}/images/manager/help.gif"
			style="vertical-align: middle;" /> <span id="span_help"
			style="cursor: pointer;">帮助</span></td>
	</tr>
</table>
</div>
<div class="rtabcont1"><html-el:form
	action="/admin/KonkaPhotoUploadType.do">
	<html-el:hidden property="method" value="list" />
	<html-el:hidden property="id" value="id" />
	<html-el:hidden property="isfirst" value="first"/>
	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<table width="100%" border="0" cellspacing="5" cellpadding="0"
		class="rtable1">
		<tr>
		<td align="right"><strong class="fb">添&nbsp;&nbsp;加&nbsp;&nbsp;日&nbsp;&nbsp;期：</strong></td>
			<td><html-el:text property="add_date_s" styleId="add_date_s"
				size="10" maxlength="10" readonly="true"
				onclick="new Calendar(1900, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /> 到 <html-el:text
				property="add_date_e" styleId="add_date_e" size="10" maxlength="10"
				readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
			<td align="right"><strong class="fb">分 公 司：</strong></td>
			<td><html-el:select property="dept_id" styleId="dept_id"
				style="width:100px">
				<html-el:option value=" ">请选择...</html-el:option>
				<c:forEach var="cur" items="${sybDeptInfoList}">
					<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
				</c:forEach>
			</html-el:select></td>
			<td align="right"><strong class="fb">类型名称：</strong></td>
			<td><html-el:text property="type_title_like"
				styleId="type_title_like" size="15" maxlength="10" /></td>
			<td align="right"><strong class="fb">类型状态：</strong></td>
			<td><html-el:select property="_state" styleId="_state"
				style="width:100px">
				<html-el:option value=" ">--请选择--</html-el:option>
					<html-el:option value="0">正常</html-el:option>
					<html-el:option value="1">停用</html-el:option>
			</html-el:select></td>	
			<td>
			<html-el:submit styleClass="but1" value="搜索" /></td>
		</tr>
	</table>
</html-el:form></div>
<%@ include file="/commons/pages/messages.jsp"%>
<div style="overflow-x: auto; height: 340px;">
<div class="rtabcont1"><input type="button" class="but2"
	value="新 增"
	onclick="location.href='KonkaPhotoUploadType.do?method=add&mod_id=${af.map.mod_id}';" /></div>
<div class="rtabcont1" style="overflow-x: auto;">
<table width="100%" border="0" cellpadding="0" cellspacing="1"
	class="rtable2">
	<tr class="tabtt1">
		<td width="5%" nowrap="nowrap" align="center">序号</td>
		<td width="3%" nowrap="nowrap" align="center">添加时间</td>
		<td width="5%" nowrap="nowrap" align="center">分公司</td>
		<td width="5%" nowrap="nowrap" align="center">类型名称</td>
		<td width="5%" nowrap="nowrap" align="center">类型描述</td>
		<td width="5%" nowrap="nowrap" align="center">添加人</td>
		<td width="11%" nowrap="nowrap" align="center">类型状态</td>
		<td width="11%" nowrap="nowrap" align="center">操作</td>
	</tr>
	<c:forEach items="${entityList}" var="cur" varStatus="vs">
		<tr>
		<td align="center" nowrap="nowrap">${(af.map.pager.currentPage -
			1) * af.map.pager.pageSize + vs.count}</td>
			<td align="center" nowrap="nowrap"><fmt:formatDate
				value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
			<td nowrap="nowrap" align="left" title="${cur.map.dept_name}">
			<c:choose>
				<c:when test="${fn:length(cur.map.dept_name) > 20}">
					<c:out value="${fn:substring(cur.map.dept_name, 0, 20)}......" />
				</c:when>
				<c:otherwise>
					<c:out value="${cur.map.dept_name}" />
				</c:otherwise>
			</c:choose></td>
			<td align="left" nowrap="nowrap">${cur.type_tilte}</td>
			<td align="left" nowrap="nowrap" title="${cur.type_memo}">${fn:substring(cur.type_memo,0,20)}</td>
			<td align="center" nowrap="nowrap">${cur.add_user_name}</td>
			<td align="center" nowrap="nowrap"><c:if
				test="${cur.state eq 0}">正常</c:if> <c:if test="${cur.state eq 1}">停用</c:if>
			</td>
			<td align="center" nowrap="nowrap"><a class="fblue"
				href="javascript:doNeedMethod(null, 'KonkaPhotoUploadType.do', 'view', 'id=${cur.id}&'+$('#bottomPageForm').serialize());">查看</a>
			<span style="cursor: pointer;" class="fblue"
				onclick="doNeedMethod(null, 'KonkaPhotoUploadType.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>
<!--				<span style="cursor: pointer;" class="fblue"-->
<!--				onclick="doNeedMethod(null, 'KonkaPhotoUpload.do','add' ,'type_id=${cur.id}&' + $('#bottomPageForm').serialize())">拍照上传</span>-->
			</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
<form id="bottomPageForm" name="bottomPageForm" method="post"
	action="KonkaPhotoUploadType.do"><input id='export_id'
	style="display: none" name='excel_all' value='0' />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="20" align="center">
		<div style="text-align: right; padding-right: 5px;"><script
			type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
		<script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
								pager.addHiddenInputs("type_title_like", "${af.map.type_title_like}");
								pager.addHiddenInputs("add_date_s", "${af.map.add_date_s}");
								pager.addHiddenInputs("add_date_e", "${af.map.add_date_e}");
								pager.addHiddenInputs("_state", "${af.map._state}");
								document.write(pager.toString());
								</script></div>
		</td>
	</tr>
</table>
</form>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript"
	src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
</body>
</html>