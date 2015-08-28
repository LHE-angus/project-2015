<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳EPP内部优惠购机平台</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/login-new.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/footer.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="head">
  <div class="logo" style="padding-top: 0px;padding-bottom: 10px;"><span class="l"><img src="${ctx}/styles/member/images/pingtai.jpg"  /></span><span class="r" style="padding-top: 20px;"><img src="${ctx}/styles/member/images/tel.gif" width="166" height="33" /></span></div>
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
      <h1>什么是EPP？</h1>
      <p> 
		 EPP(Employee Purchase Program)内购平台是康佳VIP个人购机专享绿色通道，为康佳集团内部员工/ 康佳重点合作企业员工/ 康佳合作企业高级客户提供专享服务。会员可以享受优惠价格购买康佳产品、专享货到付款服务，让生活充满康佳的惊喜。
 		</p>
    </div>
    <form action="${ctx }/member/login.do?method=login" method="post" style="margin:0;padding:0;" id="login_form">
    <div class="signin">
      <div class="loginbox">
        <h3 class="logintit">欢迎登录康佳EPP内部优惠购机平台</h3>
        <table width="307" border="0" cellpadding="0" cellspacing="0">          
          <tr>
             <td width="25%" align="center" style="font-size:16px;" >用户名</td><td class="logintd1"  width="75%" ><input class="input_login" name="user_name" type="text" id="user_name" maxlength="40" value="${user_name}" style="width:180px;" /></td>
          </tr> 
          <tr>
            <td width="25%" align="center" style="font-size:16px;" >密　码</td> <td class="logintd1"  width="75%" ><input class="input_login" name="password" type="password" id="password" maxlength="40" value="${password}" style="width:180px;" /></td>
          </tr>
          <tr>
            <td width="25%" align="center" style="font-size:16px;" >验证码</td> <td class="logintd1"  width="75%"> <input type="text" name="verificationCode" id="verificationCode" tabindex="3" class="input_login"  maxlength="4" style="width:60px;color:#000;" />&nbsp;&nbsp;<img src="${ctx}/images/VerificationCode.jpg" width="76" height="29" style="border:1px solid #A1BCA3;cursor:pointer;" alt="验证码，看不清楚请用鼠标点击此处！" onclick="$(this).hide().attr('src', this.src + '?' + new Date().getTime()).fadeIn();" /></td>
          </tr>
          <tr>
            <td class="logintd3" colspan="2"><p style="margin-left:15px;" ><input name="is_remember" type="checkbox" id="is_remember" value="1" />
             <font>记住密码</font><a href="${ctx}/member/EcBackPassword.do" style="margin-left: 25px;color: #08C;">忘记登录密码</a>
            <a href="${ctx}/member/login.do?method=view&id=806598" style="margin-left: 25px;color: #08C;">新手上路</a></p>
             </td>
          </tr>
        
          <tr>
            <td class="logintd4"  colspan="2"><a title="登录" id="btn_sub" style="margin-left:12px;cursor:pointer;"><img alt="登录" src="${ctx}/styles/member/images/but_login.gif" width="280" height="34" /></a></td>
          </tr>
        </table>
      </div>
    </div>
    </form>
  </div>
   <div class="bottom2" style="width:100%"><span><img src="${ctx}/styles/member/images/bottom4.gif"/></span>
    <div class="b_service_pic_sub"><img src="${ctx}/styles/member/images/bottom1.gif" height="38" width="114" border="0"/></div>
    <div class="b_service_pic_sub"><img src="${ctx}/styles/member/images/bottom2.gif"  height="38" width="114"border="0"/></div>
    <div class="b_service_pic_sub"><img src="${ctx}/styles/member/images/bottom3.gif" height="38" width="114" border="0"/></div>
    <div class="clear"></div>
  </div>
  <div class="foot">康佳集团 版权所有 KONKA 2013.All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网站管理：电子商务部&nbsp;&nbsp;&nbsp;运营电话：0755-26608866-6346 &nbsp;&nbsp;粤ICP备05059413&nbsp;&nbsp;&nbsp;V1.0.0.2014122901</div>
   
</div>
<!--<div class="konka_float_index">
	<ul><li><img src="${ctx}/styles/member/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/member/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/member/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/member/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/member/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/member/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/member/images/998.jpg" width="125" height="43" /></li>
</ul>-->

</body>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/styles/member/js/dell-epp-track.js"></script>
<script type="text/javascript" src="${ctx}/styles/member/js/slides4epp.js"></script>
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
	$("#verificationCode" ).attr("datatype", "Require").attr("msg", "请填写验证码！");
	$("#user_name").keyup(function(){
		var job_id = $(this).val();
		$(this).val(SBC2DBC(job_id));
	});
	$("#password").keyup(function(){
		var job_id = $(this).val();
		$(this).val(SBC2DBC(job_id));
	});
	$(document).delegate("#btn_sub", "click", function(){
		if(Validator.Validate($("#login_form")[0], 1)){
			$("#btn_sub").attr("disabled", true);
			$("#login_form").submit();
		}
	});
	//全角转换成半角
	function SBC2DBC(str){
		var   i;  
		var   result= '';  
		for(i=0;i <str.length;i++)   {
			var  code=str.charCodeAt(i)
			//“65281”是“！”，“65373”是“｝”
			if(code >= 65281&&code < 65373){
				//     “65248”是转换码距
				result+=String.fromCharCode(str.charCodeAt(i)-65248);
			} else {
				result+=str.charAt(i);
			}
		}  
		return result;  
	}
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
//]]></script>
</html>