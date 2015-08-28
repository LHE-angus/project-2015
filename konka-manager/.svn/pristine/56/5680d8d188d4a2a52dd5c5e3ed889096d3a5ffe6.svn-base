<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--[if IE]>
<link href="${ctx}/styles/manager/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
</head>
<body>
<div id="navTab" class="tabsPage" style="text-align:left;">
	<div class="tabsPageHeader">
		<div class="tabsPageHeaderContent">
			<ul class="navTab-tab">
				<li class="main"><a><span><span class="home_icon"></span>${naviString}</span></a></li>
			</ul>
		</div>
	</div>
	<div class="navTab-panel tabsPageContent">
		<div class="page">
			<div class="pageContent">
				<div style="height:10px;"></div>
				<html-el:form action="/admin/BranchAssign" enctype="multipart/form-data">
					<html-el:hidden property="id" styleId="id" />
					<html-el:hidden property="mod_id" styleId="mod_id" />
					<html-el:hidden property="method" styleId="method" value="save" />
					<html-el:hidden property="queryString" styleId="queryString" />
					<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list">
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">id：</td>
							<td width="88%" align="left"><html-el:text property="id" size="40" maxlength="30" styleId="id" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">分配类别 1R3网点分配 2经销网点分配：</td>
							<td width="88%" align="left"><html-el:text property="branch_type" size="40" maxlength="30" styleId="branch_type" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">KONKA_R3_SHOP的ID：</td>
							<td width="88%" align="left"><html-el:text property="konka_r3_id" size="40" maxlength="30" styleId="konka_r3_id" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">mmt_shop_id：</td>
							<td width="88%" align="left"><html-el:text property="mmt_shop_id" size="40" maxlength="30" styleId="mmt_shop_id" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">事业部_dept_ID：</td>
							<td width="88%" align="left"><html-el:text property="syb_id" size="40" maxlength="30" styleId="syb_id" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">分公司_Dept_ID：</td>
							<td width="88%" align="left"><html-el:text property="fgs_id" size="40" maxlength="30" styleId="fgs_id" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">经营部_dept_ID：</td>
							<td width="88%" align="left"><html-el:text property="jyb_id" size="40" maxlength="30" styleId="jyb_id" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">办事处_dept_ID：</td>
							<td width="88%" align="left"><html-el:text property="bsc_id" size="40" maxlength="30" styleId="bsc_id" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">USER_ID：</td>
							<td width="88%" align="left"><html-el:text property="user_id" size="40" maxlength="30" styleId="user_id" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<html-el:button property="" value="提 交" styleClass="websub" styleId="btn_submit" />
								<html-el:button property="" value="重 置" styleClass="websub" styleId="btn_reset" onclick="this.form.reset();" />
								<html-el:button property="" value="返 回" styleClass="websub" styleId="btn_back" onclick="history.back();return false;" />
							</td>
						</tr>
					</table>
				</html-el:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#id").attr("dataType", "Require").attr("msg", "请填写");
	$("#branch_type").attr("dataType", "Require").attr("msg", "请填写");
	$("#konka_r3_id").attr("dataType", "Require").attr("msg", "请填写");
	$("#mmt_shop_id").attr("dataType", "Require").attr("msg", "请填写");
	$("#syb_id").attr("dataType", "Require").attr("msg", "请填写");
	$("#fgs_id").attr("dataType", "Require").attr("msg", "请填写");
	$("#jyb_id").attr("dataType", "Require").attr("msg", "请填写");
	$("#bsc_id").attr("dataType", "Require").attr("msg", "请填写");
	$("#user_id").attr("dataType", "Require").attr("msg", "请填写");
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
