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
	<html-el:form action="/admin/KonkaMobileStorages">
		<html-el:hidden property="method" styleId="method" value="list" />
		<html-el:hidden property="mod_id" styleId="mod_id" />
		<div class="searchBar">
			<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      		  <tr>
          			<td width="15"></td>
					<td><strong class="fb"> &nbsp;&nbsp;仓库名称：</strong>
						<html-el:text property="storage_name" size="20" maxlength="20" styleId="storage_name" />
						 &nbsp;&nbsp;
               		 <input class="but1" type="submit" name="Submit" value="搜索" />
					</td>
				</tr>
			</table>
		</div>
	</html-el:form>
</div>
<div class="rtabcont1">
<%@ include file="/commons/pages/messages.jsp" %>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
		<td><logic-el:match name="popedom" value="+1+">
				<input style="align:left" name="button" type="button"  value="新增" class="but2" onclick="location.href='KonkaMobileStorages.do?method=add&mod_id=${af.map.mod_id}';"/>
			</logic-el:match>
			<logic-el:match name="popedom" value="+4+">
				 <input class="but3" type="submit" name="Submit3" value="回收站" onclick="confirmDeleteAll(document.getElementById('listForm'));"/>
			</logic-el:match>
		</td>
	</tr>
</table>
</div>
<div class="rtabcont1">
   <form id="listForm" name="listForm" method="post" action="KonkaMobileStorages.do?method=delete">
     <input type="hidden" name="method" id="method" value="delete" />
     <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
     <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       	<tr class="tabtt1">
				<td width="25" nowrap="nowrap" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
				<td nowrap="nowrap" align="center">仓库名称</td>
				<td nowrap="nowrap" align="center">仓库所属部门</td>
				<td width="160" nowrap="nowrap" align="center">操作</td>
			</tr>
			<tbody>
			<c:forEach var="cur" items="${entityList}" varStatus="vs">
			<tr>
				<td align="center" nowrap="nowrap">
				  <c:if test="${cur.is_del eq 0}"><input name="pks" type="checkbox" id="pks" value="${cur.storage_id}" /> </c:if>
          		  <c:if test="${cur.is_del eq 1}"><input name="pks" type="checkbox" id="pks" value="${cur.storage_id}" disabled="disabled" /></c:if></td>
				<td align="left" nowrap="nowrap">${cur.storage_name}</td>
				<td align="left" nowrap="nowrap">
				<c:if test="${cur.storage_type eq 1}">${cur.map.type_name} （经办）</c:if>
				<c:if test="${cur.storage_type eq 2}">${cur.map.dept_name} （分公司）</c:if>
				</td>
				<td align="center">
				<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaMobileStorages.do', 'view','mod_id=${af.map.mod_id}&storage_id=${cur.storage_id}&'+$('#bottomPageForm').serialize())">查看</span>
				|
				 <c:if test="${cur.is_del eq 0}">
				<logic-el:match name="popedom" value="+2+">
					<span style="cursor:pointer;"  class="fblue" onclick="confirmUpdate(null, 'KonkaMobileStorages.do','storage_id=${cur.storage_id}&' + $('#bottomPageForm').serialize())">修改</span>
				</logic-el:match></c:if>
				 <c:if test="${cur.is_del eq 1}">
					<span style="color:#CCC;"  class="fblue">修改</span>
				</c:if>
				|
				<c:if test="${cur.is_del eq 0}">
				<span style="cursor:pointer;"  class="fblue" onclick="doNeedMethod(null, 'KonkaMobileStorages.do',  'storages','storage_id=${cur.storage_id}&' + $('#bottomPageForm').serialize())">仓位</span>
				</c:if>
				<c:if test="${cur.is_del eq 1}">
				<span style="color:#CCC;"  class="fblue">仓位</span>
				</c:if>
				|
				 <c:if test="${cur.is_del eq 0}">
				<logic-el:match name="popedom" value="+4+">
					<span style="cursor:pointer;"  class="fblue" onclick="confirmDelete('确定删除吗?', 'KonkaMobileStorages.do', 'storage_id=${cur.storage_id}&' + $('#bottomPageForm').serialize())">删除</span>
				</logic-el:match></c:if>
				  <c:if test="${cur.is_del eq 1}">
					<span style="color:#CCC;"  class="fblue">删除</span>
				</c:if>
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
			</tr>
			</c:forEach>
			</tbody>
		</table>
	</form>
	<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileStorages.do">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="40">
					<div style="text-align:right; padding-right:5px;">
					<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
					<script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						pager.addHiddenInputs("method", "list");
						pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						pager.addHiddenInputs("storage_name", "${fn:escapeXml(af.map.storage_name)}");
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