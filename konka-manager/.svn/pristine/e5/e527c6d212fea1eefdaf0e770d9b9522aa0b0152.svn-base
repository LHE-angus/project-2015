<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${naviString}</title>
<!--[if IE]>
<link href="${ctx}"}/styles/manager/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
<style>
.trClass {cursor:pointer;}
.trClass td:hover { background-color:#eeeeee;}
</style>
</head>
<body>
<div id="navTab" class="tabsPage" style="text-align:left;padding-left:5px;">
  <div class="navTab-panel tabsPageContent">
    <div class="page">
		<html-el:form action="/admin/PdDeptManager">
		<html-el:hidden property="method" value="searchPeDept" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <%@ include file="/commons/pages/messages.jsp" %>
      <div class="pageHeader">
        <div class="searchBar">
            <table width="100%" border="0" cellspacing="5" cellpadding="0">
               <tr>
                <td>
                	部门名称：
                  	<input type="text" name="dept_name_like" maxlength="64" size="20" id="dept_name_like" />
                </td>
              </tr>
            </table>
            <div class="subBar">
              <ul>
                <li><input class="but1"
							type="submit" name="Submit" value="搜索" />
						</li>
              </ul>
            </div>
          </div>
      </div>
      </html-el:form>
      <div class="pageContent">
        <form id="listForm" name="listForm" method="post" action="PdDeptManager.do?method=searchPeDept">
        <input type="hidden" name="method" id="method" value="searchPeDept" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
        <div class="panelBar">
          <ul class="toolBar">
          </ul>
        </div>
        <table class="list" width="100%">
          <thead>
            <tr>
              <th width="30px" align="center" nowrap="nowrap">序号</th>
              <th nowrap="nowrap" align="center" width="200px">部门名称</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="cur" items="${entityList}" varStatus="vs">
              <tr onclick="doSelectDept('${cur.dept_id}');" class="trClass">
                <td align="center" nowrap="nowrap">${vs.count}
                	<html-el:hidden property="dept_name_${cur.dept_id}" styleId="dept_name_${cur.dept_id}" value="${cur.dept_name}" />
                </td>
                <td align="center" nowrap="nowrap">${cur.dept_name}</td>
              </tr>
              <c:if test="${vs.last eq true}">
                <c:set var="i" value="${vs.count}" />
              </c:if>
            </c:forEach>
            <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
              <tr align="center">
            	<c:forEach begin="1" end="3">
                <td>&nbsp;</td>
                </c:forEach>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        </form>
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="PdDeptManager.do">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40"><div style="text-align:right; padding-right:5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "searchPeDept");
			    	pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			   		pager.addHiddenInputs("dept_name_like", "${fn:escapeXml(af.map.dept_name_like)}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">
	//<![CDATA[
	function doSelectDept(dept_id) {
		var name = $("#dept_name_" +dept_id).val();
		var dept = new ShopInfo(id, name);
		window.parent.initShopInfo(dept);
	}
	
	function ShopInfo(id, name){
		this.dept_id = id;
		this.dept_name = name;
	}
	//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>