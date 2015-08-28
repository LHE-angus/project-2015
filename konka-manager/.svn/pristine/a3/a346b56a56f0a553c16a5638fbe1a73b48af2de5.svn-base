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
    <h1>竞品数据上报</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
    <a onclick="logout();" data-icon="forward" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a>
  </div>

<form id="iForm">
  <div data-role="fieldcontain">
    <select name="select-choice-1" id="select-choice-1" data-native-menu="false" data-theme="b" data-form="ui-btn-up-a">
      <option value="">品牌</option>
        <c:forEach var="cur" items="${brandList}" varStatus="vs">
        	<option value="${cur.id}">${cur.name}</option>
        </c:forEach>
    </select>
    <select name="select-choice-3" id="select-choice-3" data-native-menu="false" data-theme="b" data-form="ui-btn-up-a">
      <option value="">尺寸</option>
        <c:forEach var="cur" items="${sizeList}" varStatus="vs">
        	<option value="${cur.id}">${cur.name}</option>
        </c:forEach>
    </select>
    <select name="select-choice-2" id="select-choice-2" data-native-menu="false" data-theme="b" data-form="ui-btn-up-a">
      <option value="">品类</option>
        <c:forEach var="cur" items="${baseList}" varStatus="vs">
        	<option value="${cur.id}">${cur.name}</option>
        </c:forEach>
    </select>
    <input type="text" value="型号" name="model" id="model" class="input" data-form="ui-body-a" />
    <input type="text" value="消量" name="sales_count" id="sales_count" class="input" data-form="ui-body-b" onkeyup="javascript:setOnlyInter(this);"/>
    <input type="text" value="价格" name="sales_price" id="sales_price" class="input" data-form="ui-body-c" onkeyup="javascript:setOnlyDouble(this);"/>
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
	$("#model").click(function(){
		$("#model").val("");
	});
	
	$("#sales_count").click(function(){
		$("#sales_count").val("");
	});
	
	$("#sales_price").click(function(){
		$("#sales_price").val("");
	});

	$("#submitBtn").click(function() {
				$("#method").val("save");
				var options = { 
						url:"CompeteReport.do",
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