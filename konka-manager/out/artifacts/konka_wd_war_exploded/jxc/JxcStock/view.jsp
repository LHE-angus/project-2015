<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>实时库存详细</title>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：库存管理 &gt; 实时库存 &gt; 实时库存详细</div>
<div class="rtabcont1">
  <html-el:form action="/JxcStock.do?method=view">
    <html-el:hidden property="keySeq" />
    <html-el:hidden property="id" />
    <html-el:hidden property="brand_name" />
    <html-el:hidden property="pd_type_name" />
    <html-el:hidden property="name" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td align="left"><strong class="fb">产品</strong>：[${af.map.pd_type_name}]&nbsp;[${af.map.brand_name}]&nbsp;[${af.map.name}] <strong class="fb">&nbsp;时间</strong>：
          <html-el:text property="add_date_st" styleId="add_date_st" value="${af.map.add_date_st}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:left;width:85px;" styleClass="webinput" />
          &nbsp;至
          <html-el:text property="add_date_en" styleId="add_date_en" value="${af.map.add_date_en}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:left;width:85px;" styleClass="webinput" />
          &nbsp;
          <input name="button" type="submit" class="bgSearch" id="button" value="提交" /></td>
      </tr>
    </table>
  </html-el:form>
</div>
<div class="rtabcont1">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr class="title_top">
      <td rowspan="2" width="5%" align="center" nowrap="nowrap" >业务类别</td>
      <td rowspan="2" align="center" nowrap="nowrap" >业务日期</td>
      <td rowspan="2" width="" align="center" nowrap="nowrap" >交易单位</td>
      <td colspan="3" align="center" nowrap="nowrap" >日常进货</td>
      <td colspan="3" align="center" nowrap="nowrap" >日常出货（成本）</td>
      <td colspan="3" align="center" nowrap="nowrap" >盘盈/盘亏（-）</td>
      <td colspan="3" align="center" nowrap="nowrap" >期末结存（成本）</td>
    </tr>
    <tr class="title_top">
      <td align="center" nowrap="nowrap" >数量</td>
      <td align="center" nowrap="nowrap" >单价</td>
      <td align="center" nowrap="nowrap" >金额</td>
      <td align="center" nowrap="nowrap" >数量</td>
      <td align="center" nowrap="nowrap" >单价</td>
      <td align="center" nowrap="nowrap" >金额</td>
      <td align="center" nowrap="nowrap" >数量</td>
      <td align="center" nowrap="nowrap" >单价</td>
      <td align="center" nowrap="nowrap" >金额</td>
      <td align="center" nowrap="nowrap" >数量</td>
      <td align="center" nowrap="nowrap" >单价</td>
      <td align="center" nowrap="nowrap" >金额</td>
    </tr>
    <tr>
      <td colspan="3" align="left" nowrap="nowrap" ><font class="redbig" style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;合计</font></td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.rcjh_count_total}" var="rcjh_count_total" pattern="0" />
        ${rcjh_count_total}</td>
      <td align="center" nowrap="nowrap" ></td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.rcjh_money_total}" type="currency" /></td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.rcch_count_total}" var="rcch_count_total" pattern="0" />
        ${rcch_count_total}</td>
      <td align="center" nowrap="nowrap" ></td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.rcch_money_total}" type="currency" /></td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.pc_count_total}" var="pc_count_total" pattern="0" />
        ${pc_count_total }</td>
      <td align="right" nowrap="nowrap" >&nbsp;</td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.pc_money_total}" type="currency" /></td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.qmjc_count_total}" var="qmjc_count_total" pattern="0" />
        ${qmjc_count_total}</td>
      <td align="center" nowrap="nowrap" ></td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.qmjc_money_total}" type="currency" /></td>
    </tr>
    <tr>
      <td align="center" nowrap="nowrap">期初</td>
      <td align="center" nowrap="nowrap"></td>
      <td align="center" nowrap="nowrap"></td>
      <td align="center" nowrap="nowrap"></td>
      <td align="center" nowrap="nowrap"></td>
      <td align="center" nowrap="nowrap"></td>
      <td align="center" nowrap="nowrap"></td>
      <td align="center" nowrap="nowrap"></td>
      <td align="center" nowrap="nowrap"></td>
      <td align="right" nowrap="nowrap">&nbsp;</td>
      <td align="right" nowrap="nowrap">&nbsp;</td>
      <td align="right" nowrap="nowrap">&nbsp;</td>
      <td align="right" nowrap="nowrap"><fmt:formatNumber value="${af.map.init_count}" var="init_count" pattern="0" />
        ${init_count}</td>
      <td align="right" nowrap="nowrap"><fmt:formatNumber value="${af.map.cb}" type="currency" /></td>
      <td align="right" nowrap="nowrap"><fmt:formatNumber value="${af.map.qc_money}" type="currency" /></td>
    </tr>
    <c:forEach items="${stockList}" var="cur">
      <c:set var="isCh" value="false" />
      <c:set var="isJh" value="false" />
      <c:set var="isPc" value="false" />
      <c:set var="isFx" value="false" />
      <c:if test="${cur.map.type eq 1}">
        <c:set var="isCh" value="true" />
      </c:if>
      <c:if test="${cur.map.type eq 0}">
        <c:set var="isJh" value="true" />
      </c:if>
      <c:if test="${cur.map.type eq 2}">
        <c:set var="isPc" value="true" />
      </c:if>
      <c:if test="${cur.map.type eq 3}">
        <c:set var="isFx" value="true" />
      </c:if>
      <tr>
        <td align="center" nowrap="nowrap"><c:if test="${isCh}">出货</c:if>
          <c:if test="${isJh}">进货</c:if>
          <c:if test="${isPc}">盘存</c:if>
          <c:if test="${isFx}">分销</c:if></td>
        <td align="center" nowrap="nowrap">${cur.map.biz_date}</td>
        <td align="left" nowrap="nowrap">${cur.map.name}</td>
        <td align="right" nowrap="nowrap"><c:if test="${isJh}">
            <fmt:formatNumber value="${cur.map.count}" var="count" pattern="0" />
            ${count}</c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isJh}">
            <fmt:formatNumber value="${cur.map.price}" type="currency" />
          </c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isJh}">
            <fmt:formatNumber value="${cur.rcjh_money}" type="currency" />
          </c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isCh or isFx}">
            <fmt:formatNumber value="${cur.map.count}" var="count" pattern="0" />
            ${count}</c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isCh or isFx}">
            <fmt:formatNumber value="${cur.map.cost_price}" type="currency" />
          </c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isCh or isFx}">
            <fmt:formatNumber value="${cur.rcch_money}" type="currency" />
          </c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isPc}">
            <fmt:formatNumber value="${cur.map.count}" var="count" pattern="0" />
            ${count}</c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isPc}">
            <fmt:formatNumber value="${cur.map.price}" type="currency" />
          </c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isPc}">
            <fmt:formatNumber value="${cur.pc_money}" type="currency" />
          </c:if></td>
        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.qmjc_count}" var="qmjc_count" pattern="0" />
          ${qmjc_count}</td>
        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.qmjc_price}" type="currency" /></td>
        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.qmjc_money}" type="currency" /></td>
      </tr>
    </c:forEach>
  </table>
</div>
<div style="padding-left: 5px;">
  <input type="button" name="return" id="btn_back" class="bgButtonBack" value=" 返 回 " onclick="history.back();" />
</div>
<c:url value="" var="expPara">
  <c:param name="method" value="toExcelForDetails"/>
  <c:param name="type" value="sskcDetails"/>
  <c:param name="keySeq" value="${af.map.keySeq}"/>
  <c:param name="add_date_lt" value="${af.map.add_date_st}"/>
  <c:param name="add_date_gt" value="${af.map.add_date_en}"/>
  <c:param name="id" value="${af.map.id}"/>
</c:url>
<!--<div class="rtabcont1">
<html-el:form action="/JxcStock.do${expPara}">
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-left:5px;padding-top:10px">
  <tr>
    <td height="26">
      <div class="left">
        <input name="button" type="button" class="bgButton" id="button" value=" 打 印 " onclick="openChild()" />
        <input name="button" type="button" class="bgButton" id="toExcel" value=" 导 出 " />
        <input type="button" class="bgButton" value=" 返 回 " onclick="history.back();" />
      </div>
    </td>
  </tr>
</table>
</html-el:form>
</div>-->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
});

var type = "sskcDetails";
var keySeq = "${af.map.keySeq}";
var add_date_st = "${af.map.add_date_st}";
var add_date_en = "${af.map.add_date_en}";
var id = "${af.map.id}";
var param = "type=" + type + "&keySeq=" + keySeq + "&add_date_lt=" + add_date_st + "&add_date_gt=" + add_date_en + "&id=" + id;
//alert(param);
function openChild(){
	window.showModalDialog("?method=print&"+param, window, "dialogWidth:900px;status:no;dialogHeight:580px");
}
$("#toExcel").click(function(){
	this.form.submit();
	//window.open("?method=toExcelForDetails&" + param, "下载页面", "height=100,width=100");
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>