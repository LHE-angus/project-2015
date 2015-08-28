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
<body style="font-family:Microsoft Yahei,'宋体';">
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
<div class="rtabcont2">
	<html-el:form action="/admin/KonkaMobilePdBrand" enctype="multipart/form-data">
      <html-el:hidden property="brand_id" value="${af.map.brand_id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
	  	  <td align="center" colspan="4" style="font-weight:900;">品牌添加</td>
	  	</tr>
      	<tr>
          <td width="12%" nowrap="nowrap" class="title_item"><span style="color:red">[必填]</span>品牌名称：</td>
          <td width="88%">
          	<c:if test="${empty af.map.brand_id}"><html-el:text property="brand_name" styleId="brand_name" size="20" maxlength="60" /></c:if>
          	<c:if test="${not empty af.map.brand_id}">${af.map.brand_name}<html-el:hidden property="brand_name" styleId="brand_name" /></c:if>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">描述：</td>
          <td>
           	<html-el:textarea property="type_desc" styleId="type_desc" cols="60" rows="4"/>
           	<div id="info_chat_content" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/manager/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">排序值：</td>
          <td><html-el:text property="order_value" styleId="order_value" size="4" maxlength="4"/></td>
        </tr>
        
        <tr>
            <td colspan="4" height="40"  align="center">
            	<input class="but4" type="button" name="Submit4" value="提交 " id="btn_submit" />
            	<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" />
            </td>
        </tr>
      </table>
    </html-el:form>
</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	$("#brand_name").attr("dataType","Require").attr("msg","品牌名称不能为空！");
	$("#order_value").attr("dataType","Integer").attr("msg","排序值为整数！").attr("require", "false");
	$("#type_desc").textbox({
		maxLength: 60,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/manager/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_chat_content").slideUp("normal");
	});
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 1)){
			if(confirm("确定要提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});
});
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>