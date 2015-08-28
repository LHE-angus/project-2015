<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳直销平台</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/global.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/login-new.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/footer.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="head">
  <div class="logo" style="padding-top: 0px;padding-bottom: 10px;"><span class="l"><a href="${ctx}/zxmall/Index.do"><img src="${ctx}/styles/zxmall/images/zxmall3.jpg" width="429" /></a></span><span class="r" style="padding-top: 20px;"><img src="${ctx}/styles/zxmall/images/tel.gif" width="166" height="33" /></span></div>
  <div class="main">
    <div class="guanggao">
      <div class="pro-switch">
		<div class="slider">
			<div class="flexslider">
				<ul class="slides">
					<c:forEach var="cur" items="${imgList}">
						<li><div class="img"><img src="${ctx}/${cur.image_path}" height="310" width="960" alt="" /></div></li>  
					</c:forEach>
				</ul>
			</div>
		</div>
	  </div>
    </div>
    <div class="intro">
      <h1>什么是“康佳触网直销商城”？</h1>
      <p>  康佳触网直销商城(zx.konka.com)：是KONKA为经销商、会员提供的在线销售平台。帮助经销商朋友以互联网O2O模式做经营转型，同时降低中间环节成本惠及广大会员。 </p>
    </div>
    <form action="${ctx }/zxmall/login.do?method=login" method="post" style="margin:0;padding:0;" id="login_form">
    <div class="signin">
      <div class="loginbox">
        <h3 class="logintit">欢迎登录康佳直销平台</h3>
        <table width="307" border="0" cellpadding="0" cellspacing="0">          
          <tr>
             <td width="25%" align="center" style="font-size:16px;" >用户名</td><td class="logintd1"  width="75%" ><input class="input_login" name="user_name" type="text" id="user_name"  tabindex="1" onkeyup="FtoH(this);"  maxlength="40" value="${user_name}" style="width:180px;" /></td>
          </tr> 
          <tr>
            <td width="25%" align="center" style="font-size:16px;" >密　码</td> <td class="logintd1"  width="75%" ><input class="input_login" name="password" type="password" id="password"  tabindex="2" onkeyup="FtoH(this);" maxlength="40" value="${password}" style="width:180px;" /></td>
          </tr>
          <tr>
            <td width="25%" align="center" style="font-size:16px;" >验证码</td> <td class="logintd1"  width="75%"> <input type="text" name="verificationCode" id="verificationCode" tabindex="3" class="input_login"  maxlength="4" style="width:60px;color:#000;" />&nbsp;&nbsp;<img src="${ctx}/images/VerificationCode.jpg" width="76" height="29" style="border:1px solid #A1BCA3;cursor:pointer;" alt="验证码，看不清楚请用鼠标点击此处！" onclick="$(this).hide().attr('src', this.src + '?' + new Date().getTime()).fadeIn();" /></td>
          </tr>
          <tr>
            <td class="logintd3" colspan="2"><p style="margin-left:15px;" ><input name="is_remember" type="checkbox" id="is_remember" value="1" />
             <font>记住密码</font><a href="${ctx}/zxmall/EcBackPassword.do" style="margin-left: 25px;color: #08C;">忘记登录密码</a>
             <a href="${ctx}/zxmall/RegisterNew.do" style="margin-left: 25px;color: #08C;">免费注册</a></p>
             </td>
          </tr>
          <tr>
            <td class="logintd4"  colspan="2"><a title="登录" id="btn_sub" style="margin-left:12px;cursor:pointer;"><img alt="登录" src="${ctx}/styles/zxmall/images/but_login.gif" width="280" height="34" /></a></td>
          </tr>
        </table>
      </div>
    </div>
    </form>
  </div>
   <div class="bottom2" style="width:100%"><span><img src="${ctx}/styles/zxmall/images/bottom4.gif"/></span>
    <div class="b_service_pic_sub"><img src="${ctx}/styles/zxmall/images/bottom1.gif" height="38" width="114" border="0"/></div>
    <div class="b_service_pic_sub"><img src="${ctx}/styles/zxmall/images/bottom2.gif"  height="38" width="114"border="0"/></div>
    <div class="b_service_pic_sub"><img src="${ctx}/styles/zxmall/images/bottom3.gif" height="38" width="114" border="0"/></div>
    <div class="clear"></div>
  </div>
  <div class="foot">康佳集团 版权所有 KONKA 2013.All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网站管理：多媒体渠道管理部&nbsp;&nbsp;&nbsp;运营电话：0755-26608866-6346 &nbsp;&nbsp;粤ICP备05059413</div>
   
</div>
<!--<div class="konka_float_index">
	<ul><li><img src="${ctx}/styles/zxmall/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/zxmall/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/zxmall/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/zxmall/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/zxmall/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/zxmall/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/zxmall/images/998.jpg" width="125" height="43" /></li>
</ul>-->

</body>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/styles/zxmall/js/dell-epp-track.js"></script>
<script type="text/javascript" src="${ctx}/styles/zxmall/js/slides4epp.js"></script>
<script type="text/javascript">//<![CDATA[
//轮播图
$(function(){
    $('.flexslider').flexslider({
      animation: "slide",
      start: function(slider){
        $('body').removeClass('loading');
      }
    });
});

$(document).ready(function(){
	$("#user_name").attr("datatype", "Require").attr("msg", "请输入用户名！");
	$("#password" ).attr("datatype", "Require").attr("msg", "请填写密码！");
	$("#user_name").focus(function() {
	    //获得焦点时，如果值为默认值，则设置为空
	    if (this.value == vdefault) {
	        this.value = "";
	        this.style.color='#333';
	    }
	}).blur(function() {
	    //失去焦点时，如果值为空，则设置为默认值
	    if (this.value == "") {
	        this.value = vdefault;
	        this.style.color='#999999';
	    }
	    
	  	//begin 转化全角为半角
		//$("#user_name").val($.trim(DBC2SBC(this.value)));
		//end 
	});
	$("#password").focus(function() {
	    //获得焦点时，如果值为默认值，则设置为空
	    if (this.value == vdefault) {
	        this.value = "";
	        this.style.color='#333';
	    }
	}).blur(function() {
	    //失去焦点时，如果值为空，则设置为默认值
	    if (this.value == "") {
	        this.value = vdefault;
	        this.style.color='#999999';
	    }
	    
	  	//begin 转化全角为半角
		//$("#password").val($.trim(DBC2SBC(this.value)));
		//end 
	});
	$(document).delegate("#btn_sub", "click", function(){
		if(Validator.Validate($("#login_form")[0], 1)){
			$("#btn_sub").attr("disabled", true);
			$("#login_form").submit();
		}
	});
	
	// 记住密码回显
	if ("true" == "${not empty is_remember}") {
		$("#is_remember")[0].checked = true;
	}
	
	document.onkeydown = function(e){     
           var ev = document.all ? window.event : e;    
           if(ev.keyCode==13) {  
        	   if(Validator.Validate($("#login_form")[0], 1)){
       			$("#btn_sub").attr("disabled", true);
       			$("#login_form").submit();
       		}  
           }
	 }
});

// 右边广告位
s_dell.pageName="";   
s_dell.events="";
s_dell.prop49="c=cn&cs=cndhs1&l=cn&s=dhs";
var s_code=s_dell.t();if(s_code)document.write(s_code);
function f_close(){
	$('.ff').css('display',"none");
}
function DBC2SBC(str){
    var result = '';
    for (var i=0 ; i<str.length; i++){
        code = str.charCodeAt(i);//获取当前字符的unicode编码
        if (code >= 65281 && code <= 65373){//在这个unicode编码范围中的是所有的英文字母已及各种字符
   			result += String.fromCharCode(str.charCodeAt(i) - 65248);//把全角字符的unicode编码转换为对应半角字符的unicode码
  		}else if (code == 12288){//空格
   			result += String.fromCharCode(str.charCodeAt(i) - 12288 + 32);
  		}else{
   			result += str.charAt(i);
  		}
   }
   return result;
}
function FtoH(obj){ 
	var str=obj.value; 
	var result="";
	for (var i = 0; i < str.length; i++){ 
		if (str.charCodeAt(i)==12288){ 
			result+= String.fromCharCode(str.charCodeAt(i)-12256); 
			continue;
		}
		if (str.charCodeAt(i)>65280 && str.charCodeAt(i)<65375){
			result+= String.fromCharCode(str.charCodeAt(i)-65248); 
		}else{ 
			result+= String.fromCharCode(str.charCodeAt(i)); 
		}
	}
	obj.value=result; 
}
//]]></script>
</html>