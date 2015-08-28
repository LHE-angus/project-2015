<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.rtable1 td {
	padding: 2px 5px;
}

.button1 {
	width: 65px;
	height: 32px;
	font: normal 12px/20px "宋体";
	text-align: left;
	color: #fff;
	border: 1px #ccc solid;
	border-left: 0;
	cursor: pointer;
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
<div class="rtabcont1">
<fieldset
	style="border: 1px solid #ccc; color: #666; padding-left: 10px;">
<legend>说明</legend>
<ul style="margin-left: 20px;">
	<li>1. 审核客户订单的角色（用户）是依据与分公司或总部对订单流程的定义；</li>
	<li>2.
	总部统一要求，客户提交（非业务员代提交）的订单均未确定审核流程，这些订单只能由分管客户的业务员审核，审核时需确定订单审核流程。</li>
	<li>3. 以下列表数据是根据您的数据级别可查询“<c:choose>
		<c:when test="${max_dlevel eq 9}">集团及旗下全部分公司/直管经营部</c:when>
		<c:when test="${max_dlevel eq 8}">您所在分公司及下级经办/部门</c:when>
		<c:when test="${max_dlevel eq 7}">您所在部门及下级部门</c:when>
		<c:when test="${max_dlevel eq 0}">您直管</c:when>
	</c:choose>”（以最高级别计算）的数据为依据综合查询得出；</li>
</ul>
</fieldset>
</div>
<div class="rtabcont1"><html-el:form
	action="/admin/KonkaOrderAudit">
	<html-el:hidden property="method" value="list" />
	<html-el:hidden property="mod_id" />
	<html-el:hidden property="tj_type" styleId="tj_type"
		value="${af.map.tj_type}" />
	<table width="100%" border="0" cellspacing="1" cellpadding="0"
		class="rtable1">
		<tr>
			<td align="right"><strong class="fb">交易流水号：</strong></td>
			<td><html-el:text property="trade_index_like" size="20"
				maxlength="40" styleId="trade_index_like" /></td>
			<td align="right"><strong class="fb">下单日期：</strong></td>
			<td><html-el:text property="order_date_start"
				styleId="order_date_start" size="10" maxlength="10" readonly="true"
				onclick="new Calendar(2011, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /> 至 <html-el:text
				property="order_date_end" styleId="order_date_end" size="10"
				maxlength="10" readonly="true"
				onclick="new Calendar(2011, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
			<td align="right"><strong class="fb">客户名称：</strong></td>
			<td><html-el:text property="user_shop_name_like" size="40"
				maxlength="40" styleId="user_shop_name_like" /></td>
		</tr>
		<tr>
			<!-- 	          	<td align="right"><strong class="fb">流程：</strong></td> -->
			<!-- 	          	<td> -->
			<%-- 	          		<html-el:select property="process_id" styleId="process_id" onchange="this.form.submit();" style="width:100px;"> --%>
			<%-- 	          			<html-el:option value="">=请选择=</html-el:option> --%>
			<%-- 	          			<c:forEach var="cur" items="${processList}"> --%>
			<%-- 	          				<html-el:option value="${cur.id}"> --%>
			<%-- 	          			    <c:if test="${cur.add_dept_id eq 0}">★[统一流程] </c:if> --%>
			<%-- 		          			<c:if test="${cur.add_dept_id ne 0}">[${cur.add_dept_name}]</c:if> --%>
			<%-- 		          			${cur.process_desc} --%>
			<%-- 	          				</html-el:option> --%>
			<%-- 	          			</c:forEach> --%>
			<%-- 	          		</html-el:select> --%>
			<!-- 	          	</td> -->
			<td align="right"><strong class="fb">支付方式：</strong></td>
			<td><html-el:select property="pay_type" styleClass="webinput"
				styleId="pay_type">
				<html-el:option value="">--请选择--</html-el:option>
				<html-el:option value="4">现汇</html-el:option>
				<html-el:option value="5">帐期</html-el:option>
				<html-el:option value="6">承兑</html-el:option>
				<html-el:option value="45">现汇、帐期</html-el:option>
				<html-el:option value="46">现汇、承兑</html-el:option>
				<html-el:option value="56">帐期、承兑</html-el:option>
				<html-el:option value="456">现汇、帐期、承兑</html-el:option>
			</html-el:select></td>
			<td align="right"><strong class="fb">配送方式：</strong></td>
			<td><html-el:select property="send_type" styleClass="webinput"
				styleId="send_type">
				<html-el:option value="">--请选择--</html-el:option>
				<html-el:option value="1">自提</html-el:option>
				<html-el:option value="2">配送</html-el:option>
			</html-el:select></td>
			<td align="right"><strong class="fb">部门：</strong></td>
			<td><c:if test="${empty current_dept}">
				<html-el:select property="l3_dept_id" styleId="l3_dept_id"
					disabled="${disabled}">
					<html-el:option value="">-分公司/经营部-</html-el:option>
				</html-el:select>
				<html-el:select property="l4_dept_id" styleId="l4_dept_id">
					<html-el:option value="">-请选择-</html-el:option>
				</html-el:select>
				<html-el:select property="l5_dept_id" styleId="l5_dept_id">
					<html-el:option value="">-请选择-</html-el:option>
				</html-el:select>
			</c:if> <c:if test="${not empty current_dept}">
		          		${fn:replace(current_dept.full_name, ',', ' &gt; ')}
		          	</c:if></td>
		</tr>
		<tr>
			<td align="right"><strong class="fb">产品型号：</strong></td>
			<td><html-el:text property="pd_name_like" styleClass="webinput"
				styleId="pd_name_like" maxlength="40" /></td>
			<td align="right"><strong class="fb">R3客户编码：</strong></td>
			<td><html-el:text property="ag_like" styleClass="webinput"
				styleId="ag_like" maxlength="40" /></td>
			<td colspan="2" style="padding-left: 30px"><html-el:submit
				styleClass="but1" value="搜索" /></td>
		</tr>
		<tr>
			<td align="right"><strong class="fb">排序方式：</strong></td>
			<td colspan="5">
			<img class="order_by_img" src="${ctx}/images/button/rqs.jpg" width="45px" height="20px" alt="1" />&nbsp;&nbsp;
			<img class="order_by_img" src="${ctx}/images/button/rqx.jpg" width="45px" height="20px" alt="2" />&nbsp;&nbsp; 
		  	<img class="order_by_img" src="${ctx}/images/button/r3bms.jpg" width="45px" height="20px" alt="3" /> &nbsp;&nbsp;
			<img class="order_by_img" src="${ctx}/images/button/r3bms.jpg" width="45px" height="20px" alt="4" />&nbsp;&nbsp;
			<img class="order_by_img" src="${ctx}/images/button/sls.jpg" width="45px" height="20px" alt="5" /> &nbsp;&nbsp; 
			<img class="order_by_img" src="${ctx}/images/button/slx.jpg" width="45px"	height="20px" alt="6" /> &nbsp;&nbsp;
			<img class="order_by_img" src="${ctx}/images/button/jes.jpg" width="45px" height="20px" alt="7" />&nbsp;&nbsp;
			<img class="order_by_img" src="${ctx}/images/button/jex.jpg" width="45px" height="20px" alt="8" />&nbsp;&nbsp;
			</td>
			<html-el:hidden property="pathSrc" value="" styleClass="pathSrc" />
		</tr>
	</table>
	<html-el:hidden property="order_by_index" styleId="order_by_index"
		value="" />
</html-el:form></div>
<script type="text/javascript">
  	
  </script>
<div class="rtabcont1"><%@ include
	file="/commons/pages/messages.jsp"%>
<div style="overflow-x: auto;">
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
	<tr class="tabtt1">
		<td width="5%" nowrap="nowrap" align="center">序号</td>
		<td width="8%" nowrap="nowrap" align="center">下单日期</td>
		<td width="10%" nowrap="nowrap" align="center">交易流水号</td>
		<td nowrap="nowrap" align="center">客户名称</td>
		<td width="5%" nowrap="nowrap" align="center">数量</td>
		<td width="8%" nowrap="nowrap" align="center">金额（元）</td>
		<td width="8%" nowrap="nowrap" align="center">折扣（元）</td>
		<td width="8%" nowrap="nowrap" align="center">审核状态</td>

		<td nowrap="nowrap" align="center">分公司</td>
		<td nowrap="nowrap" align="center">经办</td>
		<td width="5%" nowrap="nowrap" align="center">流程</td>
		<td width="8%" nowrap="nowrap" align="center">R3客户编码</td>
		<td width="8%" nowrap="nowrap" align="center">操作</td>
	</tr>
	<c:forEach var="cur" items="${entityList}" varStatus="vs">
		<tr>
			<td align="center" nowrap="nowrap">${(af.map.pager.currentPage -
			1) * af.map.pager.pageSize + vs.count}</td>
			<td align="center" nowrap="nowrap"><fmt:formatDate
				value="${cur.order_date}" pattern="yyyy-MM-dd" /></td>
			<td align="center" nowrap="nowrap"><a
				href="${ctx}/manager/admin/KonkaOrderSearch.do?method=view&order_id=${cur.id}&mod_id=${af.map.mod_id}"
				style="color: blue; text-decoration: underline;">${cur.trade_index}</a></td>
			<td align="left"><a style="cursor: pointer; color: blue;"
				href="KonkaR3MmtMatch.do?method=detail&id=${cur.cust_id}&mod_id=101010&key=1">${cur.user_shop_name}</a></td>
			<td align="center" nowrap="nowrap">${cur.order_num}</td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber
				value="${cur.money}" type="currency" /></span></td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber
				value="${cur.good_discount_price}" type="currency" /></span></td>
			<td align="center" nowrap="nowrap"><c:choose>
				<c:when test="${cur.audit_state eq 3}">审核通过</c:when>
				<c:otherwise>待审核</c:otherwise>
			</c:choose></td>
			<td align="right" nowrap="nowrap">${cur.map.fgsName}</td>
			<td align="right" nowrap="nowrap">${cur.map.jbName}</td>
			<td align="center" nowrap="nowrap">${empty cur.process_id ?
			'未确定' : '已确定'}</td>
			<td align="center" nowrap="nowrap"><a
				style="cursor: pointer; color: blue;"
				href="KonkaR3MmtMatch.do?method=detail&id=${cur.cust_id}&mod_id=101010&key=1">${cur.ag}</a></td>
			<td align="center" nowrap="nowrap"><span
				style="cursor: pointer;" class="fblue"
				onclick="doNeedMethod(null, 'KonkaOrderAudit.do', 'audit','id=${cur.id}&'+$('#bottomPageForm').serialize())">审核</span>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<form id="bottomPageForm" name="bottomPageForm" method="post"
	action="KonkaOrderAudit.do">
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td height="40" align="center"><script type="text/javascript"
			src="${ctx}/commons/scripts/pager.js">;</script> <script
			type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						pager.addHiddenInputs("method", "list");
						pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
						pager.addHiddenInputs("order_date_start", "${af.map.order_date_start}");
						pager.addHiddenInputs("order_date_end", "${af.map.order_date_end}");
						pager.addHiddenInputs("user_shop_name_like", "${af.map.user_shop_name_like}");
						pager.addHiddenInputs("process_id", "${af.map.process_id}");
						pager.addHiddenInputs("pay_type", "${af.map.pay_type}");
						pager.addHiddenInputs("send_type", "${af.map.send_type}");
						pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");
						pager.addHiddenInputs("ag_like", "${af.map.ag_like}");					
						pager.addHiddenInputs("l3_dept_id", "${af.map.l3_dept_id}");							
						pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");							
						pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");	
						document.write(pager.toString());
					</script></td>
	</tr>
</table>
</form>
</div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script type="text/javascript">//<![CDATA[ 
//  var ff=document.form[0];


$(document).ready(function(){
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });


var ff=	document.forms[0];
$(".order_by_img").click(function(){
	var path="/images/button/"+"xz"+$(this)[0].src.substr($(this)[0].src.lastIndexOf("/")+1);
 	$(this).attr("src","${ctx}"+path);
	var order_by_index=	$(this).attr("alt");
	$("#order_by_index").val(order_by_index);
	loading();
	ff.submit();	
	
});

var order_by_index='${order_by_index}';
$(".order_by_img").each(function(){
	if($(this).attr("alt")== order_by_index)
	{
		$(this).attr("src","${ctx}"+"/images/button/"+"xz"+$(this)[0].src.substr($(this)[0].src.lastIndexOf("/")+1));
	}
});
	
	var current_dept = '${empty current_dept}';
	if(current_dept != 'false'){
		$("#l3_dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l3_dept_id}"});
		$("#l4_dept_id").attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
		$("#l5_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});
	
		$("#l3_dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "${empty cs_par_id ? 0 : cs_par_id}");
		$("#l3_dept_id").change();
	}
});
	function loading(){
	jLoading("&nbsp;&nbsp;正在加载数据...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
};

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>