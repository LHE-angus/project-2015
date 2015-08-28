<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/manager/scripts/charts.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
<div class="oartop">
  <table width="400" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td nowrap="nowrap">当前位置：${naviString}</td>
    </tr>
  </table>
</div>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
</div>
<c:if test="${not empty entityList}">
	<div class="rtabcont1">
		<div id="divExcel" title="交易明细表">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
				<tr>
					<th align="center" width="5%">行号</th>
					<th align="center" >商品</th>
					<th align="center" width="10%">交易类型</th>
					<th align="center" width="10%">日期</th>
					<th align="center" width="10%">价格</th>
					<th align="center" width="10%">数量</th>
					<th align="center" width="10%">成本</th>
					<th align="center" width="10%">金额</th>
					<th align="center" width="20%">交易单号</th>
				</tr>
				<c:set var="total_num" value="0" />
				<c:set var="total_money" value="0" />
				<c:set var="total_cost" value="0" />
				<c:forEach items="${entityList}" var="cur" varStatus="vs">
				<tr>
					<td align="center">${vs.count}</td>
					<td align="left">${cur[1]}</td>
					<td align="center">${cur[2]}</td>
					<td align="center">${fn:substring(cur[3], 0, 10)}</td>
					<td align="right"><span class="kz-price-12"><fmt:formatNumber value="${cur[4]}" type="currency" /></span></td>				
					<td align="right"><c:if test="${cur[9] eq 21 }">-</c:if>${cur[5]}</td>
					<td align="right"><c:if test="${cur[9] eq 21 }"><span class="kz-price-12"><fmt:formatNumber value="-${cur[7]}" type="currency" /></span></c:if>
						<c:if test="${cur[9] ne 21 }"><span class="kz-price-12"><fmt:formatNumber value="${cur[7]}" type="currency" /></span></c:if>
					</td>
					<td align="right"><c:if test="${cur[9] eq 21 }"><span class="kz-price-12"><fmt:formatNumber value="-${cur[6]}" type="currency" /></span></c:if>
						<c:if test="${cur[9] ne 21 }"><span class="kz-price-12"><fmt:formatNumber value="${cur[6]}" type="currency" /></span></c:if>
					</td>
					<td align="center">${cur[8]}</td>
				</tr>
				<c:if test="${cur[9] eq 21}">
					<c:set var="total_num" value="${total_num-cur[5]}" />
					<c:set var="total_money" value="${total_money-cur[6]}" />
					<c:set var="total_cost" value="${total_cost-cur[7]}" />
				</c:if>
				<c:if test="${cur[9] ne 21}">
					<c:set var="total_num" value="${total_num+cur[5]}" />
					<c:set var="total_money" value="${total_money+cur[6]}" />
					<c:set var="total_cost" value="${total_cost+cur[7]}" />
				</c:if>
				</c:forEach>
				<tr>
					<td align="center" colspan="2"><span style="font-size:14px;font-family:verdana;font-weight:700;">总计</span></td>
					<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
					<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
					<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
					<td align="right"><span style="font-size:14px;font-family:verdana;font-weight:700;">${total_num}</span></td>
					<td align="right"><span class="kz-price"><fmt:formatNumber value="${total_cost}" type="currency" /></span></td>
					<td align="right"><span class="kz-price"><fmt:formatNumber value="${total_money}" type="currency" /></span></td>
					<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
				</tr>
			</table>
		</div>
	</div>
</c:if>

<div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">


</script>
<jsp:include page="/customer/__analytics.jsp" />
</body>