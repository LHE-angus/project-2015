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
      <html-el:form action="/admin/KonkaCategoryType">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
 		<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       	 <tr>
 			<td><strong class="fb">R3编码：</strong><html-el:text property="r3_code" size="20" maxlength="20" styleId="r3_code" title="请输入R3客户编码"/></td>
 			<td><strong class="fb">R3合并编码：</strong><html-el:text property="customer_code" size="20" maxlength="20" styleId="customer_code"  /></td>
 			<td><strong class="fb">客户名称：</strong><html-el:text property="customer_name" size="20" maxlength="20" styleId="customer_name"  /></td>
      	  	<td><html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
       
       </table>
      </html-el:form>
    </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaCategoryType.do">
    <%@ include file="/commons/pages/messages.jsp" %>
      <html-el:hidden property="method" value="edit" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
       <tr>
        <input type="button" name=button id="button" class="but7" value="批量" onclick="confirmDispatchAll(this.form);" />
       	</tr>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="30" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td nowrap="nowrap">序号</td>
          <td nowrap="nowrap">R3编码</td>
          <td nowrap="nowrap">R3合并编码</td>
          <td nowrap="nowrap">客户名称</td>
          <td nowrap="nowrap">添加时间</td>
          <td nowrap="nowrap" align="center" width="60">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.id}" /></td>
            <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.r3_code)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.customer_code)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.customer_name)}</td>
            <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.import_date}" pattern="yyyy-MM-dd" /></td>
            <td>
            <a href="${ctx}/manager/admin/R3UserCodeMerge.do?method=edit&pks=${cur.id }&mod_id=${af.map.mod_id}">修改</a>
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
          </tr>
        </c:forEach>
      </table>
    </form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="R3UserAssign.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>

<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
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

function confirmDispatchAll(form) {
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
	form.submit();
}
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>