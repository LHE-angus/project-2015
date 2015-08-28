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
    <html-el:form action="/admin/KonkaStock">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">R3编码　：</strong>
            <html-el:text property="r3_code_like" size="15" maxlength="20" styleId="r3_code_like"  /></td>
          <td><strong class="fb">客户名称：</strong>
            <html-el:text property="customer_name_like" size="15" maxlength="20" styleId="customer_name_like"  /></td>
          <td><strong class="fb">经办名称：</strong>
            <html-el:text property="handle_name_like" size="20" maxlength="20" styleId="handle_name_like"  /></td>
        </tr>
        <tr>
          <td width="15"></td>
          <td><strong class="fb">上报状态：</strong>
            <html-el:select property="stock_type">
              <html-el:option value="">请选择上报状态</html-el:option>
              <html-el:option value="1">未初始化库存</html-el:option>
              <html-el:option value="0">已初始化库存</html-el:option>
            </html-el:select></td>
          <td> <html-el:submit styleClass="but1" value="搜索" /></td>
          <td> </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <form id="listForm" name="listForm" method="post" action="KonkaStock.do?method=delete">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="30" align="center">序号</td>
          <td nowrap="nowrap" width="60">R3编码</td>
          <td nowrap="nowrap">网点名称</td>
          <td>网点分配</td>
          <td nowrap="nowrap" align="center" width="50">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.r3_code)}</td>
            <td align="left" title="分公司:${fn:escapeXml(cur.branch_area_name)}"><a style="cursor:pointer;color:blue;" href="KonkaR3MmtMatch.do?method=detail&id=${cur.id}">${cur.customer_name}</a></td>
            <td nowrap="nowrap"><%@ include file="../_inc/view_fgs_jyb_bsc_ywy_name.jsp" %></td>
            <td align="center"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaStock.do','edit' ,'r3_shop_id=${cur.id}&' + $('#bottomPageForm').serialize())">数据上报</span></td>
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
      </table>
    </form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaStock.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("r3_code_like", "${fn:escapeXml(af.map.r3_code_like)}");	
			pager.addHiddenInputs("area_name_like", "${fn:escapeXml(af.map.area_name_like)}");	
			pager.addHiddenInputs("handle_name_like", "${fn:escapeXml(af.map.handle_name_like)}");	
			pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.customer_name_like)}");	
			pager.addHiddenInputs("stock_type", "${af.map.stock_type}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
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