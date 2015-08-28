<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<title>补贴统计</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
<div class="rtabcont1">
  <html-el:form action="/JnhmAllowance">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" />
    <html-el:hidden property="keySeq" />
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td width="10%" nowrap="nowrap">产品大类：
        <html-el:select property="pd_type_id" styleId="pd_type_id">
        	<html-el:option value="">请选择...</html-el:option>
        	<html-el:option value="2">平板电视</html-el:option>
        	<html-el:option value="4">电动洗衣机</html-el:option>
        	<html-el:option value="1">电冰箱</html-el:option>
        </html-el:select>
	  &nbsp;&nbsp;产品型号：
        <html-el:text property="pd_name_like" styleId="pd_name_like" maxlength="10"></html-el:text>
          &nbsp;&nbsp;销售日期：
        <html-el:text property="sell_date_eq" styleId="sell_date_eq" size="9" maxlength="9" readonly="true" styleClass="webinput" style="cursor:pointer;text-align:left;width:70px;" onclick="new Calendar(2011, 2031, 0).show(this);"/>
       &nbsp;&nbsp; <html-el:button value="搜 索" styleClass="bgSearch" property=""/></td>
      </tr>
      <tr><td><span id="searchTip" class="jxcTip">默认不显示数据，点击搜索显示数据</span></td> </tr>
    </table>
  </html-el:form>
</div>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
</div>
<c:url value="" var="expPara">
	<c:param name="method" value="toExcelForList"/>
	<c:param name="keySeq" value="${af.map.keySeq}"/>
	<c:param name="sell_date_eq" value="${af.map.sell_date_eq}"/>
	<c:param name="pd_type_id" value="${af.map.pd_type_id}"/>
	<c:param name="pd_name_like" value="${af.map.pd_name_like}"/>
</c:url>
  <c:if test="${not empty entityList}">
	<div class="rtabcont1">
	<html-el:form action="/JnhmAllowance.do${expPara}">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-left:5px;padding-top:5px">
		  <tr>
		    <td height="5" align="left">
		      <div class="left">
		        <input name="button" type="button" class="bgButtonExport" id="toExcel" value="导出" />
		        <input name="button" type="button" class="bgButtonPrint" id="print" value="打印" onclick="openChild()" />
		      </div>
		    </td>
		  </tr>
		</table>
	</html-el:form>
</div>
</c:if>
<div class="rtabcont1">
    <div align="center" style="height:30px;font-size:20px;font-weight:bolder;">
     ${af.map.sell_date_eq}${customer_name}补贴统计</div>
    <div style="overflow-x:auto;height:400px;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tbody>
        <tr>
          <th width="5%" align="center" nowrap="nowrap">序号</th>
          <th width="10%" align="center" nowrap="nowrap">产品唯一编码</th>
          <th nowrap="nowrap" align="center">产品型号</th>
          <th width="10%" align="center" nowrap="nowrap">销售单号</th>
          <th width="10%" nowrap="nowrap" align="center">销售企业</th>
          <th width="6%" nowrap="nowrap" align="center">单价（元）</th>
          <th width="6%" nowrap="nowrap" align="center">补贴金额（元）</th>
          <th width="6%" nowrap="nowrap" align="center">购买人</th>
          <th width="8%" nowrap="nowrap" align="center">联系电话</th>
          <th width="8%" nowrap="nowrap" align="center">销售时间</th>
        </tr>
        <c:if test="${not empty entityList}">
        <c:forEach items="${entityList}" var="cur" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_unique_code)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.pd_name)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.map.sn)}</td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.map.shop_name)}</td>
            <td align="center" nowrap="nowrap"><span class="kz-price-12">
              <fmt:formatNumber value="${cur.map.pd_price}" type="currency" />
              </span></td>
            <td align="center" nowrap="nowrap"><span class="kz-price-12">
              <fmt:formatNumber value="${cur.map.pd_allowance_money}" type="currency" />
              </span></td>
            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.map.customer_name)}</td>
            <td align="center" nowrap="nowrap">
              <c:if test="${not empty cur.map.customer_tel}">${cur.map.customer_tel}</c:if>
              <c:if test="${empty cur.map.customer_tel}"><span style="color:gray;">未填写</span></c:if>
              </td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.sell_date}" pattern="yyyy-MM-dd" /></td>
          </tr>
        </c:forEach>
        <tr>
        <td colspan="6" align="left">合计</td>
        <td align="center" > <fmt:formatNumber value="${total_allowance_money}" type="currency" /></td>
        <td colspan="3" align="left">&nbsp;</td>
        </tr>
        </c:if>
        <c:if test="${empty entityList}">
        <tr><td height="30" align="center" nowrap="nowrap" colspan="10"><font color="red">无数据</font></td></tr>
        </c:if>
      </tbody>
    </table>
    
    
    </div>
  </div>

<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">//<![CDATA[
var f=document.forms[0];
$(document).ready(function(){
	$("#toExcel").click(function(){
		this.form.submit();
	});

	$("#sell_date_eq").attr("dataType", "Require").attr("msg", "请选择销售日期");
	$(".bgSearch").click(function(){
		if(Validator.Validate(f, 1)){
			f.submit();
		}
	});  
});  

var keySeq = "${af.map.keySeq}";
var sell_date_eq = "${af.map.sell_date_eq}";
var pd_type_id = "${af.map.pd_type_id}";
var pd_name_like = "${af.map.pd_name_like}";
var param ="keySeq=" + keySeq + "&sell_date_eq=" + sell_date_eq + "&pd_type_id=" + pd_type_id + "&pd_name_like=" + pd_name_like + "&random=" + Math.random();

function openChild(){
	window.showModalDialog("?method=print&" + encodeURI(param), window, "dialogWidth:900px;status:no;dialogHeight:580px"); 
}

//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>