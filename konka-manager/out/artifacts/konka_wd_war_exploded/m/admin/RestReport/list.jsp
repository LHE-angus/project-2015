<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>零售通-康佳电器</title>
<link rel="stylesheet" href="${ctx}/m/jqm/jquery.mobile-1.3.1.min.css" />
<script src="${ctx}/m/jqm/jquery-1.9.1.min.js"></script>
<script src="${ctx}/m/jqm/jquery.mobile-1.3.1.min.js"></script>
</head>
<body>
<div data-role="page">
  <div data-role="header" data-position="inline" data-theme="b">
    <h1>休息上报</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a> <a onclick="logout();" data-icon="forward" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a> </div>
  <form id="iForm">
    <div data-role="fieldcontain">
      <input type="text" name="date" id="date" readonly="readonly">
      <textarea name="retail_desc" id="retail_desc" data-native-menu="false" data-theme="b" data-form="ui-btn-up-a">休息说明</textarea>
      <input type="hidden" id="method" name="method" />
    </div>
  </form>
  <div data-role="footer" class="ui-bar" data-position="fixed" data-theme="b">
    <div data-role="controlgroup"  data-type="horizontal"> <a onclick="refresh();" data-role="button" data-icon="refresh" data-theme="b" data-inline="true">重置</a> <a id="submitBtn" data-role="button" data-icon="plus" data-theme="b" data-inline="true">提交</a> </div>
  </div>
  <script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#retail_desc").click(function(){
		$("#retail_desc").val("");
	});

	$('#date').scroller({theme: "default",mode:"clickpick"});

	var nowDate = new Date();
	$('#date').val((nowDate.getMonth() + 1) + "/" + nowDate.getDate() + "/" +nowDate.getFullYear());

	$("#submitBtn").click(function() {
				$("#method").val("save");
				var options = { 
						url:"RestReport.do",
						type:"POST",
						success:function(msg){
							if(msg == "success"){
								location.href = "Frames.do";
							}else{
								alert(msg);
							}
						}
				    };
				$("#iForm").ajaxSubmit(options);
			});
});
//]]></script>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>