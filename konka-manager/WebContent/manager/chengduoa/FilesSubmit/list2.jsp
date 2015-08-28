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
      <td>当前位置：协同办公 &gt;我的审批</td>
    </tr>
  </table>
</div>
<div class="rtabcont1">
  <html-el:form action="/chengduoa/FilesSubmit">
  <html-el:hidden property="method" value="getMyAuditFiles" />
  <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0"  class="rtable1">
      <tr>
        <td ><strong class="fb">文件标题：</strong>
          <html-el:text property="file_tile" size="20" maxlength="20" styleId="file_tile" />
        </td>
        <td ><strong class="fb">文件编号：</strong>
          <html-el:text property="file_no" size="20" maxlength="20" styleId="file_no" />
        </td>
        <td ><strong class="fb">审批时间>：</strong>
          <html-el:text property="audit_datetime" size="20" maxlength="20" readonly="true" style="cursor:pointer;width:120px;" onclick="new Calendar(2012, 2050, 0).show(this);" />
        </td>
      </tr>
      <tr>
      	<td ></td>
      	<td ></td>
      	<td ><input type="submit" value="查询" class="but1"></input></td>
      </tr>
    </table>
    </html-el:form>
  </div>
 
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="FilesSubmit.do?method=delete">
      <input type="hidden" name="method" id="method" value="getMyAuditFiles" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td >文件编号</td>
          <td >文件标题</td>
          <td >申请人</td>
          <td >申请时间</td>
          <td >文件状态</td>
          <td >查看</td> 
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr id="tr_${cur.id}">
              <td>${cur.file_no}</td>
              <td >
              	${fn:escapeXml(cur.file_title)}
              </td>
              <td nowrap="nowrap" align="center">${cur.submit_user}</td>
              <td align="center" nowrap="nowrap" valign="top" ><fmt:formatDate value="${cur.submit_datetime}" pattern="yyyy-MM-dd HH:mm" /></td>
              <td align="center" nowrap="nowrap" valign="top" >
                <c:choose>
                  <c:when test="${cur.file_status eq 0 or cur.file_status eq -3}"><span style="color:#000;font-weight:bold;">未提交</span></c:when>
                  <c:when test="${cur.file_status eq 1}"><span style="color:#00F;font-weight:bold;">审批中</span></c:when>
                  <c:when test="${cur.file_status eq 2}"><span style="color:#00CC66;font-weight:bold;">已审批</span></c:when>
                </c:choose>
               </td>
              <td align="center" nowrap="nowrap" valign="top" ><a href="${ctx}/manager/oa/FilesSubmit.do?method=view&id=${cur.id}&mod_id=${af.map.mod_id}">查看 </a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="FilesSubmit.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "getMyAuditFiles");
		            pager.addHiddenInputs("file_no", "${af.map.file_no}");								
					pager.addHiddenInputs("file_tile", "${af.map.file_tile}");	
					pager.addHiddenInputs("audit_datetime", "${af.map.audit_datetime}");	
		            document.write(pager.toString());
		            </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>

  <div class="clear"></div>
</div>


<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function() {
	

});
	 
function view_and_print(id) {
    // window.showModalDialog("AuditIngFiles.do?azaz=" + Math.random() + "&method=view&id=" +id, window, "dialogWidth:800px;status:no;dialogHeight:600px");
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
