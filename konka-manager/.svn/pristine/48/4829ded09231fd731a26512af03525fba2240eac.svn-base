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
<input type="button" name="" value="返回" onclick="location.href='${ctx}/manager/admin/KonkaMobileSailData.do?method=imp&mod_id=${af.map.mod_id}'" id="btn_reset" class="but5" />
数据存在异常，请仔细检查，修改后重新提交（注：数量、单价、总金额不能为空，且不能使用公式）
</div>
<div style="overflow-x: auto;">
	<table width="100%" border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	  <tr>
	    <th nowrap="nowrap" width="5%">序号</th>
	    <th nowrap="nowrap" width="8%" align="center">门店名称</th>
	    <th nowrap="nowrap" align="center">全称</th>
	    <th nowrap="nowrap" width="8%" align="center">*门店编号</th>
	    <th nowrap="nowrap" width="8%" align="center">*直销员岗位ID</th>
	    <th nowrap="nowrap" width="8%" align="center">直销员</th>
	    <th nowrap="nowrap" width="8%" align="center">*上报时间</th>
	    <th nowrap="nowrap" width="8%" align="center">型号名称</th>
	    <th nowrap="nowrap" width="8%" align="center">数量</th>
	    <th nowrap="nowrap" width="8%" align="center">单价</th>
	    <th nowrap="nowrap" width="8%" align="center">总金额</th>
	    <th nowrap="nowrap" width="8%" align="center">消费者姓名</th>
	    <th nowrap="nowrap" width="8%" align="center">消费者联系方式</th>
	    <th nowrap="nowrap" width="8%" align="center">消费者身份证</th>
	    <th nowrap="nowrap" width="8%" align="center">地址</th>
	    <th nowrap="nowrap" width="8%" align="center">备注</th>
	  </tr>
	  <c:forEach items="${demoEntityList}" var="cur" varStatus="vs">
	    <tr>
	      <td nowrap="nowrap" align="center" style="text-align:center;">${vs.count}</td>
	      <td nowrap="nowrap" title="${cur.map.msg0}" class="${empty cur.map.msg0 ? '' : 'warn'}">${cur.map.c0}</td>
	      <td nowrap="nowrap" title="${cur.map.msg1}" class="${empty cur.map.msg1 ? '' : 'warn'}">${cur.map.c1}</td>
	      <td nowrap="nowrap" title="${cur.map.msg2}" class="${empty cur.map.msg2 ? '' : 'warn'}">${cur.map.c2}</td>
	      <td nowrap="nowrap" title="${cur.map.msg3}" class="${empty cur.map.msg3 ? '' : 'warn'}">${cur.map.c3}</td>
	      <td nowrap="nowrap" title="${cur.map.msg4}" class="${empty cur.map.msg4 ? '' : 'warn'}">${cur.map.c4}</td>
	      <td nowrap="nowrap" title="${cur.map.msg5}" class="${empty cur.map.msg5 ? '' : 'warn'}">${cur.map.c5}</td>
	      <td nowrap="nowrap" title="${cur.map.msg6}" class="${empty cur.map.msg6 ? '' : 'warn'}">${cur.map.c6}</td>
	      <td nowrap="nowrap" title="${cur.map.msg7}" class="${empty cur.map.msg7 ? '' : 'warn'}">${cur.map.c7}</td>
	      <td nowrap="nowrap" title="${cur.map.msg8}" class="${empty cur.map.msg8 ? '' : 'warn'}">${cur.map.c8}</td>
	      <td nowrap="nowrap" title="${cur.map.msg9}" class="${empty cur.map.msg9 ? '' : 'warn'}">${cur.map.c9}</td>
	      <td nowrap="nowrap" title="${cur.map.msg10}" class="${empty cur.map.msg10 ? '' : 'warn'}">${cur.map.c10}</td>
	      <td nowrap="nowrap" title="${cur.map.msg11}" class="${empty cur.map.msg11 ? '' : 'warn'}">${cur.map.c11}</td>
	      <td nowrap="nowrap" title="${cur.map.msg12}" class="${empty cur.map.msg12 ? '' : 'warn'}">${cur.map.c12}</td>
	      <td nowrap="nowrap" title="${cur.map.msg13}" class="${empty cur.map.msg13 ? '' : 'warn'}">${cur.map.c13}</td>
	      <td nowrap="nowrap" title="${cur.map.msg14}" class="${empty cur.map.msg14 ? '' : 'warn'}">${cur.map.c14}</td>  
	    </tr>
	  </c:forEach>
	</table>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});
//]]></script>
</body>
</html>