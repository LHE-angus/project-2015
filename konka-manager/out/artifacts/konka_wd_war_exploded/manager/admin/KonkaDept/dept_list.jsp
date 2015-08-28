<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body style="background-color:#fff;">
<div class="oarcont">
  <div>
    <html-el:form action="/admin/KonkaDept.do" enctype="multipart/form-data">
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
        <td width="90%"></td>
        </tr>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
		<c:set var="checked" value="false" />
		<c:forEach var="_cur" items="${konkaDeptList}">
			<c:if test="${cur.dept_id eq _cur.dept_id}">
				<c:set var="checked" value="true" />
			</c:if>
		</c:forEach>
			<tr>
			  <td>&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${checked ne true}"><input type="checkbox" id="${cur.dept_id}" name="dept_info" value="${cur.dept_name}" /></c:if>
			      <c:if test="${checked eq true}"><input type="checkbox" id="${cur.dept_id}" name="dept_info" value="${cur.dept_name}" checked="checked" /></c:if>
                  &nbsp;&nbsp;<label for="${cur.dept_id}">${fn:escapeXml(cur.dept_name)}</label></td>
			</tr>      	
		</c:forEach>
        <tr>
          <td><input type="checkbox" id="select_all" name="select_all" value="select_all" onclick="selectAll(this);" /> <label for="select_all">选择全部 </label>&nbsp;&nbsp;&nbsp;
            <input type="button" class="but4" value="确认" onclick="submitDeptInfo();" />
            <input type="button" class="but5" value="关闭 " onclick="window.close();" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});

function submitDeptInfo(){
	var es = document.getElementsByName("dept_info");
	var ids = [], names = [];
	for(var i = 0; i < es.length; i++) {
		if(es[i].checked){
			ids[ids.length] = es[i].getAttribute("id");
			names[names.length] = es[i].value;
		}
	}
	window.returnValue = {
			'ids'   : ids.join(","),
			'names' : names.join(",")
		};
	window.close();
}                  

function selectAll(o){
	var es = document.getElementsByName("dept_info");
	for(var i = 0; i < es.length; i++) {
		es[i].checked = o.checked;
	}
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
