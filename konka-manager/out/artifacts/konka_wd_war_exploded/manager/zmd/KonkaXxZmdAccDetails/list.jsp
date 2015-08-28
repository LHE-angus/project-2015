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
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div style="line-height:30px;margin:0px 7px;"><a class="fblue" href="${ctx}/manager/zmd/KonkaXxZmdAccDetails.do?mod_id=${af.map.mod_id}">账户余额</a> | 收支明细</div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdAccDetails">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="zmd_id" value="${konkaXxZmd.zmd_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr style="height:25px;">
          <td width="15" style="height:25px;"></td>
          <td style="height:25px;"> 时间：
            <html-el:text property="do_time_start" styleId="do_time_start" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            &nbsp;至&nbsp;
            <html-el:text property="do_time_end" styleId="do_time_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            &nbsp;
            <input class="but1" type="submit" name="" value="查询" id="btn_sub" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="8%" nowrap="nowrap" align="center"><font class="blue">专卖店编号</font></td>
        <td width="10%" nowrap="nowrap" align="center"><font class="blue">时间</font></td>
        <td width="10%" align="center" nowrap="nowrap"><font class="blue">支出</font></td>
        <td width="10%" align="center" nowrap="nowrap"><font class="blue">收入</font></td>
        <td width="10%" align="center" nowrap="nowrap"><font class="blue">余额</font></td>
        <td align="center" nowrap="nowrap"><font class="blue">备注</font></td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${konkaXxZmd.zmd_sn}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.do_time}" pattern="yyyy-MM-dd HH:mm" /></td>
          <c:if test="${cur.note_type eq -1}">
            <td nowrap="nowrap" align="right" class="kz-price-12"><fmt:formatNumber value="${cur.money}" type="currency" /></td>
            <td nowrap="nowrap" align="center">-</td>
          </c:if>
          <c:if test="${cur.note_type eq 1}">
            <td nowrap="nowrap" align="center">-</td>
            <td align="right" class="kz-price-12"><fmt:formatNumber value="${cur.money}" type="currency" /></td>
          </c:if>
          <!--  
          <td nowrap="nowrap" align="right" class="kz-price-12">
          	<c:if test="${cur.note_type eq -1}"><fmt:formatNumber value="${cur.money}" type="currency" /></c:if>
          </td>
          <td nowrap="nowrap" align="right" class="kz-price-12">
          	<c:if test="${cur.note_type eq 1}"><fmt:formatNumber value="${cur.money}" type="currency" /></c:if>
          </td>
          -->
          <td align="right" nowrap="nowrap" class="kz-price-12"><fmt:formatNumber value="${cur.left_money}" type="currency" /></td>
          <td nowrap="nowrap" align="left">${cur.memo}</td>
        </tr>
      </c:forEach>
      <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdAccDetails.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td>信用额度：<span class="kz-price">
            <fmt:formatNumber value="${konkaXxZmd.cur_credit_line}" type="currency" />
            </span>&nbsp;&nbsp;&nbsp;
            余额：<span class="kz-price">
            <fmt:formatNumber value="${konkaXxZmd.total_credit_line}" type="currency" />
            </span></td>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("zmd_id", "${konkaXxZmd.zmd_id}");
	            pager.addHiddenInputs("do_time_start", "${af.map.do_time_start}");
	            pager.addHiddenInputs("do_time_end", "${af.map.do_time_end}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 日期控件
	$("#do_time_start").datepicker();
	$("#do_time_end").datepicker();
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>