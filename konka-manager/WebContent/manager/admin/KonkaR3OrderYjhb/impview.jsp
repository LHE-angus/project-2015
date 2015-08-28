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
<input type="button" name="" value="返回" onclick="history.back();" id="btn_reset" class="but5" />
数据存在异常，请仔细检查，修改后重新提交
</div>

<table width="100%" border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
  <tr>
    <th width="5%">序号</th>
    <th width="8%" align="center">售达方</th>
    <th align="center">送达方</th>
    <th width="8%" align="center">订单创建时间</th>
    <th width="8%" align="center">销售单号</th>
    <th width="8%" align="center">订单类型</th>
    <th width="8%" align="center">项目号</th>
    <th width="8%" align="center">机型</th>
    <th width="8%" align="center">每一订单行机型数量</th>
    <th width="8%" align="center">含税单价</th>
    <th width="8%" align="center">总金额（含税）</th>
    <th width="8%" align="center">K007（含税）</th>
    <th width="8%" align="center">总净值金额(含税)</th>
    <th width="8%" align="center">采购订单编号</th>
    <th width="8%" align="center">物料组</th>
    <th width="8%" align="center">办事处</th>
    <th width="8%" align="center">销售组织</th>
    <th width="8%" align="center">订单r/3凭证日期</th>
    <th width="8%" align="center">交货单数量</th>
    <th width="8%" align="center">已发货数量</th>
    <th width="8%" align="center">已开发票数量</th>
    <th width="8%" align="center">客户采购订单日期</th>
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
      <td title="${cur.map.msg7}" class="${empty cur.map.msg7 ? '' : 'warn'}">${cur.map.c7}</td>
      <td title="${cur.map.msg8}" class="${empty cur.map.msg8 ? '' : 'warn'}">${cur.map.c8}</td>
      <td title="${cur.map.msg9}" class="${empty cur.map.msg9 ? '' : 'warn'}">${cur.map.c9}</td>
      <td title="${cur.map.msg10}" class="${empty cur.map.msg10 ? '' : 'warn'}">${cur.map.c10}</td>
      <td title="${cur.map.msg11}" class="${empty cur.map.msg11 ? '' : 'warn'}">${cur.map.c11}</td>
      <td title="${cur.map.msg12}" class="${empty cur.map.msg12 ? '' : 'warn'}">${cur.map.c12}</td>
      <td title="${cur.map.msg13}" class="${empty cur.map.msg13 ? '' : 'warn'}">${cur.map.c13}</td>
      <td title="${cur.map.msg14}" class="${empty cur.map.msg14 ? '' : 'warn'}">${cur.map.c14}</td>
      <td title="${cur.map.msg15}" class="${empty cur.map.msg15 ? '' : 'warn'}">${cur.map.c15}</td>
      <td title="${cur.map.msg16}" class="${empty cur.map.msg16 ? '' : 'warn'}">${cur.map.c16}</td>
      <td title="${cur.map.msg17}" class="${empty cur.map.msg17 ? '' : 'warn'}">${cur.map.c17}</td>
      <td title="${cur.map.msg18}" class="${empty cur.map.msg18 ? '' : 'warn'}">${cur.map.c18}</td>
      <td title="${cur.map.msg19}" class="${empty cur.map.msg19 ? '' : 'warn'}">${cur.map.c19}</td>
      <td title="${cur.map.msg20}" class="${empty cur.map.msg20 ? '' : 'warn'}">${cur.map.c20}</td>
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