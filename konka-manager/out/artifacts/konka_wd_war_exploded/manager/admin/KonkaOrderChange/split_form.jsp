<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_div">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaOrderChange" method="post">
      <html-el:hidden property="order_id" styleId="order_id" value="${af.map.id}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="flag" styleId="flag" value="${flag}" />
      <html-el:hidden property="audit_proc_cond_flag" styleId="audit_proc_cond_flag" value="${has_proc_cond}" />
      <html-el:hidden property="customer_type" styleId="customer_type" value="${customer_type}" />
      <html-el:hidden property="flag_Zb_role" styleId="flag_Zb_role" value="${flag_Zb_role}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
        <tr>
          <th colspan="4">订单基本信息</th>
        </tr>
        <tr>
          <td width="15%" class="title_item">交易流水号：</td>
          <td colspan="3">${fn:escapeXml(af.map.trade_index)}</td>
        </tr>
        <tr>
<!--          <td class="title_item" nowrap="nowrap">客户名称：</td>-->
<!--          <td colspan="3">${fn:escapeXml(af.map.user_shop_name)}</td>-->
          
          
          <td class="title_item" width="15%">客户名称：</td>
							<td width="35%">${fn:escapeXml(af.map.user_shop_name)}</td>
							<td class="title_item" width="15%">售达方编码：</td>
							<td width="15%">${r3_code}</td>
        </tr>
        <tr>
          <td class="title_item">提交日期：</td>
          <td ><fmt:formatDate value="${af.map.order_date}" pattern="yyyy-MM-dd"/></td>
           <td class="title_item">创建日期：</td>
          <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
         <td class="title_item" width="15%" >制单人：</td>
		<td width="35%" >${af.map.add_user_name}</td>
		<td class="title_item" width="15%">业务员：</td>
		<td width="35%">${ywy_user_name}</td>
        </tr>
        <c:if test="${not empty af.map.freight}">
        <tr>
          <td class="title_item">运费：</td>
          <td colspan="3"> ${fn:escapeXml(af.map.freight)} </td>
        </tr>
        </c:if>
        <tr>
          <td class="title_item">备注：</td>
          <td colspan="3">${fn:escapeXml(af.map.remark)}</td>
        </tr>        
        <c:if test="${not empty af.map.konkaOrderInfoDetailsList}">
        <tr>
          <td colspan="4"><strong>拆分型号：</strong><br /><table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable1">
					<tr >
						<td width="12%" nowrap="nowrap" align="center">产品型号</td>
						<td width="8%" nowrap="nowrap" align="center">数量</td>
		                <td width="5%" nowrap="nowrap" align="center">单位</td>
		                <td width="8%" nowrap="nowrap" align="center">单价（元）</td>
		                <td width="8%" nowrap="nowrap" align="center">金额（元）</td>
		                <td width="10%" nowrap="nowrap" align="center">折扣金额-RB00</td>
		                <td width="10%" nowrap="nowrap" align="center">折扣(%)-K007</td>
		                <td width="8%" nowrap="nowrap" align="center">折后金额</td>
		                <td width="12%" nowrap="nowrap" align="center">工厂/仓位xxxxxxxxx</td>
		                <td nowrap="nowrap" align="center">产品备注</td>
		                <td width="5%" align="center">拆分</td>
      				</tr>
      				<tbody id="details_body">
      				<c:forEach items="${af.map.konkaOrderInfoDetailsList}" var="cur">
        			<tr>
            			<td align="center"><span id="pd_name_span-${cur.id}">${fn:escapeXml(cur.pd_name)}</span><html-el:hidden property="details_id" value="${cur.id}" /></td>
						<td align="center">
						${cur.good_count}</td>
						<td align="center">${fn:escapeXml(cur.good_unit)}</td>
						<td align="center">${cur.good_price}</td>
						<td align="center">${cur.good_sum_price}</td>
						<td align="center" style="font-weight:800;">${cur.good_discount_price}</td>
						<!-- 折扣比例（%） -->
						<td align="center">${cur.good_discount}</td>
						
						 <td align="center">${cur.good_sum_price+cur.good_discount_price+cur.good_sum_price*cur.good_discount/100}</td>
						 <td>
						 <c:if test="${!flag_Zb_role}">
						 <select name="store_key">
							<option value="">请选择...</option>
							<c:forEach items="${storeList}" var="cur_s">
								<c:set var="k" value="[${cur_s.fac_sn}-${cur_s.store_sn}]${cur_s.store_desc}" />
								<c:if test="${cur.store_key eq k}">
									<option value="${k}" selected="selected">${k}</option>
								</c:if>
								<c:if test="${cur.store_key ne k}">
									<option value="${k}">${k}</option>
								</c:if>
							</c:forEach>
						</select>
						</c:if>
						
						<c:if test="${flag_Zb_role}">
						<c:forEach items="${storeList}" var="cur_s">
						<c:set var="k" value="[${cur_s.fac_sn}-${cur_s.store_sn}]${cur_s.store_desc}" />
						<c:if test="${k eq cur.store_key}">${k}</c:if>
						</c:forEach>
						</c:if>
						<c:if test="${empty storeList}">
						 ${cur.store_key}
						</c:if>
						</td>
						<td>
						<c:if test="${not empty fn:escapeXml(cur.pd_remark)}">
					${fn:escapeXml(cur.pd_remark)}
						</c:if>
						</td>
						
						<td align="center">
						<html-el:checkbox property="details_id" value="${cur.id}"></html-el:checkbox>
						</td>
					</tr>
				    </c:forEach>
				    
				    
				    </tbody>
       			</table>
            </td>
        </tr>
        </c:if>
      
      
       <tr><td colspan="11" align="center"><label>
              <input class="but4" type="button" name="Submit4" id="split" value="拆单" />
              <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" />
            </label></td></tr>
      </table>
     
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	});
//]]></script>
<style type="text/css">
.title_item,.title_item1{ background-color: #F5F4F4;
    font-weight: bold;
    text-align: right;}
.title_item2{ background-color: #F5F4F4;
    font-weight: bold;
    text-align: right;}
.but4{text-align:right;width:67px;}
.but5{text-align:right;width:67px;}
.one{background-color:#EBE3E2;}
.tabtt1{background-color:#EBE3E2;}
</style>


<jsp:include page="/__analytics.jsp" />
</body>
</html>
