<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>进货登记</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <%@ include file="/commons/pages/messages.jsp" %>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="2"  bgcolor="#CCCCCC" style="font-weight:bold;">商品信息</td>
        </tr>
        <tr>
          <td width="17%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>商品类型：</td>
          <td width="83%" align="left">
          		<c:forEach var="cur" items="${list10001}" varStatus="vs">
          		   <c:if test="${af.map.goods_type eq cur.type_id}">${cur.type_name}</c:if>
          		</c:forEach>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>商品名称：</td>
          <td align="left">${af.map.goods_name}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">商品条码：</td>
          <td align="left">${af.map.goods_sn}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>商品单位：</td>
          <td align="left">
          		<c:forEach var="cur" items="${list10002}" varStatus="vs">
          		   <c:if test="${af.map.goods_unit eq cur.type_id}">${cur.type_name}</c:if>
          		</c:forEach>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>商品状态：</td>
          <td align="left"><c:if test="${af.map.goods_stutes eq 0}"><span style="color:green;">上架</span></c:if>
	          	<c:if test="${af.map.goods_stutes eq 1}"><span style="color:#f00;">下架</span></c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">商品描述：</td>
          <td align="left">${af.map.goods_desc}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>起初库存：</td>
          <td align="left">${af.map.init_count}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>建议销售单价（元）：</td>
          <td align="left"><fmt:formatNumber value="${af.map.sell_price}" pattern="0.00" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>康佳商品标识：</td>
          <td align="left"><c:if test="${af.map.is_konka eq 0}"><span style="color:green;">否</span></c:if>
	          	<c:if test="${af.map.is_konka eq 1}"><span style="color:#f00;">是</span></c:if></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" /></td>
        </tr>
      </table>
  </div>
</div>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
