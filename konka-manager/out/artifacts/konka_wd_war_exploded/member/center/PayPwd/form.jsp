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
<!-- head start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/member/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="member_right padbot45">
    <div class="position"><a href="${ctx }/member/Index.do">首页</a> &gt; <a href="${ctx }/member/center/Index.do">会员中心</a> &gt; 支付密码设置</div>
    <div class="membertab3">
      <html-el:form action="/center/PayPwd" >
        <html-el:hidden property="method" styleId="method" value="save" />
        <%@ include file="/commons/pages/messages.jsp" %>
         <c:if test="${s ne 1 }">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="member_form_table1">
          <tr>
            <td width="20%" align="right">原密码：</td>
            <td width="80%" align="left"><input name="pwd" type="password" maxlength="20" id="old_password" class="input_txt" style="width:200px;" />
            </td>
          </tr>
          <tr>
            <td align="right">新密码：</td>
            <td align="left"><input name="newpwd" type="password" maxlength="20" id="newpwd" class="input_txt" style="width:200px;" /><font>&nbsp;&nbsp;请使用6-20位大小写字母与数字混合密码！</font>
            </td>
          </tr>
          <tr>
            <td align="right">重复新密码：</td>
            <td align="left"><input name="repwd" type="password" maxlength="20" id="repeat" class="input_txt" style="width:200px;" />
            </td>
          </tr>
          <tr>
            <td></td>
            <td align="left"><input type="button" name="btn_submit" value="修改支付密码" id="btn_submit" class="inputbtn" />
            </td>
          </tr>
        </table></c:if>
      </html-el:form>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/member/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#old_password").attr("dataType", "Require").attr("msg", "原密码不能为空");
	$("#newpwd").attr("dataType","User_name").attr("Require","true").attr("msg", "请填写6-20位大小写字母与数字混合密码！");
	$("#repeat").attr("dataType", "Repeat" ).attr("to", "newpwd").attr("msg", "两次输入的密码不一致");
	$("#btn_submit").click(function(){
		if(pwCheck($("#newpwd").val())<2){ 
			alert("您的密码安全性等级过弱,\n(建议使用大小写字母与数字混合密码)"); 
			return false;
		}
		if(Validator.Validate(this.form, 3)){
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
</html>
