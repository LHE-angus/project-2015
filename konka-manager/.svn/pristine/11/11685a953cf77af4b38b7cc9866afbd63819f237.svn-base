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
		<html-el:form action="/admin/KonkaR3ShopForReport">
		<html-el:hidden property="method" value="list" />
		<html-el:hidden property="mod_id" />
		<html-el:hidden property="dept_sn" />
		<html-el:hidden property="dept_type" />
		<html-el:hidden property="search_save_flag" styleId="search_save_flag" value="0" />
		<c:if test="${not empty af.map.dept_type and af.map.dept_type eq 3 and not empty af.map.dept_sn}">
			<input type="hidden" name="filter_params" value="BRANCH_AREA_NAME_2" />
			<input type="hidden" name="filter_operators" value="1" />
			<input type="hidden" name="filter_values" value="${af.map.dept_sn}" />
			<input type="hidden" name="filter_types" value="3" />
		</c:if>
		<div class="f18wblue">过滤器：</div>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
			<tr>
				<td width="30%">名称</td>
				<td width="30%">运算符</td>
				<td width="30%">值域</td>
				<td width="10%" align="center" style="cursor:pointer;" id="addFilterTD"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
			</tr>
			
			<tr>
				<td width="30%">
				<html-el:select property="filter_params" style="width:200px;">
						<html-el:option value="IS_DEL">是否停用</html-el:option>
					</html-el:select>
				</td>
				<td width="30%"><html-el:select property="filter_operators" value="1">

				    <html-el:option value="">请选择</html-el:option>
				    <html-el:option value="1" >等于</html-el:option>
				    <html-el:option value="2">不等于</html-el:option>
				    </html-el:select>
				    </td>
				<td width="30%"><html-el:select property="filter_operators" value="1">
				    <html-el:option value="">请选择</html-el:option>
				    <html-el:option value="1" >否</html-el:option>
				    <html-el:option value="2">是</html-el:option>
				    </html-el:select></td>
				<td width="10%" align="center" style="cursor:pointer;" id="delcurtr"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
			</tr>
			
			
			<tr>
				<td width="30%">
				<html-el:select property="filter_params" style="width:200px;">
						<html-el:option value="IS_SDF">r3分类</html-el:option>
					</html-el:select>
				</td>
				<td width="30%"><html-el:select property="filter_operators" value="1">

				    <html-el:option value="">请选择</html-el:option>
				    <html-el:option value="1" >等于</html-el:option>
				    <html-el:option value="2">不等于</html-el:option>
				    </html-el:select>
				    </td>
				<td width="30%"><html-el:select property="filter_operators" value="0">
				    <html-el:option value="">请选择</html-el:option>
				    <html-el:option value="0" >售达方</html-el:option>
				    <html-el:option value="1">送达方</html-el:option>
				    </html-el:select></td>
				<td width="10%" align="center" style="cursor:pointer;" id="delcurtr1"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
			</tr>
			
			<tr id="tr_filter" style="display:none;">
				<td>
					<html-el:select property="filter_params" style="width:200px;">
						<html-el:option value="">请选择</html-el:option>
						<c:forEach items="${entityList}" var="cur">
							<c:if test="${not empty af.map.dept_type and af.map.dept_type eq 3 and cur.column_name ne 'BRANCH_AREA_NAME_2'}">
								<html-el:option value="${cur.column_name}">${cur.china_column_name}</html-el:option>
							</c:if>
							<c:if test="${empty af.map.dept_type or af.map.dept_type ne 3}">
								<html-el:option value="${cur.column_name}">${cur.china_column_name}</html-el:option>
							</c:if>
						</c:forEach>
					</html-el:select>
				</td>
				<td>
					<html-el:select property="filter_operators">
						<html-el:option value="">请选择</html-el:option>
						<html-el:option value="1">等于</html-el:option>
						<html-el:option value="2">不等于</html-el:option>
						<html-el:option value="3">大于</html-el:option>
						<html-el:option value="4">大于等于</html-el:option>
						<html-el:option value="5">小于</html-el:option>
						<html-el:option value="6">小于等于</html-el:option>
						<html-el:option value="7">包含</html-el:option>
						<html-el:option value="8">不包含</html-el:option>
					</html-el:select>
				</td>
				<td><input type="text" name="filter_values" style="width:200px;" value="" />
					<input type="hidden" name="filter_types" value="" /></td>
				<td align="center" style="cursor:pointer;"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
			</tr>
			<tbody id="showAddTrsTbody"></tbody>
		</table>
		<div class="f18wblue">&nbsp;<input type="checkbox" name="out_all_flag" id="out_all_flag" value="1" onclick="clickOutAll(this);" />&nbsp;数据列全部输出</div>
		<br/>
		<div class="f18wblue">自定义输出列：</div>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
			<tr>
				<td width="1%"></td>
				<td width="20%">待选择区<br/>
					<html-el:select property="out_select" styleId="out_select" multiple="true" style="width:200px;height:200px;" onchange="moveSelected(this.form.out_select, this.form.out_params);">
						<c:forEach items="${entityList}" var="cur">
							<html-el:option value="${cur.column_name}">${cur.china_column_name}</html-el:option>
						</c:forEach>
					</html-el:select></td>
				<td width="79%" align="left">已选择区<br/><html-el:select property="out_params" styleId="out_params" multiple="true" style="width:200px;height:200px;" onchange="moveSelected(this.form.out_params, this.form.out_select);"></html-el:select></td>
			</tr>
		</table>
		
		<div>&nbsp;</div>
		<div style="display:none"><strong class="fb">查询标题：</strong><input name="filter_name" id="filter_name" size="30" maxlength="40" /></div>
		<div>&nbsp;</div>
		<div><html-el:button property="btn_search" styleClass="bgButton" styleId="btn_search" value="&nbsp;&nbsp;查询&nbsp;&nbsp;" />
			 &nbsp;
			 <!-- 
			 <html-el:button property="btn_submit" styleClass="bgButton" styleId="btn_submit" value="&nbsp;&nbsp;查询并保存条件&nbsp;&nbsp;" />
			  -->
		</div>
			 
		<div>&nbsp;</div>
		</html-el:form>  	
  	
  	</div>
  	
</div>
<div style="height:200px;">&nbsp;</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[    

$(document).ready(function(){

	if(document.getElementById("out_all_flag").checked){
		document.getElementById("out_select").disabled = true;
		document.getElementById("out_params").disabled = true;
		
		$("#out_params").removeAttr("dataType");
	}else {

		document.getElementById("out_select").disabled = false;
		document.getElementById("out_params").disabled = false;
		
		$("#out_params").removeAttr("dataType");
		$("#out_params").attr("dataType" , "Require").attr("msg" , "请选择输出！");
		
	}
	
	var form = document.forms[0];
	
	//$("#out_params" ).attr("dataType" , "Require").attr("msg" , "请选择输出！");
	
	$("#btn_search").click(function(){
		$("#filter_name").removeAttr("dataType");
		$("#search_save_flag").val("0");
		selectAll(form.out_params);
		if (Validator.Validate(form, 2)) {
			$("#btn_search").attr("disabled", "true");
			$("#btn_submit").attr("disabled", "true");
			form.submit();
		}else {
			unSelectAll(form.out_params);
		}
	});
	
	$("#btn_submit").click(function(){
		$("#filter_name").attr("dataType" , "Require").attr("msg" , "请填写查询标题！");
		$("#search_save_flag").val("1");
		selectAll(form.out_params);
		if (Validator.Validate(form, 2)) {
			$("#btn_search").attr("disabled", "true");
			$("#btn_submit").attr("disabled", "true");
			form.submit();
		}else {
			unSelectAll(form.out_params);
		}
	});
	
	$("#addFilterTD").click(function(){
		var tr_pd = $("#tr_filter").clone(true).attr("class","tr_pd");
		tr_pd.appendTo($("#showAddTrsTbody")).show();
		var lastTR = $("tr:last", "#showAddTrsTbody");

		$("td:last", lastTR).click(function (){
			$(this).parent().remove();
	    }).css("cursor", "pointer");
		   
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});

	
	$("#delcurtr").click(function (){
		$(this).parent().remove();
		window.parent.resizeFrameHeight('mainFrame', 3);
    }).css("cursor", "pointer");
	$("#delcurtr1").click(function (){
		$(this).parent().remove();
		window.parent.resizeFrameHeight('mainFrame', 3);
    }).css("cursor", "pointer");
	
	$("select[name='filter_params']").change(function(){
		var filter_params = $(this).parent().parent().children().eq(0).children().eq(0);
		var filter_operators = $(this).parent().parent().children().eq(1).children().eq(0);
		var filter_values = $(this).parent().parent().children().eq(2).children().eq(0);

		//$(this).parent().parent().children().eq(1).children().eq(0).children().remove();
		//$(this).parent().parent().children().eq(1).children().eq(0).append("<option value=''>测试</option>");
		
		<c:forEach items="${entityList}" var="cur">
			
			if($(this).val() == "${cur.column_name}"){
				
				if("${cur.select_type}" == "1"){
					var option_list = "<option value=''>请选择</option>";
					option_list += "<option value='1'>等于</option>";
					option_list += "<option value='2'>不等于</option>";
					option_list += "<option value='3'>大于</option>";
					option_list += "<option value='4'>大于等于</option>";
					option_list += "<option value='5'>小于</option>";
					option_list += "<option value='6'>小于等于</option>";
					filter_operators.children().remove();
					filter_operators.append(option_list);
					
					var data_value = "<input type='text' name='filter_values' style='width:200px;'  />";
					data_value += "<input type='hidden' name='filter_types' value='1' />";
					$(this).parent().parent().children().eq(2).children().remove();
					$(this).parent().parent().children().eq(2).append(data_value);
				}else if("${cur.select_type}" == "2"){
					var option_list = "<option value=''>请选择</option>";
					option_list += "<option value='3'>大于</option>";
					option_list += "<option value='5'>小于</option>";
					filter_operators.children().remove();
					filter_operators.append(option_list);
					
					var data_value = "<input type='text' name='filter_values' readonly='readonly' size='10' onclick='new Calendar(2011, 2021, 0).show(this);' style='cursor:pointer;text-align:center;' title='点击选择日期' />";
					data_value += "<input type='hidden' name='filter_types' value='2' />";
					$(this).parent().parent().children().eq(2).children().remove();
					$(this).parent().parent().children().eq(2).append(data_value);

				}else if("${cur.select_type}" == "3"){
					var option_list = "<option value=''>请选择</option>";
					option_list += "<option value='1'>等于</option>";
					option_list += "<option value='2'>不等于</option>";
					option_list += "<option value='7'>包含</option>";
					option_list += "<option value='8'>不包含</option>";
					filter_operators.children().remove();
					filter_operators.append(option_list);
					
					var data_value = "<input type='text' name='filter_values' style='width:200px;'  />";
					data_value += "<input type='hidden' name='filter_types' value='3' />";
					$(this).parent().parent().children().eq(2).children().remove();
					$(this).parent().parent().children().eq(2).append(data_value);
				}else if("${cur.select_type}" == "4"){
					var option_list = "<option value=''>请选择</option>";
					option_list += "<option value='1'>等于</option>";
					option_list += "<option value='2'>不等于</option>";
					filter_operators.children().remove();
					filter_operators.append(option_list);
					
					var data_value = "<select name='filter_values' style='width:200px;'>";
					data_value += "<option value=''>请选择</option>";
						<c:forEach items="${cur.enumEntityList}" var="cur_enum">
							data_value += "<option value='${cur_enum.index}'>${cur_enum.name}</option>";
						</c:forEach>
					data_value += "</select>";
					data_value += "<input type='hidden' name='filter_types' value='4' />";
					$(this).parent().parent().children().eq(2).children().remove();
					$(this).parent().parent().children().eq(2).append(data_value);
				}
				
			}
		</c:forEach>
	});
});

function clickOutAll(obj){
	if(obj.checked){

		document.getElementById("out_select").disabled = true;
		document.getElementById("out_params").disabled = true;
		
		$("#out_params").removeAttr("dataType");

	}else {

		document.getElementById("out_select").disabled = false;
		document.getElementById("out_params").disabled = false;
		
		$("#out_params").removeAttr("dataType");
		$("#out_params").attr("dataType" , "Require").attr("msg" , "请选择输出！");
		
	}
	
	//iframe高度自适应
	window.parent.resizeFrameHeight('mainFrame', 3);
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