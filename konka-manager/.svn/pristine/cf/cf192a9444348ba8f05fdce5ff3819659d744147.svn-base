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
    <div class="position"><a href="${ctx }/member/Index.do">首页</a> &gt; <a href="${ctx }/member/center/Index.do">会员中心</a> &gt; 咨询信息</div>
    <div class="membertab3">
      <html-el:form action="/center/EcQaInfo" >
        <html-el:hidden property="method" styleId="method" value="save" />
        <html-el:hidden property="id"/>
        <%@ include file="/commons/pages/messages.jsp" %>
        <p style="margin-left:20px;margin-top:15px;font-size:16px;"> 咨询信息</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="member_form_table1">
          <tr>
            <td align="right" width="20%">咨询类别：</td>
            <td align="left" width="80%"><html-el:radio property="qa_type_code" value="0"> 咨询 </html-el:radio>
              <html-el:radio property="qa_type_code" value="1"> 投诉 </html-el:radio> 
              <html-el:radio property="qa_type_code" value="2"> 建议 </html-el:radio>
            </td>
          </tr>
          
          <tr>
            <td align="right"><font color="red">*</font>标题：</td>
            <td align="left"><html-el:text styleClass="input_txt" property="q_title"  maxlength="100" style="width:300px;"></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">内容：</td>
            <td align="left">
            <textarea name="q_content" rows="8" cols="20" id="content" style="width:60%;"></textarea>
	    <label id="info1" style="font-size: 12px;"> 还能输入</label>
	    <span id="info" style=" font-size: 14px; ">250</span>
	    <label id="info2" style=" font-size: 12px;"> 个字</label>   
            </td>
          </tr>
        
         </table>  
         <p style="margin-left:200px;margin-top:15px;font-size:16px;">  <input class="inputbtn" type="button" name="Submit4" id="btn_submit" value="确认" /></p>            
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
	$("#content").keyup(check);  
		$("input[name='q_title']").attr("dataType", "Require").attr("msg", "标题不能为空！");  
		$("#content").attr("dataType", "Require").attr("msg", "内容不能为空！"); 
		
		$("#btn_submit").click(function(){
			var isSubmit = Validator.Validate(this.form,3);
			if (isSubmit) {
				if(c==0){
					$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				    var str = $("#content").val(); 
				    str = trim(str);  
				    $("#content").val(str);
					 this.form.submit();
				}else{
					alert("总结字数超出");
				}
			}
		});
		
});

function check() {
    var str = $("#content").val();  
    var len = strlen(str);  
    var info = 250 - len;  
    info = info + "";  
    if(info.indexOf('.') > 0){
        info = info.substring(0, info.indexOf('.'));
    } 
    if(len <= 250) {
    	c=0;
        $("#info1").html("还能输入");
        $("#info").css('color', 'gray');
        $("#info").html(info);
    }else {
    	c=1;
        info = info.substr(1)
        $("#info1").html("超出");
        $("#info").css('color', 'red');
        $("#info").html(info);
     }
}  

function trim(str) {
    return (str + '').replace(/(\s+)$/g, '').replace(/^\s+/g, '');
}
function strlen(str) {
    str = trim(str);  
    var len = 0;  
    for (var i = 0; i < str.length; i++) {  
        len += str.charCodeAt(i) > 0 && str.charCodeAt(i) < 255 ? 0.5 : 1;  
    }
    return len;
}  

//]]></script>
</html>
