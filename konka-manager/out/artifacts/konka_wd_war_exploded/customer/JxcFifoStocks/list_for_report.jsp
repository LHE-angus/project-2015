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
<link
	href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/colorbox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.tabtt1 td {
	background-color: #eff;
}
</style>
<style type="text/css">
select {
	font-family: Microsoft YAHEI;
	font-size: 12px;
}

input {
	font-family: Microsoft YAHEI;
	font-size: 12px;
}

label {
	cursor: pointer;
}
</style>
<style type="text/css">
a:link,a:visited {
	text-decoration: none;
	color: #416CE5;
	border-bottom: 1px solid #416CE5;
}

h2 {
	font-size: 13px;
	margin: 15px 0 0 0;
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
			<html-el:form action="/manager/JxcFifoStocks">
				<html-el:hidden property="method" value="listAccount" />
				<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
				<table width="100%" border="0" cellspacing="5" cellpadding="0"
					class="rtable1">

					<tr>
						<td nowrap="nowrap"><strong class="fb">客户名称：</strong>${konkaR3Shop.customer_name}
						</td>
						<td nowrap="nowrap"><strong class="fb">客户R3编码：</strong>${konkaR3Shop.r3_code}
						</td>
						<td colspan="4"></td>
					</tr>
					<tr>
						<td nowrap="nowrap"><strong class="fb">仓库：</strong> <html-el:select
								property="store_id" styleId="store_id">
								<html-el:option value="">请选择</html-el:option>
								<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
									<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
								</c:forEach>
							</html-el:select></td>
						<td nowrap="nowrap"><strong class="fb">型号：</strong> <html-el:select
								property="goods_id" styleId="goods_id">
								<html-el:option value="">请选择</html-el:option>
								<c:forEach var="cur" items="${jBaseGoodsList}" varStatus="vs">
									<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
								</c:forEach>
							</html-el:select> <html-el:hidden property="goods_name_like"
								styleId="goods_name_like" /></td>
						<td align="center" colspan="4">
							<html-el:checkbox property="group_by_store" value="true">&nbsp;区分仓库</html-el:checkbox>&nbsp;&nbsp;&nbsp;&nbsp;
							<html-el:submit styleClass="but1" value="搜索" /> <input class="but5"
							type="button" name="Submit5" value="返回"
							onclick="history.back();return false;" /></td>

					</tr>
				</table>
			</html-el:form>
		</div>
		<div>
			<!--   <input type="button" value="导出" id="export_22" class="but_excel" style="margin-left: 10px;" /> -->

		</div>
		<div class="rtabcont1" style="overflow-x: auto;">
			<div style="text-align: right;">单位：台、元</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				   class="rtable2">
				<tr class="tabtt1">
					<td width="5%" align="center" nowrap="nowrap" rowspan="2">序号</td>
					<c:if test="${group_by_store =='true'}" >
						<td align="center" nowrap="nowrap" rowspan="2">仓库名称</td>
					</c:if>
					<td align="center" nowrap="nowrap" rowspan="2">型号名称</td>
					<td align="center" nowrap="nowrap" colspan="3">期初</td>
					<td align="center" nowrap="nowrap" colspan="2">进</td>
					<td align="center" nowrap="nowrap" colspan="3">销</td>
					<td align="center" nowrap="nowrap" colspan="2">其他</td>
					<td width="10%" align="center" nowrap="nowrap" rowspan="2">剩余库存量</td>
					<td width="10%" align="center" nowrap="nowrap" rowspan="2">剩余库存金额</td>
				</tr>
				<tr class="tabtt1">
					<td width="10%" align="center" nowrap="nowrap">期初库存</td>
					<td width="10%" align="center" nowrap="nowrap">期初金额</td>
					<td width="10%" align="center" nowrap="nowrap">盘存时间</td>
					<td width="10%" align="center" nowrap="nowrap">进货数量</td>
					<td width="10%" align="center" nowrap="nowrap">进货金额</td>
					<td width="10%" align="center" nowrap="nowrap">销售数量</td>
					<td width="10%" align="center" nowrap="nowrap">销售成本</td>
					<td width="10%" align="center" nowrap="nowrap">销售金额</td>
					<td width="10%" align="center" nowrap="nowrap">其他数量</td>
					<td width="10%" align="center" nowrap="nowrap">其他金额</td>
				</tr>
				<c:set var="s_init_num" value="0"></c:set>
				<c:set var="s_init_money" value="0"></c:set>
				<c:set var="s_come_num" value="0"></c:set>
				<c:set var="s_come_money" value="0"></c:set>
				<c:set var="s_out_num" value="0"></c:set>
				<c:set var="s_sale_cost" value="0"></c:set>
				<c:set var="s_out_money" value="0"></c:set>
				<c:set var="s_other_num" value="0"></c:set>
				<c:set var="s_other_money" value="0"></c:set>
				<c:set var="s_result_money" value="0"></c:set>
				<c:set var="s_result_num" value="0"></c:set>


				<c:forEach var="cur" items="${entityList}" varStatus="vs">

					<c:set var="s_init_num" value="${s_init_num+ cur.map.stock_init_num}"></c:set>
					<c:set var="s_init_money"
						   value="${s_init_money+ cur.map.stock_init_price}"></c:set>
					<c:set var="s_come_num" value="${s_come_num+ cur.stock_in_num}"></c:set>
					<c:set var="s_come_money"
						   value="${s_come_money+ cur.stock_in_price}"></c:set>
					<c:set var="s_out_num" value="${s_out_num+ cur.map.stock_inasout_num+cur.stock_out_num}"></c:set>
					<c:set var="s_sale_cost"
						   value="${s_sale_cost+ cur.map.stock_inasout_cost+cur.map.stock_out_cost}"></c:set>
					<c:set var="s_out_money" value="${s_out_money+ cur.map.stock_inasout_price+cur.stock_out_price}"></c:set>

					<c:set var="s_other_num" value="${s_other_num+ cur.map.stock_other_in_num -cur.map.stock_other_out_num}"></c:set>
					<c:set var="s_other_money" value="${s_other_num+ cur.map.stock_other_in_price-cur.map.stock_other_out_price}"></c:set>

					<c:set var="s_result_money"
						   value="${s_result_money+cur.map.result_cost+cur.map.result_loss_cost}"></c:set>
					<c:set var="s_result_num"
						   value="${s_result_num+ cur.map.result_num+cur.map.result_loss_num}"></c:set>
					<tr>
						<td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
						<c:if test="${group_by_store =='true'}" >
							<td nowrap="nowrap" align="center">${cur.map.store_name}</td>
						</c:if>
						<td nowrap="nowrap" align="center">${cur.goods_model}</td>
						<td nowrap="nowrap" align="center">${cur.map.stock_init_num }</td>
						<td nowrap="nowrap" align="center"><span class="kz-price-12">
							<fmt:formatNumber value="${cur.map.stock_init_price }" type="currency" /></span></td>
						<td nowrap="nowrap" align="center">
							<fmt:formatDate value="${cur.stock_in_date}" pattern="yyyy-MM-dd" />

						</td>
						<td nowrap="nowrap" align="center">
							<c:if test="${group_by_store =='true'}" >
								<a class='iframea' href="${ctx}/customer/manager/JxcFifoStocks.do?method=viewDetails&mod_id=${af.map.mod_id}&store_id=${cur.stock_in_store}&goods_name_like=${cur.goods_model}&stock_state=10">
										${cur.stock_in_num}</a>
							</c:if>
							<c:if test="${group_by_store !='true'}" >
								<a class='iframea' href="${ctx}/customer/manager/JxcFifoStocks.do?method=viewDetails&mod_id=${af.map.mod_id}&goods_name_like=${cur.goods_model}&stock_state=10">
										${cur.stock_in_num}</a>
							</c:if>
						</td>
						<td nowrap="nowrap" align="right"><span class="kz-price-12"><fmt:formatNumber
								value="${cur.stock_in_price}" type="currency" /></span></td>
						<td nowrap="nowrap" align="center">
							<c:if test="${group_by_store =='true'}" >
								<a class='iframea' href="${ctx}/customer/manager/JxcFifoStocks.do?method=viewDetails&mod_id=${af.map.mod_id}&store_id=${cur.stock_out_store}&goods_name_like=${cur.goods_model}&stock_state=20">
										${cur.stock_out_num+cur.map.stock_inasout_num}</a>
							</c:if>
							<c:if test="${group_by_store !='true'}" >
								<a class='iframea' href="${ctx}/customer/manager/JxcFifoStocks.do?method=viewDetails&mod_id=${af.map.mod_id}&goods_name_like=${cur.goods_model}&stock_state=20">
										${cur.stock_out_num+cur.map.stock_inasout_num}</a>
							</c:if>
						</td>
						<td nowrap="nowrap" align="center"><span class="kz-price-12"><fmt:formatNumber
								value="${cur.map.stock_out_cost+cur.map.stock_inasout_cost}" type="currency" /></span></td>
						<td nowrap="nowrap" align="center"><span class="kz-price-12"><fmt:formatNumber
								value="${cur.stock_out_price+cur.map.stock_inasout_price}" type="currency" /></span></td>

						<td nowrap="nowrap" align="center">${ cur.map.stock_other_in_num - cur.map.stock_other_out_num}</td>
						<td nowrap="nowrap" align="center"><span class="kz-price-12"><fmt:formatNumber
								value="${ cur.map.stock_other_in_price-cur.map.stock_other_out_price}" type="currency" /></span></td>


						<td nowrap="nowrap" align="center">${cur.map.result_num+cur.map.result_loss_num}</td>

						<td nowrap="nowrap" align="center"><span class="kz-price-12"><fmt:formatNumber
								value="${cur.map.result_cost+cur.map.result_loss_cost}"
								type="currency" /></span></td>
					</tr>
				</c:forEach>
				<tr>
					<td align="center" nowrap="nowrap" style="font-weight: 800;">合计</td>
					<c:if test="${group_by_store =='true'}">
						<td align="left" nowrap="nowrap" style="font-weight: 800;"></td>
					</c:if>
					<td align="left" nowrap="nowrap" style="font-weight: 800;"></td>
					<td align="right" nowrap="nowrap" style="font-weight: 800;">${s_init_num}</td>
					<td align="right" nowrap="nowrap" style="font-weight: 800;"><span
							class="kz-price-12"><fmt:formatNumber
							value="${s_init_money}" type="currency" /></span></td>
					<td align="center" nowrap="nowrap" style="font-weight: 800;"></td>
					<td align="right" nowrap="nowrap" style="font-weight: 800;">${s_come_num}</td>
					<td align="right" nowrap="nowrap" style="font-weight: 800;"><span
							class="kz-price-12"><fmt:formatNumber
							value="${s_come_money}" type="currency" /></span></td>
					<td align="right" nowrap="nowrap" style="font-weight: 800;">${s_out_num}</td>
					<td align="right" nowrap="nowrap" style="font-weight: 800;"><span
							class="kz-price-12"><fmt:formatNumber
							value="${s_sale_cost}" type="currency" /></span></td>

					<td align="right" nowrap="nowrap" style="font-weight: 800;"><span
							class="kz-price-12"><fmt:formatNumber
							value="${s_out_money}" type="currency" /></span></td>
					<td align="right" nowrap="nowrap" style="font-weight: 800;"><span
							class="kz-price-12"><fmt:formatNumber
							value="${s_other_num}" type="currency" /></span></td>

					<td align="right" nowrap="nowrap" style="font-weight: 800;"><span
							class="kz-price-12"><fmt:formatNumber
							value="${s_other_money}" type="currency" /></span></td>
					<td align="right" nowrap="nowrap" style="font-weight: 800;"><span
							class="kz-price-12">${s_result_num}</span></td>
					<td align="right" nowrap="nowrap" style="font-weight: 800;"><span
							class="kz-price-12"><fmt:formatNumber
							value="${s_result_money}" type="currency" /></span></td>
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/colorbox/jquery.colorbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
	<script type="text/javascript">
		//<![CDATA[
		$(document).ready(function() {
			$("#goods_id").change(function() {
				//拿到选中的下拉的文本
				var textVal = $("#goods_id").find("option:selected").text();
				if ("请选择" != textVal) {
					$("#goods_name_like").val(textVal);
				} else {
					$("#goods_name_like").val("");
				}
			});
		});
		$(".iframea").colorbox({iframe:true, width:"60%", height:"480px"});

		// 		$("#btn_back").click(function() {
		// 			history.back(-1);
		// 		});
		//导出
		$("#export_22").click(function() {
			if (confirm("提示，您确认导出数据？")) {
				$("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='1' />");
				$("#bottomPageForm").submit();
			}
						});
		function setOnlyNum() {
			$(this).css("ime-mode", "disabled");
			$(this).attr("t_value", "");
			$(this).attr("o_value", "");
			$(this).bind("dragenter", function() {
				return false;
			});
			$(this).keypress(function() {
				if (!this.value.match(/^\d*?\d*?$/))
					this.value = this.t_value;
				else
					this.t_value = this.value;
				if (this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))
					this.o_value = this.value;
			}).keyup(function() {
				if (!this.value.match(/^\d*?\d*?$/))
					this.value = this.t_value;
				else
					this.t_value = this.value;
				if (this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))
					this.o_value = this.value;
			}).blur(function() {
				if (!this.value.match(/^(?:[\+]?\d+(?:\d+)?|\.\d*?)?$/))
					this.value = this.o_value;
				else {
					if (this.value.match(/^\.\d+$/))
						this.value = 0 + this.value;
					if (this.value.match(/^$/))
						this.value = 0;
					this.o_value = this.value
				}
				;
				if (this.value.length == 0)
					this.value = "0";
			});
			//this.text.selected;j
		}
		//]]>
	</script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>