<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${naviString}</title>
<!--[if IE]>
<link href="${ctx}/styles/manager/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
</head>
<body>
<div id="navTab" class="tabsPage">
  <div class="tabsPageHeader">
    <div class="tabsPageHeaderContent">
      <ul class="navTab-tab">
        <li class="main"><a><span><span class="home_icon">${naviString}</span></span></a></li>
      </ul>
    </div>
  </div>
  <div class="navTab-panel tabsPageContent">
    <div class="page">
      <div class="pageHeader">
        <html-el:form action="/admin/SetCategory">
          <html-el:hidden property="method" value="list" />
          <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
          <div class="searchBar">
            <table width="100%" border="0" cellspacing="5" cellpadding="0">
              <tr>
                <td width="250">类别名称：
                  <html-el:text property="c_name" size="20" maxlength="20" styleId="c_name" /></td>
                <td width="250">类别类型：
              <html-el:select property="c_type" styleId="c_type">
              	<html-el:option value="">请选择...</html-el:option>
              	<html-el:option value="10">咨询类型</html-el:option>
              	<html-el:option value="2">补助类型</html-el:option>
              	<html-el:option value="3">扣罚类型</html-el:option>
              	<html-el:option value="4">故障类型</html-el:option>
              	<html-el:option value="5">业务分类</html-el:option>
              	<html-el:option value="6">维修级别</html-el:option>
              	<html-el:option value="7">服务方式</html-el:option>
              	<html-el:option value="8">产品类型</html-el:option>
              	<html-el:option value="9">新闻类型</html-el:option>
              	<html-el:option value="12">回访模板类别</html-el:option>
              </html-el:select></td>
                <!--<td>是否删除：
                  <html-el:select property="is_del">
                    <html-el:option value="0">正常</html-el:option>
                    <html-el:option value="1">已删除</html-el:option>
                  </html-el:select></td>
              --></tr>
            </table>
            <div class="subBar">
              <ul>
                <li><input type="image" name="imageField" id="imageField" src="${ctx}/images/button_search.gif" /></li>
              </ul>
            </div>
          </div>
        </html-el:form>
      </div>
      <%@ include file="/commons/pages/messages.jsp" %>
      <div class="pageContent">
        <form id="listForm" name="listForm" method="post" action="SetCategory.do?method=delete">
        <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
        <input type="hidden" name="c_type" id="c_type" value="${af.map.c_type}" />
        <div class="panelBar">
          <ul class="toolBar">
            <li><a class="add" href="#" onclick="location.href='SetCategory.do?method=add&c_type=${af.map.c_type}&mod_id=${af.map.mod_id}';return false;"><span>添加</span></a></li>
            <li class="line">line</li>
            <li><a class="delete" onclick="confirmDeleteAll(document.getElementById('listForm'));"><span>删除</span></a></li>
          </ul>
        </div>
        <table class="list" width="100%">
          <thead>
            <tr>
              <th width="25" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" />
              </th>
              <th width="100" nowrap="nowrap">类别类型</th>
              <th nowrap="nowrap">类别名称</th>
              <th width="100" nowrap="nowrap">排序值</th>
              <th width="100" nowrap="nowrap">操作</th>
            </tr>
          </thead>
          <tbody>
        <c:forEach var='cur' items='${entityList}' varStatus="vs">
          <tr>
            <td align="center">
            <c:if test="${cur.is_del eq 0}"><input name="pks" type="checkbox" id="pks" value="${cur.c_index}" /> </c:if>
            <c:if test="${cur.is_del eq 1}"><input name="pks" type="checkbox" id="pks" value="${cur.c_index}" disabled="disabled" /></c:if>
            </td>
            <td align="left">
            	<c:choose>
	            	<c:when test="${cur.c_type eq 10}">咨询类型</c:when>
	              	<c:when test="${cur.c_type eq 2}">补助类型</c:when>
	              	<c:when test="${cur.c_type eq 3}">扣罚类型</c:when>
	              	<c:when test="${cur.c_type eq 4}">故障类型</c:when>
	              	<c:when test="${cur.c_type eq 5}">业务分类</c:when>
	              	<c:when test="${cur.c_type eq 6}">维修级别</c:when>
	              	<c:when test="${cur.c_type eq 7}">服务方式</c:when>
	              	<c:when test="${cur.c_type eq 8}">产品类型</c:when>
	              	<c:when test="${cur.c_type eq 9}">新闻类型</c:when>
	              	<c:when test="${cur.c_type eq 12}">回访模板类别</c:when>
            	</c:choose>
            </td>
            <td align="left"><span style="margin-left:${(cur.map.level-1)*2}0px;">${cur.c_name}</span></td>
            <td align="center">${cur.order_sort}</td>
            <td align="center">
            <span style="cursor:pointer;" class="fblue" onclick="confirmUpdate(null, 'SetCategory.do', 'mod_id=${af.map.mod_id}&c_index=${cur.c_index}&par_index=${cur.par_index}')">修改</span>
            |
            <c:if test="${cur.is_del eq 0}"><span class="fblue" style="cursor:pointer; margin-left:7px;" onclick="confirmDelete('确定删除吗?', 'SetCategory.do', 'c_index=${cur.c_index}&mod_id=${af.map.mod_id}&c_type=${af.map.c_type}')">删除</span></c:if>
            <c:if test="${cur.is_del eq 1}"><span class="fblue" style="color:#999; margin-left:7px;">删除</span></c:if></td>
          </tr>
            </c:forEach>
          </tbody>
        </table>
        </form>
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="SetCategory.do">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40"><div style="text-align:right; padding-right:5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
			    	pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			    	pager.addHiddenInputs("c_type", "${af.map.c_type}");
			    	pager.addHiddenInputs("c_name", "${fn:escapeXml(af.map.c_name)}");
		            document.write(pager.toString());
		          </script>
                </div></td>
            </tr>
          </table>
        </form>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

});

String.prototype.trim = function(){ 
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>