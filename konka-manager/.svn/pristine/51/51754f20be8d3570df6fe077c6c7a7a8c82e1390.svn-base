<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>平板销售完成情况 </title>

<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	.tips_div { background-color: yellow; border: 1px ;color: red ; height:100px; border-style: solid; font-size:16pt;font-weight:bold; text-align:center;  }
</style>
 
</head> 
<body>
	<div class="oartop" style="width:100% ">
		当前位置: 开心猫 > 机型日销量统计报表
	</div>
		<div class="rtabcont1" id="tip_div1">
		 <html-el:form action="/spgl/PdSellCount">
		<html-el:hidden property="method" value="list" />
      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
			<tr>
				<td>
				<strong class="fb">选择日期：</strong>
      				<html-el:text property="day_time_start" styleId="day_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				<input id="search_btn" type="submit" class="bgSearch" id="s_button" value="搜 索" style="margin-left:50px;" /></td>  
			</tr>
		</table>
		</html-el:form>
		</div>
		<h1 align="center" style="padding-top: 20px;font-size: 22px;">开心猫机型日销量统计报表（${af.map.month_time_start}至${af.map.day_time_start}）  </h1>
	<div>
		<div class="rtabcont1" style="overflow-x:auto;">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" id="mytable">
					<tr>
						<td rowspan="2" style="text-align: center ; font-weight:bold; background-color: #FFD39B;">&nbsp;&nbsp;机型 &nbsp;&nbsp;</td>
						<td colspan="3" style="text-align: center ; font-weight:bold; background-color: #A4D3EE;">当日销量（${af.map.day_time_start}）</td>
						<td colspan="3" style="text-align: center ; font-weight:bold; background-color: #CDB5CD;">当月累计销量（${af.map.month_time_start}至${af.map.day_time_start}）</td>  
					</tr>
					
					<tr>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #A4D3EE;">金额</td>
						<td align="center" nowrap="nowrap"  style="text-align: center ; background-color: #A4D3EE;">数量</td>
						<td align="center"  nowrap="nowrap"  style="text-align: center ; background-color: #A4D3EE;">单价</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #CDB5CD;">金额</td>  
						<td align="center" nowrap="nowrap"  style="text-align: center ; background-color: #CDB5CD;">数量</td>
						<td align="center"  nowrap="nowrap"  style="text-align: center ; background-color: #CDB5CD;">单价</td>
					</tr>
					<c:choose>
				  	<c:when test="${entityList !=null}">
					<c:forEach var="cur" items="${entityList}" varStatus="vs" >
						<tr id="row_${vs.count}">
							<td align="center" nowrap="nowrap" >${cur.map.pd_sn}</td>
							<td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.map.day_total_price}" pattern="#" /></td>
							<td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.map.day_sell_num}" pattern="#" /></td>
							<td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.map.day_avg_price}" pattern="#" /></td>
							<td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.map.month_total_price}" pattern="#" /></td>
							<td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.map.month_sell_num}" pattern="#" /></td>
							<td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.map.month_avg_price}" pattern="#" /></td>
						</tr>
						</c:forEach>
				  	</c:when>
				  </c:choose>
				  <tr>
				  		<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #FFD39B;">彩电合计</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #A4D3EE;"><fmt:formatNumber value="${af.map.day_money}" pattern="#" /></td>
						<td align="center" nowrap="nowrap"  style="text-align: center ; background-color: #A4D3EE;"><fmt:formatNumber value="${af.map.day_num}" pattern="#" /></td>
						<td align="center"  nowrap="nowrap"  style="text-align: center ; background-color: #A4D3EE;">
						<c:if test="${af.map.day_num ne '0.00'}">
						<fmt:formatNumber value="${af.map.day_money/af.map.day_num}" pattern="#" />
						</c:if>
						<c:if test="${af.map.day_num eq '0.00'}">
						--
						</c:if>
						</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #CDB5CD;"><fmt:formatNumber value="${af.map.month_money}" pattern="#" /></td>  
						<td align="center" nowrap="nowrap"  style="text-align: center ; background-color: #CDB5CD;"><fmt:formatNumber value="${af.map.month_num}" pattern="#" /></td>
						<td align="center"  nowrap="nowrap"  style="text-align: center ; background-color: #CDB5CD;">
						<c:if test="${af.map.month_num ne '0.00'}">
						<fmt:formatNumber value="${af.map.month_money/af.map.month_num}" pattern="#" />
						</c:if>
						<c:if test="${af.map.month_num eq '0.00'}">
						--
						</c:if>
						</td>
					</tr>
					 <tr>
				  		<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #FFD39B;">生活电器合计</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #A4D3EE;"><fmt:formatNumber value="${af.map.day_money_shdq}" pattern="#" /></td>
						<td align="center" nowrap="nowrap"  style="text-align: center ; background-color: #A4D3EE;"><fmt:formatNumber value="${af.map.day_num_shdq}" pattern="#" /></td>
						<td align="center"  nowrap="nowrap"  style="text-align: center ; background-color: #A4D3EE;">
							<c:if test="${af.map.day_num_shdq ne '0.00'}">
						<fmt:formatNumber value="${af.map.day_money_shdq/af.map.day_num_shdq}" pattern="#" />
						</c:if>
						<c:if test="${af.map.day_num_shdq eq '0.00'}">
						--
						</c:if>
						</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #CDB5CD;"><fmt:formatNumber value="${af.map.month_money_shdq}" pattern="#" /></td>  
						<td align="center" nowrap="nowrap"  style="text-align: center ; background-color: #CDB5CD;"><fmt:formatNumber value="${af.map.month_num_shdq}" pattern="#" /></td>
						<td align="center"  nowrap="nowrap"  style="text-align: center ; background-color: #CDB5CD;">
							<c:if test="${af.map.month_num_shdq ne '0.00'}">
						<fmt:formatNumber value="${af.map.month_money_shdq/af.map.month_num_shdq}" pattern="#" />
						</c:if>
						<c:if test="${af.map.month_num_shdq eq '0.00'}">
						--
						</c:if>
						</td>
					</tr>
					 <tr>
				  		<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #FFD39B;">白色家电合计</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #A4D3EE;"><fmt:formatNumber value="${af.map.day_money_bd}" pattern="#" /></td>
						<td align="center" nowrap="nowrap"  style="text-align: center ; background-color: #A4D3EE;"><fmt:formatNumber value="${af.map.day_num_bd}" pattern="#" /></td>
						<td align="center"  nowrap="nowrap"  style="text-align: center ; background-color: #A4D3EE;">
							<c:if test="${af.map.day_num_bd ne '0.00'}">
						<fmt:formatNumber value="${af.map.day_money_bd/af.map.day_num_bd}" pattern="#" />
						</c:if>
						<c:if test="${af.map.day_num_bd eq '0.00'}">
						--
						</c:if>
						</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #CDB5CD;"><fmt:formatNumber value="${af.map.month_money_bd}" pattern="#" /></td>  
						<td align="center" nowrap="nowrap"  style="text-align: center ; background-color: #CDB5CD;"><fmt:formatNumber value="${af.map.month_num_bd}" pattern="#" /></td>
						<td align="center"  nowrap="nowrap"  style="text-align: center ; background-color: #CDB5CD;">
							<c:if test="${af.map.month_num_bd ne '0.00'}">
						<fmt:formatNumber value="${af.map.month_money_bd/af.map.month_num_bd}" pattern="#" />
						</c:if>
						<c:if test="${af.map.month_num_bd eq '0.00'}">
						--
						</c:if>
						</td>
					</tr>
					<tr>
				  		<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #FFD39B;">总合计</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #A4D3EE;"><fmt:formatNumber value="${af.map.total_day_money}" pattern="#" /></td>
						<td align="center" nowrap="nowrap"  style="text-align: center ; background-color: #A4D3EE;"><fmt:formatNumber value="${af.map.total_day_num}" pattern="#" /></td>
						<td align="center"  nowrap="nowrap"  style="text-align: center ; background-color: #A4D3EE;">--</td>
						<td align="center" nowrap="nowrap" style="text-align: center ; background-color: #CDB5CD;"><fmt:formatNumber value="${af.map.total_month_money}" pattern="#" /></td>  
						<td align="center" nowrap="nowrap"  style="text-align: center ; background-color: #CDB5CD;"><fmt:formatNumber value="${af.map.total_month_num}" pattern="#" /></td>
						<td align="center"  nowrap="nowrap"  style="text-align: center ; background-color: #CDB5CD;">--</td>
					</tr>
				  
			</table>
		</div>

	<div class="rtabcont3">
	<span></span> 

	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" >
</script>	

</body>
</html>