<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>康佳触网直销商城</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/register/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/register/css/css.css" />
<style type="text/css">
	.reUser_text_1{width:91px; height:32px; border:1px solid #a9a8a8; padding-left:3px; padding-top:3px;}
</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<div class="all">
<div class="top">
<div class="top_cont">
<div class="t_c">
<div class="logo" ><span class="l" ><img src="${ctx}/styles/touch/images/pingtai2.jpg" /></span><span class="r"><img src="${ctx}/styles/member/images/tel.gif" width="166" height="33" /></span></div>
</div>
</div></div>
<div class="clear">
</div>
<div class="content" >
<html-el:form action="/IsAct3">
<div class="cont_a" >
<font class="inf">修改密码</font>
  <html-el:hidden property="method" styleId="method" value="save" />
  <html-el:hidden property="id" value="${af.map.id}"/>
   <html-el:hidden property="e_id" value="${af.map.e_id}"/>
   <html-el:hidden property="flag" value="${af.map.flag}"/>
  <div class="confir"  style="margin-top:0px;" >
  <%@ include file="/commons/pages/messages.jsp" %>
  <div class="login_int"><label><span class="s1"><i class="lg_redStar">*&nbsp;</i>新密码：</span><html-el:password property="new_password" styleClass="reUser_text" styleId="new_password" maxlength="20" /></label></div>
  <div class="login_int"><label><span class="s1"><i class="lg_redStar">*&nbsp;</i>重复密码：</span><html-el:password property="pass_word_repeat" styleClass="reUser_text" styleId="pass_word_repeat" maxlength="20" /></label></div>
  <div class="login_int"><label>
  <span class="s1"><i class="lg_redStar">*&nbsp;</i>验证码：</span>
   <span ><html-el:text property="verificationCode" styleClass="reUser_text_1" styleId="verificationCode"  style="margin-left:2px;margin-top:6px;"/></span>
  <span style="margin-bottom:0px;" >
  	<img id="mm" src="${ctx}/images/VerificationCode.jpg" width="76" height="35" style="text-align:inline;border:1px solid #A1BCA3;cursor:pointer;" alt="验证码，看不清楚请用鼠标点击此处！" onclick="$(this).hide().attr('src', this.src + '?' + new Date().getTime()).fadeIn();" />
  </span>
  <a id="mk" class="codeRound">看不清？换一张</a>
  </label>
  </div>
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
$("#new_password").attr("dataType","User_name").attr("Require","true").attr("msg", "请填写6-20位大小写数字特殊字符混合密码！");
$("#pass_word_repeat").attr("datatype","Repeat").attr("to", "new_password").attr("msg","两次输入的密码不一致！");

$("#mk").click(function(){
	var src = $("#mm").attr("src");
	$("#mm").hide().attr("src", src + '?' + new Date().getTime()).fadeIn();
});

$("#btn_submit").click(function(){
	var isSubmit = Validator.Validate(this.form,3);
	if (isSubmit) {

		if(pwCheck($("#new_password").val())<2){ 
			alert("您的密码安全性等级过弱,\n(建议使用大小写数字特殊字符混用密码)"); 
			return false;
		}   

		if($('#verificationCode').val() == ''){
			alert('验证码不能为空！');
			$('#verificationCode').focus();
			return;
		}    
		
	 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	 this.form.submit();
	}
});
});

function pwCheck(str){
	  var number_str ,big_a,small_a,sign,count=0;
		  number_str=/\d/;
		  big_a=/[A-Z]/;
		  small_a=/[a-z_]/;  //小写字母 加上'_' （下划线 ）
		  sign=/\W/;
	  if (str.match(number_str)!=null)count++;
	  if (str.match(big_a)!=null)count++;
	  if (str.match(small_a)!=null)count++;
	  if (str.match(sign)!=null)count++;
	  //密码位数小于6，定为弱
	  if (str.length<6)  {
	  	count=1;
	  }
	  return count; 
} 

//]]></script>

</body>
</html>