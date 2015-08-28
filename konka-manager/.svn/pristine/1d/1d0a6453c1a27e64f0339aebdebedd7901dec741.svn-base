<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>任务接收</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body style="font-family:Microsoft Yahei,'宋体';">
<div class="oartop">
<table width="400" border="0" cellpadding="0" cellspacing="0">
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
<div style="padding: 5px">
<html-el:form action="/admin/YwtTaskReceive">
 <html-el:hidden property="method" value="list" />
 <html-el:hidden property="mod_id" styleId="mod_id" value="${allmap.mod_id }" />
 <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
	<tr>
	<td align="left"><label for="dept_id"><strong class="fb">分 &nbsp;&nbsp;公&nbsp;&nbsp; 司：</strong></label>
	    <html-el:select styleId="_fgs_dept_id" property="_fgs_dept_id" style="width:150px;">
      		   <html-el:option value="">--请选择--</html-el:option>
      		   
    	</html-el:select>		   		
	</td>
	<td align="left"><label for="_state"><strong class="fb">接收人：</strong></label>
		 <html-el:text styleId="_reve_real_name_like" property="_reve_real_name_like"
			style="width: 100px" maxlength="30"/> 
	</td>
	<td align="left"><label for="_par_task_name_like"><strong class="fb">&nbsp;&nbsp;父任务名称： </strong></label>
		<html-el:text styleId="_par_task_name_like" property="_par_task_name_like" style="width: 100px" />
	</td>
	<td align="left"><label for="_real_name_like"><strong class="fb">任务名称： </strong></label>
		<html-el:text styleId="_task_name_like" property="_task_name_like" style="width: 100px" />
	</td>
	</tr>
    <tr>
    <td align="left"><label for="_state"><strong class="fb">完成状态：</strong></label>
		<html-el:select styleId="_state" property="_state" style="width:150px;" >
      		   <html-el:option  value="-1">--请选择--</html-el:option >
      		   <html-el:option  value="0">完成</html-el:option >
      		   <html-el:option  value="1">未完成</html-el:option >
    	</html-el:select>
	</td>
    <td align="left"><label for="_begin_add_date"><strong class="fb">&nbsp;&nbsp;接收日期： </strong></label>
		 <html-el:text property="_begin_add_date" styleId="_begin_add_date"  size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;"
				onclick="new Calendar(2005, 2030, 0).show(this);" />
		 -
		 <html-el:text property="_end_add_date" styleId="_end_add_date"  size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;"
				onclick="new Calendar(2005, 2030, 0).show(this);" />
	</td>
	<td align="left"><label for="_begin_reve_finsh_date"><strong class="fb"> 完成日期： </strong></label>
  	 <html-el:text property="_begin_reve_finsh_date" styleId="_begin_reve_finsh_date"  size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;"
				onclick="new Calendar(2005, 2030, 0).show(this);" />
	 -
 	 <html-el:text property="_end_reve_finsh_date" styleId="_end_reve_finsh_date"  size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;"
				onclick="new Calendar(2005, 2030, 0).show(this);" />


	</td>
	<td align="left"><label for="_begin_audit_date"><strong class="fb"> 审核日期： </strong></label>
  	 <html-el:text property="_begin_audit_date" styleId="_begin_audit_date"  size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;"
				onclick="new Calendar(2005, 2030, 0).show(this);" />
	 -
 	 <html-el:text property="_end_audit_date" styleId="_end_audit_date"  size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;"
				onclick="new Calendar(2005, 2030, 0).show(this);" />
	</td>
	</tr>
 </table>
			<div id="tb" style="height: auto; padding-right: 10px" align="right">
				<logic-el:match name="popedom" value="+1+">
					<input class="but_excel" type="submit" value="查 询" />
				</logic-el:match>
				<logic-el:match name="popedom" value="+128+">
					<input class="but_excel" type="button" id="output" name="output"
						value="导出" />
				</logic-el:match>
			</div>
		</html-el:form >
</div>
    <form id="listForm" name="listForm" >
  	  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr>
        <td width="100px;" >分公司</td>
        <td width="100px;" >接收人</td>
	    <td width="100px;" >父任务名称</td>
	    <td width="100px;" >任务名称</td>
	    <td width="100px;" >当前子任务数</td>
	    <td width="80px;" >任务状态</td>
	    <td width="100px;" >接收/拒接期限</td>
	    <td width="90px;" >任务开始日期</td>
	    <td width="90px;" >附件</td>
	    <td width="150px;" >操作</td>
      </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
          	<tr>
							<td width="80px;">${cur.map.fgs_Dept }</td>
							<td width="80px;">${cur.map.user_name }</td>
							<td width="80px;">${cur.map.par_task_name }</td>
							<td width="80px;"><a href="${ctx}/manager/admin/YwtTaskReceive.do?method=view&id=${cur.id}" style="color:red;" target="_blank" iconCls="icon-add" plain="true">${cur.task_name }</a></td>
							<td width="80px;">${cur.map.sub_task_count }</td>
							<c:if test="${cur.is_receive==0 }"><td width="80px;"><lable style='color: red;'><b>接收</b></lable></td></c:if>
							<c:if test="${cur.is_receive==1 }"><td width="80px;"><lable style='color: green;'><b>拒绝</b></lable></td></c:if>
							<c:if test="${cur.is_receive==2 }"><td width="80px;">未接收</td></c:if>
							<td width="80px;">${cur.map.begin_date}</td>
							<td width="70px;">${cur.map.add_date }</td>
							<td width="70px;">
							  <c:forEach var="ur" items="${fn:split(cur.map.fj_paths, ',')}" varStatus="v">
								<a href="${ctx}/${ur}'" style="color:red;" target="_blank" iconCls="icon-add" plain="true" ><c:if test="${!empty ur}"> 附件${v.count }</c:if></a>
							  </c:forEach>
							</td>
							<td width="100px;">
								<c:if test="${cur.is_receive==0 }"><a href="${ctx}/manager/admin/YwtTaskReceive.do?method=addSubTask&par_task_id=${cur.task_id}&id=${cur.id}" style="color:red;">添加子任务&nbsp;</a> </c:if><c:if test="${cur.is_receive!=0 }">添加子任务&nbsp;</c:if>
								<c:if test="${cur.is_receive==2 }"><a href="${ctx}/manager/admin/YwtTaskReceive.do?method=save&par_task_id=${cur.task_id}&id=${cur.id}&is_receive=0" style="color:red;"> 接收 </a></c:if ><c:if test="${cur.is_receive!=2 }"> 接收 </c:if>
								<c:if test="${cur.is_receive==2 }"><a href="${ctx}/manager/admin/YwtTaskReceive.do?method=save&par_task_id=${cur.task_id}&id=${cur.id}&is_receive=1" style="color:red;"> 拒绝 </a></c:if ><c:if test="${cur.is_receive!=2 }"> 拒绝 </c:if>
								<a href="${ctx}/manager/admin/YwtTaskReceive.do?method=view&par_task_id=${cur.task_id}&id=${cur.id}&view=1" style="color:red;">查看</a>
							</td>
			</tr>
          </c:forEach>
      	</tbody>
      </table>
      </div>
    </form>
  	<form id="bottomPageForm" name="bottomPageForm" method="post" action="YwtTaskReceive.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("_fgs_dept_id", "${af.map._fgs_dept_id}");
				pager.addHiddenInputs("_reve_real_name_like", "${af.map._reve_real_name_like}");
				pager.addHiddenInputs("_par_task_name_like", "${af.map._par_task_name_like}");
				pager.addHiddenInputs("_task_name_like", "${af.map._task_name_like}");
				pager.addHiddenInputs("_state", "${af.map._state}");
				pager.addHiddenInputs("_begin_add_date", "${af.map._begin_add_date}");
				pager.addHiddenInputs("_end_add_date", "${af.map._end_add_date}");
				pager.addHiddenInputs("_begin_reve_finsh_date", "${af.map._begin_reve_finsh_date}");
				pager.addHiddenInputs("_end_reve_finsh_date", "${af.map._end_reve_finsh_date}");
				pager.addHiddenInputs("_begin_audit_date", "${af.map._begin_audit_date}");
				pager.addHiddenInputs("_end_audit_date", "${af.map._end_audit_date}");
				document.write(pager.toString());
			  </script> 
            </div></td>
        </tr>
      </table>
    </form>
  <div class="clear"></div>
</div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
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
	
	function receive(id,is_receive,receive){
		 if(is_receive==0&&is_receive1==1){
             alert('已拒绝 不可接收');
             return false;
		 }
		 if(is_receive==1&&is_receive1==0){
			 alert('已接收不可拒绝');
             return false;
         }
		
	}
});



//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>