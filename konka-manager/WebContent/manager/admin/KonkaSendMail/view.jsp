<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
textarea {
	width: 527px;
	overflow-y: hidden;
	background: transparent;
}
.title {
	background-color:#eee;
	text-align:right;
}
span.desc {color:#848484;font-size:12px;margin-left:1em;}
div.desc {color:#848484;font-size:12px;}
-->
</style>
</head>
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<div class="oarcont" style="margin-bottom: 100px;">
	<div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
 	<div class="rtabcont2"> 
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td colspan="2" class="item_class" align="left" ><strong class="fb">接收人信息查看</strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>姓　 名：</td>
          <td>${fn:escapeXml(entity.real_name)}&nbsp; </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item"><span style="color: #F00;">* </span>邮　 箱：</td>
          <td>${fn:escapeXml(entity.email)}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">主送/抄送：</td>
          <td><c:if test="${entity.send_type eq 1 }"> 主送</c:if><c:if test="${entity.send_type eq 2 }"> 抄送</c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">状 　态：</td>
          <td><c:if test="${entity.state eq 0 }"> 不发送</c:if><c:if test="${entity.state eq 1 }"> 发送</c:if></td>
        </tr>
       <tr>
          <td nowrap="nowrap" class="title_item">排 序 号：</td>
          <td>${fn:escapeXml(entity.order_value)}    <span class="desc">值越大，显示越靠前，范围：0-9999</span></td>
        </tr>
        <tr>
          <td colspan="2" align="center"> 
            <input type="button" class="but5" name="return" value="返回 " onclick="history.back();" /></td>
        </tr>
      </table>
  </div>
  <div class="clear"></div>
</div>

<jsp:include page="/__analytics.jsp" />
</body>
</html>