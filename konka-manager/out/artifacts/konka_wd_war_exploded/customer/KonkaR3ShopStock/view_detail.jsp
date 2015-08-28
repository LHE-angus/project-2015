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
           <c:if test="${direction eq 1 }">
          <td width="10%" align="center" nowrap="nowrap">门店名称</td>
           </c:if>
          <c:if test="${direction eq 0 }">
          <td width="10%" align="center" nowrap="nowrap">送达方名称</td>
          <td width="10%" align="center" nowrap="nowrap">送达方编码</td>
          </c:if>
          <td width="10%" align="center" nowrap="nowrap">型号名称</td>
          <td width="10%" align="center" nowrap="nowrap">时间</td>
          <td width="10%" align="center" nowrap="nowrap">数量</td>
          <c:if test="${direction eq 1 }">
	          <td width="10%" align="center" nowrap="nowrap">成本</td>
          </c:if>
          <td width="10%" align="center" nowrap="nowrap">金额</td>
          <td width="10%" align="center" nowrap="nowrap">方向</td>
          <td width="10%" align="center" nowrap="nowrap">类型</td>
         </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <c:if test="${cur.map.direction eq 1 }">
          		<td width="10%" align="center" nowrap="nowrap">${cur.map.terminal_name}</td>
            </c:if>
            <c:if test="${direction eq 0 }">
        	<td width="10%" align="center" nowrap="nowrap">${cur.map.sdf_name}</td>
        	<td width="10%" align="center" nowrap="nowrap">${cur.map.sdf_code}</td>
            </c:if>
            <td align="left" nowrap="nowrap">${cur.map.goods_name}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.opr_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td align="right" nowrap="nowrap">${cur.map.num}</td>
            <c:if test="${direction eq 1 }">
            	<td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.sale_cost}" type="currency" /></span></td>
            </c:if>
            <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur.map.money}" type="currency" /></span></td>
            <td align="center" nowrap="nowrap">
             <c:if test="${cur.map.direction eq 0 }">
             	进货
             </c:if>
             <c:if test="${cur.map.direction eq 1 }">
             	销售
             </c:if>
            </td>
            <td align="center" nowrap="nowrap">${cur.map.type }</td>
          </tr>
        </c:forEach>
      </table>
<!--       <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1"> -->
<!--         <tr> -->
<!--           <td align="center"> -->
<!--           	<input type="button" id="btn_back" class="but5" value="关闭" onclick="javascript:window.close();"/> -->
<!--           </td> -->
<!--         </tr> -->
<!--       </table> -->
      </div>
</div>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>