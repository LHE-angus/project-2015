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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
         <td>当前位置：${naviString}</td>
        <!--  <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /><span id="span_help" style="cursor:pointer;">帮助</span> </td> --> 
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/oa/SelfEventCenter">
      <html-el:hidden property="method" value="list" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <form id="listForm" name="listForm" method="post" action="SelfEventCenter.do?method=delete">
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
       <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
         <tr class="tabtt1">
          <td width="30" nowrap="nowrap" align="center">序号</td>
          <td nowrap="nowrap">标题</td>
          <td width="65" nowrap="nowrap" align="center">事务类型</td>
          <td width="150" nowrap="nowrap">信息来源</td>
          <td width="100" align="center" nowrap="nowrap">发起时间</td>
          <td width="50" align="center" nowrap="nowrap">操作</td>
        </tr>
        <tbody>
          <c:set var="ids" value="0" />
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr id="tr_${cur.id}">
              <td align="center" nowrap="nowrap" onclick="">${cur.sequence}</td>
              <td align="left">${cur.eventiltle}</td>
              <td align="center">${cur.eventType}</td>
              <td>${cur.fromPerson}</td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.enterDate}" pattern="yyyy-MM-dd HH:mm" /></td>
              <td align="center" nowrap="nowrap">${cur.eventDo}</td>
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
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="SelfEventCenter.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
           <td height="40"><div style="text-align:right; padding-right:5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
		        var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		        pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");							
		        document.write(pager.toString());
		      </script>
           </div> </td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
    $(document).ready(function() {
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
			
	$("#btn_submit").click(function(){
		if (this.form.st_pub_date.value != "" && this.form.en_pub_date.value != "") {
			if (this.form.en_pub_date.value < this.form.st_pub_date.value) {
				alert("发布时间结束日期不得大于开始日期,请重新选择!");
				return false;
			}
		}
		
		this.form.submit();
	});
});

function confirmMove(msg, page, queryString, method) {
	//msg  = msg  || "\u786e\u5b9a\u4fee\u6539\u8fd9\u6761\u4fe1\u606f\u5417\uff1f";
	page = page || "?";
	page = page.indexOf("?") != -1 ? page : (page + "?");
	method = method || "move";
	location.href = page  + "method=" + method + "&" + encodeURI(queryString);
}

function confirmCopy(msg, page, queryString, method) {
	//msg  = msg  || "\u786e\u5b9a\u4fee\u6539\u8fd9\u6761\u4fe1\u606f\u5417\uff1f";
	page = page || "?";
	page = page.indexOf("?") != -1 ? page : (page + "?");
	method = method || "copy";
	location.href = page  + "method=" + method + "&" + encodeURI(queryString);
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
