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
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaDeptImp">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="dept_sn" value="${af.map.dept_sn}" />
      <html-el:hidden property="type_id" value="${af.map.type_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">操作项：</td>
          <td><c:choose>
          	<c:when test="${af.map.type_id eq 1}">初始化系统数据${af.map.dept_name}分公司部门信息</c:when>
          	<c:when test="${af.map.type_id eq 2}">初始化系统数据${af.map.dept_name}分公司角色信息</c:when>
          	<c:when test="${af.map.type_id eq 3}">初始化系统数据${af.map.dept_name}分公司门店信息</c:when>
          	<c:when test="${af.map.type_id eq 4}">初始化系统数据${af.map.dept_name}分公司用户信息</c:when>
          	<c:when test="${af.map.type_id eq 5}">初始化系统数据${af.map.dept_name}分公司直销员信息</c:when>
          </c:choose></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">分公司：</td>
          <td><span style="color: red;">${af.map.dept_name}</span></td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">数据来源：</td>
          <td><html-el:select property="his_id" styleId="his_id">
          	<html-el:option value="">-请选择-</html-el:option>
          	<c:forEach items="${entityList}" var="cur">
          		<html-el:option value="${cur.opr_his_id}">${cur.opr_title}<fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd hh:mm:ss" /></html-el:option>
          	</c:forEach>
          </html-el:select></td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input type="button" value="数据初始化" id="send" />
          	<input type="button" value="历史记录" id="his_btn" onclick="window.location.href='${ctx}/manager/admin/KonkaDeptImp.do?method=hislist&dept_id=${af.map.dept_id}&type_id=${af.map.type_id}'" />
            <input type="button" value="返回" id ="ret_btn" onclick="history.back()" />
            </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#his_id").attr("dataType", "Require").attr("msg", "请选择数据来源！");
	
	$("#send").click(function(){
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$("#send").attr("disabled", "true");
			$("#ret_btn").attr("disabled", "true");
			$("#ret_btn").attr("disabled", "true");
			this.form.submit();
		}
	 });
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
