<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳渠道管理系统</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/login/css/css.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/login/css/global.css" />
<style type="text/css">
html{overflow: hidden;}
	
.smallslider{position:relative;padding:0;margin:0;overflow:hidden;}
.smallslider ul{list-style-type:none;padding:0;margin:0;position: absolute;width:auto;height:auto;}
.smallslider li{margin:0;padding:0;}
.smallslider li a{margin:0;padding:0;}
.smallslider li a img{border:0;padding:0;margin:0;vertical-align:top;}
.smallslider  h3{position:absolute;font-weight:bold;font-size:12px;margin:0;padding:0;text-indent:2%;line-height:26px;z-index:102; width:98%;color:#CCC;}
.smallslider  h3 a{padding:0;margin:0;text-indent:0; }
.smallslider  h3 a:link,.smallslider  h3 a:visited{text-decoration:none;color:#FFFFFF;}
.smallslider  h3 a:hover{text-decoration:underline;color:#FF6600;}
.smallslider li.current-li{}
.smallslider-btns{position:absolute;z-index:103;}
.smallslider-btns span{background-color:#FFFFFF;border:1px solid #DCDCDC;color:#9F9F9F;cursor:pointer;float:left;font-size:12px;height:16px;line-height:16px;text-align:center; width:16px;}
.smallslider-btns span.current-btn{ background-color:#C00100; border:1px solid #A00100; color:white; font-size:13px;font-weight:bold;}
.smallslider-lay{position:absolute;background:black;height:26px;width:100%; z-index:101;}

#scrollDiv {width:413px;height:100px;min-height:25px;line-height:25px;overflow:hidden;margin:0;padding:0;} 
#scrollDiv li{height:25px;padding-left:10px;margin:0;padding:0;} 
</style>
</head>
<body class="bg_main">
<div class="mian_mian">
  <div class="right_contcontleft"  >
    <img src="${ctx}/styles/login/images/logo1.png" width="500" height="45" /></div>
    <div style="width:99%; background-color:#FFF; padding: 10px;">
      <div class="m_l">
        <div class="sub_top">
    	<div id="imgplayer1" class="sub_r smallslider" style="width:222px;height:327px;">
    	<ul>
    		<c:forEach var="cur" items="${imgList}">
    		 <li><a href="${ctx}/${cur.image_url}" target="_blank"><img src="${ctx}/${cur.image_path}" title="" alt="${cur.title}" width="222" height="327" /></a></li>
    		</c:forEach>
    		<c:if test="${empty imgList}">
    		  <li><a href="#"><img src="${ctx}/styles/login/images/8he.png" title="" alt="8核就是快！" width="222" height="327" style="border:0px;" /></a></li>
    		  <li><a href="#"><img src="${ctx}/styles/login/images/8he.png" title="" alt="8核就是快！" width="222" height="327" style="border:0px;" /></a></li>
    		  <li><a href="#"><img src="${ctx}/styles/login/images/8he.png" title="" alt="8核就是快！" width="222" height="327" style="border:0px;" /></a></li>
    		</c:if>
        </ul>
        </div> 
        <div class="sub_l">
            <div class="sub_1">
              <div class="sub_1_l">
                <div class="sub_1_l1"></div>
                <div class="sub_1_l2"></div>
                <div class="sub_1_l3"></div>
              </div>
              <div class="sub_r1"><img src="${ctx}/styles/login/images/shijian.png" width="76" height="74" /></div>
            </div>
            <div class="sub_l01">
              <div class="tianqiyub">
				<iframe src="http://konka.mymyty.com/w.html?t=123123" frameborder="0" width="100%" style="margin:-20px 0px 0px 0px; padding: 0px; height: 100px;"></iframe>
			  </div>
            </div>
          </div>
          <div class="ios"><a href="http://files.mymyty.com/download/konka/" target="_blank"><img src="${ctx}/styles/login/images/and1.png" width="222" height="159" /></a></div>
          <div class="ios1"><a href="http://files.mymyty.com/download/konka/" target="_blank"><img src="${ctx}/styles/login/images/iph1.png" width="222" height="159" /></a></div>
        </div>
        <div class="sub_bottom">
          <div class="chenghao">
            	<div class="huan"><img src="${ctx}/styles/login/images/huan.png" width="37" height="36" /></div>
            	<div id="scrollDiv"> 
            	<ul>
	            <c:forEach var="cur" items="${articleList}">
	            	<li><a href="${ctx}/webservice/KonkaPeArticleInfoInterface.do?method=view&id=${cur.id}" title="${cur.title}" style="color:#fff;" target="_blank">
	            		<span style="font-size:17px">${fnx:abbreviate(cur.title, 16 * 2 ,'...')}</span>
	            	【<fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" />】</a></li>
	            </c:forEach>
	            </ul>
	            </div>
          	</div>
          <div class="ios2"><a href="${ctx}/customer/"><img src="${ctx}/styles/login/images/khdl.png" width="222" height="159" /></a></div>
        </div>
      </div>
      <html-el:form action="/login" method="post">
    	<html-el:hidden property="method" value="login" />
    	<html-el:hidden property="url" value="${url}" />
      <div class="m_r">
        <div class="denglu">
          <div class="gldl">管理端用户登陆</div>
          <div class="dd"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tdTab">
          <tr>
            <td width="28%" align="center">用户名:</td>
            <td width="72%" colspan="2"><html-el:text property="user_name" styleId="user_name" value="${user_name}" maxlength="32" tabindex="1" styleClass="webinput" /></td>
            </tr>
          
          <tr>
            <td align="center">密　码:</td>
            <td colspan="2"><html-el:password property="password" value="${password}" styleId="password" styleClass="webinput2" maxlength="32" tabindex="2" /></td>
            </tr>
          <c:if test="${isEnabledCode eq '1'}">
          <tr>
            <td align="center" style="padding-top:5px">验证码:</td>
            <td width="140" valign="middle"><input type="text" name="verificationCode" id="verificationCode" tabindex="3" class="webinput2" style="width:80px;" maxlength="4" /></td>
            <td valign="middle"><img src="${ctx}/images/VerificationCode.jpg" width="60" height="21" src="${ctx}/images/VerificationCode.jpg" style="border:1px solid #A1BCA3;cursor:pointer;" alt="验证码，看不清楚请用鼠标点击此处！" onclick="$(this).hide().attr('src', this.src + '?' + new Date().getTime()).fadeIn();" /></td>
          </tr>
          </c:if>
          <tr>
            <td align="left" valign="bottom">&nbsp;</td>
            <td align="left" valign="bottom"><input name="button" type="submit" class="websub" id="button" value="" /></td>
            <td width="39%"><input type="checkbox" name="is_remember" value="1" id="is_remember" style="vertical-align:middle;padding:0;margin:0 3px 3px 0 " />
              <font style="font-size:12px; color: #666;">记住密码</font></td>
            </tr>
          </table>
        </div></div>
        <div class="jizhu"><a href="#">忘记密码</a> | <a href="#">意见反馈</a></div>
      </div></html-el:form>
      <div class="clear"></div>
    </div>
  <div class="fot">康佳集团 版权所有 KONKA 2013.All Rights Reserved 　　　　</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.smallslider.js"></script>
<script type="text/javascript">//<![CDATA[
(function($){ 
	$.fn.extend({ 
		Scroll:function(opt, callback){ 
			var timerID = null;
			//参数初始化 
			if(!opt) var opt = {}; 
			var _this = this.eq(0).find("ul:first");
			
			var lineH = _this.find("li:first").height(), //获取行高 
			line = opt.line ? parseInt(opt.line, 10) : parseInt(this.height() / lineH, 10), //每次滚动的行数，默认为一屏，即父容器高度 
			speed = opt.speed ? parseInt(opt.speed, 10) : 500, //卷动速度，数值越大，速度越慢（毫秒） 
			timer = opt.timer ? parseInt(opt.timer, 10) : 3000; //滚动的时间间隔（毫秒） 
			if(line == 0) line = 1; 
			var upHeight = 0 - line * lineH;
			
			//鼠标事件绑定 
			var timerID = null;
			_this.hover(function(){ 
				if (!!timerID) clearInterval(timerID); 
			},function(){
				timerID = setInterval(function(){
					_this.animate({
						marginTop : upHeight 
					}, speed, function(){ 
						for(var i = 1; i <= line; i++){ 
							_this.find("li:first").hide().appendTo(_this).fadeIn(); 
						}
						_this.css({marginTop:0}); 
					});
				}, timer); 
			}).mouseout();
		}
	});
})(jQuery);

$(document).ready(function(){
	$("#scrollDiv").Scroll({line:1, speed:500, timer:1500}); 
	
	$('#imgplayer1').smallslider({
        onImageStop:true,
        switchEffect:'ease',
        switchEase: 'easeOutQuart',
        switchPath: 'up',
        switchMode: 'hover',
        switchTime : 1000,
        time : 5000,
        showText:true,
        onImageStop : true,
        textSwitch:2,
        showButtons:false
    });
	
	browserRedirect();
	
	$("#user_name" ).attr("dataType", "Require").attr("msg", "请填写您的用户名！");
	
	<c:if test="${not isDebugMode}">
	$("#password"  ).attr("dataType", "Require").attr("msg", "请填写您的密码！");
	if ("${isEnabledCode}" == "1") {
		$("#verificationCode" ).attr("dataType" ,"Custom" ).attr("msg", "验证码必须是一个4位的数字").attr("regexp", "\\d{4}");
	}
	</c:if>
	
	$(document.forms[0]).submit(function(){
		return Validator.Validate(this, 1);
	});
	
	if ("true" == "${not empty (is_remember)}") {
		$("#is_remember")[0].checked = true;
	}
	
	var s = "屏幕分辨率：" + window.screen.width + "×" + window.screen.height;
	if (window.screen.width <= 1024) {
		s = "系统检测到您的分辨率（" + window.screen.width + "×" + window.screen.height + "）过低，建议您使用更高的分辨率以获得更佳的用户体验。";
	}
	$("#screen_tip").append(s);
	
	function resizeBody() {
		var width = $(window).width();
		
		if ( width < 1024) {
			$('#mainimg').attr('src', 'images/login/954.gif');
			$('#dynaTD').width(590);
		} else if ( width >= 1024) {
			$('#mainimg').attr('src', 'images/login/im_1.gif');
			$('#dynaTD').width(760);
		}
		
		$('#main').css('margin-top', Math.max(($(window).height() - $('#main').height()) / 2, 0));
	}
	
	resizeBody();
	
	$(window).resize(resizeBody);

	//设置时间
	var server_time = parseInt("${now}");
    var client_time = new Date().getTime();  
    var diff = server_time - client_time;
	timeChange(diff);  
 
}); 

function timeChange(diff) {  
     var myDate = new Date();
     myDate.setTime(myDate.getTime() + diff);
     var weeks = ['星期日','星期一','星期二','星期三','星期四','星期五','星期六']; 
     var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
     var month = myDate.getMonth() + 1;       //获取当前月份(0-11,0代表1月)
     var day = myDate.getDate();        //获取当前日(1-31)
     var week = weeks[myDate.getDay()];  //获取当前星期X(0-6,0代表星期天)
     var hour = myDate.getHours();       //获取当前小时数(0-23)
     var min = myDate.getMinutes();     //获取当前分钟数(0-59)
     $(".sub_1_l1").html(hour + ":" + (min < 10 ? ("0" + min) : min));
     $(".sub_1_l2").html(year + "年" + month + "月" + day + "日");
     $(".sub_1_l3").html(week);  
     setTimeout("timeChange(" + diff + ");", 1000);
} 

function browserRedirect() {  
    var sUserAgent = navigator.userAgent.toLowerCase();  
    var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";  
    var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";  
    var bIsMidp = sUserAgent.match(/midp/i) == "midp";  
    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";  
    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";  
    var bIsAndroid = sUserAgent.match(/android/i) == "android";  
    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";  
    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
    if (bIsAndroid){
        //window.location.href="${ctx}/mobile";
    } 
}  
//]]></script>
<jsp:include page="/__ie6_check.jsp" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
