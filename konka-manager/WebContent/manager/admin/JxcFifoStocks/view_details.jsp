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
<style type="text/css">
	.tabtt1 td{background-color:#eff;}
</style>
<style type="text/css">
	select{font-family:Microsoft YAHEI;font-size:12px;}
	input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
	<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="25%">
          	<strong class="fb">&nbsp;&nbsp;客户名称：</strong>${konkaR3Shop.customer_name}
          </td>
          <td width="25%">
          	<strong class="fb">客户R3编码：</strong>${konkaR3Shop.r3_code}
          </td>
        </tr>
      </table>
      <div style="text-align:right;">单位：台、元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
         <tr class="tabtt1">
          <td width="4%" align="center" nowrap="nowrap">序号</td>
          <td width="4%" align="center" nowrap="nowrap">仓库</td>
          <td width="4%" align="center" nowrap="nowrap">型号</td>
          <td width="4%" align="center" nowrap="nowrap">数量</td>
          <td width="4%" align="center" nowrap="nowrap">金额</td>
          <td width="4%" align="center" nowrap="nowrap">时间</td>
          <td width="4%" align="center" nowrap="nowrap">类型</td>
          <td width="4%" align="center" nowrap="nowrap">方向</td>
         </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <c:if test="${stock_state eq 10 }">
          		<td width="10%" align="center" nowrap="nowrap">${cur.map.stock_in_store_name}</td>
          		<td width="10%" align="center" nowrap="nowrap">${cur.goods_model}</td>
          		<td width="10%" align="center" nowrap="nowrap">${cur.stock_in_num}</td>
          		<td width="10%" align="center" nowrap="nowrap">${cur.stock_in_price}</td>
          		<td width="10%" align="center" nowrap="nowrap">
          	<fmt:formatDate value="${cur.stock_in_date}" pattern="yyyy-MM-dd" />
          		</td>
          		<td width="10%" align="center" nowrap="nowrap">
          		     <c:choose>
						<c:when test="${cur.stock_in_type eq 10 }">地采</c:when>
						<c:when test="${cur.stock_in_type eq 20 }">集采</c:when>
						<c:when test="${cur.stock_in_type eq 30 }">初始化</c:when>
						<c:when test="${cur.stock_in_type eq 40 }">客户端采购</c:when>
						<c:when test="${cur.stock_in_type eq 50 }">盘盈</c:when>
						<c:when test="${cur.stock_in_type eq 60 }">零售通退货</c:when>
						<c:when test="${cur.stock_in_type eq 70 }">零售通销售无效化</c:when>
						<c:when test="${cur.stock_in_type eq 80 }">库存调拨（入）</c:when>
						<c:when test="${cur.stock_in_type eq 90 }">库存转仓（入）</c:when>
						<c:when test="${cur.stock_in_type eq 100 }">零售通导入（负数）</c:when>
						<c:when test="${cur.stock_in_type eq 110 }">销售退货</c:when>
						<c:when test="${cur.stock_in_type eq 120 }">分销退货</c:when>
						</c:choose>
				</td>
				<td width="4%" align="center" nowrap="nowrap">进仓</td>		
           </c:if>
<!--            这是出仓时候 -->
           <c:if test="${stock_state eq 20 }">
        		<td width="10%" align="center" nowrap="nowrap">${cur.map.stock_out_store_name}</td>
        		<td width="10%" align="center" nowrap="nowrap">${cur.goods_model}</td>
          		<td width="10%" align="center" nowrap="nowrap">${cur.stock_out_num}</td>
          		<td width="10%" align="center" nowrap="nowrap">${cur.stock_out_price}</td>
          		<td width="10%" align="center" nowrap="nowrap">
          	 	<fmt:formatDate value="${cur.stock_out_date}" pattern="yyyy-MM-dd" />
          		</td>
          		<td width="10%" align="center" nowrap="nowrap">
          		     <c:choose>
						<c:when test="${cur.stock_out_type eq 510 }">地采退货</c:when>
						<c:when test="${cur.stock_out_type eq 520 }">集采退货</c:when>
						<c:when test="${cur.stock_out_type eq 530 }">零售通销售</c:when>
						<c:when test="${cur.stock_out_type eq 540 }">分销</c:when>
						<c:when test="${cur.stock_out_type eq 550 }">专卖店销售</c:when>
						<c:when test="${cur.stock_out_type eq 560 }">盘亏</c:when>
						<c:when test="${cur.stock_out_type eq 570 }">库存调拨（出）</c:when>
						<c:when test="${cur.stock_out_type eq 580 }">库存转仓（出）</c:when>
						<c:when test="${cur.stock_out_type eq 590 }">产品初始化（负）</c:when>
						<c:when test="${cur.stock_out_type eq 600 }">零售通导入</c:when>
						<c:when test="${cur.stock_out_type eq 610 }">客户端销售</c:when>
						<c:when test="${cur.stock_out_type eq 620 }">采购退货</c:when>
						</c:choose>
				</td>
				<td width="4%" align="center" nowrap="nowrap">出货</td>
            </c:if>

			  <c:if test="${stock_state eq 30 }">
				  <td width="10%" align="center" nowrap="nowrap">${cur.map.stock_out_store_name}</td>
				  <td width="10%" align="center" nowrap="nowrap">${cur.goods_model}</td>
				  <td width="10%" align="center" nowrap="nowrap">${cur.stock_out_num}</td>
				  <td width="10%" align="center" nowrap="nowrap">${cur.stock_out_price}</td>
				  <td width="10%" align="center" nowrap="nowrap">
					  <fmt:formatDate value="${cur.stock_out_date}" pattern="yyyy-MM-dd" />
				  </td>
				  <td width="10%" align="center" nowrap="nowrap">
					  <c:choose>
						  <c:when test="${cur.stock_out_type eq 510 }">地采退货</c:when>
						  <c:when test="${cur.stock_out_type eq 520 }">集采退货</c:when>
						  <c:when test="${cur.stock_out_type eq 530 }">零售通销售</c:when>
						  <c:when test="${cur.stock_out_type eq 540 }">分销</c:when>
						  <c:when test="${cur.stock_out_type eq 550 }">专卖店销售</c:when>
						  <c:when test="${cur.stock_out_type eq 560 }">盘亏</c:when>
						  <c:when test="${cur.stock_out_type eq 570 }">库存调拨（出）</c:when>
						  <c:when test="${cur.stock_out_type eq 580 }">库存转仓（出）</c:when>
						  <c:when test="${cur.stock_out_type eq 590 }">产品初始化（负）</c:when>
						  <c:when test="${cur.stock_out_type eq 600 }">零售通导入</c:when>
						  <c:when test="${cur.stock_out_type eq 610 }">客户端销售</c:when>
						  <c:when test="${cur.stock_out_type eq 620 }">采购退货</c:when>
					  </c:choose>
				  </td>
				  <td width="4%" align="center" nowrap="nowrap">负卖</td>
			  </c:if>
          </tr>
        </c:forEach>
      </table>
      </div>
</div>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>