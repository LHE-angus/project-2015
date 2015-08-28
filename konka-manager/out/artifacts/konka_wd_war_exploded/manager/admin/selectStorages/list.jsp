<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>请选择仓库仓位</title>
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
     <html-el:form action="/admin/selectStorages">
       <html-el:hidden property="method" styleId="method" value="list" />
       <html-el:hidden property="mod_id" styleId="mod_id" value="${af.map.mod_id}" />
       <html-el:hidden property="dept_id" styleId="dept_id" />
       <html-el:hidden property="storage_type" styleId="storage_type" />
       <html-el:hidden property="storage_par_id" styleId="storage_par_id" />
       <html-el:hidden property="tree_param" value="${tree_param}" />
          <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2" >
			<tr>
			<!-- <c:if test="${af.map.storage_type eq 1}">
             	<td><strong class="fb"> &nbsp;&nbsp;请选择：</strong>
             	 <html-el:select property="storage_par_name" styleId="storage_par_name" style="width:130px;">
                  <html-el:option value="">请选择...</html-el:option>
                  <c:forEach var="cur" items="${sybDeptInfoList}">
                    <html-el:option value="${cur.id}">${cur.type_name}</html-el:option>
                  </c:forEach>
                </html-el:select>
				  </td></c:if>
				  <c:if test="${af.map.storage_type eq 2}">
             	<td><strong class="fb"> &nbsp;&nbsp;请选择：</strong>
             	 <html-el:select property="storage_par_name" styleId="storage_par_name" style="width:130px;">
                  <html-el:option value="">请选择...</html-el:option>
                  <c:forEach var="cur" items="${sybDeptInfoList}">
                    <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                  </c:forEach>
                </html-el:select>
				  </td></c:if> -->
             	<td><strong class="fb"> &nbsp;&nbsp;仓位名称：</strong>
             	 <html-el:text property="storage_name" size="20" maxlength="20" styleId="storage_name" />
				  </td>
                 <td><html-el:submit styleClass="but1" value="搜索" /></td>
              </tr> 
            </table>
      </html-el:form>
</div>   
<div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
        <form id="listForm" name="listForm" method="post" action="selectStorages.do?method=delete">
         <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
         <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
         	<tr class="tabtt1">
              <td width="30" nowrap="nowrap" align="center">序号</td>
              <td nowrap="nowrap" align="center">仓库名称</td>
              <td nowrap="nowrap" align="center">所属上级部门</td>
            </tr>
          <tbody>
            <c:forEach var="cur" items="${entityList}" varStatus="vs">
              <tr style="cursor: pointer;" onclick="setReturnValue('${cur.storage_id}','${cur.storage_name}');">
               <td align="center">${vs.count}
                	<html-el:hidden property="storage_name_${cur.storage_id}" styleId="storage_name_${cur.storage_id}" value="${cur.storage_name}" />
                  </td>
                <td align="center" nowrap="nowrap">${cur.storage_name}</td>
                <td align="center" nowrap="nowrap">
                <c:if test="${cur.storage_type eq 1}">
				${cur.map.type_name}（经办）
				</c:if>
				<c:if test="${cur.storage_type eq 2}">
				${cur.map.dept_name}（分公司）
				</c:if>
				<c:if test="${cur.storage_type eq 3}">
				${cur.map.dept_name}>>${cur.map.par_name}
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
              </tr>
            </c:forEach>
          </tbody>
        </table>
        </form>
     <form id="bottomPageForm" name="bottomPageForm" method="post" action="selectStorages.do">
       <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
			pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("storage_par_name", "${af.map.storage_par_name}");
			pager.addHiddenInputs("storage_name", "${af.map.storage_name}");
            document.write(pager.toString());
            </script></td>
        </tr>
        </table>
      </form>
      </div>
    </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
                                          
$(document).ready(function(){
	
});
function setReturnValue(id,value) {
		var name = $("#storage_name_" + id).val();
			window.returnValue = {
				storageid : id,
				storagename : name
			};
			window.close(); 
	};
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
