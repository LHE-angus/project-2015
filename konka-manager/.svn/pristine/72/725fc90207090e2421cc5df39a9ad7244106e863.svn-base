<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<!--[if IE]>
<link href="${ctx}"}/styles/manager/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
</head>
<body>
<div id="navTab" class="tabsPage" style="text-align:left;">
	<div class="tabsPageHeader">
		<div class="tabsPageHeaderContent">
			<ul class="navTab-tab">
				<li class="main"><a><span><span class="home_icon">${naviString}</span></span></a></li>
			</ul>
		</div>
	</div>
	<div class="navTab-panel tabsPageContent">
		<div class="page">
			<div class="pageHeader">
				<html-el:form action="/admin/BranchAssign">
					<html-el:hidden property="method" styleId="method" value="list" />
					<html-el:hidden property="mod_id" styleId="mod_id" />
					<div class="searchBar">
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							<tr>
								<td width="250">用户名称：
									<html-el:text property="user_name" size="20" maxlength="20" styleId="user_name" />
								</td>
							</tr>
						</table>
						<div class="subBar">
							<ul>
								<li><a class="button"><span onclick="document.getElementById('af').submit()">立即检索</span></a></li>
							</ul>
						</div>
					</div>
				</html-el:form>
			</div>
			<%@ include file="/commons/pages/messages.jsp" %>
			<div class="pageContent">
				<form id="listForm" name="listForm" method="post" action="BranchAssign.do?method=delete">
					<input type="hidden" name="method" id="method" value="delete" />
					<input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
					<div class="panelBar">
						<ul class="toolBar">
							<logic-el:match name="popedom" value="+1+">
								<li><a class="add" onclick="location.href='BranchAssign.do?method=add&mod_id=${af.map.mod_id}';"><span>添加</span></a></li>
							</logic-el:match>
							<logic-el:notMatch name="popedom" value="+1+">
								<li><span style="color:#CCC;">添加</span></li>
							</logic-el:notMatch>
								<li class="line">line</li>
							<logic-el:match name="popedom" value="+4+">
								<li><a class="delete" onclick="confirmDeleteAll(document.getElementById('listForm'));"><span>删除</span></a></li>
							</logic-el:match>
							<logic-el:notMatch name="popedom" value="+4+">
								<li><span style="color:#CCC;">删除</span></li>
							</logic-el:notMatch>
						</ul>
					</div>
					<table class="list" width="100%">
						<thead>
						<tr>
							<th width="25" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
							<th nowrap="nowrap" align="center">id</th>
							<th nowrap="nowrap" align="center">分配类别 1R3网点分配 2经销网点分配</th>
							<th nowrap="nowrap" align="center">KONKA_R3_SHOP的ID</th>
							<th nowrap="nowrap" align="center">mmt_shop_id</th>
							<th nowrap="nowrap" align="center">事业部_dept_ID</th>
							<th nowrap="nowrap" align="center">分公司_Dept_ID</th>
							<th nowrap="nowrap" align="center">经营部_dept_ID</th>
							<th nowrap="nowrap" align="center">办事处_dept_ID</th>
							<th nowrap="nowrap" align="center">USER_ID</th>
							<th width="100" nowrap="nowrap">操作</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="cur" items="${entityList}" varStatus="vs">
						<tr>
							<td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.id}" /></td>
							<td align="left" nowrap="nowrap">${cur.id}</td>
							<td align="left" nowrap="nowrap">${cur.branch_type}</td>
							<td align="left" nowrap="nowrap">${cur.konka_r3_id}</td>
							<td align="left" nowrap="nowrap">${cur.mmt_shop_id}</td>
							<td align="left" nowrap="nowrap">${cur.syb_id}</td>
							<td align="left" nowrap="nowrap">${cur.fgs_id}</td>
							<td align="left" nowrap="nowrap">${cur.jyb_id}</td>
							<td align="left" nowrap="nowrap">${cur.bsc_id}</td>
							<td align="left" nowrap="nowrap">${cur.user_id}</td>
							<td align="center">
							<logic-el:match name="popedom" value="+2+">
								<span style="cursor:pointer;" onclick="confirmUpdate(null, 'BranchAssign.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>
							</logic-el:match>
							<logic-el:notMatch name="popedom" value="+2+">
								<span style="color:#CCC;">修改</span>
							</logic-el:notMatch>
							<logic-el:match name="popedom" value="+4+">
								<span style="cursor:pointer; margin-left:7px;" onclick="confirmDelete('确定删除吗?', 'BranchAssign.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span>
							</logic-el:match>
							<logic-el:notMatch name="popedom" value="+4+">
								<span style="color:#CCC; margin-left:7px;">删除</span></logic-el:notMatch>
							</td>
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
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						</c:forEach>
						</tbody>
					</table>
				</form>
				<form id="bottomPageForm" name="bottomPageForm" method="post" action="BranchAssign.do">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td height="40">
								<div style="text-align:right; padding-right:5px;">
								<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
								<script type="text/javascript">
									var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
									pager.addHiddenInputs("method", "list");
									pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
									document.write(pager.toString());
								</script>
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#user_name").blur(function() {
		$(this).val(this.value.trim());						   
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>