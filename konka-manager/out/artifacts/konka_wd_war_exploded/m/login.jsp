<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>康佳零售通</title>
<link rel="stylesheet" href="${ctx}/m/themes/konka.min.css" />
<!-- 
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.css" />
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>
 -->
<link rel="stylesheet" href="${ctx}/m/jqm/jquery.mobile-1.3.1.min.css" />
<script src="${ctx}/m/jqm/jquery-1.9.1.min.js"></script>
<script src="${ctx}/m/jqm/jquery.mobile-1.3.1.min.js"></script>
<script src="${ctx}/commons/scripts/jquery.form.js" ></script>
<style type="text/css">
.ui-content { padding:0px 0.5em; }
.ui-dialog-contain {
	width: 92.5%;
	max-width: 500px;
	margin: 10% auto 15px auto;
	padding: 0;
	position: absolute;
	top: -25px;
	font-size:0.5em;
	color:#F00;
	text-align:center;
}
</style>
</head>
<body>
<!-- Start of first page -->
<div data-role="page" id="page"> 　　　
  <div data-role="header" data-position="fixed" data-theme="b"> 　　　　　
    <div style="font-size:1.2em;font-weight:900;text-align:center;margin-bottom:0.7em;font-family:微软雅黑,宋体;">KONKA 康佳零售通</div>
  </div>
  <!-- /header -->
  
  <div data-role="content" style="position:relative;">
    <div id="dialog"></div>
    <form id="iForm">
      <div data-role="fieldcontain">
        <label for="user_name"> 用户名 </label>
        <input name="user_name" id="user_name" placeholder="请输入用户名/手机号码" value="" type="text" data-mini="true">
      </div>
      <div data-role="fieldcontain">
        <label for="password"> 密码 </label>
        <input name="password" id="password" placeholder="请输入密码" value="" type="password" data-mini="true">
      </div>
	  <div data-role="fieldcontain" data-inline="true">
		<input id="show_pass" name="show_pass" type="checkbox" data-mini="true" />
		<label for="show_pass" id="show_pass_label">密码显示明文</label>
      </div>
    </form>
  </div>
  <!-- /content -->
  
  <div data-role="footer" data-position="fixed" data-theme="b" style="padding:10px 0px;text-align:center;">
	<a data-role="button" data-theme="b" data-icon="check" id="loginBtn" data-inline="true"> 登录 </a>
  </div>
  <!-- /footer --> 
</div>
<!-- /page -->
<script type="text/javascript">//<![CDATA[
$(document).ready(function () {
	
	var setPassType = function(){ $("#password").attr("type", !$("#show_pass").is(':checked') ? "text" : "password"); };
	
	$(document).delegate("#show_pass_label", "click", setPassType);
	
	/*
	$(document).bind( 'mobileinit', function(){
		$.mobile.loader.prototype.options.text = "登录中...";
		$.mobile.loader.prototype.options.textVisible = true;
		$.mobile.loader.prototype.options.theme = "b";
		$.mobile.loader.prototype.options.html = "登录中...";
	});
	*/
	
	$(document).delegate("#loginBtn", "click", function() {
		$.mobile.loading( 'show' , {
			text: '登录中...',
			textVisible: true,
			theme: 'b'
		});
		$("#iForm").ajaxSubmit({
			url:"${ctx}/m/login.do?method=login",
			type:"POST",
			success:function(msg) {
				if(msg == "success") {
					//location.href = "${ctx}/m/admin/Frames.do";
					location.href = "${ctx}/mobile/admin/Frames.do";
				} else {
					//$("#dialog").html(msg).dialog({ overlayTheme: "b" });
					alert(msg);
				}
				$.mobile.loading( 'hide' );
			}
		});
	});
	
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>