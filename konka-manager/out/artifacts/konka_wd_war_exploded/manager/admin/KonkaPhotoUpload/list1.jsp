<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>已提交列表</title>
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
	action="/admin/KonkaPhotoUpload.do">
	<html-el:hidden property="method" value="list1" />
	<html-el:hidden property="id" value="id" />
	<html-el:hidden property="isfirst" value="first"/>
	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<table width="100%" border="0" cellspacing="5" cellpadding="0"
		class="rtable1">
		<tr>
		    	<td align="right"><strong class="fb">上报日期：</strong></td>
			<td><html-el:text property="_begin_report_date" styleId="_begin_report_date"
				size="10" maxlength="10" readonly="true"
				onclick="new Calendar(1900, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /> - <html-el:text
				property="_end_report_date" styleId="_end_report_date" size="10" maxlength="10"
				readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
				
				<td align="right"><strong class="fb">分 公 司：</strong></td>
			<td><html-el:select property="_dept_id" styleId="_dept_id"
				style="width:100px">
				<html-el:option value=" ">请选择...</html-el:option>
				<c:forEach var="cur" items="${sybDeptInfoList}">
					<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
				</c:forEach>
			</html-el:select></td>
			
			<td align="right"><strong class="fb">上报人：</strong></td>
			<td><html-el:text property="_report_user_name_like"
				styleId="_report_user_name_like" size="15" maxlength="10" /></td>
		</tr>
		<tr>
			<td align="right"><strong class="fb">客户R3编码：</strong></td>
			<td><html-el:text property="_r3_code_like"
				styleId="_r3_code_like" size="15" maxlength="10" /></td>
			<td align="right"><strong class="fb">终端编码：</strong></td>
			<td><html-el:text property="_store_id_like"
				styleId="_store_id_like" size="15" maxlength="10" /></td>	
				
			<td align="right"><strong class="fb">活动类型：</strong></td>
			<td><html-el:select property="_type_id" styleId="_type_id" value="${af.map._type_id}">
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<c:forEach items="${photoTypes}" var="cur">
	    		<html-el:option value="${cur.ID}">${cur.TYPE_TILTE}</html-el:option>
	    		</c:forEach>
	    	</html-el:select></td>
		</tr>
		<tr>
			<td align="right"><strong class="fb">客户名称：</strong></td>
			<td><html-el:text property="_customer_name_like"
				styleId="_customer_name_like" size="15" maxlength="10" /></td>
			<td align="right"><strong class="fb">终端名称：</strong></td>
			<td><html-el:text property="_store_name_like"
				styleId="_store_name_like" size="15" maxlength="10" /></td>
				<td></td>
				<td align="center"><html-el:submit styleClass="but1" value="搜索" /></td>
		</tr>
	</table>
</html-el:form></div>
<%@ include file="/commons/pages/messages.jsp"%>
<div style="overflow-x: auto; height: 340px;">
<div class="rtabcont1">
<input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />
</div>
<!--<div class="rtabcont1">

<input type="button" class="but2" value="新 增" onclick="location.href='KonkaPhotoUpload.do?method=add&status=2&mod_id=${af.map.mod_id}';" />
	</div>
<div class="rtabcont1" style="overflow-x: auto;"> -->
<table width="100%" border="0" cellpadding="0" cellspacing="1"
	class="rtable2">
	<tr class="tabtt1">
		<td width="5%" nowrap="nowrap" align="center">序号</td>
		<td width="11%" nowrap="nowrap" align="left">上报日期</td>
		<td width="5%" nowrap="nowrap" align="left">分公司</td>
		<td width="3%" nowrap="nowrap" align="left">上报人</td>
		<td width="5%" nowrap="nowrap" align="left">活动类型</td>
		<td width="5%" nowrap="nowrap" align="left">客户R3编码</td>
		<td width="5%" nowrap="nowrap" align="left">客户名称</td>
		<td width="5%" nowrap="nowrap" align="left">终端编码</td>
		<td width="5%" nowrap="nowrap" align="left">终端名称</td>
		<td width="11%" nowrap="nowrap" align="left">上报说明</td>
		<td width="11%" nowrap="nowrap" align="left">备注</td>
		<td width="11%" nowrap="nowrap" align="left">定位信息</td>
		<td width="300px;" nowrap="nowrap" align="left">附件</td>
		<td width="11%" nowrap="nowrap" align="left">操作</td>
	</tr>
	<c:forEach items="${entityList}" var="cur" varStatus="vs">
		<tr>
			<td align="left" nowrap="nowrap">${(af.map.pager.currentPage-1) * af.map.pager.pageSize + vs.count}</td>
			<td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.report_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
			<td nowrap="nowrap" align="left" >${cur.dept_name}</td>
			<td align="left" nowrap="nowrap">${cur.report_user_name}</td>
			<td align="left" nowrap="nowrap">${cur.map.type_tilte}</td>
			<td align="left" nowrap="nowrap">${cur.r3_code}</td>
			<td align="left" nowrap="nowrap">${cur.customer_name}</td>
			<td align="left" nowrap="nowrap">${cur.store_id}</td>
			<td align="left" nowrap="nowrap">${cur.store_name}</td>
			<td align="left" nowrap="nowrap" title="${cur.report_memo}">${fn:substring(cur.report_memo,0,20)}</td>
			<td align="left" nowrap="nowrap" title="${cur.remark}">${fn:substring(cur.remark,0,20)}</td>
			<td align="left" nowrap="nowrap" title="${cur.map.addr}">${fn:substring(cur.map.addr,0,20)}</td>
			<td width="300px;">
           <c:if test="${not empty cur.map.fj_paths}">
	          <c:set var="fj_paths" value="${fn:split(cur.map.fj_paths,',')}" />
	          <c:forEach items="${fj_paths}" var="fj_path" varStatus="vs1">
	          	      <a href="${ctx}/${fj_path}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
	          </c:forEach>
          </c:if>
          </td>
			<td align="left" nowrap="nowrap"><a class="fblue"
				href="javascript:doNeedMethod(null, 'KonkaPhotoUpload.do', 'view', 'id=${cur.id}&'+$('#bottomPageForm').serialize());">查看</a>
			<!--<span style="cursor: pointer;" class="fblue"
				onclick="doNeedMethod(null, 'KonkaPhotoUpload.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>
			--></td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
<form id="bottomPageForm" name="bottomPageForm" method="post"
	action="KonkaPhotoUpload.do"><input id='export_id'
	style="display: none" name='excel_all' value='0' />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="20" align="center">
		<div style="text-align: right; padding-right: 5px;"><script
			type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
		<script type="text/javascript">
			var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			pager.addHiddenInputs("method", "list1");
			pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("_dept_id", "${af.map._dept_id}");
			pager.addHiddenInputs("_customer_name_like", "${af.map._customer_name_like}");
			pager.addHiddenInputs("_store_name_like", "${af.map._store_name_like}");
			pager.addHiddenInputs("_store_name_like", "${af.map._store_name_like}");
			pager.addHiddenInputs("_begin_report_date", "${af.map._begin_report_date}");
			pager.addHiddenInputs("_end_report_date", "${af.map._end_report_date}");
			pager.addHiddenInputs("_type_id", "${af.map._type_id}");
			pager.addHiddenInputs("_r3_code_like", "${af.map._r3_code_like}");
			pager.addHiddenInputs("_store_id_like", "${af.map._store_id_like}");
			pager.addHiddenInputs("_is_del", "${af.map._is_del}");
			pager.addHiddenInputs("isfirst", "${af.map.isfirst}");
			pager.addHiddenInputs("_report_user_name_like", "${af.map._report_user_name_like}");
			document.write(pager.toString());
			</script>
		</div>
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
	
	<script type="text/javascript">
		$("#export_excel").click(function(){
			if(confirm("提示，您确认导出数据？")){
				$("#export_id").val(1);
				$("#bottomPageForm").submit();
			}
			$("#export_id").val(0);
		});
	</script>
</body>
</html>