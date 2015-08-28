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
        <!--  <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /><span id="span_help" style="cursor:pointer;">帮助</span> </td> --> 
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/chengduoa/AuditNodeManager">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="100%" nowrap="nowrap">&nbsp;&nbsp;
          	&nbsp;&nbsp;<strong class="fb">流程名称：
          	</strong><html-el:text property="audit_node_name_like" styleId="audit_node_name_like" />
          	&nbsp;&nbsp;<strong class="fb">流程类别：</strong>
          	<html-el:select property="node_type" onchange="this.form.submit();">
          		<html-el:option value="">-请选择-</html-el:option>
          		<html-el:option value="0">文件审批</html-el:option>
          		<html-el:option value="1">费用审批</html-el:option>
          		<html-el:option value="2">请假审批</html-el:option>
          	</html-el:select>
            &nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input style="align:left" name="button" type="button"  value="新增" class="but2" onclick="location.href='AuditNodeManager.do?method=add&mod_id=${af.map.mod_id}';" />
          <input class="but3" type="submit" name="Submit3" value="删除" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="AuditNodeManager.do?method=delete">
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
       <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
         <tr class="tabtt1">
          <td width="5%" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td nowrap="nowrap" align="center">流程名称</td>
          <c:if test="${af.map.is_admin eq 1}">
          	<td width="10%" align="center" nowrap="nowrap">分公司</td>
          </c:if>
          <td nowrap="nowrap" align="center" width="10%">流程类别</td>
          <td nowrap="nowrap" width="8%" align="center">操作</td>
        </tr>
        <tbody>
          <c:set var="ids" value="0" />
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">
              <c:if test="${cur.audit_node_name eq 'EPP退换货订单审核流程'}">
	              <input name="pks" type="checkbox" id="pks_${cur.link_id}" value="${cur.link_id}" disabled="disabled"/>
              </c:if>
              <c:if test="${cur.audit_node_name ne 'EPP退换货订单审核流程'}">
	              <c:if test="${cur.audit_node_name eq '顺丰审核流程'}">
		              <input name="pks" type="checkbox" id="pks_${cur.link_id}" value="${cur.link_id}" disabled="disabled"/>
	              </c:if>
	              <c:if test="${cur.audit_node_name ne '顺丰审核流程'}">
	            	  <input name="pks" type="checkbox" id="pks_${cur.link_id}" value="${cur.link_id}" />
	              </c:if>
              </c:if>
              </td>
              <td align="left"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'AuditNodeManager.do', 'view', 'id=${cur.id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">${cur.audit_node_name}</span></td>
              <c:if test="${af.map.is_admin eq 1}">
              	<td align="left"  nowrap="nowrap">${cur.map.dept_name}</td>
              </c:if>
              <td align="center"  nowrap="nowrap"><c:choose>
              	<c:when test="${cur.node_type eq 0}">文件审批</c:when>
              	<c:when test="${cur.node_type eq 1}">费用申请</c:when>
              	<c:when test="${cur.node_type eq 2}">请假审批</c:when>
              </c:choose></td>
              <td align="center"  nowrap="nowrap">
               <span style="cursor: pointer;" onclick="confirmUpdate(null, 'AuditNodeManager.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>
                |
                <c:if test="${cur.audit_node_name eq 'EPP退换货订单审核流程'}">
	                	删除
                </c:if>
                <c:if test="${cur.audit_node_name ne 'EPP退换货订单审核流程'}">
	                <c:if test="${cur.audit_node_name eq '顺丰审核流程'}">
	                	删除
	                </c:if>
	                <c:if test="${cur.audit_node_name ne '顺丰审核流程'}">
		                <span style="cursor: pointer;" onclick="confirmDelete(null, 'AuditNodeManager.do', 'link_id=${cur.link_id}&' + $('#bottomPageForm').serialize())">删除</span>
	                </c:if>
                </c:if>
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
              <c:if test="${af.map.is_admin eq 1}">
              	<td>&nbsp;</td>
              </c:if>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="AuditNodeManager.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
           <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
			  var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		        pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");							
				pager.addHiddenInputs("audit_node_name_like", "${af.map.audit_node_name_like}");							
				pager.addHiddenInputs("node_type", "${af.map.node_type}");							
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
