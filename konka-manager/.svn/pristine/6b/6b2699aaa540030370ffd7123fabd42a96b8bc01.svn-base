<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户端登录——康佳渠道管理系统</title>
<link href="${ctx}/styles/customer-login/css/login.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.smallslider {
	position:relative;
	padding:0;
	margin:0;
	overflow:hidden;
}
.smallslider ul {
	list-style-type:none;
	padding:0;
	margin:0;
	position: absolute;
	width:auto;
	height:auto;
}
.smallslider li {
	margin:0;
	padding:0;
}
.smallslider li a {
	margin:0;
	padding:0;
}
.smallslider li a img {
	border:0;
	padding:0;
	margin:0;
	vertical-align:top;
}
.smallslider h3 {
	position:absolute;
	font-weight:bold;
	font-size:12px;
	margin:0;
	padding:0;
	text-indent:2%;
	line-height:26px;
	z-index:102;
	width:98%;
	color:#CCC;
}
.smallslider h3 a {
	padding:0;
	margin:0;
	text-indent:0;
}
.smallslider h3 a:link, .smallslider h3 a:visited {
	text-decoration:none;
	color:#FFFFFF;
}
.smallslider h3 a:hover {
	text-decoration:underline;
	color:#FF6600;
}
.smallslider li.current-li {
}
.smallslider-btns {
	position:absolute;
	z-index:103;
}
.smallslider-btns span {
	background-color:#FFFFFF;
	border:1px solid #DCDCDC;
	color:#9F9F9F;
	cursor:pointer;
	float:left;
	font-size:12px;
	height:16px;
	line-height:16px;
	text-align:center;
	width:16px;
}
.smallslider-btns span.current-btn {
	background-color:#C00100;
	border:1px solid #A00100;
	color:white;
	font-size:13px;
	font-weight:bold;
}
.smallslider-lay {
	position:absolute;
	background:black;
	height:26px;
	width:100%;
	z-index:101;
}
.logintd1 {
	padding-top: 8px;
	height: 28px;
	text-align: center;
}
.input_login {
	width: 150px;
	height: 26px;
	border: 1px solid #CCC;
	font: normal 12px/32px "宋体";
	color: #999;
	padding-top: 0;
	padding-right: 2px;
	padding-bottom: 0;
	padding-left: 2px;
	display: inline;
}
.input_login2 {
	width: 100px;
	height: 26px;
	border: 1px solid #CCC;
	font: normal 12px/32px "宋体";
	color: #999;
	padding-top: 0;
	padding-right: 2px;
	padding-bottom: 0;
	padding-left: 2px;
	display: inline;
}
</style>
</head>
<body>
<div class="login-title"><img src="${ctx}/styles/customer-login/images/kanka-login.png"  height="29" /></div>
<div class="Wallpaper">
  <div class="login-l">
    <div id="imgplayer1" class="sub_r smallslider" style="width:258px;height:311px;">
      <ul>
        <c:forEach var="cur" items="${imgList_1201}">
          <li><a href="${ctx}/${cur.image_url}" target="_blank"><img src="${ctx}/${cur.image_path}" title="" alt="${cur.title}" width="258" height="311" /></a></li>
        </c:forEach>
        <c:if test="${empty imgList_1201}">
          <li><a href="#"><img src="${ctx}/styles/customer-login/images/img-guanggao.jpg" title="尖端科技，极致艺术！" alt="尖端科技，极致艺术！" width="258" height="311" style="border:0px;" /></a></li>
          <li><a href="#"><img src="${ctx}/styles/customer-login/images/img-guanggao.jpg" title="尖端科技，极致艺术！" alt="尖端科技，极致艺术！" width="258" height="311" style="border:0px;" /></a></li>
          <li><a href="#"><img src="${ctx}/styles/customer-login/images/img-guanggao.jpg" title="尖端科技，极致艺术！" alt="尖端科技，极致艺术！" width="258" height="311" style="border:0px;" /></a></li>
        </c:if>
      </ul>
    </div>
  </div>
  <div class="login-c">
    <div class="login-c-t">
      <div class="time"><span class="font36"><span id="time" style="font-size:80%;"></span>
        <p class="font14" id="date">2013年9月15日</p>
        <p class="font14" id="week">星期日</p>
        </span></div>
      <div class="time-r"></div>
    </div>
    <div class="login-c-b">
      <div id="imgplayer2" class="sub_r smallslider" style="width:380px;height:179px;">
        <ul>
          <c:forEach var="cur" items="${imgList_1202}">
            <li><a href="${ctx}/${cur.image_url}" target="_blank"><img src="${ctx}/${cur.image_path}" title="" alt="${cur.title}" width="380" height="179" /></a></li>
          </c:forEach>
          <c:if test="${empty imgList_1202}">
            <li><a href="http://www.kktvmall.com/"><img src="${ctx}/styles/customer-login/images/kktv-ad.jpg" title="" alt="康佳新感官革命" width="380" height="179" style="border:0px;" /></a></li>
            <li><a href="http://www.kktvmall.com/"><img src="${ctx}/styles/customer-login/images/kktv-ad.jpg" title="" alt="康佳新感官革命" width="380" height="179" style="border:0px;" /></a></li>
            <li><a href="http://www.kktvmall.com/"><img src="${ctx}/styles/customer-login/images/kktv-ad.jpg" title="" alt="康佳新感官革命" width="380" height="179" style="border:0px;" /></a></li>
          </c:if>
        </ul>
      </div>
    </div>
  </div>
  <div class="loginbox">
    <h3 class="logintit" style="padding-bottom:10px;">客户登录</h3>
    <html-el:form action="/login" method="post" style="margin:0px;padding:0px;" styleClass="touch_form">
      <html-el:hidden property="method" value="login" />
      <html-el:hidden property="url" value="${url}" />
      <html-el:hidden property="type1" value="${af.map.type1}" styleId="type1" />
      <table width="330" border="0" cellpadding="0" cellspacing="0">
        <tbody>
          <tr>
            <td width="30%" class="logintd1"><font>用户名</font></td>
            <td colspan="2"><html-el:text property="user_name" styleId="user_name" value="${user_name}" size="30" maxlength="32" tabindex="1" styleClass="input_login" /></td>
          </tr>
          <tr>
            <td class="logintd1"><font>密　码</font></td>
            <td colspan="2"><html-el:password property="password" value="${password}" styleId="password" styleClass="input_login" size="30" maxlength="32" tabindex="2" /></td>
          </tr>
          <tr>
            <td class="logintd1"><font>验证码</font></td>
            <td width="30%" valign="middle"><input type="text" name="verificationCode" id="verificationCode" tabindex="3" class="input_login2" style="width:80px;" maxlength="4" /></td>
            <td width="40%" align="left" valign="middle"><img src="${ctx}/images/VerificationCode.jpg" width="80" height="21" style="border:1px solid #A1BCA3;cursor:pointer;width:70px;" alt="验证码，看不清楚请用鼠标点击此处！" onclick="$(this).hide().attr('src', this.src + '?' + new Date().getTime()).fadeIn();" /></td>
          </tr>
          <tr>
            <td align="center" valign="middle" class="logintd1"><span>登录类型</span></td>
            <td colspan="2" align="left" valign="middle" class="logintd1" style="text-align:left;"><span>
              <input name="login_type" type="checkbox" class="input22" value="1" id="login_type_1" onclick="chooseOne(this);" checked="checked" />
              <label for="login_type_1" style="cursor:pointer;" data="1">产品展示端</label>
              <input name="login_type" type="checkbox" class="input22" value="0" id="login_type_0" onclick="chooseOne(this);" />
              <label for="login_type_0" style="cursor:pointer;">后台管理端</label></span></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td class="logintd3" colspan="2"><input type="checkbox" name="is_retouch" value="1" id="is_retouch" style="vertical-align:middle;padding:0;margin:0 3px 3px 0 " />
              <font style="font-size:12px; color: #666;">记住密码</font></td>
          </tr>
          <tr>
            <td colspan="3" class="logintd4" align="center"><a title="登录" onclick="$('#loginForm').submit();" style="cursor:pointer;"><img alt="登录" src="${ctx}/styles/customer-login/images/but_login.gif" width="307" height="34"></a></td>
          </tr>
        </tbody>
      </table>
    </html-el:form>
  </div>
</div>
<div class="foot">康佳集团 版权所有 KONKA 2013.All Rights Reserved<br>
  
  网站管理：多媒体渠道管理部  运营电话：0755-26608866-6303</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.smallslider.js"></script> 
<script type="text/javascript">//<![CDATA[
/**
 * @param   d the delimiter
 * @param   p the pattern of your date
 * @author  meizz
 * @author  kimsoft add w+ pattern
 */
Date.prototype.format = function(style) {
	var o = {
		"M+" : this.getMonth() + 1, //month
		"d+" : this.getDate(),      //day
		"h+" : this.getHours(),     //hour
		"m+" : this.getMinutes(),   //minute
		"s+" : this.getSeconds(),   //second
		"w+" : "\u65e5\u4e00\u4e8c\u4e09\u56db\u4e94\u516d".charAt(this.getDay()),   //week
		"q+" : Math.floor((this.getMonth() + 3) / 3),  //quarter
		"S"  : this.getMilliseconds() //millisecond
	}
	if (/(y+)/.test(style)) {
		style = style.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for(var k in o){
		if (new RegExp("("+ k +")").test(style)){
			style = style.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return style;
};

$(document).ready(function(){
	$("#user_name").attr("dataType", "Require").attr("msg", "请填写您的用户名！");
	
	if ("true" != "${isDebugMode}") {
		$("#password").attr("dataType", "Require").attr("msg", "请填写您的密码！");
		if ("${isEnabledCode}" == "1") {
			$("#verificationCode" ).attr("dataType" ,"Custom" ).attr("msg", "验证码必须是一个4位的数字").attr("regexp", "\\d{4}");
		}
	}

	$(document.forms[0]).submit(function(){
		return Validator.Validate(this, 1);
	});
	
	if ("true" == "${not empty (is_retouch)}") {
		$("#is_retouch")[0].checked = true;
	}

	$("li.li_class a").click(function(){
		var id = $(this).attr("id");
		$("#type1").val(id);	
		var a = $(this).removeClass("hui1").addClass("hui");
		var ap = a.parent().addClass("xuanzhong");
		ap.siblings().removeClass("xuanzhong").find("a").removeClass("hui").addClass("hui1");
	});
	

	$('#imgplayer1').smallslider({
        onImageStop:true,
        switchEffect:'ease',
        switchEase: 'easeOutQuart',
        switchPath: 'left',
        switchMode: 'hover',
        switchTime : 1000,
        time : 5000,
        showText:true,
        onImageStop : true,
        textSwitch:2,
        showButtons:false
    });
	
	$('#imgplayer2').smallslider({
        onImageStop:true,
        switchEffect:'ease',
        switchEase: 'easeOutQuart',
        switchPath: 'up',
        switchMode: 'hover',
        switchTime : 1000,
        time : 5000,
        showText:false,
        onImageStop : true,
        textSwitch:2,
        showButtons:false
    });
	
	setInterval(function(){
		var now = new Date();
		$("#time").text(now.format("hh:mm:ss"));
		$("#date").text(now.format("yyyy年M月d日"));
		$("#week").text(now.format("星期w"));
	}, 1000);
	
	// 回车提交表单
	document.onkeydown = function() { if(event.keyCode == 13) $("form")[0].submit(); }; 
}); 

function chooseOne(cb){     
    var obj = document.getElementsByName("login_type");     
    for (i=0; i < obj.length; i++){     
        if (obj[i] == cb) {
        	obj[i].checked = true;
        	if(obj[i].value == 1){
        		
        	}
        } else {
        	obj[i].checked = false; 
        }   
    }     
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
