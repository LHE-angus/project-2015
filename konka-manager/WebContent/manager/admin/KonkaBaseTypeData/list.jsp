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
			<html-el:form action="/admin/KonkaBaseTypeData">
				<html-el:hidden property="method" value="list" />
				<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
				 <html-el:hidden property="isfirst"/>
				<table width="100%" border="0" cellspacing="5" cellpadding="0"
					class="rtable1">
					
					<tr>
					 <td>父类别名称 ：</td><td> <html-el:text property="_par_type_name_like"
								styleId="_par_type_name_like" styleClass="webinput" /></td>
					 <td>类别名称 ：</td><td><html-el:text property="_type_name_like"
								styleId="type_name_like" styleClass="webinput" /></td>
					 <td>类别取值 ：</td><td><html-el:text property="_field1"
								styleId="_field1" styleClass="webinput" /></td>
					 <td>是否删除：</td><td><html-el:select property="_is_del" styleId="is_del" style="width:120px;">
										       <html-el:option value="">请选择</html-el:option>
								      		   <html-el:option value="0">正常</html-el:option>
								      		   <html-el:option value="1">已删除</html-el:option>
							        	   </html-el:select> 
        	        </td>
					</tr>
					<tr>
					<td>  是否锁定：</td><td><html-el:select property="_is_lock" styleId="is_lock" style="width:120px;">
									       <html-el:option value="">请选择</html-el:option>
							      		   <html-el:option value="0">正常</html-el:option>
							      		   <html-el:option value="1">已锁定</html-el:option>
						        	   </html-el:select> 
        	                          </td>
					<td> 添加时间：</td><td colspan="3"><html-el:text property="_begin_date" styleId="begin_date"  size="20" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />到<html-el:text property="_end_date" styleId="end_date"  size="20" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
					<td><input name="button" type="submit" class="bgSearch" id="button"
							value="搜 索" style="font-family: Microsoft YAHEI;" /></td><td></td>
					</tr>
					
					
					<!--<tr>
						<td height="36" align="left" style="padding-left: 5px;">
						
						父类别名称 ： <html-el:text property="_par_type_name_like"
								styleId="_par_type_name_like" styleClass="webinput" /> &nbsp;&nbsp;
						类别名称 ： <html-el:text property="_type_name_like"
								styleId="type_name_like" styleClass="webinput" /> &nbsp;&nbsp;
								
								类别取值 ： <html-el:text property="_field1"
								styleId="_field1" styleClass="webinput" /> &nbsp;&nbsp;
								
							是否删除： <html-el:select property="_is_del" styleId="is_del" style="width:120px;">
			       <html-el:option value="">请选择</html-el:option>
	      		   <html-el:option value="0">正常</html-el:option>
	      		   <html-el:option value="1">已删除</html-el:option>
        	   </html-el:select> 
				 是否锁定：<html-el:select property="_is_lock" styleId="is_lock" style="width:120px;">
			       <html-el:option value="">请选择</html-el:option>
	      		   <html-el:option value="0">正常</html-el:option>
	      		   <html-el:option value="1">已锁定</html-el:option>
        	   </html-el:select> 
        	  <br/>
        	 添加时间：
       			<html-el:text property="_begin_date" styleId="begin_date"  size="20" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
       		到
       			<html-el:text property="_end_date" styleId="end_date"  size="20" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				
				<input name="button" type="submit" class="bgSearch" id="button"
							value="搜 索" style="font-family: Microsoft YAHEI;" />
						</td>
					</tr>
				-->
				
				
				</table>
			</html-el:form>
		</div>
		<div class="rtabcont1">
			<%@ include file="/commons/pages/messages.jsp"%>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><input type="button" class="but2" value="新 增" onclick="location.href='KonkaBaseTypeData.do?method=add&par_type_id=0&mod_id=${af.map.mod_id}';" /><logic-el:match name="popedom" value="+1+">
					&nbsp;
						</logic-el:match> <input class="but_excel" type="button" name="export_excel"
						id="export_excel" value="导出" />

					</td>
					<td align="right">
					<html-el:button property="" value="返 回" style="" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
				</tr>
			</table>
		</div>
		<div class="rtabcont1">
			<div style="overflow-x: auto; height: 340px;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="rtable2">
					<tr class="tabtt1">
						<td width="5%" nowrap="nowrap" align="center">序号</td>
						<td width="10%" nowrap="nowrap" align="left">类别名称</td>
						<td width="10%" nowrap="nowrap" align="left">类别取值</td>
						<td width="10%" nowrap="nowrap" align="left">父类别</td>
						<td width="10%" nowrap="nowrap" align="center">级别</td>
						<td width="10%" nowrap="nowrap" align="center">是否删除</td>
						<td width="10%" nowrap="nowrap" align="center">是否锁定</td>
						<td width="10%" nowrap="nowrap" align="left">添加人</td>
						<td width="10%" nowrap="nowrap" align="center">添加时间</td>
						<td width="10%" nowrap="nowrap" align="left">更新人</td>
						<td width="10%" nowrap="nowrap" align="left">备注</td>
						<td nowrap="nowrap" width="150px;" align="left">附件</td>
						<td width="6%" nowrap="nowrap" align="left">操作</td>
					</tr>
					<c:forEach items="${entityList}" var="cur" varStatus="vs">
						<tr>
							<td align="center" nowrap="nowrap">${vs.count}</td>
							<td align="left" nowrap="nowrap">
							${cur.type_name}
							</td>
							<td align="left" nowrap="nowrap">${cur.field1}</td>
							<td align="left" nowrap="nowrap">${cur.map.par_type_name}</td>
							<td align="right" nowrap="nowrap">${cur.field2}</td>
							<td align="left" nowrap="nowrap">
							<c:if test="${cur.is_del eq 0}">正常</c:if>
							<c:if test="${cur.is_del eq 1}">已删除</c:if>
							</td>
							<td align="left" nowrap="nowrap">
							<c:if test="${cur.is_lock eq 0}">正常</c:if>
							<c:if test="${cur.is_lock eq 1}">已锁定</c:if>
							</td>
							<td align="left" nowrap="nowrap">${cur.map.add_user}</td>
                            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
							<td align="right" nowrap="nowrap">${cur.map.update_user}</td>
							<td align="left" nowrap="nowrap">${cur.memo}</td>
							  <td>
					           <c:if test="${not empty cur.map.fj_paths}">
						          <c:set var="fj_paths" value="${fn:split(cur.map.fj_paths,',')}" />
						          <c:forEach items="${fj_paths}" var="fj_path" varStatus="vs1">
						          	      <a href="${ctx}/${fj_path}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
						          </c:forEach>
					          </c:if>
					          </td>
							<td align="center" nowrap="nowrap">
							<a class="fblue" href="javascript:doNeedMethod(null, 'KonkaBaseTypeData.do', 'add', 'par_type_id=${cur.type_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());">添加子节点</a>
								<a class="fblue" href="javascript:doNeedMethod(null, 'KonkaBaseTypeData.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());">查看</a>
								<span style="cursor: pointer;" class="fblue"
								onclick="doNeedMethod(null, 'KonkaBaseTypeData.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>
								<span style="cursor: pointer;" class="fblue"
								onclick="doNeedMethod('您确定删除吗？', 'KonkaBaseTypeData.do','delete' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
			<form id="bottomPageForm" name="bottomPageForm" method="post"
				action="KonkaBaseTypeData.do">
				<input id='export_id' style="display: none" name='excel_all'
					value='0' />
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="40">
						<div
								style="text-align: right; padding-right: 5px;">
								<script type="text/javascript"
									src="${ctx}/commons/scripts/pager.js">;</script>
								<script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("_par_type_id", "${af.map._par_type_id}");
								pager.addHiddenInputs("_type_name_like", "${af.map._type_name_like}");
								pager.addHiddenInputs("_is_del", "${af.map._is_del}");
								pager.addHiddenInputs("_is_lock", "${af.map._is_lock}");
								pager.addHiddenInputs("_begin_date", "${af.map._begin_date}");
								pager.addHiddenInputs("_end_date", "${af.map._end_date}");
								pager.addHiddenInputs("_field1", "${af.map._field1}");
								document.write(pager.toString());
			                </script>
						 </div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="clear"></div>
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
		<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#export_excel").click(function(){
				if(confirm("提示，您确认导出数据？")){
					$("#export_id").val(1);
					$("#bottomPageForm").submit();
				}
				$("#export_id").val(0);
			});
		});
</script>
	<jsp:include page="/__analytics.jsp" />
</body>
</html>
