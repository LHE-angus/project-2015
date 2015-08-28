<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ include file="/commons/pages/taglibs.jsp" %>
<table> 
<tr>
<td width="5%" nowrap="nowrap" align="center">序号</td>
         <td nowrap="nowrap" align="center" >优惠券名称</td>
        <td width="5%" nowrap="nowrap" align="center">面额</td>
        <td width="5%" nowrap="nowrap" align="center">申请人</td>
        <td width="5%" nowrap="nowrap" align="center">申请日期</td>
        <td width="5%" nowrap="nowrap" align="center">组织</td>
        <td width="10%" nowrap="nowrap" align="center">使用品类</td>
        <td width="10%" nowrap="nowrap" align="center">使用属性</td>
        <td width="10%" nowrap="nowrap" align="center">可使用商品</td>
        <td width="5%" nowrap="nowrap" align="center">状态</td>
        <td width="10%" nowrap="nowrap" align="center">使用时间</td>
        <td width="10%" nowrap="nowrap" align="center">兑换人</td>
        <td width="10%" nowrap="nowrap" align="center">优惠券编码</td> 
        <td width="10%" nowrap="nowrap" align="center">优惠券密码</td>
</tr><c:forEach var="cur" items="${entityList}" varStatus="vs">
<tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
         <td align="left" nowrap="nowrap">${cur.title}</td>
          <td align="right" nowrap="nowrap">
          <fmt:formatNumber type="number" value="${cur.price}" maxFractionDigits="0"/>
          </td>
          <td align="left" nowrap="nowrap">${cur.map.apply_user_name}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.apply_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="left" nowrap="nowrap">${cur.map.group_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.pd_type}</td>
          <td align="left" nowrap="nowrap">${cur.map.goods_type}</td>
          <td align="left" nowrap="nowrap" >${cur.map.pd_sn}</td>
          <td align="left" nowrap="nowrap">
          <c:if test="${cur.info_state eq 0}">未使用</c:if>
          <c:if test="${cur.info_state eq 1}">已使用</c:if>  
          </td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.used_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="left" nowrap="nowrap">${cur.map.ec_user_name}</td>
          <td align="left" nowrap="nowrap">${cur.vouchers_code}</td>
          <td align="left" nowrap="nowrap">${cur.vouchers_pwd}</td>
        </tr>
</c:forEach>
</table>