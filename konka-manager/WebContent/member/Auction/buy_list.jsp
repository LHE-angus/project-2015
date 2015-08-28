<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> </title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/epp.css" />  
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/purchase.css" />  
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
</head>
<body> 
<table style="width:100%;" border="1" id="table_0"> 
	<tr>
		<td align="center" width="5%">序号 </td><td align="center" width="15%">流水号 </td><td align="center" width="20%">竞拍时间 </td><td align="center" width="10%">金额 </td><td align="center" width="15%">竞拍人 </td>
	</tr><c:forEach items="${entity.ecAuctionBuyList}" var="cur" varStatus="vs">   
	<tr>
		<td align="center">${vs.count} </td>
		<td align="center">${cur.trade_index }</td>
		<td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td align="center">${cur.price }</td>
		<td>${cur.user_name } </td>
	</tr></c:forEach>
</table> 
</body>
</html>