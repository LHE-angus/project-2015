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
    <html-el:form action="/admin/DocIssue" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td class="title_item" width="15%">新闻标题：</td>
          <td width="85%"><html-el:text property="title" styleId="title" maxlength="100" style="width:300px;"  />&nbsp;<span style="color:red">*</span></td>
        </tr>
        <tr>
          <td class="title_item">内容：</td>
          <td><html-el:textarea property="content" styleId="content" cols="60" rows="6" />
          	<div id="info_chat_content_1"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><label>
            <input class="but4" type="button" name="Submit4" id="btn_submit" value="提交" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
            </label></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#content").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content_1").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_chat_content_1").slideUp("normal");
	});
   
	$("#title"	).attr("dataType", "Require").attr("msg", "通知标题必须填写！");
	$("#content").attr("dataType", "Require").attr("msg", "通知内容必须填写！");
	
	// 提交
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 2)){
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
