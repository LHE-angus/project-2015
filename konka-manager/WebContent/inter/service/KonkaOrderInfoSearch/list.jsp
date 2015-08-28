<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functionsx" prefix="fnx"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="http://qdgl.konka.com/styles/global.css" rel="stylesheet" type="text/css" />
<link href="http://qdgl.konka.com/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.rtable1 td {
	padding:2px 5px;
}
.filed_border{
	border-left: 1px solid #ccc;;
	border-right: 1px solid #ccc;;
	border-bottom:1px solid #ccc;;
}
</style>

<script type="text/javascript" src="http://qdgl.konka.com/commons/scripts/jquery.js"></script> 
</head>
<body>
	<div align="left">提交日期： ${start_date} 至 ${end_date}</div>
    <div style="overflow-x:auto;">
    <table width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
     <tr class="tabtt1">
<!--         <td width="5%" nowrap="nowrap" align="center">订单ID</td> -->
       <td width="5%" nowrap="nowrap" align="center">操作</td>
        <td width="10%" nowrap="nowrap" align="center">交易流水号</td>
        <td width="8%" nowrap="nowrap" align="center">提交日期</td>
        <td width="8%" nowrap="nowrap" align="center">R3客户编码</td>
        <td nowrap="nowrap" align="center">客户名称</td>
        <td nowrap="nowrap" align="center">客户类型</td>
        <td width="6%" nowrap="nowrap" align="center">数量</td>
        <td width="6%" nowrap="nowrap" align="center">金额￥</td>
        <td width="6%" nowrap="nowrap" align="center">折扣￥</td>
        <td width="6%" nowrap="nowrap" align="center">订单状态</td>
        <td width="5%" nowrap="nowrap" align="center">R3单号</td>
        <td width="5%" nowrap="nowrap" align="center">订单变更</td>
        <td width="6%" nowrap="nowrap" align="center">发货状态</td>
        <td width="5%" nowrap="nowrap" align="center">R3物流单号</td>
        <td width="5%" nowrap="nowrap" align="center">发货时间</td>
        <td width="5%" nowrap="nowrap" align="center">收货时间</td>
        <td nowrap="nowrap" align="center">分公司</td>
        <td nowrap="nowrap" align="center">经办</td>
        <td width="6%" nowrap="nowrap" align="center">变更短信</td>
        <td width="5%" nowrap="nowrap" align="center">流程</td>
        <td width="5%" nowrap="nowrap" align="center">订单来源</td>
       <td width="5%" nowrap="nowrap" align="center">创建日期</td>
       <td width="5%" nowrap="nowrap" align="center">待审核角色</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          	<td align="center" nowrap="nowrap">
          		<a href="http://192.168.2.216:18080/inter/service/KonkaOIAPInterface.do?method=list&order_id=${cur.id}&licenses_sn=${licenses_sn}&user_id=${user_id}&user_key=${user_key }">审核记录</a>
          		
          		<a href="http://192.168.2.216:18080/inter/service/KonkaOAInterface.do?method=list&order_id=${cur.id}&licenses_sn=${licenses_sn}&user_id=${user_id}&user_key=${user_key }">附件</a>
          	</td>
        	<td align="center" nowrap="nowrap">
        	<a href="http://192.168.2.216:18080/inter/service/KonkaOrderInfoView.do?method=list&order_id=${cur.id}&licenses_sn=${licenses_sn}&user_id=${user_id}&user_key=${user_key }">${cur.trade_index}</a>
        	</td>
          	<td align="center" nowrap="nowrap">${cur.order_date}</td>
          	<td align="right" nowrap="nowrap">${cur.r3_code}</td>
          	<td align="left" nowrap="nowrap">${cur.customer_name}</td>
          	<td align="center" nowrap="nowrap">${cur.customer_type_name}</td>
          	<td align="right" nowrap="nowrap">${cur.order_num}</td>
          	<td align="right" nowrap="nowrap">${cur.money}</td>
          	<td align="right" nowrap="nowrap">${cur.good_discount_price}</td>
          	<td align="center" nowrap="nowrap">${cur.order_state}</td>
          	<td align="center" nowrap="nowrap">${empty cur.r3_id?'未同步':cur.r3_id}</td>
          	<td align="center" nowrap="nowrap">${cur.is_change}</td>
          	<td align="center" nowrap="nowrap">${cur.is_delivered}</td>
          	<td align="center" nowrap="nowrap">${cur.vbedl}</td>
          	<td align="center" nowrap="nowrap">${cur.shipping_date}</td>
          	<td align="center" nowrap="nowrap">${cur.receiving_date}</td>
          	<td align="center" nowrap="nowrap">${cur.branch_name}</td>
          	<td align="center" nowrap="nowrap">${cur.handle_name}</td>
          	<td align="center" nowrap="nowrap">${cur.message_confirm_state}</td>
          	<td align="center" nowrap="nowrap">${cur.process_name}</td>
          	<td align="center" nowrap="nowrap">${cur.order_type}</td>
          	<td align="center" nowrap="nowrap">${cur.add_date}</td>
          	<td align="center" nowrap="nowrap">${cur.next_audit_role_name}</td>
         </tr>
      </c:forEach>
    </table>
    </div>
</body>
</html>