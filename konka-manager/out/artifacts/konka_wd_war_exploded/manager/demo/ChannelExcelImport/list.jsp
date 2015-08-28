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
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      <html-el:form action="/admin/ChannelExcelImport.do" enctype="multipart/form-data">
        <html-el:hidden property="method" value="saveBatch"/>
        <html-el:hidden property="type" value="${af.map.type}"/>
        <tr>
          <td width="120">选择Excel文件：</td>
          <td width=""><input type="file" name="excel" id="excel" onKeyDown="return false();" onpaste="return false();"/></td>
          <td width=""><input type="button" name="btn_submit" id="btn_submit" class="but4" value="导入"/></td>
          <td width=""><a style="cursor:pointer;color:blue;" href="${ctx}/files/upload/shop_import_help.doc">导入说明</a> <a style="cursor:pointer;color:blue;" href="${ctx}/files/upload/shop_import_template.xls">Excel模板下载 </a></td>
        </tr>
      </html-el:form>
    </table>
    <p>&nbsp;</p>
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr>
        <td width="40" align="center" nowrap="nowrap">1</td>
        <td nowrap="nowrap">20111105 09:10 管理员 Excel表总记录2345条，新增234条，更新2111条</td>
      </tr>
      <tr>
        <td align="center" nowrap="nowrap">2</td>
        <td nowrap="nowrap">20111104 09:13 管理员 Excel表总记录2145条，新增223条，更新1922条</td>
      </tr>
      <tr>
        <td align="center" nowrap="nowrap">3</td>
        <td nowrap="nowrap">20111103 09:20 管理员 Excel表总记录2045条，新增204条，更新1841条</td>
      </tr>
      <tr>
        <td align="center" nowrap="nowrap">&nbsp;</td>
        <td nowrap="nowrap"></td>
      </tr>
      <tr>
        <td align="center" nowrap="nowrap">&nbsp;</td>
        <td nowrap="nowrap"></td>
      </tr>
      <tr>
        <td align="center" nowrap="nowrap">&nbsp;</td>
        <td nowrap="nowrap"></td>
      </tr>
      <tr>
        <td align="center" nowrap="nowrap">&nbsp;</td>
        <td nowrap="nowrap"></td>
      </tr>
      <tr>
        <td align="center" nowrap="nowrap">&nbsp;</td>
        <td nowrap="nowrap"></td>
      </tr>
      <tr>
        <td align="center" nowrap="nowrap">&nbsp;</td>
        <td nowrap="nowrap"></td>
      </tr>
      <tr>
        <td align="center" nowrap="nowrap">&nbsp;</td>
        <td nowrap="nowrap"></td>
      </tr>
      <tr>
        <td align="center" nowrap="nowrap">&nbsp;</td>
        <td nowrap="nowrap"></td>
      </tr>
    </table>
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
		var path = f.excel.value;
	    if (path == "" || !path.isPath()) {
	        alert("请选择一个有效Excel文件!");
	        return false;
	    }
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});	

})
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
