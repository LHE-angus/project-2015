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
    <html-el:form action="/admin/KonkaPdModelPrices.do" enctype="multipart/form-data">
      <html-el:hidden property="method" value="saveBatch"/>
       <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
       <fieldset style="padding:10px;margin:10px;">
      <legend>批量现款价</legend>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <th width="15%" align="right" class="title_item">选择Excel文件：</th>
          <td width=""><input type="file" name="excel" id="excel" onkeydown="return false" onpaste="return false"/></td>
        </tr>
        <tr>
          <td align="center" colspan="2"><input type="button" name="btn_submit" id="btn_submit" class="but4" value="导入"/>
            &nbsp;
            <input type="button" name="btn_back" id="btn_back" class="but5" value="返回" onclick="history.back();"/></td>
        </tr>
      </table>
     </fieldset>
 <a style="cursor:pointer;color:blue;" href="${ctx}/files/upload/konka_pd_model_prices_import_help.doc">现款价导入说明下载</a><br />
 <a style="cursor:pointer;color:blue;" href="${ctx}/files/upload/konka_pd_model_prices_import_template.xls">现款价导入Excel模板下载</a>

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
