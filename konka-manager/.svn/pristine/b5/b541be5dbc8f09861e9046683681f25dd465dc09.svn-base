<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳粉丝会</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/shop.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/shop/__inc/top.jsp" flush="true" />
<jsp:include page="/shop/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/shop/__inc/nav.jsp" flush="true" />
<!-- topnav end -->
<!-- second start -->
<div class="maincont">
  <jsp:include page="/shop/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="shop_right padbot45">
    <div class="position"><a href="${ctx }/shop/Index.do">首页</a> &gt; <a href="${ctx }/shop/center/Index.do">会员中心</a> &gt; 登录验证</div>
    <div class="shoptab3">
      <html-el:form action="/center/Skip" >
        <html-el:hidden property="method" styleId="method" value="list" /> 
        <p style="margin-left:20px;margin-top:15px;font-size:16px;">账户中心登录验证</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="shop_form_table1">
          <tr>
            <td width="30%" align="right">请输入支付密码：</td>
            <td width="60%" align="left"><input class="input_txt" name="pwd" type="password" id="pwd" maxlength="20" value="${pwd}" />
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
<jsp:include page="/shop/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
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

//]]></script>
</html>
