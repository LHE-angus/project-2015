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
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdAccouts">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <div id="condition_div" style="100%;overflow-x:auto;" >
        <table id="condition_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable5">
          <tr>
            <td>&nbsp;<strong class="fb">专卖店编号：</strong>
              <html-el:select property="zmd_id" onchange="this.form.submit();" style="width:154px;">
                <html-el:option value="">==请选择==</html-el:option>
                <c:forEach var="cur" items="${konkaXxZmdList}">
                  <html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
                </c:forEach>
              </html-el:select>
              &nbsp;&nbsp; <strong class="fb">账单状态：</strong>
              <html-el:select property="bill_state" onchange="this.form.submit();">
                <html-el:option value="">==请选择==</html-el:option>
                <html-el:option value="1">==已结账==</html-el:option>
                <html-el:option value="0" >==已出账未结账==</html-el:option>
              </html-el:select></td>
          </tr>
          <tr>
            <td>&nbsp;<strong class="fb">系统出账时间：</strong>
              <html-el:text property="add_date_start" styleId="add_date_start" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
              至
              <html-el:text property="add_date_end" styleId="add_date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
              &nbsp;&nbsp;
              <input class="but1" type="submit" name="Submit" value="搜索" /></td>
          </tr>
        </table>
      </div>
    </html-el:form>
  </div>
  <div>
   &nbsp;<span style="color:red">提示：账单总金额、实收款的单位为（元）</span>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td width="8%" align="center" nowrap="nowrap">分公司</td>
        <td width="11%" align="center" nowrap="nowrap">专卖店编号</td>
        <td align="center" nowrap="nowrap">账单标题</td>
        <td width="11%" align="center" nowrap="nowrap">账单总金额</td>
        <td width="9%" align="center" nowrap="nowrap">实收款</td>
        <td width="12%" align="center" nowrap="nowrap">账单状态</td>
        <td width="8%" align="center" nowrap="nowrap">出账时间</td>
        <td width="8%" align="center" nowrap="nowrap">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left">${fn:escapeXml(cur.map.dept_name)}</td>
          <td align="left">${fn:escapeXml(cur.map.zmd_sn)}</td>
          <td align="left">${fn:escapeXml(cur.title)}</td>
          <td align="right"><fmt:formatNumber value="${cur.bill_money}" pattern="#0.00" /></td>
          <td align="right"><fmt:formatNumber value="${cur.rec_money}" pattern="#0.00" /></td>
          <td align="center"><c:choose>
              <c:when test="${cur.bill_state eq 1}">已结账</c:when>
              <c:when test="${cur.bill_state eq 0 }">已出账未结账</c:when>
            </c:choose></td>
          <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
          <td align="center"><c:if test="${cur.bill_state eq 0}"> <span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxZmdAccouts.do', 'edit','bill_id=${cur.bill_id}&'+$('#bottomPageForm').serialize())">结账</span></c:if>
            <c:if test="${cur.bill_state eq 1}"> <span style="color: gray">结账</span></c:if></td>
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
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdAccouts.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
			pager.addHiddenInputs("zmd_id", "${af.map.zmd_id}");
			pager.addHiddenInputs("bill_state", "${af.map.bill_state}");
			pager.addHiddenInputs("add_date_start", "${af.map.add_date_start}");
			pager.addHiddenInputs("add_date_end", "${af.map.add_date_end}");	
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#add_date_start").datepicker();
	$("#add_date_end").datepicker();
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
