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
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdRemitForMyRec">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15">&nbsp;</td>
          <td><strong class="fb">汇款状态：</strong>
            <html-el:select property="state" onchange="this.form.submit();">
              <html-el:option value="" >==请选择==</html-el:option>
              <html-el:option value="0" >==未确认==</html-el:option>
              <html-el:option value="1" >==未结算==</html-el:option>
              <html-el:option value="2">==已结算==</html-el:option>
            </html-el:select>
            &nbsp;<strong class="fb">计划时间：</strong>
            <html-el:text property="plan_date_ge" styleId="plan_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            至
            <html-el:text property="plan_date_le" styleId="plan_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" /></td>
          <td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <%@ include file="/commons/pages/messages.jsp"%>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" >
      <tr style="display:none;" id="t_2">
        <td colspan="13" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">康佳专卖店资源管理数据表${af.map.now_date}</td>
      </tr>
      <tr class="tabtt1">
      	<td align="center" nowrap="nowrap" width="12%"><font class="blue">专卖店编号</font></td>
        <td width="10%" align="center" nowrap="nowrap"><font class="blue">汇款流水号</font></td>
        <td align="center" nowrap="nowrap"><font class="blue">汇款/转账流水号</font></td>
        <td width="12%" align="center" nowrap="nowrap"><font class="blue">应汇款金额（元）</font></td>
        <td width="12%" align="center" nowrap="nowrap"><font class="blue">实际汇款金额（元）</font></td>
        <td width="12%" align="center" nowrap="nowrap"><font class="blue">计划时间</font></td>
        <td width="8%" align="center" nowrap="nowrap"><font class="blue">汇款状态</font></td>
        <td width="8%" align="center" nowrap="nowrap"><font class="blue">操作</font></td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="left">${cur.map.zmd_sn}</td>
          <td align="center" title="备注:${empty cur.memo ? '无' : cur.memo}"><c:out value="ZH${fnx:leftPad_sis(cur.remit_rec_id, 10, '0')}" /></td>
          <td align="left" nowrap="nowrap"> ${cur.remit_sn}
           <c:if test="${empty cur.remit_sn}"><span style="color:gray;">未填写</span></c:if></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.todo_money}" type="currency" />
            </span></td>
          <td align="right" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${cur.total_money}" type="currency" />
            </span></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.plan_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          <td align="center" nowrap="nowrap"><c:choose>
              <c:when test="${cur.state eq 0}">未确认</c:when>
              <c:when test="${cur.state eq 1}">未结算</c:when>
              <c:when test="${cur.state eq 2}">已结算</c:when>
            </c:choose></td>
          <td align="center" nowrap="nowrap"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdRemitForMyRec.do', 'view','remit_rec_id=${cur.remit_rec_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span></td>
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
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdRemitForMyRec.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td>&nbsp;</td>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("plan_date_ge", "${af.map.plan_date_ge}");
	            pager.addHiddenInputs("plan_date_le", "${af.map.plan_date_le}");
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
	$("#plan_date_ge").datepicker();
	$("#plan_date_le").datepicker();
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>