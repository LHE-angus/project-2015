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
  <div>
    <h3 align="center" ><strong class="fb">网点开拓情况查看</strong></h3>
  </div>
  <div class="rtabcont2">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
     <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title">用户名：</td>
        <td><c:out value="${entity.user_name}" /></td>
      </tr>
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title">网点名称：</td>
        <td><c:out value="${entity.shop_name}" /></td>
      </tr>
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title">开拓状态：</td>
        <td><c:if test="${entity.develop_status eq 0}" >待开拓</c:if>
        <c:if test="${entity.develop_status eq 1}" >开拓中</c:if>
        <c:if test="${entity.develop_status eq 2}" >已开拓</c:if>
        </td>
      </tr>
      <tr >
        <td height="28" nowrap="nowrap" class="title_item">网点转化类型：</td>
        <td>
        <c:if test="${entity.develop_status ne 2}" >--</c:if>
        <c:if test="${entity.develop_status eq 2}" >
          <c:if test="${entity.jxs_id ne null}" >经销商</c:if>
          <c:if test="${entity.r3_id  ne null}" >R3用户</c:if>
        </c:if>
        </td>
      </tr>
        <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title">部门名称：</td>
        <td><c:out value="${kd.dept_name}" /></td>
      </tr>
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title">R3用户名称：</td>
        <td>
         <c:if test="${not empty r3.customer_name }">${r3.customer_name} </c:if>
        <c:if test="${empty r3.customer_name }">--</c:if>
        </td>
      </tr>
      <tr >
        <td height="28" width="15%" nowrap="nowrap" class="title">代理商名称：</td>
        <td>
        <c:if test="${not empty ked.customer_name }">${ked.customer_name} </c:if>
        <c:if test="${empty ked.customer_name }">--</c:if>
        </td>
      </tr>
      <tr >
        <td height="28" nowrap="nowrap" class="title_item">开始开拓时间：</td>
        <td>
         <c:if test="${not empty entity.start_date }">  <fmt:formatDate value="${entity.start_date }" pattern="yyyy-MM-dd"></fmt:formatDate> </c:if>
        <c:if test="${empty entity.start_date }">--</c:if>
        </td>
      </tr>
       <tr >
        <td height="28" nowrap="nowrap" class="title_item">结束开拓时间：</td>
        <td>
         <c:if test="${not empty entity.end_date }">  <fmt:formatDate value="${entity.end_date }" pattern="yyyy-MM-dd"></fmt:formatDate> </c:if>
        <c:if test="${empty entity.end_date }">--</c:if>
        </td>
      </tr>
       <tr >
        <td height="28" nowrap="nowrap" class="title_item"></td>
        <td>
           <font color="red">"--"  &nbsp; 表示不存在</font>
        </td>
      </tr>
    </table>
    <div> <br />
      <label >
      <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
      </label>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
