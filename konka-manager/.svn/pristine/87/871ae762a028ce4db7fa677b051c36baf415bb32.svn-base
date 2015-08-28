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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<link
	href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/jquery-ui/ui/ui.core.js"></script>
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
	action="/admin/KonkaPhotoUploadType.do">
	<html-el:hidden property="id" styleId="id" />
	<html-el:hidden property="method" styleId="method" value="save" />
	<html-el:hidden property="mod_id" styleId="mod_id"
		value="${af.map.mod_id}" />
	<html-el:hidden property="deptString" styleId="deptString"
		value="${af.map.deptString}" />
	<html-el:hidden property="queryString" styleId="queryString" />
	<c:if test="${af.map.id ne null}">
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
				<td nowrap="nowrap" class="title_item" align="right"><span
					style="color: red">*&nbsp;</span>添加时间：</td>
				<td><html-el:text property="year" styleId="year" size="4" />年&nbsp;
				<html-el:text property="month" styleId="month" size="2" />月&nbsp; <html-el:text
					property="day" styleId="day" size="2" />日&nbsp;</td>
			</tr>
			<tr>
				<td class="title_item" align="right" nowrap="nowrap"><font
					color="red">* </font>分公司：</td>
				<td align="left" nowrap="nowrap"><select id="dept_id_name"
					multiple="multiple" name="example-basic" size="5">
					<c:forEach items="${sybDeptInfoList}" var="cur">
						<option value="${cur.dept_id}#${cur.dept_name}">${cur.dept_name}</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="title_item" align="right"><span
					style="color: red">*&nbsp;</span>类型名称：</td>
				<td><html-el:text property="type_tilte"
					styleId="type_tilte" style="width:250px" maxlength="20"/></td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="title_item" align="right">拍照类型描述：</td>
				<td colspan="5"><html-el:textarea
					property="type_memo" styleId="type_memo"
					style="resize:none;width:600px;height:100px" /></td>
			</tr>
			<tr>
			<td nowrap="nowrap" class="title_item" align="right"><span style="color:red">*&nbsp;</span>是否停用：</td>
			<td><html-el:select property="state" styleId="state"
				styleClass="webinput">
				<html-el:option value="0">正常</html-el:option>
				<html-el:option value="1">停用</html-el:option>
			</html-el:select></td>
			</tr>
			<tr>
			</tr>
		</table>
	</c:if>
	<c:if test="${af.map.id eq null}">
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
				<td class="title_item" align="right" nowrap="nowrap"><font
					color="red">* </font>分公司：</td>
				<td align="left" nowrap="nowrap"><select id="dept_id_name"
					multiple="multiple" name="example-basic" size="5">
					<c:forEach items="${sybDeptInfoList}" var="cur">
						<option value="${cur.dept_id}#${cur.dept_name}">${cur.dept_name}</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="title_item" align="right"><span
					style="color: red">*&nbsp;</span>类型名称：</td>
				<td><html-el:text property="type_tilte"
					styleId="type_tilte" style="width:250px" maxlength="20"/></td>
			</tr>
			<tr>
			<td nowrap="nowrap" class="title_item" align="right"><span style="color:red">*&nbsp;</span>是否停用：</td>
			<td><html-el:select property="state" styleId="state"
				styleClass="webinput">
				<html-el:option value="0">正常</html-el:option>
				<html-el:option value="1">停用</html-el:option>
			</html-el:select></td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="title_item" align="right"><span
					style="color: red">*&nbsp;</span>添加时间：</td>
				<td><html-el:text property="year" styleId="year" size="4" />年&nbsp;
				<html-el:text property="month" styleId="month" size="2" />月&nbsp; <html-el:text
					property="day" styleId="day" size="2" />日&nbsp;</td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="title_item" align="right">拍照类型描述：</td>
				<td colspan="5"><html-el:textarea
					property="type_memo" styleId="type_memo"
					style="resize:none;width:600px;height:100px" /></td>
			</tr>
			<tr>
			</tr>
		</table>
	</c:if>
	<table>
		<tr>
			<td style="width: 500px;"></td>
			<td><html-el:button property="" value="提交" styleClass="but4"
				styleId="btn_submit" /> <html-el:button property="" value="返 回"
				styleClass="but5" styleId="btn_back"
				onclick="history.back();return false;" /></td>
		</tr>
	</table>

</html-el:form></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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
<script type="text/javascript"
	src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//$("#is_del").attr("dataType", "Require").attr("msg", "请选择是否删除");
	//$("#is_lock").attr("dataType", "Require").attr("msg", "请选择是否锁定");
	//$("#par_type_id").attr("dataType", "Require").attr("msg", "请选择父类级别");
	//$("#order_sort").attr("dataType", "Require").attr("msg", "请填写排序值");
	//$("#order_sort" ).focus(function(){setOnlyInt(this);});
    
    var dept = $("#deptString").val();
    var deptname = dept.split(",");
    for( var i = 0; i < deptname.length; i++){
        $("#dept_id_name option[value='"+deptname[i]+"']").attr("selected", "selected");

    }
	function multiCustOrShop(id){
		 $("#"+id).multiselect( {
			noneSelectedText : "==请选择==",
			checkAllText : "全选",
			uncheckAllText : '全不选',
			selectedList : 3
		}).multiselectfilter();
	}
	multiCustOrShop("dept_id_name");
	//Ajax列表查询


	$("#btn_submit").click(function(){
		  //$("#id").attr("dataType","Require").attr("msg","请填写ID");
		  //$("#user_id").attr("dataType","Require").attr("msg","请填写用户ID");
		  //$("#password").attr("dataType","Require").attr("msg","请填写密码");
		  //$("#role_id").attr("dataType","Require").attr("msg","请填写角色ID");
		  //$("#user_name").attr("dataType","Require").attr("msg","请填写用户名");
		  $("#dept_id_name").attr("dataType","Require").attr("msg","必须选择一个分公司！");
		  $("#type_tilte").attr("dataType","Require").attr("msg","类型名称不能为空！");
		  $("#type_memo").attr("dataType", "Limit").attr("max", "300").attr("msg", "拍照类型描述不能超过300字");
			if (Validator.Validate(this.form, 3)) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
		});
		});



	function setOnlyInt(obj) {
		$(obj).css("ime-mode", "disabled");
		$(obj).attr("t_value", "");
		$(obj).attr("o_value", "");
		$(obj).bind("dragenter", function() {
			return false;
		});
		$(obj).keypress(function() {
			if (!obj.value.match(/^\d*$/))
				obj.value = obj.t_value;
			else
				obj.t_value = obj.value;
			if (obj.value.match(/^(?:\d+(?:\d+)?)?$/))
				obj.o_value = obj.value;
		}).keyup(function() {
			if (!obj.value.match(/^\d*$/))
				obj.value = obj.t_value;
			else
				obj.t_value = obj.value;
			if (obj.value.match(/^(?:\d+(?:\d+)?)?$/))
				obj.o_value = obj.value;
		}).blur(function() {
			if (!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))
				obj.value = obj.o_value;
			else {
				if (obj.value.match(/^\d+$/))
					obj.value = obj.value;
				if (obj.value.match(/^\.$/))
					obj.value = 0;
				obj.o_value = obj.value;
			}
			if (obj.value.length == 0 || isNaN(obj.value) || obj.value == 0)
				obj.value = "0";
		});
	}

	function setOnlyNum() {
		$(this).css("ime-mode", "disabled");
		$(this).attr("t_value", "");
		$(this).attr("o_value", "");
		$(this).bind("dragenter", function() {
			return false;
		});
		$(this).keypress(function() {
			if (!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))
				this.value = this.t_value;
			else
				this.t_value = this.value;
			if (this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))
				this.o_value = this.value;
		}).keyup(function() {
			if (!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))
				this.value = this.t_value;
			else
				this.t_value = this.value;
			if (this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))
				this.o_value = this.value;
		}).blur(function() {
			if (!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))
				this.value = this.o_value;
			else {
				if (this.value.match(/^\.\d+$/))
					this.value = 0 + this.value;
				if (this.value.match(/^\.$/))
					this.value = 0;
				this.o_value = this.value
			}
			if (this.value.length == 0)
				this.value = "0";
		});
		//this.text.selected;
	}

	//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
