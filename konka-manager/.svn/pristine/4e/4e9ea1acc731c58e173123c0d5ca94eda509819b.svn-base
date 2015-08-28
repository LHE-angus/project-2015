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
	action="/admin/KonkaR3ShopLink">
	<html-el:hidden property="method" value="list" />
	<html-el:hidden property="link_id" value="link_id" />
	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<table width="100%" border="0" cellspacing="10" cellpadding="0"
		class="rtable1">
		<tr>
			<td align="right"><strong class="fb">R3 编 码：</strong></td>
			<td><html-el:text property="r3_code_like" size="15"
				maxlength="20" styleId="r3_code_like" /></td>
			<td align="right"><strong class="fb">客户名称：</strong></td>
			<td><html-el:text property="customer_name_like" size="17"
				maxlength="20" styleId="customer_name_like" title="请输入客户名称" /></td>
			<td align="right"><strong class="fb">职务：</strong></td>
			<td><html-el:text property="position" size="10" maxlength="10"
				styleId="position" /></td>
			<!--  <td align="right"><strong class="fb">生&nbsp;&nbsp;&nbsp;&nbsp;日：</strong></td>
			<td><html-el:text property="birthday_s" styleId="birthday_s"
				size="10" maxlength="10" readonly="true"
				onclick="new Calendar(1900, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /> 到 <html-el:text
				property="birthday_e" styleId="birthday_e" size="10" maxlength="10"
				readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>-->
		</tr>
		<tr>
			<td align="right"><strong class="fb">分 公 司：</strong></td>
			<td><html-el:select property="dept_id" styleId="dept_id"
				style="width:100px">
				<html-el:option value=" ">请选择...</html-el:option>
				<c:forEach var="cur" items="${sybDeptInfoList}">
					<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
				</c:forEach>
			</html-el:select></td>
			<td align="right"><strong class="fb">固定电话：</strong></td>
			<td><html-el:text property="tel_like" size="17" maxlength="20"
				styleId="tel_like" /></td>
			<td align="right"><strong class="fb">岗位：</strong></td>
			<td><html-el:text property="job_like" size="10" maxlength="10"
				styleId="job_like" /></td>
			<td align="right"><strong class="fb">是否默认：</strong></td>
			<td><html-el:select property="is_default" styleId="is_default"
				styleClass="webinput">
				<html-el:option value="">--请选择--</html-el:option>
				<html-el:option value="0">是</html-el:option>
				<html-el:option value="1">否</html-el:option>
			</html-el:select></td>
		</tr>
		<tr>
			<td align="right"><strong class="fb">微 信 号：</strong></td>
			<td><html-el:text property="weixin_like" styleId="weixin_like" size="15"
				maxlength="10" /></td>
			<td align="right"><strong class="fb">移动电话：</strong></td>
			<td><html-el:text property="telephone_like" styleId="telephone_like" size="17" maxlength="20"
				styleClass="webinput" /></td>
			<td align="right"><strong class="fb">姓名：</strong></td>
			<td><html-el:text property="real_name_like" size="10" maxlength="10"
				styleId="real_name_like" styleClass="webinput" /></td>
			<td align="right"><strong class="fb">是否有效：</strong></td>
			<td><html-el:select property="is_valid" styleId="is_valid"
				styleClass="webinput">
				<html-el:option value="">--请选择--</html-el:option>
				<html-el:option value="0">是</html-el:option>
				<html-el:option value="1">否</html-el:option>
			</html-el:select>&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td align="right"><strong class="fb">Q&nbsp;&nbsp;Q&nbsp;&nbsp;号：</strong></td>
			<td><html-el:text property="qq_like" styleId="qq_like" size="15"
				maxlength="10" /></td>
			<td align="right"><strong class="fb">电子邮箱：</strong></td>
			<td><html-el:text property="email_like" styleId="email_like" size="17" maxlength="20"
				styleClass="webinput" /></td>
			<td align="right"><strong class="fb">传真：</strong></td>
			<td><html-el:text property="fax_like" styleId="fax_like" size="10" maxlength="15"
				styleClass="webinput" style="width:110px"/></td>
			<td valign="middle" nowrap="nowrap" class="title_item" align="right">
			<html-el:submit styleClass="but1" value="搜索" />
			</td>
		</tr>
	</table>
</html-el:form></div>
<%@ include file="/commons/pages/messages.jsp"%>
<div style="overflow-x: auto; height: 340px;">
<div class="rtabcont1"><input type="button" class="but2" value="新 增" 
				            onclick="location.href='KonkaR3ShopLink.do?method=add&mod_id=${af.map.mod_id}';" /></div>
<table width="100%" border="0" cellpadding="0" cellspacing="1"
	class="rtable2">
	<tr class="tabtt1">
	    <td width="5%" nowrap="nowrap" align="center">序号</td>
		<td width="5%" nowrap="nowrap" align="center">分公司</td>
		<td width="5%" nowrap="nowrap" align="center">职务</td>
		<td width="5%" nowrap="nowrap" align="center">姓名</td>
		<td width="5%" nowrap="nowrap" align="center">岗位</td>
		<td width="3%" nowrap="nowrap" align="center">性别</td>
		<td width="11%" nowrap="nowrap" align="center">生日</td>
		<td width="11%" nowrap="nowrap" align="center">移动电话</td>
		<td width="11%" nowrap="nowrap" align="center">固定电话</td>
		<td width="11%" nowrap="nowrap" align="center">电子邮箱</td>
		<td width="11%" nowrap="nowrap" align="center">微信号</td>
		<td width="11%" nowrap="nowrap" align="center">QQ号</td>
		<td width="11%" nowrap="nowrap" align="center">传真</td>
		<td width="3%" nowrap="nowrap" align="center">是否默认</td>
		<td width="3%" nowrap="nowrap" align="center">是否有效</td>
		<td width="11%" nowrap="nowrap" align="center">客户编码</td>
		<td width="10%" nowrap="nowrap" align="center">客户名称</td>
		<td width="11%" nowrap="nowrap" align="center">维护人</td>
		<td width="11%" nowrap="nowrap" align="center">维护时间</td>
		<td width="5%" nowrap="nowrap" align="center">操作</td>
	</tr>
	<c:forEach items="${entityList}" var="cur" varStatus="vs">
		<tr>
			<td align="center" nowrap="nowrap">${(af.map.pager.currentPage -
			1) * af.map.pager.pageSize + vs.count}</td>
			<td align="center" nowrap="nowrap">${cur.map.dept_name}</td>
			<td align="center" nowrap="nowrap">
			<c:if
				test="${cur.position eq 1}">
        				付款
	            	</c:if> <c:if test="${cur.position eq 2}">
	            		对账
	            	</c:if> <c:if test="${cur.position eq 3}">
	            		业务
	            	</c:if> <c:if test="${cur.position eq 4}">
	            		法人
	            	</c:if> <c:if test="${cur.position eq 5}">
	            		售后
	            	</c:if> <c:if test="${cur.position eq 6}">
	            		收货
	            	</c:if> <c:if test="${cur.position eq 7}">
	            		送货
	            	</c:if> <c:if test="${cur.position eq 8}">
	            		发票
	            	</c:if> <c:if test="${cur.position eq 9}">
	            		其他
	            	</c:if></td>
			<td align="center" nowrap="nowrap">${cur.real_name}</td>
			<td align="center" nowrap="nowrap">${cur.job}</td>
			<td align="center" nowrap="nowrap">
			<c:if test="${cur.sex eq 0}">男</c:if>
			<c:if test="${cur.sex eq 1}">女</c:if> 
			<c:if test="${cur.sex eq 2}">未知</c:if>
			</td>
			<td align="center" nowrap="nowrap"><fmt:formatDate
				value="${cur.birthday}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
			<td align="left" nowrap="nowrap">${cur.tel}</td>
			<td align="left" nowrap="nowrap">${cur.telephone}</td>
			<td align="center" nowrap="nowrap">${cur.email}</td>
			<td align="left" nowrap="nowrap">${cur.weixin}</td>
			<td align="left" nowrap="nowrap">${cur.qq}</td>
			<td align="left" nowrap="nowrap">${cur.fax}</td>
			<td align="center" nowrap="nowrap"><c:if
				test="${cur.is_default eq 0}">是</c:if> <c:if
				test="${cur.is_default eq 1}">否</c:if> <c:if
				test="${cur.is_default == ''}">${cur.is_default}</c:if></td>
			<td align="center" nowrap="nowrap"><c:if
				test="${cur.is_valid eq 0}">是</c:if> <c:if
				test="${cur.is_valid eq 1}">否</c:if> <c:if
				test="${cur.is_valid == ''}">${cur.is_valid}</c:if></td>
			<td align="center" nowrap="nowrap">${cur.map.r3_code}</td>
			<td align="center" nowrap="nowrap">${cur.map.customer_name}</td>
			<td align="center" nowrap="nowrap">${cur.map.user_name}</td>
			<td align="center" nowrap="nowrap"><fmt:formatDate
				value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
			<td align="center" nowrap="nowrap"><a class="fblue"
				href="javascript:doNeedMethod(null, 'KonkaR3ShopLink.do', 'view', 'link_id=${cur.link_id}&'+$('#bottomPageForm').serialize());">查看</a>
				<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3ShopLink.do','edit' ,'link_id=${cur.link_id}&' + $('#bottomPageForm').serialize())">修改</span>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<form id="bottomPageForm" name="bottomPageForm" method="post"
	action="KonkaR3ShopLink.do"><input id='export_id'
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
								pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
								pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
								pager.addHiddenInputs("real_name_like", "${af.map.real_name_like}");
								pager.addHiddenInputs("sex", "${af.map.sex}");
								pager.addHiddenInputs("birthday_s", "${af.map.birthday_s}");
								pager.addHiddenInputs("birthday_e", "${af.map.birthday_e}");
								pager.addHiddenInputs("tel_like", "${af.map.tel_like}");
								pager.addHiddenInputs("mobile_like", "${af.map.mobile_like}");
								pager.addHiddenInputs("job_like", "${af.map.job_like}");
								pager.addHiddenInputs("position", "${af.map.position}");
								pager.addHiddenInputs("fax_like", "${af.map.fax_like}");
								pager.addHiddenInputs("email_like", "${af.map.email_like}");
								pager.addHiddenInputs("weixin_like", "${af.map.weixin_like}");
								pager.addHiddenInputs("qq_like", "${af.map.qq_like}");
								pager.addHiddenInputs("is_default", "${af.map.is_default}");
								pager.addHiddenInputs("is_valid", "${af.map.is_valid}");
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