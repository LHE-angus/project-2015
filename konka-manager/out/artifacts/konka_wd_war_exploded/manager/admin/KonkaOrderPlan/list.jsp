<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.rtable1 td {
	padding: 2px 5px;
}
.float_div {
	position: absolute;
	border: solid 1px #d1e3f5;
	top: 220px;
	text-align: center;
	background: #f5f4f4;
	left: 40%;
	width: 400px;
	padding-bottom: 20px;
	padding-top: 0px;
	display: none;
	z-index: 1000;
}

.float_div div {
	margin-top: 0px;
}

.close {
	float: right;
	bottom: 0px;
	color: red;
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
	action="/admin/KonkaOrderPlan">
	<html-el:hidden property="method" value="list" />
	<html-el:hidden property="id" value="id" />
	<table width="100%" border="0" cellspacing="5" cellpadding="0"
		class="rtable1">
		<tr>
			<td align="right"><strong class="fb">计划年月：</strong></td>
			<td><html-el:select property="plan_year" styleId="plan_year"
				styleClass="webinput">
				<html-el:option value="">--请选择--</html-el:option>
				<html-el:option value="2010">2010</html-el:option>
				<html-el:option value="2011">2011</html-el:option>
				<html-el:option value="2012">2012</html-el:option>
				<html-el:option value="2013">2013</html-el:option>
				<html-el:option value="2014">2014</html-el:option>
				<html-el:option value="2015">2015</html-el:option>
				<html-el:option value="2016">2016</html-el:option>
				<html-el:option value="2017">2017</html-el:option>
				<html-el:option value="2018">2018</html-el:option>
				<html-el:option value="2019">2019</html-el:option>
				<html-el:option value="2020">2020</html-el:option>
			</html-el:select>年 <html-el:select property="plan_month" styleId="plan_month"
				styleClass="webinput">
				<html-el:option value="">--请选择--</html-el:option>
				<html-el:option value="01">01</html-el:option>
				<html-el:option value="02">02</html-el:option>
				<html-el:option value="03">03</html-el:option>
				<html-el:option value="04">04</html-el:option>
				<html-el:option value="05">05</html-el:option>
				<html-el:option value="06">06</html-el:option>
				<html-el:option value="07">07</html-el:option>
				<html-el:option value="08">08</html-el:option>
				<html-el:option value="09">09</html-el:option>
				<html-el:option value="10">10</html-el:option>
				<html-el:option value="11">11</html-el:option>
				<html-el:option value="12">12</html-el:option>
			</html-el:select>月</td>
			<td align="right"><strong class="fb">分 公 司/部门：</strong></td>
			<td><html-el:select property="dept_id" styleId="dept_id">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select> <html-el:select property="l4_dept_id" styleId="l4_dept_id">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select></td>
			<td align="right"><strong class="fb">机型：</strong></td>
			<td><html-el:text property="pd_name_like" size="17"
				maxlength="20" styleId="pd_name_like" title="请输入机型" /></td>
		</tr>
		<tr>
			<td align="right"><strong class="fb">R3编码：</strong></td>
			<td><html-el:text property="r3_code_like" size="17"
				maxlength="20" styleId="r3_code_like" /></td>
			<td align="right"><strong class="fb">客户名称:</strong></td>
			<td><html-el:text property="customer_name_like" size="10"
				maxlength="10" styleId="customer_name_like" /></td>
			<td align="right"><strong class="fb">客户类型:</strong></td>
			<td><html-el:select property="v_customer_type1"
				styleId="v_customer_type1" style="width:80px;">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select> <html-el:select property="v_customer_type2"
				styleId="v_customer_type2" style="width:130px;">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select></td>
		</tr>
		<tr>
			<td align="right"><strong class="fb">制单人：</strong></td>
			<td><html-el:text property="add_user_name_like"
				styleId="add_user_name_like" size="15" maxlength="10" /></td>
			<td align="right"><strong class="fb">制单时间：</strong></td>
			<td><html-el:text property="add_date_s" styleId="add_date_s"
				size="10" maxlength="10" readonly="true"
				onclick="new Calendar(2010, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /> 到 <html-el:text
				property="add_date_e" styleId="add_date_e" size="10" maxlength="10"
				readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
			<td align="right"><strong class="fb">修改时间：</strong></td>
			<td><html-el:text property="modify_date_s"
				styleId="modify_date_s" size="10" maxlength="10" readonly="true"
				onclick="new Calendar(2010, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /> 到 <html-el:text
				property="modify_date_e" styleId="modify_date_e" size="10"
				maxlength="10" readonly="true"
				onclick="new Calendar(2010, 2021, 0).show(this);"
				style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
			<td valign="middle" nowrap="nowrap" class="title_item" align="right"><html-el:submit
				styleClass="but1" value=" " style="width:60px;height:30px" /></td>
		</tr>
	</table>
</html-el:form></div>
<%@ include file="/commons/pages/messages.jsp"%>
<html-el:form action="/admin/KonkaOrderPlan?method=delete">
	<input type="hidden" name="method" id="method" value="delete" />
	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<div class="rtabcont1"><input type="button" class="but2"
		value="新 增"
		onclick="location.href='KonkaOrderPlan.do?method=add&mod_id=${af.map.mod_id}';" />
					&nbsp;<input name="button" type="button" class="btn_upload"
			class="websub" id="btn_import" value="导入" />
	<input class="but3" type="button" name=button3 id="button3"
		style="width: 67px;" value="删除" onclick="confirmDeleteAll(this.form);" />
	<a href="${ctx}/manager/admin/KonkaOrderPlan/carndo.xls">excel模版下载</a>
	</div>
	<div style="overflow-x: auto; height: 400px;">
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		class="rtable2">
		<tr class="tabtt1">
			<td width="3%" align="center" nowrap="nowrap"><input
				name="chkAll" type="checkbox" id="chkAll" value="-1"
				onclick="checkAll(this);" /></td>
			<td width="5%" nowrap="nowrap" align="center">序号</td>
			<td width="5%" nowrap="nowrap" align="center">年度</td>
			<td width="5%" nowrap="nowrap" align="center">月份</td>
			<td width="5%" nowrap="nowrap" align="center">分公司</td>
			<td width="5%" nowrap="nowrap" align="center">部门</td>
			<td width="3%" nowrap="nowrap" align="center">R3编码</td>
			<td width="11%" nowrap="nowrap" align="center">客户名称</td>
			<td width="11%" nowrap="nowrap" align="center">客户类型</td>
			<td width="11%" nowrap="nowrap" align="center">机型</td>
			<td width="11%" nowrap="nowrap" align="center">上月末库存量</td>
			<td width="11%" nowrap="nowrap" align="center">预计进货量</td>
			<td width="11%" nowrap="nowrap" align="center">预计销售量</td>
			<td width="11%" nowrap="nowrap" align="center">制单人</td>
			<td width="3%" nowrap="nowrap" align="center">制单时间</td>
			<td width="3%" nowrap="nowrap" align="center">修改人</td>
			<td width="11%" nowrap="nowrap" align="center">修改时间</td>
			<td width="10%" nowrap="nowrap" align="center">备注</td>
			<td width="5%" nowrap="nowrap" align="center">操作</td>
		</tr>
		<c:forEach items="${entityList}" var="cur" varStatus="vs">
			<tr>
				<td align="center" nowrap="nowrap"><input name="pks"
					type="checkbox" id="pks" value="${cur.id}" /></td>
				<td align="center" nowrap="nowrap">${(af.map.pager.currentPage
				- 1) * af.map.pager.pageSize + vs.count}</td>
				<td align="center" nowrap="nowrap">${cur.plan_year}</td>
				<td align="center" nowrap="nowrap">${cur.plan_month }</td>
				<td align="center" nowrap="nowrap">${cur.map.dept_name}</td>
				<td align="center" nowrap="nowrap">${cur.map.l4_dept_name}</td>
				<td align="center" nowrap="nowrap">${cur.r3_code}</td>
				<td align="center" nowrap="nowrap">${cur.customer_name}</td>
				<td align="center" nowrap="nowrap">${cur.map.customer_type_name}</td>
				<td align="center" nowrap="nowrap">${cur.pd_name}</td>
				<td align="center" nowrap="nowrap">${cur.last_stock_num}</td>
				<td align="center" nowrap="nowrap">${cur.plan_stock_num}</td>
				<td align="center" nowrap="nowrap">${cur.plan_sale_num}</td>
				<td align="center" nowrap="nowrap">${cur.add_user_name}</td>
				<td align="center" nowrap="nowrap"><fmt:formatDate
					value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
				<td align="center" nowrap="nowrap">${cur.modify_user_name}</td>
				<td align="center" nowrap="nowrap">
				<fmt:formatDate
					value="${cur.modify_date}" pattern="yyyy-MM-dd"></fmt:formatDate>
				</td>
				<td align="center" nowrap="nowrap">${cur.memo}</td>
				<td align="center" nowrap="nowrap"><a class="fblue"
					href="javascript:doNeedMethod(null, 'KonkaOrderPlan.do', 'view', 'id=${cur.id}&'+$('#bottomPageForm').serialize());">查看</a>
				<span style="cursor: pointer;" class="fblue"
					onclick="doNeedMethod(null, 'KonkaOrderPlan.do','edit' ,'id=${cur.id}&mod_id=${af.map.mod_id}' + $('#bottomPageForm').serialize())">修改</span>
				<span style="cursor: pointer;" class="fblue"
					onclick="doNeedMethod('确定删除吗?', 'KonkaOrderPlan.do','delete','id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span>
				</td>
			</tr>
		</c:forEach>
	</table>
	<form id="bottomPageForm" name="bottomPageForm" method="post"
		action="KonkaOrderPlan.do">
	<table width="98%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="60" align="center"><script type="text/javascript"
				src="${ctx}/commons/scripts/pager.js">;</script> <script
				type="text/javascript"> 
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("date_begin", "${fn:escapeXml(af.map.date_begin)}");							
			pager.addHiddenInputs("date_end", "${fn:escapeXml(af.map.date_end)}");	
			pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");
            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");	
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
            pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
            pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");
            pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
            pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
            pager.addHiddenInputs("add_user_name_like", "${af.map.add_user_name_like}");
            pager.addHiddenInputs("add_date_s", "${af.map.add_date_s}");
            pager.addHiddenInputs("add_date_e", "${af.map.add_date_e}");
            pager.addHiddenInputs("modify_date_s", "${af.map.modify_date_s}");
            pager.addHiddenInputs("modify_date_e", "${af.map.modify_date_e}");      					
            document.write(pager.toString());
            </script></td>
		</tr>
	</table>
	</form>
	</div>
	
</html-el:form></div>
<div class="float_div" id="uploadPanel"
		style="z-index: 10000; position: absolute;">
	<div style="font-size: 14px;"><b>请选择Excel文件</b></div>
	<br />
	<form action="KonkaOrderPlan.do?method=excelImport" method="post"
		enctype="multipart/form-data"><html-el:hidden property="method"
		value="excelImport" /> <html-el:hidden property="mod_id"
		value="${af.map.mod_id}" /> <input type="file" name="excel" /> <input
		type="button" name="btn_submit" class="but4" id="btn_submit"
		value="导入" /></form>
	<span style="float: right; bottom: 0px;" id="btn_close" class="but9"><span
		style="color: red;">取消</span>&nbsp;</span></div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
	
	//分公司初始化
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});

	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();

	$("#btn_import").click(function(){$("#uploadPanel").fadeIn(500);	});
	$("#btn_close").click(function(){$("#uploadPanel").fadeOut(500);		
		$("#excel").after($("#excel").clone().val(""));   
		$("#excel").remove();		
	});
	 
	$("#btn_submit").click(function(){
		this.disabled=true;
		this.value="正在导入...";
		$("#btn_close").hide(); 
		this.form.submit();
	}); 
	
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
	</script>
</body>
</html>