<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <form id="listForm" name="listForm" method="post" action="">
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
    </div>
    <div class="rtabcont1" id="divExcel" title="${shopName}${start_date}至${end_date}发货来源详细表.xls">
      <div>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
          <tr>
            <th colspan=3>${dept_name}发货数量总数:<font color="red">${pd_fh_num}</font></th>
            <th colspan=3>${shopName}进货数量总数:<font color="red">${tatolCount}</font></th>
          </tr>
        </table>
      </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan=6><b>${shopName}&nbsp;&nbsp;${start_date}至${end_date}收发货详细对比表</b></th>
        </tr>
        <tr>
          <th width="5%" nowrap="nowrap">序号</th>
          <th nowrap="nowrap">产品型号</th>
          <th width="12%" nowrap="nowrap">产品大类</th>
          <th width="12%" nowrap="nowrap">产品品牌</th>
          <th width="10%" nowrap="nowrap">产品数量</th>
          <th width="8%" nowrap="nowrap">进货来源</th>
        </tr>
        <c:forEach items="${entityList}" var="cur" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_name)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_type_name)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.brand_name)}</td>
            <td align="center" nowrap="nowrap">${cur.count}</td>
            <td align="center" nowrap="nowrap"><!-- 进货来源 1：进货 2：收货确认 3：分销确认 -->
              
              <c:if test="${cur.is_pc eq 1}">网点进货</c:if>
              <c:if test="${cur.is_pc eq 2}">网点收货</c:if>
              <c:if test="${cur.is_pc eq 3}">代理商分销</c:if></td>
          </tr>
        </c:forEach>
        <tr>
          <td colspan="4" align="center" nowrap="nowrap">进货数量合计</td>
          <td align="center" nowrap="nowrap">${tatolCount}</td>
          <td></td>
        </tr>
      </table>
    </div>
  </form>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-left:5px;padding-top:10px">
    <tr>
      <td height="26" align="center"><div class="left">
          <input name="button" type="button" class="bgButtonExport" id="toExcel" value="导出" onclick="toExcel('divExcel', '?method=toExcel');"/>
          &nbsp;
          <input name="button" type="button" class="bgButtonBack" id="button" value="返回" onclick="history.back();"/>
        </div></td>
    </tr>
  </table>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});
            
 
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>