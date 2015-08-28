<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<script type="text/javascript"> !window.jQuery && document.write('<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"><\/script >'); </script>
<script type="text/javascript"> !window.jQuery && document.write('<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"><\/script >'); </script>

<div id="top_topbox">
  <div id="top_topt">
    <div class="top_top1">
      <div class="top_topr">欢迎您！
        <c:if test="${not empty touchUserInfo}">${touchUserInfo.real_name}&nbsp;|<a href="${ctx}/touch/center/Index.do">会员中心</a>|<a href="${ctx}/touch/login.do?method=logout">退出登陆</a></c:if>
        <c:if test="${empty touchUserInfo}">[<a id="top_login" style="cursor:pointer;">请登录</a>]</c:if>
        </div>
      <div class="top_topl">
        <h4><cite><img src="${ctx}/styles/touch/images/ico_2.gif" /></cite>&nbsp;&nbsp;<a href="<c:url value='/touch/manager/Index.do' />">欢迎光临康佳触网平台!</a></h4>
       
      </div>
      <div class="top_clear"></div>
    </div>
  </div>
</div><c:if test="${empty touchUserInfo}">
<div id="login" style="display:none;z-index:99999;">
  <form action="${ctx }/touch/login.do" method="post" style="margin:0;padding:0;">
  	<input type="hidden" name="method" value="login" />
    <div class="top_login_heard">
      <div class="top_login_left">会员登录</div>
      <div class="top_login_right"><a href="javascript:void(0);" id="login_close" title="close">关闭</a></div>
    </div>
    <div class="top_login_content">
      <div class="login_top">
        <div class="top_login_left">用户名:</div>
        <div class="top_login_right">
          <input id="txtUserName" name="user_name" type="text" maxlength="20" tabindex="1"/>
        </div>
      </div>
      <div class="login_top">
        <div class="top_login_left">密&nbsp; 码:</div>
        <div class="top_login_right">
          <input id="txtPassword"  name="password" type="password" maxlength="20" tabindex="2"/>
        </div>
      </div>
      <div class="login_top">
        <div class="top_login_left">验证码:</div>
        <div class="top_login_right">
          <input type="text" name="verificationCode" id="verificationCode" tabindex="3"  maxlength="4" style="width:80px;color:#000;" />
          <img src="${ctx}/images/VerificationCode.jpg" width="76" id="vfc" height="27" style="border:1px solid #A1BCA3;cursor:pointer;margin-top:0px;" alt="验证码，看不清楚请用鼠标点击此处！" onclick="$(this).hide().attr('src', this.src + '?' + new Date().getTime()).fadeIn();" /> </div>
      </div>
      <div class="top_login_bottom-btn">
        <input id="btnConnect" type="button" class="top_login_btn-submit" value="登　 录" />
        <input id="btnCancel" type="button" class="top_login_button" style="margin-left:20px;" value="取　 消" />
        <font id="message"></font></div>
    </div>
  </form>
</div>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#btnConnect").click(function() {
		if ($("#txtUserName").val().length < 1) {
			$("#message").css("color", "red"); $("#message").html("请输入用户名");
		}else if ($("#txtPassword").val().length < 1) {
			$("#message").css("color", "red"); $("#message").html("请输入密码");
		}else if ($("#verificationCode").val().length < 4) {
			$("#message").css("color", "red"); $("#message").html("请输入验证码");
		}else{
			$("#btnConnect").attr("disabled", "true");
			$("#message").css("color", "green"); $("#message").html("正在验证...请稍后");
			this.form.submit();
		}
	});
 
	var screenwidth, screenheight, mytop, getPosLeft, getPosTop;
		screenwidth = $(window).width(); 
		screenheight = $(window).height();
		//获取滚动条距顶部的偏移 
		mytop = $(document).scrollTop();
		//计算弹出层的left
		getPosLeft = screenwidth / 2 - 200;
		//计算弹出层的top 
		getPosTop = screenheight / 2 - 150;
		//css定位弹出层 
		$("#login").css({ "left": getPosLeft, "top": getPosTop });
	//当浏览器窗口大小改变时 
	$(window).resize(function() {
		screenwidth = $(window).width();
		screenheight = $(window).height();
		mytop = $(document).scrollTop();
		getPosLeft = screenwidth / 2 - 200;
		getPosTop = screenheight / 2 - 150;
		$("#login").css({ "left": getPosLeft, "top": getPosTop + mytop });
	});
	//当拉动滚动条时，弹出层跟着移动 
	$(window).scroll(function(){
		screenwidth = $(window).width(); 
		screenheight = $(window).height();
		mytop = $(document).scrollTop();
		getPosLeft = screenwidth / 2 - 200;
		getPosTop = screenheight / 2 - 150;
		$("#login").css({ "left": getPosLeft, "top": getPosTop + mytop });
	});
	
	$(document).delegate("#top_login", "click", function(){
		// showLogin();
		location.href = "<c:url value='/touch/login.do' />";
	}); 
	//点击关闭按钮 
	$("#login_close").click(function() {
		hideLogin();
	});
	
	$("#btnCancel").click(function(){
		hideLogin();
	});

});

function showLogin(){//显示登陆框  
	document.getElementById("vfc").src="${ctx}/images/VerificationCode.jpg?" + new Date().getTime();//刷新验证码
	$("#login").fadeIn("slow");
	var docheight = $(document).height(); //获取页面文档的高度 
	$("body").append("<div id='greybackground' style='z-index:99990;'></div>"); //追加一个层，使背景变灰 
	$("#greybackground").css({ "opacity": "0.5", "height": docheight });
	return false;
}

function hideLogin(){
	$("#login").fadeOut("slow"); 
	$("#greybackground").remove();//删除变灰的层 
	return false; 
}
//]]></script></c:if>