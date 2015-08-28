<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<c:set var="naviString" value="公告管理" />
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${naviString}</title>
<!--[if IE]>
<link href="${ctx}/styles/manager/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<style type="text/css">
.title_item {
	text-align: right;
}
</style>
</head>
<body>
<div id="navTab" class="tabsPage" style="text-align:left;">
  <div class="tabsPageHeader">
    <div class="tabsPageHeaderContent"> 
      <!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
      <ul class="navTab-tab">
        <li class="main"><a href="javascript:void(0)"><span class="home_icon">${naviString}</span></a></li>
      </ul>
    </div>
  </div>
  <div class="navTab-panel tabsPageContent" style="width:100%;">
    <div class="page">
      <div class="pageContent">
    <html-el:form action="/manager/NewsInfo.do" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="info_state" value="1"/>
      <html-el:hidden property="id" />
      <html-el:hidden property="pub_user_id" />
      <br />
      <%@ include file="/commons/pages/messages.jsp" %>
      <table width="98%" align="center" class="list">
        <tr>
          <td width="15%" class="title_item"><span style="color: #F00;">*</span>标题：</td>
          <td width="85%"><html-el:text property="title" styleId="title" style="width:600px" /></td>
        </tr>
        <tr>
          <td class="title_item">内容：</td>
          <td><FCK:editor instanceName="content" width="660px" height="400px">
              <jsp:attribute name="value">${af.map.content}</jsp:attribute>
            </FCK:editor></td>
        </tr>
        <tr  style="display: none;">
          <td class="title_item">发布时间：</td>
          <td><fmt:formatDate value="${af.map.add_datetime}" pattern="yyyy-MM-dd" var="_add_datetime" />
            <html-el:text property="add_datetime" size="10" maxlength="10" readonly="true" onclick="new Calendar(1900, 2020, 0).show(this);" value="${_add_datetime}" />
          </td>
        </tr>
        <tr >
          <td class="title_item">过期时间：</td>
          <td><fmt:formatDate value="${af.map.invalid_date}" pattern="yyyy-MM-dd" var="_invalid_date" />
            <html-el:text property="invalid_date" styleId="invalid_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(1900, 2020, 0).show(this);" value="${_invalid_date}" />
          </td>
        </tr>
        <tr>
          <td class="title_item">&nbsp;</td>
          <td><html-el:submit property="btn_submit" styleId="btn_submit" value=" 保存 " />
            <html-el:button property="btn_reset" styleId="btn_reset" value=" 重填 "/>
            <html-el:button property="btn_back" styleId="btn_back" value=" 返回 " onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
   $("title").attr("datatype","Require").attr("msg","标题必须填写");

	$("#btn_reset").click(function(){
      $("#title").val("");
      $("#invalid_date").val("");
      var oEditor = FCKeditorAPI.GetInstance('content');  
      oEditor.SetHTML(""); 
	})
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});

})
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
