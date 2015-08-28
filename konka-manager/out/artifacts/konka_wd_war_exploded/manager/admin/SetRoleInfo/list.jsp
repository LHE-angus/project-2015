<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>角色权限管理</title>
</head>
<body>
<div id="navTab" class="tabsPage">
  <div class="tabsPageHeader">
    <div class="tabsPageHeaderContent">
      <ul class="navTab-tab">
        <li class="main"><a><span><span class="home_icon">角色权限管理</span></span></a></li>
      </ul>
    </div>
  </div>
  <div class="navTab-panel tabsPageContent">
    <div class="page">
      <html-el:form action="/admin/SetRoleInfo">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" />
        <div class="pageHeader">
          <div class="searchBar">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="250">角色名称：
                  <html-el:text property="role_name_like" size="20" maxlength="20" styleId="role_name_like" /></td>
                <td>角色状态：
                  <html-el:select property="is_del">
                    <html-el:option value="">全部</html-el:option>
                    <html-el:option value="0">未删除</html-el:option>
                    <html-el:option value="1">已删除</html-el:option>
                  </html-el:select></td>
              </tr>
            </table>
            <div class="subBar">
              <ul>
                <li><input type="image" name="imageField" id="imageField" src="${ctx}/images/button_search.gif" /></li>
              </ul>
            </div>
          </div>
        </div>
      </html-el:form>
      <%@ include file="/commons/pages/messages.jsp" %>
      <div class="pageContent">
        <form id="listForm" name="listForm" method="post" action="SetRoleInfo.do?method=delete&mod_id=${af.map.mod_id}">
          <div class="panelBar">
            <ul class="toolBar">
              <li><a class="add" href="#" onclick="location.href='SetRoleInfo.do?method=add&mod_id=${af.map.mod_id}';return false;"><span>添加</span></a> </li>
              <li class="line">line</li>
              <li><a class="delete" onclick="confirmDeleteAll(document.getElementById('listForm'));"><span>删除</span></a></li>
            </ul>
          </div>
          <table class="list" width="100%">
            <thead>
              <tr>
                <th width="25" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
                <th nowrap="nowrap">角色名称</th>
                <th width="10%" nowrap="nowrap">排序值</th>
                <th width="15%" nowrap="nowrap">角色状态</th>
                <th width="150" nowrap="nowrap">操作</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="cur" items="${entityList}" varStatus="vs">
                <c:set var="is_del" value="true" />
                <c:if test="${cur.is_lock eq 1 or cur.is_del eq 1}">
                  <c:set var="is_del" value="false" />
                </c:if>
                <tr>
                  <td align="center"><c:if test="${is_del}">
                      <input name="pks" type="checkbox" id="pks" value="${cur.id}" />
                    </c:if>
                    <c:if test="${not is_del}">
                      <input name="pks" type="checkbox" id="pks" value="${cur.id}" disabled="disabled" />
                    </c:if></td>
                  <td align="left" nowrap="nowrap">${fn:escapeXml(cur.role_name)}</td>
                  <td align="right" nowrap="nowrap">${cur.order_value}</td>
                  <td align="center" nowrap="nowrap"><c:out value="${cur.is_del eq 0 ? '未删除' : '已删除'}" /></td>
                  <td align="center"><span style="cursor:pointer;" class="fblue" onclick="location.href='SetRoleInfo.do?method=view&amp;mod_id=${af.map.mod_id}&amp;role_id=${cur.id}&' + $('#bottomPageForm').serialize();">用户</span>
                  |
                  <span style="cursor:pointer; margin-left:7px;" class="fblue" onclick="location.href='SetModPopedom.do?method=edit&mod_id=${af.map.mod_id}&role_id=${cur.id}&url=SetRoleInfo';">授权</span>
                  |
                  <c:if test="${is_del}"><span style="cursor:pointer; margin-left:7px;" class="fblue" onclick="confirmUpdate(null, 'SetRoleInfo.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改  | </span></c:if>
                  <c:if test="${not is_del}"><span class="fblue" style="color:#ccc; margin-left:7px;">修改 | </span></c:if>
                    <c:if test="${is_del}"><span class="fblue" style="cursor:pointer; margin-left:7px;" onclick="confirmDelete(null, 'SetRoleInfo.do', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span></c:if>
                    <c:if test="${not is_del}"><span class="fblue" style="color:#ccc; margin-left:7px;">删除</span></c:if></td>
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
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </form>
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="SetRoleInfo.do">
          <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td height="10" align="center"><div style="text-align:right; padding-right:5px;"> 
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
                  <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("role_name_like", "${fn:escapeXml(af.map.role_name_like)}");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("is_lock", "${af.map.is_lock}");
				pager.addHiddenInputs("is_del", "${af.map.is_del}");				
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
