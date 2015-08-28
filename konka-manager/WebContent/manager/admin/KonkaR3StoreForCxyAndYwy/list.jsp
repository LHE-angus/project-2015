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
        <td nowrap="nowrap">当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <h1 align="center" style="padding-top: 20px;font-size: 22px;">出样门店与负责人对照表</h1>    
  <div class="rtabcont1" style="overflow:auto;height:700px;">    
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">         
            <td width="5%" nowrap="nowrap" align="center" ><font class="blue">序号</font></td>
            <td width="10%"  align="center" nowrap="nowrap"><font class="blue">分公司</font></td>
            <td align="center" width="170"  nowrap="nowrap" ><font class="blue">门店</font></td>
            <td  align="center" nowrap="nowrap"><font class="blue">直销员</font></td>
            <td  align="center" nowrap="nowrap"><font class="blue">直销员手机</font></td>
            <td  align="center" nowrap="nowrap" ><font class="blue">业务员</font></td>
            <td  align="center" nowrap="nowrap"><font class="blue">业务员手机</font></td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
              <td align="center">${cur.map.dept_name}</td>
              <td width="170" align="center">${cur.map.store_name} </td>
              <td width="170" align="center">${cur.map.cxy_name} </td>
              <td width="170" align="center">${cur.map.cxy_phone} </td>
              <td width="170" align="center">${cur.map.ywy_name} </td>
              <td width="170" align="center">${cur.map.ywy_phone} </td>     
            </tr>
          </c:forEach>
        </tbody>
      </table>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
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