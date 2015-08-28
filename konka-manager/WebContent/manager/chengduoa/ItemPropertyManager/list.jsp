<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<c:set var="naviString" value="事项属性维护" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
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
        <li class="main"><a href="javascript:void(0)"><span><span class="home_icon">${naviString}</span></span></a></li>
      </ul>
    </div>
  </div>
  <div class="navTab-panel tabsPageContent">
    <div class="page">
      <div class="pageHeader">
        <html-el:form action="/manager/ItemPropertyManager">
          <html-el:hidden property="method" value="list" />
          <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
          <div class="searchBar">
            <table width="100%" border="0" cellspacing="5" cellpadding="0">
              <tr>
                <td nowrap="nowrap">属性名称：
                  <html-el:text property="p_name" size="10" maxlength="50" styleId="p_name" />
                  &nbsp;&nbsp;属性类型：
                  <html-el:select property="p_type" styleId="p_type" style="width:80px" >
                    <html-el:option value="">全部</html-el:option>
                    <html-el:option value="0">属性1</html-el:option>
                    <html-el:option value="1">属性2</html-el:option>
                  </html-el:select>
                </td>
              </tr>
            </table>
            <div class="subBar">
              <ul>
                <li><a class="button"><span id="btn_submit">立即检索</span></a></li>
              </ul>
            </div>
          </div>
        </html-el:form>
      </div>
       <%@ include file="/commons/pages/messages.jsp" %>
      <div class="pageContent">
        <form id="listForm" name="listForm" method="post" action="ItemPropertyManager.do?method=delete">
          <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
          <c:if test="${not empty isKonkaBaseManager}">
            <div class="panelBar">
              <ul class="toolBar">
                <li><a class="add" onclick="location.href='ItemPropertyManager.do?method=add&mod_id=${af.map.mod_id}';"><span>添加</span></a></li>
                <li class="line">line</li>
                <li> <a class="delete" onclick="document.forms[1].action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(document.forms[1]);"><span>删除</span></a> </li>
              </ul>
            </div>
          </c:if>
          <table class="list" width="100%">
            <thead>
              <tr>
                <th width="30" nowrap="nowrap">
                  <input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" />
                </th>
                <th width="20%" nowrap="nowrap">属性名称</th>
                <th width="15%" nowrap="nowrap">属性类型</th>
                <th width="15%" nowrap="nowrap">添加人</th>
                <th width="15%" nowrap="nowrap">添加时间</th>
                <th width="10%" nowrap="nowrap">排序值</th>
                <c:if test="${not empty isKonkaBaseManager}">
                  <th width="20%" nowrap="nowrap">操作</th>
                </c:if>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="cur" items="${entityList}" varStatus="vs">
                <tr>
                  <td align="center" nowrap="nowrap">
                    <input name="pks" type="checkbox" id="pks_${cur.p_id}" value="${cur.p_id}" />
                  </td>
                  <td align="center">${fn:escapeXml(cur.p_name)}</td>
                  <td align="center" nowrap="nowrap">
                    <c:if test="${cur.p_type eq 0}">属性1</c:if>
                    <c:if test="${cur.p_type eq 1}">属性2</c:if>
                  </td>
                  <td align="center" nowrap="nowrap">${fn:escapeXml(cur.add_user_name)}</td>
                  <td align="center">
                    <fmt:formatDate value="${cur.add_time}" pattern="yyyy-MM-dd" />
                  </td>
                  <td align="center">${cur.order_value }</td>
                  <c:if test="${not empty isKonkaBaseManager}">
                    <td align="center" nowrap="nowrap"> <span style="cursor:pointer; margin-left:7px;" onclick="doNeedMethod(null, 'ItemPropertyManager.do', 'view', 'p_id=${cur.p_id}&' + $('#bottomPageForm').serialize())">查看</span> <span style="cursor:pointer; margin-left:7px;" onclick="doNeedMethod(null, 'ItemPropertyManager.do', 'edit', 'p_id=${cur.p_id}&' + $('#bottomPageForm').serialize())">修改</span> <span style="cursor:pointer; margin-left:7px;" onclick="confirmDelete(null, 'ItemPropertyManager.do', 'p_id=${cur.p_id}&' + $('#bottomPageForm').serialize())">删除</span></td>
                  </c:if>
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
                  <c:if test="${not empty isKonkaBaseManager}">
                    <td>&nbsp;</td>
                  </c:if>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </form>
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="ItemPropertyManager.do">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40">
                <div style="text-align:right; padding-right:5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
					pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
					pager.addHiddenInputs("p_name", "${fn:escapeXml(af.map.p_name)}");
					pager.addHiddenInputs("p_type", "${af.map.p_type}");
		            document.write(pager.toString());
		          </script>
                </div>
              </td>
            </tr>
          </table>
        </form>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.getElementById('af');
	$("#btn_submit").click(function(){
		f.submit();
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>