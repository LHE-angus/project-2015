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
<style type="text/css">
.areause1 {
}
.areause1 td {
	border-bottom: 0px solid #E3E3E3;
	border-right: 0px solid #E3E3E3;
	padding: 0px 0px 0px;
}
#areaList0 {
}
#areaList1 {
}
#areaList0 td {
	border-bottom: 2px dotted #FFDCB9;
	padding-left:5px;
}
#areaList1 td {
	border-bottom: 2px dotted #FFDCB9;
	padding-left:5px;
}
</style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="20"><img src="${ctx}/images/manager/arrow3.gif" style="vertical-align:middle;" /></td>
        <td>当前位置：&nbsp;&gt;&nbsp;信息接收&nbsp;&gt;&nbsp;站内信息&nbsp;&gt;&nbsp;发件箱</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/jxcnokey/JxcReceivePeShopMsg/shop_msg_top.jsp" %>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/JxcReceivePeShopMsg.do" enctype="multipart/form-data" >
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="keySeq" styleId="keySeq" value="${af.map.keySeq}" />
      <html-el:hidden property="state" styleId="state" />
      <html-el:hidden property="id" styleId="id" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <td nowrap="nowrap" class="title_item" width="15%">发件人：</td>
          <td width="85%"><c:out value="${af.map.send_user_name}" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="left">发件方式：</td>
          <td><c:if test="${af.map.receive_user_type eq 1}">指定人员</c:if>
            <c:if test="${af.map.receive_user_type eq 0}">全部人员</c:if></td>
        </tr>
        <tr id="select_1" 
        <c:if test="${af.map.receive_user_type eq 0}"> style="display: none;"</c:if>
        >
        <td nowrap="nowrap" class="title_item" align="left">分公司收件人角色：</td>
          <td><c:out value="${af.map.role_names}" /></td>
        </tr>
        <tr id="select_2" 
        <c:if test="${af.map.receive_user_type eq 0}"> style="display: none;"</c:if>
        >
        <td nowrap="nowrap" class="title_item" align="left">分公司收件人：</td>
          <td><c:out value="${af.map.user_names}" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" width="15%">标题：</td>
          <td width="85%"><c:out value="${af.map.title}" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">内容：</td>
          <td><c:out value="${af.map.content}" /></td>
        </tr>
        <tr >
	      <td nowrap="nowrap" class="title_item" align="left">发送时间：</td>
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
