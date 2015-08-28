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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/paragon/KonkaParagonEquipmentC" enctype="multipart/form-data">
      <html-el:hidden property="equipment_id" styleId="equipment_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="etype"  />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">名称：<span style="color:red">*</span></td>
          <td><html-el:text property="equipment_name" styleId="equipment_name" style="width:200px;" styleClass="webinput" maxlength="25" /></td>
        </tr>
        <c:if test="${af.map.etype eq 2}">
	        <tr>
	          <td nowrap="nowrap" class="title_item" align="right" >样机尺寸：<span style="color:red">*</span></td>
	          <td><html-el:text property="proto_size" styleId="proto_size" style="width:200px;" styleClass="webinput" maxlength="6" /></td>
	        </tr>
        </c:if>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">状态：<span style="color:red">*</span></td>
          <td>
          	<html-el:select property="status" styleId="status"  >
              <html-el:option value="1">正常</html-el:option>
              <html-el:option value="2">锁定</html-el:option>
            </html-el:select></td>
        </tr>
          
        <tr>
          <td>&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont3"></div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[

$(document).ready(function(){
	$("#equipment_name").attr("dataType", "Require").attr("msg", "请填写");
	<c:if test="${af.map.etype eq 2}">
	$("#proto_size").attr("dataType", "Double").attr("msg", "请正确填写尺寸 ");
	</c:if>
	// 提交
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
