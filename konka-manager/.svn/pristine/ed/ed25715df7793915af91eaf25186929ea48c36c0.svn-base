<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
	<div class="rtabcont2">
		<html-el:form action="/paragon/KonkaParagonShowmanre" enctype="multipart/form-data">
			<html-el:hidden property="re_id" styleId="re_id" />
			<html-el:hidden property="mod_id" styleId="mod_id" />
			<html-el:hidden property="user_id" styleId="user_id" />
			<html-el:hidden property="method" styleId="method" value="saveForEdit" />
			<html-el:hidden property="queryString" styleId="queryString" />
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
				<tr>
					<td nowrap="nowrap" class="title_item" >数据上报员：</td>
					<td  width="85%" >
						<html-el:text property="user_name" size="40" maxlength="30" styleId="user_name" disabled="true" value="${af.map.map.user_name}"/>&nbsp;
						 <!-- <input id="gsBTN" type='button' value='选择' onclick="getSalesMan(this);" class="but2" disabled="disabled"/> -->
	               		 <html-el:hidden property="user_id" styleId="user_id"/>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" >关联门店     ：</td>
					<td width="85%" >
						 <input id="gsBTN" type='button' value='匹配' onclick="getShowInfo(this);" class="but2" />
	               	 </td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label>
		            <input class="but5" type="button" name=btn_back value="返回" onclick="goBack();" />
		          </label></td>
				</tr>
			</table>
		</html-el:form>
	</div>
	<div class="rtabcont2">
	数据上报员<font style="color:red;">${af.map.map.user_name}</font>已匹配的门店信息列表展示
	</div>
	<div class="rtabcont1">
	<%@ include file="/commons/pages/messages.jsp" %>
	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td>
			    <input class="but3" type="button" name="Submit3" value="回收站" onclick="confirmDeleteAll(document.getElementById('listForm1'));" />
			</td>
		 </tr>
		</table>
	  </div>
	 <div class="rtabcont1">
			<form id="listForm1" name="listForm1" method="post" action="KonkaParagonShowmanre.do?method=deleteForShowOut">
				<input type="hidden" name="method" id="method" value="deleteForShowOut" />
				<input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
				<input type="hidden" name="user_id" id="user_id" value="${af.map.user_id}" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       	       		<tr class="tabtt1">
       	       			<td width="25" nowrap="nowrap" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
						<td nowrap="nowrap" align="center">上报员名称</td>
						<td nowrap="nowrap" align="center">门店代码</td>
						<td nowrap="nowrap" align="center">门店名称</td>
						<td nowrap="nowrap" align="center">区域</td>
						<td nowrap="nowrap" align="center">分公司</td>
						<td width="100" nowrap="nowrap" align="center">操作</td>
					</tr>
					<tbody>
					<c:forEach var="cur" items="${entityList}" varStatus="vs">
					<tr>
						<td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.re_id}" /></td>
						<td align="center" nowrap="nowrap">${cur.map.user_name}</td>
						<td align="center" nowrap="nowrap">${cur.show_shop_code}</td>
					    <td align="left">${cur.map.show_shop_name}</td>
					    <td align="center" nowrap="nowrap">
							<c:choose>
								<c:when test="${af.map.area_id eq 10}">华东</c:when>
								<c:when test="${af.map.area_id eq 20}">山东</c:when>
								<c:when test="${af.map.area_id eq 30}">东北</c:when>
								<c:when test="${af.map.area_id eq 40}">华北</c:when>
								<c:when test="${af.map.area_id eq 50}">华南</c:when>
								<c:when test="${af.map.area_id eq 60}">西南</c:when>
								<c:when test="${af.map.area_id eq 70}">华中</c:when>
								<c:when test="${af.map.area_id eq 80}">西北</c:when>
							</c:choose>
						</td>
						<td align="center" nowrap="nowrap">${cur.map.part_name}</td>
						<td align="center">
							<span style="cursor:pointer;"  class="fblue" onclick="doNeedMethod('确定删除吗?', 'KonkaParagonShowmanre.do', 'deleteForShowOut','re_id=${cur.re_id}&mod_id=${af.map.mod_id}&user_id=${af.map.user_id}&'+$('#bottomPageForm').serialize())">删除</span>
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
					</tr>
					</c:forEach>
					</tbody>
				</table>
			</form>
			<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaParagonShowmanre.do">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="40">
							<div style="text-align:right; padding-right:5px;">
							<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
							<script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "edit");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("user_id", "${af.map.user_id}");
								document.write(pager.toString());
							</script>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#user_id").attr("dataType", "Require").attr("msg", "请选择数据上报员");
	$("#show_shop_codes").attr("dataType", "Require").attr("msg", "请选择门店");
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			this.form.submit();
		}
	});
});

function getSalesMan(obj) {
	var $p = $(obj).parent();
	var returnValue = window.showModalDialog("SelectSalesMan.do?selects=" + $("#user_id").val() + "&selectype=signal&azaz=" + Math.random(), window, "dialogWidth:600px;status:no;dialogHeight:450px");
	if (returnValue != null) {
		$("#user_name", $p).val(returnValue.names);
		$("#user_id", $p).val(returnValue.ids);
	}
}

function getShowInfo(obj) {
	this.location.href = 'SelectShowInfo.do?user_id='+$("#user_id").val()+"&mod_id="+$("#mod_id").val() ;
}
function goBack(){
	this.location.href = 'KonkaParagonShowmanre.do?user_id='+$("#user_id").val()+"&mod_id=506000";
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
