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
				<html-el:form action="/admin/KonkaMobileReportHistory" enctype="multipart/form-data">
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
							<td width="12%" nowrap="nowrap" class="title_item" align="right">1.销售数据上报
2.退货
3.终端
4.竞品：</td>
							<td width="88%" align="left"><html-el:text property="type" size="40" maxlength="30" styleId="type" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">content：</td>
							<td width="88%" align="left"><html-el:text property="content" size="40" maxlength="30" styleId="content" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">report_man：</td>
							<td width="88%" align="left"><html-el:text property="report_man" size="40" maxlength="30" styleId="report_man" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">report_time：</td>
							<td width="88%" align="left"><html-el:text property="report_time" size="40" maxlength="30" styleId="report_time" /></td>
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
	$("#type").attr("dataType", "Require").attr("msg", "请填写");
	$("#content").attr("dataType", "Require").attr("msg", "请填写");
	$("#report_man").attr("dataType", "Require").attr("msg", "请填写");
	$("#report_time").attr("dataType", "Require").attr("msg", "请填写");
	
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
