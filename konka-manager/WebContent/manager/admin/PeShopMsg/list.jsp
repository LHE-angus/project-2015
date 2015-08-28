<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/manager/admin/PeShopMsg/shop_msg_top.jsp" %>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="PeShopMsg.do?method=delete">
      <div style="text-align: left">
        <input type="hidden" name="method" id="method" value="delete" />
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
        <input type="hidden" name="tag_id" id="tag_id" value="${tag_id}" />
        <input type="hidden" name="receive_user_type" id="receive_user_type" value="${af.map.receive_user_type}" />
        <input type="hidden" name="public_target" id="public_target" value="${public_target}" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr class="tabtt1">
            <td width="50" nowrap="nowrap" align="center" >序号</td>
            <td width="20%" nowrap="nowrap" >发件人</td>
            <td nowrap="nowrap" >标题</td>
            <td width="140" nowrap="nowrap" align="center" >发送时间</td>
            <td width="90"  nowrap="nowrap" align="center" >操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">${vs.count }</td>
              <td align="left" nowrap="nowrap"> ${fn:escapeXml(cur.send_user_name)} </td>
              <td align="left" nowrap="nowrap"> ${fn:escapeXml(cur.title)} </td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.send_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              <td align="center" ><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'PeShopMsg.do', 'viewReceive', 'id=${cur.id}&tag_id=${tag_id}&mod_id=${af.map.mod_id}&receive_user_type=${receive_user_type}&public_target=${public_target}&'+$('#bottomPageForm').serialize())">查看</span>
              |&nbsp;<a style="text-decoration: none"  class="fblue"  href="PeShopMsg.do?method=reply&id=${cur.id}&receive_user_id=${cur.send_user_id}&receive_user_type=${receive_user_type}&mod_id=${af.map.mod_id}&public_target=${public_target}">回复</a></td>  
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
      </div>
    </form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PeShopMsg.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
			   var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			   pager.addHiddenInputs("method", "list");
			   pager.addHiddenInputs("tag_id", '${tag_id}');
			   pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			   pager.addHiddenInputs("tree_param", "${tree_param}");
			   pager.addHiddenInputs("receive_user_type", "${af.map.receive_user_type}");
			   pager.addHiddenInputs("public_target", "${af.map.public_target}");
			   document.write(pager.toString());
			   </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
