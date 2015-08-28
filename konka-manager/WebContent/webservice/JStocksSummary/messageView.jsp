<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<META charset="UTF-8">
<META name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>库存预警</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body>
<div>

<table align="center">
	<tr>
		<td  class="title_item" align="right" width="20%">客户编码:</td>
		<td align="left">${af.map.r3_code}</td>
	</tr>
	<tr>
		<td class="title_item" align="right" width="20%" >客户名称:</td>
		<td align="left">${af.map.cust_name}</td>
	</tr>
	<tr>
		<td class="title_item" align="right" width="20%">型号:</td>
		<td align="left">${af.map.goods_name}</td>
	</tr>
	<tr>
		<td class="title_item" align="right" width="20%">当前剩余库存:</td>
		<td align="left">${af.map.total_cur_num}</td>
	</tr>
	<tr>
		<td class="title_item" align="right" width="20%">业务员姓名:</td>
		<td align="left">${af.map.user_name}</td>
	</tr>
	<tr>
		<td class="title_item" align="right" width="20%" >业务员电话:</td>
		<td align="left">${af.map.link_tel}</td>
	</tr>
</table>
</div>
</body>
</html>