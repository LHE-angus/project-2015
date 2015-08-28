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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css"
	rel="stylesheet" type="text/css" />
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
<div class="rtabcont1"><html-el:form
	action="/admin/KonkaMobileSailDataBill">
	<html-el:hidden property="method" value="listForFinalAudit" />
	<html-el:hidden property="mod_id" styleId="mod_id" />
	<table width="100%" border="0" cellspacing="1" cellpadding="5"
		class="rtable1">
		<tr>
			<td width="10%" nowrap="nowrap" title="客户业务员所在部门" align="right">
			<strong class="fb">部门：</strong></td>
			<td width="28%">
			<html-el:select property="dept_id">
			<html-el:option value="">请选择</html-el:option>
			<c:forEach items="${konkaDeptList}" var="cur" varStatus="vs" >
			<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
			</c:forEach>
			</html-el:select>
			</td>
			<td width="7%" align="right"><strong class="fb">转单人：</strong></td>
			<td width="18%"><html-el:text property="switch_user_name_like"
				size="30" style="width:170px;" maxlength="40"
				styleId="switch_user_name_like" styleClass="webinput" /></td>
		</tr>
		
		<tr>
			<td colspan="4">&nbsp;</td>
			<td colspan="2" align="left" nowrap="nowrap"><html-el:submit
				styleId="btn_submit" styleClass="but1">搜索</html-el:submit>
			&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>
</html-el:form></div>
<div class="rtabcont1"><%@ include
	file="/commons/pages/messages.jsp"%></div>

<div class="rtabcont1" style="overflow-x: auto;">
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
	<tr class="tabtt1">
		<td width="5%" align="center" nowrap="nowrap">序号</td>
		<logic-el:match name="popedom" value="+2+">
			<td width="5%" nowrap="nowrap" align="center">操作</td>
		</logic-el:match>
		<td align="center" nowrap="nowrap">分公司</td>
		<td align="center" nowrap="nowrap">转单人</td>
		<td align="center" nowrap="nowrap">日期</td>
		<td align="center" nowrap="nowrap">标题</td>
		<td align="center" nowrap="nowrap">数量</td>
		<td align="center" nowrap="nowrap">金额</td>
		<td align="center" nowrap="nowrap">申请提成金额</td>
		<td align="center" nowrap="nowrap">初审提成金额</td>
		<td nowrap="nowrap" align="center">终审金额</td>
	</tr>
	<c:forEach var="cur" items="${entityList}" varStatus="vs">
		<tr class="tabtt2" align="center">
			<td align="center" nowrap="nowrap">${(af.map.pager.currentPage -
			1) * af.map.pager.pageSize + vs.count}</td>
			<td align="center" nowrap="nowrap">
			<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null,'KonkaMobileSailDataBill.do', 'viewFinalAudit','id=${cur.switch_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span>
			<logic-el:match name="popedom" value="+2+">
			<c:if test="${role_id_125 && cur.state == 0 }"><span
					style="cursor: pointer;" class="fblue"
					onclick="doNeedMethod(null,'KonkaMobileSailDataBill.do', 'toFinalAudit','id=${cur.switch_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">审核</span>
				</c:if>
				<c:if test="${!role_id_125}"><span
					style="cursor: pointer;color:grey;">审核</span>
				</c:if>
					</logic-el:match>
				</td>
		
			<td>${cur.map.subcomp_name}</td>
			<td>${cur.switch_user_name}</td>
			<td align="center" nowrap="nowrap"><fmt:formatDate
				value="${cur.switch_date}" pattern="yyyy-MM-dd" /></td>
			<td>${cur.switch_title}</td>
			<td>${cur.map.num}</td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.map.all_price}" type="currency" /> </span></td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.map.dec_money}" type="currency" /> </span></td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.map.audit_money}" type="currency" />
			</span></td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.map.final_audit_money}"
				type="currency" /> </span></td>
		</tr>
		<c:if test="${vs.last eq true}">
			<c:set var="i" value="${vs.count}" />
		</c:if>
	</c:forEach>
</table>
<form id="bottomPageForm" name="bottomPageForm" method="post"	action="KonkaMobileSailDataBill.do">
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td height="60" align="center"><script type="text/javascript"
			src="${ctx}/commons/scripts/pager.js">;</script> 
			<script	type="text/javascript"> 
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "listForFinalAudit");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
            pager.addHiddenInputs("switch_user_name_like", "${af.map.switch_user_name_like}");
            document.write(pager.toString());
            </script></td>
	</tr>
</table>
</form>

</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> <script
	type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/imgpreview.0.22.js"></script> <script
	type="text/javascript"
	src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> <script
	type="text/javascript">//<![CDATA[
$(document).ready(function(){
}
//]]></script> <jsp:include page="/__analytics.jsp" />
</body>
</html>
