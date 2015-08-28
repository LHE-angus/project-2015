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
	<html-el:form action="/admin/KonkaMobileStorages" enctype="post">
		<html-el:hidden property="storage_id" styleId="storage_id" />
		<html-el:hidden property="mod_id" styleId="mod_id" />
		<html-el:hidden property="method" styleId="method" value="saveForEditStorages" />
		<html-el:hidden property="queryString" styleId="queryString" />
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
			<tr>
				<td width="12%" nowrap="nowrap" class="title_item" align="right"><strong>仓位名称：</strong></td>
				<td width="88%" align="left"><html-el:text property="storage_name" size="40" maxlength="30" styleId="storage_name" /></td>
			</tr>
			<tr>
				<td width="12%" nowrap="nowrap" class="title_item" align="right"><strong>所属仓库：</strong></td>
				<td width="88%" align="left">
				  <html-el:select property="par_id" styleId="par_id" style="width:130px;">
                  <html-el:option value="">请选择...</html-el:option>
                  <c:forEach var="cur" items="${entityList}">
                    <html-el:option value="${cur.storage_id}">${cur.storage_name}</html-el:option>
                  </c:forEach>
                </html-el:select></td>
			</tr>
		<tr>
			<td>&nbsp;</td>
			<td><label>
	            <input class="but4" type="button" name="Submit4" id="send" value="提交" />
	            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
       		 	</label></td>
		</tr>
	</table>
</html-el:form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#storage_name").attr("dataType", "Require").attr("msg", "请填写仓位名称");
	$("#storage_areacom").attr("dataType", "Require").attr("msg", "请选择所属仓库");
	//$("#storage_com").attr("dataType", "Require").attr("msg", "请填写经办");
	
	$("#send").click(function(){
		var isSubmit = Validator.Validate(this.form, 2);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
