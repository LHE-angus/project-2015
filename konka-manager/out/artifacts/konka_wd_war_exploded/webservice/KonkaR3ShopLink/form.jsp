<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8"></meta>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv=Expires content=0 />
<meta http-equiv=Cache-Control content=no-cache />
<meta http-equiv=Pragma content=no-cache />
<title>客户联系人</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/wap/common.css" />
<link href="mobiscroll.custom-2.6.2.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="conlist">
<ul>
	<li><html-el:form action="/KonkaR3ShopLink">
		<html-el:hidden property="method" value="save" styleId="method" />
		<html-el:hidden property="link_id" value="${af.map.link_id}" />
		<html-el:hidden property="user_id" value="${user_id}" />
		<html-el:hidden property="userpass" value="${userpass}" />
		<html-el:hidden property="queryString" styleId="queryString" />
		<table class="tdclass" width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<c:if test="${not empty af.map.link_id}">
				<tr>
					<td width="35%" align="right">客户名称：</td>
					<td width="65%" colspan="1"><c:out
						value="${af.map.map.customer_name}" /></td>
				</tr>
				<tr>
					<td align="right">R3编码：</td>
					<td><c:out value="${af.map.map.r3_code}" /></td>
				</tr>
				<tr>
					<td align="right">分公司：</td>
					<td colspan="1"><c:out value="${af.map.map.dept_name}" /></td>
				</tr>
			</c:if>
			<c:if test="${empty af.map.link_id}">
				<tr>
					<td width="35%" align="right"><font color="red">* </font>客户：</td>
					<td width="65%" colspan="1"><html-el:select
						property="customer_code_select" styleClass="input-txt"
						styleId="customer_code_select" onchange="customer_code_chg();">
						<html-el:option value="">--请选择--</html-el:option>
						<c:forEach items="${customerList}" var="cur" varStatus="vs">
							<html-el:option value="${cur.map.customer_code}">[${cur.map.customer_code}]${cur.map.customer_name}</html-el:option>
						</c:forEach>
					</html-el:select></td>
				</tr>
				<tr>
					<td width="35%" align="right"><span style="color: red">*&nbsp;</span>客户名称：</td>
					<td width="65%" colspan="1"><html-el:text
						property="customer_name" styleId="customer_name"
						styleClass="input-txt"  readonly="true" /></td>
				</tr>
				<tr>
					<td align="right"><font style="color: red">*&nbsp;</font>R3编码：</td>
					<td><html-el:text property="r3_code" styleClass="input-txt"
						styleId="r3_code" readonly="true" /></td>
				</tr>
				<tr>
					<td align="right"><span style="color: red">*&nbsp;</span>分公司：</td>
					<td colspan="1"><html-el:select property="dept_id"
						styleId="dept_id" styleClass="input-txt">
						<html-el:option value=" ">--请选择--</html-el:option>
						<c:forEach var="cur" items="${sybDeptInfoList}">
							<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
						</c:forEach>
					</html-el:select></td>
				</tr>
			</c:if> 
			<tr>
				<td align="right"><font style="color: red">*&nbsp;</font>姓名：</td>
				<td><html-el:text property="real_name" styleClass="input-txt"
					styleId="real_name" maxlength="20" /></td>
			</tr>
			<tr>
				<td align="right"><font style="color: red">*&nbsp;</font>职务：</td>
				<td><html-el:select property="position" styleId="position"
					styleClass="input-txt">
					<html-el:option value="1">付款</html-el:option>
					<html-el:option value="2">对账</html-el:option>
					<html-el:option value="3">业务</html-el:option>
					<html-el:option value="4">法人</html-el:option>
					<html-el:option value="5">售后</html-el:option>
					<html-el:option value="6">收货</html-el:option>
					<html-el:option value="7">送货</html-el:option>
					<html-el:option value="8">发票</html-el:option>
					<html-el:option value="9">其他</html-el:option>
				</html-el:select></td>
			</tr>
			<tr>
				<td align="right"><font style="color: red">*&nbsp;</font>性别：</td>
				<td><html-el:select property="sex" styleId="sex"
					styleClass="input-txt">
					<html-el:option value="0">男</html-el:option>
					<html-el:option value="1">女</html-el:option>
					<html-el:option value="2">未知</html-el:option>
				</html-el:select></td>
			</tr>
			<tr>
				<td align="right">移动电话：</td>
				<td><html-el:text property="tel" styleId="tel"
					styleClass="input-txt" maxlength="20" /></td>
			</tr>
			<tr>
				<td align="right">电子邮箱：</td>
				<td><html-el:text property="email" styleId="email"
					styleClass="input-txt" maxlength="30" /></td>
			</tr>
			<tr>
				<td align="right">QQ号：</td>
				<td><html-el:text property="qq" styleId="qq"
					styleClass="input-txt" maxlength="20" /></td>
			</tr>
			<tr>
				<td align="right">是否默认：</td>
				<td><html-el:select property="is_default" styleId="is_default"
					styleClass="input-txt">
					<html-el:option value="0">是</html-el:option>
					<html-el:option value="1">否</html-el:option>
				</html-el:select></td>
			</tr>

			<tr>
				<td align="right">岗位：</td>
				<td><html-el:text property="job" styleClass="input-txt"
					styleId="job" maxlength="20" /></td>
			</tr>
			<tr>
				<td align="right">生日：</td>
				<td><fmt:formatDate value="${af.map.birthday}" var="_s_date"
					pattern="yyyy-MM-dd" /> <html-el:text property="birthday"
					styleClass="input-txt" styleId="birthday" size="10" maxlength="10"
					value="${_s_date}"  title="点击选择日期" /></td>
			</tr>
			<tr>
				<td align="right">固定电话：</td>
				<td><html-el:text property="telephone" styleClass="input-txt"
					styleId="telephone" maxlength="20" /></td>
			</tr>
			<tr>
				<td align="right">传真：</td>
				<td><html-el:text property="fax" styleClass="input-txt"
					styleId="fax" maxlength="20" /></td>
			</tr>
			<tr>
				<td align="right">微信号：</td>
				<td><html-el:text property="weixin" styleClass="input-txt"
					styleId="weixin" maxlength="20" /></td>
			</tr>
			<tr>
				<td align="right">是否有效：</td>
				<td><html-el:select property="is_valid" styleId="is_valid"
					styleClass="input-txt">
					<html-el:option value="0">是</html-el:option>
					<html-el:option value="1">否</html-el:option>
				</html-el:select></td>
			</tr>
			<tr>
				<td align="right">客户喜欢：</td>
				<td><html-el:textarea property="customer_preferences"
					styleId="customer_preferences"
					style="resize:none;width:100%;height:100px;border: 1px solid #cfc5d2;" /></td>
			</tr>
			<tr>
				<td align="right">维护人：</td>
				<td><c:out value="${af.map.user_name}" /></td> 
			</tr>
			<c:if test="${not empty af.map.link_id}">
				<tr>
					<td align="right">维护时间：</td>
					<td><fmt:formatDate value="${af.map.add_date}"
						pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:if>  
		</table>
		<div class="but"><input class="websub1" value="提交" type="button"
			id="btn_submit" />&nbsp;&nbsp;<input class="websub1" value="返回"
			type="button" id="btn_his" onclick=
	history.back();
/></div>
	</html-el:form></li>
</ul>
</div>
<div class="clear"></div>

<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script src="mobiscroll.custom-2.6.2.min.js" type="text/javascript"></script>  
<script type="text/javascript">
	//<![CDATA[
	$(document).ready(
			function() {

				$("#real_name").attr("dataType", "Require").attr("msg",
						"姓名不能为空！");
				$("#customer_name").attr("dataType", "Require").attr("msg",
						"客户名称不能为空！");
				$("#r3_code").attr("dataType", "Require").attr("msg",
						"R3编码不能为空！");
				$("#dept_id").attr("dataType", "Require").attr("msg",
						"分公司名称不能为空！");

				$("#qq").attr("dataType", "QQ").attr("Require", "false").attr(
						"msg", "QQ格式不正确！");
				$("#email").attr("dataType", "Email").attr("Require", "false")
						.attr("msg", "Email格式不正确！");
				$("#tel").attr("dataType", "Mobile").attr("Require", "false")
						.attr("msg", "手机格式不正确！");
				$("#telephone").attr("dataType", "Phone").attr("Require",
						"false").attr("msg", "固定电话格式不正确！");


				 var curr = new Date().getFullYear();
			     var opt = {

			     }

			    opt.date = {preset : 'date'};
				opt.datetime = { preset : 'datetime', minDate: new Date(2012,3,10,9,22), maxDate: new Date(2014,7,30,15,44), stepMinute: 5 }; 
				opt.time = {preset : 'time'};
				opt.tree_list = {preset : 'list', labels: ['Region', 'Country', 'City']};
				opt.image_text = {preset : 'list', labels: ['Cars']};
				opt.select = {preset : 'select'};

			    $('#birthday').scroller($.extend(opt['date'], { theme: 'default', mode: 'scroller', display: 'modal', lang: 'zh', dateFormat: 'yy-mm-dd',startYear:curr-65,   
			        endYear:curr + 10 }));

			   

				$("#btn_submit").click(
						function() {

							if ($("#tel").val().length == 0
									&& $("#telephone").val().length == 0) {
								alert("固定电话和移动电话不能同时为空！");
								return;
							}

							if ($("#customer_preferences").val().length > 100) {
								alert("客户喜爱不能超过100个字！");
								return;
							}

							if (Validator.Validate(this.form, 3)) {
								$(":button").attr("disabled", "true");
								$(":reset").attr("disabled", "true");
								this.form.submit();
							}
						});

			});

	function customer_code_chg() {
		$("#r3_code").val($("#customer_code_select").val());
		var old_customer_name = $("#customer_code_select").find(
				"option:selected").text();
		var start_index = old_customer_name.indexOf("]") + 1;
		var customer_name = (old_customer_name.substring(start_index)).trim();
		$("#customer_name").val(customer_name);

	}

	function resizeFrameHeight(offset, min_height) {
		// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
		$("#mainFrame", window.parent.document).height(
				Math.max((min_height || 0), $(document).find("body").height(),
						$(document).children().height())
						+ (offset || 0));
	}
	//]]>
</script>
</body>
</html>
