<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<title>月销售及安装/配送信息汇总表</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
<div class="rtabcont1">
  <html-el:form action="/JnhmEntpSellDetailsReport">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" />
     <html-el:hidden property="keySeq" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td width="10%" nowrap="nowrap">
        产品大类：
        <html-el:select property="pd_type_id" styleId="pd_type_id">
        	<html-el:option value="">请选择...</html-el:option>
        	<html-el:option value="2">平板电视</html-el:option>
        	<html-el:option value="4">电动洗衣机</html-el:option>
        	<html-el:option value="1">电冰箱</html-el:option>
        </html-el:select>
	  &nbsp;&nbsp;产品型号：
        <html-el:text property="pd_name_like" styleId="pd_name_like" maxlength="10"></html-el:text>
          &nbsp;&nbsp;<span style="color:#f00;">*</span>销售年月：
		<html-el:select property="yyyy" styleId="yyyy" >
              <html-el:option value="2012">2012年</html-el:option>
              <html-el:option value="2013">2013年</html-el:option>
              <html-el:option value="2014">2014年</html-el:option>
              <html-el:option value="2015">2015年</html-el:option>
            </html-el:select>
            <html-el:select property="mm" styleId="mm" >
              <html-el:option value="01">01月</html-el:option>
              <html-el:option value="02">02月</html-el:option>
              <html-el:option value="03">03月</html-el:option>
              <html-el:option value="04">04月</html-el:option>
              <html-el:option value="05">05月</html-el:option>
              <html-el:option value="06">06月</html-el:option>
              <html-el:option value="07">07月</html-el:option>
              <html-el:option value="08">08月</html-el:option>
              <html-el:option value="09">09月</html-el:option>
              <html-el:option value="10">10月</html-el:option>
              <html-el:option value="11">11月</html-el:option>
              <html-el:option value="12">12月</html-el:option>
            </html-el:select>
            
       &nbsp;&nbsp; <html-el:button value="搜 索" styleClass="bgSearch" property=""/></td>
      </tr>
      <tr><td><span id="searchTip" class="jxcTip">默认不显示数据，点击搜索显示数据</span></td> </tr>
    </table>
  </html-el:form>
</div>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
</div>
  <c:if test="${not empty entityList}">
<c:url value="" var="expPara">
	<c:param name="method" value="toExcelForList"/>
	<c:param name="keySeq" value="${af.map.keySeq}"/>
	<c:param name="yyyy" value="${af.map.yyyy}"/>
	<c:param name="mm" value="${af.map.mm}"/>
	<c:param name="pd_type_id" value="${af.map.pd_type_id}"/>
	<c:param name="pd_name_like" value="${af.map.pd_name_like}"/>
</c:url>
	<div class="rtabcont1">
	<html-el:form action="/JnhmEntpSellDetailsReport.do${expPara}">
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
      ${af.map.yyyy}年${af.map.mm}月${customer_name}销售及安装/配送信息汇总表</div>
    <div style="overflow-x:auto;height:400px;">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th rowspan="2">序号</th>
          <th colspan="7" align="center">消费者信息</th>
          <th colspan="5" align="center">销售信息</th>
          <th colspan="2" align="center">终端经销商信息</th>
        </tr>
        <tr>
          <th nowrap="nowrap" align="center">消费者名称</th>
          <th nowrap="nowrap" align="center">身份证信息</th>
          <th nowrap="nowrap" align="center">联系电话</th>
          <th nowrap="nowrap" align="center">详细地址</th>
          <th nowrap="nowrap" align="center">消费者实际所在地行政区划代码</th>
          <th nowrap="nowrap" align="center">消费者实际所在地行政区划代码对应名称</th>
          <th nowrap="nowrap" align="center">销售时间</th>
          <th nowrap="nowrap" align="center">企业名称</th>
          <th nowrap="nowrap" align="center">产品型号</th>
          <th nowrap="nowrap" align="center">产品唯一编码</th>
          <th nowrap="nowrap" align="center">销售价格（元）</th>
          <th nowrap="nowrap" align="center">发票号</th>
          <th nowrap="nowrap" align="center">终端销售机构名称</th>
          <th nowrap="nowrap" align="center">终端销售机构组织机构代码</th>
        </tr>
        <c:if test="${not empty entityList}">
	        <c:forEach var="cur" items="${entityList}" varStatus="vs">
	          <tr align="center">
	            <td align="center">${vs.count}</td>
	            <td align="left">${fn:escapeXml(cur.map.customer_name)}</td>
	            <td align="left" nowrap="nowrap" style="mso-number-format:'\@';">${cur.map.customer_cus_idcard}</td>
	            <td align="left">${cur.map.customer_tel}</td>
	            <td align="left">${cur.map.customer_addr}</td>
	            <td align="left">${cur.map.customer_shop_p_index}</td>
	            <td align="left">${cur.map.customer_p_name}</td>
	            <td nowrap="nowrap"><fmt:formatDate value="${cur.sell_date}" pattern="yyyy-MM-dd"/></td>
	            <td align="left">康佳</td>
	            <td align="left">${fn:escapeXml(cur.jxcSellBillDetailsEntity.pd_name)}</td>
	            <td align="left" style="mso-number-format:'\@';">${cur.jxcSellBillDetailsEntity.pd_unique_code}</td>
	            <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><fmt:formatNumber value="${cur.pay_money}" type="currency" /></td>
	            <td align="left" style="mso-number-format:'\@';">${cur.jxcSellBillDetailsEntity.invoice_bh}</td>
	            <td align="left">${fn:escapeXml(cur.map.shop_name)}</td>
	            <td align="left">${fn:escapeXml(cur.map.org_sn)}</td>
	          </tr>
	        </c:forEach>
        </c:if>
        <c:if test="${empty entityList}">
        <tr><td height="30" align="center" nowrap="nowrap" colspan="15"><font color="red">无数据</font></td></tr>
        </c:if>
      </table>
    </div>
  </div>

<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript">//<![CDATA[
var f=document.forms[0];
$(document).ready(function(){
	$("#toExcel").click(function(){
		this.form.submit();
	});
	$(".bgSearch").click(function(){
		$("#start_date").attr("dataType", "Require").attr("msg", "请选择起始日期");
		$("#end_date").attr("dataType", "Require").attr("msg", "请选择结束日期");
	   	var s_time = $("#start_date").val();
		var e_time = $("#end_date").val();
		if ("" != s_time && "" != e_time && s_time > e_time) {
			alert("起始日期不能大于结束日期！");
			return false;
		}
		f.submit();
	});  
});   

var keySeq = "${af.map.keySeq}";
var yyyy = "${af.map.yyyy}";
var mm = "${af.map.mm}";
var pd_type_id = "${af.map.pd_type_id}";
var pd_name_like = "${af.map.pd_name_like}";
var param ="keySeq=" + keySeq + "&yyyy=" + yyyy+ "&mm=" + mm + "&pd_type_id=" + pd_type_id + "&pd_name_like=" + pd_name_like + "&random=" + Math.random();

function openChild(){
	window.showModalDialog("?method=print&" + encodeURI(param), window, "dialogWidth:900px;status:no;dialogHeight:580px"); 
}

//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>