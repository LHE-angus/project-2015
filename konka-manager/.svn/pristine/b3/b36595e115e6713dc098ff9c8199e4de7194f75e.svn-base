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
    <html-el:form action="/paragon/KonkaParagonManinfo" enctype="multipart/form-data">
      <html-el:hidden property="seller_id" styleId="seller_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
       <tr>
          <td nowrap="nowrap" class="title_item" align="right">门店代码：<span style="color:red">*</span></td>
          <td>
	         <html-el:text property="show_shop_code" size="40" maxlength="30" styleId="show_shop_code" disabled="true"/>&nbsp;
			 <input id="gsBTN" type='button' value='选择' onclick="getShowInfo(this);" class="but2" />
	   		 <html-el:hidden property="show_shop_code" styleId="show_shop_code"/>
	   		 <html-el:hidden property="show_shop_ids" styleId="show_shop_ids" value="${af.map.show_shop_id}"/>
		</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">门店名称：<span style="color:red">*</span></td>
          <td><html-el:text property="show_shop_name" styleId="show_shop_name"  value="${af.map.show_shop_name}" style="width:150px;" styleClass="webinput" maxlength="10" disabled="true"/></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">直销员姓名：<span style="color:red">*</span></td>
          <td><html-el:text property="seller_name" styleId="seller_name" style="width:150px;" styleClass="webinput" maxlength="10" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">身份证号：<span style="color:red">*</span></td>
          <td><html-el:text property="seller_code" styleId="seller_code" style="width:200px;" styleClass="webinput" maxlength="20" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">直销员电话：<span style="color:red">*</span></td>
          <td><html-el:text property="seller_phone" styleId="seller_phone" style="width:150px;" styleClass="webinput" maxlength="25" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">银行账号：<span style="color:red">*</span></td>
          <td><html-el:text property="seller_bank_count" styleId="seller_bank_count" style="width:150px;" styleClass="webinput" maxlength="25" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">入司时间：<span style="color:red">*</span></td>
          <td>
          <fmt:formatDate value="${af.map.seller_in}" pattern="yyyy-MM-dd" var="_seller_in"/>
		  <html-el:text property="seller_in" styleId="seller_in" value="${_seller_in}" size="9" maxlength="10" readonly="true" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">数据期号：<span style="color:red">*</span></td>
          <td>
          <html-el:text property="fixdate" styleId="fixdate" size="32" maxlength="20" readonly="true" onclick="WdatePicker({dateFmt:'yyyyMM'})" style="cursor:pointer;" value="${af.map.fixdate}" />
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">//<![CDATA[

$(document).ready(function(){
	$("#seller_name").attr("dataType", "Require").attr("msg", "请填写");
	$("#seller_code").attr("dataType", "IdCard").attr("msg", "请正确填写身份证号码");
	$("#seller_phone").attr("dataType", "Require").attr("msg", "请填写");
	$("#show_shop_code").attr("dataType", "Require").attr("msg", "请选择");
	$("#seller_bank_count").attr("dataType", "Require").attr("msg", "请填写");
	$("#seller_in").attr("dataType", "Require").attr("msg", "请选择");
	$("#fixdate").attr("dataType", "Require").attr("msg", "请填写");

	// 提交
	$("#btn_submit").click(function(){
		   if(Validator.Validate(this.form, 3)){
				$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				$("#btn_back").attr("disabled", "true");
				this.form.submit();
		   }
	});

});

function getShowInfo(obj) {
	var $p = $(obj).parent();
	var returnValue = window.showModalDialog("SelectShowInfoForManinfo.do?selects=" + $("#show_shop_ids").val() + "&selectype=signal&azaz=" + Math.random(), window, "dialogWidth:940px;status:no;dialogHeight:680px");
	if (returnValue != null) {
		$("#show_shop_code", $p).val(returnValue.shop_code);
		$("#show_shop_name").val(returnValue.shop_name);
	}
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
