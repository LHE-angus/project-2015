<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<base target="_self" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaR3Store.do">
      <html-el:hidden property="method" value="listCustomer" />
      <html-el:hidden property="type" value="${type }" />
      <html-el:hidden property="fgs_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
          	<strong class="fb">客户名称：</strong>
          	<html-el:text property="customer_name_like" size="40" maxlength="40" styleId="customer_name_like" />
          </td>
          <td>
          	<strong class="fb">客户R3编码：</strong>
          	<html-el:text property="r3_code" size="20" maxlength="20" styleId="r3_code" />
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
          <td width="5%" align="center" nowrap="nowrap">客户群类型</td>
          <td nowrap="nowrap" width="10%">R3编码</td>
         <!--  <td nowrap="nowrap" width="10%">经办名称</td> -->
          <td nowrap="nowrap" width="10%">分公司所在地名称</td>
          <td nowrap="nowrap" width="10%">合并客户编码</td>
          <td nowrap="nowrap" align="center" width="10%">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.customer_name}</td>
            <td align="center" nowrap="nowrap">${cur.customer_type}</td>
            <td align="left">${fn:escapeXml(cur.r3_code)}</td>
             <!--
            <td align="left" >${fn:escapeXml(cur.handle_name)}</td>
            -->
            <td align="left" >${fn:escapeXml(cur.branch_area_name)}</td>
            <td align="left" >${fn:escapeXml(cur.customer_code)}</td>
            <td align="center" nowrap="nowrap"><a href="#" id="sel" onclick="selectCust('${cur.id}', '${cur.r3_code}', '${cur.customer_name}','${cur.link_man_tel}')">选择</a></td>
          </tr>
        </c:forEach>
      </table>
    <br />
    
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
function selectCust(cust_id, cust_r3_code, cust_name,link_man_tel){
	var api = frameElement.api;
	var W = api.opener;
	var type = "${type}";
	if('r3_code'==type){
		W.document.getElementById("r3_code").value = cust_r3_code;
		W.document.getElementById("kh_name").value = cust_name;
		W.document.getElementById("r3_shsdf_sn").value = cust_r3_code;
	}
	if('r3_sdf_sn'==type){
		W.document.getElementById("r3_sdf_sn").value = cust_r3_code;
	}
//	if('r3_code_2'==type){
//		W.document.getElementById("r3_code").value = cust_r3_code;
//		W.document.getElementById("kh_name").value = cust_name;
//		W.document.getElementById("r3_code_2").value = cust_r3_code;
//		W.document.getElementById("kh_name_2").value = cust_name;
//		W.document.getElementById("r3_shsdf_sn_2").value = cust_r3_code;
//	}
	api.close();
	//window.opener.document.getElementById("r3_sdf_sn").value=cust_r3_code; 
	//window.close(); 
	
	/*window.returnValue = { 'cust_id' : cust_id, 'cust_r3_code' : cust_r3_code, 'cust_name' : cust_name,'link_man_tel':link_man_tel};
	if (window.opener && window.opener != null) 
		window.opener.returnValue = { 'cust_id' : cust_id, 'cust_r3_code' : cust_r3_code, 'cust_name' : cust_name,'link_man_tel':link_man_tel};
	window.close();*/
}
$(document).ready(function(){
});
//]]></script>

</body>
</html>