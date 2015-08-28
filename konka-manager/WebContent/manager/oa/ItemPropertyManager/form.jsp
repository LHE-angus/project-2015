<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<c:set var="naviString" value="事项属性维护" />
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
        <html-el:form action="/manager/ItemPropertyManager.do" enctype="multipart/form-data">
          <html-el:hidden property="queryString" styleId="queryString" />
          <html-el:hidden property="method" value="save" />
          <html-el:hidden property="mod_id" />
          <html-el:hidden property="p_id" />
          <br />
          <%@ include file="/commons/pages/messages.jsp" %>
          <table width="100%" align="center" class="list">
            <tr>
              <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color: #F00;">*</span>属性名称：</td>
              <td width="85%">
                <html-el:text property="p_name" styleId="p_name" maxlength="50" size="20px" styleClass="p_name" />
                <span style="color:#f00;display:none;" id="p_name_exist">该属性名称已存在，请重新输入</span></td>
            </tr>
            <tr>
              <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color: #F00;">*</span>属性类型：</td>
              <td>
                <html-el:select property="p_type" styleId="p_type" style="width:80px" styleClass="p_name">
                  <html-el:option value="0">属性1</html-el:option>
                  <html-el:option value="1">属性2</html-el:option>
                </html-el:select>
              </td>
            </tr>
            <tr>
              <td width="15%" nowrap="nowrap" class="title_item" align="right">排序值：</td>
              <td width="85%">
                <html-el:text property="order_value" styleId="order_value" maxlength="5" size="7"/>
              </td>
            </tr>
            <tr>
              <td class="title_item">&nbsp;</td>
              <td>
                <html-el:button property="btn_submit" styleId="btn_submit" value=" 保存 " />
                <html-el:button property="btn_back" styleId="btn_back" value=" 返回 " onclick="history.back();" />
              </td>
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
   $("#p_name").attr("datatype","Require").attr("msg","属性名称必须填写");
   $("#order_value").attr("datatype","Range").attr("min","-1").attr("max","10000").attr("msg","排序值必须在0~9999之间").focus(setOnlyNum);
   $("#p_name").focus(function(){
	   $("#p_name_exist").hide();
   });
   $(".p_name").change(function(){
		var p_name = $("#p_name").val();
		var p_type = $("#p_type").val();
		if(p_name == ''){
			$("#p_name_exist").hide();
			$("#btn_submit").attr("disabled", "");
			return false;
		} 
		$.ajax({
			type: "POST",
			url: "ItemPropertyManager.do",
			data: "method=validateProperty&p_name=" + p_name+"&p_type="+p_type,
			dataType: "json",
			error: function(request, settings) {
				alert("检查属性名称失败，请稍候再次尝试。");
				$("#user_name_exist_error").show();
				$(this).css("background-color", "#ddcc00");
				$(this).focus();
			},
			success: function(oper) {
				if(oper.result == 0 || (oper.result == 1 && "edit" == '${af.map.method}')){
					$("#p_name_exist").hide();
					$("#btn_submit").attr("disabled", "");
				}else{
					$("#p_name_exist").show();
					$("#btn_submit").attr("disabled", "true");
				}
			}
		});
   });

   var f = document.forms[0];
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(f, 3);
		if (isSubmit){
			$(":submit").attr("value", "正在提交...").attr("disabled", "true");
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			f.submit();
		}
	});	
});
function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = 0;
	});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
