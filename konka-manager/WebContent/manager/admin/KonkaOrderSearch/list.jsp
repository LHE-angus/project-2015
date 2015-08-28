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
<style type="text/css">

.rtable1 td {
	padding: 2px 5px;
}

.filed_border {
	border-left: 1px solid #ccc;;
	border-right: 1px solid #ccc;;
	border-bottom: 1px solid #ccc;;
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
<fieldset style="border-top: 1px solid #ccc; padding-left: 10px;"
	id="fs_hide"><legend id="l_toggle" style="cursor: pointer;"
	title="点击查看说明">说明</legend>
<ol
	style="margin-left: 20px; padding-left: 20px; list-style: decimal; display: none;"
	id="ol_hide">
	<li>本页面可查询已审核的订单，支持下单日期最多跨度为90天的查询；</li>
	<li>以下列表数据是根据您的数据级别可查询“<c:choose>
		<c:when test="${max_dlevel eq 9}">集团及旗下全部分公司/直管经营部</c:when>
		<c:when test="${max_dlevel eq 8}">您所在分公司及下级经办/部门</c:when>
		<c:when test="${max_dlevel eq 7}">您所在部门及下级部门</c:when>
		<c:when test="${max_dlevel eq 0}">您直管</c:when>
	</c:choose>”（以最高级别计算）的数据为依据综合查询得出（“审核状态”查询条件不设置）；</li>
	<li>以上4中职位查询分公司全部数据的审核状态为已审核不表示为当前人已审核，表示订单已有审核记录；</li>
	<li>2013年8月9日14:10:51前的订单<a
		href="${ctx}/jxc/KonkaJxcOrderInfoView.do?mod_id=205400"
		style="color: #00F; text-decoration: underline;">去这里查询</a>；</li>
</ol>
</fieldset>
<br />
<html-el:form action="/admin/KonkaOrderSearch">
	<html-el:hidden property="method" value="list" />
	<html-el:hidden property="mod_id" />
	<html-el:hidden property="tj_type" styleId="tj_type"
		value="${af.map.tj_type}" />
	<html-el:hidden property="current_fgs_code" styleId="current_fgs_code"
		value="${current_fgs_code}" />
	<html-el:hidden property="current_dept_code"
		styleId="current_dept_code" value="${current_dept_code}" />
	<table width="100%" border="0" cellspacing="1" cellpadding="5"
		class="rtable1">
		<tr>
			<td align="right" nowrap="nowrap"><strong class="fb">交易流水号：</strong></td>
			<td><html-el:text property="trade_index_like" size="20"
				maxlength="40" styleId="trade_index_like" /></td>
			<td align="right"><strong class="fb">提交日期：</strong></td>
			<td><html-el:text property="order_date_start"
				styleId="order_date_start" size="10" maxlength="10" readonly="true"
				onclick="new Calendar(2011, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /> 至 <html-el:text
				property="order_date_end" styleId="order_date_end" size="10"
				maxlength="10" readonly="true"
				onclick="new Calendar(2011, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
			<td align="right"><strong class="fb">分公司：</strong></td>
			<td colspan="3"><c:if
				test="${show_fgs and show_fgs_jb and show_fgs_gr}">
				<html-el:select property="dept_id" styleId="dept_id"
					disabled="${show_fgs}" style="width:90px">
					<html-el:option value="">-请选择-</html-el:option>
				</html-el:select>
			</c:if> <c:if test="${!(show_fgs and show_fgs_jb and show_fgs_gr)}">
				<html-el:select property="dept_id" styleId="dept_id"
					disabled="${show_fgs}" style="width:90px">
					<html-el:option value="">-请选择-</html-el:option>
				</html-el:select>
				<html-el:select property="l4_dept_id" disabled="${show_fgs_jb}"
					styleId="l4_dept_id" style="width:110px">
					<html-el:option value="">-请选择-</html-el:option>
				</html-el:select>
				<html-el:select property="l5_dept_id" styleId="l5_dept_id"
					style="width:110px">
					<html-el:option value="">-请选择-</html-el:option>
				</html-el:select>
			</c:if></td>
		</tr>
		<tr>
			<td align="right"><strong class="fb">支付方式：</strong></td>
			<td><html-el:select property="pay_type" styleClass="webinput"
				styleId="pay_type">
				<html-el:option value="">-请选择-</html-el:option>
				<html-el:option value="4">现汇</html-el:option>
				<html-el:option value="5">帐期</html-el:option>
				<html-el:option value="6">承兑</html-el:option>
				<html-el:option value="45">现汇、帐期</html-el:option>
				<html-el:option value="46">现汇、承兑</html-el:option>
				<html-el:option value="56">帐期、承兑</html-el:option>
				<html-el:option value="456">现汇、帐期、承兑</html-el:option>
			</html-el:select></td>
			<td align="right"><strong class="fb">变更短信：</strong></td>
			<td><html-el:select property="kh_confirm_state"
				styleClass="webinput" styleId="kh_confirm_state">
				<html-el:option value="">-请选择-</html-el:option>
				<html-el:option value="0">未变更</html-el:option>
				<html-el:option value="1">已发送提醒</html-el:option>
				<html-el:option value="-1">未发送提醒</html-el:option>
			</html-el:select></td>
			<td align="right"><strong class="fb">配送方式：</strong></td>
			<td><html-el:select property="send_type" styleClass="webinput"
				styleId="send_type">
				<html-el:option value="">-请选择-</html-el:option>
				<html-el:option value="1">自提</html-el:option>
				<html-el:option value="2">配送</html-el:option>
			</html-el:select></td>
			<td align="right"><strong class="fb">订单状态：</strong></td>
			<td><html-el:select property="or_audit_state"
				styleClass="webinput" styleId="or_audit_state">
				<html-el:option value="">-请选择-</html-el:option>
				<html-el:option value="0">未审核</html-el:option>
				<html-el:option value="1">审核中</html-el:option>
				<html-el:option value="3">已完结</html-el:option>
				<html-el:option value="4">已作废</html-el:option>
			</html-el:select></td>
		</tr>
		<tr>

			<td align="right"><strong class="fb">发货状态：</strong></td>
			<td><html-el:select property="is_delivered"
				styleClass="webinput" styleId="is_delivered">
				<html-el:option value="">-请选择-</html-el:option>
				<html-el:option value="0">未发货</html-el:option>
				<html-el:option value="1">已发货</html-el:option>
			</html-el:select></td>
			<td align="right"><strong class="fb">订单类型：</strong></td>
			<td><html-el:select property="doc_type" styleClass="webinput"
				styleId="doc_type">
				<html-el:option value="">-请选择-</html-el:option>
				<html-el:option value="ZFOR">ZFOR</html-el:option>
				<html-el:option value="ZFGC">ZFGC</html-el:option>
				<html-el:option value="ZFCR">ZFCR</html-el:option>
			</html-el:select></td>
			<td align="right"><strong class="fb">客户类型：</strong></td>
			<td><html-el:select property="customer_type_index"
				styleId="customer_type_index">
				<html-el:option value="">请选择...</html-el:option>
				<c:forEach var="cur" items="${konkaCategoryList}">
					<html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
				</c:forEach>
			</html-el:select></td>
			<td align="right"><strong class="fb">订单同步状态：</strong></td>
			<td><html-el:select property="sync_state" styleClass="webinput"
				styleId="sync_state">
				<html-el:option value="">-请选择-</html-el:option>
				<html-el:option value="0">未同步</html-el:option>
				<html-el:option value="1">已同步</html-el:option>
			</html-el:select></td>
		</tr>
		<tr>

			<td align="right"><strong class="fb">R3客户编码：</strong></td>
			<td><html-el:text property="ag_like" styleClass="webinput"
				styleId="ag_like" maxlength="40" /></td>
			<td align="right"><strong class="fb">客户名称：</strong></td>
			<td><html-el:text property="user_shop_name_like" size="20"
				maxlength="40" styleId="user_shop_name_like" /></td>
			<td align="right"><strong class="fb">R3物流单号：</strong></td>
			<td><html-el:text property="vbedl_like" styleClass="webinput"
				styleId="vbedl_like" maxlength="40" /></td>
			<td align="right"><strong class="fb">R3物流单号：</strong></td>
			<td><html-el:select property="vbedl_null" styleClass="webinput"
				styleId="vbedl_null">
				<html-el:option value="">-请选择-</html-el:option>
				<html-el:option value="0">有R3物流单号</html-el:option>
				<html-el:option value="1">无R3物流单号</html-el:option>
			</html-el:select></td>
		</tr>
		<tr>
			<td align="right"><strong class="fb">产品型号：</strong></td>
			<td><html-el:text property="pd_name_like" styleClass="webinput"
				styleId="pd_name_like" maxlength="40" /></td>
			<td align="right"><strong class="fb">R3单号：</strong></td>
			<td><html-el:text property="r3_id" styleClass="webinput"
				title="不能超过18位数字" styleId="r3_id" maxlength="18" /></td>
			<td align="right"><strong class="fb">订单来源：</strong></td>
			<td><html-el:select property="order_type" styleClass="webinput"
				styleId="order_type">
				<html-el:option value="">-请选择-</html-el:option>
				<html-el:option value="0">在线下单</html-el:option>
				<html-el:option value="1">图片下单</html-el:option>
				<html-el:option value="2">触网转单</html-el:option>
				<html-el:option value="4">从返利转</html-el:option>
				<html-el:option value="5">DRP转入</html-el:option>
			</html-el:select></td>
			<td align="right"><strong class="fb">订单变更：</strong></td>
			<td><html-el:select property="is_change" styleClass="webinput"
				styleId="is_change">
				<html-el:option value="">-请选择-</html-el:option>
				<html-el:option value="0">未变更</html-el:option>
				<html-el:option value="1">变更未同步</html-el:option>
				<html-el:option value="2">变更并同步</html-el:option>
			</html-el:select></td>
		</tr>
		<tr>
			<td align="right"><strong class="fb">同步人：</strong></td>
			<td><html-el:text property="sync_user" size="20" maxlength="40"
				styleId="sync_user" /></td>
			<td align="right"><strong class="fb">同步时间：</strong></td>
			<td><html-el:text property="sync_date_start"
				styleId="sync_date_start" size="10" maxlength="10" readonly="true"
				onclick="new Calendar(2011, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /> 至 <html-el:text
				property="sync_date_end" styleId="sync_date_end" size="10"
				maxlength="10" readonly="true"
				onclick="new Calendar(2011, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>

		</tr>
		<tr>
			<td colspan="8"><input class="but_excel" type="button"
				name="export_excel" id="export_excel" value="导出" /> <c:if
				test="${af.map.dept_type eq 1}">
				<!-- 系统管理员 -->
				<input type="button" id="syncBtnAllsys" class="but8" value="同步物流"></input>
				<font color="#696969"></font>
			</c:if> <c:if
				test="${af.map.dept_type eq 2 and (role_id_eq_30 or role_id_eq_57 or role_id_eq_30 or role_id_eq_57 or role_id_eq_34 or role_id_eq_36 or role_id_eq_38 or role_id_eq_39 or role_id_eq_40 or role_id_eq_41 or role_id_eq_47 or role_id_eq_49 or role_id_eq_50)}">
				<!-- 系统管理员 -->
				<input type="button" id="syncBtnAllfgs" class="but8" value="同步物流"></input>
				<font color="#696969"></font>
			</c:if> <html-el:button styleId="btn_submit" property="btn_submit"
				styleClass="but1" value="搜索" />&nbsp;&nbsp;</td>
		</tr>
	</table>
</html-el:form></div>
<div class="rtabcont1" style="color: #465F6D;">&nbsp;&nbsp;注：本页订单商品数量为：${pageCount}&nbsp;台;商品总价为：￥${pageMoney}&nbsp;元;商品总折扣为：￥${pageDiscount}元。
&nbsp;&nbsp;所有商品数量为：${numCount}&nbsp;台;商品总价为：￥${moneyCount}&nbsp;元;商品总折扣为：￥${discount}元。</div>
<%@ include file="/commons/pages/messages.jsp"%>
<div style="overflow-x: auto;">
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
	<tr class="tabtt1">
		<td width="5%" nowrap="nowrap" align="center">序号</td>
		<td width="4%" nowrap="nowrap" align="center">同步|打印</td>
		<td width="10%" nowrap="nowrap" align="center">流水号</td>
		<td width="8%" nowrap="nowrap" align="center">提交日期</td>
		<td width="8%" nowrap="nowrap" align="center">R3客户编码</td>
		<td nowrap="nowrap" align="center">客户名称</td>
		<td nowrap="nowrap" align="center">客户类型</td>
		<td width="6%" nowrap="nowrap" align="center">数量</td>
		<td width="6%" nowrap="nowrap" align="center">金额￥</td>
		<td width="6%" nowrap="nowrap" align="center">折扣￥</td>
		<td width="6%" nowrap="nowrap" align="center">折扣后￥</td>
		<td width="6%" nowrap="nowrap" align="center">待审核角色</td>
		<td width="6%" nowrap="nowrap" align="center">订单状态</td>
		<td width="5%" nowrap="nowrap" align="center">R3单号</td>
		<td width="5%" nowrap="nowrap" align="center">同步人</td>
		<td width="5%" nowrap="nowrap" align="center">同步日期</td>
		<td width="5%" nowrap="nowrap" align="center">订单变更</td>
		<td width="6%" nowrap="nowrap" align="center">发货状态</td>
		<td width="5%" nowrap="nowrap" align="center">R3物流单号</td>
		<td width="5%" nowrap="nowrap" align="center">发货时间</td>
		<td width="5%" nowrap="nowrap" align="center">收货时间</td>

		<td nowrap="nowrap" align="center">分公司</td>
		<td nowrap="nowrap" align="center">经办</td>
		<td width="6%" nowrap="nowrap" align="center">变更短信</td>
		<td width="5%" nowrap="nowrap" align="center">流程</td>
		<td width="5%" nowrap="nowrap" align="center">订单来源</td>
		<td width="5%" nowrap="nowrap" align="center">创建日期</td>
		<td width="9%" nowrap="nowrap" align="center">操作</td>
	</tr>
	<c:forEach var="cur" items="${entityList}" varStatus="vs">
		<tr>
			<td align="center" nowrap="nowrap">${(af.map.pager.currentPage -
			1) * af.map.pager.pageSize + vs.count}</td>
			<td align="center" nowrap="nowrap"><!-- 电子商务分公司的订单不同步至sap,同步到DRP ,用order_type=5区分出电子商务的订单-->
			<c:choose>
				<c:when
					test="${fn:contains(popedom,'+16+') and ( role_id_eq_38 or role_id_eq_41 or role_id_eq_33 or role_id_eq_54 or role_id_eq_55 or role_id_eq_56 or role_id_eq_43 or role_id_eq_44 or role_id_eq_46) and (cur.next_audit_role_id eq -1) and ((cur.sync_state eq 0  and empty cur.r3_id) or cur.is_change eq 1)  and (cur.kh_confirm_state eq 1 or cur.kh_confirm_state eq 0)  and (cur.audit_state ne 4) and (cur.order_type ne 5)}">
					<span style="cursor: pointer;" class="fblue" id="tongbu_${cur.id}" onclick="tongbu('${cur.id}','${cur.sales_org }')">同步订单</span>|
				</c:when>
				<c:when
					test="${fn:contains(popedom,'+16+') and ( role_id_eq_38 or role_id_eq_41 or role_id_eq_33 or role_id_eq_54 or role_id_eq_55 or role_id_eq_56 or role_id_eq_43 or role_id_eq_44 or role_id_eq_46) and (cur.next_audit_role_id eq -1) and ((cur.sync_state eq 0  and empty cur.r3_id) or cur.is_change eq 1)  and (cur.kh_confirm_state eq 1 or cur.kh_confirm_state eq 0)  and (cur.audit_state ne 4) and (cur.order_type eq 5)}">
					<span style="cursor: pointer;" class="fblue" id="tongbu_${cur.id}" onclick="tbDRP('${cur.id}','${cur.trade_index}')">同步DRP</span>|
 				</c:when>
				<c:otherwise>
					<span style="color: gray;" title="提示：此订单暂不可同步，可能是：&#13;1、未被审核完成；&#13;2、被修改过但客户暂未确认；&#13;3、已被同步订单；&#13;4、您没有权限;&#13;5、订单被作废">同步订单</span>|
				</c:otherwise>
			 </c:choose>
			 <c:choose>
				<c:when
					test="${cur.sync_state eq 1 and cur.is_sh eq 0 and (role_id_eq_30 or role_id_eq_57 or role_id_eq_34 or role_id_eq_36 or role_id_eq_38 or role_id_eq_39 or role_id_eq_40 or role_id_eq_41 or role_id_eq_47 or role_id_eq_49 or role_id_eq_50)}">
					<span style="cursor: pointer;" class="fblue" id="tbwl_${cur.id}"
						onclick="tbwl(${cur.id})">同步物流</span>|
           		</c:when> 
				<c:otherwise>
					<span style="color: gray;" title="提示：此订单暂不可同步物流信息">同步物流</span>|
	            			     </c:otherwise>
			</c:choose> 
			<c:if test="${cur.printCount gt 0}">
				<span
					style="cursor: pointer; background-color: #CCFFCC; color: #646464;"
					onclick="window.open('${ctx}/jxc/KonkaJxcOrderAudit.do?method=print&' + encodeURI('id=' + ${cur.id} +'&random=' + Math.random()), '订单打印', 'dialogWidth=900px,status=no,dialogHeight=580px,resizable=yes,scrollbars=yes')">打印</span>
			</c:if> <c:if test="${(empty cur.printCount) or (cur.printCount eq 0)}">
				<span style="cursor: pointer;" class="fblue"
					onclick="window.open('${ctx}/jxc/KonkaJxcOrderAudit.do?method=print&' + encodeURI('id=' + ${cur.id} +'&random=' + Math.random()), '订单打印', 'dialogWidth=900px,status=no,dialogHeight=580px,resizable=yes,scrollbars=yes')">打印</span>
			</c:if> <c:if test="${cur.printCount gt 0}">
				<span
					style="cursor: pointer; background-color: #CCFFCC; color: #646464;"
					onclick="window.open('${ctx}/jxc/KonkaJxcOrderAudit.do?method=print&printType=a4&' + encodeURI('id=' + ${cur.id} +'&random=' + Math.random()), '订单打印', 'dialogWidth=900px,status=no,dialogHeight=580px,resizable=yes,scrollbars=yes')">A4打印</span>
			</c:if> <c:if test="${(empty cur.printCount) or (cur.printCount eq 0)}">
				<span style="cursor: pointer;" class="fblue"
					onclick="window.open('${ctx}/jxc/KonkaJxcOrderAudit.do?method=print&printType=a4&' + encodeURI('id=' + ${cur.id} +'&random=' + Math.random()), '订单打印', 'dialogWidth=900px,status=no,dialogHeight=580px,resizable=yes,scrollbars=yes')">A4打印</span>
			</c:if></td>
			<!-- 流水号           -->
			<td align="center" nowrap="nowrap"><a
				href="${ctx}/manager/admin/KonkaOrderSearch.do?method=view&order_id=${cur.id}&mod_id=${af.map.mod_id}"
				style="color: blue; text-decoration: underline;">${cur.trade_index}</a></td>
			<td align="center" nowrap="nowrap"><fmt:formatDate
				value="${cur.order_date}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
			<!-- R3客户编码 -->
			<td align="center" nowrap="nowrap"><a
				style="cursor: pointer; color: blue;"
				href="KonkaR3MmtMatch.do?method=detail&id=${cur.cust_id}&mod_id=101010&key=1">${cur.ag}</a></td>
			<!-- 客户名称 -->
			<td nowrap="nowrap"><a style="cursor: pointer; color: blue;"
				href="KonkaR3MmtMatch.do?method=detail&id=${cur.cust_id}&mod_id=101010&key=1">${cur.user_shop_name}</a></td>
			<td nowrap="nowrap" align="center">${cur.map.customer_type_name}</td>
			<td align="right" nowrap="nowrap">${cur.order_num}</td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.money}" type="currency" /></span></td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.good_discount_price}" type="currency" />
			</span></td>

			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.money+cur.good_discount_price}"
				type="currency" /> </span></td>
			<td align="center" nowrap="nowrap"><c:choose>
				<c:when test="${empty cur.map.next_audit_role_name}">无</c:when>
				<c:when test="${not empty cur.map.next_audit_role_name}">${cur.map.next_audit_role_name}</c:when>
			</c:choose></td>
			<td align="center" nowrap="nowrap"><c:choose>
				<c:when test="${cur.audit_state eq 0}">
					<span style="color: #F00;">未审核</span>
				</c:when>
				<c:when test="${cur.audit_state eq 1}">
					<span style="color: green;">审核中</span>
				</c:when>
				<c:when test="${cur.audit_state eq 3}">
					<span style="color: 00F;">已完结</span>
				</c:when>
				<c:when test="${cur.audit_state eq 4}">
					<span style="color: grey;">已作废</span>
				</c:when>
			</c:choose></td>
			<td align="center" nowrap="nowrap">${empty cur.r3_id ? '暂无' :
			cur.r3_id}</td>
			<td align="center" nowrap="nowrap">${cur.sync_user}</td>
			<td align="center" nowrap="nowrap">${cur.sync_date}</td>
			<td align="center" nowrap="nowrap"><c:choose>
				<c:when test="${cur.is_change eq 0}">
					<span style="color: grey;">未变更</span>
				</c:when>
				<c:when test="${cur.is_change eq 1}">
					<span style="color: green;">变更未同步</span>
				</c:when>
				<c:when test="${cur.is_change eq 2}">
					<span style="color: red;">变更并同步</span>
				</c:when>
			</c:choose></td>
			<td align="center" nowrap="nowrap"><c:if
				test="${cur.is_delivered eq 0}">未发货</c:if> <c:if
				test="${cur.is_delivered eq 1}">已发货</c:if></td>
			<td align="right" nowrap="nowrap">${cur.map.vbedl}</td>
			<td align="right" nowrap="nowrap">${cur.map.lfdat}</td>
			<td align="right" nowrap="nowrap">${cur.map.shdat}</td>
			<td align="right" nowrap="nowrap">${cur.map.fgsName}</td>
			<td align="right" nowrap="nowrap">${cur.map.jbName}</td>
			<td align="center" nowrap="nowrap"><span id="message_${cur.id}"
				onmouseover="show('${cur.id}')" onmouseout="hide('${cur.id}')">${fn:split('未发送提醒,未变更,已发送提醒',',')[cur.kh_confirm_state
			+ 1]} <c:if test="${cur.kh_confirm_state eq 1 }">
				<span id="messagecount_${cur.id}"> ${cur.map.messagecount}次 </span>
			</c:if> </span>&nbsp; <c:if test="${af.map.dept_type eq 1}">
				<c:if test="${cur.kh_confirm_state ne 0}">
					<c:choose>
						<c:when
							test="${(cur.map.messagecount <= 5) and (cur.is_delivered eq 0) and (cur.audit_state ne 4)}">
							<input type="button" value="发送提醒"
								onclick="sendmsg('${cur.id}','${cur.map.messagecount}')" />
						</c:when>
						<c:otherwise>
							<input disabled="disabled" type="button" value="发送提醒" />
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:if> <c:if test="${af.map.dept_type eq 2}">
				<c:choose>
					<c:when
						test="${cur.kh_confirm_state ne 0 and cur.audit_state ne 4 and cur.map.messagecount <= 5 and cur.is_delivered eq 0}">
						<input type="button" value="发送提醒"
							onclick="sendmsg('${cur.id}','${cur.map.messagecount}')" />
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</c:if>

			<div id="msgshow_${cur.id}"
				style="background-color: pink; display: none; position: absolute; left: 500px; top: 380px; z-index: 99999">
			<ul>
				<c:forEach var="cur2"
					items="${cur.map.KonkaOrderInfoMessageSendList}" varStatus="vs2">
					<li>接收人：${cur2.receiver_name}&emsp;手机号码：${cur2.receiver_phone}&emsp;时间：<fmt:formatDate
						value="${cur2.send_date}" pattern="yyyy年MM月dd日" /></li>
				</c:forEach>
			</ul>
			</div>
			</td>
			<td align="center" nowrap="nowrap">${empty cur.process_id ?
			'未确定' : '已确定'}</td>
			<td align="center" nowrap="nowrap"><c:choose>
				<c:when test="${cur.order_type eq 0}">
					<span style="color: green;">在线下单</span>
				</c:when>
				<c:when test="${cur.order_type eq 1}">
					<span style="color: green;">图片下单</span>
				</c:when>
				<c:when test="${cur.order_type eq 2}">
					<span style="color: green;">触网转单</span>
				</c:when>
				<c:when test="${cur.order_type eq 4}">
					<span style="color: green;">从返利转</span>
				</c:when>
				<c:when test="${cur.order_type eq 5}">
					<span style="color: green;">DRP转入</span>
				</c:when>
			</c:choose></td>
			<td align="center" nowrap="nowrap"><fmt:formatDate
				value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
			<td align="center" nowrap="nowrap"><c:choose>
				<c:when
					test="${cur.audit_state eq 1 and  not empty cur.map.next_audit_role_name}">
					<span style="cursor: pointer;" id="cuiban" class="fblue"
						onclick="sendmessageprocess(${cur.id});">催办</span>|</c:when>
				<c:otherwise>
					<span style="color: gray;">催办</span>|</c:otherwise>
			</c:choose> <!-- 作废条件,审批完成,没有物流号 --> <c:choose>
				<c:when
					test="${(role_id_eq_38 or role_id_eq_41 or role_id_eq_33 or role_id_eq_54 or role_id_eq_55 or role_id_eq_56 or role_id_eq_43 or role_id_eq_44 or role_id_eq_46) and (cur.audit_state eq 3) and empty cur.map.vbedl}">
					<span style="cursor: pointer;" class="fblue"
						onclick="doNeedMethod('是否作废此订单！', 'KonkaOrderSearch.do', 'cancel','id=${cur.id}&cust_id=${cur.cust_id}&r3_id=${cur.r3_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">作废</span>|</c:when>
				<c:otherwise>
					<span style="color: gray;"
						title="提示：此订单暂不可作废，可能是：&#13;1、未被审核完成；&#13;2、您不是产品会计；&#13;3、订单已被作废。">作废</span>|</c:otherwise>
			</c:choose> <!-- 删除条件,提交人自己删除,并且未审核开始 --> <c:choose>
				<c:when
					test="${(cur.add_user_id eq af.map.user_id) and (cur.audit_state eq 0)}">
					<span style="cursor: pointer;" class="fblue"
						onclick="doNeedMethod('是否删除此订单！', 'KonkaOrderSearch.do', 'delete','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">删除</span>|</c:when>
				<c:otherwise>
					<span style="color: gray;">删除</span>|</c:otherwise>
			</c:choose> <!-- 变更条件 --> <c:choose>
				<c:when
					test="${(fn:contains(popedom,'+16+') or role_id_eq_38 or role_id_eq_41 or role_id_eq_33 or role_id_eq_54 or role_id_eq_55 or role_id_eq_56 or role_id_eq_43 or role_id_eq_44 or role_id_eq_46) and cur.next_audit_role_id eq -1  and (cur.kh_confirm_state eq 1 or cur.kh_confirm_state eq 0)  and cur.audit_state ne 4 }">
					<span style="cursor: pointer;" class="fblue"
						id="biangeng_${cur.id}"
						onclick="doNeedMethod('是否变更此订单！', 'KonkaOrderChange.do', 'change','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">变更</span>|</c:when>
				<c:otherwise>
					<span style="color: gray;"
						title="提示：此订单暂不可同步，可能是：&#13;1、未被审核完成；&#13;2、被修改过但客户暂未确认；&#13;3、已被同步订单；&#13;4、您没有权限;&#13;5、订单被作废">变更</span>|</c:otherwise>
			</c:choose></td>
		</tr>
	</c:forEach>
</table>
<form id="bottomPageForm" name="bottomPageForm" method="post"
	action="KonkaOrderSearch.do"><input id='export_id'
	style="display: none" name='excel_all' value='0' />
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td height="60" align="center"><script type="text/javascript"
			src="${ctx}/commons/scripts/pager.js">;</script> <script
			type="text/javascript">
						
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						
						pager.addHiddenInputs("method", "list");
						pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
						pager.addHiddenInputs("order_date_start", "${af.map.order_date_start}");
						pager.addHiddenInputs("order_date_end", "${af.map.order_date_end}");
						pager.addHiddenInputs("user_name_like", "${af.map.user_name_like}");
						pager.addHiddenInputs("process_state", "${af.map.process_state}");
						pager.addHiddenInputs("process_id", "${af.map.process_id}");
						pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
						pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
						pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
						
						pager.addHiddenInputs("kh_confirm_state", "${af.map.kh_confirm_state}");
						pager.addHiddenInputs("pay_type", "${af.map.pay_type}");
						pager.addHiddenInputs("doc_type", "${af.map.doc_type}");
						pager.addHiddenInputs("send_type", "${af.map.send_type}");
						pager.addHiddenInputs("my_audit_state", "${af.map.my_audit_state}");
						pager.addHiddenInputs("or_audit_state", "${af.map.or_audit_state}");
						pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");
						pager.addHiddenInputs("is_delivered", "${af.map.is_delivered}");
						pager.addHiddenInputs("ag_like", "${af.map.ag_like}");
						pager.addHiddenInputs("r3_id", "${af.map.r3_id}");
						pager.addHiddenInputs("sync_user", "${af.map.sync_user}");
						pager.addHiddenInputs("sync_date_start", "${af.map.sync_date_start}");
						pager.addHiddenInputs("sync_date_end", "${af.map.sync_date_end}");
						pager.addHiddenInputs("is_change", "${af.map.is_change}");
						pager.addHiddenInputs("sync_state", "${af.map.sync_state}");
						pager.addHiddenInputs("vbedl_like", "${af.map.vbedl_like}");
						pager.addHiddenInputs("vbedl_null", "${af.map.vbedl_null}");
						pager.addHiddenInputs("order_type", "${af.map.order_type}");
						pager.addHiddenInputs("user_shop_name_like", "${af.map.user_shop_name_like}");
						pager.addHiddenInputs("customer_type_index", "${af.map.customer_type_index}");
						document.write(pager.toString());
					</script></td>
	</tr>
</table>
</form>
</div>


<div class="rtabcont1" style="overflow-x: auto; display: none;"
	id="divExcel_all" title="订单">
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="rtable2">
	<tr class="tabtt1">
		<td width="5%" nowrap="nowrap" align="center">序号</td>
		<td nowrap="nowrap" align="center">分公司</td>
		<td nowrap="nowrap" align="center">经办</td>
		<td width="8%" nowrap="nowrap" align="center">下单日期</td>
		<td width="10%" nowrap="nowrap" align="center">流水号</td>
		<td width="8%" nowrap="nowrap" align="center">R3客户编码</td>
		<td nowrap="nowrap" align="center">客户名称</td>
		<td width="6%" nowrap="nowrap" align="center">数量</td>
		<td width="6%" nowrap="nowrap" align="center">金额￥</td>
		<td width="6%" nowrap="nowrap" align="center">折扣￥</td>
		<c:if test="${af.map.dept_type eq 1}">
			<!-- 系统管理员 -->
			<td width="6%" nowrap="nowrap" align="center">审核状态</td>
			<td width="6%" nowrap="nowrap" align="center">待审核角色</td>
		</c:if>
		<c:if test="${af.map.dept_type eq 2}">
			<!-- 非系统管理员 -->
			<td width="6%" nowrap="nowrap" align="center">审核状态</td>
		</c:if>
		<td width="6%" nowrap="nowrap" align="center">变更短信</td>
		<td width="6%" nowrap="nowrap" align="center">发货状态</td>
		<td width="6%" nowrap="nowrap" align="center">R3物流单号</td>
		<td width="6%" nowrap="nowrap" align="center">发货时间</td>
		<td width="6%" nowrap="nowrap" align="center">收货时间</td>
		<td width="6%" nowrap="nowrap" align="center">流程</td>
		<td width="6%" nowrap="nowrap" align="center">R3订单号</td>

	</tr>
	<c:forEach var="cur" items="${allList}" varStatus="vs">
		<tr>
			<td align="center" nowrap="nowrap">${(af.map.pager.currentPage -
			1) * af.map.pager.pageSize + vs.count}</td>
			<td align="right" nowrap="nowrap">${cur.map.fgsName}</td>
			<td align="right" nowrap="nowrap">${cur.map.jbName}</td>
			<td align="center" nowrap="nowrap"><fmt:formatDate
				value="${cur.order_date}" pattern="yyyy-MM-dd" /></td>
			<td align="center" nowrap="nowrap">${cur.trade_index}</td>
			<td align="center" nowrap="nowrap">${cur.ag}</td>
			<td nowrap="nowrap">${cur.user_shop_name}</td>
			<td align="right" nowrap="nowrap">${cur.order_num}</td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.money}" type="currency" /> </span></td>
			<td align="right" nowrap="nowrap"><span class="kz-price-12">
			<fmt:formatNumber value="${cur.good_discount_price}" type="currency" />
			</span></td>
			<c:if test="${af.map.dept_type eq 1}">
				<!-- 系统管理员 -->
				<td align="center" nowrap="nowrap"><c:choose>
					<c:when
						test="${(cur.audit_state eq 3) and (cur.kh_confirm_state ne -1) }">
						<span style="color: #00F;">已完结</span>
					</c:when>
					<c:when test="${(cur.audit_state eq 4)}">
						<span style="color: #00F;">已作废</span>
					</c:when>
					<c:otherwise>
						<span style="color: #F00;">审核中</span>
					</c:otherwise>
				</c:choose></td>

				<td align="center" nowrap="nowrap"><c:choose>
					<c:when test="${empty cur.map.next_audit_role_name}">无</c:when>
					<c:when test="${not empty cur.map.next_audit_role_name}">${cur.map.next_audit_role_name}</c:when>
				</c:choose></td>
			</c:if>
			<c:if test="${af.map.dept_type eq 2}">
				<!-- 非系统管理员 -->
				<td align="center" nowrap="nowrap"><c:choose>
					<c:when test="${cur.audit_state eq 3}">
						<span style="color: #00F;">已完结</span>
					</c:when>
					<c:when test="${cur.audit_state eq 4}">
						<span style="color: #00F;">已作废</span>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${cur.map.states eq 0}">
								<span style="color: #F00;">待审核</span>
							</c:when>
							<c:when test="${cur.map.states eq 1}">
								<span style="color: green;">审核中</span>
							</c:when>
						</c:choose>
					</c:otherwise>
				</c:choose></td>
			</c:if>
			<td align="center" nowrap="nowrap">${fn:split('未发送提醒,未变更,已发送提醒',
			',')[cur.kh_confirm_state + 1]}</td>
			<td align="center" nowrap="nowrap"><c:if
				test="${cur.is_delivered eq 0}">未发货</c:if> <c:if
				test="${cur.is_delivered eq 1}">已发货</c:if></td>
			<td align="right" nowrap="nowrap">${cur.map.vbedl}</td>
			<td align="right" nowrap="nowrap">${cur.map.lfdat}</td>
			<td align="right" nowrap="nowrap">${cur.map.shdat}</td>
			<td align="center" nowrap="nowrap">${empty cur.process_id ?
			'未确定' : '已确定'}</td>
			<td align="center" nowrap="nowrap">${empty cur.r3_id ? '未同步' :
			cur.r3_id}</td>
		</tr>
	</c:forEach>
</table>
</div>

</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[    
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
	$("#order_date_start").attr("dataType", "Require").attr("msg", "请选择下单开始日期！");
	$("#order_date_end").attr("dataType", "Require").attr("msg", "请选择下单结束日期！");
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();
	$("#vbedl_null").change(function(){
		var isChecked = $('#vbedl_null').val();
		if (isChecked=='1') {
			$("#vbedl_like").val("");
			$("#vbedl_null").val("1");
		}
	});
	
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form, 1);
		if (isSubmit) {
			var min_date = $("#order_date_start").val();
			if (min_date != '' && min_date < '2013-08-10') {
				alert("2013年8月9日14:10:51及之前的订单请按照说明3的方式查询。");
				return;
			}
			//var load_data = $.dialog({content: '正在提交数据...', max: false, min: false, icon: 'loading.gif', title: '提示！'});
			this.form.submit();	
		}
	});
	
	//默认当前分公司
	var fgs = $("#current_fgs_code").val();
	var fgs_dept = $("#current_dept_code").val();
	if(fgs!=""){
		$("#dept_id").val(fgs);
		$("#dept_id").change();
	}
	if(fgs_dept!=""){
		$("#l4_dept_id").val(fgs_dept);
		$("#l4_dept_id").change();
	}
	
	
	//导出excel
	$("#export_excel").click(function(){
		if(confirm("提示，您确认导出数据？")){
			//CNZZ统计代码
		//	$("#bottomPageForm").append("<input id='export_id'  name='excel_all' value='1' />");
			$("#export_id").val(1);
			$("#bottomPageForm").submit();
		}
		$("#export_id").val(0);
	});
	var excel_all = "${af.map.excel_all}";
	if("1" == excel_all){
		toExcel('divExcel_all', '${ctx}/manager/admin/KonkaOrderSearch.do?method=toExcel');
		
	}


	$("#syncBtnAllsys").click(function(){
			var isExecute = doSyncMethod("此操作用于同步所有分公司R3物流数据,确认操作?", 'KonkaOrderSearch.do','ZbSync','mod_id=${af.map.mod_id}' ,$('#bottomPageForm').serialize());
			if( isExecute == true){
				$("#syncBtnAllsys").attr("disabled","disabled");
				$("font").text("数据同步中...").css("color","red");
			}
	});
	
	$("#syncBtnAllfgs").click(function(){
			var isExecute = doSyncMethod("此操作用于同步分公司所有R3物流数据,确认操作?", 'KonkaOrderSearch.do','ZbSync','mod_id=${af.map.mod_id}' ,$('#bottomPageForm').serialize());
			if( isExecute == true){
				$("#syncBtnAllfgs").attr("disabled","disabled");
				$("font").text("数据同步中...").css("color","red");
			}
	});
	
	
	//收放说明文字
	$("#l_toggle").click(function(){
		$("#ol_hide").toggle();
		$("#fs_hide").toggleClass("filed_border");
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
});

	// 回车提交表单
	function keyEnter(){ if(event.keyCode == 13) $("#btn_submit").click(); } 
	document.onkeydown = keyEnter; 
	
	function doSyncMethod(msg, page, method, queryString) {
		if(msg != null) {
			if(!confirm(msg))
				return false;
		}
		page = page || "?";
		page = page.indexOf("?") != -1 ? page : (page + "?");
		location.href = page  + "method=" + method + "&" + encodeURI(queryString);
		return true;
	}
	
	
	function tongbu(id,sales_org){
		 $.ajax({
			 	type: "POST",
				url: "${ctx}/manager/admin/KonkaOrderSearch.do?method=stockCheckBeforeSync2Sap",
				data: {"sales_org" : sales_org, "order_id" : id},
				dataType: "json",
				sync: true, 
				error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
				success: function(result) {
					if(result===null) {alert("参数丢失,结果为空!");}
					//0,库存不足 ,提供库存不足的机型 
					if(result["isok"]==="0"){
						 if(window.confirm('库存预警:'+result["msg"]+'\n 是否需要同步?')){
						 	doNeedMethod(null, 'KonkaOrderSearch.do', 'odrerTb','id=' + id + '&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());
				 			$("#tongbu_"+id).removeAttr("onclick");
				 			$("#tongbu_"+id).css("color","gray");
			                return true;
			              }else{
			                 return false;
			             }
					}else{
							//库存充足不提示
							doNeedMethod(null, 'KonkaOrderSearch.do', 'odrerTb','id=' + id + '&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());
				 			$("#tongbu_"+id).removeAttr("onclick");
				 			$("#tongbu_"+id).css("color","gray");
			                return true;
					}
				}
			 });
	}

	//同步至drp系统 
	function tbDRP(id,index){
		doNeedMethod(null, 'KonkaOrderSearch.do', 'orderTbDRP','index=' + index +'&id='+id+ '&mod_id=${af.map.mod_id}');
	}
		
	function tbwl(id){
		doNeedMethod(null, 'KonkaOrderSearch.do', 'orderWl','id=' + id + '&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());
	} 
	
	$("#r3_id").blur(function(){
	    var text =this.value;
	    regx = /[^\n\d\r]/;
	    if(regx.test(text)){
	        alert('R3单号只允许输入数字');
	        this.value="";
	    }
	 });
	 
	function  show(id){
		var X = $('#message_'+id).offset().top-10;
		var Y = $('#message_'+id).offset().left-410;
		$("#msgshow_"+id).css({"top":X,"left":Y});
		$("#msgshow_"+id).show();
	}
	function  hide(id){
		$("#msgshow_"+id).hide();
	}
	
	function sendmsg(id,count){
			   if(count<=5){
				if(confirm("提示，您确认发送提醒短信？")){
		   			$.ajax({type: "post", 
						   url : "${ctx}/manager/admin/KonkaOrderSearch.do?method=sendmsg", 
						   dataType:'json',
						   sync: true, 
						   error: function(){alert("短信发送未成功！");},
						   data: {"order_id" :id},
						   success: function(json){
		      				var num=json.messagecount;
				  			$("#message_"+id).html("已发送提醒&nbsp;"+num+"次");
							var list=json.konkaorderinfomessagesendlist;
							$("#msgshow_"+id).empty();
							$("#msgshow_"+id).append("<ul  style='list-style-type:none;' id='msgshow_ul_"+id+"'>");
							
							for(var k=0;k<list.length;k++){
								var time =new Date(list[k].send_date).toLocaleDateString();
							$("#msgshow_ul_"+id).after("<li style='list-style-type:none;' > 发送人："+list[k].sender_name+"&emsp;接收人:"+list[k].receiver_name+"&emsp;时间："+time+"</li>");
							}
							
							$("#msgshow_"+id).append("</ul>") ;
						 	if (num> count){
								 alert("提醒短信已成功发送");
							 }else{
						   		 alert("提醒短信发送失败！请确保制单人手机号码不为空。");
						    }
				    	}
				    });
	  		 }// confirm end
		}}
		
	function sendmessageprocess(order_id){
		if(confirm("提示，您确认发送催办？")){
			 $.ajax({type: "post", 
			  url : "${ctx}/manager/admin/KonkaOrderMessageProcess.do?method=save", 
			   dataType:'json',
			   sync: true, 
			   error: function(){alert("订单催办未成功！");},
			   data: {"order_id" :order_id},
			   success: function(json){
				   if(json.addcount != 0){
					   alert("催办成功,已经催办"+json.count+"次");
				   }else{
					   alert("请查看下一个审核角色是否有审核人！");
				   }
			   }});
		}
	}
//]]>
	</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>