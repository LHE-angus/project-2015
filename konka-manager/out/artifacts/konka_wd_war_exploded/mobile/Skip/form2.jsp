<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/member.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="">
  <!--right-->
  <div class="">
    <div class="">
      <html-el:form action="/Skip2" >
	<html-el:hidden property="method" styleId="method" value="list" />
	<html-el:hidden property="user_id"  value="${user_id}" />
	<html-el:hidden property="username"  value="${username}" />
	<html-el:hidden property="userpass"  value="${userpass}" />
	<html-el:hidden property="mod_id"  value="${mod_id}" />
	<p>  &nbsp;</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="">
         <tr>
            <td width="30%" align="left">请输入支付密码：</td>

            </tr>
          <tr>

            <td width="60%" align="center"><input class="input_txt" name="pwd" type="password" id="pwd" maxlength="20" value="${pwd}" />
           <font color="red">${af.map.msg }</font>
            </td>
          </tr> 
         </table>
        
         <p style="margin-left:200px;margin-top:15px;font-size:16px;">  <input class="inputbtn" type="button" name="Submit4" id="btn_submit" value="确认" /></p> 
           
      </html-el:form>
    </div>
  </div>
  <div class="clear"></div>
</div>
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
   		
		$("#pwd").attr("dataType", "Require").attr("msg", "请输入支付密码！");  
		
		$("#btn_submit").click(function(){ 
			var isSubmit = Validator.Validate(this.form,3);
			if (isSubmit) {
				 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				 this.form.submit();
			}
		});
});

//]]></script>
</html>
