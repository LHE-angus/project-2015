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
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
<div class="rtabcont1">
  <html-el:form action="/KonkaJxcStock.do?method=view">
    <html-el:hidden property="pd_id" />
    <html-el:hidden property="dept_id" />
    <html-el:hidden property="pd_type_id" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td align="left"><strong class="fb">产品</strong>：[${af.map.pd_type_name }]&nbsp;[${af.map.pd_name }]&nbsp; <strong class="fb">&nbsp;时间</strong>：
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
      <td rowspan="2" width="" align="center" nowrap="nowrap" >交易仓库</td>
      <td colspan="3" align="center" nowrap="nowrap" >日常进货（退货）</td>
      <td colspan="3" align="center" nowrap="nowrap" >日常发货（成本）</td>
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
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.jh_count_total - af.map.th_count_total}" var="jh_count_total" pattern="0" />
        ${jh_count_total}</td>
      <td align="center" nowrap="nowrap" ></td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.jh_money_total - af.map.th_money_total}" type="currency" /></td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.fh_count_total}" var="fh_count_total" pattern="0" />
        ${fh_count_total}</td>
      <td align="center" nowrap="nowrap" ></td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.fh_money_total}" type="currency" /></td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.pc_count_total}" var="pc_count_total" pattern="0" />
        ${pc_count_total }</td>
      <td align="right" nowrap="nowrap" >&nbsp;</td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.pc_money_total}" type="currency" /></td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.qm_count_total}" var="qm_count_total" pattern="0" />
        ${qm_count_total}</td>
      <td align="center" nowrap="nowrap" ></td>
      <td align="right" nowrap="nowrap" ><fmt:formatNumber value="${af.map.qm_money_total}" type="currency" /></td>
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
      <td align="right" nowrap="nowrap"><fmt:formatNumber value="${af.map.qc_count}" var="qc_count" pattern="0" />
        ${qc_count}</td>
      <td align="right" nowrap="nowrap"><fmt:formatNumber value="${af.map.qc_price}" type="currency" /></td>
      <td align="right" nowrap="nowrap"><fmt:formatNumber value="${af.map.qc_money}" type="currency" /></td>
    </tr>
    <c:forEach items="${stockList}" var="cur">
      <c:set var="isJh" value="false" />
      <c:set var="isCh" value="false" />
      <c:set var="isTh" value="false" />
      <c:set var="isPc" value="false" />
      <c:if test="${cur.map.type eq 0}">
        <c:set var="isJh" value="true" />
      </c:if>
      <c:if test="${cur.map.type eq 2}">
        <c:set var="isTh" value="true" />
      </c:if>
      <c:if test="${cur.map.type eq 1}">
        <c:set var="isCh" value="true" />
      </c:if>
      <c:if test="${cur.map.type eq 3}">
        <c:set var="isPc" value="true" />
      </c:if>
      <tr>
        <td align="center" nowrap="nowrap"><c:if test="${isCh}">发货</c:if>
          <c:if test="${isJh}">进货</c:if>
          <c:if test="${isPc}">盘存</c:if>
          <c:if test="${isTh}">退货</c:if></td>
        <td align="center" nowrap="nowrap"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${cur.add_date}"></fmt:formatDate></td>
        <td align="left" nowrap="nowrap">${cur.store_name}</td>
        <td align="right" nowrap="nowrap"><c:if test="${isJh or isTh}">
            <fmt:formatNumber value="${cur.map.count}" var="count" pattern="0" />
            ${count}</c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isJh or isTh}">
            <fmt:formatNumber value="${cur.map.price}" type="currency" />
          </c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isJh or isTh}">
            <fmt:formatNumber value="${cur.map.money}" type="currency" />
          </c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isCh }">
            <fmt:formatNumber value="${cur.map.count}" var="count" pattern="0" />
            ${count}</c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isCh }">
            <fmt:formatNumber value="${cur.map.price}" type="currency" />
          </c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isCh }">
            <fmt:formatNumber value="${cur.map.money}" type="currency" />
          </c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isPc}">
            <fmt:formatNumber value="${cur.map.count}" var="count" pattern="0" />
            ${count}</c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isPc}">
            <fmt:formatNumber value="${cur.map.price}" type="currency" />
          </c:if></td>
        <td align="right" nowrap="nowrap"><c:if test="${isPc}">
            <fmt:formatNumber value="${cur.map.money}" type="currency" />
          </c:if></td>
        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.qm_count}" var="qm_count" pattern="0" />
          ${qm_count}</td>
        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.qm_price}" type="currency" /></td>
        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.qm_money}" type="currency" /></td>
      </tr>
    </c:forEach>
      <tr>
      	<td colspan="15" align="center"><input type="button" name="return" id="btn_back" class="bgButtonBack" value=" 返 回 " onclick="history.back();" /></td>
      </tr>
  </table>
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