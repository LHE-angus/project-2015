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
       <!--  <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td> --> 
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/chengduoa/SsuedDocument.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="50%" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">文件编号：</strong>
            <html-el:text property="file_code" size="20" maxlength="20" styleId="file_code" />
              &nbsp;&nbsp;<strong class="fb">文件主题：</strong>
            <html-el:text property="title" styleId="title" size="20" maxlength="50" />
            &nbsp;&nbsp;<strong class="fb">事务类型：</strong>
            <html-el:select property="mod_type">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="notice">公告通知</html-el:option>
              <!-- <html-el:option value="meeting_record">会议纪要</html-el:option> -->
              <html-el:option value="file">下发文件</html-el:option>
              <!--<html-el:option value="meeting">会议通知</html-el:option>-->
            </html-el:select>
            &nbsp;&nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="30" nowrap="nowrap" align="center">序号</td>
        <td width="40" nowrap="nowrap" align="center">月份</td>
        <td width="55" nowrap="nowrap" align="center">事务类型</td>
        <td width="60" nowrap="nowrap">文件编号</td>
        <td nowrap="nowrap">文件主题</td>
        <td nowrap="nowrap">发放人/部门</td>
        <td width="100" nowrap="nowrap" align="center">发文时间</td>
      </tr>
      <tbody>
        <c:forEach var='cur' items='${entityList}' varStatus="vs">
          <c:set var="ids" value="${ids},${cur.id }" />
            <tr valign="top" id="tr_${cur.id}">
            <td valign="top" align="center" nowrap="nowrap">${vs.count }</td>
            <td valign="top" align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM" /></td>
            <td valign="top" align="center" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${cur.mod_name}</td>
            <td valign="top" align="left" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${cur.file_code}</td>   
            <td valign="top" align="left" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;"><c:choose>
                <c:when test="${cur.mod_type eq 'notice' }"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'DocIssue.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">${cur.title}</span></c:when>
                <c:when test="${cur.mod_type eq 'file' }"><span style="cursor:pointer;" class="fblue" onclick="view_and_print(${cur.id});">${cur.title}</span></c:when>
                <c:otherwise></c:otherwise>
              </c:choose></td>
            <td valign="top" align="left" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">${cur.pass_man_name}/${cur.part_name}</td>
            <td valign="top" align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm" /></td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
          <c:set var="ids" value="${ids},${cur.id }" />
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
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="SsuedDocument.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
			  var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", '${af.map.mod_id}');
				pager.addHiddenInputs("file_code", '${af.map.file_code}');
				pager.addHiddenInputs("title", '${af.map.title}');
				pager.addHiddenInputs("mod_type", '${af.map.mod_type}');
				document.write(pager.toString());
            	 </script>
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function() {
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
  
function view_and_print(id) {
   // window.showModalDialog("AuditIngFiles.do?azaz=" + Math.random() + "&method=view&id=" +id, window, "dialogWidth:800px;status:no;dialogHeight:600px");
	 window.open("AuditIngFiles.do?azaz=" + Math.random() + "&method=view&id=" +id);

}                                    
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
