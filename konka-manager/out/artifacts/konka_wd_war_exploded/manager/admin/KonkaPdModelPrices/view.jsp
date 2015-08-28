<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
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
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
    <div class="rtabcont2">
     <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title_item">型号：</td>
        <td><c:out value="${af.map.pd_name}" /></td>
      </tr>
      <tr >
        <td height="28" nowrap="nowrap" class="title_item">现款价：</td>
        <td><c:out value="${af.map.cash_price}" /></td>
      </tr>
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title_item">零售指导价：</td>
        <td><c:out value="${af.map.sale_price}" /></td>
      </tr>
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title_item">扣点：</td>
        <td><c:out value="${af.map.discount}" /></td>
      </tr>
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title_item">月份：</td>
        <td><c:out value="${af.map.price_month}" /></td>
      </tr>
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title_item">添加时间：</td>
        <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      </tr>
    </table>
    <div>
     <br />
        <label >
            <input class="but5" type="button"  value="返回" onclick="history.back();" />
          </label>
  </div>
  </div>
<div class="clear"></div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
