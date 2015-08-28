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
<style type="text/css">
.rtable1 td {
	padding: 2px 5px;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
<div class="oartop">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="3%"><img
			src="${ctx}/styles/admin-index/images/k_tup.jpg"
			style="vertical-align: middle;" /></td>
		<td>当前位置：${naviString}</td>
		<td width="60"><img src="${ctx}/images/manager/help.gif"
			style="vertical-align: middle;" /> <span id="span_help"
			style="cursor: pointer;">帮助</span></td>
	</tr>
</table>
</div>
<div class="rtabcont1"><html-el:form action="/admin/KonkaOrderPlanStatement">
	<html-el:hidden property="method" value="channellist" />
	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<html-el:hidden property="id" value="id" />
<!--	<html-el:hidden property="mod_id" value="id" />-->
	<table width="100%" border="0" cellspacing="5" cellpadding="0"
		class="rtable1">
		<tr>
			<td align="right"><strong class="fb">计划年月：</strong></td>
			<td><html-el:select property="plan_year" styleId="plan_year"
				styleClass="webinput">
				<html-el:option value="2010">2010</html-el:option>
				<html-el:option value="2011">2011</html-el:option>
				<html-el:option value="2012">2012</html-el:option>
				<html-el:option value="2013">2013</html-el:option>
				<html-el:option value="2014">2014</html-el:option>
				<html-el:option value="2015">2015</html-el:option>
				<html-el:option value="2016">2016</html-el:option>
				<html-el:option value="2017">2017</html-el:option>
				<html-el:option value="2018">2018</html-el:option>
				<html-el:option value="2019">2019</html-el:option>
				<html-el:option value="2020">2020</html-el:option>
			</html-el:select>年 <html-el:select property="plan_month" styleId="plan_month"
				styleClass="webinput">
				<html-el:option value="01">01</html-el:option>
				<html-el:option value="02">02</html-el:option>
				<html-el:option value="03">03</html-el:option>
				<html-el:option value="04">04</html-el:option>
				<html-el:option value="05">05</html-el:option>
				<html-el:option value="06">06</html-el:option>
				<html-el:option value="07">07</html-el:option>
				<html-el:option value="08">08</html-el:option>
				<html-el:option value="09">09</html-el:option>
				<html-el:option value="10">10</html-el:option>
				<html-el:option value="11">11</html-el:option>
				<html-el:option value="12">12</html-el:option>
			</html-el:select>月</td>
			<td align="right"><strong class="fb">客户类型:</strong></td>
			<td><html-el:select property="v_customer_type1"
				styleId="v_customer_type1" style="width:80px;">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select> <html-el:select property="v_customer_type2"
				styleId="v_customer_type2" style="width:130px;">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select></td>
			<td valign="middle" nowrap="nowrap" class="title_item" align="right">
			<html-el:submit styleClass="but1" value="搜索" /></td>
		</tr>
	</table>
</html-el:form>
</div>
<%@ include file="/commons/pages/messages.jsp"%>
<div style="overflow-x: auto; height: 400px;">
<table width="100%" border="0" cellpadding="0" cellspacing="1"
	class="rtable2">
	<tr class="tabtt1">
	    <td width="5%" nowrap="nowrap" align="center">序号</td>
		<td width="11%" nowrap="nowrap" align="center">客户类型</td>
		<td width="11%" nowrap="nowrap" align="center">细分类型</td>
		<td width="11%" nowrap="nowrap" align="center">上月末库存量</td>
		<td width="11%" nowrap="nowrap" align="center">渠道系统库存</td>
		<td width="11%" nowrap="nowrap" align="center">预计进货量</td>
		<td width="11%" nowrap="nowrap" align="center">实际进货量</td>
		<td width="11%" nowrap="nowrap" align="center">进货完成率</td>
		<td width="11%" nowrap="nowrap" align="center">系统库存差异</td>
		<td width="11%" nowrap="nowrap" align="center">预计销售量</td>
		<td width="11%" nowrap="nowrap" align="center">实际销售量</td>
		<td width="3%" nowrap="nowrap" align="center">销售完成率</td>
	</tr>
	<c:forEach items="${entityList}" var="cur" varStatus="vs">
		<tr>
			<td align="center" nowrap="nowrap">${(af.map.pager.currentPage -
			1) * af.map.pager.pageSize + vs.count}</td>			
			<td align="center" nowrap="nowrap">${cur.map.c_comm}</td>
			<td align="center" nowrap="nowrap">${cur.map.c_name}</td>
			<td align="center" nowrap="nowrap">
			<c:if test="${cur.map.last_stock_num eq null}">0</c:if>
		    <c:if test="${cur.map.last_stock_num ne null}">${cur.map.last_stock_num}</c:if></td>
			<td align="center" nowrap="nowrap">
			<c:if test="${cur.map.stock eq null}">0</c:if>
		    <c:if test="${cur.map.stock ne null}">${cur.map.stock}</c:if></td>
			<td align="center" nowrap="nowrap">
			<c:if test="${cur.map.plan_stock_num eq null}">0</c:if>
		    <c:if test="${cur.map.plan_stock_num ne null}">${cur.map.plan_stock_num}</c:if></td>
			<td align="center" nowrap="nowrap">
			<c:if test="${cur.map.come_num eq null}">0</c:if>
		    <c:if test="${cur.map.come_num ne null}">${cur.map.come_num}</c:if></td>
			<td align="center" nowrap="nowrap">
			<c:if test="${cur.map.plan_stock_num eq null || cur.map.plan_stock_num eq 0 || cur.map.stock eq null || cur.map.stock eq 0 }">0</c:if>
		    <c:if test="${cur.map.plan_stock_num ne null && cur.map.plan_stock_num ne 0 && cur.map.stock ne null && cur.map.stock ne 0}">((${cur.map.stock}) * 100) / ${cur.map.plan_stock_num}</c:if>%</td>
		    <td align="center" nowrap="nowrap">
			<c:if test="${cur.map.stock_dif eq null}">0</c:if>
		    <c:if test="${cur.map.stock_dif ne null}">${cur.map.stock_dif}</c:if></td>
			<td align="center" nowrap="nowrap">
			<c:if test="${cur.map.plan_sale_num eq null}">0</c:if>
		    <c:if test="${cur.map.plan_sale_num ne null}">${cur.map.plan_sale_num}</c:if></td>
			<td align="center" nowrap="nowrap">
			<c:if test="${cur.map.out_num eq null}">0</c:if>
		    <c:if test="${cur.map.out_num ne null}">${cur.map.out_num}</c:if></td>
			
			<td align="center" nowrap="nowrap">
			<c:if test="${cur.map.plan_sale_num eq null || cur.map.plan_sale_num eq 0 || cur.map.out_num eq null || cur.map.out_num eq 0}">0</c:if>
		    <c:if test="${cur.map.plan_sale_num ne null && cur.map.plan_sale_num ne 0 && cur.map.out_num ne null && cur.map.out_num ne 0}">((${cur.map.out_num}) * 100)/ ${cur.map.plan_sale_num}</c:if>%</td>
		</tr>
	</c:forEach>
</table>
 <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaOrderPlanStatement.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="60" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript"> 
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "channellist");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("plan_year", "${fn:escapeXml(af.map.plan_year)}");							
			pager.addHiddenInputs("plan_month", "${fn:escapeXml(af.map.plan_month)}");
			pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");
            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");							
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript"
	src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){

	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}

	
	//客户类型初始化
	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});
	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	$("#v_customer_type1").change();

    //导出
	$("#export_excel").click(function(){
		if(confirm("提示，您确认导出数据？")){
			$("#export_id").val(1);
			$("#bottomPageForm").submit();
		}
		$("#export_id").val(0);
	});
});
	</script>
</body>
</html>