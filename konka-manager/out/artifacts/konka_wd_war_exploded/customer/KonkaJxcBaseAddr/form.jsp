<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/manager/KonkaJxcBaseAddr.do">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" styleId="id"/>
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="8">收货信息</th>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>收货人：</td>
          <td colspan="7">
              <html-el:text property="user_name" styleId="user_name" maxlength="20" />
          </td>
        </tr>
        <tr>
          <td class="title_item">固定电话：</td>
          <td colspan="3"><html-el:text property="user_tel" styleId="user_tel" maxlength="20" /></td>
          <td class="title_item"><font color="red">*</font>手机号码：</td>
          <td colspan="3"><html-el:text property="user_phone" styleId="user_phone" maxlength="20" /></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>邮政编码：</td>
          <td colspan="7"><html-el:text property="user_zip" styleId="user_zip" maxlength="10" style="width:160px;"/></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>所属区域：</td>
          <td colspan="7">
           <select name="province" id="province"  class="input_txt" style="width:160px;" >
                <option value="">-请选择省/直辖市/自治区-</option>
              </select>
              <select name="city" id="city"  class="input_txt" style="width:100px;">
                <option value="">-请选择市-</option>
              </select>
              <select name="country" id="country"  class="input_txt" style="width:100px;">
                <option value="">-请选择县-</option>
              </select>
              <select name="town" id="town"  class="input_txt" style="width:100px;">
                <option value="">-请选择乡/镇-</option>
              </select>
          </td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>详细地址：</td>
          <td colspan="7">
          <html-el:text property="user_addr"  styleId="user_addr" maxlength="100"  style="width:400px;"/>
          </td>
        </tr>
        <tr>
          <td class="title_item">备注：</td>
          <td colspan="7">
          <html-el:textarea property="user_remark"  styleId="user_remark" rows="8" cols="80" style="resize:none"/>
          </td>
        </tr>
        <tr>
          <td colspan="8" align="center"><html-el:button property="" value="保 存" styleClass="bgButtonSave" styleId="btn_submit" />
            &nbsp;
            <html-el:reset property="" value="重填" styleClass="bgButtonBack" styleId="btn_reset" />
            &nbsp;
            <html-el:button property="" value="返 回" styleClass="bgButtonBack" styleId="btn_back" onclick="history.back();return false;"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	// 区域
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${af.map.town}"});
	
	$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","");
	$("#province").change();
	
	
	$("#user_name").attr("dataType", "Require").attr("msg", "收货人姓名必须填写！"); 
	$("select[name='province']").attr("dataType", "Require").attr("msg", "请选择省！"); 
	$("select[name='city']").attr("dataType", "Require").attr("msg", "请选择市！"); 
	$("select[name='country']").attr("dataType", "Require").attr("msg", "请选择县！"); 
	$("#user_addr").attr("dataType", "Require").attr("msg", "详细街道地址必须填写！"); 
	$("#use_tel").attr("dataType", "Phone").attr("Require", "false").attr("msg", "电话格式不正确！"); 
	$("#user_phone").attr("dataType", "Mobile").attr("Require", "false").attr("msg", "手机格式不正确！"); 
	$("#user_remark").attr("dataType", "Limit").attr("max", "100").attr("msg", "备注不能超过100个文字");
	$("#user_phone").attr("dataType", "Mobile").attr("Require", "true").attr("msg", "手机必须填写且格式要正确！"); 
	$("#user_tel").attr("dataType", "Phone").attr("Require", "false").attr("msg", "电话格式不正确！"); 
	
	$("#btn_submit").click(function(){ 
		
		if (Validator.Validate(this.form, 1)) {
			$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			$("#btn_reset").attr("disabled", "true");
			$("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
