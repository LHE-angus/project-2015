<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<title>${naviString}</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/admin/GcxmAuditProcess">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
         
          <td width="6%" nowrap="nowrap"><strong class="fb">审核类型:</strong></td>
          <td width="5%"><html-el:select property="audit_type" styleId="audit_type" style="width:120px;" onchange="this.form.submit();">
              <html-el:option value="">请选择...</html-el:option>
              <html-el:option value="1001">工程项目审核</html-el:option>
              <html-el:option value="1002">工程项目报价审核</html-el:option>
            </html-el:select></td>
   
          <td width="6%" nowrap="nowrap"><strong class="fb">是否删除:</strong></td>
          <td width="8%"><html-el:select property="is_del" styleId="is_del" style="width:80px;" onchange="this.form.submit();">
            <html-el:option value="0">未删除</html-el:option>
            <html-el:option value="1">已删除</html-el:option>
          </html-el:select></td>
          
          <td><html-el:submit value="搜 索" styleClass="bgSearch"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="GcxmAuditProcess.do?method=delete">
  <input type="hidden" name="mod_id" id="mod_id" />
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
      <!--     <input type="button" name="delete" id="delete" class="bgButton" value="删除全部" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />&nbsp; -->
     	<input type="button" name="add" id="add" class="bgButtonAdd"  value="新 增 " onclick="location.href='GcxmAuditProcess.do?method=add&mod_id=${af.map.mod_id}';" />
    </div>
    <div class="rtabcont1" style="overflow-x: auto;">
      <div style="overflow-x:auto;">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr> 
          <!--        <th width="5%" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>-->
          <th width="5%" nowrap="nowrap">序号</th>
          <th width="10%" nowrap="nowrap">流程名称</th>
          <th nowrap="nowrap">流程描述</th>
          <th width="5%" nowrap="nowrap">添加人</th>
          <th width="10%" nowrap="nowrap">添加日期</th>
          <th width="10%" nowrap="nowrap">审核类型</th>
          <th width="5%" nowrap="nowrap">是否删除</th>
          <th width="5%" nowrap="nowrap">操作</th>
        </tr>
        <c:forEach items="${entityList}" var="cur" varStatus="vs">
          <tr> 
            <td align="center" nowrap="nowrap">${vs.count} </td>
            <td align="left" nowrap="nowrap">${cur.process_name} </td>
            <td align="left" nowrap="nowrap">${cur.map.process_desc}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.create_user_name)}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.create_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
             <td align="left" nowrap="nowrap">
             <c:if test="${cur.audit_type eq 1001}">工程项目审核</c:if>
             <c:if test="${cur.audit_type eq 1002}">工程项目报价审核</c:if>
             </td>
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${cur.is_del eq 0}"> <span style="color:#060;">未删除</span></c:when>
                <c:when test="${cur.is_del eq 1}"> <span style="color:#F00;">已删除</span></c:when>
              </c:choose>
            </td>
            <td align="center" nowrap="nowrap">
           <c:if test="${cur.is_del eq 0}">
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'GcxmAuditProcess.do', 'edit','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());">修改</span>
           </c:if>
            <c:if test="${cur.is_del eq 1}">
            <span style="color: #ccc">修改</span> 
            </c:if>
           	| 
           	<c:if test="${cur.is_del eq 0}">
           	<span style="cursor:pointer;"  class="fblue"  onclick="if(confirm('确定删除？')){doNeedMethod(null, 'GcxmAuditProcess.do', 'delete','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())}else{return false}">删除</span>
        	</c:if>
        	<c:if test="${cur.is_del eq 1}">
        	<span style="color: #ccc">删除</span>
        	</c:if>
        	| <span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'GcxmAuditProcess.do', 'view','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span>
            </td>
          </tr>
        </c:forEach>
      </table>
      </div>
    </div>
  </form>
  <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="GcxmAuditProcess.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
		       var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		       pager.addHiddenInputs("method", "list");
		       pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		       pager.addHiddenInputs("audit_type", "${af.map.audit_type}");
		       pager.addHiddenInputs("is_del", "${af.map.is_del}");
		       document.write(pager.toString());
		       </script></td>
        </tr>
      </table>
    </form>
  </div>
 
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>