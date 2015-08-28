<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<c:set var="naviString" value="康佳事项维护" />
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
        <html-el:form action="/manager/KonkaItemManager">
          <html-el:hidden property="method" value="list" />
          <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
          <div class="searchBar">
            <table width="100%" border="0" cellspacing="5" cellpadding="0">
              <tr>
                <td style="width: 30%">事项：
                  <html-el:text property="item_content_like" size="20" maxlength="20" styleId="item_content_like" />
                </td>
                <td style="width: 30%">负责人：
                  <html-el:text property="receive_user_name_like" size="10" maxlength="10" styleId="receive_user_name_like" />
                </td>
                <td>计划完成时间：
                  <html-el:text property="st_plan_finish_date" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:65px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
                  至
                  <html-el:text property="en_plan_finish_date" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:65px;" onclick="new Calendar(2005, 2030, 0).show(this);" />
                </td>
              </tr>
              <tr>
                <td>属性1：
                  <html-el:select property="p_type_1" style="width:70%">
                    <html-el:option value="">请选择...</html-el:option>
                    <c:forEach items="${propertyList}" var="p_cur">
                      <c:if test="${p_cur.p_type eq 0}">
                        <html-el:option value="${p_cur.p_id}">${p_cur.p_name}</html-el:option>
                      </c:if>
                    </c:forEach>
                  </html-el:select>
                </td>
                <td>属性2：
                  <html-el:select property="p_type_2" style="width:70%">
                    <html-el:option value="">请选择...</html-el:option>
                    <c:forEach items="${propertyList}" var="p_cur">
                      <c:if test="${p_cur.p_type eq 1}">
                        <html-el:option value="${p_cur.p_id}">${p_cur.p_name}</html-el:option>
                      </c:if>
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
        <form id="listForm" name="listForm" method="post" action="KonkaItemManager.do?method=delete">
          <input type="hidden" name="method" id="method" value="delete" />
          <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
          <c:if test="${not empty isKonkaBaseManager}">
            <div class="panelBar">
              <ul class="toolBar">
                <li><a class="add" onclick="doNeedMethod(null,'KonkaItemManager.do','add','mod_id=${af.map.mod_id}');"><span>添加</span></a></li>
                <li class="line">line</li>
                <li><a class="delete" onclick="confirmDeleteAll(document.getElementById('listForm'));"><span>删除</span></a></li>
              </ul>
            </div>
          </c:if>
          <table class="list" width="100%">
            <tr>
              <th width="5%" nowrap="nowrap">
                <input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" />
              </th>
              <th nowrap="nowrap">事项</th>
              <th width="10%" nowrap="nowrap">负责人</th>
              <th width="12%"nowrap="nowrap">计划完成时间</th>
              <th width="15%" nowrap="nowrap">属性1</th>
              <th width="15%" nowrap="nowrap">属性2</th>
              <th width="8%" nowrap="nowrap">排序值</th>
              <c:if test="${not empty isKonkaBaseManager}">
                <th width="10%" nowrap="nowrap">操作</th>
              </c:if>
            </tr>
            <c:forEach var="cur" items="${entityList}" varStatus="vs">
              <tr>
                <td align="center" nowrap="nowrap">
                  <input name="pks" type="checkbox" id="pks_${cur.id}" value="${cur.id}" />
                </td>
                <td align="left">${fn:escapeXml(cur.item_content)}</td>
                <td align="center">${fn:escapeXml(cur.receive_user_name)}</td>
                <td align="center" nowrap="nowrap">
                  <fmt:formatDate value="${cur.plan_finish_date}" pattern="yyyy-MM-dd" />
                </td>
                <td align="left">
                  <c:forEach items="${propertyList}" var="p_cur">
                    <c:if test="${p_cur.p_id eq cur.p_type_1}">${fn:escapeXml(p_cur.p_name)}</c:if>
                  </c:forEach>
                </td>
                <td align="left">
                  <c:forEach items="${propertyList}" var="p_cur">
                    <c:if test="${p_cur.p_id eq cur.p_type_2}">${fn:escapeXml(p_cur.p_name)}</c:if>
                  </c:forEach>
                </td>
                <td align="center" nowrap="nowrap">${cur.order_value}</td>
                <c:if test="${not empty isKonkaBaseManager}">
                  <td align="center" nowrap="nowrap"> <span style="cursor:pointer; margin-left:7px;" onclick="doNeedMethod(null, 'KonkaItemManager.do', 'edit', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>| <span style="cursor:pointer; margin-right:7px;" onclick="doNeedMethod(null, 'KonkaItemManager.do', 'delete', 'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span></td>
                </c:if>
              </tr>
              <c:if test="${vs.last eq true}">
                <c:set var="i" value="${vs.count}" />
              </c:if>
            </c:forEach>
            <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
              <tr align="center">
                <c:if test="${not empty isKonkaBaseManager}">
                  <td>&nbsp;</td>
                </c:if>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </c:forEach>
          </table>
        </form>
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaItemManager.do">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40">
                <div style="text-align:right; padding-right:5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
					pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
					pager.addHiddenInputs("item_content_like", "${fn:escapeXml(af.map.item_content_like)}");
					pager.addHiddenInputs("receive_user_name_like", "${fn:escapeXml(af.map.receive_user_name_like)}");					
					pager.addHiddenInputs("st_plan_finish_date", "${af.map.st_plan_finish_date}");
					pager.addHiddenInputs("en_plan_finish_date", "${af.map.en_plan_finish_date}");
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
		if (f.st_plan_finish_date.value != "" && f.en_plan_finish_date.value != "") {
			if (f.en_plan_finish_date.value < f.st_plan_finish_date.value) {
				alert("时间结束日期不得大于开始日期,请重新选择!");
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