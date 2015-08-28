<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ include file="/commons/pages/taglibs.jsp" %>
<table> 
<tr>
<td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >购物券名称</td>
        <td width="5%" nowrap="nowrap" align="center">金额</td>
        <td width="10%" nowrap="nowrap" align="center">优惠券编码</td>
        <td width="10%" nowrap="nowrap" align="center">优惠券密码</td>
        <td width="10%" nowrap="nowrap" align="center">使用品类</td>
        <td width="10%" nowrap="nowrap" align="center">使用属性</td>
        <td width="15%" nowrap="nowrap" align="center">状态</td>
        <td width="20%" nowrap="nowrap" align="center">兑换时间</td>
        <td width="20%" nowrap="nowrap" align="center">兑换人</td>
</tr><c:forEach var="cur" items="${entityList}" varStatus="vs">
<tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.title}</td>
          <td align="right" nowrap="nowrap">
          <fmt:formatNumber type="number" value="${cur.price}" maxFractionDigits="0"/>
          </td>
          <td align="left" nowrap="nowrap">${cur.vouchers_code}</td>
          <td align="left" nowrap="nowrap">${cur.vouchers_pwd}</td>
          <td align="left" nowrap="nowrap">${cur.map.pd_type}</td>
          <td align="left" nowrap="nowrap">${cur.map.goods_type}</td>
          <td align="left" nowrap="nowrap">
          <c:if test="${cur.info_state eq 0}">未使用</c:if>
          <c:if test="${cur.info_state eq 1}">已使用</c:if>  
          </td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="left" nowrap="nowrap">${cur.map.ec_user_name}</td>
        </tr>
</c:forEach>
</table>