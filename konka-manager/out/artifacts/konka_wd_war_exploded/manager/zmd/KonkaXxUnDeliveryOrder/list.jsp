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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" style="position:relative;overflow:hidden;">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  <table id="table1" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
    <tr class="tabtt1">
      <td width="5%" align="center">序号</td>
      <td align="center">标题</td>
      <td width="20%" align="center">添加时间</td>
      <td width="8%" align="center">操作</td>
    </tr>
    <c:forEach var="cur" items="${list}" varStatus="vs">
      <tr>
        <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
        <td align="left" nowrap="nowrap"><font class="blue12px">${cur.dist_title}</font></td>
        <td align="center" nowrap="nowrap"><font class="blue12px"><fmt:formatDate value="${cur.dist_date}" pattern="yyyy-MM-dd HH:mm:ss" /></font></td>
        <td align="center" nowrap="nowrap"><img src="${ctx}/images/more.png" alt="" /><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxUnDeliveryOrder.do', 'view','dist_id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span></td>
      </tr>
    </c:forEach>
    <c:forEach begin="${fn:length(list)}" end="${af.map.pager.pageSize - 1}">
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</c:forEach>
  </table>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxUnDeliveryOrder.do">
    <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
          <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
            document.write(pager.toString());
            </script></td>
      </tr>
    </table>
  </form>
  </div>
  <div class="clear"></div>
  <div id="message_tip" style="display:none;">
    <div class="ui-overlay">
  	  <div class="ui-widget-overlay"></div>
  	  <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:25%"></div>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();
	
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
