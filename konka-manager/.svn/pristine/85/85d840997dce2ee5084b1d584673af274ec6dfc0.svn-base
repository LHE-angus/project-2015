<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>---</title>
<link href="${ctx}/commons/styles/themes/blue/styles/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div align="center">
  <div align="left" style="width:99%;">
    <div align="left" class="nav">${naviString}</div>
    <br />
    <html-el:form action="/admin/ImportDataTypes" enctype="multipart/form-data">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_code" styleId="mod_code" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" align="left" cellpadding="0" cellspacing="1" class="datagrid">
        <tr>
          <th colspan="2" class="form_title">编辑</th>
        </tr>
		<tr>
          <td nowrap="nowrap" class="title_item">ID：</td>
          <td><html-el:text property="id" styleId="id" style="width:380px;" styleClass="webinput" /></td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item">父ID<br />
顶级为0：</td>
          <td><html-el:text property="par_id" styleId="par_id" style="width:380px;" styleClass="webinput" /></td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item">数据类别<br />
暂定：<br />
1：单客户，此种类别去R3表中查相关数据<br />
2：客户群<br />
3：分公司，此种类别去组织架构表中查相关数据<br />
4：分大区
：</td>
          <td><html-el:text property="data_type" styleId="data_type" style="width:380px;" styleClass="webinput" /></td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item">类别名称：</td>
          <td><html-el:text property="type_name" styleId="type_name" style="width:380px;" styleClass="webinput" maxlength="127" /></td>
        </tr>	
		<tr>
          <td nowrap="nowrap" class="title_item">排序值：</td>
          <td><html-el:text property="order_value" styleId="order_value" style="width:380px;" styleClass="webinput" /></td>
        </tr>	
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提 交" styleClass="websub" styleId="btn_submit" />
            <html-el:button property="" value="重 置" styleClass="websub" styleId="btn_reset" onclick="this.form.reset();" />
            <html-el:button property="" value="返 回" styleClass="websub" styleId="btn_back" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#id").attr("dataType", "Require").attr("msg", "请填写ID");
	$("#par_id").attr("dataType", "Require").attr("msg", "请填写父ID<br />
顶级为0");
	$("#data_type").attr("dataType", "Require").attr("msg", "请填写数据类别<br />
暂定：<br />
1：单客户，此种类别去R3表中查相关数据<br />
2：客户群<br />
3：分公司，此种类别去组织架构表中查相关数据<br />
4：分大区
");
	$("#type_name").attr("dataType", "Require").attr("msg", "请填写类别名称");
	$("#order_value").attr("dataType", "Require").attr("msg", "请填写排序值");
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>