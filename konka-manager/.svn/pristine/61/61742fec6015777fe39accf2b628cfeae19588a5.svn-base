<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>实时库存</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
<div class="rtabcont1" >
  <html-el:form action="admin/KonkaRealTimeStock3.do">
    <html-el:hidden property="method" value="list"/>
    <html-el:hidden property="mod_id" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
         <!-- 
         <td nowrap="nowrap" width="220">
         <strong class="fb">类别</strong>：
         <html-el:radio property="customer_type" styleId="customer_type_1" value="1"><label for="customer_type_1">单客户</label></html-el:radio>
         <html-el:radio property="customer_type" styleId="customer_type_5" value="5"><label for="customer_type_5">经办</label></html-el:radio>
         <html-el:radio property="customer_type" styleId="customer_type_3" value="3"><label for="customer_type_3">分公司</label></html-el:radio>
         <html-el:radio property="customer_type" styleId="customer_type_2" value="2"><label for="customer_type_2">客户群</label></html-el:radio>
         <html-el:radio property="customer_type" styleId="customer_type_4" value="4"><label for="customer_type_4">分大区</label></html-el:radio> 
         </td>-->
         <td> 
         <strong class="fb">R3编码　</strong>：<html-el:text property="keyword" styleId="keyword"></html-el:text>
         <strong class="fb">R3网点名称　</strong>：<html-el:text property="customer_name_like" styleId="customer_name_like"></html-el:text>
         </td>
         
         <td id="keyword_select" style="display:none"> 
         <strong class="fb">经办名称</strong>：
         <html-el:select property="handle_name" styleId="handle_name">
         	<html-el:option value="">请选择</html-el:option>
         	<c:forEach items="${handleList}" var="cur">
         		<html-el:option value="${cur.handle_name}">${cur.handle_name}</html-el:option>
         	</c:forEach>
         </html-el:select>
         </td>
      </tr>
      <tr>
        <td nowrap="nowrap" colspan="3"><strong class="fb">类型</strong>：
          <html-el:select property="cls_id" styleId="cls_id" value="${af.map.cls_id}">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach items="${basePdClassList}" var="cur" varStatus="vs">
                <html-el:option value="${cur.cls_id}">${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select>
          &nbsp; <strong class="fb">型号</strong>：
          <html-el:text property="md_name_like" style="width:75px" />
          &nbsp;<strong class="fb">时间</strong>：
          <html-el:text styleClass="webinput" property="add_date_st" styleId="add_date_st" value="${af.map.add_date_st}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:left;width:80px;" />
          &nbsp;至
          <html-el:text styleClass="webinput" property="add_date_en" styleId="add_date_en" value="${af.map.add_date_en}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:left;width:80px;" />
          &nbsp;
         <!--  <html-el:checkbox property="is_show_0_store" styleId="is_show_0_store" />
          <label for="is_show_0_store" title="点击，显示零库存产品">&nbsp;零库存</label> -->
          &nbsp;
          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" /></td>
      </tr>
    </table>
  </html-el:form>
</div>

<div class="rtabcont1" >
  <form id="listForm" name="listForm" method="post" action="KonkaRealTimeStock3.do">
  	<input type="button" id="btn_toExcel" value="导出"></input>
  	<span style="color:red">注：如果按R3网点名称查询，请尽量填写完整的名称，方便查询所需的网点信息。如有问题请点击右上角“帮助”或联系管理员</span>
  	<div id="divExcel" title="客户进销存-${af.map.cus_name}-<fmt:formatDate value="${af.map.startDate}" pattern="yyyy-MM-dd" />-<fmt:formatDate value="${af.map.endDate}" pattern="yyyy-MM-dd" />">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    <tr class="title_top">
     <!--  <td rowspan="2" width="25" align="center"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td> -->
      <td rowspan="2" align="center" >序号</td>
      <td rowspan="2" align="center" >产品型号</td>
      <td colspan="3" align="center">期初结存</td>
      <td colspan="3" align="center">日常进货</td>
      <td colspan="5" align="center">日常出货<br/>
        （成本）</td>
      <td colspan="3" align="center">期末结存</td>
    </tr>
    <tr class="title_top">
      <td align="center">数量</td>
      <td align="center">单价</td>
      <td align="center">金额</td>
      <td align="center">数量</td>
      <td align="center">单价</td>
      <td align="center">金额</td>
      <td align="center">数量</td>
      <td align="center">单价</td>
      <td align="center">成本金额</td>
      <td align="center">零售单价</td>
      <td align="center">零售金额</td>
      <td align="center">数量</td>
      <td align="center">单价</td>
      <td align="center">金额</td>
    </tr>
    <c:if test="${af.map.customer_type eq 1 }">
     <tr>
      <td colspan="2" align="center" ><font class="redbig" style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合计</font></td>
      <td align="right"><fmt:formatNumber value="${af.map.totleCurrentCountStart}"  pattern="0"/></td>
      <td align="right">&nbsp;</td>
      <td align="right"><fmt:formatNumber value="${af.map.totleCurrentCostStart}" type="currency" pattern="0.00" /></td>
       <td align="right"><fmt:formatNumber value="${af.map.totleDataImportSum}"  pattern="0" /></td>
      <td align="right">&nbsp;</td>
      <td align="right"><fmt:formatNumber value="${af.map.totleDataImportMoneySum }" type="currency" pattern="0.00" /></td>
      <td align="right"><fmt:formatNumber value="${af.map.totleSellCount}" pattern="0" /></td>
      <td align="right">&nbsp;</td>
      <td align="right"><fmt:formatNumber value="${af.map.totleSellCost}" type="currency" pattern="0.00" /></td>
      <td align="right">&nbsp;</td>
      <td align="right"><fmt:formatNumber value="${af.map.totleSellMoney}" type="currency" pattern="0.00" /></td>
      <td align="right"><fmt:formatNumber value="${af.map.totleCurrentCountEnd}" pattern="0" /></td>
      <td align="right"></td>
      <td align="right"><fmt:formatNumber value="${af.map.totleCurrentCostEnd}" type="currency" pattern="0.00" /></td>
    </tr>
    </c:if> 
      <c:set var="key" value="0"/>
    <c:forEach items="${stockList}" var="s" varStatus="vu">
     <c:choose >
      <c:when test="${ 0 ne s.map.dataImportMoneySum  or  0 ne s.map.sellMoney  or not empty s.map.currentCostEnd  and not empty s.map.md_name }">
      <c:set var="key" value="${key+1}"/>
	      <tr>
	        <td align="center">${key}</td>
			<td>${s.map.md_name }</td>
		<td align="right">
			<c:if test="${not empty s.map.currentCountStart}">
				<fmt:formatNumber value="${s.map.currentCountStart}" pattern="0"/>
			</c:if>
			<c:if test="${empty s.map.currentCountStart}">
			0
			</c:if>
			</td>
			<td align="right">
			<c:if test="${not empty s.map.currentPriceStart}">
				<fmt:formatNumber value="${s.map.currentPriceStart}" type="currency" pattern="0.00" />
			</c:if>
			<c:if test="${empty s.map.currentPriceStart}">
			0.00
			</c:if>
			</td>
			<td align="right">
			<c:if test="${not empty s.map.currentCostStart}">
				<fmt:formatNumber value="${s.map.currentCostStart}" type="currency" pattern="0.00" />
			</c:if>
			<c:if test="${empty s.map.currentCostStart}">
			0.00
			</c:if>
			</td>
			<td align="right">
			<c:if test="${not empty s.map.dataImportSum}">
			<fmt:formatNumber value="${s.map.dataImportSum}" pattern="0"/> 
			</c:if>
			<c:if test="${empty s.map.dataImportSum}">
			0
			</c:if>
			</td>
			<td align="right">
			<c:if test="${not empty s.map.dataImportPrice}">
				<fmt:formatNumber value="${s.map.dataImportPrice}" type="currency" pattern="0.00" />
			</c:if>
			<c:if test="${empty s.map.dataImportPrice}">
			0.00
			</c:if>
			</td>
			<td align="right">
			<c:if test="${not empty s.map.dataImportMoneySum}">
				<fmt:formatNumber value="${s.map.dataImportMoneySum}" type="currency" pattern="0.00" />
			</c:if>
			<c:if test="${empty s.map.dataImportMoneySum}">
			0.00
			</c:if>
			</td>
			<td align="right">
			<c:if test="${not empty s.map.sellCount}">
				<fmt:formatNumber value="${s.map.sellCount}" pattern="0"/>
			</c:if>
			<c:if test="${empty s.map.sellCount}">
			0
			</c:if>
			</td>
			<td align="right">
			<c:if test="${not empty s.map.sellPrice}">
				<fmt:formatNumber value="${s.map.sellPrice}" type="currency" pattern="0.00" />
			</c:if>
			<c:if test="${empty s.map.sellPrice}">
			0.00
			</c:if>
			</td>
			<td align="right">
			<c:if test="${not empty s.map.sellCost}">
				<fmt:formatNumber value="${s.map.sellCost}" type="currency" pattern="0.00" />
			</c:if>
			<c:if test="${ empty s.map.sellCost}">
			0.00
			</c:if>
			</td>
			<td align="right">
			<c:if test="${not empty s.map.price}">
				<fmt:formatNumber value="${s.map.price}" type="currency" pattern="0.00" />
			</c:if>
			<c:if test="${empty s.map.price}">
			0.00
			</c:if>
			</td>
			<td align="right">
			<c:if test="${not empty s.map.sellMoney}">
			<fmt:formatNumber value="${s.map.sellMoney}" type="currency" pattern="0.00" />
			</c:if>
			<c:if test="${empty s.map.sellMoney}">
			0.00
			</c:if>
			</td>
			<td align="right">
			<c:if test="${not empty s.map.currentCountEnd}">
			<fmt:formatNumber value="${s.map.currentCountEnd}" pattern="0"/>
			</c:if>
			<c:if test="${empty s.map.currentCountEnd}">
			0
			</c:if>
			</td>
			<td align="right">
			<c:if test="${not empty s.map.currentPriceEnd}">
			<fmt:formatNumber value="${s.map.currentPriceEnd}" type="currency" pattern="0.00" />
			</c:if>
			<c:if test="${empty s.map.currentPriceEnd}">
			0.00
			</c:if>
			</td>
			<td align="right">
			<c:if test="${not empty s.map.currentCostEnd}">
			<fmt:formatNumber value="${s.map.currentCostEnd}" type="currency" pattern="0.00" />
			</c:if>
			<c:if test="${empty s.map.currentCostEnd}">
			0.00
			</c:if>
			</td>
	      </tr>
      </c:when>
     </c:choose>
    </c:forEach>
  </table>
  </div>
  </form>
</div>
 </div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
//	getCountTotal(); //纵向
   $("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });

	$("form").submit(function(){
	   var keyword = $("#keyword").val();
	   var customer_name = $("#customer_name_like").val();
	   var isSubmit = Validator.Validate(this, 1);	
	   if (isSubmit) {
		   if(e_d.val() < b_d.val()) {
				alert("开始日期必须小于等于结束日期!");
				return false;
		   }
		   
		   if(keyword == ""&&customer_name == ""){
			   alert("请填写R3编码或R3网点名称中任意一个!");
			   return false;
		   }
	   }
	   return isSubmit;
	});
});

$("#btn_toExcel").click(function (){
	toExcel('divExcel', '${ctx}/manager/admin/KonkaRealTimeStock3.do?method=toExcel');
});
function confirmDispatchAll(form) {
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("请至少选择一个型号！");
	} else {
			form.submit();
	}
}
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>