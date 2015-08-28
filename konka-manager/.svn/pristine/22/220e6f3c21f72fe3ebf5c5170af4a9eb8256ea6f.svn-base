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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input type="button" class="but2" value="新 增" onclick="location.href='EcGoodsPrice.do?method=add&goods_id_1=${goods_id_1}&price_type=${af.map.price_type}&mod_id=${af.map.mod_id}';" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1" >
   <div style="overflow-x:auto;">
    <table class="rtable2" width="100%" border="0" cellspacing="1" cellpadding="0">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap"   align="center">序号</td>
        <td width="10%" nowrap="nowrap" align="center">产品名称</td>
        <td align="center" width="10%" nowrap="nowrap">价格类型</td>
        <c:if test="${af.map.price_type ne 0}">
        <td align="center" width="10%" nowrap="nowrap">分公司</td>
        <td align="center" width="30%" nowrap="nowrap">区域</td>
        </c:if>
        <td align="center" width="10%" nowrap="nowrap">原价格</td>
        <td align="center" width="10%" nowrap="nowrap">销售价格</td>
        <td nowrap="nowrap"align="center">开始时间</td>
        <td nowrap="nowrap" align="center">结束时间</td>
         <td nowrap="nowrap" align="center">备注</td>
        <td nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap" >${vs.count}</td>
          <td align="left" nowrap="nowrap" >${cur.map.pd_name}</td>
          <td align="left" nowrap="nowrap"><c:if test="${cur.price_type eq 0}">全国价格</c:if>
            <c:if test="${cur.price_type eq 1}">区域价格</c:if>
            <c:if test="${cur.price_type eq 2}">分公司价格</c:if>
            <c:if test="${cur.price_type eq 3}">地市价格</c:if>
          </td>
          <c:if test="${cur.price_type ne 0}">
          <td align="center" nowrap="nowrap" >${cur.map.dept_name}</td>
          <td align="left" nowrap="nowrap" >${cur.map.areaList}</td>
          </c:if>
          <td align="right" nowrap="nowrap" >${cur.original_price}</td>
          <td align="right" nowrap="nowrap" >${cur.price}</td>
          <td align="right" nowrap="nowrap" ><fmt:formatDate value="${cur.start_time}" pattern="yyyy-MM-dd "/></td>
          <td align="right" nowrap="nowrap" ><fmt:formatDate value="${cur.end_time}" pattern="yyyy-MM-dd "/></td>
          <td align="right" nowrap="nowrap" >${cur.remarks}</td>
          <td align="center" nowrap="nowrap" ><!-- <input type="button" class="but2" value="修改" onclick="location.href='EcGoodsPrice.do?method=edit&id=${cur.id}&goods_id=${cur.goods_id}&price_type=${cur.price_type}&mod_id=${af.map.mod_id}';" />  -->
          <input type="button" value="删除" onclick=" if(confirm('确定删除?')) location.href='EcGoodsPrice.do?method=delete&id=${cur.id}&goods_id=${cur.goods_id}&price_type=${cur.price_type}&mod_id=${af.map.mod_id}';" />
          </td>
        </tr>
      </c:forEach>
      <tr>
        <td colspan="11" align="center"><html-el:button property="1" value="返 回" styleClass="but5" styleId="btn_back" onclick="location.href='${ctx}/manager/spgl/EcGoodsPrice.do?method=list&mod_id=${af.map.mod_id} ' " /></td>
      </tr>
    </table>
    </div>
  </div>
</div>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
