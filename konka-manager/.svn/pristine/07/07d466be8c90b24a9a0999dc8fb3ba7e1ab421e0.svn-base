<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳粉丝会</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/global.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/login-new.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/shop/css/footer.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="head">
  <div class="logo" style="padding-top: 0px;padding-bottom: 10px;"><span class="l"><img src="${ctx}/styles/shop/images/pingtai2.jpg" width="429" /></span><span class="r" style="padding-top: 20px;"><img src="${ctx}/styles/shop/images/tel.gif" width="166" height="33" /></span></div>
  <div class="main">
    <div class="guanggao">
      <div class="pro-switch">
		<div class="slider">
			<div class="flexslider">
				<ul class="slides">
					<c:forEach var="cur" items="${imgList}">
						<li><div class="img"><img src="http://epp.konka.com/${ctx}/${cur.image_path}" height="310" width="960" alt="" /></div></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	  </div>
    </div>
    <div class="intro">
      <h1>什么是KKmall？</h1>
      <p> KKmall(KONKA mall):是KONKA为经销商提供的专业在线销售平台，帮助经销商朋友以互联网的思维做传统生意，创新经营模式、拓展无限空间、规避商业风险……<br />KKmall出生于2013年12月1日，从它诞生的那一刻起，就承载着KONKA对未来商业模式的梦想，我们愿与经销商朋友们一起，共同开启O2O未来之门…… </p>
    </div>
    <form action="${ctx }/shop/login.do?method=login" method="post" style="margin:0;padding:0;" id="login_form"> 
    <div class="signin">
      <div class="loginbox">
        <h3 class="logintit">欢迎登录康佳粉丝会</h3>
        <table width="307" border="0" cellpadding="0" cellspacing="0">
         <tr>
             <td width="25%" align="center" style="font-size:16px;" >用户名</td><td class="logintd1"  width="75%" ><input class="input_login" name="user_name" type="text" id="user_name" maxlength="40" value="${user_name}" style="width:160px;" /></td>
          </tr> 
          <tr>
            <td width="25%" align="center" style="font-size:16px;" >密　码</td> <td class="logintd1"  width="75%" ><input class="input_login" name="password" type="password" id="password" maxlength="40" value="${password}" style="width:160px;" /></td>
          </tr>
          <tr>
            <td class="logintd3" colspan="2"><p style="margin-left:16px;" ><input name="is_reshop" type="checkbox" id="is_reshop" value="1" />
             <font>记住密码</font>&nbsp;&nbsp;</p></td>
          </tr>
          <tr>
            <td class="logintd4"  colspan="2"><a title="登录" id="btn_sub" style="margin-left:12px;cursor:pointer;"><img alt="登录" src="${ctx}/styles/shop/images/but_login.gif" width="280" height="34" /></a></td>
          </tr>
        </table>
      </div>
    </div>
    </form>
  </div>
  
   <div class="bottom2" style="width:100%"><span><img src="${ctx}/styles/shop/images/bottom4.gif"/></span>
    <div class="b_service_pic_sub"><img src="${ctx}/styles/shop/images/bottom1.gif" height="38" width="114" border="0"/></div>
    <div class="b_service_pic_sub"><img src="${ctx}/styles/shop/images/bottom2.gif"  height="38" width="114"border="0"/></div>
    <div class="b_service_pic_sub"><img src="${ctx}/styles/shop/images/bottom3.gif" height="38" width="114" border="0"/></div>
    <div class="clear"></div>
  </div>
  <div class="foot">康佳集团 版权所有 KONKA 2013.All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网站管理：多媒体渠道管理部&nbsp;&nbsp;&nbsp;运营电话：0755-26608866-6303  粤ICP备05059413</div>
   
</div>
<!--<div class="konka_float_index">
	<ul><li><img src="${ctx}/styles/shop/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/shop/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/shop/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/shop/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/shop/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/shop/images/998.jpg" width="125" height="43" /></li><li><img src="${ctx}/styles/shop/images/998.jpg" width="125" height="43" /></li>
</ul>-->
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/styles/shop/js/dell-epp-track.js"></script>
<script type="text/javascript" src="${ctx}/styles/shop/js/slides4epp.js"></script>
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
	
	$(document).delegate("#btn_sub", "click", function(){
		if(Validator.Validate($("#login_form")[0], 1)){
			$("#btn_sub").attr("disabled", true);
			$("#login_form").submit();
		}
	});
	
	// 记住密码回显
	if ("true" == "${not empty is_reshop}") {
		$("#is_reshop")[0].checked = true;
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