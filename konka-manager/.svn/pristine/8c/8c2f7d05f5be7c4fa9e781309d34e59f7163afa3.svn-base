<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
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
    <%@ include file="/manager/admin/PeShopMsg/shop_msg_top.jsp" %>
  </div>
  <div>
    <h3 align="center" ><strong class="fb">站内信息查看</strong></h3>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/PeShopMsg.do" enctype="multipart/form-data" >
      <html-el:hidden property="method" value="viewReceive" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="receive_user_id" styleId="receive_user_id" value="${af.map.receive_user_id}" />
      <html-el:hidden property="state" styleId="state" />
      <html-el:hidden property="id" styleId="id" />
     <table width="98%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
     <c:if test="${_entity.id ne null}">
          <tr>
	        <td colspan="2" align="right">站内信原文</td>
	      </tr>
	      <tr >
	        <td height="28" width="15%" nowrap="nowrap" class="title_item">发件人：</td>
	        <td><c:out value="${_entity.send_user_name}" /></td>
	      </tr>
	      <tr >
	        <td height="28" width="15%" nowrap="nowrap" class="title_item">标题：</td>
	        <td><c:out value="${_entity.title}" /></td>
	      </tr>
	      <tr >
	        <td height="28" width="15%" nowrap="nowrap" class="title_item">内容：</td>
	        <td><c:out value="${_entity.content}" /></td>
	      </tr>
	      <tr >
	        <td height="28" width="15%" nowrap="nowrap" class="title_item">发送时间：</td>
	        <td><fmt:formatDate value="${_entity.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	      </tr>
	      <tr>
	        <td colspan="2" align="right">站内信回复</td>
	      </tr>
	 </c:if>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title">发件人：</td>
          <td width="85%"><c:out value="${af.map.send_user_name}" /></td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title">发件方式：</td>
          <td><c:if test="${af.map.receive_user_type ne 0}">指定人员</c:if>
            <c:if test="${af.map.receive_user_type eq 0}">全部人员</c:if></td>
        </tr>
        <tr id="select_1" 
        <c:if test="${af.map.receive_user_type eq 0}"> style="display: none;"</c:if>
        >
        <td height="28" width="15%" nowrap="nowrap" class="title">分公司收件人角色：</td>
          <td><c:out value="${af.map.role_names}" /></td>
        </tr>
        <tr id="select_2" 
        <c:if test="${af.map.receive_user_type eq 0}"> style="display: none;"</c:if>
        >
        <td height="28" width="15%" nowrap="nowrap" class="title">分公司收件人：</td>
          <td><c:out value="${af.map.user_names}" /></td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title">标题：</td>
          <td width="85%"><c:out value="${af.map.title}" /></td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title">内容：</td>
          <td><c:out value="${af.map.content}" /></td>
        </tr>
        <tr >
	      <td height="28" width="15%" nowrap="nowrap" class="title">发送时间：</td>
	      <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	    </tr>
        <tr>
          <td colspan="2" align="center"><input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>

<jsp:include page="/__analytics.jsp" />
</body>
</html>
