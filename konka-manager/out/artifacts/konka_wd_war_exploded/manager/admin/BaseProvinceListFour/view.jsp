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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">

    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr class="oartop">
          <td colspan="2">地区详细信息展示</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">区域编码：</td>
          <td>${af.map.p_index}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">区域名称：</td>
          <td>${af.map.p_name}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">区域简称：</td>
          <td>${af.map.s_name}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">区域级别：</td>
          <td>
          	<c:if test="${af.map.p_level eq 1}">省/直辖市/自治区</c:if>
          	<c:if test="${af.map.p_level eq 2}">市</c:if>
          	<c:if test="${af.map.p_level eq 3}">县</c:if>
          </td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">所在省简称：</td>
          <td>${af.map.p_code}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">电话区号：</td>
          <td>${af.map.tel_code}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">邮编：</td>
          <td>${af.map.post_code}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">父区域编码：</td>
          <td>${af.map.par_index}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">父区域名称：</td>
          <td><c:if test="${af.map.p_level eq 1}"> -- </c:if>
          	  <c:if test="${af.map.p_level ne 1}"><a href="BaseProvinceListFour.do?method=view&p_index=${af.map.par_index}&mod_id=${af.map.mod_id}" title="${par_name}">${fn:escapeXml(par_name)}</a></c:if>
          </td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">子区域名称：</td>
          <td> 
          	<c:if test="${empty childrenProvinceListList}"><font style="color:red;">无更详细的区域信息</font></c:if>
          	<c:if test="${not empty childrenProvinceListList}"><ul class="plist">
          	<c:forEach items="${childrenProvinceListList}" var="cur" varStatus="vs">
          	  <li>
          	  	<a href="BaseProvinceListFour.do?method=view&p_index=${cur.p_index}&mod_id=${af.map.mod_id}" title="${cur.p_name}">${fn:substring(fn:escapeXml(cur.p_name), 0, 9)}</a>
          	  </li>
          	</c:forEach></ul></c:if>
          </td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">根区域编码：</td>
          <td>${af.map.root_code}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">根区域名称：</td>
          <td>${root_name}</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item">添加日期：</td>
          <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" var="_add_date" /> <c:out value="${_add_date}"></c:out> </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" /></td>
        </tr>
    </table>
  </div>
  <div class="clear"></div>
</div>

<jsp:include page="/__analytics.jsp" />
</body>
</html>
