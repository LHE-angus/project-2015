<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>

<div data-role="page">
  <div data-role="header" data-position="inline">
    <h1>到店登记</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
	<a onclick="logout();" data-icon="forward" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a>
  </div>
<form id="iForm">
    <div data-role="fieldcontain">
    	<label for="date">到店日期</label>
		<input type="text" name="date" id="date" readonly="readonly">
    	<label for="shop_visit_status">到店状态</label>
		<select name="visit_status" id="visit_status" data-native-menu="false" data-theme="b">
			<option value="1">路上</option>
	        <option value="2">已到店</option>
		</select>
	    <label for="shop_id">客户名称</label>
	   	<select name="shop_id" id="shop_id" data-native-menu="false" data-theme="b">
			<c:forEach var="cur" items="${shopList}">
			<option value="${cur.shop_id}"><c:out value="${cur.shop_name}"/></option>
			</c:forEach>
		</select>
		<textarea name="visit_note" id="visit_note" data-native-menu="false" data-theme="b" data-form="ui-btn-up-a">到店说明</textarea>
		<input type="hidden" id="method" name="method" />
		<input type="hidden" id="shop_name" name="shop_name" />
	</div>
</form>

<div data-role="footer" class="ui-bar" data-position="fixed">
   	<div data-role="controlgroup"  data-type="horizontal">
		<a id="submitBtn" data-role="button" data-icon="plus" data-theme="b" data-inline="true">提交</a>
	</div>
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
		$("#shop_name").val($("#shop_id option:selected").text());
		var options = { 
				url:"VisitDone.do",
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