<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>康佳EPP内部优惠购机平台</title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/register/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/register/css/css.css" />
<style type="text/css">

.reUser_text_1{width:91px; height:32px; border:1px solid #a9a8a8; padding-left:3px; padding-top:3px;}

#myHiddenDiv1 {display:none;}
.popup a:hover{text-decoration:none; color:#fff;}
.popup-header {height:24px; padding-top:20px; height:38px; line-height:32px;}
.popup-header h2 {font-size:14px; width:100%; text-align:center;}
.popup-body { width:100%; padding-top:8px; }
.popup-body strong{ display:block; text-align:center; font-size:12px; font-weight:normal; margin-bottom:5px;}
.con{ padding:10px; width:692px; height:247px; margin:0 auto 20px auto; overflow:auto; border:1px solid #a4c9e3;}
.con p{ text-indent:2em; line-height:18px; margin-bottom:10px;}
.con b{ text-indent:2em;}
</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<div class="all">
<div class="top">
<div class="top_cont">
<div class="t_c">
<div class="logo" ><span class="l"><img src="${ctx}/styles/member/images/pingtai.jpg" /></span><span class="r"><img src="${ctx}/styles/member/images/tel.gif" width="166" height="33" /></span></div>
</div>
</div>
</div>
<div class="clear"></div>
<div class="content">
<div class="cont_a">
<font class="inf">会员身份验证</font> 
  <div class="confir">
  <html-el:form action="/EcBackPassword">
  <html-el:hidden property="method" styleId="method" value="nextStep" />
  <html-el:hidden property="id"/>
  <%@ include file="/commons/pages/messages.jsp" %>
  <div class="login_int"><label><span class="s2"><i class="lg_redStar">&nbsp;*&nbsp;</i>邮箱：</span><html-el:text property="email" styleClass="reUser_text" styleId="email" maxlength="60" /></label></div>
  <div class="login_int"><label><span class="s2"><i class="lg_redStar">&nbsp;*&nbsp;</i>姓名：</span><html-el:text property="real_name" styleClass="reUser_text" styleId="real_name" maxlength="15" /></label></div>
  <div class="login_int"><label><span class="s2"><i class="lg_redStar">&nbsp;*&nbsp;</i>工卡号：</span><html-el:text property="card_no" styleClass="reUser_text" styleId="card_no" maxlength="15" /></label></div>
  <div class="login_int"><input type="button" name="Submit4" id="btn_submit" class="regist_btn" value="立即验证" /></div>
  </html-el:form>	
</div>
</div>
</div>
<div class="information"> 康佳集团 版权所有 KONKA 2013.All Rights Reserved </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jmpopups-0.5.1.js"></script>
<script type="text/javascript">

$("#email").attr("datatype", "Require").attr("msg", "邮箱不能为空！");
$("#card_no").attr("datatype", "Require").attr("msg", "工卡号不能为空！");
$("#real_name").attr("datatype", "Require").attr("msg", "姓名不能为空！");

$("#email").keyup(function(){
	var job_id = $(this).val();
	$(this).val(SBC2DBC(job_id));
});
$("#card_no").keyup(function(){
	var job_id = $(this).val();
	$(this).val(SBC2DBC(job_id));
});
$("#real_name").keyup(function(){
	var job_id = $(this).val();
	$(this).val(SBC2DBC(job_id));
});
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form,3);
		if (isSubmit) {
		 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
		 this.form.submit();
		}
	});
	//全角转换成半角
	function SBC2DBC(str){
		var   i;  
		var   result= '';  
		for(i=0;i <str.length;i++)   {
			var  code=str.charCodeAt(i)
			//“65281”是“！”，“65373”是“｝”
			if(code >= 65281&&code < 65373){
				//     “65248”是转换码距
				result+=String.fromCharCode(str.charCodeAt(i)-65248);
			} else {
				result+=str.charAt(i);
			}
		}  
		return result;  
	}
	

</script>
</body>
</html>

