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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/javascripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>  
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
	<html-el:form action="/paragon/KonkaParagonShowmanre">
		<html-el:hidden property="method" styleId="method" value="list" />
		<html-el:hidden property="mod_id" styleId="mod_id" />
		<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
		<tr>
		    <td width="15"></td>
	       <td><strong class="fb">上报员名称：</strong><html-el:text property="user_name_like" size="30" maxlength="20" styleId="user_name_like"  /></td>
	        <td><html-el:submit styleClass="but1" value="搜索"  styleId="btn_submit" /></td>
        </tr>
       </table>
		</html-el:form>
	</div>
	 <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp" %>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td>
			    <logic-el:match name="popedom" value="+1+">  
			    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaParagonShowmanre.do?method=add&mod_id=${af.map.mod_id}';" />
			    </logic-el:match>
			</td>
		 </tr>
		</table>
	  </div>
	  <div class="rtabcont1">
			<form id="listForm" name="listForm" method="post" action="KonkaParagonShowmanre.do?method=delete">
				<input type="hidden" name="method" id="method" value="delete" />
				<input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
       	       		<tr class="tabtt1">
						<td width="30" nowrap="nowrap" align="center">序号</td>
						<td nowrap="nowrap" align="center">上报员名称</td>
						<td nowrap="nowrap" align="center">门店信息</td>
						<td width="100" nowrap="nowrap" align="center">操作</td>
					</tr>
					<tbody>
					<c:forEach var="cur" items="${entityList}" varStatus="vs">
					<tr>
						<td align="center" nowrap="nowrap">${vs.count}</td>
						<td align="center" nowrap="nowrap">${cur.map.user_name}</td>
						<td align="left">${cur.map.dislist}</td>
						<td align="center">
						<logic-el:match name="popedom" value="+2+">
							<span style="cursor:pointer;" class="fblue" onclick="confirmUpdate(null, 'KonkaParagonShowmanre.do', 'user_id=${cur.user_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">修改</span>
						</logic-el:match>
						<logic-el:notMatch name="popedom" value="+2+">
							<span style="color:#CCC;">修改</span>
						</logic-el:notMatch>
						|
						<logic-el:match name="popedom" value="+4+">
							<span style="cursor:pointer;"  class="fblue" onclick="confirmDelete('确定删除吗?', 'KonkaParagonShowmanre.do', 'user_id=${cur.user_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">删除</span>
						</logic-el:match>
						<logic-el:notMatch name="popedom" value="+4+">
							<span style="color:#CCC;">删除</span></logic-el:notMatch>
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
					</tr>
					</c:forEach>
					</tbody>
				</table>
			</form>
			<form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaParagonShowmanre.do">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="40">
							<div style="text-align:right; padding-right:5px;">
							<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
							<script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("user_name_like", "${af.map.user_name_like}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.forms[0];
	$("#btn_submit").click(function(){
		if (this.form.st_pub_date.value != "" && this.form.en_pub_date.value != "") {
			if (this.form.en_pub_date.value < this.form.st_pub_date.value) {
				alert("结束日期不得大于开始日期,请重新选择!")
				return false;
			}
		}
		this.form.submit();
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>