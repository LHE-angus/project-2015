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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div id="divExcel" class="rtabcont2" align="center" title="康佳专卖店电子销售单${af.map.now_date}" style="margin-top:4px;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="10" align="center" style="padding-bottom:10px;"><font style="font-weight:bold;font-size:20px;">康佳专卖店电子销售单</font></td>
      </tr>
      <tr>
        <td align="left" colspan="2"><font style="font-weight:bold;">订单流水号 :</font>
          <span style="color:#F00;font-weight:700;"><c:out value="${fnx:leftPad_sis(sell_bill_id, 12, '0')}" /></span></td>
          <td align="left" colspan="3"><font style="font-weight:bold;">销售日期 :</font>
          <fmt:formatDate value="${sell_date}" pattern="yyyy年MM月dd日" /></td>
        <td align="right" colspan="5"><font style="font-weight:bold;">开单人 :</font>
          <c:out value="${add_user_realname}" /></td>
      </tr>
    </table>
    <table width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="10%" nowrap="nowrap" align="center">分公司</td>
        <td width="10%" nowrap="nowrap" align="center">专卖店编号</td>
        <td width="9%" nowrap="nowrap" align="center">销售品类</td>
        <td width="10%" nowrap="nowrap" align="center">产品型号</td>
        <td width="12%" nowrap="nowrap" align="center">出货仓位</td>
        <td width="11%" nowrap="nowrap" align="center">销售价格（元）</td>
        <td width="7%" nowrap="nowrap" align="center">销售数量</td>
        <td width="11%" nowrap="nowrap" align="center">金额合计（元）</td>
        <td width="12%" nowrap="nowrap" align="center">赠品</td>
        <td width="9%" nowrap="nowrap" align="center">付款方式</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="left">${dept_name}</td>
          <td align="left"><font class="blue12px">${zmd_sn}</font></td>
          <td align="left"><font class="blue12px">${cur.pd_cls_name}</font></td>
          <td align="left"><font class="blue12px">${cur.md_name}</font></td>
          <td align="left"><font class="blue12px">${cur.map.store_name}</font></td>
          <td align="right"><font class="blue12px">
            <fmt:formatNumber value="${cur.price}" pattern="#,#00.00#" />
            </font></td>
          <td align="right"><font class="blue12px">${cur.counts}</font></td>
          <td align="right" style="mso-number-format:'\@';"><font class="blue12px">
            <fmt:formatNumber value="${cur.price * cur.counts}" pattern="#,#00.00#" />
            </font></td>
          <td align="left"><font class="blue12px">${cur.gift}</font></td>
          <c:if test="${vs.first eq true}">
            <td align="center" valign="middle" rowspan="${detail_count}"><font class="blue12px">${fn:split(' ,现金付款,POS机刷卡,货到付款', ',')[pay_way]}</font></td>
          </c:if>
        </tr>
      </c:forEach>
      <tr>
        <td align="center" nowrap="nowrap"><font class="blue">合计</font></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><fmt:formatNumber value="${total_pay}" pattern="#,#00.00#" /></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>
  </div>
  <div align="left" style="margin-left:10px;margin-bottom:20px;">
    <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
    <input class="but_excel" type="button"  value="导出" onclick="toExcel('divExcel', '?method=toExcel');" />
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
