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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
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
    <html-el:form action="/admin/PeShopCategory">
      <html-el:hidden property="category_id" value="${af.map.category_id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="category_pid" value="${af.map.category_pid}" />
      <html-el:hidden property="returnUrl" />
      <html-el:hidden property="queryString" />
       <c:if test="${af.map.category_pid ne 0}">
      <html-el:hidden property="dept_id" value="${af.map.dept_id}" />
       </c:if>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item">网点类别名称：</td>
          <td><html-el:text property="category_name" styleId="category_name" style="width:400px;" size="30" maxlength="30"/>
            <html-el:text property="hidden" style="display:none"/> <div class="note"> 网点类别名称最长为30个字，超出部分不被保存</div>
           <span style="color:red">*</span></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item">网点类别描述：</td>
          <td><html-el:textarea property="category_desc" styleId="category_desc" cols="77" rows="5"/>
            <span class="note"> 最多可输入150个字</span></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><label>
            <input class="but4" type="button" name="Submit4" value="提交" id="send" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
            </label>
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
	$("#category_name" ).attr("dataType" , "Require").attr("msg" , "请填写网点类别名称！");
	$("#dept_id" ).attr("dataType" , "Require").attr("msg" , "请选择事业部！");
	$("#category_name").focus(setLength).attr("len", 30);
	$("#category_desc").focus(setLength).attr("len", 150);
	 
	 $("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 2);
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
		});
});

function setLength(){
	$(this).keypress(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	}).keyup(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	}).blur(function (){
		if(this.value.length > this.len){
			this.value = this.value.substring(0, this.len);
		}
	});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
