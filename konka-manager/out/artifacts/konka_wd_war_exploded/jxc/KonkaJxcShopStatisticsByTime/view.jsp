<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网点经营情况统计</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <form id="listForm" name="listForm" method="post" action="KonkaJxcShopSellStatistics.do">
    <div class="rtabcont1" id="divExcel" title="${fn:escapeXml(shop_name)}(${start_date})至(${end_date})康佳进销存表.xls">
      <table style="width:1080px;" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="16" align="center" style="font-size: +5;">康佳进销存表${fn:escapeXml(shop_name)}</th>
        </tr>
        <tr class="title_top">
          <td rowspan="2" align="center" width="5%" nowrap="nowrap">规格</td>
          <td rowspan="2" align="center" width="10%" nowrap="nowrap">机型</td>
          <td colspan="3" align="center" width="15%" nowrap="nowrap">期初库存(${start_date})</td>
          <td colspan="3" align="center" width="15%" nowrap="nowrap">进货</td>
          <td colspan="3" align="center" width="15%" nowrap="nowrap">出货/零售</td>
          <td colspan="3" align="center" width="18%" nowrap="nowrap">期末库存(${end_date})</td>
          <td rowspan="2" align="center" width="8%" nowrap="nowrap">本周销售计划</td>
          <td rowspan="2" align="center"  nowrap="nowrap">本周备货计划</td>
        </tr>
        <tr class="title_top">
          <td align="center" width="5%" nowrap="nowrap">数量</td>
          <td align="center" width="5%" nowrap="nowrap">进货单价</td>
          <td align="center" width="5%" nowrap="nowrap">金额</td>
          <td align="center" width="5%" nowrap="nowrap">数量</td>
          <td align="center" width="5%" nowrap="nowrap">单价</td>
          <td align="center" width="5%" nowrap="nowrap">金额</td>
          <td align="center" width="5%" nowrap="nowrap">数量</td>
          <td align="center" width="5%" nowrap="nowrap">单价</td>
          <td align="center" width="5%" nowrap="nowrap">金额</td>
          <td align="center"  nowrap="nowrap">数量</td>
          <td align="center" width="5%" nowrap="nowrap">金额</td>
          <td align="center" width="8%"nowrap="nowrap">库存提示</td>
        </tr>
        <c:forEach items="${pdModelPropsValList}" var="cur" varStatus="status">
          <tr>
            <td align="center" width="5%">${cur.prop_value}</td>
            <td colspan="15" align="center" width="95%"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
                <c:forEach items="${cur.pdModelPropsValList}" var="cur1" varStatus="status">
                  <c:if test="${not empty cur1.map.md_name}">
                    <tr>
                      <td align="center" width="10%">${cur1.map.md_name}</td>
                      <c:if test="${empty cur1.jxcPdList}">
                        <td align="center" width="5%">0</td>
                        <td align="center" width="5%">￥ 0</td>
                        <td align="center" width="5%">￥ 0</td>
                        <td align="center" width="5%">0</td>
                        <td align="center" width="5%">￥ 0</td>
                        <td align="center" width="5%">￥ 0</td>
                        <td align="center" width="5%">0</td>
                        <td align="center" width="5%">￥ 0</td>
                        <td align="center" width="5%">￥ 0</td>
                        <td align="center" width="5%">0</td>
                        <td align="center" width="5%">￥ 0</td>
                        <td align="center" width="5%"><font color=red>*库存为零</font></td>
                        <td align="center" width="10%">&nbsp;</td>
                        <td align="center" width="10%">&nbsp;</td>
                      </c:if>
                      <c:if test="${not empty cur1.jxcPdList}">
                        <c:forEach items="${cur1.jxcPdList}" var="cur2" varStatus="status">
                          <td align="center" width="5%">${empty cur2.qcjc_pd.map.qcjc_count?0:cur2.qcjc_pd.map.qcjc_count}</td>
                          <td align="center" width="5%"><fmt:formatNumber value="${cur2.qcjc_pd.map.qcjc_price}" var="qcjc_price" type="currency" />
                            ${empty cur2.qcjc_pd.map.qcjc_price?0:cur2.qcjc_pd.map.qcjc_price}</td>
                          <td align="center" width="5%"><fmt:formatNumber value="${cur2.qcjc_pd.map.qcjc_money}" var="qcjc_money" type="currency" />
                            ${empty cur2.qcjc_pd.map.qcjc_money?'0.00':qcjc_money}</td>
                          <td align="center" width="5%">${empty cur2.map.jh_count?0:cur2.map.jh_count}</td>
                          <td align="center" width="5%"><fmt:formatNumber value="${cur2.map.rcjh_price}" var="rcjh_price" type="currency" />
                            ${empty cur2.map.rcjh_price?0:cur2.map.rcjh_price}</td>
                          <td align="center" width="5%"><fmt:formatNumber value="${cur2.map.jh_sum_money}" var="jh_sum_money" type="currency" />
                            ${empty cur2.map.jh_sum_money?'0.00':jh_sum_money}</td>
                          <td align="center" width="5%">${empty cur2.map.ch_count?0:cur2.map.ch_count}</td>
                          <td align="center" width="5%"><fmt:formatNumber value="${cur2.map.rcch_price}" var="rcch_price" type="currency" />
                            ${empty cur2.map.rcch_price?0:cur2.map.rcch_price}</td>
                          <td align="center" width="5%"><fmt:formatNumber value="${cur2.map.ch_sum_money}" var="ch_sum_money" type="currency" />
                            ${empty cur2.map.ch_sum_money?'0.00':ch_sum_money}</td>
                          <td align="center" width="5%"><fmt:formatNumber value="${cur2.qmjc_count}" pattern="0" /></td>
                          <td align="center" width="5%"><fmt:formatNumber value="${cur2.qmjc_money}" type="currency" /></td>
                          <td align="center" width="5%"><c:if test="${cur2.qmjc_count eq 0}"><font color=red>*库存为空</font></c:if>
                            <c:if test="${cur2.qmjc_count gt 0}"><font color=red>余存【
                              <fmt:formatNumber value="${cur2.qmjc_count}" pattern="0" />
                              】</font></c:if></td>
                          <td align="center" width="10%">&nbsp;</td>
                          <td align="center" width="10%">&nbsp;</td>
                        </c:forEach>
                      </c:if>
                    </tr>
                  </c:if>
                  <c:if test="${empty cur1.map.md_name}">
                    
                      <td align="center" width="10%">-</td>
                      <td align="center" width="7%">0</td>
                      <td align="center" width="7%">￥ 0</td>
                      <td align="center" width="5%">￥ 0</td>
                      <td align="center" width="5%">0</td>
                      <td align="center" width="5%">￥ 0</td>
                      <td align="center" width="5%">￥ 0</td>
                      <td align="center" width="5%">0</td>
                      <td align="center" width="5%">￥ 0</td>
                      <td align="center" width="5%">￥ 0</td>
                      <td align="center" width="5%">0</td>
                      <td align="center" width="5%">￥ 0</td>
                      <td align="center" width="7%"><font color=red>*库存为空</font></td>
                      <td align="center" width="10%">&nbsp;</td>
                      <td align="center" width="10%">&nbsp;</td>
                  </c:if>
                </c:forEach>
              </table></td>
          </tr>
        </c:forEach>
        <tr>
          <td colspan="2" align="center"><font class="redbig" style="color: red;">合计</font></td>
          <td align="center"><fmt:formatNumber value="${af.map.qcjc_count_total}" pattern="0" /></td>
          <td align="center">-</td>
          <td align="center"><fmt:formatNumber value="${af.map.qcjc_money_total}" type="currency" /></td>
          <td align="center"><fmt:formatNumber value="${af.map.rcjh_count_total}" pattern="0" /></td>
          <td align="center">-</td>
          <td align="center"><fmt:formatNumber value="${af.map.rcjh_money_total}" type="currency" /></td>
          <td align="center"><fmt:formatNumber value="${af.map.rcch_count_total}" pattern="0" /></td>
          <td align="center">-</td>
          <td align="center"><fmt:formatNumber value="${af.map.rcch_money_total}" type="currency" /></td>
          <td align="center"><fmt:formatNumber value="${af.map.qmjc_count_total}" pattern="0" /></td>
          <td align="center"><fmt:formatNumber value="${af.map.qmjc_money_total}" type="currency" /></td>
          <td align="center">-</td>
          <td align="center"></td>
          <td align="center"></td>
        </tr>
      </table>
    </div>
  </form>
  <div class="rtabcont3">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-left:5px;padding-top:10px">
      <tr>
        <td height="26" align="center"><div class="left">
            <input name="button" type="button" class="bgButtonExport" id="toExcel" value="导出" onclick="toExcel('divExcel', '?method=toExcel');"/>
            &nbsp;
            <input name="button" type="button" class="bgButtonPrint" id="button" value="打印" />
            &nbsp;
            <input name="button" type="button" class="bgButtonBack" id="button" value="返回" onclick="history.back();"/>
          </div></td>
      </tr>
    </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">//<![CDATA[

var id = "${id}";
var start_date = "${start_date}";
var end_date = "${end_date}";
var is_agent = "${is_agent}";
var shop_name = "${shop_name}";
var param = "id=" + id  + "&start_date=" + start_date+ "&end_date=" + end_date+ "&is_agent=" + is_agent+ "&shop_name=" + shop_name + "&random=" + Math.random();

$(".bgButtonPrint").click(function(){
	window.showModalDialog("?method=print&" + encodeURI(param), window, "dialogWidth:900px;status:no;dialogHeight:580px");
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
