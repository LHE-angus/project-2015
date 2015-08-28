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
        <td nowrap="nowrap">当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/spgl/EcUserHydj.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td style="height:25px;" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">姓名：</strong>
            <html-el:text property="name_like" styleId="name_like"  style="width:160px;" maxlength="20"/>
           &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td> 
        
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="EcUserHydj.do">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="30" nowrap="nowrap" ><font class="blue">序号</font></td>
            <td nowrap="nowrap" ><font class="blue">会员卡号</font></td>
            <td nowrap="nowrap" ><font class="blue">姓名</font></td>
            <td nowrap="nowrap" ><font class="blue">会员等级</font></td>
            <td nowrap="nowrap" ><font class="blue">类型</font></td>
            <td nowrap="nowrap" ><font class="blue">部门名称</font></td> 
            <td nowrap="nowrap" align="center">付款积分</td>
            <td width="80" align="center"><font class="blue">操作</font></td>
          </tr><c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
              <td height="28"  align="center">${vs.count}</td>
              <td align="left"><c:out value="${cur.card_no}" /></td>
              <td align="left" valign="middle"><a style="text-decoration:underline;" href="EcUserHydj.do?method=view&id=${cur.id}&mod_id=${af.map.mod_id}">${cur.real_name}</a></td>
              <td align="left"><c:out value="${cur.ecBaseCardLevel.card_level_name}" /></td>
              <td align="left"><c:if test="${cur.user_type eq 1}">工卡会员</c:if><c:if test="${cur.user_type eq 2}">触网会员</c:if><c:if test="${cur.user_type eq 3}">其他</c:if></td>
              <td align="left"><c:out value="${cur.department}" /></td>
              <td align="center"><fmt:formatNumber value="${cur.map.integral }" pattern="#,##0" /></td>
              <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcUserHydj.do', 'edit','id=${cur.id}&'+$('#bottomPageForm').serialize())">会员等级管理</span></td>
           </tr>
           <c:if test="${vs.last eq true}"><c:set var="i" value="${vs.count}" /></c:if>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcUserHydj.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("name_like", "${fn:escapeXml(af.map.name_like)}"); 
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
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
   
	 $("#export_excel").click(function(){
	    	if(!confirm("提示，您确认导出数据？")){
	    		return false;
	    	}
	 });

	 
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
