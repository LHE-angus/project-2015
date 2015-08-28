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
      <%@ include file="/commons/pages/messages.jsp" %>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td width="30%" align="center" nowrap="nowrap">分公司</td>
          <td width="40%" align="center" nowrap="nowrap">标题</td>
          <td nowrap="nowrap" align="center">操作</td>
        </tr>
          <tr>
            <td align="center" nowrap="nowrap">1</td>
            <td align="left">${entity.dept_name}</td>
            <td align="left">导入${entity.dept_name}分公司部门信息</td>
            <td align="center">
            	<c:if test="${entity.map.kmid_count eq 0}"><a href="${ctx}/manager/admin/KonkaDataInit.do?method=addBatch&mod_id=${af.map.mod_id}&dept_id=${entity.dept_id}&type=dept">进入</a></c:if>
            	<c:if test="${entity.map.kmid_count ne 0}"><span style="color:#ccc;" title="已经有数据！">进入</span></c:if>
            </td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">2</td>
            <td align="left">${entity.dept_name}</td>
            <td align="left">导入${entity.dept_name}分公司门店信息</td>
            <td align="center">
            	<c:if test="${entity.map.kmis_count eq 0}"><a href="${ctx}/manager/admin/KonkaDataInit.do?method=addBatch&mod_id=${af.map.mod_id}&dept_id=${entity.dept_id}&type=store">进入</a></c:if>
            	<c:if test="${entity.map.kmis_count ne 0}"><span style="color:#ccc;" title="已经有数据！">进入</span></c:if>
            </td>
          </tr>
          <tr>
            <td align="center" nowrap="nowrap">3</td>
            <td align="left">${entity.dept_name}</td>
            <td align="left">导入${entity.dept_name}分公司人员信息</td>
            <td align="center">
            	<c:if test="${entity.map.kmiu_count eq 0}"><a href="${ctx}/manager/admin/KonkaDataInit.do?method=addBatch&mod_id=${af.map.mod_id}&dept_id=${entity.dept_id}&type=user">进入</a></c:if>
            	<c:if test="${entity.map.kmiu_count ne 0}"><span style="color:#ccc;" title="已经有数据！">进入</span></c:if>
            </td>
          </tr>
      </table>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
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