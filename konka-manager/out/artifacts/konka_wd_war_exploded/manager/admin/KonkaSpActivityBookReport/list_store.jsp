<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	select{font-family:Microsoft YAHEI;font-size:12px;}
	input{font-family:Microsoft YAHEI;font-size:12px;}
</style>
<title>门店信息</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="rtabcont1">
    <html-el:form  action="/admin/KonkaSpActivityAddr.do">
      <html-el:hidden property="method" value="showCustomer" />
      <html-el:hidden property="mod_id" styleId="mod_id"/>
       <table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td width="60"><strong class="fb">门店名称：</strong></td>
          <td><html-el:text property="store_name_like" maxlength="30"></html-el:text></td>
          <td><input type="submit" value="搜索"/></td>
         </tr>
         </table> 
         </html-el:form>
         </div>

	<div class="rtabcont1">
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	    <tr>
	      <th width="5%" nowrap="nowrap">序号</th>
	      <th width="8%" nowrap="nowrap">门店</th>
	      <th width="15%" nowrap="nowrap">门店名称</th>
	      <th width="8%" nowrap="nowrap">操作</th>
	    </tr>
	    <c:forEach items="${entityList}" var="cur" varStatus="vs">
	      <tr>
	        <td align="center" bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	        <td align="center" nowrap="nowrap">${cur.store_id}</td>
	        <td align="center" nowrap="nowrap">${cur.store_name}</td>
	        <td align="center" nowrap="nowrap">
	            <a href="#" id="sel" onclick="selectCust('${cur.store_id}','${cur.store_name}')">选择</a>
	        </td>
	      </tr>
	    </c:forEach>
	   
	  </table>
	</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/customer/script/rowEffect.js"></script> 
<script type="text/javascript">//<![CDATA[

function selectCust(ck_id, ck_name){
	window.returnValue = { 'store_id' : ck_id, 'store_name' : ck_name};
	if (window.opener && window.opener != null) window.opener.returnValue = { 'store_id' : ck_id, 'store_name' : ck_name};
	window.close();
}

$(document).ready(function(){

});	
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>