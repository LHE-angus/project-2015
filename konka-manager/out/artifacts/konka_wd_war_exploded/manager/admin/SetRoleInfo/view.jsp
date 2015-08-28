<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<c:set var="naviString" value="职位人员编辑" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style>
.user-info li {width:150px;float:left;}
</style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="200" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/SetRoleInfo">
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="method" styleId="method" value="view" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="role_id" styleId="role_id" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="url" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
         <tr>
          <td> <c:if test="${peRoleUser.role_id eq 10}">
              &nbsp;&nbsp;
              <strong class="fb">部门：</strong>
              <html-el:select property="dept_id" styleId="dept_id" style="width:200px;">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${peDeptList}">
                  <html-el:option value="${cur.dept_id}">${fn:escapeXml(cur.map.tree_name)}</html-el:option>
                </c:forEach>
              </html-el:select>
            </c:if> &nbsp;&nbsp;
          <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
       </html-el:form>
      </div>
      <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
           <tr class="tabtt1">
            <td width="30" nowrap="nowrap" ><font class="blue">序号</font></td>
            <td nowrap="nowrap" ><font class="blue">岗位ID</font></td>
            <td nowrap="nowrap" ><font class="blue">登录名</font></td>
            <td nowrap="nowrap"><font class="blue">部门</font></td>
            <td nowrap="nowrap" ><font class="blue">职务</font></td>
            <td nowrap="nowrap" align="center"><font class="blue">手机/电话</font></td>
          </tr>
            <c:forEach var="cur" items="${userInfoList}" varStatus="vs">
          <tr>
            <td>${vs.count}</td>
            <td><c:out value="${cur.job_id}" /><c:if test="${empty cur.job_id}"><span style="color:#F00;">未填写</span></c:if></td>
            <td>${cur.user_name}</td>
             <td>${cur.map.full_dept_name}</td>
             <td >${cur.map.role_name}</td>
            <td><c:out value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '')}" /></td>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="SetRoleInfo.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript"> 
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "view");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("role_id", "${af.map.role_id}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
