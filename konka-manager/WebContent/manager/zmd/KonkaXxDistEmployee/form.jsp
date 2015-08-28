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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
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
    <html-el:form action="/zmd/KonkaXxDistEmployee">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="dist_employee_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">分公司：</td>
          <td><strong class="fb">${konkaDept.dept_name}</strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span>姓名：</td>
          <td><html-el:text property="real_name" styleId="real_name" size="20" maxlength="10"></html-el:text></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span>员工身份证号码</td>
          <td><html-el:text property="idcard" styleId="idcard" size="40" maxlength="20"></html-el:text></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right">生日：</td>
          <td><html-el:text property="birthday" styleId="birthday" size="10" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" value="${birthday_format}" /> </td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span>联系电话：</td>
          <td><html-el:text property="link_phone" styleId="link_phone" size="40" maxlength="20"></html-el:text><span style="margin-left:6px;font-size:12px;">例如：0551-65361082</span></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span>联系手机：</td>
          <td><html-el:text property="mobile_phone" styleId="mobile_phone" size="40" maxlength="20"></html-el:text></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span>所在地：</td>
          <td>
          	<html-el:select property="province" styleId="province" style="width:150px;"></html-el:select>&nbsp;
	        <html-el:select property="city" styleId="city" style="width:150px;">
	        	<html-el:option value="">=请选择市=</html-el:option>
	        </html-el:select>&nbsp;
	        <html-el:select property="country" styleId="country" style="width:150px;">
	        	<html-el:option value="">=请选择区/县=</html-el:option>
	        </html-el:select>&nbsp;
	        <html-el:select property="town" styleId="town" style="width:150px;">
	        	<html-el:option value="">=请选择乡/镇=</html-el:option>
	        </html-el:select>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span>详细地址：</td>
          <td><html-el:text property="addr" styleId="addr" size="40" maxlength="20"></html-el:text></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><span style="color:red">[必填]</span>是否删除：</td>
          <td><html-el:select property="is_del" styleId="is_del" style="width:90px;">
	        	<html-el:option value="">=请选择=</html-el:option>
	        	<html-el:option value="0">未删除</html-el:option>
	        	<html-el:option value="1">已删除</html-el:option>
	        </html-el:select></td>
        </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="保存" id="send" />
            <input class="but3" type="reset"  value="重填 " />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#birthday").datepicker();
	$("#birthday").datepicker('option','yearRange','1950:2012'); //设置日期控件的年份范围
	
	$("#province").attr({"subElement": "city", "defaultText": "=请选择省/市/自治区=", "defaultValue": "", "selectedValue" : "${fn:substring(af.map.p_index, 0, 2)}${empty af.map.p_index ? af.map.province : '0000'}"});
	$("#city").attr({"subElement": "country", "defaultText": "=请选择市=", "defaultValue": "", "selectedValue" : "${fn:substring(af.map.p_index, 0, 4)}${empty af.map.p_index ? af.map.city : '00'}"});
	$("#country").attr({"subElement": "town","defaultText": "=请选择区/县=", "defaultValue": "", "selectedValue": "${fn:substring(af.map.p_index, 0, 6)}"});
	$("#town").attr({"defaultText": "=请选择乡/镇=", "defaultValue": "", "selectedValue": "${af.map.p_index}"});
	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false ,"p_index_join","${af.map.p_index_join}");
	$("#province").change();
	
	$("#real_name"   ).attr("dataType", "Require").attr("msg", "请填写姓名！");
	$("#idcard"      ).attr("dataType", "IdCard" ).attr("msg", "请正确填写身份证号码！");
	$("#link_phone"  ).attr("dataType", "Phone"  ).attr("msg", "请正确填写联系电话！");
	$("#mobile_phone").attr("dataType", "Mobile" ).attr("msg", "请正确填写联系手机！");
	$("#country"     ).attr("dataType", "Require").attr("msg", "请选择所在地！");
	$("#addr"        ).attr("dataType", "Require").attr("msg", "请填写详细地址！");
	$("#is_del"      ).attr("dataType", "Require").attr("msg", "请选择是否删除！");
	
	$("#send").click(function(){
	   if (Validator.Validate(this.form, 3)) {
			this.form.submit();
		}
	 });
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
