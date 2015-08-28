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
<body onkeydown="backspace();">
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
<div class="rtabcont2">
	<html-el:form action="/paragon/KonkaParagonSub" enctype="multipart/form-data">
	<html-el:hidden property="show_shop_id" styleId="show_shop_id" />
	<html-el:hidden property="mod_id" styleId="mod_id" />
	<html-el:hidden property="method" styleId="method" value="save" />
	<html-el:hidden property="queryString" styleId="queryString" />
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		class="rtable3">
		<tr>
			<td width="8%" nowrap="nowrap" class="title_item">分公司：</td>
			<td width="42%">
			<html-el:text property="dept_name" size="40" maxlength="30" styleId="dept_name" disabled="true" value="${af.map.map.part_name}"/>
			</td>
			<td width="8%" nowrap="nowrap" class="title_item">门店代码：</td>
			<td width="42%">
				<html-el:hidden property="show_shop_code" styleId="show_shop_code" />
				<html-el:text property="show_shop_code1" styleId="show_shop_code1" maxlength="4" size="4" disabled="true"/>
				<html-el:text property="show_shop_code2" styleId="show_shop_code2" maxlength="3" size="4" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item">经办：</td>
			<td>
			<html-el:text property="channel_name" size="40"
				maxlength="30" styleId="channel_name" disabled="true" value="${af.map.channel_name}"/>
			</td>
			<td nowrap="nowrap" class="title_item">门店名称：</td>
			<td><html-el:text property="show_shop_name" size="40"
				maxlength="30" styleId="show_shop_name" disabled="true"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item">客户姓名：</td>
			<td><html-el:text property="custom_name" size="40"
				maxlength="30" styleId="custom_name" disabled="true"/></td>
			<td nowrap="nowrap" class="title_item">区域：</td>
			<td>
			<html-el:select property="area_id" styleId="area_id" disabled="true">
				<html-el:option value="">请选择...</html-el:option>
				<html-el:option value="10">华东</html-el:option>
                <html-el:option value="20">山东</html-el:option>
                <html-el:option value="30">东北</html-el:option>
                <html-el:option value="40">华北</html-el:option>
				<html-el:option value="50">华南</html-el:option>
                <html-el:option value="60">西南</html-el:option>
                <html-el:option value="70">华中</html-el:option>
                <html-el:option value="80">西北</html-el:option>
			</html-el:select></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item">客户类别：</td>
			<td colspan="3"><html-el:select property="custom_type"
				styleId="custom_type" disabled="true">
				<html-el:option value="">请选择...</html-el:option>
				<html-el:option value="1">连锁</html-el:option>
				<html-el:option value="2">超市</html-el:option>
				<html-el:option value="3">县乡客户群</html-el:option>
				<html-el:option value="4">城市客户群</html-el:option>
				<html-el:option value="5">城市专卖店</html-el:option>
			</html-el:select></td>
		</tr>
		<tr>
			<td nowrap="nowrap" class="title_item">数据上报期：</td>
			<td>
				<html-el:text property="make_date" styleId="make_date" size="32" maxlength="20" readonly="true" onclick="WdatePicker({dateFmt:'yyyyMM'})" style="cursor:pointer;" value="${makeDate}" onchange="dateChange();"/>
			</td>
			<td colspan="2">
				将<html-el:text property="old_date" styleId="old_date" size="12" maxlength="20" readonly="true" onclick="WdatePicker({dateFmt:'yyyyMM'})" style="cursor:pointer;" value="${oldate}"/>的数据
				<input type="button" name="loadLastData" id="loadLastData" value="载入" class="but7" onclick="dataLoad();"/>
			</td>
		</tr>
		<tr>
			<td valign="top" nowrap="nowrap" class="title_item"><strong>促 销 员：</strong></td>
			<td colspan="3">
			<fieldset>
			<img onclick="addMan();" src="../../images/+.gif" style="vertical-align: middle; cursor: pointer;" title="再添加一个" />
			<a onclick="addMan();"  style="cursor:pointer;">新增</a>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3s1" id="audits">
				<tbody>
				<tr style="display: none;" id="a_model">
					<td>
					姓名：
					<input type="hidden" name="seller_id" id="seller_id" />
					<input type="text" name="seller_name" id="seller_name" />	
					电话：
					<input type="text" name="seller_phone" id="seller_phone" />
					<img onclick="removeMan(this);" src="../../images/x.gif" style="vertical-align: middle; cursor: pointer;" alt='删除' title="删除" />
					</td>
				</tr>
				<c:if test="${not empty sellerList}">
					<c:forEach var="cur" items="${sellerList}" varStatus="vs">
						<tr>
							<td>
							姓名：
							<input type="hidden" name="a_seller_id" id="a_seller_id" value="${cur.seller_id}" />
							<input type="text" name="a_seller_name" id="a_seller_name" value="${cur.seller_name}" />	
							电话：
							<input type="text" name="a_seller_phone" id="a_seller_phone" value="${cur.seller_phone}" />
							<img onclick="removeMan(this);" src="../../images/x.gif" style="vertical-align: middle; cursor: pointer;" alt='删除' title="删除" />
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
			<img onclick="addShowT();" src="../../images/+.gif" style="vertical-align: middle; cursor: pointer;" title="再添加一个" />
			<a onclick="addShowT();"  style="cursor:pointer;">新增</a>
			<div id="showTtd">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3s1" style="display: none;" id="t_model">
			<tbody>
				<tr>
					<td nowrap="nowrap" class="title_item">展位面积：</td>
					<td width="35%">
						<html-el:hidden property="showt_id" styleId="showt_id"/>
						<html-el:text property="showt_area" styleId="showt_area"/>㎡
					</td>
					<td nowrap="nowrap" class="title_item">展位类型：</td>
					<td width="35%">
						  <html-el:select property="showt_type" styleId="showt_type">
				                <html-el:option value="--">--</html-el:option>
				                <html-el:option value="L">L</html-el:option>
				                <html-el:option value="U">U</html-el:option>
				                <html-el:option value="通到">通道</html-el:option>
				                <html-el:option value="其他">其他</html-el:option>
			              </html-el:select>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item">展位延米：</td>
					<td><html-el:text property="showt_mile" styleId="showt_mile"/>m</td>
					<td nowrap="nowrap" class="title_item">制作费用：</td>
					<td><html-el:text property="showt_cash" styleId="showt_cash"/>元</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item">制作时间：</td>
					<td>
						<html-el:text property="showt_time" styleId="showt_time" size="32" maxlength="20" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor:pointer;"/>
					</td>
					<td nowrap="nowrap" class="title_item">形象版本：</td>
					<td>
						<html-el:select property="version_id" styleId="version_id" style="width:120px;">
		                  <html-el:optionsCollection name="versionList" label="version_name" value="version_id" />
		               </html-el:select>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<img onclick="removeShowT(this);" src="../../images/x.gif" style="vertical-align: middle; cursor: pointer;" alt='删除' title="删除" />
					</td>
				</tr>
			</tbody>
			</table>
			<c:if test="${not empty showtList}">
				<c:forEach var="cur" items="${showtList}" varStatus="vs">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3s1" id="t_model">
			<tbody>
				<tr>
					<td nowrap="nowrap" class="title_item">展位面积：</td>
					<td width="35%">
						<html-el:hidden property="a_showt_id" styleId="a_showt_id" value="${cur.showt_id}"/>
						<html-el:text property="a_showt_area" styleId="a_showt_area" value="${cur.showt_area}"/>㎡
					</td>
					<td nowrap="nowrap" class="title_item">展位类型：</td>
					<td width="35%">
						<html-el:select property="a_showt_type" styleId="a_showt_type" value="${cur.showt_type}">
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
					<td><html-el:text property="a_showt_mile" styleId="a_showt_mile" value="${cur.showt_mile}"/>m</td>
					<td nowrap="nowrap" class="title_item">制作费用：</td>
					<td><html-el:text property="a_showt_cash" styleId="a_showt_cash" value="${cur.showt_cash}"/>元</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item">制作时间：</td>
					<td>
						<fmt:formatDate value="${cur.showt_time}" pattern="yyyy-MM-dd" var="_showt_time" /> 
						<html-el:text property="a_showt_time" styleId="a_showt_time" value="${_showt_time}" size="32" maxlength="20" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor:pointer;"/>
					</td>
					<td nowrap="nowrap" class="title_item">形象版本：</td>
					<td>
						<html-el:select property="a_version_id" styleId="a_version_id" style="width:120px;" value="${cur.version_id}">
		                  <html-el:optionsCollection name="versionList" label="version_name" value="version_id" />
		               </html-el:select>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<img onclick="removeShowT(this);" src="../../images/x.gif" style="vertical-align: middle; cursor: pointer;" alt='删除' title="删除" />
					</td>
				</tr>
			</tbody>
			</table>
				</c:forEach>
			</c:if>
			</div>
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
				<tr>
				<td width="20%" align="right" nowrap="nowrap">
					<label for="set_1_${cur.equipment_id}">${cur.equipment_name}</label>
				</td>
				<td width="80%">
					<input type="hidden" id="set_1_${cur.equipment_id}" name="set_1" value="${cur.equipment_id}" />
					<input type="text" id="set_1_num_${cur.equipment_id}" name="set_1_num" value="${cur.map.set_num}" /> 台
					<fmt:formatDate value="${cur.map.startime}" pattern="yyyy-MM-dd" var="_startime" /> 
					启用时间:<input type="text" id="set_1_start_${cur.equipment_id}" name="set_1_start"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor:pointer;" value="${_startime}" />
					<fmt:formatDate value="${cur.map.endtime}" pattern="yyyy-MM-dd" var="_endtime" /> 
					废弃时间:<input type="text" id="set_1_end_${cur.equipment_id}" name="set_1_end"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor:pointer;" value="${_endtime}" />
				</td>
				</tr>
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
				<tr>
				<td width="20%" align="right" nowrap="nowrap">
					<label for="set_2_${cur.equipment_id}">${cur.equipment_name}</label>
					<input type="hidden" id="set_2_${cur.equipment_id}" name="set_2" value="${cur.equipment_id}" />
				</td>
				<td width="80%">
					<input type="text" id="set_2_num_${cur.equipment_id}" name="set_2_num" value="${cur.map.set_num}" /> 台 
					<fmt:formatDate value="${cur.map.startime}" pattern="yyyy-MM-dd" var="_startime" /> 
					启用时间:<input type="text" id="set_2_start_${cur.equipment_id}" name="set_2_start" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor:pointer;" value="${_startime}" />
					<fmt:formatDate value="${cur.map.endtime}" pattern="yyyy-MM-dd" var="_endtime" /> 
					废弃时间:<input type="text" id="set_2_end_${cur.equipment_id}" name="set_2_end" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor:pointer;" value="${_endtime}" />
				</td>
				</tr>
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
				<tr>
					<td nowrap="nowrap" class="title_item">销售额：(万元,小数点后2位有效)</td>
					<td width="35%"><html-el:text property="sales_val"
						styleId="sales_val" value="${af.map.sales.sales}" /></td>
					<td nowrap="nowrap" class="title_item">进场费：(万元,小数点后2位有效)</td>
					<td width="35%"><html-el:text property="etcash_val"
						styleId="etcash_val" value="${af.map.etcash.enter_ticket}" /></td>
				</tr>
			</tbody>
			</table>
			</fieldset>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td colspan="3"><input class="but4" type="button"
				name="btn_submit" id="btn_submit" value="提交" /> <input class="but5"
				type="button" name=btn_back value="返回"
				onclick="history.back();return false;" /></td>
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

//在申请工单时.屏蔽退格键返回上一次浏览页面功能
function backspace(){
 if(event.keyCode!=8){
  event.returnValue=true;
  return;
 }
 else{
 //如果当前焦点在form里,退格键有效,否则无效
  if(activeElement()){
  //alert("焦点是否在form里: true ");
   event.returnValue=true;
  }else{
  //alert("焦点是否在form里: false ");
   event.returnValue=false;
  }}}

function activeElement(){
 var forms = document.getElementById("af");
 if(forms!=null){
  if(children(forms))
   return true; 
 }
  return false;
}

//迭代判断焦点所在
function children(obj){
 if(obj==document.activeElement) 
  return false;
 //如果有子元素
 if(obj.hasChildNodes()){        
  for(var i=0;i<obj.childNodes.length;i++){
     if(obj.childNodes[i]==document.activeElement&&obj.childNodes[i].tagName.toLowerCase()!="td"){
     if(obj.childNodes[i].type.toLowerCase()=="text"||obj.childNodes[i].tagName.toLowerCase()=="textarea"){
        return true; 
     }}  
   if(children(obj.childNodes[i])) 
    return true;
  }}
 return false;
}

var $audits = $("#audits"), $m = $("#a_model");
function addMan() {
	var $new_m = $m.clone();
	var $e_id = $new_m.find("#seller_id"), $e_order = $new_m.find("#seller_phone"), $e_name = $new_m.find("#seller_name");
	$e_id.attr({"id":"a_" + $e_id.attr("id"),"name":"a_" + $e_id.attr("name")});
	$e_order.attr({"dataType":"Require","msg":"请填写","id":"a_" + $e_order.attr("id"),"name":"a_" + $e_order.attr("name")});
	$e_name.attr({"dataType":"Require","msg":"请填写","id":"a_" + $e_name.attr("id"),"name":"a_" + $e_name.attr("name")});
	$new_m.appendTo($audits).show();	
}

var $showTtd = $("#showTtd"), $t = $("#t_model");
function addShowT() {
	var $new_t = $t.clone();
	var $e_id = $new_t.find("#showt_id"), $e_area = $new_t.find("#showt_area"), $e_type = $new_t.find("#showt_type"), $e_mile = $new_t.find("#showt_mile"), $e_cash = $new_t.find("#showt_cash"), $e_time = $new_t.find("#showt_time"), $e_ver = $new_t.find("#version_id");
	$e_id.attr({"id":"a_" + $e_id.attr("id"),"name":"a_" + $e_id.attr("name")});
	$e_area.attr({"dataType":"Require","msg":"请填写","id":"a_" + $e_area.attr("id"),"name":"a_" + $e_area.attr("name")});
	$e_type.attr({"dataType":"Require","msg":"请填写","id":"a_" + $e_type.attr("id"),"name":"a_" + $e_type.attr("name")});
	$e_mile.attr({"dataType":"Require","msg":"请填写","id":"a_" + $e_mile.attr("id"),"name":"a_" + $e_mile.attr("name")});
	$e_cash.attr({"dataType":"Require","msg":"请填写","id":"a_" + $e_cash.attr("id"),"name":"a_" + $e_cash.attr("name")});
	$e_time.attr({"dataType":"Require","msg":"请填写","id":"a_" + $e_time.attr("id"),"name":"a_" + $e_time.attr("name")});
	$e_ver.attr({"dataType":"Require","msg":"请选择","id":"a_" + $e_ver.attr("id"),"name":"a_" + $e_ver.attr("name")});
	$new_t.appendTo($showTtd).show();	
}

function removeMan(obj){
	$("#del_ids").val($("#del_ids").val() + $(obj).parent().children("td>input:child(1)").val() + ",");
	$(obj).parent().remove();
}

function removeShowT(obj){
	$(obj).parent().parent().parent().remove();
}

function dateChange(){
	this.location.href = "KonkaParagonSub.do?method=add&mod_id=501000&scode=${scode}&oldate="+$("#old_date").val()+"&fixdate="+$("#make_date").val();
}

function dataLoad(){
	if($("#old_date").val() == null ||$("#old_date").val() == ""){
		alert("请填写要载入的期数");
		return false;
	}
	this.location.href = "KonkaParagonSub.do?method=load&mod_id=501000&scode=${scode}&oldate="+$("#old_date").val()+"&fixdate="+$("#make_date").val();
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
