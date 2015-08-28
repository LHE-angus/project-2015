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
<html-el:form action="/admin/JoinInfoManager" enctype="multipart/form-data">
<html-el:hidden property="id" styleId="id" />
<html-el:hidden property="mod_id" styleId="mod_id" />
<html-el:hidden property="method" styleId="method" value="save" />
<html-el:hidden property="queryString" styleId="queryString" />
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
<tr>
	<td nowrap="nowrap" class="title_item" align="right" width="147">标题：<span style="color:red">*</span></td>
	<td><html-el:text property="title" styleId="title" style="width:380px;" styleClass="webinput" maxlength="60" />
	</td>
</tr> 
 
<tr>
	<td nowrap="nowrap" class="title_item" align="right" >内容：</td>
	<td><FCK:editor instanceName="content">
	<jsp:attribute name="value">${af.map.content}</jsp:attribute>
	</FCK:editor>
	<div class="note">1、此处上传的图片不会自动缩放，请用相关做图软件缩放成您想要的大小；</div>
	<div class="note">2、点击最后一排倒数第三个按钮可实现全屏编辑。
	</div></td>
</tr>
<tr>
	<td nowrap="nowrap" class="title_item" align="right">发布方式：<span style="color:red">*</span></td>
	<td>
		<html-el:select property="public_type" styleId="public_type">
			<html-el:option value="0">所有网点</html-el:option>
			<html-el:option value="1">选择对象发布</html-el:option>
		</html-el:select>
	</td>
</tr>
<tr id="public_type_1" <c:if test="${empty af.map.public_type || af.map.public_type eq 0}">style="display: none;"</c:if>>
<td nowrap="nowrap" class="title_item" align="right">按地区发布：</td>
<td align="left">
   <select name="province" id="province" style="width:164px;">
      <option value="">-请选择省/直辖市/自治区-</option>
	</select>
	<select name="city" id="city" style="width:164px;">
	   <option value="">-请选择市-</option>
	</select>
	<select name="country" id="country" style="width:164px;">
	   <option value="">-请选择县-</option>
	</select>  &nbsp;注：目前仅支持县级范围</td>
</tr> 
<tr id="public_type_2" <c:if test="${empty af.map.public_type || af.map.public_type eq 0}">style="display: none;"</c:if>>
<td nowrap="nowrap" class="title_item" align="right">按网点类别发布：</td>
<td><html-el:select property="shop_category_id" styleId="shop_category_id">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:optionsCollection name="peShopCategoryList" label="category_name" value="category_id" />
            </html-el:select>
        </td>
</tr>
<!--
<tr id="public_type_3" style="display: none;">
<td nowrap="nowrap" class="title_item" align="right">选择网点：</td>
<td><html-el:select property="shop_id" styleId="shop_id">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:optionsCollection name="peShopCategoryList" label="category_name" value="category_id" />
            </html-el:select>
            </td>
</tr>
-->
<tr>
<td>&nbsp;</td>
<td>
<input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" />
<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" />
</td>
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
	$("#title").attr("dataType", "Require").attr("msg", "请填写标题");

	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}", "datatype": "Require", "msg": "请选择省名称"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});

	$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	$("#province").change();
	

	// 发布方式
	$("#public_type").change(function(){
		var index = this.value;
		if(index == '0'){
			$('#public_type_1').css("display","none");
			$('#public_type_2').css("display","none");
			$('#public_type_3').css("display","none");
		}else if(index == '1'){
			$('#public_type_1').css("display","block");
			$('#public_type_2').css("display","block");
			$('#public_type_3').css("display","block");
		}
	});

	// 提交
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

