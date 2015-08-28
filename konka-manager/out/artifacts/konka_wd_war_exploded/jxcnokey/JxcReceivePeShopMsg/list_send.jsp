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
       <td>当前位置：&nbsp;&gt;&nbsp;信息接收&nbsp;&gt;&nbsp;站内信息&nbsp;&gt;&nbsp;发件箱</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/jxcnokey/JxcReceivePeShopMsg/shop_msg_top.jsp" %>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <input class="but3" type="button" name="Submit3" value="回收站" onclick="confirmDeleteAll(document.forms[0]);" />
  </div>
  <div class="rtabcont1">
    <html-el:form action="/JxcReceivePeShopMsg.do" enctype="multipart/form-data" >
      <div style="text-align: left">
        <html-el:hidden property="tag_id" styleId="tag_id" />
        <html-el:hidden property="receive_user_type" />
        <html-el:hidden property="public_target" />
        <html-el:hidden property="tag_id" styleId="tag_id" />
        <html-el:hidden property="method" styleId="method" value="delete" />
        <html-el:hidden property="mod_id" styleId="mod_id" />
        <html-el:hidden property="keySeq" styleId="keySeq" value="${af.map.keySeq}" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr class="tabtt1">
            <td width="5%" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
            <td nowrap="nowrap">标题</td>
            <td width="15%" nowrap="nowrap" align="center">发送时间</td>
            <td width="10%" nowrap="nowrap" align="center">操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap"><input name="pks" type="checkbox" id="pks" value="${cur.id}" /></td>
              <td align="left"> ${fn:escapeXml(cur.title)}</td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.send_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.par_id eq 0}"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'JxcReceivePeShopMsg.do', 'viewSend', 'id=${cur.id}&tag_id=${af.map.tag_id}&&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span> | </c:if>
                <c:if test="${cur.par_id ne 0}"> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'JxcReceivePeShopMsg.do', 'view', 'id=${cur.id}&tag_id=${af.map.tag_id}&&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span> | </c:if>
                <span style="cursor:pointer;" class="fblue" onclick="confirmDelete(null, 'JxcReceivePeShopMsg.do', 'id=${cur.id}&tag_id=${af.map.tag_id}&&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">删除</span></td>
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
			   pager.addHiddenInputs("method", "listOut");
			   pager.addHiddenInputs("tag_id", '${tag_id}');
			   pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			   pager.addHiddenInputs("keySeq", "${af.map.keySeq}");
			   pager.addHiddenInputs("tag_id", '${tag_id}');
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
<jsp:include page="/__analytics.jsp" />
</body>
</html>
