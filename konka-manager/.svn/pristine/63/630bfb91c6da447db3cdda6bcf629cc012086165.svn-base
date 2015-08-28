<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<div data-role="page">
  <div data-role="header" data-position="inline" data-theme="b" >
    <h1>休息上报</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
	<a onclick="logout();" data-icon="forward" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a>
  </div>
<form id="iForm">
    <div data-role="fieldcontain">

      <input type="text" name="date" id="date" readonly="readonly"><br/>
      <textarea name="retail_desc" id="retail_desc" data-native-menu="false" data-theme="b" data-form="ui-btn-up-a">休息说明</textarea>

      <input type="hidden" id="method" name="method" />
      <input type="hidden" id="mod_id" name="mod_id" value="${af.map.mod_id}"/>
    </div>
</form>

<div class="content-primary">
<ul data-role="listview" data-divider-theme="b" data-inset="true">
    <li data-role="list-divider" role="heading">
        请假历史信息
    </li>
    <c:forEach var="cur" items="${restlist}" varStatus="vs">
	<li data-theme="c"><fmt:formatDate value="${cur.retail_date}" pattern="yyyy年MM月dd日"/> 请假申请 状态：
		<c:choose>
		<c:when test="${cur.status eq 0}">待审批</c:when>
		<c:when test="${cur.status eq 1}">未同意</c:when>
		<c:when test="${cur.status eq 2}">已同意</c:when>
		</c:choose>
	</li>
	</c:forEach>
</ul>
</div>
			
<div data-role="footer" class="ui-bar" data-position="fixed" data-theme="b" >
   	<div data-role="controlgroup"  data-type="horizontal">
		<a id="submitBtn" data-role="button" data-icon="plus" data-theme="b" data-inline="true">提交</a>
	</div>
</div>

<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#retail_desc").click(function(){
		$("#retail_desc").val("");
	});
	//$('#date').scroller({theme: "default",mode:"clickpick"});

	var nowDate = new Date();
	$('#date').val((nowDate.getMonth() + 1) + "/" + nowDate.getDate() + "/" +nowDate.getFullYear());

	var opt = {
	        preset: 'date', //日期
	        theme: 'jqm', //皮肤样式
	        display: 'modal', //显示方式 
	        mode: 'clickpick', //日期选择模式
	        dateFormat: 'm/d/yy', // 日期格式
	        setText: '确定', //确认按钮名称
	        cancelText: '取消',//取消按钮名籍我
	        dateOrder: 'yymmdd', //面板中日期排列格式
	        dayText: '日', monthText: '月', yearText: '年', //面板中年月日文字
	        endYear:2020 //结束年份
	    };
	$('#date').mobiscroll(opt);
	//$('#date').scroller({theme: "default",mode:"clickpick"});
	
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