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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
	<html-el:form  action="/admin/KonkaMobileCategory">
      	<html-el:hidden property="method" value="list" />
      	<html-el:hidden property="mod_id" styleId="mod_id"/>
     	<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
     		<tr>
          		<td width="15"></td>
          		<td width="70"><strong class="fb">类别名称：</strong></td>
          		<td colspan="2"><html-el:text property="c_name" size="16" maxlength="16" styleId="c_name" styleClass="webinput" /></td>
          		<td width="70"></td>
          		<td><html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit></td>
          	</tr>
     	</table>
	</html-el:form>
  </div>
  <%@ include file="/commons/pages/messages.jsp" %>
  <div class="rtabcont1">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
	    <tr class="tabtt1">
	    	<td width="30" nowrap="nowrap" align="center">序号</td>
	    	<td width="" nowrap="nowrap" align="center">类别名称</td>
	    	<td width="180" nowrap="nowrap" align="center">说明</td>
	    	<td width="70" nowrap="nowrap" align="center">级别</td>
	    	<td width="70" nowrap="nowrap" align="center">父级</td>
	    	<td width="70" nowrap="nowrap" align="center">排序值</td>
	    	<td width="70" nowrap="nowrap" align="center">类型值</td>
	    	<td width="70" nowrap="nowrap" align="center">是否删除</td>
	    	<td width="70" nowrap="nowrap" align="center">类别类型</td>
	    	<td width="70" nowrap="nowrap" align="center">锁定</td>
	    </tr>
	    <c:forEach var="cur" items="${entityList}" varStatus="vs">
	    	<tr>
	    		<td align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	    		<td align="left">${cur.c_name}</td>
	    		<td align="left">${cur.c_comm}</td>
	    		<td align="center">${cur.c_level}</td>
	    		<td align="center">${cur.par_index}</td>
	    		<td align="center">${cur.order_sort}</td>
	    		<td align="center">${cur.order_value}</td>
	    		<td align="center">${cur.is_del}</td>
	    		<td align="center">${cur.is_type}</td>
	    		<td align="center">${cur.is_lock}</td>
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
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	  </table>
	  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileCategory.do">
		 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			 <tr>
				<td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
					<script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
						pager.addHiddenInputs("method", "list");
						pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
						pager.addHiddenInputs("c_name", "${af.map.c_name}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	subcomp_id_chg();
	
	var queryForm = document.forms[0];
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
  