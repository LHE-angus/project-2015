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
<link href="${ctx}/commons/styles/EntpShopSearch_style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/EntpShopGSite/map.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString} &nbsp;${p_name}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" />
  </div>
  <div class="rtabcont1">
    <table class="rtable2" width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
      <tr>
        <td align="center" nowrap="nowrap" width="13%">截至${af.map.sel_year}年${af.map.sel_month}月</td>
        <td align="center">R3销售量（台）</td>
        <td align="center" nowrap="nowrap" width="12%">总销售量（台）</td>
        <td align="center" nowrap="nowrap">总销售额（元）</td>
      </tr>
      <c:forEach var="cur" items="${town_list}" varStatus="vs">
        <tr>
          <td align="center" bgcolor="#FFFFFF">${cur.p_name}</td>
          <c:forEach var="sub_cur" items="${cur.map.shopTypeXL_list}" varStatus="sub_vs">
            <td align="center" bgcolor="#FFFFFF"><fmt:formatNumber value="${sub_cur}" pattern="0" /></td>
          </c:forEach>
          <td align="center" bgcolor="#FFFFFF"><fmt:formatNumber value="${cur.map.r3_count}" pattern="0" /></td>
          <td align="center" bgcolor="#FFFFFF"><fmt:formatNumber value="${cur.map.sum_xl}" pattern="0" /></td>
          <td align="center" bgcolor="#FFFFFF"><fmt:formatNumber value="${cur.map.sum_xe}" pattern="0" /></td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <div class="rtabcont3"></div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.pager.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
   
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>