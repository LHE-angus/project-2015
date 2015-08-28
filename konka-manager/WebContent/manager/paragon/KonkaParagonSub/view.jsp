<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
<div class="oarcont">
<div class="oartop">
<table width="500" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt=""
			style="vertical-align: middle;" /></td>
		<td>当前位置：${naviString}</td>
	</tr>
</table>
</div>
<div class="rtabcont2"><html-el:form
	action="/paragon/KonkaParagonSub" enctype="multipart/form-data">
	<html-el:hidden property="show_shop_id" styleId="show_shop_id" />
	<html-el:hidden property="mod_id" styleId="mod_id" />
	<html-el:hidden property="method" styleId="method" value="save" />
	<html-el:hidden property="del_ids" styleId="del_ids" />
	<html-el:hidden property="queryString" styleId="queryString" />
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
		<tr>
			<td width="8%" nowrap="nowrap" class="title_item">分公司：</td>
			<td width="42%">
				<c:out value="${af.map.map.part_name}" />
			</td>
			<td width="8%" nowrap="nowrap" class="title_item">门店代码：</td>
			<td width="42%">
				<c:out value="${af.map.show_shop_code}" />
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item">经办：</td>
			<td>
				<c:out value="${af.map.channel_name}" />
			</td>
			<td nowrap="nowrap" class="title_item">门店名称：</td>
			<td>
				<c:out value="${af.map.show_shop_name}" />
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item">客户姓名：</td>
			<td>
				<c:out value="${af.map.custom_name}" />
			</td>
			<td nowrap="nowrap" class="title_item">区域：</td>
			<td>
				<c:choose>
					<c:when test="${af.map.area_id eq 10}">华东</c:when>
					<c:when test="${af.map.area_id eq 20}">山东</c:when>
					<c:when test="${af.map.area_id eq 30}">东北</c:when>
					<c:when test="${af.map.area_id eq 40}">华北</c:when>
					<c:when test="${af.map.area_id eq 50}">华南</c:when>
					<c:when test="${af.map.area_id eq 60}">西南</c:when>
					<c:when test="${af.map.area_id eq 70}">华中</c:when>
					<c:when test="${af.map.area_id eq 80}">西北</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item">客户类别：</td>
			<td colspan="3">
				<c:choose>
					<c:when test="${af.map.custom_type eq 1}">连锁</c:when>
					<c:when test="${af.map.custom_type eq 2}">超市</c:when>
					<c:when test="${af.map.custom_type eq 3}">县乡客户群</c:when>
					<c:when test="${af.map.custom_type eq 4}">城市客户群</c:when>
					<c:when test="${af.map.custom_type eq 5}">城市专卖店</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item">数据上报期：</td>
			<td>
				<html-el:text property="make_date" styleId="make_date" size="32" maxlength="20" readonly="true" onclick="WdatePicker({dateFmt:'yyyyMM'})" style="cursor:pointer;" value="${makeDate}" onchange="dateChange();"/>
			</td>
			<td colspan="2">
			</td>
		</tr>
		<tr>
			<td valign="top" nowrap="nowrap" class="title_item"><strong>促 销 员：</strong></td>
			<td colspan="3">
			<fieldset>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3s1" id="audits">
				<tbody>
				<c:if test="${not empty sellerList}">
					<c:forEach var="cur" items="${sellerList}" varStatus="vs">
						<tr>
							<td width="20%" align="right" nowrap="nowrap">
							姓名：
							<c:out value="${cur.seller_name}" />
							</td>
							<td width="80%">	
							电话：
							<c:out value="${cur.seller_phone}" />
							</td>
						</tr>
					</c:forEach>
				</c:if>
				</tbody>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td valign="top" nowrap="nowrap" class="title_item"><strong>展台展柜：</strong></td>
			<td colspan="3">
			<c:if test="${not empty showtList}">
				<c:forEach var="cur" items="${showtList}" varStatus="vs">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3s1" id="t_model">
			<tbody>
				<tr>
					<td nowrap="nowrap" class="title_item">展位面积：</td>
					<td width="35%">
						<c:out value="${cur.showt_area}"/>㎡
					</td>
					<td nowrap="nowrap" class="title_item">展位类型：</td>
					<td width="35%">
						<html-el:select property="showt_type" styleId="showt_type" value="${cur.showt_type}" disabled="true">
						<html-el:option value="1">--</html-el:option>
						<html-el:option value="2">L</html-el:option>
						<html-el:option value="3">U</html-el:option>
						<html-el:option value="4">通道</html-el:option>
						<html-el:option value="5">其他</html-el:option>
						</html-el:select>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item">展位延米：</td>
					<td><c:out value="${cur.showt_mile}"/>m
					</td>
					<td nowrap="nowrap" class="title_item">制作费用：</td>
					<td>￥ <fmt:formatNumber value="${cur.showt_cash}" pattern="#,##0.00"/> 元
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item">制作时间：</td>
					<td>
						<fmt:formatDate value="${cur.showt_time}" pattern="yyyy-MM-dd" /> 
					</td>
					<td nowrap="nowrap" class="title_item">形象版本：</td>
					<td>
						<html-el:select property="version_id" styleId="version_id" style="width:120px;" value="${cur.version_id}" disabled="true">
		                  <html-el:optionsCollection name="versionList" label="version_name" value="version_id" />
		               </html-el:select>
					</td>
				</tr>
			</tbody>
			</table>
				</c:forEach>
			</c:if>
			</td>
		</tr>
		<tr>
			<td valign="top" nowrap="nowrap" class="title_item"><strong>演示设备：</strong></td>
			<td colspan="3">
			<fieldset>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3s1">
			<tbody>
			<c:forEach var="cur" items="${setList}" varStatus="vs">
				<!-- 
						<c:if test="${cur.map.set_num ne null}">
					    	<span style="margin:30px;"><input type="checkbox" id="set_${cur.equipment_id}" name="set_1" value="${cur.equipment_id}" checked="checked"/> <label for="set_1_${cur.equipment_id}">${cur.equipment_name}</label></span>
					    </c:if>
						<c:if test="${cur.map.set_num eq null}">
					    	<span style="margin:30px;"><input type="checkbox" id="set_${cur.equipment_id}" name="set_1" value="${cur.equipment_id}"/> <label for="set_1_${cur.equipment_id}">${cur.equipment_name}</label></span>
					    </c:if>
						 -->
				<c:if test="${cur.map.set_num ne null}">
				<tr>
				<td width="20%" align="right" nowrap="nowrap">
					<label for="set_1_${cur.equipment_id}">${cur.equipment_name}</label>
				</td>
				<td width="20%">
				<c:if test="${cur.equipment_id ne 1}">
					<c:out value="${cur.map.set_num}" /> 台
				</c:if>
				<c:if test="${cur.equipment_id eq 1}">
					<c:if test="${cur.map.set_num ne 0}">
						有
					</c:if>
					<c:if test="${cur.map.set_num eq 0}">
						无
					</c:if>
				</c:if>
				</td>
				<td width="10%">启用时间：</td>
				<td width="20%">${cur.map.startime}</td>
				<td width="10%">废弃时间：</td>
				<td width="20%">${cur.map.endtime}</td>
				</tr>
				</c:if>
			</c:forEach>
			</tbody>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td valign="top" nowrap="nowrap" class="title_item"><strong>样机：</strong></td>
			<td colspan="3">
			<fieldset>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3s1">
			<tbody>
			<c:forEach var="cur" items="${machineList}" varStatus="vs">
				<c:if test="${cur.map.set_num ne null}">
				<tr>
				<td width="20%" align="right" nowrap="nowrap">
					<label for="set_2_${cur.equipment_id}">${cur.equipment_name}</label>
				</td>
				<td width="20%">
					<c:out value="${cur.map.set_num}" /> 台 
				</td>
				<td width="10%">启用时间：</td>
				<td width="20%">${cur.map.startime}</td>
				<td width="10%">废弃时间：</td>
				<td width="20%">${cur.map.endtime}</td>
				</tr>
				</c:if>
			</c:forEach>
			</tbody>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td valign="top" nowrap="nowrap" class="title_item"><strong>销售额及入场费：</strong></td>
			<td colspan="3">
			<fieldset>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3s1">
			<tbody>
			<c:forEach var="cur" items="${cashList}" varStatus="vs">
				<tr>
					<td nowrap="nowrap" class="title_item">销售额：</td>
					<td width="25%">￥ <fmt:formatNumber value="${cur.SALES}" type="currency" pattern="#,##0.00" /> 万元</td>
					<td nowrap="nowrap" class="title_item">进场费：</td>
					<td width="25%">￥ <fmt:formatNumber value="${cur.ENTER_TICKET}" type="currency" pattern="#,##0.00" /> 万元</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td colspan="3">
			<input class="but5" type="button" name=btn_back value="返回" onclick="history.back();return false;" />
			</td>
		</tr>
	</table>
</html-el:form></div>
<div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript"><!--//<![CDATA[

var $audits = $("#audits"), $m = $("#a_model");
function addMan() {
	var $new_m = $m.clone();
	var $e_id = $new_m.find("#seller_id"), $e_order = $new_m.find("#seller_phone"), $e_name = $new_m.find("#seller_name");
	$e_id.attr({"id":"a_" + $e_id.attr("id"),"name":"a_" + $e_id.attr("name")});
	$e_order.attr({"dataType":"Require","msg":"请填写","id":"a_" + $e_order.attr("id"),"name":"a_" + $e_order.attr("name")});
	$e_name.attr({"dataType":"Require","msg":"请填写","id":"a_" + $e_name.attr("id"),"name":"a_" + $e_name.attr("name")});
	$new_m.appendTo($audits).show();	
}

function removeMan(obj){
	$("#del_ids").val($("#del_ids").val() + $(obj).parent().children("td>input:child(1)").val() + ",");
	$(obj).parent().remove();
}

function dateChange(){
	this.location.href = "KonkaParagonSub.do?method=view&mod_id=501000&scode=${scode}&fixdate="+$("#make_date").val();
}

var mark_val=0;

$(document).ready(function(){
	$("#show_shop_name").attr("dataType", "Require").attr("msg", "请填写");
	$("#area_id").attr("dataType", "Require").attr("msg", "请选择");
	$("#custom_type").attr("dataType", "Require").attr("msg", "请选择");
	$("#custom_name").attr("dataType", "Require").attr("msg", "请填写");
	$("#show_shop_code").attr("datatype","LimitB").attr("max","20").attr("min","1").attr("msg","代码不可为空");
	$("#show_shop_code1").val("${af.map.show_shop_code}".substring(0,4));
	$("#show_shop_code2").val("${af.map.show_shop_code}".substring(4,7));
	
		 $("#show_shop_code").blur(function(){
				var value = this.value;
				if (value != "") {
					var reg = /^[a-zA-Z0-9_!@#\$\*]{7,20}$/;
					if (!reg.test(value)) {
						$("#show_shop_code").val("");
						$("#show_shop_code").focus();
						alert("门店代码格式不正确：6-16位字符（字母和数字），不能包含“%”、“&amp;”、“？”、“=”和空格，请重新输入！");
						return false;
					}
				}
			});
		 $("#part_company_id").change( function() {
				var dept_id = $("#part_company_id").val();
				$("#channel_id").empty();
				mark_val++;
				if(mark_val>1){
				$.ajax({
					type: "POST",
					url: "KonkaParagonSub.do",
					data: "method=getval&comid=" + $("#part_company_id").val(),
					dataType: "html",
					error: function(request, settings) {alert("生成异常，分公司信息维护不完整！"); },
					success: function(code) {
					if(code != null && code != "") {
						$("#show_shop_code1").val(code.substring(0,4));
						$("#show_shop_code2").val(code.substring(4,7));
						return false;
					} else {
						alert("生成异常，分公司信息维护不完整！");
						$("#show_shop_code1").val("");
						$("#show_shop_code2").val("");
					}}});
				}

				if(""==dept_id){
			   		var opt1 = new Option( "请选择...",""); 
					$("#channel_id").get(0).options.add(opt1);
				   	}
			   	if(dept_id!=""){
				   	$.ajax({
						type: "POST",
						cache: false,
						url: "${ctx}/manager/admin/CsAjax.do",
						data: "method=getChannelId&part_company_id=" + $("#part_company_id").val(),
						dataType: "json",
						error: function(request, settings){},
						success: function(data) {
							if (data.length >= 1) {
								var opt1 = new Option( "请选择...",""); 
								$("#channel_id").get(0).options.add(opt1);
								
								for(var i = 0; i < data.length - 1; i++) {
									var opt = new Option( data[i].name,data[i].id); 
									$("#channel_id").get(0).options.add(opt);
								}
								
								<c:if test="${not empty af.map.channel_id }">$("#channel_id").val("${af.map.channel_id}");$("#channel_id").change();</c:if>
								<c:if test="${empty af.map.channel_id}"></c:if>
							}
						}
					});
			   	}
			});
		 $("#part_company_id").change();
	$("#btn_submit").click(function(){
		$("#show_shop_code").val($("#show_shop_code1").val()+$("#show_shop_code2").val());
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});

});
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
