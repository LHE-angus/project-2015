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
<div class="rtabcont1">
    <html-el:form action="/admin/KonkaSellRank">
      <c:if test="${af.map.rank_type eq 100 }">
      <html-el:hidden property="method" value="list_100" />
      </c:if>
      <c:if test="${af.map.rank_type eq 200 }">
      <html-el:hidden property="method" value="list_200" />
      </c:if>
      <c:if test="${af.map.rank_type eq 300 }">
      <html-el:hidden property="method" value="list_300" />
      </c:if>
      <c:if test="${af.map.rank_type eq 400 }">
      <html-el:hidden property="method" value="list_400" />
      </c:if>
      <c:if test="${af.map.rank_type eq 500 }">
      <html-el:hidden property="method" value="list_500" />
      </c:if>
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="rank_type" />
      <html-el:hidden property="tj_type" styleId="tj_type" value="${af.map.tj_type}" />
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
        <tr>
          	<td width="5%"></td>
          	<td width="5%" align="right"><strong class="fb">销售时间：</strong></td>
			<td width="20%">
				<html-el:text property="sell_date_start" styleId="sell_date_start"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="sell_date_end" styleId="sell_date_end"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
			</td>
			<td width="15%" align="right"><strong class="fb">排名显示：</strong></td>
			<td width="10%">
          		<html-el:select property="show_top" styleId="show_top">
					<html-el:option value="">全部</html-el:option>
					<html-el:option value="5">Top5</html-el:option>
					<html-el:option value="10">Top10</html-el:option>
					<html-el:option value="20">Top20</html-el:option>
					<html-el:option value="30">Top30</html-el:option>
					<html-el:option value="40">Top40</html-el:option>
					<html-el:option value="50">Top50</html-el:option>
				</html-el:select>
			</td>
			<td align="right" width="15%">
				<c:choose>
					<c:when test="${af.map.rank_type ge 200}">
						<strong class="fb">分公司　：</strong>
					</c:when>
					<c:otherwise>&nbsp;</c:otherwise>
				</c:choose>
			</td>
			<td width="10%">
				<c:choose>
					<c:when test="${af.map.rank_type ge 200}">
						<c:set var="disabled" value="false" />
						<c:if test="${af.map.dept_type eq 3}">
							<c:set var="disabled" value="true" />
						</c:if>
						<html-el:select property="dept_id" styleId="dept_id" disabled="${disabled}">
							<html-el:option value="">-请选择-</html-el:option>
						</html-el:select>
						&nbsp;
						<html-el:select property="l4_dept_id" styleId="l4_dept_id">
							<html-el:option value="">-请选择-</html-el:option>
						</html-el:select>
						&nbsp;
						<html-el:select property="l5_dept_id" styleId="l5_dept_id">
							<html-el:option value="">-请选择-</html-el:option>
						</html-el:select>
					</c:when>
					<c:otherwise>&nbsp;</c:otherwise>
				</c:choose>
		    </td>
        </tr>
		<tr>
			<td width="15"></td>
			<td align="right"><strong class="fb">客户类型：</strong></td>
			<td>
				<html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
					<html-el:option value="">-请选择-</html-el:option>
				</html-el:select>
				<html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
					<html-el:option value="">-请选择-</html-el:option>
				</html-el:select>
			</td>
			<td align="right">
				<c:if test="${af.map.rank_type ge 300 }">
					<strong class="fb">业务员　：</strong>
				</c:if>
				<c:if test="${af.map.rank_type ge 400 }">
					&nbsp;<strong class="fb">客&nbsp;&nbsp;户　：</strong>
				</c:if>
				<c:if test="${af.map.rank_type ge 500 }">
					&nbsp;<strong class="fb">促销员　：</strong>
				</c:if>
			</td>
			<td>
				<c:if test="${af.map.rank_type ge 300 }">
					<html-el:text property="ywy_user_name" size="15" maxlength="20" styleId="ywy_user_name"  />
				</c:if>
				<c:if test="${af.map.rank_type ge 400 }">
					<html-el:text property="customer_name" size="15" maxlength="20" styleId="customer_name"  />
				</c:if>
				<c:if test="${af.map.rank_type ge 500 }">
					<html-el:text property="cxy_user_name" size="15" maxlength="20" styleId="cxy_user_name"  />
				</c:if>
			</td>
			<td colspan="2"><html-el:submit styleClass="but1" value="搜索" /></td>
		</tr>
      </table>
    </html-el:form>
</div>
<div align="right">
		<a href="javascript:setTjType(1);" style="color:${af.map.tj_type eq 1 ? '#B11212':'#0072bc'};">按销售额排名</a>&nbsp;&nbsp;<a href="javascript:setTjType(2);" style="color:${af.map.tj_type eq 2 ? '#B11212':'#0072bc'};">按销售量排名</a>&nbsp;

		<!-- 
		<a href="javascript:setChartType(1);" style="color:#0072bc;">柱状图</a>&nbsp;&nbsp;<a href="javascript:setChartType(2);" style="color:#0072bc;">折线图</a>
		&nbsp; -->
</div>
<script type="text/javascript">//<![CDATA[    
$(document).ready(function(){
	<c:if test="${af.map.rank_type ge 200 }">
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", true);
	$("#dept_id").change();
	</c:if>
	
});

function setTjType(type){
	document.getElementById("tj_type").value = type;
	//$("#tj_type").val(type);
	document.forms[0].submit();
}
                                                                               
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>