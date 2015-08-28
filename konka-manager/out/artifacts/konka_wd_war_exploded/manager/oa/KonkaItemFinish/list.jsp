<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<c:set var="naviString" value="康佳事项完成情况" />
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
        <html-el:form action="/manager/KonkaItemFinish">
          <html-el:hidden property="method" value="list" />
          <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
          <div class="searchBar">
            <table width="100%" border="0" cellspacing="5" cellpadding="0">
              <tr>
                <td nowrap="nowrap">事项：
                  <html-el:text property="item_content" size="20" maxlength="50" styleId="item_content" />
                </td>
                <td> 负责人：
                  <html-el:text property="receive_user_name" styleId="receive_user_name" size="10"  maxlength="50"></html-el:text>
                </td>
                <td> 是否完成：
                  <html-el:select property="is_finished" styleId="is_finished" style="width:100px">
                    <html-el:option value="">全部</html-el:option>
                    <html-el:option value="0">未完成</html-el:option>
                    <html-el:option value="1">已完成</html-el:option>
                  </html-el:select>
                </td>
              </tr>
              <tr>
                <td> 属性1：
                  <html-el:select property="p_type_1" styleId="p_type_1" style="width:150px">
                    <html-el:option value="">请选择...</html-el:option>
                    <c:forEach items="${propertyList1}" var="cur">
                      <html-el:option value="${cur.p_id}">${fn:escapeXml(cur.p_name)}</html-el:option>
                    </c:forEach>
                  </html-el:select>
                </td>
                <td> &nbsp;&nbsp;属性2：
                  <html-el:select property="p_type_2" styleId="p_type_2" style="width:150px">
                    <html-el:option value="">请选择...</html-el:option>
                    <c:forEach items="${propertyList2}" var="cur">
                      <html-el:option value="${cur.p_id}">${fn:escapeXml(cur.p_name)}</html-el:option>
                    </c:forEach>
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
        <form id="listForm" name="listForm" method="post" action="KonkaItemFinish.do?method=delete">
          <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
          <div class="panelBar">
            <ul class="toolBar">
            </ul>
          </div>
          <table class="list" width="100%">
            <thead>
              <tr>
                <th width="5%" nowrap="nowrap">序号</th>
                <th width="15%" nowrap="nowrap">事项</th>
                <th width="10%" nowrap="nowrap">负责人</th>
                <th width="10%" nowrap="nowrap">计划完成时间</th>
                <th width="10%" nowrap="nowrap">计划完成百分比</th>
                <th width="10%" nowrap="nowrap">是否已完成</th>
                <th width="15%" nowrap="nowrap">属性1</th>
                <th width="15%" nowrap="nowrap">属性2</th>
                <th width="10%" nowrap="nowrap">操作</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="cur" items="${entityList}" varStatus="vs">
                <tr>
                  <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
                  <td align="center">${fn:escapeXml(cur.item_content)}</td>
                  <td align="center" nowrap="nowrap">${fn:escapeXml(cur.receive_user_name)}</td>
                  <td align="center">
                    <fmt:formatDate value="${cur.plan_finish_date}" pattern="yyyy-MM-dd" />
                  </td>
                  <td align="center">${cur.plan_finish_rate }
                    <c:if test="${cur.plan_finish_rate ne null}">%</c:if>
                  </td>
                  <td align="center">
                    <c:if test="${cur.is_finished eq 0}">未完成</c:if>
                    <c:if test="${cur.is_finished eq 1}">已完成</c:if>
                  </td>
                  <td align="center">${fn:escapeXml(cur.map.p_type1_name)}</td>
                  <td align="center">${fn:escapeXml(cur.map.p_type2_name)}</td>
                  <td align="center" nowrap="nowrap"> <span style="cursor:pointer; margin-left:7px;" onclick="doNeedMethod(null, 'KonkaItemFinish.do', 'view', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">查看</span>
                    <c:if test="${canModify eq 1 || loginId eq cur.receive_user_id}" var="can_modify"><span style="cursor:pointer; margin-left:7px;" onclick="doNeedMethod(null, 'KonkaItemFinish.do', 'edit', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span></c:if>
                    <c:if test="${not can_modify}"><span style="color:#ccc;">修改</span></c:if>
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
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </form>
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaItemFinish.do">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40">
                <div style="text-align:right; padding-right:5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
					pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
					pager.addHiddenInputs("item_content", "${fn:escapeXml(af.map.item_content)}");
					pager.addHiddenInputs("receive_user_name", "${fn:escapeXml(af.map.receive_user_name)}");
					pager.addHiddenInputs("is_finished", "${af.map.is_finished}");
					pager.addHiddenInputs("p_type_1", "${af.map.p_type_1}");
					pager.addHiddenInputs("p_type_2", "${af.map.p_type_2}");
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