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
    <html-el:form action="/manager/KonkaXxSellBillCstSatform">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15">&nbsp;</td>
          <td width="25%" nowrap="nowrap"><strong class="fb">销售时间：</strong>
            <html-el:text property="sell_date_begin" styleId="sell_date_begin" size="10" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            &nbsp;至&nbsp;
            <html-el:text property="sell_date_end" styleId="sell_date_end" size="10" maxlength="10" readonly="true" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            &nbsp;<strong class="fb">是否回访：</strong>
            <html-el:select property="return_v_state" onchange="this.form.submit();" styleId="return_v_state"  style="width:90px;" >
              <html-el:option value="0">未回访</html-el:option>
              <html-el:option value="1">已回访</html-el:option>
            </html-el:select></td>
          <td align="left" style="padding-left:15px;"><input class="but1" type="submit" name="Submit" value="搜索" /></td>

        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <form id="listForm" name="listForm" method="post" action="KonkaXxSellBillCstSatform.do">
      <input type="hidden" name="method" id="method" value="save" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <div style="overflow-x:auto;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="8%"  align="center" nowrap="nowrap">订单流水号</td>
          <td width="8%" align="center" nowrap="nowrap">专卖店编号</td>
          <td width="9%" align="center" nowrap="nowrap">合计金额（元）</td>
          <td width="8%" align="center" nowrap="nowrap">销售时间</td>
          <td width="8%" align="center" nowrap="nowrap">销售单状态</td>
          <td width="8%"  align="center" nowrap="nowrap">付款方式</td>
          <td width="9%" align="center" nowrap="nowrap">物流费用（元）</td>
          <td width="9%" align="center" nowrap="nowrap">定金（元）</td>
          <td width="8%" align="center" nowrap="nowrap">回访人</td>
          <td width="8%" align="center" nowrap="nowrap">联系电话</td>
          <td width="13%" align="center" nowrap="nowrap">操作</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap"><font class="blue12px">
                <c:out value="${fnx:leftPad_sis(cur.sell_bill_id, 12, '0')}" />
                </font></td>
              <td align="left" nowrap="nowrap">${cur.zmd_sn}</td>
              <td align="right" nowrap="nowrap"><span class="kz-price-12">
                <fmt:formatNumber type="currency" value="${cur.total_money}" />
                </span></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.sell_date}" pattern="yyyy-MM-dd" /></td>
<!--               <td align="center" nowrap="nowrap"><span style="color:green;">确认收货</span></td> -->
              <td align="center" nowrap="nowrap">
              	<c:choose>
	              <c:when test="${cur.sell_state eq 0}"><span style="color:#ccc;">未付款</span></c:when>
	              <c:when test="${cur.sell_state eq 10}"><span style="color:red;">待审核</span></c:when>
	              <c:when test="${cur.sell_state eq 20}">已审核</c:when>
	              <c:when test="${cur.sell_state eq 21}">已审核不通过</c:when>
	              <c:when test="${cur.sell_state eq 30}">已发货</c:when>
	              <c:when test="${cur.sell_state eq 70}"><span style="color:green;">交易成功</span></c:when>
	              <c:otherwise>-</c:otherwise>
	            </c:choose>
              </td>
              <td align="center" nowrap="nowrap">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[cur.pay_way]}</td>
              <td align="right" nowrap="nowrap"><span class="kz-price-12">
                <fmt:formatNumber type="currency" value="${cur.fee_of_post}" />
                </span></td>
              <td align="right" nowrap="nowrap"><c:if test="${not empty cur.money_of_deposit}"> <span class="kz-price-12">
                  <fmt:formatNumber type="currency" value="${cur.money_of_deposit}" />
                  </span> </c:if>
                <c:if test="${empty cur.money_of_deposit}"> <span style="color:#999;">无定金</span> </c:if></td>
              <c:if test="${empty cur.return_v_user_id}">
                <td align="center" nowrap="nowrap">-</td>
                <td align="center" nowrap="nowrap">-</td>
              </c:if>
              <c:if test="${not empty cur.return_v_user_id}">
                <td align="left" nowrap="nowrap">${cur.return_v_real_name}</td>
                <td align="center" nowrap="nowrap">${cur.buyer_phone}
                  <c:if test="${empty cur.buyer_phone}">${cur.buyer_tel}</c:if></td>
              </c:if>
              <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="$('#message_tip').show();doNeedMethod(null, 'KonkaXxSellBillCstSatform.do', 'edit', 'sell_bill_id=${cur.sell_bill_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize());">编辑回访</span> | <span style="cursor:pointer;" class="fblue" onclick="$('#message_tip').show();doNeedMethod(null, 'KonkaXxZmdAddSalesOrder.do', 'view', 'sell_bill_id=${cur.sell_bill_id}&mod_id=802001');">查看状态</span></td>
            </tr>
          </c:forEach>
          <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}">
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
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      </div>
    </form>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxSellBillCstSatform.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td>&nbsp;</td>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("return_v_state", "${af.map.return_v_state}");
				pager.addHiddenInputs("sell_date_begin", "${af.map.sell_date_begin}");
				pager.addHiddenInputs("sell_date_end", "${af.map.sell_date_end}");
				document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<!-- Ajax 提交 覆盖层  -->
<div id="message_tip" style="display:none;z-index:888888888;">
  <div class="ui-overlay">
    <div class="ui-widget-overlay"></div>
    <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:absolute;left:35%;top:25%"></div>
  </div>
  <div style="position:absolute;width:280px;height:130px;left:35%;top:25%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" />正在查询，请稍等...</span> </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 日期控件
	$("#sell_date_begin").datepicker();
	$("#sell_date_end").datepicker();
});
//]]></script>

<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>