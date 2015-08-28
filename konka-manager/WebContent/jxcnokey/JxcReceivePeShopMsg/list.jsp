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
        <td width="20"><img src="${ctx}/images/manager/arrow3.gif" style="vertical-align:middle;" /></td>
        <td>当前位置：&nbsp;&gt;&nbsp;信息接收&nbsp;&gt;&nbsp;站内信息&nbsp;&gt;&nbsp;收件箱</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/jxcnokey/JxcReceivePeShopMsg/shop_msg_top.jsp" %>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/JxcReceivePeShopMsg">
      <div style="text-align: left">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" />
        <html-el:hidden property="keySeq" />
        <html-el:hidden property="infoType" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr class="tabtt1">
            <td width="5%" align="center" >序号</td>
            <td nowrap="nowrap" >标题</td>
            <td width="20%"  nowrap="nowrap" >发件人</td>
            <td width="15%" nowrap="nowrap" align="center">发送时间</td>
            <td width="10%"  nowrap="nowrap" align="center">操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">${vs.count}</td>
              <td align="left" nowrap="nowrap"> ${fn:escapeXml(cur.title)} </td>
              <td align="left" nowrap="nowrap"> ${fn:escapeXml(cur.send_user_name)} </td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.send_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              <td  align="center" nowrap="nowrap"><a style="text-decoration: none"  class="fblue"  href="JxcReceivePeShopMsg.do?method=view&id=${cur.id}&tag_id=a&mod_id=${af.map.mod_id}&tree_param=${tree_param}&keySeq=${af.map.keySeq}">查看</a>
               |&nbsp;<a style="text-decoration: none"  class="fblue"  href="JxcReceivePeShopMsg.do?method=reply&id=${cur.id}&keySeq=${af.map.keySeq}">回复</a></td>
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
    </html-el:form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JxcReceivePeShopMsg.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
			   var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			   pager.addHiddenInputs("method", "list");
			   pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			   pager.addHiddenInputs("tree_param", "${tree_param}");
			   pager.addHiddenInputs("keySeq", "${af.map.keySeq}");
			   pager.addHiddenInputs("tag_id", '${tag_id}');
			   document.write(pager.toString());
			   </script></td>
        </tr>
      </table>
    </form>
  </div>
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
