<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
	<div class="oartop">
	    <table width="100%" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
	      </tr>
	    </table>
  	</div>
	<div class="rtabcont1">
		<html-el:form action="/admin/KonkaR3OrSellReport">
		<html-el:hidden property="method" value="view_mul" />
		<html-el:hidden property="mod_id" />
		<html-el:hidden property="search_save_flag" styleId="search_save_flag" value="0" />
		<div>查询条件：</div>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
			<tr>
	          <td width="1%"></td>
	          <td><strong class="fb">销售时间：</strong>
				  <html-el:text property="sell_date_start" styleId="sell_date_start"  size="10" maxlength="10" readonly="true" onclick="WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false });" style="cursor:pointer;text-align:center;" title="点击选择日期" />
		 		     至
				  <html-el:text property="sell_date_end" styleId="sell_date_end"  size="10" maxlength="10" readonly="true" onclick="WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false });" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				  &nbsp;<strong class="fb">分公司　：</strong>
		            <c:set var="disabled" value="false" />
		            <c:if test="${af.map.dept_type eq 3}">
		            	<c:set var="disabled" value="true" />
		            </c:if>
		          	<html-el:select property="dept_id" styleId="dept_id" disabled="${disabled}">
		          		<html-el:option value="">-请选择-</html-el:option>
		          	</html-el:select>
		          	&nbsp;
		          	<html-el:select property="l4_dept_id" styleId="l4_dept_id">
		          		<html-el:option value="">-请选择-</html-el:option>
		          	</html-el:select>
		          	&nbsp;
		          	<html-el:select property="l5_dept_id" styleId="l5_dept_id">
		          		<html-el:option value="">-请选择-</html-el:option>
		          	</html-el:select></td>
	        </tr>
	        <tr>
	            <td width="1%"></td>
	        	<td><strong class="fb">业务员　：</strong>
	        		<html-el:text property="ywy_user_name" size="15" maxlength="20" styleId="ywy_user_name"  />
	        		&nbsp;<strong class="fb">客&nbsp;&nbsp;户　：</strong>
	        		<html-el:text property="customer_name" size="15" maxlength="20" styleId="customer_name"  />
	        	</td>
	        </tr>
	        <tr>
	            <td width="1%"></td>
	        	<td><strong class="fb">规格尺寸：</strong>
	        		<html-el:select property="size_sec" styleId="size_sec">
	        			<html-el:option value="">全部</html-el:option>
	        			<c:forEach items="${sizeSecList}" var="cur" varStatus="vs">
						   <html-el:option value="${cur.field1}">${cur.type_name}</html-el:option>
						</c:forEach>
	        		</html-el:select>
	        		&nbsp;<strong class="fb">型&nbsp;&nbsp;号　：</strong>
	        		<html-el:text property="model_name" size="15" maxlength="20" styleId="model_name"  />
	        		&nbsp;<strong class="fb">是否大板：</strong>
	        		<html-el:select property="label_db" styleId="label_db">
	        		    <html-el:option value="">全部</html-el:option>
	        			<html-el:option value="1">大板</html-el:option>
	        			<html-el:option value="0">非大板</html-el:option>
	        		</html-el:select>
	        		&nbsp;<strong class="fb">是否智能：</strong>
	        		<html-el:select property="label_int" styleId="label_int">
	        			<html-el:option value="">全部</html-el:option>
	        			<html-el:option value="1">智能</html-el:option>
	        			<html-el:option value="0">非智能</html-el:option>
	        		</html-el:select></td>
	        </tr>	   		
		</table>
		<div>&nbsp;</div>
		<div>分组标示：</div>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
			<tr>
				<td width="1%"></td>
				<td width="20%">待选择区<br/>
					<html-el:select property="group_flag_select" styleId="group_flag_select" multiple="true" style="width:200px;height:200px;" onchange="moveSelected(this.form.group_flag_select, this.form.group_by_field);">
						<html-el:option value="" disabled="true" style="background-color:yellow;" styleId="aa">分组织架构</html-el:option>
						<html-el:option value="aa_1">&nbsp;&nbsp;分公司</html-el:option>
						<html-el:option value="aa_2">&nbsp;&nbsp;经营部</html-el:option>
						<html-el:option value="aa_3">&nbsp;&nbsp;办事处</html-el:option>
						<html-el:option value="aa_4">&nbsp;&nbsp;业务员</html-el:option>
						<html-el:option value="aa_5">&nbsp;&nbsp;客户</html-el:option>
						<html-el:option value="" disabled="true" style="background-color:yellow;" styleId="bb">时间维度</html-el:option>
						<html-el:option value="bb_1" styleId="sj_select_1">&nbsp;&nbsp;年度</html-el:option>
						<html-el:option value="bb_2" styleId="sj_select_2">&nbsp;&nbsp;季度</html-el:option>
						<html-el:option value="bb_3" styleId="sj_select_3">&nbsp;&nbsp;月度</html-el:option>
						<!--<html-el:option value="bb_4" styleId="sj_select_4">&nbsp;&nbsp;周</html-el:option>
						<html-el:option value="bb_5" styleId="sj_select_5">&nbsp;&nbsp;日</html-el:option>-->
						<html-el:option value="" disabled="true" style="background-color:yellow;" styleId="cc">产品维度</html-el:option>
						<html-el:option value="cc_1">&nbsp;&nbsp;尺寸规格</html-el:option>
						<html-el:option value="cc_2">&nbsp;&nbsp;型号</html-el:option>
						<html-el:option value="cc_3">&nbsp;&nbsp;是否大板</html-el:option>
						<html-el:option value="cc_4">&nbsp;&nbsp;是否智能</html-el:option>
						<html-el:option value="" disabled="true" style="background-color:yellow;" styleId="dd">客户分类维度</html-el:option>
						<html-el:option value="dd_1">&nbsp;&nbsp;客户分类大类</html-el:option>
						<html-el:option value="dd_2">&nbsp;&nbsp;客户分类明细</html-el:option>
					</html-el:select></td>
				
				<td width="79%" align="left">已选择区<br/><html-el:select property="group_by_field" styleId="group_by_field" multiple="true" style="width:200px;height:200px;" onchange="moveSelected(this.form.group_by_field, this.form.group_flag_select);"></html-el:select></td>
			</tr>
		</table>
		<div>&nbsp;</div>
		<div>显示字段：</div>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
			<tr>
				<td width="1%"></td>
				<td width="20%">待选择区<br/><html-el:select property="show_field_select" styleId="show_field_select" multiple="true" style="width:200px;height:200px;" onchange="moveSelected(this.form.show_field_select, this.form.show_field);">
						<html-el:option value="10001">&nbsp;&nbsp;R3销售量</html-el:option>
						<html-el:option value="10011">&nbsp;&nbsp;R3销售额</html-el:option>
						<html-el:option value="10021">&nbsp;&nbsp;零售量</html-el:option>
						<html-el:option value="10031">&nbsp;&nbsp;零售额</html-el:option>
						<html-el:option value="10041">&nbsp;&nbsp;回款额</html-el:option>
						<html-el:option value="10051">&nbsp;&nbsp;任务额</html-el:option>
						<html-el:option value="10061">&nbsp;&nbsp;任务系数</html-el:option>
						<html-el:option value="10071">&nbsp;&nbsp;回款任务完成度</html-el:option>
						<html-el:option value="10081">&nbsp;&nbsp;销售任务完成度</html-el:option>
						<!--<html-el:option value="10091">&nbsp;&nbsp;库存数量（月末）</html-el:option>
						<html-el:option value="10101">&nbsp;&nbsp;库存金额（月末）</html-el:option>-->
						<html-el:option value="10111">&nbsp;&nbsp;平均单价</html-el:option>
						<html-el:option value="10121">&nbsp;&nbsp;平均毛利</html-el:option>
						<html-el:option value="10131">&nbsp;&nbsp;产品零售指导价</html-el:option>
						<html-el:option value="10141">&nbsp;&nbsp;产品现款价</html-el:option>
					</html-el:select></td>
				<td width="79%">已选择区<br/><html-el:select property="show_field" styleId="show_field" multiple="true" style="width:200px;height:200px;" onchange="moveSelected(this.form.show_field, this.form.show_field_select);"></html-el:select></td>
			</tr>
			<tr>
				<td width="1%"></td>
				<td colspan="2">
					<html-el:checkbox property="contrast" value="1" styleId="contrast_1" />&nbsp;同比
					<html-el:checkbox property="contrast" value="2" styleId="contrast_2" />&nbsp;环比
				</td>
			</tr>
		</table>
		<div>&nbsp;</div>
		<div><strong class="fb">查询标题：</strong><input name="filter_name" id="filter_name" size="30" maxlength="40" /></div>
		<!-- <div><a href="${ctx}/manager/admin/KonkaR3OrSellReport.do?method=list_search&mod_id=${af.map.mod_id}&filter_type=2"><span style="cursor:pointer;" class="fblue">历史查询记录</span></a></div> -->
		<div>&nbsp;</div>
		<div><html-el:button property="btn_search" styleClass="bgButton" styleId="btn_search" value="&nbsp;&nbsp;查询&nbsp;&nbsp;" />
			 &nbsp;
			 <html-el:button property="btn_submit" styleClass="bgButton" styleId="btn_submit" value="&nbsp;&nbsp;查询并保存条件&nbsp;&nbsp;" /></div>
		<div>&nbsp;</div>
		</html-el:form>
	</div>
	
</div>


<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[    
$(document).ready(function(){
	
		$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
		$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
		$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});
		
		$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId&isNotEpp=isNotEpp", "par_id", "0", false);
		$("#dept_id").change();
		
		$("#group_by_field" ).attr("dataType" , "Require").attr("msg" , "请选择分组标示！");
		$("#show_field" ).attr("dataType" , "Require").attr("msg" , "请选择显示字段！");

		$("#btn_search").click(function(){
			$("#filter_name").removeAttr("dataType");
			$("#search_save_flag").val("0");
			submit_validate();
		});
		
		$("#btn_submit").click(function(){
			$("#filter_name").attr("dataType" , "Require").attr("msg" , "请填写查询标题！");
			$("#search_save_flag").val("1");
			submit_validate();
		});
});

function submit_validate(){
	var form = document.forms[0];
	selectAll(form.group_by_field);
	selectAll(form.show_field);
	
	//同比、环比与时间维度关联
	var flag = false;
	if($("select[id='group_by_field'] option[value^='bb']").length>=1){
		flag = true;
	}
	var flag_ck = false;
	if(document.getElementById("contrast_1").checked==true || document.getElementById("contrast_2").checked==true){
		flag_ck = true;
	}
	var sub_flag = false;
	if(flag_ck){
		if(flag){
			sub_flag = true;
		}else {
			alert("选择同比或环比时，必须选择时间维度！");
			sub_flag = false;
			unSelectAll(form.group_by_field);
			unSelectAll(form.show_field);
		}
	}else {
		sub_flag = true;
	}
	//
	
	//产品零售指导价、产品现款价与月份，产品维度的型号关联
	var pd_flag = false;
	var model_flag = false;
	if($("select[id='show_field'] option[value^='10131']").length>=1||$("select[id='show_field'] option[value^='10141']").length>=1){
		pd_flag = true;
	}
	if($("select[id='group_by_field'] option[value^='bb_3']").length>=1&&$("select[id='group_by_field'] option[value^='cc_2']").length>=1){
		model_flag = true;
	}
	var sub_flag_2 = false;
	if(pd_flag){
		if(model_flag){
			sub_flag_2 = true;
		}else {
			alert("选择产品零售指导价或产品现款价时，必须选择月份和产品维度的型号！");
			sub_flag_2 = false;
			unSelectAll(form.group_by_field);
			unSelectAll(form.show_field);
		}
	}else {
		sub_flag_2 = true;
	}
	//
	
	//任务系数,任务额,回款任务完成度,销售任务完成度只能到分公司，经营部
	var ratio_flag = false;
	var dept_flag = false;
	if($("select[id='show_field'] option[value^='10051']").length>=1
			||$("select[id='show_field'] option[value^='10061']").length>=1
			||$("select[id='show_field'] option[value^='10071']").length>=1
			||$("select[id='show_field'] option[value^='10081']").length>=1){
		ratio_flag = true;
	}
	if(($("select[id='group_by_field'] option[value^='aa_1']").length>=1||$("select[id='group_by_field'] option[value^='aa_2']").length>=1)
			&&$("select[id='group_by_field'] option[value^='aa_3']").length<1
			&&$("select[id='group_by_field'] option[value^='aa_4']").length<1
			&&$("select[id='group_by_field'] option[value^='aa_5']").length<1
			&&$("select[id='group_by_field'] option[value^='cc_']").length<1
			&&$("select[id='group_by_field'] option[value^='dd_']").length<1){
		dept_flag = true;
	}
	var sub_flag_3 = false;
	if(ratio_flag){
		if(dept_flag){
			sub_flag_3 = true;
		}else {
			alert("选择任务系数、任务额回款、任务完成度、销售任务完成度时，只能选择到分公司、经营部、时间维度，并且必须选择分公司和经营部中的一个！");
			sub_flag_3 = false;
			unSelectAll(form.group_by_field);
			unSelectAll(form.show_field);
		}
		if(flag){
			sub_flag_3 = true;
		}else {
			alert("选择任务系数、任务额回款、任务完成度、销售任务完成度时，必须选择时间维度！");
			sub_flag_3 = false;
			unSelectAll(form.group_by_field);
			unSelectAll(form.show_field);
		}
	}else {
		sub_flag_3 = true;
	}
	//
				
	var isSubmit = Validator.Validate(form, 2);
	if (isSubmit && sub_flag && sub_flag_2 && sub_flag_3) {
		$("#btn_search").attr("disabled", "true");
		$("#btn_submit").attr("disabled", "true");
		form.submit();
	}
}


function moveSelected(sourceSelect, targetSelect, isDelete){
	if(sourceSelect.id == "group_flag_select"){
		if($("select[id='group_flag_select'] option:selected[value^='bb']").length>1){
			alert("时间维度只能选择一个！");
			return false;
		}
	}
	var cachOptionsArray = new Array();
	var index = 0;
	for (var i = sourceSelect.options.length - 1; i >= 0; i--){
		if (sourceSelect.options[i].selected){
			cachOptionsArray[index] = new Option(sourceSelect.options[i].text, sourceSelect.options[i].value);
			if(isDelete==undefined || isDelete==true){
				sourceSelect.options[i] = null;
			}
			index++;
		}
	}
	var exist = false;
	for (var i = cachOptionsArray.length - 1; i >= 0; i--){
		exist = false;
		for (var j = 0; j < targetSelect.options.length; j++){
			if (targetSelect.options[j].value.toString() == cachOptionsArray[i].value.toString()){
				exist = true; 
				break;
			}
		}
		if (!exist){
			if (targetSelect.id == "group_flag_select"){
				var cachOptionsArray_1 = targetSelect;
				if(cachOptionsArray[i].value.toString().substring(0,2) == "aa"){
					$("#aa").after(cachOptionsArray[i]);
				}
				if(cachOptionsArray[i].value.toString().substring(0,2) == "bb"){
					$("#bb").after(cachOptionsArray[i]);
				}
				if(cachOptionsArray[i].value.toString().substring(0,2) == "cc"){
					$("#cc").after(cachOptionsArray[i]);
				}
				if(cachOptionsArray[i].value.toString().substring(0,2) == "cc"){
					$("#cc").after(cachOptionsArray[i]);
				}
				if(cachOptionsArray[i].value.toString().substring(0,2) == "dd"){
					$("#dd").after(cachOptionsArray[i]);
				}

				var flag = false;
				for (var j = 0; j < sourceSelect.options.length; j++){
					if (sourceSelect.options[j].value.toString().substring(0,2) == "bb"){
						flag = true;
						break;
					}
				}

				$("select[id='group_flag_select'] option[value^='bb']").attr("disabled", flag);
			} else {
				targetSelect.options[targetSelect.options.length] = cachOptionsArray[i];

				var flag = false;
				for (var j = 0; j < targetSelect.options.length; j++){
					if (targetSelect.options[j].value.toString().substring(0,2) == "bb"){
						flag = true;
						break;
					}
				}
				$("select[id='group_flag_select'] option[value^='bb']").attr("disabled", flag);
			}
			
		}
	}
	
}

function unSelectAll(selectElement){
	for (var i = 0; i < selectElement.length; i++){
		selectElement.options[i].selected = false;
	}
}

                                                                       
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
