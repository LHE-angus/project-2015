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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  <html-el:form action="/admin/KonkaSendMailInfo">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
        <td width="50%" nowrap="nowrap"><strong class="fb">&nbsp;&nbsp;&nbsp;&nbsp;发送时间：</strong>　 
        <html-el:text styleClass="input_txt" property="start_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="start_date" ></html-el:text>
              至
              <html-el:text styleClass="input_txt" property="end_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="end_date" ></html-el:text>
        
          &nbsp;&nbsp;
       <input class="but1" type="submit" name="Submit" value="搜索" />
       </td>
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
		    <input name="button" type="button"  class="but2" value=" 新增 " onclick="location.href='KonkaSendMailInfo.do?method=add&mod_id=${af.map.mod_id}';" />
		    </logic-el:match>
		    <logic-el:match name="popedom" value="+4+">
		    <input class="but3" type="submit" name="Submit3" value="删除" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
		    </logic-el:match> 
		    <input name="button" type="button" class="but2"  value="EMAIL " onclick="sendmail('maildiv');" />
		 </td>
	    </tr>
	  </table>  
  </div>
<div class="rtabcont1">
<form id="listForm" name="listForm" method="post" action="KonkaSendMailInfo.do?method=delete">
 <input type="hidden" name="method" id="method" value="delete" />
 <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
 <div id="maildiv">
 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
   <tr class="tabtt1">
      <td nowrap="nowrap" width="30" align="center" >序号</td>
      <td nowrap="nowrap" align="center">主题</td>
      <td nowrap="nowrap" width="200" align="center">发送时间</td> 
      <td width="120" align="center">操作</td>
    </tr>
    <tbody>
    <c:forEach var="cur" items="${entityList}" varStatus="vs">
      <tr> 
        <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
        <td align="left">${fn:escapeXml(cur.title)}</td>
        <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
        <td align="center">
        <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaSendMailInfo.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span>
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
 </div>
</form>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaSendMailInfo.do">
   <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;"> 
          <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("tree_param", "${tree_param}");
            pager.addHiddenInputs("title", "${fn:escapeXml(af.map.title)}");
            pager.addHiddenInputs("start_date", "${fn:escapeXml(af.map.start_date)}");
            pager.addHiddenInputs("end_date", "${fn:escapeXml(af.map.end_date)}");
            document.write(pager.toString());
		   </script>
		   </div></td>
      </tr>
    </table>
   </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>                                  
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
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
});

function sendmail(id){
	var title=document.title;
	var content =$("#"+id).html(); 
	var frmToPrint = document.createElement("form");
	  var titlehtml = document.createElement("input");
	  titlehtml.type = "hidden";
	  titlehtml.name = "title";
	  titlehtml.value = title; 
	  frmToPrint.appendChild(titlehtml);  
	  
	  var contenthtml = document.createElement("input");
	  contenthtml.type = "hidden";
	  contenthtml.name = "content";
	  contenthtml.value = content; 
	  frmToPrint.appendChild(contenthtml); 
	  
	  document.body.appendChild(frmToPrint); 
	  frmToPrint.method = "post";
	  frmToPrint.action = "KonkaSendMailInfo.do?method=add";
	  frmToPrint.submit();
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
