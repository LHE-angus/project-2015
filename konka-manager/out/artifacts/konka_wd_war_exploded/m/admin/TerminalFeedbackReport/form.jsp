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
    <h1>咨询反馈</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
	<a onclick="logout();" data-icon="forward" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a>
  </div>
	<form id="iForm">
    <div data-role="fieldcontain">
      <select name="message_type" id="message_type" data-native-menu="false" data-theme="b" data-form="ui-btn-up-a">
        <option value="">留言类别</option>
        <c:forEach items="${mobileCategoryList}" var="cur">
        <option value="${cur.id}">${cur.name}</option>
        </c:forEach>
        <option value="0">其它</option>
      </select>
      <textarea name="content" id="content" data-native-menu="false" data-theme="b" data-form="ui-btn-up-a">留言内容</textarea>
      <input id="method" name="method" type="hidden">
    </div>
	</form>
<div data-role="footer" class="ui-bar" data-position="fixed" data-theme="b">
   	<div data-role="controlgroup"  data-type="horizontal">
		<a onclick="refresh();" data-role="button" data-icon="refresh" data-theme="b" data-inline="true">重置</a>
		<a id="submitBtn" data-role="button" data-icon="plus" data-theme="b" data-inline="true">提交</a>
		<a href="TerminalFeedbackReport.do?method=old" data-role="button" data-icon="grid" data-theme="b" data-inline="true">历史</a>
		<a href="TerminalFeedbackReport.do" data-role="button" data-icon="info" data-theme="b" data-inline="true">最新</a>
	</div>
</div>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function() {
		$("#content").click(function() {
			$("#content").val("");
		});
		
		$("#submitBtn").click(function() {
			$("#method").val("save");
			var options = { 
					url:"TerminalFeedbackReport.do",
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
	//]]>
</script>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>