<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style>
.datatable tr:hover, .datatable tr.hilite {
	background-color: #DFE7F2;
	color: #000000;
}
.bg_g{
	background-color: #97FFFF;
}
.bg_c{
	background-color: #EE9A49;
}
.bg_y{
	background-color: #FFEC8B;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <html-el:form action="/admin/KonkaR3DeptStockInfoToFgsTop">
    <html-el:hidden property="method" value="detail" />
    <html-el:hidden property="mod_id" styleId="mod_id" />
    <html-el:hidden property="v_class" styleId="v_class" />
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">
           &nbsp;<strong class="fb">型号：</strong>
           	<html-el:text property="matnr_like" styleId="matnr_like" />
           &nbsp;&nbsp;<strong class="fb">年月：</strong>
            <html-el:select property="year" styleId="year">
              <c:forEach items="${yearList}" var="cur">
                <html-el:option value="${cur}">${cur}年</html-el:option>
              </c:forEach>
            </html-el:select>
            <html-el:select property="month" styleId="month">
              <html-el:option value="01">1月</html-el:option>
              <html-el:option value="02">2月</html-el:option>
              <html-el:option value="03">3月</html-el:option>
              <html-el:option value="04">4月</html-el:option>
              <html-el:option value="05">5月</html-el:option>
              <html-el:option value="06">6月</html-el:option>
              <html-el:option value="07">7月</html-el:option>
              <html-el:option value="08">8月</html-el:option>
              <html-el:option value="09">9月</html-el:option>
              <html-el:option value="10">10月</html-el:option>
              <html-el:option value="11">11月</html-el:option>
              <html-el:option value="12">12月</html-el:option>
            </html-el:select>
            &nbsp;&nbsp;&nbsp;
            <input name="button" type="submit" class="bgSearch" id="sear_btn" value="搜 索" />
            &nbsp;<input name="but_exl" type="button"  id="but_exl" onclick="history.back();return false;" class="but5" value="返回" /></td>
        </tr>
      </table>
    </div>
  </html-el:form>
  <h1 align="center" style="padding-top: 20px;font-size: 22px;">${af.map.year}年${af.map.month}月份${v_class}平板销售评估表</h1>
  <div><span style="color: red;float:left; padding-left: 10px;">数据最新同步时间：<fmt:formatDate value="${max_date}" pattern="yyyy-MM-dd HH:mm:ss" /></span><span style="color: red;float:right; padding-right: 10px;" >单位:台</span></div>
  <div class="rtabcont1" style="overflow-x: auto;">
    <table class="rtable6 datatable" width="100%" cellspacing="1" cellpadding="0" border="0">
      <tr >
      	<td height="20" width="7%" nowrap="nowrap" align="center" class="bg_g">片区代码</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">分公司</td>
        <td nowrap="nowrap" align="center" class="bg_g">机型</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">铺底60</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">铺底P仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_c">周转90仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">商务仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">经营部F仓</td>
        
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">样机Y仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">质检Q仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">退机T仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">周转Z仓</td>
        
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">电子商务仓</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">在途</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">未发</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">总量</td>
        
        <td width="7%" nowrap="nowrap" align="center" class="bg_g">上月销量</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_c">本月销量</td>
        <td width="7%" nowrap="nowrap" align="center" class="bg_y">周转率%</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td nowrap="nowrap" align="left">${cur.bzirk}</td>
          <td nowrap="nowrap" align="left">${cur.class1}</td>
          <td nowrap="nowrap" align="left">${cur.matnr}</td>
          
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.pdlabst}" pattern="###,##0" /></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.pclabst}" pattern="###,##0" /></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.zzlabst}" pattern="###,##0" /></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.bclabst}" pattern="###,##0" /></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.jylabst}" pattern="###,##0" /></td>
          
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.yjlabst}" pattern="###,##0" /></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.cllabst}" pattern="###,##0" /></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.tjlabst}" pattern="###,##0" /></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.zclabst}" pattern="###,##0" /></td>
          
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.dzlabst}" pattern="###,##0" /></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.mzt}" pattern="###,##0" /></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.mwf}" pattern="###,##0" /></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.sum_}" pattern="###,##0" /></td>
          
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.lfimg}" pattern="###,##0" /></td>
          <td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.lfimg1}" pattern="###,##0" /></td>
          <td nowrap="nowrap" align="right" class="bg_y"><fmt:formatNumber value="${cur.map.zzl}" pattern="###,##0.00" /></td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[ 	
$(document).ready(function(){
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>