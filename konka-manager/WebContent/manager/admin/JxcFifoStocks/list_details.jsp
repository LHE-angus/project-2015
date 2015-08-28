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
					<td width="60"><img src="${ctx}/images/manager/help.gif"
						style="vertical-align: middle;" /> <span id="span_help"
						style="cursor: pointer;">帮助</span></td>
				</tr>
			</table>
		</div>
		<div class="rtabcont1">
			<html-el:form action="/admin/JxcFifoStocks">
			   <html-el:hidden property="method" value="listDetails" />
				<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
				<table width="100%" border="0" cellspacing="5" cellpadding="0"
					class="rtable1">
					<tr>
						<td width="10%" class="fb" align="right">部门：</td>
						<td><html-el:select property="dept_id" styleId="dept_id"
								style="width:90px">
								<html-el:option value="">-请选择-</html-el:option>
							</html-el:select> <html-el:select property="l4_dept_id" styleId="l4_dept_id"
								style="width:110px">
								<html-el:option value="">-请选择-</html-el:option>
							</html-el:select> <html-el:select property="l5_dept_id" styleId="l5_dept_id"
								style="width:110px">
								<html-el:option value="">-请选择-</html-el:option>
							</html-el:select> <%-- 	          <html-el:select property="handle_name_like_1" styleId="handle_name_like_1" > --%>
							<%-- 	            <html-el:option value="">-请选择-</html-el:option> --%>
							<%-- 	          </html-el:select> --%></td>
						<td width="10%" class="fb" align="right">客户R3编码：</td>
						<td><html-el:text property="r3_code" size="15" maxlength="20"
								styleId="r3_code" /></td>
						<td width="10%" class="fb" align="right">客户名称：</td>
						<td><html-el:text property="customer_name_like" size="15"
								maxlength="20" styleId="customer_name_like" /></td>
					</tr>
					<tr>
						          <td width="10%" class="fb" align="right">出／入库：</td>
						          <td>
						          	<html-el:select property="stock_state" styleId="stock_state" style="width:80px;">
						            	<html-el:option value="">-请选择-</html-el:option>
						            	<html-el:option value="10">入仓</html-el:option>
						            	<html-el:option value="20">出仓</html-el:option>
										<html-el:option value="20">负卖</html-el:option>
						            </html-el:select> 
						          </td>
						<td width="10%" class="fb" align="right">产品型号：</td>
						<td><html-el:text property="goods_name_like" size="15"
								maxlength="20" styleId="goods_name_like" /></td>
						<td align="center" colspan="2"><html-el:submit
								styleClass="but1" value="搜索" /></td>

					</tr>
				</table>
			</html-el:form>
		</div>
		<div>
			<!--   <input type="button" value="导出" id="export_22" class="but_excel" style="margin-left: 10px;" /> -->

		</div>
		<div class="rtabcont1" style="overflow-x: auto;">
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="rtable2">
				<tr class="tabtt1">
					<td width="5%" align="center">序号</td>
					<td nowrap="nowrap" align="center" width="8%">分公司</td>
					<td nowrap="nowrap" align="center" width="8%">R3编码</td>
					<td nowrap="nowrap" align="center" width="8%">客户名称</td>
					<td nowrap="nowrap" align="center" width="8%">进货批次</td>
					<td nowrap="nowrap" align="center" width="8%">产品型号</td>
					<td nowrap="nowrap" align="center" width="6%">数量</td>
					<td nowrap="nowrap" align="center" width="6%">状态</td>
					<td nowrap="nowrap" align="center" width="8%">进货成本</td>
					<td nowrap="nowrap" align="center" width="6%">进货时间</td>
					<td nowrap="nowrap" align="center" width="6%">进货仓库</td>
					<td nowrap="nowrap" align="center" width="6%">进货类型</td>
<!-- 					<td nowrap="nowrap" align="center" width="6%">销售数量</td> -->
					<td nowrap="nowrap" align="center" width="8%">销售金额</td>
					<td nowrap="nowrap" align="center" width="8%">销售时间</td>
					<td nowrap="nowrap" align="center" width="6%">出货仓库</td>
					<td nowrap="nowrap" align="center" width="6%">出货类型</td>

				</tr>
				<c:forEach var="cur" items="${entityList}" varStatus="vs">
					<tr>
						<td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
						<td align="center" nowrap="nowrap">${cur.subcomp_name}</td>
						<td align="center" nowrap="nowrap">${cur.r3_code}</td>
						<td align="center" nowrap="nowrap">${cur.customer_name}</td>

						<td nowrap="nowrap" align="center">${cur.stock_in_batch}</td>
						<td nowrap="nowrap" align="center">${cur.goods_model}</td>
							<td nowrap="nowrap" align="center">${cur.stock_in_num}</td>
						<td nowrap="nowrap" align="center">
						    <c:if test="${cur.stock_state eq 10}">
								<font color="green">入仓</font>
							</c:if> <c:if test="${cur.stock_state eq 20}">
								<font color="blue">出仓</font>
							</c:if>
						</td>
					
						<td nowrap="nowrap" align="right"><font color="red">
								<fmt:formatNumber value=" ${cur.stock_in_price}"
									pattern="¥00.00" />
						</font></td>
						<td nowrap="nowrap" align="center"><fmt:formatDate
								value="${cur.stock_in_opr_date}" pattern="yyyy-MM-dd" /></td>

						<td nowrap="nowrap" align="center">${cur.map.stock_in_store_name}</td>
						<td>
						<c:choose>
						<c:when test="${cur.stock_in_type eq 10 }">地采</c:when>
						<c:when test="${cur.stock_in_type eq 20 }">集采</c:when>
						<c:when test="${cur.stock_in_type eq 30 }">初始化</c:when>
						<c:when test="${cur.stock_in_type eq 40 }">客户端采购</c:when>
						<c:when test="${cur.stock_in_type eq 50 }">盘盈</c:when>
						<c:when test="${cur.stock_in_type eq 60 }">零售通退货</c:when>
						<c:when test="${cur.stock_in_type eq 70 }">零售通销售无效化</c:when>
						<c:when test="${cur.stock_in_type eq 80 }">库存调拨（入）</c:when>
						<c:when test="${cur.stock_in_type eq 90 }">库存转仓（入）</c:when>
						<c:when test="${cur.stock_in_type eq 100 }">零售通导入（负数）</c:when>
						<c:when test="${cur.stock_in_type eq 110 }">销售退货</c:when>
						<c:when test="${cur.stock_in_type eq 120 }">分销退货</c:when>
						</c:choose>
						</td>
<%-- 						<td nowrap="nowrap" align="center">${cur.stock_out_num}</td> --%>
						<td nowrap="nowrap" align="center">${cur.stock_out_price}</td>
						<td nowrap="nowrap" align="center"><fmt:formatDate
								value="${cur.stock_out_opr_date}" pattern="yyyy-MM-dd" /></td>
						<td nowrap="nowrap" align="center">${cur.map.stock_out_store_name}</td>
						<td>
						<c:choose>
						<c:when test="${cur.stock_out_type eq 510 }">地采退货</c:when>
						<c:when test="${cur.stock_out_type eq 520 }">集采退货</c:when>
						<c:when test="${cur.stock_out_type eq 530 }">零售通销售</c:when>
						<c:when test="${cur.stock_out_type eq 540 }">分销</c:when>
						<c:when test="${cur.stock_out_type eq 550 }">专卖店销售</c:when>
						<c:when test="${cur.stock_out_type eq 560 }">盘亏</c:when>
						<c:when test="${cur.stock_out_type eq 570 }">库存调拨（出）</c:when>
						<c:when test="${cur.stock_out_type eq 580 }">库存转仓（出）</c:when>
						<c:when test="${cur.stock_out_type eq 590 }">产品初始化（负）</c:when>
						<c:when test="${cur.stock_out_type eq 600 }">零售通导入</c:when>
						<c:when test="${cur.stock_out_type eq 610 }">客户端销售</c:when>
						<c:when test="${cur.stock_out_type eq 620 }">采购退货</c:when>
						
						</c:choose>
						</td>
					</tr>
				</c:forEach>
				<c:forEach begin="${fn:length(entityList)}"
					end="${af.map.pager.pageSize - 1}">
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<form id="bottomPageForm" name="bottomPageForm" method="post"
				action="JxcFifoStocks.do">
				<table width="98%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="40" align="right"><script type="text/javascript"
								src="${ctx}/commons/scripts/pager.js">;</script> <script
								type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "listDetails");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("r3_code", "${fn:escapeXml(af.map.r3_code)}");	
			pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
			pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
			pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
			pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.customer_name_like)}");	
			pager.addHiddenInputs("goods_name_like", "${fn:escapeXml(af.map.goods_name_like)}");	
            pager.addHiddenInputs("stock_state", "${af.map.stock_state}");
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
	<script type="text/javascript"
		src="${ctx}/commons/scripts/rowEffect.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/jquery.cs.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/validator.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
	<script type="text/javascript">//<![CDATA[
$(document).ready(function(){                                         
	//客户类型初始化
// 	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
// 	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});
// 	$("#cur_min_num").attr("focus",setOnlyNum);
// 	$("#cur_max_num").attr("focus",setOnlyNum);
// 	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	
	
// 	if('${af.map.handle_name_like_1}'!=""||'${af.map.handle_name_like_1}'!=null){
// 		changeFgsSn();  
// 	} 
	
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();
	
	
});


// function changeFgsSn(){ 
// 	//取得分公司
// 	var vdeptsn = $("#dept_sn").val();
// 	if(vdeptsn !=""){
// 		$.ajax({
// 			type: "post",
// 			url: "${ctx}/manager/admin/JStocksSummary.do?method=getJBDataBydeptCode",
// 			data: {"dept_sn" : vdeptsn},
// 			dataType: "json",
// 			error: function(request, settings) {
// 				var html = "<option value=''>请选择</option>";
// 				$("#handle_name_like_1").empty();
// 				$("#handle_name_like_1").html(html);
// 				}, 
// 			success: function(result) {
// 				var html = "<option value=''>请选择</option>";
// 				for(var i = 0 ;i<result.length ;i++){
// 					if(result[i] == "${af.map.handle_name_like_1}"){
// 						html += "<option selected='selected' value='"+result[i]+"'>"+result[i]+"</option>";
// 						}else{
// 							html += "<option value='"+result[i]+"'>"+result[i]+"</option>";
// 							}
// 				}
// 				$("#handle_name_like_1").empty();
// 				$("#handle_name_like_1").html(html);
// 			}
// 		});
// 	}else{
// 		var html = "<option value=''>请选择</option>";
// 		$("#handle_name_like_1").empty();
// 		$("#handle_name_like_1").html(html);
// 	}
	
// }
//返回
$("#btn_back").click(function() {
	history.back(-1);
});

//导出
$("#export_22").click(function(){
	if(confirm("提示，您确认导出数据？")){
		$("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='1' />");
		$("#bottomPageForm").submit();	
	}
});                                     
function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+]?\d+(?:\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^$/))this.value=0;this.o_value=this.value};
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}
//]]></script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>