<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
</head>
<body>
	<div>
	<table width="100%" border="1" cellpadding="0" cellspacing="1" >
    	<tr>
      		<td nowrap="nowrap" align="center" width="3%">序号</td>
            <td nowrap="nowrap" align="center" width="5%">创建日期</td>
            <td nowrap="nowrap" align="center" width="5%">分销单号</td>
            <td nowrap="nowrap" align="center" width="5%">单据类型</td>
            <td nowrap="nowrap" align="center" width="5%">分销商名称</td>
            <td nowrap="nowrap" align="center" width="5%">分销商编码</td>
            <td nowrap="nowrap" align="center" width="5%">商品类型</td>
            <td nowrap="nowrap" align="center" width="5%">机型</td>
            <td nowrap="nowrap" align="center" width="5%">入库仓库</td>
            <td nowrap="nowrap" align="center" width="5%">数量</td>
            <td nowrap="nowrap" align="center" width="5%">销售单价</td>
            <td nowrap="nowrap" align="center" width="5%">总金额（元）</td>
            <td nowrap="nowrap" align="center" width="5%">折让金额（元）</td>
            <td nowrap="nowrap" align="center" width="5%">折后金额（元）</td>
            <td nowrap="nowrap" align="center" width="5%">关联销售单号</td>
            <td nowrap="nowrap" align="center" width="5%">网点确认</td>
    	</tr>
        <c:forEach items="${allList}" var="cur" varStatus="vs">
             <tr>
               <td nowrap="nowrap" align="center">${vs.count}</td>
               <td nowrap="nowrap" align="center"><fmt:formatDate value="${cur.OPR_DATE}" pattern="yyyy-MM-dd" /></td>
               <td nowrap="nowrap" align="center">
               	<span title="点击可查看" style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/JSubSellRec.do', 'view','bill_confirm=2&bill_id=${cur.BILL_ID}&'+$('#bottomPageForm').serialize())"><font class="blue12px">${cur.BILL_SN}</font></span>
               </td>
               <td nowrap="nowrap" align="center">
               	<c:if test="${cur.BILL_TYPE eq 40 }">分销</c:if>
               	<c:if test="${cur.BILL_TYPE eq 42 }">分销退货</c:if>
               </td>
               <td nowrap="nowrap" align="left">${cur.CUSTOMER_NAME }</td>
               <td nowrap="nowrap" align="left">${cur.R3_CODE }</td>
               <td nowrap="nowrap" align="center">${cur.TYPE_NAME}</td>
               <td nowrap="nowrap" align="center">${cur.GOODS_NAME }</td>
               <td nowrap="nowrap" align="center">${cur.STORE_NAME }</td>
               <td nowrap="nowrap" align="center">${cur.NUM }</td>
               <td nowrap="nowrap" align="right">${cur.PRICE }</td>
               <td nowrap="nowrap" align="right">${cur.MONEY }</td>
               <td nowrap="nowrap" align="right">${cur.DIS_MONEY }</td>
               <td nowrap="nowrap" align="right">${cur.REC_MONEY }</td>
               <td nowrap="nowrap" align="right">
               	<span title="点击可查看" style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, '${ctx}/customer/manager/JSubSellRec.do', 'view','bill_confirm=2&r_bill_id=${cur.R_BILL_ID}&'+$('#bottomPageForm').serialize())"><font class="blue12px">${cur.R_BILL_SN}</font></span>
               </td>
               <td nowrap="nowrap" align="center">
               	<c:if test="${cur.STATUS eq 0}">待确认</c:if>
               	<c:if test="${cur.STATUS eq 1}">已确认</c:if>
               </td>
             </tr>
             <c:if test="${vs.last eq true}">
       		<c:set var="i" value="${vs.count}" />
     		  </c:if>
           </c:forEach>
  	</table>
	</div>
</body>
</html>