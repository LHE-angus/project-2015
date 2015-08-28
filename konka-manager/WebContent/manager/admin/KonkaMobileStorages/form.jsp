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
		<html-el:hidden property="method" styleId="method" value="save" />
		<html-el:hidden property="queryString" styleId="queryString" />
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
			<tr>
				<td width="12%" nowrap="nowrap" class="title_item" align="right"><strong>仓库名称：</strong></td>
				<td width="88%" align="left"><html-el:text property="storage_name" size="40" maxlength="30" styleId="storage_name" /></td>
			</tr>
			<tr>
				<td width="12%" nowrap="nowrap" class="title_item" align="right"><strong>仓库分类：</strong></td>
				<td width="88%" align="left">
				 <html-el:select property="storage_type" styleId="storage_type" style="width:130px;">
                  <html-el:option value="">请选择...</html-el:option>
                  <html-el:option value="1">经办仓库</html-el:option>
                  <html-el:option value="2">分公司仓库</html-el:option>
                </html-el:select></td>
			</tr>
			<tr>
				<td width="12%" nowrap="nowrap" class="title_item" align="right"><strong>仓库所属部门：</strong></td>
				<td width="88%" align="left">
				  <html-el:select property="storage_par_name" styleId="storage_par_name" style="width:130px;">
                  <html-el:option value="">请选择...</html-el:option>
                </html-el:select></td>
			</tr>
			<!-- <tr>
				<td width="13%" nowrap="nowrap" class="title_item">选择仓位：<font color="red"> * </font></td>
				<td width="38%">
				<html-el:hidden property="storage_id" styleId="storage_id" />
				<html-el:text property="storage_name" styleId="storage_name" size="30" maxlength="20"/>
				<img id="select_storage" src="${ctx}/images/search.gif" style='margin: 0 0 -3px 0; cursor: pointer;' alt='选择仓位'/> </td>
				</tr> -->
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
	$("#storage_name").attr("dataType", "Require").attr("msg", "请填写仓库名称");
	$("#storage_par_name").attr("dataType", "Require").attr("msg", "请填写所属部门");
	$("#storage_type").attr("dataType", "Require").attr("msg", "请选择分类");
	
	
	$("#storage_type").change( function() {
		$("#storage_par_name").empty();
	   	$.ajax({
			type: "POST",
			cache: false,
			url: "${ctx}/manager/admin/CsAjax.do",
			data: "method=getStorageAreacom&storage_type=" + $("#storage_type").val(),
			dataType: "json",
			error: function(request, settings){},
			success: function(data) {
				if (data.length >= 1) {
					var opt = new Option( "请选择...",""); 
					$("#storage_par_name").get(0).options.add(opt);
					
					for(var i = 0; i < data.length - 1; i++) {
						var opt = new Option( data[i].name,data[i].id); 
						$("#storage_par_name").get(0).options.add(opt);
					}
					<c:if test="${not empty af.map.storage_par_name}">$("#storage_par_name").val("${af.map.storage_par_name}");$("#storage_par_name").change();</c:if>
				}
			}
		});
	   	
	});
	<c:if test="${not empty af.map.storage_type }">
	$("#storage_type").val("${af.map.storage_type}");
	$("#storage_type").change();
	</c:if>
	
	$("#send").click(function(){
		var isSubmit = Validator.Validate(this.form, 2);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
});
	//$("#select_storage").click(function() {
	//var returnValue = window.showModalDialog("selectStorages.do?storage_type=2&azaz=" + Math.random(),window,"dialogWidth:550px;status:no;dialogHeight:530px");
	//if (returnValue != null) {
	//$("#storage_name").val(returnValue.storagename);
	//$("#storage_id").val(returnValue.storageid);
	//};
	//});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
