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
          <th colspan="2" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>积分明细信息</span></th>
        </tr>
        <tr>
          <td nowrap="nowrap" align="left"><font style="font-weight:bold">会员卡号：</font><c:out value="${af.map.user_sn}" />
          &nbsp;&nbsp;&nbsp;&nbsp;<font style="font-weight:bold">姓名：</font><c:out value="${af.map.user_name}" />
          &nbsp;&nbsp;&nbsp;&nbsp;<font style="font-weight:bold">当前积分：</font><c:out value="${af.map.total_scorts}" /></td>
        </tr>
        <tr>
          <td bgcolor="#CCCCCC">明细信息</td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          		<tr>
          			<td width="5%" align="center">序号</td>
          			<td width="10%" align="center">积分种类</td>
          			<td width="10%" align="center">分公司</td>
          			<td align="center">产品型号</td>
          			<td width="20%" align="center">购买时间</td>
          			<td width="10%" align="center">积分调整</td>
          			<td width="10%" align="center">所得积分</td>
          		</tr>
          		<c:forEach var="cur" items="${detailsList}" varStatus="vs">
          		<tr>
          			<td align="center">${vs.count}</td>
          			<td align="center"><c:choose>
		          			<c:when test="${cur.jf_cate eq 1}">购买产品</c:when>
		          			<c:when test="${cur.jf_cate eq 2}"><font style="color:blue;">积分调整</font></c:when>
		          		</c:choose></td>
          			<td>${cur.map.dept_name}</td>
          			<td>${cur.pd_id}</td>
          			<td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          			<td align="center"><c:out value="${cur.jf_cate eq 2 ? cur.scorts : ''}"/></td>
          			<td align="center">${cur.scorts}</td>
          		</tr>
          		</c:forEach>
          		</table></td>
        </tr>
        <tr>
          <td bgcolor="#CCCCCC">兑换礼品</td>
        </tr>
        <tr>
          <td><table  width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          		<tr>
          			<td width="5%" align="center">序号</td>
          			<td align="center">礼品名称</td>
          			<td align="center">兑换时间</td>
          			<td align="center">所用积分</td>
          		</tr>
          		<c:forEach var="cur" items="${exchangeList}" varStatus="vs">
          		<tr>
          			<td align="center">${vs.count}</td>
          			<td>${cur.map.gift_name}</td>
          			<td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          			<td align="center">${cur.scorts}</td>
          		</tr>
          		</c:forEach>
          		</table></td>
        </tr>
        <tr>
          <td colspan="6" align="center"><html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
  </div>
</div>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
