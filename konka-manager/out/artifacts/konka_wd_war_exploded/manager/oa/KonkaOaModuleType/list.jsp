<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/oa/KonkaOaModuleType">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="50%" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">模板名称：</strong>
            <html-el:text property="module_name_like" styleId="module_name_like" size="20" maxlength="20" />
            &nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input style="align:left" name="button" type="button"  value="新增" class="but2" onclick="location.href='KonkaOaModuleType.do?method=add&mod_id=${af.map.mod_id}';" />
          <input class="but3" type="submit" name="Submit3" value="删除" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaOaModuleType.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
        <tr class="tabtt1">
          <td width="5%" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td width="16%" nowrap="nowrap" align="center">模板名称</td>
          <td width="10%" nowrap="nowrap" align="center">分公司</td>
          <td nowrap="nowrap">模板说明</td>
          <td width="10%" nowrap="nowrap" align="center">排序值</td>
          <td width="20%" nowrap="nowrap" align="center">操作</td>
        </tr>    
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap" valign="top">
                <c:if test="${cur.is_del le 0}"><input name="pks" type="checkbox" id="pks_${cur.module_id}" value="${cur.module_id}" /></c:if>
                <c:if test="${cur.is_del gt 0}"><input name="pks" type="checkbox" id="pks_${cur.module_id}" value="${cur.module_id}" disabled="disabled" /></c:if>
              </td>
              <td nowrap="nowrap" align="left" valign="top" >
              <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaOaModuleType.do', 'view', 'module_id=${cur.module_id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">${fn:escapeXml(cur.module_name)}</span>
              </td>
              <td nowrap="nowrap" align="left" valign="top" >${af.map.dept_name}</td>
              <td align="left" valign="top" title="${cur.module_desc}">
                <c:choose>  
              <c:when test="${fn:length(cur.module_desc) > 29}">  
              <c:out value="${fn:substring(cur.module_desc, 0, 29)}...." />  
              </c:when>  
              <c:otherwise>  
              <c:out value=" ${fn:escapeXml(cur.module_desc)}" />  
              </c:otherwise>  
              </c:choose>  
             </td>
              <td nowrap="nowrap" align="right" valign="top" >${cur.order_value}</td>
              <td nowrap="nowrap" align="center" valign="top">
                <span class="fblue" style="cursor: pointer;" onclick="confirmUpdate(null, 'KonkaOaModuleType.do', 'module_id=${cur.module_id}&' + $('#bottomPageForm').serialize())">修改</span>
                |
                <span class="fblue" style="cursor: pointer;" onclick="confirmDelete(null, 'KonkaOaModuleType.do', 'module_id=${cur.module_id}&' + $('#bottomPageForm').serialize())">删除</span>
              	|
              	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaoaCategory.do', 'add', 'module_id=${cur.module_id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">增加子项</span>
              	|
              	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaoaCategory.do', 'list', 'module_id=${cur.module_id}&mod_id=${af.map.mod_id }&' + $('#bottomPageForm').serialize())">查看子项</span>
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
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaOaModuleType.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
			var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("module_name_like", "${fn:escapeXml(af.map.module_name_like)}");
			pager.addHiddenInputs("module_id", "${af.map.module_id}");
			document.write(pager.toString());
            </script>
           </td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
