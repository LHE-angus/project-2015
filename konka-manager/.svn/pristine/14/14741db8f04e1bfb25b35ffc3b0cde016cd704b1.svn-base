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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
          <td class="title_item" width="15%" align="right">分公司：</td>
          <td width="85%"><span style="color: red;">${af.map.dept_name}</span></td>
        </tr>
        <tr>
          <td class="title_item" width="15%" align="right">模板名称：</td>
          <td width="85%">${af.map.module_name}</td>
        </tr>
        <tr>
          <td class="title_item" width="15%" align="right">模板描述：</td>
          <td width="85%">${af.map.module_desc}</td>
        </tr>
        <tr>
          <td class="title_item" align="right">内容：</td>
          <td>${af.map.module_content}</td>
        </tr>
        <tr>
          <td class="title_item" width="15%" align="right">排序值：</td>
          <td width="85%">${af.map.order_value}</td>
        </tr>
        
      </table>
      <div><br />
      	<label>
      		<input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
        </label>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
