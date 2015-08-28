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
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
	<html-el:form  action="/admin/KonkaPeArticleUserGroup.do">
      	<html-el:hidden property="method" value="list" />
      	<html-el:hidden property="mod_id" styleId="mod_id"/>
     	<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
     		<tr>
          		<td width="15"></td>
          		<td width="70"><strong class="fb">群组名称：</strong></td>
          		<td colspan="2"><html-el:text property="group_name_like" size="16" maxlength="30" styleId="group_name_like" styleClass="webinput" /></td>
          		<td width="70"></td>
          		<td><html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit></td>
          	</tr>
     	</table>
	</html-el:form>
  </div>
  <%@ include file="/commons/pages/messages.jsp" %>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
		    <logic-el:match name="popedom" value="+1+">  
		    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaPeArticleUserGroup.do?method=add&mod_id=${af.map.mod_id}';" />
		    </logic-el:match>
		</td>
	 </tr>
	</table>
  </div>
  <div class="rtabcont1">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
	    <tr class="tabtt1">
	    	<td width="5%" nowrap="nowrap" align="center">序号</td>
	    	<td width="16%" nowrap="nowrap" align="center">群组</td>
	    	<td nowrap="nowrap" align="center">群组成员</td>
	    	<td width="10%" nowrap="nowrap" align="center">添加时间</td>
	    	<td width="10%" nowrap="nowrap" align="center">操作</td>
	    </tr>
	    <c:forEach var="cur" items="${entityList}" varStatus="vs">
	    	<tr>
	    		<td align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	    		<td align="left">${cur.group_name}</td>
	    		<td align="left" title="${cur.map.group_users}">${fn:substring(cur.map.group_users,0,52)}</td>
	    		<td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
	    		<td align="center"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaPeArticleUserGroup.do', 'edit','group_id=${cur.group_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">修改</span>
	    		|<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod('确认删除？', 'KonkaPeArticleUserGroup.do', 'delete','group_id=${cur.group_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">删除</span></td>
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
			</tr>
		</c:forEach>
	  </table>
	  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaPeArticleUserGroup.do">
		 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			 <tr>
				<td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
					<script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						pager.addHiddenInputs("method", "list");
						pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						pager.addHiddenInputs("group_name_like", "${af.map.group_name_like}");
						document.write(pager.toString());
					</script>
				</td>
			</tr>
		 </table>
	  </form>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
  