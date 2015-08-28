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
          <td colspan="2" class="item_class" align="left" ><strong class="fb">邮件发送记录查看</strong></td>
        </tr>
        <tr>
          <td nowrap="nowrap"  width="20%" class="title_item">主送：</td>
          <td width="80%" >${fn:escapeXml(entity.to_name)}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">抄送：</td>
          <td>${fn:escapeXml(entity.cc_nane)}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">主题：</td>
          <td>${fn:escapeXml(entity.title)}</td>
        </tr>
         <tr>
          <td nowrap="nowrap" class="title_item"> 内容：</td>
          <td><div style="width:98%;height:400px;overflow: auto;" >${entity.content}</div></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">发送时间：</td>
          <td><fmt:formatDate value="${entity.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
        </tr>
        <tr>
          <td colspan="2" align="center" > <input type="button" class="but5" name="return" value="返回 " onclick="history.back();" /></td>
        </tr>
      </table>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>