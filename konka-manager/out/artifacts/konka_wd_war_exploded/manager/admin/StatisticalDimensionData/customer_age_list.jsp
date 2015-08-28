<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
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
	</tr>
</table>
</div>
<div class="rtabcont1"><html-el:form
	action="/admin/StatisticalDimensionData">
	<html-el:hidden property="method" value="customerAgeList" />
	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<table width="100%" border="0" cellspacing="5" cellpadding="0"
		class="rtable1">
		<tr>
			<td align="right"><strong class="fb">分公司：</strong></td>
			<td><html-el:select property="fgs_id" styleId="fgs_id"
				style="width:100px">
				<html-el:option value=" ">请选择...</html-el:option>
				<c:forEach var="cur" items="${sybDeptInfoList}">
					<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
				</c:forEach>
			</html-el:select></td>
			<td align="right"><strong class="fb">客户类型：</strong></td>
			<td><html-el:select property="v_customer_type1"
				styleId="v_customer_type1" style="width:80px;">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select> <html-el:select property="v_customer_type2"
				styleId="v_customer_type2" style="width:80px;">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select></td>
			<td align="right"><strong class="fb">R3分类：</strong></td>
			<td><html-el:select property="is_sdf" styleId="is_sdf"
				style="width:70px">
				<html-el:option value="0">售达方</html-el:option>
				<html-el:option value="1">送达方</html-el:option>
			</html-el:select></td>
		</tr>
		<tr>
			<td align="right"><strong class="fb">客户R3编码：</strong></td>
			<td><html-el:text property="r3_code_like" styleId="r3_code_like"
				maxlength="60" size="20" styleClass="webinput" /></td>
			<td align="right"><strong class="fb">客户名称：</strong></td>
			<td><html-el:text property="customer_name_like"
				styleId="customer_name_like" maxlength="100" size="20"
				styleClass="webinput" /></td>
			<td align="right"><strong class="fb">业务员：</strong></td>
			<td><html-el:text property="ywy_name_like"
				styleId="ywy_name_like" maxlength="100" size="9"
				styleClass="webinput" /></td>
		</tr>
		<tr>
			<td align="right"><strong class="fb">加盟时间：</strong></td>
			<td><html-el:text property="joindate_s" styleId="joindate_s"
				size="10" maxlength="10" readonly="true"
				onclick="new Calendar(1980, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /> 到 <html-el:text
				property="joindate_e" styleId="joindate_e" size="10" maxlength="10"
				readonly="true" onclick="new Calendar(1980, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
			<td align="right"><strong class="fb">加盟年限：</strong></td>
			<td><html-el:text property="joindatetype_like"
				styleId="joindatetype_like" maxlength="100" size="20"
				styleClass="webinput" /></td>
			<td valign="middle" nowrap="nowrap" class="title_item" align="right"><html-el:submit
				styleClass="but1" value="搜索" /></td>
			<td><input class="but_excel" type="button" name="export_excel"
				id="export_excel" value="导出" /></td>
		</tr>
	</table>
</html-el:form></div>
<div class="rtabcont1"><%@ include
	file="/commons/pages/messages.jsp"%></div>
<div class="rtabcont1">
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
	<tr class="tabtt1">
		<td nowrap="nowrap" align="center" width="4%">序号</td>
		<td nowrap="nowrap" align="center" width="8%">分公司</td>
		<td nowrap="nowrap" align="center" width="6%">客户类型</td>
		<td nowrap="nowrap" align="center" width="10%">客户R3编码</td>
		<td nowrap="nowrap" align="center" width="6%">R3分类</td>
		<td nowrap="nowrap" align="center" width="20%">客户名称</td>
		<td nowrap="nowrap" align="center" width="6%">业务员</td>
		<td nowrap="nowrap" align="center" width="8%">加盟时间</td>
		<td nowrap="nowrap" align="center" width="8%">加盟年限</td>
		<td nowrap="nowrap" align="center" width="8%">上年结算额</td>
		<td nowrap="nowrap" align="center" width="8%">本年结算额</td>
		<td nowrap="nowrap" align="center" width="4%">增长率</td>
		<td nowrap="nowrap" align="center" width="4%">贡献率</td>
	</tr>
	<tbody>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
			<tr>
				<td align="center" nowrap="nowrap">${(af.map.pager.currentPage
				- 1) * af.map.pager.pageSize + vs.count}</td>
				<td align="center">${fn:escapeXml(cur.map.fgs_name)}</td>
				<td align="center">${fn:escapeXml(cur.map.c_name)} <c:if
					test="${empty cur.map.c_name}">
					<span style="color: #ccc;">未指定</span>
				</c:if></td>
				<td align="center">${fn:escapeXml(cur.r3_code)}</td>
				<td align="center">
				<c:if test="${cur.is_sdf eq 0}">售达方</c:if>
				<c:if test="${cur.is_sdf eq 1}">送达方</c:if>
				</td>
				<td align="left">${fn:escapeXml(cur.customer_name)}</td>
				<td align="center">${fn:escapeXml(cur.map.ywy_name)}</td>
				<td align="center"><fmt:formatDate value="${cur.join_date}"
					pattern="yyyy-MM-dd"></fmt:formatDate></td>
				<td align="center">${fn:escapeXml(cur.join_date_type) }</td>
				<!-- 加盟年限 -->
				<td align="right"><c:if
					test="${cur.pre_annual_settle_money ne null && cur.pre_annual_settle_money ne 0}">
					<fmt:formatNumber value="${cur.pre_annual_settle_money}"
						type="currency" pattern="0.00"></fmt:formatNumber>
				</c:if> <c:if test="${cur.pre_annual_settle_money eq null}">0.00</c:if></td>
				<td align="right"><c:if
					test="${cur.annual_settle_money ne null && cur.annual_settle_money ne 0}">
					<fmt:formatNumber value="${cur.annual_settle_money}"
						type="currency" pattern="0.00"></fmt:formatNumber>
				</c:if> <c:if test="${cur.annual_settle_money eq null}">0.00</c:if></td>
				<td align="right"><c:if
					test="${cur.annual_settle_money_tbzf ne null && cur.annual_settle_money_tbzf ne 0}">
					<fmt:formatNumber value="${(cur.annual_settle_money_tbzf) / 100}"
						type="percent" pattern="0.00%"></fmt:formatNumber>
				</c:if> <c:if test="${cur.annual_settle_money_tbzf eq null}">0.00%</c:if></td>
				<td align="right"><c:if
					test="${cur.annual_settle_money_bz ne null && cur.annual_settle_money_bz ne 0}">
					<fmt:formatNumber value="${(cur.annual_settle_money_bz / 100)}"
						type="currency" pattern="0.00%"></fmt:formatNumber>
				</c:if> <c:if test="${cur.annual_settle_money_bz eq null}">0.00%</c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<form id="bottomPageForm" name="bottomPageForm" method="post"
	action="StatisticalDimensionData.do"><input id='export_id'
	style="display: none" name='excel_all' value='0' />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="40">
		<div style="text-align: right; padding-right: 5px;"><script
			type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
		<script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "customerAgeList");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("joindate_s", "${af.map.joindate_s}");
				pager.addHiddenInputs("joindate_s", "${af.map.joindate_e}");
				pager.addHiddenInputs("fgs_id", "${af.map.fgs_id}");
				pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");
	            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");
				pager.addHiddenInputs("is_sdf", "${af.map.is_sdf}");
				pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
				pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
				pager.addHiddenInputs("ywy_name_like", "${af.map.ywy_name_like}");
				pager.addHiddenInputs("joinyear_like", "${af.map.joinyear_like}");
				document.write(pager.toString());
			  </script></div>
		</td>
	</tr>
</table>
</form>
</div>
<div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
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
<script type="text/javascript">//<![CDATA[
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
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
