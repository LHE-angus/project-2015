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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
  	<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
  		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right">购物券名称：</td>
			<td width="88%" align="left">${af.map.title}</td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">金额：</td>
			<td align="left">
				${af.map.price}
			</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">兑换时间：</td>
			<td align="left"><fmt:formatDate value="${af.map.add_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">兑换人：</td>
			<td align="left">${af.map.real_name}</td>
		</tr> 
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">使用状态：</td>
			<td align="left"><c:if test="${af.map.info_state eq 0}">未使用</c:if>
			<c:if test="${af.map.info_state eq 1}">已使用</c:if>
			</td>
		</tr>  
		<tr>
			<td nowrap="nowrap" class="title_item" align="right">备注：</td>
			<td align="left">
				${af.map.memo}
			</td>
		</tr>
		<tr>
	          <td align="center" colspan="2">
				<input class="but5" type="button" name="Submit5" value="返回" id="btn_back" onclick="history.back()" />
	          </td>
	    </tr>		
  	</table>
  </div>
  
</div>
</body>
</html>