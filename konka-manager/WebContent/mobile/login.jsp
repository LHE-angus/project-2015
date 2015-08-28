<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<c:redirect url="/m/login.do" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>康佳电器渠道管理系统</title>
<link rel="stylesheet" href="${ctx}/mobile/themes/konka.min.css" />
<link rel="stylesheet" href="${ctx}/commons/styles/jquery.mobile.structure-1.0.min.css" />
<script src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<script src="${ctx}/commons/scripts/jquery.mobile-1.0.min.js"></script>
<script src="${ctx}/commons/scripts/jquery.form.js" ></script>
 <style type="text/css">
 #div_time{
 display:none;
 font:24px;
 }
 #div_body{
 display:inline;
 }
 </style>
</head>
<body>
<div data-role="page">
<div class="theme-preview">
	<div data-role="header" data-position="inline" >
		<h1>智能手机版-康佳电器</h1>
	</div>
	<div data-role="content">
		<div data-role="fieldcontain">
		<form id="iForm">
			<div data-role="fieldcontain">
		         <label for="user_name">用户名:</label>
		         <input type="text" name="user_name" id="user_name" value="" maxlength="50" />
			</div>
			<div data-role="fieldcontain">
		         <label for="password">密码 :</label>
		         <input type="password" name="password" id="password" value="" maxlength="50" autocomplete="off" />
			</div>			
		</form>
		</div>
	</div>
</div>
	<div data-role="footer" class="ui-bar" data-position="fixed">
	   	<div data-role="controlgroup"  data-type="horizontal">
			<a id="submitBtn" data-role="button" data-icon="check" data-theme="c" data-inline="true">登录</a>
		</div>
	</div>
<script type="text/javascript">
//<![CDATA[
$(document).ready(function (){
	$("#password").click(function() {
		$("#password").val("");
	});

	$("#submitBtn").click(function() {
		var options = { 
				url:"login.do?method=login",
				data:{'username':$("#user_name").val(),'password':$("#password").val()},
				type:"POST",
				success:function(msg){
					if(msg == "success"){
						location.href = "admin/Frames.do";
					}else{
						alert(msg);
					}
				}
		    };
		$("#iForm").ajaxSubmit(options);
	});
});
//]]>
</script>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>