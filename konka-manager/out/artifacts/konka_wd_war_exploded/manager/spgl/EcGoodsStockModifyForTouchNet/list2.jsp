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
        <td>
        <c:if test="${is_admin eq 1}">
        	<input type="button" class="but2" value="新 增" onclick="location.href='EcGoodsStockModifyForTouchNet.do?method=add2&goods_id=${af.map.goods_id}&mod_id=${af.map.mod_id}&own_sys=${af.map.own_sys}';" />
        </c:if>
        <c:if test="${is_fgs eq 1}">
        	<c:if test="${af.map.pd_dept ne 0 }">
        		<input type="button" class="but2" value="新 增" onclick="location.href='EcGoodsStockModifyForTouchNet.do?method=add2&goods_id=${af.map.goods_id}&mod_id=${af.map.mod_id}&own_sys=${af.map.own_sys}';" />
        	</c:if>
        	
        	<!-- <c:if test="${af.map.pd_dept eq 0 }">
        		<input type="button" value="修改库存" onclick="location.href='EcGoodsStockModifyForTouchNet.do?method=add2&goods_id=${af.map.goods_id}&mod_id=${af.map.mod_id}&own_sys=${af.map.own_sys}';" />
        	</c:if> -->
        </c:if>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1" >
    <table class="rtable2" width="100%" border="0" cellspacing="1" cellpadding="0">
      <tr class="tabtt1">
        <td width="5%"  nowrap="nowrap" align="center">序号</td>
        <td width="10%" nowrap="nowrap" align="center">商品名称</td>
        <td width="10%" nowrap="nowrap" align="center">产品所属</td>
        <td width="10%" nowrap="nowrap" align="center">仓库名称</td>
        <td width="10%" nowrap="nowrap" align="center" >仓库类型</td>
        <td width="10%" nowrap="nowrap" align="center" >仓库地址</td>
        <td width="10%" nowrap="nowrap" align="center" >库存</td>
        <td nowrap="nowrap" width="10%" align="center">操作</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap" >${vs.count}</td>
          <td align="left"  nowrap="nowrap" >${cur.map.pd_name}</td>
           <td align="left"  nowrap="nowrap" >
           	<c:if test="${af.map.pd_dept eq 0}">总部产品</c:if>
           	<c:if test="${af.map.pd_dept ne 0}">分公司产品</c:if>
           </td>
          <td align="left"  nowrap="nowrap" >${cur.map.store_name}</td>
          <td align="left"  nowrap="nowrap" >
          	<c:if test="${cur.map.store_type eq 0}">全国仓</c:if>
          	<c:if test="${cur.map.store_type eq 1}">区域仓</c:if>
          	<c:if test="${cur.map.store_type eq 2}">分公司仓</c:if>
          </td>
          <td align="left"  nowrap="nowrap" >${cur.map.store_addr}</td>
          <td align="right"  nowrap="nowrap" >${cur.stocks}</td>
          <td nowrap="nowrap" align="center"> 
          <c:if test="${is_admin eq 1}">
	          <input type="button" class="but2" value="修改" onclick=" location.href='EcGoodsStockModifyForTouchNet.do?method=edit2&store_id=${cur.store_id}&goods_id=${cur.goods_id}&store_type=${cur.map.store_type}&mod_id=${af.map.mod_id}&own_sys=${af.map.own_sys}';" />
	          <input type="button" class="but2" value="删除" onclick=" if(confirm('确定删除?')) location.href='EcGoodsStockModifyForTouchNet.do?method=delete2&store_id=${cur.store_id}&goods_id=${cur.goods_id}&mod_id=${af.map.mod_id}&own_sys=${af.map.own_sys}';" />
          </c:if>
           <c:if test="${is_fgs eq 1 && af.map.pd_dept ne 0}">
           	  <input type="button" class="but2" value="修改" onclick=" location.href='EcGoodsStockModifyForTouchNet.do?method=edit2&store_id=${cur.store_id}&goods_id=${cur.goods_id}&store_type=${cur.map.store_type}&mod_id=${af.map.mod_id}&own_sys=${af.map.own_sys}';" />
	          <input type="button" class="but2" value="删除" onclick=" if(confirm('确定删除?')) location.href='EcGoodsStockModifyForTouchNet.do?method=delete2&store_id=${cur.store_id}&goods_id=${cur.goods_id}&mod_id=${af.map.mod_id}&own_sys=${af.map.own_sys}';" /> 	
           </c:if>
          </td>
        </tr>
      </c:forEach>
      <tr>
        <td colspan="8" align="center"><html-el:button property="1" value="返 回" styleClass="but5" styleId="btn_back" onclick="location.href = '${ctx}/manager/spgl/PdShow.do?method=list&own_sys=${af.map.own_sys}&mod_id=905101' " /></td>
      </tr>
    </table>
  </div>
</div>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
