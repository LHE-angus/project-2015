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
<title>协同办公-康佳电器</title>
<link rel="stylesheet" href="${ctx}/mobile/themes/konka.min.css" />
<link rel="stylesheet" href="${ctx}/commons/styles/jquery.mobile.structure-1.0.min.css" />
<link href="${ctx}/styles/mobiscroll-1.5.2.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/commons/styles/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<script src="${ctx}/commons/scripts/jquery.mobile-1.0.min.js"></script>
<script src="${ctx}/commons/scripts/jquery.form.js" ></script>
<script src="${ctx}/commons/scripts/mobiscroll-1.5.2.js" ></script>
<script src="${ctx}/commons/scripts/jquery.autocomplete.js" ></script>
</head>
<body>
<div data-role="page">
  <div data-role="header" data-position="inline">
    <h1>文件提交</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a> <a onclick="logout();" data-icon="forward" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a> </div>
  <div class="content-primary">
    <form id="iForm" name="iForm">
      <input type="hidden" id="method" name="method" />
      <input type="hidden" id="id" name="id" value="${id}" />
      <div data-role="fieldcontain">
        <label for="apply_user_name">负&nbsp;&nbsp;责&nbsp;&nbsp;人：</label>
        <input type="text" name="apply_user_name" id="apply_user_name" maxlength="30" style="width:150px;" value="${af.map.apply_user_name}"/>
        <label for="apply_user_tel">电　　话：</label>
        <input type="text" name="apply_user_tel" id="apply_user_tel" maxlength="20" style="width:130px;" value="${af.map.apply_user_tel}"  onkeyup="javascript:setOnlyNum(this);"/>
        <label for="file_title">标　　题：</label>
        <input type="text" name="file_title" id="file_title" maxlength="100" style="width:200px;" value="${af.map.file_title}"/>
        <label for="audit_user_name">审&nbsp;&nbsp;批&nbsp;&nbsp;人：</label>
        <input type="text" name="audit_user_name" id="audit_user_name" maxlength="30" style="width:150px;" value="${af.map.audit_user_name}"/>
        <label for="content">请示内容：</label>
        <textarea cols="35" rows="8" name="content" id="content" >${af.map.content}</textarea>
      </div>
    </form>
  </div>
  <div data-role="footer" class="ui-bar" data-position="fixed">
    <div data-role="controlgroup"  data-type="horizontal"> 
    <a onclick="javascript:history.back(-1);" data-role="button" data-icon="back" data-theme="b" data-inline="true">返回</a>
     <a id="submitBtn" data-role="button" data-icon="plus" data-theme="b" data-inline="true">提交</a>
      </div>
  </div>
  <script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#submitBtn").click(function() {
		$("#method").val("save");
		var options = { 
				url:"FilesManager.do",
				type:"POST",
				success:function(msg){
					if(msg == "success"){
						alert("提交成功");
						location.href = "FilesManager.do";
					}else{
						alert(msg);
					}
				}
		    };
		$("#iForm").ajaxSubmit(options);
	});
});
function setOnlyNum(obj) {
	var v = obj.value.replace(/[^\d-]/gi,'');
	obj.value=v;
	
}
	//]]>
</script> 
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
