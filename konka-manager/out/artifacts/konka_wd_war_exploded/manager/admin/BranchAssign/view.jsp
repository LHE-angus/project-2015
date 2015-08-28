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
					<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list">
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">id：</td>
							<td width="88%" align="left"><c:out value="${af.map.id}" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">分配类别 1R3网点分配 2经销网点分配：</td>
							<td width="88%" align="left"><c:out value="${af.map.branch_type}" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">KONKA_R3_SHOP的ID：</td>
							<td width="88%" align="left"><c:out value="${af.map.konka_r3_id}" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">mmt_shop_id：</td>
							<td width="88%" align="left"><c:out value="${af.map.mmt_shop_id}" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">事业部_dept_ID：</td>
							<td width="88%" align="left"><c:out value="${af.map.syb_id}" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">分公司_Dept_ID：</td>
							<td width="88%" align="left"><c:out value="${af.map.fgs_id}" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">经营部_dept_ID：</td>
							<td width="88%" align="left"><c:out value="${af.map.jyb_id}" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">办事处_dept_ID：</td>
							<td width="88%" align="left"><c:out value="${af.map.bsc_id}" /></td>
						</tr>
						<tr>
							<td width="12%" nowrap="nowrap" class="title_item" align="right">USER_ID：</td>
							<td width="88%" align="left"><c:out value="${af.map.user_id}" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
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
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
