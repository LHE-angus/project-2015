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
  <html-el:form action="/admin/KonkaBooths" enctype="multipart/form-data">
    <html-el:hidden property="booths_id" styleId="booths_id" value="${af.map.booths_id}" />
    <html-el:hidden property="method" value="save" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      <tr>
        <td nowrap="nowrap" class="title_item">R3网点：</td>
        <td width="85%">
        	<html-el:hidden property="r3_shop_id" styleId="r3_shop_id" />
        	<html-el:text property="r3_name" size="40" maxlength="30" styleId="r3_name" readonly="true" value="${af.map.map.r3_name}"/>
        	<input type='button' value='选择' onclick="getAgentsList();" />
        </td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">登记月份：</td>
        <td>
        	<fmt:formatDate value="${af.map.booths_month}" type="both" pattern="yyyy-MM" var="_booths_month"/>
        	<html-el:hidden property="booths_month" styleId="booths_month"/>
        	<html-el:text property="booths_month_show" size="40" maxlength="30" value="${_booths_month}" styleId="booths_month_show" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM'});" style="cursor:pointer;text-align:center;" title="点击选择日期"/>
        </td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">年累积销售额：</td>
        <td><html-el:text property="booths_sale" size="40" maxlength="30" styleId="booths_sale" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">展台延米数：</td>
        <td><html-el:text property="booths_num" size="40" maxlength="30" styleId="booths_num" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">有无直销员：</td>
        <td>
        	<html-el:select property="if_man" styleId="if_man">
		        <html-el:option value="1">有</html-el:option>
		        <html-el:option value="0">无</html-el:option>
        	</html-el:select>
        <span style="color:red" >*</span>
        </td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">直销员姓名：</td>
        <td><html-el:text property="man_name" size="40" maxlength="30" styleId="man_name" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">直销员电话：</td>
        <td><html-el:text property="man_phone" size="40" maxlength="30" styleId="man_phone" /></td>
      </tr>
      <tr>
        <td nowrap="nowrap" class="title_item">最近改造时间：</td>
        <td>
        <fmt:formatDate value="${af.map.last_rebuild}" type="both" pattern="yyyy-MM-dd" var="_last_rebuild"/>
        <html-el:text property="last_rebuild" value="${_last_rebuild}" size="40" maxlength="30" styleId="last_rebuild" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" style="cursor:pointer;text-align:center;" title="点击选择日期"/>
        </td>
      </tr>
      <tr>
       <td nowrap="nowrap" class="title_item">形象照片：</td>
       <td><input type="file" name="photos" id="photos"/></td>
      </tr>
       <tr>
        <td>&nbsp;</td>
        <td><label>
            <input class="but4" type="button" name="btn_submit" id="btn_submit" value="提交" />
            <input class="but5" type="button" name=btn_back value="返回" onclick="history.back();return false;" />
          </label></td>
      </tr>
    </table>
  </html-el:form>
 </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#r3_shop_id").attr("dataType", "Require").attr("msg", "请填写");
	$("#booths_month_show").attr("dataType", "Require").attr("msg", "请填写");
	$("#booths_sale").attr("dataType", "Number").attr("msg", "请填写数字");
	$("#booths_num").attr("dataType", "Number").attr("msg", "请填写数字");
	$("#if_man").attr("dataType", "Require").attr("msg", "请填写");
	$("#last_rebuild").attr("dataType", "Require").attr("msg", "请填写");
	
	var date = new Date();
	$("#booths_month").val(date.getFullYear()+"-"+(date.getMonth()<9?"0":"") +(date.getMonth()+1));
	
	$("#btn_submit").click(function(){
		$("#booths_month").val($("#booths_month_show").val()+"-01");
		if($("#if_man").val()==1){
			$("#man_name").attr("dataType", "Require").attr("msg", "请填写");
			$("#man_phone").attr("dataType", "Require").attr("msg", "请填写");
		}
		else{
			$("#man_name").attr("dataType", "");
			$("#man_phone").attr("dataType", "");
		}
		
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});

function getAgentsList() { 
	var returnValue = window.showModalDialog("SelectAgentsList.do?area_limit=1&no_dls=1&selectype=signal&azaz=" + Math.random(),window,"dialogWidth:930px;status:no;dialogHeight:500px");
	if (returnValue != null) {
		$("#r3_shop_id").val(returnValue.id);
		$("#r3_name").val(returnValue.name);
	};
};
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>