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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaStoreR3Shop">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">门店名称：</strong>
            <html-el:text property="store_name_like" size="20" maxlength="20" styleId="store_name_like" title="请输入网点名称"/></td>
          <td><strong class="fb">R3编码：</strong>
            <html-el:text property="code_like" size="20" maxlength="20" styleId="code_like"  /></td>
          <td><strong class="fb">关联状态： </strong>
            <html-el:select property="is_match" styleId="is_match" value="${af.map.is_match}">
              <html-el:option value="">请选择</html-el:option>
              <html-el:option value="0">未关联</html-el:option>
              <html-el:option value="1">已关联</html-el:option>
            </html-el:select></td>
          <td><html-el:submit styleClass="but1" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center">序号</td>
          <td width="8%" nowrap="nowrap" width="55">分公司</td>
          <td width="15%" nowrap="nowrap">门店名称</td>
          <td width="8%" nowrap="nowrap">客户R3编码</td>
          <td width="15%" nowrap="nowrap">客户名称</td>
          <td nowrap="nowrap">地址</td>
          <td width="8%" nowrap="nowrap" align="center" width="60">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
            <td align="left" nowrap="nowrap">${cur.dept_name}</td>
             <td align="left" nowrap="nowrap">${cur.store_name} </td>
            <td nowrap="nowrap"><c:if test="${not empty cur.r3_code}">${cur.r3_code}</c:if><c:if test="${empty cur.r3_code}"><span style="color:#f00;">未关联客户</span></c:if></td>
            <td nowrap="nowrap"><c:if test="${not empty cur.map.r3_shop_name}">${cur.map.r3_shop_name}</c:if><c:if test="${empty cur.map.r3_shop_name}"><span style="color:#f00;">未关联客户</span></c:if></td>
            <td nowrap="nowrap"><c:if test="${not empty cur.map.p_name}">${cur.map.p_name}</c:if>
            <c:if test="${empty cur.map.p_name}">${cur.province}${cur.city}${cur.country}${cur.town}</c:if></td>
            <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaStoreR3Shop.do','edit','&store_id=${cur.store_id}&' + $('#bottomPageForm').serialize())">关联管理</span></td>
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
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaStoreR3Shop.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("store_name_like", "${fn:escapeXml(af.map.store_name_like)}");	
			pager.addHiddenInputs("code_like", "${fn:escapeXml(af.map.code_like)}");	
			pager.addHiddenInputs("is_match", "${fn:escapeXml(af.map.is_match)}");	
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
	
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>