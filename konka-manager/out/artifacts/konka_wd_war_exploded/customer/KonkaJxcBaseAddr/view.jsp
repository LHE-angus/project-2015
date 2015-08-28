<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tr>
        <th colspan="2">收货地址信息</th>
      </tr>
      <tr>
        <td width="15%" class="title_item">收货人名称：</td>
        <td width="85%">${fn:escapeXml(af.map.user_name)}</td>
      </tr>
      <c:if test="${not empty af.map.user_tel}">
      <tr>
        <td width="15%" class="title_item">固定电话：</td>
        <td width="85%">${fn:escapeXml(af.map.user_tel)}</td>
      </tr>
      </c:if>
      <c:if test="${not empty af.map.user_phone}">
      <tr>
        <td width="15%" class="title_item">手机号码：</td>
        <td width="85%">${fn:escapeXml(af.map.user_phone)}</td>
      </tr>
      </c:if>
      <tr>
        <td width="15%" class="title_item">邮政编码：</td>
        <td width="85%">${fn:escapeXml(af.map.user_zip)}</td>
      </tr>
      <tr>
        <td width="15%" class="title_item">所属区域：</td>
        <td width="85%">${fn:escapeXml(af.map.map.full_name)}</td>
      </tr>
      <tr>
        <td width="15%" class="title_item">详细地址：</td>
        <td width="85%">${fn:escapeXml(af.map.user_addr)}</td>
      </tr>
      <tr>
        <td width="15%" class="title_item">添加时间：</td>
        <td width="85%"><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd"/></td>
      </tr>
      <tr>
        <td width="15%" class="title_item">备注：</td>
        <td width="85%">${fn:escapeXml(af.map.user_remark)}</td>
      </tr>
       <tr>
        <td align="center" colspan="9" style="text-align:center"><input type="button" class="bgButton" value=" 返回 " onclick="history.back();" /></td>
      </tr>
    </table>
  </div>
</div>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>