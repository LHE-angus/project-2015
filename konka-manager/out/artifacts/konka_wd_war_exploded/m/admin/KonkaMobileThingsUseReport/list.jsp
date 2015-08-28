<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>零售通-康佳电器</title>
</head>
<body>
<div data-role="page">
  <div data-role="header" data-position="inline">
    <h1>终端物料上报</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
	<a onclick="logout();" data-icon="forward" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a>
  </div>
  
<form id="iForm">
<div data-role="fieldcontain">
	<select name="thing_id" id="thing_id" data-native-menu="false" data-theme="b" data-form="ui-btn-up-a">
	  <option value="">物料名称</option>
	  <c:forEach items="${wuliaoList}" var="cur">
	  <option value="${cur.id}">${cur.name}</option>
	  </c:forEach>
	</select>
	<input type="text" value="使用量" name="sales_count" id="sales_count" class="input" data-form="ui-body-a" onkeyup="javascript:setOnlyInter(this);"/>
	<input type="hidden" id="method" name="method" />
</div>
</form>

<div data-role="footer" class="ui-bar" data-position="fixed">
   	<div data-role="controlgroup"  data-type="horizontal">
		<a onclick="refresh();" data-role="button" data-icon="refresh" data-theme="b" data-inline="true">重置</a>
		<a id="submitBtn" data-role="button" data-icon="plus" data-theme="b" data-inline="true">提交</a>
	</div>
</div>

<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#sales_count").click(function() {
		$("#sales_count").val("");
	});
	$("#submitBtn").click(function() {
				$("#method").val("save");
				var options = { 
						url:"KonkaMobileThingsUseReport.do",
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