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
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/BasePdClazzList.do">
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <!-- <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       <tbody>
          <tr>
          <td width="15"></td>
          <td>
            <ul>
              <li>
                <strong class="fb">根类别名称：</strong>       
                <html-el:text property="root_name" style="width:120px;" />&nbsp;&nbsp;
                <strong class="fb">父类别名称：</strong>       
                <html-el:text property="par_name" style="width:120px;" />&nbsp;&nbsp;
                <strong class="fb">类别名称：</strong>       
                <html-el:text property="cls_name" style="width:120px;" />&nbsp;&nbsp;
                <input class="but1" type="submit" name="Submit" value="搜索" />
              </li>
            </ul>
          </td>
         </tr>
       </tbody>
      </table> -->
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="BasePdClazzList.do">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tbody>
         <tr class="tabtt1">
          <td width="30" nowrap="nowrap" align="center">序号</td> 
          <td align="center" nowrap="nowrap">根类别名称</td>  
          <td nowrap="nowrap">父类别名称</td>
          <td nowrap="nowrap">类别名称</td>
          <td nowrap="nowrap">类别名称（树）</td>
          <td width="7%" nowrap="nowrap">层级</td>
          <td width="7%" align="center" nowrap="nowrap">排序值</td>
        </tr>
        <c:forEach var="cur" items="${basePdClazzList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="center" nowrap="nowrap">${cur.root_name}</td>
            <td align="center" nowrap="nowrap">${cur.par_name}</td>
            <td align="center" nowrap="nowrap">${cur.cls_name}</td>
            <td align="left" nowrap="nowrap">${cur.tree_name}</td>  
            <td align="left" nowrap="nowrap">${cur.cls_level}</td>
            <td align="center" nowrap="nowrap">${cur.order_value}</td>
          </tr>
          <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
            <tr align="center">
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#user_name").blur(function() {
		$(this).val(this.value.trim());						   
	});
});

String.prototype.trim = function(){ 
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>