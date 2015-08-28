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
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}&nbsp;> 查看日志</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
   <a href="KonkaR3Backmoney.do?method=list&amp;mod_id=${af.map.mod_id}"><font color="red" >返回</font> </a>
  </div>
  <div class="rtabcont2">
  <html-el:form action="/admin/ChannelDataImport.do" enctype="multipart/form-data">
        <html-el:hidden property="method" value="logList"/>
         <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>


    <table  width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
    <tr class="tabtt1">
            <td align="center" nowrap="nowrap">序号</td>
            <td align="center" nowrap="nowrap">操作时间</td>
            <td align="center" nowrap="nowrap">操作用户</td>
            <td align="center" nowrap="nowrap">操作用户的IP</td>
            <td align="center" nowrap="nowrap">操作类型</td>
            </tr>
    <c:forEach var="cur" items="${operLogList}" varStatus="vs">
    <tr>
		            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
		            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.oper_time}" type="both"/></td>  
		            <td align="center" nowrap="nowrap">${cur.oper_uname}</td>          	    	
		            <td align="center" nowrap="nowrap">${cur.oper_ip}</td>
		            <td align="center" nowrap="nowrap">${cur.oper_type}</td>
		  
    </tr>
    </c:forEach>
    </table>
    </html-el:form>
    </div>
 
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="ChannelDataImport.do?method=logList">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "logList");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
})
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
