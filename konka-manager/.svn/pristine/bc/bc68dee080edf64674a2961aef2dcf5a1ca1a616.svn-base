<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>零售通-康佳电器</title>
<link rel="stylesheet" href="${ctx}/mobile/themes/konka.min.css" />
<link rel="stylesheet" href="${ctx}/commons/styles/jquery.mobile.structure-1.0.min.css" />
<link href="${ctx}/styles/mobiscroll-1.5.2.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<script src="${ctx}/commons/scripts/jquery.mobile-1.0.min.js"></script>
<script src="${ctx}/commons/scripts/jquery.form.js" ></script>
<script src="${ctx}/commons/scripts/mobiscroll-1.5.2.js" ></script>
</head>
<body>
<div data-role="page">
  <div data-role="header" data-position="inline">
    <h1>竞品数据上报</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
    <a onclick="logout();" data-icon="forward" data-iconpos="notext" class="ui-btn-right jqm-home">退出</a>
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
    <input type="text" value="型号" name="model" id="model" maxlength="15" data-form="ui-body-a" class="required"/>
    <input type="text" value="消量" name="sales_count" id="sales_count" maxlength="10" data-form="ui-body-b"/>
    <input type="text" value="价格" name="sales_price" id="sales_price" maxlength="15" data-form="ui-body-c"/>
    <input type="hidden" id="method" name="method" />
     <input type="hidden" id="mod_id" name="mod_id" value="${af.map.mod_id}"/>
  </div>
</form>

<div data-role="footer" class="ui-bar" data-position="fixed">
   	<div data-role="controlgroup"  data-type="horizontal"> 
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