<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="${ctx}/styles/manager/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaR3MmtMatch" enctype="multipart/form-data">
      <html-el:hidden property="r3_shop_id" styleId="r3_shop_id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="popedom" styleId="popedom" />
      <html-el:hidden property="method" styleId="method" value="toSave" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="2"  bgcolor="#CCCCCC">康佳客户端用户信息填写</td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>登录名：</td>
          <td width="88%" align="left"><html-el:text property="user_name" size="40" maxlength="30" styleId="user_name" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>密码 ：</td>
          <td width="88%" align="left"><html-el:password property="pass_word" size="30" maxlength="30" styleId="pass_word" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>确认密码：</td>
          <td width="88%" align="left"><input name="re_password" type="password" class="biaodan" id="re_password" size="30" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">真实姓名：</td>
          <td width="88%" align="left"><html-el:text property="real_name" styleId="real_name" size="30" maxlength="30"/></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>性别：</td>
          <td width="88%" align="left"><select name="sex" id="sex">
              <option value="0">男</option>
              <option value="1">女</option>
              <option value="90">未知</option>
              <option value="99">不详</option>
            </select></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">生日：</td>
          <td width="88%" align="left"><html-el:text property="birthday" size="15" maxlength="30" styleId="birthday" readonly="true" onclick="new Calendar(1900, 2030, 0).show(this);"/></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>手机：</td>
          <td width="88%" align="left"><html-el:text property="mobile" size="40" maxlength="30" styleId="mobile" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">固定电话：</td>
          <td width="88%" align="left"><html-el:text property="tel" size="40" maxlength="30" styleId="tel" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">电子信箱：</td>
          <td width="88%" align="left"><html-el:text property="email" size="40" maxlength="30" styleId="email" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>所在区域：</td>
          <td width="88%" align="left"><ul>
              <li style="padding-top:3px;">
                <select name="province" id="province" style="width:180px;">
                  <option value="">-请选择省/直辖市/自治区-</option>
                </select>
                &nbsp;
                <select name="city" id="city" style="width:100px;">
                  <option value="">-请选择市-</option>
                </select>
                &nbsp;
                <select name="country" id="country" style="width:100px;">
                  <option value="">-请选择县-</option>
                </select>
              </li>
            </ul></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">形象照片：</td>
          <td width=""><input type="file" name="user_image" id="user_image" onkeydown="return false" onpaste="return false"/></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">MSN：</td>
          <td width="88%" align="left"><html-el:text property="msn" size="40" maxlength="30" styleId="msn" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">QQ：</td>
          <td width="88%" align="left"><html-el:text property="qq" size="40" maxlength="30" styleId="qq" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">数字签名：</td>
          <td width="88%" align="left"><html-el:text property="sign" size="40" maxlength="30" styleId="sign" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">地址：</td>
          <td width="88%" align="left"><html-el:text property="address" size="40" maxlength="30" styleId="address" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="下一步" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="重 置" styleClass="but5" styleId="btn_reset" onclick="this.form.reset();" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#user_name").attr("datatype","Require").attr("msg","请填写用户名！");
	$("#pass_word").attr("datatype","Require").attr("msg","请填写密码！");
	$("#re_password").attr("datatype","Repeat").attr("to","pass_word").attr("msg","密码不一致！");
	$("#tel").attr("Require",false).attr("datatype","Phone").attr("msg","请正确填写");
	$("#mobile").attr("datatype","Require").attr("msg","请正确填写");
	$("#sex").attr("datatype","Require").attr("msg","请选择");
	$("#email").attr("Require",false).attr("datatype","Email").attr("msg","请正确填写");
	
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}", "datatype": "Require", "msg": "请选择省名称"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	$("#province").change();

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
    		this.form.submit();
    		}
	});
	$("#user_name").blur(function(){
		var value = $.trim(this.value);
		$("#user_name").val(value);
		if (value != "") {
			var reg = /^[a-z]\w{3,16}$/i;
			if (!reg.test(value)) {
				$("#user_name").focus();
				alert("用户名格式不正确：4-16位之间且只能包含字母、数字和下划线，不能以数字开头，请重新输入！");
				return false;
			} 
			$.ajax({
				type: "POST",
				url: "${ctx}/manager/admin/KonkaR3MmtMatch.do",
				data: "method=validateName&user_name=" + value,
				error: function(request, settings) {},
				success: function(data) {
					if(data == "true"){
						$("#user_name").val("");
						alert("系统已存在此用户");
						$("#user_name").focus();
					}else {
						return;
					}
				}
			});
		}
	});

	$("#pass_word").blur(function(){
		var value = this.value;
		if (value != "") {
			var reg = /^[a-zA-Z0-9_!@#\$\*]{6,16}$/;
			if (!reg.test(value)) {
				$("#pass_word").val("");
				$("#pass_word").focus();
				alert("密码格式不正确：6-16位字符，不能包含“%”、“&amp;”、“？”、“=”和空格，请重新输入！");
				return false;
			}
		}
	});
	
});


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
