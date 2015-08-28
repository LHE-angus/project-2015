<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" /> 
<title>开心猫</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
</head>
<body> 
<div id="content">
<div class="mainbox">
<div class="maincont">  
<html-el:form action="/center/Skip" >
        <html-el:hidden property="method" styleId="method" value="list" /> 
       <p style="color:#fc7200;margin-left:20px;margin-top:15px;font-size:22px;line-height:40px;">账户中心登录验证</p>
        <table style="100%">
          <tr>
            <td width="120" align="right">请输入支付密码：</td>
            <td align="left"><input class="input_txt" name="pwd" type="password" id="pwd" maxlength="20" value="${pwd}" />
           <font color="red">${af.map.msg }</font>
            </td>
          </tr> 
         </table>        
         <p style="margin-left:120px;margin-top:15px;font-size:16px;">  <input class="inputbtn" type="button" name="Submit4" id="btn_submit" value=" 确 认 " /></p> 
</html-el:form>
</div>
<div class="clear"></div>
</div>
</div> 
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[ 
$(document).ready(function(){
	$("#pwd").attr("dataType", "Require").attr("msg", "请输入密码！");  
	
	$("#btn_submit").click(function(){ 
		var isSubmit = Validator.Validate(this.form,3);
		if (isSubmit) {
			 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			 this.form.submit();
		}
	});
});                                          
                                          
function showNav(){  
	if(document.getElementById("jdkey").style.display=='none'){ 
		document.getElementById("jdkey").style.display='block';
	}else{
		document.getElementById("jdkey").style.display='none';
	} 
}
//]]>
</script> 
</html>