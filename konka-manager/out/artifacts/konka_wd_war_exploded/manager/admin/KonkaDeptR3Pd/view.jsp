<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>效果预览</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	font-size:13px;
	padding:5px;
}
.warn {
	background-color:yellow;
}
</style>
</head>
<body>
<div style="font-size:14px;font-weight:700;color:#F00;line-height:25px;">
<input type="button" name="" value="返回" onclick="location.href='${ctx}/manager/admin/KonkaDeptJbTask.do?method=imp&mod_id=${af.map.mod_id}'" id="btn_reset" class="but5" />
数据存在异常，请仔细检查，修改后重新提交
</div>

<table width="100%" border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
  <tr>
    <th width="5%">序号</th>
    <th width="8%" align="center">*R3客户编码</th>
    <th width="8%" align="center">*型号</th>
    <th width="8%" align="center">*年</th>
    <th width="8%" align="center">*月</th>
    <th width="8%" align="center">*价格</th>
    <th width="8%" align="center">*最低价格</th>
     <th width="8%" align="center">*最高价格</th>
  </tr>
  <c:forEach items="${demoEntityList}" var="cur" varStatus="vs">
    <tr>
      <td align="center" style="text-align:center;">${vs.count}</td>
      <td title="${cur.map.msg0}" class="${empty cur.map.msg0 ? '' : 'warn'}">${cur.map.c0}</td>
      <td title="${cur.map.msg1}" class="${empty cur.map.msg1 ? '' : 'warn'}">${cur.map.c1}</td>
      <td title="${cur.map.msg2}" class="${empty cur.map.msg2 ? '' : 'warn'}">${cur.map.c2}</td>
      <td title="${cur.map.msg3}" class="${empty cur.map.msg3 ? '' : 'warn'}">${cur.map.c3}</td>
      <td title="${cur.map.msg4}" class="${empty cur.map.msg4 ? '' : 'warn'}">${cur.map.c4}</td>
      <td title="${cur.map.msg5}" class="${empty cur.map.msg5 ? '' : 'warn'}">${cur.map.c5}</td>
      <td title="${cur.map.msg6}" class="${empty cur.map.msg6 ? '' : 'warn'}">${cur.map.c6}</td>
    </tr>
  </c:forEach>
</table>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});
//]]></script>
</body>
</html>