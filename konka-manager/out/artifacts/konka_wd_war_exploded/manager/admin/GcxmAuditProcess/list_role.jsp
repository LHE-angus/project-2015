<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
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
<body>
<div class="oarcont">
  <div>
  	<div style="color:#F00;">职位ID需要在30和60之间（包括30和60）</div>
    <html-el:form action="/admin/GcxmAuditProcess.do" >
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
       <html-el:hidden property="method" value="list_role" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
        <td width="10%" align="center">序列</td>
        <td width="10%" align="center" nowrap="nowrap">职位ID</td>
        <td width="80%" align="center">职位名称</td>
        </tr>
		<c:forEach var="cur" items="${peRoleInfoList}" varStatus="vs">
		<c:set var="checked" value="false" />
		<c:forEach var="_cur" items="${peRoleInfoSelectedList}">
			<c:if test="${cur.role_id eq _cur.role_id}">
				<c:set var="checked" value="true" />
			</c:if>
		</c:forEach>
			<tr>
			  <td align="center" class="title_item">${vs.count}</td>
			  <td align="center" class="title_item">${cur.role_id}</td>
			  <td>
				 <c:if test="${checked eq true}">
				  <input type="checkbox" id="${cur.role_id}" name="role_info" value="${fn:escapeXml(cur.role_name)}" checked="checked" />
				 </c:if>
				 <c:if test="${checked ne true}">
				  <input type="checkbox" id="${cur.role_id}" name="role_info" value="${fn:escapeXml(cur.role_name)}" />
				 </c:if>
			      <label for="${cur.role_id}">${fn:escapeXml(cur.role_name)}</label>
			  </td>
			</tr>      	
        <c:if test="${vs.last eq true}"><c:out value="</tbody>" escapeXml="false" /></c:if>			
		</c:forEach>
        <tr>
          <td colspan="3" align="center">
            <input type="button" class="but4" value="确认" onclick="return submitRoleInfo();" />
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
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
<script type="text/javascript">//<![CDATA[
function submitRoleInfo(){
	var es = document.getElementsByName("role_info");
	var ids = [], names = [];
	for(var i = 0; i < es.length; i++) {
		if(es[i].checked){
			ids[ids.length] = es[i].getAttribute("id");
			names[names.length] = es[i].value;
		}
	}
	var obj = { 'ids' : ids.join(","), 'names' : names.join(",") };
	window.returnValue = obj;
	if (window.opener && window.opener != null) window.opener.returnValue = obj;
	window.close();
}                  
$(document).ready(function(){ 
	 $(':checkbox[name=role_info]').each(function(){
         $(this).click(function(){
             if($(this).attr('checked')){
                 $(':checkbox[name=role_info]').removeAttr('checked');
                 $(this).attr('checked','checked');
             }
         });
     });
	
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
