<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>基础数据管理&gt; 仓库信息</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：基础数据管理&gt; 仓库信息</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcStoreInfo.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td align="left" nowrap="nowrap"><strong class="fb">&nbsp;&nbsp;仓库名称：</strong>
            <html-el:text property="store_name" styleClass="webinput" styleId="store_name"/>
            &nbsp; <strong class="fb">仓库地址：</strong>
            <html-el:text property="store_addr" styleClass="webinput" styleId="store_addr" />
            &nbsp; <strong class="fb">负责人：</strong>
            <html-el:text property="link_man" styleClass="webinput" styleId="link_man" />
            &nbsp;
            <input class="bgSearch" type="submit" name="Submit" value="搜 索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
   <form id="listForm" name="listForm" method="post" action="KonkaJxcStoreInfo.do?method=delete">
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <input class="bgButtonReset" type="button" name="delete" id="delete" value="回收站" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />
    <input class="bgButtonAdd" type="submit" name="Submit2" value="新 增" onclick="location.href='KonkaJxcStoreInfo.do?method=add&mod_id=${af.map.mod_id}';return false;" />
  </div>
 <div class="rtabcont1">
   <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th width="5%"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>
          <th width="20%" nowrap="nowrap">仓库名称</th>
          <th width="20%" nowrap="nowrap">仓库地址</th>
          <th width="20%" nowrap="nowrap">负责人</th>
          <th width="10%" nowrap="nowrap">联系电话</th>
          <th width="10%" nowrap="nowrap">添加时间</th>
          <th width="10%" nowrap="nowrap">操作</th>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td height="28"  align="center"><input name="pks" type="checkbox" id="pks" value="${cur.id}" /></td>
            <td align="center"><a class="fgray" href="KonkaJxcStoreInfo.do?method=view&id=${cur.id}&mod_id=${af.map.mod_id}">${fn:escapeXml(cur.store_name)}</a></td>
            <td align="center" valign="middle">${fn:escapeXml(cur.store_addr)}</td>
            <td align="center">${fn:escapeXml(cur.link_man)}</td>
            <td align="center">${cur.link_tel}</td>
            <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" var="_add_datetime" />
              ${_add_datetime}</td>
            <td align="center" nowrap="nowrap"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcStoreInfo.do', 'edit','id=${cur.id}&'+$('#bottomPageForm').serialize())">修改</span>
            |
            <span style="cursor:pointer;"  class="fblue"  onclick="confirmDelete(null, 'KonkaJxcStoreInfo.do','id=${cur.id}&'+$('#bottomPageForm').serialize())">删除</span></td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
      </table>
      </div>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcStoreInfo.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("store_name", "${fn:escapeXml(af.map.store_name)}");
	            pager.addHiddenInputs("store_addr", "${fn:escapeXml(af.map.store_addr)}");
	            pager.addHiddenInputs("link_man", "${fn:escapeXml(af.map.link_man)}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="rtabcont3"></div>
  <div class="clear"></div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>