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
<base target="_self" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
    <html-el:form action="/spgl/EcUserNew.do">
      <html-el:hidden property="method" value="listCustomer" />
      <html-el:hidden property="group_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
          	<strong class="fb">客户名称：</strong>
          	<html-el:text property="cust_name_like" size="15"  maxlength="30" styleId="cust_name_like" />
          	<strong class="fb">客户编码：</strong>
          	<html-el:text property="cust_code_like" size="15" maxlength="30" styleId="cust_code_like" />
          </td>
          <td><html-el:submit styleClass="but1" value="搜索" /></td>
        </tr> 
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap" align="left" >客户名称</td>
          <td nowrap="nowrap" align="left" >客户编码</td>
          <td width="10%" nowrap="nowrap" align="center" >R3编码</td>
          <td nowrap="nowrap" align="center" width="10%">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.cust_name}</td>
            <td align="left" nowrap="nowrap">${cur.cust_code}</td>
            <td align="center" nowrap="nowrap">${cur.r3_code}</td>
            <td align="center" nowrap="nowrap"><a href="#" id="sel" onclick="selectCust('${cur.id}','${cur.cust_code}','${cur.cust_name}')">选择</a></td>
          </tr>
        </c:forEach>
      </table>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcUserNew.do?method=listCustomer">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("group_id", "${af.map.group_id}");
	            pager.addHiddenInputs("cust_name_like", "${fn:escapeXml(af.map.cust_name_like)}");
	            pager.addHiddenInputs("cust_code_like", "${fn:escapeXml(af.map.cust_code_like)}");
	            document.write(pager.toString()); 
	      </script></td>
        </tr>
      </table>
    </form>
    
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
function selectCust(cust_id,cust_code,cust_name){

	var api = frameElement.api, W = api.opener;
	W.document.getElementById("cust_id").value = cust_id;
	W.document.getElementById("c_name").value = cust_name;
	W.document.getElementById("cust_name").value = cust_name;
	api.close();

}
$(document).ready(function(){
	
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>