<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width: 100%;">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcPcInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableTop">
        <tr>
          <td width="80%" nowrap="nowrap"><strong class="fb">产品大类：</strong>
            <html-el:select property="cls_id" styleId="cls_id">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${pdClazzList}">
                <html-el:option value="${cur.cls_id}">${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp; <strong class="fb">所属仓库：</strong>
            <html-el:select property="store_id" styleId="store_id">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${storeList}">
                <html-el:option value="${cur.id}">${fn:escapeXml(cur.store_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp;
            <input class="bgSearch" type="submit" name="Submit" value="搜 索" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="KonkaJxcPcInfo.do?method=delete">

  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <input class="bgButtonAdd" type="button" name="Submit2" value="新 增 " onclick="location.href='KonkaJxcPcInfo.do?method=add&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize();" />
  </div>
  
    
      
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" />
      <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tableClass">
        <tr>
          <th width="4%" align="center" nowrap="nowrap">序号</th>
          <th width="16%" nowrap="nowrap" align="center">产品大类</th>
          <th nowrap="nowrap" align="center">产品型号</th>
          <th width="8%" nowrap="nowrap" align="center">盘存数量</th>
          <th width="15%" nowrap="nowrap" align="center">盘存时间</th>
          <th width="15%" nowrap="nowrap" align="center">添加时间</th>
          <th width="12%" nowrap="nowrap" align="center">操作</th>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="left">${fn:escapeXml(cur.pd_type_name)}</td>
            <td align="left">${fn:escapeXml(cur.pd_name)}</td>
            <td align="left">${cur.pc_num}</td>
            <td align="center">
              <fmt:formatDate value="${cur.pc_date}" pattern="yyyy-MM-dd" />
            </td>
            <td align="center">
              <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" />
            </td>
            <td align="center" nowrap="nowrap"><span style="cursor: pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcPcInfo.do', 'view','id=${cur.id}&'+$('#bottomPageForm').serialize())">查看</span></td>
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
          </tr>
        </c:forEach>
      </table>
      </div>
    </form>
  
    <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcPcInfo.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" >
        <tr>
          <td height="40" align="right">
            <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
			            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("cls_id", "${af.map.cls_id}");
			            pager.addHiddenInputs("store_id", "${af.map.store_id}");
			            document.write(pager.toString());
			   </script>
          </td>
        </tr>
      </table>
    </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
