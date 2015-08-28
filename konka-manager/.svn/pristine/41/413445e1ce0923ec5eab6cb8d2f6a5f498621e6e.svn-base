<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcOrderPdModelLowestPrice.do">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="id" value="${af.map.id}"/>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">产品最低限价信息</th>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">产品型号：</td>
          <td>${fn:escapeXml(af.map.pd_name)}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">品牌：</td>
          <td>${fn:escapeXml(af.map.brand_name)}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">产品类型：</td>
          <td>${fn:escapeXml(af.map.pd_type_name)}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">限定年份：</td>
          <td>${fn:escapeXml(af.map.set_year)}</td>
         </tr>
         <tr>   
            <td width="15%" nowrap="nowrap" class="title_item">限定月份：</td>
            <td>${fn:escapeXml(af.map.set_month)}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">最低限价：</td>
          <td><fmt:formatNumber value="${af.map.lowest_price}" pattern="0.00" />(元)</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">部门：</td>
          <td>${fn:escapeXml(af.map.dept_name)}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">添加时间：</td>
          <td>
          	<fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
          </td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">是否删除：</td>
          <td>
          	<c:if test="${af.map.is_del eq 0}"><span>未删除</span></c:if>
        	<c:if test="${af.map.is_del eq 1}"><span style="color:#F00;">已删除</span></c:if>
          </td>
        </tr>
        <c:if test="${af.map.is_del eq 1}">
        	<tr>
	          <td width="15%" nowrap="nowrap" class="title_item">删除时间：</td>
	          <td>
	          	<fmt:formatDate value="${af.map.del_date}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
	          </td>
	        </tr>
        </c:if>
        <tr>
          <td colspan="2" align="center"><html-el:button property="back" value="返 回 " onclick="history.back();" styleClass="bgButtonBack"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[

//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
