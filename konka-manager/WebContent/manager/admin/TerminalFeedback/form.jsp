<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>意见反馈管理</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：意见反馈管理</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/TerminalFeedback" method="post">
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">反馈类别：</td>
          <td width="88%" align="left"><html-el:select property="message_type" styleId="message_type">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:optionsCollection name="mobileCategoryList" value="ID"  label="name"/>
            </html-el:select></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">内容：</td>
          <td width="88%" align="left"><textarea name="content" id="content" rows="12" cols="70"></textarea></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><label>
              <input class="but4" type="button" name="Submit4" id="btn_submit" value="提交" />
              <input class="but5" type="button" name="Submit5" id="btn_back"value="返回" onclick="history.back();return false;" />
            </label></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#message_type").attr("dataType" , "Require").attr("msg" , "请选择反馈类别.");
	$("#content").attr("datatype","LimitB").attr("max","500").attr("min","1").attr("msg","反馈内容必须在1-500字范围内.");
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
			 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	         $("#btn_back").attr("disabled", "true");
			 this.form.submit();
		}
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
