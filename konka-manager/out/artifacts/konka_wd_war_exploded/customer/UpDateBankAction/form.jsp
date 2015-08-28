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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet" type="text/css" />

<style type="text/css">
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 2px;/*padding:2px 5px;*/}
input,textarea,select,file,button{font-family:Microsoft Yahei;font-size:12px;}
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
.rtable3 td {
  padding: 7px 0 4px 5px;
  border-bottom: 1px rgba(199, 199, 199, 0) dotted;
}
input#btn_submit {
  background: url("../../images/manager/but_serv1.jpg") no-repeat;
  height: 30px;
  width: 60px;
}
input#btn_back {
  background: url("../../images/manager/but_return1.jpg") no-repeat;
  height: 30px;
  width: 60px;
}
.ck-li{position:relative;height:22px;padding:1px auto;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}


.navClass {background-color:#CCC;border-collapse:collapse;}
.navClass th {background:#F6F6F6;color:#C4C4C4;font-size:12px;font-weight:bold;height:20px;line-height:20px;text-align:center;padding:2px;border:1px solid #CCC;}
.navClass td {padding:3px;height:18px;background-color:#fff;border:1px solid #CCC;}

.xubox_close{position:absolute;}
.xubox_close1_0{ right:-25px; top:-16px; width:33px; height:31px; background:url("${ctx}/styles/customer/images/xubox_ico0_1.png") -6px -182px no-repeat; cursor:pointer; overflow:hidden;}
.xubox_close1_0:hover{background:url("${ctx}/styles/customer/images/xubox_ico0_1.png") -6px -215px no-repeat;}
.xubox_border{border-radius:5px;position:absolute;}

.main_box{position:relative;width:260px;z-index:825;border:1px solid rgba(0,0,0,0);-webkit-border-radius:1px;-moz-border-radius:1px;border-radius:1px;-webkit-box-shadow:0 0 5px rgba(0,0,0,0.4);-moz-box-shadow:0 0 3px rgba(0,0,0,0.4);box-shadow:0 0 5px rgba(0,0,0,0.4);border:1px solid #CCC;}
.pic_box{text-align:center;;z-index:827;background:#FFFFFF;margin:5px;}
.title_item{
		text-align: right;
		height:40px;
}

input[type='submit'],input[type='button'] { border-radius:3px; border:1px solid #CCC; padding: 0px 5px 6px 5px;background-color:#eee; color:#555; cursor: pointer; margin-right: 1px;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">

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
	  <html-el:form action="/manager/UpDateBank"  method="post">
	    <html-el:hidden property="method" value="save" />
	    <html-el:hidden property="user_id" value="${af.map.user_id}" />
	    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	    <html-el:hidden property="queryString" />
	    <%@ include file="/commons/pages/messages.jsp"%>
      	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      		<tr>
            <td class="title_item" nowrap="nowrap">登录账号：</td>
            <td >
                ${af.map.user_name}
            </td>
            </tr>
      		<tr>
            <td class="title_item" nowrap="nowrap">原密码：</td>
            <td ><input name="pwd" type="password" maxlength="20" id="old_password" class="webinput" style="width:200px; height:25px;" />
            </td>
          </tr>
          <tr>
            <td class="title_item" nowrap="nowrap">新密码：</td>
            <td ><input name="newpwd" type="password" maxlength="20" id="newpwd" class="webinput" style="width:200px; height:25px;" /><font>&nbsp;&nbsp;请输入6-20个字符的英文字母或数字！</font>
            </td>
          </tr>
          <tr>
            <td class="title_item" nowrap="nowrap">重复新密码：</td>
            <td ><input name="repwd" type="password" maxlength="20" id="repeat" class="webinput" style="width:200px; height:25px;" />
            </td>
          </tr>
      		<tr>
          		<td class="title_item" nowrap="nowrap">开户名：</td>
          		<td><html-el:text property="bank_account_name" styleId="bank_account_name" styleClass="webinput" maxlength="18" style="width:200px; height:25px;" /></td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">银行名称：</td>
          		<td><html-el:text property="bank_name" styleId="bank_name" styleClass="webinput" maxlength="18" style="width:200px; height:25px;" /></td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">银行卡号：</td>
          		<td><html-el:text property="bank_account" styleId="bank_account" styleClass="webinput" size="60" maxlength="100" style="width:200px;height:25px;" /></td>
          	</tr>
          	<tr>
          		<td></td>
          		<td align="left">
          			<input class="but_ec" type="button" name="Submit4" value="" id="btn_submit" />
					<input class="but_ec" type="button" name="Submit5" value="" id="btn_back" onclick="history.back()" />
          		</td>
          	</tr>
      	</table>
      </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#old_password").attr("dataType", "Require").attr("msg", "原密码不能为空");
	$("#newpwd").attr("dataType","User_name").attr("Require","true").attr("msg", "请填写密码,且必须在6-20个字符之间！");
	$("#repeat").attr("dataType", "Repeat" ).attr("to", "newpwd").attr("msg", "两次输入的密码不一致");

	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
			if(confirm("确定提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});

});


//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>  