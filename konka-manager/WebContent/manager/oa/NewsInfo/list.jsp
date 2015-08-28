<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<c:if test="${af.map.mod_id eq 5000}">
<c:set var="naviString" value="公告管理" />
</c:if>
<c:if test="${af.map.mod_id eq 192}">
<c:set var="naviString" value="下载中心" />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" /><title>${naviString}</title>
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
        <html-el:form action="/manager/NewsInfo">
          <html-el:hidden property="method" value="list" />
          <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
          <div class="searchBar">
            <table width="100%" border="0" cellspacing="5" cellpadding="0">
              <tr>
                <td width="250">文件标题：
                  <html-el:text property="title" size="20" maxlength="20" styleId="title" /></td>
                <td width="300">提交时间：
                  <html-el:text property="st_add_datetime" size="20" maxlength="20" readonly="true" style="cursor:pointer;width:75px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
                  至
                  <html-el:text property="en_add_datetime" size="20" maxlength="20" readonly="true" style="cursor:pointer;width:75px;" onclick="new Calendar(2005, 2030, 0).show(this);" /></td>
                <c:if test="${af.map.mod_id eq 5000}">
                <td>过期时间：
                  <html-el:text property="st_invalid_date" size="20" maxlength="20" readonly="true" style="cursor:pointer;width:75px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
                  至
                  <html-el:text property="en_invalid_date" size="20" maxlength="20" readonly="true" style="cursor:pointer;width:75px;" onclick="new Calendar(2005, 2030, 0).show(this);" /></td>
         </c:if>
                  <td></td>
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
        <form id="listForm" name="listForm" method="post" action="NewsInfo.do?method=list">
          <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <c:if test="${af.map.mod_id eq 5000}">
          <div class="panelBar">
	          <ul class="toolBar">
	              <li><a class="add" onclick="location.href='NewsInfo.do?method=add&mod_id=${af.map.mod_id}';"><span>添加</span></a></li>
	            <li class="line">line</li>
	          </ul>
	      </div>
	  </c:if>
          <table class="list" width="100%">
            <thead>
              <tr>
                <th width="30" nowrap="nowrap">序列</th>
                <th nowrap="nowrap">文件标题</th>
                <th width="120" nowrap="nowrap">提交时间</th>
              <c:if test="${af.map.mod_id eq 5000}">
                <th width="120" nowrap="nowrap">过期时间</th>
                <th width="120" nowrap="nowrap">发布人</th>
              </c:if>
                <th width="70" nowrap="nowrap">操作</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="cur" items="${newsInfoentity}" varStatus="vs">
                <tr>
                  <td align="center" nowrap="nowrap">${vs.count}</td>
                  <td align="left">${fn:escapeXml(cur.title)}</td>
                  <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_datetime}" pattern="yyyy-MM-dd" /></td>
                <c:if test="${af.map.mod_id eq 5000}">
                  <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.invalid_date}" pattern="yyyy-MM-dd" /></td>
                  <td align="left">${cur.map.add_user_name }</td>
                </c:if>
                  <td align="center" nowrap="nowrap">
                  <span style="cursor:pointer; margin-left:7px;" onclick="doNeedMethod(null, 'NewsInfo.do', 'view', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">查看</span>
                <c:if test="${af.map.mod_id eq 5000}">
                  <span style="cursor:pointer; margin-left:7px;" onclick="doNeedMethod(null, 'NewsInfo.do', 'edit', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>
                </c:if>
                </td>
                </tr>
                <c:if test="${vs.last eq true}">
                  <c:set var="i" value="${vs.count}" />
                </c:if>
              </c:forEach>
              <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
                <tr align="center">
                  <c:if test="${af.map.mod_id eq 5000}">
                    <td>&nbsp;</td>
                    <td>&nbsp;</td></c:if>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </form>
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="NewsInfo.do">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40"><div style="text-align:right; padding-right:5px;"> 
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
                  <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
					pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
					pager.addHiddenInputs("title", "${fn:escapeXml(af.map.title)}");
					pager.addHiddenInputs("st_add_datetime", "${af.map.st_add_datetime}");
					pager.addHiddenInputs("en_add_datetime", "${af.map.en_add_datetime}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f = document.getElementById('af');
	$("#btn_submit").click(function(){
		if (f.st_add_datetime.value != "" && f.en_add_datetime.value != "") {
			if (f.en_add_datetime.value < f.st_add_datetime.value) {
				alert("提交时间结束日期不得大于开始日期,请重新选择!")
				return false;
			}
		}
		f.submit();
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>