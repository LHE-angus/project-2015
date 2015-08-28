<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
	<div class="oartop">
	    <table width="100%" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
	      </tr>
	    </table>
  	</div>
  	<div class="rtabcont1">
  		<input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
  		<input class="but_excel" type="button" name="Submit5" value="导出" onclick="exportExcel();" />
  	</div>
  	<div class="rtabcont1">
  		<div id="divExcel" title="订单统计">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
			<tr class="tabtt1">
				<td width="5%" nowrap="nowrap" align="center">序号</td>
				<c:if test="${fn:contains(group_flag_string, ',aa_1,')}">
				<td nowrap="nowrap" align="center">分公司</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',aa_2,')}">
				<td nowrap="nowrap" align="center">经营部</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',aa_3,')}">
				<td nowrap="nowrap" align="center">办事处</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',aa_4,')}">
				<td nowrap="nowrap" align="center">业务员</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',aa_5,')}">
				<td nowrap="nowrap" align="center">客户</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',bb_1,')}">
				<td nowrap="nowrap" align="center">年度</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',bb_2,')}">
				<td nowrap="nowrap" align="center">季度</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',bb_3,')}">
				<td nowrap="nowrap" align="center">月度</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',bb_4,')}">
				<td nowrap="nowrap" align="center">周</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',bb_5,')}">
				<td nowrap="nowrap" align="center">日</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',cc_1,')}">
				<td nowrap="nowrap" align="center">尺寸规格</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',cc_2,')}">
				<td nowrap="nowrap" align="center">型号</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',cc_3,')}">
				<td nowrap="nowrap" align="center">是否大板</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',cc_4,')}">
				<td nowrap="nowrap" align="center">是否智能</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',dd_1,')}">
				<td nowrap="nowrap" align="center">客户分类大类</td>
				</c:if>
				<c:if test="${fn:contains(group_flag_string, ',dd_2,')}">
				<td nowrap="nowrap" align="center">客户分类明细</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10001,')}">
				<td nowrap="nowrap" align="center">R3销售量</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10002,')}">
				<td nowrap="nowrap" align="center">R3销售量同比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10003,')}">
				<td nowrap="nowrap" align="center">R3销售量环比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10011,')}">
				<td nowrap="nowrap" align="center">R3销售额</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10012,')}">
				<td nowrap="nowrap" align="center">R3销售额同比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10013,')}">
				<td nowrap="nowrap" align="center">R3销售额环比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10021,')}">
				<td nowrap="nowrap" align="center">零售量</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10022,')}">
				<td nowrap="nowrap" align="center">零售量同比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10023,')}">
				<td nowrap="nowrap" align="center">零售量环比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10031,')}">
				<td nowrap="nowrap" align="center">零售额</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10032,')}">
				<td nowrap="nowrap" align="center">零售额同比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10033,')}">
				<td nowrap="nowrap" align="center">零售额环比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10041,')}">
				<td nowrap="nowrap" align="center">回款额</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10042,')}">
				<td nowrap="nowrap" align="center">回款额同比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10043,')}">
				<td nowrap="nowrap" align="center">回款额环比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10051,')}">
				<td nowrap="nowrap" align="center">任务额</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10052,')}">
				<td nowrap="nowrap" align="center">任务额同比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10053,')}">
				<td nowrap="nowrap" align="center">任务额环比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10061,')}">
				<td nowrap="nowrap" align="center">任务系数</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10062,')}">
				<td nowrap="nowrap" align="center">任务系数同比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10063,')}">
				<td nowrap="nowrap" align="center">任务系数环比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10071,')}">
				<td nowrap="nowrap" align="center">回款任务完成度%</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10072,')}">
				<td nowrap="nowrap" align="center">回款任务完成度%同比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10073,')}">
				<td nowrap="nowrap" align="center">回款任务完成度%环比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10081,')}">
				<td nowrap="nowrap" align="center">销售任务完成度%</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10082,')}">
				<td nowrap="nowrap" align="center">销售任务完成度%同比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10083,')}">
				<td nowrap="nowrap" align="center">销售任务完成度%环比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10111,')}">
				<td nowrap="nowrap" align="center">平均单价</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10112,')}">
				<td nowrap="nowrap" align="center">平均单价同比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10113,')}">
				<td nowrap="nowrap" align="center">平均单价环比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10121,')}">
				<td nowrap="nowrap" align="center">平均毛利</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10122,')}">
				<td nowrap="nowrap" align="center">平均毛利同比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10123,')}">
				<td nowrap="nowrap" align="center">平均毛利环比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10131,')}">
				<td nowrap="nowrap" align="center">产品零售指导价</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10132,')}">
				<td nowrap="nowrap" align="center">产品零售指导价同比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10133,')}">
				<td nowrap="nowrap" align="center">产品零售指导价环比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10141,')}">
				<td nowrap="nowrap" align="center">产品现款价</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10142,')}">
				<td nowrap="nowrap" align="center">产品现款价同比</td>
				</c:if>
				<c:if test="${fn:contains(show_field_string, ',10143,')}">
				<td nowrap="nowrap" align="center">产品现款价环比</td>
				</c:if>
			</tr>
			<c:forEach items="${entityList}" var="cur" varStatus="vs">
				<tr>
					<td align="center" nowrap="nowrap">${vs.count }</td>
					<c:forEach begin="0" end="${show_num-1}" var="num">
					<td align="center">${fn:escapeXml(cur[num])}</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table> 
		</div>
  	</div>

</div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[    

function exportExcel(){
	toExcel('divExcel', '?method=toExcel');
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>