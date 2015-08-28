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
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt; 购物券兑换</div>
    <div class="zxmalltab3">
      <html-el:form action="/center/EcVouchers" >
        <html-el:hidden property="method" styleId="method" value="save" />
        <html-el:hidden property="id" value="${af.map.id}"/>
        <%@ include file="/commons/pages/messages.jsp" %>
        <p style="margin-left:20px;margin-top:15px;font-size:16px;"> 购物券信息</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="zxmall_form_table1">
          <tr>
            <td align="right" width="20%">购物券名称：</td>
            <td align="left" width="80%">
              <html-el:hidden property="title"  value="${af.map.title}"/>
              ${af.map.title}
            </td>
          </tr>
          <tr>
            <td align="right" width="20%">金额(元)：</td>
            <td align="left" width="80%">
              <html-el:hidden property="price"  value="${af.map.price}"/>
              <fmt:formatNumber type="number" value="${af.map.price}" pattern="#0.00"/>
            </td>
          </tr>
          <tr>
            <td align="right" width="20%">兑换积分：</td>
            <td align="left" width="80%">
              <fmt:formatNumber type="number" value="${af.map.price*100}" pattern="#0"/>
            </td>
          </tr>
          <tr>
            <td align="right">备注：</td>
            <td align="left">
            <textarea name="memo" rows="8" cols="20" id="memo" style="width:60%;"></textarea>
		    <label id="info1" style="font-size: 12px;"> 还能输入</label>
		    <span id="info" style=" font-size: 14px; ">250</span>
		    <label id="info2" style=" font-size: 12px;"> 个字</label>   
            </td>
          </tr>
        
         </table>  
         <p style="margin-left:200px;margin-top:15px;font-size:16px;">  <input class="inputbtn" type="button" name="Submit4" id="btn_submit" value="兑换" />
         <html-el:button property="" value="返 回" styleClass="inputbtn" styleId="btn_back" onclick="history.back();return false;" />
         </p>            
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
var c=0;                                          
$(document).ready(function(){
		$("#memo").keyup(check);  
		$("#btn_submit").click(function(){
			var isSubmit = Validator.Validate(this.form,3);
			if (isSubmit) {
				if(c==0){
					$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				    var str = $("#memo").val(); 
				    str = trim(str);  
				    $("#memo").val(str);
					 this.form.submit();
				}else{
					alert("总结字数超出");
				}
			}
		});
		
});

function check() {
    var str = $("#memo").val();  
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
