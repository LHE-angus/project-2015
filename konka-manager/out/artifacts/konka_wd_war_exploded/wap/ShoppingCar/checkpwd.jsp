<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>支付验证</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.webinput {background:#f5f4f4;	padding-left: 5px;height: 19px;line-height: 19px;	/*font-family: Arial, Helvetica, sans-serif;*/	border: #ccc solid 1px;}
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
.txt {float: left;padding-top:8px;}.txt a {color: blue;}
.float_div{	position: absolute;	border: solid 1px #d1e3f5;top:40px;text-align: center;background: #f5f4f4; width:300px;left:50px;padding-bottom: 20px;padding-top: 0px;}
.float_div div{margin-top: 0px;}
.close{ float: right;bottom: 0px;color:red;}
</style>
</head>
<body style="font-family:Microsoft Yahei;"> 
<div class="float_div" id="uploadPanel" >
      <div style="font-size:14px;">
        <b>请输入支付密码</b>
      </div><br />
       <div >
	<form action="ShoppingCar.do?" method="post"  >
      <html-el:hidden property="method" value="checkPayPwd" />
      <html-el:hidden property="show" value="1" />
	<input name="password" type="password" id="password" maxlength="30" value="" style="width:180px;" /> <input type="button" name="btn_submit" class="but4" id="btn_submit" value="确定"/>
	</form> 
	</div>
</div> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#password" ).attr("datatype", "Require").attr("msg", "请填写支付密码！");
	$("#btn_submit").click(function(){
		this.disabled=true;
		this.value="正在验证。。。"; 
		this.form.submit();
	}); 
});
//]]>
</script> 
</body>
</html>
