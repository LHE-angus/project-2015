<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ include file="/commons/pages/taglibs.jsp" %>
<table> 
<tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >所属组织</td>
        <td nowrap="nowrap" width="10%" align="center">客户名称</td>
        <td width="10%" nowrap="nowrap" align="center">客户编码</td>
        <td width="10%" nowrap="nowrap" align="center">R3编码</td>
        <td width="10%" nowrap="nowrap" align="center">客户类型</td>
        <td width="10%" nowrap="nowrap" align="center">绑定会员</td>
        <td width="10%" nowrap="nowrap" align="center">状态</td>
        <td width="10%" nowrap="nowrap" align="center">添加人</td>
        <td width="10%" nowrap="nowrap" align="center">添加时间</td>
      </tr><c:forEach var="cur" items="${entityList}" varStatus="vs">
         <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.map.group_name}</td>
          <td align="left" nowrap="nowrap">${cur.cust_name}</td>
          <td align="left" nowrap="nowrap">${cur.cust_code}</td> 
          <td align="left" nowrap="nowrap">${cur.r3_code}</td>
          <td align="left" nowrap="nowrap">
          <c:if test="${cur.cust_type eq 0}">R3客户</c:if>
          <c:if test="${cur.cust_type eq 1}">虚拟客户</c:if>
          </td>
          <td align="left" nowrap="nowrap">${cur.map.ec_user_names}</td>
          <td align="left" nowrap="nowrap">
          <c:if test="${cur.del_mark eq 0}">正常</c:if>
          <c:if test="${cur.del_mark eq 1}">已停用</c:if>
          </td>
          <td align="left" nowrap="nowrap">${cur.map.add_user_name}</td> 
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
        </tr>
</c:forEach>
</table>