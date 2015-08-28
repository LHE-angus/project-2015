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
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
 <div class="rtabcont1">
<html-el:form action="/admin/KonkaSxOperLog">
	<html-el:hidden property="method" styleId="method" value="list" />
	<html-el:hidden property="mod_id" styleId="mod_id" />
	<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          	<td><strong class="fb">操作人：</strong>
				<html-el:text property="user_name" size="20" maxlength="20" styleId="user_name" /></td>
			 <td><strong class="fb">操作时间范围：</strong>
            <%-- <html-el:text property="oper_starttime"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /> --%>
            <input name="oper_starttime" id="oper_starttime" size="12" value="${af.map.oper_starttime}" title="点击选择日期" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true})" />
				至
			<input name="oper_endtime" id="oper_endtime" size="12" value="${af.map.oper_endtime}" title="点击选择日期" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true})" />
			<%-- <html-el:text property="oper_endtime"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /> --%>
			</td>	
			<td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
		</tr>
	</table>
</html-el:form>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
   <div class="pageContent">
		<form id="listForm" name="listForm" method="post" action="KonkaSxOperLog.do?method=delete">
		<input type="hidden" name="method" id="method" value="delete" />
		<input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
		 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
			<tr class="tabtt1">
				<td align="center" width="35" nowrap="nowrap">序号</td>
				<td align="center" nowrap="nowrap" align="center">操作人</td>
				<td align="center" nowrap="nowrap" align="center">操作时间</td>
				<td  align="center" nowrap="nowrap" align="center">操作IP</td>
				<td  align="center" nowrap="nowrap" align="center">操作表</td>
				<td  align="center" nowrap="nowrap" align="center">操作表ID</td>
				<td  align="center" nowrap="nowrap" align="center">方式</td>
				<td  align="center" nowrap="nowrap" align="center">路径</td>
				<td align="center" width="50" nowrap="nowrap">操作</td>
			</tr>
			<tbody>
			<c:forEach var="cur" items="${entityList}" varStatus="vs">
			<tr>
				<td align="center" nowrap="nowrap">${vs.count}</td>
				<td align="left">${cur.oper_uname}</td>
				<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.oper_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td align="left" nowrap="nowrap">${cur.oper_ip}</td>
				<td align="left" nowrap="nowrap">${cur.link_tab}</td>

				<td align="center" nowrap="nowrap">${cur.link_id}</td>
				<td align="left">账户名：${cur.oper_uname}</td>
				<td align="left">${cur.ppdm_name}</td>
				<td align="center">
				<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSxOperLog.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span>
				</td>
			</tr>
			<c:if test="${vs.last eq true}">
				<c:set var="i" value="${vs.count}" />
			</c:if>
			</c:forEach>
			<c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
			<tr align="center">
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
			</tbody>
		</table>
	</form>
	<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaSxOperLog.do">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="40">
					<div style="text-align:right; padding-right:5px;">
					<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
					<script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						pager.addHiddenInputs("method", "list");
						pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						pager.addHiddenInputs("user_name", "${af.map.user_name}");
						pager.addHiddenInputs("oper_starttime", "${af.map.oper_starttime}");
						pager.addHiddenInputs("oper_endtime", "${af.map.oper_endtime}");
						document.write(pager.toString());
					</script>
					</div>
				</td>
			</tr>
		</table>
	</form>
</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#oper_uname").blur(function() {
		$(this).val(this.value.trim());						   
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>