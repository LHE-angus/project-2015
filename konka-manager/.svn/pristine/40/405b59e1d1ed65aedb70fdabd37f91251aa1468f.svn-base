<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>请选择代理商</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="${ctx}/styles/manager/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
</head>
<body>
<div class="oarcont" id="body_oarcont">
<div class="rtabcont1">
      <html-el:form action="/admin/SelectAgentsList">
        <html-el:hidden property="id" styleId="id"/>
        <html-el:hidden property="area_limit" value="${af.map.area_limit}"/>
        <html-el:hidden property="method" value="list" />
 		<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       	 <tr>
       	    <td width="15"></td>
 			<td>关键字：
              <html-el:text property="keyword" styleId="keyword" size="24" maxlength="100" style="width:100px;"/>
       	 </td>
      	  <td><html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
       </table>
      </html-el:form>
    </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <form id="listForm" name="listForm" method="post" action="SelectAgentsList.do">
        <input type="hidden" name="method" id="method" value="delete" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="30" align="center">序号</td>
          <td nowrap="nowrap" width="60">R3编码</td>
          <td nowrap="nowrap">客户名称/有无交易</td>	
          <td nowrap="nowrap" width="52">区域信息</td>	
          <td nowrap="nowrap" width="70">经办名称</td>	
          <td nowrap="nowrap" width="60">合并编码</td>
          <td nowrap="nowrap" align="center" width="70">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap"><c:out value="${vs.count}"/></td>
           <td align="left" nowrap="nowrap">${fn:escapeXml(cur.r3_code)}</td>  
            <c:if test="${af.map.is_match eq 0 or empty af.map.is_match}">
            <td align="left">${fn:escapeXml(cur.customer_name)}</td>    
            </c:if>
            <c:if test="${af.map.is_match eq 1}">
            <td align="left" ><a style="cursor:pointer;color:blue;" href="KonkaR3MmtMatch.do?method=detail&id=${cur.id}">${cur.customer_name}</a></td>
            </c:if>      	
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.area_name)}-${fn:escapeXml(cur.branch_area_name)}</td>            	
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.handle_name)}</td>            	
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.customer_code)}</td>
            <td align="center"><html-el:button property="" value="选 择" styleClass="but4" styleId="btn_submit" onclick="choose(${cur.id},'${fn:escapeXml(cur.customer_name)}');"/></td>
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="SelectAgentsList.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("area_limit", "${af.map.area_limit}");
			pager.addHiddenInputs("keyword", "${fn:escapeXml(af.map.keyword)}");	
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#key_word").attr("autocomplete", "off");
	$("#key_word").attr("disableautocomplete", "true");
});

function choose(id,name){
	window.returnValue = {id:id, name:name};
	window.close();
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>