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
<!--Android-->
<body>
<div>
   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtab2" style="font-size: 10px;">
	<tr>
          <th colspan="2" width="90%" align="left" class="bno" id="filetitle">库存提醒</th>
        </tr>
	<c:forEach items="${entityList}" var="cur" varStatus="vs">
		<tr>
			<td  class="title_item" align="right" width="20%">姓名:</td>
			<td align="left">${cur.map.ywy_user_name}</td>
		</tr>
		<tr>
			<td  class="title_item" align="right" width="20%">电话:</td>
			<td align="left">${cur.map.ywy_tel}</td>
		</tr>
		<tr>
		<td class="title_item" align="right" width="20%">消息:</td>
			<td align="center" >${cur.remark}</td>
		</tr>
		<tr>
			<td align="center" colspan="2"><hr style="color: green;"></hr></td>
		</tr>
	</c:forEach>
</table>
</div>
</body>
</html>