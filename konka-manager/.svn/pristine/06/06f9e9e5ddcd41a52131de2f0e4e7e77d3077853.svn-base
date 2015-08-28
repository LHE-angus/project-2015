<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<body>
<html-el:form action="/manager/JxcXsReport.do">
	<c:if test="${af.map.flag_type eq 100}">
	<html-el:hidden property="method" value="xs_time" />
	</c:if>
	<c:if test="${af.map.flag_type eq 200}">
	<html-el:hidden property="method" value="xs_partner" />
	</c:if>
	<c:if test="${af.map.flag_type eq 300}">
	<html-el:hidden property="method" value="xs_goods" />
	</c:if>
	<c:if test="${af.map.flag_type eq 400}">
	<html-el:hidden property="method" value="cg_time" />
	</c:if>
	<c:if test="${af.map.flag_type eq 500}">
	<html-el:hidden property="method" value="list" />
	</c:if>
	<c:if test="${af.map.flag_type eq 600}">
	<html-el:hidden property="method" value="xs_store" />
	</c:if>
	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<html-el:hidden property="flag_type" value="${af.map.flag_type}" />
  	<html-el:hidden property="tree_param" value="${tree_param}" />
  	<html-el:hidden property="chart_type" styleId="chart_type" value="${af.map.chart_type}" />
  	<html-el:hidden property="tj_type" styleId="tj_type" value="${af.map.tj_type}" />
	<div class="rtabcont1">
	  	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableTop">
	  		<tr>
	  			<td height="36" align="left" style="padding-left:5px;">
	  				&nbsp;<strong class="fb">时间选择：</strong><html-el:text property="st_date" styleId="st_date" size="10" styleClass="webinput" maxlength="10" readonly="true" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
	  				- <html-el:text property="en_date" styleId="en_date" size="10" styleClass="webinput" maxlength="10" readonly="true" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
	  				<c:if test="${af.map.flag_type eq 500}">
	  				&nbsp;<strong class="fb">交易类型</strong>：<html-el:select property="bill_type" styleId="bill_type">
	  				<html-el:option value="">请选择</html-el:option>
			        	<html-el:option value="10">采购</html-el:option>
			        	<html-el:option value="11">采购退货</html-el:option>
			        	<html-el:option value="20">销售</html-el:option>
			        	<html-el:option value="21">销售退货</html-el:option>
			        	<html-el:option value="30">盘亏</html-el:option>
			        	<html-el:option value="31">盘盈</html-el:option>
			        </html-el:select>
			        </c:if>
	  				&nbsp;<strong class="fb">商品类型</strong>：<html-el:select property="type_id" styleId="type_id" onchange="this.form.submit();">
	  				<html-el:option value="">请选择</html-el:option>
			        <c:forEach var="cur" items="${jBaseTypes}" varStatus="vs">
			        	<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
			        </c:forEach></html-el:select>
			        &nbsp;<strong class="fb">商品</strong>：<html-el:text property="goods_names" styleId="goods_names" size="10" styleClass="webinput" readonly="true" value="${empty af.map.goods_names?'请选择':af.map.goods_names}" onclick="getJBaseGoods();" title="111" />
	  				<html-el:hidden property="goods_ids" styleId="goods_ids" value="${af.map.goods_ids}" />
	  				&nbsp;<strong class="fb">往来单位</strong>：<html-el:select property="partner_id" styleId="partner_id" onchange="this.form.submit();">
	  				<html-el:option value="">请选择</html-el:option>
          			<c:forEach var="cur" items="${jBasePartners}" varStatus="vs">
          				<html-el:option value="${cur.partner_id}">${cur.partner_name}</html-el:option>
          			</c:forEach>
	  				</html-el:select>
	  				<c:if test="${af.map.flag_type eq 600}">
	  				&nbsp;<strong class="fb">网点级别</strong>:
	  				<html-el:select property="p_level" styleId="p_level">
	  					<html-el:option value="">请选择</html-el:option>
	  					<html-el:option value="1">1级</html-el:option>
	  					<html-el:option value="2">2级</html-el:option>
	  					<html-el:option value="3">3级</html-el:option>
	  					<html-el:option value="4">4级</html-el:option>
	  				</html-el:select>
	  				</c:if>
	  				<br/>
	           		<input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
	           		<c:if test="${af.map.flag_type eq 500}">
	           		<input name="btn" type="button" class="but_excel" id="export_excel" value="Excel" style="margin-left:10px;" onclick="exportExcel();" />
	           		</c:if>
	           	</td>
	  		</tr>
	  	</table>
	</div>
	<div align="right">
		<c:if test="${(af.map.flag_type eq 100) or (af.map.flag_type eq 400)}">
			<a href="javascript:setTjType(1);" style="color:#0072bc;">按日期统计</a>&nbsp;&nbsp;<a href="javascript:setTjType(2);" style="color:#0072bc;">按月份统计</a>
		</c:if>
		<c:if test="${af.map.flag_type eq 300}">
			<a href="javascript:setTjType(3);" style="color:#0072bc;">按商品统计</a>&nbsp;&nbsp;<a href="javascript:setTjType(4);" style="color:#0072bc;">按商品类型统计</a>
		</c:if>
		<c:if test="${af.map.flag_type ne 500}">
			<a href="javascript:setChartType(1);" style="color:#0072bc;">柱状图</a>&nbsp;&nbsp;<a href="javascript:setChartType(2);" style="color:#0072bc;">折线图</a>
		</c:if>
		<c:if test="${af.map.flag_type ne 600}">
			<a href="javascript:setChartType(1);" style="color:#0072bc;">柱状图</a>&nbsp;&nbsp;<a href="javascript:setChartType(2);" style="color:#0072bc;">折线图</a>
		</c:if>
		&nbsp;
	</div>
</html-el:form>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript">
function setChartType(type){
	document.getElementById("chart_type").value = type;
	//$("#chart_type").val(type);
	document.forms[0].submit();
}
function setTjType(type){
	document.getElementById("tj_type").value = type;
	//$("#tj_type").val(type);
	document.forms[0].submit();
}

function exportExcel(){
	toExcel('divExcel', '?method=toExcel');
}

function getJBaseGoods(){
	var returnValue = window.showModalDialog("JxcXsReport.do?method=getJBaseGoodsList&selectype=mutiple&goods_ids=" + $("#goods_ids").val() + "&type_id=" + $("#type_id").val() + "&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:370px");
	$("#goods_ids").val(returnValue.ids);
	$("#goods_names").val(returnValue.names);
}

$("#goods_names").mouseover(function(){
	document.getElementById("goods_names").title = $("#goods_names").val();
});

</script>
<jsp:include page="/customer/__analytics.jsp" />
</body>