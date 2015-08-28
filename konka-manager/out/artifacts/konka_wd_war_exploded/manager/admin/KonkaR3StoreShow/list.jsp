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
.webinput {
	background: #f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}

ul.ckUl {
	list-style-type: none;
	display: inline;
}

ul.ckUl li {
	float: left;
	margin: auto 5px auto 0px; /*padding:2px 5px;*/
}

input,textarea,select {
	font-family: Microsoft Yahei;
	font-size: 12px;
}

.ck-li {
	position: relative;
}

.ck-li .hidden-accessible {
	position: absolute !important;
	clip: rect(1px, 1px, 1px, 1px);
}

.ck-li .ck-default {
	font-family: Microsoft yahei, verdana, Geneva, Tahoma, Georgia;
	font-size: 12px;
	display: inline-block;
	padding: 1px 8px;
	height: 16px;
	line-height: 16px;
	border: 1px solid #CCC;
	background: #F6F6F6;
	font-weight: bold;
	color: #C4C4C4;
	cursor: pointer;
}

.ck-li .ck-hover {
	font-family: Microsoft yahei, verdana, Geneva, Tahoma, Georgia;
	font-size: 12px;
	display: inline-block;
	padding: 1px 8px;
	height: 16px;
	line-height: 16px;
	border: 1px solid #FBCB09;
	background: #FDF5CE;
	font-weight: bold;
	color: #C77405;
	cursor: pointer;
}

.ck-li .ck-visited {
	font-family: Microsoft yahei, verdana, Geneva, Tahoma, Georgia;
	font-size: 12px;
	display: inline-block;
	padding: 1px 8px;
	height: 16px;
	line-height: 16px;
	border: 2px solid #EF0F28 /*#FF4800/*FBD850*/;
	background: white url("${ctx}/styles/customer/images/ck-visited.gif")
		right bottom no-repeat;
	font-weight: bold;
	color: #EF0F28 /*#FF4800/*#EB8F00*/;
	cursor: pointer;
}

.txt {
	float: left;
	padding-top: 8px;
}

.txt a {
	color: blue;
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
<body style="font-family: Microsoft Yahei;">
	<div class="oarcont" id="body_oarcont">
		<div class="oartop">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="3%"><img
						src="${ctx}/styles/admin-index/images/k_tup.jpg"
						style="vertical-align: middle;" /></td>
					<td nowrap="nowrap">当前位置：${naviString}</td>
				</tr>
			</table>
		</div>
		<div class="rtabcont1">
			<html-el:form action="/admin/KonkaR3StoreShow">
				<html-el:hidden property="method" value="list" />
				<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
				<table width="100%" border="0" cellspacing="5" cellpadding="0"
					class="rtable1">
					<tr>
						<td height="36" align="left" style="padding-left: 15px;"><strong class="fb">门店名称 ： </strong></td>
						<td><html-el:text property="store_name_like" styleId="store_name_like" styleClass="webinput" /></td>
						<td style="padding-left: 15px;"><strong class="fb">分公司：</strong></td>
						<td>
							<html-el:select property="dept_id" styleId="dept_id">
								<html-el:option value="">--请选择--</html-el:option>
								<c:forEach var="cur" items="${sybDeptInfoList}">
									<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
								</c:forEach>
							</html-el:select>
						</td>
						<td style="padding-left: 15px;"><strong class="fb">类&nbsp;&nbsp;&nbsp;&nbsp;型：</strong></td>
						<td>
							<html-el:text property="task_name_like" styleId="task_name_like" styleClass="webinput" />
						</td>
						<td style="padding-left: 15px;"><strong class="fb">品类名称：</strong></td>
						<td><html-el:text property="category_name_like" styleId="category_name_like" styleClass="webinput" /></td>
					</tr>
					<tr><td style="padding-left: 15px;"><strong class="fb">年&nbsp;&nbsp;&nbsp;&nbsp;份：</strong></td>
					<td><html-el:text property="year" styleId="year" styleClass="webinput" /></td>
					<td style="padding-left: 15px;"><strong class="fb">月&nbsp;&nbsp;&nbsp;&nbsp;份：</strong></td>
					<td><html-el:text property="month" styleId="month" styleClass="webinput" /></td>
					<td colspan="4"><input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family: Microsoft YAHEI;" /></td>
					</tr>
				</table>
			</html-el:form>
		</div>
		<div class="rtabcont1">
			<%@ include file="/commons/pages/messages.jsp"%>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><input type="button" class="but2" value="新 增" onclick="location.href='KonkaR3StoreShow.do?method=add&mod_id=${af.map.mod_id}';" /><logic-el:match name="popedom" value="+1+">
					&nbsp;<input name="button" type="button" class="btn_upload"
								class="websub" id="btn_import" value="导入" />
						</logic-el:match> <input class="but_excel" type="button" name="export_excel"
						id="export_excel" value="导出" />&nbsp;&nbsp; <a
						href="${ctx}/manager/admin/KonkaR3StoreShow/carndo.xls">excel模版下载</a>

					</td>
				</tr>
			</table>
		</div>
		<div class="rtabcont1">
			<div style="overflow-x: auto; height: 340px;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="rtable2">
					<tr class="tabtt1">
						<td width="5%" nowrap="nowrap" align="center">序号</td>
						<td width="10%" nowrap="nowrap" align="center">分公司</td>
						<td width="10%" nowrap="nowrap" align="center">客户R3</td>
						<td width="10%" nowrap="nowrap" align="center">客户名称</td>
						<td width="10%" nowrap="nowrap" align="center">门店名称</td>
						<td width="10%" nowrap="nowrap" align="center">门店ID</td>
						<td width="10%" nowrap="nowrap" align="center">客户类型</td>
						<td nowrap="nowrap" align="center">类型</td>
						<td nowrap="nowrap" align="center">品类名称</td>
					    <td width="10%" nowrap="nowrap" align="center">尺寸/规格</td>
						<td width="10%" nowrap="nowrap" align="center">数量</td>
						<td width="10%" nowrap="nowrap" align="center">金额</td>
						<td width="10%" nowrap="nowrap" align="center">发放年</td>
						<td width="10%" nowrap="nowrap" align="center">发放月</td>
						<td width="10%" nowrap="nowrap" align="center">添加人</td>
						<td width="10%" nowrap="nowrap" align="center">添加时间</td>
						<td width="10%" nowrap="nowrap" align="center">备注</td>
						<td width="6%" nowrap="nowrap" align="center">操作</td>
					</tr>
					<c:forEach items="${entityList}" var="cur" varStatus="vs">
						<tr>
							<td align="center" nowrap="nowrap">${vs.count}</td>
							<td align="left" nowrap="nowrap">${cur.dept_name}</td>
							<td align="left" nowrap="nowrap">${cur.map.r3_code}</td>
							<td align="left" nowrap="nowrap">${cur.map.customer_name}</td>
							<td align="left" nowrap="nowrap">${cur.store_name}</td>
							<td align="left" nowrap="nowrap">${cur.store_id}</td>
							<td align="left" nowrap="nowrap">${cur.map.par_customer_type_name}</td>
							<td align="left" nowrap="nowrap">${cur.task_name}</td>
							<td align="left" nowrap="nowrap">${cur.category_name}</td>
							<td align="right" nowrap="nowrap">${cur.size}</td>
							<td align="right" nowrap="nowrap">${cur.num}</td>
							<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.task_money}" pattern="#.00"/></td>
							<td align="left" nowrap="nowrap">${cur.year}</td>
							<td align="left" nowrap="nowrap">${cur.month}</td>
							<td align="right" nowrap="nowrap">${cur.map.user_name}</td>
							<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
							<td align="center" nowrap="nowrap">${cur.remark}</td>
							<td align="center" nowrap="nowrap">
								<!--  <a class="fblue" href="javascript:doNeedMethod(null, 'KonkaR3StoreShow.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());">查看</a>-->
								<span style="cursor: pointer;" class="fblue"
								onclick="doNeedMethod(null, 'KonkaR3StoreShow.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>
								<span style="cursor: pointer;" class="fblue"
								onclick="doNeedMethod('您确定删除吗？', 'KonkaR3StoreShow.do','delete' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<c:if test="${not vs.last}">
				<div style="height: 20px;"></div>
			</c:if>
			<form id="bottomPageForm" name="bottomPageForm" method="post"
				action="KonkaR3StoreShow.do">
				<input id='export_id' style="display: none" name='excel_all'
					value='0' />
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="40"><div
								style="text-align: right; padding-right: 5px;">
								<script type="text/javascript"
									src="${ctx}/commons/scripts/pager.js">;</script>
								<script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("store_name_like", "${af.map.store_name_like}");
								pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
								pager.addHiddenInputs("year", "${af.map.year}");
								pager.addHiddenInputs("month", "${af.map.month}");
								pager.addHiddenInputs("task_name_like", "${af.map.task_name_like}");
								pager.addHiddenInputs("category_name_like", "${af.map.category_name_like}");
								document.write(pager.toString());
			  </script>
							</div></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<div class="float_div" id="uploadPanel"
		style="z-index: 10000; position: absolute;">
		<div style="font-size: 14px;">
			<b>请选择Excel文件</b>
		</div>
		<br />
		<form action="KonkaR3StoreShow.do?method=excelImport" method="post"
			enctype="multipart/form-data">
			<html-el:hidden property="method" value="excelImport" />
			<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
			<input type="file" name="excel" /> <input type="button"
				name="btn_submit" class="but4" id="btn_submit" value="导入" />
		</form>
		<span style="float: right; bottom: 0px;" id="btn_close"><span
			style="color: red;">取消</span>&nbsp;</span>
	</div>
	<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/rowEffect.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
	<script type="text/javascript"
		src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/validator.js"></script>
	<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

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
	
	$("#export_excel").click(function(){
		if(confirm("提示，您确认导出数据？")){
			//CNZZ统计代码
			//_czc.push(["_trackEvent", "门店展台/演示设备下载", "导出", "Excel", 0, "export_excel"]);
		//	$("#bottomPageForm").append("<input id='export_id'  name='excel_all' value='1' />");
			$("#export_id").val(1);
			$("#bottomPageForm").submit();
		}
		$("#export_id").val(0);
	});
	
	
	/* var selectOption={"=请选择=":{"=请选择=":"=请选择=","defaultvalue" : "展台展柜","values":{"展台":"展台","展柜":"展柜"}},
			"演示设备":{"key":"演示设备","values":{"普通码流仪":"普通码流仪","4K码流仪":"4K码流仪",
				"网线":"网线","有线电视":"有线电视","分配器":"分配器","线材(HDMI)":"线材(HDMI)","其他":"其他"}}};
	$("#s1").doubleSelect('s2',selectOption);
	 */
});


function updateBatchAll(form) {
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u5361\u53f7\uff01");
	} else {
		if(confirm("\u4f60\u786e\u8ba4\u6279\u91cf\u66f4\u65b0\u8fd9\u4e9b\u7528\u6237\u5417\uff1f")) {
			form.method.value = "updateBatch";
			form.submit();
		}
	}
}

//]]>
</script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>
