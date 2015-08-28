<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
  	<html-el:form action="/admin/KonkaPlanRatio" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save_excel" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="this_year" value="${af.map.this_year}" />
      <html-el:hidden property="dept_type" value="${af.map.dept_type}" />
      <html-el:hidden property="queryString" />
       <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      	<tr>
      		<td>选择Excel文件：</td>
      		<td><input type="file" name="excel" id="excel" contenteditable="false"  /></td>
      		<td><input class="but_excel" type="button" name="btn_submit" id="btn_submit" value="导入" /></td>
      		<td><a style="cursor:pointer;color:blue;" href="${ctx}/files/upload/crm_customer_tpl.xls">Excel模板下载</a></td>
      	</tr>
      	<tr>
      		<td colspan="4"><img src="${ctx }/manager/admin/CrmPriceHeader/TPL.jpg" alt="sry,load fail" border="1"></img></td>
      	</tr>
      	<tr>
      	</tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script> 
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	var f = document.forms["af"];
	String.prototype.isPath=function() { return /[^\n\r\t]*\.[x|X][l|L][s|S]/i.test(this); };
	$("#btn_submit").click(function(){
		//var path = f.excel.value;
		var path = $("#excel").val();
	    if (path == "" || !path.isPath()) {
	        alert("请选择一个有效Excel文件!");
	        return false;
	    }
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "导入中").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});	

})
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>