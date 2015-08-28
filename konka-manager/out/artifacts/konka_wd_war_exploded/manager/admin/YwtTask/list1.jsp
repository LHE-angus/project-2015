<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<body style="font-family: Microsoft Yahei, '宋体';">
	<div class="oartop">
		<table width="400" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="3%"><img
					src="${ctx}/styles/admin-index/images/k_tup.jpg"
					style="vertical-align: middle;" />
				</td>
				<td>当前位置：客户管理 > 业务通 > 任务交办 ><span>新建任务</span>
				</td>
				<td width="60"><img src="${ctx}/images/manager/help.gif"
					style="vertical-align: middle;" /> <span id="span_help"
					style="cursor: pointer;">帮助</span>
				</td>
			</tr>
		</table>
	</div>
	<div style="padding: 5px">
		<html-el:form action="/admin/YwtTask">
			<html-el:hidden property="method" value="list" />
			<table width="100%" border="0" cellspacing="5" cellpadding="0"
				class="rtable1">
				<tr>
					<td align="left"><label for="dept_id"><strong
							class="fb">分 &nbsp;&nbsp;公&nbsp;&nbsp; 司：</strong>
					</label> <html-el:select styleId="_fgs_dept_id" property="_fgs_dept_id"
							style="width:150px;">
							<html-el:option value="">--请选择--</html-el:option>

						</html-el:select></td>
					<td align="left"><label for="_par_task_name_like"><strong
							class="fb">&nbsp;&nbsp;父任务名称： </strong>
					</label> <html-el:text styleId="_par_task_name_like"
							property="_par_task_name_like" style="width: 100px" /></td>
					<td align="left"><label for="_real_name_like"><strong
							class="fb">任务名称： </strong>
					</label> <html-el:text styleId="_task_name_like" property="_task_name_like"
							style="width: 100px" /></td>
					<td align="left"><label for="_real_name_like"><strong
							class="fb">&nbsp;&nbsp;创建人： </strong>
					</label> <html-el:text styleId="_real_name_like" property="_real_name_like"
							style="width: 100px" /></td>
					<td align="left"><label for="_is_lock"><strong
							class="fb">是否启用：</strong>
					</label> <html-el:select styleId="_is_del" property="_is_del"
							style="width:150px;">
							<html-el:option value="-1">--请选择--</html-el:option>
							<html-el:option value="0">启用</html-el:option>
							<html-el:option value="1">未启用</html-el:option>
						</html-el:select></td>
				</tr>
				<tr>
					<td align="left"><label for="_begin_begin_date"><strong
							class="fb"> 任务开始日期： </strong>
					</label> <html-el:text property="_begin_begin_date"
							styleId="_begin_begin_date" size="9" maxlength="10"
							readonly="true" styleClass="webinput" style="cursor:pointer;"
							onclick="new Calendar(2005, 2030, 0).show(this);" />
						<td align="left"><strong class="fb"> 任务完成期限： </strong> <html-el:text
								property="_end_finsh_date" styleId="_end_finsh_date" size="9"
								maxlength="10" readonly="true" styleClass="webinput"
								style="cursor:pointer;"
								onclick="new Calendar(2005, 2030, 0).show(this);" /></td>
						<td align="left"><strong class="fb">任务状态：</strong> <html-el:select
								styleId="_task_state" property="_task_state"
								style="width:150px;">
								<html-el:option value="">--请选择--</html-el:option>
								<html-el:option value="0">未开始</html-el:option>
								<html-el:option value="1">进行中</html-el:option>
								<html-el:option value="2">已完成</html-el:option>
							</html-el:select></td>
						<td align="left"><label for=""><strong class="fb">创建日期：
							</strong>
						</label> <html-el:select styleId="_year" property="_year" style="">
								<html-el:option value="-1">--请选择--</html-el:option>
								<html-el:option value="2014">2014</html-el:option>
								<html-el:option value="2015">2015</html-el:option>
								<html-el:option value="2016">2016</html-el:option>
								<html-el:option value="2017">2017</html-el:option>
								<html-el:option value="2018">2018</html-el:option>
								<html-el:option value="2018">2019</html-el:option>
								<html-el:option value="2018">2020</html-el:option>
							</html-el:select> <html-el:select styleId="_month" property="_month" style="">
								<html-el:option value="-1">--请选择--</html-el:option>
								<html-el:option value="01">1</html-el:option>
								<html-el:option value="02">2</html-el:option>
								<html-el:option value="03">3</html-el:option>
								<html-el:option value="04">4</html-el:option>
								<html-el:option value="05">5</html-el:option>
								<html-el:option value="06">6</html-el:option>
								<html-el:option value="07">7</html-el:option>
								<html-el:option value="08">8</html-el:option>
								<html-el:option value="09">9</html-el:option>
								<html-el:option value="10">10</html-el:option>
								<html-el:option value="11">11</html-el:option>
								<html-el:option value="12">12</html-el:option>
							</html-el:select></td>
						<td><label for="_priority"><strong class="fb">&nbsp;&nbsp;优先级：</strong>
						</label> <html-el:select styleId="_priority" property="_priority"
								style="width:150px;">
								<html-el:option value="-1">--请选择--</html-el:option>
								<html-el:option value="0">高</html-el:option>
								<html-el:option value="1">中</html-el:option>
								<html-el:option value="2">低</html-el:option>
							</html-el:select></td>
				</tr>
				<tr>
					<td align="left" colspan="10"><label for="_task_type"><strong
							class="fb">任务类型：</strong>
					</label> <html-el:select property="_task_type" styleId="_task_type"
							style="width:150px;">
							<html-el:option value="">--请选择--</html-el:option>
							<c:forEach var="rw" items="${taskTypeList}" varStatus="vs">
								<html-el:option value="${rw.type_id }">${rw.type_name }</html-el:option>
							</c:forEach>
						</html-el:select>
				</tr>
			</table>
			<div id="tb" style="height: auto; padding-right: 10px" align="right">
				<logic-el:match name="popedom" value="+1+">
					<input type="button" value="新增"
						onclick="window.location.href='${ctx}/manager/admin/YwtTask.do?method=add'"
						class="but_excel" style="float: left;" />
				</logic-el:match>
				<logic-el:match name="popedom" value="+0+">
					<input class="but_excel" type="submit" value="查 询" />
				</logic-el:match>
				<logic-el:match name="popedom" value="+128+">
					<input class="but_excel" type="button" id="output" name="output"
						value="导出" />
				</logic-el:match>
			</div>
		</html-el:form>

	</div>
	<form id="listForm" name="listForm">
		<div class="rtabcont1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="rtable2">
				<tr>
					<td width="80px;">分公司</td>
					<td width="100px;">创建人</td>
					<td width="80px;">创建日期</td>
					<td width="80px;">父任务名称</td>
					<td width="80px;">任务名称</td>
					<td width="120px;">当前子任务数</td>
					<td width="80px;">优先级</td>
					<td width="100px;">任务类型</td>
					<td width="80px;">任务内容</td>
					<td width="80px;">备注</td>
					<td width="80px;">任务状态</td>
					<td width="80px;">任务开始日期</td>
					<td width="80px;">任务完成期限</td>
					<td width="80px;">附件</td>
					<td width="80px;">是否启用</td>
					<td width="150px;">操作</td>
				</tr>
				<tbody>
					<c:forEach var="cur" items="${entityList}" varStatus="vs">
						<tr>
							<td width="80px;">${cur.map.fgs_Dept }</td>
							<td width="80px;">${cur.map.reveUser }</td>
							<td width="80px;">${cur.map.add_date }</td>
							<td width="80px;">${cur.par_task_name }</td>
							<td width="80px;"><a
								href="${ctx}/manager/admin/YwtTask.do?method=view&id=${cur.id}"
								style="color: red;" target="_blank" iconCls="icon-add"
								plain="true">${cur.task_name }</a>
							</td>
							<td width="80px;"><c:if test="${cur.map.subcount>0 }">
									<a
										href="${ctx}/manager/admin/YwtTask.do?method=list&_par_task_id=${cur.id}"
										style="color: red;" target="_blank" iconCls="icon-add"
										plain="true">${cur.map.subcount }</a>
								</c:if> <c:if test="${cur.map.subcount==0 }">
								${cur.map.subcount }
								</c:if></td>
							<c:if test="${cur.priority==0 }">
								<td width="80px;">高</td>
							</c:if>
							<c:if test="${cur.priority==1 }">
								<td width="80px;">中</td>
							</c:if>
							<c:if test="${cur.priority==2 }">
								<td width="80px;">低</td>
							</c:if>
							<td width="80px;">${cur.map.type_name }</td>
							<td width="80px;">${cur.content }</td>
							<td width="80px;">${cur.remark }</td>
							<c:if test="${cur.task_state==0 }">
								<td width="80px;"><lable style='color: red;'>
									<b>未开始</b></lable>
								</td>
							</c:if>
							<c:if test="${cur.task_state==1 }">
								<td width="80px;"><lable style='color: green;'>
									<b>进行中</b></lable>
								</td>
							</c:if>
							<c:if test="${cur.task_state==2 }">
								<td width="80px;">已完成</td>
							</c:if>
							<td width="80px;">${cur.map.begin_date }</td>
							<td width="80px;">${cur.map.finsh_date }</td>
							<td width="80px;"><c:forEach var="ur"
									items="${fn:split(cur.map.fj_paths, ',')}" varStatus="v">
									<a href="${ctx}/${ur}'" style="color: red;" target="_blank"
										iconCls="icon-add" plain="true"><c:if test="${!empty ur}"> 附件${v.count }</c:if>
									</a>
								</c:forEach></td>
							<c:if test="${cur.is_del==0 }">
								<td width="80px;">是</td>
							</c:if>
							<c:if test="${cur.is_del==1 }">
								<td width="80px;">否</td>
							</c:if>
							<td width="150px;"><a
								href="${ctx}/manager/admin/YwtTask.do?method=edit&id=${cur.id}">修改</a>
								<a
								href="${ctx}/manager/admin/YwtTask.do?method=view&id=${cur.id}&view=1">查看</a>
								<c:if test="${cur.is_del==1}">
									<a
										href="${ctx}/manager/admin/YwtTask.do?method=updateIsdel&id=${cur.id}">启用</a>
								</c:if> <c:if test="${cur.is_del==0}">
									<a
										href="${ctx}/manager/admin/YwtTask.do?method=delete&id=${cur.id}">停用</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</form>
	<form id="bottomPageForm" name="bottomPageForm" method="post"
		action="YwtTask.do">
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
				pager.addHiddenInputs("_fgs_dept_id", "${af.map._fgs_dept_id}");
				pager.addHiddenInputs("_real_name_like", "${af.map._real_name_like}");
				pager.addHiddenInputs("_assigned_user_name_like", "${af.map._assigned_user_name_like}");
				pager.addHiddenInputs("_begin_begin_date", "${af.map._begin_begin_date}");
				pager.addHiddenInputs("_end_finsh_date", "${af.map._end_finsh_date}");
				pager.addHiddenInputs("_year", "${af.map._year}");
				pager.addHiddenInputs("_month", "${af.map._month}");
				pager.addHiddenInputs("_task_name_like", "${af.map._task_name_like}");
				pager.addHiddenInputs("_par_task_name_like", "${af.map._par_task_name_like}");
				pager.addHiddenInputs("_task_state", "${af.map._task_state}");
				pager.addHiddenInputs("_priority", "${af.map._priority}");
				pager.addHiddenInputs("_task_type", "${af.map._task_type}");
				pager.addHiddenInputs("_is_del", "${af.map._is_del}");
				document.write(pager.toString());
			  </script>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<div class="clear"></div>
	</div>

	<script type="text/javascript"
		src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/rowEffect.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/validator.js"></script>
	<script type="text/javascript"
		src="${ctx}/commons/scripts/jquery.cs.js"></script>
	<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
	<script type="text/javascript">$(document).ready(function(){
	$.post('${ctx}/manager/admin/CsAjax.do?method=getAjaxKonkaDeptList',function(result){
		result.forEach(function(e){
			$("#_fgs_dept_id").append("<option value="+e.DEPT_ID+">"+e.DEPT_NAME+"</option>");
		});
			
		
	});
	$("#output").click(function(){
		if(confirm("提示，您确认导出数据？")){
			$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' />");
			$("#bottomPageForm").submit();	
		}
	});
});



</script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>