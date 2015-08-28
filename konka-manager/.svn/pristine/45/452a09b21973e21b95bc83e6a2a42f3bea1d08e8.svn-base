<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
</head>
<body>
  <div>
    <table width="100%" border="1" cellspacing="0" cellpadding="0">
      <tr class="tabtt1">
        <td >序号</td>
        <td >上报时间</td>
        <td >分公司</td>
        <td >客户名称</td>
        <td >客户R3编码</td>
        <td >客户类型</td>
        <td >细分类型</td>
        <td >上报人</td>
        <td >上报门店</td>
        <td >门店ID</td>
        <td >门店所属经办</td>
        <td >品牌</td>
         <td >规格段</td>
        <td >尺寸</td>
        <td >型号</td>
        <td >销量</td>
        <td >单价</td>
        <td >金额</td>
        <td >备注</td>
        <td >数据来源</td>
        <td >附件</td>
      </tr>
      <tbody> 
        <c:forEach var="cur" items="${allList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.report_time}" pattern="yyyy-MM-dd" /></td>
            <td align="left" nowrap="nowrap">${cur.map.vo_dept_name}</td>
            <td align="center">${cur.map.store_name}</td>
            <td align="center">${cur.map.r3_code}</td>
            <td align="center">${cur.map.par_customer_type_name}</td>
       		<td align="center">${cur.map.customer_type_name}</td>
       		<td align="left" nowrap="nowrap">${cur.report_name}</td>
       		<td align="left">${cur.dept_name}</td>
            <td align="center">${cur.dept_id}</td>
            <td align="left" nowrap="nowrap">${cur.map.l5_dept_name}</td>
            <td align="center" nowrap="nowrap">${cur.map.brand_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.type_name}</td>
            <td align="center" nowrap="nowrap">${cur.map.e_size}</td>
            <c:if test="${cur.model_id=='1001'}">
            	<td nowrap="nowrap">全部</td>
            </c:if>
            <c:if test="${cur.model_id!='1001'}">
            	 <td align="left" nowrap="nowrap">${cur.model_id}</td>
            </c:if>
            <td align="right" nowrap="nowrap">${cur.num}</td>
            <td align="right" nowrap="nowrap" class="kz-price-12">
            <fmt:formatNumber value="${cur.single_price}" type="currency"></fmt:formatNumber>
            </td>
            <td align="right" nowrap="nowrap" class="kz-price-12">
            <fmt:formatNumber value="${cur.price}" type="currency"></fmt:formatNumber>
            </td>
          	<td align="center">${cur.memo}</td>
          	<td align="center">
          		<c:if test="${cur.data_source==0}">电脑端</c:if>
          		<c:if test="${cur.data_source==1}">手机端</c:if>
          	</td>
            <td>
          	 	<c:forEach items="${cur.map.attachlist}" var="tt" varStatus="vs1">
          	 	<td>
          	 	<a href="http://qdgl.konka.com/${tt.save_path}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
          	 	</td>
         		</c:forEach>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</body>
</html>
