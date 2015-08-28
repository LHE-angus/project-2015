<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>康佳EPP内部优惠购机平台</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/register/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/register/css/css.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<div class="all">
<div class="top">
<div class="top_cont">
<div class="t_c">
<div class="logo" style="margin-top:10px;"><span class="l" style="margin-top:5px;"><img src="${ctx}/styles/member/images/k_logo1.gif" width="429" height="25" /></span><span class="r"><img src="${ctx}/styles/member/images/tel.gif" width="166" height="33" /></span></div>
</div>
</div></div>
<div class="clear">
</div>
<div class="content" >
<html-el:form action="/Register">
<div class="cont_a" >
<font class="inf">注册会员信息确认</font><a href="#"><font class="help">注册帮助</font></a>
  <html-el:hidden property="method" styleId="method" value="save" />
  <html-el:hidden property="id"/>
  <html-el:hidden property="card_no" value="${af.map.card_no}"/>
  <html-el:hidden property="real_name" value="${af.map.real_name}"/>
  <div class="confir"  style="margin-top:0px;" >
  <%@ include file="/commons/pages/messages.jsp" %>
  <div class="login_int" style="margin-top:0px;" ><label><span class="s1">真实姓名：</span>${af.map.real_name}</label></div>
  <div class="login_int" >
  <label><span class="s1">登录名：</span><html-el:text property="user_name" styleClass="reUser_text" styleId="user_name" maxlength="20" /><span style="color:#f00;display:none;" id="user_name_exist_error">该登录名已被使用，请重新输入！</span><span style="color:#f00;display:none;" id="user_name_erro">登录名不能含空白字符！</span></label></div>
  <div class="login_int"><label><span class="s1">登录密码：</span><html-el:password property="pass_word" styleClass="reUser_text" styleId="pass_word" maxlength="10" /></label></div>
  <div class="login_int"><label><span class="s1"><i class="lg_redStar"></i>重复密码：</span><html-el:password property="pass_word_repeat" styleClass="reUser_text" styleId="pass_word_repeat" maxlength="10" /></label></div>
  <div class="login_int"><span class="s1"><i class="lg_redStar"></i>居住地：</span> <select name="province" id="province" onmouseover="this.style.width='110px';this.style.height='25px';" onchange="this.style.width='110px';this.style.height='25px';" onblur="this.style.width='110px';this.style.height='25px';" style="width:110px;height:25px;">
                <option value="">-请选择-</option>
              </select>
              <select name="city" id="city" onmouseover="this.style.width='110px';this.style.height='25px';" onchange="this.style.width='110px';this.style.height='25px';" onblur="this.style.width='110px';this.style.height='25px';" style="width:110px;height:25px;">
                <option value="">-请选择市-</option>
              </select>
              <select name="country" id="country" onmouseover="this.style.width='110px';this.style.height='25px';" onchange="this.style.width='110px';this.style.height='25px';" onblur="this.style.width='110px';this.style.height='25px';" style="width:110px;height:25px;">
                <option value="">-请选择县-</option>
              </select>
              <select name="town" id="town" onmouseover="this.style.width='110px';this.style.height='25px';" onchange="this.style.width='110px';this.style.height='25px';" onblur="this.style.width='110px';this.style.height='25px';" style="width:110px;height:25px;">
                <option value="">-请选择镇-</option>
              </select></div>
  <div class="login_int"><label><span class="s1"><i class="lg_redStar"></i>街道地址：</span><html-el:text property="link_addr" styleClass="reUser_text" styleId="link_addr" maxlength="40" /></label></div>
  <div class="login_int"><label><span class="s1"><i class="lg_redStar"></i>手机：</span><html-el:text property="link_phone" styleClass="reUser_text" styleId="link_phone" maxlength="40" /></label></div>
  <div class="login_int"><label><span class="s1"><i class="lg_redStar"></i>邮箱：</span><html-el:text property="email" styleClass="reUser_text" styleId="email" maxlength="40" /></label></div>
  <div class="login_int"><label><span class="s1"><i class="lg_redStar"></i>开户名：</span><html-el:text property="bank_account_name" styleClass="reUser_text" styleId="bank_account_name" maxlength="40" /></label></div>
  <div class="login_int"><label><span class="s1"><i class="lg_redStar"></i>开户银行：</span><html-el:text property="bank_name" styleClass="reUser_text" styleId="bank_name" maxlength="40" /></label></div>
  <div class="login_int"><label><span class="s1"><i class="lg_redStar"></i>银行账号：</span><html-el:text property="bank_account" styleClass="reUser_text" styleId="bank_account" maxlength="40" /></label></div>
  <div class="login_int">  <input type="button" name="Submit4" id="btn_submit" class="regist_btn" style="margin-top: 0px;" value="立即提交" /> </div>
  
   </div> 
</div>
  </html-el:form>
</div>
<div class="information" > 康佳集团 版权所有 KONKA 2013.All Rights Reserved </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
$("#user_name").attr("datatype", "LimitB").attr("min", "6").attr("max", "20").attr("msg", "登录名由6-20个字母和数字组成！");
$("#real_name").attr("datatype", "Chinese").attr("msg", "真实姓名只允许中文！");
$("#pass_word").attr("datatype", "Require").attr("msg", "请填写密码！");
$("#pass_word_repeat").attr("datatype","Repeat").attr("to", "pass_word").attr("msg","两次输入的密码不一致！");
$("#email").attr("datatype", "Email").attr("msg", "请填写正确的邮箱！")
$("#link_phone" ).attr("datatype", "Mobile").attr("msg", "请填写正确的手机号码！");
$("#link_tel" ).attr("datatype", "Phone").attr("msg", "请填写正确的电话号码！").attr("require", "false");

$("#bank_account_name").attr("datatype", "Require").attr("msg", "请填写开户人姓名！");
$("#bank_name").attr("datatype", "Require").attr("msg", "请填写开户银行！");
$("#bank_account").attr("datatype", "Require").attr("msg", "请填写银行账号！");

//验证用户名是否存在
$("#user_name").blur(function(){
	//$("#btn_submit").attr("disabled", "disabled");
	var user_name = $("#user_name").val();
	if(null == $(this).val() || $(this).val() == ''){
		$("#user_name_exist_error").hide();
		$("#user_name_erro").hide();
		$(this).css("background-color", "#fff");
		return ;
	}
	if($(this).val().indexOf(' ')>-1){
		$("#user_name_exist_error").hide();
		$("#user_name_erro").show();
		return;
	}
	
	$("#user_name_erro").hide();
	$("#user_name_exist_error").hide();
	$(this).css("background-color", "#fff");
	if(user_name != '${af.map.user_name}'){
		$.ajax({
			type: "POST",
			url: "Register.do",
			data: "method=validateName&user_name=" + $(this).val(),
			dataType: "json",
			error: function(request, settings) {
				alert("检查用户名重复失败，请稍候再次尝试。");
				$("#user_name_exist_error").show();
				$(this).css("background-color", "#ddcc00");
				$(this).focus();
			},
			success: function(oper) {
				if (oper.result) {
					alert("该用户名已存在！");
					$("#user_name").val("");
					return;
				} else {
					$("#user_name_exist_error").hide();
					$("#user_name").css("background-color", "#fff");
					$("#btn_submit").removeAttr("disabled");
				}
			}
		});
	}
});	


//区域
$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}","dataType":"Require","msg":"请选择省！"});
$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
$("#country").attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
$("#town").attr({"defaultText": "-请选择乡/镇-", "defaultValue": "", "selectedValue": "${town}"});

$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","");
$("#province").change();

$("#btn_submit").click(function(){
	var isSubmit = Validator.Validate(this.form,3);
	if (isSubmit) {
	 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	 this.form.submit();
	}
});
});
//]]></script>

</body>
</html>