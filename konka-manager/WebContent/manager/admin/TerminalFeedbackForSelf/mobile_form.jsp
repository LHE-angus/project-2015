<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>提交反馈</title>
<style>
body { font-family: 宋体; font-size:12px;}
</style>
</head>
<body>
<html-el:form action="/admin/TerminalFeedbackForSelf" method="post">
  <html-el:hidden property="mod_id" styleId="mod_id" />
  <html-el:hidden property="method" styleId="method" value="saveonmobile" />
  <html-el:hidden property="queryString" styleId="queryString" />
  反馈类别：
  <html-el:select property="message_type" styleId="message_type">
    <html-el:option value="">请选择...</html-el:option>
    <html-el:optionsCollection name="mobileCategoryList" value="id"  label="name"/>
  </html-el:select>
  <br />
  <textarea name="content" id="content" rows="12" style="width:90%" placeholder="请输入不超过500个中文字符的反馈内容..."></textarea>
  <input class="but4" type="button" name="Submit4" id="btn_submit" value="提交" />
  <input class="but5" type="button" name="Submit5" id="btn_back"value="返回" onClick="history.back();return false;" />
</html-el:form>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#message_type").attr("dataType" , "Require").attr("msg" , "请选择反馈类别.");
	$("#content").attr("datatype","LimitB").attr("max","500").attr("min","1").attr("msg","反馈内容必须在1-500字范围内.");
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
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
