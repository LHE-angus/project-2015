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
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/JoinInfoFollow">
      <html-el:hidden property="method" value="visitList" />
      <html-el:hidden property="article_id" styleId="article_id" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table>
        <tr>
          <td width="80%" nowrap="nowrap">
            &nbsp;<strong class="fb">是否同意：</strong>
            <html-el:select property="shop_agree">
            	<html-el:option value="">-- 所有 --</html-el:option>
            	<html-el:option value="0">同意</html-el:option>
            	<html-el:option value="1">不同意</html-el:option>
            	<html-el:option value="-1">未操作</html-el:option>
            </html-el:select>
            
            &nbsp;&nbsp;<html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit>
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="JoinInfoFollow.do?method=delete">
      <div style="text-align: left">
        <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      </div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="27" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap" align="center" >网点</td>
          <td width="70" align="center" nowrap="nowrap">是否同意</td>
          <td width="90" nowrap="nowrap" align="center" >最近浏览时间</td>
          <td  width="70" nowrap="nowrap" align="center" >操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.map.shop_name }</td>
            <td align="center" nowrap="nowrap">
            	<c:if test="${cur.shop_agree eq 0}">同意</c:if>
            	<c:if test="${cur.shop_agree eq 1}">不同意</c:if>
            	<c:if test="${cur.shop_agree eq -1}">未操作</c:if>
            </td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.last_view_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center">
            	<a class="fblue" href="<c:url value='/manager/admin/KonkaEntpShop.do?method=view&id=${cur.shop_id}&mod_id=${af.map.mod_id}'/>" style="text-decoration:none;"><font class="blue12px">查看网点</font></a>
	        </td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
      </table>
    </form>
    <br />
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
