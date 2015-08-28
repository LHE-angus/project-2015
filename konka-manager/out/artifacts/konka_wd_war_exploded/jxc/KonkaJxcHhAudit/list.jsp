<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退换货管理&gt; 换货审批</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
<div class="rtabcont1">
  <html-el:form action="/KonkaJxcHhAudit.do">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td align="left" nowrap="nowrap"><strong class="fb">产品型号：</strong>
          <html-el:text property="pd_name_like" styleClass="webinput" styleId="pd_name_like" />
          &nbsp; <strong class="fb">审批状态：</strong>
          <html-el:select property="is_audit" styleId="is_audit">
            <html-el:option value="">全部</html-el:option>
            <html-el:option value="0">未审批</html-el:option>
            <html-el:option value="1">已通过</html-el:option>
            <html-el:option value="-1">未通过</html-el:option>
          </html-el:select>
          &nbsp; <strong class="fb">是否确认：</strong>
          <html-el:select property="hh_is_confirm" styleId="hh_is_confirm">
            <html-el:option value="">全部</html-el:option>
            <html-el:option value="0">未确认</html-el:option>
            <html-el:option value="1">已确认</html-el:option>
          </html-el:select>
          &nbsp;
          <input class="bgSearch" type="submit" name="Submit" value="搜 索" /></td>
      </tr>
    </table>
  </html-el:form>
</div>
<%@ include file="/commons/pages/messages.jsp"%>
<div class="rtabcont1">
  <form id="listForm" name="listForm" method="post" action="KonkaJxcHhAudit.do?method=delete">
    <input type="hidden" name="method" id="method" value="delete" />
    <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tr>
        <th width="5%">序号</th>
        <th width="20%" nowrap="nowrap">产品类别</th>
        <th width="20%" nowrap="nowrap">产品型号</th>
        <th width="5%" nowrap="nowrap">换货数量</th>
        <th nowrap="nowrap">产品单价</th>
        <th width="10%" nowrap="nowrap">添加时间</th>
        <th width="10%" nowrap="nowrap">审批状态</th>
        <th width="10%" nowrap="nowrap">是否确认</th>
        <th width="10%" nowrap="nowrap">操作</th>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td height="28"  align="center">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="center">${fn:escapeXml(cur.pd_type_name)}</td>
          <td align="center">${fn:escapeXml(cur.pd_name)}</td>
          <td align="center">${cur.hh_count}</td>
          <td align="center">${cur.price}</td>
          <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" var="_add_datetime" />
            ${_add_datetime}</td>
          <td align="center"><c:if test="${cur.is_audit eq 0}">未审批</c:if>
            <c:if test="${cur.is_audit eq -1}"><span style="color:#F00;">未通过</span></c:if>
            <c:if test="${cur.is_audit eq 1}"><span style="color:#060;">已通过</span></c:if></td>
          <td align="center"><c:if test="${cur.hh_is_confirm ne 1}"><span style="color:#F00;">未确认</span></c:if>
            <c:if test="${cur.hh_is_confirm eq 1}"><span style="color:#060;">已确认</span></c:if></td>
          <td align="center" nowrap="nowrap"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcHhAudit.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span> |
            <c:if test="${cur.is_audit ne 0 }"><span style="color:#ccc" class="fblue"  >审批</span></c:if>
            <c:if test="${cur.is_audit eq 0 }"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcHhAudit.do', 'edit','id=${cur.id}&'+$('#bottomPageForm').serialize())">审批</span></c:if></td>
        </tr>
        <c:if test="${vs.last eq true}">
          <c:set var="i" value="${vs.count}" />
        </c:if>
      </c:forEach>
    </table>
  </form>
  <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcHhAudit.do">
      <table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("pd_name_like", "${af.map.pd_name_like}");
	            pager.addHiddenInputs("is_audit", "${af.map.is_audit}");
	            pager.addHiddenInputs("hh_is_confirm", "${af.map.is_hh_is_confirm}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <!--<div class="rtabcont3"></div>
  <div class="clear"></div>
	-->
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>