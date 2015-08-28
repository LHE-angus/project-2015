<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>康佳</title>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/base.css"/>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/index.css"/> 

<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/citybox.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/cloud-zoom/styles/image_show.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/zxmall.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body id="web_body">
<jsp:include page="/zxmall/__inc/2015/top.jsp" flush="true" />  
<jsp:include page="/zxmall/__inc/2015/nav.jsp" flush="true" />  
<div class="main" style="padding-top:0px;">
<div class="w1200">
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt; 登录密码设置</div>
    <div class="zxmalltab3">
      <html-el:form action="/center/Pwd" >
        <html-el:hidden property="method" styleId="method" value="save" />
        <%@ include file="/commons/pages/messages.jsp" %>
        <c:if test="${s ne 1 }">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="zxmall_form_table1">
          <tr>
            <td width="20%" align="right">原密码：</td>
            <td width="80%" align="left"><input name="pwd" type="password" maxlength="20" id="old_password" class="input_txt" style="width:200px;" />
            </td>
          </tr>
          <tr>
            <td align="right">新密码：</td>
            <td align="left"><input name="newpwd" type="password" maxlength="20" id="newpwd" class="input_txt" style="width:200px;" /><font>&nbsp;&nbsp;请输入6-20个字符的英文字母或数字！</font>
            </td>
          </tr>
          <tr>
            <td align="right">重复新密码：</td>
            <td align="left"><input name="repwd" type="password" maxlength="20" id="repeat" class="input_txt" style="width:200px;" />
            </td>
          </tr>
          <tr>
            <td></td>
            <td align="left"><input type="button" name="btn_submit" value="修改密码" id="btn_submit" class="inputbtn" />
            </td>
          </tr>
        </table></c:if>
      </html-el:form>
    </div>
  </div>
  <div class="clear"></div>
</div>
</div> 
</div>
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#old_password").attr("dataType", "Require").attr("msg", "原密码不能为空");
	$("#newpwd").attr("dataType","User_name").attr("Require","true").attr("msg", "请填写密码,且必须在6-20个字符之间！");
	$("#repeat").attr("dataType", "Repeat" ).attr("to", "newpwd").attr("msg", "两次输入的密码不一致");
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			this.form.submit();
		}
	});
});
//]]></script>
</html>
