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
	    <input class="but2" type="button" name="Submit2" value="新增" onclick="location.href='KonkaParagonShowimg.do?method=add&mod_id=${af.map.mod_id}&show_shop_code=${af.map.show_shop_code}&';" />
	    <input class="but3" type="button" name="Submit3" value="删除" onclick="confirmDeleteAll(document.getElementById('listForm'));" />
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaParagonShowimg.do?method=delete">
      <div style="text-align: left">
        <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
        <input type="hidden" name="show_shop_code" id="show_shop_code" value="${af.map.show_shop_code}" />
      </div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="25" align="center" ><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
          <td width="30" align="center" >序号</td>
          <td nowrap="nowrap" align="center" >形象图片</td>
          <td width="70" align="center" >门店代码</td>
          <td width="70" nowrap="nowrap" align="center" >添加时间</td>
          <td width="90" nowrap="nowrap" align="center" >操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.img_id}" />
            </td>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align=left nowrap="nowrap">
				<a href="${ctx}/${cur.img_path}" target="_blank"><img src="${ctx}/${cur.img_path}" alt="" width="220" height="80" /></a>
			</td>
            <td align="center" nowrap="nowrap">${af.map.show_shop_code}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.addtime}" pattern="yyyy-MM-dd" /></td>
            <td align="center">
	            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaParagonShowimg.do', 'edit','img_id=${cur.img_id}&mod_id=${af.map.mod_id}&show_shop_code=${af.map.show_shop_code}&'+$('#bottomPageForm').serialize())">修改</span>
	            |
	            <span style="cursor:pointer;" class="fblue" onclick="confirmDelete('确定删除吗?', 'KonkaParagonShowimg.do', 'img_id=${cur.img_id}&mod_id=${af.map.mod_id}&show_shop_code=${af.map.show_shop_code}&'+$('#bottomPageForm').serialize())">删除</span> 
	         </td>
          </tr>
        </c:forEach>
      </table>
    </form>
    <br />
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
