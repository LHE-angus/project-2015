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
    <html-el:form action="/JxcReceivePeShopMsg.do" enctype="multipart/form-data">
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="method" value="list_user" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="10%">序列</td>
          <td width="45%">用户名称</td>
          <td width="55%">角色名称</td>
        </tr>
        <c:forEach var="cur" items="${peProdUserList}" varStatus="vs">
          <!--<c:if test="${cur.map.level eq 1 and vs.first eq true}"><c:out value="<tbody>" escapeXml="false" /></c:if>
		<c:if test="${cur.map.level eq 1 and vs.first eq false}"><c:out value="</tbody><tbody>" escapeXml="false" /></c:if>
		-->
          <c:set var="checked" value="false" />
          <c:forEach var="_cur" items="${peProdUserSelectedList}">
            <c:if test="${cur.id eq _cur.id}">
              <c:set var="checked" value="true" />
            </c:if>
          </c:forEach>
          <tr>
            <td align="center" class="title_item">${vs.count}</td>
            <td><c:if test="${checked eq true}">
                <input type="checkbox" id="${cur.id}" name="user_info" value="${fn:escapeXml(cur.user_name)}" checked="checked" />
              </c:if>
              <c:if test="${checked ne true}">
                <input type="checkbox" id="${cur.id}" name="user_info" value="${fn:escapeXml(cur.user_name)}" />
              </c:if>
              <label for="${cur.id}">${fn:escapeXml(cur.user_name)}</label></td>
            <td> ${fn:escapeXml(cur.map.role_name)} </td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:out value="</tbody>" escapeXml="false" />
          </c:if>
        </c:forEach>
        <tr>
          <td>&nbsp;</td>
          <td colspan=2><!--
          <input type="checkbox" id="select_all" name="select_all" value="select_all" onclick="selectAll(this);" /> <label for="select_all">选择全部 </label>&nbsp;&nbsp;&nbsp;
           -->
            <input type="button" class="but4" value="确认" onclick="return submitRoleInfo();" />
            <input type="button" class="but5" value="关闭 " onclick="window.close();" /></td>
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
		var es = document.getElementsByName("user_info");
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
		var es = document.getElementsByName("user_info");
		for(var i = 0; i < es.length; i++) {
			es[i].checked = o.checked;
		}
	}             
$(document).ready(function(){


});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
