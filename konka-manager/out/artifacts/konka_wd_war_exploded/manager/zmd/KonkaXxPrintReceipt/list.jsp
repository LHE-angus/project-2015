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
    <html-el:form action="/zmd/KonkaXxPrintReceipt">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <div id="condition_div" style="100%;overflow-x:auto;" >
        <table id="condition_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable5">
          <tr>
            <!--          <td width="10%">&nbsp;<strong class="fb">分公司名称：</strong></td>-->
            <!--          <td width="40%"><c:if test="${empty dept_name}">-->
            <!--              <html-el:select property="dept_id" styleId="dept_id" onchange="this.form.submit();" style="width:154px;">-->
            <!--                <html-el:option value="">==请选择==</html-el:option>-->
            <!--                <c:forEach var="cur" items="${konkaDepts}">-->
            <!--                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>-->
            <!--                </c:forEach>-->
            <!--              </html-el:select>-->
            <!--            </c:if>-->
            <!--            <c:if test="${not empty dept_name}">-->
            <!--              <label style="color:blue;">${dept_name}</label>-->
            <!--            </c:if></td>-->
            <td width="10%"><strong class="fb">专卖店编号：</strong></td>
            <td width="40%"><c:if test="${empty zmd_sn}">
                <html-el:select property="zmd_id" onchange="this.form.submit();" style="width:154px;">
                  <html-el:option value="">==请选择==</html-el:option>
                  <c:forEach var="cur" items="${zmdList}">
                    <html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
                  </c:forEach>
                </html-el:select>
              </c:if>
              <c:if test="${not empty zmd_sn}">
                <label style="color:blue;">${zmd_sn}</label>
              </c:if></td>
          </tr>
          <tr>
            <td>&nbsp;<strong class="fb">开单人姓名：</strong></td>
            <td><html-el:text property="add_user_realname_like" styleId="add_user_realname_like" size="20" maxlength="20" ></html-el:text></td>
            <td><strong class="fb">开单日期：</strong></td>
            <td><html-el:text property="add_date_ge" styleId="add_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
              至
              <html-el:text property="add_date_le" styleId="add_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" /></td>
          </tr>
          <tr>
            <td>&nbsp;<strong class="fb">订单流水号：</strong></td>
            <td><html-el:text property="sell_bill_id" styleId="sell_bill_id" size="20" maxlength="12" ></html-el:text></td>
            <td><strong class="fb">消费者姓名：</strong></td>
            <td><html-el:text property="buyer_name_like" styleId="buyer_name_like" size="20" maxlength="20" ></html-el:text></td>
          </tr>
          <tr>
            <td>&nbsp;<strong class="fb">付款方式：</strong></td>
            <td><html-el:select property="pay_way" styleId="pay_way" onchange="this.form.submit();" style="width:154px;">
                <html-el:option value="">==请选择==</html-el:option>
                <html-el:option value="1">现金支付</html-el:option>
                <html-el:option value="2">POS机刷卡</html-el:option>
                <html-el:option value="3">货到付款</html-el:option>
              </html-el:select></td>
            <td colspan="2"><input class="but1" type="submit" name="Submit" value="搜索" /></td>
          </tr>
        </table>
      </div>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table id="table1" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="10%" align="center">订单流水号</td>
        <td width="10%" align="center">分公司</td>
        <td width="10%" align="center">专卖店编号</td>
        <td width="12%" align="center">销售单状态</td>
        <td width="16%" align="center">开单时间</td>
        <td width="10%" align="center">开单人姓名</td>
        <td width="10%" align="center">消费者姓名</td>
        <td width="10%" align="center">付款方式</td>
        <td width="12%" align="center">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center"><a href="${ctx}/manager/zmd/KonkaXxZmdAddSalesOrder.do?method=view&sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}">
            <c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" />
            </a></td>
          <td align="left" class="dept_name"><font class="blue12px">${cur.map.dept_name}</font></td>
          <td align="left"><font class="blue12px">${cur.zmd_sn}</font></td>
          <td align="left"><font class="blue12px">
            <c:choose>
              <c:when test="${cur.sell_state eq 0}">未付款</c:when>
              <c:when test="${cur.sell_state eq 10}">已付款未审核</c:when>
              <c:when test="${cur.sell_state eq 20}">已审核通过</c:when>
              <c:when test="${cur.sell_state eq 21}"><span style="color:#FF0000">已审核不通过</span></c:when>
              <c:when test="${cur.sell_state eq 30}">已发货</c:when>
              <c:when test="${cur.sell_state eq 40}">已确认消费者收货</c:when>
              <c:when test="${cur.sell_state eq 70}">交易成功</c:when>
            </c:choose>
            </font></td>
          <td align="left"><font class="blue12px">
            <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss" />
            </font></td>
          <td align="left" nowrap="nowrap"><font class="blue12px">${cur.add_user_realname}</font></td>
          <td align="left"><font class="blue12px">${cur.buyer_name}</font></td>
          <td align="center"><font class="blue12px">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[cur.pay_way]}</font></td>
          <td align="center"><img src="${ctx}/images/print2.png" /> <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxPrintReceipt.do', 'showReceipt','sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">打印收款单</span></td>
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxPrintReceipt.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
			pager.addHiddenInputs("zmd_id", "${af.map.zmd_id}");	
            pager.addHiddenInputs("sell_bill_id", "${af.map.sell_bill_id}");
            pager.addHiddenInputs("add_date_ge", "${af.map.add_date_ge}");
            pager.addHiddenInputs("add_date_le", "${af.map.add_date_le}");
            pager.addHiddenInputs("sell_state", "${af.map.sell_state}");
			pager.addHiddenInputs("add_user_realname_like", "${fn:escapeXml(af.map.add_user_realname_like)}");
			pager.addHiddenInputs("buyer_name_like", "${fn:escapeXml(af.map.buyer_name_like)}");
			pager.addHiddenInputs("pay_way", "${af.map.pay_way}");
			
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#add_date_ge").datepicker();
	$("#add_date_le").datepicker();
	
	trMerge("dept_name",$("#table1"));
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>